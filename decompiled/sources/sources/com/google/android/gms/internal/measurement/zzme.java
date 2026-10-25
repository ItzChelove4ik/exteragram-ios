package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzme extends zzadu implements zzafd {
    private static final zzme zzh = null;
    private static volatile zzafj zzi;
    private int zzb;
    private String zze;
    private zzmd zzf;
    private String zzg;

    static {
        zzme r0 = new zzme();
        zzh = r0;
        zzadu.zzcs(zzme.class, r0);
    }

    private zzme() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzmb zzb() {
        return (zzmb) zzh.zzcn();
    }

    public static /* synthetic */ zzme zzf() {
        return zzh;
    }

    public final String zza() {
        return this.zze;
    }

    public final /* synthetic */ void zzc(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzd(zzmd r1) {
        r1.getClass();
        this.zzf = r1;
        this.zzb |= 2;
    }

    public final /* synthetic */ void zze(String r2) {
        r2.getClass();
        this.zzb |= 4;
        this.zzg = r2;
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
        monitor-enter(zzme.class);
        zzafj r02 = zzi;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzh);     // Catch: Throwable -> L21
        zzi = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzme.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzmb(null);
    L34:
        return new zzme();
    L36:
        return zzadu.zzct(zzh, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001\u0003ဈ\u0002", new Object[]{"zzb", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }
}
