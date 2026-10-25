package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

@Deprecated
/* loaded from: classes5.dex */
public final class zzxf extends zzafv implements zzagy {
    public static final /* synthetic */ int zza = 0;
    private static final zzxf zzf = null;
    private static volatile zzahe zzg;
    private String zzd;
    private zzage zze;

    static {
        zzxf r0 = new zzxf();
        zzf = r0;
        zzafv.zzL(zzxf.class, r0);
    }

    private zzxf() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = zzafv.zzR();
    }

    public static /* synthetic */ zzxf zza() {
        return zzf;
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
        zzahe r0 = zzg;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzxf.class);
        zzahe r02 = zzg;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzf);     // Catch: Throwable -> L21
        zzg = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzxf.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzf;
    L32:
        return new zzxe(null);
    L34:
        return new zzxf();
    L36:
        return zzafv.zzM(zzf, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zzd", "zze", zzwu.class});
    L38:
        return (byte) 1;
    }
}
