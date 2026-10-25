package com.google.android.material.transition.platform;

import android.graphics.RectF;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class FitModeEvaluators {
    private static final FitModeEvaluator HEIGHT = null;
    private static final FitModeEvaluator WIDTH = null;

    /* renamed from: com.google.android.material.transition.platform.FitModeEvaluators$1, reason: invalid class name */
    public class AnonymousClass1 implements FitModeEvaluator {
        public AnonymousClass1() {
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public void applyMask(RectF r1, float r2, FitModeResult r3) {
            r1.bottom -= Math.abs(r3.currentEndHeight - r3.currentStartHeight) * r2;
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public FitModeResult evaluate(float r7, float r8, float r9, float r10, float r11, float r12, float r13) {
            float r92 = TransitionUtils.lerp(r10, r12, r8, r9, r7, true);
            float r72 = r92 / r10;
            float r82 = r92 / r12;
            return new FitModeResult(r72, r82, r92, r11 * r72, r92, r13 * r82);
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public boolean shouldMaskStartBounds(FitModeResult r1) {
            if (r1.currentStartHeight <= r1.currentEndHeight) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.google.android.material.transition.platform.FitModeEvaluators$2, reason: invalid class name */
    public class AnonymousClass2 implements FitModeEvaluator {
        public AnonymousClass2() {
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public void applyMask(RectF r2, float r3, FitModeResult r4) {
            float r1 = (Math.abs(r4.currentEndWidth - r4.currentStartWidth) / 2.0f) * r3;
            r2.left += r1;
            r2.right -= r1;
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public FitModeResult evaluate(float r7, float r8, float r9, float r10, float r11, float r12, float r13) {
            float r6 = TransitionUtils.lerp(r11, r13, r8, r9, r7, true);
            float r72 = r6 / r11;
            float r82 = r6 / r13;
            return new FitModeResult(r72, r82, r10 * r72, r6, r12 * r82, r6);
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public boolean shouldMaskStartBounds(FitModeResult r1) {
            if (r1.currentStartWidth <= r1.currentEndWidth) goto L6;
            return true;
        L6:
            return false;
        }
    }

    static {
        WIDTH = new AnonymousClass1();
        HEIGHT = new AnonymousClass2();
    }

    private FitModeEvaluators() {
    }

    public static FitModeEvaluator get(int r0, boolean r1, RectF r2, RectF r3) {
        if (r0 == 0) goto L14;
        if (r0 == 1) goto L12;
        if (r0 == 2) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid fit mode: ", r0);
        return null;
    L8:
        return HEIGHT;
    L12:
        return WIDTH;
    L14:
        if (shouldAutoFitToWidth(r1, r2, r3) == false) goto L18;
        return WIDTH;
    L18:
        return HEIGHT;
    }

    private static boolean shouldAutoFitToWidth(boolean r4, RectF r5, RectF r6) {
        float r0 = r5.width();
        float r52 = r5.height();
        float r1 = r6.width();
        float r62 = r6.height();
        float r2 = (r62 * r0) / r1;
        float r12 = (r1 * r52) / r0;
        if (r4 == false) goto L9;
        if (r2 < r52) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r12 < r62) goto L11;
        return true;
    L11:
        return false;
    }
}
