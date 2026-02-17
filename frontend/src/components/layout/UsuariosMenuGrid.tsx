"use client";

import { useRouter } from "next/navigation";
import { Users, PlusCircle, ArrowLeft } from "lucide-react";

const usuariosMenuItems = [
  {
    label: "Listar Usuários",
    icon: <Users size={40} />,
    onClickPath: "/usuarios/listar",
  },
  {
    label: "Cadastrar Usuários",
    icon: <PlusCircle size={40} />,
    onClickPath: "/usuarios/cadastrar",
  },
  {
    label: "Voltar",
    icon: <ArrowLeft size={40} />,
    onClickPath: "/dashboard",
  },
];

export default function UsuariosMenuGrid() {
  const router = useRouter();

  return (
    <div className="flex justify-center mt-10 z-10">
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8 justify-items-center items-center">
        {usuariosMenuItems.map((item) => (
          <div
            key={item.label}
            onClick={() => router.push(item.onClickPath)}
            className="flex flex-col items-center justify-center w-40 h-40
                       bg-white/10 backdrop-blur-md border border-white/20
                       shadow-xl rounded-2xl text-white
                       hover:bg-indigo-500/30 transition duration-300 ease-in-out cursor-pointer"
          >
            {item.icon}
            <span className="mt-3 text-base font-semibold text-center">
              {item.label}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
}
