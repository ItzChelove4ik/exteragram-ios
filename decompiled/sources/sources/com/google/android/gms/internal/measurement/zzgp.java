package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzgp extends zzadu implements zzafd {
    private static final zzgp zzj = null;
    private static volatile zzafj zzk;
    private int zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;

    static {
        zzgp r0 = new zzgp();
        zzj = r0;
        zzadu.zzcs(zzgp.class, r0);
    }

    private zzgp() {
        this.zze = 14;
        this.zzf = 11;
        this.zzg = 60;
        this.zzh = 13;
        this.zzi = 11;
    }

    public static /* synthetic */ zzgp zza() {
        return zzj;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r7, Object r8, Object r9) {
        int r72 = r7 - 1;
        if (r72 == 0) goto L38;
        if (r72 == 2) goto L36;
        if (r72 == 3) goto L34;
        if (r72 == 4) goto L32;
        if (r72 == 5) goto L30;
        if (r72 != 6) goto L28;
        zzafj r6 = zzk;
        if (r6 == null) goto L17;
        return r6;
    L17:
        monitor-enter(zzgp.class);
        zzafj r62 = zzk;     // Catch: Throwable -> L21
        if (r62 != null) goto L23;
        r62 = new zzadq(zzj);     // Catch: Throwable -> L21
        zzk = r62;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzgp.class);     // Catch: Throwable -> L21
        return r62;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzgo(null);
    L34:
        return new zzgp();
    L36:
        return zzadu.zzct(zzj, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi"});
    L38:
        return (byte) 1;
    }
}
