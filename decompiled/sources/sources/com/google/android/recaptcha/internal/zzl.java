package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzl implements zzj {
    private int zza;
    private final byte[] zzb;
    private final zzn zzc;

    public zzl(zzn r6) {
        this.zza = (((((~461273879) & 174229020) | 446042145) + ((461273879 & (-261061090)) | (-208510846))) - 434046989) ^ (1917305981 % 575705360);
        this.zzb = new byte[(((((~1967632854) & 1312378678) | 897683697) + ((1967632854 & 2054835974) | 835063985)) - (-1657540593)) ^ (1391927494 % 1051402293)];
        this.zzc = r6;
    }

    @Override // com.google.android.recaptcha.internal.zzj
    public final byte zza(zzv r8, int r9) {
        int r3 = ((((~1959970879) & 1413655861) | 1940175766) + ((1959970879 & 625104105) | 2040877774)) - (-456070292);
        int r4 = 1544048623 % 665228399;
        int r0 = (((((~523465512) & 646269132) | 193241389) + ((523465512 & 1948338368) | 1538307385)) - (-2007873589)) ^ (1424627019 % 1376108019);
        int r2 = ((((~175821956) & 1754890507) | 1438001845) + ((175821956 & 672108874) | 394690288)) - (-1923654329);
        int r5 = 1178341976 % 1045251304;
        int r02 = r9 >>> r0;
        if (r02 == this.zza) goto L5;
        this.zzc.zza(r02, this.zzb);
        this.zza = r02;
    L5:
        int r03 = r2 ^ r5;
        byte r82 = r8.zzb(r9);
        return (byte) (((this.zzb[r9 % (r3 ^ r4)] ^ r82) << r03) >> r03);
    }

    @Override // com.google.android.recaptcha.internal.zzj
    public final zzv zzb(zzv r4, int r5, int r6) {
        if (r5 < 0) goto L12;
        if (r5 > r6) goto L12;
        if (r6 > r4.zza.length) goto L12;
        byte[] r0 = new byte[r6 - r5];
        int r1 = 0;
    L7:
        if (r5 >= r6) goto L10;
        r0[r1] = zza(r4, r5);
        r5 = r5 + 1;
        r1 = r1 + 1;
        goto L7
    L10:
        return zzv.zze(r0);
    L12:
        throw new IndexOutOfBoundsException();
    }

    @Override // com.google.android.recaptcha.internal.zzj
    public final /* bridge */ /* synthetic */ zzj zzc() {
        return new zzl(this.zzc);
    }
}
