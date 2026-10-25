package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzaja extends zzafv implements zzagy {
    private static final zzaja zzj = null;
    private static volatile zzahe zzk;
    private String zza;
    private String zzd;
    private int zze;
    private String zzf;
    private String zzg;
    private int zzh;
    private int zzi;

    static {
        zzaja r0 = new zzaja();
        zzj = r0;
        zzafv.zzL(zzaja.class, r0);
    }

    private zzaja() {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzaiz zza() {
        return (zzaiz) zzj.zzF();
    }

    public static /* synthetic */ zzaja zzg() {
        return zzj;
    }

    public final /* synthetic */ void zzb(String r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    public final /* synthetic */ void zzc(int r1) {
        this.zze = r1;
    }

    public final /* synthetic */ void zzd(String r1) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
    }

    public final /* synthetic */ void zze(int r1) {
        this.zzi = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzahe r7 = zzk;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzaja.class);
        zzahe r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzafr(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzaja.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzaiz(null);
    L34:
        return new zzaja();
    L36:
        return zzafv.zzM(zzj, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001Ȉ\u0002\u0004\u0003Ȉ\u0004\u0004\u0005Ȉ\u0006Ȉ\u0007\u0004", new Object[]{"zza", "zze", "zzg", "zzh", "zzd", "zzf", "zzi"});
    L38:
        return (byte) 1;
    }
}
