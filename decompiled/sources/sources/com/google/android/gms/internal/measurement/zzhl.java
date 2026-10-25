package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzhl extends zzadu implements zzafd {
    private static final zzhl zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private int zze;
    private int zzf;

    static {
        zzhl r0 = new zzhl();
        zzg = r0;
        zzadu.zzcs(zzhl.class, r0);
    }

    private zzhl() {
    }

    public static zzhk zza() {
        return (zzhk) zzg.zzcn();
    }

    public static /* synthetic */ zzhl zzb() {
        return zzg;
    }

    public final int zzc() {
        int r0 = zzhj.zza(this.zze);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final int zzd() {
        int r0 = zzhn.zza(this.zzf);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final /* synthetic */ void zze(int r1) {
        this.zze = r1 - 1;
        this.zzb |= 1;
    }

    public final /* synthetic */ void zzf(int r1) {
        this.zzf = r1 - 1;
        this.zzb |= 2;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L38;
        if (r22 == 2) goto L36;
        if (r22 == 3) goto L34;
        if (r22 == 4) goto L32;
        if (r22 == 5) goto L30;
        if (r22 != 6) goto L28;
        zzafj r1 = zzh;
        if (r1 == null) goto L17;
        return r1;
    L17:
        monitor-enter(zzhl.class);
        zzafj r12 = zzh;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhl.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzhk(null);
    L34:
        return new zzhl();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zzb", "zze", zzhi.zza, "zzf", zzhm.zza});
    L38:
        return (byte) 1;
    }
}
