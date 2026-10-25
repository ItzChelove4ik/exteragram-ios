package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Arrays;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class p extends d {
    private static final long serialVersionUID = -5207853542612002020L;

    /* renamed from: a, reason: collision with root package name */
    public final transient n f349a;

    /* renamed from: b, reason: collision with root package name */
    public final transient int f350b;

    /* renamed from: c, reason: collision with root package name */
    public final transient int f351c;
    public final transient int d;

    public p(n r1, int r2, int r3, int r4) {
        r1.c0(r2, r3, r4);
        this.f349a = r1;
        this.f350b = r2;
        this.f351c = r3;
        this.d = r4;
    }

    public p(n r5, long r6) {
        int r62 = (int) r6;
        r5.a0();
        if (r62 >= r5.e) goto L5;
    L11:
        j$.nio.file.b.h("Hijrah date out of range");
        throw null;
    L5:
        if (r62 >= r5.f) goto L11;
        int r7 = Arrays.binarySearch(r5.d, r62);
        if (r7 >= 0) goto L9;
        r7 = (-r7) - 2;
    L9:
        int r1 = r5.g;
        int r2 = (r7 + r1) / 12;
        int r12 = (r1 + r7) % 12;
        int[] r63 = {r2, r12 + 1, (r62 - r5.d[r7]) + 1};
        this.f349a = r5;
        this.f350b = r63[0];
        this.f351c = r63[1];
        this.d = r63[2];
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new d0((byte) 6, this);
    }

    @Override // j$.time.chrono.d
    public final /* bridge */ /* synthetic */ b I(long r1) {
        return a0(r1);
    }

    @Override // j$.time.chrono.d
    public final /* bridge */ /* synthetic */ b K(long r1) {
        return b0(r1);
    }

    @Override // j$.time.chrono.b
    public final long L() {
        return this.f349a.c0(this.f350b, this.f351c, this.d);
    }

    @Override // j$.time.chrono.b
    public final ChronoLocalDateTime M(LocalTime r2) {
        return new f(this, r2);
    }

    @Override // j$.time.chrono.b
    public final l P() {
        return q.AH;
    }

    @Override // j$.time.chrono.d
    public final b S(long r3) {
        if (r3 != 0) goto L6;
        return this;
    L6:
        return c0(Math.addExact(this.f350b, (int) r3), this.f351c, this.d);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b U(j$.time.temporal.o r1) {
        return (p) super.U(r1);
    }

    public final int Y() {
        return this.f349a.f0(this.f350b, this.f351c - 1) + this.d;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ b a(long r1, j$.time.temporal.p r3) {
        return d0(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return d0(r1, r3);
    }

    public final p a0(long r5) {
        return new p(this.f349a, L() + r5);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final b b(long r1, TemporalUnit r3) {
        return (p) super.b(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final Temporal b(long r1, TemporalUnit r3) {
        return (p) super.b(r1, r3);
    }

    public final p b0(long r9) {
        if (r9 != 0) goto L5;
        return this;
    L5:
        long r0 = ((this.f350b * 12) + (this.f351c - 1)) + r9;
        n r92 = this.f349a;
        long r4 = Math.floorDiv(r0, 12);
        int r10 = r92.g;
        if (r4 < (r10 / 12)) goto L12;
        if (r4 > ((((r92.d.length - 1) + r10) / 12) - 1)) goto L12;
        return c0((int) r4, ((int) Math.floorMod(r0, 12)) + 1, this.d);
    L12:
        throw new j$.time.b("Invalid Hijrah year: " + r4);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final b c(long r1, TemporalUnit r3) {
        return (p) super.c(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final Temporal c(long r1, TemporalUnit r3) {
        return (p) super.c(r1, r3);
    }

    public final p c0(int r2, int r3, int r4) {
        int r0 = this.f349a.d0(r2, r3);
        if (r4 <= r0) goto L6;
        r4 = r0;
    L6:
        return new p(this.f349a, r2, r3, r4);
    }

    public final p d0(long r8, j$.time.temporal.p r10) {
        if ((r10 instanceof j$.time.temporal.a) == false) goto L39;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r10;
        this.f349a.B(r0).b(r8, r0);
        int r1 = (int) r8;
        switch(o.f348a[r0.ordinal()]) {
            case 1: goto L37;
            case 2: goto L35;
            case 3: goto L33;
            case 4: goto L31;
            case 5: goto L29;
            case 6: goto L27;
            case 7: goto L25;
            case 8: goto L23;
            case 9: goto L21;
            case 10: goto L19;
            case 11: goto L13;
            case 12: goto L11;
            case 13: goto L9;
            default: goto L7;
        };
    L7:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r10));
    L9:
        return c0(1 - this.f350b, this.f351c, this.d);
    L11:
        return c0(r1, this.f351c, this.d);
    L13:
        if (this.f350b >= 1) goto L17;
        r1 = 1 - r1;
    L17:
        return c0(r1, this.f351c, this.d);
    L19:
        return b0(r8 - (((this.f350b * 12) + this.f351c) - 1));
    L21:
        return c0(this.f350b, r1, this.d);
    L23:
        return a0((r8 - k(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR)) * 7);
    L25:
        return new p(this.f349a, r8);
    L27:
        return a0(r8 - k(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
    L29:
        return a0(r8 - k(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
    L31:
        return a0(r8 - (((int) Math.floorMod(L() + 3, 7)) + 1));
    L33:
        return a0((r8 - k(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH)) * 7);
    L35:
        return a0(Math.min(r1, this.f349a.f0(this.f350b, 12)) - Y());
    L37:
        return c0(this.f350b, this.f351c, r1);
    L39:
        return (p) super.a(r8, r10);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof p) == false) goto L16;
        p r52 = (p) r5;
        if (this.f350b != r52.f350b) goto L16;
        if (this.f351c != r52.f351c) goto L16;
        if (this.d != r52.d) goto L16;
        if (this.f349a.equals(r52.f349a) == false) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // j$.time.chrono.b
    public final k f() {
        return this.f349a;
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (p) super.j(r1);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final int hashCode() {
        int r0 = this.f350b;
        int r1 = this.f351c;
        int r2 = this.d;
        this.f349a.getClass();
        return ((r0 & (-2048)) ^ 2100100019) ^ (((r0 << 11) + (r1 << 6)) + r2);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b j(j$.time.temporal.m r1) {
        return (p) super.j(r1);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L32;
        int r1 = 1;
        switch(o.f348a[((j$.time.temporal.a) r5).ordinal()]) {
            case 1: goto L30;
            case 2: goto L29;
            case 3: goto L28;
            case 4: goto L27;
            case 5: goto L26;
            case 6: goto L25;
            case 7: goto L24;
            case 8: goto L21;
            case 9: goto L20;
            case 10: goto L19;
            case 11: goto L17;
            case 12: goto L14;
            case 13: goto L9;
            default: goto L7;
        };
    L14:
        int r4 = this.f350b;
    L16:
        return r4;
    L17:
        r4 = this.f350b;
        goto L16
    L20:
        r4 = this.f351c;
        goto L16
    L21:
        int r42 = (Y() - 1) / 7;
    L22:
        r4 = r42 + 1;
        goto L16
    L25:
        r42 = (Y() - 1) % 7;
        goto L22
    L26:
        r42 = (this.d - 1) % 7;
        goto L22
    L27:
        r42 = (int) Math.floorMod(L() + 3, 7);
        goto L22
    L28:
        r42 = (this.d - 1) / 7;
        goto L22
    L29:
        r4 = Y();
        goto L16
    L30:
        r4 = this.d;
        goto L16
    L7:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r5));
    L9:
        if (this.f350b > 1) goto L13;
        r1 = 0;
    L13:
        return r1;
    L19:
        return ((this.f350b * 12) + this.f351c) - 1;
    L24:
        return L();
    L32:
        return r5.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L23;
        if (i(r5) == false) goto L21;
        j$.time.temporal.a r52 = (j$.time.temporal.a) r5;
        int r0 = o.f348a[r52.ordinal()];
        if (r0 == 1) goto L19;
        if (r0 == 2) goto L17;
        if (r0 == 3) goto L15;
        return this.f349a.B(r52);
    L15:
        return j$.time.temporal.s.f(1, 5);
    L17:
        return j$.time.temporal.s.f(1, this.f349a.f0(this.f350b, 12));
    L19:
        return j$.time.temporal.s.f(1, this.f349a.d0(this.f350b, this.f351c));
    L21:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r5));
    L23:
        return r5.D(this);
    }
}
