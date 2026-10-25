package com.google.android.play.integrity.internal;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class an extends aw {

    /* renamed from: a, reason: collision with root package name */
    private final int f109a;

    /* renamed from: b, reason: collision with root package name */
    private int f110b;

    public an(int r2, int r3) {
        al.b(r3, r2, "index");
        this.f109a = r2;
        this.f110b = r3;
    }

    public abstract Object a(int r1);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        if (this.f110b >= this.f109a) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.f110b <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext() == false) goto L6;
        int r0 = this.f110b;
        this.f110b = r0 + 1;
        return a(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f110b;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious() == false) goto L6;
        int r0 = this.f110b - 1;
        this.f110b = r0;
        return a(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f110b - 1;
    }
}
