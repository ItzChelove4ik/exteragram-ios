package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzajg extends zzafv implements zzagy {
    private static final zzajg zzj = null;
    private static volatile zzahe zzk;
    private int zza;
    private String zzd;
    private int zze;
    private String zzf;
    private int zzg;
    private String zzh;
    private int zzi;

    static {
        zzajg r0 = new zzajg();
        zzj = r0;
        zzafv.zzL(zzajg.class, r0);
    }

    private zzajg() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzajg zza() {
        return zzj;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzahe r7 = zzk;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzajg.class);
        zzahe r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzafr(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajg.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzajf(null);
    L34:
        return new zzajg();
    L36:
        return zzafv.zzM(zzj, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0004\u0002Ȉ\u0003\u0004\u0004Ȉ\u0005\u0004\u0006Ȉ\u0007\u0004", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
    L38:
        return (byte) 1;
    }
}
