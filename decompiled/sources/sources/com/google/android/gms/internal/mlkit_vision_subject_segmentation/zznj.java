package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
public final class zznj {
    private final zznh zza;
    private final Integer zzb;
    private final Integer zzc;
    private final Boolean zzd;

    public /* synthetic */ zznj(zzng r1, zzni r2) {
        this.zza = zzng.zzc(r1);
        this.zzb = zzng.zze(r1);
        this.zzc = null;
        this.zzd = null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zznj) == true) goto L8;
        return false;
    L8:
        zznj r52 = (zznj) r5;
        if (Objects.equal(this.zza, r52.zza) == true) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L17;
        if (Objects.equal(null, null) == false) goto L17;
        if (Objects.equal(null, null) == false) goto L17;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, null, null});
    }

    @zzcd(zza = 1)
    public final zznh zza() {
        return this.zza;
    }

    @zzcd(zza = 2)
    public final Integer zzb() {
        return this.zzb;
    }
}
