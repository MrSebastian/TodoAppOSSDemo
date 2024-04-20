import type { TaskClientInterface } from "@/features/task/services/api/TaskClientInterface";

import { DONT_USE_RESTCLIENT } from "@/Constants";
import TaskClientLocalStorage from "@/features/task/services/api/impl/localstorage/TaskClientLocalStorage";
import TaskRestClient from "@/features/task/services/api/impl/rest/TaskRestClient";

export default class TaskClientFactory {
    static getTaskClient(): TaskClientInterface {
        if (DONT_USE_RESTCLIENT === "true") {
            return new TaskClientLocalStorage();
        } else {
            return new TaskRestClient();
        }
    }
}
