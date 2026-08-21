import {useState} from "react";
import {getSunriseSunset} from "../client/sunriseSunset.js";


function SolarWatchPage() {
    const [city, setCity] = useState("");
    const [date, setDate] = useState("");
    const [sunData, setSunData] = useState(null);
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            const response = await getSunriseSunset(city, date);
            const data = await response.json();
            setSunData(data);

        } catch (err) {
            setError("" + err);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={city}
                onChange={(e) => setCity(e.target.value)}
                placeholder="City"
            />
            <input
                type="text"
                value={date}
                onChange={(e) => setDate(e.target.value)}
                placeholder="Date"
            />
            <button type="submit">Get sunrise and sunset times</button>
            {error ? <p>{error}</p> : null}
            {sunData ? (
                <div>
                    <p>City: {sunData.city}</p>
                    <p>Sunrise: {sunData.sunrise}</p>
                    <p>Sunset: {sunData.sunset}</p>
                </div>
            ) : null}
        </form>
    )
}

export default SolarWatchPage;