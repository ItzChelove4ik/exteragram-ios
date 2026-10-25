package j$.time.format;

import j$.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public final DateTimeFormatter f403a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f404b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f405c;
    public final ArrayList d;

    public r(DateTimeFormatter r2) {
        this.f404b = true;
        this.f405c = true;
        ArrayList r0 = new ArrayList();
        this.d = r0;
        this.f403a = r2;
        r0.add(new y());
    }

    public static boolean b(char r2, char r3) {
        if (r2 != r3) goto L4;
        return true;
    L4:
        if (Character.toUpperCase(r2) != Character.toUpperCase(r3)) goto L6;
        return true;
    L6:
        if (Character.toLowerCase(r2) == Character.toLowerCase(r3)) goto L13;
        return false;
    L13:
        return true;
    }

    public final boolean a(char r1, char r2) {
        if (this.f404b == false) goto L10;
        if (r1 != r2) goto L7;
        return true;
    L7:
        return false;
    L10:
        return b(r1, r2);
    }

    public final y c() {
        return (y) this.d.get(r1.size() - 1);
    }

    public final Long d(j$.time.temporal.a r1) {
        return (Long) ((HashMap) c().f418a).get(r1);
    }

    public final void e(ZoneId r2) {
        Objects.requireNonNull(r2, "zone");
        c().f419b = r2;
    }

    public final int f(j$.time.temporal.p r2, long r3, int r5, int r6) {
        Objects.requireNonNull(r2, "field");
        Map r1 = c().f418a;
        HashMap r12 = (HashMap) r1;
        Long r13 = (Long) r12.put(r2, Long.valueOf(r3));
        if (r13 != null) goto L5;
    L8:
        return r6;
    L5:
        if (r13.longValue() == r3) goto L8;
        return ~r5;
    }

    public final boolean g(CharSequence r6, int r7, CharSequence r8, int r9, int r10) {
        if ((r7 + r10) <= r6.length()) goto L5;
    L27:
        return false;
    L5:
        if ((r9 + r10) > r8.length()) goto L27;
        if (this.f404b == false) goto L15;
        int r5 = 0;
    L10:
        if (r5 >= r10) goto L25;
        if (r6.charAt(r7 + r5) != r8.charAt(r9 + r5)) goto L27;
        r5 = r5 + 1;
        goto L10
    L25:
        return true;
    L15:
        int r52 = 0;
    L16:
        if (r52 >= r10) goto L35;
        char r0 = r6.charAt(r7 + r52);
        char r1 = r8.charAt(r9 + r52);
        if (r0 == r1) goto L24;
        if (Character.toUpperCase(r0) == Character.toUpperCase(r1)) goto L24;
        if (Character.toLowerCase(r0) != Character.toLowerCase(r1)) goto L27;
    L24:
        r52 = r52 + 1;
        goto L16
    L35:
        return true;
    }

    public final String toString() {
        return c().toString();
    }
}
