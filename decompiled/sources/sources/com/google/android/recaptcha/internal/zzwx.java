package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzwx extends zzafv implements zzagy {
    private static final zzwx zzh = null;
    private static volatile zzahe zzi;
    private int zza;
    private zzwr zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzwx r0 = new zzwx();
        zzh = r0;
        zzafv.zzL(zzwx.class, r0);
    }

    private zzwx() {
    }

    public static zzww zzd() {
        return (zzww) zzh.zzF();
    }

    public static /* synthetic */ zzwx zzh() {
        return zzh;
    }

    public final boolean zza() {
        if ((this.zza & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final zzwr zzb() {
        zzwr r0 = this.zzd;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzwr.zzd();
    }

    public final int zzc() {
        return this.zzf;
    }

    public final /* synthetic */ void zze(zzwr r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
        this.zza |= 1;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L38;
        if (r22 == 2) goto L36;
        if (r22 == 3) goto L34;
        if (r22 == 4) goto L32;
        if (r22 == 5) goto L30;
        if (r22 != 6) goto L28;
        zzahe r1 = zzi;
        if (r1 == null) goto L17;
        return r1;
    L17:
        monitor-enter(zzwx.class);
        zzahe r12 = zzi;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzafr(zzh);     // Catch: Throwable -> L21
        zzi = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwx.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzww(null);
    L34:
        return new zzwx();
    L36:
        return zzafv.zzM(zzh, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f\u0003\u000b\u0004\f", new Object[]{"zza", "zzd", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(int r1) {
        this.zzf = r1;
    }

    public final int zzi() {
        int r3 = this.zze;
        int r0 = 2;
        if (r3 != 0) goto L5;
    L12:
        if (r0 != 0) goto L14;
        return 1;
    L14:
        return r0;
    L5:
        if (r3 == 1) goto L11;
        if (r3 == 2) goto L10;
        if (r3 == 3) goto L9;
        r0 = 0;
        goto L12
    L9:
        r0 = 5;
        goto L12
    L10:
        r0 = 4;
        goto L12
    L11:
        r0 = 3;
        goto L12
    }

    public final int zzj() {
        int r4 = this.zzg;
        int r0 = 2;
        if (r4 != 0) goto L5;
    L17:
        if (r0 != 0) goto L19;
        return 1;
    L19:
        return r0;
    L5:
        if (r4 != 1) goto L7;
        r0 = 3;
        goto L17
    L7:
        if (r4 == 2) goto L15;
        r0 = 5;
        if (r4 == 3) goto L17;
        if (r4 == 4) goto L14;
        if (r4 == 5) goto L13;
        r0 = 0;
        goto L17
    L13:
        r0 = 7;
        goto L17
    L14:
        r0 = 6;
        goto L17
    L15:
        r0 = 4;
        goto L17
    }

    public final /* synthetic */ void zzk(int r1) {
        this.zze = zzws.zza(r1);
    }

    public final /* synthetic */ void zzl(int r1) {
        this.zzg = zzxd.zza(r1);
    }
}
