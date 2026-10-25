package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzja extends zzadu implements zzafd {
    private static final zzja zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private zzaef zze;
    private zziw zzf;

    static {
        zzja r0 = new zzja();
        zzg = r0;
        zzadu.zzcs(zzja.class, r0);
    }

    private zzja() {
        this.zze = zzadu.zzcy();
    }

    public static /* synthetic */ zzja zzc() {
        return zzg;
    }

    public final List zza() {
        return this.zze;
    }

    public final zziw zzb() {
        zziw r0 = this.zzf;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zziw.zzc();
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
        monitor-enter(zzja.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzja.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zziz(null);
    L34:
        return new zzja();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zzb", "zze", zzje.class, "zzf"});
    L38:
        return (byte) 1;
    }
}
