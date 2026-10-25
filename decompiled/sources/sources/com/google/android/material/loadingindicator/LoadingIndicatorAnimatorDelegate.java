package com.google.android.material.loadingindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.loadingindicator.LoadingIndicatorDrawingDelegate;

/* loaded from: classes5.dex */
class LoadingIndicatorAnimatorDelegate {
    private static final Property<LoadingIndicatorAnimatorDelegate, Float> ANIMATION_FRACTION = null;
    private static final int CONSTANT_ROTATION_PER_SHAPE_DEGREES = 50;
    private static final int DURATION_PER_SHAPE_IN_MS = 650;
    private static final int EXTRA_ROTATION_PER_SHAPE_DEGREES = 90;
    private static final FloatPropertyCompat<LoadingIndicatorAnimatorDelegate> MORPH_FACTOR = null;
    private static final float SPRING_DAMPING_RATIO = 0.6f;
    private static final float SPRING_STIFFNESS = 200.0f;
    private float animationFraction;
    private ObjectAnimator animator;
    LoadingIndicatorDrawable drawable;
    LoadingIndicatorDrawingDelegate.IndicatorState indicatorState;
    private float morphFactor;
    private int morphFactorTarget;
    LoadingIndicatorSpec specs;
    private SpringAnimation springAnimation;

    /* renamed from: com.google.android.material.loadingindicator.LoadingIndicatorAnimatorDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ LoadingIndicatorAnimatorDelegate this$0;

        public AnonymousClass1(LoadingIndicatorAnimatorDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r1) {
            super.onAnimationRepeat(r1);
            LoadingIndicatorAnimatorDelegate.access$100(this.this$0).animateToFinalPosition(LoadingIndicatorAnimatorDelegate.access$004(this.this$0));
        }
    }

    /* renamed from: com.google.android.material.loadingindicator.LoadingIndicatorAnimatorDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends Property<LoadingIndicatorAnimatorDelegate, Float> {
        public AnonymousClass2(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(LoadingIndicatorAnimatorDelegate r1) {
            return Float.valueOf(LoadingIndicatorAnimatorDelegate.access$200(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(LoadingIndicatorAnimatorDelegate r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(LoadingIndicatorAnimatorDelegate r1, Float r2) {
            r1.setAnimationFraction(r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(LoadingIndicatorAnimatorDelegate r1, Float r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: com.google.android.material.loadingindicator.LoadingIndicatorAnimatorDelegate$3, reason: invalid class name */
    public class AnonymousClass3 extends FloatPropertyCompat<LoadingIndicatorAnimatorDelegate> {
        public AnonymousClass3(String r1) {
            super(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(LoadingIndicatorAnimatorDelegate r1) {
            return LoadingIndicatorAnimatorDelegate.access$300(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(LoadingIndicatorAnimatorDelegate r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(LoadingIndicatorAnimatorDelegate r1, float r2) {
            r1.setMorphFactor(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(LoadingIndicatorAnimatorDelegate r1, float r2) {
            setValue2(r1, r2);
        }
    }

    static {
        ANIMATION_FRACTION = new AnonymousClass2(Float.class, "animationFraction");
        MORPH_FACTOR = new AnonymousClass3("morphFactor");
    }

    public LoadingIndicatorAnimatorDelegate(LoadingIndicatorSpec r1) {
        this.specs = r1;
        this.indicatorState = new LoadingIndicatorDrawingDelegate.IndicatorState();
    }

    public static /* synthetic */ int access$004(LoadingIndicatorAnimatorDelegate r1) {
        int r0 = r1.morphFactorTarget + 1;
        r1.morphFactorTarget = r0;
        return r0;
    }

    public static /* synthetic */ SpringAnimation access$100(LoadingIndicatorAnimatorDelegate r0) {
        return r0.springAnimation;
    }

    public static /* synthetic */ float access$200(LoadingIndicatorAnimatorDelegate r0) {
        return r0.getAnimationFraction();
    }

    public static /* synthetic */ float access$300(LoadingIndicatorAnimatorDelegate r0) {
        return r0.getMorphFactor();
    }

    private float getAnimationFraction() {
        return this.animationFraction;
    }

    private float getMorphFactor() {
        return this.morphFactor;
    }

    private void maybeInitializeAnimators() {
        if (this.springAnimation != null) goto L6;
        this.springAnimation = new SpringAnimation(this, MORPH_FACTOR).setSpring(new SpringForce().setStiffness(SPRING_STIFFNESS).setDampingRatio(SPRING_DAMPING_RATIO)).setMinimumVisibleChange(0.01f);
    L6:
        if (this.animator != null) goto L9;
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
        this.animator = r0;
        r0.setDuration(650);
        this.animator.setInterpolator(null);
        this.animator.setRepeatCount(-1);
        this.animator.addListener(new AnonymousClass1(this));
        return;
    }

    private void updateIndicatorRotation(int r4) {
        float r0 = this.morphFactorTarget - 1;
        float r1 = this.morphFactor - r0;
        float r42 = r4 / 650.0f;
        if (r42 != 1.0f) goto L5;
        r42 = 0.0f;
    L5:
        this.indicatorState.rotationDegree = (((r0 * 140.0f) + (r42 * 50.0f)) + (r1 * 90.0f)) % 360.0f;
    }

    private void updateIndicatorShapeAndColor() {
        LoadingIndicatorDrawingDelegate.IndicatorState r0 = this.indicatorState;
        r0.morphFraction = this.morphFactor;
        int r1 = this.morphFactorTarget - 1;
        int[] r2 = this.specs.indicatorColors;
        int r12 = r1 % r2.length;
        int r3 = (r12 + 1) % r2.length;
        r0.color = ArgbEvaluatorCompat.getInstance().evaluate2(MathUtils.clamp(this.morphFactor - (this.morphFactorTarget - 1), 0.0f, 1.0f), Integer.valueOf(r2[r12]), Integer.valueOf(r2[r3])).intValue();
    }

    public void cancelAnimatorImmediately() {
        ObjectAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        SpringAnimation r1 = this.springAnimation;
        if (r1 == null) goto L9;
        r1.skipToEnd();
        return;
    }

    public void invalidateSpecValues() {
        resetPropertiesForNewStart();
    }

    public void registerDrawable(LoadingIndicatorDrawable r1) {
        this.drawable = r1;
    }

    public void resetPropertiesForNewStart() {
        this.morphFactorTarget = 1;
        setMorphFactor(0.0f);
        LoadingIndicatorDrawingDelegate.IndicatorState r0 = this.indicatorState;
        r0.color = this.specs.indicatorColors[0];
    }

    public void setAnimationFraction(float r2) {
        this.animationFraction = r2;
        updateIndicatorRotation((int) (r2 * 650.0f));
        LoadingIndicatorDrawable r1 = this.drawable;
        if (r1 == null) goto L6;
        r1.invalidateSelf();
        return;
    }

    public void setMorphFactor(float r1) {
        this.morphFactor = r1;
        updateIndicatorShapeAndColor();
        LoadingIndicatorDrawable r0 = this.drawable;
        if (r0 == null) goto L6;
        r0.invalidateSelf();
        return;
    }

    public void setMorphFactorTarget(int r1) {
        this.morphFactorTarget = r1;
    }

    public void startAnimator() {
        maybeInitializeAnimators();
        resetPropertiesForNewStart();
        this.springAnimation.animateToFinalPosition(this.morphFactorTarget);
        this.animator.start();
    }
}
