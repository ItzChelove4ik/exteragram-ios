package com.google.android.recaptcha.internal;

import java.security.NoSuchAlgorithmException;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes5.dex */
public abstract class zzada {
    public static byte[] zza(byte[] r9) {
        int r0 = r9.length;
        if ((r0 & 1) != 0) goto L13;
        if (r0 == 0) goto L13;
        if (r0 > 132) goto L13;
        int r1 = r0 >> 1;
        byte[] r2 = zzc(Arrays.copyOf(r9, r1));
        byte[] r92 = zzc(Arrays.copyOfRange(r9, r1, r0));
        int r02 = r2.length;
        int r3 = r92.length;
        int r12 = (r02 + 4) + r3;
        if (r12 < 128) goto L10;
        byte[] r4 = new byte[r12 + 3];
        r4[0] = 48;
        r4[1] = -127;
        r4[2] = (byte) r12;
        int r13 = 3;
    L11:
        int r5 = r13 + 1;
        r4[r13] = 2;
        int r14 = r13 + 2;
        r4[r5] = (byte) r02;
        System.arraycopy(r2, 0, r4, r14, r02);
        int r15 = r14 + r02;
        r4[r15] = 2;
        r4[r15 + 1] = (byte) r3;
        System.arraycopy(r92, 0, r4, r15 + 2, r3);
        return r4;
    L10:
        r4 = new byte[r12 + 2];
        r4[0] = 48;
        r4[1] = (byte) r12;
        r13 = 2;
    L13:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid IEEE_P1363 encoding");
        return null;
    }

    public static ECParameterSpec zzb(zzacy r2) {
        int r0 = r2.ordinal();
        if (r0 == 0) goto L15;
        if (r0 == 1) goto L13;
        if (r0 != 2) goto L11;
        return zztt.zzc;
    L11:
        throw new NoSuchAlgorithmException("curve not implemented:".concat(r2.toString()));
    L13:
        return zztt.zzb;
    L15:
        return zztt.zza;
    }

    private static byte[] zzc(byte[] r5) {
        int r0 = 0;
        int r1 = 0;
    L3:
        int r2 = r5.length;
        if (r1 >= r2) goto L8;
        if (r5[r1] != 0) goto L8;
        r1 = r1 + 1;
    L8:
        if (r1 != r2) goto L11;
        r1 = r2 - 1;
    L11:
        if ((r5[r1] & ByteCompanionObject.MIN_VALUE) != 128) goto L13;
        r0 = 1;
    L13:
        int r22 = r2 - r1;
        byte[] r3 = new byte[r22 + r0];
        System.arraycopy(r5, r1, r3, r0, r22);
        return r3;
    }
}
