package j$.time;

import de.robv.android.xposed.callbacks.XCallback;
import j$.time.format.a0;
import j$.time.format.z;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class YearMonth implements Temporal, j$.time.temporal.m, Comparable<YearMonth>, Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f311c = 0;
    private static final long serialVersionUID = 4183400860270640070L;

    /* renamed from: a, reason: collision with root package name */
    public final int f312a;

    /* renamed from: b, reason: collision with root package name */
    public final int f313b;

    static {
        j$.time.format.q r0 = new j$.time.format.q();
        r0.h(j$.time.temporal.a.YEAR, 4, 10, a0.EXCEEDS_PAD);
        r0.c(SignatureVisitor.SUPER);
        r0.g(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        r0.l(Locale.getDefault(), z.SMART, null);
    }

    public YearMonth(int r1, int r2) {
        this.f312a = r1;
        this.f313b = r2;
    }

    public static YearMonth of(int r3, int r4) {
        j$.time.temporal.a.YEAR.a0(r3);
        j$.time.temporal.a.MONTH_OF_YEAR.a0(r4);
        return new YearMonth(r3, r4);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 12, this);
    }

    public final YearMonth D(long r3, TemporalUnit r5) {
        if ((r5 instanceof ChronoUnit) == false) goto L21;
        switch(q.f438b[((ChronoUnit) r5).ordinal()]) {
            case 1: goto L19;
            case 2: goto L17;
            case 3: goto L15;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L8;
            default: goto L6;
        };
    L6:
        j$.nio.file.b.d("Unsupported unit: ", r5);
        return null;
    L8:
        j$.time.temporal.a r52 = j$.time.temporal.a.ERA;
        return Y(Math.addExact(k(r52), r3), r52);
    L11:
        return K(Math.multiplyExact(r3, 1000));
    L13:
        return K(Math.multiplyExact(r3, 100));
    L15:
        return K(Math.multiplyExact(r3, 10));
    L17:
        return K(r3);
    L19:
        return I(r3);
    L21:
        return (YearMonth) r5.s(this, r3);
    }

    public final YearMonth I(long r7) {
        if (r7 != 0) goto L5;
        return this;
    L5:
        long r0 = ((this.f312a * 12) + (this.f313b - 1)) + r7;
        j$.time.temporal.a r72 = j$.time.temporal.a.YEAR;
        return S(r72.f447b.a(Math.floorDiv(r0, 12), r72), ((int) Math.floorMod(r0, 12)) + 1);
    }

    public final YearMonth K(long r4) {
        if (r4 != 0) goto L5;
        return this;
    L5:
        j$.time.temporal.a r0 = j$.time.temporal.a.YEAR;
        return S(r0.f447b.a(this.f312a + r4, r0), this.f313b);
    }

    public final YearMonth S(int r2, int r3) {
        if (this.f312a != r2) goto L8;
        if (this.f313b != r3) goto L8;
        return this;
    L8:
        return new YearMonth(r2, r3);
    }

    public final YearMonth Y(long r5, j$.time.temporal.p r7) {
        if ((r7 instanceof j$.time.temporal.a) == false) goto L33;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r7;
        r0.a0(r5);
        int r02 = q.f437a[r0.ordinal()];
        if (r02 != 1) goto L7;
        int r52 = (int) r5;
        j$.time.temporal.a.MONTH_OF_YEAR.a0(r52);
        return S(this.f312a, r52);
    L7:
        if (r02 == 2) goto L29;
        if (r02 == 3) goto L24;
        if (r02 != 4) goto L13;
        int r53 = (int) r5;
        j$.time.temporal.a.YEAR.a0(r53);
        return S(r53, this.f313b);
    L13:
        if (r02 != 5) goto L20;
        if (k(j$.time.temporal.a.ERA) != r5) goto L17;
        return this;
    L17:
        int r1 = 1 - this.f312a;
        j$.time.temporal.a.YEAR.a0(r1);
        return S(r1, this.f313b);
    L20:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r7));
    L24:
        if (this.f312a >= 1) goto L26;
        r5 = 1 - r5;
    L26:
        int r54 = (int) r5;
        j$.time.temporal.a.YEAR.a0(r54);
        return S(r54, this.f313b);
    L29:
        return I(r5 - s());
    L33:
        return (YearMonth) r7.Y(this, r5);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return Y(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return D(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        YearMonth this = D(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.D(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // java.lang.Comparable
    public final int compareTo(YearMonth r3) {
        YearMonth r32 = r3;
        int r0 = this.f312a - r32.f312a;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return this.f313b - r32.f313b;
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f464b) goto L7;
        return j$.time.chrono.r.f353c;
    L7:
        if (r2 != j$.time.temporal.q.f465c) goto L11;
        return ChronoUnit.MONTHS;
    L11:
        return super.d(r2);
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        if (j$.time.chrono.k.r(r4).equals(j$.time.chrono.r.f353c) == false) goto L6;
        j$.time.temporal.a r0 = j$.time.temporal.a.PROLEPTIC_MONTH;
        return r4.a(s(), r0);
    L6:
        j$.nio.file.b.h("Adjustment only supported on ISO date-time");
        return null;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof YearMonth) == false) goto L12;
        YearMonth r52 = (YearMonth) r5;
        if (this.f312a != r52.f312a) goto L12;
        if (this.f313b != r52.f313b) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r4) {
        return l(r4).a(k(r4), r4);
    }

    public int getMonthValue() {
        return this.f313b;
    }

    public int getYear() {
        return this.f312a;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (YearMonth) r1.e(this);
    }

    public final int hashCode() {
        int r0 = this.f312a;
        return (this.f313b << 27) ^ r0;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L17;
        return false;
    L17:
        if (r2.s(this) == false) goto L28;
        return true;
    L28:
        return false;
    L5:
        if (r2 != j$.time.temporal.a.YEAR) goto L7;
        return true;
    L7:
        if (r2 != j$.time.temporal.a.MONTH_OF_YEAR) goto L9;
        return true;
    L9:
        if (r2 != j$.time.temporal.a.PROLEPTIC_MONTH) goto L11;
        return true;
    L11:
        if (r2 != j$.time.temporal.a.YEAR_OF_ERA) goto L13;
        return true;
    L13:
        if (r2 == j$.time.temporal.a.ERA) goto L26;
        return false;
    L26:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r4) {
        if ((r4 instanceof j$.time.temporal.a) == false) goto L33;
        int r0 = q.f437a[((j$.time.temporal.a) r4).ordinal()];
        int r1 = 1;
        if (r0 != 1) goto L7;
        int r3 = this.f313b;
    L23:
        return r3;
    L7:
        if (r0 == 2) goto L30;
        if (r0 != 3) goto L11;
        int r32 = this.f312a;
        if (r32 >= 1) goto L28;
        r32 = 1 - r32;
    L28:
        return r32;
    L11:
        if (r0 != 4) goto L13;
        r3 = this.f312a;
        goto L23
    L13:
        if (r0 != 5) goto L20;
        if (this.f312a >= 1) goto L18;
        r1 = 0;
    L18:
        return r1;
    L20:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r4));
    L30:
        return s();
    L33:
        return r4.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r3) {
        if (r3 != j$.time.temporal.a.YEAR_OF_ERA) goto L11;
        if (getYear() > 0) goto L9;
        long r2 = 1000000000;
    L8:
        return j$.time.temporal.s.f(1, r2);
    L9:
        r2 = 999999999;
        goto L8
    L11:
        return super.l(r3);
    }

    public int lengthOfMonth() {
        k r0 = k.K(this.f313b);
        j$.time.chrono.r r1 = j$.time.chrono.r.f353c;
        long r2 = this.f312a;
        r1.getClass();
        return r0.D(j$.time.chrono.r.a0(r2));
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r8, TemporalUnit r9) {
        if ((r8 instanceof YearMonth) == false) goto L5;
        YearMonth r82 = (YearMonth) r8;
    L13:
        if ((r9 instanceof ChronoUnit) == false) goto L29;
        long r3 = r82.s() - s();
        switch(q.f438b[((ChronoUnit) r9).ordinal()]) {
            case 1: goto L34;
            case 2: goto L27;
            case 3: goto L25;
            case 4: goto L23;
            case 5: goto L21;
            case 6: goto L18;
            default: goto L16;
        };
    L16:
        j$.nio.file.b.d("Unsupported unit: ", r9);
        return 0;
    L18:
        j$.time.temporal.a r92 = j$.time.temporal.a.ERA;
        return r82.k(r92) - k(r92);
    L34:
        return r3;
    L21:
        return r3 / 12000;
    L23:
        return r3 / 1200;
    L25:
        return r3 / 120;
    L27:
        return r3 / 12;
    L29:
        return r9.between(this, r82);
    L5:
        Objects.requireNonNull(r8, "temporal");
    L9:
        e = move-exception;
        j$.nio.file.b.g("Unable to obtain YearMonth from TemporalAccessor: ", r8, r8.getClass().getName(), e);
        return 0;
    L7:
        if (j$.time.chrono.r.f353c.equals(j$.time.chrono.k.r(r8)) == true) goto L11;
        r8 = LocalDate.I(r8);     // Catch: b -> L9
    L11:
        r82 = of(r8.g(j$.time.temporal.a.YEAR), r8.g(j$.time.temporal.a.MONTH_OF_YEAR));     // Catch: b -> L9
        goto L13
    }

    public final long s() {
        return ((this.f312a * 12) + this.f313b) - 1;
    }

    public final String toString() {
        int r0 = Math.abs(this.f312a);
        StringBuilder r1 = new StringBuilder(9);
        int r2 = this.f312a;
        if (r0 >= 1000) goto L7;
        if (r2 >= 0) goto L6;
        r1.append(r2 + XCallback.PRIORITY_LOWEST);
        r1.deleteCharAt(1);
    L9:
        if (this.f313b >= 10) goto L11;
        String r02 = "-0";
    L12:
        r1.append(r02);
        r1.append(this.f313b);
        return r1.toString();
    L11:
        r02 = "-";
        goto L12
    L6:
        r1.append(r2 + 10000);
        r1.deleteCharAt(0);
        goto L9
    L7:
        r1.append(r2);
        goto L9
    }
}
