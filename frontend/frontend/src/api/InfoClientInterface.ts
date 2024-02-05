import type { Info } from "@/api/InfoService";

export interface InfoClientInterface {
    getInfo(): Promise<Info>;
}
