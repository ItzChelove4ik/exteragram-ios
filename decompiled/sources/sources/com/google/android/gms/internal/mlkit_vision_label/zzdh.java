package com.google.android.gms.internal.mlkit_vision_label;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
public final class zzdh {
    private final zzke zza;
    private final Boolean zzb;
    private final zzjt zzc;
    private final zzlg zzd;

    public /* synthetic */ zzdh(zzdf r1, zzdg r2) {
        this.zza = zzdf.zze(r1);
        this.zzb = zzdf.zzg(r1);
        this.zzc = null;
        this.zzd = zzdf.zzf(r1);
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzdh) == true) goto L8;
        return false;
    L8:
        zzdh r52 = (zzdh) r5;
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

    @zzcm(zza = 1)
    public final zzke zza() {
        return this.zza;
    }

    @zzcm(zza = 4)
    public final zzlg zzb() {
        return this.zzd;
    }

    @zzcm(zza = 2)
    public final Boolean zzc() {
        return this.zzb;
    }
}
