package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzho extends zzadu implements zzafd {
    private static final zzho zze = null;
    private static volatile zzafj zzf;
    private zzaef zzb;

    static {
        zzho r0 = new zzho();
        zze = r0;
        zzadu.zzcs(zzho.class, r0);
    }

    private zzho() {
        this.zzb = zzadu.zzcy();
    }

    public static zzhh zzb() {
        return (zzhh) zze.zzcn();
    }

    public static zzho zzc() {
        return zze;
    }

    public static /* synthetic */ zzho zze() {
        return zze;
    }

    public final List zza() {
        return this.zzb;
    }

    public final /* synthetic */ void zzd(Iterable r3) {
        zzaef r0 = this.zzb;
        if (r0.zza() == true) goto L5;
        this.zzb = zzadu.zzcz(r0);
    L5:
        zzacb.zzcg(r3, this.zzb);
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
        zzafj r0 = zzf;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzho.class);
        zzafj r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzho.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzhh(null);
    L34:
        return new zzho();
    L36:
        return zzadu.zzct(zze, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzb", zzhl.class});
    L38:
        return (byte) 1;
    }
}
