package com.google.android.recaptcha.internal;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzalc extends zzafv implements zzagy {
    private static final zzalc zzo = null;
    private static volatile zzahe zzp;
    private int zza;
    private String zzd;
    private long zze;
    private int zzf;
    private zzage zzg;
    private zzaer zzh;
    private String zzi;
    private String zzj;
    private zzaer zzk;
    private String zzl;
    private String zzm;
    private String zzn;

    static {
        zzalc r0 = new zzalc();
        zzo = r0;
        zzafv.zzL(zzalc.class, r0);
    }

    private zzalc() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = zzafv.zzR();
        zzaer r1 = zzaer.zza;
        this.zzh = r1;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzk = r1;
        this.zzl = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzm = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzn = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzalc zze() {
        return zzo;
    }

    public static /* synthetic */ zzalc zzg() {
        return zzo;
    }

    public final String zza() {
        return this.zzd;
    }

    public final List zzb() {
        return this.zzg;
    }

    public final zzaer zzc() {
        return this.zzh;
    }

    public final zzaer zzd() {
        return this.zzk;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r14, Object r15, Object r16) {
        int r142 = r14 - 1;
        if (r142 == 0) goto L38;
        if (r142 == 2) goto L36;
        if (r142 == 3) goto L34;
        if (r142 == 4) goto L32;
        if (r142 == 5) goto L30;
        if (r142 != 6) goto L28;
        zzahe r13 = zzp;
        if (r13 == null) goto L17;
        return r13;
    L17:
        monitor-enter(zzalc.class);
        zzahe r132 = zzp;     // Catch: Throwable -> L21
        if (r132 != null) goto L23;
        r132 = new zzafr(zzo);     // Catch: Throwable -> L21
        zzp = r132;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzalc.class);     // Catch: Throwable -> L21
        return r132;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzo;
    L32:
        return new zzalb(null);
    L34:
        return new zzalc();
    L36:
        return zzafv.zzM(zzo, "\u0004\u000b\u0000\u0001\u0001\f\u000b\u0000\u0001\u0000\u0001ለ\u0000\u0002ဂ\u0001\u0004ဌ\u0002\u0005\u001b\u0006ည\u0003\u0007ለ\u0004\bለ\u0005\tည\u0006\nለ\u0007\u000bለ\b\fለ\t", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", zzale.class, "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
    L38:
        return (byte) 1;
    }

    public final int zzh() {
        int r1 = this.zzf;
        if (r1 != 15) goto L5;
        int r12 = 17;
    L21:
        if (r12 != 0) goto L24;
        return 1;
    L24:
        return r12;
    L5:
        if (r1 == 16) goto L19;
        switch(r1) {
            case 0: goto L18;
            case 1: goto L17;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            case 9: goto L9;
            case 10: goto L8;
            default: goto L7;
        };
    L7:
        r12 = 0;
        goto L21
    L8:
        r12 = 12;
        goto L21
    L9:
        r12 = 11;
        goto L21
    L10:
        r12 = 10;
        goto L21
    L11:
        r12 = 9;
        goto L21
    L12:
        r12 = 8;
        goto L21
    L13:
        r12 = 7;
        goto L21
    L14:
        r12 = 6;
        goto L21
    L15:
        r12 = 5;
        goto L21
    L16:
        r12 = 4;
        goto L21
    L17:
        r12 = 3;
        goto L21
    L18:
        r12 = 2;
        goto L21
    L19:
        r12 = 18;
        goto L21
    }
}
