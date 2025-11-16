package com.tfg.digitalcitizen.platform.client_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NonNullableStringValueObject;

public class CompanyName extends NonNullableStringValueObject {

    public static final int MIN_LENGTH = 3;

    private CompanyName(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("El nombre de la empresa debe tener al menos " + MIN_LENGTH + " caracteres.");
        }
    }

    public static CompanyName fromPrimitive(String value) {
        return new CompanyName(value);
    }
}


