package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzalt extends zzafv implements zzagy {
    private static final zzalt zzf = null;
    private static volatile zzahe zzg;
    private int zza;
    private String zzd;
    private zzage zze;

    static {
        zzalt r0 = new zzalt();
        zzf = r0;
        zzafv.zzL(zzalt.class, r0);
    }

    private zzalt() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = zzafv.zzR();
    }

    public static zzalt zzb(zzaer r1) {
        return (zzalt) zzafv.zzT(zzf, r1);
    }

    public static zzalt zzc(byte[] r1) {
        return (zzalt) zzafv.zzV(zzf, r1);
    }

    public static zzals zzd() {
        return (zzals) zzf.zzF();
    }

    public static /* synthetic */ zzalt zzj() {
        return zzf;
    }

    private final void zzk() {
        zzage r0 = this.zze;
        if (r0.zza() == true) goto L6;
        this.zze = zzafv.zzS(r0);
        return;
    }

    public final List zza() {
        return this.zze;
    }

    public final /* synthetic */ void zze(String r2) {
        Objects.requireNonNull(r2);
        this.zza |= 1;
        this.zzd = r2;
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
        monitor-enter(zzalt.class);
        zzahe r02 = zzg;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzf);     // Catch: Throwable -> L21
        zzg = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzalt.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzf;
    L32:
        return new zzals(null);
    L34:
        return new zzalt();
    L36:
        return zzafv.zzM(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000", new Object[]{"zza", "zze", zzalr.class, "zzd"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(zzalr r1) {
        Objects.requireNonNull(r1);
        zzk();
        this.zze.add(r1);
    }

    public final /* synthetic */ void zzh(Iterable r1) {
        zzk();
        zzaec.zzy(r1, this.zze);
    }

    public final /* synthetic */ void zzi() {
        this.zze = zzafv.zzR();
    }
}
