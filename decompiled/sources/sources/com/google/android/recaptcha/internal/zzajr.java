package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzajr extends zzafv implements zzagy {
    private static final zzajr zzj = null;
    private static volatile zzahe zzk;
    private int zza;
    private String zzd;
    private zzajc zze;
    private zzaiw zzf;
    private zzaje zzg;
    private String zzh;
    private String zzi;

    static {
        zzajr r0 = new zzajr();
        zzj = r0;
        zzafv.zzL(zzajr.class, r0);
    }

    private zzajr() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzajq zza() {
        return (zzajq) zzj.zzF();
    }

    public static /* synthetic */ zzajr zzg() {
        return zzj;
    }

    public final /* synthetic */ void zzb(String r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    public final /* synthetic */ void zzc(zzaiw r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
        this.zza |= 2;
    }

    public final /* synthetic */ void zzd(String r1) {
        Objects.requireNonNull(r1);
        this.zzh = r1;
    }

    public final /* synthetic */ void zze(String r1) {
        Objects.requireNonNull(r1);
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
        monitor-enter(zzajr.class);
        zzahe r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzafr(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajr.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzajq(null);
    L34:
        return new zzajr();
    L36:
        return zzafv.zzM(zzj, "\u0000\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000\u0003ဉ\u0001\u0004ဉ\u0002\u0005Ȉ\u0006Ȉ", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
    L38:
        return (byte) 1;
    }
}
