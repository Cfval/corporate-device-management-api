package com.tfg.digitalcitizen.platform.user_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class Email extends NullableStringValueObject {

    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final Email EMPTY = new Email("-");

    private Email(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Formato de correo electrónico no válido.");
        }
    }

    public static Email fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new Email(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}

