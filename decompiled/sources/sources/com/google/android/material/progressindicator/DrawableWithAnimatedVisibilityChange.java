package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Property;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
abstract class DrawableWithAnimatedVisibilityChange extends Drawable implements Animatable {
    private static final boolean DEFAULT_DRAWABLE_RESTART = false;
    private static final float DEFAULT_MOCK_PHASE_FRACTION = -1.0f;
    private static final int GROW_DURATION = 500;
    private static final Property<DrawableWithAnimatedVisibilityChange, Float> GROW_FRACTION = null;
    private List<Animatable2Compat$AnimationCallback> animationCallbacks;
    AnimatorDurationScaleProvider animatorDurationScaleProvider;
    final BaseProgressIndicatorSpec baseSpec;
    Rect clipBounds;
    final Context context;
    private float growFraction;
    private ValueAnimator hideAnimator;
    private boolean ignoreCallbacks;
    private Animatable2Compat$AnimationCallback internalAnimationCallback;
    private float mockGrowFraction;
    private boolean mockHideAnimationRunning;
    private float mockPhaseFraction;
    private boolean mockShowAnimationRunning;
    final Paint paint;
    private ValueAnimator showAnimator;
    private int totalAlpha;

    /* renamed from: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ DrawableWithAnimatedVisibilityChange this$0;

        public AnonymousClass1(DrawableWithAnimatedVisibilityChange r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            super.onAnimationStart(r1);
            DrawableWithAnimatedVisibilityChange.access$000(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ DrawableWithAnimatedVisibilityChange this$0;

        public AnonymousClass2(DrawableWithAnimatedVisibilityChange r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            DrawableWithAnimatedVisibilityChange.access$101(this.this$0, false, false);
            DrawableWithAnimatedVisibilityChange.access$200(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange$3, reason: invalid class name */
    public class AnonymousClass3 extends Property<DrawableWithAnimatedVisibilityChange, Float> {
        public AnonymousClass3(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(DrawableWithAnimatedVisibilityChange r1) {
            return Float.valueOf(r1.getGrowFraction());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(DrawableWithAnimatedVisibilityChange r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(DrawableWithAnimatedVisibilityChange r1, Float r2) {
            r1.setGrowFraction(r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(DrawableWithAnimatedVisibilityChange r1, Float r2) {
            set2(r1, r2);
        }
    }

    static {
        GROW_FRACTION = new AnonymousClass3(Float.class, "growFraction");
    }

    public DrawableWithAnimatedVisibilityChange(Context r2, BaseProgressIndicatorSpec r3) {
        this.mockPhaseFraction = DEFAULT_MOCK_PHASE_FRACTION;
        this.paint = new Paint();
        this.context = r2;
        this.baseSpec = r3;
        this.clipBounds = new Rect();
        this.animatorDurationScaleProvider = new AnimatorDurationScaleProvider();
        setAlpha(255);
    }

    public static /* synthetic */ void access$000(DrawableWithAnimatedVisibilityChange r0) {
        r0.dispatchAnimationStart();
    }

    public static /* synthetic */ boolean access$101(DrawableWithAnimatedVisibilityChange r0, boolean r1, boolean r2) {
        return super.setVisible(r1, r2);
    }

    public static /* synthetic */ void access$200(DrawableWithAnimatedVisibilityChange r0) {
        r0.dispatchAnimationEnd();
    }

    private void cancelAnimatorsWithoutCallbacks(ValueAnimator... r5) {
        boolean r0 = this.ignoreCallbacks;
        this.ignoreCallbacks = true;
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r5[r2].cancel();
        r2 = r2 + 1;
        goto L3
    L5:
        this.ignoreCallbacks = r0;
    }

    private void dispatchAnimationEnd() {
        Animatable2Compat$AnimationCallback r0 = this.internalAnimationCallback;
        if (r0 == null) goto L5;
        r0.onAnimationEnd(this);
    L5:
        List<Animatable2Compat$AnimationCallback> r02 = this.animationCallbacks;
        if (r02 != null) goto L8;
        return;
    L8:
        if (this.ignoreCallbacks == true) goto L15;
        Iterator<Animatable2Compat$AnimationCallback> r03 = r02.iterator();
    L11:
        if (r03.hasNext() == false) goto L16;
        r03.next().onAnimationEnd(this);
        goto L11
    L16:
        return;
    }

    private void dispatchAnimationStart() {
        Animatable2Compat$AnimationCallback r0 = this.internalAnimationCallback;
        if (r0 == null) goto L5;
        r0.onAnimationStart(this);
    L5:
        List<Animatable2Compat$AnimationCallback> r02 = this.animationCallbacks;
        if (r02 != null) goto L8;
        return;
    L8:
        if (this.ignoreCallbacks == true) goto L15;
        Iterator<Animatable2Compat$AnimationCallback> r03 = r02.iterator();
    L11:
        if (r03.hasNext() == false) goto L16;
        r03.next().onAnimationStart(this);
        goto L11
    L16:
        return;
    }

    private void endAnimatorsWithoutCallbacks(ValueAnimator... r5) {
        boolean r0 = this.ignoreCallbacks;
        this.ignoreCallbacks = true;
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r5[r2].end();
        r2 = r2 + 1;
        goto L3
    L5:
        this.ignoreCallbacks = r0;
    }

    private boolean isDeterminateDrawable() {
        return this instanceof DeterminateDrawable;
    }

    private void maybeInitializeAnimators() {
        if (this.showAnimator != null) goto L6;
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this, GROW_FRACTION, new float[]{0.0f, 1.0f});
        this.showAnimator = r0;
        r0.setDuration(500);
        this.showAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        setShowAnimator(this.showAnimator);
    L6:
        if (this.hideAnimator != null) goto L9;
        ObjectAnimator r02 = ObjectAnimator.ofFloat(this, GROW_FRACTION, new float[]{1.0f, 0.0f});
        this.hideAnimator = r02;
        r02.setDuration(500);
        this.hideAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        setHideAnimator(this.hideAnimator);
        return;
    }

    private void setHideAnimator(ValueAnimator r2) {
        ValueAnimator r0 = this.hideAnimator;
        if (r0 != null) goto L5;
    L9:
        this.hideAnimator = r2;
        r2.addListener(new AnonymousClass2(this));
        return;
    L5:
        if (r0.isRunning() == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Cannot set hideAnimator while the current hideAnimator is running.");
    }

    private void setShowAnimator(ValueAnimator r2) {
        ValueAnimator r0 = this.showAnimator;
        if (r0 != null) goto L5;
    L9:
        this.showAnimator = r2;
        r2.addListener(new AnonymousClass1(this));
        return;
    L5:
        if (r0.isRunning() == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Cannot set showAnimator while the current showAnimator is running.");
    }

    public void clearAnimationCallbacks() {
        this.animationCallbacks.clear();
        this.animationCallbacks = null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.totalAlpha;
    }

    public float getGrowFraction() {
        if (this.baseSpec.isShowAnimationEnabled() == true) goto L9;
        if (this.baseSpec.isHideAnimationEnabled() == true) goto L9;
        return 1.0f;
    L9:
        if (this.mockHideAnimationRunning == true) goto L16;
        if (this.mockShowAnimationRunning == true) goto L16;
        return this.growFraction;
    L16:
        return this.mockGrowFraction;
    }

    public ValueAnimator getHideAnimator() {
        return this.hideAnimator;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getPhaseFraction() {
        float r0 = this.mockPhaseFraction;
        if (r0 <= 0.0f) goto L6;
        return r0;
    L6:
        if (this.baseSpec.hasWavyEffect(isDeterminateDrawable()) == true) goto L8;
    L19:
        return 0.0f;
    L8:
        if (this.baseSpec.waveSpeed == 0) goto L19;
        float r02 = this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver());
        if (r02 <= 0.0f) goto L19;
        boolean r2 = isDeterminateDrawable();
        BaseProgressIndicatorSpec r3 = this.baseSpec;
        if (r2 == false) goto L14;
        int r22 = r3.wavelengthDeterminate;
    L15:
        float r03 = (SystemClock.uptimeMillis() % r6) / ((int) (((r22 * 1000.0f) / this.baseSpec.waveSpeed) * r02));
        if (r03 < 0.0f) goto L18;
        return r03;
    L18:
        return (r03 % 1.0f) + 1.0f;
    L14:
        r22 = r3.wavelengthIndeterminate;
        goto L15
    }

    public boolean hideNow() {
        return setVisible(false, false, false);
    }

    public boolean isHiding() {
        ValueAnimator r0 = this.hideAnimator;
        if (r0 == null) goto L7;
        if (r0.isRunning() == false) goto L7;
        return true;
    L7:
        if (this.mockHideAnimationRunning == true) goto L12;
        return false;
    L12:
        return true;
    }

    public boolean isRunning() {
        if (isShowing() == false) goto L5;
        return true;
    L5:
        if (isHiding() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isShowing() {
        ValueAnimator r0 = this.showAnimator;
        if (r0 == null) goto L7;
        if (r0.isRunning() == false) goto L7;
        return true;
    L7:
        if (this.mockShowAnimationRunning == true) goto L12;
        return false;
    L12:
        return true;
    }

    public void registerAnimationCallback(Animatable2Compat$AnimationCallback r2) {
        if (this.animationCallbacks != null) goto L6;
        this.animationCallbacks = new ArrayList();
    L6:
        if (this.animationCallbacks.contains(r2) == true) goto L9;
        this.animationCallbacks.add(r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.totalAlpha = r1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.paint.setColorFilter(r2);
        invalidateSelf();
    }

    public void setGrowFraction(float r2) {
        if (this.growFraction == r2) goto L6;
        this.growFraction = r2;
        invalidateSelf();
        return;
    }

    public void setInternalAnimationCallback(Animatable2Compat$AnimationCallback r1) {
        this.internalAnimationCallback = r1;
    }

    public void setMockHideAnimationRunning(boolean r1, float r2) {
        this.mockHideAnimationRunning = r1;
        this.mockGrowFraction = r2;
    }

    public void setMockPhaseFraction(float r1) {
        this.mockPhaseFraction = r1;
    }

    public void setMockShowAnimationRunning(boolean r1, float r2) {
        this.mockShowAnimationRunning = r1;
        this.mockGrowFraction = r2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r2, boolean r3) {
        return setVisible(r2, r3, true);
    }

    public boolean setVisible(boolean r3, boolean r4, boolean r5) {
        float r0 = this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver());
        if (r5 == true) goto L5;
    L7:
        boolean r52 = false;
    L9:
        return setVisibleInternal(r3, r4, r52);
    L5:
        if (r0 <= 0.0f) goto L7;
        r52 = true;
        goto L9
    }

    public boolean setVisibleInternal(boolean r4, boolean r5, boolean r6) {
        maybeInitializeAnimators();
        boolean r1 = false;
        if (isVisible() == true) goto L6;
        if (r4 == true) goto L6;
        return false;
    L6:
        if (r4 == false) goto L8;
        ValueAnimator r0 = this.showAnimator;
    L9:
        if (r4 == false) goto L11;
        ValueAnimator r2 = this.hideAnimator;
    L12:
        if (r6 == true) goto L23;
        if (r2.isRunning() == false) goto L17;
        cancelAnimatorsWithoutCallbacks(new ValueAnimator[]{r2});
    L17:
        if (r0.isRunning() == false) goto L19;
        r0.end();
    L21:
        return super.setVisible(r4, false);
    L19:
        endAnimatorsWithoutCallbacks(new ValueAnimator[]{r0});
        goto L21
    L23:
        if (r0.isRunning() == false) goto L25;
        return false;
    L25:
        if (r4 == true) goto L27;
    L28:
        r1 = true;
    L29:
        BaseProgressIndicatorSpec r62 = this.baseSpec;
        if (r4 == false) goto L32;
        boolean r42 = r62.isShowAnimationEnabled();
    L33:
        if (r42 == true) goto L36;
        endAnimatorsWithoutCallbacks(new ValueAnimator[]{r0});
        return r1;
    L36:
        if (r5 == false) goto L38;
    L42:
        r0.start();
        return r1;
    L38:
        if (r0.isPaused() == false) goto L42;
        r0.resume();
        return r1;
    L32:
        r42 = r62.isHideAnimationEnabled();
        goto L33
    L27:
        if (super.setVisible(r4, false) == false) goto L29;
    L11:
        r2 = this.showAnimator;
        goto L12
    L8:
        r0 = this.hideAnimator;
        goto L9
    }

    public void start() {
        setVisibleInternal(true, true, false);
    }

    public void stop() {
        setVisibleInternal(false, true, false);
    }

    public boolean unregisterAnimationCallback(Animatable2Compat$AnimationCallback r2) {
        List<Animatable2Compat$AnimationCallback> r0 = this.animationCallbacks;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.contains(r2) == false) goto L14;
        this.animationCallbacks.remove(r2);
        if (this.animationCallbacks.isEmpty() == false) goto L13;
        this.animationCallbacks = null;
        return true;
    L13:
        return true;
    L14:
        return false;
    }
}
