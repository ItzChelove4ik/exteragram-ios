package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzhg extends zzadu implements zzafd {
    private static final zzhg zzi = null;
    private static volatile zzafj zzj;
    private int zzb;
    private int zze;
    private zzii zzf;
    private zzii zzg;
    private boolean zzh;

    static {
        zzhg r0 = new zzhg();
        zzi = r0;
        zzadu.zzcs(zzhg.class, r0);
    }

    private zzhg() {
    }

    public static zzhf zzi() {
        return (zzhf) zzi.zzcn();
    }

    public static /* synthetic */ zzhg zzn() {
        return zzi;
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

    public final zzii zzc() {
        zzii r0 = this.zzf;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzii.zzk();
    }

    public final boolean zzd() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzii zze() {
        zzii r0 = this.zzg;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzii.zzk();
    }

    public final boolean zzf() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
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
        zzafj r1 = zzj;
        if (r1 == null) goto L17;
        return r1;
    L17:
        monitor-enter(zzhg.class);
        zzafj r12 = zzj;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzadq(zzi);     // Catch: Throwable -> L21
        zzj = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhg.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzhf(null);
    L34:
        return new zzhg();
    L36:
        return zzadu.zzct(zzi, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        return this.zzh;
    }

    public final /* synthetic */ void zzj(int r2) {
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzk(zzii r1) {
        r1.getClass();
        this.zzf = r1;
        this.zzb |= 2;
    }

    public final /* synthetic */ void zzl(zzii r1) {
        this.zzg = r1;
        this.zzb |= 4;
    }

    public final /* synthetic */ void zzm(boolean r2) {
        this.zzb |= 8;
        this.zzh = r2;
    }
}
