package com.google.android.gms.internal.measurement;

import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzack extends zzacl {
    final /* synthetic */ zzacr zza;
    private int zzb;
    private final int zzc;

    public zzack(zzacr r2) {
        Objects.requireNonNull(r2);
        this.zza = r2;
        this.zzb = 0;
        this.zzc = r2.zzb();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb >= this.zzc) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzacn
    public final byte zza() {
        int r0 = this.zzb;
        if (r0 >= this.zzc) goto L6;
        this.zzb = r0 + 1;
        return this.zza.zza(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
