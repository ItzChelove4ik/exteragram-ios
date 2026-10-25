package com.google.android.gms.common.internal;

import android.content.ComponentName;

/* loaded from: classes4.dex */
public final class zzn {
    private final String zza;
    private final String zzb;
    private final ComponentName zzc;
    private final int zzd;
    private final boolean zze;

    public zzn(String r1, String r2, int r3, boolean r4) {
        Preconditions.checkNotEmpty(r1);
        this.zza = r1;
        Preconditions.checkNotEmpty(r2);
        this.zzb = r2;
        this.zzc = null;
        this.zzd = 4225;
        this.zze = r4;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzn) == true) goto L8;
        return false;
    L8:
        zzn r52 = (zzn) r5;
        if (Objects.equal(this.zza, r52.zza) == true) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L17;
        if (Objects.equal(this.zzc, r52.zzc) == false) goto L17;
        if (this.zze != r52.zze) goto L17;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, 4225, Boolean.valueOf(this.zze)});
    }

    public final String toString() {
        String r0 = this.zza;
        if (r0 != null) goto L6;
        ComponentName r1 = this.zzc;
        Preconditions.checkNotNull(r1);
        return r1.flattenToString();
    L6:
        return r0;
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final ComponentName zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zze;
    }
}
