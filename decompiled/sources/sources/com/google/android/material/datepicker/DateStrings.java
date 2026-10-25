package com.google.android.material.datepicker;

import android.content.Context;
import androidx.core.util.Pair;
import com.google.android.material.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes5.dex */
class DateStrings {
    private DateStrings() {
    }

    public static Pair<String, String> getDateRangeString(Long r1, Long r2) {
        return getDateRangeString(r1, r2, null);
    }

    public static Pair<String, String> getDateRangeString(Long r5, Long r6, SimpleDateFormat r7) {
        if (r5 != null) goto L7;
        if (r6 != null) goto L7;
        return Pair.create(null, null);
    L7:
        if (r5 == null) goto L9;
        if (r6 == null) goto L12;
        Calendar r0 = UtcDates.getTodayCalendar();
        Calendar r1 = UtcDates.getUtcCalendar();
        r1.setTimeInMillis(r5.longValue());
        Calendar r2 = UtcDates.getUtcCalendar();
        r2.setTimeInMillis(r6.longValue());
        if (r7 == null) goto L18;
        Date r02 = new Date(r5.longValue());
        Date r52 = new Date(r6.longValue());
        return Pair.create(r7.format(r02), r7.format(r52));
    L18:
        if (r1.get(1) != r2.get(1)) goto L26;
        if (r1.get(1) != r0.get(1)) goto L24;
        return Pair.create(getMonthDay(r5.longValue(), Locale.getDefault()), getMonthDay(r6.longValue(), Locale.getDefault()));
    L24:
        return Pair.create(getMonthDay(r5.longValue(), Locale.getDefault()), getYearMonthDay(r6.longValue(), Locale.getDefault()));
    L26:
        return Pair.create(getYearMonthDay(r5.longValue(), Locale.getDefault()), getYearMonthDay(r6.longValue(), Locale.getDefault()));
    L12:
        return Pair.create(getDateString(r5.longValue(), r7), null);
    L9:
        return Pair.create(null, getDateString(r6.longValue(), r7));
    }

    public static String getDateString(long r1) {
        return getDateString(r1, null);
    }

    public static String getDateString(long r1, SimpleDateFormat r3) {
        if (r3 == null) goto L6;
        return r3.format(new Date(r1));
    L6:
        if (isDateWithinCurrentYear(r1) == false) goto L10;
        return getMonthDay(r1);
    L10:
        return getYearMonthDay(r1);
    }

    public static String getDayContentDescription(Context r0, long r1, boolean r3, boolean r4, boolean r5) {
        String r12 = getOptionalYearMonthDayOfWeekDay(r1);
        if (r3 == false) goto L5;
        r12 = String.format(r0.getString(R.string.mtrl_picker_today_description), new Object[]{r12});
    L5:
        if (r4 == true) goto L7;
        if (r5 == true) goto L10;
        return r12;
    L10:
        return String.format(r0.getString(R.string.mtrl_picker_end_date_description), new Object[]{r12});
    L7:
        return String.format(r0.getString(R.string.mtrl_picker_start_date_description), new Object[]{r12});
    }

    public static String getMonthDay(long r1) {
        return getMonthDay(r1, Locale.getDefault());
    }

    public static String getMonthDay(long r1, Locale r3) {
        return UtcDates.getAbbrMonthDayFormat(r3).format(new Date(r1));
    }

    public static String getMonthDayOfWeekDay(long r1) {
        return getMonthDayOfWeekDay(r1, Locale.getDefault());
    }

    public static String getMonthDayOfWeekDay(long r1, Locale r3) {
        return UtcDates.getMonthWeekdayDayFormat(r3).format(new Date(r1));
    }

    public static String getOptionalYearMonthDayOfWeekDay(long r1) {
        if (isDateWithinCurrentYear(r1) == false) goto L7;
        return getMonthDayOfWeekDay(r1);
    L7:
        return getYearMonthDayOfWeekDay(r1);
    }

    public static String getYearContentDescription(Context r2, int r3) {
        if (UtcDates.getTodayCalendar().get(1) != r3) goto L7;
        return String.format(r2.getString(R.string.mtrl_picker_navigate_to_current_year_description), new Object[]{Integer.valueOf(r3)});
    L7:
        return String.format(r2.getString(R.string.mtrl_picker_navigate_to_year_description), new Object[]{Integer.valueOf(r3)});
    }

    public static String getYearMonth(long r2) {
        return UtcDates.getYearMonthFormat(Locale.getDefault()).format(new Date(r2));
    }

    public static String getYearMonthDay(long r1) {
        return getYearMonthDay(r1, Locale.getDefault());
    }

    public static String getYearMonthDay(long r1, Locale r3) {
        return UtcDates.getYearAbbrMonthDayFormat(r3).format(new Date(r1));
    }

    public static String getYearMonthDayOfWeekDay(long r1) {
        return getYearMonthDayOfWeekDay(r1, Locale.getDefault());
    }

    public static String getYearMonthDayOfWeekDay(long r1, Locale r3) {
        return UtcDates.getYearMonthWeekdayDayFormat(r3).format(new Date(r1));
    }

    private static boolean isDateWithinCurrentYear(long r2) {
        Calendar r0 = UtcDates.getTodayCalendar();
        Calendar r1 = UtcDates.getUtcCalendar();
        r1.setTimeInMillis(r2);
        if (r0.get(1) != r1.get(1)) goto L5;
        return true;
    L5:
        return false;
    }
}
