package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/* loaded from: classes5.dex */
public class CarouselSnapHelper extends SnapHelper {
    private static final float HORIZONTAL_SNAP_SPEED = 100.0f;
    private static final float VERTICAL_SNAP_SPEED = 50.0f;
    private final boolean disableFling;
    private RecyclerView recyclerView;

    /* renamed from: com.google.android.material.carousel.CarouselSnapHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearSmoothScroller {
        final /* synthetic */ CarouselSnapHelper this$0;
        final /* synthetic */ RecyclerView.LayoutManager val$layoutManager;

        public AnonymousClass1(CarouselSnapHelper r1, Context r2, RecyclerView.LayoutManager r3) {
            this.this$0 = r1;
            this.val$layoutManager = r3;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public float calculateSpeedPerPixel(DisplayMetrics r1) {
            if (this.val$layoutManager.canScrollVertically() == false) goto L7;
            float r0 = r1.densityDpi;
            float r12 = CarouselSnapHelper.VERTICAL_SNAP_SPEED;
        L6:
            return r12 / r0;
        L7:
            r0 = r1.densityDpi;
            r12 = CarouselSnapHelper.HORIZONTAL_SNAP_SPEED;
            goto L6
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public void onTargetFound(View r3, RecyclerView.State r4, RecyclerView.SmoothScroller.Action r5) {
            if (CarouselSnapHelper.access$000(this.this$0) == null) goto L8;
            CarouselSnapHelper r42 = this.this$0;
            int[] r32 = CarouselSnapHelper.access$100(r42, CarouselSnapHelper.access$000(r42).getLayoutManager(), r3, true);
            int r43 = r32[0];
            int r33 = r32[1];
            int r0 = calculateTimeForDeceleration(Math.max(Math.abs(r43), Math.abs(r33)));
            if (r0 <= 0) goto L9;
            r5.update(r43, r33, r0, this.mDecelerateInterpolator);
            return;
        L9:
            return;
        }
    }

    public CarouselSnapHelper() {
        this(true);
    }

    public CarouselSnapHelper(boolean r1) {
        this.disableFling = r1;
    }

    public static /* synthetic */ RecyclerView access$000(CarouselSnapHelper r0) {
        return r0.recyclerView;
    }

    public static /* synthetic */ int[] access$100(CarouselSnapHelper r0, RecyclerView.LayoutManager r1, View r2, boolean r3) {
        return r0.calculateDistanceToSnap(r1, r2, r3);
    }

    private int[] calculateDistanceToSnap(RecyclerView.LayoutManager r3, View r4, boolean r5) {
        if ((r3 instanceof CarouselLayoutManager) == false) goto L5;
        int r2 = distanceToFirstFocalKeyline(r4, (CarouselLayoutManager) r3, r5);
        if (r3.canScrollHorizontally() == false) goto L11;
        return new int[]{r2, 0};
    L11:
        if (r3.canScrollVertically() == false) goto L15;
        return new int[]{0, r2};
    L15:
        return new int[]{0, 0};
    L5:
        return new int[]{0, 0};
    }

    private int distanceToFirstFocalKeyline(View r1, CarouselLayoutManager r2, boolean r3) {
        return r2.getOffsetToScrollToPositionForSnap(r2.getPosition(r1), r3);
    }

    private View findViewNearestFirstKeyline(RecyclerView.LayoutManager r8) {
        int r7 = r8.getChildCount();
        View r0 = null;
        if (r7 != 0) goto L5;
    L13:
        return r0;
    L5:
        if ((r8 instanceof CarouselLayoutManager) == false) goto L13;
        CarouselLayoutManager r1 = (CarouselLayoutManager) r8;
        int r3 = Integer.MAX_VALUE;
        int r4 = 0;
    L8:
        if (r4 >= r7) goto L13;
        View r5 = r8.getChildAt(r4);
        int r6 = Math.abs(r1.getOffsetToScrollToPositionForSnap(r8.getPosition(r5), false));
        if (r6 >= r3) goto L12;
        r0 = r5;
        r3 = r6;
    L12:
        r4 = r4 + 1;
        goto L8
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
    public void attachToRecyclerView(RecyclerView r1) {
        super.attachToRecyclerView(r1);
        this.recyclerView = r1;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager r2, View r3) {
        return calculateDistanceToSnap(r2, r3, false);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager r3) {
        if ((r3 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) == true) goto L5;
        return null;
    L5:
        return new AnonymousClass1(this, this.recyclerView.getContext(), r3);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager r1) {
        return findViewNearestFirstKeyline(r1);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager r12, int r13, int r14) {
        if (this.disableFling == true) goto L5;
        return -1;
    L5:
        int r0 = r12.getItemCount();
        if (r0 != 0) goto L8;
        return -1;
    L8:
        int r2 = r12.getChildCount();
        View r4 = null;
        int r8 = 0;
        int r7 = Integer.MAX_VALUE;
        int r6 = Integer.MIN_VALUE;
        View r5 = null;
    L9:
        if (r8 >= r2) goto L21;
        View r9 = r12.getChildAt(r8);
        if (r9 == null) goto L20;
        int r10 = distanceToFirstFocalKeyline(r9, (CarouselLayoutManager) r12, false);
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
