export const API_BASE: string | undefined = import.meta.env
    .VITE_VUE_APP_API_URL;
export const ROUTER_BASE: string = import.meta.env.BASE_URL;

export const API_BACKEND_BASE = `${API_BASE}/api/clientid-backend-service/`;
