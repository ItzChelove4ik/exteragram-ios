package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import kotlin.time.DurationKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class f implements ChronoLocalDateTime, Temporal, j$.time.temporal.m, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;

    /* renamed from: a, reason: collision with root package name */
    public final transient b f332a;

    /* renamed from: b, reason: collision with root package name */
    public final transient LocalTime f333b;

    public f(b r2, LocalTime r3) {
        Objects.requireNonNull(r3, "time");
        this.f332a = r2;
        this.f333b = r3;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static f s(k r1, Temporal r2) {
        f r22 = (f) r2;
        if (r1.equals(r22.f()) == false) goto L5;
        return r22;
    L5:
        j$.nio.file.b.k("Chronology mismatch, required: ", r1.getId(), r22.f().getId());
        return null;
    }

    private Object writeReplace() {
        return new d0((byte) 2, this);
    }

    public final f D(long r18, TemporalUnit r20) {
        if ((r20 instanceof ChronoUnit) == false) goto L23;
        switch(e.f330a[((ChronoUnit) r20).ordinal()]) {
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
        b r1 = this.f332a;
        f r7 = S(r1.b(r18 / 256, ChronoUnit.DAYS), this.f333b);
        b r8 = r7.f332a;
        return r7.I(r8, (r18 % 256) * 12, 0, 0, 0);
    L16:
        long r3 = r18 / DurationKt.MILLIS_IN_DAY;
        f r5 = S(this.f332a.b(r3, ChronoUnit.DAYS), this.f333b);
        long r13 = (r18 % DurationKt.MILLIS_IN_DAY) * 1000000;
        return r5.I(r5.f332a, 0, 0, 0, r13);
    L18:
        b r52 = this.f332a;
        f r53 = S(r52.b(r18 / 86400000000L, ChronoUnit.DAYS), this.f333b);
        b r6 = r53.f332a;
        return r53.I(r6, 0, 0, 0, (r18 % 86400000000L) * 1000);
    L7:
        return S(this.f332a.b(r18, r20), this.f333b);
    L11:
        return I(this.f332a, r18, 0, 0, 0);
    L13:
        return I(this.f332a, 0, r18, 0, 0);
    L15:
        return I(this.f332a, 0, 0, r18, 0);
    L21:
        return I(this.f332a, 0, 0, 0, r18);
    L23:
        return s(this.f332a.f(), r20.s(this, r18));
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoZonedDateTime H(ZoneId r2) {
        return j.D(r2, null, this);
    }

    public final f I(b r19, long r20, long r22, long r24, long r26) {
        long r2 = ((r20 | r22) | r24) | r26;
        LocalTime r3 = this.f333b;
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
        LocalTime r25 = this.f333b;
    L11:
        return S(r19.b(r63, ChronoUnit.DAYS), r25);
    L9:
        r25 = LocalTime.S(r4);
        goto L11
    L5:
        return S(r19, r3);
    }

    public final f K(long r3, j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L11;
        boolean r0 = ((j$.time.temporal.a) r5).b0();
        b r1 = this.f332a;
        if (r0 == false) goto L9;
        return S(r1, this.f333b.h0(r3, r5));
    L9:
        return S(r1.a(r3, r5), this.f333b);
    L11:
        return s(this.f332a.f(), r5.Y(this, r3));
    }

    public final f S(Temporal r3, LocalTime r4) {
        b r0 = this.f332a;
        if (r0 != r3) goto L8;
        if (this.f333b != r4) goto L8;
        return this;
    L8:
        return new f(d.s(r0.f(), r3), r4);
    }

    public final f Y(j$.time.temporal.m r3) {
        if ((r3 instanceof b) == true) goto L5;
        boolean r0 = r3 instanceof LocalTime;
        b r1 = this.f332a;
        if (r0 == false) goto L11;
        return S(r1, (LocalTime) r3);
    L11:
        if ((r3 instanceof f) == false) goto L15;
        return s(r1.f(), (f) r3);
    L15:
        return s(r1.f(), (f) r3.e(this));
    L5:
        return S((b) r3, this.f333b);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ ChronoLocalDateTime a(long r1, j$.time.temporal.p r3) {
        return K(r1, r3);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return K(r1, r3);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ ChronoLocalDateTime b(long r1, TemporalUnit r3) {
        return D(r1, r3);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return D(r1, r3);
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof ChronoLocalDateTime) == true) goto L8;
    L10:
        return false;
    L8:
        if (R((ChronoLocalDateTime) r4) != 0) goto L10;
        return true;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r4) {
        if ((r4 instanceof j$.time.temporal.a) == false) goto L11;
        if (((j$.time.temporal.a) r4).b0() == false) goto L9;
        return this.f333b.g(r4);
    L9:
        return this.f332a.g(r4);
    L11:
        return l(r4).a(k(r4), r4);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal h(LocalDate r1) {
        return Y(r1);
    }

    public final int hashCode() {
        int r0 = this.f332a.hashCode();
        return this.f333b.hashCode() ^ r0;
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
        return Y(r1);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L11;
        if (((j$.time.temporal.a) r2).b0() == false) goto L9;
        return this.f333b.k(r2);
    L9:
        return this.f332a.k(r2);
    L11:
        return r2.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L11;
        if (((j$.time.temporal.a) r2).b0() == false) goto L9;
        j$.time.temporal.l r1 = this.f333b;
    L8:
        return r1.l(r2);
    L9:
        r1 = this.f332a;
        goto L8
    L11:
        return r2.D(this);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final b m() {
        return this.f332a;
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r7, TemporalUnit r8) {
        Objects.requireNonNull(r7, "endExclusive");
        ChronoLocalDateTime r72 = f().O(r7);
        if ((r8 instanceof ChronoUnit) == false) goto L23;
        ChronoUnit r0 = (ChronoUnit) r8;
        ChronoUnit r1 = ChronoUnit.DAYS;
        if (r0.compareTo(r1) >= 0) goto L18;
        j$.time.temporal.a r12 = j$.time.temporal.a.EPOCH_DAY;
        long r2 = r72.k(r12) - this.f332a.k(r12);
        switch(e.f330a[r0.ordinal()]) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            default: goto L17;
        };
    L9:
        r2 = Math.multiplyExact(r2, 2);
        goto L17
    L10:
        r2 = Math.multiplyExact(r2, 24);
        goto L17
    L11:
        r2 = Math.multiplyExact(r2, 1440);
        goto L17
    L12:
        r2 = Math.multiplyExact(r2, 86400);
        goto L17
    L13:
        r2 = Math.multiplyExact(r2, DurationKt.MILLIS_IN_DAY);
        goto L17
    L14:
        r2 = Math.multiplyExact(r2, 86400000000L);
        goto L17
    L15:
        r2 = Math.multiplyExact(r2, 86400000000000L);
    L17:
        return Math.addExact(r2, this.f333b.n(r72.toLocalTime(), r8));
    L18:
        b r02 = r72.m();
        if (r72.toLocalTime().s(this.f333b) >= 0) goto L22;
        r02 = r02.c(1, r1);
    L22:
        return this.f332a.n(r02, r8);
    L23:
        Objects.requireNonNull(r8, "unit");
        return r8.between(this, r72);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final LocalTime toLocalTime() {
        return this.f333b;
    }

    public final String toString() {
        return this.f332a.toString() + "T" + this.f333b.toString();
    }
}
