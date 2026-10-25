package j$.time.zone;

import j$.time.DayOfWeek;
import j$.time.LocalTime;
import j$.time.ZoneOffset;
import j$.time.k;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class e implements Serializable {
    private static final long serialVersionUID = 6889046316657758795L;

    /* renamed from: a, reason: collision with root package name */
    public final k f479a;

    /* renamed from: b, reason: collision with root package name */
    public final byte f480b;

    /* renamed from: c, reason: collision with root package name */
    public final DayOfWeek f481c;
    public final LocalTime d;
    public final boolean e;
    public final d f;
    public final ZoneOffset g;
    public final ZoneOffset h;
    public final ZoneOffset i;

    static {
    }

    public e(k r1, int r2, DayOfWeek r3, LocalTime r4, boolean r5, d r6, ZoneOffset r7, ZoneOffset r8, ZoneOffset r9) {
        this.f479a = r1;
        this.f480b = (byte) r2;
        this.f481c = r3;
        this.d = r4;
        this.e = r5;
        this.f = r6;
        this.g = r7;
        this.h = r8;
        this.i = r9;
    }

    public static e a(DataInput r18) {
        int r0 = r18.readInt();
        k r3 = k.K(r0 >>> 28);
        int r4 = ((264241152 & r0) >>> 22) - 32;
        int r1 = (3670016 & r0) >>> 19;
        if (r1 != 0) goto L5;
        DayOfWeek r5 = null;
    L6:
        int r12 = (507904 & r0) >>> 14;
        d r8 = d.values()[(r0 & 12288) >>> 12];
        int r6 = (r0 & 4080) >>> 4;
        int r7 = (r0 & 12) >>> 2;
        int r02 = r0 & 3;
        boolean r11 = false;
        if (r12 != 31) goto L9;
        long r122 = r18.readInt();
        LocalTime r14 = LocalTime.e;
        j$.time.temporal.a.SECOND_OF_DAY.a0(r122);
        int r142 = (int) (r122 / 3600);
        k r16 = r3;
        e r17 = null;
        long r123 = r122 - (r142 * 3600);
        LocalTime r2 = LocalTime.D(r142, (int) (r123 / 60), (int) (r123 - (r2 * 60)), 0);
    L11:
        if (r6 != 255) goto L14;
        ZoneOffset r32 = ZoneOffset.ofTotalSeconds(r18.readInt());
    L13:
        ZoneOffset r9 = r32;
        if (r7 != 3) goto L19;
        ZoneOffset r33 = ZoneOffset.ofTotalSeconds(r18.readInt());
    L18:
        ZoneOffset r10 = r33;
        if (r02 != 3) goto L23;
        int r03 = r18.readInt();
    L22:
        ZoneOffset r04 = ZoneOffset.ofTotalSeconds(r03);
        if (r12 != 24) goto L27;
        r11 = true;
    L27:
        boolean r72 = r11;
        k r34 = r16;
        Objects.requireNonNull(r34, "month");
        Objects.requireNonNull(r2, "time");
        Objects.requireNonNull(r8, "timeDefnition");
        Objects.requireNonNull(r9, "standardOffset");
        Objects.requireNonNull(r10, "offsetBefore");
        Objects.requireNonNull(r04, "offsetAfter");
        if (r4 >= (-28)) goto L30;
    L44:
        j$.nio.file.b.a("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        return r17;
    L30:
        if (r4 > 31) goto L44;
        if (r4 == 0) goto L44;
        if (r72 == false) goto L39;
        if (r2.equals(LocalTime.g) == true) goto L39;
        j$.nio.file.b.a("Time must be midnight when end of day flag is true");
        return r17;
    L39:
        if (r2.getNano() == 0) goto L41;
        j$.nio.file.b.a("Time's nano-of-second must be zero");
        return r17;
    L41:
        return new e(r34, r4, r5, r2, r72, r8, r9, r10, r04);
    L23:
        r03 = (r02 * 1800) + r9.getTotalSeconds();
        goto L22
    L19:
        r33 = ZoneOffset.ofTotalSeconds((r7 * 1800) + r9.getTotalSeconds());
        goto L18
    L14:
        r32 = ZoneOffset.ofTotalSeconds((r6 - 128) * 900);
        goto L13
    L9:
        r16 = r3;
        r17 = null;
        int r22 = r12 % 24;
        LocalTime r35 = LocalTime.e;
        j$.time.temporal.a.HOUR_OF_DAY.a0(r22);
        r2 = LocalTime.h[r22];
        goto L11
    L5:
        r5 = DayOfWeek.s(r1);
        goto L6
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 3, this);
    }

    public final void b(DataOutput r13) {
        if (this.e == false) goto L5;
        int r0 = 86400;
    L6:
        int r1 = this.g.getTotalSeconds();
        int r2 = this.h.getTotalSeconds() - r1;
        int r3 = this.i.getTotalSeconds() - r1;
        if ((r0 % 3600) == 0) goto L9;
        int r4 = 31;
    L14:
        if ((r1 % 900) != 0) goto L16;
        int r6 = (r1 / 900) + 128;
    L18:
        if (r2 == 0) goto L23;
        if (r2 == 1800) goto L23;
        if (r2 == 3600) goto L23;
        int r22 = 3;
    L24:
        if (r3 == 0) goto L29;
        if (r3 == 1800) goto L29;
        if (r3 == 3600) goto L29;
        int r32 = 3;
    L30:
        DayOfWeek r8 = this.f481c;
        if (r8 != null) goto L33;
        int r82 = 0;
    L34:
        r13.writeInt((((((((this.f479a.getValue() << 28) + ((this.f480b + 32) << 22)) + (r82 << 19)) + (r4 << 14)) + (this.f.ordinal() << 12)) + (r6 << 4)) + (r22 << 2)) + r32);
        if (r4 != 31) goto L37;
        r13.writeInt(r0);
    L37:
        if (r6 != 255) goto L39;
        r13.writeInt(r1);
    L39:
        if (r22 != 3) goto L41;
        r13.writeInt(this.h.getTotalSeconds());
    L41:
        if (r32 != 3) goto L44;
        r13.writeInt(this.i.getTotalSeconds());
        return;
    L44:
        return;
    L33:
        r82 = r8.getValue();
    L29:
        r32 = r3 / 1800;
    L23:
        r22 = r2 / 1800;
        goto L24
    L16:
        r6 = 255;
        goto L18
    L9:
        if (this.e == false) goto L11;
        r4 = 24;
        goto L14
    L11:
        r4 = this.d.getHour();
        goto L14
    L5:
        r0 = this.d.g0();
        goto L6
    }

    public final boolean equals(Object r3) {
        if (r3 != this) goto L5;
        return true;
    L5:
        if ((r3 instanceof e) == false) goto L26;
        e r32 = (e) r3;
        if (this.f479a == r32.f479a) goto L9;
        return false;
    L9:
        if (this.f480b == r32.f480b) goto L11;
        return false;
    L11:
        if (this.f481c == r32.f481c) goto L13;
        return false;
    L13:
        if (this.f == r32.f) goto L15;
        return false;
    L15:
        if (this.d.equals(r32.d) == true) goto L17;
        return false;
    L17:
        if (this.e == r32.e) goto L19;
        return false;
    L19:
        if (this.g.equals(r32.g) == true) goto L21;
        return false;
    L21:
        if (this.h.equals(r32.h) == true) goto L23;
        return false;
    L23:
        if (this.i.equals(r32.i) == false) goto L37;
        return true;
    L37:
        return false;
    L26:
        return false;
    }

    public final int hashCode() {
        int r0 = (((this.d.g0() + (this.e ? 1 : 0)) << 15) + (this.f479a.ordinal() << 11)) + ((this.f480b + 32) << 5);
        DayOfWeek r1 = this.f481c;
        if (r1 != null) goto L5;
        int r12 = 7;
    L6:
        int r02 = r0 + (r12 << 2);
        int r13 = this.f.ordinal() + r02;
        int r03 = (this.g.f315b ^ r13) ^ this.h.f315b;
        return this.i.f315b ^ r03;
    L5:
        r12 = r1.ordinal();
        goto L6
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("TransitionRule[");
        if ((this.i.f315b - this.h.f315b) <= 0) goto L5;
        String r1 = "Gap ";
    L6:
        r0.append(r1);
        r0.append(this.h);
        r0.append(" to ");
        r0.append(this.i);
        r0.append(", ");
        DayOfWeek r12 = this.f481c;
        if (r12 == null) goto L14;
        byte r3 = this.f480b;
        if (r3 != (-1)) goto L11;
        r0.append(r12.name());
        r0.append(" on or before last day of ");
        r0.append(this.f479a.name());
    L15:
        r0.append(" at ");
        if (this.e == false) goto L18;
        String r13 = "24:00";
    L19:
        r0.append(r13);
        r0.append(" ");
        r0.append(this.f);
        r0.append(", standard offset ");
        r0.append(this.g);
        r0.append(']');
        return r0.toString();
    L18:
        r13 = this.d.toString();
        goto L19
    L11:
        if (r3 >= 0) goto L13;
        r0.append(r12.name());
        r0.append(" on or before last day minus ");
        r0.append((-this.f480b) - 1);
        r0.append(" of ");
        r0.append(this.f479a.name());
        goto L15
    L13:
        r0.append(r12.name());
        r0.append(" on or after ");
        r0.append(this.f479a.name());
        r0.append(' ');
        r0.append(this.f480b);
        goto L15
    L14:
        r0.append(this.f479a.name());
        r0.append(' ');
        r0.append(this.f480b);
        goto L15
    L5:
        r1 = "Overlap ";
        goto L6
    }
}
