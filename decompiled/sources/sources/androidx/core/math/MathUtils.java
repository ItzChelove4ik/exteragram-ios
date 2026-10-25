package androidx.core.math;

/* loaded from: classes.dex */
public abstract class MathUtils {
    public static float clamp(float r1, float r2, float r3) {
        if (r1 >= r2) goto L6;
        return r2;
    L6:
        if (r1 <= r3) goto L8;
        return r3;
    L8:
        return r1;
    }

    public static int clamp(int r0, int r1, int r2) {
        if (r0 >= r1) goto L4;
        return r1;
    L4:
        if (r0 <= r2) goto L6;
        return r2;
    L6:
        return r0;
    }

    public static long clamp(long r1, long r3, long r5) {
        if (r1 >= r3) goto L6;
        return r3;
    L6:
        if (r1 <= r5) goto L8;
        return r5;
    L8:
        return r1;
    }
}
