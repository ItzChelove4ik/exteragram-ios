package j$.time;

import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class o implements Externalizable {
    private static final long serialVersionUID = -7683839454370182990L;

    /* renamed from: a, reason: collision with root package name */
    public byte f433a;

    /* renamed from: b, reason: collision with root package name */
    public Object f434b;

    public o() {
    }

    public o(byte r1, Object r2) {
        this.f433a = r1;
        this.f434b = r2;
    }

    public static Object a(byte r2, ObjectInput r3) {
        switch(r2) {
            case 1: goto L38;
            case 2: goto L36;
            case 3: goto L34;
            case 4: goto L33;
            case 5: goto L30;
            case 6: goto L21;
            case 7: goto L19;
            case 8: goto L18;
            case 9: goto L15;
            case 10: goto L13;
            case 11: goto L11;
            case 12: goto L9;
            case 13: goto L7;
            case 14: goto L5;
            default: goto L4;
        };
    L5:
        Period r22 = Period.d;
        return Period.of(r3.readInt(), r3.readInt(), r3.readInt());
    L7:
        int r23 = MonthDay.f297c;
        return MonthDay.of(r3.readByte(), r3.readByte());
    L9:
        int r24 = YearMonth.f311c;
        return YearMonth.of(r3.readInt(), r3.readByte());
    L11:
        int r25 = Year.f309b;
        return Year.of(r3.readInt());
    L13:
        int r26 = OffsetDateTime.f300c;
        LocalDate r27 = LocalDate.d;
        return OffsetDateTime.of(LocalDateTime.of(LocalDate.of(r3.readInt(), r3.readByte(), r3.readByte()), LocalTime.e0(r3)), ZoneOffset.d0(r3));
    L15:
        int r28 = OffsetTime.f303c;
        return OffsetTime.of(LocalTime.e0(r3), ZoneOffset.d0(r3));
    L19:
        int r29 = r.d;
        return ZoneId.D(r3.readUTF(), false);
    L21:
        LocalDateTime r210 = LocalDateTime.f291c;
        LocalDate r211 = LocalDate.d;
        LocalDateTime r212 = LocalDateTime.of(LocalDate.of(r3.readInt(), r3.readByte(), r3.readByte()), LocalTime.e0(r3));
        ZoneOffset r0 = ZoneOffset.d0(r3);
        ZoneId r32 = (ZoneId) a(r3.readByte(), r3);
        Objects.requireNonNull(r212, "localDateTime");
        Objects.requireNonNull(r0, "offset");
        Objects.requireNonNull(r32, "zone");
        if ((r32 instanceof ZoneOffset) == false) goto L29;
        if (r0.equals(r32) == true) goto L29;
        j$.nio.file.b.a("ZoneId must match ZoneOffset");
        return null;
    L29:
        return new ZonedDateTime(r212, r0, r32);
    L30:
        LocalDateTime r213 = LocalDateTime.f291c;
        LocalDate r214 = LocalDate.d;
        return LocalDateTime.of(LocalDate.of(r3.readInt(), r3.readByte(), r3.readByte()), LocalTime.e0(r3));
    L34:
        LocalDate r215 = LocalDate.d;
        return LocalDate.of(r3.readInt(), r3.readByte(), r3.readByte());
    L36:
        Instant r216 = Instant.f285c;
        return Instant.ofEpochSecond(r3.readLong(), r3.readInt());
    L38:
        Duration r217 = Duration.ZERO;
        return Duration.ofSeconds(r3.readLong(), r3.readInt());
    L4:
        throw new StreamCorruptedException("Unknown serialized type");
    L18:
        return ZoneOffset.d0(r3);
    L33:
        return LocalTime.e0(r3);
    }

    private Object readResolve() {
        return this.f434b;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput r2) {
        byte r0 = r2.readByte();
        this.f433a = r0;
        this.f434b = a(r0, r2);
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput r4) {
        byte r0 = this.f433a;
        Object r3 = this.f434b;
        r4.writeByte(r0);
        switch(r0) {
            case 1: goto L32;
            case 2: goto L30;
            case 3: goto L28;
            case 4: goto L26;
            case 5: goto L24;
            case 6: goto L22;
            case 7: goto L20;
            case 8: goto L18;
            case 9: goto L16;
            case 10: goto L14;
            case 11: goto L12;
            case 12: goto L10;
            case 13: goto L8;
            case 14: goto L6;
            default: goto L5;
        };
    L6:
        Period r32 = (Period) r3;
        r4.writeInt(r32.f306a);
        r4.writeInt(r32.f307b);
        r4.writeInt(r32.f308c);
        return;
    L8:
        MonthDay r33 = (MonthDay) r3;
        r4.writeByte(r33.f298a);
        r4.writeByte(r33.f299b);
        return;
    L10:
        YearMonth r34 = (YearMonth) r3;
        r4.writeInt(r34.f312a);
        r4.writeByte(r34.f313b);
        return;
    L12:
        r4.writeInt(((Year) r3).f310a);
        return;
    L14:
        OffsetDateTime r35 = (OffsetDateTime) r3;
        LocalDateTime r02 = r35.f301a;
        LocalDate r1 = r02.f292a;
        r4.writeInt(r1.f288a);
        r4.writeByte(r1.f289b);
        r4.writeByte(r1.f290c);
        r02.f293b.j0(r4);
        r35.f302b.e0(r4);
        return;
    L16:
        OffsetTime r36 = (OffsetTime) r3;
        r36.f304a.j0(r4);
        r36.f305b.e0(r4);
        return;
    L18:
        ((ZoneOffset) r3).e0(r4);
        return;
    L20:
        r4.writeUTF(((r) r3).f439b);
        return;
    L22:
        ZonedDateTime r37 = (ZonedDateTime) r3;
        LocalDateTime r03 = r37.f317a;
        LocalDate r12 = r03.f292a;
        r4.writeInt(r12.f288a);
        r4.writeByte(r12.f289b);
        r4.writeByte(r12.f290c);
        r03.f293b.j0(r4);
        r37.f318b.e0(r4);
        r37.f319c.S(r4);
        return;
    L24:
        LocalDateTime r38 = (LocalDateTime) r3;
        LocalDate r04 = r38.f292a;
        r4.writeInt(r04.f288a);
        r4.writeByte(r04.f289b);
        r4.writeByte(r04.f290c);
        r38.f293b.j0(r4);
        return;
    L26:
        ((LocalTime) r3).j0(r4);
        return;
    L28:
        LocalDate r39 = (LocalDate) r3;
        r4.writeInt(r39.f288a);
        r4.writeByte(r39.f289b);
        r4.writeByte(r39.f290c);
        return;
    L30:
        Instant r310 = (Instant) r3;
        r4.writeLong(r310.f286a);
        r4.writeInt(r310.f287b);
        return;
    L32:
        Duration r311 = (Duration) r3;
        r4.writeLong(r311.f283a);
        r4.writeInt(r311.f284b);
        return;
    L5:
        throw new InvalidClassException("Unknown serialized type");
    }
}
