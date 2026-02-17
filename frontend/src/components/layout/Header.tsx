"use client";

import { useRouter } from "next/navigation";
import { motion } from "framer-motion";

export default function Header() {
  const router = useRouter();

  const handleLogout = () => {
    localStorage.removeItem("token"); // Remove o token de autenticação
    router.push("/login"); // Redireciona para a tela de login
  };

  return (
    <header className="w-full h-16 flex items-center justify-between px-6 shadow-md z-20 bg-transparent">
      {/* Título com efeito hover */}
      <h2 className="text-lg font-semibold text-white transition-transform duration-300 hover:scale-105 hover:text-blue-300">
        Gestão de Estoque
      </h2>

      <div className="flex items-center gap-4">
        {/* Texto animado da empresa com gradiente e borda luminosa */}
        <motion.span
          className="font-extrabold text-transparent bg-clip-text drop-shadow-[0_0_8px_rgba(255,255,255,0.5)]"
          style={{
            backgroundImage: "linear-gradient(90deg, #4f8fbf, #2c5364, #203a43)",
            fontSize: "1.5rem",
            lineHeight: 1,
            letterSpacing: "-0.02em",
          }}
          animate={{ scale: [1, 1.1, 1] }}
          transition={{ repeat: Infinity, duration: 3, ease: "easeInOut" }}
        >
          STI
        </motion.span>

        {/* Botão Sair moderno */}
        <button
          onClick={handleLogout}
          className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded-lg transition-all duration-300 shadow-sm hover:shadow-lg transform hover:-translate-y-0.5"
        >
          Sair
        </button>
      </div>
    </header>
  );
}
