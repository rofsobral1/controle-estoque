"use client";

import { useRequireAuth } from "@/hooks/useRequireAuth";
import Header from "@/components/layout/Header";
import UsuariosMenuGrid from "@/components/layout/UsuariosMenuGrid";

export default function UsuariosPage() {
  useRequireAuth();

  return (
    <div
      className="min-h-screen flex flex-col relative overflow-hidden"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)", // mesmo fundo do dashboard
      }}
    >
      {/* Efeitos de fundo */}
      <div className="absolute w-96 h-96 bg-indigo-500/20 rounded-full blur-3xl top-10 left-[-100px] animate-pulse" />
      <div className="absolute w-80 h-80 bg-pink-500/20 rounded-full blur-3xl bottom-10 right-[-100px] animate-pulse" />

      {/* Header */}
      <Header />

      {/* Conteúdo */}
      <main className="flex-1 flex flex-col items-center justify-start p-8 z-10">
        <h1 className="text-3xl font-bold text-white mb-8">Usuários</h1>
        <UsuariosMenuGrid />
      </main>
    </div>
  );
}
