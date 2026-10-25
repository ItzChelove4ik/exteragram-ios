package com.google.android.material.transition;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class FadeModeEvaluators {
    private static final FadeModeEvaluator CROSS = null;
    private static final FadeModeEvaluator IN = null;
    private static final FadeModeEvaluator OUT = null;
    private static final FadeModeEvaluator THROUGH = null;

    /* renamed from: com.google.android.material.transition.FadeModeEvaluators$1, reason: invalid class name */
    public class AnonymousClass1 implements FadeModeEvaluator {
        public AnonymousClass1() {
        }

        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float r1, float r2, float r3, float r4) {
            return FadeModeResult.endOnTop(255, TransitionUtils.lerp(0, 255, r2, r3, r1));
        }
    }

    /* renamed from: com.google.android.material.transition.FadeModeEvaluators$2, reason: invalid class name */
    public class AnonymousClass2 implements FadeModeEvaluator {
        public AnonymousClass2() {
        }

        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float r1, float r2, float r3, float r4) {
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, r2, r3, r1), 255);
        }
    }

    /* renamed from: com.google.android.material.transition.FadeModeEvaluators$3, reason: invalid class name */
    public class AnonymousClass3 implements FadeModeEvaluator {
        public AnonymousClass3() {
        }

        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float r2, float r3, float r4, float r5) {
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, r3, r4, r2), TransitionUtils.lerp(0, 255, r3, r4, r2));
        }
    }

    /* renamed from: com.google.android.material.transition.FadeModeEvaluators$4, reason: invalid class name */
    public class AnonymousClass4 implements FadeModeEvaluator {
        public AnonymousClass4() {
        }

        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float r2, float r3, float r4, float r5) {
            float r1 = ((r4 - r3) * r5) + r3;
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, r3, r1, r2), TransitionUtils.lerp(0, 255, r1, r4, r2));
        }
    }

    static {
        IN = new AnonymousClass1();
        OUT = new AnonymousClass2();
        CROSS = new AnonymousClass3();
        THROUGH = new AnonymousClass4();
    }

    private FadeModeEvaluators() {
    }

    public static FadeModeEvaluator get(int r1, boolean r2) {
        if (r1 != 0) goto L4;
        if (r2 == false) goto L24;
        return IN;
    L24:
        return OUT;
    L4:
        if (r1 != 1) goto L6;
        if (r2 == false) goto L19;
        return OUT;
    L19:
        return IN;
    L6:
        if (r1 == 2) goto L14;
        if (r1 == 3) goto L10;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid fade mode: ", r1);
        return null;
    L10:
        return THROUGH;
    L14:
        return CROSS;
    }
}
