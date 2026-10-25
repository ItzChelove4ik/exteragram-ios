package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzfh extends zzadu implements zzafd {
    private static final zzfh zzi = null;
    private static volatile zzafj zzj;
    private int zzb;
    private zzfr zze;
    private zzfl zzf;
    private boolean zzg;
    private String zzh;

    static {
        zzfh r0 = new zzfh();
        zzi = r0;
        zzadu.zzcs(zzfh.class, r0);
    }

    private zzfh() {
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzfh zzj() {
        return zzi;
    }

    public static /* synthetic */ zzfh zzl() {
        return zzi;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final zzfr zzb() {
        zzfr r0 = this.zze;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzfr.zzi();
    }

    public final boolean zzc() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzfl zzd() {
        zzfl r0 = this.zzf;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzfl.zzk();
    }

    public final boolean zze() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzf() {
        return this.zzg;
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
        monitor-enter(zzfh.class);
        zzafj r12 = zzj;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzadq(zzi);     // Catch: Throwable -> L21
        zzj = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfh.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzfg(null);
    L34:
        return new zzfh();
    L36:
        return zzadu.zzct(zzi, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzi() {
        return this.zzh;
    }

    public final /* synthetic */ void zzk(String r2) {
        this.zzb |= 8;
        this.zzh = r2;
    }
}
