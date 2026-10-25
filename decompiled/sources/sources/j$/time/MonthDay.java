package j$.time;

import j$.time.format.z;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class MonthDay implements j$.time.temporal.l, j$.time.temporal.m, Comparable<MonthDay>, Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f297c = 0;
    private static final long serialVersionUID = -939150713474957432L;

    /* renamed from: a, reason: collision with root package name */
    public final int f298a;

    /* renamed from: b, reason: collision with root package name */
    public final int f299b;

    static {
        j$.time.format.q r0 = new j$.time.format.q();
        r0.d("--");
        r0.g(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        r0.c(SignatureVisitor.SUPER);
        r0.g(j$.time.temporal.a.DAY_OF_MONTH, 2);
        r0.l(Locale.getDefault(), z.SMART, null);
    }

    public MonthDay(int r1, int r2) {
        this.f298a = r1;
        this.f299b = r2;
    }

    public static MonthDay of(int r3, int r4) {
        k r32 = k.K(r3);
        Objects.requireNonNull(r32, "month");
        j$.time.temporal.a.DAY_OF_MONTH.a0(r4);
        if (r4 > r32.I()) goto L7;
        return new MonthDay(r32.getValue(), r4);
    L7:
        throw new b("Illegal value for DayOfMonth field, value " + r4 + " is not valid for month " + r32.name());
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 13, this);
    }

    @Override // java.lang.Comparable
    public final int compareTo(MonthDay r3) {
        MonthDay r32 = r3;
        int r0 = this.f298a - r32.f298a;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return this.f299b - r32.f299b;
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f464b) goto L7;
        return j$.time.chrono.r.f353c;
    L7:
        return super.d(r2);
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r6) {
        if (j$.time.chrono.k.r(r6).equals(j$.time.chrono.r.f353c) == false) goto L6;
        Temporal r62 = r6.a(this.f298a, j$.time.temporal.a.MONTH_OF_YEAR);
        j$.time.temporal.a r0 = j$.time.temporal.a.DAY_OF_MONTH;
        return r62.a(Math.min(r62.l(r0).d, this.f299b), r0);
    L6:
        j$.nio.file.b.h("Adjustment only supported on ISO date-time");
        return null;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MonthDay) == false) goto L12;
        MonthDay r52 = (MonthDay) r5;
        if (this.f298a != r52.f298a) goto L12;
        if (this.f299b != r52.f299b) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r4) {
        return l(r4).a(k(r4), r4);
    }

    public int getDayOfMonth() {
        return this.f299b;
    }

    public int getMonthValue() {
        return this.f298a;
    }

    public final int hashCode() {
        return (this.f298a << 6) + this.f299b;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L11;
        return false;
    L11:
        if (r2.s(this) == false) goto L19;
        return true;
    L19:
        return false;
    L5:
        if (r2 != j$.time.temporal.a.MONTH_OF_YEAR) goto L7;
        return true;
    L7:
        if (r2 == j$.time.temporal.a.DAY_OF_MONTH) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L15;
        int r0 = l.f430a[((j$.time.temporal.a) r3).ordinal()];
        if (r0 != 1) goto L7;
        int r2 = this.f299b;
    L10:
        return r2;
    L7:
        if (r0 != 2) goto L12;
        r2 = this.f298a;
        goto L10
    L12:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r3));
    L15:
        return r3.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r3) {
        if (r3 != j$.time.temporal.a.MONTH_OF_YEAR) goto L7;
        return r3.K();
    L7:
        if (r3 != j$.time.temporal.a.DAY_OF_MONTH) goto L24;
        k r32 = k.K(this.f298a);
        r32.getClass();
        int r33 = j.f427a[r32.ordinal()];
        if (r33 != 1) goto L11;
        int r34 = 28;
    L22:
        return j$.time.temporal.s.g(r34, k.K(this.f298a).I());
    L11:
        if (r33 != 2) goto L13;
    L19:
        r34 = 30;
        goto L22
    L13:
        if (r33 == 3) goto L19;
        if (r33 == 4) goto L19;
        if (r33 == 5) goto L19;
        r34 = 31;
        goto L22
    L24:
        return super.l(r3);
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(10);
        r0.append("--");
        if (this.f298a >= 10) goto L5;
        String r2 = MVEL.VERSION_SUB;
    L6:
        r0.append(r2);
        r0.append(this.f298a);
        if (this.f299b >= 10) goto L9;
        String r1 = "-0";
    L10:
        r0.append(r1);
        r0.append(this.f299b);
        return r0.toString();
    L9:
        r1 = "-";
        goto L10
    L5:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
