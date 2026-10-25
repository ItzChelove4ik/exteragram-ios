package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzalk extends zzafv implements zzagy {
    private static final zzalk zzh = null;
    private static volatile zzahe zzi;
    private int zza;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;

    static {
        zzalk r0 = new zzalk();
        zzh = r0;
        zzafv.zzL(zzalk.class, r0);
    }

    private zzalk() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzalj zza() {
        return (zzalj) zzh.zzF();
    }

    public static /* synthetic */ zzalk zzg() {
        return zzh;
    }

    public final /* synthetic */ void zzb(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 1;
        this.zzd = r2;
    }

    public final /* synthetic */ void zzc(String r1) {
        this.zza |= 2;
        this.zze = "18.9.2";
    }

    public final /* synthetic */ void zzd(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zze(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 8;
        this.zzg = r2;
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
        monitor-enter(zzalk.class);
        zzahe r12 = zzi;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzafr(zzh);     // Catch: Throwable -> L21
        zzi = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzalk.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzalj(null);
    L34:
        return new zzalk();
    L36:
        return zzafv.zzM(zzh, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003", new Object[]{"zza", "zzd", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }
}
