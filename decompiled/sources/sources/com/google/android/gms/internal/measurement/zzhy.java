package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzhy extends zzadu implements zzafd {
    private static final zzhy zzh = null;
    private static volatile zzafj zzi;
    private int zzb;
    private String zze;
    private String zzf;
    private zzhc zzg;

    static {
        zzhy r0 = new zzhy();
        zzh = r0;
        zzadu.zzcs(zzhy.class, r0);
    }

    private zzhy() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzhy zza() {
        return zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 == 2) goto L36;
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzafj r0 = zzi;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzhy.class);
        zzafj r02 = zzi;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzh);     // Catch: Throwable -> L21
        zzi = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhy.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzhx(null);
    L34:
        return new zzhy();
    L36:
        return zzadu.zzct(zzh, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002", new Object[]{"zzb", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }
}
