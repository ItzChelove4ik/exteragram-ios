package androidx.core.util;

/* loaded from: classes3.dex */
public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F r1, S r2) {
        this.first = r1;
        this.second = r2;
    }

    public static <A, B> Pair<A, B> create(A r1, B r2) {
        return new Pair(r1, r2);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof Pair) == true) goto L5;
        return false;
    L5:
        Pair r42 = (Pair) r4;
        if (ObjectsCompat.equals(r42.first, this.first) == true) goto L8;
    L11:
        return false;
    L8:
        if (ObjectsCompat.equals(r42.second, this.second) == false) goto L11;
        return true;
    }

    public int hashCode() {
        F r0 = this.first;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        S r2 = this.second;
        if (r2 == null) goto L11;
        r1 = r2.hashCode();
    L11:
        return r02 ^ r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }
}
