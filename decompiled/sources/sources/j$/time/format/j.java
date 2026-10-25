package j$.time.format;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class j extends k {
    @Override // j$.time.format.k
    public final boolean b(char r1, char r2) {
        return r.b(r1, r2);
    }

    @Override // j$.time.format.k
    public final k d(String r1, String r2, k r3) {
        return new j(r1, r2, r3);
    }

    @Override // j$.time.format.k
    public final boolean e(CharSequence r5, int r6, int r7) {
        int r0 = this.f389a.length();
        if (r0 <= (r7 - r6)) goto L5;
        return false;
    L5:
        int r72 = 0;
    L6:
        int r2 = r0 - 1;
        if (r0 <= 0) goto L12;
        int r3 = r72 + 1;
        int r02 = r6 + 1;
        if (r.b(this.f389a.charAt(r72), r5.charAt(r6)) == false) goto L10;
        r6 = r02;
        r0 = r2;
        r72 = r3;
        goto L6
    L10:
        return false;
    L12:
        return true;
    }
}
