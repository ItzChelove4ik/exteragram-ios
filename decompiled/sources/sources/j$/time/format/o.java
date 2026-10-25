package j$.time.format;

import j$.time.ZoneId;
import j$.time.ZoneOffset;
import java.text.ParsePosition;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import okhttp3.internal.url._UrlKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class o implements e {

    /* renamed from: c, reason: collision with root package name */
    public static volatile Map.Entry f397c;
    public static volatile Map.Entry d;

    /* renamed from: a, reason: collision with root package name */
    public final j$.time.e f398a;

    /* renamed from: b, reason: collision with root package name */
    public final String f399b;

    public o(j$.time.e r1, String r2) {
        this.f398a = r1;
        this.f399b = r2;
    }

    public static int b(r r3, CharSequence r4, int r5, int r6, i r7) {
        String r0 = r4.subSequence(r5, r6).toString().toUpperCase();
        if (r6 < r4.length()) goto L7;
        r3.e(ZoneId.of(r0));
        return r6;
    L7:
        if (r4.charAt(r6) != '0') goto L9;
    L23:
        r3.e(ZoneId.of(r0));
        return r6;
    L9:
        if (r3.a(r4.charAt(r6), 'Z') == true) goto L23;
        r r1 = new r(r3.f403a);
        r1.f404b = r3.f404b;
        r1.f405c = r3.f405c;
        int r42 = r7.D(r1, r4, r6);
        if (r42 < 0) goto L14;
        r3.e(ZoneId.I(r0, ZoneOffset.ofTotalSeconds((int) r1.d(j$.time.temporal.a.OFFSET_SECONDS).longValue())));     // Catch: j$.time.b -> L21
        return r42;
    L14:
        if (r7 == i.e) goto L16;
        r3.e(ZoneId.of(r0));     // Catch: j$.time.b -> L21
        return r6;
    L16:
        return ~r5;
    L22:
        return ~r5;
    }

    @Override // j$.time.format.e
    public final int D(r r8, CharSequence r9, int r10) {
        int r0 = r9.length();
        if (r10 > r0) goto L55;
        if (r10 == r0) goto L6;
        char r1 = r9.charAt(r10);
        if (r1 == '+') goto L53;
        if (r1 == '-') goto L53;
        int r2 = r10 + 2;
        if (r0 < r2) goto L42;
        char r3 = r9.charAt(r10 + 1);
        if (r8.a(r1, 'U') == false) goto L27;
        if (r8.a(r3, 'T') == false) goto L27;
        int r7 = r10 + 3;
        if (r0 < r7) goto L25;
        if (r8.a(r9.charAt(r2), 'C') == false) goto L25;
        return b(r8, r9, r10, r7, i.f);
    L25:
        return b(r8, r9, r10, r2, i.f);
    L27:
        if (r8.a(r1, 'G') == false) goto L42;
        int r4 = r10 + 3;
        if (r0 < r4) goto L42;
        if (r8.a(r3, 'M') == false) goto L42;
        if (r8.a(r9.charAt(r2), 'T') == false) goto L42;
        int r72 = r10 + 4;
        if (r0 < r72) goto L41;
        if (r8.a(r9.charAt(r4), '0') == false) goto L41;
        r8.e(ZoneId.of("GMT0"));
        return r72;
    L41:
        return b(r8, r9, r10, r4, i.f);
    L42:
        k r73 = a(r8);
        ParsePosition r02 = new ParsePosition(r10);
        String r74 = r73.c(r9, r02);
        if (r74 == null) goto L45;
        r8.e(ZoneId.of(r74));
        return r02.getIndex();
    L45:
        if (r8.a(r1, 'Z') == false) goto L49;
        r8.e(ZoneOffset.UTC);
        return r10 + 1;
    L49:
        return ~r10;
    L53:
        return b(r8, r9, r10, r10, i.e);
    L6:
        return ~r10;
    L55:
        throw new IndexOutOfBoundsException();
    }

    public k a(r r7) {
        Set r0 = j$.time.zone.g.d;
        int r1 = r0.size();
        if (r7.f404b == false) goto L5;
        Map.Entry r2 = f397c;
    L6:
        if (r2 != null) goto L8;
    L9:
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L11:
        if (r7.f404b == false) goto L15;
        r2 = f397c;     // Catch: Throwable -> L13
    L16:
        if (r2 != null) goto L18;
    L19:
        Integer r12 = Integer.valueOf(r1);     // Catch: Throwable -> L13
        if (r7.f404b == false) goto L22;
        k r4 = new k(_UrlKt.FRAGMENT_ENCODE_SET, null, null);     // Catch: Throwable -> L13
    L23:
        Iterator r02 = r0.iterator();     // Catch: Throwable -> L13
    L25:
        if (r02.hasNext() == false) goto L27;
        String r3 = (String) r02.next();     // Catch: Throwable -> L13
        r4.a(r3, r3);     // Catch: Throwable -> L13
        goto L25
    L27:
        r2 = new AbstractMap.SimpleImmutableEntry(r12, r4);     // Catch: Throwable -> L13
        if (r7.f404b == false) goto L30;
        f397c = r2;     // Catch: Throwable -> L13
    L31:
        monitor-exit(this);     // Catch: Throwable -> L13
    L33:
        return (k) r2.getValue();
    L30:
        d = r2;     // Catch: Throwable -> L13
        goto L31
    L22:
        r4 = new j(_UrlKt.FRAGMENT_ENCODE_SET, null, null);     // Catch: Throwable -> L13
        goto L23
    L18:
        if (((Integer) r2.getKey()).intValue() == r1) goto L31;
    L15:
        r2 = d;     // Catch: Throwable -> L13
        goto L16
    L8:
        if (((Integer) r2.getKey()).intValue() == r1) goto L33;
    L5:
        r2 = d;
        goto L6
    }

    @Override // j$.time.format.e
    public boolean s(t r1, StringBuilder r2) {
        ZoneId r0 = (ZoneId) r1.b(this.f398a);
        if (r0 != null) goto L6;
        return false;
    L6:
        r2.append(r0.getId());
        return true;
    }

    public final String toString() {
        return this.f399b;
    }
}
