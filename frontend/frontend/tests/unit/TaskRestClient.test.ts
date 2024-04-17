// Generated by CodiumAI
import type TaskCreateDTO from "@/features/task/services/api/model/TaskCreateDTO";

import { describe, expect, it, vi } from "vitest";

import { API_BACKEND_BASE } from "@/Constants";
import TaskRestClient from "@/features/task/services/api/impl/rest/TaskRestClient";

describe("TaskRestClient", () => {
    // Should be able to create a task successfully
    it("should create a task successfully", () => {
        // Mock fetch api
        const mockFetch = vi.fn(() =>
            Promise.resolve({ ok: true, status: 204 } as Response)
        );
        vi.spyOn(global, "fetch").mockImplementation(mockFetch);

        // Create a new task
        const newTask: TaskCreateDTO = {
            title: "New Task",
            description: "This is a new task",
            assignee: "John Doe",
        };

        // Create an instance of TaskRestClient
        const taskRestClient = new TaskRestClient();

        // Call the createTask method
        taskRestClient.createTask(newTask);

        // Verify that fetch is called with the correct URL and config
        expect(mockFetch).toHaveBeenCalledWith(`${API_BACKEND_BASE}/tasks`, {
            method: "POST",
            body: JSON.stringify(newTask),
            headers: new Headers({
                "Content-Type": "application/json",
            }),
            mode: "cors",
            credentials: "same-origin",
            redirect: "manual",
        });
    });
});
