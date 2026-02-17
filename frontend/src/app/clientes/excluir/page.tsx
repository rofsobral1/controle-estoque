"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { ArrowLeft, Trash2, X, Menu } from "lucide-react";
import Sidebar from "@/components/layout/Sidebar";

interface Cliente {
  id: number;
  nome: string;
  email: string;
  telefone: string;
  endereco: string;
  cpf: string;
  cnpj: string;
}

export default function ExcluirClientePage() {
  const router = useRouter();
  const [clienteId, setClienteId] = useState("");
  const [cliente, setCliente] = useState<Cliente | null>(null);
  const [mensagem, setMensagem] = useState("");
  const [loading, setLoading] = useState(false);
  const [showConfirmModal, setShowConfirmModal] = useState(false);
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  const [sidebarOpen, setSidebarOpen] = useState(false); // mobile

  const apiUrl = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080/api";

  const handlePesquisar = async () => {
    setMensagem("");
    setCliente(null);
    if (!clienteId) {
      setMensagem("Informe o ID do cliente.");
      return;
    }
    try {
      setLoading(true);
      const response = await fetch(`${apiUrl}/clientes/${clienteId}`, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
      });
      if (response.ok) {
        const data = await response.json();
        setCliente(data);
      } else if (response.status === 404 || response.status === 403) {
        setMensagem("Cliente não encontrado.");
      } else {
        throw new Error(`Erro ao buscar cliente: ${response.status}`);
      }
    } catch (error) {
      setMensagem("Erro ao buscar cliente.");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const handleExcluir = async () => {
    if (!cliente) return;
    try {
      setLoading(true);
      const response = await fetch(`${apiUrl}/clientes/${cliente.id}`, {
        method: "DELETE",
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
      });
      if (response.status === 200 || response.status === 204) {
        setCliente(null);
        setClienteId("");
        setShowConfirmModal(false);
        setShowSuccessModal(true);
      } else if (response.status === 404) {
        setMensagem("Cliente não encontrado para exclusão.");
      } else {
        throw new Error(`Falha ao excluir cliente: ${response.status}`);
      }
    } catch (error) {
      setMensagem("Erro ao excluir cliente.");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      className="flex min-h-screen text-sm text-gray-800 relative overflow-hidden"
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

      {/* Sidebar Desktop */}
      <div className="hidden md:flex md:w-64 md:flex-col md:fixed md:inset-y-0 z-50">
        <Sidebar />
      </div>

      {/* Sidebar Mobile */}
      {sidebarOpen && (
        <div className="fixed inset-0 z-40 md:hidden">
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

      {/* Conteúdo principal */}
      <div className="flex-1 flex flex-col md:pl-64 relative z-10">
        {/* Header mobile com botão de abrir sidebar */}
        <header className="p-4 flex items-center justify-between md:hidden">
          <button onClick={() => setSidebarOpen(true)} className="text-white">
            <Menu size={24} />
          </button>
          <h1 className="text-lg font-bold text-white">Excluir Cliente</h1>
          <div className="w-8" />
        </header>

        <main className="p-8 flex-1 flex flex-col relative">
          {/* Botão voltar canto superior direito (desktop) */}
          <div className="absolute top-8 right-8">
            <button
              onClick={() => router.push("/clientes")}
              className="group flex items-center gap-2 px-4 py-2 bg-white rounded-xl shadow-md hover:bg-blue-500 hover:text-white transition text-sm"
            >
              <ArrowLeft size={18} />
              <span className="hidden group-hover:inline">Voltar</span>
            </button>
          </div>

          {/* Título */}
          <div className="flex justify-center mb-10">
            <h1 className="text-2xl font-bold text-white mb-6 text-center drop-shadow-md">
              Excluir Cliente
            </h1>
          </div>

          {/* Campo de pesquisa */}
          <div className="flex items-center gap-2 w-full max-w-sm mx-auto mb-8">
            <input
              type="number"
              value={clienteId}
              onChange={(e) => setClienteId(e.target.value)}
              placeholder="ID do cliente"
              className="flex-1 p-3 rounded-lg bg-white/10 text-white placeholder-gray-300 border border-white/20 focus:outline-none focus:ring-2 focus:ring-blue-400 transition"
            />
            <button
              onClick={handlePesquisar}
              disabled={loading}
              className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg transition"
            >
              {loading ? "..." : "Pesquisar"}
            </button>
          </div>

          {/* Mensagem */}
          {mensagem && (
            <p className="mb-4 text-center font-medium text-white">{mensagem}</p>
          )}

          {/* Dados do cliente */}
          {cliente && (
            <div className="bg-[rgba(255,255,255,0.05)] backdrop-blur-md shadow-md rounded-2xl p-6 max-w-lg mx-auto text-white">
              <h2 className="text-lg font-semibold mb-2">{cliente.nome}</h2>
              <p>
                <strong>Email:</strong> {cliente.email}
              </p>
              <p>
                <strong>Telefone:</strong> {cliente.telefone}
              </p>
              <p>
                <strong>Endereço:</strong> {cliente.endereco}
              </p>
              <p>
                <strong>CPF:</strong> {cliente.cpf}
              </p>
              <p>
                <strong>CNPJ:</strong> {cliente.cnpj}
              </p>

              <button
                onClick={() => setShowConfirmModal(true)}
                disabled={loading}
                className="mt-4 bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-lg flex items-center gap-2"
              >
                <Trash2 size={18} /> Excluir Cliente
              </button>
            </div>
          )}

          {/* Modal de confirmação */}
          {showConfirmModal && (
            <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
              <div className="bg-gray-800 p-6 rounded-2xl shadow-lg w-full max-w-sm text-white flex flex-col gap-4">
                <p>Deseja realmente excluir o cliente {cliente?.nome}?</p>
                <div className="flex justify-end gap-2">
                  <button
                    onClick={() => setShowConfirmModal(false)}
                    className="px-4 py-2 rounded-lg bg-gray-600 hover:bg-gray-700 transition"
                  >
                    Cancelar
                  </button>
                  <button
                    onClick={handleExcluir}
                    className="px-4 py-2 rounded-lg bg-red-600 hover:bg-red-700 transition"
                  >
                    Excluir
                  </button>
                </div>
              </div>
            </div>
          )}

          {/* Modal de sucesso */}
          {showSuccessModal && (
            <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
              <div className="bg-green-600 p-6 rounded-2xl shadow-lg w-full max-w-sm text-white flex flex-col gap-4 items-center">
                <p className="font-bold text-lg">Cliente excluído com sucesso!</p>
                <button
                  onClick={() => setShowSuccessModal(false)}
                  className="flex items-center gap-1 px-4 py-2 bg-white text-green-700 rounded-lg hover:bg-gray-100 transition"
                >
                  <X size={16} /> Fechar
                </button>
              </div>
            </div>
          )}
        </main>
      </div>
    </div>
  );
}
