package com.google.android.recaptcha.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes5.dex */
public final class zzgp {
    private final zzd zza;

    public zzgp(zzd r1) {
        this.zza = r1;
    }

    public static final String zzb() {
        zzrv r0 = zzrv.zzi();
        int r1 = (((((~350322227) & 1152172203) | 1825506674) + ((350322227 & 18993305) | 769070672)) - 1815051211) ^ (1982275856 % 1275373743);
        int r2 = (((((~269455306) & 1628467779) | 508432336) + ((269455306 & 1769894151) | 149815628)) - 2089097405) ^ (524872353 % 327254586);
        int[] r3 = {491705403, 818579176, 1201981447, 810223592, 1243973908, -593404049, 554701476, 1889947178, 1780695788};
        int r5 = r3[0];
        int r6 = r3[1];
        int r7 = r3[2];
        int r8 = r3[3];
        int r9 = r3[4];
        int r10 = r3[5];
        int r11 = r3[6];
        int r32 = r3[7];
        int r33 = r32 % 1780695788;
        ByteBuffer r34 = ByteBuffer.allocate(r33 ^ (r11 + ((((r6 & (~r5)) | r7) + ((r5 & r8) | r9)) - r10)));
        r34.order(ByteOrder.LITTLE_ENDIAN);
        r34.putShort((short) r2);
        r34.putInt(r1);
        byte[] r12 = r34.array();
        return r0.zzg(r12, 0, r12.length);
    }

    public final zzd zza() {
        return this.zza;
    }
}
