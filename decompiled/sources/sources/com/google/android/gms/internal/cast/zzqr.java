package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzqr extends zzyd implements zzzj {
    private static final zzqr zzaz = null;
    private zzyl zzA;
    private zzyl zzB;
    private zzum zzC;
    private int zzD;
    private int zzE;
    private zzrp zzF;
    private int zzG;
    private zzqp zzH;
    private zzyl zzI;
    private zzrp zzJ;
    private int zzK;
    private int zzL;
    private int zzM;
    private int zzN;
    private int zzO;
    private int zzP;
    private zzvr zzQ;
    private zzqg zzR;
    private zzqz zzS;
    private zzpu zzT;
    private zzst zzU;
    private zzuk zzV;
    private zzsz zzW;
    private zzyl zzX;
    private zzsx zzY;
    private int zzZ;
    private zztj zzaa;
    private zzyl zzab;
    private boolean zzac;
    private boolean zzad;
    private int zzae;
    private zzpw zzaf;
    private zztr zzag;
    private zzsn zzah;
    private zzrh zzai;
    private zzth zzaj;
    private zzuu zzak;
    private zzrj zzal;
    private int zzam;
    private int zzan;
    private int zzao;
    private zzyl zzap;
    private zzvx zzaq;
    private zzuw zzar;
    private zzus zzas;
    private zzqk zzat;
    private zzvc zzau;
    private zzug zzav;
    private zzsv zzaw;
    private zzsh zzax;
    private byte zzay;
    private int zzb;
    private int zzd;
    private long zze;
    private long zzf;
    private int zzg;
    private zzsj zzh;
    private zztp zzi;
    private zzrz zzj;
    private zzrv zzk;
    private zzqn zzl;
    private zztn zzm;
    private zzqa zzn;
    private zzvi zzo;
    private String zzp;
    private zzrt zzq;
    private zzlc zzr;
    private String zzs;
    private String zzt;
    private int zzu;
    private zztf zzv;
    private String zzw;
    private zzyj zzx;
    private zzyl zzy;
    private zzyl zzz;

    static {
        zzqr r0 = new zzqr();
        zzaz = r0;
        zzyd.zzG(zzqr.class, r0);
    }

    private zzqr() {
        this.zzay = 2;
        this.zzp = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzs = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzt = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzw = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzx = zzyd.zzJ();
        this.zzy = zzyd.zzM();
        this.zzz = zzyd.zzM();
        this.zzA = zzyd.zzM();
        this.zzB = zzyd.zzM();
        this.zzI = zzyd.zzM();
        this.zzX = zzyd.zzM();
        this.zzab = zzyd.zzM();
        this.zzap = zzyd.zzM();
    }

    public static zzqq zzc() {
        return (zzqq) zzaz.zzB();
    }

    public static zzqq zzd(zzqr r1) {
        zzya r0 = zzaz.zzB();
        r0.zzv(r1);
        return (zzqq) r0;
    }

    public static /* synthetic */ zzqr zzq() {
        return zzaz;
    }

    public final zzqg zza() {
        zzqg r0 = this.zzR;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzqg.zzd();
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r94, Object r95, Object r96) {
        int r1 = r94 - 1;
        if (r1 == 0) goto L26;
        if (r1 == 2) goto L24;
        if (r1 == 3) goto L22;
        if (r1 == 4) goto L20;
        if (r1 == 5) goto L18;
        if (r95 != null) goto L14;
        byte r12 = 0;
    L15:
        this.zzay = r12;
        return null;
    L14:
        r12 = 1;
        goto L15
    L18:
        return zzaz;
    L20:
        return new zzqq(null);
    L22:
        return new zzqr();
    L24:
        return zzyd.zzH(zzaz, "\u0001H\u0000\u0002\u0001HH\u0000\t\u0001\u0001ဂ\u0000\u0002ဂ\u0001\u0003᠌\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဈ\u000e\nဉ\b\u000bဉ\t\fဉ\n\rဈ\u000b\u000eဉ\f\u000fဉ\r\u0010ဉ\u0011\u0011ဈ\u0012\u0012\u0016\u0013\u001b\u0014\u001b\u0015\u001b\u0016\u001b\u0017᠌\u0014\u0018ဉ\u0018\u0019\u001b\u001aဉ\u0019\u001b᠌\u001b\u001cင\u001c\u001dင\u001d\u001eင\u001e\u001fဆ\u001f ဉ !ဉ!\"ဉ##᠌\u0015$ဉ\u0016%ᐉ$&ဉ%'ဉ&(\u001b)᠌(*ဉ)+\u001b,᠌\u001a-ဇ*.ဇ+/᠌,0ဉ-1င\u00172ဉ.3ဉ/4ဉ15ဉ26ဉ37᠌48᠌59᠌6:\u001b;ဈ\u000f<ဉ7=ဉ0>ဉ\u0013?ဉ\"@င\u0010Aဉ8Bဉ'Cဉ9Dဉ:Eဉ;Fဉ<Gဉ=Hဉ>", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", zzow.zza(), "zzh", "zzi", "zzj", "zzk", "zzl", "zzs", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzv", "zzw", "zzx", "zzy", zzsl.class, "zzz", zzsp.class, "zzA", zzrl.class, "zzB", zzuo.class, "zzD", zzog.zza(), "zzH", "zzI", zzrp.class, "zzJ", "zzL", zzmu.zza(), "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzT", "zzE", zzoc.zza(), "zzF", "zzU", "zzV", "zzW", "zzX", zzqc.class, "zzZ", zzoe.zza(), "zzaa", "zzab", zzqi.class, "zzK", zzms.zza(), "zzac", "zzad", "zzae", zzmo.zza(), "zzaf", "zzG", "zzag", "zzah", "zzaj", "zzak", "zzal", "zzam", zzlu.zza(), "zzan", zzno.zza(), "zzao", zzls.zza(), "zzap", zzve.class, "zzt", "zzaq", "zzai", "zzC", "zzS", "zzu", "zzar", "zzY", "zzas", "zzat", "zzau", "zzav", "zzaw", "zzax"});
    L26:
        return Byte.valueOf(this.zzay);
    }

    public final /* synthetic */ void zze(long r2) {
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzf(String r2) {
        r2.getClass();
        this.zzb |= 2048;
        this.zzp = r2;
    }

    public final /* synthetic */ void zzg(String r2) {
        r2.getClass();
        this.zzb |= 16384;
        this.zzs = r2;
    }

    public final /* synthetic */ void zzh(String r3) {
        r3.getClass();
        this.zzb |= 32768;
        this.zzt = r3;
    }

    public final /* synthetic */ void zzi(int r3) {
        this.zzb |= 65536;
        this.zzu = r3;
    }

    public final /* synthetic */ void zzj(String r3) {
        this.zzb |= 262144;
        this.zzw = r3;
    }

    public final /* synthetic */ void zzk(int r3) {
        this.zzb |= Integer.MIN_VALUE;
        this.zzP = r3;
    }

    public final /* synthetic */ void zzl(zzqg r1) {
        r1.getClass();
        this.zzR = r1;
        this.zzd |= 2;
    }

    public final /* synthetic */ void zzm(zzqz r1) {
        r1.getClass();
        this.zzS = r1;
        this.zzd |= 4;
    }

    public final /* synthetic */ void zzn(zzqc r3) {
        r3.getClass();
        zzyl r0 = this.zzX;
        if (r0.zza() == true) goto L5;
        this.zzX = zzyd.zzN(r0);
    L5:
        this.zzX.add(r3);
    }

    public final /* synthetic */ void zzo(zzpw r1) {
        r1.getClass();
        this.zzaf = r1;
        this.zzd |= 8192;
    }

    public final /* synthetic */ void zzp(zzus r2) {
        r2.getClass();
        this.zzas = r2;
        this.zzd |= 33554432;
    }
}
