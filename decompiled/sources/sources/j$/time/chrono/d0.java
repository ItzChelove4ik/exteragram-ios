package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class d0 implements Externalizable {
    private static final long serialVersionUID = -6103370247208168577L;

    /* renamed from: a, reason: collision with root package name */
    public byte f328a;

    /* renamed from: b, reason: collision with root package name */
    public Object f329b;

    public d0() {
    }

    public d0(byte r1, Object r2) {
        this.f328a = r1;
        this.f329b = r2;
    }

    private Object readResolve() {
        return this.f329b;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput r5) {
        byte r0 = r5.readByte();
        this.f328a = r0;
        switch(r0) {
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            case 8: goto L7;
            case 9: goto L6;
            default: goto L5;
        };
    L6:
        int r02 = g.e;
        Object r3 = new g(k.of(r5.readUTF()), r5.readInt(), r5.readInt(), r5.readInt());
    L15:
        this.f329b = r3;
        return;
    L7:
        int r03 = r5.readInt();
        byte r1 = r5.readByte();
        byte r52 = r5.readByte();
        f0.f334c.getClass();
        r3 = new h0(LocalDate.of(r03 - 543, r1, r52));
        goto L15
    L8:
        int r04 = r5.readInt();
        byte r12 = r5.readByte();
        byte r53 = r5.readByte();
        z.f365c.getClass();
        r3 = new b0(LocalDate.of(r04 + 1911, r12, r53));
        goto L15
    L9:
        n r05 = (n) r5.readObject();
        int r13 = r5.readInt();
        byte r2 = r5.readByte();
        byte r54 = r5.readByte();
        r05.getClass();
        r3 = new p(r05, r13, r2, r54);
        goto L15
    L10:
        x r06 = x.d;
        r3 = x.r(r5.readByte());
        goto L15
    L11:
        LocalDate r07 = w.d;
        int r08 = r5.readInt();
        byte r14 = r5.readByte();
        byte r55 = r5.readByte();
        u.f356c.getClass();
        r3 = new w(LocalDate.of(r08, r14, r55));
        goto L15
    L12:
        ChronoLocalDateTime r09 = (ChronoLocalDateTime) r5.readObject();
        ZoneOffset r15 = (ZoneOffset) r5.readObject();
        r3 = r09.H(r15).G((ZoneId) r5.readObject());
        goto L15
    L13:
        r3 = ((b) r5.readObject()).M((LocalTime) r5.readObject());
        goto L15
    L14:
        ConcurrentHashMap r010 = a.f322a;
        r3 = k.of(r5.readUTF());
        goto L15
    L5:
        throw new StreamCorruptedException("Unknown serialized type");
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput r2) {
        byte r0 = this.f328a;
        Object r1 = this.f329b;
        r2.writeByte(r0);
        switch(r0) {
            case 1: goto L22;
            case 2: goto L20;
            case 3: goto L18;
            case 4: goto L16;
            case 5: goto L14;
            case 6: goto L12;
            case 7: goto L10;
            case 8: goto L8;
            case 9: goto L6;
            default: goto L5;
        };
    L6:
        g r12 = (g) r1;
        r2.writeUTF(r12.f335a.getId());
        r2.writeInt(r12.f336b);
        r2.writeInt(r12.f337c);
        r2.writeInt(r12.d);
        return;
    L8:
        h0 r13 = (h0) r1;
        r13.getClass();
        r2.writeInt(r13.g(j$.time.temporal.a.YEAR));
        r2.writeByte(r13.g(j$.time.temporal.a.MONTH_OF_YEAR));
        r2.writeByte(r13.g(j$.time.temporal.a.DAY_OF_MONTH));
        return;
    L10:
        b0 r14 = (b0) r1;
        r14.getClass();
        r2.writeInt(r14.g(j$.time.temporal.a.YEAR));
        r2.writeByte(r14.g(j$.time.temporal.a.MONTH_OF_YEAR));
        r2.writeByte(r14.g(j$.time.temporal.a.DAY_OF_MONTH));
        return;
    L12:
        p r15 = (p) r1;
        r2.writeObject(r15.f349a);
        r2.writeInt(r15.g(j$.time.temporal.a.YEAR));
        r2.writeByte(r15.g(j$.time.temporal.a.MONTH_OF_YEAR));
        r2.writeByte(r15.g(j$.time.temporal.a.DAY_OF_MONTH));
        return;
    L14:
        r2.writeByte(((x) r1).f361a);
        return;
    L16:
        w r16 = (w) r1;
        r16.getClass();
        r2.writeInt(r16.g(j$.time.temporal.a.YEAR));
        r2.writeByte(r16.g(j$.time.temporal.a.MONTH_OF_YEAR));
        r2.writeByte(r16.g(j$.time.temporal.a.DAY_OF_MONTH));
        return;
    L18:
        j r17 = (j) r1;
        r2.writeObject(r17.f343a);
        r2.writeObject(r17.f344b);
        r2.writeObject(r17.f345c);
        return;
    L20:
        f r18 = (f) r1;
        r2.writeObject(r18.f332a);
        r2.writeObject(r18.f333b);
        return;
    L22:
        r2.writeUTF(((a) r1).getId());
        return;
    L5:
        throw new InvalidClassException("Unknown serialized type");
    }
}
