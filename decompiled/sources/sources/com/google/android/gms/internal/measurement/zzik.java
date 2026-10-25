package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzik extends zzadu implements zzafd {
    private static final zzik zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private int zze;
    private zzaee zzf;

    static {
        zzik r0 = new zzik();
        zzg = r0;
        zzadu.zzcs(zzik.class, r0);
    }

    private zzik() {
        this.zzf = zzadu.zzcw();
    }

    public static zzij zzf() {
        return (zzij) zzg.zzcn();
    }

    public static /* synthetic */ zzik zzj() {
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

    public final List zzc() {
        return this.zzf;
    }

    public final int zzd() {
        return this.zzf.size();
    }

    public final long zze(int r1) {
        return this.zzf.zzc(r1);
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
        monitor-enter(zzik.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzik.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzij(null);
    L34:
        return new zzik();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zzb", "zze", "zzf"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzh(int r2) {
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzi(Iterable r3) {
        zzaee r0 = this.zzf;
        if (r0.zza() == true) goto L5;
        this.zzf = zzadu.zzcx(r0);
    L5:
        zzacb.zzcg(r3, this.zzf);
    }
}
