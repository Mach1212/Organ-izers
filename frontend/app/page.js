"use client";

import Sidebar from "@/components/sidebar";
import dynamic from "next/dynamic";
import { useState } from "react";

const Map = dynamic(() => import("@/components/map"), {
  ssr: false,
});

export default function App() {
  let [showSidebar, setShowSidebar] = useState(true);
  const visibleSidebar = () => {
    console.log("show");
    setShowSidebar(true);
  };
  const hideSidebar = () => {
    console.log("hide");
    setShowSidebar(false);
  };

  return (
    <main className="h-screen w-screen flex">
      <div className="h-full w-full">
        <Map visibleSidebar={visibleSidebar} />
      </div>
      <Sidebar
        className="w-80 h-full bg-red-400"
        toggleClasses="-right-full absolute hidden"
        hideSidebar={hideSidebar}
        showSidebar={showSidebar}
      />
    </main>
  );
}
