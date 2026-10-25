package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzaba implements Iterator {
    final /* synthetic */ zzabb zza;
    private int zzb;

    public zzaba(zzabb r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = 0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int r0 = this.zzb;
        zzabb r2 = this.zza;
        if (r0 >= (r2.zzc() - r2.zzb())) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int r0 = this.zzb;
        zzabb r1 = this.zza;
        if (r0 >= (r1.zzc() - r1.zzb())) goto L6;
        zzabc r2 = r1.zzb;
        int r12 = r1.zzb() + r0;
        Object r13 = r2.zzb()[r12];
        this.zzb = r0 + 1;
        return r13;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
