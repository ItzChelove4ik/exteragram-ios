package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzaiy extends zzafv implements zzagy {
    private static final zzaiy zzk = null;
    private static volatile zzahe zzl;
    private zzaer zza;
    private String zzd;
    private zzaer zze;
    private String zzf;
    private String zzg;
    private zzaer zzh;
    private String zzi;
    private zzaer zzj;

    static {
        zzaiy r0 = new zzaiy();
        zzk = r0;
        zzafv.zzL(zzaiy.class, r0);
    }

    private zzaiy() {
        zzaer r0 = zzaer.zza;
        this.zza = r0;
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = r0;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = r0;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = r0;
    }

    public static /* synthetic */ zzaiy zza() {
        return zzk;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L38;
        if (r92 == 2) goto L36;
        if (r92 == 3) goto L34;
        if (r92 == 4) goto L32;
        if (r92 == 5) goto L30;
        if (r92 != 6) goto L28;
        zzahe r8 = zzl;
        if (r8 == null) goto L17;
        return r8;
    L17:
        monitor-enter(zzaiy.class);
        zzahe r82 = zzl;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzafr(zzk);     // Catch: Throwable -> L21
        zzl = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzaiy.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzaix(null);
    L34:
        return new zzaiy();
    L36:
        return zzafv.zzM(zzk, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001\n\u0002Ȉ\u0003\n\u0004Ȉ\u0005Ȉ\u0006\n\u0007Ȉ\b\n", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L38:
        return (byte) 1;
    }
}
