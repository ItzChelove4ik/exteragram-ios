package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzwn extends zzafv implements zzagy {
    private static final zzwn zze = null;
    private static volatile zzahe zzf;
    private int zza;
    private zzaer zzd;

    static {
        zzwn r0 = new zzwn();
        zze = r0;
        zzafv.zzL(zzwn.class, r0);
    }

    private zzwn() {
        this.zzd = zzaer.zza;
    }

    public static zzwn zzc(zzaer r1, zzafh r2) {
        return (zzwn) zzafv.zzU(zze, r1, r2);
    }

    public static zzwm zzd() {
        return (zzwm) zze.zzF();
    }

    public static zzwn zze() {
        return zze;
    }

    public static zzahe zzg() {
        return (zzahe) zze.zzf(7, null, null);
    }

    public static /* synthetic */ zzwn zzi() {
        return zze;
    }

    public final int zza() {
        return this.zza;
    }

    public final zzaer zzb() {
        return this.zzd;
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
        monitor-enter(zzwn.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwn.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzwm(null);
    L34:
        return new zzwn();
    L36:
        return zzafv.zzM(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zza", "zzd"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzh(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }
}
