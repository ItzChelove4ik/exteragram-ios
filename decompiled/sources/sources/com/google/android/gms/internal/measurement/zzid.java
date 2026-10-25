package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public final class zzid extends zzadu implements zzafd {
    private static final zzid zzaw = null;
    private static volatile zzafj zzax;
    private long zzA;
    private int zzB;
    private String zzC;
    private String zzD;
    private boolean zzE;
    private zzaef zzF;
    private String zzG;
    private int zzH;
    private int zzI;
    private int zzJ;
    private String zzK;
    private long zzL;
    private long zzM;
    private String zzN;
    private String zzO;
    private int zzP;
    private String zzQ;
    private zzig zzR;
    private zzaeb zzS;
    private long zzT;
    private long zzU;
    private String zzV;
    private String zzW;
    private int zzX;
    private boolean zzY;
    private String zzZ;
    private boolean zzaa;
    private zzhy zzab;
    private String zzac;
    private zzaef zzad;
    private String zzae;
    private long zzaf;
    private boolean zzag;
    private String zzah;
    private boolean zzai;
    private String zzaj;
    private int zzak;
    private String zzal;
    private zzhe zzam;
    private int zzan;
    private zzha zzao;
    private String zzap;
    private zzis zzaq;
    private long zzar;
    private String zzas;
    private zzho zzat;
    private String zzau;
    private zzaef zzav;
    private int zzb;
    private int zze;
    private int zzf;
    private zzaef zzg;
    private zzaef zzh;
    private long zzi;
    private long zzj;
    private long zzk;
    private long zzl;
    private long zzm;
    private String zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private int zzr;
    private String zzs;
    private String zzt;
    private String zzu;
    private long zzv;
    private long zzw;
    private String zzx;
    private boolean zzy;
    private String zzz;

    static {
        zzid r0 = new zzid();
        zzaw = r0;
        zzadu.zzcs(zzid.class, r0);
    }

    private zzid() {
        this.zzg = zzadu.zzcy();
        this.zzh = zzadu.zzcy();
        this.zzn = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzo = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzp = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzq = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzs = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzt = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzu = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzx = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzz = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzC = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzD = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzF = zzadu.zzcy();
        this.zzG = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzK = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzN = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzO = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzQ = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzS = zzadu.zzcv();
        this.zzV = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzW = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzZ = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzac = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzad = zzadu.zzcy();
        this.zzae = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzah = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzaj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzal = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzap = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzas = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzau = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzav = zzadu.zzcy();
    }

    public static zzic zzaE() {
        return (zzic) zzaw.zzcn();
    }

    public static zzic zzaF(zzid r1) {
        zzadp r0 = zzaw.zzcn();
        r0.zzbe(r1);
        return (zzic) r0;
    }

    private final void zzcE() {
        zzaef r0 = this.zzg;
        if (r0.zza() == true) goto L6;
        this.zzg = zzadu.zzcz(r0);
        return;
    }

    private final void zzcF() {
        zzaef r0 = this.zzh;
        if (r0.zza() == true) goto L6;
        this.zzh = zzadu.zzcz(r0);
        return;
    }

    public static /* synthetic */ zzid zzcb() {
        return zzaw;
    }

    public final String zzA() {
        return this.zzt;
    }

    public final String zzB() {
        return this.zzu;
    }

    public final boolean zzC() {
        if ((this.zzb & 16384) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzD() {
        return this.zzv;
    }

    public final boolean zzE() {
        if ((this.zzb & 32768) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzF() {
        return this.zzw;
    }

    public final String zzG() {
        return this.zzx;
    }

    public final boolean zzH() {
        if ((this.zzb & 131072) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzI() {
        return this.zzy;
    }

    public final String zzJ() {
        return this.zzz;
    }

    public final boolean zzK() {
        if ((this.zzb & 524288) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzL() {
        return this.zzA;
    }

    public final boolean zzM() {
        if ((this.zzb & 1048576) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzN() {
        return this.zzB;
    }

    public final String zzO() {
        return this.zzC;
    }

    public final String zzP() {
        return this.zzD;
    }

    public final boolean zzQ() {
        if ((this.zzb & 8388608) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzR() {
        return this.zzE;
    }

    public final List zzS() {
        return this.zzF;
    }

    public final String zzT() {
        return this.zzG;
    }

    public final boolean zzU() {
        if ((this.zzb & 33554432) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzV() {
        return this.zzH;
    }

    public final boolean zzW() {
        if ((this.zzb & 536870912) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzX() {
        return this.zzL;
    }

    public final boolean zzY() {
        if ((this.zzb & Integer.MIN_VALUE) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzZ() {
        return this.zzN;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final boolean zzaA() {
        if ((this.zze & 134217728) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzaB() {
        return this.zzar;
    }

    public final boolean zzaC() {
        if ((this.zze & 536870912) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzho zzaD() {
        zzho r0 = this.zzat;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzho.zzc();
    }

    public final /* synthetic */ void zzaG(int r2) {
        this.zzb |= 1;
        this.zzf = 1;
    }

    public final /* synthetic */ void zzaH(int r1, zzhs r2) {
        r2.getClass();
        zzcE();
        this.zzg.set(r1, r2);
    }

    public final /* synthetic */ void zzaI(zzhs r1) {
        r1.getClass();
        zzcE();
        this.zzg.add(r1);
    }

    public final /* synthetic */ void zzaJ(Iterable r1) {
        zzcE();
        zzacb.zzcg(r1, this.zzg);
    }

    public final /* synthetic */ void zzaK() {
        this.zzg = zzadu.zzcy();
    }

    public final /* synthetic */ void zzaL(int r1) {
        zzcE();
        this.zzg.remove(r1);
    }

    public final /* synthetic */ void zzaM(int r1, zziu r2) {
        r2.getClass();
        zzcF();
        this.zzh.set(r1, r2);
    }

    public final /* synthetic */ void zzaN(zziu r1) {
        r1.getClass();
        zzcF();
        this.zzh.add(r1);
    }

    public final /* synthetic */ void zzaO(Iterable r1) {
        zzcF();
        zzacb.zzcg(r1, this.zzh);
    }

    public final /* synthetic */ void zzaP(int r1) {
        zzcF();
        this.zzh.remove(r1);
    }

    public final /* synthetic */ void zzaQ(long r2) {
        this.zzb |= 2;
        this.zzi = r2;
    }

    public final /* synthetic */ void zzaR() {
        this.zzb &= -3;
        this.zzi = 0;
    }

    public final /* synthetic */ void zzaS(long r2) {
        this.zzb |= 4;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzaT(long r2) {
        this.zzb |= 8;
        this.zzk = r2;
    }

    public final /* synthetic */ void zzaU(long r2) {
        this.zzb |= 16;
        this.zzl = r2;
    }

    public final /* synthetic */ void zzaV() {
        this.zzb &= -17;
        this.zzl = 0;
    }

    public final /* synthetic */ void zzaW(long r2) {
        this.zzb |= 32;
        this.zzm = r2;
    }

    public final /* synthetic */ void zzaX() {
        this.zzb &= -33;
        this.zzm = 0;
    }

    public final /* synthetic */ void zzaY(String r1) {
        this.zzb |= 64;
        this.zzn = "android";
    }

    public final /* synthetic */ void zzaZ(String r2) {
        r2.getClass();
        this.zzb |= 128;
        this.zzo = r2;
    }

    public final boolean zzaa() {
        if ((this.zze & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzab() {
        return this.zzP;
    }

    public final boolean zzac() {
        if ((this.zze & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzad() {
        return this.zzT;
    }

    public final boolean zzae() {
        if ((this.zze & 128) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzaf() {
        return this.zzW;
    }

    public final boolean zzag() {
        if ((this.zze & 8192) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzah() {
        return this.zzac;
    }

    public final boolean zzai() {
        if ((this.zze & 32768) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzaj() {
        return this.zzaf;
    }

    public final boolean zzak() {
        return this.zzag;
    }

    public final boolean zzal() {
        if ((this.zze & 131072) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzam() {
        return this.zzah;
    }

    public final boolean zzan() {
        if ((this.zze & 262144) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzao() {
        return this.zzai;
    }

    public final boolean zzap() {
        if ((this.zze & 524288) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzaq() {
        return this.zzaj;
    }

    public final int zzar() {
        return this.zzak;
    }

    public final boolean zzas() {
        if ((this.zze & 4194304) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzhe zzat() {
        zzhe r0 = this.zzam;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzhe.zzj();
    }

    public final boolean zzau() {
        if ((this.zze & 8388608) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzav() {
        return this.zzan;
    }

    public final boolean zzaw() {
        if ((this.zze & 16777216) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzha zzax() {
        zzha r0 = this.zzao;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzha.zzu();
    }

    public final boolean zzay() {
        if ((this.zze & 67108864) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzis zzaz() {
        zzis r0 = this.zzaq;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzis.zzc();
    }

    public final int zzb() {
        return this.zzf;
    }

    public final /* synthetic */ void zzbA(long r3) {
        this.zzb |= 536870912;
        this.zzL = r3;
    }

    public final /* synthetic */ void zzbB(String r3) {
        r3.getClass();
        this.zzb |= Integer.MIN_VALUE;
        this.zzN = r3;
    }

    public final /* synthetic */ void zzbC() {
        this.zzb &= Integer.MAX_VALUE;
        this.zzN = zzaw.zzN;
    }

    public final /* synthetic */ void zzbD(int r2) {
        this.zze |= 2;
        this.zzP = r2;
    }

    public final /* synthetic */ void zzbE(zzig r1) {
        r1.getClass();
        this.zzR = r1;
        this.zze |= 8;
    }

    public final /* synthetic */ void zzbF(Iterable r3) {
        zzaeb r0 = this.zzS;
        if (r0.zza() == true) goto L5;
        int r1 = r0.size();
        this.zzS = r0.zze(r1 + r1);
    L5:
        zzacb.zzcg(r3, this.zzS);
    }

    public final /* synthetic */ void zzbG(long r2) {
        this.zze |= 16;
        this.zzT = r2;
    }

    public final /* synthetic */ void zzbH(long r2) {
        this.zze |= 32;
        this.zzU = r2;
    }

    public final /* synthetic */ void zzbI(String r2) {
        this.zze |= 128;
        this.zzW = r2;
    }

    public final /* synthetic */ void zzbJ(String r2) {
        r2.getClass();
        this.zze |= 8192;
        this.zzac = r2;
    }

    public final /* synthetic */ void zzbK() {
        this.zze &= -8193;
        this.zzac = zzaw.zzac;
    }

    public final /* synthetic */ void zzbL(Iterable r3) {
        zzaef r0 = this.zzad;
        if (r0.zza() == true) goto L5;
        this.zzad = zzadu.zzcz(r0);
    L5:
        zzacb.zzcg(r3, this.zzad);
    }

    public final /* synthetic */ void zzbM(String r2) {
        r2.getClass();
        this.zze |= 16384;
        this.zzae = r2;
    }

    public final /* synthetic */ void zzbN(long r3) {
        this.zze |= 32768;
        this.zzaf = r3;
    }

    public final /* synthetic */ void zzbO(boolean r3) {
        this.zze |= 65536;
        this.zzag = r3;
    }

    public final /* synthetic */ void zzbP(String r3) {
        this.zze |= 131072;
        this.zzah = r3;
    }

    public final /* synthetic */ void zzbQ(boolean r3) {
        this.zze |= 262144;
        this.zzai = r3;
    }

    public final /* synthetic */ void zzbR(String r3) {
        r3.getClass();
        this.zze |= 524288;
        this.zzaj = r3;
    }

    public final /* synthetic */ void zzbS(int r3) {
        this.zze |= 1048576;
        this.zzak = r3;
    }

    public final /* synthetic */ void zzbT(zzhe r2) {
        r2.getClass();
        this.zzam = r2;
        this.zze |= 4194304;
    }

    public final /* synthetic */ void zzbU(int r3) {
        this.zze |= 8388608;
        this.zzan = r3;
    }

    public final /* synthetic */ void zzbV(zzha r2) {
        r2.getClass();
        this.zzao = r2;
        this.zze |= 16777216;
    }

    public final /* synthetic */ void zzbW(zzis r2) {
        this.zzaq = r2;
        this.zze |= 67108864;
    }

    public final /* synthetic */ void zzbX(long r3) {
        this.zze |= 134217728;
        this.zzar = r3;
    }

    public final /* synthetic */ void zzbY(zzho r2) {
        r2.getClass();
        this.zzat = r2;
        this.zze |= 536870912;
    }

    public final /* synthetic */ void zzbZ(String r3) {
        r3.getClass();
        this.zze |= TLObject.FLAG_30;
        this.zzau = r3;
    }

    public final /* synthetic */ void zzba(String r2) {
        r2.getClass();
        this.zzb |= 256;
        this.zzp = r2;
    }

    public final /* synthetic */ void zzbb() {
        this.zzb &= -257;
        this.zzp = zzaw.zzp;
    }

    public final /* synthetic */ void zzbc(String r2) {
        r2.getClass();
        this.zzb |= 512;
        this.zzq = r2;
    }

    public final /* synthetic */ void zzbd(int r2) {
        this.zzb |= 1024;
        this.zzr = r2;
    }

    public final /* synthetic */ void zzbe(String r2) {
        r2.getClass();
        this.zzb |= 2048;
        this.zzs = r2;
    }

    public final /* synthetic */ void zzbf(String r2) {
        r2.getClass();
        this.zzb |= 4096;
        this.zzt = r2;
    }

    public final /* synthetic */ void zzbg(String r2) {
        r2.getClass();
        this.zzb |= 8192;
        this.zzu = r2;
    }

    public final /* synthetic */ void zzbh(long r2) {
        this.zzb |= 16384;
        this.zzv = r2;
    }

    public final /* synthetic */ void zzbi(long r1) {
        this.zzb |= 32768;
        this.zzw = 161000;
    }

    public final /* synthetic */ void zzbj(String r3) {
        r3.getClass();
        this.zzb |= 65536;
        this.zzx = r3;
    }

    public final /* synthetic */ void zzbk() {
        this.zzb &= -65537;
        this.zzx = zzaw.zzx;
    }

    public final /* synthetic */ void zzbl(boolean r3) {
        this.zzb |= 131072;
        this.zzy = r3;
    }

    public final /* synthetic */ void zzbm() {
        this.zzb &= -131073;
        this.zzy = false;
    }

    public final /* synthetic */ void zzbn(String r3) {
        r3.getClass();
        this.zzb |= 262144;
        this.zzz = r3;
    }

    public final /* synthetic */ void zzbo() {
        this.zzb &= -262145;
        this.zzz = zzaw.zzz;
    }

    public final /* synthetic */ void zzbp(long r3) {
        this.zzb |= 524288;
        this.zzA = r3;
    }

    public final /* synthetic */ void zzbq(int r3) {
        this.zzb |= 1048576;
        this.zzB = r3;
    }

    public final /* synthetic */ void zzbr(String r3) {
        this.zzb |= TLObject.FLAG_21;
        this.zzC = r3;
    }

    public final /* synthetic */ void zzbs() {
        this.zzb &= -2097153;
        this.zzC = zzaw.zzC;
    }

    public final /* synthetic */ void zzbt(String r3) {
        r3.getClass();
        this.zzb |= 4194304;
        this.zzD = r3;
    }

    public final /* synthetic */ void zzbu(boolean r3) {
        this.zzb |= 8388608;
        this.zzE = r3;
    }

    public final /* synthetic */ void zzbv(Iterable r3) {
        zzaef r0 = this.zzF;
        if (r0.zza() == true) goto L5;
        this.zzF = zzadu.zzcz(r0);
    L5:
        zzacb.zzcg(r3, this.zzF);
    }

    public final /* synthetic */ void zzbw() {
        this.zzF = zzadu.zzcy();
    }

    public final /* synthetic */ void zzbx(String r3) {
        r3.getClass();
        this.zzb |= 16777216;
        this.zzG = r3;
    }

    public final /* synthetic */ void zzby(int r3) {
        this.zzb |= 33554432;
        this.zzH = r3;
    }

    public final /* synthetic */ void zzbz() {
        this.zzb &= -268435457;
        this.zzK = zzaw.zzK;
    }

    public final List zzc() {
        return this.zzg;
    }

    public final /* synthetic */ void zzca(Iterable r3) {
        zzaef r0 = this.zzav;
        if (r0.zza() == true) goto L5;
        this.zzav = zzadu.zzcz(r0);
    L5:
        zzacb.zzcg(r3, this.zzav);
    }

    public final int zzd() {
        return this.zzg.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzhs zze(int r1) {
        return (zzhs) this.zzg.get(r1);
    }

    public final List zzf() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r79, Object r80, Object r81) {
        int r0 = r79 - 1;
        if (r0 == 0) goto L38;
        if (r0 == 2) goto L36;
        if (r0 == 3) goto L34;
        if (r0 == 4) goto L32;
        if (r0 == 5) goto L30;
        if (r0 != 6) goto L28;
        zzafj r02 = zzax;
        if (r02 == null) goto L17;
        return r02;
    L17:
        monitor-enter(zzid.class);
        zzafj r03 = zzax;     // Catch: Throwable -> L21
        if (r03 != null) goto L23;
        r03 = new zzadq(zzaw);     // Catch: Throwable -> L21
        zzax = r03;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzid.class);     // Catch: Throwable -> L21
        return r03;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzaw;
    L32:
        return new zzic(null);
    L34:
        return new zzid();
    L36:
        return zzadu.zzct(zzaw, "\u0004E\u0000\u0002\u0001YE\u0000\u0006\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5᠌(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-@\u001aAဈ.Cဂ/Dဇ0Gဈ1Hဇ2Iဈ3Jင4Kဈ5Lဉ6Mင7Oဉ8Pဈ9Qဉ:Rဂ;Sဈ<Vဉ=Xဈ>Y\u001b", new Object[]{"zzb", "zze", "zzf", "zzg", zzhs.class, "zzh", zziu.class, "zzi", "zzj", "zzk", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzl", "zzE", "zzF", zzhg.class, "zzG", "zzH", "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", zzgw.zza, "zzY", "zzZ", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau", "zzav", zzfb.class});
    L38:
        return (byte) 1;
    }

    public final int zzh() {
        return this.zzh.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zziu zzi(int r1) {
        return (zziu) this.zzh.get(r1);
    }

    public final boolean zzj() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzk() {
        return this.zzi;
    }

    public final boolean zzl() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzm() {
        return this.zzj;
    }

    public final boolean zzn() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzo() {
        return this.zzk;
    }

    public final boolean zzp() {
        if ((this.zzb & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzq() {
        return this.zzl;
    }

    public final boolean zzr() {
        if ((this.zzb & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzs() {
        return this.zzm;
    }

    public final String zzt() {
        return this.zzn;
    }

    public final String zzu() {
        return this.zzo;
    }

    public final String zzv() {
        return this.zzp;
    }

    public final String zzw() {
        return this.zzq;
    }

    public final boolean zzx() {
        if ((this.zzb & 1024) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzy() {
        return this.zzr;
    }

    public final String zzz() {
        return this.zzs;
    }
}
