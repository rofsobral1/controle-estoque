"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { useRequireAuth } from "@/hooks/useRequireAuth";
import Sidebar from "@/components/layout/Sidebar";
import { apiFetch } from "@/services/api";
import { ArrowLeft, Pencil, X, CheckCircle2, Menu } from "lucide-react";

interface Cliente {
  id: number;
  nome: string;
  email: string;
  telefone: string;
  endereco: string;
  cep: string;
  bairro: string;
  estado: string;
}

export default function ListarClientesPage() {
  useRequireAuth();
  const [clientes, setClientes] = useState<Cliente[]>([]);
  const [loading, setLoading] = useState(true);
  const [clienteEditando, setClienteEditando] = useState<Cliente | null>(null);
  const [formData, setFormData] = useState<Cliente | null>(null);
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const fetchClientes = async () => {
      try {
        const data = await apiFetch("/clientes");
        setClientes(data);
      } catch (error) {
        console.error("Erro ao buscar clientes:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchClientes();
  }, []);

  const handleEditClick = (cliente: Cliente) => {
    setClienteEditando(cliente);
    setFormData({ ...cliente });
  };

  const handleClose = () => {
    setClienteEditando(null);
    setFormData(null);
  };

  const handleUpdate = async () => {
    if (!formData) return;
    try {
      await apiFetch(`/clientes/${formData.id}`, {
        method: "PUT",
        body: JSON.stringify(formData),
        headers: { "Content-Type": "application/json" },
      });

      setClientes((prev) =>
        prev.map((c) => (c.id === formData.id ? formData : c))
      );
      handleClose();
    } catch (error) {
      console.error("Erro ao atualizar cliente:", error);
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

      {/* Sidebar Mobile (overlay + painel) */}
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
        {/* Header mobile: botão de abrir sidebar + título */}
        <header className="p-4 flex items-center justify-between lg:hidden">
          <button onClick={() => setSidebarOpen(true)} className="text-white">
            <Menu size={24} />
          </button>
          <h1 className="text-lg font-bold text-white">Listagem de Cliente</h1>
        </header>

        <main className="p-8 flex-1 relative">
          {/* Botão voltar canto superior direito */}
          <div className="absolute top-8 right-8">
            <button
              onClick={() => router.push("/clientes")}
              className="group flex items-center gap-2 px-4 py-2 bg-white rounded-xl shadow-md hover:bg-blue-500 hover:text-white transition text-sm"
            >
              <ArrowLeft size={18} />
              <span className="hidden group-hover:inline">Voltar</span>
            </button>
          </div>

          {/* Título centralizado */}
          <div className="flex justify-center mb-10">
            <h1 className="text-2xl font-bold text-white mb-6 text-center drop-shadow-md">
              Listagem de Cliente
            </h1>
          </div>

          {loading ? (
            <p className="text-white text-sm text-center">Carregando clientes...</p>
          ) : clientes.length === 0 ? (
            <p className="text-white text-sm text-center">Nenhum cliente encontrado.</p>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {clientes.map((cliente) => (
                <div
                  key={cliente.id}
                  className="bg-[rgba(255,255,255,0.05)] backdrop-blur-md shadow-md rounded-2xl p-5 flex flex-col justify-between text-sm"
                >
                  <div>
                    <h2 className="text-lg font-semibold text-white mb-1">{cliente.nome}</h2>
                    <p className="text-gray-200"><strong>Telefone:</strong> {cliente.telefone}</p>
                    <p className="text-gray-200"><strong>Email:</strong> {cliente.email}</p>
                    <p className="text-gray-200"><strong>Endereço:</strong> {cliente.endereco}</p>
                    <p className="text-gray-200"><strong>CEP:</strong> {cliente.cep}</p>
                    <p className="text-gray-200"><strong>Bairro:</strong> {cliente.bairro}</p>
                    <p className="text-gray-200"><strong>Estado:</strong> {cliente.estado}</p>
                  </div>

                  <div className="mt-3 flex justify-end">
                    <button
                      onClick={() => handleEditClick(cliente)}
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
      {clienteEditando && formData && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
          <div className="bg-[rgba(15,32,39,0.85)] rounded-2xl shadow-xl w-full max-w-3xl p-6 text-white backdrop-blur-md">
            <h2 className="text-xl font-bold mb-4">Editar Cliente</h2>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {["nome", "telefone", "email", "endereco", "cep", "bairro", "estado"].map(
                (field) => (
                  <div key={field}>
                    <label className="block text-sm font-medium mb-1">
                      {field.charAt(0).toUpperCase() + field.slice(1)}
                    </label>
                    <input
                      type="text"
                      value={(formData as any)[field]}
                      onChange={(e) => setFormData({ ...formData, [field]: e.target.value })}
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
