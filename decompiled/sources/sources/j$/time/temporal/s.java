package j$.time.temporal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class s implements Serializable {
    private static final long serialVersionUID = -7317881728594519368L;

    /* renamed from: a, reason: collision with root package name */
    public final long f466a;

    /* renamed from: b, reason: collision with root package name */
    public final long f467b;

    /* renamed from: c, reason: collision with root package name */
    public final long f468c;
    public final long d;

    public s(long r1, long r3, long r5, long r7) {
        this.f466a = r1;
        this.f467b = r3;
        this.f468c = r5;
        this.d = r7;
    }

    public static s f(long r10, long r12) {
        if (r10 <= r12) goto L5;
        j$.nio.file.b.a("Minimum value must be less than maximum value");
        return null;
    L5:
        return new s(r10, r10, r12, r12);
    }

    public static s g(long r10, long r12) {
        if (r10 <= r12) goto L5;
        j$.nio.file.b.a("Smallest maximum value must be less than largest maximum value");
        return null;
    L5:
        if (1 <= r12) goto L7;
        j$.nio.file.b.a("Minimum value must be less than maximum value");
        return null;
    L7:
        return new s(1, 1, r10, r12);
    }

    private void readObject(ObjectInputStream r5) {
        r5.defaultReadObject();
        long r0 = this.f466a;
        long r2 = this.f467b;
        if (r0 > r2) goto L14;
        long r02 = this.f468c;
        long r4 = this.d;
        if (r02 > r4) goto L12;
        if (r2 > r4) goto L10;
        return;
    L10:
        throw new InvalidObjectException("Minimum value must be less than maximum value");
    L12:
        throw new InvalidObjectException("Smallest maximum value must be less than largest maximum value");
    L14:
        throw new InvalidObjectException("Smallest minimum value must be less than largest minimum value");
    }

    public final int a(long r2, p r4) {
        if (d() == true) goto L5;
    L8:
        j$.nio.file.b.h(c(r2, r4));
        return 0;
    L5:
        if (e(r2) == false) goto L8;
        return (int) r2;
    }

    public final void b(long r2, p r4) {
        if (e(r2) == false) goto L5;
        return;
    L5:
        j$.nio.file.b.h(c(r2, r4));
    }

    public final String c(long r4, p r6) {
        if (r6 == null) goto L7;
        return "Invalid value for " + r6 + " (valid values " + this + "): " + r4;
    L7:
        return "Invalid value (valid values " + this + "): " + r4;
    }

    public final boolean d() {
        if (this.f466a >= (-2147483648L)) goto L5;
        return false;
    L5:
        if (this.d > 2147483647L) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean e(long r3) {
        if (r3 >= this.f466a) goto L5;
        return false;
    L5:
        if (r3 > this.d) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof s) == false) goto L16;
        s r82 = (s) r8;
        if (this.f466a != r82.f466a) goto L16;
        if (this.f467b != r82.f467b) goto L16;
        if (this.f468c != r82.f468c) goto L16;
        if (this.d != r82.d) goto L16;
        return true;
    L16:
        return false;
    }

    public final int hashCode() {
        long r0 = this.f466a;
        long r2 = this.f467b;
        long r02 = (r0 + (r2 << 16)) + (r2 >> 48);
        long r22 = this.f468c;
        long r03 = (r02 + (r22 << 32)) + (r22 >> 32);
        long r23 = this.d;
        long r04 = (r03 + (r23 << 48)) + (r23 >> 16);
        return (int) (r04 ^ (r04 >>> 32));
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.f466a);
        if (this.f466a == this.f467b) goto L5;
        r0.append('/');
        r0.append(this.f467b);
    L5:
        r0.append(" - ");
        r0.append(this.f468c);
        if (this.f468c == this.d) goto L9;
        r0.append('/');
        r0.append(this.d);
    L9:
        return r0.toString();
    }
}
