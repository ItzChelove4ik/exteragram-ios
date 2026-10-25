package j$.time;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class OffsetDateTime implements Temporal, j$.time.temporal.m, Comparable<OffsetDateTime>, Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f300c = 0;
    private static final long serialVersionUID = 2287754244819255394L;

    /* renamed from: a, reason: collision with root package name */
    public final LocalDateTime f301a;

    /* renamed from: b, reason: collision with root package name */
    public final ZoneOffset f302b;

    static {
        LocalDateTime r0 = LocalDateTime.f291c;
        ZoneOffset r1 = ZoneOffset.g;
        r0.getClass();
        of(r0, r1);
        LocalDateTime r02 = LocalDateTime.d;
        ZoneOffset r12 = ZoneOffset.f;
        r02.getClass();
        of(r02, r12);
    }

    public OffsetDateTime(LocalDateTime r2, ZoneOffset r3) {
        Objects.requireNonNull(r2, "dateTime");
        this.f301a = r2;
        Objects.requireNonNull(r3, "offset");
        this.f302b = r3;
    }

    public static OffsetDateTime of(LocalDateTime r1, ZoneOffset r2) {
        return new OffsetDateTime(r1, r2);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static OffsetDateTime s(Instant r2, ZoneId r3) {
        Objects.requireNonNull(r2, "instant");
        Objects.requireNonNull(r3, "zone");
        ZoneOffset r32 = r3.getRules().getOffset(r2);
        return new OffsetDateTime(LocalDateTime.K(r2.getEpochSecond(), r2.getNano(), r32), r32);
    }

    private Object writeReplace() {
        return new o((byte) 10, this);
    }

    public final OffsetDateTime D(long r2, TemporalUnit r4) {
        if ((r4 instanceof ChronoUnit) == false) goto L7;
        return I(this.f301a.S(r2, r4), this.f302b);
    L7:
        return (OffsetDateTime) r4.s(this, r2);
    }

    public final OffsetDateTime I(LocalDateTime r2, ZoneOffset r3) {
        if (this.f301a != r2) goto L8;
        if (this.f302b.equals(r3) == false) goto L8;
        return this;
    L8:
        return new OffsetDateTime(r2, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal a(long r5, j$.time.temporal.p r7) {
        if ((r7 instanceof j$.time.temporal.a) == false) goto L15;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r7;
        int r1 = m.f431a[r0.ordinal()];
        LocalDateTime r2 = this.f301a;
        if (r1 == 1) goto L13;
        if (r1 == 2) goto L11;
        return I(r2.b0(r5, r7), this.f302b);
    L11:
        return I(r2, ZoneOffset.ofTotalSeconds(r0.f447b.a(r5, r0)));
    L13:
        return s(Instant.ofEpochSecond(r5, r2.f293b.getNano()), this.f302b);
    L15:
        return (OffsetDateTime) r7.Y(this, r5);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return D(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        OffsetDateTime this = D(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.D(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // java.lang.Comparable
    public final int compareTo(OffsetDateTime r5) {
        OffsetDateTime r52 = r5;
        if (getOffset().equals(r52.getOffset()) == false) goto L5;
        int r0 = toLocalDateTime().R(r52.toLocalDateTime());
    L8:
        if (r0 == 0) goto L10;
        return r0;
    L10:
        return toLocalDateTime().R(r52.toLocalDateTime());
    L5:
        r0 = Long.compare(this.f301a.toEpochSecond(this.f302b), r52.f301a.toEpochSecond(r52.f302b));
        if (r0 != 0) goto L8;
        r0 = this.f301a.toLocalTime().getNano() - r52.f301a.toLocalTime().getNano();
        goto L8
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 == j$.time.temporal.q.d) goto L30;
        if (r2 == j$.time.temporal.q.e) goto L30;
        if (r2 != j$.time.temporal.q.f463a) goto L12;
        return null;
    L12:
        if (r2 != j$.time.temporal.q.f) goto L16;
        return this.f301a.toLocalDate();
    L16:
        if (r2 != j$.time.temporal.q.g) goto L20;
        return this.f301a.toLocalTime();
    L20:
        if (r2 != j$.time.temporal.q.f464b) goto L24;
        return j$.time.chrono.r.f353c;
    L24:
        if (r2 != j$.time.temporal.q.f465c) goto L28;
        return ChronoUnit.NANOS;
    L28:
        return r2.l(this);
    L30:
        return getOffset();
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        return r4.a(this.f301a.toLocalDate().L(), j$.time.temporal.a.EPOCH_DAY).a(this.f301a.toLocalTime().f0(), j$.time.temporal.a.NANO_OF_DAY).a(getOffset().getTotalSeconds(), j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof OffsetDateTime) == false) goto L12;
        OffsetDateTime r52 = (OffsetDateTime) r5;
        if (this.f301a.equals(r52.f301a) == false) goto L12;
        if (this.f302b.equals(r52.f302b) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        int r0 = m.f431a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 == 1) goto L13;
        if (r0 == 2) goto L11;
        return this.f301a.g(r3);
    L11:
        return getOffset().getTotalSeconds();
    L13:
        throw new j$.time.temporal.r("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
    L15:
        return super.g(r3);
    }

    public ZoneOffset getOffset() {
        return this.f302b;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal h(LocalDate r2) {
        if (r2 != null) goto L6;
        return (OffsetDateTime) r2.e(this);
    L6:
        return I(this.f301a.d0(r2), this.f302b);
    }

    public final int hashCode() {
        int r0 = this.f301a.hashCode();
        return this.f302b.f315b ^ r0;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L10;
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.s(this) == true) goto L13;
        return false;
    L13:
        return true;
    L10:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        int r0 = m.f431a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 == 1) goto L13;
        if (r0 == 2) goto L11;
        return this.f301a.k(r3);
    L11:
        return getOffset().getTotalSeconds();
    L13:
        return this.f301a.toEpochSecond(this.f302b);
    L15:
        return r3.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L14;
        if (r2 == j$.time.temporal.a.INSTANT_SECONDS) goto L12;
        if (r2 == j$.time.temporal.a.OFFSET_SECONDS) goto L12;
        return this.f301a.l(r2);
    L12:
        return ((j$.time.temporal.a) r2).f447b;
    L14:
        return r2.D(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11, types: [j$.time.OffsetDateTime] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    @Override // j$.time.temporal.Temporal
    public final long n(OffsetDateTime r4, TemporalUnit r5) {
        if ((r4 instanceof OffsetDateTime) == false) goto L24;
        r4 = (OffsetDateTime) r4;
    L11:
        if ((r5 instanceof ChronoUnit) == false) goto L19;
        ZoneOffset r0 = this.f302b;
        boolean r1 = r0.equals(r4.f302b);
        OffsetDateTime r42 = r4;
        if (r1 == true) goto L17;
        r42 = new OffsetDateTime(r4.f301a.Y(r0.getTotalSeconds() - r4.f302b.getTotalSeconds()), r0);
    L17:
        return this.f301a.n(r42.f301a, r5);
    L19:
        return r5.between(this, r4);
    L24:
        ZoneOffset r02 = ZoneOffset.Y(r4);     // Catch: b -> L20
        LocalDate r12 = (LocalDate) r4.d(j$.time.temporal.q.f);     // Catch: b -> L20
        LocalTime r2 = (LocalTime) r4.d(j$.time.temporal.q.g);     // Catch: b -> L20
        if (r12 == null) goto L9;
        if (r2 == null) goto L9;
        r4 = new OffsetDateTime(LocalDateTime.of(r12, r2), r02);     // Catch: b -> L20
    L23:
    L9:
        r4 = s(Instant.D(r4), r02);     // Catch: b -> L20
    L20:
        e = move-exception;
        j$.nio.file.b.g("Unable to obtain OffsetDateTime from TemporalAccessor: ", r4, r4.getClass().getName(), e);
        return 0;
    }

    public LocalDateTime toLocalDateTime() {
        return this.f301a;
    }

    public final String toString() {
        return this.f301a.toString() + this.f302b.f316c;
    }
}
