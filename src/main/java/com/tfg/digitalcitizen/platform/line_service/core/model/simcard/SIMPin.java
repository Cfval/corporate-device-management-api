package com.tfg.digitalcitizen.platform.line_service.core.model.simcard;

import com.tfg.digitalcitizen.platform.shared.domain.valueobject.NullableStringValueObject;

public class SIMPin extends NullableStringValueObject {

    public static final SIMPin EMPTY = new SIMPin("-");
    private static final String PIN_PATTERN = "^\\d{4,8}$";

    private SIMPin(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(PIN_PATTERN)) {
            throw new IllegalArgumentException("Invalid PIN. Must be 4–8 digits.");
        }
    }

    public static SIMPin fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new SIMPin(value);
    }

    public boolean isEmpty() { return "-".equals(this.toPrimitive()); }
}

