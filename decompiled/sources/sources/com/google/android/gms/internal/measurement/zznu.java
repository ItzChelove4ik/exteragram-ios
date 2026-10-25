package com.google.android.gms.internal.measurement;

import java.util.Map;

/* loaded from: classes4.dex */
public final class zznu extends zzadu implements zzafd {
    private static final zznu zze = null;
    private static volatile zzafj zzf;
    private zzaew zzb;

    static {
        zznu r0 = new zznu();
        zze = r0;
        zzadu.zzcs(zznu.class, r0);
    }

    private zznu() {
        this.zzb = zzaew.zza();
    }

    public static zznu zzb() {
        return zze;
    }

    public static /* synthetic */ zznu zzd() {
        return zze;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zznr zza(String r1, zznr r2) {
        r1.getClass();
        zznr r0 = (zznr) this.zzb.get(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r2;
    }

    public final /* synthetic */ Map zzc() {
        if (this.zzb.zze() == true) goto L6;
        this.zzb = this.zzb.zzc();
    L6:
        return this.zzb;
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
        monitor-enter(zznu.class);
        zzafj r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzadq(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zznu.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zznt(null);
    L34:
        return new zznu();
    L36:
        return zzadu.zzct(zze, "\u0004\u0001\u0000\u0000\u0002\u0002\u0001\u0001\u0000\u0000\u00022", new Object[]{"zzb", zzns.zza});
    L38:
        return (byte) 1;
    }
}
