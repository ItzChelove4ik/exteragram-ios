package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzalm extends zzafv implements zzagy {
    private static final zzalm zze = null;
    private static volatile zzahe zzf;
    private zzaer zza;
    private zzaer zzd;

    static {
        zzalm r0 = new zzalm();
        zze = r0;
        zzafv.zzL(zzalm.class, r0);
    }

    private zzalm() {
        zzaer r0 = zzaer.zza;
        this.zza = r0;
        this.zzd = r0;
    }

    public static zzall zzc() {
        return (zzall) zze.zzF();
    }

    public static zzalm zzd() {
        return zze;
    }

    public static /* synthetic */ zzalm zzh() {
        return zze;
    }

    public final zzaer zza() {
        return this.zza;
    }

    public final zzaer zzb() {
        return this.zzd;
    }

    public final /* synthetic */ void zze(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
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
        monitor-enter(zzalm.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzalm.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzall(null);
    L34:
        return new zzalm();
    L36:
        return zzafv.zzM(zze, "\u0004\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\n", new Object[]{"zza", "zzd"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }
}
