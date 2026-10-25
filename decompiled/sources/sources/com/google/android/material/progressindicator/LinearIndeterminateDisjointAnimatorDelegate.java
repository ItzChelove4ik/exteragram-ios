package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import androidx.core.math.MathUtils;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.google.android.material.R;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class LinearIndeterminateDisjointAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final Property<LinearIndeterminateDisjointAnimatorDelegate, Float> ANIMATION_FRACTION = null;
    private static final int[] DELAY_TO_MOVE_SEGMENT_ENDS = null;
    private static final int[] DURATION_TO_MOVE_SEGMENT_ENDS = null;
    private static final int TOTAL_DURATION_IN_MS = 1800;
    private float animationFraction;
    private ObjectAnimator animator;
    Animatable2Compat$AnimationCallback animatorCompleteCallback;
    private final BaseProgressIndicatorSpec baseSpec;
    private ObjectAnimator completeEndAnimator;
    private boolean dirtyColors;
    private int indicatorColorIndex;
    private final Interpolator[] interpolatorArray;

    /* renamed from: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ LinearIndeterminateDisjointAnimatorDelegate this$0;

        public AnonymousClass1(LinearIndeterminateDisjointAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r4) {
            super.onAnimationRepeat(r4);
            LinearIndeterminateDisjointAnimatorDelegate r42 = this.this$0;
            LinearIndeterminateDisjointAnimatorDelegate.access$002(r42, (LinearIndeterminateDisjointAnimatorDelegate.access$000(r42) + 1) % LinearIndeterminateDisjointAnimatorDelegate.access$100(this.this$0).indicatorColors.length);
            LinearIndeterminateDisjointAnimatorDelegate.access$202(this.this$0, true);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ LinearIndeterminateDisjointAnimatorDelegate this$0;

        public AnonymousClass2(LinearIndeterminateDisjointAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.cancelAnimatorImmediately();
            LinearIndeterminateDisjointAnimatorDelegate r0 = this.this$0;
            Animatable2Compat$AnimationCallback r12 = r0.animatorCompleteCallback;
            if (r12 == null) goto L6;
            r12.onAnimationEnd(r0.drawable);
            return;
        }
    }

    /* renamed from: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate$3, reason: invalid class name */
    public class AnonymousClass3 extends Property<LinearIndeterminateDisjointAnimatorDelegate, Float> {
        public AnonymousClass3(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(LinearIndeterminateDisjointAnimatorDelegate r1) {
            return Float.valueOf(LinearIndeterminateDisjointAnimatorDelegate.access$300(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(LinearIndeterminateDisjointAnimatorDelegate r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(LinearIndeterminateDisjointAnimatorDelegate r1, Float r2) {
            r1.setAnimationFraction(r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(LinearIndeterminateDisjointAnimatorDelegate r1, Float r2) {
            set2(r1, r2);
        }
    }

    static {
        DURATION_TO_MOVE_SEGMENT_ENDS = new int[]{533, 567, 850, 750};
        DELAY_TO_MOVE_SEGMENT_ENDS = new int[]{1267, 1000, 333, 0};
        ANIMATION_FRACTION = new AnonymousClass3(Float.class, "animationFraction");
    }

    public LinearIndeterminateDisjointAnimatorDelegate(Context r6, LinearProgressIndicatorSpec r7) {
        super(2);
        this.indicatorColorIndex = 0;
        this.animatorCompleteCallback = null;
        this.baseSpec = r7;
        this.interpolatorArray = new Interpolator[]{AnimationUtilsCompat.loadInterpolator(r6, R.anim.linear_indeterminate_line1_head_interpolator), AnimationUtilsCompat.loadInterpolator(r6, R.anim.linear_indeterminate_line1_tail_interpolator), AnimationUtilsCompat.loadInterpolator(r6, R.anim.linear_indeterminate_line2_head_interpolator), AnimationUtilsCompat.loadInterpolator(r6, R.anim.linear_indeterminate_line2_tail_interpolator)};
    }

    public static /* synthetic */ int access$000(LinearIndeterminateDisjointAnimatorDelegate r0) {
        return r0.indicatorColorIndex;
    }

    public static /* synthetic */ int access$002(LinearIndeterminateDisjointAnimatorDelegate r0, int r1) {
        r0.indicatorColorIndex = r1;
        return r1;
    }

    public static /* synthetic */ BaseProgressIndicatorSpec access$100(LinearIndeterminateDisjointAnimatorDelegate r0) {
        return r0.baseSpec;
    }

    public static /* synthetic */ boolean access$202(LinearIndeterminateDisjointAnimatorDelegate r0, boolean r1) {
        r0.dirtyColors = r1;
        return r1;
    }

    public static /* synthetic */ float access$300(LinearIndeterminateDisjointAnimatorDelegate r0) {
        return r0.getAnimationFraction();
    }

    private float getAnimationFraction() {
        return this.animationFraction;
    }

    private void maybeInitializeAnimators() {
        if (this.animator != null) goto L6;
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
        this.animator = r0;
        r0.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 1800.0f));
        this.animator.setInterpolator(null);
        this.animator.setRepeatCount(-1);
        this.animator.addListener(new AnonymousClass1(this));
    L6:
        if (this.completeEndAnimator != null) goto L9;
        ObjectAnimator r02 = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{1.0f});
        this.completeEndAnimator = r02;
        r02.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 1800.0f));
        this.completeEndAnimator.setInterpolator(null);
        this.completeEndAnimator.addListener(new AnonymousClass2(this));
        return;
    }

    private void maybeUpdateSegmentColors() {
        if (this.dirtyColors == false) goto L11;
        Iterator<DrawingDelegate.ActiveIndicator> r0 = this.activeIndicators.iterator();
    L6:
        if (r0.hasNext() == false) goto L8;
        DrawingDelegate.ActiveIndicator r1 = r0.next();
        r1.color = this.baseSpec.indicatorColors[this.indicatorColorIndex];
        goto L6
    L8:
        this.dirtyColors = false;
        return;
    }

    private void updateAnimatorsDuration() {
        maybeInitializeAnimators();
        this.animator.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 1800.0f));
        this.completeEndAnimator.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 1800.0f));
    }

    private void updateSegmentPositions(int r9) {
        int r0 = 0;
    L4:
        if (r0 >= this.activeIndicators.size()) goto L6;
        DrawingDelegate.ActiveIndicator r1 = this.activeIndicators.get(r0);
        int[] r2 = DELAY_TO_MOVE_SEGMENT_ENDS;
        int r3 = r0 * 2;
        int r4 = r2[r3];
        int[] r5 = DURATION_TO_MOVE_SEGMENT_ENDS;
        float r42 = getFractionInRange(r9, r4, r5[r3]);
        r1.startFraction = MathUtils.clamp(this.interpolatorArray[r3].getInterpolation(r42), 0.0f, 1.0f);
        int r32 = r3 + 1;
        float r22 = getFractionInRange(r9, r2[r32], r5[r32]);
        r1.endFraction = MathUtils.clamp(this.interpolatorArray[r32].getInterpolation(r22), 0.0f, 1.0f);
        r0 = r0 + 1;
        goto L4
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
        if (r0.isRunning() == true) goto L13;
        cancelAnimatorImmediately();
        if (this.drawable.isVisible() == false) goto L12;
        this.completeEndAnimator.setFloatValues(new float[]{this.animationFraction, 1.0f});
        this.completeEndAnimator.setDuration((long) ((1.0f - this.animationFraction) * 1800.0f));
        this.completeEndAnimator.start();
        return;
    L12:
        return;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void resetPropertiesForNewStart() {
        this.indicatorColorIndex = 0;
        Iterator<DrawingDelegate.ActiveIndicator> r1 = this.activeIndicators.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        DrawingDelegate.ActiveIndicator r2 = r1.next();
        r2.color = this.baseSpec.indicatorColors[0];
        goto L4
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void setAnimationFraction(float r2) {
        this.animationFraction = r2;
        updateSegmentPositions((int) (r2 * 1800.0f));
        maybeUpdateSegmentColors();
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
