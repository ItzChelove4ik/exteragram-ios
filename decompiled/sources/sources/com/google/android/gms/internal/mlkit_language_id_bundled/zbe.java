package com.google.android.gms.internal.mlkit_language_id_bundled;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
abstract class zbe extends zbl {
    private final int zba;
    private int zbb;

    public zbe(int r2, int r3) {
        zbc.zbb(r3, r2, "index");
        this.zba = r2;
        this.zbb = r3;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        if (this.zbb >= this.zba) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.zbb <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext() == false) goto L6;
        int r0 = this.zbb;
        this.zbb = r0 + 1;
        return zba(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zbb;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious() == false) goto L6;
        int r0 = this.zbb - 1;
        this.zbb = r0;
        return zba(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zbb - 1;
    }

    public abstract Object zba(int r1);
}
