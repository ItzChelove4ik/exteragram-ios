package com.google.android.gms.internal.play_billing;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class zzbn extends zzci {
    private final int zza;
    private int zzb;

    public zzbn(int r2, int r3) {
        zzbg.zzb(r3, r2, "index");
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        if (this.zzb >= this.zza) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.zzb <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext() == false) goto L6;
        int r0 = this.zzb;
        this.zzb = r0 + 1;
        return zza(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzb;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious() == false) goto L6;
        int r0 = this.zzb - 1;
        this.zzb = r0;
        return zza(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzb - 1;
    }

    public abstract Object zza(int r1);
}
