"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { useRequireAuth } from "@/hooks/useRequireAuth";
import Sidebar from "@/components/layout/Sidebar";
import { apiFetch } from "@/services/api";
import { ArrowLeft, Pencil, X, CheckCircle2, Menu } from "lucide-react";

interface Produto {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
  quantidade: number;
  categoria: string;
}

export default function ListarProdutosPage() {
  useRequireAuth();
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [loading, setLoading] = useState(true);
  const [produtoEditando, setProdutoEditando] = useState<Produto | null>(null);
  const [formData, setFormData] = useState<Produto | null>(null);
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const fetchProdutos = async () => {
      try {
        const data = await apiFetch("/produtos");
        setProdutos(data);
      } catch (error) {
        console.error("Erro ao buscar produtos:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchProdutos();
  }, []);

  const handleEditClick = (produto: Produto) => {
    setProdutoEditando(produto);
    setFormData({ ...produto });
  };

  const handleClose = () => {
    setProdutoEditando(null);
    setFormData(null);
  };

  const handleUpdate = async () => {
    if (!formData) return;
    try {
      await apiFetch(`/produtos/${formData.id}`, {
        method: "PUT",
        body: JSON.stringify(formData),
        headers: { "Content-Type": "application/json" },
      });

      setProdutos((prev) =>
        prev.map((p) => (p.id === formData.id ? formData : p))
      );
      handleClose();
    } catch (error) {
      console.error("Erro ao atualizar produto:", error);
    }
  };

  return (
    <div
      className="flex min-h-screen text-gray-800 text-sm relative overflow-hidden"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
      }}
    >
      {/* ====== MARCA D'ÁGUA STI ====== */}
      <div className="absolute inset-0 flex items-center justify-center pointer-events-none">
        <span className="text-white text-[30rem] font-extrabold opacity-10 select-none">
          STI
        </span>
      </div>

      {/* Sidebar Mobile */}
      {sidebarOpen && (
        <div className="fixed inset-0 z-40 lg:hidden">
          <div
            className="absolute inset-0 bg-black/50"
            onClick={() => setSidebarOpen(false)}
          />
          <div
            className="relative w-64 h-full bg-[#0f2027] p-4"
            onClick={(e) => e.stopPropagation()}
          >
            <button
              onClick={() => setSidebarOpen(false)}
              className="absolute top-4 right-4 text-white"
            >
              <X size={20} />
            </button>
            <Sidebar />
          </div>
        </div>
      )}

      {/* Sidebar Desktop */}
      <div className="hidden lg:flex flex-shrink-0 w-64 h-screen sticky top-0">
        <Sidebar />
      </div>

      {/* Conteúdo principal */}
      <div className="flex-1 flex flex-col relative z-10">
        {/* Header mobile */}
        <header className="p-4 flex items-center justify-between lg:hidden">
          <button onClick={() => setSidebarOpen(true)} className="text-white">
            <Menu size={24} />
          </button>
          <h1 className="text-lg font-bold text-white">Listagem de Produtos</h1>
        </header>

        <main className="p-8 flex-1 relative">
          {/* Botão voltar */}
          <div className="absolute top-8 right-8">
            <button
              onClick={() => router.push("/produtos")}
              className="group flex items-center gap-2 px-4 py-2 bg-white rounded-xl shadow-md hover:bg-blue-500 hover:text-white transition text-sm"
            >
              <ArrowLeft size={18} />
              <span className="hidden group-hover:inline">Voltar</span>
            </button>
          </div>

          {/* Título */}
          <div className="flex justify-center mb-10">
            <h1 className="text-2xl font-bold text-white mb-6 text-center drop-shadow-md">
              Listagem de Produtos
            </h1>
          </div>

          {loading ? (
            <p className="text-white text-sm text-center">Carregando produtos...</p>
          ) : produtos.length === 0 ? (
            <p className="text-white text-sm text-center">Nenhum produto encontrado.</p>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {produtos.map((produto) => (
                <div
                  key={produto.id}
                  className={`backdrop-blur-md shadow-md rounded-2xl p-5 flex flex-col justify-between text-sm ${
                    produto.quantidade < 0
                      ? "bg-red-500/20 border border-red-400/40"
                      : "bg-[rgba(255,255,255,0.05)] border border-white/20"
                  }`}
                >
                  <div>
                    <h2 className="text-lg font-semibold text-white mb-1">
                      {produto.nome}
                    </h2>
                    <p className="text-gray-200">
                      <strong>Descrição:</strong> {produto.descricao}
                    </p>
                    <p className="text-gray-200">
                      <strong>Preço:</strong> R$ {produto.preco.toFixed(2)}
                    </p>
                    <p
                      className={`${
                        produto.quantidade < 0
                          ? "text-red-300 font-semibold"
                          : "text-gray-200"
                      }`}
                    >
                      <strong>Quantidade:</strong> {produto.quantidade}
                    </p>
                    <p className="text-gray-200">
                      <strong>Categoria:</strong> {produto.categoria}
                    </p>
                  </div>

                  <div className="mt-3 flex justify-end">
                    <button
                      onClick={() => handleEditClick(produto)}
                      className="group flex items-center gap-1 px-2.5 py-1 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition text-sm"
                    >
                      <Pencil size={16} />
                      <span className="hidden group-hover:inline">Editar</span>
                    </button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </main>
      </div>

      {/* Modal de Edição */}
      {produtoEditando && formData && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
          <div className="bg-[rgba(15,32,39,0.85)] rounded-2xl shadow-xl w-full max-w-3xl p-6 text-white backdrop-blur-md">
            <h2 className="text-xl font-bold mb-4">Editar Produto</h2>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {["nome", "descricao", "preco", "quantidade", "categoria"].map(
                (field) => (
                  <div key={field}>
                    <label className="block text-sm font-medium mb-1 capitalize">
                      {field}
                    </label>
                    <input
                      type={
                        field === "preco" || field === "quantidade"
                          ? "number"
                          : "text"
                      }
                      value={(formData as any)[field]}
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          [field]:
                            field === "preco" || field === "quantidade"
                              ? parseFloat(e.target.value)
                              : e.target.value,
                        })
                      }
                      className="w-full p-3 border border-gray-300 rounded-lg bg-white text-gray-800 focus:ring-2 focus:ring-blue-600"
                    />
                  </div>
                )
              )}
            </div>

            <div className="flex justify-end gap-3 mt-6">
              <button
                onClick={handleClose}
                className="group flex items-center gap-1 px-4 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition"
              >
                <X size={18} />
                <span className="hidden group-hover:inline">Fechar</span>
              </button>
              <button
                onClick={handleUpdate}
                className="group flex items-center gap-1 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
              >
                <CheckCircle2 size={18} />
                <span className="hidden group-hover:inline">Atualizar</span>
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
