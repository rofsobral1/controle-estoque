"use client";

import { useState, useEffect } from "react";
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

interface Categoria {
  id: number;
  nome: string;
  descricao?: string;
}

export default function CadastrarProdutoPage() {
  const router = useRouter();

  const [formData, setFormData] = useState({
    nome: "",
    descricao: "",
    preco: "",
    quantidade: "",
    categoria: "",
  });

  const [categorias, setCategorias] = useState<Categoria[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const formatPrice = (value: string) => {
    let numericValue = value.replace(/[^\d.]/g, "");
    const parts = numericValue.split(".");
    if (parts.length > 2) numericValue = parts[0] + "." + parts[1];
    if (numericValue.length > 10) numericValue = numericValue.slice(0, 10);
    if (numericValue.includes(".")) {
      const [intPart, decPart] = numericValue.split(".");
      numericValue = intPart + "." + decPart.slice(0, 2);
    }
    return numericValue;
  };

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    let formattedValue = value;

    if (name === "preco") formattedValue = formatPrice(value);
    else if (name === "quantidade") formattedValue = value.replace(/\D/g, "");

    setFormData({ ...formData, [name]: formattedValue });
  };

  const handleClear = () => {
    setFormData({
      nome: "",
      descricao: "",
      preco: "",
      quantidade: "",
      categoria: "",
    });
    setError(null);
    setSuccess(false);
  };

  useEffect(() => {
    const fetchCategorias = async () => {
      try {
        const data: Categoria[] = await apiFetch("/categorias");
        setCategorias(data);
      } catch (error) {
        console.error("Erro ao carregar categorias:", error);
      }
    };
    fetchCategorias();
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setSuccess(false);

    const hasEmptyField = Object.values(formData).some((value) => !value.trim());
    if (hasEmptyField) {
      setError("Todos os campos devem ser preenchidos.");
      setLoading(false);
      return;
    }

    const payload = {
      ...formData,
      preco: parseFloat(formData.preco),
      quantidade: parseInt(formData.quantidade),
      categoriaId: parseInt(formData.categoria),
    };

    try {
      await apiFetch("/produtos", {
        method: "POST",
        body: JSON.stringify(payload),
      });
      setSuccess(true);
      setTimeout(() => router.push("/produtos"), 1500);
    } catch (err: any) {
      setError(err.message || "Erro ao salvar produto");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      className="flex min-h-screen w-full text-sm text-gray-100 relative overflow-hidden"
      style={{ background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)" }}
    >
      {/* Marca d'água STI */}
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
            Cadastro de Produto
          </h1>

          <form onSubmit={handleSubmit} className="space-y-8">
            <div>
              <h2 className="text-lg font-semibold text-white mb-4">
                Dados do Produto
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
                  type="text"
                  name="descricao"
                  value={formData.descricao}
                  onChange={handleChange}
                  placeholder="Descrição"
                  className="w-full p-3 rounded-lg bg-white/10 text-white placeholder-gray-400 border border-white/30 text-sm focus:ring-2 focus:ring-blue-400"
                />
                <input
                  type="text"
                  name="preco"
                  value={formData.preco}
                  onChange={handleChange}
                  placeholder="Preço (0.00)"
                  className="w-full p-3 rounded-lg bg-white/10 text-white placeholder-gray-400 border border-white/30 text-sm focus:ring-2 focus:ring-blue-400"
                />
                <input
                  type="text"
                  name="quantidade"
                  value={formData.quantidade}
                  onChange={handleChange}
                  placeholder="Quantidade"
                  className="w-full p-3 rounded-lg bg-white/10 text-white placeholder-gray-400 border border-white/30 text-sm focus:ring-2 focus:ring-blue-400"
                />

                {/* Combobox com seta customizada */}
                <div className="relative w-full">
                  <select
                    name="categoria"
                    value={formData.categoria}
                    onChange={handleChange}
                    className="w-full p-3 rounded-lg bg-white/10 text-white border border-white/30 text-sm focus:ring-2 focus:ring-blue-400 appearance-none pr-10"
                    style={{ color: "white" }}
                  >
                    <option value="" className="bg-[#0f2027] text-white">
                      Selecione uma categoria
                    </option>
                    {categorias.map((cat) => (
                      <option
                        key={cat.id}
                        value={cat.id}
                        className="bg-[#0f2027] text-white"
                      >
                        {cat.nome}
                      </option>
                    ))}
                  </select>
                  {/* Seta para baixo */}
                  <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-3">
                    <svg
                      className="h-5 w-5 text-white"
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                    >
                      <path
                        fillRule="evenodd"
                        d="M5.23 7.21a.75.75 0 011.06.02L10 10.585l3.71-3.355a.75.75 0 111.02 1.1l-4 3.625a.75.75 0 01-1.02 0l-4-3.625a.75.75 0 01.02-1.06z"
                        clipRule="evenodd"
                      />
                    </svg>
                  </div>
                </div>
              </div>
            </div>

            {/* Feedback */}
            {error && <p className="text-red-400 font-bold text-xs">{error}</p>}
            {success && (
              <p className="text-green-300 text-xs">
                Produto cadastrado com sucesso!
              </p>
            )}

            {/* Botões */}
            <div className="flex flex-wrap justify-end gap-3">
              <button
                type="button"
                onClick={() => router.push("/produtos")}
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
                  "... "
                ) : (
                  <>
                    <CheckIcon className="h-5 w-5" />
                    <span className="hidden group-hover:inline ml-2">
                      Salvar
                    </span>
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
