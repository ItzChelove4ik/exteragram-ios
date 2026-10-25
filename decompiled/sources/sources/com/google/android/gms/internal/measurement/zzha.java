package com.google.android.gms.internal.measurement;

import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzha extends zzadu implements zzafd {
    private static final zzha zzp = null;
    private static volatile zzafj zzq;
    private int zzb;
    private String zze;
    private String zzf;
    private String zzg;
    private long zzh;
    private String zzi;
    private String zzj;
    private String zzk;
    private long zzl;
    private zzaew zzm;
    private zzaew zzn;
    private String zzo;

    static {
        zzha r0 = new zzha();
        zzp = r0;
        zzadu.zzcs(zzha.class, r0);
    }

    private zzha() {
        this.zzm = zzaew.zza();
        this.zzn = zzaew.zza();
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzk = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzo = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzha zzN() {
        return zzp;
    }

    public static zzgx zzt() {
        return (zzgx) zzp.zzcn();
    }

    public static zzha zzu() {
        return zzp;
    }

    public final /* synthetic */ void zzA() {
        this.zzb &= -5;
        this.zzg = zzp.zzg;
    }

    public final /* synthetic */ void zzB(long r2) {
        this.zzb |= 8;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzC(String r2) {
        this.zzb |= 16;
        this.zzi = r2;
    }

    public final /* synthetic */ void zzD() {
        this.zzb &= -17;
        this.zzi = zzp.zzi;
    }

    public final /* synthetic */ void zzE(String r2) {
        this.zzb |= 32;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzF() {
        this.zzb &= -33;
        this.zzj = zzp.zzj;
    }

    public final /* synthetic */ void zzG(String r2) {
        this.zzb |= 64;
        this.zzk = r2;
    }

    public final /* synthetic */ void zzH() {
        this.zzb &= -65;
        this.zzk = zzp.zzk;
    }

    public final /* synthetic */ void zzI(long r2) {
        this.zzb |= 128;
        this.zzl = r2;
    }

    public final /* synthetic */ Map zzJ() {
        if (this.zzm.zze() == true) goto L6;
        this.zzm = this.zzm.zzc();
    L6:
        return this.zzm;
    }

    public final /* synthetic */ Map zzK() {
        if (this.zzn.zze() == true) goto L6;
        this.zzn = this.zzn.zzc();
    L6:
        return this.zzn;
    }

    public final /* synthetic */ void zzL(String r2) {
        this.zzb |= 256;
        this.zzo = r2;
    }

    public final /* synthetic */ void zzM() {
        this.zzb &= -257;
        this.zzo = zzp.zzo;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final String zzb() {
        return this.zze;
    }

    public final boolean zzc() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final boolean zze() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzf() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r15, Object r16, Object r17) {
        int r14 = r15 - 1;
        if (r14 == 0) goto L38;
        if (r14 == 2) goto L36;
        if (r14 == 3) goto L34;
        if (r14 == 4) goto L32;
        if (r14 == 5) goto L30;
        if (r14 != 6) goto L28;
        zzafj r142 = zzq;
        if (r142 == null) goto L17;
        return r142;
    L17:
        monitor-enter(zzha.class);
        zzafj r143 = zzq;     // Catch: Throwable -> L21
        if (r143 != null) goto L23;
        r143 = new zzadq(zzp);     // Catch: Throwable -> L21
        zzq = r143;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzha.class);     // Catch: Throwable -> L21
        return r143;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzp;
    L32:
        return new zzgx(null);
    L34:
        return new zzha();
    L36:
        return zzadu.zzct(zzp, "\u0004\u000b\u0000\u0001\u0001\u000b\u000b\u0002\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဂ\u0007\t2\n2\u000bဈ\b", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", zzgy.zza, "zzn", zzgz.zza, "zzo"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzi() {
        return this.zzh;
    }

    public final boolean zzj() {
        if ((this.zzb & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzk() {
        return this.zzi;
    }

    public final boolean zzl() {
        if ((this.zzb & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzm() {
        return this.zzj;
    }

    public final boolean zzn() {
        if ((this.zzb & 64) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzo() {
        return this.zzk;
    }

    public final boolean zzp() {
        if ((this.zzb & 128) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzq() {
        return this.zzl;
    }

    public final boolean zzr() {
        if ((this.zzb & 256) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzs() {
        return this.zzo;
    }

    public final /* synthetic */ void zzv(String r2) {
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzw() {
        this.zzb &= -2;
        this.zze = zzp.zze;
    }

    public final /* synthetic */ void zzx(String r2) {
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzy() {
        this.zzb &= -3;
        this.zzf = zzp.zzf;
    }

    public final /* synthetic */ void zzz(String r2) {
        this.zzb |= 4;
        this.zzg = r2;
    }
}
