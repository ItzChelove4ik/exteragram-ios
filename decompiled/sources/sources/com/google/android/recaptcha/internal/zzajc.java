package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzajc extends zzafv implements zzagy {
    private static final zzajc zzi = null;
    private static volatile zzahe zzj;
    private String zza;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;

    static {
        zzajc r0 = new zzajc();
        zzi = r0;
        zzafv.zzL(zzajc.class, r0);
    }

    private zzajc() {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzajc zza() {
        return zzi;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r7, Object r8, Object r9) {
        int r72 = r7 - 1;
        if (r72 == 0) goto L38;
        if (r72 == 2) goto L36;
        if (r72 == 3) goto L34;
        if (r72 == 4) goto L32;
        if (r72 == 5) goto L30;
        if (r72 != 6) goto L28;
        zzahe r6 = zzj;
        if (r6 == null) goto L17;
        return r6;
    L17:
        monitor-enter(zzajc.class);
        zzahe r62 = zzj;     // Catch: Throwable -> L21
        if (r62 != null) goto L23;
        r62 = new zzafr(zzi);     // Catch: Throwable -> L21
        zzj = r62;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajc.class);     // Catch: Throwable -> L21
        return r62;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzajb(null);
    L34:
        return new zzajc();
    L36:
        return zzafv.zzM(zzi, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }
}
