package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.R$attr;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class BaseProgressIndicator<S extends BaseProgressIndicatorSpec> extends ProgressBar {
    static final float DEFAULT_OPACITY = 0.2f;
    static final int DEF_STYLE_RES = 0;
    public static final int HIDE_ESCAPE = 3;
    public static final int HIDE_INWARD = 2;
    public static final int HIDE_NONE = 0;
    public static final int HIDE_OUTWARD = 1;
    static final int MAX_ALPHA = 255;
    static final int MAX_HIDE_DELAY = 1000;
    public static final int SHOW_INWARD = 2;
    public static final int SHOW_NONE = 0;
    public static final int SHOW_OUTWARD = 1;
    AnimatorDurationScaleProvider animatorDurationScaleProvider;
    private final Runnable delayedHide;
    private final Runnable delayedShow;
    private final DynamicAnimation.OnAnimationEndListener hideAfterMaxProgressListener;
    private final Animatable2Compat$AnimationCallback hideAnimationCallback;
    boolean initialized;
    private boolean isIndeterminateModeChangeRequested;
    private boolean isParentDoneInitializing;
    private long lastShowStartTime;
    private final int minHideDelay;
    private final int showDelay;
    S spec;
    private int storedProgress;
    private boolean storedProgressAnimated;
    private final Animatable2Compat$AnimationCallback switchIndeterminateModeCallback;
    private int visibilityAfterHide;

    /* renamed from: com.google.android.material.progressindicator.BaseProgressIndicator$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ BaseProgressIndicator this$0;

        public AnonymousClass1(BaseProgressIndicator r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator.access$000(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.BaseProgressIndicator$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ BaseProgressIndicator this$0;

        public AnonymousClass2(BaseProgressIndicator r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator.access$100(this.this$0);
            BaseProgressIndicator.access$202(this.this$0, -1);
        }
    }

    /* renamed from: com.google.android.material.progressindicator.BaseProgressIndicator$3, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass3 extends Animatable2Compat$AnimationCallback {
        final /* synthetic */ BaseProgressIndicator this$0;

        public AnonymousClass3(BaseProgressIndicator r1) {
            this.this$0 = r1;
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
        public void onAnimationEnd(Drawable r2) {
            this.this$0.setIndeterminate(false);
            BaseProgressIndicator r22 = this.this$0;
            r22.setProgressCompat(BaseProgressIndicator.access$300(r22), BaseProgressIndicator.access$400(this.this$0));
        }
    }

    /* renamed from: com.google.android.material.progressindicator.BaseProgressIndicator$4, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass4 extends Animatable2Compat$AnimationCallback {
        final /* synthetic */ BaseProgressIndicator this$0;

        public AnonymousClass4(BaseProgressIndicator r1) {
            this.this$0 = r1;
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
        public void onAnimationEnd(Drawable r1) {
            super.onAnimationEnd(r1);
            if (BaseProgressIndicator.access$500(this.this$0) == true) goto L6;
            BaseProgressIndicator r0 = this.this$0;
            r0.setVisibility(BaseProgressIndicator.access$600(r0));
            return;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface HideAnimationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface ShowAnimationBehavior {
    }

    public static /* synthetic */ void $r8$lambda$9LFVkjgCbQazReD7AnEDM8_FfLk(BaseProgressIndicator r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        if (r0.getProgressDrawable() != null) goto L5;
        return;
    L5:
        if (r0.getProgressDrawable().getLevel() != 10000) goto L9;
        r0.hide();
        return;
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_ProgressIndicator;
    }

    public BaseProgressIndicator(Context r8, AttributeSet r9, int r10, int r11) {
        super(MaterialThemeOverlay.wrap(r8, r9, r10, DEF_STYLE_RES), r9, r10);
        this.lastShowStartTime = -1;
        this.isIndeterminateModeChangeRequested = false;
        this.visibilityAfterHide = 4;
        this.hideAfterMaxProgressListener = new BaseProgressIndicator$$ExternalSyntheticLambda0(this);
        this.delayedShow = new AnonymousClass1(this);
        this.delayedHide = new AnonymousClass2(this);
        this.switchIndeterminateModeCallback = new AnonymousClass3(this);
        this.hideAnimationCallback = new AnonymousClass4(this);
        Context r1 = getContext();
        this.spec = (S) createSpec(r1, r9);
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r1, r9, R.styleable.BaseProgressIndicator, r10, r11, new int[0]);
        this.showDelay = r82.getInt(R.styleable.BaseProgressIndicator_showDelay, -1);
        this.minHideDelay = Math.min(r82.getInt(R.styleable.BaseProgressIndicator_minHideDelay, -1), 1000);
        r82.recycle();
        this.animatorDurationScaleProvider = new AnimatorDurationScaleProvider();
        this.isParentDoneInitializing = true;
    }

    public static /* synthetic */ void access$000(BaseProgressIndicator r0) {
        r0.internalShow();
    }

    public static /* synthetic */ void access$100(BaseProgressIndicator r0) {
        r0.internalHide();
    }

    public static /* synthetic */ long access$202(BaseProgressIndicator r0, long r1) {
        r0.lastShowStartTime = r1;
        return r1;
    }

    public static /* synthetic */ int access$300(BaseProgressIndicator r0) {
        return r0.storedProgress;
    }

    public static /* synthetic */ boolean access$400(BaseProgressIndicator r0) {
        return r0.storedProgressAnimated;
    }

    public static /* synthetic */ boolean access$500(BaseProgressIndicator r0) {
        return r0.isIndeterminateModeChangeRequested;
    }

    public static /* synthetic */ int access$600(BaseProgressIndicator r0) {
        return r0.visibilityAfterHide;
    }

    private DrawingDelegate<S> getCurrentDrawingDelegate() {
        if (isIndeterminate() == false) goto L10;
        if (getIndeterminateDrawable() != null) goto L8;
        return null;
    L8:
        return getIndeterminateDrawable().getDrawingDelegate();
    L10:
        if (getProgressDrawable() != null) goto L13;
        return null;
    L13:
        return getProgressDrawable().getDrawingDelegate();
    }

    private void internalHide() {
        ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).setVisible(false, false, true);
        if (isNoLongerNeedToBeVisible() == false) goto L6;
        setVisibility(4);
        return;
    }

    private void internalShow() {
        if (this.minHideDelay <= 0) goto L5;
        this.lastShowStartTime = SystemClock.uptimeMillis();
    L5:
        setVisibility(0);
    }

    private boolean isNoLongerNeedToBeVisible() {
        if (getProgressDrawable() == null) goto L7;
        if (getProgressDrawable().isVisible() == false) goto L7;
        return false;
    L7:
        if (getIndeterminateDrawable() != null) goto L9;
        return true;
    L9:
        if (getIndeterminateDrawable().isVisible() == false) goto L16;
        return false;
    L16:
        return true;
    }

    private void registerAnimationCallbacks() {
        registerSwitchIndeterminateModeCallback();
        if (getProgressDrawable() == null) goto L6;
        getProgressDrawable().registerAnimationCallback(this.hideAnimationCallback);
    L6:
        if (getIndeterminateDrawable() == null) goto L9;
        getIndeterminateDrawable().registerAnimationCallback(this.hideAnimationCallback);
        return;
    }

    private void unregisterAnimationCallbacks() {
        if (getIndeterminateDrawable() == null) goto L6;
        getIndeterminateDrawable().unregisterAnimationCallback(this.hideAnimationCallback);
        getIndeterminateDrawable().getAnimatorDelegate().unregisterAnimatorsCompleteCallback();
    L6:
        if (getProgressDrawable() == null) goto L9;
        getProgressDrawable().unregisterAnimationCallback(this.hideAnimationCallback);
        return;
    }

    public void applyNewVisibility(boolean r3) {
        if (this.isParentDoneInitializing == true) goto L5;
        return;
    L5:
        ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).setVisible(visibleToUser(), false, r3);
    }

    public abstract S createSpec(Context r1, AttributeSet r2);

    @Override // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        if (isIndeterminate() == false) goto L7;
        return getIndeterminateDrawable();
    L7:
        return getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.spec.hideAnimationBehavior;
    }

    @Override // android.widget.ProgressBar
    public /* bridge */ /* synthetic */ Drawable getIndeterminateDrawable() {
        return getIndeterminateDrawable();
    }

    @Override // android.widget.ProgressBar
    public IndeterminateDrawable<S> getIndeterminateDrawable() {
        return (IndeterminateDrawable) super.getIndeterminateDrawable();
    }

    public int[] getIndicatorColor() {
        return this.spec.indicatorColors;
    }

    public int getIndicatorTrackGapSize() {
        return this.spec.indicatorTrackGapSize;
    }

    @Override // android.widget.ProgressBar
    public /* bridge */ /* synthetic */ Drawable getProgressDrawable() {
        return getProgressDrawable();
    }

    @Override // android.widget.ProgressBar
    public DeterminateDrawable<S> getProgressDrawable() {
        return (DeterminateDrawable) super.getProgressDrawable();
    }

    public int getShowAnimationBehavior() {
        return this.spec.showAnimationBehavior;
    }

    public int getTrackColor() {
        return this.spec.trackColor;
    }

    public int getTrackCornerRadius() {
        return this.spec.trackCornerRadius;
    }

    public float getTrackCornerRadiusFraction() {
        return this.spec.trackCornerRadiusFraction;
    }

    public int getTrackThickness() {
        return this.spec.trackThickness;
    }

    public int getWaveAmplitude() {
        return this.spec.waveAmplitude;
    }

    public int getWaveSpeed() {
        return this.spec.waveSpeed;
    }

    public int getWavelengthDeterminate() {
        return this.spec.wavelengthDeterminate;
    }

    public int getWavelengthIndeterminate() {
        return this.spec.wavelengthIndeterminate;
    }

    public void hide() {
        if (getVisibility() == 0) goto L6;
        removeCallbacks(this.delayedShow);
        return;
    L6:
        removeCallbacks(this.delayedHide);
        long r0 = SystemClock.uptimeMillis() - this.lastShowStartTime;
        int r2 = this.minHideDelay;
        long r3 = r2;
        Runnable r4 = this.delayedHide;
        if (r0 < r3) goto L10;
        r4.run();
        return;
    L10:
        postDelayed(r4, r2 - r0);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() == null) goto L6;
        getCurrentDrawable().invalidateSelf();
        return;
    }

    public boolean isEffectivelyVisible() {
        View r0 = this;
    L4:
        if (r0.getVisibility() != 0) goto L5;
        Object r02 = r0.getParent();
        if (r02 == null) goto L9;
        if ((r02 instanceof View) == false) goto L14;
        r0 = (View) r02;
        goto L4
    L14:
        return true;
    L9:
        if (getWindowVisibility() != 0) goto L11;
        return true;
    L11:
        return false;
    L5:
        return false;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerAnimationCallbacks();
        if (visibleToUser() == false) goto L6;
        internalShow();
        return;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.delayedHide);
        removeCallbacks(this.delayedShow);
        ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).hideNow();
        unregisterAnimationCallbacks();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas r6) {
        monitor-enter(this);
        int r0 = r6.save();     // Catch: Throwable -> L8
        if (getPaddingLeft() == 0) goto L6;
    L10:
        r6.translate(getPaddingLeft(), getPaddingTop());     // Catch: Throwable -> L8
    L12:
        if (getPaddingRight() == 0) goto L14;
    L15:
        r6.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));     // Catch: Throwable -> L8
    L16:
        getCurrentDrawable().draw(r6);     // Catch: Throwable -> L8
        r6.restoreToCount(r0);     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L14:
        if (getPaddingBottom() == 0) goto L16;
    L6:
        if (getPaddingTop() == 0) goto L12;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        getCurrentDrawingDelegate().invalidateCachedPaths();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int r3, int r4) {
        monitor-enter(this);
        DrawingDelegate<S> r0 = getCurrentDrawingDelegate();     // Catch: Throwable -> L10
        if (r0 != null) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (r0.getPreferredWidth() >= 0) goto L12;
        int r32 = View.getDefaultSize(getSuggestedMinimumWidth(), r3);     // Catch: Throwable -> L10
    L14:
        if (r0.getPreferredHeight() >= 0) goto L16;
        int r42 = View.getDefaultSize(getSuggestedMinimumHeight(), r4);     // Catch: Throwable -> L10
    L17:
        setMeasuredDimension(r32, r42);     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L16:
        r42 = (r0.getPreferredHeight() + getPaddingTop()) + getPaddingBottom();     // Catch: Throwable -> L10
        goto L17
    L12:
        r32 = (r0.getPreferredWidth() + getPaddingLeft()) + getPaddingRight();     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    @Override // android.view.View
    public void onVisibilityChanged(View r1, int r2) {
        super.onVisibilityChanged(r1, r2);
        if (r2 != 0) goto L5;
        boolean r12 = true;
    L6:
        applyNewVisibility(r12);
        return;
    L5:
        r12 = false;
        goto L6
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int r1) {
        super.onWindowVisibilityChanged(r1);
        applyNewVisibility(false);
    }

    public void registerSwitchIndeterminateModeCallback() {
        if (getProgressDrawable() != null) goto L5;
        return;
    L5:
        if (getIndeterminateDrawable() == null) goto L9;
        getIndeterminateDrawable().getAnimatorDelegate().registerAnimatorsCompleteCallback(this.switchIndeterminateModeCallback);
        return;
    }

    public void setAnimatorDurationScaleProvider(AnimatorDurationScaleProvider r2) {
        this.animatorDurationScaleProvider = r2;
        if (getProgressDrawable() == null) goto L6;
        getProgressDrawable().animatorDurationScaleProvider = r2;
    L6:
        if (getIndeterminateDrawable() == null) goto L9;
        getIndeterminateDrawable().animatorDurationScaleProvider = r2;
        return;
    }

    public void setHideAfterMaxProgress(boolean r2) {
        if (getProgressDrawable() != null) goto L5;
        return;
    L5:
        if (r2 == false) goto L8;
        getProgressDrawable().addSpringAnimationEndListener(this.hideAfterMaxProgressListener);
        return;
    L8:
        getProgressDrawable().removeSpringAnimationEndListener(this.hideAfterMaxProgressListener);
    }

    public void setHideAnimationBehavior(int r2) {
        this.spec.hideAnimationBehavior = r2;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean r3) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (r3 != isIndeterminate()) goto L7;
        monitor-exit(this);
        return;
    L7:
        DrawableWithAnimatedVisibilityChange r0 = (DrawableWithAnimatedVisibilityChange) getCurrentDrawable();     // Catch: Throwable -> L10
        if (r0 == null) goto L12;
        r0.hideNow();     // Catch: Throwable -> L10
    L12:
        super.setIndeterminate(r3);     // Catch: Throwable -> L10
        DrawableWithAnimatedVisibilityChange r32 = (DrawableWithAnimatedVisibilityChange) getCurrentDrawable();     // Catch: Throwable -> L10
        if (r32 == null) goto L16;
        r32.setVisible(visibleToUser(), false, false);     // Catch: Throwable -> L10
    L16:
        if ((r32 instanceof IndeterminateDrawable) == true) goto L18;
    L20:
        this.isIndeterminateModeChangeRequested = false;     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L18:
        if (visibleToUser() == false) goto L20;
        ((IndeterminateDrawable) r32).getAnimatorDelegate().startAnimator();     // Catch: Throwable -> L10
        goto L20
    }

    public void setIndeterminateAnimatorDurationScale(float r3) {
        S r0 = this.spec;
        if (r0.indeterminateAnimatorDurationScale == r3) goto L6;
        r0.indeterminateAnimatorDurationScale = r3;
        getIndeterminateDrawable().getAnimatorDelegate().invalidateSpecValues();
        return;
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable r2) {
        if ((r2 instanceof IndeterminateDrawable) == false) goto L7;
        ((DrawableWithAnimatedVisibilityChange) r2).hideNow();
        super.setIndeterminateDrawable(r2);
        return;
    L7:
        if (this.initialized == true) goto L10;
        super.setIndeterminateDrawable(r2);
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Cannot set framework drawable as indeterminate drawable.");
    }

    public void setIndicatorColor(int... r4) {
        if (r4.length != 0) goto L6;
        r4 = new int[]{MaterialColors.getColor(getContext(), R$attr.colorPrimary, -1)};
    L6:
        if (Arrays.equals(getIndicatorColor(), r4) == true) goto L9;
        this.spec.indicatorColors = r4;
        getIndeterminateDrawable().getAnimatorDelegate().invalidateSpecValues();
        invalidate();
        return;
    }

    public void setIndicatorTrackGapSize(int r3) {
        S r0 = this.spec;
        if (r0.indicatorTrackGapSize == r3) goto L6;
        r0.indicatorTrackGapSize = r3;
        r0.validateSpec();
        invalidate();
        return;
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int r2) {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L4:
        if (isIndeterminate() == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        setProgressCompat(r2, false);     // Catch: Throwable -> L11
        monitor-exit(this);
    }

    public void setProgressCompat(int r2, boolean r3) {
        if (isIndeterminate() == true) goto L5;
        super.setProgress(r2);
        if (getProgressDrawable() == null) goto L21;
        if (r3 == true) goto L22;
        getProgressDrawable().jumpToCurrentState();
        return;
    L22:
        return;
    L21:
        return;
    L5:
        if (getProgressDrawable() == null) goto L20;
        this.storedProgress = r2;
        this.storedProgressAnimated = r3;
        this.isIndeterminateModeChangeRequested = true;
        if (getIndeterminateDrawable().isVisible() == true) goto L9;
    L13:
        this.switchIndeterminateModeCallback.onAnimationEnd(getIndeterminateDrawable());
        return;
    L9:
        if (this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(getContext().getContentResolver()) == 0.0f) goto L13;
        getIndeterminateDrawable().getAnimatorDelegate().requestCancelAnimatorAfterCurrentCycle();
        return;
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable r2) {
        if ((r2 instanceof DeterminateDrawable) == false) goto L7;
        DeterminateDrawable r22 = (DeterminateDrawable) r2;
        r22.hideNow();
        super.setProgressDrawable(r22);
        r22.setLevelByFraction(getProgress() / getMax());
        return;
    L7:
        if (this.initialized == true) goto L10;
        super.setProgressDrawable(r2);
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Cannot set framework drawable as progress drawable.");
    }

    public void setShowAnimationBehavior(int r2) {
        this.spec.showAnimationBehavior = r2;
        invalidate();
    }

    public void setTrackColor(int r3) {
        S r0 = this.spec;
        if (r0.trackColor == r3) goto L6;
        r0.trackColor = r3;
        invalidate();
        return;
    }

    public void setTrackCornerRadius(int r3) {
        S r0 = this.spec;
        if (r0.trackCornerRadius == r3) goto L6;
        r0.trackCornerRadius = Math.min(r3, r0.trackThickness / 2);
        this.spec.useRelativeTrackCornerRadius = false;
        invalidate();
        return;
    }

    public void setTrackCornerRadiusFraction(float r3) {
        S r0 = this.spec;
        if (r0.trackCornerRadiusFraction == r3) goto L6;
        r0.trackCornerRadiusFraction = Math.min(r3, 0.5f);
        this.spec.useRelativeTrackCornerRadius = true;
        invalidate();
        return;
    }

    public void setTrackThickness(int r3) {
        S r0 = this.spec;
        if (r0.trackThickness == r3) goto L6;
        r0.trackThickness = r3;
        requestLayout();
        return;
    }

    public void setVisibilityAfterHide(int r2) {
        if (r2 != 0) goto L4;
    L10:
        this.visibilityAfterHide = r2;
        return;
    L4:
        if (r2 == 4) goto L10;
        if (r2 == 8) goto L10;
        f$$ExternalSyntheticBUOutline1.m("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
    }

    public void setWaveAmplitude(int r3) {
        S r0 = this.spec;
        if (r0.waveAmplitude == r3) goto L6;
        r0.waveAmplitude = Math.abs(r3);
        requestLayout();
        return;
    }

    public void setWaveAmplitudeRampProgressMax(float r2) {
        getProgressDrawable().setWaveAmplitudeRampProgressMax(r2);
        invalidate();
    }

    public void setWaveAmplitudeRampProgressMin(float r2) {
        getProgressDrawable().setWaveAmplitudeRampProgressMin(r2);
        invalidate();
    }

    public void setWaveSpeed(int r2) {
        this.spec.waveSpeed = r2;
        DeterminateDrawable<S> r22 = getProgressDrawable();
        if (this.spec.waveSpeed == 0) goto L5;
        boolean r1 = true;
    L6:
        r22.setEnforcedDrawing(r1);
        return;
    L5:
        r1 = false;
        goto L6
    }

    public void setWavelength(int r1) {
        setWavelengthDeterminate(r1);
        setWavelengthIndeterminate(r1);
    }

    public void setWavelengthDeterminate(int r3) {
        S r0 = this.spec;
        if (r0.wavelengthDeterminate == r3) goto L8;
        r0.wavelengthDeterminate = Math.abs(r3);
        if (isIndeterminate() == true) goto L9;
        requestLayout();
        return;
    L9:
        return;
    }

    public void setWavelengthIndeterminate(int r3) {
        S r0 = this.spec;
        if (r0.wavelengthIndeterminate == r3) goto L8;
        r0.wavelengthIndeterminate = Math.abs(r3);
        if (isIndeterminate() == false) goto L9;
        requestLayout();
        return;
    L9:
        return;
    }

    public void show() {
        int r0 = this.showDelay;
        Runnable r1 = this.delayedShow;
        if (r0 <= 0) goto L6;
        removeCallbacks(r1);
        postDelayed(this.delayedShow, this.showDelay);
        return;
    L6:
        r1.run();
    }

    public boolean visibleToUser() {
        if (isAttachedToWindow() == true) goto L5;
        return false;
    L5:
        if (getWindowVisibility() == 0) goto L7;
        return false;
    L7:
        if (isEffectivelyVisible() == false) goto L13;
        return true;
    L13:
        return false;
    }
}
