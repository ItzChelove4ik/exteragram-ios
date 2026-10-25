package j$.time.chrono;

import j$.time.LocalDate;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class x implements l, Serializable {
    public static final x d = null;
    public static final x[] e = null;
    private static final long serialVersionUID = 1466499369062886794L;

    /* renamed from: a, reason: collision with root package name */
    public final transient int f361a;

    /* renamed from: b, reason: collision with root package name */
    public final transient LocalDate f362b;

    /* renamed from: c, reason: collision with root package name */
    public final transient String f363c;

    static {
        x r0 = new x(-1, LocalDate.of(1868, 1, 1), "Meiji");
        d = r0;
        e = new x[]{r0, new x(0, LocalDate.of(1912, 7, 30), "Taisho"), new x(1, LocalDate.of(1926, 12, 25), "Showa"), new x(2, LocalDate.of(1989, 1, 8), "Heisei"), new x(3, LocalDate.of(2019, 5, 1), "Reiwa")};
    }

    public x(int r1, LocalDate r2, String r3) {
        this.f361a = r1;
        this.f362b = r2;
        this.f363c = r3;
    }

    public static x p(LocalDate r4) {
        if (r4.b0(w.d) == true) goto L11;
        int r0 = e.length - 1;
    L5:
        if (r0 < 0) goto L10;
        x r2 = e[r0];
        if (r4.X(r2.f362b) >= 0) goto L8;
        r0 = r0 - 1;
        goto L5
    L8:
        return r2;
    L10:
        return null;
    L11:
        j$.nio.file.b.h("JapaneseDate before Meiji 6 are not supported");
        return null;
    }

    public static x r(int r3) {
        int r0 = r3 + 1;
        if (r0 < 0) goto L8;
        x[] r1 = e;
        if (r0 >= r1.length) goto L8;
        return r1[r0];
    L8:
        j$.nio.file.b.b("Invalid era: ", r3);
        return null;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new d0((byte) 5, this);
    }

    @Override // j$.time.chrono.l
    public final int getValue() {
        return this.f361a;
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        j$.time.temporal.a r0 = j$.time.temporal.a.ERA;
        if (r2 != r0) goto L7;
        return u.f356c.B(r0);
    L7:
        return super.l(r2);
    }

    public final x q() {
        if (this != e[r0.length - 1]) goto L7;
        return null;
    L7:
        return r(this.f361a + 1);
    }

    public final String toString() {
        return this.f363c;
    }
}
