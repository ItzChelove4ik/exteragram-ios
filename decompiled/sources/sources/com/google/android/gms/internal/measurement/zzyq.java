package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public abstract class zzyq {
    public static final zzyq zzc = null;
    public static final zzyq zzd = null;

    static {
        zzc = new zzym();
        zzd = new zzym();
    }

    public zzyq() {
    }

    public static zzyq zzc(zzyq r2, zzyq r3) {
        if (r2 != null) goto L4;
        return r3;
    L4:
        if (r3 == null) goto L17;
        zzyq r0 = zzc;
        if (r2 == r0) goto L17;
        zzyq r1 = zzd;
        if (r3 == r1) goto L17;
        if (r3 == r0) goto L16;
        if (r2 == r1) goto L16;
        return new zzyn(r2, r3);
    L16:
        return r3;
    L17:
        return r2;
    }

    public abstract void zzb();
}
