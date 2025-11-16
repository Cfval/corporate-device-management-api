package com.tfg.digitalcitizen.platform.device_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class Brand extends NullableStringValueObject {

    public static final Brand EMPTY = new Brand("-");
    public static final int MAX_LENGTH = 50;

    private Brand(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && (value.isBlank() || value.length() > MAX_LENGTH)) {
            throw new IllegalArgumentException("La marca no puede estar vacía ni superar los 50 caracteres.");
        }
    }

    public static Brand fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new Brand(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}


