package com.google.android.gms.internal.cast;

import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzxe extends zzxf {
    final /* synthetic */ zzxk zza;
    private int zzb;
    private final int zzc;

    public zzxe(zzxk r2) {
        Objects.requireNonNull(r2);
        this.zza = r2;
        this.zzb = 0;
        this.zzc = r2.zzc();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb >= this.zzc) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzxh
    public final byte zza() {
        int r0 = this.zzb;
        if (r0 >= this.zzc) goto L6;
        this.zzb = r0 + 1;
        return this.zza.zzb(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
