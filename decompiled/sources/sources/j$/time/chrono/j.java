package j$.time.chrono;

import j$.time.Duration;
import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import j$.time.zone.ZoneRules;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class j implements ChronoZonedDateTime, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;

    /* renamed from: a, reason: collision with root package name */
    public final transient f f343a;

    /* renamed from: b, reason: collision with root package name */
    public final transient ZoneOffset f344b;

    /* renamed from: c, reason: collision with root package name */
    public final transient ZoneId f345c;

    public j(ZoneId r2, ZoneOffset r3, f r4) {
        Objects.requireNonNull(r4, "dateTime");
        this.f343a = r4;
        Objects.requireNonNull(r3, "offset");
        this.f344b = r3;
        Objects.requireNonNull(r2, "zone");
        this.f345c = r2;
    }

    public static j D(ZoneId r11, ZoneOffset r12, f r13) {
        Objects.requireNonNull(r13, "localDateTime");
        Objects.requireNonNull(r11, "zone");
        if ((r11 instanceof ZoneOffset) == true) goto L5;
        ZoneRules r0 = r11.getRules();
        LocalDateTime r1 = LocalDateTime.D(r13);
        List r2 = r0.f(r1);
        if (r2.size() != 1) goto L10;
        r12 = (ZoneOffset) r2.get(0);
    L18:
        Objects.requireNonNull(r12, "offset");
        return new j(r11, r12, r13);
    L10:
        if (r2.size() != 0) goto L13;
        j$.time.zone.b r122 = r0.e(r1);
        r13 = r13.I(r13.f332a, 0, 0, Duration.ofSeconds(r122.d.getTotalSeconds() - r122.f476c.getTotalSeconds()).getSeconds(), 0);
        r12 = r122.d;
        goto L18
    L13:
        if (r12 != null) goto L15;
    L17:
        r12 = (ZoneOffset) r2.get(0);
    L16:
        r13 = r13;
        goto L18
    L15:
        if (r2.contains(r12) == false) goto L17;
    L5:
        return new j(r11, (ZoneOffset) r11, r13);
    }

    public static j I(k r3, Instant r4, ZoneId r5) {
        ZoneOffset r0 = r5.getRules().getOffset(r4);
        Objects.requireNonNull(r0, "offset");
        return new j(r5, r0, (f) r3.O(LocalDateTime.K(r4.getEpochSecond(), r4.getNano(), r0)));
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static j s(k r1, Temporal r2) {
        j r22 = (j) r2;
        if (r1.equals(r22.f()) == false) goto L5;
        return r22;
    L5:
        j$.nio.file.b.k("Chronology mismatch, required: ", r1.getId(), r22.f().getId());
        return null;
    }

    private Object writeReplace() {
        return new d0((byte) 3, this);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDateTime A() {
        return this.f343a;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime G(ZoneId r2) {
        f r0 = this.f343a;
        return D(r2, this.f344b, r0);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime, j$.time.temporal.Temporal
    public final ChronoZonedDateTime a(long r4, j$.time.temporal.p r6) {
        if ((r6 instanceof j$.time.temporal.a) == false) goto L15;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r6;
        int r1 = i.f341a[r0.ordinal()];
        if (r1 == 1) goto L13;
        if (r1 == 2) goto L10;
        f r42 = this.f343a.K(r4, r6);
        return D(this.f345c, this.f344b, r42);
    L10:
        ZoneOffset r43 = ZoneOffset.ofTotalSeconds(r0.f447b.a(r4, r0));
        Instant r44 = this.f343a.toInstant(r43);
        ZoneId r5 = this.f345c;
        return I(f(), r44, r5);
    L13:
        return b(r4 - Z(), ChronoUnit.SECONDS);
    L15:
        return s(f(), r6.Y(this, r4));
    }

    @Override // j$.time.chrono.ChronoZonedDateTime, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return a(r1, r3);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime, j$.time.temporal.Temporal
    public final ChronoZonedDateTime b(long r2, TemporalUnit r4) {
        if ((r4 instanceof ChronoUnit) == false) goto L7;
        return j(this.f343a.D(r2, r4));
    L7:
        return s(f(), r4.s(this, r2));
    }

    @Override // j$.time.chrono.ChronoZonedDateTime, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return b(r1, r3);
    }

    public final boolean equals(Object r2) {
        if (this != r2) goto L5;
        return true;
    L5:
        if ((r2 instanceof ChronoZonedDateTime) == true) goto L7;
        return false;
    L7:
        if (t((ChronoZonedDateTime) r2) != 0) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneOffset getOffset() {
        return this.f344b;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneId getZone() {
        return this.f345c;
    }

    public final int hashCode() {
        int r0 = this.f343a.hashCode() ^ this.f344b.f315b;
        return Integer.rotateLeft(this.f345c.hashCode(), 3) ^ r0;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L10;
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.s(this) == true) goto L13;
        return false;
    L13:
        return true;
    L10:
        return true;
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r2, TemporalUnit r3) {
        Objects.requireNonNull(r2, "endExclusive");
        ChronoZonedDateTime r22 = f().w(r2);
        if ((r3 instanceof ChronoUnit) == false) goto L6;
        ChronoZonedDateTime r23 = r22.o(this.f344b);
        return this.f343a.n(r23.A(), r3);
    L6:
        Objects.requireNonNull(r3, "unit");
        return r3.between(this, r22);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime o(ZoneId r3) {
        Objects.requireNonNull(r3, "zone");
        if (this.f345c.equals(r3) == false) goto L5;
        return this;
    L5:
        Instant r0 = this.f343a.toInstant(this.f344b);
        return I(f(), r0, r3);
    }

    public final String toString() {
        String r0 = this.f343a.toString() + this.f344b.f316c;
        ZoneOffset r1 = this.f344b;
        ZoneId r3 = this.f345c;
        if (r1 != r3) goto L5;
        return r0;
    L5:
        return r0 + "[" + r3.toString() + "]";
    }
}
