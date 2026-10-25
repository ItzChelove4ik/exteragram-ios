package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzis extends zzadu implements zzafd {
    private static final zzis zzh = null;
    private static volatile zzafj zzi;
    private int zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzis r0 = new zzis();
        zzh = r0;
        zzadu.zzcs(zzis.class, r0);
    }

    private zzis() {
    }

    public static zzil zzb() {
        return (zzil) zzh.zzcn();
    }

    public static zzis zzc() {
        return zzh;
    }

    public static /* synthetic */ zzis zze() {
        return zzh;
    }

    public final zzin zza() {
        zzin r0 = zzin.zzb(this.zzf);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzin.zza;
    }

    public final /* synthetic */ void zzd(zzin r1) {
        this.zzf = r1.zza();
        this.zzb |= 2;
    }

    public final int zzf() {
        int r0 = zzir.zza(this.zze);
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
        monitor-enter(zzis.class);
        zzafj r72 = zzi;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzh);     // Catch: Throwable -> L21
        zzi = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzis.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzil(null);
    L34:
        return new zzis();
    L36:
        return zzadu.zzct(zzh, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzb", "zze", zziq.zza, "zzf", zzim.zza, "zzg", zzio.zza});
    L38:
        return (byte) 1;
    }

    public final int zzh() {
        int r0 = zzip.zza(this.zzg);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final /* synthetic */ void zzi(int r1) {
        this.zze = r1 - 1;
        this.zzb |= 1;
    }

    public final /* synthetic */ void zzj(int r1) {
        this.zzg = r1 - 1;
        this.zzb |= 4;
    }
}
