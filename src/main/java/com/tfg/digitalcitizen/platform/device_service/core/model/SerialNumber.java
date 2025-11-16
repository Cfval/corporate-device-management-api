package com.tfg.digitalcitizen.platform.device_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class SerialNumber extends NullableStringValueObject {

    public static final SerialNumber EMPTY = new SerialNumber("-");

    private static final String SERIAL_PATTERN = "^[A-Za-z0-9-]{4,50}$";

    private SerialNumber(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(SERIAL_PATTERN)) {
            throw new IllegalArgumentException(
                    "El número de serie debe tener entre 4 y 50 caracteres alfanuméricos (puede incluir guiones)."
            );
        }
    }

    public static SerialNumber fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new SerialNumber(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}
