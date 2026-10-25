package j$.time.format;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class c implements e {

    /* renamed from: a, reason: collision with root package name */
    public final char f380a;

    public c(char r1) {
        this.f380a = r1;
    }

    @Override // j$.time.format.e
    public final int D(r r2, CharSequence r3, int r4) {
        if (r4 == r3.length()) goto L5;
        char r32 = r3.charAt(r4);
        char r1 = this.f380a;
        if (r32 == r1) goto L17;
        if (r2.f404b == true) goto L15;
        if (Character.toUpperCase(r32) == Character.toUpperCase(r1)) goto L17;
        if (Character.toLowerCase(r32) == Character.toLowerCase(r1)) goto L17;
    L15:
        return ~r4;
    L17:
        return r4 + 1;
    L5:
        return ~r4;
    }

    @Override // j$.time.format.e
    public final boolean s(t r1, StringBuilder r2) {
        r2.append(this.f380a);
        return true;
    }

    public final String toString() {
        char r2 = this.f380a;
        if (r2 != '\'') goto L7;
        return "''";
    L7:
        return "'" + r2 + "'";
    }
}
