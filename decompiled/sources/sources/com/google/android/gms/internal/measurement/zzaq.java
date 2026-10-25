package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzaq implements Iterator {
    final /* synthetic */ zzas zza;
    private int zzb;

    public zzaq(zzas r1) {
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
        String r0 = this.zza.zzb();
        int r1 = this.zzb;
        if (r1 >= r0.length()) goto L6;
        this.zzb = r1 + 1;
        return new zzas(String.valueOf(r1));
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
