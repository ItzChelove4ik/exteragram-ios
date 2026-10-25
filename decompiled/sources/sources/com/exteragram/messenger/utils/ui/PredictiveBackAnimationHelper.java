package com.exteragram.messenger.utils.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.ViewPagerActivity;

/* loaded from: classes.dex */
public final class PredictiveBackAnimationHelper {
    private float closingAlpha;
    private final RectF commitStartClosingRect;
    private final RectF commitStartEnteringRect;
    private final RectF commitTargetClosingRect;
    private final RectF commitTargetEnteringRect;
    private final RectF currentClosingRect;
    private float currentCornerRadius;
    private final RectF currentEnteringRect;
    private final Interpolator gestureInterpolator;
    private float initialTouchY;
    private float interpolatedProgress;
    private final Interpolator postCommitInterpolator;
    private float progress;
    private float scrimAlphaMultiplier;
    private final RectF startClosingRect;
    private float startCornerRadius;
    private final RectF startEnteringRect;
    private final RectF targetClosingRect;
    private float targetCornerRadius;
    private final RectF targetEnteringRect;
    private final Interpolator verticalMoveInterpolator;

    public PredictiveBackAnimationHelper() {
        this.startClosingRect = new RectF();
        this.targetClosingRect = new RectF();
        this.currentClosingRect = new RectF();
        this.startEnteringRect = new RectF();
        this.targetEnteringRect = new RectF();
        this.currentEnteringRect = new RectF();
        this.commitStartClosingRect = new RectF();
        this.commitTargetClosingRect = new RectF();
        this.commitStartEnteringRect = new RectF();
        this.commitTargetEnteringRect = new RectF();
        this.gestureInterpolator = new PathInterpolator(0.1f, 0.1f, 0.0f, 1.0f);
        this.postCommitInterpolator = createEmphasizedInterpolator();
        this.verticalMoveInterpolator = new DecelerateInterpolator();
        this.closingAlpha = 1.0f;
        this.scrimAlphaMultiplier = 1.0f;
    }

    private static Interpolator createEmphasizedInterpolator() {
        Path r0 = new Path();
        r0.moveTo(0.0f, 0.0f);
        r0.cubicTo(0.05f, 0.0f, 0.133333f, 0.06f, 0.166666f, 0.4f);
        r0.cubicTo(0.208333f, 0.82f, 0.25f, 1.0f, 1.0f, 1.0f);
        return new PathInterpolator(r0);
    }

    public static void drawTransitionBackground(Canvas r1, Drawable r2, int r3, int r4, Rect r5) {
        if (r2 != null) goto L4;
        return;
    L4:
        r2.copyBounds(r5);
        r2.setBounds(0, 0, r3, r4);
        r2.draw(r1);
        r2.setBounds(r5);
    }

