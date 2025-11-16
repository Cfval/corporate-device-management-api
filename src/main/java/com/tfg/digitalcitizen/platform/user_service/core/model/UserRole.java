package com.tfg.digitalcitizen.platform.user_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class UserRole extends NullableStringValueObject {

    public static final int MAX_LENGTH = 50;
    public static final UserRole EMPTY = new UserRole("-");

    private UserRole(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "El rol de usuario no puede superar los " + MAX_LENGTH + " caracteres."
            );
        }
    }

    public static UserRole fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new UserRole(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}

