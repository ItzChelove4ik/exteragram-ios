package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzama extends zzafv implements zzagy {
    private static final zzama zze = null;
    private static volatile zzahe zzf;
    private int zza;
    private Object zzd;

    static {
        zzama r0 = new zzama();
        zze = r0;
        zzafv.zzL(zzama.class, r0);
    }

    private zzama() {
        this.zza = 0;
    }

    public static zzalz zza() {
        return (zzalz) zze.zzF();
    }

    public static /* synthetic */ zzama zze() {
        return zze;
    }

    public final /* synthetic */ void zzb(boolean r1) {
        this.zza = 1;
        this.zzd = Boolean.TRUE;
    }

    public final /* synthetic */ void zzc(long r2) {
        this.zza = 7;
        this.zzd = Long.valueOf(r2);
    }

    public final /* synthetic */ void zzd(String r2) {
        Objects.requireNonNull(r2);
        this.zza = 11;
        this.zzd = r2;
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
        monitor-enter(zzama.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzama.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzalz(null);
    L34:
        return new zzama();
    L36:
        return zzafv.zzM(zze, "\u0000\u000b\u0001\u0000\u0001\u000b\u000b\u0000\u0000\u0000\u0001:\u0000\u0002=\u0000\u0003Ȼ\u0000\u0004B\u0000\u0005B\u0000\u0006>\u0000\u0007C\u0000\b6\u0000\t4\u0000\n3\u0000\u000bȻ\u0000", new Object[]{"zzd", "zza"});
    L38:
        return (byte) 1;
    }
}
