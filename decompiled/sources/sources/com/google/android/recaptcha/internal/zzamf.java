package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzamf extends zzafv implements zzagy {
    private static final zzamf zzq = null;
    private static volatile zzahe zzr;
    private int zza;
    private String zzd;
    private String zze;
    private String zzf;
    private int zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private boolean zzk;
    private boolean zzl;
    private String zzm;
    private String zzn;
    private String zzo;
    private String zzp;

    static {
        zzamf r0 = new zzamf();
        zzq = r0;
        zzafv.zzL(zzamf.class, r0);
    }

    private zzamf() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzm = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzn = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzo = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzp = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzame zza() {
        return (zzame) zzq.zzF();
    }

    public static /* synthetic */ zzamf zzn() {
        return zzq;
    }

    public final /* synthetic */ void zzb(String r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    public final /* synthetic */ void zzc(String r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    public final /* synthetic */ void zzd(String r1) {
        this.zzf = "18.9.2";
    }

    public final /* synthetic */ void zze(String r1) {
        Objects.requireNonNull(r1);
        this.zzh = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r15, Object r16, Object r17) {
        int r14 = r15 - 1;
        if (r14 == 0) goto L38;
        if (r14 == 2) goto L36;
        if (r14 == 3) goto L34;
        if (r14 == 4) goto L32;
        if (r14 == 5) goto L30;
        if (r14 != 6) goto L28;
        zzahe r142 = zzr;
        if (r142 == null) goto L17;
        return r142;
    L17:
        monitor-enter(zzamf.class);
        zzahe r143 = zzr;     // Catch: Throwable -> L21
        if (r143 != null) goto L23;
        r143 = new zzafr(zzq);     // Catch: Throwable -> L21
        zzr = r143;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzamf.class);     // Catch: Throwable -> L21
        return r143;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzq;
    L32:
        return new zzame(null);
    L34:
        return new zzamf();
    L36:
        return zzafv.zzM(zzq, "\u0000\r\u0000\u0001\u0001\u000e\r\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f\u0006Ȉ\u0007Ȉ\bȈ\t\u0007\n\u0007\u000bȈ\fለ\u0000\rለ\u0001\u000eለ\u0002", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(String r1) {
        Objects.requireNonNull(r1);
        this.zzi = r1;
    }

    public final /* synthetic */ void zzh(String r1) {
        Objects.requireNonNull(r1);
        this.zzj = r1;
    }

    public final /* synthetic */ void zzi(boolean r1) {
        this.zzk = r1;
    }

    public final /* synthetic */ void zzj(boolean r1) {
        this.zzl = r1;
    }

    public final /* synthetic */ void zzk(String r1) {
        Objects.requireNonNull(r1);
        this.zzm = r1;
    }

    public final /* synthetic */ void zzl(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 1;
        this.zzn = r2;
    }

    public final /* synthetic */ void zzo(int r1) {
        this.zzg = r1 - 2;
    }
}
