package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class PreJava9DateFormatProvider {
    private static String getDatePartOfDateTimePattern(int r1) {
        if (r1 != 0) goto L4;
        return "EEEE, MMMM d, yyyy";
    L4:
        if (r1 != 1) goto L6;
        return "MMMM d, yyyy";
    L6:
        if (r1 != 2) goto L8;
        return "MMM d, yyyy";
    L8:
        if (r1 != 3) goto L11;
        return "M/d/yy";
    L11:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown DateFormat style: ", r1);
        return null;
    }

    private static String getTimePartOfDateTimePattern(int r1) {
        if (r1 != 0) goto L4;
        return "h:mm:ss a z";
    L4:
        if (r1 != 1) goto L6;
        return "h:mm:ss a z";
    L6:
        if (r1 != 2) goto L8;
        return "h:mm:ss a";
    L8:
        if (r1 != 3) goto L11;
        return "h:mm a";
    L11:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown DateFormat style: ", r1);
        return null;
    }

    public static DateFormat getUsDateTimeFormat(int r1, int r2) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(r1) + " " + getTimePartOfDateTimePattern(r2), Locale.US);
    }
}
