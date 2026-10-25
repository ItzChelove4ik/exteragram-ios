package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzar implements Iterator {
    final /* synthetic */ zzas zza;
    private int zzb;

    public zzar(zzas r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = 0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        String r0 = this.zza.zzb();
        if (this.zzb >= r0.length()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        zzas r0 = this.zza;
        String r1 = r0.zzb();
        int r2 = this.zzb;
        if (r2 >= r1.length()) goto L6;
        this.zzb = r2 + 1;
        return new zzas(String.valueOf(r0.zzb().charAt(r2)));
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
