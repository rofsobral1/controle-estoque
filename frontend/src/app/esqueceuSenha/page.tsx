"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

export default function EsqueceuSenhaPage() {
  const [email, setEmail] = useState("");
  const [mensagemErro, setMensagemErro] = useState("");
  const [loading, setLoading] = useState(false);

  const router = useRouter();

  async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setMensagemErro("");
    setLoading(true);

    try {
      const response = await fetch(
        `http://localhost:8080/api/recuperacao-senha/verificar-email?email=${encodeURIComponent(
          email
        )}`,
        {
          method: "POST",
        }
      );

      if (response.ok) {
        // E-mail encontrado → redireciona para nova senha
        router.push(`/novaSenha?email=${encodeURIComponent(email)}`);
      } else {
        // E-mail não encontrado
        setMensagemErro("Este e-mail não está cadastrado.");
      }
    } catch (error) {
      setMensagemErro("Erro ao conectar com o servidor.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div
      className="min-h-screen flex items-center justify-center"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
      }}
    >
      <div className="bg-white/10 backdrop-blur-xl shadow-2xl rounded-2xl w-full max-w-sm p-8 border border-white/20">
        <h1 className="text-2xl font-bold text-white text-center">
          Recuperar Senha
        </h1>

        <p className="text-gray-300 text-xs text-center mt-2">
          Informe seu e-mail para redefinir sua senha
        </p>

        <form onSubmit={handleSubmit} className="space-y-4 mt-6">
          {mensagemErro && (
            <div className="p-2 bg-red-500/10 text-red-400 text-xs rounded-md text-center border border-red-400/30">
              {mensagemErro}
            </div>
          )}

          <div className="space-y-1">
            <label className="block text-xs font-medium text-gray-200">
              E-mail
            </label>
            <input
              type="email"
              placeholder="exemplo@empresa.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-3 py-2 border border-white/20 rounded-lg text-sm text-white placeholder-gray-300 bg-white/10 focus:outline-none focus:ring-2 focus:ring-blue-400"
              required
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className={`w-full py-2.5 rounded-lg font-semibold text-sm shadow-md transition-colors duration-300
              ${
                loading
                  ? "bg-gray-500 cursor-not-allowed"
                  : "bg-blue-600 hover:bg-blue-500 text-white"
              }
            `}
          >
            {loading ? "Verificando..." : "Continuar"}
          </button>

          <button
            type="button"
            onClick={() => router.push("/login")}
            className="w-full text-xs text-gray-300 hover:text-gray-100 mt-2"
          >
            Voltar para o login
          </button>
        </form>
      </div>
    </div>
  );
}
