package j$.time.format;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum l extends Enum implements e {
    public static final l INSENSITIVE = null;
    public static final l LENIENT = null;
    public static final l SENSITIVE = null;
    public static final l STRICT = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ l[] f392a = null;

    static {
        l r0 = new l("SENSITIVE", 0);
        SENSITIVE = r0;
        l r1 = new l("INSENSITIVE", 1);
        INSENSITIVE = r1;
        l r2 = new l("STRICT", 2);
        STRICT = r2;
        l r3 = new l("LENIENT", 3);
        LENIENT = r3;
        f392a = new l[]{r0, r1, r2, r3};
    }

    public static l valueOf(String r1) {
        return (l) Enum.valueOf(l.class, r1);
    }

    public static l[] values() {
        return (l[]) f392a.clone();
    }

    @Override // j$.time.format.e
    public final int D(r r3, CharSequence r4, int r5) {
        int r2 = ordinal();
        if (r2 != 0) goto L5;
        r3.f404b = true;
        return r5;
    L5:
        if (r2 != 1) goto L7;
        r3.f404b = false;
        return r5;
    L7:
        if (r2 != 2) goto L9;
        r3.f405c = true;
        return r5;
    L9:
        if (r2 == 3) goto L11;
        return r5;
    L11:
        r3.f405c = false;
        return r5;
    }

    @Override // j$.time.format.e
    public final boolean s(t r1, StringBuilder r2) {
        return true;
    }

    @Override // java.lang.Enum
    public final String toString() {
        int r1 = ordinal();
        if (r1 != 0) goto L5;
        return "ParseCaseSensitive(true)";
    L5:
        if (r1 != 1) goto L7;
        return "ParseCaseSensitive(false)";
    L7:
        if (r1 != 2) goto L9;
        return "ParseStrict(true)";
    L9:
        if (r1 != 3) goto L13;
        return "ParseStrict(false)";
    L13:
        throw new IllegalStateException("Unreachable");
    }
}
