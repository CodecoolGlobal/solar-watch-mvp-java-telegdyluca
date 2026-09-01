import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../client/auth.js";
import { useAuth } from "../Context/AuthProvider.jsx";
import "../styles/Auth.css";

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
            const userData = await response.json();
            login(userData);
            navigate("/solar-watch");
        } catch (err) {
            setError("" + err);
        }
    };

    return (
        <div className="auth-page">
            <form className="auth-card" onSubmit={handleSubmit}>
                <h1>Log in</h1>
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
                {error ? <p className="auth-error">{error}</p> : null}
            </form>
        </div>
    );
}

export default LoginPage;