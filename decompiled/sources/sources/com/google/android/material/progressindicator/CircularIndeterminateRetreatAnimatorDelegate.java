package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Property;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.gms.cast.MediaError;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.math.MathUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes5.dex */
final class CircularIndeterminateRetreatAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final Property<CircularIndeterminateRetreatAnimatorDelegate, Float> ANIMATION_FRACTION = null;
    private static final Property<CircularIndeterminateRetreatAnimatorDelegate, Float> COMPLETE_END_FRACTION = null;
    private static final int CONSTANT_ROTATION_DEGREES = 1080;
    private static final TimeInterpolator DEFAULT_INTERPOLATOR = null;
    private static final int DELAY_GROW_ACTIVE_IN_MS = 0;
    private static final int DELAY_SHRINK_ACTIVE_IN_MS = 3000;
    private static final int[] DELAY_SPINS_IN_MS = null;
    private static final int DURATION_GROW_ACTIVE_IN_MS = 3000;
    private static final int DURATION_SHRINK_ACTIVE_IN_MS = 3000;
    private static final int DURATION_SPIN_IN_MS = 500;
    private static final int DURATION_TO_COMPLETE_END_IN_MS = 500;
    private static final int DURATION_TO_FADE_IN_MS = 100;
    private static final float[] END_FRACTION_RANGE = null;
    private static final int SPIN_ROTATION_DEGREES = 90;
    private static final float START_FRACTION = 0.0f;
    private static final int TOTAL_DURATION_IN_MS = 6000;
    private float animationFraction;
    private ObjectAnimator animator;
    Animatable2Compat$AnimationCallback animatorCompleteCallback;
    private final BaseProgressIndicatorSpec baseSpec;
    private ObjectAnimator completeEndAnimator;
    private float completeEndFraction;
    private int indicatorColorIndexOffset;
    private final TimeInterpolator standardInterpolator;

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateRetreatAnimatorDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ CircularIndeterminateRetreatAnimatorDelegate this$0;

        public AnonymousClass1(CircularIndeterminateRetreatAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r3) {
            super.onAnimationRepeat(r3);
            CircularIndeterminateRetreatAnimatorDelegate r32 = this.this$0;
            CircularIndeterminateRetreatAnimatorDelegate.access$002(r32, (CircularIndeterminateRetreatAnimatorDelegate.access$000(r32) + CircularIndeterminateRetreatAnimatorDelegate.access$100().length) % CircularIndeterminateRetreatAnimatorDelegate.access$200(this.this$0).indicatorColors.length);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateRetreatAnimatorDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ CircularIndeterminateRetreatAnimatorDelegate this$0;

        public AnonymousClass2(CircularIndeterminateRetreatAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.cancelAnimatorImmediately();
            CircularIndeterminateRetreatAnimatorDelegate r0 = this.this$0;
            Animatable2Compat$AnimationCallback r12 = r0.animatorCompleteCallback;
            if (r12 == null) goto L6;
            r12.onAnimationEnd(r0.drawable);
            return;
        }
    }

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateRetreatAnimatorDelegate$3, reason: invalid class name */
    public class AnonymousClass3 extends Property<CircularIndeterminateRetreatAnimatorDelegate, Float> {
        public AnonymousClass3(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(CircularIndeterminateRetreatAnimatorDelegate r1) {
            return Float.valueOf(CircularIndeterminateRetreatAnimatorDelegate.access$300(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(CircularIndeterminateRetreatAnimatorDelegate r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(CircularIndeterminateRetreatAnimatorDelegate r1, Float r2) {
            r1.setAnimationFraction(r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(CircularIndeterminateRetreatAnimatorDelegate r1, Float r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.CircularIndeterminateRetreatAnimatorDelegate$4, reason: invalid class name */
    public class AnonymousClass4 extends Property<CircularIndeterminateRetreatAnimatorDelegate, Float> {
        public AnonymousClass4(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(CircularIndeterminateRetreatAnimatorDelegate r1) {
            return Float.valueOf(CircularIndeterminateRetreatAnimatorDelegate.access$400(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(CircularIndeterminateRetreatAnimatorDelegate r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(CircularIndeterminateRetreatAnimatorDelegate r1, Float r2) {
            CircularIndeterminateRetreatAnimatorDelegate.access$500(r1, r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(CircularIndeterminateRetreatAnimatorDelegate r1, Float r2) {
            set2(r1, r2);
        }
    }

    static {
        DEFAULT_INTERPOLATOR = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        DELAY_SPINS_IN_MS = new int[]{0, 1500, 3000, 4500};
        END_FRACTION_RANGE = new float[]{0.1f, 0.87f};
        ANIMATION_FRACTION = new AnonymousClass3(Float.class, "animationFraction");
        COMPLETE_END_FRACTION = new AnonymousClass4(Float.class, "completeEndFraction");
    }

    public CircularIndeterminateRetreatAnimatorDelegate(Context r2, CircularProgressIndicatorSpec r3) {
        super(1);
        this.indicatorColorIndexOffset = 0;
        this.animatorCompleteCallback = null;
        this.baseSpec = r3;
        this.standardInterpolator = MotionUtils.resolveThemeInterpolator(r2, R.attr.motionEasingStandardInterpolator, DEFAULT_INTERPOLATOR);
    }

    public static /* synthetic */ int access$000(CircularIndeterminateRetreatAnimatorDelegate r0) {
        return r0.indicatorColorIndexOffset;
    }

    public static /* synthetic */ int access$002(CircularIndeterminateRetreatAnimatorDelegate r0, int r1) {
        r0.indicatorColorIndexOffset = r1;
        return r1;
    }

    public static /* synthetic */ int[] access$100() {
        return DELAY_SPINS_IN_MS;
    }

    public static /* synthetic */ BaseProgressIndicatorSpec access$200(CircularIndeterminateRetreatAnimatorDelegate r0) {
        return r0.baseSpec;
    }

    public static /* synthetic */ float access$300(CircularIndeterminateRetreatAnimatorDelegate r0) {
        return r0.getAnimationFraction();
    }

    public static /* synthetic */ float access$400(CircularIndeterminateRetreatAnimatorDelegate r0) {
        return r0.getCompleteEndFraction();
    }

    public static /* synthetic */ void access$500(CircularIndeterminateRetreatAnimatorDelegate r0, float r1) {
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
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{START_FRACTION, 1.0f});
        this.animator = r0;
        r0.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 6000.0f));
        this.animator.setInterpolator(null);
        this.animator.setRepeatCount(-1);
        this.animator.addListener(new AnonymousClass1(this));
    L6:
        if (this.completeEndAnimator != null) goto L9;
        ObjectAnimator r02 = ObjectAnimator.ofFloat(this, COMPLETE_END_FRACTION, new float[]{START_FRACTION, 1.0f});
        this.completeEndAnimator = r02;
        r02.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 500.0f));
        this.completeEndAnimator.addListener(new AnonymousClass2(this));
        return;
    }

    private void maybeUpdateSegmentColors(int r6) {
        int r1 = 0;
    L3:
        int[] r2 = DELAY_SPINS_IN_MS;
        if (r1 >= r2.length) goto L12;
        float r22 = getFractionInRange(r6, r2[r1], 100);
        if (r22 < START_FRACTION) goto L11;
        if (r22 > 1.0f) goto L11;
        int r12 = r1 + this.indicatorColorIndexOffset;
        int[] r62 = this.baseSpec.indicatorColors;
        int r13 = r12 % r62.length;
        int r3 = (r13 + 1) % r62.length;
        int r14 = r62[r13];
        int r63 = r62[r3];
        float r23 = this.standardInterpolator.getInterpolation(r22);
        this.activeIndicators.get(0).color = ArgbEvaluatorCompat.getInstance().evaluate2(r23, Integer.valueOf(r14), Integer.valueOf(r63)).intValue();
        return;
    L11:
        r1 = r1 + 1;
        goto L3
    }

    private void setCompleteEndFraction(float r1) {
        this.completeEndFraction = r1;
    }

    private void updateAnimatorsDuration() {
        maybeInitializeAnimators();
        this.animator.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 6000.0f));
        this.completeEndAnimator.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 500.0f));
    }

    private void updateSegmentPositions(int r12) {
        DrawingDelegate.ActiveIndicator r0 = this.activeIndicators.get(0);
        float r3 = this.animationFraction * 1080.0f;
        int[] r2 = DELAY_SPINS_IN_MS;
        int r4 = r2.length;
        int r6 = 0;
        float r7 = 0.0f;
    L3:
        if (r6 >= r4) goto L5;
        r7 = r7 + (this.standardInterpolator.getInterpolation(getFractionInRange(r12, r2[r6], MediaError.DetailedErrorCode.SEGMENT_UNKNOWN)) * 90.0f);
        r6 = r6 + 1;
        goto L3
    L5:
        r0.rotationDegree = r3 + r7;
        float r22 = this.standardInterpolator.getInterpolation(getFractionInRange(r12, 0, 3000)) - this.standardInterpolator.getInterpolation(getFractionInRange(r12, 3000, 3000));
        r0.startFraction = START_FRACTION;
        float[] r122 = END_FRACTION_RANGE;
        float r123 = MathUtils.lerp(r122[0], r122[1], r22);
        r0.endFraction = r123;
        float r11 = this.completeEndFraction;
        if (r11 <= START_FRACTION) goto L10;
        r0.endFraction = r123 * (1.0f - r11);
        return;
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
        this.completeEndFraction = START_FRACTION;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void setAnimationFraction(float r2) {
        this.animationFraction = r2;
        int r22 = (int) (r2 * 6000.0f);
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
