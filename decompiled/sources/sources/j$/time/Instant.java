package j$.time;

import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class Instant implements Temporal, j$.time.temporal.m, Comparable<Instant>, Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final Instant f285c = null;
    public static final Instant d = null;
    public static final Instant e = null;
    private static final long serialVersionUID = -665713676816604388L;

    /* renamed from: a, reason: collision with root package name */
    public final long f286a;

    /* renamed from: b, reason: collision with root package name */
    public final int f287b;

    static {
        f285c = new Instant(0, 0);
        d = ofEpochSecond(-31557014167219200L, 0);
        e = ofEpochSecond(31556889864403199L, 999999999);
    }

    public Instant(long r1, int r3) {
        this.f286a = r1;
        this.f287b = r3;
    }

    public static Instant D(j$.time.temporal.l r4) {
        if ((r4 instanceof Instant) == true) goto L5;
        Objects.requireNonNull(r4, "temporal");
        return ofEpochSecond(r4.k(j$.time.temporal.a.INSTANT_SECONDS), r4.g(j$.time.temporal.a.NANO_OF_SECOND));
    L9:
        e = move-exception;
        j$.nio.file.b.g("Unable to obtain Instant from TemporalAccessor: ", r4, r4.getClass().getName(), e);
        return null;
    L5:
        return (Instant) r4;
    }

    public static Instant now() {
        a.f320b.getClass();
        return ofEpochMilli(System.currentTimeMillis());
    }

    public static Instant ofEpochMilli(long r4) {
        return s(Math.floorDiv(r4, 1000), ((int) Math.floorMod(r4, 1000)) * DurationKt.NANOS_IN_MILLIS);
    }

    public static Instant ofEpochSecond(long r4, long r6) {
        return s(Math.addExact(r4, Math.floorDiv(r6, 1000000000)), (int) Math.floorMod(r6, 1000000000));
    }

    public static Instant parse(CharSequence r3) {
        return (Instant) DateTimeFormatter.f.b(r3, new e(0));
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static Instant s(long r4, int r6) {
        if ((r6 | r4) != 0) goto L7;
        return f285c;
    L7:
        if (r4 >= (-31557014167219200L)) goto L9;
    L12:
        j$.nio.file.b.h("Instant exceeds minimum or maximum instant");
        return null;
    L9:
        if (r4 > 31556889864403199L) goto L12;
        return new Instant(r4, r6);
    }

    private Object writeReplace() {
        return new o((byte) 2, this);
    }

    public final Instant I(long r5, long r7) {
        if ((r5 | r7) != 0) goto L6;
        return this;
    L6:
        return ofEpochSecond(Math.addExact(Math.addExact(this.f286a, r5), r7 / 1000000000), this.f287b + (r7 % 1000000000));
    }

    public final Instant K(long r8, TemporalUnit r10) {
        if ((r10 instanceof ChronoUnit) == false) goto L25;
        switch(f.f369b[((ChronoUnit) r10).ordinal()]) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            default: goto L6;
        };
    L6:
        j$.nio.file.b.d("Unsupported unit: ", r10);
        return null;
    L9:
        return plusSeconds(Math.multiplyExact(r8, 86400));
    L11:
        return plusSeconds(Math.multiplyExact(r8, 43200));
    L13:
        return plusSeconds(Math.multiplyExact(r8, 3600));
    L15:
        return plusSeconds(Math.multiplyExact(r8, 60));
    L17:
        return plusSeconds(r8);
    L19:
        return I(r8 / 1000, (r8 % 1000) * 1000000);
    L21:
        return I(r8 / 1000000, (r8 % 1000000) * 1000);
    L23:
        return I(0, r8);
    L25:
        return (Instant) r10.s(this, r8);
    }

    public final long S(Instant r9) {
        long r0 = Math.subtractExact(r9.f286a, this.f286a);
        long r8 = r9.f287b - this.f287b;
        if (r0 > 0) goto L5;
    L8:
        if (r0 < 0) goto L10;
        return r0;
    L10:
        if (r8 > 0) goto L12;
        return r0;
    L12:
        return r0 + 1;
    L5:
        if (r8 >= 0) goto L8;
        return r0 - 1;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal a(long r3, j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L31;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r5;
        r0.a0(r3);
        int r02 = f.f368a[r0.ordinal()];
        if (r02 == 1) goto L27;
        if (r02 != 2) goto L9;
        int r32 = ((int) r3) * 1000;
        if (r32 != this.f287b) goto L25;
        return this;
    L25:
        return s(this.f286a, r32);
    L9:
        if (r02 != 3) goto L11;
        int r33 = ((int) r3) * DurationKt.NANOS_IN_MILLIS;
        if (r33 != this.f287b) goto L21;
        return this;
    L21:
        return s(this.f286a, r33);
    L11:
        if (r02 != 4) goto L17;
        if (r3 != this.f286a) goto L15;
        return this;
    L15:
        return s(r3, this.f287b);
    L17:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r5));
    L27:
        if (r3 != this.f287b) goto L29;
        return this;
    L29:
        return s(this.f286a, (int) r3);
    L31:
        return (Instant) r5.Y(this, r3);
    }

    public OffsetDateTime atOffset(ZoneOffset r1) {
        return OffsetDateTime.s(this, r1);
    }

    public ZonedDateTime atZone(ZoneId r3) {
        Objects.requireNonNull(r3, "zone");
        return ZonedDateTime.s(getEpochSecond(), getNano(), r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return K(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        Instant this = K(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.K(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // java.lang.Comparable
    public final int compareTo(Instant r5) {
        Instant r52 = r5;
        int r0 = Long.compare(this.f286a, r52.f286a);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.f287b - r52.f287b;
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f465c) goto L7;
        return ChronoUnit.NANOS;
    L7:
        if (r2 != j$.time.temporal.q.f464b) goto L9;
        return null;
    L9:
        if (r2 != j$.time.temporal.q.f463a) goto L11;
        return null;
    L11:
        if (r2 != j$.time.temporal.q.e) goto L13;
        return null;
    L13:
        if (r2 != j$.time.temporal.q.d) goto L15;
        return null;
    L15:
        if (r2 != j$.time.temporal.q.f) goto L17;
        return null;
    L17:
        if (r2 != j$.time.temporal.q.g) goto L20;
        return null;
    L20:
        return r2.l(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        return r4.a(this.f286a, j$.time.temporal.a.INSTANT_SECONDS).a(this.f287b, j$.time.temporal.a.NANO_OF_SECOND);
    }

    public final boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof Instant) == false) goto L12;
        Instant r82 = (Instant) r8;
        if (this.f286a != r82.f286a) goto L12;
        if (this.f287b != r82.f287b) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r4) {
        if ((r4 instanceof j$.time.temporal.a) == false) goto L23;
        int r0 = f.f368a[((j$.time.temporal.a) r4).ordinal()];
        if (r0 == 1) goto L21;
        if (r0 == 2) goto L19;
        if (r0 == 3) goto L17;
        if (r0 != 4) goto L15;
        j$.time.temporal.a r02 = j$.time.temporal.a.INSTANT_SECONDS;
        long r1 = this.f286a;
        r02.f447b.a(r1, r02);
    L15:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r4));
    L17:
        return this.f287b / DurationKt.NANOS_IN_MILLIS;
    L19:
        return this.f287b / 1000;
    L21:
        return this.f287b;
    L23:
        return super.l(r4).a(r4.S(this), r4);
    }

    public long getEpochSecond() {
        return this.f286a;
    }

    public int getNano() {
        return this.f287b;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (Instant) r1.e(this);
    }

    public final int hashCode() {
        long r0 = this.f286a;
        int r4 = this.f287b * 51;
        return r4 + ((int) (r0 ^ (r0 >>> 32)));
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L15;
        return false;
    L15:
        if (r2.s(this) == false) goto L25;
        return true;
    L25:
        return false;
    L5:
        if (r2 != j$.time.temporal.a.INSTANT_SECONDS) goto L7;
        return true;
    L7:
        if (r2 != j$.time.temporal.a.NANO_OF_SECOND) goto L9;
        return true;
    L9:
        if (r2 != j$.time.temporal.a.MICRO_OF_SECOND) goto L11;
        return true;
    L11:
        if (r2 == j$.time.temporal.a.MILLI_OF_SECOND) goto L23;
        return false;
    L23:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L22;
        int r0 = f.f368a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 != 1) goto L7;
        int r2 = this.f287b;
    L18:
        return r2;
    L7:
        if (r0 != 2) goto L9;
        r2 = this.f287b / 1000;
        goto L18
    L9:
        if (r0 != 3) goto L11;
        r2 = this.f287b / DurationKt.NANOS_IN_MILLIS;
        goto L18
    L11:
        if (r0 != 4) goto L15;
        return this.f286a;
    L15:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r3));
    L22:
        return r3.S(this);
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r8, TemporalUnit r9) {
        Instant r82 = D(r8);
        if ((r9 instanceof ChronoUnit) == false) goto L25;
        switch(f.f369b[((ChronoUnit) r9).ordinal()]) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            default: goto L6;
        };
    L6:
        j$.nio.file.b.d("Unsupported unit: ", r9);
        return 0;
    L9:
        return S(r82) / 86400;
    L11:
        return S(r82) / 43200;
    L13:
        return S(r82) / 3600;
    L15:
        return S(r82) / 60;
    L17:
        return S(r82);
    L19:
        return Math.subtractExact(r82.toEpochMilli(), toEpochMilli());
    L21:
        return Math.addExact(Math.multiplyExact(Math.subtractExact(r82.f286a, this.f286a), 1000000000), r82.f287b - this.f287b) / 1000;
    L23:
        return Math.addExact(Math.multiplyExact(Math.subtractExact(r82.f286a, this.f286a), 1000000000), r82.f287b - this.f287b);
    L25:
        return r9.between(this, r82);
    }

    public Instant plusSeconds(long r3) {
        return I(r3, 0);
    }

    public long toEpochMilli() {
        long r0 = this.f286a;
        if (r0 >= 0) goto L9;
        if (this.f287b <= 0) goto L9;
        return Math.addExact(Math.multiplyExact(r0 + 1, 1000), (this.f287b / DurationKt.NANOS_IN_MILLIS) - 1000);
    L9:
        return Math.addExact(Math.multiplyExact(r0, 1000), this.f287b / DurationKt.NANOS_IN_MILLIS);
    }

    public String toString() {
        return DateTimeFormatter.f.a(this);
    }
}
