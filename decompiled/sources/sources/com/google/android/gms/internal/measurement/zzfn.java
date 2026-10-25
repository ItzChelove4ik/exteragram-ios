package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzfn extends zzadu implements zzafd {
    private static final zzfn zzk = null;
    private static volatile zzafj zzl;
    private int zzb;
    private int zze;
    private String zzf;
    private zzfh zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;

    static {
        zzfn r0 = new zzfn();
        zzk = r0;
        zzadu.zzcs(zzfn.class, r0);
    }

    private zzfn() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzfm zzj() {
        return (zzfm) zzk.zzcn();
    }

    public static /* synthetic */ zzfn zzl() {
        return zzk;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final int zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final zzfh zzd() {
        zzfh r0 = this.zzg;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzfh.zzj();
    }

    public final boolean zze() {
        return this.zzh;
    }

    public final boolean zzf() {
        return this.zzi;
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
        zzafj r7 = zzl;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzfn.class);
        zzafj r72 = zzl;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzk);     // Catch: Throwable -> L21
        zzl = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfn.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzfm(null);
    L34:
        return new zzfn();
    L36:
        return zzadu.zzct(zzk, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        if ((this.zzb & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzi() {
        return this.zzj;
    }

    public final /* synthetic */ void zzk(String r2) {
        this.zzb |= 2;
        this.zzf = r2;
    }
}
