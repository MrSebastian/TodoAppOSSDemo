import type { InfoClientInterface } from "@/api/InfoClientInterface";
import type { Info } from "@/api/InfoService";

export default class InfoLocalStorageClient implements InfoClientInterface {
    private readonly KEY_INFO_NAME = "info.app.name";
    private readonly KEY_INFO_VERSION = "info.app.version";

    private readonly defaultInfo: Info = {
        application: {
            name: "TodoAppOSSDemo",
            version: "0.1",
        },
    };
    getInfo(): Promise<Info> {
        return Promise.resolve(this.getStoredInfo() ?? this.defaultInfo);
    }

    private getStoredInfo(): Info | undefined {
        const storedName = localStorage.getItem(this.KEY_INFO_NAME);
        const storedVersion = localStorage.getItem(this.KEY_INFO_VERSION);

        if (storedName !== null && storedVersion !== null) {
            return {
                application: {
                    name: storedName,
                    version: storedVersion,
                },
            };
        } else {
            return undefined;
        }
    }
}
