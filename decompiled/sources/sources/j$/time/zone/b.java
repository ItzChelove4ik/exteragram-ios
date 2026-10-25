package j$.time.zone;

import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class b implements Comparable, Serializable {
    public static final /* synthetic */ int e = 0;
    private static final long serialVersionUID = -6946044323557704546L;

    /* renamed from: a, reason: collision with root package name */
    public final long f474a;

    /* renamed from: b, reason: collision with root package name */
    public final LocalDateTime f475b;

    /* renamed from: c, reason: collision with root package name */
    public final ZoneOffset f476c;
    public final ZoneOffset d;

    static {
    }

    public b(long r2, ZoneOffset r4, ZoneOffset r5) {
        this.f474a = r2;
        this.f475b = LocalDateTime.K(r2, 0, r4);
        this.f476c = r4;
        this.d = r5;
    }

    public b(LocalDateTime r3, ZoneOffset r4, ZoneOffset r5) {
        this.f474a = r3.toEpochSecond(r4);
        this.f475b = r3;
        this.f476c = r4;
        this.d = r5;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 2, this);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object r3) {
        return Long.compare(this.f474a, ((b) r3).f474a);
    }

    public final boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof b) == false) goto L14;
        b r82 = (b) r8;
        if (this.f474a != r82.f474a) goto L14;
        if (this.f476c.equals(r82.f476c) == false) goto L14;
        if (this.d.equals(r82.d) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        int r0 = this.f475b.hashCode() ^ this.f476c.f315b;
        return Integer.rotateLeft(this.d.f315b, 16) ^ r0;
    }

    public final boolean s() {
        if (this.d.getTotalSeconds() <= this.f476c.getTotalSeconds()) goto L6;
        return true;
    L6:
        return false;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("Transition[");
        if (s() == false) goto L5;
        String r1 = "Gap";
    L6:
        r0.append(r1);
        r0.append(" at ");
        r0.append(this.f475b);
        r0.append(this.f476c);
        r0.append(" to ");
        r0.append(this.d);
        r0.append(']');
        return r0.toString();
    L5:
        r1 = "Overlap";
        goto L6
    }
}
