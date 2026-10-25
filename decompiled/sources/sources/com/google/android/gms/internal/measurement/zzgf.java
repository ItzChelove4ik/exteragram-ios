package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzgf extends zzadu implements zzafd {
    private static final zzgf zzj = null;
    private static volatile zzafj zzk;
    private int zzb;
    private zzaef zze;
    private zzaef zzf;
    private zzaef zzg;
    private boolean zzh;
    private zzaef zzi;

    static {
        zzgf r0 = new zzgf();
        zzj = r0;
        zzadu.zzcs(zzgf.class, r0);
    }

    private zzgf() {
        this.zze = zzadu.zzcy();
        this.zzf = zzadu.zzcy();
        this.zzg = zzadu.zzcy();
        this.zzi = zzadu.zzcy();
    }

    public static zzgf zzh() {
        return zzj;
    }

    public static /* synthetic */ zzgf zzi() {
        return zzj;
    }

    public final List zza() {
        return this.zze;
    }

    public final List zzb() {
        return this.zzf;
    }

    public final List zzc() {
        return this.zzg;
    }

    public final boolean zzd() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final boolean zze() {
        return this.zzh;
    }

    public final List zzf() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r11, Object r12, Object r13) {
        int r112 = r11 - 1;
        if (r112 == 0) goto L38;
        if (r112 == 2) goto L36;
        if (r112 == 3) goto L34;
        if (r112 == 4) goto L32;
        if (r112 == 5) goto L30;
        if (r112 != 6) goto L28;
        zzafj r10 = zzk;
        if (r10 == null) goto L17;
        return r10;
    L17:
        monitor-enter(zzgf.class);
        zzafj r102 = zzk;     // Catch: Throwable -> L21
        if (r102 != null) goto L23;
        r102 = new zzadq(zzj);     // Catch: Throwable -> L21
        zzk = r102;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzgf.class);     // Catch: Throwable -> L21
        return r102;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzfs(null);
    L34:
        return new zzgf();
    L36:
        return zzadu.zzct(zzj, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004ဇ\u0000\u0005\u001b", new Object[]{"zzb", "zze", zzfu.class, "zzf", zzfw.class, "zzg", zzgc.class, "zzh", "zzi", zzfu.class});
    L38:
        return (byte) 1;
    }
}
