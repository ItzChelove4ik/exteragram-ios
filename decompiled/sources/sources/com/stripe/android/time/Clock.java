package com.stripe.android.time;

import java.util.Calendar;

/* loaded from: classes5.dex */
public class Clock {
    private static Clock instance;
    protected Calendar calendarInstance;

    public Clock() {
    }

    private Calendar _calendarInstance() {
        Calendar r0 = this.calendarInstance;
        if (r0 == null) goto L7;
        return (Calendar) r0.clone();
    L7:
        return Calendar.getInstance();
    }

    public static Calendar getCalendarInstance() {
        return getInstance()._calendarInstance();
    }

    public static Clock getInstance() {
        if (instance != null) goto L6;
        instance = new Clock();
    L6:
        return instance;
    }
}
