import Sidebar from "@/components/sidebar";
import dynamic from "next/dynamic";

const Map = dynamic(() => import("@/components/map"), {
  ssr: false,
});

export default function App() {
  return (
    <main className="h-screen w-screen">
      <Map />
    </main>
  );
}
