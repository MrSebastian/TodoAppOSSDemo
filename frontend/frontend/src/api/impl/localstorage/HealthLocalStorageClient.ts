import type { HealthClientInterface } from "@/api/HealthClientInterface";

import HealthState from "@/types/HealthState";

export default class HealthLocalStorageClient implements HealthClientInterface {
    private readonly KEY_HEALTHSTATE = "healthstate";

    private readonly defaultHealthStateValue = "UP";

    getHealthState(): Promise<HealthState> {
        const localStoredHealthStateValue = localStorage.getItem(
            this.KEY_HEALTHSTATE
        );
        const healthState = new HealthState(
            localStoredHealthStateValue ?? this.defaultHealthStateValue
        );
        return Promise.resolve(healthState);
    }
}
