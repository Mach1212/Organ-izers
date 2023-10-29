"use client";

import Sidebar from "@/components/sidebar";
import dynamic from "next/dynamic";
import { useEffect, useState } from "react";
import { Surreal } from "surrealdb.js";
import { extend } from "lodash";

const Map = dynamic(() => import("@/components/map"), {
  ssr: false,
});

const db = new Surreal();
const hospitalTableName = "Hospital";
const getHospitals = () => db.select(hospitalTableName);

export default function App() {
  let [hospitals, setHospitals] = useState([]);
  useEffect(() => {
    const getData = async () => {
      await setupDB();
      const hospitals = await getHospitals();
      setHospitals(hospitals);
    };
    let promise = getData();
    // const setLiveQuery = async () => {
    //   await db.live(
    //     hospitalTableName,
    //     // The callback function takes an object with the "action" and "result" properties
    //     ({ action, result }) => {
    //       // action can be: "CREATE", "UPDATE", "DELETE" or "CLOSE"
    //       if (action === "CLOSE") return;
    //
    //       setHospitals(hospitals.extend([result]));
    //     },
    //   );
    // };
    // setLiveQuery();
  }, []);

  let [showSidebar, setShowSidebar] = useState(false);
  let [sidebarHospital, setSidebarHospital] = useState({});
  const onValueChange = (e, hospital, organ) => {
    const patchData = async () => {
      let result = await db.merge(hospital.id, {
        inventory: { [organ]: Number(e.target.value) },
      });
      console.log(result);
    };
    patchData();
  };

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
        onValueChange={onValueChange}
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
