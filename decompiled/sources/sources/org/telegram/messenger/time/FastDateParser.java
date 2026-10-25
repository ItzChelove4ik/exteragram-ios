package org.telegram.messenger.time;

import c.f$$ExternalSyntheticBUOutline1;
import j$.util.DesugarTimeZone;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class FastDateParser implements DateParser, Serializable {
    private static final Strategy ABBREVIATED_YEAR_STRATEGY = null;
    private static final Strategy DAY_OF_MONTH_STRATEGY = null;
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = null;
    private static final Strategy DAY_OF_YEAR_STRATEGY = null;
    private static final Strategy HOUR_OF_DAY_STRATEGY = null;
    private static final Strategy HOUR_STRATEGY = null;
    static final Locale JAPANESE_IMPERIAL = null;
    private static final Strategy LITERAL_YEAR_STRATEGY = null;
    private static final Strategy MILLISECOND_STRATEGY = null;
    private static final Strategy MINUTE_STRATEGY = null;
    private static final Strategy MODULO_HOUR_OF_DAY_STRATEGY = null;
    private static final Strategy MODULO_HOUR_STRATEGY = null;
    private static final Strategy NUMBER_MONTH_STRATEGY = null;
    private static final Strategy SECOND_STRATEGY = null;
    private static final Strategy WEEK_OF_MONTH_STRATEGY = null;
    private static final Strategy WEEK_OF_YEAR_STRATEGY = null;
    private static final ConcurrentMap<Locale, Strategy>[] caches = null;
    private static final Pattern formatPattern = null;
    private static final long serialVersionUID = 2;
    private final int century;
    private transient String currentFormatField;
    private final Locale locale;
    private transient Strategy nextStrategy;
    private transient Pattern parsePattern;
    private final String pattern;
    private final int startYear;
    private transient Strategy[] strategies;
    private final TimeZone timeZone;

    /* renamed from: org.telegram.messenger.time.FastDateParser$1, reason: invalid class name */
    public class AnonymousClass1 extends NumberStrategy {
        public AnonymousClass1(int r1) {
            super(r1);
        }

        @Override // org.telegram.messenger.time.FastDateParser.NumberStrategy, org.telegram.messenger.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser r1, Calendar r2, String r3) {
            int r0 = Integer.parseInt(r3);
            if (r0 >= 100) goto L5;
            r0 = FastDateParser.m5174$$Nest$madjustYear(r1, r0);
        L5:
            r2.set(1, r0);
        }
    }

    /* renamed from: org.telegram.messenger.time.FastDateParser$2, reason: invalid class name */
    public class AnonymousClass2 extends NumberStrategy {
        public AnonymousClass2(int r1) {
            super(r1);
        }

        @Override // org.telegram.messenger.time.FastDateParser.NumberStrategy
        public int modify(int r1) {
            return r1 - 1;
        }
    }

    /* renamed from: org.telegram.messenger.time.FastDateParser$3, reason: invalid class name */
    public class AnonymousClass3 extends NumberStrategy {
        public AnonymousClass3(int r1) {
            super(r1);
        }

        @Override // org.telegram.messenger.time.FastDateParser.NumberStrategy
        public int modify(int r1) {
            return r1 % 24;
        }
    }

    /* renamed from: org.telegram.messenger.time.FastDateParser$4, reason: invalid class name */
    public class AnonymousClass4 extends NumberStrategy {
        public AnonymousClass4(int r1) {
            super(r1);
        }

        @Override // org.telegram.messenger.time.FastDateParser.NumberStrategy
        public int modify(int r1) {
            return r1 % 12;
        }
    }

    public static class CopyQuotedStrategy extends Strategy {
        private final String formatField;

        public CopyQuotedStrategy(String r2) {
            super(null);
            this.formatField = r2;
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser r1, StringBuilder r2) {
            FastDateParser.m5175$$Nest$smescapeRegex(r2, this.formatField, true);
            return false;
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public boolean isNumber() {
            char r0 = this.formatField.charAt(0);
            if (r0 != '\'') goto L6;
            r0 = this.formatField.charAt(1);
        L6:
            return Character.isDigit(r0);
        }
    }

    public static class NumberStrategy extends Strategy {
        private final int field;

        public NumberStrategy(int r2) {
            super(null);
            this.field = r2;
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser r1, StringBuilder r2) {
            if (r1.isNextNumber() == false) goto L5;
            r2.append("(\\p{Nd}{");
            r2.append(r1.getFieldWidth());
            r2.append("}+)");
            return true;
        L5:
            r2.append("(\\p{Nd}++)");
            return true;
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public boolean isNumber() {
            return true;
        }

        public int modify(int r1) {
            return r1;
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser r1, Calendar r2, String r3) {
            r2.set(this.field, modify(Integer.parseInt(r3)));
        }
    }

    public static abstract class Strategy {
        private Strategy() {
        }

        public /* synthetic */ Strategy(FastDateParserIA r1) {
            this();
        }

        public abstract boolean addRegex(FastDateParser r1, StringBuilder r2);

        public boolean isNumber() {
            return false;
        }

        public void setCalendar(FastDateParser r1, Calendar r2, String r3) {
        }
    }

    public static class TextStrategy extends Strategy {
        private final int field;
        private final Map<String, Integer> keyValues;

        public TextStrategy(int r2, Calendar r3, Locale r4) {
            super(null);
            this.field = r2;
            this.keyValues = FastDateParser.m5176$$Nest$smgetDisplayNames(r2, r3, r4);
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser r2, StringBuilder r3) {
            r3.append('(');
            Iterator<String> r1 = this.keyValues.keySet().iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            FastDateParser.m5175$$Nest$smescapeRegex(r3, r1.next(), false).append('|');
            goto L4
        L6:
            r3.setCharAt(r3.length() - 1, ')');
            return true;
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser r1, Calendar r2, String r3) {
            Integer r12 = this.keyValues.get(r3);
            if (r12 != null) goto L10;
            StringBuilder r13 = new StringBuilder(r3);
            r13.append(" not in (");
            Iterator<String> r0 = this.keyValues.keySet().iterator();
        L6:
            if (r0.hasNext() == false) goto L8;
            r13.append(r0.next());
            r13.append(' ');
            goto L6
        L8:
            r13.setCharAt(r13.length() - 1, ')');
            throw new IllegalArgumentException(r13.toString());
        L10:
            r2.set(this.field, r12.intValue());
        }
    }

    /* loaded from: classes3.dex */
    public static class TimeZoneStrategy extends Strategy {
        private static final int ID = 0;
        private static final int LONG_DST = 3;
        private static final int LONG_STD = 1;
        private static final int SHORT_DST = 4;
        private static final int SHORT_STD = 2;
        private final SortedMap<String, TimeZone> tzNames;
        private final String validTimeZoneChars;

        public TimeZoneStrategy(Locale r9) {
            super(null);
            this.tzNames = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            String[][] r92 = DateFormatSymbols.getInstance(r9).getZoneStrings();
            int r0 = r92.length;
            int r2 = 0;
        L4:
            if (r2 >= r0) goto L23;
            String[] r4 = r92[r2];
            if (r4[0].startsWith("GMT") == true) goto L22;
            TimeZone r5 = DesugarTimeZone.getTimeZone(r4[0]);
            if (this.tzNames.containsKey(r4[1]) == true) goto L12;
            this.tzNames.put(r4[1], r5);
        L12:
            if (this.tzNames.containsKey(r4[2]) == true) goto L15;
            this.tzNames.put(r4[2], r5);
        L15:
            if (r5.useDaylightTime() == false) goto L22;
            if (this.tzNames.containsKey(r4[3]) == true) goto L20;
            this.tzNames.put(r4[3], r5);
        L20:
            if (this.tzNames.containsKey(r4[4]) == true) goto L22;
            this.tzNames.put(r4[4], r5);
        L22:
            r2 = r2 + 1;
            goto L4
        L23:
            StringBuilder r93 = new StringBuilder();
            r93.append("(GMT[+\\-]\\d{0,1}\\d{2}|[+\\-]\\d{2}:?\\d{2}|");
            Iterator<String> r02 = this.tzNames.keySet().iterator();
        L25:
            if (r02.hasNext() == false) goto L27;
            FastDateParser.m5175$$Nest$smescapeRegex(r93, r02.next(), false).append('|');
            goto L25
        L27:
            r93.setCharAt(r93.length() - 1, ')');
            this.validTimeZoneChars = r93.toString();
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser r1, StringBuilder r2) {
            r2.append(this.validTimeZoneChars);
            return true;
        }

        @Override // org.telegram.messenger.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser r4, Calendar r5, String r6) {
            if (r6.charAt(0) != '+') goto L5;
        L15:
            TimeZone r3 = DesugarTimeZone.getTimeZone("GMT".concat(r6));
        L16:
            r5.setTimeZone(r3);
            return;
        L5:
            if (r6.charAt(0) == '-') goto L15;
            if (r6.startsWith("GMT") == false) goto L10;
            r3 = DesugarTimeZone.getTimeZone(r6);
            goto L16
        L10:
            r3 = this.tzNames.get(r6);
            if (r3 != null) goto L16;
            f$$ExternalSyntheticBUOutline1.m(r6.concat(" is not a supported timezone name"));
        }
    }

    /* renamed from: -$$Nest$madjustYear, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5174$$Nest$madjustYear(FastDateParser r0, int r1) {
        return r0.adjustYear(r1);
    }

    /* renamed from: -$$Nest$smescapeRegex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StringBuilder m5175$$Nest$smescapeRegex(StringBuilder r0, String r1, boolean r2) {
        return escapeRegex(r0, r1, r2);
    }

    /* renamed from: -$$Nest$smgetDisplayNames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m5176$$Nest$smgetDisplayNames(int r0, Calendar r1, Locale r2) {
        return getDisplayNames(r0, r1, r2);
    }

    static {
        JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
        formatPattern = Pattern.compile("D+|E+|F+|G+|H+|K+|M+|L+|S+|W+|Z+|a+|d+|h+|k+|m+|s+|w+|y+|z+|''|'[^']++(''[^']*+)*+'|[^'A-Za-z]++");
        caches = new ConcurrentMap[17];
        ABBREVIATED_YEAR_STRATEGY = new AnonymousClass1(1);
        NUMBER_MONTH_STRATEGY = new AnonymousClass2(2);
        LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
        WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
        WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
        DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
        DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
        DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
        HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
        MODULO_HOUR_OF_DAY_STRATEGY = new AnonymousClass3(11);
        MODULO_HOUR_STRATEGY = new AnonymousClass4(10);
        HOUR_STRATEGY = new NumberStrategy(10);
        MINUTE_STRATEGY = new NumberStrategy(12);
        SECOND_STRATEGY = new NumberStrategy(13);
        MILLISECOND_STRATEGY = new NumberStrategy(14);
    }

    public FastDateParser(String r2, TimeZone r3, Locale r4) {
        this(r2, r3, r4, null);
    }

    public FastDateParser(String r1, TimeZone r2, Locale r3, Date r4) {
        this.pattern = r1;
        this.timeZone = r2;
        this.locale = r3;
        Calendar r12 = Calendar.getInstance(r2, r3);
        if (r4 == null) goto L6;
        r12.setTime(r4);
        int r22 = r12.get(1);
    L9:
        int r32 = (r22 / 100) * 100;
        this.century = r32;
        this.startYear = r22 - r32;
        init(r12);
        return;
    L6:
        if (r3.equals(JAPANESE_IMPERIAL) == false) goto L8;
        r22 = 0;
        goto L9
    L8:
        r12.setTime(new Date());
        r22 = r12.get(1) - 80;
        goto L9
    }

    private int adjustYear(int r2) {
        int r0 = this.century + r2;
        if (r2 < this.startYear) goto L6;
        return r0;
    L6:
        return r0 + 100;
    }

    private static StringBuilder escapeRegex(StringBuilder r3, String r4, boolean r5) {
        r3.append("\\Q");
        int r0 = 0;
    L4:
        if (r0 >= r4.length()) goto L22;
        char r1 = r4.charAt(r0);
        if (r1 != '\'') goto L8;
        if (r5 == false) goto L21;
        r0 = r0 + 1;
        if (r0 == r4.length()) goto L19;
        r1 = r4.charAt(r0);
        goto L21
    L19:
        return r3;
    L21:
        r3.append(r1);
        r0 = r0 + 1;
        goto L4
    L8:
        if (r1 != '\\') goto L21;
        r0 = r0 + 1;
        if (r0 == r4.length()) goto L21;
        r3.append(r1);
        r1 = r4.charAt(r0);
        if (r1 != 'E') goto L21;
        r3.append("E\\\\E\\");
        r1 = 'Q';
        goto L21
    L22:
        r3.append("\\E");
        return r3;
    }

    private static ConcurrentMap<Locale, Strategy> getCache(int r3) {
        ConcurrentMap<Locale, Strategy>[] r0 = caches;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0[r3] != null) goto L9;
        r0[r3] = new ConcurrentHashMap(3);     // Catch: Throwable -> L7
    L9:
        ConcurrentMap<Locale, Strategy> r32 = r0[r3];     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r32;
    }

    private static String[] getDisplayNameArray(int r1, boolean r2, Locale r3) {
        DateFormatSymbols r0 = new DateFormatSymbols(r3);
        if (r1 == 0) goto L25;
        if (r1 != 2) goto L7;
        if (r2 == false) goto L23;
        return r0.getMonths();
    L23:
        return r0.getShortMonths();
    L7:
        if (r1 != 7) goto L9;
        if (r2 == false) goto L18;
        return r0.getWeekdays();
    L18:
        return r0.getShortWeekdays();
    L9:
        if (r1 == 9) goto L13;
        return null;
    L13:
        return r0.getAmPmStrings();
    L25:
        return r0.getEras();
    }

    private static Map<String, Integer> getDisplayNames(int r0, Calendar r1, Locale r2) {
        return getDisplayNames(r0, r2);
    }

    private static Map<String, Integer> getDisplayNames(int r2, Locale r3) {
        HashMap r0 = new HashMap();
        insertValuesInMap(r0, getDisplayNameArray(r2, false, r3));
        insertValuesInMap(r0, getDisplayNameArray(r2, true, r3));
        if (r0.isEmpty() == false) goto L6;
        return null;
    L6:
        return r0;
    }

    private Strategy getLocaleSpecificStrategy(int r4, Calendar r5) {
        ConcurrentMap<Locale, Strategy> r0 = getCache(r4);
        Strategy r1 = r0.get(this.locale);
        if (r1 != null) goto L12;
        Locale r12 = this.locale;
        if (r4 != 15) goto L7;
        Strategy r42 = new TimeZoneStrategy(r12);
    L8:
        Strategy r3 = r0.putIfAbsent(this.locale, r42);
        if (r3 == null) goto L11;
        return r3;
    L11:
        return r42;
    L7:
        r42 = new TextStrategy(r4, r5, r12);
        goto L8
    L12:
        return r1;
    }

    private Strategy getStrategy(String r5, Calendar r6) {
        char r1 = r5.charAt(0);
        if (r1 == 'y') goto L55;
        if (r1 == 'z') goto L53;
        switch(r1) {
            case 39: goto L47;
            case 83: goto L45;
            case 87: goto L43;
            case 90: goto L53;
            case 97: goto L41;
            case 100: goto L39;
            case 104: goto L37;
            case 107: goto L35;
            case 109: goto L33;
            case 115: goto L31;
            case 119: goto L29;
            default: goto L7;
        };
    L7:
        switch(r1) {
            case 68: goto L27;
            case 69: goto L25;
            case 70: goto L23;
            case 71: goto L21;
            case 72: goto L19;
            default: goto L8;
        };
    L8:
        switch(r1) {
            case 75: goto L17;
            case 76: goto L11;
            case 77: goto L11;
            default: goto L51;
        };
    L11:
        if (r5.length() < 3) goto L15;
        return getLocaleSpecificStrategy(2, r6);
    L15:
        return NUMBER_MONTH_STRATEGY;
    L17:
        return HOUR_STRATEGY;
    L51:
        return new CopyQuotedStrategy(r5);
    L19:
        return MODULO_HOUR_OF_DAY_STRATEGY;
    L21:
        return getLocaleSpecificStrategy(0, r6);
    L23:
        return DAY_OF_WEEK_IN_MONTH_STRATEGY;
    L25:
        return getLocaleSpecificStrategy(7, r6);
    L27:
        return DAY_OF_YEAR_STRATEGY;
    L29:
        return WEEK_OF_YEAR_STRATEGY;
    L31:
        return SECOND_STRATEGY;
    L33:
        return MINUTE_STRATEGY;
    L35:
        return HOUR_OF_DAY_STRATEGY;
    L37:
        return MODULO_HOUR_STRATEGY;
    L39:
        return DAY_OF_MONTH_STRATEGY;
    L41:
        return getLocaleSpecificStrategy(9, r6);
    L43:
        return WEEK_OF_MONTH_STRATEGY;
    L45:
        return MILLISECOND_STRATEGY;
    L47:
        if (r5.length() <= 2) goto L51;
        return new CopyQuotedStrategy(r5.substring(1, r5.length() - 1));
    L53:
        return getLocaleSpecificStrategy(15, r6);
    L55:
        if (r5.length() <= 2) goto L59;
        return LITERAL_YEAR_STRATEGY;
    L59:
        return ABBREVIATED_YEAR_STRATEGY;
    }

    private void init(Calendar r7) {
        StringBuilder r0 = new StringBuilder();
        ArrayList r1 = new ArrayList();
        Matcher r2 = formatPattern.matcher(this.pattern);
        if (r2.lookingAt() == false) goto L21;
        String r3 = r2.group();
        this.currentFormatField = r3;
        Strategy r32 = getStrategy(r3, r7);
    L5:
        r2.region(r2.end(), r2.regionEnd());
        if (r2.lookingAt() == false) goto L7;
        String r4 = r2.group();
        this.nextStrategy = getStrategy(r4, r7);
        if (r32.addRegex(this, r0) == false) goto L19;
        r1.add(r32);
    L19:
        this.currentFormatField = r4;
        r32 = this.nextStrategy;
        goto L5
    L7:
        this.nextStrategy = null;
        if (r2.regionStart() != r2.regionEnd()) goto L15;
        if (r32.addRegex(this, r0) == false) goto L12;
        r1.add(r32);
    L12:
        this.currentFormatField = null;
        this.strategies = (Strategy[]) r1.toArray(new Strategy[r1.size()]);
        this.parsePattern = Pattern.compile(r0.toString());
        return;
    L15:
        throw new IllegalArgumentException("Failed to parse \"" + this.pattern + "\" ; gave up at index " + r2.regionStart());
    L21:
        throw new IllegalArgumentException("Illegal pattern character '" + this.pattern.charAt(r2.regionStart()) + "'");
    }

    private static void insertValuesInMap(Map<String, Integer> r3, String[] r4) {
        if (r4 == null) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= r4.length) goto L18;
        String r1 = r4[r0];
        if (r1 == null) goto L12;
        if (r1.length() <= 0) goto L12;
        r3.put(r4[r0], Integer.valueOf(r0));
    L12:
        r0 = r0 + 1;
        goto L6
    L18:
        return;
    }

    private void readObject(ObjectInputStream r2) {
        r2.defaultReadObject();
        init(Calendar.getInstance(this.timeZone, this.locale));
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof FastDateParser) == true) goto L5;
        return false;
    L5:
        FastDateParser r42 = (FastDateParser) r4;
        if (this.pattern.equals(r42.pattern) == true) goto L8;
    L13:
        return false;
    L8:
        if (this.timeZone.equals(r42.timeZone) == false) goto L13;
        if (this.locale.equals(r42.locale) == false) goto L13;
        return true;
    }

    public int getFieldWidth() {
        return this.currentFormatField.length();
    }

    @Override // org.telegram.messenger.time.DateParser, org.telegram.messenger.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    public Pattern getParsePattern() {
        return this.parsePattern;
    }

    @Override // org.telegram.messenger.time.DateParser, org.telegram.messenger.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    @Override // org.telegram.messenger.time.DateParser, org.telegram.messenger.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public int hashCode() {
        return this.pattern.hashCode() + ((this.timeZone.hashCode() + (this.locale.hashCode() * 13)) * 13);
    }

    public boolean isNextNumber() {
        Strategy r0 = this.nextStrategy;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isNumber() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.messenger.time.DateParser
    public Date parse(String r6) {
        Date r0 = parse(r6, new ParsePosition(0));
        if (r0 == null) goto L5;
        return r0;
    L5:
        if (this.locale.equals(JAPANESE_IMPERIAL) == false) goto L9;
        StringBuilder r3 = new StringBuilder("(The ");
        r3.append(this.locale);
        String r5 = this.parsePattern.pattern();
        r3.append(" locale does not support dates before 1868 AD)\nUnparseable date: \"");
        r3.append(r6);
        r3.append("\" does not match ");
        r3.append(r5);
        throw new ParseException(r3.toString(), 0);
    L9:
        throw new ParseException("Unparseable date: \"" + r6 + "\" does not match " + this.parsePattern.pattern(), 0);
    }

    @Override // org.telegram.messenger.time.DateParser
    public Date parse(String r6, ParsePosition r7) {
        int r0 = r7.getIndex();
        Matcher r62 = this.parsePattern.matcher(r6.substring(r0));
        if (r62.lookingAt() == true) goto L6;
        return null;
    L6:
        Calendar r1 = Calendar.getInstance(this.timeZone, this.locale);
        r1.clear();
        int r2 = 0;
    L7:
        Strategy[] r3 = this.strategies;
        if (r2 >= r3.length) goto L10;
        int r4 = r2 + 1;
        r3[r2].setCalendar(this, r1, r62.group(r4));
        r2 = r4;
        goto L7
    L10:
        r7.setIndex(r0 + r62.end());
        return r1.getTime();
    }

    @Override // org.telegram.messenger.time.DateParser
    public Object parseObject(String r1) {
        return parse(r1);
    }

    @Override // org.telegram.messenger.time.DateParser
    public Object parseObject(String r1, ParsePosition r2) {
        return parse(r1, r2);
    }

    public String toString() {
        return "FastDateParser[" + this.pattern + "," + this.locale + "," + this.timeZone.getID() + "]";
    }
}
