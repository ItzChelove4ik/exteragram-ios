package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface l extends j$.time.temporal.l, j$.time.temporal.m {
    @Override // j$.time.temporal.l
    default Object d(j$.time.e r2) {
        if (r2 != j$.time.temporal.q.f465c) goto L7;
        return ChronoUnit.ERAS;
    L7:
        return super.d(r2);
    }

    @Override // j$.time.temporal.m
    default Temporal e(Temporal r4) {
        return r4.a(getValue(), j$.time.temporal.a.ERA);
    }

    @Override // j$.time.temporal.l
    default int g(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.ERA) goto L7;
        return getValue();
    L7:
        return super.g(r2);
    }

    int getValue();

    @Override // j$.time.temporal.l
    default boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L9;
        return false;
    L9:
        if (r2.s(this) == false) goto L16;
        return true;
    L16:
        return false;
    L5:
        if (r2 == j$.time.temporal.a.ERA) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // j$.time.temporal.l
    default long k(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.ERA) goto L7;
        return getValue();
    L7:
        if ((r2 instanceof j$.time.temporal.a) == true) goto L11;
        return r2.S(this);
    L11:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r2));
    }
}
