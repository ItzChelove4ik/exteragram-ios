package j$.time.format;

import java.text.ParsePosition;
import kotlin.jvm.internal.CharCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    public String f389a;

    /* renamed from: b, reason: collision with root package name */
    public String f390b;

    /* renamed from: c, reason: collision with root package name */
    public final char f391c;
    public k d;
    public k e;

    public k(String r1, String r2, k r3) {
        this.f389a = r1;
        this.f390b = r2;
        this.d = r3;
        if (r1.isEmpty() == false) goto L6;
        this.f391c = CharCompanionObject.MAX_VALUE;
        return;
    L6:
        this.f391c = this.f389a.charAt(0);
    }

    public final boolean a(String r8, String r9) {
        int r1 = 0;
    L4:
        if (r1 >= r8.length()) goto L12;
        if (r1 >= this.f389a.length()) goto L12;
        if (b(r8.charAt(r1), this.f389a.charAt(r1)) == false) goto L12;
        r1 = r1 + 1;
    L12:
        if (r1 == this.f389a.length()) goto L14;
        k r2 = d(this.f389a.substring(r1), this.f390b, this.d);
        this.f389a = r8.substring(0, r1);
        this.d = r2;
        if (r1 >= r8.length()) goto L30;
        this.d.e = d(r8.substring(r1), r9, null);
        this.f390b = null;
        return true;
    L30:
        this.f390b = r9;
        return true;
    L14:
        if (r1 >= r8.length()) goto L24;
        String r82 = r8.substring(r1);
        k r12 = this.d;
    L16:
        if (r12 == null) goto L22;
        if (b(r12.f391c, r82.charAt(0)) == true) goto L20;
        r12 = r12.e;
        goto L16
    L20:
        return r12.a(r82, r9);
    L22:
        k r83 = d(r82, r9, null);
        r83.e = this.d;
        this.d = r83;
        return true;
    L24:
        this.f390b = r9;
        return true;
    }

    public boolean b(char r1, char r2) {
        if (r1 != r2) goto L5;
        return true;
    L5:
        return false;
    }

    public final String c(CharSequence r5, ParsePosition r6) {
        int r0 = r6.getIndex();
        int r1 = r5.length();
        if (e(r5, r0, r1) == true) goto L6;
        return null;
    L6:
        int r2 = this.f389a.length() + r0;
        k r02 = this.d;
        if (r02 == null) goto L16;
        if (r2 == r1) goto L16;
    L10:
        if (b(r02.f391c, r5.charAt(r2)) == true) goto L11;
        r02 = r02.e;
        if (r02 != null) goto L10;
    L11:
        r6.setIndex(r2);
        String r52 = r02.c(r5, r6);
        if (r52 == null) goto L16;
        return r52;
    L16:
        r6.setIndex(r2);
        return this.f390b;
    }

    public k d(String r1, String r2, k r3) {
        return new k(r1, r2, r3);
    }

    public boolean e(CharSequence r5, int r6, int r7) {
        boolean r0 = r5 instanceof String;
        String r1 = this.f389a;
        if (r0 == true) goto L5;
        int r02 = r1.length();
        if (r02 <= (r7 - r6)) goto L9;
        return false;
    L9:
        int r72 = 0;
    L10:
        int r2 = r02 - 1;
        if (r02 <= 0) goto L16;
        int r3 = r72 + 1;
        int r03 = r6 + 1;
        if (b(this.f389a.charAt(r72), r5.charAt(r6)) == false) goto L14;
        r6 = r03;
        r02 = r2;
        r72 = r3;
        goto L10
    L14:
        return false;
    L16:
        return true;
    L5:
        return ((String) r5).startsWith(r1, r6);
    }
}
