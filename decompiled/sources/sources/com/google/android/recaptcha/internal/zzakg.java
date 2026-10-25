package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzakg extends zzafv implements zzagy {
    private static final zzakg zzs = null;
    private static volatile zzahe zzt;
    private int zza;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private zzagd zzj;
    private zzaer zzk;
    private zzakk zzl;
    private zzake zzm;
    private zzako zzn;
    private boolean zzo;
    private long zzp;
    private boolean zzq;
    private boolean zzr;

    static {
        zzakg r0 = new zzakg();
        zzs = r0;
        zzafv.zzL(zzakg.class, r0);
    }

    private zzakg() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = zzafv.zzQ();
        this.zzk = zzaer.zza;
    }

    public static zzakg zzn() {
        return zzs;
    }

    public static /* synthetic */ zzakg zzt() {
        return zzs;
    }

    public final String zza() {
        return this.zzf;
    }

    public final String zzb() {
        return this.zzh;
    }

    public final boolean zzc() {
        if ((this.zza & 128) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzakk zzd() {
        zzakk r0 = this.zzl;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzakk.zzh();
    }

    public final boolean zze() {
        if ((this.zza & 256) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r19, Object r20, Object r21) {
        int r0 = r19 - 1;
        if (r0 == 0) goto L38;
        if (r0 == 2) goto L36;
        if (r0 == 3) goto L34;
        if (r0 == 4) goto L32;
        if (r0 == 5) goto L30;
        if (r0 != 6) goto L28;
        zzahe r02 = zzt;
        if (r02 == null) goto L17;
        return r02;
    L17:
        monitor-enter(zzakg.class);
        zzahe r03 = zzt;     // Catch: Throwable -> L21
        if (r03 != null) goto L23;
        r03 = new zzafr(zzs);     // Catch: Throwable -> L21
        zzt = r03;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzakg.class);     // Catch: Throwable -> L21
        return r03;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzs;
    L32:
        return new zzakf(null);
    L34:
        return new zzakg();
    L36:
        return zzafv.zzM(zzs, "\u0004\u000f\u0000\u0001\u0001\u0011\u000f\u0000\u0001\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007%\bည\u0006\u000bဉ\u0007\fဉ\b\rဉ\t\u000eဇ\n\u000fဂ\u000b\u0010ဇ\f\u0011ဇ\r", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr"});
    L38:
        return (byte) 1;
    }

    public final zzake zzg() {
        zzake r0 = this.zzm;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzake.zzb();
    }

    public final zzako zzh() {
        zzako r0 = this.zzn;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzako.zzd();
    }

    public final boolean zzi() {
        return this.zzo;
    }

    public final long zzj() {
        return this.zzp;
    }

    public final boolean zzk() {
        return this.zzq;
    }

    public final boolean zzl() {
        return this.zzr;
    }

    public final /* synthetic */ void zzo(zzakk r1) {
        Objects.requireNonNull(r1);
        this.zzl = r1;
        this.zza |= 128;
    }

    public final /* synthetic */ void zzp(zzake r1) {
        Objects.requireNonNull(r1);
        this.zzm = r1;
        this.zza |= 256;
    }

    public final /* synthetic */ void zzq(zzako r1) {
        Objects.requireNonNull(r1);
        this.zzn = r1;
        this.zza |= 512;
    }

    public final /* synthetic */ void zzr() {
        this.zzn = null;
        this.zza &= -513;
    }
}
