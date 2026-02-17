import api from "@/lib/api";

export interface Usuario {
  id: number;
  nome: string;
  email: string;
  perfil: string;
  ativo: boolean;
  dataCriacao: string;
}

interface LoginResponse {
  token: string;
  usuario: Usuario;
}

/**
 * Realiza login e salva token no sessionStorage
 */
export async function login(email: string, senha: string): Promise<LoginResponse> {
  const response = await api.post<LoginResponse>("/auth/login", { email, senha });
  const { token, usuario } = response.data;

  if (typeof window !== "undefined") {
    sessionStorage.setItem("token", token);
  }

  return { token, usuario };
}

/**
 * Remove token do sessionStorage
 */
export function logout() {
  if (typeof window !== "undefined") {
    sessionStorage.removeItem("token");
  }
}

/**
 * Recupera token do sessionStorage
 */
export function getToken(): string | null {
  if (typeof window !== "undefined") {
    return sessionStorage.getItem("token");
  }
  return null;
}
