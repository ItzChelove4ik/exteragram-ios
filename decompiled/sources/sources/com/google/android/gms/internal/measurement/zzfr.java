package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzfr extends zzadu implements zzafd {
    private static final zzfr zzi = null;
    private static volatile zzafj zzj;
    private int zzb;
    private int zze;
    private String zzf;
    private boolean zzg;
    private zzaef zzh;

    static {
        zzfr r0 = new zzfr();
        zzi = r0;
        zzadu.zzcs(zzfr.class, r0);
    }

    private zzfr() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = zzadu.zzcy();
    }

    public static zzfr zzi() {
        return zzi;
    }

    public static /* synthetic */ zzfr zzj() {
        return zzi;
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

    public final String zzc() {
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

    public final List zzf() {
        return this.zzh;
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
        zzafj r6 = zzj;
        if (r6 == null) goto L17;
        return r6;
    L17:
        monitor-enter(zzfr.class);
        zzafj r62 = zzj;     // Catch: Throwable -> L21
        if (r62 != null) goto L23;
        r62 = new zzadq(zzi);     // Catch: Throwable -> L21
        zzj = r62;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfr.class);     // Catch: Throwable -> L21
        return r62;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzfo(null);
    L34:
        return new zzfr();
    L36:
        return zzadu.zzct(zzi, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zzb", "zze", zzfp.zza, "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }

    public final int zzh() {
        return this.zzh.size();
    }

    public final int zzk() {
        int r0 = zzfq.zza(this.zze);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }
}
