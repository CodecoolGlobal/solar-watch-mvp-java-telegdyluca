
const BASE_URL = `${import.meta.env.VITE_API_BASE_URL}`;

const getUriForRegistration = () => `${BASE_URL}/user/register`;
const getUriForLogin = () => `${BASE_URL}/user/login`;


export const registerUser = async (username, password) => {
    const response = await fetch(getUriForRegistration(), {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
        throw new Error("Registration failed");
    }
    return response;
};

export const loginUser = async (username, password) => {
    const response = await fetch(getUriForLogin(), {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
        throw new Error("Log in failed");
    }
    return response;
};