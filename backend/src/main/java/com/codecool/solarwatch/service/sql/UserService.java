package com.codecool.solarwatch.service.sql;

import com.codecool.solarwatch.model.entity.Role;
import com.codecool.solarwatch.model.entity.UserEntity;
import com.codecool.solarwatch.model.payload.UserRequest;
import com.codecool.solarwatch.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void registerUser(UserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(getPassword(request));
        userEntity.setRoles(Set.of(Role.ROLE_USER));

        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()) {
            throw new IllegalArgumentException(format("Username %s is already taken", userEntity.getUsername()));
        }
        userRepository.save(userEntity);
    }

    public String getPassword(UserRequest request) {
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
