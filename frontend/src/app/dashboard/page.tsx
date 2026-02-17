"use client";

import Link from "next/link";
import Header from "@/components/layout/Header"; // importa o Header pronto
import { Users, Package, ShoppingCart, UserCog } from "lucide-react";

const menuItems = [
  { label: "Clientes", href: "/clientes", icon: <Users size={40} /> },
  { label: "Usuários", href: "/usuarios", icon: <UserCog size={40} /> },
  { label: "Fornecedores", href: "/fornecedores", icon: <UserCog size={40} /> },
  { label: "Produtos", href: "/produtos", icon: <Package size={40} /> },
  { label: "Vendas", href: "/vendas", icon: <ShoppingCart size={40} /> },
];

export default function MenuGrid() {
  return (
    <div
      className="min-h-screen flex flex-col relative overflow-hidden"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)", // mesmo fundo da tela de login
      }}
    >
      {/* ====== HEADER REUTILIZADO ====== */}
      <Header />

      {/* ====== EFEITOS DE FUNDO ====== */}
      <div className="absolute w-96 h-96 bg-blue-500/20 rounded-full blur-3xl top-10 left-[-100px] animate-pulse" />
      <div className="absolute w-80 h-80 bg-purple-500/20 rounded-full blur-3xl bottom-10 right-[-100px] animate-pulse" />

      {/* ====== MARCA D'ÁGUA STI ====== */}
      <div className="absolute inset-0 flex items-center justify-center pointer-events-none">
        <span className="text-white text-[30rem] font-extrabold opacity-10 select-none">
          STI
        </span>
      </div>

      {/* ====== GRID DE MENU ====== */}
      <div className="flex-1 flex items-center justify-center p-8 z-10">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-8">
          {menuItems.map((item) => (
            <Link
              key={item.label}
              href={item.href}
              className="flex flex-col items-center justify-center w-40 h-40 
                         bg-white/10 backdrop-blur-md border border-white/20
                         shadow-xl rounded-2xl 
                         text-white hover:bg-blue-500/30 transition duration-300 ease-in-out cursor-pointer"
            >
              {item.icon}
              <span className="mt-3 text-base font-semibold">{item.label}</span>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
}
