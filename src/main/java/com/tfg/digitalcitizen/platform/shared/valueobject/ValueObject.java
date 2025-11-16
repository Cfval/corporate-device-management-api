//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.tfg.digitalcitizen.platform.shared.valueobject;

import java.util.Objects;

public abstract class ValueObject<V> {
    protected final V value;

    protected ValueObject(V value) {
        this.value = (V)this.validate(value);
    }

    protected abstract V validate(V var1);

    public V toPrimitive() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ValueObject<?> that = (ValueObject)o;
            return this.value.equals(that.value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.value});
    }
}
