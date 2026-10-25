package org.telegram.messenger.time;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class FastDateFormat extends Format implements DateParser, DatePrinter {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final FormatCache<FastDateFormat> cache = null;
    private static final long serialVersionUID = 2;
    private final FastDateParser parser;
    private final FastDatePrinter printer;

    /* renamed from: org.telegram.messenger.time.FastDateFormat$1, reason: invalid class name */
    public class AnonymousClass1 extends FormatCache<FastDateFormat> {
        public AnonymousClass1() {
        }

        @Override // org.telegram.messenger.time.FormatCache
        public /* bridge */ /* synthetic */ Format createInstance(String r1, TimeZone r2, Locale r3) {
            return createInstance(r1, r2, r3);
        }

        @Override // org.telegram.messenger.time.FormatCache
        public FastDateFormat createInstance(String r1, TimeZone r2, Locale r3) {
            return new FastDateFormat(r1, r2, r3);
        }
    }

    static {
        cache = new AnonymousClass1();
    }

    public FastDateFormat(String r2, TimeZone r3, Locale r4) {
        this(r2, r3, r4, null);
    }

    public FastDateFormat(String r2, TimeZone r3, Locale r4, Date r5) {
        this.printer = new FastDatePrinter(r2, r3, r4);
        this.parser = new FastDateParser(r2, r3, r4, r5);
    }

    public static FastDateFormat getDateInstance(int r2) {
        return (FastDateFormat) cache.getDateInstance(r2, null, null);
    }

    public static FastDateFormat getDateInstance(int r2, Locale r3) {
        return (FastDateFormat) cache.getDateInstance(r2, null, r3);
    }

    public static FastDateFormat getDateInstance(int r2, TimeZone r3) {
        return (FastDateFormat) cache.getDateInstance(r2, r3, null);
    }

    public static FastDateFormat getDateInstance(int r1, TimeZone r2, Locale r3) {
        return (FastDateFormat) cache.getDateInstance(r1, r2, r3);
    }

    public static FastDateFormat getDateTimeInstance(int r2, int r3) {
        return (FastDateFormat) cache.getDateTimeInstance(r2, r3, null, null);
    }

    public static FastDateFormat getDateTimeInstance(int r2, int r3, Locale r4) {
        return (FastDateFormat) cache.getDateTimeInstance(r2, r3, null, r4);
    }

    public static FastDateFormat getDateTimeInstance(int r1, int r2, TimeZone r3) {
        return getDateTimeInstance(r1, r2, r3, null);
    }

    public static FastDateFormat getDateTimeInstance(int r1, int r2, TimeZone r3, Locale r4) {
        return (FastDateFormat) cache.getDateTimeInstance(r1, r2, r3, r4);
    }

    public static FastDateFormat getInstance() {
        return (FastDateFormat) cache.getInstance();
    }

    public static FastDateFormat getInstance(String r2) {
        return (FastDateFormat) cache.getInstance(r2, null, null);
    }

    public static FastDateFormat getInstance(String r2, Locale r3) {
        return (FastDateFormat) cache.getInstance(r2, null, r3);
    }

    public static FastDateFormat getInstance(String r2, TimeZone r3) {
        return (FastDateFormat) cache.getInstance(r2, r3, null);
    }

    public static FastDateFormat getInstance(String r1, TimeZone r2, Locale r3) {
        return (FastDateFormat) cache.getInstance(r1, r2, r3);
    }

    public static FastDateFormat getTimeInstance(int r2) {
        return (FastDateFormat) cache.getTimeInstance(r2, null, null);
    }

    public static FastDateFormat getTimeInstance(int r2, Locale r3) {
        return (FastDateFormat) cache.getTimeInstance(r2, null, r3);
    }

    public static FastDateFormat getTimeInstance(int r2, TimeZone r3) {
        return (FastDateFormat) cache.getTimeInstance(r2, r3, null);
    }

    public static FastDateFormat getTimeInstance(int r1, TimeZone r2, Locale r3) {
        return (FastDateFormat) cache.getTimeInstance(r1, r2, r3);
    }

    public StringBuffer applyRules(Calendar r1, StringBuffer r2) {
        return this.printer.applyRules(r1, r2);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof FastDateFormat) == true) goto L7;
        return false;
    L7:
        return this.printer.equals(((FastDateFormat) r2).printer);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public String format(long r1) {
        return this.printer.format(r1);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public String format(Calendar r1) {
        return this.printer.format(r1);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public String format(Date r1) {
        return this.printer.format(r1);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public StringBuffer format(long r1, StringBuffer r3) {
        return this.printer.format(r1, r3);
    }

    @Override // java.text.Format, org.telegram.messenger.time.DatePrinter
    public StringBuffer format(Object r1, StringBuffer r2, FieldPosition r3) {
        return this.printer.format(r1, r2, r3);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public StringBuffer format(Calendar r1, StringBuffer r2) {
        return this.printer.format(r1, r2);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public StringBuffer format(Date r1, StringBuffer r2) {
        return this.printer.format(r1, r2);
    }

    @Override // org.telegram.messenger.time.DateParser, org.telegram.messenger.time.DatePrinter
    public Locale getLocale() {
        return this.printer.getLocale();
    }

    public int getMaxLengthEstimate() {
        return this.printer.getMaxLengthEstimate();
    }

    @Override // org.telegram.messenger.time.DateParser, org.telegram.messenger.time.DatePrinter
    public String getPattern() {
        return this.printer.getPattern();
    }

    @Override // org.telegram.messenger.time.DateParser, org.telegram.messenger.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.printer.getTimeZone();
    }

    public int hashCode() {
        return this.printer.hashCode();
    }

    @Override // org.telegram.messenger.time.DateParser
    public Date parse(String r1) {
        return this.parser.parse(r1);
    }

    @Override // org.telegram.messenger.time.DateParser
    public Date parse(String r1, ParsePosition r2) {
        return this.parser.parse(r1, r2);
    }

    @Override // java.text.Format, org.telegram.messenger.time.DateParser
    public Object parseObject(String r1, ParsePosition r2) {
        return this.parser.parseObject(r1, r2);
    }

    public String toString() {
        return "FastDateFormat[" + this.printer.getPattern() + "," + this.printer.getLocale() + "," + this.printer.getTimeZone().getID() + "]";
    }
}
