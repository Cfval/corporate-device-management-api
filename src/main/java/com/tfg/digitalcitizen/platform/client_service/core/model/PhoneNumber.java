package com.tfg.digitalcitizen.platform.client_service.core.model;

import com.tfg.digitalcitizen.platform.shared.domain.valueobject.NullableStringValueObject;

public class PhoneNumber extends NullableStringValueObject {

    public static final String PHONE_PATTERN = "^[6789]\\d{8}$"; // Ejemplo: números españoles de 9 dígitos que comienzan con 6, 7, 8 o 9
    public static final PhoneNumber EMPTY = new PhoneNumber("-");

    private PhoneNumber(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(PHONE_PATTERN)) {
            throw new IllegalArgumentException("El número de teléfono debe tener 9 dígitos y comenzar por 6, 7, 8 o 9.");
        }
    }

    public static PhoneNumber fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new PhoneNumber(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}


