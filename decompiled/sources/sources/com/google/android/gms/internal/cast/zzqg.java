package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzqg extends zzyd implements zzzj {
    private static final zzqg zzs = null;
    private int zzb;
    private zzrp zzd;
    private boolean zze;
    private long zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private zzui zzl;
    private int zzm;
    private int zzn;
    private boolean zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;

    static {
        zzqg r0 = new zzqg();
        zzs = r0;
        zzyd.zzG(zzqg.class, r0);
    }

    private zzqg() {
    }

    public static zzqf zza() {
        return (zzqf) zzs.zzB();
    }

    public static zzqf zzc(zzqg r1) {
        zzya r0 = zzs.zzB();
        r0.zzv(r1);
        return (zzqf) r0;
    }

    public static zzqg zzd() {
        return zzs;
    }

    public static /* synthetic */ zzqg zzo() {
        return zzs;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r21, Object r22, Object r23) {
        int r0 = r21 - 1;
        if (r0 == 0) goto L22;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L18;
        if (r0 == 4) goto L16;
        if (r0 == 5) goto L13;
        throw null;
    L13:
        return zzs;
    L16:
        return new zzqf(null);
    L18:
        return new zzqg();
    L20:
        return zzyd.zzH(zzs, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဇ\u0001\u0003စ\u0002\u0004ဆ\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007င\u0006\bင\u0007\tဉ\b\n᠌\t\u000bင\n\fဇ\u000b\rင\f\u000eင\r\u000fဇ\u000e", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", zzli.zza(), "zzi", zzlg.zza(), "zzj", "zzk", "zzl", "zzm", zzmk.zza(), "zzn", "zzo", "zzp", "zzq", "zzr"});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zze(zzrp r1) {
        r1.getClass();
        this.zzd = r1;
        this.zzb |= 1;
    }

    public final /* synthetic */ void zzf(boolean r2) {
        this.zzb |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zzg(long r2) {
        this.zzb |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzh(int r2) {
        this.zzb |= 64;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzi(int r2) {
        this.zzb |= 128;
        this.zzk = r2;
    }

    public final /* synthetic */ void zzj(int r2) {
        this.zzb |= 1024;
        this.zzn = r2;
    }

    public final /* synthetic */ void zzk(boolean r2) {
        this.zzb |= 2048;
        this.zzo = r2;
    }

    public final /* synthetic */ void zzl(int r2) {
        this.zzb |= 4096;
        this.zzp = r2;
    }

    public final /* synthetic */ void zzm(int r2) {
        this.zzb |= 8192;
        this.zzq = r2;
    }

    public final /* synthetic */ void zzn(boolean r2) {
        this.zzb |= 16384;
        this.zzr = r2;
    }
}
