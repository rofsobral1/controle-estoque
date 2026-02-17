"use client";

import { useRouter } from "next/navigation";
import { Users, PlusCircle, Trash2, ArrowLeft } from "lucide-react";

const clientesMenuItems = [
  {
    label: "Listar Clientes",
    icon: <Users size={40} />,
    onClickPath: "/clientes/listar",
  },
  {
    label: "Cadastrar Clientes",
    icon: <PlusCircle size={40} />,
    onClickPath: "/clientes/cadastrar",
  },
  {
    label: "Excluir Clientes",
    icon: <Trash2 size={40} />,
    onClickPath: "/clientes/excluir",
  },
  {
    label: "Voltar",
    icon: <ArrowLeft size={40} />,
    onClickPath: "/dashboard",
  },
];

export default function ClientesMenuGrid() {
  const router = useRouter();

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 justify-items-center items-center mt-10 z-10">
      {clientesMenuItems.map((item) => (
        <div
          key={item.label}
          onClick={() => router.push(item.onClickPath)}
          className="flex flex-col items-center justify-center w-40 h-40
                     bg-white/10 backdrop-blur-md border border-white/20
                     shadow-xl rounded-2xl text-white
                     hover:bg-blue-500/30 transition duration-300 ease-in-out cursor-pointer"
        >
          {item.icon}
          <span className="mt-3 text-base font-semibold">{item.label}</span>
        </div>
      ))}
    </div>
  );
}
