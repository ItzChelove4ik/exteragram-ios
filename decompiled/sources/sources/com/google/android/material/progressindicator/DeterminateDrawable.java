package com.google.android.material.progressindicator;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes5.dex */
public final class DeterminateDrawable<S extends BaseProgressIndicatorSpec> extends DrawableWithAnimatedVisibilityChange {
    private static final int AMPLITUDE_ANIMATION_DURATION_MS = 500;
    static final float FULL_AMPLITUDE_PROGRESS_MAX = 0.9f;
    static final float FULL_AMPLITUDE_PROGRESS_MIN = 0.1f;
    static final float GAP_RAMP_DOWN_THRESHOLD = 0.01f;
    private static final FloatPropertyCompat<DeterminateDrawable<?>> INDICATOR_LENGTH_IN_LEVEL = null;
    static final int MAX_DRAWABLE_LEVEL = 10000;
    private static final int PHASE_ANIMATION_DURATION_MS = 1000;
    private static final float SPRING_FORCE_STIFFNESS = 50.0f;
    private final DrawingDelegate.ActiveIndicator activeIndicator;
    private ValueAnimator amplitudeAnimator;
    private TimeInterpolator amplitudeInterpolator;
    private TimeInterpolator amplitudeOffInterpolator;
    private TimeInterpolator amplitudeOnInterpolator;
    private DrawingDelegate<S> drawingDelegate;
    private final ValueAnimator phaseAnimator;
    private boolean skipAnimationOnLevelChange;
    private final SpringAnimation springAnimation;
    private float targetAmplitudeFraction;

