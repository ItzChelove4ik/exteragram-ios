package com.android.dx.ssa;

import com.android.dx.util.BitIntSet;
import com.android.dx.util.IntSet;
import com.android.dx.util.ListIntSet;

/* loaded from: classes4.dex */
public final class SetFactory {
    private static final int DOMFRONT_SET_THRESHOLD_SIZE = 3072;
    private static final int INTERFERENCE_SET_THRESHOLD_SIZE = 3072;
    private static final int LIVENESS_SET_THRESHOLD_SIZE = 3072;

    public SetFactory() {
    }

    public static IntSet makeDomFrontSet(int r1) {
        if (r1 > 3072) goto L7;
        return new BitIntSet(r1);
    L7:
        return new ListIntSet();
    }

    public static IntSet makeInterferenceSet(int r1) {
        if (r1 > 3072) goto L7;
        return new BitIntSet(r1);
    L7:
        return new ListIntSet();
    }

    public static IntSet makeLivenessSet(int r1) {
        if (r1 > 3072) goto L7;
        return new BitIntSet(r1);
    L7:
        return new ListIntSet();
    }
}
