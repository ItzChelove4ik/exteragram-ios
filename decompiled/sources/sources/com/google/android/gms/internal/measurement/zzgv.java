package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzgv extends zzadu implements zzafd {
    private static final zzgv zzj = null;
    private static volatile zzafj zzk;
    private int zzb;
    private String zze;
    private String zzf;
    private String zzg;
    private int zzh;
    private String zzi;

    static {
        zzgv r0 = new zzgv();
        zzj = r0;
        zzadu.zzcs(zzgv.class, r0);
    }

    private zzgv() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzgv zze() {
        return zzj;
    }

    public static /* synthetic */ zzgv zzf() {
        return zzj;
    }

    public final String zza() {
        return this.zze;
    }

    public final String zzb() {
        return this.zzf;
    }

    public final int zzc() {
        return this.zzh;
    }

    public final String zzd() {
        return this.zzi;
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
        monitor-enter(zzgv.class);
        zzafj r62 = zzk;     // Catch: Throwable -> L21
        if (r62 != null) goto L23;
        r62 = new zzadq(zzj);     // Catch: Throwable -> L21
        zzk = r62;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzgv.class);     // Catch: Throwable -> L21
        return r62;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzgu(null);
    L34:
        return new zzgv();
    L36:
        return zzadu.zzct(zzj, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003\u0005ဈ\u0004", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi"});
    L38:
        return (byte) 1;
    }
}
