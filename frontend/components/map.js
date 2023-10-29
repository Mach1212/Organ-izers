"use client";

import "leaflet/dist/leaflet.css";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import "leaflet-defaulticon-compatibility";
import "leaflet-defaulticon-compatibility/dist/leaflet-defaulticon-compatibility.css";

function Map({ visibleSidebar, hospitals }) {
    return (
        <MapContainer
            center={[35.909740, -79.047291]}
            zoom={13}
            scrollWheelZoom={false}
            style={{ height: "100%", width: "100%" }}
        >
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {hospitals.map((hospital) => (
                <Marker
                    key={hospital.name}
                    position={[hospital.latitude, hospital.longitude]}
                    eventHandlers={{ click: () => visibleSidebar(hospital) }}
                >
                    <Popup>
                        {hospital.name}
                    </Popup>
                </Marker>
            ))}
        </MapContainer>
    );
}

export default Map;
