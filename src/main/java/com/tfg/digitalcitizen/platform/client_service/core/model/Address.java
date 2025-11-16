package com.tfg.digitalcitizen.platform.client_service.core.model;

import com.tfg.digitalcitizen.platform.shared.valueobject.NullableStringValueObject;

public class Address extends NullableStringValueObject {

    public static final Address EMPTY = new Address("-");

    private Address(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        // No validación específica, pero se podría extender
    }

    public static Address fromPrimitive(String value) {
        return (value == null || value.trim().isEmpty() || "-".equals(value))
                ? EMPTY
                : new Address(value);
    }

    public boolean isEmpty() {
        return "-".equals(this.toPrimitive());
    }
}


