"use client";

import Image from "next/image";
import xIcon from "@/public/x-mark.svg";

function Sidebar(
  {
    className,
    hideSidebar,
    showSidebar,
    toggleClasses,
    hospital,
    onValueChange,
  },
) {
  if (!hospital.inventory) {
    return;
  }

  let classes = showSidebar ? className : [className, toggleClasses];
  console.log(JSON.stringify(hospital));
  let { inventory, requiredInventory } = hospital;
  return (
    <section className={classes}>
      <div className="flex">
        <button onClick={hideSidebar}>
          <Image src={xIcon} alt="something" />
        </button>
        <h1>Inventory</h1>
      </div>
      <div className="flex flex-col">
        {Object.entries(inventory).map(([organ, amount]) => (
          <div key={organ} className="flex flex-row">
            <label htmlFor={organ}>{organ}</label>
            <div className="flex flex-row rounded-lg border-green-400 border-4">
              <input
                className="bg-transparent"
                id={organ}
                type="number"
                step="1"
                defaultValue={amount}
                onChange={(e) =>
                  onValueChange(e, hospital, organ)}
              />
              <span className="">/</span>
              <p className="">{requiredInventory[organ]}</p>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}

export default Sidebar;
