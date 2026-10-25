package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzala extends zzafv implements zzagy {
    private static final zzala zzn = null;
    private static volatile zzahe zzo;
    private int zza;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private zzaku zzk;
    private String zzl;
    private String zzm;

    static {
        zzala r0 = new zzala();
        zzn = r0;
        zzafv.zzL(zzala.class, r0);
    }

    private zzala() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzl = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzm = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzakz zza() {
        return (zzakz) zzn.zzF();
    }

    public static /* synthetic */ zzala zzh() {
        return zzn;
    }

    public final /* synthetic */ void zzb(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 1;
        this.zzd = r2;
    }

    public final /* synthetic */ void zzc(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 2;
        this.zze = r2;
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
        monitor-enter(zzala.class);
        zzahe r112 = zzo;     // Catch: Throwable -> L21
        if (r112 != null) goto L23;
        r112 = new zzafr(zzn);     // Catch: Throwable -> L21
        zzo = r112;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzala.class);     // Catch: Throwable -> L21
        return r112;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzn;
    L32:
        return new zzakz(null);
    L34:
        return new zzala();
    L36:
        return zzafv.zzM(zzn, "\u0004\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007ለ\u0006\bဉ\u0007\tለ\b\nለ\t", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(zzaku r1) {
        Objects.requireNonNull(r1);
        this.zzk = r1;
        this.zza |= 128;
    }
}
