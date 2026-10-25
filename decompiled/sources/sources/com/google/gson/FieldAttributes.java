package com.google.gson;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field r1) {
        Objects.requireNonNull(r1);
        this.field = r1;
    }

    public Type getDeclaredType() {
        return this.field.getGenericType();
    }

    public Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public String getName() {
        return this.field.getName();
    }

    public String toString() {
        return this.field.toString();
    }
}
