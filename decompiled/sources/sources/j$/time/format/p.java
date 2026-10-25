package j$.time.format;

import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.ZonedDateTime;
import j$.time.zone.ZoneRules;
import java.lang.ref.SoftReference;
import java.text.DateFormatSymbols;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class p extends o {
    public static final Map h = null;
    public final TextStyle e;
    public final Map f;
    public final Map g;

    static {
        h = new ConcurrentHashMap();
    }

    public p(TextStyle r3) {
        super(j$.time.temporal.q.e, "ZoneText(" + r3 + ")");
        this.f = new HashMap();
        this.g = new HashMap();
        Objects.requireNonNull(r3, "textStyle");
        this.e = r3;
    }

    @Override // j$.time.format.o
    public final k a(r r15) {
        if (this.e == TextStyle.NARROW) goto L5;
        Locale r0 = r15.f403a.f371b;
        boolean r1 = r15.f404b;
        Set r2 = j$.time.zone.g.d;
        int r3 = r2.size();
        if (r1 == false) goto L9;
        Map r12 = this.f;
    L10:
        Map.Entry r4 = (Map.Entry) r12.get(r0);
        if (r4 == null) goto L19;
        if (((Integer) r4.getKey()).intValue() != r3) goto L19;
        k r42 = (k) ((SoftReference) r4.getValue()).get();
        if (r42 == null) goto L19;
        return r42;
    L19:
        if (r15.f404b == false) goto L21;
        k r152 = new k(_UrlKt.FRAGMENT_ENCODE_SET, null, null);
    L22:
        String[][] r43 = DateFormatSymbols.getInstance(r0).getZoneStrings();
        int r5 = r43.length;
        int r7 = 0;
    L23:
        if (r7 >= r5) goto L50;
        String[] r8 = r43[r7];
        String r9 = r8[0];
        if (r2.contains(r9) == false) goto L49;
        r152.a(r9, r9);
        HashMap r10 = (HashMap) b0.d;
        String r11 = (String) r10.get(r9);
        if (r11 != null) goto L32;
        HashMap r122 = (HashMap) b0.g;
        if (r122.containsKey(r9) == false) goto L32;
        r9 = (String) r122.get(r9);
        r11 = (String) r10.get(r9);
    L32:
        if (r11 == null) goto L39;
        Map r92 = (Map) ((HashMap) b0.f).get(r11);
        if (r92 != null) goto L36;
    L38:
        r9 = (String) ((HashMap) b0.e).get(r11);
        goto L39
    L36:
        if (r92.containsKey(r0.getCountry()) == false) goto L38;
        r9 = (String) r92.get(r0.getCountry());
    L39:
        HashMap r102 = (HashMap) b0.g;
        if (r102.containsKey(r9) == false) goto L43;
        r9 = (String) r102.get(r9);
    L43:
        if (this.e != TextStyle.FULL) goto L45;
        int r103 = 1;
    L47:
        if (r103 >= r8.length) goto L49;
        r152.a(r8[r103], r9);
        r103 = r103 + 2;
        goto L47
    L45:
        r103 = 2;
    L49:
        r7 = r7 + 1;
        goto L23
    L50:
        r12.put(r0, new AbstractMap.SimpleImmutableEntry(Integer.valueOf(r3), new SoftReference(r152)));
        return r152;
    L21:
        r152 = new j(_UrlKt.FRAGMENT_ENCODE_SET, null, null);
        goto L22
    L9:
        r12 = this.g;
        goto L10
    L5:
        return super.a(r15);
    }

    @Override // j$.time.format.o, j$.time.format.e
    public final boolean s(t r13, StringBuilder r14) {
        ZoneId r0 = (ZoneId) r13.b(j$.time.temporal.q.f463a);
        if (r0 != null) goto L5;
        return false;
    L5:
        String r2 = r0.getId();
        if ((r0 instanceof ZoneOffset) == true) goto L38;
        j$.time.temporal.l r3 = r13.f409a;
        String r6 = null;
        Map r62 = null;
        if (r3.i(j$.time.temporal.a.INSTANT_SECONDS) == false) goto L10;
        boolean r02 = r0.getRules().g(Instant.D(r3));
    L18:
        Locale r132 = r13.f410b.f371b;
        TextStyle r32 = TextStyle.NARROW;
        TextStyle r12 = this.e;
        if (r12 == r32) goto L36;
        ConcurrentHashMap r33 = (ConcurrentHashMap) h;
        SoftReference r5 = (SoftReference) r33.get(r2);
        if (r5 == null) goto L27;
        r62 = (Map) r5.get();
        if (r62 == null) goto L27;
        String[] r52 = (String[]) r62.get(r132);
        if (r52 == null) goto L27;
    L31:
        if (r02 == false) goto L35;
        if (r02 == true) goto L34;
        r6 = r52[r12.f374a + 5];
        goto L36
    L34:
        r6 = r52[r12.f374a + 3];
        goto L36
    L35:
        r6 = r52[r12.f374a + 1];
    L27:
        TimeZone r53 = TimeZone.getTimeZone(r2);
        String[] r10 = {r2, r53.getDisplayName(false, 1, r132), r53.getDisplayName(false, 0, r132), r53.getDisplayName(true, 1, r132), r53.getDisplayName(true, 0, r132), r2, r2};
        if (r62 != null) goto L30;
        r62 = new ConcurrentHashMap();
    L30:
        r62.put(r132, r10);
        r33.put(r2, new SoftReference(r62));
        r52 = r10;
    L36:
        if (r6 == null) goto L38;
        r2 = r6;
        goto L38
    L10:
        j$.time.temporal.a r54 = j$.time.temporal.a.EPOCH_DAY;
        if (r3.i(r54) == false) goto L17;
        j$.time.temporal.a r8 = j$.time.temporal.a.NANO_OF_DAY;
        if (r3.i(r8) == false) goto L17;
        LocalDateTime r34 = LocalDateTime.of(LocalDate.g0(r3.k(r54)), LocalTime.S(r3.k(r8)));
        if (r0.getRules().e(r34) != null) goto L17;
        ZoneRules r55 = r0.getRules();
        r34.getClass();
        r02 = r55.g(ZonedDateTime.I(r34, null, r0).toInstant());
    L17:
        r02 = 2;
    L38:
        r14.append(r2);
        return true;
    }
}
