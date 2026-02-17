// src/services/api.ts
export async function apiFetch(url: string, options: RequestInit = {}) {
  const token = localStorage.getItem("token");

  const headers: HeadersInit = {
    "Content-Type": "application/json",
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
    ...options.headers,
  };

  try {
    const response = await fetch(`http://localhost:8080/api${url}`, {
      ...options,
      headers,
    });

    if (!response.ok) {
      const text = await response.text();
      console.error("Erro backend:", response.status, text);
      throw new Error(`Erro ${response.status}: ${text}`);
    }

    return await response.json();
  } catch (err: any) {
    console.error("Erro de rede ou CORS:", err);
    throw err;
  }
}
