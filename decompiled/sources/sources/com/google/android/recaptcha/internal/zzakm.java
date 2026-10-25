package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzakm extends zzafv implements zzagy {
    private static final zzakm zzg = null;
    private static volatile zzahe zzh;
    private int zza;
    private String zzd;
    private zzalt zze;
    private zzaer zzf;

    static {
        zzakm r0 = new zzakm();
        zzg = r0;
        zzafv.zzL(zzakm.class, r0);
    }

    private zzakm() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzaer.zza;
    }

    public static zzakl zza() {
        return (zzakl) zzg.zzF();
    }

    public static /* synthetic */ zzakm zzd() {
        return zzg;
    }

    public final /* synthetic */ void zzb(zzalt r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
        this.zza |= 2;
    }

    public final /* synthetic */ void zzc(zzaer r2) {
        Objects.requireNonNull(r2);
        this.zza |= 4;
        this.zzf = r2;
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
        zzahe r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzakm.class);
        zzahe r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzakm.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzakl(null);
    L34:
        return new zzakm();
    L36:
        return zzafv.zzM(zzg, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ለ\u0000\u0002ဉ\u0001\u0003ည\u0002", new Object[]{"zza", "zzd", "zze", "zzf"});
    L38:
        return (byte) 1;
    }
}
