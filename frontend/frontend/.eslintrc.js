module.exports = {
    root: true,
    env: {
        node: true,
    },
    plugins: ["vuetify"],
    extends: [
        // JavaScript/TypeScript-Regeln
        "eslint:recommended",
        "@vue/eslint-config-typescript",
        "@vue/eslint-config-typescript/recommended",
        // Vue-Regeln: https://eslint.vuejs.org/rules/
        "plugin:vue/vue3-essential",
        "plugin:vue/vue3-strongly-recommended",
        "plugin:vue/vue3-recommended",
        // Vuetify-Regeln: https://github.com/vuetifyjs/eslint-plugin-vuetify
        "plugin:vuetify/base",
        "plugin:vuetify/recommended",
        "@vue/eslint-config-prettier",
    ],
    rules: {
        "no-console": "error",
        "vue/component-name-in-template-casing": ["error", "kebab-case"],
    },
};
