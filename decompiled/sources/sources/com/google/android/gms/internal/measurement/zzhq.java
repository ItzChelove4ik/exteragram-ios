package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzhq extends zzadu implements zzafd {
    private static final zzhq zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private int zze;
    private long zzf;

    static {
        zzhq r0 = new zzhq();
        zzg = r0;
        zzadu.zzcs(zzhq.class, r0);
    }

    private zzhq() {
    }

    public static zzhp zze() {
        return (zzhp) zzg.zzcn();
    }

    public static /* synthetic */ zzhq zzi() {
        return zzg;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final int zzb() {
        return this.zze;
    }

    public final boolean zzc() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzd() {
        return this.zzf;
    }

    public final /* synthetic */ void zzf(int r2) {
        this.zzb |= 1;
        this.zze = r2;
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
        monitor-enter(zzhq.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhq.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzhp(null);
    L34:
        return new zzhq();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zzb", "zze", "zzf"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzh(long r2) {
        this.zzb |= 2;
        this.zzf = r2;
    }
}
