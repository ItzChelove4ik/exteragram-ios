package com.google.android.recaptcha.internal;

import kotlin.UByte;

/* loaded from: classes5.dex */
final class zzrs extends zzru {
    public zzrs(String r2, String r3, Character r4) {
        zzrq r0 = new zzrq(r2, r3.toCharArray());
        super(r0, r4);
        if (r0.zze().length != 64) goto L5;
        boolean r1 = true;
    L6:
        zzoq.zza(r1);
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // com.google.android.recaptcha.internal.zzru, com.google.android.recaptcha.internal.zzrv
    public final void zza(Appendable r6, byte[] r7, int r8, int r9) {
        int r0 = 0;
        zzoq.zzg(0, r9, r7.length);
        int r82 = r9;
    L4:
        if (r82 < 3) goto L6;
        int r2 = r7[r0] & UByte.MAX_VALUE;
        int r1 = r7[r0 + 1] & UByte.MAX_VALUE;
        int r3 = r7[r0 + 2] & UByte.MAX_VALUE;
        zzrq r4 = this.zza;
        int r12 = ((r1 << 8) | (r2 << 16)) | r3;
        r6.append(r4.zza(r12 >>> 18));
        r6.append(r4.zza((r12 >>> 12) & 63));
        r6.append(r4.zza((r12 >>> 6) & 63));
        r6.append(r4.zza(r12 & 63));
        r0 = r0 + 3;
        r82 = r82 - 3;
        goto L4
    L6:
        if (r0 >= r9) goto L10;
        zzd(r6, r7, r0, r9 - r0);
        return;
    }

    @Override // com.google.android.recaptcha.internal.zzru, com.google.android.recaptcha.internal.zzrv
    public final int zzb(byte[] r8, CharSequence r9) {
        CharSequence r92 = zzf(r9);
        int r0 = r92.length();
        zzrq r7 = this.zza;
        if (r7.zzb(r0) == false) goto L15;
        int r02 = 0;
        int r1 = 0;
    L6:
        if (r02 >= r92.length()) goto L14;
        int r4 = r1 + 1;
        int r2 = (r7.zzc(r92.charAt(r02 + 1)) << 12) | (r7.zzc(r92.charAt(r02)) << 18);
        r8[r1] = (byte) (r2 >>> 16);
        int r3 = r02 + 2;
        if (r3 < r92.length()) goto L9;
        r02 = r3;
        r1 = r4;
        goto L6
    L9:
        int r5 = r02 + 3;
        int r22 = r2 | (r7.zzc(r92.charAt(r3)) << 6);
        int r32 = r1 + 2;
        r8[r4] = (byte) ((r22 >>> 8) & 255);
        if (r5 < r92.length()) goto L11;
        r1 = r32;
        r02 = r5;
        goto L6
    L11:
        r02 = r02 + 4;
        r1 = r1 + 3;
        r8[r32] = (byte) ((r22 | r7.zzc(r92.charAt(r5))) & 255);
        goto L6
    L14:
        return r1;
    L15:
        int r82 = r92.length();
        StringBuilder r03 = new StringBuilder(String.valueOf(r82).length() + 21);
        r03.append("Invalid input length ");
        r03.append(r82);
        throw new zzrt(r03.toString());
    }
}
