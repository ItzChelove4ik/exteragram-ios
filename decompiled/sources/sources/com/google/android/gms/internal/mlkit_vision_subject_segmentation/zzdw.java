package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
public final class zzdw {
    private final zzoa zza;
    private final Boolean zzb;
    private final zznj zzc;
    private final zzrz zzd;

    public /* synthetic */ zzdw(zzdu r1, zzdv r2) {
        this.zza = zzdu.zze(r1);
        this.zzb = zzdu.zzg(r1);
        this.zzc = null;
        this.zzd = zzdu.zzf(r1);
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzdw) == true) goto L8;
        return false;
    L8:
        zzdw r52 = (zzdw) r5;
        if (Objects.equal(this.zza, r52.zza) == true) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L17;
        if (Objects.equal(null, null) == false) goto L17;
        if (Objects.equal(this.zzd, r52.zzd) == false) goto L17;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, null, this.zzd});
    }

    @zzcd(zza = 1)
    public final zzoa zza() {
        return this.zza;
    }

    @zzcd(zza = 4)
    public final zzrz zzb() {
        return this.zzd;
    }

    @zzcd(zza = 2)
    public final Boolean zzc() {
        return this.zzb;
    }
}
