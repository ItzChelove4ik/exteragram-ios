package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.BuildVars;

/* loaded from: classes4.dex */
public class LinearSmoothScroller extends RecyclerView.SmoothScroller {
    private static final boolean DEBUG = false;
    private static final float MILLISECONDS_PER_INCH = 25.0f;
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    protected static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2f;
    protected static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
    protected final DecelerateInterpolator mDecelerateInterpolator;
    private final DisplayMetrics mDisplayMetrics;
    private boolean mHasCalculatedMillisPerPixel;
    protected int mInterimTargetDx;
    protected int mInterimTargetDy;
    protected final LinearInterpolator mLinearInterpolator;
    private float mMillisPerPixel;
    protected PointF mTargetVector;

    static {
        DEBUG = BuildVars.DEBUG_VERSION;
    }

    public LinearSmoothScroller(Context r2) {
        this.mLinearInterpolator = new LinearInterpolator();
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mHasCalculatedMillisPerPixel = false;
        this.mInterimTargetDx = 0;
        this.mInterimTargetDy = 0;
        this.mDisplayMetrics = r2.getResources().getDisplayMetrics();
    }

    private int clampApplyScroll(int r1, int r2) {
        int r0 = r1 - r2;
        if ((r1 * r0) > 0) goto L6;
        return 0;
    L6:
        return r0;
    }

    private float getSpeedPerPixel() {
        if (this.mHasCalculatedMillisPerPixel == true) goto L6;
        this.mMillisPerPixel = calculateSpeedPerPixel(this.mDisplayMetrics);
        this.mHasCalculatedMillisPerPixel = true;
    L6:
        return this.mMillisPerPixel;
    }

    public int calculateDtToFit(int r1, int r2, int r3, int r4, int r5) {
        if (r5 == (-1)) goto L20;
        if (r5 != 0) goto L6;
        int r32 = r3 - r1;
        if (r32 <= 0) goto L14;
        return r32;
    L14:
        int r42 = r4 - r2;
        if (r42 >= 0) goto L17;
        return r42;
    L17:
        return 0;
    L6:
        if (r5 == 1) goto L8;
        f$$ExternalSyntheticBUOutline1.m("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        return 0;
    L8:
        return r4 - r2;
    L20:
        return r3 - r1;
    }

    public int calculateDxToMakeVisible(View r11, int r12) {
        RecyclerView.LayoutManager r0 = getLayoutManager();
        if (r0 != null) goto L5;
        return 0;
    L5:
        if (r0.canScrollHorizontally() == false) goto L11;
        RecyclerView.LayoutParams r1 = (RecyclerView.LayoutParams) r11.getLayoutParams();
        return calculateDtToFit(r0.getDecoratedLeft(r11) - ((ViewGroup.MarginLayoutParams) r1).leftMargin, r0.getDecoratedRight(r11) + ((ViewGroup.MarginLayoutParams) r1).rightMargin, r0.getPaddingLeft(), r0.getWidth() - r0.getPaddingRight(), r12);
    L11:
        return 0;
    }

    public int calculateDyToMakeVisible(View r11, int r12) {
        RecyclerView.LayoutManager r0 = getLayoutManager();
        if (r0 != null) goto L5;
        return 0;
    L5:
        if (r0.canScrollVertically() == false) goto L11;
        RecyclerView.LayoutParams r1 = (RecyclerView.LayoutParams) r11.getLayoutParams();
        return calculateDtToFit(r0.getDecoratedTop(r11) - ((ViewGroup.MarginLayoutParams) r1).topMargin, r0.getDecoratedBottom(r11) + ((ViewGroup.MarginLayoutParams) r1).bottomMargin, r0.getPaddingTop(), r0.getHeight() - r0.getPaddingBottom(), r12);
    L11:
        return 0;
    }

    public float calculateSpeedPerPixel(DisplayMetrics r1) {
        return MILLISECONDS_PER_INCH / r1.densityDpi;
    }

    public int calculateTimeForDeceleration(int r3) {
        return (int) Math.ceil(calculateTimeForScrolling(r3) / 0.3356d);
    }

    public int calculateTimeForScrolling(int r1) {
        return (int) Math.ceil(Math.abs(r1) * getSpeedPerPixel());
    }

    public int getHorizontalSnapPreference() {
        PointF r2 = this.mTargetVector;
        if (r2 == null) goto L13;
        float r22 = r2.x;
        if (r22 != 0.0f) goto L8;
        return 0;
    L8:
        if (r22 <= 0.0f) goto L11;
        return 1;
    L11:
        return -1;
    L13:
        return 0;
    }

    public int getVerticalSnapPreference() {
        PointF r2 = this.mTargetVector;
        if (r2 == null) goto L13;
        float r22 = r2.y;
        if (r22 != 0.0f) goto L8;
        return 0;
    L8:
        if (r22 <= 0.0f) goto L11;
        return 1;
    L11:
        return -1;
    L13:
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public void onSeekTargetStep(int r3, int r4, RecyclerView.State r5, RecyclerView.SmoothScroller.Action r6) {
        if (getChildCount() != 0) goto L7;
        stop();
        return;
    L7:
        if (DEBUG == false) goto L17;
        PointF r52 = this.mTargetVector;
        if (r52 == null) goto L17;
        if ((r52.x * r3) >= 0.0f) goto L13;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Scroll happened in the opposite direction of the target. Some calculations are wrong");
        return;
    L13:
        if ((r52.y * r4) < 0.0f) goto L15;
    L17:
        this.mInterimTargetDx = clampApplyScroll(this.mInterimTargetDx, r3);
        int r32 = clampApplyScroll(this.mInterimTargetDy, r4);
        this.mInterimTargetDy = r32;
        if (this.mInterimTargetDx != 0) goto L22;
        if (r32 != 0) goto L23;
        updateActionForInterimTarget(r6);
        return;
    L23:
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
        int r42 = calculateDxToMakeVisible(r3, getHorizontalSnapPreference());
        int r32 = calculateDyToMakeVisible(r3, getVerticalSnapPreference());
        int r0 = calculateTimeForDeceleration((int) Math.sqrt((r42 * r42) + (r32 * r32)));
        if (r0 <= 0) goto L6;
        r5.update(-r42, -r32, r0, this.mDecelerateInterpolator);
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
