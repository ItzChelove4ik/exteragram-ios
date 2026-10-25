package org.telegram.ui.recyclerview;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class LinearSmoothScrollerCustom extends RecyclerView.SmoothScroller {
    private final float MILLISECONDS_PER_PX;
    private float durationMultiplier;
    protected final DecelerateInterpolator mDecelerateInterpolator;
    protected int mInterimTargetDx;
    protected int mInterimTargetDy;
    protected final LinearInterpolator mLinearInterpolator;
    protected PointF mTargetVector;
    private int offset;
    private int scrollPosition;

    public LinearSmoothScrollerCustom(Context r3, int r4) {
        this.mLinearInterpolator = new LinearInterpolator();
        this.mDecelerateInterpolator = new DecelerateInterpolator(1.5f);
        this.mInterimTargetDx = 0;
        this.mInterimTargetDy = 0;
        this.durationMultiplier = 1.0f;
        this.MILLISECONDS_PER_PX = 25.0f / r3.getResources().getDisplayMetrics().densityDpi;
        this.scrollPosition = r4;
    }

    public LinearSmoothScrollerCustom(Context r3, int r4, float r5) {
        this.mLinearInterpolator = new LinearInterpolator();
        this.mDecelerateInterpolator = new DecelerateInterpolator(1.5f);
        this.mInterimTargetDx = 0;
        this.mInterimTargetDy = 0;
        this.durationMultiplier = r5;
        this.MILLISECONDS_PER_PX = (25.0f / r3.getResources().getDisplayMetrics().densityDpi) * r5;
        this.scrollPosition = r4;
    }

    private int clampApplyScroll(int r1, int r2) {
        int r0 = r1 - r2;
        if ((r1 * r0) > 0) goto L6;
        return 0;
    L6:
        return r0;
    }

    public int calculateDyToMakeVisible(View r8) {
        RecyclerView.LayoutManager r0 = getLayoutManager();
        if (r0 != null) goto L5;
    L21:
        return 0;
    L5:
        if (r0.canScrollVertically() == false) goto L21;
        RecyclerView.LayoutParams r2 = (RecyclerView.LayoutParams) r8.getLayoutParams();
        int r3 = r0.getDecoratedTop(r8) - ((ViewGroup.MarginLayoutParams) r2).topMargin;
        int r82 = r0.getDecoratedBottom(r8) + ((ViewGroup.MarginLayoutParams) r2).bottomMargin;
        int r4 = (r0.getHeight() - r0.getPaddingBottom()) - r0.getPaddingTop();
        int r22 = r82 - r3;
        int r5 = this.scrollPosition;
        if (r5 != 2) goto L10;
        int r02 = r0.getPaddingTop() + this.offset;
    L15:
        int r23 = r22 + r02;
        int r03 = r02 - r3;
        if (r03 <= 0) goto L18;
        return r03;
    L18:
        int r24 = r23 - r82;
        if (r24 >= 0) goto L21;
        return r24;
    L10:
        if (r22 <= r4) goto L12;
        r02 = 0;
        goto L15
    L12:
        if (r5 != 0) goto L14;
        r02 = (r4 - r22) / 2;
        goto L15
    L14:
        r02 = (r0.getPaddingTop() + this.offset) - AndroidUtilities.dp(88.0f);
        goto L15
    }

    public int calculateTimeForDeceleration(int r3) {
        return (int) Math.ceil(calculateTimeForScrolling(r3) / 0.3356d);
    }

    public int calculateTimeForScrolling(int r1) {
        return (int) Math.ceil(Math.abs(r1) * this.MILLISECONDS_PER_PX);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public PointF computeScrollVectorForPosition(int r2) {
        Object r1 = getLayoutManager();
        if ((r1 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) == true) goto L5;
        return null;
    L5:
        return ((RecyclerView.SmoothScroller.ScrollVectorProvider) r1).computeScrollVectorForPosition(r2);
    }

    public void onEnd() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public void onSeekTargetStep(int r1, int r2, RecyclerView.State r3, RecyclerView.SmoothScroller.Action r4) {
        if (getChildCount() != 0) goto L6;
        stop();
        return;
    L6:
        this.mInterimTargetDx = clampApplyScroll(this.mInterimTargetDx, r1);
        int r12 = clampApplyScroll(this.mInterimTargetDy, r2);
        this.mInterimTargetDy = r12;
        if (this.mInterimTargetDx != 0) goto L11;
        if (r12 != 0) goto L12;
        updateActionForInterimTarget(r4);
        return;
    L12:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public void onStart() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public void onTargetFound(View r3, RecyclerView.State r4, RecyclerView.SmoothScroller.Action r5) {
        int r32 = calculateDyToMakeVisible(r3);
        int r42 = calculateTimeForDeceleration(r32);
        if (r42 <= 0) goto L6;
        r5.update(0, -r32, Math.max((int) (this.durationMultiplier * 400.0f), r42), this.mDecelerateInterpolator);
        return;
    L6:
        onEnd();
    }

    public void setOffset(int r1) {
        this.offset = r1;
    }

    public void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action r5) {
        PointF r0 = computeScrollVectorForPosition(getTargetPosition());
        if (r0 != null) goto L5;
    L11:
        r5.jumpTo(getTargetPosition());
        stop();
        return;
    L5:
        if (r0.x == 0.0f) goto L7;
    L9:
        normalize(r0);
        this.mTargetVector = r0;
        this.mInterimTargetDx = (int) (r0.x * 10000.0f);
        this.mInterimTargetDy = (int) (r0.y * 10000.0f);
        r5.update((int) (this.mInterimTargetDx * 1.2f), (int) (this.mInterimTargetDy * 1.2f), (int) (calculateTimeForScrolling(10000) * 1.2f), this.mLinearInterpolator);
        return;
    L7:
        if (r0.y != 0.0f) goto L9;
        goto L9
    }
}
