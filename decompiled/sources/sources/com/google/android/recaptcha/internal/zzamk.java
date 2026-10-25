package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzamk extends zzafv implements zzagy {
    private static final zzamk zze = null;
    private static volatile zzahe zzf;
    private int zza;
    private Object zzd;

    static {
        zzamk r0 = new zzamk();
        zze = r0;
        zzafv.zzL(zzamk.class, r0);
    }

    private zzamk() {
        this.zza = 0;
    }

    public static zzamk zzc(byte[] r1) {
        return (zzamk) zzafv.zzV(zze, r1);
    }

    public static zzamj zzd() {
        return (zzamj) zze.zzF();
    }

    public static /* synthetic */ zzamk zzg() {
        return zze;
    }

    public final zzajj zza() {
        if (this.zza != 1) goto L7;
        return (zzajj) this.zzd;
    L7:
        return zzajj.zze();
    }

    public final zzajt zzb() {
        if (this.zza != 2) goto L7;
        return (zzajt) this.zzd;
    L7:
        return zzajt.zza();
    }

    public final /* synthetic */ void zze(zzajj r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
        this.zza = 1;
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
        monitor-enter(zzamk.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzamk.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzamj(null);
    L34:
        return new zzamk();
    L36:
        return zzafv.zzM(zze, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"zzd", "zza", zzajj.class, zzajt.class});
    L38:
        return (byte) 1;
    }

    public final int zzh() {
        int r1 = this.zza;
        if (r1 == 0) goto L11;
        int r0 = 1;
        if (r1 == 1) goto L10;
        r0 = 2;
        if (r1 == 2) goto L10;
        return 0;
    L10:
        return r0;
    L11:
        return 3;
    }
}
