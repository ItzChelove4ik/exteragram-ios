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
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface ChronoLocalDateTime<D extends b> extends Temporal, j$.time.temporal.m, Comparable<ChronoLocalDateTime<?>> {
    ChronoZonedDateTime H(ZoneId r1);

    default int R(ChronoLocalDateTime r3) {
        int r0 = m().X(r3.m());
        if (r0 != 0) goto L8;
        r0 = toLocalTime().s(r3.toLocalTime());
        if (r0 != 0) goto L8;
        k r2 = f();
        k r32 = r3.f();
        return ((a) r2).getId().compareTo(r32.getId());
    L8:
        return r0;
    }

    @Override // j$.time.temporal.Temporal
    ChronoLocalDateTime a(long r1, j$.time.temporal.p r3);

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal a(long r1, j$.time.temporal.p r3) {
        return a(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    ChronoLocalDateTime b(long r1, TemporalUnit r3);

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal b(long r1, TemporalUnit r3) {
        return b(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    default ChronoLocalDateTime c(long r2, TemporalUnit r4) {
        return f.s(f(), super.c(r2, r4));
    }

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal c(long r1, TemporalUnit r3) {
        return c(r1, r3);
    }

    @Override // java.lang.Comparable
    /* bridge */ /* synthetic */ default int compareTo(ChronoLocalDateTime<?> r1) {
        return R(r1);
    }

    @Override // j$.time.temporal.l
    default Object d(j$.time.e r2) {
        if (r2 != j$.time.temporal.q.f463a) goto L5;
        return null;
    L5:
        if (r2 != j$.time.temporal.q.e) goto L7;
        return null;
    L7:
        if (r2 != j$.time.temporal.q.d) goto L10;
        return null;
    L10:
        if (r2 != j$.time.temporal.q.g) goto L14;
        return toLocalTime();
    L14:
        if (r2 != j$.time.temporal.q.f464b) goto L18;
        return f();
    L18:
        if (r2 != j$.time.temporal.q.f465c) goto L22;
        return ChronoUnit.NANOS;
    L22:
        return r2.l(this);
    }

    @Override // j$.time.temporal.m
    default Temporal e(Temporal r4) {
        j$.time.temporal.a r0 = j$.time.temporal.a.EPOCH_DAY;
        Temporal r42 = r4.a(m().L(), r0);
        j$.time.temporal.a r02 = j$.time.temporal.a.NANO_OF_DAY;
        return r42.a(toLocalTime().f0(), r02);
    }

    default k f() {
        return m().f();
    }

    @Override // j$.time.temporal.Temporal
    /* bridge */ /* synthetic */ default Temporal h(LocalDate r1) {
        return j(r1);
    }

    default ChronoLocalDateTime j(j$.time.temporal.m r2) {
        return f.s(f(), r2.e(this));
    }

    b m();

    default long toEpochSecond(ZoneOffset r5) {
        Objects.requireNonNull(r5, "offset");
        return ((m().L() * 86400) + toLocalTime().g0()) - r5.getTotalSeconds();
    }

    default Instant toInstant(ZoneOffset r3) {
        return Instant.ofEpochSecond(toEpochSecond(r3), toLocalTime().getNano());
    }

    LocalTime toLocalTime();
}
