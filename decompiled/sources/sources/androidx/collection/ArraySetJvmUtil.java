package androidx.collection;

import java.lang.reflect.Array;

/* loaded from: classes3.dex */
abstract class ArraySetJvmUtil {
    public static <T> T[] resizeForToArray(T[] r1, int r2) {
        if (r1.length >= r2) goto L7;
        return (T[]) ((Object[]) Array.newInstance(r1.getClass().getComponentType(), r2));
    L7:
        if (r1.length <= r2) goto L9;
        r1[r2] = null;
    L9:
        return r1;
    }
}
