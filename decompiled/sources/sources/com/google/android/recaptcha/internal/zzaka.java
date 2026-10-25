package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzaka extends zzafv implements zzagy {
    private static final zzaka zzi = null;
    private static volatile zzahe zzj;
    private int zza;
    private String zzd;
    private zzaer zze;
    private String zzf;
    private String zzg;
    private zzaer zzh;

    static {
        zzaka r0 = new zzaka();
        zzi = r0;
        zzafv.zzL(zzaka.class, r0);
    }

    private zzaka() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        zzaer r1 = zzaer.zza;
        this.zze = r1;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = r1;
    }

    public static zzajz zzg() {
        return (zzajz) zzi.zzF();
    }

    public static zzahe zzh() {
        return (zzahe) zzi.zzf(7, null, null);
    }

    public static /* synthetic */ zzaka zzo() {
        return zzi;
    }

    public final String zza() {
        return this.zzd;
    }

    public final zzaer zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final zzaer zze() {
        return this.zzh;
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
        zzahe r6 = zzj;
        if (r6 == null) goto L17;
        return r6;
    L17:
        monitor-enter(zzaka.class);
        zzahe r62 = zzj;     // Catch: Throwable -> L21
        if (r62 != null) goto L23;
        r62 = new zzafr(zzi);     // Catch: Throwable -> L21
        zzj = r62;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzaka.class);     // Catch: Throwable -> L21
        return r62;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzajz(null);
    L34:
        return new zzaka();
    L36:
        return zzafv.zzM(zzi, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ለ\u0000\u0002ည\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ည\u0004", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzi(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 1;
        this.zzd = r2;
    }

    public final /* synthetic */ void zzj(zzaer r2) {
        Objects.requireNonNull(r2);
        this.zza |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zzk(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzl(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 8;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzn(zzaer r2) {
        Objects.requireNonNull(r2);
        this.zza |= 16;
        this.zzh = r2;
    }
}
