import type {TaskClientInterface} from "@/features/task/services/api/TaskClientInterface";
import {API_BASE} from "@/Constants";
import TaskRestClient from "@/features/task/services/api/TaskRestClient";
import TaskClientLocalStorage from "@/features/task/services/api/impl/localstorage/TaskClientLocalStorage";

export default class TaskClientFactory {
    static getTaskClient(): TaskClientInterface {
        if (API_BASE) {
            return new TaskRestClient();
        } else {
            return new TaskClientLocalStorage();
        }
    }
}