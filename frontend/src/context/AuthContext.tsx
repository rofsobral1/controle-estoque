"use client";

import { createContext, useContext, useState, useEffect, ReactNode } from "react";
import { useRouter } from "next/navigation";
import { apiFetch } from "@/services/api";

interface AuthContextType {
  token: string | null;
  login: (email: string, senha: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [token, setToken] = useState<string | null>(null);
  const [loading, setLoading] = useState(true); // controle de carregamento
  const router = useRouter();

  useEffect(() => {
    const storedToken = sessionStorage.getItem("token");
    if (storedToken) setToken(storedToken);
    setLoading(false);
  }, []);

  const login = async (email: string, senha: string) => {
    const res = await apiFetch("/auth/login", {
      method: "POST",
      body: JSON.stringify({ email, senha }),
      headers: { "Content-Type": "application/json" },
    });

    if (res.token) {
      setToken(res.token);
      sessionStorage.setItem("token", res.token);
      router.push("/dashboard");
    } else if (res.message) {
      throw new Error(res.message);
    } else {
      throw new Error("Credenciais inválidas");
    }
  };

  const logout = () => {
    setToken(null);
    sessionStorage.removeItem("token");
    router.push("/login");
  };

  return (
    <AuthContext.Provider value={{ token, login, logout }}>
      {!loading && children} {/* renderiza apenas após carregar token */}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error("useAuth deve ser usado dentro de AuthProvider");
  return context;
};
