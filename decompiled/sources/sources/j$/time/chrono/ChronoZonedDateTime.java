package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.b;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface ChronoZonedDateTime<D extends b> extends Temporal, Comparable<ChronoZonedDateTime<?>> {
    ChronoLocalDateTime A();

    ChronoZonedDateTime G(ZoneId r1);

    default long Z() {
        return ((m().L() * 86400) + toLocalTime().g0()) - getOffset().getTotalSeconds();
    }

    @Override // j$.time.temporal.Temporal
    ChronoZonedDateTime a(long r1, j$.time.temporal.p r3);

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal a(long r1, j$.time.temporal.p r3) {
        return a(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    ChronoZonedDateTime b(long r1, TemporalUnit r3);

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal b(long r1, TemporalUnit r3) {
        return b(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    default ChronoZonedDateTime c(long r2, TemporalUnit r4) {
        return j.s(f(), super.c(r2, r4));
    }

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal c(long r1, TemporalUnit r3) {
        return c(r1, r3);
    }

    @Override // java.lang.Comparable
    /* bridge */ /* synthetic */ default int compareTo(ChronoZonedDateTime<?> r1) {
        return t(r1);
    }

    @Override // j$.time.temporal.l
    default Object d(j$.time.e r2) {
        if (r2 == j$.time.temporal.q.e) goto L26;
        if (r2 == j$.time.temporal.q.f463a) goto L26;
        if (r2 != j$.time.temporal.q.d) goto L12;
        return getOffset();
    L12:
        if (r2 != j$.time.temporal.q.g) goto L16;
        return toLocalTime();
    L16:
        if (r2 != j$.time.temporal.q.f464b) goto L20;
        return f();
    L20:
        if (r2 != j$.time.temporal.q.f465c) goto L24;
        return ChronoUnit.NANOS;
    L24:
        return r2.l(this);
    L26:
        return getZone();
    }

    default k f() {
        return m().f();
    }

    @Override // j$.time.temporal.l
    default int g(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        int r0 = h.f339a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 == 1) goto L13;
        if (r0 == 2) goto L11;
        return A().g(r3);
    L11:
        return getOffset().getTotalSeconds();
    L13:
        throw new j$.time.temporal.r("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
    L15:
        return super.g(r3);
    }

    ZoneOffset getOffset();

    ZoneId getZone();

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal h(LocalDate r1) {
        return j(r1);
    }

    default ChronoZonedDateTime j(j$.time.temporal.m r2) {
        return j.s(f(), r2.e(this));
    }

    @Override // j$.time.temporal.l
    default long k(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        int r0 = h.f339a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 == 1) goto L13;
        if (r0 == 2) goto L11;
        return A().k(r3);
    L11:
        return getOffset().getTotalSeconds();
    L13:
        return Z();
    L15:
        return r3.S(this);
    }

    @Override // j$.time.temporal.l
    default j$.time.temporal.s l(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L14;
        if (r2 == j$.time.temporal.a.INSTANT_SECONDS) goto L12;
        if (r2 == j$.time.temporal.a.OFFSET_SECONDS) goto L12;
        return A().l(r2);
    L12:
        return ((j$.time.temporal.a) r2).f447b;
    L14:
        return r2.D(this);
    }

    default b m() {
        return A().m();
    }

    ChronoZonedDateTime o(ZoneId r1);

    default int t(ChronoZonedDateTime r5) {
        int r0 = Long.compare(Z(), r5.Z());
        if (r0 != 0) goto L12;
        r0 = toLocalTime().getNano() - r5.toLocalTime().getNano();
        if (r0 != 0) goto L12;
        r0 = A().R(r5.A());
        if (r0 != 0) goto L12;
        r0 = getZone().getId().compareTo(r5.getZone().getId());
        if (r0 != 0) goto L12;
        k r4 = f();
        k r52 = r5.f();
        return ((a) r4).getId().compareTo(r52.getId());
    L12:
        return r0;
    }

    default Instant toInstant() {
        return Instant.ofEpochSecond(Z(), toLocalTime().getNano());
    }

    default LocalTime toLocalTime() {
        return A().toLocalTime();
    }
}
