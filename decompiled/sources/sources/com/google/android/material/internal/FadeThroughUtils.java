package com.google.android.material.internal;

/* loaded from: classes5.dex */
final class FadeThroughUtils {
    static final float THRESHOLD_ALPHA = 0.5f;

    private FadeThroughUtils() {
    }

    public static void calculateFadeOutAndInAlphas(float r6, float[] r7) {
        if (r6 > THRESHOLD_ALPHA) goto L6;
        r7[0] = 1.0f - (r6 * 2.0f);
        r7[1] = 0.0f;
        return;
    L6:
        r7[0] = 0.0f;
        r7[1] = (r6 * 2.0f) - 1.0f;
    }
}
