package com.exteragram.messenger.pillstack.ui.pills;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LoadingDrawable;

/* loaded from: classes.dex */
public abstract class BasePill extends FrameLayout {
    private static final SparseArray<Long> globalLastUpdateTimes = null;
    private final Runnable autoRefreshRunnable;
    protected boolean loading;
    protected LoadingDrawable loadingDrawable;
    protected View loadingTargetView;
    private final RectF rectF;
    protected Theme.ResourcesProvider resourcesProvider;
    private boolean stackVisible;

    /* renamed from: $r8$lambda$cBJm39agtpK7S37SSBb-izCrNRA, reason: not valid java name */
    public static /* synthetic */ void m1524$r8$lambda$cBJm39agtpK7S37SSBbizCrNRA(BasePill r0) {
        r0.lambda$new$0();
    }

    static {
        globalLastUpdateTimes = new SparseArray();
    }

    public BasePill(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.rectF = new RectF();
        this.stackVisible = true;
        this.autoRefreshRunnable = new BasePill$$ExternalSyntheticLambda0(this);
        this.resourcesProvider = r3;
        if (LocaleController.isRTL == false) goto L5;
        int r32 = 3;
    L6:
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2, r32 | 16));
        setClipChildren(false);
        setClipToPadding(false);
        return;
    L5:
        r32 = 5;
        goto L6
    }

    private /* synthetic */ void lambda$new$0() {
        onUpdateData(false);
        scheduleNextUpdate();
    }

    private void scheduleNextUpdate() {
        removeCallbacks(this.autoRefreshRunnable);
        if (this.stackVisible == false) goto L10;
        long r0 = getRefreshInterval();
        if (r0 <= 0) goto L9;
        postDelayed(this.autoRefreshRunnable, r0);
        return;
    L9:
        return;
    }

    public void animateSizeChange() {
        if (isLaidOut() == true) goto L5;
        return;
    L5:
        if (getVisibility() == 0) goto L8;
        return;
    L8:
        if (getParent() != null) goto L10;
        return;
    L10:
        if ((getParent().getParent() instanceof ViewGroup) == false) goto L15;
        TransitionManager.beginDelayedTransition((ViewGroup) getParent().getParent(), new TransitionSet().addTransition(new ChangeBounds()).setDuration(300).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT));
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r6) {
        super.dispatchDraw(r6);
        LoadingDrawable r0 = this.loadingDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getAlpha() <= 0) goto L7;
    L8:
        View r02 = this.loadingTargetView;
        if (r02 != null) goto L12;
        r02 = this;
    L12:
        this.rectF.set(r02.getLeft(), r02.getTop(), r02.getRight(), r02.getBottom());
        LoadingDrawable r03 = this.loadingDrawable;
        if (r03.stroke == false) goto L15;
        float r04 = r03.strokePaint.getStrokeWidth() / 2.0f;
        this.rectF.inset(r04, r04);
    L15:
        this.loadingDrawable.setBounds(this.rectF);
        this.loadingDrawable.draw(r6);
        invalidate();
        return;
    L7:
        if (this.loadingDrawable.isDisappearing() == false) goto L8;
    }

    public abstract int getPillId();

    public abstract long getRefreshInterval();

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public int getThemedColor(int r1, float r2) {
        return Theme.multAlpha(getThemedColor(r1), r2);
    }

    public boolean isRefreshDue() {
        long r0 = getRefreshInterval();
        if (r0 > 0) goto L5;
        return true;
    L5:
        long r6 = globalLastUpdateTimes.get(getPillId(), 0L).longValue();
        if (r6 != 0) goto L8;
    L12:
        return true;
    L8:
        if ((SystemClock.elapsedRealtime() - r6) >= r0) goto L12;
        return false;
    }

    public void markDataUpdated() {
        globalLastUpdateTimes.put(getPillId(), Long.valueOf(SystemClock.elapsedRealtime()));
        scheduleNextUpdate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        long r0 = getRefreshInterval();
        if (r0 > 0) goto L6;
        return;
    L6:
        if (PillStackConfig.checkAndClearPendingUpdate(getPillId()) == false) goto L10;
        onUpdateData(true);
        scheduleNextUpdate();
        return;
    L10:
        if (this.stackVisible == true) goto L12;
        return;
    L12:
        long r4 = SystemClock.elapsedRealtime();
        long r6 = globalLastUpdateTimes.get(getPillId(), 0L).longValue();
        if (r6 == 0) goto L19;
        long r42 = r4 - r6;
        if (r42 >= r0) goto L19;
        postDelayed(this.autoRefreshRunnable, r0 - r42);
        return;
    L19:
        this.autoRefreshRunnable.run();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.autoRefreshRunnable);
    }

    public abstract void onPillClicked();

    public abstract boolean onPillLongClicked();

    public void onPillSelected() {
    }

    public void onPillUnselected() {
    }

    public void onStackVisibilityChanged(boolean r5) {
        if (this.stackVisible == r5) goto L20;
        this.stackVisible = r5;
        if (r5 == true) goto L8;
        removeCallbacks(this.autoRefreshRunnable);
        return;
    L8:
        if (getRefreshInterval() > 0) goto L10;
        return;
    L10:
        if (isAttachedToWindow() == true) goto L12;
        return;
    L12:
        if (isRefreshDue() == false) goto L14;
        onUpdateData(false);
    L14:
        scheduleNextUpdate();
        return;
    }

    public abstract void onUpdateData(boolean r1);

    public void setLoadingTargetView(View r1) {
        this.loadingTargetView = r1;
    }

    public void startLoading() {
        this.loading = true;
        if (this.loadingDrawable != null) goto L5;
        LoadingDrawable r0 = new LoadingDrawable(this.resourcesProvider);
        this.loadingDrawable = r0;
        r0.setCallback(this);
        this.loadingDrawable.setGradientScale(2.0f);
        this.loadingDrawable.setRadiiDp(14.0f);
        updateLoadingColors();
    L5:
        this.loadingDrawable.reset();
        this.loadingDrawable.resetDisappear();
        this.loadingDrawable.setAlpha(255);
        invalidate();
    }

    public void stopLoading() {
        this.loading = false;
        LoadingDrawable r1 = this.loadingDrawable;
        if (r1 == null) goto L6;
        r1.disappear();
        return;
    }

    public abstract void updateColors();

    public void updateLoadingColors() {
        if (this.loadingDrawable == null) goto L6;
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider);
        this.loadingDrawable.setColors(Theme.multAlpha(r0, 0.05f), Theme.multAlpha(r0, 0.15f), Theme.multAlpha(r0, 0.1f), Theme.multAlpha(r0, 0.25f));
        return;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.loadingDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
