package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzxb extends zzafv implements zzagy {
    private static final zzxb zzg = null;
    private static volatile zzahe zzh;
    private String zza;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzxb r0 = new zzxb();
        zzg = r0;
        zzafv.zzL(zzxb.class, r0);
    }

    private zzxb() {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzxa zza() {
        return (zzxa) zzg.zzF();
    }

    public static /* synthetic */ zzxb zzd() {
        return zzg;
    }

    public final /* synthetic */ void zzb(String r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    public final /* synthetic */ void zzc(int r1) {
        this.zze = r1;
    }

    public final /* synthetic */ void zze(int r1) {
        this.zzd = zzws.zza(r1);
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
        zzahe r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzxb.class);
        zzahe r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzxb.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzxa(null);
    L34:
        return new zzxb();
    L36:
        return zzafv.zzM(zzg, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zza", "zzd", "zze", "zzf"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(int r1) {
        this.zzf = zzxd.zza(r1);
    }
}
