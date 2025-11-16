package com.tfg.digitalcitizen.platform.device_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class OperatingSystem extends NullableStringValueObject {

    public static final OperatingSystem EMPTY = new OperatingSystem("-");

    private static final String OS_PATTERN = "^[A-Za-z0-9 ._-]{2,50}$";

    private OperatingSystem(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(OS_PATTERN)) {
            throw new IllegalArgumentException(
                    "El nombre del sistema operativo debe tener entre 2 y 50 caracteres válidos."
            );
        }
    }

    public static OperatingSystem fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new OperatingSystem(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}
