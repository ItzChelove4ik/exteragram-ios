package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzqv extends zzadu implements zzafd {
    private static final zzqv zzj = null;
    private static volatile zzafj zzk;
    private int zzb;
    private String zze;
    private zzacr zzf;
    private String zzg;
    private long zzh;
    private zzaef zzi;

    static {
        zzqv r0 = new zzqv();
        zzj = r0;
        zzadu.zzcs(zzqv.class, r0);
    }

    private zzqv() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzacr.zza;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = zzadu.zzcy();
    }

    public static zzqu zzh() {
        return (zzqu) zzj.zzcn();
    }

    public static zzqv zzi() {
        return zzj;
    }

    public static /* synthetic */ zzqv zzo() {
        return zzj;
    }

    public final String zza() {
        return this.zze;
    }

    public final zzacr zzb() {
        return this.zzf;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final long zzd() {
        return this.zzh;
    }

    public final List zze() {
        return this.zzi;
    }

    public final int zzf() {
        return this.zzi.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzafj r7 = zzk;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzqv.class);
        zzafj r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzqv.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzqu(null);
    L34:
        return new zzqv();
    L36:
        return zzadu.zzct(zzj, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005\u001b", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", zzqx.class});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzj(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzk(zzacr r2) {
        r2.getClass();
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzl(String r2) {
        r2.getClass();
        this.zzb |= 4;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzm(long r2) {
        this.zzb |= 8;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzn(zzqx r3) {
        r3.getClass();
        zzaef r0 = this.zzi;
        if (r0.zza() == true) goto L5;
        this.zzi = zzadu.zzcz(r0);
    L5:
        this.zzi.add(r3);
    }
}
