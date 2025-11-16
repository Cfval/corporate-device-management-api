
package com.tfg.digitalcitizen.platform.shared.valueobject;

import io.micrometer.common.util.StringUtils;

import java.util.Optional;


public class NonNullableStringValueObject extends ValueObject<String> {
    protected NonNullableStringValueObject(String value) {
        super(value);
    }

    protected String validate(String value) {
        return Optional.ofNullable(value).filter(StringUtils::isNotBlank).orElseThrow();
    }
}