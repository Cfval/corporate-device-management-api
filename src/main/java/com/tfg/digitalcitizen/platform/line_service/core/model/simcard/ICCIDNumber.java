package com.tfg.digitalcitizen.platform.line_service.core.model.simcard;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class ICCIDNumber extends NullableStringValueObject {

    public static final ICCIDNumber EMPTY = new ICCIDNumber("-");
    private static final String ICCID_PATTERN = "^\\d{19,20}$";

    private ICCIDNumber(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(ICCID_PATTERN)) {
            throw new IllegalArgumentException("Invalid ICCID format. Must be 19–20 digits.");
        }
    }

    public static ICCIDNumber fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new ICCIDNumber(value);
    }

    public boolean isEmpty() { return "-".equals(this.toPrimitive()); }
}

