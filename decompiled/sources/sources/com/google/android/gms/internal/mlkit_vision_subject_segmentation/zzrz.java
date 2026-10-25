package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
public final class zzrz {
    private final Boolean zza;
    private final Boolean zzb;
    private final Boolean zzc;
    private final Boolean zzd;
    private final Boolean zze;

    public /* synthetic */ zzrz(zzrx r1, zzry r2) {
        this.zza = zzrx.zzh(r1);
        this.zzb = zzrx.zzg(r1);
        this.zzc = zzrx.zzi(r1);
        this.zzd = zzrx.zzk(r1);
        this.zze = zzrx.zzj(r1);
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzrz) == true) goto L8;
        return false;
    L8:
        zzrz r52 = (zzrz) r5;
        if (Objects.equal(this.zza, r52.zza) == true) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L19;
        if (Objects.equal(this.zzc, r52.zzc) == false) goto L19;
        if (Objects.equal(this.zzd, r52.zzd) == false) goto L19;
        if (Objects.equal(this.zze, r52.zze) == false) goto L19;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, this.zze});
    }

    @zzcd(zza = 2)
    public final Boolean zza() {
        return this.zzb;
    }

    @zzcd(zza = 1)
    public final Boolean zzb() {
        return this.zza;
    }

    @zzcd(zza = 3)
    public final Boolean zzc() {
        return this.zzc;
    }

    @zzcd(zza = 5)
    public final Boolean zzd() {
        return this.zze;
    }

    @zzcd(zza = 4)
    public final Boolean zze() {
        return this.zzd;
    }
}
