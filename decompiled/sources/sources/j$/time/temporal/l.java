package j$.time.temporal;

import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface l {
    default Object d(j$.time.e r2) {
        if (r2 != q.f463a) goto L5;
        return null;
    L5:
        if (r2 != q.f464b) goto L7;
        return null;
    L7:
        if (r2 != q.f465c) goto L10;
        return null;
    L10:
        return r2.l(this);
    }

    default int g(p r6) {
        s r0 = l(r6);
        if (r0.d() == false) goto L11;
        long r1 = k(r6);
        if (r0.e(r1) == false) goto L9;
        return (int) r1;
    L9:
        throw new j$.time.b("Invalid value for " + r6 + " (valid values " + r0 + "): " + r1);
    L11:
        throw new r("Invalid field " + r6 + " for get() method, use getLong() instead");
    }

    boolean i(p r1);

    long k(p r1);

    default s l(p r2) {
        if ((r2 instanceof a) == true) goto L5;
        Objects.requireNonNull(r2, "field");
        return r2.D(this);
    L5:
        if (i(r2) == false) goto L9;
        return ((a) r2).f447b;
    L9:
        throw new r(j$.time.c.a("Unsupported field: ", r2));
    }
}
