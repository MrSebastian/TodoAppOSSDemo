import type { InfoClientInterface } from "@/api/InfoClientInterface";
import type { Info } from "@/api/InfoService";

import FetchUtils from "@/api/FetchUtils";
import { API_BASE } from "@/Constants";

export default class InfoRestClient implements InfoClientInterface {
    getInfo(): Promise<Info> {
        return fetch(`${API_BASE}/actuator/info`, FetchUtils.getGETConfig())
            .then((response) => {
                FetchUtils.defaultResponseHandler(response);
                return response.json();
            })
            .catch((err) => {
                FetchUtils.defaultResponseHandler(err);
            });
    }
}
