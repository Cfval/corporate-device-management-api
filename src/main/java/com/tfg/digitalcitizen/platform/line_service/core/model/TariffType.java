package com.tfg.digitalcitizen.platform.line_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class TariffType extends NullableStringValueObject {

    public static final TariffType EMPTY = new TariffType("-");

    private TariffType(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && value.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de tarifa no puede estar vacío si existe.");
        }
    }

    public static TariffType fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new TariffType(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}

