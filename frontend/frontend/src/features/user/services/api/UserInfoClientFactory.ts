import type { UserInfoClientInterface } from "@/features/user/services/api/UserInfoClientInterface";

import { API_BASE } from "@/Constants";
import UserInfoLocalStorageClient from "@/features/user/services/api/impl/localstorage/UserInfoLocalStorageClient";
import UserInfoRestClient from "@/features/user/services/api/impl/rest/UserInfoRestClient";

export default class UserInfoClientFactory {
    static getClient(): UserInfoClientInterface {
        if (API_BASE) {
            return new UserInfoRestClient();
        } else {
            return new UserInfoLocalStorageClient();
        }
    }
}
