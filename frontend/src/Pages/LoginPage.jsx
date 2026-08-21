import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../client/auth.js";
import { useAuth } from "../Context/AuthProvider.jsx";

function LoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const { login } = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await loginUser(username, password);
            const userData = await response.json()
            login(userData);
            navigate("/solar-watch");
        } catch (err) {
            setError("" + err);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Username"
            />
            <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password"
            />
            <button type="submit">Log in</button>
            {error ? <p>{error}</p> : null}
        </form>
    );
}

export default LoginPage;