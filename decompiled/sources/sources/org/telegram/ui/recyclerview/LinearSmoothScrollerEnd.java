package org.telegram.ui.recyclerview;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes7.dex */
public abstract class LinearSmoothScrollerEnd extends RecyclerView.SmoothScroller {
    private final float MILLISECONDS_PER_PX;
    protected final DecelerateInterpolator mDecelerateInterpolator;
    protected int mInterimTargetDx;
    protected int mInterimTargetDy;
    protected final LinearInterpolator mLinearInterpolator;
    protected PointF mTargetVector;

    public LinearSmoothScrollerEnd(Context r3) {
        this.mLinearInterpolator = new LinearInterpolator();
        this.mDecelerateInterpolator = new DecelerateInterpolator(1.5f);
        this.mInterimTargetDx = 0;
        this.mInterimTargetDy = 0;
        this.MILLISECONDS_PER_PX = 25.0f / r3.getResources().getDisplayMetrics().densityDpi;
    }

    private int clampApplyScroll(int r1, int r2) {
        int r0 = r1 - r2;
        if ((r1 * r0) > 0) goto L6;
        return 0;
    L6:
        return r0;
    }

    public int calculateDxToMakeVisible(View r5) {
        RecyclerView.LayoutManager r4 = getLayoutManager();
        if (r4 != null) goto L5;
    L17:
        return 0;
    L5:
        if (r4.canScrollHorizontally() == false) goto L17;
        RecyclerView.LayoutParams r1 = (RecyclerView.LayoutParams) r5.getLayoutParams();
        int r2 = r4.getDecoratedLeft(r5) - ((ViewGroup.MarginLayoutParams) r1).leftMargin;
        int r52 = r4.getDecoratedRight(r5) + ((ViewGroup.MarginLayoutParams) r1).rightMargin;
        int r12 = r4.getPaddingLeft();
        int r3 = r4.getWidth() - r4.getPaddingRight();
        if (r2 <= r12) goto L11;
        if (r52 >= r3) goto L11;
        return 0;
    L11:
        int r42 = r52 - r2;
        int r32 = (r3 - r12) - r42;
        int r43 = r42 + r32;
        int r33 = r32 - r2;
        if (r33 <= 0) goto L14;
        return r33;
    L14:
        int r44 = r43 - r52;
        if (r44 >= 0) goto L17;
        return r44;
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
    public void onTargetFound(View r2, RecyclerView.State r3, RecyclerView.SmoothScroller.Action r4) {
        int r22 = calculateDxToMakeVisible(r2);
        int r32 = calculateTimeForDeceleration(r22);
        if (r32 <= 0) goto L6;
        r4.update(-r22, 0, Math.max(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, r32), this.mDecelerateInterpolator);
        return;
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
