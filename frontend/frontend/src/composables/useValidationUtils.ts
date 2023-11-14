import type { SubmitEventPromise } from "vuetify";

export function useValidationUtils() {
    async function proceedOnValid(
        submitEventPromise: SubmitEventPromise
    ): Promise<void> {
        const validationResult = await submitEventPromise;
        return validationResult.valid
            ? Promise.resolve()
            : Promise.reject(new Error("validation failed"));
    }

    return { proceedOnValid };
}
