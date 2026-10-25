package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzfl extends zzadu implements zzafd {
    private static final zzfl zzj = null;
    private static volatile zzafj zzk;
    private int zzb;
    private int zze;
    private boolean zzf;
    private String zzg;
    private String zzh;
    private String zzi;

    static {
        zzfl r0 = new zzfl();
        zzj = r0;
        zzadu.zzcs(zzfl.class, r0);
    }

    private zzfl() {
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzfl zzk() {
        return zzj;
    }

    public static /* synthetic */ zzfl zzl() {
        return zzj;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
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

    public final String zze() {
        return this.zzg;
    }

    public final boolean zzf() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzafj r7 = zzk;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzfl.class);
        zzafj r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfl.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzfi(null);
    L34:
        return new zzfl();
    L36:
        return zzadu.zzct(zzj, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zzb", "zze", zzfj.zza, "zzf", "zzg", "zzh", "zzi"});
    L38:
        return (byte) 1;
    }

    public final String zzh() {
        return this.zzh;
    }

    public final boolean zzi() {
        if ((this.zzb & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzj() {
        return this.zzi;
    }

    public final int zzm() {
        int r0 = zzfk.zza(this.zze);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }
}
