package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzpl extends zzadu implements zzafd {
    private static final zzpl zze = null;
    private static volatile zzafj zzf;
    private zzaef zzb;

    static {
        zzpl r0 = new zzpl();
        zze = r0;
        zzadu.zzcs(zzpl.class, r0);
    }

    private zzpl() {
        this.zzb = zzadu.zzcy();
    }

    public static zzpl zzb(byte[] r1, zzadf r2) {
        return (zzpl) zzadu.zzcA(zze, r1, r2);
    }

    public static /* synthetic */ zzpl zzc() {
        return zze;
    }

    public final List zza() {
        return this.zzb;
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
        zzafj r0 = zzf;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzpl.class);
        zzafj r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzpl.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzpk(null);
    L34:
        return new zzpl();
    L36:
        return zzadu.zzct(zze, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzb"});
    L38:
        return (byte) 1;
    }
}
