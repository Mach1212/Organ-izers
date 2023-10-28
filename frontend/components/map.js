"use client";

import "leaflet/dist/leaflet.css";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import { useState } from "react";

const Map = () => {
    const [coord, setCoord] = useState([51.505, -0.09]);

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            setCoord([position.coords.latitude, position.coords.longitude]);
        });
    }

    return (
        <MapContainer
            center={coord}
            zoom={13}
            scrollWheelZoom={false}
            style={{ height: "100%", width: "100%" }}
        >
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
        </MapContainer>
    );
};

export default Map;
