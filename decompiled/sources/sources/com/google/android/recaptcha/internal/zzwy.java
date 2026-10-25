package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzwy extends zzafv implements zzagy {
    private static final zzwy zze = null;
    private static volatile zzahe zzf;
    private int zza;
    private zzage zzd;

    static {
        zzwy r0 = new zzwy();
        zze = r0;
        zzafv.zzL(zzwy.class, r0);
    }

    private zzwy() {
        this.zzd = zzafv.zzR();
    }

    public static zzwy zzd(byte[] r1, zzafh r2) {
        return (zzwy) zzafv.zzW(zze, r1, r2);
    }

    public static zzwv zze() {
        return (zzwv) zze.zzF();
    }

    public static /* synthetic */ zzwy zzi() {
        return zze;
    }

    public final int zza() {
        return this.zza;
    }

    public final List zzb() {
        return this.zzd;
    }

    public final int zzc() {
        return this.zzd.size();
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 == 2) goto L36;
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzahe r0 = zzf;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzwy.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwy.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzwv(null);
    L34:
        return new zzwy();
    L36:
        return zzafv.zzM(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zza", "zzd", zzwx.class});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(int r1) {
        this.zza = r1;
    }

    public final /* synthetic */ void zzh(zzwx r3) {
        Objects.requireNonNull(r3);
        zzage r0 = this.zzd;
        if (r0.zza() == true) goto L5;
        this.zzd = zzafv.zzS(r0);
    L5:
        this.zzd.add(r3);
    }
}
