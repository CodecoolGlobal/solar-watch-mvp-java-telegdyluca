package com.codecool.solarwatch.service.sql;

import com.codecool.solarwatch.model.entity.Role;
import com.codecool.solarwatch.model.entity.UserEntity;
import com.codecool.solarwatch.model.payload.JwtResponse;
import com.codecool.solarwatch.model.payload.UserRequest;
import com.codecool.solarwatch.repository.UserRepository;
import com.codecool.solarwatch.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public UserEntity registerUser(UserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(getPassword(request));
        userEntity.setRoles(Set.of(Role.ROLE_USER));

        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()) {
            throw new IllegalArgumentException(format("Username %s is already taken", userEntity.getUsername()));
        }
        userRepository.save(userEntity);
        return userEntity;
    }

    public JwtResponse loginUser(UserRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return new JwtResponse(token, userDetails.getUsername(), roles);
    }

    private String getPassword(UserRequest request) {
        return encoder.encode(request.getPassword());
    }

    public UserEntity findCurrentUser() {
        UserDetails contextUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = contextUser.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(format("Could not find user %s in the repository", username)));
    }

    public void addRoleFor(UserEntity user, Role newRole) {
        Set<Role> updatedRoles = new HashSet<>(user.getRoles());
        updatedRoles.add(newRole);
        user.setRoles(updatedRoles);
        userRepository.save(user);
    }
}
