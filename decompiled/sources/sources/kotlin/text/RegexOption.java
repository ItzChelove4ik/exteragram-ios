package kotlin.text;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u001b\bB\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0003\u001a\u00020\u0004X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u00020\u0004X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lkotlin/text/RegexOption;", "Lkotlin/text/FlagEnum;", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.MASK, "<init>", "(Ljava/lang/String;III)V", "getValue", "()I", "getMask", "IGNORE_CASE", "MULTILINE", "LITERAL", "UNIX_LINES", "COMMENTS", "DOT_MATCHES_ALL", "CANON_EQ", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum RegexOption extends Enum<RegexOption> implements FlagEnum {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ RegexOption[] $VALUES = null;
    public static final RegexOption CANON_EQ = null;
    public static final RegexOption COMMENTS = null;
    public static final RegexOption DOT_MATCHES_ALL = null;
    public static final RegexOption IGNORE_CASE = null;
    public static final RegexOption LITERAL = null;
    public static final RegexOption MULTILINE = null;
    public static final RegexOption UNIX_LINES = null;
    private final int mask;
    private final int value;

    private static final /* synthetic */ RegexOption[] $values() {
        return new RegexOption[]{IGNORE_CASE, MULTILINE, LITERAL, UNIX_LINES, COMMENTS, DOT_MATCHES_ALL, CANON_EQ};
    }

    static {
        IGNORE_CASE = new RegexOption("IGNORE_CASE", 0, 2, 0, 2, null);
        MULTILINE = new RegexOption("MULTILINE", 1, 8, 0, 2, null);
        LITERAL = new RegexOption("LITERAL", 2, 16, 0, 2, null);
        UNIX_LINES = new RegexOption("UNIX_LINES", 3, 1, 0, 2, null);
        COMMENTS = new RegexOption("COMMENTS", 4, 4, 0, 2, null);
        DOT_MATCHES_ALL = new RegexOption("DOT_MATCHES_ALL", 5, 32, 0, 2, null);
        CANON_EQ = new RegexOption("CANON_EQ", 6, 128, 0, 2, null);
        RegexOption[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    RegexOption(String r1, int r2, int r3, int r4) {
        this.value = r3;
        this.mask = r4;
    }

    /* synthetic */ RegexOption(String r1, int r2, int r3, int r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 2) == 0) goto L5;
        r4 = r3;
    L5:
        this(r1, r2, r3, r4);
    }

    public static EnumEntries<RegexOption> getEntries() {
        return $ENTRIES;
    }

    public static RegexOption valueOf(String r1) {
        return (RegexOption) Enum.valueOf(RegexOption.class, r1);
    }

    public static RegexOption[] values() {
        return (RegexOption[]) $VALUES.clone();
    }

    @Override // kotlin.text.FlagEnum
    public int getMask() {
        return this.mask;
    }

    @Override // kotlin.text.FlagEnum
    public int getValue() {
        return this.value;
    }
}
