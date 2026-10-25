package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzaje extends zzafv implements zzagy {
    private static final zzaje zze = null;
    private static volatile zzahe zzf;
    private String zza;
    private String zzd;

    static {
        zzaje r0 = new zzaje();
        zze = r0;
        zzafv.zzL(zzaje.class, r0);
    }

    private zzaje() {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzaje zza() {
        return zze;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 == 2) goto L36;
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzahe r0 = zzf;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzaje.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzaje.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzajd(null);
    L34:
        return new zzaje();
    L36:
        return zzafv.zzM(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zza", "zzd"});
    L38:
        return (byte) 1;
    }
}
