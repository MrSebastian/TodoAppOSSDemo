import { defineStore } from "pinia";

export interface UserState {
    username: string | undefined;
    roles: string[];
}

const initialUserState: UserState = {
    username: "",
    roles: [],
};

export const useUserStore = defineStore("user", {
    state: (): UserState => initialUserState,
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
