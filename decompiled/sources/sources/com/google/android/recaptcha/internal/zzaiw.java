package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzaiw extends zzafv implements zzagy {
    private static final zzaiw zzj = null;
    private static volatile zzahe zzk;
    private int zza;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private zzagc zzi;

    static {
        zzaiw r0 = new zzaiw();
        zzj = r0;
        zzafv.zzL(zzaiw.class, r0);
    }

    private zzaiw() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = zzafv.zzO();
    }

    public static zzaiu zza() {
        return (zzaiu) zzj.zzF();
    }

    public static /* synthetic */ zzaiw zzj() {
        return zzj;
    }

    public final /* synthetic */ void zzb(int r1) {
        this.zza = r1;
    }

    public final /* synthetic */ void zzc(String r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    public final /* synthetic */ void zzd(String r1) {
        this.zze = "18.9.2";
    }

    public final /* synthetic */ void zze(String r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzahe r7 = zzk;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzaiw.class);
        zzahe r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzafr(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzaiw.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzaiu(null);
    L34:
        return new zzaiw();
    L36:
        return zzafv.zzM(zzj, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007,", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(String r1) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
    }

    public final /* synthetic */ void zzh(String r1) {
        Objects.requireNonNull(r1);
        this.zzh = r1;
    }

    public final /* synthetic */ void zzi(Iterable r3) {
        zzagc r0 = this.zzi;
        if (r0.zza() == true) goto L5;
        this.zzi = zzafv.zzP(r0);
    L5:
        Iterator r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        zzaiv r02 = (zzaiv) r32.next();
        this.zzi.zzh(r02.zza());
        goto L7
    }
}
