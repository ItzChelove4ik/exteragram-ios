package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzii extends zzadu implements zzafd {
    private static final zzii zzh = null;
    private static volatile zzafj zzi;
    private zzaee zzb;
    private zzaee zze;
    private zzaef zzf;
    private zzaef zzg;

    static {
        zzii r0 = new zzii();
        zzh = r0;
        zzadu.zzcs(zzii.class, r0);
    }

    private zzii() {
        this.zzb = zzadu.zzcw();
        this.zze = zzadu.zzcw();
        this.zzf = zzadu.zzcy();
        this.zzg = zzadu.zzcy();
    }

    public static zzih zzj() {
        return (zzih) zzh.zzcn();
    }

    public static zzii zzk() {
        return zzh;
    }

    public static /* synthetic */ zzii zzt() {
        return zzh;
    }

    public final List zza() {
        return this.zzb;
    }

    public final int zzb() {
        return this.zzb.size();
    }

    public final List zzc() {
        return this.zze;
    }

    public final int zzd() {
        return this.zze.size();
    }

    public final List zze() {
        return this.zzf;
    }

    public final int zzf() {
        return this.zzf.size();
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
        zzafj r6 = zzi;
        if (r6 == null) goto L17;
        return r6;
    L17:
        monitor-enter(zzii.class);
        zzafj r62 = zzi;     // Catch: Throwable -> L21
        if (r62 != null) goto L23;
        r62 = new zzadq(zzh);     // Catch: Throwable -> L21
        zzi = r62;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzii.class);     // Catch: Throwable -> L21
        return r62;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzih(null);
    L34:
        return new zzii();
    L36:
        return zzadu.zzct(zzh, "\u0004\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zzb", "zze", "zzf", zzhq.class, "zzg", zzik.class});
    L38:
        return (byte) 1;
    }

    public final List zzh() {
        return this.zzg;
    }

    public final int zzi() {
        return this.zzg.size();
    }

    public final /* synthetic */ void zzl(Iterable r3) {
        zzaee r0 = this.zzb;
        if (r0.zza() == true) goto L5;
        this.zzb = zzadu.zzcx(r0);
    L5:
        zzacb.zzcg(r3, this.zzb);
    }

    public final /* synthetic */ void zzm() {
        this.zzb = zzadu.zzcw();
    }

    public final /* synthetic */ void zzn(Iterable r3) {
        zzaee r0 = this.zze;
        if (r0.zza() == true) goto L5;
        this.zze = zzadu.zzcx(r0);
    L5:
        zzacb.zzcg(r3, this.zze);
    }

    public final /* synthetic */ void zzo() {
        this.zze = zzadu.zzcw();
    }

    public final /* synthetic */ void zzp(Iterable r3) {
        zzaef r0 = this.zzf;
        if (r0.zza() == true) goto L5;
        this.zzf = zzadu.zzcz(r0);
    L5:
        zzacb.zzcg(r3, this.zzf);
    }

    public final /* synthetic */ void zzq() {
        this.zzf = zzadu.zzcy();
    }

    public final /* synthetic */ void zzr(Iterable r3) {
        zzaef r0 = this.zzg;
        if (r0.zza() == true) goto L5;
        this.zzg = zzadu.zzcz(r0);
    L5:
        zzacb.zzcg(r3, this.zzg);
    }

    public final /* synthetic */ void zzs() {
        this.zzg = zzadu.zzcy();
    }
}
