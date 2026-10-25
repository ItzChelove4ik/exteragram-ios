package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzajl extends zzafv implements zzagy {
    private static final zzajl zzg = null;
    private static volatile zzahe zzh;
    private int zza;
    private zzage zzd;
    private zzage zze;
    private zzaiy zzf;

    static {
        zzajl r0 = new zzajl();
        zzg = r0;
        zzafv.zzL(zzajl.class, r0);
    }

    private zzajl() {
        this.zzd = zzafv.zzR();
        this.zze = zzafv.zzR();
    }

    public static zzajk zzc() {
        return (zzajk) zzg.zzF();
    }

    public static /* synthetic */ zzajl zzg() {
        return zzg;
    }

    public final int zza() {
        return this.zzd.size();
    }

    public final int zzb() {
        return this.zze.size();
    }

    public final /* synthetic */ void zzd(zzajj r3) {
        Objects.requireNonNull(r3);
        zzage r0 = this.zzd;
        if (r0.zza() == true) goto L5;
        this.zzd = zzafv.zzS(r0);
    L5:
        this.zzd.add(r3);
    }

    public final /* synthetic */ void zze(zzajt r3) {
        Objects.requireNonNull(r3);
        zzage r0 = this.zze;
        if (r0.zza() == true) goto L5;
        this.zze = zzafv.zzS(r0);
    L5:
        this.zze.add(r3);
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r7, Object r8, Object r9) {
        int r72 = r7 - 1;
        if (r72 == 0) goto L38;
        if (r72 == 2) goto L36;
        if (r72 == 3) goto L34;
        if (r72 == 4) goto L32;
        if (r72 == 5) goto L30;
        if (r72 != 6) goto L28;
        zzahe r6 = zzh;
        if (r6 == null) goto L17;
        return r6;
    L17:
        monitor-enter(zzajl.class);
        zzahe r62 = zzh;     // Catch: Throwable -> L21
        if (r62 != null) goto L23;
        r62 = new zzafr(zzg);     // Catch: Throwable -> L21
        zzh = r62;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajl.class);     // Catch: Throwable -> L21
        return r62;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzajk(null);
    L34:
        return new zzajl();
    L36:
        return zzafv.zzM(zzg, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003ဉ\u0000", new Object[]{"zza", "zzd", zzajj.class, "zze", zzajt.class, "zzf"});
    L38:
        return (byte) 1;
    }
}
