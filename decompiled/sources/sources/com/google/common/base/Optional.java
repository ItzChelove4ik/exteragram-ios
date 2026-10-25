package com.google.common.base;

import java.io.Serializable;

/* loaded from: classes5.dex */
public abstract class Optional<T> implements Serializable {
    public Optional() {
    }

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T r1) {
        return new Present(Preconditions.checkNotNull(r1));
    }

    public abstract boolean equals(Object r1);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract T or(Supplier<? extends T> r1);

    public abstract T orNull();
}
