import HealthState from "@/types/HealthState";

export interface HealthClientInterface {
    getHealthState(): Promise<HealthState>;
}
