package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzwr extends zzafv implements zzagy {
    private static final zzwr zzf = null;
    private static volatile zzahe zzg;
    private String zza;
    private zzaer zzd;
    private int zze;

    static {
        zzwr r0 = new zzwr();
        zzf = r0;
        zzafv.zzL(zzwr.class, r0);
    }

    private zzwr() {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzd = zzaer.zza;
    }

    public static zzwq zzc() {
        return (zzwq) zzf.zzF();
    }

    public static zzwr zzd() {
        return zzf;
    }

    public static /* synthetic */ zzwr zzh() {
        return zzf;
    }

    public final String zza() {
        return this.zza;
    }

    public final zzaer zzb() {
        return this.zzd;
    }

    public final /* synthetic */ void zze(String r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 == 2) goto L36;
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzahe r0 = zzg;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzwr.class);
        zzahe r02 = zzg;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzf);     // Catch: Throwable -> L21
        zzg = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwr.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzf;
    L32:
        return new zzwq(null);
    L34:
        return new zzwr();
    L36:
        return zzafv.zzM(zzf, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zza", "zzd", "zze"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    public final int zzi() {
        int r4 = this.zze;
        int r0 = 2;
        if (r4 != 0) goto L5;
    L15:
        if (r0 != 0) goto L17;
        return 1;
    L17:
        return r0;
    L5:
        if (r4 != 1) goto L7;
        r0 = 3;
        goto L15
    L7:
        if (r4 == 2) goto L13;
        if (r4 == 3) goto L12;
        if (r4 == 4) goto L11;
        r0 = 0;
        goto L15
    L11:
        r0 = 6;
        goto L15
    L12:
        r0 = 5;
        goto L15
    L13:
        r0 = 4;
        goto L15
    }

    public final /* synthetic */ void zzj(int r1) {
        this.zze = r1 - 2;
    }
}
