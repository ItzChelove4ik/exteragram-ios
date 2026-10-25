package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
public final class zze {
    public int zza;
    public int zzb;
    public int zzc;
    public int zzd;
    public int zze;
    public boolean zzf;

    public zze() {
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zze) == true) goto L8;
        return false;
    L8:
        zze r52 = (zze) r5;
        if (this.zza == r52.zza) goto L11;
    L21:
        return false;
    L11:
        if (this.zzb != r52.zzb) goto L21;
        if (this.zzc != r52.zzc) goto L21;
        if (this.zzd != r52.zzd) goto L21;
        if (this.zze != r52.zze) goto L21;
        if (this.zzf != r52.zzf) goto L21;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Integer.valueOf(this.zze), Boolean.valueOf(this.zzf)});
    }
}
