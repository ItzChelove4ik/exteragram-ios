package kotlin.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 .2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001.B\u0019\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0086\u0082\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)¨\u0006/"}, d2 = {"Lkotlin/text/CharCategory;", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "code", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getCode", "()Ljava/lang/String;", "UNASSIGNED", "UPPERCASE_LETTER", "LOWERCASE_LETTER", "TITLECASE_LETTER", "MODIFIER_LETTER", "OTHER_LETTER", "NON_SPACING_MARK", "ENCLOSING_MARK", "COMBINING_SPACING_MARK", "DECIMAL_DIGIT_NUMBER", "LETTER_NUMBER", "OTHER_NUMBER", "SPACE_SEPARATOR", "LINE_SEPARATOR", "PARAGRAPH_SEPARATOR", "CONTROL", "FORMAT", "PRIVATE_USE", "SURROGATE", "DASH_PUNCTUATION", "START_PUNCTUATION", "END_PUNCTUATION", "CONNECTOR_PUNCTUATION", "OTHER_PUNCTUATION", "MATH_SYMBOL", "CURRENCY_SYMBOL", "MODIFIER_SYMBOL", "OTHER_SYMBOL", "INITIAL_QUOTE_PUNCTUATION", "FINAL_QUOTE_PUNCTUATION", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "char", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum CharCategory extends Enum<CharCategory> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ CharCategory[] $VALUES = null;
    public static final CharCategory COMBINING_SPACING_MARK = null;
    public static final CharCategory CONNECTOR_PUNCTUATION = null;
    public static final CharCategory CONTROL = null;
    public static final CharCategory CURRENCY_SYMBOL = null;
    public static final Companion Companion = null;
    public static final CharCategory DASH_PUNCTUATION = null;
    public static final CharCategory DECIMAL_DIGIT_NUMBER = null;
    public static final CharCategory ENCLOSING_MARK = null;
    public static final CharCategory END_PUNCTUATION = null;
    public static final CharCategory FINAL_QUOTE_PUNCTUATION = null;
    public static final CharCategory FORMAT = null;
    public static final CharCategory INITIAL_QUOTE_PUNCTUATION = null;
    public static final CharCategory LETTER_NUMBER = null;
    public static final CharCategory LINE_SEPARATOR = null;
    public static final CharCategory LOWERCASE_LETTER = null;
    public static final CharCategory MATH_SYMBOL = null;
    public static final CharCategory MODIFIER_LETTER = null;
    public static final CharCategory MODIFIER_SYMBOL = null;
    public static final CharCategory NON_SPACING_MARK = null;
    public static final CharCategory OTHER_LETTER = null;
    public static final CharCategory OTHER_NUMBER = null;
    public static final CharCategory OTHER_PUNCTUATION = null;
    public static final CharCategory OTHER_SYMBOL = null;
    public static final CharCategory PARAGRAPH_SEPARATOR = null;
    public static final CharCategory PRIVATE_USE = null;
    public static final CharCategory SPACE_SEPARATOR = null;
    public static final CharCategory START_PUNCTUATION = null;
    public static final CharCategory SURROGATE = null;
    public static final CharCategory TITLECASE_LETTER = null;
    public static final CharCategory UNASSIGNED = null;
    public static final CharCategory UPPERCASE_LETTER = null;
    private final String code;
    private final int value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004¨\u0006\b"}, d2 = {"Lkotlin/text/CharCategory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "valueOf", "Lkotlin/text/CharCategory;", "category", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CharCategory valueOf(int r2) {
            if (r2 < 0) goto L8;
            if (r2 >= 17) goto L8;
            return CharCategory.getEntries().get(r2);
        L8:
            if (18 <= r2) goto L10;
        L13:
            CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Category #", r2, " is not defined.");
            return null;
        L10:
            if (r2 >= 31) goto L13;
            return CharCategory.getEntries().get(r2 - 1);
        }
    }

    private static final /* synthetic */ CharCategory[] $values() {
        return new CharCategory[]{UNASSIGNED, UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER, MODIFIER_LETTER, OTHER_LETTER, NON_SPACING_MARK, ENCLOSING_MARK, COMBINING_SPACING_MARK, DECIMAL_DIGIT_NUMBER, LETTER_NUMBER, OTHER_NUMBER, SPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATOR, CONTROL, FORMAT, PRIVATE_USE, SURROGATE, DASH_PUNCTUATION, START_PUNCTUATION, END_PUNCTUATION, CONNECTOR_PUNCTUATION, OTHER_PUNCTUATION, MATH_SYMBOL, CURRENCY_SYMBOL, MODIFIER_SYMBOL, OTHER_SYMBOL, INITIAL_QUOTE_PUNCTUATION, FINAL_QUOTE_PUNCTUATION};
    }

    static {
        UNASSIGNED = new CharCategory("UNASSIGNED", 0, 0, "Cn");
        UPPERCASE_LETTER = new CharCategory("UPPERCASE_LETTER", 1, 1, "Lu");
        LOWERCASE_LETTER = new CharCategory("LOWERCASE_LETTER", 2, 2, "Ll");
        TITLECASE_LETTER = new CharCategory("TITLECASE_LETTER", 3, 3, "Lt");
        MODIFIER_LETTER = new CharCategory("MODIFIER_LETTER", 4, 4, "Lm");
        OTHER_LETTER = new CharCategory("OTHER_LETTER", 5, 5, "Lo");
        NON_SPACING_MARK = new CharCategory("NON_SPACING_MARK", 6, 6, "Mn");
        ENCLOSING_MARK = new CharCategory("ENCLOSING_MARK", 7, 7, "Me");
        COMBINING_SPACING_MARK = new CharCategory("COMBINING_SPACING_MARK", 8, 8, "Mc");
        DECIMAL_DIGIT_NUMBER = new CharCategory("DECIMAL_DIGIT_NUMBER", 9, 9, "Nd");
        LETTER_NUMBER = new CharCategory("LETTER_NUMBER", 10, 10, "Nl");
        OTHER_NUMBER = new CharCategory("OTHER_NUMBER", 11, 11, "No");
        SPACE_SEPARATOR = new CharCategory("SPACE_SEPARATOR", 12, 12, "Zs");
        LINE_SEPARATOR = new CharCategory("LINE_SEPARATOR", 13, 13, "Zl");
        PARAGRAPH_SEPARATOR = new CharCategory("PARAGRAPH_SEPARATOR", 14, 14, "Zp");
        CONTROL = new CharCategory("CONTROL", 15, 15, "Cc");
        FORMAT = new CharCategory("FORMAT", 16, 16, "Cf");
        PRIVATE_USE = new CharCategory("PRIVATE_USE", 17, 18, "Co");
        SURROGATE = new CharCategory("SURROGATE", 18, 19, "Cs");
        DASH_PUNCTUATION = new CharCategory("DASH_PUNCTUATION", 19, 20, "Pd");
        START_PUNCTUATION = new CharCategory("START_PUNCTUATION", 20, 21, "Ps");
        END_PUNCTUATION = new CharCategory("END_PUNCTUATION", 21, 22, "Pe");
        CONNECTOR_PUNCTUATION = new CharCategory("CONNECTOR_PUNCTUATION", 22, 23, "Pc");
        OTHER_PUNCTUATION = new CharCategory("OTHER_PUNCTUATION", 23, 24, "Po");
        MATH_SYMBOL = new CharCategory("MATH_SYMBOL", 24, 25, "Sm");
        CURRENCY_SYMBOL = new CharCategory("CURRENCY_SYMBOL", 25, 26, "Sc");
        MODIFIER_SYMBOL = new CharCategory("MODIFIER_SYMBOL", 26, 27, "Sk");
        OTHER_SYMBOL = new CharCategory("OTHER_SYMBOL", 27, 28, "So");
        INITIAL_QUOTE_PUNCTUATION = new CharCategory("INITIAL_QUOTE_PUNCTUATION", 28, 29, "Pi");
        FINAL_QUOTE_PUNCTUATION = new CharCategory("FINAL_QUOTE_PUNCTUATION", 29, 30, "Pf");
        CharCategory[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    CharCategory(String r1, int r2, int r3, String r4) {
        this.value = r3;
        this.code = r4;
    }

    public static EnumEntries<CharCategory> getEntries() {
        return $ENTRIES;
    }

    public static CharCategory valueOf(String r1) {
        return (CharCategory) Enum.valueOf(CharCategory.class, r1);
    }

    public static CharCategory[] values() {
        return (CharCategory[]) $VALUES.clone();
    }

    public final boolean contains(char r1) {
        if (Character.getType(r1) != this.value) goto L6;
        return true;
    L6:
        return false;
    }

    public final String getCode() {
        return this.code;
    }

    public final int getValue() {
        return this.value;
    }
}
