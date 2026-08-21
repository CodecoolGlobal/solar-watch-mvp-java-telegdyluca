
const BASE_URL = `${import.meta.env.VITE_API_BASE_URL}`;

const getUriForRegistration = () => `${BASE_URL}/user/register`;


export const registerUser = async (username, password) => {
    const response = await fetch(getUriForRegistration(), {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
        throw new Error("Create operation failed");
    }
    return response;
};