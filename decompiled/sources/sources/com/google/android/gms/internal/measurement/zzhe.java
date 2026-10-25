package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzhe extends zzadu implements zzafd {
    private static final zzhe zzl = null;
    private static volatile zzafj zzm;
    private int zzb;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;

    static {
        zzhe r0 = new zzhe();
        zzl = r0;
        zzadu.zzcs(zzhe.class, r0);
    }

    private zzhe() {
    }

    public static zzhd zzi() {
        return (zzhd) zzl.zzcn();
    }

    public static zzhe zzj() {
        return zzl;
    }

    public static /* synthetic */ zzhe zzr() {
        return zzl;
    }

    public final boolean zza() {
        return this.zze;
    }

    public final boolean zzb() {
        return this.zzf;
    }

    public final boolean zzc() {
        return this.zzg;
    }

    public final boolean zzd() {
        return this.zzh;
    }

    public final boolean zze() {
        return this.zzi;
    }

    public final boolean zzf() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L38;
        if (r92 == 2) goto L36;
        if (r92 == 3) goto L34;
        if (r92 == 4) goto L32;
        if (r92 == 5) goto L30;
        if (r92 != 6) goto L28;
        zzafj r8 = zzm;
        if (r8 == null) goto L17;
        return r8;
    L17:
        monitor-enter(zzhe.class);
        zzafj r82 = zzm;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzadq(zzl);     // Catch: Throwable -> L21
        zzm = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhe.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzl;
    L32:
        return new zzhd(null);
    L34:
        return new zzhe();
    L36:
        return zzadu.zzct(zzl, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဇ\u0006", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        return this.zzk;
    }

    public final /* synthetic */ void zzk(boolean r2) {
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzl(boolean r2) {
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzm(boolean r2) {
        this.zzb |= 4;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzn(boolean r2) {
        this.zzb |= 8;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzo(boolean r2) {
        this.zzb |= 16;
        this.zzi = r2;
    }

    public final /* synthetic */ void zzp(boolean r2) {
        this.zzb |= 32;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzq(boolean r2) {
        this.zzb |= 64;
        this.zzk = r2;
    }
}
