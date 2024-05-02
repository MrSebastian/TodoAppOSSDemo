import type { ComponentPublicInstance } from "vue";

import { Levels } from "@/api/error";
import { useSnackbarStore } from "@/stores/snackbar";

export function useErrorHandler() {
    const UNDEFINED_COMPONENT_NAME_PLACEHOLDER = "undefined";

    function vueErrorHandler(
        err: unknown,
        instance: ComponentPublicInstance | null,
        info: string
    ): void {
        const componentNamesAsTree = instance
            ? getComponentNameTree(instance)
            : UNDEFINED_COMPONENT_NAME_PLACEHOLDER;
        if (err instanceof Error) {
            handleError(
                new Error(
                    `${err.message} in instance: ${componentNamesAsTree} with info ${info}`
                )
            );
        } else {
            handleError(
                new Error(
                    `unknown error in instance: ${componentNamesAsTree} with info ${info}`
                )
            );
        }
    }

    function onUnhandledRejection(
        promiseRejectionEvent: PromiseRejectionEvent
    ): unknown {
        if (promiseRejectionEvent.reason instanceof Error) {
            handleError(promiseRejectionEvent.reason);
        } else {
            handleError(new Error(`unknown reason of unhandledRejection`));
        }
        promiseRejectionEvent.stopPropagation();
        promiseRejectionEvent.preventDefault();
        promiseRejectionEvent.stopImmediatePropagation();
        return undefined;
    }

    function handleError(error: Error): void {
        const { showMessage, updateShow } = useSnackbarStore();
        showMessage({ message: error.message, level: Levels.ERROR });
        updateShow(true);
    }

    function getComponentNameTree(component: ComponentPublicInstance): string {
        const componentsNames: string[] = [];
        let componentToGetItsName: ComponentPublicInstance | null = component;
        do {
            componentsNames.push(
                componentToGetItsName.$options.name ||
                    UNDEFINED_COMPONENT_NAME_PLACEHOLDER
            );
            componentToGetItsName = componentToGetItsName.$parent;
        } while (componentToGetItsName);

        return componentsNames.join(" > ");
    }

    return {
        vueErrorHandler,
        onUnhandledRejection,
    };
}
