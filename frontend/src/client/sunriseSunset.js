
const BASE_URL = `${import.meta.env.VITE_API_BASE_URL}`;

const getUriForSolarWatch = (city, date) => `${BASE_URL}/sunrise-sunset?city=${city}&date=${date}`;

const getToken = () => {
    const saved = localStorage.getItem("user");
    const user = saved ? JSON.parse(saved) : null;
    return user ? user.jwt : "";
}

export const getSunriseSunset = async (city, date) => {
    const response = await fetch(getUriForSolarWatch(city, date), {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${getToken()}`,
        },
    });

    if (!response.ok) {
        throw new Error("Get operation failed");
    }

    return response;
}

