package com.tfg.digitalcitizen.platform.line_service.core.model.simcard;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class SIMType extends NullableStringValueObject {

    public static final SIMType EMPTY = new SIMType("-");
    private static final String VALID_PATTERN = "^(SIM|ESIM|DUAL SIM|MULTISIM |-)$";

    private SIMType(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.toUpperCase().matches(VALID_PATTERN)) {
            throw new IllegalArgumentException("Invalid SIM type. Allowed: SIM, eSIM, DUAL SIM.");
        }
    }

    public static SIMType fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new SIMType(value);
    }

    public boolean isEmpty() { return "-".equals(this.toPrimitive()); }
}

