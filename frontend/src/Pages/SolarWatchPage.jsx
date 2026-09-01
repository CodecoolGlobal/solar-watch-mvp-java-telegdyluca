import {useState} from "react";
import {getSunriseSunset} from "../client/sunriseSunset.js";
import "../styles/SolarWatch.css";


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
        <div className="solar-page">
            <div className="solar-card">
                <h1>Solar Watch</h1>
                <form className="solar-form" onSubmit={handleSubmit}>
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
                </form>

                    {error ? <p className="solar-error">{error}</p> : null}
                    {sunData ? (
                            <div className="solar-result">
                                <p className="solar-result-city">{sunData.city}</p>
                                <div className="solar-result-grid">
                                    <div className="solar-result-item">
                                        <span className="solar-result-icon">🌅</span>
                                        <span className="solar-result-label">Sunrise</span>
                                        <span className="solar-result-value">{sunData.sunrise}</span>
                                    </div>
                                    <div className="solar-result-item">
                                        <span className="solar-result-icon">🌇</span>
                                        <span className="solar-result-label">Sunset</span>
                                        <span className="solar-result-value">{sunData.sunset}</span>
                                    </div>
                                </div>
                            </div>
                        ) : null}
            </div>
        </div>
    )
}

export default SolarWatchPage;