package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface b extends Temporal, j$.time.temporal.m, Comparable {
    default long L() {
        return k(j$.time.temporal.a.EPOCH_DAY);
    }

    default ChronoLocalDateTime M(LocalTime r2) {
        return new f(this, r2);
    }

    default l P() {
        return f().E(g(j$.time.temporal.a.ERA));
    }

    b U(j$.time.temporal.o r1);

    default int X(b r5) {
        int r0 = Long.compare(L(), r5.L());
        if (r0 != 0) goto L6;
        k r4 = f();
        k r52 = r5.f();
        return ((a) r4).getId().compareTo(r52.getId());
    L6:
        return r0;
    }

    @Override // j$.time.temporal.Temporal
    b a(long r1, j$.time.temporal.p r3);

    @Override // j$.time.temporal.Temporal
    b b(long r1, TemporalUnit r3);

    @Override // j$.time.temporal.Temporal
    default b c(long r2, TemporalUnit r4) {
        return d.s(f(), super.c(r2, r4));
    }

    @Override // java.lang.Comparable
    /* bridge */ /* synthetic */ default int compareTo(Object r1) {
        return X((b) r1);
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
        if (r2 != j$.time.temporal.q.g) goto L13;
        return null;
    L13:
        if (r2 != j$.time.temporal.q.f464b) goto L17;
        return f();
    L17:
        if (r2 != j$.time.temporal.q.f465c) goto L21;
        return ChronoUnit.DAYS;
    L21:
        return r2.l(this);
    }

    @Override // j$.time.temporal.m
    default Temporal e(Temporal r4) {
        j$.time.temporal.a r0 = j$.time.temporal.a.EPOCH_DAY;
        return r4.a(L(), r0);
    }

    boolean equals(Object r1);

    k f();

    int hashCode();

    @Override // j$.time.temporal.l
    default boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L8;
        return false;
    L8:
        if (r2.s(this) == false) goto L13;
        return true;
    L13:
        return false;
    L5:
        return ((j$.time.temporal.a) r2).isDateBased();
    }

    b j(j$.time.temporal.m r1);

    @Override // j$.time.temporal.Temporal
    long n(Temporal r1, TemporalUnit r2);

    String toString();
}
