package j$.time.zone;

import j$.time.ZoneOffset;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.util.TimeZone;
import kotlin.UByte;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class a implements Externalizable {
    private static final long serialVersionUID = -8885321777449118786L;

    /* renamed from: a, reason: collision with root package name */
    public byte f472a;

    /* renamed from: b, reason: collision with root package name */
    public Object f473b;

    public a() {
    }

    public a(byte r1, Object r2) {
        this.f472a = r1;
        this.f473b = r2;
    }

    public static long a(DataInput r4) {
        int r0 = r4.readByte() & UByte.MAX_VALUE;
        if (r0 == 255) goto L5;
        int r02 = (r0 << 16) + ((r4.readByte() & UByte.MAX_VALUE) << 8);
        return ((r02 + (r4.readByte() & UByte.MAX_VALUE)) * 900) - 4575744000L;
    L5:
        return r4.readLong();
    }

    public static ZoneOffset b(DataInput r2) {
        byte r0 = r2.readByte();
        if (r0 != Byte.MAX_VALUE) goto L7;
        return ZoneOffset.ofTotalSeconds(r2.readInt());
    L7:
        return ZoneOffset.ofTotalSeconds(r0 * 900);
    }

    public static void c(long r8, DataOutput r10) {
        if (r8 >= (-4575744000L)) goto L5;
    L10:
        r10.writeByte(255);
        r10.writeLong(r8);
        return;
    L5:
        if (r8 >= 10413792000L) goto L10;
        if ((r8 % 900) != 0) goto L10;
        int r82 = (int) ((r8 + 4575744000L) / 900);
        r10.writeByte((r82 >>> 16) & 255);
        r10.writeByte((r82 >>> 8) & 255);
        r10.writeByte(r82 & 255);
    }

    public static void d(ZoneOffset r2, DataOutput r3) {
        int r22 = r2.getTotalSeconds();
        if ((r22 % 900) != 0) goto L5;
        int r0 = r22 / 900;
    L6:
        r3.writeByte(r0);
        if (r0 != 127) goto L10;
        r3.writeInt(r22);
        return;
    L10:
        return;
    L5:
        r0 = 127;
        goto L6
    }

    private Object readResolve() {
        return this.f473b;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput r11) {
        byte r0 = r11.readByte();
        this.f472a = r0;
        if (r0 != 1) goto L5;
        long[] r02 = ZoneRules.i;
        int r2 = r11.readInt();
        if (r2 != 0) goto L22;
        long[] r5 = r02;
    L23:
        int r3 = 0;
        int r4 = 0;
    L24:
        if (r4 >= r2) goto L26;
        r5[r4] = a(r11);
        r4 = r4 + 1;
        goto L24
    L26:
        int r22 = r2 + 1;
        ZoneOffset[] r6 = new ZoneOffset[r22];
        int r42 = 0;
    L27:
        if (r42 >= r22) goto L29;
        r6[r42] = b(r11);
        r42 = r42 + 1;
        goto L27
    L29:
        int r23 = r11.readInt();
        if (r23 != 0) goto L32;
    L31:
        long[] r7 = r02;
        int r03 = 0;
    L34:
        if (r03 >= r23) goto L36;
        r7[r03] = a(r11);
        r03 = r03 + 1;
        goto L34
    L36:
        int r24 = r23 + 1;
        ZoneOffset[] r8 = new ZoneOffset[r24];
        int r04 = 0;
    L37:
        if (r04 >= r24) goto L39;
        r8[r04] = b(r11);
        r04 = r04 + 1;
        goto L37
    L39:
        int r05 = r11.readByte();
        if (r05 != 0) goto L43;
        e[] r1 = ZoneRules.j;
    L42:
        e[] r9 = r1;
    L44:
        if (r3 >= r05) goto L46;
        r9[r3] = e.a(r11);
        r3 = r3 + 1;
        goto L44
    L46:
        Object r06 = new ZoneRules(r5, r6, r7, r8, r9);
    L47:
        this.f473b = r06;
        return;
    L43:
        r1 = new e[r05];
        goto L42
    L32:
        r02 = new long[r23];
        goto L31
    L22:
        r5 = new long[r2];
        goto L23
    L5:
        if (r0 != 2) goto L7;
        int r07 = b.e;
        long r08 = a(r11);
        ZoneOffset r25 = b(r11);
        ZoneOffset r112 = b(r11);
        if (r25.equals(r112) == true) goto L17;
        r06 = new b(r08, r25, r112);
        goto L47
    L17:
        j$.nio.file.b.a("Offsets must not be equal");
        return;
    L7:
        if (r0 != 3) goto L9;
        r06 = e.a(r11);
        goto L47
    L9:
        if (r0 != 100) goto L12;
        r06 = new ZoneRules(TimeZone.getTimeZone(r11.readUTF()));
        goto L47
    L12:
        throw new StreamCorruptedException("Unknown serialized type");
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput r7) {
        byte r0 = this.f472a;
        Object r6 = this.f473b;
        r7.writeByte(r0);
        if (r0 != 1) goto L5;
        ZoneRules r62 = (ZoneRules) r6;
        r7.writeInt(r62.f469a.length);
        long[] r02 = r62.f469a;
        int r1 = r02.length;
        int r2 = 0;
        int r3 = 0;
    L19:
        if (r3 >= r1) goto L21;
        c(r02[r3], r7);
        r3 = r3 + 1;
        goto L19
    L21:
        ZoneOffset[] r03 = r62.f470b;
        int r12 = r03.length;
        int r32 = 0;
    L22:
        if (r32 >= r12) goto L24;
        d(r03[r32], r7);
        r32 = r32 + 1;
        goto L22
    L24:
        r7.writeInt(r62.f471c.length);
        long[] r04 = r62.f471c;
        int r13 = r04.length;
        int r33 = 0;
    L25:
        if (r33 >= r13) goto L27;
        c(r04[r33], r7);
        r33 = r33 + 1;
        goto L25
    L27:
        ZoneOffset[] r05 = r62.e;
        int r14 = r05.length;
        int r34 = 0;
    L28:
        if (r34 >= r14) goto L30;
        d(r05[r34], r7);
        r34 = r34 + 1;
        goto L28
    L30:
        r7.writeByte(r62.f.length);
        e[] r63 = r62.f;
        int r06 = r63.length;
    L31:
        if (r2 >= r06) goto L33;
        r63[r2].b(r7);
        r2 = r2 + 1;
        goto L31
    L33:
        return;
    L5:
        if (r0 != 2) goto L7;
        b r64 = (b) r6;
        c(r64.f474a, r7);
        d(r64.f476c, r7);
        d(r64.d, r7);
        return;
    L7:
        if (r0 != 3) goto L9;
        ((e) r6).b(r7);
        return;
    L9:
        if (r0 != 100) goto L13;
        r7.writeUTF(((ZoneRules) r6).g.getID());
        return;
    L13:
        throw new InvalidClassException("Unknown serialized type");
    }
}
