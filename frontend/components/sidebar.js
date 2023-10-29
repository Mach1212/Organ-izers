"use client";

import Image from "next/image";
import xIcon from "@/public/x-mark.svg";

function Sidebar(
  { className, hideSidebar, showSidebar, toggleClasses },
) {
  let classes = showSidebar ? className : [className, toggleClasses];
  return (
    <section className={classes}>
      <button onClick={hideSidebar}>
        <Image src={xIcon} />
      </button>
    </section>
  );
}

export default Sidebar;
