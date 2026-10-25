package com.google.common.base;

/* loaded from: classes5.dex */
final class Present<T> extends Optional<T> {
    private final T reference;

    public Present(T r1) {
        this.reference = r1;
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object r2) {
        if ((r2 instanceof Present) == true) goto L5;
        return false;
    L5:
        return this.reference.equals(((Present) r2).reference);
    }

    @Override // com.google.common.base.Optional
    public T get() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return true;
    }

    @Override // com.google.common.base.Optional
    public T or(Supplier<? extends T> r1) {
        Preconditions.checkNotNull(r1);
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return this.reference;
    }

    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
