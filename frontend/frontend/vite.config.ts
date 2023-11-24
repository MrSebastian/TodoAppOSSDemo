import { fileURLToPath, URL } from "url";

import vue from "@vitejs/plugin-vue";
import ViteFonts from "unplugin-fonts/vite";
import { defineConfig } from "vite";
import vuetify, { transformAssetUrls } from "vite-plugin-vuetify";

export default defineConfig({
    plugins: [
        vue({
            template: {
                transformAssetUrls,
            },
        }),
        vuetify({
            autoImport: true,
        }),
        ViteFonts({
            google: {
                families: [
                    {
                        name: "Roboto",
                        styles: "wght@100;300;400;500;700;900",
                    },
                ],
            },
        }),
    ],
    server: {
        port: 8081,
    },
    define: {
        "process.env": {},
    },
    resolve: {
        alias: {
            "@": fileURLToPath(new URL("./src", import.meta.url)),
        },
        extensions: [
            ".js",
            ".mjs",
            ".mts",
            ".ts",
            ".jsx",
            ".tsx",
            ".json",
            ".vue",
        ],
    },
});
