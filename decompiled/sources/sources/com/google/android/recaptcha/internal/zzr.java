package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzr {
    public zzr() {
    }

    public static zzar zza(long r1) {
        return zzar.zzf(new zzq(r1));
    }

    public static int zzb(long r23, zzaq r25, boolean r26) {
        long[] r0 = {1527337001, 959997459, 671305984, 289023511, 8866124, 1524543404, 741858923, 1040759385, 213594814};
        long r2 = r0[0];
        long r5 = r0[1];
        long r7 = r0[2];
        long r9 = r0[3];
        long r11 = r0[4];
        long r13 = r0[5];
        long r15 = r0[6];
        long r17 = r0[7];
        long r152 = r15 + (((((~r2) & r5) | r7) + ((r2 & r9) | r11)) - r13);
        long r172 = r17 % 213594814;
        int r3 = ((((~1541787377) & 807501876) | 1768652572) + ((1541787377 & 470070696) | 1817148893)) - (-800639608);
        int r4 = 1593740418 % 411266131;
        int r52 = ((((~1273207249) & 809581705) | 523346986) + ((1273207249 & 1750604005) | 1270571892)) - 2083282137;
        int r6 = 1121147484 % 869439259;
        int r72 = ((((~1075669741) & 33686543) | 912538465) + ((1075669741 & 272507934) | 477952880)) - 1375986347;
        int r8 = 589306240 % 82095572;
        if (r26 == false) goto L5;
        long r92 = (r23 >> ((((((~955701520) & 1711343908) | 956198928) + ((955701520 & 1191258916) | 557348419)) - (-2009249340)) ^ (956774406 % 554472058))) ^ (r23 + r23);
    L6:
        int r22 = 1;
    L7:
        long r12 = r152 ^ r172;
        long r19 = r92 >>> (r52 ^ r6);
        if (r19 != 0) goto L10;
        if (r22 < 0) goto L10;
        boolean r112 = false;
    L12:
        int r93 = (int) (r92 & r12);
        if (r112 == false) goto L15;
        int r122 = r3 ^ r4;
        r93 = ((r93 | (r72 ^ r8)) << r122) >> r122;
    L15:
        r25.zza((byte) r93);
        if (r112 == false) goto L17;
        r22 = r22 + 1;
        r92 = r19;
        goto L7
    L17:
        return r22;
    L10:
        r112 = true;
        goto L12
    L5:
        r92 = r23;
        goto L6
    }
}
