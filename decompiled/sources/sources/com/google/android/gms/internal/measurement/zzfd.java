package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzfd extends zzadu implements zzafd {
    private static final zzfd zzj = null;
    private static volatile zzafj zzk;
    private int zzb;
    private int zze;
    private zzaef zzf;
    private zzaef zzg;
    private boolean zzh;
    private boolean zzi;

    static {
        zzfd r0 = new zzfd();
        zzj = r0;
        zzadu.zzcs(zzfd.class, r0);
    }

    private zzfd() {
        this.zzf = zzadu.zzcy();
        this.zzg = zzadu.zzcy();
    }

    public static /* synthetic */ zzfd zzl() {
        return zzj;
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

    public final List zzc() {
        return this.zzf;
    }

    public final int zzd() {
        return this.zzf.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzfn zze(int r1) {
        return (zzfn) this.zzf.get(r1);
    }

    public final List zzf() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L38;
        if (r92 == 2) goto L36;
        if (r92 == 3) goto L34;
        if (r92 == 4) goto L32;
        if (r92 == 5) goto L30;
        if (r92 != 6) goto L28;
        zzafj r8 = zzk;
        if (r8 == null) goto L17;
        return r8;
    L17:
        monitor-enter(zzfd.class);
        zzafj r82 = zzk;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzadq(zzj);     // Catch: Throwable -> L21
        zzk = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzfd.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzfc(null);
    L34:
        return new zzfd();
    L36:
        return zzadu.zzct(zzj, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zzb", "zze", "zzf", zzfn.class, "zzg", zzff.class, "zzh", "zzi"});
    L38:
        return (byte) 1;
    }

    public final int zzh() {
        return this.zzg.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzff zzi(int r1) {
        return (zzff) this.zzg.get(r1);
    }

    public final /* synthetic */ void zzj(int r3, zzfn r4) {
        r4.getClass();
        zzaef r0 = this.zzf;
        if (r0.zza() == true) goto L5;
        this.zzf = zzadu.zzcz(r0);
    L5:
        this.zzf.set(r3, r4);
    }

    public final /* synthetic */ void zzk(int r3, zzff r4) {
        r4.getClass();
        zzaef r0 = this.zzg;
        if (r0.zza() == true) goto L5;
        this.zzg = zzadu.zzcz(r0);
    L5:
        this.zzg.set(r3, r4);
    }
}
