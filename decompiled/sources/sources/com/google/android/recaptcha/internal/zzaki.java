package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzaki extends zzafv implements zzagy {
    private static final zzaki zzg = null;
    private static volatile zzahe zzh;
    private int zza;
    private zzakg zzd;
    private String zze;
    private String zzf;

    static {
        zzaki r0 = new zzaki();
        zzg = r0;
        zzafv.zzL(zzaki.class, r0);
    }

    private zzaki() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzakh zzd() {
        return (zzakh) zzg.zzF();
    }

    public static zzahe zze() {
        return (zzahe) zzg.zzf(7, null, null);
    }

    public static /* synthetic */ zzaki zzj() {
        return zzg;
    }

    public final zzakg zza() {
        zzakg r0 = this.zzd;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzakg.zzn();
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
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
        zzahe r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzaki.class);
        zzahe r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzaki.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzakh(null);
    L34:
        return new zzaki();
    L36:
        return zzafv.zzM(zzg, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ለ\u0001\u0003ለ\u0002", new Object[]{"zza", "zzd", "zze", "zzf"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(zzakg r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
        this.zza |= 1;
    }

    public final /* synthetic */ void zzh(String r1) {
        this.zza |= 2;
        this.zze = "18.9.2";
    }

    public final /* synthetic */ void zzi(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 4;
        this.zzf = r2;
    }
}
