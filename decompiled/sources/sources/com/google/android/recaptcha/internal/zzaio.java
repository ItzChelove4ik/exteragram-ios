package com.google.android.recaptcha.internal;

import kotlin.time.DurationKt;

/* loaded from: classes5.dex */
public abstract class zzaio {
    static {
        zzafd r0 = zzafe.zzc();
        r0.zza(-315576000000L);
        r0.zzb(-999999999);
        zzafe r02 = (zzafe) r0.zzp();
        zzafd r03 = zzafe.zzc();
        r03.zza(315576000000L);
        r03.zzb(999999999);
        zzafe r04 = (zzafe) r03.zzp();
        zzafd r05 = zzafe.zzc();
        r05.zza(0);
        r05.zzb(0);
        zzafe r06 = (zzafe) r05.zzp();
    }

    public static zzafe zza(zzafe r8) {
        long r0 = r8.zza();
        int r3 = r8.zzb();
        if (r0 >= (-315576000000L)) goto L5;
    L16:
        StringBuilder r4 = new StringBuilder(((String.valueOf(r0).length() + 135) + String.valueOf(r3).length()) + 89);
        r4.append("Duration is not valid. See proto definition for valid values. Seconds (");
        r4.append(r0);
        r4.append(") must be in range [-315,576,000,000, +315,576,000,000]. Nanos (");
        r4.append(r3);
        r4.append(") must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds");
        throw new IllegalArgumentException(r4.toString());
    L5:
        if (r0 > 315576000000L) goto L16;
        if (r3 < (-999999999)) goto L16;
        if (r3 >= 1000000000) goto L16;
        if (r0 < 0) goto L13;
        if (r3 < 0) goto L13;
    L15:
        return r8;
    L13:
        if (r0 > 0) goto L16;
        if (r3 > 0) goto L16;
        goto L15
    }

    public static long zzb(zzafe r4) {
        zza(r4);
        return Math.addExact(Math.multiplyExact(r4.zza(), 1000), r4.zzb() / DurationKt.NANOS_IN_MILLIS);
    }

    public static zzafe zzc(long r6, int r8) {
        if (r8 <= (-1000000000)) goto L5;
        if (r8 >= 1000000000) goto L5;
    L7:
        if (r6 <= 0) goto L11;
        if (r8 >= 0) goto L11;
        r8 = r8 + 1000000000;
        r6 = r6 - 1;
    L11:
        if (r6 >= 0) goto L14;
        if (r8 <= 0) goto L14;
        r8 = r8 - 1000000000;
        r6 = r6 + 1;
    L14:
        zzafd r0 = zzafe.zzc();
        r0.zza(r6);
        r0.zzb(r8);
        zzafe r62 = (zzafe) r0.zzp();
        zza(r62);
        return r62;
    L5:
        r6 = Math.addExact(r6, r8 / 1000000000);
        r8 = r8 % 1000000000;
        goto L7
    }
}
