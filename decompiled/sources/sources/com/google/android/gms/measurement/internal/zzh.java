package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzh {
    private Long zzA;
    private long zzB;
    private String zzC;
    private int zzD;
    private int zzE;
    private long zzF;
    private String zzG;
    private byte[] zzH;
    private int zzI;
    private long zzJ;
    private long zzK;
    private long zzL;
    private long zzM;
    private long zzN;
    private long zzO;
    private long zzP;
    private String zzQ;
    private boolean zzR;
    private long zzS;
    private long zzT;
    private final zzic zza;
    private final String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private String zzj;
    private long zzk;
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private boolean zzp;
    private Boolean zzq;
    private long zzr;
    private List zzs;
    private String zzt;
    private boolean zzu;
    private long zzv;
    private long zzw;
    private int zzx;
    private boolean zzy;
    private Long zzz;

    public zzh(zzic r1, String r2) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkNotEmpty(r2);
        this.zza = r1;
        this.zzb = r2;
        r1.zzaX().zzg();
    }

    public final void zzA(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzn == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzn = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzB() {
        this.zza.zzaX().zzg();
        return this.zzr;
    }

    public final void zzC(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzr == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzr = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final boolean zzD() {
        this.zza.zzaX().zzg();
        return this.zzo;
    }

    public final void zzE(boolean r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzo == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzo = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final void zzF(long r6) {
        boolean r1 = false;
        if (r6 < 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
        this.zza.zzaX().zzg();
        boolean r02 = this.zzR;
        if (this.zzg == r6) goto L9;
        r1 = true;
    L9:
        this.zzR = r02 | r1;
        this.zzg = r6;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public final long zzG() {
        this.zza.zzaX().zzg();
        return this.zzg;
    }

    public final long zzH() {
        this.zza.zzaX().zzg();
        return this.zzS;
    }

    public final void zzI(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzS == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzS = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzJ() {
        this.zza.zzaX().zzg();
        return this.zzT;
    }

    public final void zzK(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzT == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzT = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final void zzL() {
        zzic r0 = this.zza;
        r0.zzaX().zzg();
        long r1 = this.zzg + 1;
        if (r1 <= 2147483647L) goto L5;
        r0.zzaW().zze().zzb("Bundle index overflow. appId", zzgu.zzl(this.zzb));
        r1 = 0;
    L5:
        this.zzR = true;
        this.zzg = r1;
    }

    public final void zzM(long r8) {
        zzic r0 = this.zza;
        r0.zzaX().zzg();
        long r1 = this.zzg + r8;
        if (r1 <= 2147483647L) goto L5;
        r0.zzaW().zze().zzb("Bundle index overflow. appId", zzgu.zzl(this.zzb));
        r1 = (-1) + r8;
    L5:
        long r82 = this.zzF + 1;
        if (r82 <= 2147483647L) goto L8;
        r0.zzaW().zze().zzb("Delivery index overflow. appId", zzgu.zzl(this.zzb));
        r82 = 0;
    L8:
        this.zzR = true;
        this.zzg = r1;
        this.zzF = r82;
    }

    public final long zzN() {
        this.zza.zzaX().zzg();
        return this.zzK;
    }

    public final void zzO(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzK == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzK = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzP() {
        this.zza.zzaX().zzg();
        return this.zzL;
    }

    public final void zzQ(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzL == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzL = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzR() {
        this.zza.zzaX().zzg();
        return this.zzM;
    }

    public final void zzS(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzM == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzM = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzT() {
        this.zza.zzaX().zzg();
        return this.zzN;
    }

    public final void zzU(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzN == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzN = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzV() {
        this.zza.zzaX().zzg();
        return this.zzP;
    }

    public final void zzW(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzP == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzP = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzX() {
        this.zza.zzaX().zzg();
        return this.zzO;
    }

    public final void zzY(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzO == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzO = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final String zzZ() {
        this.zza.zzaX().zzg();
        return this.zzQ;
    }

    public final boolean zza() {
        this.zza.zzaX().zzg();
        return this.zzR;
    }

    public final int zzaA() {
        this.zza.zzaX().zzg();
        return this.zzD;
    }

    public final void zzaB(int r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzD == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzD = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final int zzaC() {
        this.zza.zzaX().zzg();
        return this.zzE;
    }

    public final void zzaD(int r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzE == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzE = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final void zzaE(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzF == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzF = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzaF() {
        this.zza.zzaX().zzg();
        return this.zzF;
    }

    public final void zzaG(String r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzG == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzG = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final String zzaH() {
        this.zza.zzaX().zzg();
        return this.zzG;
    }

    public final void zzaI(byte[] r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzH == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzH = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final byte[] zzaJ() {
        this.zza.zzaX().zzg();
        return this.zzH;
    }

    public final void zzaK(int r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzI == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzI = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final int zzaL() {
        this.zza.zzaX().zzg();
        return this.zzI;
    }

    public final void zzaM(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzJ == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzJ = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzaN() {
        this.zza.zzaX().zzg();
        return this.zzJ;
    }

    public final String zzaa() {
        this.zza.zzaX().zzg();
        String r0 = this.zzQ;
        zzab(null);
        return r0;
    }

    public final void zzab(String r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzQ, r3);
        this.zzQ = r3;
    }

    public final boolean zzac() {
        this.zza.zzaX().zzg();
        return this.zzp;
    }

    public final void zzad(boolean r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzp == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzp = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final Boolean zzae() {
        this.zza.zzaX().zzg();
        return this.zzq;
    }

    public final void zzaf(Boolean r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzq, r3);
        this.zzq = r3;
    }

    public final List zzag() {
        this.zza.zzaX().zzg();
        return this.zzs;
    }

    public final void zzah(List r2) {
        this.zza.zzaX().zzg();
        if (Objects.equals(this.zzs, r2) == true) goto L10;
        this.zzR = true;
        if (r2 == null) goto L7;
        ArrayList r0 = new ArrayList(r2);
    L8:
        this.zzs = r0;
        return;
    L7:
        r0 = null;
        goto L8
    }

    public final boolean zzai() {
        this.zza.zzaX().zzg();
        return this.zzu;
    }

    public final void zzaj(boolean r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzu == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzu = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzak() {
        this.zza.zzaX().zzg();
        return this.zzv;
    }

    public final void zzal(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzv == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzv = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzam() {
        this.zza.zzaX().zzg();
        return this.zzw;
    }

    public final void zzan(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzw == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzw = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final int zzao() {
        this.zza.zzaX().zzg();
        return this.zzx;
    }

    public final void zzap(int r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzx == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzx = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final boolean zzaq() {
        this.zza.zzaX().zzg();
        return this.zzy;
    }

    public final void zzar(boolean r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzy == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzy = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final Long zzas() {
        this.zza.zzaX().zzg();
        return this.zzz;
    }

    public final void zzat(Long r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzz, r3);
        this.zzz = r3;
    }

    public final Long zzau() {
        this.zza.zzaX().zzg();
        return this.zzA;
    }

    public final void zzav(Long r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzA, r3);
        this.zzA = r3;
    }

    public final long zzaw() {
        this.zza.zzaX().zzg();
        return this.zzB;
    }

    public final void zzax(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzB == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzB = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final String zzay() {
        this.zza.zzaX().zzg();
        return this.zzC;
    }

    public final void zzaz(String r3) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzC == r3) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzC = r3;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final void zzb() {
        this.zza.zzaX().zzg();
        this.zzR = false;
    }

    public final String zzc() {
        this.zza.zzaX().zzg();
        return this.zzb;
    }

    public final String zzd() {
        this.zza.zzaX().zzg();
        return this.zzc;
    }

    public final void zze(String r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzc, r3);
        this.zzc = r3;
    }

    public final String zzf() {
        this.zza.zzaX().zzg();
        return this.zzd;
    }

    public final void zzg(String r4) {
        this.zza.zzaX().zzg();
        if (true != TextUtils.isEmpty(r4)) goto L5;
        r4 = null;
    L5:
        this.zzR |= true ^ Objects.equals(this.zzd, r4);
        this.zzd = r4;
    }

    public final String zzh() {
        this.zza.zzaX().zzg();
        return this.zzt;
    }

    public final void zzi(String r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzt, r3);
        this.zzt = r3;
    }

    public final String zzj() {
        this.zza.zzaX().zzg();
        return this.zze;
    }

    public final void zzk(String r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zze, r3);
        this.zze = r3;
    }

    public final String zzl() {
        this.zza.zzaX().zzg();
        return this.zzf;
    }

    public final void zzm(String r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzf, r3);
        this.zzf = r3;
    }

    public final long zzn() {
        this.zza.zzaX().zzg();
        return this.zzh;
    }

    public final void zzo(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzh == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzh = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzp() {
        this.zza.zzaX().zzg();
        return this.zzi;
    }

    public final void zzq(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzi == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzi = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final String zzr() {
        this.zza.zzaX().zzg();
        return this.zzj;
    }

    public final void zzs(String r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzj, r3);
        this.zzj = r3;
    }

    public final long zzt() {
        this.zza.zzaX().zzg();
        return this.zzk;
    }

    public final void zzu(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzk == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzk = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final String zzv() {
        this.zza.zzaX().zzg();
        return this.zzl;
    }

    public final void zzw(String r3) {
        this.zza.zzaX().zzg();
        this.zzR |= !Objects.equals(this.zzl, r3);
        this.zzl = r3;
    }

    public final long zzx() {
        this.zza.zzaX().zzg();
        return this.zzm;
    }

    public final void zzy(long r4) {
        this.zza.zzaX().zzg();
        boolean r0 = this.zzR;
        if (this.zzm == r4) goto L5;
        boolean r1 = true;
    L6:
        this.zzR = r0 | r1;
        this.zzm = r4;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public final long zzz() {
        this.zza.zzaX().zzg();
        return this.zzn;
    }
}
