package com.google.common.base;

/* loaded from: classes5.dex */
final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = null;

    static {
        INSTANCE = new Absent();
    }

    private Absent() {
    }

    public static <T> Optional<T> withType() {
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object r1) {
        if (this != r1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // com.google.common.base.Optional
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.google.common.base.Optional
    public T or(Supplier<? extends T> r1) {
        return (T) Preconditions.checkNotNull(r1.get(), "use Optional.orNull() instead of a Supplier that returns null");
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return null;
    }

    public String toString() {
        return "Optional.absent()";
    }
}
