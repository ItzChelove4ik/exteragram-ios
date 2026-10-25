package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final Property<LinearIndeterminateContiguousAnimatorDelegate, Float> ANIMATION_FRACTION = null;
    private static final int DURATION_PER_CYCLE_IN_MS = 333;
    private static final int TOTAL_DURATION_IN_MS = 667;
    private float animationFraction;
    private ObjectAnimator animator;
    private final BaseProgressIndicatorSpec baseSpec;
    private boolean dirtyColors;
    private FastOutSlowInInterpolator interpolator;
    private int newIndicatorColorIndex;

    /* renamed from: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ LinearIndeterminateContiguousAnimatorDelegate this$0;

        public AnonymousClass1(LinearIndeterminateContiguousAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r4) {
            super.onAnimationRepeat(r4);
            LinearIndeterminateContiguousAnimatorDelegate r42 = this.this$0;
            LinearIndeterminateContiguousAnimatorDelegate.access$002(r42, (LinearIndeterminateContiguousAnimatorDelegate.access$000(r42) + 1) % LinearIndeterminateContiguousAnimatorDelegate.access$100(this.this$0).indicatorColors.length);
            LinearIndeterminateContiguousAnimatorDelegate.access$202(this.this$0, true);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends Property<LinearIndeterminateContiguousAnimatorDelegate, Float> {
        public AnonymousClass2(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(LinearIndeterminateContiguousAnimatorDelegate r1) {
            return Float.valueOf(LinearIndeterminateContiguousAnimatorDelegate.access$300(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(LinearIndeterminateContiguousAnimatorDelegate r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(LinearIndeterminateContiguousAnimatorDelegate r1, Float r2) {
            r1.setAnimationFraction(r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(LinearIndeterminateContiguousAnimatorDelegate r1, Float r2) {
            set2(r1, r2);
        }
    }

    static {
        ANIMATION_FRACTION = new AnonymousClass2(Float.class, "animationFraction");
    }

    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec r2) {
        super(3);
        this.newIndicatorColorIndex = 1;
        this.baseSpec = r2;
        this.interpolator = new FastOutSlowInInterpolator();
    }

    public static /* synthetic */ int access$000(LinearIndeterminateContiguousAnimatorDelegate r0) {
        return r0.newIndicatorColorIndex;
    }

    public static /* synthetic */ int access$002(LinearIndeterminateContiguousAnimatorDelegate r0, int r1) {
        r0.newIndicatorColorIndex = r1;
        return r1;
    }

    public static /* synthetic */ BaseProgressIndicatorSpec access$100(LinearIndeterminateContiguousAnimatorDelegate r0) {
        return r0.baseSpec;
    }

    public static /* synthetic */ boolean access$202(LinearIndeterminateContiguousAnimatorDelegate r0, boolean r1) {
        r0.dirtyColors = r1;
        return r1;
    }

    public static /* synthetic */ float access$300(LinearIndeterminateContiguousAnimatorDelegate r0) {
        return r0.getAnimationFraction();
    }

    private float getAnimationFraction() {
        return this.animationFraction;
    }

    private void maybeInitializeAnimators() {
        if (this.animator != null) goto L6;
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
        this.animator = r0;
        r0.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 333.0f));
        this.animator.setInterpolator(null);
        this.animator.setRepeatCount(-1);
        this.animator.addListener(new AnonymousClass1(this));
        return;
    }

    private void maybeUpdateSegmentColors() {
        if (this.dirtyColors == true) goto L5;
        return;
    L5:
        if (this.activeIndicators.get(1).endFraction >= 1.0f) goto L9;
        DrawingDelegate.ActiveIndicator r0 = this.activeIndicators.get(2);
        r0.color = this.activeIndicators.get(1).color;
        DrawingDelegate.ActiveIndicator r02 = this.activeIndicators.get(1);
        r02.color = this.activeIndicators.get(0).color;
        DrawingDelegate.ActiveIndicator r03 = this.activeIndicators.get(0);
        r03.color = this.baseSpec.indicatorColors[this.newIndicatorColorIndex];
        this.dirtyColors = false;
        return;
    }

    private void updateAnimatorsDuration() {
        maybeInitializeAnimators();
        this.animator.setDuration((long) (this.baseSpec.indeterminateAnimatorDurationScale * 333.0f));
    }

    private void updateSegmentPositions(int r5) {
        this.activeIndicators.get(0).startFraction = 0.0f;
        float r52 = getFractionInRange(r5, 0, TOTAL_DURATION_IN_MS);
        DrawingDelegate.ActiveIndicator r0 = this.activeIndicators.get(0);
        DrawingDelegate.ActiveIndicator r1 = this.activeIndicators.get(1);
        float r3 = this.interpolator.getInterpolation(r52);
        r1.startFraction = r3;
        r0.endFraction = r3;
        DrawingDelegate.ActiveIndicator r02 = this.activeIndicators.get(1);
        DrawingDelegate.ActiveIndicator r12 = this.activeIndicators.get(2);
        FastOutSlowInInterpolator r32 = this.interpolator;
        float r53 = r32.getInterpolation(r52 + 0.49925038f);
        r12.startFraction = r53;
        r02.endFraction = r53;
        this.activeIndicators.get(2).endFraction = 1.0f;
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
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void resetPropertiesForNewStart() {
        this.dirtyColors = true;
        this.newIndicatorColorIndex = 1;
        Iterator<DrawingDelegate.ActiveIndicator> r0 = this.activeIndicators.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        DrawingDelegate.ActiveIndicator r1 = r0.next();
        BaseProgressIndicatorSpec r2 = this.baseSpec;
        r1.color = r2.indicatorColors[0];
        r1.gapSize = r2.indicatorTrackGapSize / 2;
        goto L4
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void setAnimationFraction(float r2) {
        this.animationFraction = r2;
        updateSegmentPositions((int) (r2 * 333.0f));
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
    }
}
