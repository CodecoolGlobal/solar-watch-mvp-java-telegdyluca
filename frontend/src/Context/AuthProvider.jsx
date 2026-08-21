import { createContext, useState, useCallback, useContext } from "react";

const AuthContext = createContext(null);

function AuthProvider({ children }) {
    const [user, setUser] = useState(() => {
        const saved = localStorage.getItem("user");
        if (saved) return JSON.parse(saved);

        return null;
    });

    const login = useCallback((userData) => {
        // userData { username, jwt, roles }
        localStorage.setItem("user", JSON.stringify(userData));
        setUser(userData);
    }, []);

    const logout = useCallback(() => {
        localStorage.removeItem("user");
        setUser(null);
    }, []);

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => useContext(AuthContext);

export default AuthProvider;