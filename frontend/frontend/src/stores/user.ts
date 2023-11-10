import { defineStore } from "pinia";

export interface UserState {
    username: string | undefined;
    roles: string[];
}

export const useUserStore = defineStore("user", {
    state: (): UserState => ({
        username: "",
        roles: [],
    }),
    getters: {},
    actions: {
        setUsername(payload: string): void {
            this.username = payload;
        },
        setRoles(payload: string[]): void {
            this.roles = payload;
        },
    },
});
