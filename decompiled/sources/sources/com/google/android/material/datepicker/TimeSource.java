package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes5.dex */
class TimeSource {
    private static final TimeSource SYSTEM_TIME_SOURCE = null;
    private final Long fixedTimeMs;
    private final TimeZone fixedTimeZone;

    static {
        SYSTEM_TIME_SOURCE = new TimeSource(null, null);
    }

    private TimeSource(Long r1, TimeZone r2) {
        this.fixedTimeMs = r1;
        this.fixedTimeZone = r2;
    }

    public static TimeSource fixed(long r1) {
        return new TimeSource(Long.valueOf(r1), null);
    }

    public static TimeSource fixed(long r1, TimeZone r3) {
        return new TimeSource(Long.valueOf(r1), r3);
    }

    public static TimeSource system() {
        return SYSTEM_TIME_SOURCE;
    }

    public Calendar now() {
        return now(this.fixedTimeZone);
    }

    public Calendar now(TimeZone r3) {
        if (r3 != null) goto L4;
        Calendar r32 = Calendar.getInstance();
    L5:
        Long r2 = this.fixedTimeMs;
        if (r2 == null) goto L8;
        r32.setTimeInMillis(r2.longValue());
    L8:
        return r32;
    L4:
        r32 = Calendar.getInstance(r3);
        goto L5
    }
}
