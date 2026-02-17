"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { apiFetch } from "@/services/api";
import { EyeIcon, EyeSlashIcon } from "@heroicons/react/24/outline";
import { motion } from "framer-motion";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [mostrarSenha, setMostrarSenha] = useState(false);
  const [erro, setErro] = useState("");
  const router = useRouter();

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    setErro("");

    try {
      const res = await apiFetch("/auth/login", {
        method: "POST",
        body: JSON.stringify({ email, senha }),
        headers: { "Content-Type": "application/json" },
      });

      if (res.token) {
        localStorage.setItem("token", res.token);
        router.push("/dashboard");
      } else {
        setErro(res.message || "Credenciais inválidas.");
      }
    } catch (err: any) {
      console.error(err);
      setErro(err.message || "Erro ao fazer login.");
    }
  }

  return (
    <div
      className="min-h-screen flex relative overflow-hidden"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
      }}
    >
      {/* Efeito de círculos decorativos no fundo */}
      <div className="absolute w-96 h-96 bg-blue-500/20 rounded-full blur-3xl top-10 left-[-100px] animate-pulse" />
      <div className="absolute w-80 h-80 bg-purple-500/20 rounded-full blur-3xl bottom-10 right-[-100px] animate-pulse" />

      {/* Layout em duas colunas */}
      <div className="flex flex-1 relative z-10">
        {/* Coluna esquerda - Frame de Login */}
        <div className="w-full lg:w-1/2 flex items-center justify-center p-8">
          <div className="bg-white/10 backdrop-blur-xl shadow-2xl rounded-2xl w-full max-w-sm p-8 border border-white/20">
            {/* Logo / Título */}
            <div className="flex flex-col items-center text-center mb-6">
              <h1 className="text-3xl font-extrabold text-white">
                Gestão de Estoque
              </h1>
              <p className="text-gray-200 text-sm mt-1">
                Faça login para continuar
              </p>
            </div>

            {/* Formulário */}
            <form onSubmit={handleSubmit} className="space-y-4">
              {erro && (
                <div className="p-2 bg-red-500/10 text-red-400 text-xs rounded-md text-center border border-red-400/30">
                  {erro}
                </div>
              )}

              {/* E-mail */}
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

              {/* Senha */}
              <div className="space-y-1">
                <label className="block text-xs font-medium text-gray-200">
                  Senha
                </label>
                <div className="relative">
                  <input
                    type={mostrarSenha ? "text" : "password"}
                    placeholder="••••••••"
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                    className="w-full px-3 py-2 pr-10 border border-white/20 rounded-lg text-sm text-white placeholder-gray-300 bg-white/10 focus:outline-none focus:ring-2 focus:ring-blue-400"
                    required
                  />
                  <button
                    type="button"
                    onClick={() => setMostrarSenha(!mostrarSenha)}
                    className="absolute inset-y-0 right-3 flex items-center text-gray-300 hover:text-gray-100"
                  >
                    {mostrarSenha ? (
                      <EyeSlashIcon className="w-5 h-5" />
                    ) : (
                      <EyeIcon className="w-5 h-5" />
                    )}
                  </button>
                </div>
              </div>

              {/* Botão Entrar */}
              <button
                type="submit"
                className="w-full bg-blue-600 text-white py-2.5 rounded-lg font-semibold text-sm shadow-md hover:bg-blue-500 transition-colors duration-300"
              >
                Entrar
              </button>
              <div className="text-right">
                <button
                  type="button"
                  onClick={() => router.push("/esqueceuSenha")}
                  className="text-xs text-blue-300 hover:text-blue-200 transition-colors"
                >
                  Esqueceu a senha?
                </button>
              </div>
            </form>

            {/* Rodapé */}
            <p className="mt-6 text-center text-[10px] text-gray-300">
              © {new Date().getFullYear()}{" "}
              <span className="text-blue-400 font-medium">STI Soluções</span>.{" "}
              Todos os direitos reservados.
            </p>
          </div>
        </div>

        {/* Coluna direita - Animação com o nome da empresa aumentado */}
        <div className="hidden lg:flex w-1/2 items-center justify-center p-8">
          <motion.div
            className="text-center"
            initial={{ opacity: 0, y: 40, scale: 0.95 }}
            animate={{ opacity: 1, y: 0, scale: 1 }}
            transition={{ duration: 0.8 }}
          >
            {/* título muito maior e responsivo */}
            <motion.h1
              className="font-extrabold text-transparent bg-clip-text drop-shadow-[0_0_8px_rgba(255,255,255,0.5)]"
              style={{
                fontSize: "clamp(3.5rem, 10vw, 9rem)",
                lineHeight: 1,
                letterSpacing: "-0.03em",
                backgroundImage:
                  "linear-gradient(90deg, #4f8fbf, #2c5364, #203a43)", // gradiente harmônico
              }}
              animate={{ scale: [1, 1.18, 1] }}
              transition={{
                repeat: Infinity,
                duration: 3.6,
                ease: "easeInOut",
              }}
            >
              STI
            </motion.h1>

            <motion.p
              className="text-lg text-gray-200 mt-6"
              initial={{ opacity: 0, y: 10 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.6, duration: 0.9 }}
            >
              Soluções em TI
            </motion.p>
          </motion.div>
        </div>
      </div>
    </div>
  );
}
