package org.telegram.messenger.utils;

/* loaded from: classes6.dex */
public abstract class RadiiUtils {
    public static boolean radiiAreSame(float[] r4) {
        if (r4 != null) goto L5;
    L22:
        return false;
    L5:
        if (r4.length != 8) goto L22;
        float r1 = r4[0];
        if (r1 != r4[1]) goto L22;
        if (r1 != r4[2]) goto L22;
        if (r1 != r4[3]) goto L22;
        if (r1 != r4[4]) goto L22;
        if (r1 != r4[5]) goto L22;
        if (r1 != r4[6]) goto L22;
        if (r1 != r4[7]) goto L22;
        return true;
    }
}
