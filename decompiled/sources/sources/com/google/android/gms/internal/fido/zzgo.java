package com.google.android.gms.internal.fido;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzgo extends zzgq {
    final /* synthetic */ zzgx zza;
    private int zzb;
    private final int zzc;

    public zzgo(zzgx r2) {
        this.zza = r2;
        this.zzb = 0;
        this.zzc = r2.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb >= this.zzc) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.fido.zzgs
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
