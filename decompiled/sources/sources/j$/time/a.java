package j$.time;

import java.io.ObjectInputStream;
import java.io.Serializable;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class a extends j$.desugar.sun.nio.fs.g implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    public static final a f320b = null;
    private static final long serialVersionUID = 6740630888130243051L;

    /* renamed from: a, reason: collision with root package name */
    public final ZoneId f321a;

    static {
        System.currentTimeMillis();
        f320b = new a(ZoneOffset.UTC);
    }

    public a(ZoneId r1) {
        this.f321a = r1;
    }

    private void readObject(ObjectInputStream r1) {
        r1.defaultReadObject();
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof a) == true) goto L5;
        return false;
    L5:
        return this.f321a.equals(((a) r2).f321a);
    }

    public final int hashCode() {
        return this.f321a.hashCode() + 1;
    }

    public final String toString() {
        return "SystemClock[" + this.f321a + "]";
    }
}
