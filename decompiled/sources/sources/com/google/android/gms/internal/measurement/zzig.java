package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzig extends zzadu implements zzafd {
    private static final zzig zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private int zze;
    private zzaef zzf;

    static {
        zzig r0 = new zzig();
        zzg = r0;
        zzadu.zzcs(zzig.class, r0);
    }

    private zzig() {
        this.zze = 1;
        this.zzf = zzadu.zzcy();
    }

    public static zzie zza() {
        return (zzie) zzg.zzcn();
    }

    public static /* synthetic */ zzig zzc() {
        return zzg;
    }

    public final /* synthetic */ void zzb(zzhu r3) {
        r3.getClass();
        zzaef r0 = this.zzf;
        if (r0.zza() == true) goto L5;
        this.zzf = zzadu.zzcz(r0);
    L5:
        this.zzf.add(r3);
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
        monitor-enter(zzig.class);
        zzafj r12 = zzh;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzig.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzie(null);
    L34:
        return new zzig();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b", new Object[]{"zzb", "zze", zzif.zza, "zzf", zzhu.class});
    L38:
        return (byte) 1;
    }
}
