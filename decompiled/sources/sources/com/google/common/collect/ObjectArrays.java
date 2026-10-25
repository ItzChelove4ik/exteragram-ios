package com.google.common.collect;

import com.android.dx.util.LabeledList$$ExternalSyntheticBUOutline0;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class ObjectArrays {
    public static Object checkElementNotNull(Object r0, int r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        LabeledList$$ExternalSyntheticBUOutline0.m("at index ", r1);
        return null;
    }

    public static Object[] checkElementsNotNull(Object... r1) {
        checkElementsNotNull(r1, r1.length);
        return r1;
    }

    public static Object[] checkElementsNotNull(Object[] r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L5;
        checkElementNotNull(r2[r0], r0);
        r0 = r0 + 1;
        goto L3
    L5:
        return r2;
    }

    public static <T> T[] newArray(T[] r1, int r2) {
        if (r1.length == 0) goto L7;
        r1 = (T[]) Arrays.copyOf(r1, 0);
    L7:
        return (T[]) Arrays.copyOf(r1, r2);
    }
}
