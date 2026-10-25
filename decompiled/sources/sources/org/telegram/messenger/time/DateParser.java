package org.telegram.messenger.time;

import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public interface DateParser {
    Locale getLocale();

    String getPattern();

    TimeZone getTimeZone();

    Date parse(String r1);

    Date parse(String r1, ParsePosition r2);

    Object parseObject(String r1);

    Object parseObject(String r1, ParsePosition r2);
}
