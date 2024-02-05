import HealthClientFactory from "@/api/HealthClientFactory";
import HealthState from "@/types/HealthState";

export default class HealthService {
    private static readonly healthClient =
        HealthClientFactory.getHealthClient();

    static checkHealth(): Promise<HealthState> {
        return this.healthClient.getHealthState();
    }
}
