package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zznm extends zzadu implements zzafd {
    private static final zznm zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private String zze;
    private long zzf;

    static {
        zznm r0 = new zznm();
        zzg = r0;
        zzadu.zzcs(zznm.class, r0);
    }

    private zznm() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zznm zzc() {
        return zzg;
    }

    public static /* synthetic */ zznm zzd() {
        return zzg;
    }

    public final String zza() {
        return this.zze;
    }

    public final long zzb() {
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
        monitor-enter(zznm.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zznm.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zznl(null);
    L34:
        return new zznm();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zzb", "zze", "zzf"});
    L38:
        return (byte) 1;
    }
}
