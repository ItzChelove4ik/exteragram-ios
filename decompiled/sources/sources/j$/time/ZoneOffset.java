package j$.time;

import j$.time.temporal.Temporal;
import j$.time.zone.ZoneRules;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class ZoneOffset extends ZoneId implements j$.time.temporal.l, j$.time.temporal.m, Comparable<ZoneOffset>, Serializable {
    public static final ZoneOffset UTC = null;
    public static final ConcurrentMap d = null;
    public static final ConcurrentMap e = null;
    public static final ZoneOffset f = null;
    public static final ZoneOffset g = null;
    private static final long serialVersionUID = 2357656521762053153L;

    /* renamed from: b, reason: collision with root package name */
    public final int f315b;

    /* renamed from: c, reason: collision with root package name */
    public final transient String f316c;

    static {
        d = new ConcurrentHashMap(16, 0.75f, 4);
        e = new ConcurrentHashMap(16, 0.75f, 4);
        UTC = ofTotalSeconds(0);
        f = ofTotalSeconds(-64800);
        g = ofTotalSeconds(64800);
    }

    public ZoneOffset(int r7) {
        this.f315b = r7;
        if (r7 != 0) goto L5;
        String r72 = "Z";
    L23:
        this.f316c = r72;
        return;
    L5:
        int r0 = Math.abs(r7);
        StringBuilder r1 = new StringBuilder();
        int r2 = r0 / 3600;
        int r3 = (r0 / 60) % 60;
        if (r7 >= 0) goto L8;
        String r73 = "-";
    L9:
        r1.append(r73);
        if (r2 >= 10) goto L12;
        String r4 = MVEL.VERSION_SUB;
    L13:
        r1.append(r4);
        r1.append(r2);
        String r22 = ":";
        if (r3 >= 10) goto L16;
        String r5 = ":0";
    L17:
        r1.append(r5);
        r1.append(r3);
        int r02 = r0 % 60;
        if (r02 == 0) goto L22;
        if (r02 >= 10) goto L21;
        r22 = ":0";
    L21:
        r1.append(r22);
        r1.append(r02);
    L22:
        r72 = r1.toString();
        goto L23
    L16:
        r5 = ":";
        goto L17
    L12:
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    L8:
        r73 = "+";
        goto L9
    }

    public static ZoneOffset Y(Temporal r3) {
        Objects.requireNonNull(r3, "temporal");
        ZoneOffset r0 = (ZoneOffset) r3.d(j$.time.temporal.q.d);
        if (r0 == null) goto L5;
        return r0;
    L5:
        j$.nio.file.b.f("Unable to obtain ZoneOffset from TemporalAccessor: ", r3, " of type ", r3.getClass().getName());
        return null;
    }

    public static ZoneOffset a0(String r8) {
        Objects.requireNonNull(r8, "offsetId");
        ZoneOffset r0 = (ZoneOffset) ((ConcurrentHashMap) e).get(r8);
        if (r0 == null) goto L5;
        return r0;
    L5:
        int r02 = r8.length();
        if (r02 != 2) goto L8;
        r8 = r8.charAt(0) + MVEL.VERSION_SUB + r8.charAt(1);
    L25:
        int r03 = c0(r8, 1, false);
        int r1 = 0;
        int r3 = 0;
    L26:
        char r4 = r8.charAt(0);
        if (r4 == '+') goto L32;
        if (r4 == '-') goto L32;
        j$.nio.file.b.h("Invalid ID for ZoneOffset, plus/minus not found when expected: ".concat(r8));
        return null;
    L32:
        if (r4 != '-') goto L36;
        return b0(-r03, -r1, -r3);
    L36:
        return b0(r03, r1, r3);
    L8:
        if (r02 == 3) goto L25;
        if (r02 != 5) goto L12;
        r03 = c0(r8, 1, false);
        r1 = c0(r8, 3, false);
    L22:
        r3 = 0;
        goto L26
    L12:
        if (r02 != 6) goto L14;
        r03 = c0(r8, 1, false);
        r1 = c0(r8, 4, true);
        goto L22
    L14:
        if (r02 != 7) goto L16;
        r03 = c0(r8, 1, false);
        r1 = c0(r8, 3, false);
        r3 = c0(r8, 5, false);
        goto L26
    L16:
        if (r02 != 9) goto L18;
        r03 = c0(r8, 1, false);
        r1 = c0(r8, 4, true);
        r3 = c0(r8, 7, true);
        goto L26
    L18:
        j$.nio.file.b.h("Invalid ID for ZoneOffset, invalid format: ".concat(r8));
        return null;
    }

    public static ZoneOffset b0(int r4, int r5, int r6) {
        if (r4 >= (-18)) goto L5;
    L44:
        j$.nio.file.b.c("Zone offset hours not in valid range: value ", r4, " is not in the range -18 to 18");
        return null;
    L5:
        if (r4 > 18) goto L44;
        if (r4 <= 0) goto L12;
        if (r5 < 0) goto L10;
        if (r6 < 0) goto L10;
    L26:
        if (r5 >= (-59)) goto L28;
    L42:
        j$.nio.file.b.c("Zone offset minutes not in valid range: value ", r5, " is not in the range -59 to 59");
        return null;
    L28:
        if (r5 > 59) goto L42;
        if (r6 < (-59)) goto L40;
        if (r6 > 59) goto L40;
        if (Math.abs(r4) == 18) goto L34;
    L38:
        int r52 = r5 * 60;
        return ofTotalSeconds((r52 + (r4 * 3600)) + r6);
    L34:
        if ((r5 | r6) == 0) goto L38;
        j$.nio.file.b.h("Zone offset not in valid range: -18:00 to +18:00");
        return null;
    L40:
        j$.nio.file.b.c("Zone offset seconds not in valid range: value ", r6, " is not in the range -59 to 59");
        return null;
    L10:
        j$.nio.file.b.h("Zone offset minutes and seconds must be positive because hours is positive");
        return null;
    L12:
        if (r4 >= 0) goto L18;
        if (r5 > 0) goto L16;
        if (r6 <= 0) goto L26;
    L16:
        j$.nio.file.b.h("Zone offset minutes and seconds must be negative because hours is negative");
        return null;
    L18:
        if (r5 <= 0) goto L20;
        if (r6 >= 0) goto L20;
    L23:
        j$.nio.file.b.h("Zone offset minutes and seconds must have the same sign");
        return null;
    L20:
        if (r5 >= 0) goto L26;
        if (r6 > 0) goto L23;
        goto L23
    }

    public static int c0(CharSequence r3, int r4, boolean r5) {
        if (r5 == false) goto L9;
        String r1 = (String) r3;
        if (r1.charAt(r4 - 1) == ':') goto L9;
        j$.nio.file.b.j("Invalid ID for ZoneOffset, colon not found when expected: ", r1);
        return 0;
    L9:
        String r32 = (String) r3;
        char r52 = r32.charAt(r4);
        char r42 = r32.charAt(r4 + 1);
        if (r52 >= '0') goto L12;
    L17:
        j$.nio.file.b.j("Invalid ID for ZoneOffset, non numeric characters found: ", r32);
        return 0;
    L12:
        if (r52 > '9') goto L17;
        if (r42 < '0') goto L17;
        if (r42 > '9') goto L17;
        return (r42 - '0') + ((r52 - '0') * 10);
    }

    public static ZoneOffset d0(DataInput r2) {
        byte r0 = r2.readByte();
        if (r0 != Byte.MAX_VALUE) goto L7;
        return ofTotalSeconds(r2.readInt());
    L7:
        return ofTotalSeconds(r0 * 900);
    }

    public static ZoneOffset ofTotalSeconds(int r3) {
        if (r3 >= (-64800)) goto L5;
    L15:
        j$.nio.file.b.h("Zone offset not in valid range: -18:00 to +18:00");
        return null;
    L5:
        if (r3 > 64800) goto L15;
        if ((r3 % 900) != 0) goto L14;
        Integer r0 = Integer.valueOf(r3);
        ConcurrentMap r1 = d;
        ZoneOffset r2 = (ZoneOffset) ((ConcurrentHashMap) r1).get(r0);
        if (r2 != null) goto L12;
        ZoneOffset r22 = new ZoneOffset(r3);
        ((ConcurrentHashMap) r1).putIfAbsent(r0, r22);
        ZoneOffset r32 = (ZoneOffset) ((ConcurrentHashMap) r1).get(r0);
        ConcurrentMap r02 = e;
        ConcurrentHashMap r03 = (ConcurrentHashMap) r02;
        r03.putIfAbsent(r32.f316c, r32);
        return r32;
    L12:
        return r2;
    L14:
        return new ZoneOffset(r3);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 8, this);
    }

    @Override // j$.time.ZoneId
    public final void S(DataOutput r2) {
        r2.writeByte(8);
        e0(r2);
    }

    @Override // java.lang.Comparable
    public final int compareTo(ZoneOffset r1) {
        return r1.f315b - this.f315b;
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.d) goto L5;
        return this;
    L5:
        if (r2 != j$.time.temporal.q.e) goto L8;
        return this;
    L8:
        return super.d(r2);
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        return r4.a(this.f315b, j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final void e0(DataOutput r3) {
        int r2 = this.f315b;
        if ((r2 % 900) != 0) goto L5;
        int r0 = r2 / 900;
    L6:
        r3.writeByte(r0);
        if (r0 != 127) goto L10;
        r3.writeInt(r2);
        return;
    L10:
        return;
    L5:
        r0 = 127;
        goto L6
    }

    @Override // j$.time.ZoneId
    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof ZoneOffset) == true) goto L8;
    L10:
        return false;
    L8:
        if (this.f315b != ((ZoneOffset) r4).f315b) goto L10;
        return true;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r4) {
        if (r4 == j$.time.temporal.a.OFFSET_SECONDS) goto L5;
        if (r4 != null) goto L10;
        return super.l(r4).a(k(r4), r4);
    L10:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r4));
    L5:
        return this.f315b;
    }

    @Override // j$.time.ZoneId
    public final String getId() {
        return this.f316c;
    }

    @Override // j$.time.ZoneId
    public final ZoneRules getRules() {
        return new ZoneRules(this);
    }

    public int getTotalSeconds() {
        return this.f315b;
    }

    @Override // j$.time.ZoneId
    public final int hashCode() {
        return this.f315b;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L9;
        return false;
    L9:
        if (r2.s(this) == false) goto L16;
        return true;
    L16:
        return false;
    L5:
        if (r2 == j$.time.temporal.a.OFFSET_SECONDS) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.OFFSET_SECONDS) goto L7;
        return this.f315b;
    L7:
        if ((r2 instanceof j$.time.temporal.a) == true) goto L11;
        return r2.S(this);
    L11:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r2));
    }

    @Override // j$.time.ZoneId
    public final String toString() {
        return this.f316c;
    }
}
