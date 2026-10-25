package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzbc implements Iterator {
    private final int limit;
    private int position;
    private final /* synthetic */ zzbb zzfl;

    public zzbc(zzbb r2) {
        this.zzfl = r2;
        this.position = 0;
        this.limit = r2.size();
    }

    private final byte nextByte() {
        zzbb r0 = this.zzfl;     // Catch: IndexOutOfBoundsException -> L4
        int r1 = this.position;     // Catch: IndexOutOfBoundsException -> L4
        this.position = r1 + 1;     // Catch: IndexOutOfBoundsException -> L4
        return r0.zzj(r1);
    L4:
        e = move-exception;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return 0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.position >= this.limit) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
