package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzmq extends zzadu implements zzafd {
    private static final zzmq zzj = null;
    private static volatile zzafj zzk;
    private int zzb;
    private String zze;
    private zzacr zzf;
    private String zzg;
    private long zzh;
    private zzaew zzi;

    static {
        zzmq r0 = new zzmq();
        zzj = r0;
        zzadu.zzcs(zzmq.class, r0);
    }

    private zzmq() {
        this.zzi = zzaew.zza();
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzacr.zza;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzmq zzh(zzacv r1, zzadf r2) {
        return (zzmq) zzadu.zzcC(zzj, r1, r2);
    }

    public static zzmq zzi() {
        return zzj;
    }

    public static /* synthetic */ zzmq zzj() {
        return zzj;
    }

    public final String zza() {
        return this.zze;
    }

    public final zzacr zzb() {
        return this.zzf;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final long zzd() {
        return this.zzh;
    }

    public final int zze() {
        return this.zzi.size();
    }

    public final Map zzf() {
        return Collections.unmodifiableMap(this.zzi);
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
        monitor-enter(zzmq.class);
        zzafj r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzmq.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzmo(null);
    L34:
        return new zzmq();
    L36:
        return zzadu.zzct(zzj, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0001\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u00052", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", zzmp.zza});
    L38:
        return (byte) 1;
    }
}
