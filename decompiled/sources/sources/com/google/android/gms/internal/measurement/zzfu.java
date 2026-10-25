package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzfu extends zzadu implements zzafd {
    private static final zzfu zzh = null;
    private static volatile zzafj zzi;
    private int zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzfu r0 = new zzfu();
        zzh = r0;
        zzadu.zzcs(zzfu.class, r0);
    }

    private zzfu() {
    }

    public static /* synthetic */ zzfu zza() {
        return zzh;
    }

    public final int zzb() {
        int r0 = zzga.zza(this.zze);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final int zzc() {
        int r0 = zzfy.zza(this.zzf);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final int zzd() {
        int r0 = zzge.zza(this.zzg);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzafj r7 = zzi;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzfu.class);
        zzafj r72 = zzi;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzh);     // Catch: Throwable -> L21
        zzi = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfu.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzft(null);
    L34:
        return new zzfu();
    L36:
        return zzadu.zzct(zzh, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzb", "zze", zzfz.zza, "zzf", zzfx.zza, "zzg", zzgd.zza});
    L38:
        return (byte) 1;
    }
}
