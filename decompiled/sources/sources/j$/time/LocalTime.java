package j$.time;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class LocalTime implements Temporal, j$.time.temporal.m, Comparable<LocalTime>, Serializable {
    public static final LocalTime e = null;
    public static final LocalTime f = null;
    public static final LocalTime g = null;
    public static final LocalTime[] h = null;
    private static final long serialVersionUID = 6414437269572265201L;

    /* renamed from: a, reason: collision with root package name */
    public final byte f294a;

    /* renamed from: b, reason: collision with root package name */
    public final byte f295b;

    /* renamed from: c, reason: collision with root package name */
    public final byte f296c;
    public final int d;

    static {
        h = new LocalTime[24];
        int r1 = 0;
    L3:
        LocalTime[] r2 = h;
        if (r1 >= r2.length) goto L6;
        r2[r1] = new LocalTime(r1, 0, 0, 0);
        r1 = r1 + 1;
        goto L3
    L6:
        LocalTime r0 = r2[0];
        g = r0;
        LocalTime r12 = r2[12];
        e = r0;
        f = new LocalTime(23, 59, 59, 999999999);
    }

    public LocalTime(int r1, int r2, int r3, int r4) {
        this.f294a = (byte) r1;
        this.f295b = (byte) r2;
        this.f296c = (byte) r3;
        this.d = r4;
    }

    public static LocalTime D(int r1, int r2, int r3, int r4) {
        if (((r2 | r3) | r4) != 0) goto L7;
        return h[r1];
    L7:
        return new LocalTime(r1, r2, r3, r4);
    }

    public static LocalTime I(j$.time.temporal.l r3) {
        Objects.requireNonNull(r3, "temporal");
        LocalTime r0 = (LocalTime) r3.d(j$.time.temporal.q.g);
        if (r0 == null) goto L5;
        return r0;
    L5:
        j$.nio.file.b.f("Unable to obtain LocalTime from TemporalAccessor: ", r3, " of type ", r3.getClass().getName());
        return null;
    }

    public static LocalTime S(long r7) {
        j$.time.temporal.a.NANO_OF_DAY.a0(r7);
        int r2 = (int) (r7 / 3600000000000L);
        long r72 = r7 - (r2 * 3600000000000L);
        int r3 = (int) (r72 / 60000000000L);
        long r73 = r72 - (r3 * 60000000000L);
        int r4 = (int) (r73 / 1000000000);
        return D(r2, r3, r4, (int) (r73 - (r4 * 1000000000)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4, types: [int] */
    public static LocalTime e0(DataInput r5) {
        int r0 = r5.readByte();
        byte r1 = 0;
        if (r0 >= 0) goto L5;
        r0 = ~r0;
        int r52 = 0;
        int r2 = 0;
    L13:
        return of(r0, r1, r52, r2);
    L5:
        byte r22 = r5.readByte();
        if (r22 >= 0) goto L8;
        ?? r53 = ~r22;
        r2 = 0;
        r1 = r53;
        r52 = 0;
        goto L13
    L8:
        byte r3 = r5.readByte();
        if (r3 >= 0) goto L11;
        r52 = ~r3;
        r2 = 0;
        r1 = r22;
        goto L13
    L11:
        r2 = r5.readInt();
        r1 = r22;
        r52 = r3;
        goto L13
    }

    public static LocalTime of(int r3, int r4, int r5, int r6) {
        j$.time.temporal.a.HOUR_OF_DAY.a0(r3);
        j$.time.temporal.a.MINUTE_OF_HOUR.a0(r4);
        j$.time.temporal.a.SECOND_OF_MINUTE.a0(r5);
        j$.time.temporal.a.NANO_OF_SECOND.a0(r6);
        return D(r3, r4, r5, r6);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 4, this);
    }

    public final int K(j$.time.temporal.p r3) {
        switch(i.f425a[((j$.time.temporal.a) r3).ordinal()]) {
            case 1: goto L39;
            case 2: goto L37;
            case 3: goto L35;
            case 4: goto L33;
            case 5: goto L31;
            case 6: goto L29;
            case 7: goto L27;
            case 8: goto L25;
            case 9: goto L23;
            case 10: goto L21;
            case 11: goto L19;
            case 12: goto L14;
            case 13: goto L13;
            case 14: goto L8;
            case 15: goto L7;
            default: goto L5;
        };
    L8:
        byte r2 = this.f294a;
        if (r2 != 0) goto L40;
        return 24;
    L40:
        return r2;
    L14:
        int r22 = this.f294a % 12;
        if ((r22 % 12) != 0) goto L17;
        return 12;
    L17:
        return r22;
    L5:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r3));
    L7:
        return this.f294a / 12;
    L13:
        return this.f294a;
    L19:
        return this.f294a % 12;
    L21:
        return (this.f294a * 60) + this.f295b;
    L23:
        return this.f295b;
    L25:
        return g0();
    L27:
        return this.f296c;
    L29:
        return (int) (f0() / 1000000);
    L31:
        return this.d / DurationKt.NANOS_IN_MILLIS;
    L33:
        throw new j$.time.temporal.r("Invalid field 'MicroOfDay' for get() method, use getLong() instead");
    L35:
        return this.d / 1000;
    L37:
        throw new j$.time.temporal.r("Invalid field 'NanoOfDay' for get() method, use getLong() instead");
    L39:
        return this.d;
    }

    public final LocalTime Y(long r3, TemporalUnit r5) {
        if ((r5 instanceof ChronoUnit) == false) goto L23;
        switch(i.f426b[((ChronoUnit) r5).ordinal()]) {
            case 1: goto L21;
            case 2: goto L19;
            case 3: goto L17;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L9;
            default: goto L6;
        };
    L6:
        j$.nio.file.b.d("Unsupported unit: ", r5);
        return null;
    L9:
        return a0((r3 % 2) * 12);
    L11:
        return a0(r3);
    L13:
        return b0(r3);
    L15:
        return d0(r3);
    L17:
        return c0((r3 % DurationKt.MILLIS_IN_DAY) * 1000000);
    L19:
        return c0((r3 % 86400000000L) * 1000);
    L21:
        return c0(r3);
    L23:
        return (LocalTime) r5.s(this, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return h0(r1, r3);
    }

    public final LocalTime a0(long r3) {
        if (r3 != 0) goto L6;
        return this;
    L6:
        return D(((((int) (r3 % 24)) + this.f294a) + 24) % 24, this.f295b, this.f296c, this.d);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return Y(r1, r3);
    }

    public final LocalTime b0(long r4) {
        if (r4 == 0) goto L7;
        int r0 = (this.f294a * 60) + this.f295b;
        int r42 = ((((int) (r4 % 1440)) + r0) + 1440) % 1440;
        if (r0 == r42) goto L7;
        return D(r42 / 60, r42 % 60, this.f296c, this.d);
    L7:
        return this;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        LocalTime this = Y(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.Y(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    public final LocalTime c0(long r9) {
        if (r9 == 0) goto L7;
        long r0 = f0();
        long r92 = (((r9 % 86400000000000L) + r0) + 86400000000000L) % 86400000000000L;
        if (r0 == r92) goto L7;
        return D((int) (r92 / 3600000000000L), (int) ((r92 / 60000000000L) % 60), (int) ((r92 / 1000000000) % 60), (int) (r92 % 1000000000));
    L7:
        return this;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(LocalTime r1) {
        return s(r1);
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f464b) goto L5;
        return null;
    L5:
        if (r2 != j$.time.temporal.q.f463a) goto L7;
        return null;
    L7:
        if (r2 != j$.time.temporal.q.e) goto L9;
        return null;
    L9:
        if (r2 != j$.time.temporal.q.d) goto L12;
        return null;
    L12:
        if (r2 != j$.time.temporal.q.g) goto L15;
        return this;
    L15:
        if (r2 != j$.time.temporal.q.f) goto L18;
        return null;
    L18:
        if (r2 != j$.time.temporal.q.f465c) goto L22;
        return ChronoUnit.NANOS;
    L22:
        return r2.l(this);
    }

    public final LocalTime d0(long r5) {
        if (r5 == 0) goto L7;
        int r0 = this.f294a * 3600;
        int r1 = ((this.f295b * 60) + r0) + this.f296c;
        int r52 = ((((int) (r5 % 86400)) + r1) + 86400) % 86400;
        if (r1 == r52) goto L7;
        return D(r52 / 3600, (r52 / 60) % 60, r52 % 60, this.d);
    L7:
        return this;
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        j$.time.temporal.a r0 = j$.time.temporal.a.NANO_OF_DAY;
        return r4.a(f0(), r0);
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof LocalTime) == false) goto L16;
        LocalTime r52 = (LocalTime) r5;
        if (this.f294a != r52.f294a) goto L16;
        if (this.f295b != r52.f295b) goto L16;
        if (this.f296c != r52.f296c) goto L16;
        if (this.d != r52.d) goto L16;
        return true;
    L16:
        return false;
    }

    public final long f0() {
        return ((this.f296c * 1000000000) + ((this.f295b * 60000000000L) + (this.f294a * 3600000000000L))) + this.d;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L7;
        return K(r2);
    L7:
        return super.g(r2);
    }

    public final int g0() {
        int r0 = this.f294a * 3600;
        return ((this.f295b * 60) + r0) + this.f296c;
    }

    public int getHour() {
        return this.f294a;
    }

    public int getMinute() {
        return this.f295b;
    }

    public int getNano() {
        return this.d;
    }

    public int getSecond() {
        return this.f296c;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (LocalTime) r1.e(this);
    }

    public final LocalTime h0(long r6, j$.time.temporal.p r8) {
        if ((r8 instanceof j$.time.temporal.a) == false) goto L57;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r8;
        r0.a0(r6);
        switch(i.f425a[r0.ordinal()]) {
            case 1: goto L55;
            case 2: goto L53;
            case 3: goto L51;
            case 4: goto L49;
            case 5: goto L47;
            case 6: goto L45;
            case 7: goto L39;
            case 8: goto L38;
            case 9: goto L32;
            case 10: goto L31;
            case 11: goto L29;
            case 12: goto L24;
            case 13: goto L18;
            case 14: goto L11;
            case 15: goto L9;
            default: goto L7;
        };
    L18:
        int r62 = (int) r6;
        if (this.f294a == r62) goto L41;
        j$.time.temporal.a.HOUR_OF_DAY.a0(r62);
        return D(r62, this.f295b, this.f296c, this.d);
    L41:
        return this;
    L32:
        int r63 = (int) r6;
        if (this.f295b == r63) goto L41;
        j$.time.temporal.a.MINUTE_OF_HOUR.a0(r63);
        return D(this.f294a, r63, this.f296c, this.d);
    L39:
        int r64 = (int) r6;
        if (this.f296c == r64) goto L41;
        j$.time.temporal.a.SECOND_OF_MINUTE.a0(r64);
        return D(this.f294a, this.f295b, r64, this.d);
    L7:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r8));
    L9:
        return a0((r6 - (this.f294a / 12)) * 12);
    L11:
        if (r6 != 24) goto L13;
        r6 = 0;
    L13:
        int r65 = (int) r6;
        if (this.f294a == r65) goto L41;
        j$.time.temporal.a.HOUR_OF_DAY.a0(r65);
        return D(r65, this.f295b, this.f296c, this.d);
    L24:
        if (r6 != 12) goto L27;
        r6 = 0;
    L27:
        return a0(r6 - (this.f294a % 12));
    L29:
        return a0(r6 - (this.f294a % 12));
    L31:
        return b0(r6 - ((this.f294a * 60) + this.f295b));
    L38:
        return d0(r6 - g0());
    L45:
        return S(r6 * 1000000);
    L47:
        return i0(((int) r6) * DurationKt.NANOS_IN_MILLIS);
    L49:
        return S(r6 * 1000);
    L51:
        return i0(((int) r6) * 1000);
    L53:
        return S(r6);
    L55:
        return i0((int) r6);
    L57:
        return (LocalTime) r8.Y(this, r6);
    }

    public final int hashCode() {
        long r0 = f0();
        return (int) (r0 ^ (r0 >>> 32));
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L8;
        return false;
    L8:
        if (r2.s(this) == false) goto L13;
        return true;
    L13:
        return false;
    L5:
        return ((j$.time.temporal.a) r2).b0();
    }

    public final LocalTime i0(int r4) {
        if (this.d != r4) goto L5;
        return this;
    L5:
        j$.time.temporal.a.NANO_OF_SECOND.a0(r4);
        return D(this.f294a, this.f295b, this.f296c, r4);
    }

    public final void j0(DataOutput r3) {
        if (this.d == 0) goto L5;
        r3.writeByte(this.f294a);
        r3.writeByte(this.f295b);
        r3.writeByte(this.f296c);
        r3.writeInt(this.d);
        return;
    L5:
        if (this.f296c != 0) goto L12;
        byte r0 = this.f295b;
        byte r1 = this.f294a;
        if (r0 != 0) goto L10;
        r3.writeByte(~r1);
        return;
    L10:
        r3.writeByte(r1);
        r3.writeByte(~this.f295b);
        return;
    L12:
        r3.writeByte(this.f294a);
        r3.writeByte(this.f295b);
        r3.writeByte(~this.f296c);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        if (r3 != j$.time.temporal.a.NANO_OF_DAY) goto L9;
        return f0();
    L9:
        if (r3 != j$.time.temporal.a.MICRO_OF_DAY) goto L13;
        return f0() / 1000;
    L13:
        return K(r3);
    L15:
        return r3.S(this);
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r3, TemporalUnit r4) {
        LocalTime r32 = I(r3);
        if ((r4 instanceof ChronoUnit) == false) goto L21;
        long r0 = r32.f0() - f0();
        switch(i.f426b[((ChronoUnit) r4).ordinal()]) {
            case 1: goto L22;
            case 2: goto L19;
            case 3: goto L17;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L9;
            default: goto L6;
        };
    L6:
        j$.nio.file.b.d("Unsupported unit: ", r4);
        return 0;
    L22:
        return r0;
    L9:
        return r0 / 43200000000000L;
    L11:
        return r0 / 3600000000000L;
    L13:
        return r0 / 60000000000L;
    L15:
        return r0 / 1000000000;
    L17:
        return r0 / 1000000;
    L19:
        return r0 / 1000;
    L21:
        return r4.between(this, r32);
    }

    public final int s(LocalTime r3) {
        int r0 = Integer.compare(this.f294a, r3.f294a);
        if (r0 != 0) goto L10;
        r0 = Integer.compare(this.f295b, r3.f295b);
        if (r0 != 0) goto L10;
        r0 = Integer.compare(this.f296c, r3.f296c);
        if (r0 != 0) goto L10;
        return Integer.compare(this.d, r3.d);
    L10:
        return r0;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(18);
        byte r1 = this.f294a;
        byte r2 = this.f295b;
        byte r3 = this.f296c;
        int r7 = this.d;
        if (r1 >= 10) goto L5;
        String r5 = MVEL.VERSION_SUB;
    L6:
        r0.append(r5);
        r0.append(r1);
        String r12 = ":";
        if (r2 >= 10) goto L9;
        String r6 = ":0";
    L10:
        r0.append(r6);
        r0.append(r2);
        if (r3 > 0) goto L13;
        if (r7 > 0) goto L13;
    L25:
        return r0.toString();
    L13:
        if (r3 >= 10) goto L15;
        r12 = ":0";
    L15:
        r0.append(r12);
        r0.append(r3);
        if (r7 <= 0) goto L25;
        r0.append('.');
        if ((r7 % DurationKt.NANOS_IN_MILLIS) != 0) goto L21;
        r0.append(Integer.toString((r7 / DurationKt.NANOS_IN_MILLIS) + 1000).substring(1));
        goto L25
    L21:
        if ((r7 % 1000) != 0) goto L23;
        r0.append(Integer.toString((r7 / 1000) + DurationKt.NANOS_IN_MILLIS).substring(1));
        goto L25
    L23:
        r0.append(Integer.toString(r7 + 1000000000).substring(1));
        goto L25
    L9:
        r6 = ":";
        goto L10
    L5:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
