package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.ZoneId;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class f0 extends a implements Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final f0 f334c = null;
    private static final long serialVersionUID = 2775954514031616474L;

    static {
        f334c = new f0();
        HashMap r0 = new HashMap();
        HashMap r1 = new HashMap();
        HashMap r2 = new HashMap();
        r0.put("en", new String[]{"BB", "BE"});
        r0.put("th", new String[]{"BB", "BE"});
        r1.put("en", new String[]{"B.B.", "B.E."});
        r1.put("th", new String[]{"พ.ศ.", "ปีก่อนคริสต์กาลที่"});
        r2.put("en", new String[]{"Before Buddhist", "Budhhist Era"});
        r2.put("th", new String[]{"พุทธศักราช", "ปีก่อนคริสต์กาลที่"});
    }

    private f0() {
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.k
    public final j$.time.temporal.s B(j$.time.temporal.a r8) {
        int r7 = e0.f331a[r8.ordinal()];
        if (r7 != 1) goto L5;
        j$.time.temporal.s r72 = j$.time.temporal.a.PROLEPTIC_MONTH.f447b;
        return j$.time.temporal.s.f(r72.f466a + 6516, r72.d + 6516);
    L5:
        if (r7 != 2) goto L7;
        j$.time.temporal.s r73 = j$.time.temporal.a.YEAR.f447b;
        return j$.time.temporal.s.g((-(r73.f466a + 543)) + 1, r73.d + 543);
    L7:
        if (r7 != 3) goto L9;
        j$.time.temporal.s r74 = j$.time.temporal.a.YEAR.f447b;
        return j$.time.temporal.s.f(r74.f466a + 543, r74.d + 543);
    L9:
        return r8.f447b;
    }

    @Override // j$.time.chrono.k
    public final List C() {
        return j$.desugar.sun.nio.fs.g.p(i0.values());
    }

    @Override // j$.time.chrono.k
    public final l E(int r1) {
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L6;
        j$.nio.file.b.b("Invalid era: ", r1);
        return null;
    L6:
        return i0.BE;
    L10:
        return i0.BEFORE_BE;
    }

    @Override // j$.time.chrono.k
    public final int F(l r1, int r2) {
        if ((r1 instanceof i0) == false) goto L10;
        if (r1 != i0.BE) goto L8;
        return r2;
    L8:
        return 1 - r2;
    L10:
        throw new ClassCastException("Era must be BuddhistEra");
    }

    @Override // j$.time.chrono.k
    public final b J(j$.time.temporal.l r1) {
        if ((r1 instanceof h0) == false) goto L7;
        return (h0) r1;
    L7:
        return new h0(LocalDate.I(r1));
    }

    @Override // j$.time.chrono.k
    public final b N() {
        return new h0(LocalDate.I(LocalDate.f0(j$.desugar.sun.nio.fs.g.A())));
    }

    @Override // j$.time.chrono.k
    public final b T(int r1, int r2, int r3) {
        return new h0(LocalDate.of(r1 - 543, r2, r3));
    }

    @Override // j$.time.chrono.a, j$.time.chrono.k
    public final b V(Map r1, j$.time.format.z r2) {
        return (h0) super.V(r1, r2);
    }

    @Override // j$.time.chrono.k
    public final ChronoZonedDateTime W(Instant r1, ZoneId r2) {
        return j.I(this, r1, r2);
    }

    @Override // j$.time.chrono.k
    public final String getId() {
        return "ThaiBuddhist";
    }

    @Override // j$.time.chrono.k
    public final b p(long r1) {
        return new h0(LocalDate.g0(r1));
    }

    @Override // j$.time.chrono.k
    public final String v() {
        return "buddhist";
    }

    public Object writeReplace() {
        return new d0((byte) 1, this);
    }

    @Override // j$.time.chrono.k
    public final b x(int r1, int r2) {
        return new h0(LocalDate.h0(r1 - 543, r2));
    }
}
