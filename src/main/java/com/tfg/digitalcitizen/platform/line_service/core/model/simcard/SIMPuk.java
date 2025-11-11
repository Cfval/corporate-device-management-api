package com.tfg.digitalcitizen.platform.line_service.core.model.simcard;

import com.tfg.digitalcitizen.platform.shared.domain.valueobject.NullableStringValueObject;

public class SIMPuk extends NullableStringValueObject {

    public static final SIMPuk EMPTY = new SIMPuk("-");
    private static final String PUK_PATTERN = "^\\d{8}$";

    private SIMPuk(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(PUK_PATTERN)) {
            throw new IllegalArgumentException("Invalid PUK. Must be exactly 8 digits.");
        }
    }

    public static SIMPuk fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new SIMPuk(value);
    }

    public boolean isEmpty() { return "-".equals(this.toPrimitive()); }
}

