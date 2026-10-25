package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzgj extends zzadu implements zzafd {
    private static final zzgj zzi = null;
    private static volatile zzafj zzj;
    private int zzb;
    private String zze;
    private boolean zzf;
    private boolean zzg;
    private int zzh;

    static {
        zzgj r0 = new zzgj();
        zzi = r0;
        zzadu.zzcs(zzgj.class, r0);
    }

    private zzgj() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzgj zzj() {
        return zzi;
    }

    public final String zza() {
        return this.zze;
    }

    public final boolean zzb() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzc() {
        return this.zzf;
    }

    public final boolean zzd() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zze() {
        return this.zzg;
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
        monitor-enter(zzgj.class);
        zzafj r12 = zzj;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzadq(zzi);     // Catch: Throwable -> L21
        zzj = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzgj.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzgi(null);
    L34:
        return new zzgj();
    L36:
        return zzadu.zzct(zzi, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }

    public final int zzh() {
        return this.zzh;
    }

    public final /* synthetic */ void zzi(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zze = r2;
    }
}
