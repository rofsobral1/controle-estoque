"use client";

import Link from "next/link";
import { Users, Package, ShoppingCart, UserCog, LayoutDashboard } from "lucide-react";

const menuItems = [
 { label: "Clientes", href: "/clientes", icon: <Users size={40} /> },
  { label: "Usuários", href: "/usuarios", icon: <UserCog size={40} /> },
  { label: "Fornecedores", href: "/fornecedores", icon: <UserCog size={40} /> },
  { label: "Produtos", href: "/produtos", icon: <Package size={40} /> },
  { label: "Vendas", href: "/vendas", icon: <ShoppingCart size={40} /> },
];

export default function MenuGrid() {
  return (
    <div className="grid grid-cols-2 md:grid-cols-4 gap-6 justify-items-center items-center mt-10">
      {menuItems.map((item) => (
        <Link
          key={item.label}
          href={item.href}
          className="flex flex-col items-center justify-center w-40 h-40 bg-white shadow-md rounded-2xl hover:bg-blue-500 hover:text-white transition duration-300 ease-in-out cursor-pointer"
        >
          {item.icon}
          <span className="mt-2 text-base font-semibold text-gray-800 group-hover:text-white">
            {item.label}
          </span>
        </Link>
      ))}
    </div>
  );
}
