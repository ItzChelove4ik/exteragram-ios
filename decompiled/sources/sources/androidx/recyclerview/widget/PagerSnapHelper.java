package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public class PagerSnapHelper extends SnapHelper {
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    /* renamed from: androidx.recyclerview.widget.PagerSnapHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearSmoothScroller {
        final /* synthetic */ PagerSnapHelper this$0;

        public AnonymousClass1(PagerSnapHelper r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public float calculateSpeedPerPixel(DisplayMetrics r1) {
            return 100.0f / r1.densityDpi;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateTimeForScrolling(int r2) {
            return Math.min(100, super.calculateTimeForScrolling(r2));
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public void onTargetFound(View r3, RecyclerView.State r4, RecyclerView.SmoothScroller.Action r5) {
            PagerSnapHelper r42 = this.this$0;
            int[] r32 = r42.calculateDistanceToFinalSnap(r42.mRecyclerView.getLayoutManager(), r3);
            int r43 = r32[0];
            int r33 = r32[1];
            int r0 = calculateTimeForDeceleration(Math.max(Math.abs(r43), Math.abs(r33)));
            if (r0 <= 0) goto L6;
            r5.update(r43, r33, r0, this.mDecelerateInterpolator);
            return;
        }
    }

    public PagerSnapHelper() {
    }

    private int distanceToCenter(RecyclerView.LayoutManager r1, View r2, OrientationHelper r3) {
        int r0 = r3.getDecoratedStart(r2) + (r3.getDecoratedMeasurement(r2) / 2);
        if (r1.getClipToPadding() == false) goto L5;
        int r12 = r3.getStartAfterPadding() + (r3.getTotalSpace() / 2);
    L7:
        return r0 - r12;
    L5:
        r12 = r3.getEnd() / 2;
        goto L7
    }

    private View findCenterView(RecyclerView.LayoutManager r8, OrientationHelper r9) {
        int r7 = r8.getChildCount();
        View r0 = null;
        if (r7 != 0) goto L6;
        return null;
    L6:
        if (r8.getClipToPadding() == false) goto L8;
        int r1 = r9.getStartAfterPadding() + (r9.getTotalSpace() / 2);
    L9:
        int r2 = Integer.MAX_VALUE;
        int r3 = 0;
    L10:
        if (r3 >= r7) goto L15;
        View r4 = r8.getChildAt(r3);
        int r5 = Math.abs((r9.getDecoratedStart(r4) + (r9.getDecoratedMeasurement(r4) / 2)) - r1);
        if (r5 >= r2) goto L14;
        r0 = r4;
        r2 = r5;
    L14:
        r3 = r3 + 1;
        goto L10
    L15:
        return r0;
    L8:
        r1 = r9.getEnd() / 2;
        goto L9
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager r2) {
        OrientationHelper r0 = this.mHorizontalHelper;
        if (r0 != null) goto L5;
    L6:
        this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(r2);
    L8:
        return this.mHorizontalHelper;
    L5:
        if (r0.mLayoutManager == r2) goto L8;
        goto L6
    }

    private OrientationHelper getOrientationHelper(RecyclerView.LayoutManager r2) {
        if (r2.canScrollVertically() == false) goto L7;
        return getVerticalHelper(r2);
    L7:
        if (r2.canScrollHorizontally() == true) goto L9;
        return null;
    L9:
        return getHorizontalHelper(r2);
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager r2) {
        OrientationHelper r0 = this.mVerticalHelper;
        if (r0 != null) goto L5;
    L6:
        this.mVerticalHelper = OrientationHelper.createVerticalHelper(r2);
    L8:
        return this.mVerticalHelper;
    L5:
        if (r0.mLayoutManager == r2) goto L8;
        goto L6
    }

    private boolean isForwardFling(RecyclerView.LayoutManager r2, int r3, int r4) {
        if (r2.canScrollHorizontally() == false) goto L7;
        if (r3 <= 0) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r4 <= 0) goto L9;
        return true;
    L9:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isReverseLayout(RecyclerView.LayoutManager r4) {
        int r3 = r4.getItemCount();
        if ((r4 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) == false) goto L13;
        PointF r32 = ((RecyclerView.SmoothScroller.ScrollVectorProvider) r4).computeScrollVectorForPosition(r3 - 1);
        if (r32 == null) goto L13;
        if (r32.x >= 0.0f) goto L9;
    L12:
        return true;
    L9:
        if (r32.y < 0.0f) goto L12;
        return false;
    L13:
        return false;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager r5, View r6) {
        int[] r0 = new int[2];
        if (r5.canScrollHorizontally() == false) goto L5;
        r0[0] = distanceToCenter(r5, r6, getHorizontalHelper(r5));
    L7:
        if (r5.canScrollVertically() == false) goto L10;
        r0[1] = distanceToCenter(r5, r6, getVerticalHelper(r5));
        return r0;
    L10:
        r0[1] = 0;
        return r0;
    L5:
        r0[0] = 0;
        goto L7
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager r2) {
        if ((r2 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) == true) goto L7;
        return null;
    L7:
        return new AnonymousClass1(this, this.mRecyclerView.getContext());
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager r2) {
        if (r2.canScrollVertically() == false) goto L7;
        return findCenterView(r2, getVerticalHelper(r2));
    L7:
        if (r2.canScrollHorizontally() == true) goto L9;
        return null;
    L9:
        return findCenterView(r2, getHorizontalHelper(r2));
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager r12, int r13, int r14) {
        int r0 = r12.getItemCount();
        if (r0 != 0) goto L5;
        return -1;
    L5:
        OrientationHelper r2 = getOrientationHelper(r12);
        if (r2 != null) goto L8;
        return -1;
    L8:
        int r3 = r12.getChildCount();
        View r4 = null;
        int r8 = 0;
        int r7 = Integer.MAX_VALUE;
        int r6 = Integer.MIN_VALUE;
        View r5 = null;
    L9:
        if (r8 >= r3) goto L21;
        View r9 = r12.getChildAt(r8);
        if (r9 == null) goto L20;
        int r10 = distanceToCenter(r12, r9, r2);
        if (r10 > 0) goto L17;
        if (r10 <= r6) goto L17;
        r5 = r9;
        r6 = r10;
    L17:
        if (r10 < 0) goto L20;
        if (r10 >= r7) goto L20;
        r4 = r9;
        r7 = r10;
    L20:
        r8 = r8 + 1;
        goto L9
    L21:
        boolean r132 = isForwardFling(r12, r13, r14);
        if (r132 == false) goto L26;
        if (r4 == null) goto L26;
        return r12.getPosition(r4);
    L26:
        if (r132 == true) goto L30;
        if (r5 == null) goto L30;
        return r12.getPosition(r5);
    L30:
        if (r132 == false) goto L32;
        r4 = r5;
    L32:
        if (r4 != null) goto L34;
        return -1;
    L34:
        int r142 = r12.getPosition(r4);
        if (isReverseLayout(r12) != r132) goto L37;
        int r11 = -1;
    L38:
        int r143 = r142 + r11;
        if (r143 < 0) goto L43;
        if (r143 >= r0) goto L43;
        return r143;
    L43:
        return -1;
    L37:
        r11 = 1;
        goto L38
    }
}
