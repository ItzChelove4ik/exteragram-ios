package com.google.android.gms.internal.cast;

import com.google.android.gms.common.internal.Preconditions;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes4.dex */
public final class zzy {
    private final zzj zza;
    private final zzax zzb;
    private final String zzc;
    private zzaa zzd;
    private final zzv zze;

    public zzy(zzj r1, zzax r2, String r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zze = new zzv(this, null);
    }

    @EnsuresNonNull({"SessionFlowSummary"})
    private final zzaa zzg() {
        if (this.zzd != null) goto L6;
        zzaa r0 = zzaa.zza(this.zza, this.zzc);
        this.zzd = r0;
        r0.zzj(1);
    L6:
        return this.zzd;
    }

    private final void zzh() {
        zzaa r0 = this.zzd;
        if (r0 == null) goto L6;
        r0.zzi();
        this.zzd = null;
        return;
    }

    public final /* synthetic */ void zza(zzcs r3) {
        if (r3.zzc() != 2) goto L8;
        if (this.zzd == null) goto L8;
        zzh();
    L8:
        if (r3.zzc() != 2) goto L10;
        this.zzd = zzaa.zza(this.zza, this.zzc);
    L11:
        ((zzaa) Preconditions.checkNotNull(this.zzd)).zzb(r3);
        return;
    L10:
        this.zzd = zzg();
        goto L11
    }

    public final /* synthetic */ zzaa zzb() {
        return zzg();
    }

    public final /* synthetic */ void zzc() {
        zzh();
    }

    public final /* synthetic */ zzax zzd() {
        return this.zzb;
    }

    public final /* synthetic */ zzaa zze() {
        return this.zzd;
    }

    public final /* synthetic */ zzv zzf() {
        return this.zze;
    }
}
