import type { UserInfoClientInterface } from "@/features/user/services/api/UserInfoClientInterface";

import { DONT_USE_RESTCLIENT } from "@/Constants";
import UserInfoLocalStorageClient from "@/features/user/services/api/impl/localstorage/UserInfoLocalStorageClient";
import UserInfoRestClient from "@/features/user/services/api/impl/rest/UserInfoRestClient";

export default class UserInfoClientFactory {
    static getClient(): UserInfoClientInterface {
        if (DONT_USE_RESTCLIENT) {
            return new UserInfoLocalStorageClient();
        } else {
            return new UserInfoRestClient();
        }
    }
}
