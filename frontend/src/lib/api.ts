import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
});

// Interceptor para adicionar JWT no header, somente no cliente
api.interceptors.request.use((config) => {
  if (typeof window !== "undefined") {
    const token = sessionStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
  }
  return config;
});

export default api;
