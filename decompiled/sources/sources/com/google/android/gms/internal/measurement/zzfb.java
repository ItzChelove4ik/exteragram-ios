package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzfb extends zzadu implements zzafd {
    private static final zzfb zzi = null;
    private static volatile zzafj zzj;
    private int zzb;
    private String zze;
    private boolean zzf;
    private String zzg;
    private long zzh;

    static {
        zzfb r0 = new zzfb();
        zzi = r0;
        zzadu.zzcs(zzfb.class, r0);
    }

    private zzfb() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzfa zza() {
        return (zzfa) zzi.zzcn();
    }

    public static /* synthetic */ zzfb zzf() {
        return zzi;
    }

    public final /* synthetic */ void zzb(String r2) {
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzc(boolean r1) {
        this.zzb |= 2;
        this.zzf = true;
    }

    public final /* synthetic */ void zzd(String r2) {
        r2.getClass();
        this.zzb |= 4;
        this.zzg = r2;
    }

    public final /* synthetic */ void zze(long r2) {
        this.zzb |= 8;
        this.zzh = r2;
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
        monitor-enter(zzfb.class);
        zzafj r12 = zzj;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzadq(zzi);     // Catch: Throwable -> L21
        zzj = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfb.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzfa(null);
    L34:
        return new zzfb();
    L36:
        return zzadu.zzct(zzi, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဂ\u0003", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }
}
