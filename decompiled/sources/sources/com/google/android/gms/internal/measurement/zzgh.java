package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzgh extends zzadu implements zzafd {
    private static final zzgh zzh = null;
    private static volatile zzafj zzi;
    private int zzb;
    private String zze;
    private zzaef zzf;
    private boolean zzg;

    static {
        zzgh r0 = new zzgh();
        zzh = r0;
        zzadu.zzcs(zzgh.class, r0);
    }

    private zzgh() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzadu.zzcy();
    }

    public static /* synthetic */ zzgh zzb() {
        return zzh;
    }

    public final String zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L38;
        if (r22 == 2) goto L36;
        if (r22 == 3) goto L34;
        if (r22 == 4) goto L32;
        if (r22 == 5) goto L30;
        if (r22 != 6) goto L28;
        zzafj r1 = zzi;
        if (r1 == null) goto L17;
        return r1;
    L17:
        monitor-enter(zzgh.class);
        zzafj r12 = zzi;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzadq(zzh);     // Catch: Throwable -> L21
        zzi = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzgh.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzgg(null);
    L34:
        return new zzgh();
    L36:
        return zzadu.zzct(zzh, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဇ\u0001", new Object[]{"zzb", "zze", "zzf", zzgr.class, "zzg"});
    L38:
        return (byte) 1;
    }
}
