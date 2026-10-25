package com.google.android.material.datepicker;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.text.SpannableString;
import android.text.style.TtsSpan;
import com.google.android.material.R;
import j$.util.DesugarTimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
class UtcDates {
    static final String UTC = "UTC";
    static AtomicReference<TimeSource> timeSourceRef;

    static {
        timeSourceRef = new AtomicReference();
    }

    private UtcDates() {
    }

    public static long canonicalYearMonthDay(long r1) {
        Calendar r0 = getUtcCalendar();
        r0.setTimeInMillis(r1);
        return getDayCopy(r0).getTimeInMillis();
    }

    private static int findCharactersInDateFormatPattern(String r2, String r3, int r4, int r5) {
    L2:
        if (r5 < 0) goto L17;
        if (r5 >= r2.length()) goto L17;
        if (r3.indexOf(r2.charAt(r5)) != (-1)) goto L17;
        if (r2.charAt(r5) != '\'') goto L16;
    L9:
        r5 = r5 + r4;
        if (r5 < 0) goto L16;
        if (r5 >= r2.length()) goto L16;
        if (r2.charAt(r5) != '\'') goto L9;
    L16:
        r5 = r5 + r4;
    L17:
        return r5;
    }

    @TargetApi(24)
    public static DateFormat getAbbrMonthDayFormat(Locale r1) {
        return getAndroidFormat("MMMd", r1);
    }

    @TargetApi(24)
    private static DateFormat getAndroidFormat(String r0, Locale r1) {
        DateFormat r02 = DateFormat.getInstanceForSkeleton(r0, r1);
        r02.setTimeZone(getUtcAndroidTimeZone());
        r02.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        return r02;
    }

    public static String getDatePatternAsInputFormat(String r3) {
        return r3.replaceAll("[^dMy/\\-.]", _UrlKt.FRAGMENT_ENCODE_SET).replaceAll("d{1,2}", "dd").replaceAll("M{1,2}", "MM").replaceAll("y{1,4}", "yyyy").replaceAll("\\.$", _UrlKt.FRAGMENT_ENCODE_SET).replaceAll("My", "M/y");
    }

    public static Calendar getDayCopy(Calendar r4) {
        Calendar r42 = getUtcCalendarOf(r4);
        Calendar r0 = getUtcCalendar();
        r0.set(r42.get(1), r42.get(2), r42.get(5));
        return r0;
    }

