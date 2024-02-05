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
    getters: {
        userState: (state): UserState => state,
    },
    actions: {
        setUsername(payload: string): void {
            this.username = payload;
        },
        setRoles(payload: string[]): void {
            this.roles = payload;
        },
    },
});
