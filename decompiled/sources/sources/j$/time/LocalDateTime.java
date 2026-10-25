package j$.time;

import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class LocalDateTime implements Temporal, j$.time.temporal.m, ChronoLocalDateTime<LocalDate>, Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final LocalDateTime f291c = null;
    public static final LocalDateTime d = null;
    private static final long serialVersionUID = 6207766400415563566L;

    /* renamed from: a, reason: collision with root package name */
    public final LocalDate f292a;

    /* renamed from: b, reason: collision with root package name */
    public final LocalTime f293b;

    static {
        f291c = of(LocalDate.d, LocalTime.e);
        d = of(LocalDate.e, LocalTime.f);
    }

    public LocalDateTime(LocalDate r1, LocalTime r2) {
        this.f292a = r1;
        this.f293b = r2;
    }

    public static LocalDateTime D(j$.time.temporal.l r3) {
        if ((r3 instanceof LocalDateTime) == false) goto L7;
        return (LocalDateTime) r3;
    L7:
        if ((r3 instanceof ZonedDateTime) == false) goto L11;
        return ((ZonedDateTime) r3).toLocalDateTime();
    L11:
        if ((r3 instanceof OffsetDateTime) == true) goto L13;
        return new LocalDateTime(LocalDate.I(r3), LocalTime.I(r3));
    L16:
        e = move-exception;
        j$.nio.file.b.g("Unable to obtain LocalDateTime from TemporalAccessor: ", r3, r3.getClass().getName(), e);
        return null;
    L13:
        return ((OffsetDateTime) r3).toLocalDateTime();
    }

    public static LocalDateTime K(long r5, int r7, ZoneOffset r8) {
        Objects.requireNonNull(r8, "offset");
        long r1 = r7;
        j$.time.temporal.a.NANO_OF_SECOND.a0(r1);
        long r52 = r5 + r8.getTotalSeconds();
        return new LocalDateTime(LocalDate.g0(Math.floorDiv(r52, 86400)), LocalTime.S((((int) Math.floorMod(r52, 86400)) * 1000000000) + r1));
    }

    public static LocalDateTime of(LocalDate r1, LocalTime r2) {
        Objects.requireNonNull(r1, "date");
        Objects.requireNonNull(r2, "time");
        return new LocalDateTime(r1, r2);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 5, this);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoZonedDateTime H(ZoneId r2) {
        return ZonedDateTime.I(this, null, r2);
    }

    public final boolean I(ChronoLocalDateTime r8) {
        if ((r8 instanceof LocalDateTime) == true) goto L5;
        long r3 = ((LocalDate) m()).L();
        long r5 = r8.m().L();
        if (r3 < r5) goto L15;
        if (r3 == r5) goto L12;
    L14:
        return false;
    L12:
        if (toLocalTime().f0() >= r8.toLocalTime().f0()) goto L14;
    L15:
        return true;
    L5:
        if (s((LocalDateTime) r8) >= 0) goto L7;
        return true;
    L7:
        return false;
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final int R(ChronoLocalDateTime r2) {
        if ((r2 instanceof LocalDateTime) == false) goto L7;
        return s((LocalDateTime) r2);
    L7:
        return super.R(r2);
    }

    public final LocalDateTime S(long r18, TemporalUnit r20) {
        if ((r20 instanceof ChronoUnit) == false) goto L23;
        switch(h.f424a[((ChronoUnit) r20).ordinal()]) {
            case 1: goto L21;
            case 2: goto L18;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        LocalDate r1 = this.f292a;
        LocalDateTime r7 = c0(r1.plusDays(r18 / 256), this.f293b);
        LocalDate r8 = r7.f292a;
        return r7.a0(r8, (r18 % 256) * 12, 0, 0, 0);
    L16:
        long r3 = r18 / DurationKt.MILLIS_IN_DAY;
        LocalDateTime r5 = c0(this.f292a.plusDays(r3), this.f293b);
        long r13 = (r18 % DurationKt.MILLIS_IN_DAY) * 1000000;
        return r5.a0(r5.f292a, 0, 0, 0, r13);
    L18:
        LocalDate r52 = this.f292a;
        LocalDateTime r53 = c0(r52.plusDays(r18 / 86400000000L), this.f293b);
        LocalDate r6 = r53.f292a;
        return r53.a0(r6, 0, 0, 0, (r18 % 86400000000L) * 1000);
    L7:
        return c0(this.f292a.i0(r18, r20), this.f293b);
    L11:
        return a0(this.f292a, r18, 0, 0, 0);
    L13:
        return a0(this.f292a, 0, r18, 0, 0);
    L15:
        return Y(r18);
    L21:
        return a0(this.f292a, 0, 0, 0, r18);
    L23:
        return (LocalDateTime) r20.s(this, r18);
    }

    public final LocalDateTime Y(long r11) {
        return a0(this.f292a, 0, 0, r11, 0);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ ChronoLocalDateTime a(long r1, j$.time.temporal.p r3) {
        return b0(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return b0(r1, r3);
    }

    public final LocalDateTime a0(LocalDate r19, long r20, long r22, long r24, long r26) {
        long r2 = ((r20 | r22) | r24) | r26;
        LocalTime r3 = this.f293b;
        if (r2 == 0) goto L5;
        long r10 = r24 / 86400;
        long r12 = r22 / 1440;
        long r14 = r20 / 24;
        long r142 = r14 + (r12 + (r10 + (r26 / 86400000000000L)));
        long r8 = (r24 % 86400) * 1000000000;
        long r6 = (r22 % 1440) * 60000000000L;
        long r62 = r6 + (r8 + (r26 % 86400000000000L));
        long r23 = r3.f0();
        long r82 = (((r20 % 24) * 3600000000000L) + r62) + r23;
        long r63 = Math.floorDiv(r82, 86400000000000L) + r142;
        long r4 = Math.floorMod(r82, 86400000000000L);
        if (r4 != r23) goto L9;
        LocalTime r25 = this.f293b;
    L11:
        return c0(r19.plusDays(r63), r25);
    L9:
        r25 = LocalTime.S(r4);
        goto L11
    L5:
        return c0(r19, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ ChronoLocalDateTime b(long r1, TemporalUnit r3) {
        return S(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return S(r1, r3);
    }

    public final LocalDateTime b0(long r3, j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L11;
        boolean r0 = ((j$.time.temporal.a) r5).b0();
        LocalDate r1 = this.f292a;
        if (r0 == false) goto L9;
        return c0(r1, this.f293b.h0(r3, r5));
    L9:
        return c0(r1.n0(r3, r5), this.f293b);
    L11:
        return (LocalDateTime) r5.Y(this, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final ChronoLocalDateTime c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        LocalDateTime this = S(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.S(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        LocalDateTime this = S(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.S(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    public final LocalDateTime c0(LocalDate r2, LocalTime r3) {
        if (this.f292a != r2) goto L8;
        if (this.f293b != r3) goto L8;
        return this;
    L8:
        return new LocalDateTime(r2, r3);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(ChronoLocalDateTime<?> r1) {
        return R(r1);
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f) goto L7;
        return this.f292a;
    L7:
        return super.d(r2);
    }

    public final LocalDateTime d0(j$.time.temporal.m r2) {
        if ((r2 instanceof LocalDate) == false) goto L7;
        return c0((LocalDate) r2, this.f293b);
    L7:
        if ((r2 instanceof LocalTime) == false) goto L11;
        return c0(this.f292a, (LocalTime) r2);
    L11:
        if ((r2 instanceof LocalDateTime) == false) goto L15;
        return (LocalDateTime) r2;
    L15:
        return (LocalDateTime) r2.e(this);
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof LocalDateTime) == false) goto L12;
        LocalDateTime r52 = (LocalDateTime) r5;
        if (this.f292a.equals(r52.f292a) == false) goto L12;
        if (this.f293b.equals(r52.f293b) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public String format(DateTimeFormatter r2) {
        Objects.requireNonNull(r2, "formatter");
        return r2.a(this);
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L11;
        if (((j$.time.temporal.a) r2).b0() == false) goto L9;
        return this.f293b.g(r2);
    L9:
        return this.f292a.g(r2);
    L11:
        return super.g(r2);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal h(LocalDate r1) {
        return d0(r1);
    }

    public final int hashCode() {
        int r0 = this.f292a.hashCode();
        return this.f293b.hashCode() ^ r0;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L9;
        j$.time.temporal.a r22 = (j$.time.temporal.a) r2;
        if (r22.isDateBased() == false) goto L7;
        return true;
    L7:
        if (r22.b0() == true) goto L17;
        return false;
    L17:
        return true;
    L9:
        if (r2 != null) goto L11;
        return false;
    L11:
        if (r2.s(this) == false) goto L19;
        return true;
    L19:
        return false;
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final /* bridge */ /* synthetic */ ChronoLocalDateTime j(j$.time.temporal.m r1) {
        return d0(r1);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L11;
        if (((j$.time.temporal.a) r2).b0() == false) goto L9;
        return this.f293b.k(r2);
    L9:
        return this.f292a.k(r2);
    L11:
        return r2.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L11;
        if (((j$.time.temporal.a) r2).b0() == false) goto L9;
        return this.f293b.l(r2);
    L9:
        return this.f292a.l(r2);
    L11:
        return r2.D(this);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final /* bridge */ /* synthetic */ j$.time.chrono.b m() {
        return toLocalDate();
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r11, TemporalUnit r12) {
        LocalDateTime r112 = D(r11);
        if ((r12 instanceof ChronoUnit) == false) goto L46;
        ChronoUnit r0 = (ChronoUnit) r12;
        if (r0.compareTo(ChronoUnit.DAYS) >= 0) goto L26;
        LocalDate r1 = this.f292a;
        LocalDate r4 = r112.f292a;
        r1.getClass();
        long r42 = r4.L() - r1.L();
        if (r42 == 0) goto L9;
        long r113 = r112.f293b.f0() - this.f293b.f0();
        if (r42 <= 0) goto L13;
        long r43 = r42 - 1;
        long r114 = r113 + 86400000000000L;
    L15:
        switch(h.f424a[r0.ordinal()]) {
            case 1: goto L23;
            case 2: goto L22;
            case 3: goto L21;
            case 4: goto L20;
            case 5: goto L19;
            case 6: goto L18;
            case 7: goto L17;
            default: goto L25;
        };
    L17:
        r43 = Math.multiplyExact(r43, 2);
        r114 = r114 / 43200000000000L;
        goto L25
    L18:
        r43 = Math.multiplyExact(r43, 24);
        r114 = r114 / 3600000000000L;
        goto L25
    L19:
        r43 = Math.multiplyExact(r43, 1440);
        r114 = r114 / 60000000000L;
        goto L25
    L20:
        r43 = Math.multiplyExact(r43, 86400);
        r114 = r114 / 1000000000;
        goto L25
    L21:
        r43 = Math.multiplyExact(r43, DurationKt.MILLIS_IN_DAY);
        r114 = r114 / 1000000;
        goto L25
    L22:
        r43 = Math.multiplyExact(r43, 86400000000L);
        r114 = r114 / 1000;
        goto L25
    L23:
        r43 = Math.multiplyExact(r43, 86400000000000L);
    L25:
        return Math.addExact(r43, r114);
    L13:
        r43 = r42 + 1;
        r114 = r113 - 86400000000000L;
        goto L15
    L9:
        return this.f293b.n(r112.f293b, r12);
    L26:
        LocalDate r02 = r112.f292a;
        LocalDate r13 = this.f292a;
        boolean r44 = false;
        if (r13 == null) goto L32;
        r02.getClass();
        if (r02.s(r13) <= 0) goto L34;
    L30:
        r44 = true;
    L34:
        if (r44 == false) goto L39;
        if (r112.f293b.s(this.f293b) >= 0) goto L39;
        r02 = r02.plusDays(-1);
    L44:
        return this.f292a.n(r02, r12);
    L39:
        if (r02.b0(this.f292a) == false) goto L44;
        if (r112.f293b.s(this.f293b) <= 0) goto L44;
        r02 = r02.plusDays(1);
        goto L44
    L32:
        if (r02.L() <= r13.L()) goto L34;
    L46:
        return r12.between(this, r112);
    }

    public final int s(LocalDateTime r3) {
        int r0 = this.f292a.s(r3.toLocalDate());
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return this.f293b.s(r3.toLocalTime());
    }

    public LocalDate toLocalDate() {
        return this.f292a;
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public LocalTime toLocalTime() {
        return this.f293b;
    }

    public final String toString() {
        return this.f292a.toString() + "T" + this.f293b.toString();
    }
}
