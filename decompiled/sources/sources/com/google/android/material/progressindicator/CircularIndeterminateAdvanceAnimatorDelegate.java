package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes5.dex */
final class CircularIndeterminateAdvanceAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final Property<CircularIndeterminateAdvanceAnimatorDelegate, Float> ANIMATION_FRACTION = null;
    private static final Property<CircularIndeterminateAdvanceAnimatorDelegate, Float> COMPLETE_END_FRACTION = null;
    private static final int CONSTANT_ROTATION_DEGREES = 1520;
    private static final int[] DELAY_TO_COLLAPSE_IN_MS = null;
    private static final int[] DELAY_TO_EXPAND_IN_MS = null;
    private static final int[] DELAY_TO_FADE_IN_MS = null;
    private static final int DURATION_TO_COLLAPSE_IN_MS = 667;
    private static final int DURATION_TO_COMPLETE_END_IN_MS = 333;
    private static final int DURATION_TO_EXPAND_IN_MS = 667;
    private static final int DURATION_TO_FADE_IN_MS = 333;
    private static final int EXTRA_DEGREES_PER_CYCLE = 250;
    private static final int TAIL_DEGREES_OFFSET = -20;
    private static final int TOTAL_CYCLES = 4;
    private static final int TOTAL_DURATION_IN_MS = 5400;
    private float animationFraction;
    private ObjectAnimator animator;
    Animatable2Compat$AnimationCallback animatorCompleteCallback;
    private final BaseProgressIndicatorSpec baseSpec;
    private ObjectAnimator completeEndAnimator;
    private float completeEndFraction;
    private int indicatorColorIndexOffset;
    private final FastOutSlowInInterpolator interpolator;

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateAdvanceAnimatorDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ CircularIndeterminateAdvanceAnimatorDelegate this$0;

        public AnonymousClass1(CircularIndeterminateAdvanceAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r2) {
            super.onAnimationRepeat(r2);
            CircularIndeterminateAdvanceAnimatorDelegate r22 = this.this$0;
            CircularIndeterminateAdvanceAnimatorDelegate.access$002(r22, (CircularIndeterminateAdvanceAnimatorDelegate.access$000(r22) + 4) % CircularIndeterminateAdvanceAnimatorDelegate.access$100(this.this$0).indicatorColors.length);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateAdvanceAnimatorDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ CircularIndeterminateAdvanceAnimatorDelegate this$0;

        public AnonymousClass2(CircularIndeterminateAdvanceAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.cancelAnimatorImmediately();
            CircularIndeterminateAdvanceAnimatorDelegate r0 = this.this$0;
            Animatable2Compat$AnimationCallback r12 = r0.animatorCompleteCallback;
            if (r12 == null) goto L6;
            r12.onAnimationEnd(r0.drawable);
            return;
        }
    }

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateAdvanceAnimatorDelegate$3, reason: invalid class name */
    public class AnonymousClass3 extends Property<CircularIndeterminateAdvanceAnimatorDelegate, Float> {
        public AnonymousClass3(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(CircularIndeterminateAdvanceAnimatorDelegate r1) {
            return Float.valueOf(CircularIndeterminateAdvanceAnimatorDelegate.access$200(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(CircularIndeterminateAdvanceAnimatorDelegate r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(CircularIndeterminateAdvanceAnimatorDelegate r1, Float r2) {
            r1.setAnimationFraction(r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(CircularIndeterminateAdvanceAnimatorDelegate r1, Float r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateAdvanceAnimatorDelegate$4, reason: invalid class name */
    public class AnonymousClass4 extends Property<CircularIndeterminateAdvanceAnimatorDelegate, Float> {
        public AnonymousClass4(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(CircularIndeterminateAdvanceAnimatorDelegate r1) {
            return Float.valueOf(CircularIndeterminateAdvanceAnimatorDelegate.access$300(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(CircularIndeterminateAdvanceAnimatorDelegate r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(CircularIndeterminateAdvanceAnimatorDelegate r1, Float r2) {
            CircularIndeterminateAdvanceAnimatorDelegate.access$400(r1, r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(CircularIndeterminateAdvanceAnimatorDelegate r1, Float r2) {
            set2(r1, r2);
        }
    }

    static {
        DELAY_TO_EXPAND_IN_MS = new int[]{0, 1350, 2700, 4050};
        DELAY_TO_COLLAPSE_IN_MS = new int[]{667, 2017, 3367, 4717};
        DELAY_TO_FADE_IN_MS = new int[]{1000, 2350, 3700, 5050};
        ANIMATION_FRACTION = new AnonymousClass3(Float.class, "animationFraction");
        COMPLETE_END_FRACTION = new AnonymousClass4(Float.class, "completeEndFraction");
    }

    public CircularIndeterminateAdvanceAnimatorDelegate(CircularProgressIndicatorSpec r2) {
        super(1);
        this.indicatorColorIndexOffset = 0;
        this.animatorCompleteCallback = null;
        this.baseSpec = r2;
        this.interpolator = new FastOutSlowInInterpolator();
    }

    public static /* synthetic */ int access$000(CircularIndeterminateAdvanceAnimatorDelegate r0) {
        return r0.indicatorColorIndexOffset;
    }

    public static /* synthetic */ int access$002(CircularIndeterminateAdvanceAnimatorDelegate r0, int r1) {
        r0.indicatorColorIndexOffset = r1;
        return r1;
    }

    public static /* synthetic */ BaseProgressIndicatorSpec access$100(CircularIndeterminateAdvanceAnimatorDelegate r0) {
        return r0.baseSpec;
    }

    public static /* synthetic */ float access$200(CircularIndeterminateAdvanceAnimatorDelegate r0) {
        return r0.getAnimationFraction();
    }

    public static /* synthetic */ float access$300(CircularIndeterminateAdvanceAnimatorDelegate r0) {
        return r0.getCompleteEndFraction();
    }

    public static /* synthetic */ void access$400(CircularIndeterminateAdvanceAnimatorDelegate r0, float r1) {
        r0.setCompleteEndFraction(r1);
    }

    private float getAnimationFraction() {
        return this.animationFraction;
    }

    private float getCompleteEndFraction() {
        return this.completeEndFraction;
    }

    private void maybeInitializeAnimators() {
        if (this.animator != null) goto L6;
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
        this.animator = r0;
        r0.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 5400.0f));
        this.animator.setInterpolator(null);
        this.animator.setRepeatCount(-1);
        this.animator.addListener(new AnonymousClass1(this));
    L6:
        if (this.completeEndAnimator != null) goto L9;
        ObjectAnimator r02 = ObjectAnimator.ofFloat(this, COMPLETE_END_FRACTION, new float[]{0.0f, 1.0f});
        this.completeEndAnimator = r02;
        r02.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 333.0f));
        this.completeEndAnimator.setInterpolator(this.interpolator);
        this.completeEndAnimator.addListener(new AnonymousClass2(this));
        return;
    }

    private void maybeUpdateSegmentColors(int r6) {
        int r1 = 0;
    L4:
        if (r1 >= 4) goto L12;
        float r2 = getFractionInRange(r6, DELAY_TO_FADE_IN_MS[r1], 333);
        if (r2 <= 0.0f) goto L11;
        if (r2 >= 1.0f) goto L11;
        int r12 = r1 + this.indicatorColorIndexOffset;
        int[] r62 = this.baseSpec.indicatorColors;
        int r13 = r12 % r62.length;
        int r3 = (r13 + 1) % r62.length;
        int r14 = r62[r13];
        int r63 = r62[r3];
        float r22 = this.interpolator.getInterpolation(r2);
        this.activeIndicators.get(0).color = ArgbEvaluatorCompat.getInstance().evaluate2(r22, Integer.valueOf(r14), Integer.valueOf(r63)).intValue();
        return;
    L11:
        r1 = r1 + 1;
        goto L4
    }

    private void setCompleteEndFraction(float r1) {
        this.completeEndFraction = r1;
    }

    private void updateAnimatorsDuration() {
        maybeInitializeAnimators();
        this.animator.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 5400.0f));
        this.completeEndAnimator.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 333.0f));
    }

    private void updateSegmentPositions(int r7) {
        int r1 = 0;
        DrawingDelegate.ActiveIndicator r0 = this.activeIndicators.get(0);
        float r2 = this.animationFraction;
        r0.startFraction = (r2 * 1520.0f) - 20.0f;
        r0.endFraction = r2 * 1520.0f;
    L4:
        if (r1 >= 4) goto L6;
        r0.endFraction += this.interpolator.getInterpolation(getFractionInRange(r7, DELAY_TO_EXPAND_IN_MS[r1], 667)) * 250.0f;
        r0.startFraction += this.interpolator.getInterpolation(getFractionInRange(r7, DELAY_TO_COLLAPSE_IN_MS[r1], 667)) * 250.0f;
        r1 = r1 + 1;
        goto L4
    L6:
        float r72 = r0.startFraction;
        float r12 = r0.endFraction;
        r0.startFraction = (r72 + ((r12 - r72) * this.completeEndFraction)) / 360.0f;
        r0.endFraction = r12 / 360.0f;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void cancelAnimatorImmediately() {
        ObjectAnimator r0 = this.animator;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        updateAnimatorsDuration();
        resetPropertiesForNewStart();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(Animatable2Compat$AnimationCallback r1) {
        this.animatorCompleteCallback = r1;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
        ObjectAnimator r0 = this.completeEndAnimator;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isRunning() == false) goto L8;
        return;
    L8:
        if (this.drawable.isVisible() == false) goto L11;
        this.completeEndAnimator.start();
        return;
    L11:
        cancelAnimatorImmediately();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void resetPropertiesForNewStart() {
        this.indicatorColorIndexOffset = 0;
        DrawingDelegate.ActiveIndicator r1 = this.activeIndicators.get(0);
        r1.color = this.baseSpec.indicatorColors[0];
        this.completeEndFraction = 0.0f;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void setAnimationFraction(float r2) {
        this.animationFraction = r2;
        int r22 = (int) (r2 * 5400.0f);
        updateSegmentPositions(r22);
        maybeUpdateSegmentColors(r22);
        this.drawable.invalidateSelf();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void startAnimator() {
        maybeInitializeAnimators();
        resetPropertiesForNewStart();
        this.animator.start();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
        this.animatorCompleteCallback = null;
    }
}
