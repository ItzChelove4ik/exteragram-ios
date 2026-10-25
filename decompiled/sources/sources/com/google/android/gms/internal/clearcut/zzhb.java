package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
public abstract class zzhb extends zzfu<zzhb> implements Cloneable {
    private static volatile zzhb[] zzbkd;

    public static zzhb[] zzge() {
        if (zzbkd != null) goto L16;
        Object r0 = zzfy.zzrr;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (zzbkd != null) goto L11;
        zzbkd = new zzhb[0];     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L16:
        return zzbkd;
    }
}
