package com.google.android.gms.internal.measurement;

import java.io.InputStream;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzpr extends zzadu implements zzafd {
    private static final zzpr zzl = null;
    private static volatile zzafj zzm;
    private int zzb;
    private String zze;
    private boolean zzf;
    private zzaef zzg;
    private int zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;

    static {
        zzpr r0 = new zzpr();
        zzl = r0;
        zzadu.zzcs(zzpr.class, r0);
    }

    private zzpr() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = zzadu.zzcy();
    }

    public static zzpr zzh(InputStream r1, zzadf r2) {
        return (zzpr) zzadu.zzcB(zzl, r1, r2);
    }

    public static /* synthetic */ zzpr zzi() {
        return zzl;
    }

    public final String zza() {
        return this.zze;
    }

    public final boolean zzb() {
        return this.zzf;
    }

    public final zzabz zzc() {
        zzabz r0 = zzabz.zzb(this.zzh);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzabz.zza;
    }

    public final boolean zzd() {
        return this.zzi;
    }

    public final boolean zze() {
        return this.zzj;
    }

    public final boolean zzf() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r10, Object r11, Object r12) {
        int r102 = r10 - 1;
        if (r102 == 0) goto L38;
        if (r102 == 2) goto L36;
        if (r102 == 3) goto L34;
        if (r102 == 4) goto L32;
        if (r102 == 5) goto L30;
        if (r102 != 6) goto L28;
        zzafj r9 = zzm;
        if (r9 == null) goto L17;
        return r9;
    L17:
        monitor-enter(zzpr.class);
        zzafj r92 = zzm;     // Catch: Throwable -> L21
        if (r92 != null) goto L23;
        r92 = new zzadq(zzl);     // Catch: Throwable -> L21
        zzm = r92;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzpr.class);     // Catch: Throwable -> L21
        return r92;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzl;
    L32:
        return new zzpq(null);
    L34:
        return new zzpr();
    L36:
        return zzadu.zzct(zzl, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003\u001a\u0004᠌\u0002\u0005ဇ\u0003\u0006ဇ\u0005\u0007ဇ\u0004", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", zzabz.zzc(), "zzi", "zzk", "zzj"});
    L38:
        return (byte) 1;
    }
}
