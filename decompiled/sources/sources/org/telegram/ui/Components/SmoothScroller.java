package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class SmoothScroller extends LinearSmoothScroller {
    private float durationScale;
    private Interpolator interpolator;
    private int offset;

    public SmoothScroller(Context r1) {
        super(r1);
        this.interpolator = CubicBezierInterpolator.DEFAULT;
        this.durationScale = 1.0f;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public int calculateDyToMakeVisible(View r1, int r2) {
        return super.calculateDyToMakeVisible(r1, r2) - this.offset;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public int calculateTimeForDeceleration(int r2) {
        return Math.round(Math.min(super.calculateTimeForDeceleration(r2), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN) * this.durationScale);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public int calculateTimeForScrolling(int r2) {
        return Math.round(Math.min(super.calculateTimeForScrolling(r2), 150) * this.durationScale);
    }

    public void onEnd() {
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public void onTargetFound(View r3, RecyclerView.State r4, RecyclerView.SmoothScroller.Action r5) {
        int r42 = calculateDxToMakeVisible(r3, getHorizontalSnapPreference());
        int r32 = calculateDyToMakeVisible(r3, getVerticalSnapPreference());
        int r0 = calculateTimeForDeceleration((int) Math.sqrt((r42 * r42) + (r32 * r32)));
        if (r0 <= 0) goto L5;
        r5.update(-r42, -r32, r0, this.interpolator);
    L5:
        AndroidUtilities.runOnUIThread(new SmoothScroller$$ExternalSyntheticLambda0(this), Math.max(0, r0));
    }

    public void setDurationScale(float r1) {
        this.durationScale = r1;
    }

    public void setOffset(int r1) {
        this.offset = r1;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
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
        r5.update((int) (this.mInterimTargetDx * 1.2f), (int) (this.mInterimTargetDy * 1.2f), (int) (calculateTimeForScrolling(10000) * 1.2f), this.interpolator);
        return;
    L7:
        if (r0.y != 0.0f) goto L9;
        goto L9
    }
}
