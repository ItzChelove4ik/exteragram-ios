package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzno extends zzadu implements zzafd {
    private static final zzno zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private zzni zze;
    private zznk zzf;

    static {
        zzno r0 = new zzno();
        zzg = r0;
        zzadu.zzcs(zzno.class, r0);
    }

    private zzno() {
    }

    public static zzno zzc(byte[] r1, zzadf r2) {
        return (zzno) zzadu.zzcA(zzg, r1, r2);
    }

    public static zznn zzd() {
        return (zznn) zzg.zzcn();
    }

    public static /* synthetic */ zzno zzf() {
        return zzg;
    }

    public final zzni zza() {
        zzni r0 = this.zze;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzni.zzp();
    }

    public final zznk zzb() {
        zznk r0 = this.zzf;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zznk.zza();
    }

    public final /* synthetic */ void zze(zzni r1) {
        r1.getClass();
        this.zze = r1;
        this.zzb |= 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 == 2) goto L36;
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzafj r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzno.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzno.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zznn(null);
    L34:
        return new zzno();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzb", "zze", "zzf"});
    L38:
        return (byte) 1;
    }
}
