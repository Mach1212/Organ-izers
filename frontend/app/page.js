"use client";

import Sidebar from "@/components/sidebar";
import dynamic from "next/dynamic";
import { useEffect, useState } from "react";
import { useMapEvents } from "react-leaflet";
import { Surreal } from "surrealdb.js";

const Map = dynamic(() => import("@/components/map"), {
  ssr: false,
});

const db = new Surreal();
const getHospitals = () => db.select("Hospital");

export default function App() {
  let [hospitals, setHospitals] = useState([]);
  useEffect(() => {
    const getData = async () => {
      await setupDB();
      const hospitals = await getHospitals();
      setHospitals(hospitals);
    };
    getData();
  }, []);

  let [showSidebar, setShowSidebar] = useState(false);
  let [sidebarHospital, setSidebarHospital] = useState({});

  const visibleSidebar = (hospital) => {
    setSidebarHospital(hospital);
    setShowSidebar(true);
  };
  const hideSidebar = () => {
    setShowSidebar(false);
  };

  return (
    <main className="h-screen w-screen flex">
      <div className="h-full w-full">
        <Map
          visibleSidebar={visibleSidebar}
          hospitals={hospitals}
        />
      </div>
      <Sidebar
        className="w-80 h-full bg-neutral-100"
        toggleClasses="-right-full absolute hidden"
        hideSidebar={hideSidebar}
        showSidebar={showSidebar}
        hospital={sidebarHospital}
      />
    </main>
  );
}

async function setupDB() {
  try {
    // Connect to the database

    const namespace = "namespace-name";
    const database = "database-name";
    await db.connect(`ws://localhost:8082/rpc`, {
      // Set the namespace and database for the connection
      ns: namespace,
      db: database,

      // Set the authentication details for the connection
      auth: {
        NS: namespace,
        DB: database,
        user: "hacknc",
        pass: "a_totally_secure_password_is_really_long",
      },
    });
  } catch (e) {
    console.error("ERROR", e);
  }
}
