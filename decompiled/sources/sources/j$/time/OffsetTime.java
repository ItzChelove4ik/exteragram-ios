package j$.time;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class OffsetTime implements Temporal, j$.time.temporal.m, Comparable<OffsetTime>, Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f303c = 0;
    private static final long serialVersionUID = 7264499704384272492L;

    /* renamed from: a, reason: collision with root package name */
    public final LocalTime f304a;

    /* renamed from: b, reason: collision with root package name */
    public final ZoneOffset f305b;

    static {
        LocalTime r0 = LocalTime.e;
        ZoneOffset r1 = ZoneOffset.g;
        r0.getClass();
        of(r0, r1);
        LocalTime r02 = LocalTime.f;
        ZoneOffset r12 = ZoneOffset.f;
        r02.getClass();
        of(r02, r12);
    }

    public OffsetTime(LocalTime r2, ZoneOffset r3) {
        Objects.requireNonNull(r2, "time");
        this.f304a = r2;
        Objects.requireNonNull(r3, "offset");
        this.f305b = r3;
    }

    public static OffsetTime of(LocalTime r1, ZoneOffset r2) {
        return new OffsetTime(r1, r2);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 9, this);
    }

    public final long D() {
        return this.f304a.f0() - (this.f305b.getTotalSeconds() * 1000000000);
    }

    public final OffsetTime I(LocalTime r2, ZoneOffset r3) {
        if (this.f304a != r2) goto L8;
        if (this.f305b.equals(r3) == false) goto L8;
        return this;
    L8:
        return new OffsetTime(r2, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal a(long r3, j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L11;
        j$.time.temporal.a r0 = j$.time.temporal.a.OFFSET_SECONDS;
        LocalTime r1 = this.f304a;
        if (r5 != r0) goto L9;
        j$.time.temporal.a r52 = (j$.time.temporal.a) r5;
        return I(r1, ZoneOffset.ofTotalSeconds(r52.f447b.a(r3, r52)));
    L9:
        return I(r1.h0(r3, r5), this.f305b);
    L11:
        return (OffsetTime) r5.Y(this, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return s(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        OffsetTime this = s(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.s(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // java.lang.Comparable
    public final int compareTo(OffsetTime r5) {
        OffsetTime r52 = r5;
        if (this.f305b.equals(r52.f305b) == true) goto L5;
        int r0 = Long.compare(D(), r52.D());
        if (r0 == 0) goto L9;
        return r0;
    L9:
        return this.f304a.s(r52.f304a);
    L5:
        return this.f304a.s(r52.f304a);
    }

    @Override // j$.time.temporal.l
    public final Object d(e r5) {
        if (r5 == j$.time.temporal.q.d) goto L32;
        if (r5 == j$.time.temporal.q.e) goto L32;
        boolean r1 = false;
        if (r5 != j$.time.temporal.q.f463a) goto L10;
        boolean r0 = true;
    L12:
        if (r5 != j$.time.temporal.q.f464b) goto L15;
        r1 = true;
    L15:
        if ((r0 | r1) == false) goto L17;
        return null;
    L17:
        if (r5 != j$.time.temporal.q.f) goto L20;
        return null;
    L20:
        if (r5 != j$.time.temporal.q.g) goto L24;
        return this.f304a;
    L24:
        if (r5 != j$.time.temporal.q.f465c) goto L28;
        return ChronoUnit.NANOS;
    L28:
        return r5.l(this);
    L10:
        r0 = false;
    L32:
        return this.f305b;
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        return r4.a(this.f304a.f0(), j$.time.temporal.a.NANO_OF_DAY).a(this.f305b.getTotalSeconds(), j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof OffsetTime) == false) goto L12;
        OffsetTime r52 = (OffsetTime) r5;
        if (this.f304a.equals(r52.f304a) == false) goto L12;
        if (this.f305b.equals(r52.f305b) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public ZoneOffset getOffset() {
        return this.f305b;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (OffsetTime) r1.e(this);
    }

    public final int hashCode() {
        int r0 = this.f304a.hashCode();
        return this.f305b.f315b ^ r0;
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
        if (((j$.time.temporal.a) r2).b0() == false) goto L7;
        return true;
    L7:
        if (r2 == j$.time.temporal.a.OFFSET_SECONDS) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L11;
        if (r2 != j$.time.temporal.a.OFFSET_SECONDS) goto L9;
        return this.f305b.getTotalSeconds();
    L9:
        return this.f304a.k(r2);
    L11:
        return r2.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L11;
        if (r2 != j$.time.temporal.a.OFFSET_SECONDS) goto L9;
        return ((j$.time.temporal.a) r2).f447b;
    L9:
        return this.f304a.l(r2);
    L11:
        return r2.D(this);
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r6, TemporalUnit r7) {
        if ((r6 instanceof OffsetTime) == false) goto L30;
        OffsetTime r62 = (OffsetTime) r6;
    L8:
        if ((r7 instanceof ChronoUnit) == false) goto L26;
        long r3 = r62.D() - D();
        switch(n.f432a[((ChronoUnit) r7).ordinal()]) {
            case 1: goto L32;
            case 2: goto L24;
            case 3: goto L22;
            case 4: goto L20;
            case 5: goto L18;
            case 6: goto L16;
            case 7: goto L14;
            default: goto L11;
        };
    L11:
        j$.nio.file.b.d("Unsupported unit: ", r7);
        return 0;
    L32:
        return r3;
    L14:
        return r3 / 43200000000000L;
    L16:
        return r3 / 3600000000000L;
    L18:
        return r3 / 60000000000L;
    L20:
        return r3 / 1000000000;
    L22:
        return r3 / 1000000;
    L24:
        return r3 / 1000;
    L26:
        return r7.between(this, r62);
    L30:
        r62 = new OffsetTime(LocalTime.I(r6), ZoneOffset.Y(r6));     // Catch: b -> L27
    L27:
        e = move-exception;
        j$.nio.file.b.g("Unable to obtain OffsetTime from TemporalAccessor: ", r6, r6.getClass().getName(), e);
        return 0;
    }

    public final OffsetTime s(long r2, TemporalUnit r4) {
        if ((r4 instanceof ChronoUnit) == false) goto L7;
        return I(this.f304a.Y(r2, r4), this.f305b);
    L7:
        return (OffsetTime) r4.s(this, r2);
    }

    public LocalTime toLocalTime() {
        return this.f304a;
    }

    public final String toString() {
        return this.f304a.toString() + this.f305b.f316c;
    }
}
