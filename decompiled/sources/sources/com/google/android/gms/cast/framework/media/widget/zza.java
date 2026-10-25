package com.google.android.gms.cast.framework.media.widget;

/* loaded from: classes4.dex */
public final class zza {
    public final int zza;
    public final int zzb;
    public final boolean zzc;

    public zza(int r1, int r2, boolean r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof zza) == true) goto L9;
        return false;
    L9:
        if (this.zza != ((zza) r4).zza) goto L11;
        return true;
    L11:
        return false;
    }

    public final int hashCode() {
        return Integer.valueOf(this.zza).hashCode();
    }
}
