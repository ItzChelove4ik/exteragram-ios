package com.google.android.gms.internal.vision;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzhs extends zzhu {
    private int zza;
    private final int zzb;
    private final /* synthetic */ zzht zzc;

    public zzhs(zzht r2) {
        this.zzc = r2;
        this.zza = 0;
        this.zzb = r2.zza();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zza >= this.zzb) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzhy
    public final byte zza() {
        int r0 = this.zza;
        if (r0 >= this.zzb) goto L6;
        this.zza = r0 + 1;
        return this.zzc.zzb(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
