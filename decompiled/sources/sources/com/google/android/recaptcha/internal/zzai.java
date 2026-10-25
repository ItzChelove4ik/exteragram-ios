package com.google.android.recaptcha.internal;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzai {
    public final ArrayList zza;
    public int zzb;

    public zzai(int r1) {
        this.zza = new ArrayList();
        this.zzb = 0;
    }

    public final int zza(long r25) {
        int[] r4 = {783368690, 2033355106, 1011212548, -1019590554, -1840431076, 450100676, 21082158, 1189641421, 1025202362};
        int r6 = r4[0];
        int r8 = r4[1];
        int r10 = r4[2];
        int r12 = r4[3];
        int r14 = r4[4];
        int r16 = r4[5];
        int r18 = r4[6];
        int r42 = r4[7];
        int r182 = r18 + (((((~r6) & r8) | r10) + ((r6 & r12) | r14)) - r16);
        int r43 = r42 % 1025202362;
        long[] r3 = {572660336, 1946443174, 827031559, -838041696, -1952800191, 225735627, 63423617, 2084420925, 1937477084};
        long r5 = r3[0];
        long r7 = r3[1];
        long r9 = r3[2];
        long r11 = r3[3];
        long r13 = r3[4];
        long r15 = r3[5];
        long r20 = r3[6];
        long r22 = r3[7];
        long r202 = r20 + (((((~r5) & r7) | r9) + ((r5 & r11) | r13)) - r15);
        long r222 = r22 % 1937477084;
        if (r25 < 0) goto L5;
        int r62 = this.zzb;
        long r52 = (r62 + (r182 ^ r43)) - r25;
    L7:
        if (r52 < 0) goto L13;
        if (r52 >= this.zzb) goto L13;
        return (int) r52;
    L13:
        throw new zzag();
    L5:
        r52 = (r202 ^ r222) + (-r25);
        goto L7
    }

    public final void zzb(zzar r11) {
        int[] r0 = {619290071, 707839669, 1439652248, 1857062437, 1153777986, -1073983953, 110861465, 1431419379, 620145550};
        int r1 = r0[0];
        int r3 = r0[1];
        int r4 = r0[2];
        int r5 = r0[3];
        int r6 = r0[4];
        int r7 = r0[5];
        int r8 = r0[6];
        int r02 = r0[7];
        int r82 = r8 + ((((r3 & (~r1)) | r4) + ((r1 & r5) | r6)) - r7);
        int r12 = this.zzb;
        if (r12 >= ((r02 % 620145550) ^ r82)) goto L11;
        ArrayList r03 = this.zza;
        if (r12 != r03.size()) goto L7;
        r03.add(r11);
    L8:
        this.zzb++;
        return;
    L7:
        r03.set(this.zzb, r11);
        goto L8
    L11:
        throw new zzah();
    }

    public final zzar zzc() {
        int[] r0 = {1246322141, 1353851113, 767563284, -47045399, -1353918698, 1098913221, 38700441, 1600766768, 649830540};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r72 = r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6);
        int r03 = r02 % 649830540;
        int r12 = this.zzb;
        if (r12 <= 0) goto L7;
        int r04 = r03 ^ r72;
        ArrayList r22 = this.zza;
        zzar r13 = (zzar) r22.get(r12 + r04);
        r22.set(this.zzb + r04, null);
        this.zzb += r04;
        return r13;
    L7:
        throw new zzag();
    }

    public final zzar zzd(long r2) {
        return (zzar) this.zza.get(zza(r2));
    }

    public final void zze(long r2, zzar r4) {
        this.zza.set(zza(r2), r4);
    }
}
