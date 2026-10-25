package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzff extends zzadu implements zzafd {
    private static final zzff zzm = null;
    private static volatile zzafj zzn;
    private int zzb;
    private int zze;
    private String zzf;
    private zzaef zzg;
    private boolean zzh;
    private zzfl zzi;
    private boolean zzj;
    private boolean zzk;
    private boolean zzl;

    static {
        zzff r0 = new zzff();
        zzm = r0;
        zzadu.zzcs(zzff.class, r0);
    }

    private zzff() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = zzadu.zzcy();
    }

    public static zzfe zzn() {
        return (zzfe) zzm.zzcn();
    }

    public static /* synthetic */ zzff zzq() {
        return zzm;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final int zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final List zzd() {
        return this.zzg;
    }

    public final int zze() {
        return this.zzg.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzfh zzf(int r1) {
        return (zzfh) this.zzg.get(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r11, Object r12, Object r13) {
        int r112 = r11 - 1;
        if (r112 == 0) goto L38;
        if (r112 == 2) goto L36;
        if (r112 == 3) goto L34;
        if (r112 == 4) goto L32;
        if (r112 == 5) goto L30;
        if (r112 != 6) goto L28;
        zzafj r10 = zzn;
        if (r10 == null) goto L17;
        return r10;
    L17:
        monitor-enter(zzff.class);
        zzafj r102 = zzn;     // Catch: Throwable -> L21
        if (r102 != null) goto L23;
        r102 = new zzadq(zzm);     // Catch: Throwable -> L21
        zzn = r102;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzff.class);     // Catch: Throwable -> L21
        return r102;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzm;
    L32:
        return new zzfe(null);
    L34:
        return new zzff();
    L36:
        return zzadu.zzct(zzm, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zzb", "zze", "zzf", "zzg", zzfh.class, "zzh", "zzi", "zzj", "zzk", "zzl"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzfl zzi() {
        zzfl r0 = this.zzi;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzfl.zzk();
    }

    public final boolean zzj() {
        return this.zzj;
    }

    public final boolean zzk() {
        return this.zzk;
    }

    public final boolean zzl() {
        if ((this.zzb & 64) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzm() {
        return this.zzl;
    }

    public final /* synthetic */ void zzo(String r2) {
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzp(int r3, zzfh r4) {
        r4.getClass();
        zzaef r0 = this.zzg;
        if (r0.zza() == true) goto L5;
        this.zzg = zzadu.zzcz(r0);
    L5:
        this.zzg.set(r3, r4);
    }
}
