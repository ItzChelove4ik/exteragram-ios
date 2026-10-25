package com.google.android.gms.internal.location;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;

/* loaded from: classes4.dex */
public abstract class zzen {
    private static final SimpleDateFormat zza = null;
    private static final StringBuilder zzb = null;

    static {
        Locale r1 = Locale.ROOT;
        zza = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", r1);
        new SimpleDateFormat("MM-dd HH:mm:ss", r1);
        zzb = new StringBuilder(33);
    }

    public static String zza(long r2) {
        if (r2 < 0) goto L7;
        return zza.format(new Date(r2));
    L7:
        return Long.toString(r2);
    }

    public static String zzb(long r2) {
        StringBuilder r0 = zzb;
        monitor-enter(r0);
        r0.setLength(0);     // Catch: Throwable -> L8
        zzc(r2, r0);     // Catch: Throwable -> L8
        String r22 = r0.toString();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r22;
    L8:
        th = move-exception;
        throw th;
    }

    public static StringBuilder zzc(long r8, StringBuilder r10) {
        if (r8 != 0) goto L6;
        r10.append("0s");
        return r10;
    L6:
        r10.ensureCapacity(r10.length() + 27);
        boolean r3 = false;
        if (r8 >= 0) goto L13;
        r10.append("-");
        if (r8 == Long.MIN_VALUE) goto L11;
        r8 = -r8;
        goto L13
    L11:
        r3 = true;
        r8 = LongCompanionObject.MAX_VALUE;
    L13:
        if (r8 < DurationKt.MILLIS_IN_DAY) goto L15;
        r10.append(r8 / DurationKt.MILLIS_IN_DAY);
        r10.append("d");
        r8 = r8 % DurationKt.MILLIS_IN_DAY;
    L15:
        if (r3 == false) goto L18;
        r8 = r8 + 1;
    L18:
        if (r8 < DurationKt.MILLIS_IN_HOUR) goto L21;
        r10.append(r8 / DurationKt.MILLIS_IN_HOUR);
        r10.append("h");
        r8 = r8 % DurationKt.MILLIS_IN_HOUR;
    L21:
        if (r8 < 60000) goto L24;
        r10.append(r8 / 60000);
        r10.append("m");
        r8 = r8 % 60000;
    L24:
        if (r8 < 1000) goto L27;
        r10.append(r8 / 1000);
        r10.append("s");
        r8 = r8 % 1000;
    L27:
        if (r8 <= 0) goto L29;
        r10.append(r8);
        r10.append("ms");
    L29:
        return r10;
    }
}
