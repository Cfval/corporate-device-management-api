package com.tfg.digitalcitizen.platform.user_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class Department extends NullableStringValueObject {

    public static final int MAX_LENGTH = 50;
    public static final Department EMPTY = new Department("-");

    private Department(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("El nombre del departamento no puede superar los " + MAX_LENGTH + " caracteres.");
        }
    }

    public static Department fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new Department(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}

