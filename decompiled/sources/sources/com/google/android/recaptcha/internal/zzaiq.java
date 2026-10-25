package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.time.DurationKt;

/* loaded from: classes5.dex */
public abstract class zzaiq {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ThreadLocal zzb = null;

    static {
        zzahw r0 = zzahx.zzc();
        r0.zza(-62135596800L);
        r0.zzb(0);
        zzahx r02 = (zzahx) r0.zzp();
        zzahw r03 = zzahx.zzc();
        r03.zza(253402300799L);
        r03.zzb(999999999);
        zzahx r04 = (zzahx) r03.zzp();
        zzahw r05 = zzahx.zzc();
        r05.zza(0);
        r05.zzb(0);
        zzahx r06 = (zzahx) r05.zzp();
        zzb = new zzaip();
        zzf("now");
        zzf("getEpochSecond");
        zzf("getNano");
    }

    public static zzahx zza(zzahx r5) {
        long r0 = r5.zza();
        boolean r2 = zze(r0);
        int r3 = r5.zzb();
        if (r2 == false) goto L8;
        if (r3 < 0) goto L8;
        if (r3 >= 1000000000) goto L8;
        return r5;
    L8:
        int r22 = String.valueOf(r0).length();
        StringBuilder r4 = new StringBuilder(((r22 + 135) + String.valueOf(r3).length()) + 37);
        r4.append("Timestamp is not valid. See proto definition for valid values. Seconds (");
        r4.append(r0);
        r4.append(") must be in range [-62,135,596,800, +253,402,300,799]. Nanos (");
        r4.append(r3);
        r4.append(") must be in range [0, +999,999,999].");
        throw new IllegalArgumentException(r4.toString());
    }

    public static String zzb(zzahx r5) {
        zza(r5);
        long r0 = r5.zza();
        int r52 = r5.zzb();
        StringBuilder r2 = new StringBuilder();
        Date r3 = new Date(r0 * 1000);
        r2.append(((SimpleDateFormat) zzb.get()).format(r3));
        if (r52 == 0) goto L12;
        r2.append(".");
        if ((r52 % DurationKt.NANOS_IN_MILLIS) != 0) goto L8;
        String r53 = String.format(Locale.ENGLISH, "%1$03d", new Object[]{Integer.valueOf(r52 / DurationKt.NANOS_IN_MILLIS)});
    L11:
        r2.append(r53);
        goto L12
    L8:
        if ((r52 % 1000) != 0) goto L10;
        r53 = String.format(Locale.ENGLISH, "%1$06d", new Object[]{Integer.valueOf(r52 / 1000)});
        goto L11
    L10:
        r53 = String.format(Locale.ENGLISH, "%1$09d", new Object[]{Integer.valueOf(r52)});
    L12:
        r2.append("Z");
        return r2.toString();
    }

    public static zzahx zzc(long r6) {
        long r2 = (r6 % 1000) * 1000000;
        return zzd(r6 / 1000, (int) r2);
    }

    public static zzahx zzd(long r4, int r6) {
        if (zze(r4) == true) goto L5;
        StringBuilder r1 = new StringBuilder(String.valueOf(r4).length() + 117);
        r1.append("Timestamp is not valid. Input seconds is too large. Seconds (");
        r1.append(r4);
        r1.append(") must be in range [-62,135,596,800, +253,402,300,799]. ");
        throw new IllegalArgumentException(r1.toString());
    L5:
        if (r6 <= (-1000000000)) goto L7;
        if (r6 >= 1000000000) goto L7;
    L8:
        if (r6 >= 0) goto L10;
        r6 = r6 + 1000000000;
        r4 = Math.subtractExact(r4, 1);
    L10:
        zzahw r0 = zzahx.zzc();
        r0.zza(r4);
        r0.zzb(r6);
        zzahx r42 = (zzahx) r0.zzp();
        zza(r42);
        return r42;
    L7:
        r4 = Math.addExact(r4, r6 / 1000000000);
        r6 = r6 % 1000000000;
        goto L8
    }

    private static boolean zze(long r2) {
        if (r2 >= (-62135596800L)) goto L5;
        return false;
    L5:
        if (r2 > 253402300799L) goto L10;
        return true;
    L10:
        return false;
    }

    private static Method zzf(String r2) {
        return Class.forName("java.time.Instant").getMethod(r2, null);
    L5:
        return null;
    }
}
