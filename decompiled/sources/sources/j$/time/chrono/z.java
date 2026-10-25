package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.ZoneId;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class z extends a implements Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final z f365c = null;
    private static final long serialVersionUID = 1039765215346859963L;

    static {
        f365c = new z();
    }

    private z() {
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.k
    public final j$.time.temporal.s B(j$.time.temporal.a r6) {
        int r5 = y.f364a[r6.ordinal()];
        if (r5 != 1) goto L5;
        j$.time.temporal.s r52 = j$.time.temporal.a.PROLEPTIC_MONTH.f447b;
        return j$.time.temporal.s.f(r52.f466a - 22932, r52.d - 22932);
    L5:
        if (r5 != 2) goto L7;
        j$.time.temporal.s r53 = j$.time.temporal.a.YEAR.f447b;
        return j$.time.temporal.s.g(r53.d - 1911, (-r53.f466a) + 1912);
    L7:
        if (r5 != 3) goto L9;
        j$.time.temporal.s r54 = j$.time.temporal.a.YEAR.f447b;
        return j$.time.temporal.s.f(r54.f466a - 1911, r54.d - 1911);
    L9:
        return r6.f447b;
    }

    @Override // j$.time.chrono.k
    public final List C() {
        return j$.desugar.sun.nio.fs.g.p(c0.values());
    }

    @Override // j$.time.chrono.k
    public final l E(int r1) {
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L6;
        j$.nio.file.b.b("Invalid era: ", r1);
        return null;
    L6:
        return c0.ROC;
    L10:
        return c0.BEFORE_ROC;
    }

    @Override // j$.time.chrono.k
    public final int F(l r1, int r2) {
        if ((r1 instanceof c0) == false) goto L10;
        if (r1 != c0.ROC) goto L8;
        return r2;
    L8:
        return 1 - r2;
    L10:
        throw new ClassCastException("Era must be MinguoEra");
    }

    @Override // j$.time.chrono.k
    public final b J(j$.time.temporal.l r1) {
        if ((r1 instanceof b0) == false) goto L7;
        return (b0) r1;
    L7:
        return new b0(LocalDate.I(r1));
    }

    @Override // j$.time.chrono.k
    public final b N() {
        return new b0(LocalDate.I(LocalDate.f0(j$.desugar.sun.nio.fs.g.A())));
    }

    @Override // j$.time.chrono.k
    public final b T(int r1, int r2, int r3) {
        return new b0(LocalDate.of(r1 + 1911, r2, r3));
    }

    @Override // j$.time.chrono.a, j$.time.chrono.k
    public final b V(Map r1, j$.time.format.z r2) {
        return (b0) super.V(r1, r2);
    }

    @Override // j$.time.chrono.k
    public final ChronoZonedDateTime W(Instant r1, ZoneId r2) {
        return j.I(this, r1, r2);
    }

    @Override // j$.time.chrono.k
    public final String getId() {
        return "Minguo";
    }

    @Override // j$.time.chrono.k
    public final b p(long r1) {
        return new b0(LocalDate.g0(r1));
    }

    @Override // j$.time.chrono.k
    public final String v() {
        return "roc";
    }

    public Object writeReplace() {
        return new d0((byte) 1, this);
    }

    @Override // j$.time.chrono.k
    public final b x(int r1, int r2) {
        return new b0(LocalDate.h0(r1 + 1911, r2));
    }
}
