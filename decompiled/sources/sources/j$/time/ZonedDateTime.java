package j$.time;

import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.time.zone.ZoneRules;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class ZonedDateTime implements Temporal, ChronoZonedDateTime<LocalDate>, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;

    /* renamed from: a, reason: collision with root package name */
    public final LocalDateTime f317a;

    /* renamed from: b, reason: collision with root package name */
    public final ZoneOffset f318b;

    /* renamed from: c, reason: collision with root package name */
    public final ZoneId f319c;

    public ZonedDateTime(LocalDateTime r1, ZoneOffset r2, ZoneId r3) {
        this.f317a = r1;
        this.f318b = r2;
        this.f319c = r3;
    }

    public static ZonedDateTime D(j$.time.temporal.l r5) {
        if ((r5 instanceof ZonedDateTime) == false) goto L17;
        return (ZonedDateTime) r5;
    L17:
        ZoneId r1 = ZoneId.s(r5);     // Catch: b -> L11
        j$.time.temporal.a r2 = j$.time.temporal.a.INSTANT_SECONDS;     // Catch: b -> L11
        if (r5.i(r2) == false) goto L14;
        return s(r5.k(r2), r5.g(j$.time.temporal.a.NANO_OF_SECOND), r1);
    L14:
        return I(LocalDateTime.of(LocalDate.I(r5), LocalTime.I(r5)), null, r1);
    L11:
        e = move-exception;
        j$.nio.file.b.g("Unable to obtain ZonedDateTime from TemporalAccessor: ", r5, r5.getClass().getName(), e);
        return null;
    }

    public static ZonedDateTime I(LocalDateTime r5, ZoneOffset r6, ZoneId r7) {
        Objects.requireNonNull(r5, "localDateTime");
        Objects.requireNonNull(r7, "zone");
        if ((r7 instanceof ZoneOffset) == true) goto L5;
        ZoneRules r0 = r7.getRules();
        List r1 = r0.f(r5);
        if (r1.size() != 1) goto L10;
        r6 = (ZoneOffset) r1.get(0);
    L18:
        return new ZonedDateTime(r5, r6, r7);
    L10:
        if (r1.size() != 0) goto L12;
        j$.time.zone.b r62 = r0.e(r5);
        r5 = r5.Y(Duration.ofSeconds(r62.d.getTotalSeconds() - r62.f476c.getTotalSeconds()).getSeconds());
        r6 = r62.d;
        goto L18
    L12:
        if (r6 != null) goto L14;
    L16:
        r6 = (ZoneOffset) r1.get(0);
        Objects.requireNonNull(r6, "offset");
        goto L18
    L14:
        if (r1.contains(r6) == false) goto L16;
    L5:
        return new ZonedDateTime(r5, (ZoneOffset) r7, r7);
    }

    public static ZonedDateTime ofInstant(LocalDateTime r2, ZoneOffset r3, ZoneId r4) {
        Objects.requireNonNull(r2, "localDateTime");
        Objects.requireNonNull(r3, "offset");
        Objects.requireNonNull(r4, "zone");
        if (r4.getRules().f(r2).contains(r3) == false) goto L7;
        return new ZonedDateTime(r2, r3, r4);
    L7:
        return s(r2.toEpochSecond(r3), r2.f293b.getNano(), r4);
    }

    public static ZonedDateTime parse(CharSequence r2, DateTimeFormatter r3) {
        Objects.requireNonNull(r3, "formatter");
        return (ZonedDateTime) r3.b(r2, new e(1));
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static ZonedDateTime s(long r3, int r5, ZoneId r6) {
        ZoneOffset r0 = r6.getRules().getOffset(Instant.ofEpochSecond(r3, r5));
        return new ZonedDateTime(LocalDateTime.K(r3, r5, r0), r0, r6);
    }

    private Object writeReplace() {
        return new o((byte) 6, this);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* bridge */ /* synthetic */ ChronoLocalDateTime A() {
        return toLocalDateTime();
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime G(ZoneId r2) {
        Objects.requireNonNull(r2, "zone");
        if (this.f319c.equals(r2) == false) goto L6;
        return this;
    L6:
        return I(this.f317a, this.f318b, r2);
    }

    public final ZonedDateTime K(long r3, TemporalUnit r5) {
        if ((r5 instanceof ChronoUnit) == false) goto L17;
        ChronoUnit r0 = (ChronoUnit) r5;
        if (r0.compareTo(ChronoUnit.DAYS) >= 0) goto L7;
    L9:
        boolean r02 = false;
    L10:
        LocalDateTime r1 = this.f317a;
        if (r02 == false) goto L15;
        LocalDateTime r32 = r1.S(r3, r5);
        ZoneId r4 = this.f319c;
        return I(r32, this.f318b, r4);
    L15:
        return ofInstant(r1.S(r3, r5), this.f318b, this.f319c);
    L7:
        if (r0 == ChronoUnit.FOREVER) goto L9;
        r02 = true;
        goto L10
    L17:
        return (ZonedDateTime) r5.s(this, r3);
    }

    public final ZonedDateTime S(ZoneOffset r3) {
        if (r3.equals(this.f318b) == false) goto L5;
    L8:
        return this;
    L5:
        if (this.f319c.getRules().f(this.f317a).contains(r3) == false) goto L8;
        return new ZonedDateTime(this.f317a, r3, this.f319c);
    }

    public final ZonedDateTime Y(long r4, j$.time.temporal.p r6) {
        if ((r6 instanceof j$.time.temporal.a) == false) goto L15;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r6;
        int r1 = s.f441a[r0.ordinal()];
        if (r1 == 1) goto L13;
        if (r1 == 2) goto L11;
        LocalDateTime r42 = this.f317a.b0(r4, r6);
        ZoneId r5 = this.f319c;
        return I(r42, this.f318b, r5);
    L11:
        return S(ZoneOffset.ofTotalSeconds(r0.f447b.a(r4, r0)));
    L13:
        return s(r4, this.f317a.f293b.getNano(), this.f319c);
    L15:
        return (ZonedDateTime) r6.Y(this, r4);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ ChronoZonedDateTime a(long r1, j$.time.temporal.p r3) {
        return Y(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return Y(r1, r3);
    }

    public final ZonedDateTime a0(j$.time.temporal.m r3) {
        if ((r3 instanceof LocalDate) == false) goto L7;
        LocalDateTime r32 = LocalDateTime.of((LocalDate) r3, this.f317a.toLocalTime());
        ZoneId r0 = this.f319c;
        return I(r32, this.f318b, r0);
    L7:
        if ((r3 instanceof LocalTime) == false) goto L11;
        LocalDateTime r33 = LocalDateTime.of(this.f317a.toLocalDate(), (LocalTime) r3);
        ZoneId r02 = this.f319c;
        return I(r33, this.f318b, r02);
    L11:
        if ((r3 instanceof LocalDateTime) == false) goto L15;
        ZoneId r03 = this.f319c;
        return I((LocalDateTime) r3, this.f318b, r03);
    L15:
        if ((r3 instanceof OffsetDateTime) == false) goto L19;
        OffsetDateTime r34 = (OffsetDateTime) r3;
        LocalDateTime r04 = r34.toLocalDateTime();
        ZoneId r2 = this.f319c;
        return I(r04, r34.getOffset(), r2);
    L19:
        if ((r3 instanceof Instant) == false) goto L23;
        Instant r35 = (Instant) r3;
        return s(r35.getEpochSecond(), r35.getNano(), this.f319c);
    L23:
        if ((r3 instanceof ZoneOffset) == false) goto L27;
        return S((ZoneOffset) r3);
    L27:
        return (ZonedDateTime) r3.e(this);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ ChronoZonedDateTime b(long r1, TemporalUnit r3) {
        return K(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return K(r1, r3);
    }

    public final ZonedDateTime b0(ZoneId r3) {
        Objects.requireNonNull(r3, "zone");
        if (this.f319c.equals(r3) == false) goto L6;
        return this;
    L6:
        return s(this.f317a.toEpochSecond(this.f318b), this.f317a.f293b.getNano(), r3);
    }

    @Override // j$.time.temporal.Temporal
    public final ChronoZonedDateTime c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        ZonedDateTime this = K(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.K(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        ZonedDateTime this = K(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.K(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f) goto L7;
        return toLocalDate();
    L7:
        return super.d(r2);
    }

    public final boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if ((r3 instanceof ZonedDateTime) == false) goto L14;
        ZonedDateTime r32 = (ZonedDateTime) r3;
        if (this.f317a.equals(r32.f317a) == true) goto L9;
        return false;
    L9:
        if (this.f318b.equals(r32.f318b) == true) goto L11;
        return false;
    L11:
        if (this.f319c.equals(r32.f319c) == false) goto L19;
        return true;
    L19:
        return false;
    L14:
        return false;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        int r0 = s.f441a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 == 1) goto L13;
        if (r0 == 2) goto L11;
        return this.f317a.g(r3);
    L11:
        return getOffset().getTotalSeconds();
    L13:
        throw new j$.time.temporal.r("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
    L15:
        return super.g(r3);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public ZoneOffset getOffset() {
        return this.f318b;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public ZoneId getZone() {
        return this.f319c;
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal h(LocalDate r1) {
        return a0(r1);
    }

    public final int hashCode() {
        int r0 = this.f317a.hashCode() ^ this.f318b.f315b;
        return Integer.rotateLeft(this.f319c.hashCode(), 3) ^ r0;
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

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* bridge */ /* synthetic */ ChronoZonedDateTime j(j$.time.temporal.m r1) {
        return a0(r1);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        int r0 = s.f441a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 == 1) goto L13;
        if (r0 == 2) goto L11;
        return this.f317a.k(r3);
    L11:
        return getOffset().getTotalSeconds();
    L13:
        return Z();
    L15:
        return r3.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L14;
        if (r2 == j$.time.temporal.a.INSTANT_SECONDS) goto L12;
        if (r2 == j$.time.temporal.a.OFFSET_SECONDS) goto L12;
        return this.f317a.l(r2);
    L12:
        return ((j$.time.temporal.a) r2).f447b;
    L14:
        return r2.D(this);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* bridge */ /* synthetic */ j$.time.chrono.b m() {
        return toLocalDate();
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r3, TemporalUnit r4) {
        ZonedDateTime r32 = D(r3);
        if ((r4 instanceof ChronoUnit) == false) goto L13;
        ZonedDateTime r33 = r32.b0(this.f319c);
        ChronoUnit r0 = (ChronoUnit) r4;
        if (r0.compareTo(ChronoUnit.DAYS) < 0) goto L11;
        if (r0 == ChronoUnit.FOREVER) goto L11;
        return this.f317a.n(r33.f317a, r4);
    L11:
        return OffsetDateTime.of(this.f317a, this.f318b).n(OffsetDateTime.of(r33.f317a, r33.f318b), r4);
    L13:
        return r4.between(this, r32);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* bridge */ /* synthetic */ ChronoZonedDateTime o(ZoneId r1) {
        return b0(r1);
    }

    public LocalDate toLocalDate() {
        return this.f317a.toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return this.f317a;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final LocalTime toLocalTime() {
        return this.f317a.toLocalTime();
    }

    public final String toString() {
        String r0 = this.f317a.toString() + this.f318b.f316c;
        ZoneOffset r1 = this.f318b;
        ZoneId r3 = this.f319c;
        if (r1 != r3) goto L5;
        return r0;
    L5:
        return r0 + "[" + r3.toString() + "]";
    }
}
