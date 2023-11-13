import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { fileURLToPath, URL } from "url";
import vuetify, { transformAssetUrls } from "vite-plugin-vuetify";
import ViteFonts from "unplugin-fonts/vite";

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