    /* renamed from: com.google.android.material.progressindicator.DeterminateDrawable$1, reason: invalid class name */
    public class AnonymousClass1 extends FloatPropertyCompat<DeterminateDrawable<?>> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(DeterminateDrawable<?> r1) {
            return DeterminateDrawable.access$000(r1) * 10000.0f;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(DeterminateDrawable<?> r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(DeterminateDrawable<?> r1, float r2) {
            DeterminateDrawable.access$100(r1, r2 / 10000.0f);
            DeterminateDrawable.access$200(r1, (int) r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(DeterminateDrawable<?> r1, float r2) {
            setValue2(r1, r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$TMiBORtMOk6krNAudyul128n9lo(DeterminateDrawable r0, BaseProgressIndicatorSpec r1, ValueAnimator r2) {
        r0.getClass();
        if (r1.hasWavyEffect(true) == true) goto L5;
        return;
    L5:
        if (r1.waveSpeed != 0) goto L7;
        return;
    L7:
        if (r0.isVisible() == false) goto L12;
        r0.invalidateSelf();
        return;
    }

    public static /* synthetic */ void $r8$lambda$lmoaTmJ6dTD5zbcIvzGwp2cyQ7Y(DeterminateDrawable r1, ValueAnimator r2) {
        DrawingDelegate.ActiveIndicator r22 = r1.activeIndicator;
        r22.amplitudeFraction = r1.amplitudeInterpolator.getInterpolation(r1.amplitudeAnimator.getAnimatedFraction());
    }

    static {
        INDICATOR_LENGTH_IN_LEVEL = new AnonymousClass1("indicatorLevel");
    }

    public DeterminateDrawable(Context r5, BaseProgressIndicatorSpec r6, DrawingDelegate<S> r7) {
        super(r5, r6);
        this.skipAnimationOnLevelChange = false;
        setDrawingDelegate(r7);
        DrawingDelegate.ActiveIndicator r52 = new DrawingDelegate.ActiveIndicator();
        this.activeIndicator = r52;
        r52.isDeterminate = true;
        SpringAnimation r53 = new SpringAnimation(this, INDICATOR_LENGTH_IN_LEVEL);
        this.springAnimation = r53;
        r53.setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(SPRING_FORCE_STIFFNESS));
        ValueAnimator r54 = new ValueAnimator();
        this.phaseAnimator = r54;
        r54.setDuration(1000);
        r54.setFloatValues(new float[]{0.0f, 1.0f});
        r54.setRepeatCount(-1);
        r54.addUpdateListener(new DeterminateDrawable$$ExternalSyntheticLambda1(this, r6));
        if (r6.hasWavyEffect(true) == true) goto L5;
    L7:
        setGrowFraction(1.0f);
        return;
    L5:
        if (r6.waveSpeed == 0) goto L7;
        r54.start();
        goto L7
    }

    public static /* synthetic */ float access$000(DeterminateDrawable r0) {
        return r0.getIndicatorFraction();
    }

    public static /* synthetic */ void access$100(DeterminateDrawable r0, float r1) {
        r0.setIndicatorFraction(r1);
    }

    public static /* synthetic */ void access$200(DeterminateDrawable r0, int r1) {
        r0.maybeStartAmplitudeAnimator(r1);
    }

    public static DeterminateDrawable<CircularProgressIndicatorSpec> createCircularDrawable(Context r1, CircularProgressIndicatorSpec r2) {
        return createCircularDrawable(r1, r2, new CircularDrawingDelegate(r2));
    }

    public static DeterminateDrawable<CircularProgressIndicatorSpec> createCircularDrawable(Context r1, CircularProgressIndicatorSpec r2, CircularDrawingDelegate r3) {
        return new DeterminateDrawable(r1, r2, r3);
    }

    public static DeterminateDrawable<LinearProgressIndicatorSpec> createLinearDrawable(Context r1, LinearProgressIndicatorSpec r2) {
        return createLinearDrawable(r1, r2, new LinearDrawingDelegate(r2));
    }

    public static DeterminateDrawable<LinearProgressIndicatorSpec> createLinearDrawable(Context r1, LinearProgressIndicatorSpec r2, LinearDrawingDelegate r3) {
        return new DeterminateDrawable(r1, r2, r3);
    }

    private float getAmplitudeFractionFromLevel(int r3) {
        float r32 = r3;
        BaseProgressIndicatorSpec r2 = this.baseSpec;
        if (r32 >= (r2.waveAmplitudeRampProgressMin * 10000.0f)) goto L5;
        return 0.0f;
    L5:
        if (r32 > (r2.waveAmplitudeRampProgressMax * 10000.0f)) goto L10;
        return 1.0f;
    L10:
        return 0.0f;
    }

    private float getIndicatorFraction() {
        return this.activeIndicator.endFraction;
    }

    private void maybeInitializeAmplitudeAnimator() {
        if (this.amplitudeAnimator == null) goto L5;
        return;
    L5:
        Context r0 = this.context;
        int r1 = R.attr.motionEasingStandardInterpolator;
        TimeInterpolator r2 = AnimationUtils.LINEAR_INTERPOLATOR;
        this.amplitudeOnInterpolator = MotionUtils.resolveThemeInterpolator(r0, r1, r2);
        this.amplitudeOffInterpolator = MotionUtils.resolveThemeInterpolator(this.context, R.attr.motionEasingEmphasizedAccelerateInterpolator, r2);
        ValueAnimator r02 = new ValueAnimator();
        this.amplitudeAnimator = r02;
        r02.setDuration(500);
        this.amplitudeAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        this.amplitudeAnimator.setInterpolator(null);
        this.amplitudeAnimator.addUpdateListener(new DeterminateDrawable$$ExternalSyntheticLambda0(this));
    }

    private void maybeStartAmplitudeAnimator(int r3) {
        if (this.baseSpec.hasWavyEffect(true) == false) goto L21;
        maybeInitializeAmplitudeAnimator();
        float r32 = getAmplitudeFractionFromLevel(r3);
        float r0 = this.targetAmplitudeFraction;
        ValueAnimator r1 = this.amplitudeAnimator;
        if (r32 == r0) goto L17;
        if (r1.isRunning() == false) goto L10;
        this.amplitudeAnimator.cancel();
    L10:
        this.targetAmplitudeFraction = r32;
        if (r32 != 1.0f) goto L14;
        this.amplitudeInterpolator = this.amplitudeOnInterpolator;
        this.amplitudeAnimator.start();
        return;
    L14:
        this.amplitudeInterpolator = this.amplitudeOffInterpolator;
        this.amplitudeAnimator.reverse();
        return;
    L17:
        if (r1.isRunning() == true) goto L20;
        setAmplitudeFraction(r32);
        return;
    L20:
        return;
    }

    private void setAmplitudeFraction(float r2) {
        this.activeIndicator.amplitudeFraction = r2;
        invalidateSelf();
    }

    private void setIndicatorFraction(float r2) {
        this.activeIndicator.endFraction = r2;
        invalidateSelf();
    }

    private void updateSpringMinVisibleChange() {
        int r0 = getBounds().width();
        int r1 = getBounds().height();
        if (r0 <= 0) goto L12;
        if (r1 <= 0) goto L13;
        boolean r2 = this.drawingDelegate instanceof LinearDrawingDelegate;
        SpringAnimation r4 = this.springAnimation;
        if (r2 == false) goto L10;
        r4.setMinimumVisibleChange(10000.0f / r0);
        return;
    L10:
        r4.setMinimumVisibleChange((float) (10000.0d / (Math.min(r1, r0) * 3.141592653589793d)));
        return;
    L13:
        return;
    }

    public void addSpringAnimationEndListener(DynamicAnimation.OnAnimationEndListener r1) {
        this.springAnimation.addEndListener(r1);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ void clearAnimationCallbacks() {
        super.clearAnimationCallbacks();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        if (getBounds().isEmpty() == false) goto L5;
        return;
    L5:
        if (isVisible() == true) goto L7;
        return;
    L7:
        if (r11.getClipBounds(this.clipBounds) == false) goto L21;
        r11.save();
        this.drawingDelegate.validateSpecAndAdjustCanvas(r11, getBounds(), getGrowFraction(), isShowing(), isHiding());
        this.activeIndicator.phaseFraction = getPhaseFraction();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        DrawingDelegate.ActiveIndicator r1 = this.activeIndicator;
        BaseProgressIndicatorSpec r2 = this.baseSpec;
        r1.color = r2.indicatorColors[0];
        int r12 = r2.indicatorTrackGapSize;
        DrawingDelegate<S> r3 = this.drawingDelegate;
        if (r12 > 0) goto L12;
        r3.fillTrack(r11, this.paint, 0.0f, 1.0f, r2.trackColor, getAlpha(), 0);
    L17:
        this.drawingDelegate.fillIndicator(r11, this.paint, this.activeIndicator, getAlpha());
        this.drawingDelegate.drawStopIndicator(r11, this.paint, this.baseSpec.indicatorColors[0], getAlpha());
        r11.restore();
        return;
    L12:
        if ((r3 instanceof LinearDrawingDelegate) == true) goto L15;
        r12 = (int) ((r12 * MathUtils.clamp(getIndicatorFraction(), 0.0f, GAP_RAMP_DOWN_THRESHOLD)) / GAP_RAMP_DOWN_THRESHOLD);
    L15:
        this.drawingDelegate.fillTrack(r11, this.paint, getIndicatorFraction(), 1.0f, this.baseSpec.trackColor, getAlpha(), r12);
        goto L17
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public DrawingDelegate<S> getDrawingDelegate() {
        return this.drawingDelegate;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.drawingDelegate.getPreferredWidth();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public SpringForce getSpringForce() {
        return this.springAnimation.getSpring();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean hideNow() {
        return super.hideNow();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean isHiding() {
        return super.isHiding();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean isShowing() {
        return super.isShowing();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.springAnimation.skipToEnd();
        setIndicatorFraction(getLevel() / 10000.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int r4) {
        float r0 = getAmplitudeFractionFromLevel(r4);
        if (this.skipAnimationOnLevelChange == false) goto L5;
        this.springAnimation.skipToEnd();
        setIndicatorFraction(r4 / 10000.0f);
        setAmplitudeFraction(r0);
        return true;
    L5:
        updateSpringMinVisibleChange();
        this.springAnimation.setStartValue(getIndicatorFraction() * 10000.0f);
        this.springAnimation.animateToFinalPosition(r4);
        return true;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ void registerAnimationCallback(Animatable2Compat$AnimationCallback r1) {
        super.registerAnimationCallback(r1);
    }

    public void removeSpringAnimationEndListener(DynamicAnimation.OnAnimationEndListener r1) {
        this.springAnimation.removeEndListener(r1);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int r1) {
        super.setAlpha(r1);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter r1) {
        super.setColorFilter(r1);
    }

    public void setDrawingDelegate(DrawingDelegate<S> r1) {
        this.drawingDelegate = r1;
    }

    public void setEnforcedDrawing(boolean r2) {
        if (r2 == true) goto L4;
    L7:
        if (r2 == false) goto L9;
        return;
    L9:
        if (this.phaseAnimator.isRunning() == false) goto L13;
        this.phaseAnimator.cancel();
        return;
    L13:
        return;
    L4:
        if (this.phaseAnimator.isRunning() == true) goto L7;
        this.phaseAnimator.start();
    }

    public void setLevelByFraction(float r2) {
        setLevel((int) (r2 * 10000.0f));
    }

    public void setSpringForce(SpringForce r1) {
        this.springAnimation.setSpring(r1);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setVisible(boolean r1, boolean r2) {
        return super.setVisible(r1, r2);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean setVisible(boolean r1, boolean r2, boolean r3) {
        return super.setVisible(r1, r2, r3);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean setVisibleInternal(boolean r1, boolean r2, boolean r3) {
        boolean r12 = super.setVisibleInternal(r1, r2, r3);
        float r22 = this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver());
        if (r22 != 0.0f) goto L6;
        this.skipAnimationOnLevelChange = true;
        return r12;
    L6:
        this.skipAnimationOnLevelChange = false;
        this.springAnimation.getSpring().setStiffness(SPRING_FORCE_STIFFNESS / r22);
        return r12;
    }

    public void setWaveAmplitudeRampProgressMax(float r2) {
        this.baseSpec.waveAmplitudeRampProgressMax = r2;
        invalidateSelf();
    }

    public void setWaveAmplitudeRampProgressMin(float r2) {
        this.baseSpec.waveAmplitudeRampProgressMin = r2;
        invalidateSelf();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean unregisterAnimationCallback(Animatable2Compat$AnimationCallback r1) {
        return super.unregisterAnimationCallback(r1);
    }
}
