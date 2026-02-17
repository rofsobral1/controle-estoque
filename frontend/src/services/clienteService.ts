// src/services/clienteService.ts
import api from "@/lib/api";

export interface Cliente {
  id?: number;
  nome: string;
  email?: string;
  telefone?: string;
  cpf?: string;
  cnpj?: string;
  endereco?: string;
  bairro?: string;
  estado?: string;
  dataInclusao?: string;
}

// Buscar todos os clientes
export const getClientes = async (): Promise<Cliente[]> => {
  const { data } = await api.get("/clientes");
  return data;
};

// Criar novo cliente
export const createCliente = async (cliente: Cliente): Promise<Cliente> => {
  const { data } = await api.post("/clientes", cliente);
  return data;
};

// Atualizar cliente existente
export const updateCliente = async (id: number, cliente: Cliente): Promise<Cliente> => {
  const { data } = await api.put(`/clientes/${id}`, cliente);
  return data;
};

// Excluir cliente
export const deleteCliente = async (id: number): Promise<void> => {
  await api.delete(`/clientes/${id}`);
};
