package com.google.android.gms.cast.internal;

/* loaded from: classes4.dex */
public final class zzp {
    private final int zza;

    public zzp(int r1, zzaa r2) {
        this.zza = r1;
    }

    public final int zza() {
        return this.zza;
    }

    public final boolean zzb(int r1) {
        if ((this.zza & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzc() {
        if (zzb(32) == false) goto L10;
        if (zzb(64) == true) goto L10;
        if (zzb(128) == true) goto L10;
        return true;
    L10:
        if (zzb(64) == true) goto L13;
        return false;
    L13:
        return true;
    }

    public final boolean zzd() {
        if (zzc() == false) goto L5;
        return true;
    L5:
        if (zzb(128) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
