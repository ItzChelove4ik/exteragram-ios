package j$.time.format;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum TextStyle extends Enum<TextStyle> {
    public static final TextStyle FULL = null;
    public static final TextStyle FULL_STANDALONE = null;
    public static final TextStyle NARROW = null;
    public static final TextStyle NARROW_STANDALONE = null;
    public static final TextStyle SHORT = null;
    public static final TextStyle SHORT_STANDALONE = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ TextStyle[] f373b = null;

    /* renamed from: a, reason: collision with root package name */
    public final int f374a;

    static {
        TextStyle r0 = new TextStyle("FULL", 0, 0);
        FULL = r0;
        TextStyle r1 = new TextStyle("FULL_STANDALONE", 1, 0);
        FULL_STANDALONE = r1;
        TextStyle r2 = new TextStyle("SHORT", 2, 1);
        SHORT = r2;
        TextStyle r3 = new TextStyle("SHORT_STANDALONE", 3, 1);
        SHORT_STANDALONE = r3;
        TextStyle r4 = new TextStyle("NARROW", 4, 1);
        NARROW = r4;
        TextStyle r5 = new TextStyle("NARROW_STANDALONE", 5, 1);
        NARROW_STANDALONE = r5;
        f373b = new TextStyle[]{r0, r1, r2, r3, r4, r5};
    }

    TextStyle(String r1, int r2, int r3) {
        this.f374a = r3;
    }

    public static TextStyle valueOf(String r1) {
        return (TextStyle) Enum.valueOf(TextStyle.class, r1);
    }

    public static TextStyle[] values() {
        return (TextStyle[]) f373b.clone();
    }
}
