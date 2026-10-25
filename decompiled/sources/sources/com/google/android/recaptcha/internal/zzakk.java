package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzakk extends zzafv implements zzagy {
    private static final zzakk zzk = null;
    private static volatile zzahe zzl;
    private zzaer zza;
    private String zzd;
    private long zze;
    private zzaer zzf;
    private String zzg;
    private String zzh;
    private boolean zzi;
    private boolean zzj;

    static {
        zzakk r0 = new zzakk();
        zzk = r0;
        zzafv.zzL(zzakk.class, r0);
    }

    private zzakk() {
        zzaer r0 = zzaer.zza;
        this.zza = r0;
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = r0;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzakk zzh() {
        return zzk;
    }

    public static /* synthetic */ zzakk zzr() {
        return zzk;
    }

    public final zzaer zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzd;
    }

    public final long zzc() {
        return this.zze;
    }

    public final zzaer zzd() {
        return this.zzf;
    }

    public final String zze() {
        return this.zzg;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L38;
        if (r92 == 2) goto L36;
        if (r92 == 3) goto L34;
        if (r92 == 4) goto L32;
        if (r92 == 5) goto L30;
        if (r92 != 6) goto L28;
        zzahe r8 = zzl;
        if (r8 == null) goto L17;
        return r8;
    L17:
        monitor-enter(zzakk.class);
        zzahe r82 = zzl;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzafr(zzk);     // Catch: Throwable -> L21
        zzl = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzakk.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzakj(null);
    L34:
        return new zzakk();
    L36:
        return zzafv.zzM(zzk, "\u0004\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001\n\u0002Ȉ\u0003\u0002\u0004\n\u0005Ȉ\u0006Ȉ\u0007\u0007\b\u0007", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L38:
        return (byte) 1;
    }

    public final String zzg() {
        return this.zzh;
    }

    public final /* synthetic */ void zzi(String r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    public final /* synthetic */ void zzj() {
        this.zzd = zzk.zzd;
    }

    public final /* synthetic */ void zzk(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }

    public final /* synthetic */ void zzl() {
        this.zzf = zzk.zzf;
    }

    public final /* synthetic */ void zzn(String r1) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
    }

    public final /* synthetic */ void zzo() {
        this.zzg = zzk.zzg;
    }

    public final /* synthetic */ void zzp(String r1) {
        Objects.requireNonNull(r1);
        this.zzh = r1;
    }

    public final /* synthetic */ void zzq() {
        this.zzh = zzk.zzh;
    }
}
