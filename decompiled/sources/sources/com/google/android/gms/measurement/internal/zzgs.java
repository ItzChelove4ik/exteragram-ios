package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzgs {
    final /* synthetic */ zzgu zza;
    private final int zzb;
    private final boolean zzc;
    private final boolean zzd;

    public zzgs(zzgu r1, int r2, boolean r3, boolean r4) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final void zza(String r9) {
        this.zza.zzm(this.zzb, this.zzc, this.zzd, r9, null, null, null);
    }

    public final void zzb(String r9, Object r10) {
        this.zza.zzm(this.zzb, this.zzc, this.zzd, r9, r10, null, null);
    }

    public final void zzc(String r9, Object r10, Object r11) {
        this.zza.zzm(this.zzb, this.zzc, this.zzd, r9, r10, r11, null);
    }

    public final void zzd(String r9, Object r10, Object r11, Object r12) {
        this.zza.zzm(this.zzb, this.zzc, this.zzd, r9, r10, r11, r12);
    }
}
