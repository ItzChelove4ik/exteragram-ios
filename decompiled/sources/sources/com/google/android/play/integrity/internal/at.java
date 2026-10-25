package com.google.android.play.integrity.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class at extends ar {

    /* renamed from: a, reason: collision with root package name */
    static final ar f118a = null;

    /* renamed from: b, reason: collision with root package name */
    final transient Object[] f119b;

    static {
        f118a = new at(new Object[0], 0);
    }

    public at(Object[] r1, int r2) {
        this.f119b = r1;
    }

    @Override // com.google.android.play.integrity.internal.ar, com.google.android.play.integrity.internal.ao
    public final int a(Object[] r1, int r2) {
        System.arraycopy(this.f119b, 0, r1, 0, 0);
        return 0;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final int b() {
        return 0;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final int c() {
        return 0;
    }

    @Override // com.google.android.play.integrity.internal.ao
    public final Object[] e() {
        return this.f119b;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        al.a(r3, 0, "index");
        Object r2 = this.f119b[r3];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return 0;
    }
}
