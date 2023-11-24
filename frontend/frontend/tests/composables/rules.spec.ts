import { describe, expect, it } from "vitest";

import { useRules } from "@/composables/rules";

const { required, maxlength } = useRules();
describe("rules.ts", () => {
    describe("required", () => {
        it("failure on undefined", () => {
            expect(required()(undefined)).toContain("");
        });

        it("failure on null", () => {
            expect(required()(null)).toContain("");
        });

        it("failure on empty-string", () => {
            expect(required()("")).toContain("");
        });

        it("true on String", () => {
            expect(required()("a string")).toStrictEqual(true);
        });
    });

    describe("maxlength", () => {
        it("true empty-string with maxlength 10", () => {
            expect(maxlength(10)("")).toStrictEqual(true);
        });

        it("true 10 char string with maxlength 10", () => {
            expect(maxlength(10)("0123456789")).toStrictEqual(true);
        });

        it("false 11 char string with maxlength 10", () => {
            expect(maxlength(10)("01234567890")).toContain("");
        });

        it("true on null", () => {
            expect(maxlength(10)(null)).toStrictEqual(true);
        });
    });
});
