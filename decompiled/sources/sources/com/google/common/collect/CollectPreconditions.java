package com.google.common.collect;

import com.google.common.base.Preconditions;
import okhttp3.internal.http.RealInterceptorChain$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
abstract class CollectPreconditions {
    public static void checkEntryNotNull(Object r2, Object r3) {
        if (r2 == null) goto L8;
        if (r3 == null) goto L5;
        return;
    L5:
        RealInterceptorChain$$ExternalSyntheticBUOutline0.m("null value in entry: ", r2, "=null");
        return;
    L8:
        throw new NullPointerException("null key in entry: null=" + r3);
    }

    public static int checkNonnegative(int r0, String r1) {
        if (r0 < 0) goto L4;
        return r0;
    L4:
        CollectPreconditions$$ExternalSyntheticBUOutline0.m(r1, r0);
        return 0;
    }

    public static void checkRemove(boolean r1) {
        Preconditions.checkState(r1, "no calls to next() since the last call to remove()");
    }
}
