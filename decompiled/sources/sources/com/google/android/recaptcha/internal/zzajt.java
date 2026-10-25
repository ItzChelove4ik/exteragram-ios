package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzajt extends zzafv implements zzagy {
    private static final zzajt zzm = null;
    private static volatile zzahe zzn;
    private int zza;
    private int zzd;
    private int zze;
    private String zzf;
    private String zzg;
    private zzafe zzh;
    private zzahx zzi;
    private int zzj;
    private zzajg zzk;
    private zzage zzl;

    static {
        zzajt r0 = new zzajt();
        zzm = r0;
        zzafv.zzL(zzajt.class, r0);
    }

    private zzajt() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzl = zzafv.zzR();
    }

    public static zzajt zza() {
        return zzm;
    }

    public static /* synthetic */ zzajt zzb() {
        return zzm;
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
        zzahe r11 = zzn;
        if (r11 == null) goto L17;
        return r11;
    L17:
        monitor-enter(zzajt.class);
        zzahe r112 = zzn;     // Catch: Throwable -> L21
        if (r112 != null) goto L23;
        r112 = new zzafr(zzm);     // Catch: Throwable -> L21
        zzn = r112;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajt.class);     // Catch: Throwable -> L21
        return r112;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzm;
    L32:
        return new zzajs(null);
    L34:
        return new zzajt();
    L36:
        return zzafv.zzM(zzm, "\u0000\t\u0000\u0001\u0001\u000b\t\u0000\u0001\u0000\u0001\u0004\u0003ဉ\u0000\u0004ဉ\u0001\u0005\f\u0007\u001b\b\f\tȈ\nȈ\u000bဉ\u0002", new Object[]{"zza", "zzd", "zzh", "zzi", "zzj", "zzl", zzajn.class, "zze", "zzf", "zzg", "zzk"});
    L38:
        return (byte) 1;
    }
}
