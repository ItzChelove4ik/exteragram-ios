package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zziy extends zzadu implements zzafd {
    private static final zziy zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private String zze;
    private zzaef zzf;

    static {
        zziy r0 = new zziy();
        zzg = r0;
        zzadu.zzcs(zziy.class, r0);
    }

    private zziy() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzadu.zzcy();
    }

    public static /* synthetic */ zziy zzc() {
        return zzg;
    }

    public final String zza() {
        return this.zze;
    }

    public final List zzb() {
        return this.zzf;
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
        zzafj r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zziy.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zziy.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzix(null);
    L34:
        return new zziy();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzb", "zze", "zzf", zzje.class});
    L38:
        return (byte) 1;
    }
}
