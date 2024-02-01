import InfoClientFactory from "@/api/InfoClientFactory";

export interface Info {
    application: Application;
}

export interface Application {
    name: string;
    version: string;
}

export default class InfoService {
    private static readonly infoClient = InfoClientFactory.getInfoClient();
    static getInfo(): Promise<Info> {
        return this.infoClient.getInfo();
    }
}
