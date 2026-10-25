package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzake extends zzafv implements zzagy {
    private static final zzake zzd = null;
    private static volatile zzahe zze;
    private zzaer zza;

    static {
        zzake r0 = new zzake();
        zzd = r0;
        zzafv.zzL(zzake.class, r0);
    }

    private zzake() {
        this.zza = zzaer.zza;
    }

    public static zzake zzb() {
        return zzd;
    }

    public static /* synthetic */ zzake zze() {
        return zzd;
    }

    public final zzaer zza() {
        return this.zza;
    }

    public final /* synthetic */ void zzc(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    public final /* synthetic */ void zzd() {
        this.zza = zzd.zza;
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
        zzahe r0 = zze;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzake.class);
        zzahe r02 = zze;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzd);     // Catch: Throwable -> L21
        zze = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzake.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzd;
    L32:
        return new zzakd(null);
    L34:
        return new zzake();
    L36:
        return zzafv.zzM(zzd, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"zza"});
    L38:
        return (byte) 1;
    }
}
