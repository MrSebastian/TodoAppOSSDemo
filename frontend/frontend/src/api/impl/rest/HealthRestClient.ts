import type { HealthClientInterface } from "@/api/HealthClientInterface";
import type HealthState from "@/types/HealthState";

import FetchUtils from "@/api/FetchUtils";
import { API_BASE } from "@/Constants";

export default class HealthRestClient implements HealthClientInterface {
    getHealthState(): Promise<HealthState> {
        return fetch(`${API_BASE}/actuator/health`, FetchUtils.getGETConfig())
            .then((response) => {
                FetchUtils.defaultResponseHandler(response);
                return response.json();
            })
            .catch((err) => {
                FetchUtils.defaultResponseHandler(err);
            });
    }
}
