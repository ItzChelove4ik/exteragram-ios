package j$.time.format;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class m implements e {

    /* renamed from: a, reason: collision with root package name */
    public final String f393a;

    public m(String r1) {
        this.f393a = r1;
    }

    @Override // j$.time.format.e
    public final int D(r r8, CharSequence r9, int r10) {
        if (r10 > r9.length()) goto L12;
        if (r10 < 0) goto L12;
        String r4 = this.f393a;
        if (r8.g(r9, r10, r4, 0, r4.length()) == true) goto L10;
        return ~r10;
    L10:
        return r4.length() + r10;
    L12:
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.time.format.e
    public final boolean s(t r1, StringBuilder r2) {
        r2.append(this.f393a);
        return true;
    }

    public final String toString() {
        return "'" + this.f393a.replace("'", "''") + "'";
    }
}
