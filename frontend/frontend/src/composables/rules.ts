export function useRules() {
    function required() {
        return (value?: string | null) => {
            return (
                (value !== undefined && value !== null && value.length > 0) ||
                "Pflichtfeld"
            );
        };
    }

    function maxlength(maxlength: number) {
        return (value: string | null) => {
            return (
                value === null ||
                value.length <= maxlength ||
                `Maximale Länge ist ${maxlength} Zeichen.`
            );
        };
    }

    return { required, maxlength };
}
