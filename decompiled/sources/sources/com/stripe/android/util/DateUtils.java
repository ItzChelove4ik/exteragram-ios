package com.stripe.android.util;

import com.stripe.android.time.Clock;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class DateUtils {
    public static boolean hasMonthPassed(int r3, int r4) {
        if (hasYearPassed(r3) == false) goto L5;
        return true;
    L5:
        Calendar r0 = Clock.getCalendarInstance();
        if (normalizeYear(r3) == r0.get(1)) goto L8;
        return false;
    L8:
        if (r4 >= (r0.get(2) + 1)) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean hasYearPassed(int r2) {
        if (normalizeYear(r2) >= Clock.getCalendarInstance().get(1)) goto L5;
        return true;
    L5:
        return false;
    }

    private static int normalizeYear(int r3) {
        if (r3 >= 100) goto L7;
        if (r3 >= 0) goto L6;
        return r3;
    L6:
        return Integer.parseInt(String.format(Locale.US, "%s%02d", new Object[]{String.valueOf(Clock.getCalendarInstance().get(1)).substring(0, r0.length() - 2), Integer.valueOf(r3)}));
    L7:
        return r3;
    }
}
