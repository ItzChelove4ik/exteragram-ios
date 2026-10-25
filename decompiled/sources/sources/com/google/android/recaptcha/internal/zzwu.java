package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

@Deprecated
/* loaded from: classes5.dex */
public final class zzwu extends zzafv implements zzagy {
    private static final zzwu zzh = null;
    private static volatile zzahe zzi;
    private String zza;
    private String zzd;
    private int zze;
    private boolean zzf;
    private String zzg;

    static {
        zzwu r0 = new zzwu();
        zzh = r0;
        zzafv.zzL(zzwu.class, r0);
    }

    private zzwu() {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzwu zza() {
        return zzh;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L38;
        if (r22 == 2) goto L36;
        if (r22 == 3) goto L34;
        if (r22 == 4) goto L32;
        if (r22 == 5) goto L30;
        if (r22 != 6) goto L28;
        zzahe r1 = zzi;
        if (r1 == null) goto L17;
        return r1;
    L17:
        monitor-enter(zzwu.class);
        zzahe r12 = zzi;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzafr(zzh);     // Catch: Throwable -> L21
        zzi = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwu.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzwt(null);
    L34:
        return new zzwu();
    L36:
        return zzafv.zzM(zzh, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zza", "zzd", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }
}
