package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class ArrayUtils {
    public static boolean contains(int[] r3, int r4) {
        if (r3 == null) goto L12;
        int r1 = 0;
    L6:
        if (r1 >= r3.length) goto L12;
        if (r3[r1] == r4) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public static <T> boolean contains(T[] r4, T r5) {
        if (r4 == null) goto L5;
        int r1 = r4.length;
    L6:
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L14;
        if (Objects.equal(r4[r2], r5) == true) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        if (r2 < 0) goto L14;
        return true;
    L14:
        return false;
    L5:
        r1 = 0;
        goto L6
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList();
    }
}
