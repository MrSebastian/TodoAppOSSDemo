/// <reference types="vite/client" />
interface ImportMetaEnv {
    readonly VITE_VUE_APP_API_URL: string;
    readonly VITE_DONT_USE_RESTCLIENT: string | undefined;
}

interface ImportMeta {
    readonly env: ImportMetaEnv;
}
