package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zznr extends zzadu implements zzafd {
    private static final zznr zzg = null;
    private static volatile zzafj zzh;
    private int zzb;
    private zzaef zze;
    private String zzf;

    static {
        zznr r0 = new zznr();
        zzg = r0;
        zzadu.zzcs(zznr.class, r0);
    }

    private zznr() {
        this.zze = zzadu.zzcy();
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zznr zzb() {
        return zzg;
    }

    public static /* synthetic */ zznr zze() {
        return zzg;
    }

    public final List zza() {
        return this.zze;
    }

    public final /* synthetic */ void zzc(String r2) {
        zzaef r22 = this.zze;
        if (r22.zza() == true) goto L5;
        this.zze = zzadu.zzcz(r22);
    L5:
        this.zze.add(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public final /* synthetic */ void zzd(String r1) {
        this.zzb |= 1;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
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
        zzafj r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zznr.class);
        zzafj r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zznr.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zznq(null);
    L34:
        return new zznr();
    L36:
        return zzadu.zzct(zzg, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001a\u0002ဈ\u0000", new Object[]{"zzb", "zze", "zzf"});
    L38:
        return (byte) 1;
    }
}
