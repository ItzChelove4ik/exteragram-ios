package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzfw extends zzadu implements zzafd {
    private static final zzfw zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private int zze;
    private int zzf;

    static {
        zzfw r0 = new zzfw();
        zzg = r0;
        zzadu.zzcs(zzfw.class, r0);
    }

    private zzfw() {
    }

    public static /* synthetic */ zzfw zza() {
        return zzg;
    }

    public final int zzb() {
        int r0 = zzga.zza(this.zze);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final int zzc() {
        int r0 = zzga.zza(this.zzf);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 != 2) goto L7;
        zzadz r22 = zzfz.zza;
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zzb", "zze", r22, "zzf", r22});
    L7:
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzafj r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzfw.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfw.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzfv(null);
    L34:
        return new zzfw();
    L38:
        return (byte) 1;
    }
}