    public static SimpleDateFormat getDefaultTextInputFormat() {
        SimpleDateFormat r1 = new SimpleDateFormat(getDatePatternAsInputFormat(((SimpleDateFormat) java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toPattern()), Locale.getDefault());
        r1.setTimeZone(getTimeZone());
        r1.setLenient(false);
        return r1;
    }

    public static String getDefaultTextInputHint(Resources r6, SimpleDateFormat r7) {
        String r72 = r7.toPattern();
        String r0 = r6.getString(R.string.mtrl_picker_text_input_year_abbr);
        String r1 = r6.getString(R.string.mtrl_picker_text_input_month_abbr);
        String r62 = r6.getString(R.string.mtrl_picker_text_input_day_abbr);
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) == false) goto L6;
        r72 = r72.replaceAll("d+", "d").replaceAll("M+", "M").replaceAll("y+", "y");
    L6:
        return r72.replace("d", r62).replace("M", r1).replace("y", r0);
    }

    private static java.text.DateFormat getFormat(int r0, Locale r1) {
        java.text.DateFormat r02 = java.text.DateFormat.getDateInstance(r0, r1);
        r02.setTimeZone(getTimeZone());
        return r02;
    }

    public static java.text.DateFormat getFullFormat() {
        return getFullFormat(Locale.getDefault());
    }

    public static java.text.DateFormat getFullFormat(Locale r1) {
        return getFormat(0, r1);
    }

    public static java.text.DateFormat getMediumFormat() {
        return getMediumFormat(Locale.getDefault());
    }

    public static java.text.DateFormat getMediumFormat(Locale r1) {
        return getFormat(2, r1);
    }

    public static java.text.DateFormat getMediumNoYear() {
        return getMediumNoYear(Locale.getDefault());
    }

    public static java.text.DateFormat getMediumNoYear(Locale r1) {
        SimpleDateFormat r12 = (SimpleDateFormat) getMediumFormat(r1);
        r12.applyPattern(removeYearFromDateFormatPattern(r12.toPattern()));
        return r12;
    }

    @TargetApi(24)
    public static DateFormat getMonthWeekdayDayFormat(Locale r1) {
        return getAndroidFormat("MMMMEEEEd", r1);
    }

    public static java.text.DateFormat getNormalizedFormat(java.text.DateFormat r1) {
        java.text.DateFormat r12 = (java.text.DateFormat) r1.clone();
        r12.setTimeZone(getTimeZone());
        return r12;
    }

    public static SimpleDateFormat getSimpleFormat(String r1) {
        return getSimpleFormat(r1, Locale.getDefault());
    }

    private static SimpleDateFormat getSimpleFormat(String r1, Locale r2) {
        SimpleDateFormat r0 = new SimpleDateFormat(r1, r2);
        r0.setTimeZone(getTimeZone());
        return r0;
    }

    public static TimeSource getTimeSource() {
        TimeSource r0 = timeSourceRef.get();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return TimeSource.system();
    }

    private static TimeZone getTimeZone() {
        return DesugarTimeZone.getTimeZone(UTC);
    }

    public static Calendar getTodayCalendar() {
        Calendar r0 = getTimeSource().now();
        r0.set(11, 0);
        r0.set(12, 0);
        r0.set(13, 0);
        r0.set(14, 0);
        r0.setTimeZone(getTimeZone());
        return r0;
    }

    @TargetApi(24)
    private static android.icu.util.TimeZone getUtcAndroidTimeZone() {
        return android.icu.util.TimeZone.getTimeZone(UTC);
    }

    public static Calendar getUtcCalendar() {
        return getUtcCalendarOf(null);
    }

    public static Calendar getUtcCalendarOf(Calendar r3) {
        Calendar r0 = Calendar.getInstance(getTimeZone());
        if (r3 != null) goto L6;
        r0.clear();
        return r0;
    L6:
        r0.setTimeInMillis(r3.getTimeInMillis());
        return r0;
    }

    public static SpannableString getVerbatimTextInputHint(String r4) {
        SpannableString r0 = new SpannableString(r4);
        r0.setSpan(new TtsSpan.Builder("android.type.verbatim").build(), 0, r0.length(), 33);
        return r0;
    }

    @TargetApi(24)
    public static DateFormat getYearAbbrMonthDayFormat(Locale r1) {
        return getAndroidFormat("yMMMd", r1);
    }

    @TargetApi(24)
    public static DateFormat getYearMonthFormat(Locale r1) {
        return getAndroidFormat("yMMMM", r1);
    }

    @TargetApi(24)
    public static DateFormat getYearMonthWeekdayDayFormat(Locale r1) {
        return getAndroidFormat("yMMMMEEEEd", r1);
    }

    private static String removeYearFromDateFormatPattern(String r5) {
        int r0 = findCharactersInDateFormatPattern(r5, "yY", 1, 0);
        if (r0 < r5.length()) goto L5;
        return r5;
    L5:
        String r1 = "EMd";
        int r3 = findCharactersInDateFormatPattern(r5, "EMd", 1, r0);
        if (r3 >= r5.length()) goto L9;
        r1 = "EMd,";
    L9:
        return r5.replace(r5.substring(findCharactersInDateFormatPattern(r5, r1, -1, r0) + 1, r3), " ").trim();
    }

    public static void setTimeSource(TimeSource r1) {
        timeSourceRef.set(r1);
    }
}
