package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalTime;
import j$.time.ZoneId;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface k extends Comparable {
    static k of(String r4) {
        ConcurrentHashMap r0 = a.f322a;
        Objects.requireNonNull(r4, "id");
    L3:
        ConcurrentHashMap r02 = a.f322a;
        k r1 = (k) r02.get(r4);
        if (r1 != null) goto L6;
        r1 = (k) a.f323b.get(r4);
    L6:
        if (r1 != null) goto L7;
        if (r02.get("ISO") != null) goto L21;
        n r03 = n.l;
        r03.getClass();
        a.D(r03, "Hijrah-umalqura");
        u r04 = u.f356c;
        r04.getClass();
        a.D(r04, "Japanese");
        z r05 = z.f365c;
        r05.getClass();
        a.D(r05, "Minguo");
        f0 r06 = f0.f334c;
        r06.getClass();
        a.D(r06, "ThaiBuddhist");
        Iterator r07 = Arrays.asList(new a[0]).iterator();     // Catch: Throwable -> L18
    L13:
        if (r07.hasNext() == false) goto L17;
        a r2 = (a) r07.next();
        if (r2.getId().equals("ISO") == true) goto L13;
        a.D(r2, r2.getId());
        goto L13
    L17:
        r r08 = r.f353c;
        r08.getClass();
        a.D(r08, "ISO");
    L18:
        th = move-exception;
        throw new ServiceConfigurationError(th.getMessage(), th);
    L21:
        Iterator r09 = ServiceLoader.load(k.class).iterator();
    L23:
        if (r09.hasNext() == false) goto L29;
        k r12 = (k) r09.next();
        if (r4.equals(r12.getId()) == true) goto L28;
        if (r4.equals(r12.v()) == false) goto L23;
    L28:
        return r12;
    L29:
        j$.nio.file.b.h("Unknown chronology: ".concat(r4));
        return null;
    L7:
        return r1;
    }

    static k r(j$.time.temporal.l r1) {
        Objects.requireNonNull(r1, "temporal");
        k r12 = (k) r1.d(j$.time.temporal.q.f464b);
        r r0 = r.f353c;
        if (r12 == null) goto L5;
        return r12;
    L5:
        Objects.requireNonNull(r0, "defaultObj");
        return r0;
    }

    j$.time.temporal.s B(j$.time.temporal.a r1);

    List C();

    l E(int r1);

    int F(l r1, int r2);

    b J(j$.time.temporal.l r1);

    b N();

    default ChronoLocalDateTime O(j$.time.temporal.l r4) {
        return J(r4).M(LocalTime.I(r4));
    L4:
        e = move-exception;
        throw new j$.time.b("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + r4.getClass(), e);
    }

    b T(int r1, int r2, int r3);

    b V(Map r1, j$.time.format.z r2);

    ChronoZonedDateTime W(Instant r1, ZoneId r2);

    boolean equals(Object r1);

    String getId();

    int hashCode();

    b p(long r1);

    String toString();

    String v();

    default ChronoZonedDateTime w(j$.time.temporal.l r4) {
        ZoneId r0 = ZoneId.s(r4);     // Catch: j$.time.b -> L7
        return W(Instant.D(r4), r0);
    L6:
        return j.D(r0, null, f.s(this, this.O(r4)));
    L7:
        e = move-exception;
        throw new j$.time.b("Unable to obtain ChronoZonedDateTime from TemporalAccessor: " + r4.getClass(), e);
    }

    b x(int r1, int r2);
}
