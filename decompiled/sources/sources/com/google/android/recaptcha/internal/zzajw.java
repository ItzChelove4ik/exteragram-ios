package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzajw extends zzafv implements zzagy {
    private static final zzajw zzn = null;
    private static volatile zzahe zzo;
    private int zza;
    private String zzd;
    private String zze;
    private String zzf;
    private int zzg;
    private String zzh;
    private String zzi;
    private boolean zzj;
    private boolean zzk;
    private String zzl;
    private String zzm;

    static {
        zzajw r0 = new zzajw();
        zzn = r0;
        zzafv.zzL(zzajw.class, r0);
    }

    private zzajw() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzl = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzm = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzajv zza() {
        return (zzajv) zzn.zzF();
    }

    public static /* synthetic */ zzajw zzl() {
        return zzn;
    }

    public final /* synthetic */ void zzb(String r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    public final /* synthetic */ void zzc(String r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    public final /* synthetic */ void zzd(String r1) {
        this.zzf = "18.9.2";
    }

    public final /* synthetic */ void zze(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 1;
        this.zzh = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r12, Object r13, Object r14) {
        int r122 = r12 - 1;
        if (r122 == 0) goto L38;
        if (r122 == 2) goto L36;
        if (r122 == 3) goto L34;
        if (r122 == 4) goto L32;
        if (r122 == 5) goto L30;
        if (r122 != 6) goto L28;
        zzahe r11 = zzo;
        if (r11 == null) goto L17;
        return r11;
    L17:
        monitor-enter(zzajw.class);
        zzahe r112 = zzo;     // Catch: Throwable -> L21
        if (r112 != null) goto L23;
        r112 = new zzafr(zzn);     // Catch: Throwable -> L21
        zzo = r112;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajw.class);     // Catch: Throwable -> L21
        return r112;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzn;
    L32:
        return new zzajv(null);
    L34:
        return new zzajw();
    L36:
        return zzafv.zzM(zzn, "\u0000\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f\u0005ለ\u0000\u0006Ȉ\u0007\u0007\b\u0007\tȈ\nለ\u0001", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(String r1) {
        Objects.requireNonNull(r1);
        this.zzi = r1;
    }

    public final /* synthetic */ void zzh(boolean r1) {
        this.zzj = r1;
    }

    public final /* synthetic */ void zzi(boolean r1) {
        this.zzk = r1;
    }

    public final /* synthetic */ void zzj(String r1) {
        Objects.requireNonNull(r1);
        this.zzl = r1;
    }

    public final /* synthetic */ void zzk(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 2;
        this.zzm = r2;
    }

    public final /* synthetic */ void zzn(int r1) {
        this.zzg = r1 - 2;
    }
}
