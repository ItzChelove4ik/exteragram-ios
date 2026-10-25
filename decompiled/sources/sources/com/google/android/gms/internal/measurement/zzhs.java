package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzhs extends zzadu implements zzafd {
    private static final zzhs zzm = null;
    private static volatile zzafj zzn;
    private int zzb;
    private zzaef zze;
    private String zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private long zzj;
    private long zzk;
    private long zzl;

    static {
        zzhs r0 = new zzhs();
        zzm = r0;
        zzadu.zzcs(zzhs.class, r0);
    }

    private zzhs() {
        this.zze = zzadu.zzcy();
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzhs zzB() {
        return zzm;
    }

    private final void zzC() {
        zzaef r0 = this.zze;
        if (r0.zza() == true) goto L6;
        this.zze = zzadu.zzcz(r0);
        return;
    }

    public static zzhr zzp() {
        return (zzhr) zzm.zzcn();
    }

    public final /* synthetic */ void zzA(long r2) {
        this.zzb |= 64;
        this.zzl = r2;
    }

    public final List zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zze.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzhw zzc(int r1) {
        return (zzhw) this.zze.get(r1);
    }

    public final String zzd() {
        return this.zzf;
    }

    public final boolean zze() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzf() {
        return this.zzg;
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
        monitor-enter(zzhs.class);
        zzafj r102 = zzn;     // Catch: Throwable -> L21
        if (r102 != null) goto L23;
        r102 = new zzadq(zzm);     // Catch: Throwable -> L21
        zzn = r102;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhs.class);     // Catch: Throwable -> L21
        return r102;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzm;
    L32:
        return new zzhr(null);
    L34:
        return new zzhs();
    L36:
        return zzadu.zzct(zzm, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003\u0006ဂ\u0004\u0007ဂ\u0005\bဂ\u0006", new Object[]{"zzb", "zze", zzhw.class, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzi() {
        return this.zzh;
    }

    public final boolean zzj() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzk() {
        return this.zzi;
    }

    public final boolean zzl() {
        if ((this.zzb & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzm() {
        return this.zzk;
    }

    public final boolean zzn() {
        if ((this.zzb & 64) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzo() {
        return this.zzl;
    }

    public final /* synthetic */ void zzq(int r1, zzhw r2) {
        r2.getClass();
        zzC();
        this.zze.set(r1, r2);
    }

    public final /* synthetic */ void zzr(zzhw r1) {
        r1.getClass();
        zzC();
        this.zze.add(r1);
    }

    public final /* synthetic */ void zzs(Iterable r1) {
        zzC();
        zzacb.zzcg(r1, this.zze);
    }

    public final /* synthetic */ void zzt() {
        this.zze = zzadu.zzcy();
    }

    public final /* synthetic */ void zzu(int r1) {
        zzC();
        this.zze.remove(r1);
    }

    public final /* synthetic */ void zzv(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzw(long r2) {
        this.zzb |= 2;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzx(long r2) {
        this.zzb |= 4;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzy(long r2) {
        this.zzb |= 16;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzz(long r2) {
        this.zzb |= 32;
        this.zzk = r2;
    }
}
