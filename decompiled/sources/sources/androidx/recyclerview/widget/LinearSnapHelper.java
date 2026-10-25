package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public class LinearSnapHelper extends SnapHelper {
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    public LinearSnapHelper() {
    }

    private float computeDistancePerChild(RecyclerView.LayoutManager r10, OrientationHelper r11) {
        int r9 = r10.getChildCount();
        if (r9 != 0) goto L5;
        return 1.0f;
    L5:
        View r1 = null;
        int r5 = 0;
        int r4 = Integer.MIN_VALUE;
        int r3 = Integer.MAX_VALUE;
        View r2 = null;
    L6:
        if (r5 >= r9) goto L15;
        View r6 = r10.getChildAt(r5);
        int r7 = r10.getPosition(r6);
        if (r7 == (-1)) goto L14;
        if (r7 >= r3) goto L12;
        r1 = r6;
        r3 = r7;
    L12:
        if (r7 <= r4) goto L14;
        r2 = r6;
        r4 = r7;
    L14:
        r5 = r5 + 1;
        goto L6
    L15:
        if (r1 == null) goto L23;
        if (r2 == null) goto L23;
        int r102 = Math.max(r11.getDecoratedEnd(r1), r11.getDecoratedEnd(r2)) - Math.min(r11.getDecoratedStart(r1), r11.getDecoratedStart(r2));
        if (r102 != 0) goto L22;
        return 1.0f;
    L22:
        return (r102 * 1.0f) / ((r4 - r3) + 1);
    L23:
        return 1.0f;
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

    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager r2, OrientationHelper r3, int r4, int r5) {
        int[] r42 = calculateScrollDistance(r4, r5);
        float r1 = computeDistancePerChild(r2, r3);
        if (r1 > 0.0f) goto L6;
        return 0;
    L6:
        if (Math.abs(r42[0]) <= Math.abs(r42[1])) goto L8;
        int r22 = r42[0];
    L10:
        return Math.round(r22 / r1);
    L8:
        r22 = r42[1];
        goto L10
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
    public View findSnapView(RecyclerView.LayoutManager r2) {
        if (r2.canScrollVertically() == false) goto L7;
        return findCenterView(r2, getVerticalHelper(r2));
    L7:
        if (r2.canScrollHorizontally() == true) goto L9;
        return null;
    L9:
        return findCenterView(r2, getHorizontalHelper(r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager r9, int r10, int r11) {
        if ((r9 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) == true) goto L5;
        return -1;
    L5:
        int r0 = r9.getItemCount();
        if (r0 != 0) goto L8;
        return -1;
    L8:
        View r2 = findSnapView(r9);
        if (r2 != null) goto L11;
        return -1;
    L11:
        int r22 = r9.getPosition(r2);
        if (r22 != (-1)) goto L14;
        return -1;
    L14:
        int r4 = r0 - 1;
        PointF r3 = ((RecyclerView.SmoothScroller.ScrollVectorProvider) r9).computeScrollVectorForPosition(r4);
        if (r3 != null) goto L17;
        return -1;
    L17:
        int r7 = 0;
        if (r9.canScrollHorizontally() == false) goto L22;
        int r102 = estimateNextPositionDiffForFling(r9, getHorizontalHelper(r9), r10, 0);
        if (r3.x >= 0.0f) goto L24;
        r102 = -r102;
    L24:
        if (r9.canScrollVertically() == false) goto L28;
        int r8 = estimateNextPositionDiffForFling(r9, getVerticalHelper(r9), 0, r11);
        if (r3.y >= 0.0f) goto L30;
        r8 = -r8;
    L30:
        if (r9.canScrollVertically() == false) goto L32;
        r102 = r8;
    L32:
        if (r102 != 0) goto L34;
        return -1;
    L34:
        int r23 = r22 + r102;
        if (r23 < 0) goto L38;
        r7 = r23;
    L38:
        if (r7 < r0) goto L40;
        return r4;
    L40:
        return r7;
    L28:
        r8 = 0;
        goto L30
    L22:
        r102 = 0;
        goto L24
    }
}
