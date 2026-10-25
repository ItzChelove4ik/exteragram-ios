package com.google.android.gms.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class CollectionUtils {
    @Deprecated
    public static <T> List<T> listOf(T r0) {
        return Collections.singletonList(r0);
    }

    @Deprecated
    public static <T> List<T> listOf(T... r2) {
        int r0 = r2.length;
        if (r0 == 0) goto L11;
        if (r0 == 1) goto L9;
        return Collections.unmodifiableList(Arrays.asList(r2));
    L9:
        return Collections.singletonList(r2[0]);
    L11:
        return Collections.EMPTY_LIST;
    }
}
