package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzalr extends zzafv implements zzagy {
    private static final zzalr zzl = null;
    private static volatile zzahe zzm;
    private int zza;
    private String zzd;
    private String zze;
    private zzaer zzf;
    private zzaer zzg;
    private zzaer zzh;
    private zzagc zzi;
    private int zzj;
    private int zzk;

    static {
        zzalr r0 = new zzalr();
        zzl = r0;
        zzafv.zzL(zzalr.class, r0);
    }

    private zzalr() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        zzaer r0 = zzaer.zza;
        this.zzf = r0;
        this.zzg = r0;
        this.zzh = r0;
        this.zzi = zzafv.zzO();
    }

    public static zzalp zzi() {
        return (zzalp) zzl.zzF();
    }

    public static /* synthetic */ zzalr zzp() {
        return zzl;
    }

    @Deprecated
    public final boolean zza() {
        if ((this.zza & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    @Deprecated
    public final String zzb() {
        return this.zzd;
    }

    @Deprecated
    public final boolean zzc() {
        if ((this.zza & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Deprecated
    public final String zzd() {
        return this.zze;
    }

    public final boolean zze() {
        if ((this.zza & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r11, Object r12, Object r13) {
        int r112 = r11 - 1;
        if (r112 == 0) goto L38;
        if (r112 == 2) goto L36;
        if (r112 == 3) goto L34;
        if (r112 == 4) goto L32;
        if (r112 == 5) goto L30;
        if (r112 != 6) goto L28;
        zzahe r10 = zzm;
        if (r10 == null) goto L17;
        return r10;
    L17:
        monitor-enter(zzalr.class);
        zzahe r102 = zzm;     // Catch: Throwable -> L21
        if (r102 != null) goto L23;
        r102 = new zzafr(zzl);     // Catch: Throwable -> L21
        zzm = r102;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzalr.class);     // Catch: Throwable -> L21
        return r102;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzl;
    L32:
        return new zzalp(null);
    L34:
        return new zzalr();
    L36:
        return zzafv.zzM(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003'\u0004᠌\u0005\u0005င\u0006\u0006ည\u0002\u0007ည\u0003\bည\u0004", new Object[]{"zza", "zzd", "zze", "zzi", "zzj", zzalq.zza, "zzk", "zzf", "zzg", "zzh"});
    L38:
        return (byte) 1;
    }

    public final zzaer zzg() {
        return this.zzf;
    }

    public final zzaer zzh() {
        return this.zzh;
    }

    public final /* synthetic */ void zzj() {
        this.zza &= -2;
        this.zzd = zzl.zzd;
    }

    public final /* synthetic */ void zzk() {
        this.zza &= -3;
        this.zze = zzl.zze;
    }

    public final /* synthetic */ void zzl(zzaer r2) {
        Objects.requireNonNull(r2);
        this.zza |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzn(zzaer r2) {
        Objects.requireNonNull(r2);
        this.zza |= 8;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzo(zzaer r2) {
        Objects.requireNonNull(r2);
        this.zza |= 16;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzq(int r1) {
        this.zzj = 2;
        this.zza |= 32;
    }
}
