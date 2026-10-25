package com.google.android.material.transition;

import android.graphics.RectF;

/* loaded from: classes5.dex */
interface FitModeEvaluator {
    void applyMask(RectF r1, float r2, FitModeResult r3);

    FitModeResult evaluate(float r1, float r2, float r3, float r4, float r5, float r6, float r7);

    boolean shouldMaskStartBounds(FitModeResult r1);
}
