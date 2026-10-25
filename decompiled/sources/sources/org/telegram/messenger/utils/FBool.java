package org.telegram.messenger.utils;

import androidx.core.math.MathUtils;

/* loaded from: classes6.dex */
public abstract class FBool {
    public static float and(float r0, float r1) {
        return Math.min(clamp(r0), clamp(r1));
    }

    private static float clamp(float r2) {
        return MathUtils.clamp(r2, 0.0f, 1.0f);
    }

    public static float not(float r1) {
        return 1.0f - clamp(r1);
    }

    public static float or(float r0, float r1) {
        return not(and(not(r0), not(r1)));
    }
}
