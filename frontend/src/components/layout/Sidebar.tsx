"use client";

import Link from "next/link";
import { useState } from "react";
import { ChevronDownIcon, ChevronUpIcon } from "@heroicons/react/24/solid";

export default function Sidebar() {
  const [clientesOpen, setClientesOpen] = useState(false);
  const [usuariosOpen, setUsuariosOpen] = useState(false);
  const [fornecedoresOpen, setFornecedoresOpen] = useState(false);
  const [produtosOpen, setProdutosOpen] = useState(false);

  return (
    <aside
      className="w-64 h-screen text-white flex flex-col p-4"
      style={{
        background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
      }}
    >
      <h1 className="text-2xl font-bold mb-8">Gestão de Estoque</h1>

      <nav className="flex flex-col gap-2 flex-grow">
        {/* ====== CLIENTES ====== */}
        <div>
          <button
            type="button"
            onClick={() => setClientesOpen(!clientesOpen)}
            className="flex justify-between items-center w-full p-2 rounded hover:bg-gray-700 transition"
          >
            <span>Clientes</span>
            {clientesOpen ? (
              <ChevronUpIcon className="h-4 w-4" />
            ) : (
              <ChevronDownIcon className="h-4 w-4" />
            )}
          </button>
          {clientesOpen && (
            <div className="flex flex-col ml-4 mt-1 gap-1">
              <Link
                href="/clientes/listar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Listar Clientes
              </Link>
              <Link
                href="/clientes/cadastrar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Cadastrar Cliente
              </Link>
              <Link
                href="/clientes/excluir"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Excluir Cliente
              </Link>
            </div>
          )}
        </div>

        {/* ====== USUÁRIOS ====== */}
        <div>
          <button
            type="button"
            onClick={() => setUsuariosOpen(!usuariosOpen)}
            className="flex justify-between items-center w-full p-2 rounded hover:bg-gray-700 transition"
          >
            <span>Usuários</span>
            {usuariosOpen ? (
              <ChevronUpIcon className="h-4 w-4" />
            ) : (
              <ChevronDownIcon className="h-4 w-4" />
            )}
          </button>
          {usuariosOpen && (
            <div className="flex flex-col ml-4 mt-1 gap-1">
              <Link
                href="/usuarios/listar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Listar Usuários
              </Link>
              <Link
                href="/usuarios/cadastrar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Cadastrar Usuário
              </Link>
            </div>
          )}
        </div>

        {/* ====== FORNECEDORES ====== */}
        <div>
          <button
            type="button"
            onClick={() => setFornecedoresOpen(!fornecedoresOpen)}
            className="flex justify-between items-center w-full p-2 rounded hover:bg-gray-700 transition"
          >
            <span>Fornecedores</span>
            {fornecedoresOpen ? (
              <ChevronUpIcon className="h-4 w-4" />
            ) : (
              <ChevronDownIcon className="h-4 w-4" />
            )}
          </button>
          {fornecedoresOpen && (
            <div className="flex flex-col ml-4 mt-1 gap-1">
              <Link
                href="/fornecedores/listar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Listar Fornecedores
              </Link>
              <Link
                href="/fornecedores/cadastrar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Cadastrar Fornecedor
              </Link>
            </div>
          )}
        </div>

        {/* ====== PRODUTOS ====== */}
        <div>
          <button
            type="button"
            onClick={() => setProdutosOpen(!produtosOpen)}
            className="flex justify-between items-center w-full p-2 rounded hover:bg-gray-700 transition"
          >
            <span>Produtos</span>
            {produtosOpen ? (
              <ChevronUpIcon className="h-4 w-4" />
            ) : (
              <ChevronDownIcon className="h-4 w-4" />
            )}
          </button>
          {produtosOpen && (
            <div className="flex flex-col ml-4 mt-1 gap-1">
              <Link
                href="/produtos/listar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Listar Produtos
              </Link>
              <Link
                href="/produtos/cadastrar"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Cadastrar Produto
              </Link>
              <Link
                href="/produtos/excluir"
                className="hover:bg-gray-700 p-2 rounded text-sm"
              >
                Excluir Produto
              </Link>
            </div>
          )}
        </div>

        {/* ====== VENDAS ====== */}
        <Link href="/vendas" className="hover:bg-gray-700 p-2 rounded">
          Vendas
        </Link>
      </nav>

      {/* Rodapé fixo */}
      <footer className="text-xs text-gray-400 mt-6 border-t border-gray-700 pt-3 text-center">
        © {new Date().getFullYear()} STI Soluções. <br />
        Todos os direitos reservados.
      </footer>
    </aside>
  );
}
