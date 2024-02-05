import type { InfoClientInterface } from "@/api/InfoClientInterface";

import InfoLocalStorageClient from "@/api/impl/localstorage/InfoLocalStorageClient";
import InfoRestClient from "@/api/impl/rest/InfoRestClient";
import { API_BASE } from "@/Constants";

export default class InfoClientFactory {
    static getInfoClient(): InfoClientInterface {
        if (API_BASE) {
            return new InfoRestClient();
        } else {
            return new InfoLocalStorageClient();
        }
    }
}
