import type { HealthClientInterface } from "@/api/HealthClientInterface";

import HealthLocalStorageClient from "@/api/impl/localstorage/HealthLocalStorageClient";
import HealthRestClient from "@/api/impl/rest/HealthRestClient";
import { API_BASE } from "@/Constants";

export default class HealthClientFactory {
    static getHealthClient(): HealthClientInterface {
        if (API_BASE) {
            return new HealthRestClient();
        } else {
            return new HealthLocalStorageClient();
        }
    }
}
