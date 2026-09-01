import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../client/auth.js";
import "../styles/Auth.css";

function RegistrationPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await registerUser(username, password);
            navigate("/login");
        } catch (err) {
            setError("" + err);
        }
    };

    return (
        <div className="auth-page">
            <form className="auth-card" onSubmit={handleSubmit}>
                <h1>Registration</h1>
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
                <button type="submit">Register</button>
                {error ? <p className="auth-error">{error}</p> : null}
            </form>
        </div>
    );
}

export default RegistrationPage;