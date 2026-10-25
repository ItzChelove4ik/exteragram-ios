package com.google.android.gms.internal.fido;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzcm extends zzdc {
    private static final Object zza = null;
    private Object zzb;

    static {
        zza = new Object();
    }

    public zzcm(Object r1) {
        this.zzb = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb == zza) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object r0 = this.zzb;
        Object r1 = zza;
        if (r0 == r1) goto L6;
        this.zzb = r1;
        return r0;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
