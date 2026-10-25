package org.telegram.messenger.time;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes.dex */
public class FastDatePrinter implements DatePrinter, Serializable {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = null;
    private static final long serialVersionUID = 1;
    private final Locale mLocale;
    private transient int mMaxLengthEstimate;
    private final String mPattern;
    private transient Rule[] mRules;
    private final TimeZone mTimeZone;

    public static class CharacterLiteral implements Rule {
        private final char mValue;

        public CharacterLiteral(char r1) {
            this.mValue = r1;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r1, Calendar r2) {
            r1.append(this.mValue);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 1;
        }
    }

    public interface NumberRule extends Rule {
        void appendTo(StringBuffer r1, int r2);
    }

    /* loaded from: classes3.dex */
    public static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        public PaddedNumberField(int r2, int r3) {
            if (r3 < 3) goto L6;
            this.mField = r2;
            this.mSize = r3;
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline0.m();
            throw null;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer r3, int r4) {
            if (r4 >= 100) goto L11;
            int r2 = this.mSize;
        L5:
            r2 = r2 - 1;
            if (r2 < 2) goto L8;
            r3.append('0');
            goto L5
        L8:
            r3.append((char) ((r4 / 10) + 48));
            r3.append((char) ((r4 % 10) + 48));
            return;
        L11:
            if (r4 >= 1000) goto L13;
            int r0 = 3;
        L14:
            int r22 = this.mSize;
        L15:
            r22 = r22 - 1;
            if (r22 < r0) goto L18;
            r3.append('0');
            goto L15
        L18:
            r3.append(Integer.toString(r4));
            return;
        L13:
            r0 = Integer.toString(r4).length();
            goto L14
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r2, Calendar r3) {
            appendTo(r2, r3.get(this.mField));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }
    }

    public interface Rule {
        void appendTo(StringBuffer r1, Calendar r2);

        int estimateLength();
    }

    /* loaded from: classes3.dex */
    public static class StringLiteral implements Rule {
        private final String mValue;

        public StringLiteral(String r1) {
            this.mValue = r1;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r1, Calendar r2) {
            r1.append(this.mValue);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mValue.length();
        }
    }

    public static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        public TextField(int r1, String[] r2) {
            this.mField = r1;
            this.mValues = r2;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r2, Calendar r3) {
            r2.append(this.mValues[r3.get(this.mField)]);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            int r0 = this.mValues.length;
            int r1 = 0;
        L3:
            r0 = r0 - 1;
            if (r0 < 0) goto L8;
            int r2 = this.mValues[r0].length();
            if (r2 <= r1) goto L3;
            r1 = r2;
            goto L3
        L8:
            return r1;
        }
    }

    /* loaded from: classes3.dex */
    public static class TimeZoneDisplayKey {
        private final Locale mLocale;
        private final int mStyle;
        private final TimeZone mTimeZone;

        public TimeZoneDisplayKey(TimeZone r1, boolean r2, int r3, Locale r4) {
            this.mTimeZone = r1;
            if (r2 == false) goto L5;
            this.mStyle = Integer.MIN_VALUE | r3;
        L6:
            this.mLocale = r4;
            return;
        L5:
            this.mStyle = r3;
            goto L6
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof TimeZoneDisplayKey) == false) goto L14;
            TimeZoneDisplayKey r52 = (TimeZoneDisplayKey) r5;
            if (this.mTimeZone.equals(r52.mTimeZone) == false) goto L14;
            if (this.mStyle != r52.mStyle) goto L14;
            if (this.mLocale.equals(r52.mLocale) == false) goto L14;
            return true;
        L14:
            return false;
        }

        public int hashCode() {
            return (((this.mStyle * 31) + this.mLocale.hashCode()) * 31) + this.mTimeZone.hashCode();
        }
    }

    /* loaded from: classes3.dex */
    public static class TimeZoneNameRule implements Rule {
        private final String mDaylight;
        private final Locale mLocale;
        private final String mStandard;
        private final int mStyle;

        public TimeZoneNameRule(TimeZone r2, Locale r3, int r4) {
            this.mLocale = r3;
            this.mStyle = r4;
            this.mStandard = FastDatePrinter.getTimeZoneDisplay(r2, false, r4, r3);
            this.mDaylight = FastDatePrinter.getTimeZoneDisplay(r2, true, r4, r3);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r3, Calendar r4) {
            TimeZone r0 = r4.getTimeZone();
            if (r0.useDaylightTime() == true) goto L5;
        L8:
            r3.append(FastDatePrinter.getTimeZoneDisplay(r0, false, this.mStyle, this.mLocale));
            return;
        L5:
            if (r4.get(16) == 0) goto L8;
            r3.append(FastDatePrinter.getTimeZoneDisplay(r0, true, this.mStyle, this.mLocale));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return Math.max(this.mStandard.length(), this.mDaylight.length());
        }
    }

    /* loaded from: classes3.dex */
    public static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = null;
        static final TimeZoneNumberRule INSTANCE_NO_COLON = null;
        final boolean mColon;

        static {
            INSTANCE_COLON = new TimeZoneNumberRule(true);
            INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        }

        public TimeZoneNumberRule(boolean r1) {
            this.mColon = r1;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r3, Calendar r4) {
            int r0 = r4.get(15) + r4.get(16);
            if (r0 >= 0) goto L5;
            r3.append(SignatureVisitor.SUPER);
            r0 = -r0;
        L6:
            int r42 = r0 / 3600000;
            r3.append((char) ((r42 / 10) + 48));
            r3.append((char) ((r42 % 10) + 48));
            if (this.mColon == false) goto L9;
            r3.append(':');
        L9:
            int r02 = (r0 / 60000) - (r42 * 60);
            r3.append((char) ((r02 / 10) + 48));
            r3.append((char) ((r02 % 10) + 48));
            return;
        L5:
            r3.append(SignatureVisitor.EXTENDS);
            goto L6
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 5;
        }
    }

    /* loaded from: classes3.dex */
    public static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        public TwelveHourField(NumberRule r1) {
            this.mRule = r1;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public void appendTo(StringBuffer r1, int r2) {
            this.mRule.appendTo(r1, r2);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r3, Calendar r4) {
            int r1 = r4.get(10);
            if (r1 != 0) goto L5;
            r1 = r4.getLeastMaximum(10) + 1;
        L5:
            this.mRule.appendTo(r3, r1);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }
    }

    /* loaded from: classes3.dex */
    public static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        public TwentyFourHourField(NumberRule r1) {
            this.mRule = r1;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public void appendTo(StringBuffer r1, int r2) {
            this.mRule.appendTo(r1, r2);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r3, Calendar r4) {
            int r1 = r4.get(11);
            if (r1 != 0) goto L5;
            r1 = r4.getMaximum(11) + 1;
        L5:
            this.mRule.appendTo(r3, r1);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }
    }

    /* loaded from: classes3.dex */
    public static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = null;

        static {
            INSTANCE = new TwoDigitMonthField();
        }

        public TwoDigitMonthField() {
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer r1, int r2) {
            r1.append((char) ((r2 / 10) + 48));
            r1.append((char) ((r2 % 10) + 48));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r2, Calendar r3) {
            appendTo(r2, r3.get(2) + 1);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    public static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        public TwoDigitNumberField(int r1) {
            this.mField = r1;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer r1, int r2) {
            if (r2 >= 100) goto L6;
            r1.append((char) ((r2 / 10) + 48));
            r1.append((char) ((r2 % 10) + 48));
            return;
        L6:
            r1.append(Integer.toString(r2));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r2, Calendar r3) {
            appendTo(r2, r3.get(this.mField));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    /* loaded from: classes3.dex */
    public static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = null;

        static {
            INSTANCE = new TwoDigitYearField();
        }

        public TwoDigitYearField() {
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer r1, int r2) {
            r1.append((char) ((r2 / 10) + 48));
            r1.append((char) ((r2 % 10) + 48));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r2, Calendar r3) {
            appendTo(r2, r3.get(1) % 100);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    /* loaded from: classes3.dex */
    public static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = null;

        static {
            INSTANCE = new UnpaddedMonthField();
        }

        public UnpaddedMonthField() {
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer r2, int r3) {
            if (r3 >= 10) goto L6;
            r2.append((char) (r3 + 48));
            return;
        L6:
            r2.append((char) ((r3 / 10) + 48));
            r2.append((char) ((r3 % 10) + 48));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r2, Calendar r3) {
            appendTo(r2, r3.get(2) + 1);
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    /* loaded from: classes3.dex */
    public static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        public UnpaddedNumberField(int r1) {
            this.mField = r1;
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer r2, int r3) {
            if (r3 >= 10) goto L7;
            r2.append((char) (r3 + 48));
            return;
        L7:
            if (r3 >= 100) goto L10;
            r2.append((char) ((r3 / 10) + 48));
            r2.append((char) ((r3 % 10) + 48));
            return;
        L10:
            r2.append(Integer.toString(r3));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer r2, Calendar r3) {
            appendTo(r2, r3.get(this.mField));
        }

        @Override // org.telegram.messenger.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }
    }

    static {
        cTimeZoneDisplayCache = new ConcurrentHashMap(7);
    }

    public FastDatePrinter(String r1, TimeZone r2, Locale r3) {
        this.mPattern = r1;
        this.mTimeZone = r2;
        this.mLocale = r3;
        init();
    }

    private String applyRulesToString(Calendar r3) {
        return applyRules(r3, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public static String getTimeZoneDisplay(TimeZone r3, boolean r4, int r5, Locale r6) {
        TimeZoneDisplayKey r0 = new TimeZoneDisplayKey(r3, r4, r5, r6);
        ConcurrentMap<TimeZoneDisplayKey, String> r1 = cTimeZoneDisplayCache;
        String r2 = r1.get(r0);
        if (r2 != null) goto L8;
        String r32 = r3.getDisplayName(r4, r5, r6);
        String r42 = r1.putIfAbsent(r0, r32);
        if (r42 == null) goto L7;
        return r42;
    L7:
        return r32;
    L8:
        return r2;
    }

    private void init() {
        List<Rule> r0 = parsePattern();
        Rule[] r02 = (Rule[]) r0.toArray(new Rule[r0.size()]);
        this.mRules = r02;
        int r03 = r02.length;
        int r1 = 0;
    L3:
        r03 = r03 - 1;
        if (r03 < 0) goto L6;
        r1 = r1 + this.mRules[r03].estimateLength();
        goto L3
    L6:
        this.mMaxLengthEstimate = r1;
    }

    private GregorianCalendar newCalendar() {
        return new GregorianCalendar(this.mTimeZone, this.mLocale);
    }

    private void readObject(ObjectInputStream r1) {
        r1.defaultReadObject();
        init();
    }

    public StringBuffer applyRules(Calendar r4, StringBuffer r5) {
        Rule[] r3 = this.mRules;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].appendTo(r5, r4);
        r1 = r1 + 1;
        goto L3
    L5:
        return r5;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof FastDatePrinter) == true) goto L5;
        return false;
    L5:
        FastDatePrinter r42 = (FastDatePrinter) r4;
        if (this.mPattern.equals(r42.mPattern) == true) goto L8;
    L13:
        return false;
    L8:
        if (this.mTimeZone.equals(r42.mTimeZone) == false) goto L13;
        if (this.mLocale.equals(r42.mLocale) == false) goto L13;
        return true;
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public String format(long r2) {
        GregorianCalendar r0 = newCalendar();
        r0.setTimeInMillis(r2);
        return applyRulesToString(r0);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public String format(Calendar r3) {
        return format(r3, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public String format(Date r2) {
        GregorianCalendar r0 = newCalendar();
        r0.setTime(r2);
        return applyRulesToString(r0);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public StringBuffer format(long r2, StringBuffer r4) {
        return format(new Date(r2), r4);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public StringBuffer format(Object r3, StringBuffer r4, FieldPosition r5) {
        if ((r3 instanceof Date) == false) goto L7;
        return format((Date) r3, r4);
    L7:
        if ((r3 instanceof Calendar) == false) goto L11;
        return format((Calendar) r3, r4);
    L11:
        if ((r3 instanceof Long) == false) goto L15;
        return format(((Long) r3).longValue(), r4);
    L15:
        if (r3 != null) goto L17;
        String r32 = "<null>";
    L19:
        throw new IllegalArgumentException("Unknown class: ".concat(r32));
    L17:
        r32 = r3.getClass().getName();
        goto L19
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public StringBuffer format(Calendar r1, StringBuffer r2) {
        return applyRules(r1, r2);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public StringBuffer format(Date r2, StringBuffer r3) {
        GregorianCalendar r0 = newCalendar();
        r0.setTime(r2);
        return applyRules(r0, r3);
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public String getPattern() {
        return this.mPattern;
    }

    @Override // org.telegram.messenger.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public int hashCode() {
        return this.mPattern.hashCode() + ((this.mTimeZone.hashCode() + (this.mLocale.hashCode() * 13)) * 13);
    }

    public List<Rule> parsePattern() {
        DateFormatSymbols r1 = new DateFormatSymbols(this.mLocale);
        ArrayList r2 = new ArrayList();
        String[] r3 = r1.getEras();
        String[] r4 = r1.getMonths();
        String[] r5 = r1.getShortMonths();
        String[] r6 = r1.getWeekdays();
        String[] r7 = r1.getShortWeekdays();
        String[] r12 = r1.getAmPmStrings();
        int r8 = this.mPattern.length();
        int r9 = 0;
        int r10 = 0;
    L3:
        if (r10 >= r8) goto L74;
        int[] r102 = {r10};
        String r11 = parseToken(this.mPattern, r102);
        int r103 = r102[r9];
        int r122 = r11.length();
        if (r122 == 0) goto L74;
        char r13 = r11.charAt(r9);
        if (r13 != 'y') goto L10;
        int r132 = 0;
        if (r122 != 2) goto L70;
        Rule r92 = TwoDigitYearField.INSTANCE;
    L37:
        Rule r112 = r92;
        int r93 = 1;
    L73:
        r2.add(r112);
        r10 = r103 + r93;
        r9 = r132;
        goto L3
    L70:
        if (r122 >= 4) goto L72;
        r122 = 4;
    L72:
        r93 = 1;
        r112 = selectNumberRule(1, r122);
        goto L73
    L10:
        if (r13 != 'z') goto L12;
        TimeZone r113 = this.mTimeZone;
        if (r122 < 4) goto L66;
        int r133 = 1;
        Rule r94 = new TimeZoneNameRule(r113, this.mLocale, 1);
    L55:
        r112 = r94;
        r93 = r133;
    L21:
        r132 = 0;
        goto L73
    L66:
        r132 = 0;
        r92 = new TimeZoneNameRule(r113, this.mLocale, 0);
        goto L37
    L12:
        switch(r13) {
            case 39: goto L59;
            case 83: goto L58;
            case 87: goto L57;
            case 90: goto L52;
            case 97: goto L51;
            case 100: goto L50;
            case 104: goto L49;
            case 107: goto L48;
            case 109: goto L47;
            case 115: goto L46;
            case 119: goto L45;
            default: goto L13;
        };
    L13:
        switch(r13) {
            case 68: goto L44;
            case 69: goto L40;
            case 70: goto L38;
            case 71: goto L36;
            case 72: goto L35;
            default: goto L14;
        };
    L14:
        switch(r13) {
            case 75: goto L34;
            case 76: goto L27;
            case 77: goto L17;
            default: goto L15;
        };
    L17:
        if (r122 < 4) goto L22;
        Rule r95 = new TextField(2, r4);
    L19:
        r112 = r95;
    L20:
        r93 = 1;
        goto L21
    L22:
        if (r122 != 3) goto L24;
        r95 = new TextField(2, r5);
        goto L19
    L24:
        if (r122 != 2) goto L26;
        r95 = TwoDigitMonthField.INSTANCE;
        goto L19
    L26:
        r95 = UnpaddedMonthField.INSTANCE;
        goto L19
    L27:
        if (r122 < 4) goto L29;
        r95 = new TextField(2, r4);
        goto L19
    L29:
        if (r122 != 3) goto L31;
        r95 = new TextField(2, r5);
        goto L19
    L31:
        if (r122 != 2) goto L33;
        r95 = TwoDigitMonthField.INSTANCE;
        goto L19
    L33:
        r95 = UnpaddedMonthField.INSTANCE;
        goto L19
    L34:
        r95 = selectNumberRule(10, r122);
        goto L19
    L15:
        f$$ExternalSyntheticBUOutline1.m("Illegal pattern component: ".concat(r11));
        return null;
    L35:
        r95 = selectNumberRule(11, r122);
        goto L19
    L36:
        r92 = new TextField(0, r3);
        r132 = 0;
        goto L37
    L38:
        r95 = selectNumberRule(8, r122);
        goto L19
    L44:
        r95 = selectNumberRule(6, r122);
        goto L19
    L40:
        if (r122 >= 4) goto L42;
        String[] r96 = r7;
    L43:
        r112 = new TextField(7, r96);
        goto L20
    L42:
        r96 = r6;
        goto L43
    L45:
        r95 = selectNumberRule(3, r122);
        goto L19
    L46:
        r95 = selectNumberRule(13, r122);
        goto L19
    L47:
        r95 = selectNumberRule(12, r122);
        goto L19
    L48:
        r95 = new TwentyFourHourField(selectNumberRule(11, r122));
        goto L19
    L49:
        r95 = new TwelveHourField(selectNumberRule(10, r122));
        goto L19
    L50:
        r95 = selectNumberRule(5, r122);
        goto L19
    L51:
        r95 = new TextField(9, r12);
        goto L19
    L52:
        r133 = 1;
        if (r122 != 1) goto L56;
        r94 = TimeZoneNumberRule.INSTANCE_NO_COLON;
        goto L55
    L56:
        r94 = TimeZoneNumberRule.INSTANCE_COLON;
        goto L55
    L57:
        r133 = 1;
        r94 = selectNumberRule(4, r122);
        goto L55
    L58:
        r133 = 1;
        r94 = selectNumberRule(14, r122);
        goto L55
    L59:
        String r97 = r11.substring(1);
        if (r97.length() != 1) goto L62;
        r112 = new CharacterLiteral(r97.charAt(0));
        goto L20
    L62:
        r112 = new StringLiteral(r97);
    L74:
        return r2;
    }

    public String parseToken(String r13, int[] r14) {
        StringBuilder r12 = new StringBuilder();
        int r1 = r14[0];
        int r2 = r13.length();
        char r3 = r13.charAt(r1);
        if (r3 < 'A') goto L6;
        if (r3 > 'Z') goto L6;
    L8:
        r12.append(r3);
    L9:
        int r4 = r1 + 1;
        if (r4 >= r2) goto L32;
        if (r13.charAt(r4) != r3) goto L32;
        r12.append(r3);
        r1 = r4;
    L32:
        r14[0] = r1;
        return r12.toString();
    L6:
        if (r3 < 'a') goto L14;
        if (r3 <= 'z') goto L8;
    L14:
        r12.append('\'');
        boolean r8 = false;
    L15:
        if (r1 >= r2) goto L32;
        char r9 = r13.charAt(r1);
        if (r9 != '\'') goto L24;
        int r10 = r1 + 1;
        if (r10 < r2) goto L21;
    L23:
        r8 = !r8;
    L31:
        r1 = r1 + 1;
        goto L15
    L21:
        if (r13.charAt(r10) != '\'') goto L23;
        r12.append(r9);
        r1 = r10;
        goto L31
    L24:
        if (r8 == true) goto L30;
        if (r9 < 'A') goto L27;
        if (r9 > 'Z') goto L27;
    L29:
        r1 = r1 - 1;
    L27:
        if (r9 < 'a') goto L30;
        if (r9 <= 'z') goto L29;
    L30:
        r12.append(r9);
        goto L31
    }

    public NumberRule selectNumberRule(int r1, int r2) {
        if (r2 == 1) goto L11;
        if (r2 == 2) goto L9;
        return new PaddedNumberField(r1, r2);
    L9:
        return new TwoDigitNumberField(r1);
    L11:
        return new UnpaddedNumberField(r1);
    }

    public String toString() {
        return "FastDatePrinter[" + this.mPattern + "," + this.mLocale + "," + this.mTimeZone.getID() + "]";
    }
}
