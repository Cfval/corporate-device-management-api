package com.tfg.digitalcitizen.platform.client_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NonNullableStringValueObject;

public class Cif extends NonNullableStringValueObject {

    public static final String CIF_PATTERN = "^[A-Za-z0-9]{8,9}$";

    private Cif(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (!value.matches(CIF_PATTERN)) {
            throw new IllegalArgumentException("El CIF debe tener entre 8 y 9 caracteres alfanuméricos.");
        }
    }

    public static Cif fromPrimitive(String value) {
        return new Cif(value);
    }
}

