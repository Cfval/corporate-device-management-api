package com.tfg.digitalcitizen.platform.device_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class Imei extends NullableStringValueObject {

    public static final Imei EMPTY = new Imei("-");
    public static final String IMEI_PATTERN = "^\\d{15}$";

    private Imei(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        if (value != null && !value.equals("-") && !value.matches(IMEI_PATTERN)) {
            throw new IllegalArgumentException("El IMEI debe contener exactamente 15 dígitos numéricos.");
        }
    }

    public static Imei fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new Imei(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}


