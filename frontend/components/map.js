"use client";

import "leaflet/dist/leaflet.css";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";

const Map = () => {
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
            <Marker
                position={[35.909740, -79.047291]}
            >
                <Popup>
                    A pretty CSS3 popup. <br /> Easily customizable.
                </Popup>
            </Marker>
        </MapContainer>
    );
};

export default Map;
