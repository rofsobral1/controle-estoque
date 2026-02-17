"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";
import { apiFetch } from "@/services/api";
import {
  CheckIcon,
  ArrowLeftIcon,
  TrashIcon,
  Bars3Icon,
  XMarkIcon,
} from "@heroicons/react/24/solid";
import Sidebar from "@/components/layout/Sidebar";

type UsuarioForm = {
  nome: string;
  email: string;
  senha: string;
  perfil: string;
  ativo: boolean;
};

export default function CadastrarUsuarioPage() {
  const router = useRouter();

  const [formData, setFormData] = useState<UsuarioForm>({
    nome: "",
    email: "",
    senha: "",
    perfil: "",
    ativo: true,
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const target = e.target as HTMLInputElement;
    const { name, type, value, checked } = target;

    if (type === "checkbox") {
      // checkbox será usado somente para 'ativo'
      setFormData((prev) => ({ ...prev, ativo: checked }));
    } else {
      setFormData((prev) => ({
        ...prev,
        // name é garantido ser uma key de UsuarioForm quando usado corretamente
        [name as keyof UsuarioForm]: value,
      }));
    }
  };

  const handleClear = () => {
    setFormData({
      nome: "",
      email: "",
      senha: "",
      perfil: "",
      ativo: true,
    });
    setError(null);
    setSuccess(false);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setSuccess(false);

    // Aqui usamos keyof para deixar o TypeScript feliz
    const requiredFields: (keyof Omit<UsuarioForm, "ativo">)[] = [
      "nome",
      "email",
      "senha",
      "perfil",
    ];

    const hasEmptyField = requiredFields.some(
      (field) => !String(formData[field]).trim()
    );

    if (hasEmptyField) {
      setError("Todos os campos devem ser preenchidos.");
      setLoading(false);
      return;
    }

    try {
      await apiFetch("/usuarios", {
        method: "POST",
        body: JSON.stringify(formData),
      });
      setSuccess(true);
      setTimeout(() => router.push("/usuarios"), 1200);
    } catch (err: any) {
      setError(err.message || "Erro ao cadastrar usuário.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      className="flex min-h-screen w-full text-sm text-gray-100 relative overflow-hidden"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
      }}
    >
      {/* marca d'água */}
      <div className="absolute inset-0 flex items-center justify-center pointer-events-none">
        <span className="text-white text-[30rem] font-extrabold opacity-10 select-none">
          STI
        </span>
      </div>

      {/* Sidebar Desktop */}
      <div className="hidden md:flex flex-shrink-0 w-64 h-screen sticky top-0 z-20">
        <Sidebar />
      </div>

      {/* Sidebar Mobile */}
      <div className="md:hidden z-20">
        {sidebarOpen && (
          <div
            className="fixed inset-0 z-40 flex"
            onClick={() => setSidebarOpen(false)}
          >
            <div className="fixed inset-0 bg-black/50" />
            <div
              className="relative w-64 bg-[#0f2027] p-4"
              onClick={(e) => e.stopPropagation()}
            >
              <button
                onClick={() => setSidebarOpen(false)}
                className="absolute top-4 right-4 text-white"
              >
                <XMarkIcon className="h-6 w-6" />
              </button>
              <Sidebar />
            </div>
          </div>
        )}

        <button
          onClick={() => setSidebarOpen(true)}
          className="fixed top-4 left-4 z-50 p-2 rounded-md bg-gray-700 text-white"
        >
          <Bars3Icon className="h-6 w-6" />
        </button>
      </div>

      {/* Conteúdo */}
      <main className="flex-1 flex flex-col items-center justify-start p-4 md:p-6 min-h-screen overflow-auto z-10">
        <div className="w-full max-w-4xl">
          <h1 className="text-2xl font-bold text-white mb-6 text-center drop-shadow-md">
            Cadastro de Usuário
          </h1>

          <form onSubmit={handleSubmit} className="space-y-8">
            <div>
              <h2 className="text-lg font-semibold text-white mb-4">
                Dados do Usuário
              </h2>

              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <input
                  type="text"
                  name="nome"
                  value={formData.nome}
                  onChange={handleChange}
                  placeholder="Nome"
                  className="w-full p-3 rounded-lg bg-white/10 text-white placeholder-gray-400 border border-white/30 text-sm focus:ring-2 focus:ring-blue-400"
                />

                <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  placeholder="Email"
                  className="w-full p-3 rounded-lg bg-white/10 text-white placeholder-gray-400 border border-white/30 text-sm focus:ring-2 focus:ring-blue-400"
                />

                <input
                  type="password"
                  name="senha"
                  value={formData.senha}
                  onChange={handleChange}
                  placeholder="Senha"
                  className="w-full p-3 rounded-lg bg-white/10 text-white placeholder-gray-400 border border-white/30 text-sm focus:ring-2 focus:ring-blue-400"
                />

                <select
                  name="perfil"
                  value={formData.perfil}
                  onChange={handleChange}
                  className="w-full p-3 rounded-lg bg-white/10 text-white border border-white/30 text-sm focus:ring-2 focus:ring-blue-400 cursor-pointer 
                    hover:bg-white/20 focus:bg-gray-800 focus:text-white"
                >
                  <option value="">Selecione o Perfil</option>
                  <option value="ADMIN">Administrador</option>
                  <option value="USER">Usuário</option>
                </select>

                <div className="flex items-center space-x-3">
                  <input
                    type="checkbox"
                    name="ativo"
                    checked={formData.ativo}
                    onChange={handleChange}
                    className="h-5 w-5 text-blue-600 border-gray-300 rounded"
                  />
                  <label className="text-white text-sm">Ativo</label>
                </div>
              </div>
            </div>

            {error && <p className="text-red-400 font-bold text-xs">{error}</p>}
            {success && (
              <p className="text-green-300 text-xs">Usuário cadastrado com sucesso!</p>
            )}

            <div className="flex flex-wrap justify-end gap-3">
              <button
                type="button"
                onClick={() => router.push("/usuarios")}
                className="group flex items-center justify-center px-4 py-2 rounded-lg bg-gray-700 text-white hover:bg-gray-600 transition text-sm"
              >
                <ArrowLeftIcon className="h-5 w-5" />
                <span className="hidden group-hover:inline ml-2">Voltar</span>
              </button>

              <button
                type="button"
                onClick={handleClear}
                className="group flex items-center justify-center px-4 py-2 rounded-lg bg-yellow-400 text-gray-900 hover:bg-yellow-500 transition text-sm"
              >
                <TrashIcon className="h-5 w-5" />
                <span className="hidden group-hover:inline ml-2">Limpar</span>
              </button>

              <button
                type="submit"
                disabled={loading}
                className="group flex items-center justify-center px-4 py-2 rounded-lg bg-blue-600 text-white font-medium shadow-md hover:bg-blue-700 transition disabled:opacity-50 text-sm"
              >
                {loading ? (
                  "..."
                ) : (
                  <>
                    <CheckIcon className="h-5 w-5" />
                    <span className="hidden group-hover:inline ml-2">Salvar</span>
                  </>
                )}
              </button>
            </div>
          </form>
        </div>
      </main>
    </div>
  );
}
