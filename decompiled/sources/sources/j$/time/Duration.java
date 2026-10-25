package j$.time;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import kotlin.time.DurationKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class Duration implements j$.time.temporal.o, Comparable<Duration>, Serializable {
    public static final Duration ZERO = null;

    /* renamed from: c, reason: collision with root package name */
    public static final BigInteger f282c = null;
    private static final long serialVersionUID = 3078945930695997490L;

    /* renamed from: a, reason: collision with root package name */
    public final long f283a;

    /* renamed from: b, reason: collision with root package name */
    public final int f284b;

    static {
        ZERO = new Duration(0, 0);
        f282c = BigInteger.valueOf(1000000000);
    }

    public Duration(long r1, int r3) {
        this.f283a = r1;
        this.f284b = r3;
    }

    public static Duration D(long r4, int r6) {
        if ((r6 | r4) != 0) goto L7;
        return ZERO;
    L7:
        return new Duration(r4, r6);
    }

    public static Duration of(long r11, TemporalUnit r13) {
        Duration r0 = ZERO;
        r0.getClass();
        Objects.requireNonNull(r13, "unit");
        if (r13 != ChronoUnit.DAYS) goto L7;
        return r0.I(Math.multiplyExact(r11, 86400), 0);
    L7:
        if (r13.isDurationEstimated() == true) goto L45;
        if (r11 != 0) goto L12;
        return r0;
    L12:
        if ((r13 instanceof ChronoUnit) == false) goto L31;
        ChronoUnit r132 = (ChronoUnit) r13;
        int r1 = d.f366a[r132.ordinal()];
        if (r1 == 1) goto L30;
        if (r1 == 2) goto L28;
        if (r1 == 3) goto L26;
        if (r1 == 4) goto L24;
        return r0.I(Math.multiplyExact(r132.f444b.f283a, r11), 0);
    L24:
        return r0.I(r11, 0);
    L26:
        return r0.I(r11 / 1000, (r11 % 1000) * 1000000);
    L28:
        return r0.I((r11 / 1000000000) * 1000, 0).plusNanos((r11 % 1000000000) * 1000);
    L30:
        return r0.plusNanos(r11);
    L31:
        Duration r133 = r13.getDuration();
        r133.getClass();
        if (r11 != 0) goto L35;
        r133 = r0;
    L41:
        return r0.I(r133.getSeconds(), 0).plusNanos(r133.getNano());
    L35:
        if (r11 == 1) goto L41;
        BigInteger r112 = BigDecimal.valueOf(r133.f283a).add(BigDecimal.valueOf(r133.f284b, 9)).multiply(BigDecimal.valueOf(r11)).movePointRight(9).toBigIntegerExact();
        BigInteger[] r12 = r112.divideAndRemainder(f282c);
        if (r12[0].bitLength() > 63) goto L43;
        r133 = ofSeconds(r12[0].longValue(), r12[1].intValue());
        goto L41
    L43:
        throw new ArithmeticException("Exceeds capacity of Duration: " + r112);
    L45:
        throw new j$.time.temporal.r("Unit must not have an estimated duration");
    }

    public static Duration ofMillis(long r4) {
        long r2 = r4 / 1000;
        int r42 = (int) (r4 % 1000);
        if (r42 >= 0) goto L6;
        r42 = r42 + 1000;
        r2 = r2 - 1;
    L6:
        return D(r2, r42 * DurationKt.NANOS_IN_MILLIS);
    }

    public static Duration ofMinutes(long r2) {
        return D(Math.multiplyExact(r2, 60), 0);
    }

    public static Duration ofNanos(long r4) {
        long r2 = r4 / 1000000000;
        int r42 = (int) (r4 % 1000000000);
        if (r42 >= 0) goto L6;
        r42 = (int) (r42 + 1000000000);
        r2 = r2 - 1;
    L6:
        return D(r2, r42);
    }

    public static Duration ofSeconds(long r1) {
        return D(r1, 0);
    }

    public static Duration ofSeconds(long r4, long r6) {
        return D(Math.addExact(r4, Math.floorDiv(r6, 1000000000)), (int) Math.floorMod(r6, 1000000000));
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 1, this);
    }

    public final Duration I(long r5, long r7) {
        if ((r5 | r7) != 0) goto L6;
        return this;
    L6:
        return ofSeconds(Math.addExact(Math.addExact(this.f283a, r5), r7 / 1000000000), this.f284b + (r7 % 1000000000));
    }

    @Override // java.lang.Comparable
    public final int compareTo(Duration r5) {
        Duration r52 = r5;
        int r0 = Long.compare(this.f283a, r52.f283a);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.f284b - r52.f284b;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof Duration) == false) goto L12;
        Duration r82 = (Duration) r8;
        if (this.f283a != r82.f283a) goto L12;
        if (this.f284b != r82.f284b) goto L12;
        return true;
    L12:
        return false;
    }

    public int getNano() {
        return this.f284b;
    }

    public long getSeconds() {
        return this.f283a;
    }

    public int hashCode() {
        long r0 = this.f283a;
        int r4 = this.f284b * 51;
        return r4 + ((int) (r0 ^ (r0 >>> 32)));
    }

    public Duration plusNanos(long r3) {
        return I(0, r3);
    }

    @Override // j$.time.temporal.o
    public final Temporal s(Temporal r5) {
        long r0 = this.f283a;
        if (r0 == 0) goto L5;
        r5 = r5.b(r0, ChronoUnit.SECONDS);
    L5:
        int r4 = this.f284b;
        if (r4 != 0) goto L8;
        return r5;
    L8:
        return r5.b(r4, ChronoUnit.NANOS);
    }

    public long toMillis() {
        long r0 = this.f283a;
        long r2 = this.f284b;
        if (r0 >= 0) goto L6;
        r0 = r0 + 1;
        r2 = r2 - 1000000000;
    L6:
        return Math.addExact(Math.multiplyExact(r0, 1000), r2 / 1000000);
    }

    public final String toString() {
        if (this != ZERO) goto L6;
        return "PT0S";
    L6:
        long r0 = this.f283a;
        if (r0 < 0) goto L9;
    L11:
        long r6 = r0 / 3600;
        int r4 = (int) ((r0 % 3600) / 60);
        int r02 = (int) (r0 % 60);
        StringBuilder r1 = new StringBuilder(24);
        r1.append("PT");
        if (r6 == 0) goto L14;
        r1.append(r6);
        r1.append('H');
    L14:
        if (r4 == 0) goto L16;
        r1.append(r4);
        r1.append('M');
    L16:
        if (r02 != 0) goto L24;
        if (this.f284b != 0) goto L24;
        if (r1.length() <= 2) goto L24;
        return r1.toString();
    L24:
        if (this.f283a < 0) goto L26;
    L30:
        r1.append(r02);
    L32:
        if (this.f284b <= 0) goto L41;
        int r03 = r1.length();
        long r42 = this.f283a;
        int r10 = this.f284b;
        if (r42 >= 0) goto L36;
        r1.append(2000000000 - r10);
    L38:
        if (r1.charAt(r1.length() - 1) != '0') goto L40;
        r1.setLength(r1.length() - 1);
        goto L38
    L40:
        r1.setCharAt(r03, '.');
        goto L41
    L36:
        r1.append(r10 + 1000000000);
    L41:
        r1.append('S');
        return r1.toString();
    L26:
        if (this.f284b <= 0) goto L30;
        if (r02 != 0) goto L29;
        r1.append("-0");
        goto L32
    L29:
        r1.append(r02);
        goto L32
    L9:
        if (this.f284b <= 0) goto L11;
        r0 = r0 + 1;
        goto L11
    }
}
