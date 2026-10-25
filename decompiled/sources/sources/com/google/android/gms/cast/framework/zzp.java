package com.google.android.gms.cast.framework;

/* loaded from: classes4.dex */
public final class zzp {
    private int zza;
    private int zzb;

    public zzp() {
        this.zza = 0;
        this.zzb = -1;
    }

    public final zzp zza(int r1) {
        this.zza = r1;
        return this;
    }

    public final zzp zzb(int r1) {
        this.zzb = r1;
        return this;
    }

    public final zzq zzc() {
        int r0 = this.zza;
        if (r0 != 0) goto L6;
        r0 = CastContext.zzb(this.zzb);
        this.zza = r0;
    L6:
        return new zzq(r0, this.zzb, null);
    }
}
