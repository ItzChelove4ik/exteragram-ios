package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zznf extends zzadu implements zzafd {
    private static final zznf zzf = null;
    private static volatile zzafj zzg;
    private int zzb;
    private boolean zze;

    static {
        zznf r0 = new zznf();
        zzf = r0;
        zzadu.zzcs(zznf.class, r0);
    }

    private zznf() {
    }

    public static zznf zzb() {
        return zzf;
    }

    public static /* synthetic */ zznf zzc() {
        return zzf;
    }

    public final boolean zza() {
        return this.zze;
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
        zzafj r0 = zzg;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zznf.class);
        zzafj r02 = zzg;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzf);     // Catch: Throwable -> L21
        zzg = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zznf.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzf;
    L32:
        return new zzne(null);
    L34:
        return new zznf();
    L36:
        return zzadu.zzct(zzf, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"zzb", "zze"});
    L38:
        return (byte) 1;
    }
}
