"use client";

import Image from "next/image";
import xIcon from "@/public/x-mark.svg";

function Sidebar(
  { className, hideSidebar, showSidebar, toggleClasses, hospital },
) {
  let classes = showSidebar ? className : [className, toggleClasses];
  return (
    <section className={classes}>
      <div className="flex">
        <button onClick={hideSidebar}>
          <Image src={xIcon} alt="something" />
        </button>
        <h1>Inventory</h1>
      </div>
      );
}

      export default Sidebar;
