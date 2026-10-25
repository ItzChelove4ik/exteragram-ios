package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzmd extends zzadu implements zzafd {
    private static final zzmd zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private int zze;
    private int zzf;

    static {
        zzmd r0 = new zzmd();
        zzg = r0;
        zzadu.zzcs(zzmd.class, r0);
    }

    private zzmd() {
    }

    public static zzmc zza() {
        return (zzmc) zzg.zzcn();
    }

    public static zzmd zzb() {
        return zzg;
    }

    public static /* synthetic */ zzmd zzc() {
        return zzg;
    }

    public final /* synthetic */ void zzd(int r1) {
        this.zze = r1 - 2;
        this.zzb |= 1;
    }

    public final /* synthetic */ void zze(int r2) {
        if (r2 == 1) goto L6;
        this.zzf = r2 - 2;
        this.zzb |= 2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Can't get the number of an unknown enum value.");
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
        monitor-enter(zzmd.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzmd.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzmc(null);
    L34:
        return new zzmd();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001", new Object[]{"zzb", "zze", "zzf"});
    L38:
        return (byte) 1;
    }
}
