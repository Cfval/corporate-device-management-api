package com.tfg.digitalcitizen.platform.line_service.core.model.simcard;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class Operator extends NullableStringValueObject {

    public static final Operator EMPTY = new Operator("-");
    private static final int MAX_LENGTH = 50;

    private Operator(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Operator name must not exceed " + MAX_LENGTH + " characters.");
        }
    }

    public static Operator fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new Operator(value);
    }

    public boolean isEmpty() { return "-".equals(this.toPrimitive()); }
}

