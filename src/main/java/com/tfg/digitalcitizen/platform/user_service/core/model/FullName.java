package com.tfg.digitalcitizen.platform.user_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NonNullableStringValueObject;

public class FullName extends NonNullableStringValueObject {

    public static final int MIN_LENGTH = 3;
    public static final String NAME_PATTERN = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$";

    private FullName(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value.trim().length() < MIN_LENGTH) {
            throw new IllegalArgumentException("El nombre completo debe tener al menos " + MIN_LENGTH + " caracteres.");
        }

        if (!value.matches(NAME_PATTERN)) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }

        if (!value.contains(" ")) {
            throw new IllegalArgumentException("Debe incluir al menos nombre y primer apellido.");
        }
    }

    public static FullName fromPrimitive(String value) {
        return new FullName(value);
    }
}

