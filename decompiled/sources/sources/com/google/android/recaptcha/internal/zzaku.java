package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzaku extends zzafv implements zzagy {
    private static final zzaku zzo = null;
    private static volatile zzahe zzp;
    private int zza;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private zzaky zzi;
    private zzakm zzj;
    private zzakw zzk;
    private zzakc zzl;
    private zzakq zzm;
    private zzajy zzn;

    static {
        zzaku r0 = new zzaku();
        zzo = r0;
        zzafv.zzL(zzaku.class, r0);
    }

    private zzaku() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzakt zza() {
        return (zzakt) zzo.zzF();
    }

    public static zzaku zzb() {
        return zzo;
    }

    public static /* synthetic */ zzaku zzi() {
        return zzo;
    }

    public final /* synthetic */ void zzc(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 1;
        this.zzd = r2;
    }

    public final /* synthetic */ void zzd() {
        this.zza &= -2;
        this.zzd = zzo.zzd;
    }

    public final /* synthetic */ void zze(zzakm r1) {
        Objects.requireNonNull(r1);
        this.zzj = r1;
        this.zza |= 64;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r13, Object r14, Object r15) {
        int r132 = r13 - 1;
        if (r132 == 0) goto L38;
        if (r132 == 2) goto L36;
        if (r132 == 3) goto L34;
        if (r132 == 4) goto L32;
        if (r132 == 5) goto L30;
        if (r132 != 6) goto L28;
        zzahe r12 = zzp;
        if (r12 == null) goto L17;
        return r12;
    L17:
        monitor-enter(zzaku.class);
        zzahe r122 = zzp;     // Catch: Throwable -> L21
        if (r122 != null) goto L23;
        r122 = new zzafr(zzo);     // Catch: Throwable -> L21
        zzp = r122;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzaku.class);     // Catch: Throwable -> L21
        return r122;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzo;
    L32:
        return new zzakt(null);
    L34:
        return new zzaku();
    L36:
        return zzafv.zzM(zzo, "\u0004\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(zzakc r1) {
        Objects.requireNonNull(r1);
        this.zzl = r1;
        this.zza |= 256;
    }

    public final /* synthetic */ void zzh(zzakq r1) {
        Objects.requireNonNull(r1);
        this.zzm = r1;
        this.zza |= 512;
    }
}
