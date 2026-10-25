package j$.time.format;

import java.text.DateFormatSymbols;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okhttp3.internal.url._UrlKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    public static final ConcurrentMap f414a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final u f415b = null;

    /* renamed from: c, reason: collision with root package name */
    public static final w f416c = null;

    static {
        f414a = new ConcurrentHashMap(16, 0.75f, 2);
        f415b = new u();
        f416c = new w();
    }

    public static Object a(j$.time.temporal.p r14, Locale r15) {
        AbstractMap.SimpleImmutableEntry r0 = new AbstractMap.SimpleImmutableEntry(r14, r15);
        Object r1 = ((ConcurrentHashMap) f414a).get(r0);
        if (r1 != null) goto L62;
        HashMap r12 = new HashMap();
        if (r14 != j$.time.temporal.a.ERA) goto L18;
        DateFormatSymbols r142 = DateFormatSymbols.getInstance(r15);
        HashMap r152 = new HashMap();
        HashMap r2 = new HashMap();
        String[] r143 = r142.getEras();
        int r4 = 0;
    L8:
        if (r4 >= r143.length) goto L14;
        if (r143[r4].isEmpty() == true) goto L12;
        long r5 = r4;
        r152.put(Long.valueOf(r5), r143[r4]);
        Long r52 = Long.valueOf(r5);
        String r6 = r143[r4];
        r2.put(r52, r6.substring(0, Character.charCount(r6.codePointAt(0))));
    L12:
        r4 = r4 + 1;
        goto L8
    L14:
        if (r152.isEmpty() == true) goto L16;
        r12.put(TextStyle.FULL, r152);
        r12.put(TextStyle.SHORT, r152);
        r12.put(TextStyle.NARROW, r2);
    L16:
        Object r144 = new v(r12);
    L60:
        ConcurrentHashMap r153 = (ConcurrentHashMap) f414a;
        r153.putIfAbsent(r0, r144);
        return r153.get(r0);
    L18:
        if (r14 != j$.time.temporal.a.MONTH_OF_YEAR) goto L27;
        int r145 = DateFormatSymbols.getInstance(r15).getMonths().length;
        LinkedHashMap r22 = new LinkedHashMap();
        LinkedHashMap r62 = new LinkedHashMap();
        LinkedHashMap r7 = new LinkedHashMap();
        long r8 = 1;
    L21:
        if (r8 > r145) goto L23;
        String r10 = j$.desugar.sun.nio.fs.g.b(r8, "LLLL", r15);
        r22.put(Long.valueOf(r8), r10);
        r62.put(Long.valueOf(r8), r10.substring(0, Character.charCount(r10.codePointAt(0))));
        r7.put(Long.valueOf(r8), j$.desugar.sun.nio.fs.g.b(r8, "LLL", r15));
        r8 = r8 + 1;
        goto L21
    L23:
        if (r145 <= 0) goto L25;
        r12.put(TextStyle.FULL_STANDALONE, r22);
        r12.put(TextStyle.NARROW_STANDALONE, r62);
        r12.put(TextStyle.SHORT_STANDALONE, r7);
        r12.put(TextStyle.FULL, r22);
        r12.put(TextStyle.NARROW, r62);
        r12.put(TextStyle.SHORT, r7);
    L25:
        r144 = new v(r12);
        goto L60
    L27:
        if (r14 != j$.time.temporal.a.DAY_OF_WEEK) goto L47;
        int r146 = DateFormatSymbols.getInstance(r15).getWeekdays().length;
        LinkedHashMap r23 = new LinkedHashMap();
        LinkedHashMap r63 = new LinkedHashMap();
        LinkedHashMap r72 = new LinkedHashMap();
        if (r15 != Locale.SIMPLIFIED_CHINESE) goto L31;
    L34:
        boolean r82 = true;
    L35:
        long r9 = 1;
    L37:
        if (r9 > r146) goto L43;
        String r11 = j$.desugar.sun.nio.fs.g.a(r9, "cccc", r15);
        r23.put(Long.valueOf(r9), r11);
        Long r122 = Long.valueOf(r9);
        if (r82 == false) goto L41;
        String r112 = new StringBuilder().appendCodePoint(r11.codePointBefore(r11.length())).toString();
    L42:
        r63.put(r122, r112);
        r72.put(Long.valueOf(r9), j$.desugar.sun.nio.fs.g.a(r9, "ccc", r15));
        r9 = r9 + 1;
        goto L37
    L41:
        r112 = r11.substring(0, Character.charCount(r11.codePointAt(0)));
        goto L42
    L43:
        if (r146 <= 0) goto L45;
        r12.put(TextStyle.FULL_STANDALONE, r23);
        r12.put(TextStyle.NARROW_STANDALONE, r63);
        r12.put(TextStyle.SHORT_STANDALONE, r72);
        r12.put(TextStyle.FULL, r23);
        r12.put(TextStyle.NARROW, r63);
        r12.put(TextStyle.SHORT, r72);
    L45:
        r144 = new v(r12);
        goto L60
    L31:
        if (r15 == Locale.TRADITIONAL_CHINESE) goto L34;
        r82 = false;
        goto L35
    L47:
        if (r14 != j$.time.temporal.a.AMPM_OF_DAY) goto L59;
        DateFormatSymbols r147 = DateFormatSymbols.getInstance(r15);
        HashMap r154 = new HashMap();
        HashMap r24 = new HashMap();
        String[] r148 = r147.getAmPmStrings();
        int r42 = 0;
    L50:
        if (r42 >= r148.length) goto L56;
        if (r148[r42].isEmpty() == true) goto L54;
        long r53 = r42;
        r154.put(Long.valueOf(r53), r148[r42]);
        Long r54 = Long.valueOf(r53);
        String r64 = r148[r42];
        r24.put(r54, r64.substring(0, Character.charCount(r64.codePointAt(0))));
    L54:
        r42 = r42 + 1;
        goto L50
    L56:
        if (r154.isEmpty() == true) goto L58;
        r12.put(TextStyle.FULL, r154);
        r12.put(TextStyle.SHORT, r154);
        r12.put(TextStyle.NARROW, r24);
    L58:
        r144 = new v(r12);
        goto L60
    L59:
        r144 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L60
    L62:
        return r1;
    }

    public String b(j$.time.chrono.k r2, j$.time.temporal.p r3, long r4, TextStyle r6, Locale r7) {
        if (r2 == j$.time.chrono.r.f353c) goto L10;
        if ((r3 instanceof j$.time.temporal.a) == false) goto L10;
        return null;
    L10:
        return c(r3, r4, r6, r7);
    }

    public String c(j$.time.temporal.p r1, long r2, TextStyle r4, Locale r5) {
        Object r0 = a(r1, r5);
        if ((r0 instanceof v) == true) goto L5;
        return null;
    L5:
        return ((v) r0).a(r2, r4);
    }

    public Iterator d(j$.time.chrono.k r2, j$.time.temporal.p r3, TextStyle r4, Locale r5) {
        if (r2 == j$.time.chrono.r.f353c) goto L10;
        if ((r3 instanceof j$.time.temporal.a) == false) goto L10;
        return null;
    L10:
        return e(r3, r4, r5);
    }

    public Iterator e(j$.time.temporal.p r1, TextStyle r2, Locale r3) {
        Object r0 = a(r1, r3);
        if ((r0 instanceof v) == false) goto L8;
        List r02 = (List) ((HashMap) ((v) r0).f413b).get(r2);
        if (r02 == null) goto L8;
        return r02.iterator();
    L8:
        return null;
    }
}
