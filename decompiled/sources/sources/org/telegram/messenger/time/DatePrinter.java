package org.telegram.messenger.time;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public interface DatePrinter {
    String format(long r1);

    String format(Calendar r1);

    String format(Date r1);

    StringBuffer format(long r1, StringBuffer r3);

    StringBuffer format(Object r1, StringBuffer r2, FieldPosition r3);

    StringBuffer format(Calendar r1, StringBuffer r2);

    StringBuffer format(Date r1, StringBuffer r2);

    Locale getLocale();

    String getPattern();

    TimeZone getTimeZone();
}