    public static Drawable getTransitionBackground(List<BaseFragment> r2, BaseFragment r3) {
        if (r2 == null) goto L7;
        if (r2.size() <= 1) goto L7;
        r3 = r2.get(r2.size() - 2);
    L7:
        if ((r3 instanceof ViewPagerActivity) == false) goto L14;
        BaseFragment r22 = ((ViewPagerActivity) r3).getCurrentVisibleFragment();
        if (r22 == null) goto L14;
        if (r22 == r3) goto L14;
        r3 = r22;
    L14:
        if ((r3 instanceof ProfileActivity) == true) goto L16;
        Theme.ResourcesProvider r23 = null;
        if (r3 == null) goto L20;
        View r0 = r3.fragmentView;
    L21:
        if (r0 == null) goto L23;
        Drawable r02 = r0.getBackground();
    L25:
        if ((r02 instanceof ColorDrawable) == true) goto L27;
    L29:
        if (r02 == null) goto L31;
        return r02;
    L31:
        int r1 = Theme.key_windowBackgroundGray;
        if (r3 == null) goto L35;
        r23 = r3.getResourceProvider();
    L35:
        return new ColorDrawable(Theme.getColor(r1, r23));
    L27:
        if (Color.alpha(((ColorDrawable) r02).getColor()) != 0) goto L29;
        r02 = null;
        goto L29
    L23:
        r02 = null;
        goto L25
    L20:
        r0 = null;
        goto L21
    L16:
        return new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray, r3.getResourceProvider()));
    }

    private float getYOffset(float r6, float r7) {
        float r0 = this.startClosingRect.height();
        float r72 = r7 - this.initialTouchY;
        if (r72 >= 0.0f) goto L5;
        float r2 = -1.0f;
    L6:
        float r4 = r0 / 2.0f;
        return (r2 * this.verticalMoveInterpolator.getInterpolation(Math.min(r4, Math.abs(r72)) / r4)) * Math.max(0.0f, ((r0 - r6) / 2.0f) - AndroidUtilities.dp(8.0f));
    L5:
        r2 = 1.0f;
        goto L6
    }

    private static void interpolate(RectF r4, RectF r5, RectF r6, float r7) {
        r4.set(AndroidUtilities.lerp(r5.left, r6.left, r7), AndroidUtilities.lerp(r5.top, r6.top, r7), AndroidUtilities.lerp(r5.right, r6.right, r7), AndroidUtilities.lerp(r5.bottom, r6.bottom, r7));
    }

    private static void scaleCentered(RectF r5, float r6) {
        float r0 = r5.centerX();
        float r1 = r5.centerY();
        float r2 = (r5.width() * r6) / 2.0f;
        float r4 = (r5.height() * r6) / 2.0f;
        r5.set(r0 - r2, r1 - r4, r0 + r2, r1 + r4);
    }

    public float getClosingAlpha() {
        return this.closingAlpha;
    }

    public void getClosingRect(RectF r1) {
        r1.set(this.currentClosingRect);
    }

    public float getClosingScale() {
        return this.currentClosingRect.width() / this.startClosingRect.width();
    }

    public float getCornerRadius() {
        return this.currentCornerRadius;
    }

    public void getEnteringRect(RectF r1) {
        r1.set(this.currentEnteringRect);
    }

    public float getEnteringScale() {
        return this.currentEnteringRect.width() / this.startClosingRect.width();
    }

    public int getPostCommitDuration() {
        return 375;
    }

    public float getProgress() {
        return this.progress;
    }

    public int getScrimAlpha(boolean r2) {
        if (r2 == false) goto L4;
        float r22 = 0.8f;
    L6:
        return (int) ((r22 * 255.0f) * this.scrimAlphaMultiplier);
    L4:
        r22 = 0.2f;
        goto L6
    }

    public float getSlideDistance() {
        return this.interpolatedProgress * AndroidUtilities.dp(336.0f);
    }

    public void prepareCommit() {
        this.commitStartClosingRect.set(this.currentClosingRect);
        this.commitStartEnteringRect.set(this.currentEnteringRect);
        this.commitTargetEnteringRect.set(this.startClosingRect);
        this.commitTargetClosingRect.set(this.startClosingRect);
        this.commitTargetClosingRect.offset(this.currentClosingRect.left + AndroidUtilities.dp(96.0f), 0.0f);
    }

    public void reset() {
        this.startClosingRect.setEmpty();
        this.targetClosingRect.setEmpty();
        this.currentClosingRect.setEmpty();
        this.startEnteringRect.setEmpty();
        this.targetEnteringRect.setEmpty();
        this.currentEnteringRect.setEmpty();
        this.commitStartClosingRect.setEmpty();
        this.commitTargetClosingRect.setEmpty();
        this.commitStartEnteringRect.setEmpty();
        this.commitTargetEnteringRect.setEmpty();
        this.progress = 0.0f;
        this.interpolatedProgress = 0.0f;
        this.closingAlpha = 1.0f;
        this.scrimAlphaMultiplier = 1.0f;
        this.initialTouchY = 0.0f;
        this.startCornerRadius = 0.0f;
        this.targetCornerRadius = 0.0f;
        this.currentCornerRadius = 0.0f;
    }

    public void setScrimAlphaMultiplier(float r1) {
        this.scrimAlphaMultiplier = Utilities.clamp01(r1);
    }

    public void start(int r3, int r4, float r5, boolean r6, float r7) {
        this.initialTouchY = r5;
        this.progress = 0.0f;
        this.interpolatedProgress = 0.0f;
        this.closingAlpha = 1.0f;
        this.scrimAlphaMultiplier = 1.0f;
        this.startCornerRadius = r7;
        this.targetCornerRadius = Math.max(r7, AndroidUtilities.dp(40.0f));
        this.currentCornerRadius = r7;
        this.startClosingRect.set(0.0f, 0.0f, Math.max(1, r3), Math.max(1, r4));
        this.targetClosingRect.set(this.startClosingRect);
        scaleCentered(this.targetClosingRect, 0.85f);
        if (r6 == false) goto L5;
        RectF r32 = this.targetClosingRect;
        r32.offset((this.startClosingRect.right - r32.right) - AndroidUtilities.dp(8.0f), 0.0f);
    L5:
        this.currentClosingRect.set(this.startClosingRect);
        this.startEnteringRect.set(this.startClosingRect);
        scaleCentered(this.startEnteringRect, Utilities.clamp((this.startClosingRect.height() - (r7 * 2.0f)) / this.startClosingRect.height(), 0.95f, 0.85f));
        RectF r33 = this.startEnteringRect;
        r33.offset(-Math.max(r33.width() * 0.14999998f, AndroidUtilities.dp(96.0f)), 0.0f);
        this.targetEnteringRect.set(this.startEnteringRect);
        scaleCentered(this.targetEnteringRect, 0.85f);
        this.currentEnteringRect.set(this.startEnteringRect);
    }

    public void update(float r5, float r6) {
        float r52 = Utilities.clamp01(r5);
        this.progress = r52;
        float r53 = this.gestureInterpolator.getInterpolation(r52);
        this.interpolatedProgress = r53;
        this.closingAlpha = 1.0f;
        this.scrimAlphaMultiplier = 1.0f;
        interpolate(this.currentClosingRect, this.startClosingRect, this.targetClosingRect, r53);
        float r54 = getYOffset(this.currentClosingRect.height(), r6);
        this.currentClosingRect.offset(0.0f, r54);
        interpolate(this.currentEnteringRect, this.startEnteringRect, this.targetEnteringRect, this.interpolatedProgress);
        this.currentEnteringRect.offset(0.0f, r54);
        this.currentCornerRadius = AndroidUtilities.lerp(this.startCornerRadius, this.targetCornerRadius, this.interpolatedProgress);
    }

    public void updateCommitProgress(float r5) {
        float r52 = Utilities.clamp01(r5);
        float r0 = this.postCommitInterpolator.getInterpolation(r52);
        this.closingAlpha = Math.max(1.0f - (5.0f * r52), 0.0f);
        this.scrimAlphaMultiplier = 1.0f - r52;
        interpolate(this.currentClosingRect, this.commitStartClosingRect, this.commitTargetClosingRect, r0);
        interpolate(this.currentEnteringRect, this.commitStartEnteringRect, this.commitTargetEnteringRect, r0);
        this.currentCornerRadius = AndroidUtilities.lerp(this.targetCornerRadius, this.startCornerRadius, r0);
    }
}
