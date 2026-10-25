package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzaa {
    public int zza;
    public zzv zzb;
    public zzj zzc;
    public zzm zzd;

    public zzaa(zzm r4) {
        this(zzv.zzb, 0, new zzk());
        this.zzd = r4;
    }

    private zzaa(zzv r1, int r2, zzj r3) {
        this.zzb = r1;
        this.zza = r2;
        this.zzc = r3;
    }

    public zzaa(zzv r1, int r2, zzj r3, zzm r4) {
        this(r1, r2, r3);
        this.zzd = r4;
    }

    private final long zzg() {
        int r2 = ((((~331557302) & 571573523) | 420425807) + ((331557302 & 592744208) | 1374505641)) - (-2021079919);
        int r3 = 397977904 % 305425913;
        int r4 = ((((~1403997111) & 186192065) | 1317897081) + ((1403997111 & 567545984) | 552504370)) - 1817959444;
        int r5 = 810882584 % 74061048;
        int r6 = ((((~217704412) & 144255265) | 1174857682) + ((217704412 & 1788447267) | 1667270218)) - (-1441824145);
        int r7 = 1234644696 % 220282619;
        int r8 = ((((~1632565483) & 1076483137) | 412004146) + ((1632565483 & 1110536289) | 177384740)) - 1619753338;
        int r9 = 1600243658 % 310936585;
        int r10 = ((((~2022337363) & 1106608128) | 27043809) + ((2022337363 & 1080115328) | 638477452)) - 1605484050;
        int r11 = 813592586 % 336728279;
        int r1 = 0;
        long r12 = 0;
    L4:
        if (r1 >= (r2 ^ r3)) goto L17;
        zzj r14 = this.zzc;     // Catch: IndexOutOfBoundsException -> L18
        zzv r15 = this.zzb;     // Catch: IndexOutOfBoundsException -> L18
        int r16 = r2;
        int r22 = this.zza;     // Catch: IndexOutOfBoundsException -> L18
        int r17 = r3;
        this.zza = r22 + 1;     // Catch: IndexOutOfBoundsException -> L18
        byte r23 = r14.zza(r15, r22);     // Catch: IndexOutOfBoundsException -> L18
        int r32 = r6 ^ r7;     // Catch: IndexOutOfBoundsException -> L18
        r12 = r12 | (((r4 ^ r5) & r23) << r1);     // Catch: IndexOutOfBoundsException -> L18
        if (r1 != r32) goto L12;
        if (r23 > 1) goto L10;
        r1 = r32;
        goto L12
    L10:
        throw new zzx();     // Catch: IndexOutOfBoundsException -> L18
    L12:
        if ((r23 & (r8 ^ r9)) == 0) goto L14;
        r1 = r1 + (r10 ^ r11);     // Catch: IndexOutOfBoundsException -> L18
        r2 = r16;
        r3 = r17;
        goto L4
    L14:
        return (r12 >>> 1) ^ (-(1 & r12));
    L18:
        e = move-exception;
        throw new zzz(e);
    L17:
        throw new zzx();     // Catch: IndexOutOfBoundsException -> L18
    }

    private static final void zzh(long r19) {
        long[] r0 = {1239547247, 271084552, 603409185, 805905448, 612732982, 1384275531, 148774773, 1155505365, 905894996};
        long r1 = r0[0];
        long r3 = r0[1];
        long r5 = r0[2];
        long r7 = r0[3];
        long r9 = r0[4];
        long r11 = r0[5];
        long r13 = r0[6];
        long r15 = r0[7];
        if ((r19 % ((r13 + (((((~r1) & r3) | r5) + ((r1 & r7) | r9)) - r11)) ^ (r15 % 905894996))) != 0) goto L6;
        return;
    L6:
        throw new zzy();
    }

    public final void zza(long r21) {
        long[] r1 = {1540846267, 571107382, 1484708369, 709108262, 1568035521, 3652851584L, 192048860, 2037335344, 1874960596};
        long r2 = r1[0];
        long r4 = r1[1];
        long r6 = r1[2];
        long r8 = r1[3];
        long r10 = r1[4];
        long r12 = r1[5];
        long r14 = r1[6];
        long r16 = r1[7];
        zzh(r21);
        long r13 = r21 / ((r14 + (((((~r2) & r4) | r6) + ((r2 & r8) | r10)) - r12)) ^ (r16 % 1874960596));
        if (r13 < 0) goto L9;
        if (r13 > this.zzb.zza.length) goto L9;
        this.zza = (int) r13;
        return;
    L9:
        throw new zzz();
    }

    public final long zzb() {
        long[] r0 = {1714636915, 1758565436, 174653550, 1653642768, 38095596, 2318926016L, 596516649, 1804289383, 846930886};
        long r1 = r0[0];
        long r3 = r0[1];
        long r5 = r0[2];
        long r7 = r0[3];
        long r9 = r0[4];
        long r11 = r0[5];
        return this.zza * ((r0[6] + (((((~r1) & r3) | r5) + ((r1 & r7) | r9)) - r11)) ^ (r0[7] % 846930886));
    }

    public final long zzc() {
        zzj r0 = this.zzc;     // Catch: IndexOutOfBoundsException -> L5
        zzv r1 = this.zzb;     // Catch: IndexOutOfBoundsException -> L5
        this.zza = this.zza + 1;     // Catch: IndexOutOfBoundsException -> L5
        return r0.zza(r1, r2);
    L5:
        e = move-exception;
        throw new zzz(e);
    }

    public final int zzd() {
        int r1 = ((((~1900553541) & 81494563) | 1076390106) + ((1900553541 & 1156827749) | 1781026244)) - (-1841277372);
        int r2 = 1887658390 % 1402961682;
        int r3 = ((((~2137100237) & 243279587) | 1476690354) + ((2137100237 & 1182836297) | 1215531406)) - (-1785612177);
        int r4 = 1251300606 % 959372260;
        int r5 = ((((~427355115) & 404248060) | 1318670750) + ((427355115 & 874677344) | 1819730563)) - (-1096413178);
        int r6 = 1858504292 % 235745791;
        int r7 = ((((~2143124030) & 85005397) | 430547098) + ((2143124030 & 878451813) | 1894615584)) - (-2031609849);
        int r8 = 1747844822 % 1617876982;
        zzj r0 = this.zzc;     // Catch: IndexOutOfBoundsException -> L6
        zzv r9 = this.zzb;     // Catch: IndexOutOfBoundsException -> L6
        int r10 = this.zza;     // Catch: IndexOutOfBoundsException -> L6
        this.zza = r10 + 1;     // Catch: IndexOutOfBoundsException -> L6
        int r12 = r1 ^ r2;     // Catch: IndexOutOfBoundsException -> L6
        int r02 = r0.zza(r9, r10) & r12;     // Catch: IndexOutOfBoundsException -> L6
        zzj r22 = this.zzc;     // Catch: IndexOutOfBoundsException -> L6
        zzv r92 = this.zzb;     // Catch: IndexOutOfBoundsException -> L6
        int r102 = this.zza;     // Catch: IndexOutOfBoundsException -> L6
        this.zza = r102 + 1;     // Catch: IndexOutOfBoundsException -> L6
        int r23 = r22.zza(r92, r102) & r12;     // Catch: IndexOutOfBoundsException -> L6
        int r03 = r02 | (r23 << (r3 ^ r4));     // Catch: IndexOutOfBoundsException -> L6
        zzj r24 = this.zzc;     // Catch: IndexOutOfBoundsException -> L6
        zzv r32 = this.zzb;     // Catch: IndexOutOfBoundsException -> L6
        int r42 = this.zza;     // Catch: IndexOutOfBoundsException -> L6
        this.zza = r42 + 1;     // Catch: IndexOutOfBoundsException -> L6
        byte r25 = r24.zza(r32, r42);     // Catch: IndexOutOfBoundsException -> L6
        int r13 = r12 & r25;     // Catch: IndexOutOfBoundsException -> L6
        int r04 = r03 | (r13 << (r5 ^ r6));     // Catch: IndexOutOfBoundsException -> L6
        zzj r14 = this.zzc;     // Catch: IndexOutOfBoundsException -> L6
        zzv r26 = this.zzb;     // Catch: IndexOutOfBoundsException -> L6
        int r33 = this.zza;     // Catch: IndexOutOfBoundsException -> L6
        this.zza = r33 + 1;     // Catch: IndexOutOfBoundsException -> L6
        byte r122 = r14.zza(r26, r33);     // Catch: IndexOutOfBoundsException -> L6
        return (r122 << (r7 ^ r8)) | r04;
    L6:
        e = move-exception;
        throw new zzz(e);
    }

    public final long zze() {
        return zzg();
    }

    public final zzv zzf(long r10) {
        int[] r0 = {2004270296, 933109376, 745751825, 330111618, 543277087, 1377683891, 125737768, 1632381616, 422449966};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r72 = r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6);
        zzh(zzb() + r10);
        int r12 = this.zza;
        long r22 = r12;
        zzv r42 = this.zzb;
        long r102 = (r10 >> ((r02 % 422449966) ^ r72)) + r22;
        if (r102 > r42.zza.length) goto L13;
        if (r102 < r22) goto L13;
        int r103 = (int) r102;     // Catch: IndexOutOfBoundsException -> L9
        zzv r11 = this.zzc.zzb(r42, r12, r103);     // Catch: IndexOutOfBoundsException -> L9
        this.zza = r103;
        return r11;
    L9:
        e = move-exception;
        throw new AssertionError(zzt.zza("CEiv6BFfPnitUE+D"), e);
    L13:
        throw new zzz();
    }
}
