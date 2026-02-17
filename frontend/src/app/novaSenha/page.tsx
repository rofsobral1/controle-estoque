"use client";

import { useState } from "react";
import { useSearchParams, useRouter } from "next/navigation";

export default function NovaSenhaPage() {
  const searchParams = useSearchParams();
  const email = searchParams.get("email") || "";
  const [senha, setSenha] = useState("");
  const [confirmarSenha, setConfirmarSenha] = useState("");
  const [mensagem, setMensagem] = useState("");
  const router = useRouter();

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();

    if (senha !== confirmarSenha) {
      setMensagem("As senhas não coincidem");
      return;
    }

    try {
      const res = await fetch(
        `http://localhost:8080/api/recuperar-senha/atualizar-senha?email=${encodeURIComponent(email)}&novaSenha=${encodeURIComponent(senha)}`,
        { method: "POST" }
      );

      if (res.ok) {
        setMensagem("Senha atualizada com sucesso");
        setTimeout(() => router.push("/login"), 2000);
      } else {
        const text = await res.text();
        setMensagem(text);
      }
    } catch (err) {
      setMensagem("Erro ao conectar com o servidor");
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center" style={{ background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)" }}>
      <div className="bg-white/10 backdrop-blur-xl shadow-2xl rounded-2xl w-full max-w-sm p-8 border border-white/20">
        <h1 className="text-2xl font-bold text-white text-center">Nova Senha</h1>

        {mensagem && (
          <div className="p-2 bg-green-500/10 text-green-400 text-xs rounded-md text-center border border-green-400/30 mt-2">
            {mensagem}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4 mt-6">
          <div className="space-y-1">
            <label className="block text-xs font-medium text-gray-200">Nova Senha</label>
            <input
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              className="w-full px-3 py-2 border border-white/20 rounded-lg text-sm text-white placeholder-gray-300 bg-white/10 focus:outline-none focus:ring-2 focus:ring-blue-400"
              required
            />
          </div>

          <div className="space-y-1">
            <label className="block text-xs font-medium text-gray-200">Confirmar Senha</label>
            <input
              type="password"
              value={confirmarSenha}
              onChange={(e) => setConfirmarSenha(e.target.value)}
              className="w-full px-3 py-2 border border-white/20 rounded-lg text-sm text-white placeholder-gray-300 bg-white/10 focus:outline-none focus:ring-2 focus:ring-blue-400"
              required
            />
          </div>

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2.5 rounded-lg font-semibold text-sm shadow-md hover:bg-blue-500 transition-colors duration-300"
          >
            Atualizar Senha
          </button>
        </form>
      </div>
    </div>
  );
}
