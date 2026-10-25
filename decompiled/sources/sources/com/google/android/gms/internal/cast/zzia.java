package com.google.android.gms.internal.cast;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzia extends zzil {
    private final Object zza;
    private boolean zzb;

    public zzia(Object r1) {
        this.zza = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb == true) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.zzb == true) goto L6;
        this.zzb = true;
        return this.zza;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
