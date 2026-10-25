package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class zzis extends zzfi implements zzgm {
    private static final zzis zzb = null;
    private int zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private int zzh;
    private long zzi;
    private long zzj;
    private boolean zzk;
    private int zzl;
    private int zzm;
    private long zzn;

    static {
        zzis r0 = new zzis();
        zzb = r0;
        zzfi.zzw(zzis.class, r0);
    }

    private zzis() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ void zzA(zzis r1, int r2) {
        r1.zzd |= 128;
        r1.zzl = r2;
    }

    public static /* synthetic */ void zzB(zzis r1, int r2) {
        r1.zzd |= 256;
        r1.zzm = r2;
    }

    public static /* synthetic */ void zzC(zzis r1, int r2) {
        r1.zzd |= 8;
        r1.zzh = r2;
    }

    public static /* synthetic */ void zzD(zzis r1, long r2) {
        r1.zzd |= 16;
        r1.zzi = r2;
    }

    public static /* synthetic */ void zzE(zzis r1, long r2) {
        r1.zzd |= 32;
        r1.zzj = r2;
    }

    public static /* synthetic */ void zzF(zzis r0, long r1) {
        r0.zzd |= 512;
        r0.zzn = 772604006;
    }

    public static /* synthetic */ void zzG(zzis r1, String r2) {
        r2.getClass();
        r1.zzd |= 4;
        r1.zzg = r2;
    }

    public static /* synthetic */ void zzH(zzis r1, boolean r2) {
        r1.zzd |= 64;
        r1.zzk = r2;
    }

    public static /* synthetic */ void zzI(zzis r1, String r2) {
        r2.getClass();
        r1.zzd |= 1;
        r1.zze = r2;
    }

    public static /* synthetic */ void zzJ(zzis r1, String r2) {
        r1.zzd |= 2;
        r1.zzf = r2;
    }

    public static zziq zzc() {
        return (zziq) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzis zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r12, Object r13, Object r14) {
        int r122 = r12 - 1;
        if (r122 == 0) goto L22;
        if (r122 == 2) goto L20;
        if (r122 == 3) goto L18;
        if (r122 == 4) goto L16;
        if (r122 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zziq(null);
    L18:
        return new zzis();
    L20:
        return zzfi.zzt(zzb, "\u0004\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0002\u0003င\u0003\u0004ဂ\u0004\u0005ဈ\u0001\u0006ဂ\u0005\u0007ဇ\u0006\bင\u0007\tင\b\nဂ\t", new Object[]{"zzd", "zze", "zzg", "zzh", "zzi", "zzf", "zzj", "zzk", "zzl", "zzm", "zzn"});
    L22:
        return (byte) 1;
    }
}
