"use client";

import { useRequireAuth } from "@/hooks/useRequireAuth";
import Header from "@/components/layout/Header";
import FornecedoresMenuGrid from "@/components/layout/FornecedoresMenuGrid";

export default function ClientesPage() {
  useRequireAuth();

  return (
    <div
      className="min-h-screen flex flex-col relative overflow-hidden"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)", // mesmo fundo do dashboard
      }}
    >
      {/* ====== EFEITOS DE FUNDO ====== */}
      <div className="absolute w-96 h-96 bg-blue-500/20 rounded-full blur-3xl top-10 left-[-100px] animate-pulse" />
      <div className="absolute w-80 h-80 bg-purple-500/20 rounded-full blur-3xl bottom-10 right-[-100px] animate-pulse" />

      {/* ====== MARCA D'ÁGUA STI ====== */}
      <div className="absolute inset-0 flex items-center justify-center pointer-events-none">
        <span className="text-white text-[30rem] font-extrabold opacity-10 select-none">
          STI
        </span>
      </div>

      {/* ====== HEADER ====== */}
      <Header />

      {/* ====== CONTEÚDO ====== */}
      <main className="flex-1 flex flex-col items-center justify-start p-8 z-10">
        <h1 className="text-3xl font-bold text-white mb-8">Fornecedores</h1>
        <FornecedoresMenuGrid />
      </main>
    </div>
  );
}
