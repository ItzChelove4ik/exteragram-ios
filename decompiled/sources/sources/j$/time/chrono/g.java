package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class g implements j$.time.temporal.o, Serializable {
    public static final /* synthetic */ int e = 0;
    private static final long serialVersionUID = 57387258289L;

    /* renamed from: a, reason: collision with root package name */
    public final k f335a;

    /* renamed from: b, reason: collision with root package name */
    public final int f336b;

    /* renamed from: c, reason: collision with root package name */
    public final int f337c;
    public final int d;

    static {
        j$.desugar.sun.nio.fs.g.p(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    }

    public g(k r1, int r2, int r3, int r4) {
        this.f335a = r1;
        this.f336b = r2;
        this.f337c = r3;
        this.d = r4;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof g) == false) goto L16;
        g r52 = (g) r5;
        if (this.f336b != r52.f336b) goto L16;
        if (this.f337c != r52.f337c) goto L16;
        if (this.d != r52.d) goto L16;
        if (this.f335a.equals(r52.f335a) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public final int hashCode() {
        int r0 = this.f336b;
        int r1 = Integer.rotateLeft(this.f337c, 8) + r0;
        int r02 = Integer.rotateLeft(this.d, 16) + r1;
        return this.f335a.hashCode() ^ r02;
    }

    @Override // j$.time.temporal.o
    public final Temporal s(Temporal r6) {
        k r0 = (k) r6.d(j$.time.temporal.q.f464b);
        if (r0 == null) goto L10;
        if (this.f335a.equals(r0) == true) goto L10;
        j$.nio.file.b.f("Chronology mismatch, expected: ", this.f335a.getId(), ", actual: ", r0.getId());
        return null;
    L10:
        if (this.f337c != 0) goto L14;
        int r02 = this.f336b;
        if (r02 == 0) goto L28;
        r6 = r6.b(r02, ChronoUnit.YEARS);
    L28:
        int r5 = this.d;
        if (r5 != 0) goto L31;
        return r6;
    L31:
        return r6.b(r5, ChronoUnit.DAYS);
    L14:
        j$.time.temporal.s r03 = this.f335a.B(j$.time.temporal.a.MONTH_OF_YEAR);
        if (r03.f466a == r03.f467b) goto L17;
    L21:
        long r1 = -1;
    L22:
        int r3 = this.f336b;
        if (r1 <= 0) goto L25;
        r6 = r6.b((r3 * r1) + this.f337c, ChronoUnit.MONTHS);
        goto L28
    L25:
        if (r3 == 0) goto L27;
        r6 = r6.b(r3, ChronoUnit.YEARS);
    L27:
        r6 = r6.b(this.f337c, ChronoUnit.MONTHS);
        goto L28
    L17:
        if (r03.f468c != r03.d) goto L21;
        if (r03.d() == false) goto L21;
        r1 = (r03.d - r03.f466a) + 1;
        goto L22
    }

    public final String toString() {
        if (this.f336b == 0) goto L5;
    L10:
        StringBuilder r0 = new StringBuilder();
        r0.append(this.f335a.toString());
        r0.append(" P");
        int r1 = this.f336b;
        if (r1 == 0) goto L13;
        r0.append(r1);
        r0.append('Y');
    L13:
        int r12 = this.f337c;
        if (r12 == 0) goto L16;
        r0.append(r12);
        r0.append('M');
    L16:
        int r2 = this.d;
        if (r2 == 0) goto L20;
        r0.append(r2);
        r0.append('D');
    L20:
        return r0.toString();
    L5:
        if (this.f337c != 0) goto L10;
        if (this.d != 0) goto L10;
        return this.f335a.toString() + " P0D";
    }

    public Object writeReplace() {
        return new d0((byte) 9, this);
    }
}
