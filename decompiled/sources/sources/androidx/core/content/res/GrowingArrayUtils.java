package androidx.core.content.res;

import java.lang.reflect.Array;

/* loaded from: classes3.dex */
abstract class GrowingArrayUtils {
    public static int[] append(int[] r2, int r3, int r4) {
        if ((r3 + 1) <= r2.length) goto L5;
        int[] r0 = new int[growSize(r3)];
        System.arraycopy(r2, 0, r0, 0, r3);
        r2 = r0;
    L5:
        r2[r3] = r4;
        return r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Object[]] */
    public static <T> T[] append(T[] r2, int r3, T r4) {
        if ((r3 + 1) <= r2.length) goto L5;
        ?? r0 = (Object[]) Array.newInstance(r2.getClass().getComponentType(), growSize(r3));
        System.arraycopy(r2, 0, r0, 0, r3);
        r2 = r0;
    L5:
        r2[r3] = r4;
        return r2;
    }

    public static int growSize(int r1) {
        if (r1 > 4) goto L7;
        return 8;
    L7:
        return r1 * 2;
    }
}
