package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzqx extends zzadu implements zzafd {
    private static final zzqx zzh = null;
    private static volatile zzafj zzi;
    private int zzb;
    private int zze;
    private Object zzf;
    private String zzg;

    static {
        zzqx r0 = new zzqx();
        zzh = r0;
        zzadu.zzcs(zzqx.class, r0);
    }

    private zzqx() {
        this.zze = 0;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzqw zzh() {
        return (zzqw) zzh.zzcn();
    }

    public static /* synthetic */ zzqx zzo() {
        return zzh;
    }

    public final String zza() {
        return this.zzg;
    }

    public final long zzb() {
        if (this.zze == 2) goto L5;
        return 0;
    L5:
        return ((Long) this.zzf).longValue();
    }

    public final boolean zzc() {
        if (this.zze == 3) goto L5;
        return false;
    L5:
        return ((Boolean) this.zzf).booleanValue();
    }

    public final double zzd() {
        if (this.zze == 4) goto L5;
        return 0.0d;
    L5:
        return ((Double) this.zzf).doubleValue();
    }

    public final String zze() {
        if (this.zze == 5) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return (String) this.zzf;
    }

    public final zzacr zzf() {
        if (this.zze != 6) goto L7;
        return (zzacr) this.zzf;
    L7:
        return zzacr.zza;
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
        zzafj r0 = zzi;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzqx.class);
        zzafj r02 = zzi;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzh);     // Catch: Throwable -> L21
        zzi = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzqx.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzqw(null);
    L34:
        return new zzqx();
    L36:
        return zzadu.zzct(zzh, "\u0004\u0006\u0001\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u00025\u0000\u0003:\u0000\u00043\u0000\u0005;\u0000\u0006=\u0000", new Object[]{"zzf", "zze", "zzb", "zzg"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzi(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzj(long r2) {
        this.zze = 2;
        this.zzf = Long.valueOf(r2);
    }

    public final /* synthetic */ void zzk(boolean r2) {
        this.zze = 3;
        this.zzf = Boolean.valueOf(r2);
    }

    public final /* synthetic */ void zzl(double r2) {
        this.zze = 4;
        this.zzf = Double.valueOf(r2);
    }

    public final /* synthetic */ void zzm(String r2) {
        r2.getClass();
        this.zze = 5;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzn(zzacr r2) {
        r2.getClass();
        this.zze = 6;
        this.zzf = r2;
    }

    public final int zzp() {
        int r3 = this.zze;
        if (r3 != 0) goto L5;
        return 6;
    L5:
        if (r3 != 2) goto L7;
        return 1;
    L7:
        if (r3 != 3) goto L9;
        return 2;
    L9:
        if (r3 != 4) goto L11;
        return 3;
    L11:
        if (r3 == 5) goto L16;
        if (r3 == 6) goto L15;
        return 0;
    L15:
        return 5;
    L16:
        return 4;
    }
}
