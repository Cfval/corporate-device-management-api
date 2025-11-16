package com.tfg.digitalcitizen.platform.device_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class Model extends NullableStringValueObject {

    public static final Model EMPTY = new Model("-");
    public static final int MAX_LENGTH = 50;

    private Model(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && (value.isBlank() || value.length() > MAX_LENGTH)) {
            throw new IllegalArgumentException("El modelo no puede estar vacío ni superar los 50 caracteres.");
        }
    }

    public static Model fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new Model(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}

