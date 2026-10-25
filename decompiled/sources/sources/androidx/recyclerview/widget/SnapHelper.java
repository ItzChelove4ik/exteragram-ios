package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class SnapHelper extends RecyclerView.OnFlingListener {
    static final float MILLISECONDS_PER_INCH = 100.0f;
    private Scroller mGravityScroller;
    RecyclerView mRecyclerView;
    private final RecyclerView.OnScrollListener mScrollListener;

    /* renamed from: androidx.recyclerview.widget.SnapHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        boolean mScrolled;
        final /* synthetic */ SnapHelper this$0;

        public AnonymousClass1(SnapHelper r1) {
            this.this$0 = r1;
            this.mScrolled = false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            super.onScrollStateChanged(r1, r2);
            if (r2 == 0) goto L5;
            return;
        L5:
            if (this.mScrolled == false) goto L9;
            this.mScrolled = false;
            this.this$0.snapToTargetExistingView();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (r2 != 0) goto L6;
            if (r3 != 0) goto L6;
            return;
        L6:
            this.mScrolled = true;
        }
    }

    /* renamed from: androidx.recyclerview.widget.SnapHelper$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearSmoothScroller {
        final /* synthetic */ SnapHelper this$0;

        public AnonymousClass2(SnapHelper r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public float calculateSpeedPerPixel(DisplayMetrics r1) {
            return SnapHelper.MILLISECONDS_PER_INCH / r1.densityDpi;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public void onTargetFound(View r3, RecyclerView.State r4, RecyclerView.SmoothScroller.Action r5) {
            SnapHelper r42 = this.this$0;
            RecyclerView r0 = r42.mRecyclerView;
            if (r0 == null) goto L10;
            int[] r32 = r42.calculateDistanceToFinalSnap(r0.getLayoutManager(), r3);
            int r43 = r32[0];
            int r33 = r32[1];
            int r02 = calculateTimeForDeceleration(Math.max(Math.abs(r43), Math.abs(r33)));
            if (r02 <= 0) goto L9;
            r5.update(r43, r33, r02, this.mDecelerateInterpolator);
            return;
        L9:
            return;
        }
    }

    public SnapHelper() {
        this.mScrollListener = new AnonymousClass1(this);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(null);
    }

    private void setupCallbacks() {
        if (this.mRecyclerView.getOnFlingListener() != null) goto L6;
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(this);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("An instance of OnFlingListener already set.");
    }

    private boolean snapFromFling(RecyclerView.LayoutManager r3, int r4, int r5) {
        if ((r3 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) == true) goto L5;
        return false;
    L5:
        RecyclerView.SmoothScroller r0 = createScroller(r3);
        if (r0 != null) goto L8;
        return false;
    L8:
        int r2 = findTargetSnapPosition(r3, r4, r5);
        if (r2 != (-1)) goto L11;
        return false;
    L11:
        r0.setTargetPosition(r2);
        r3.startSmoothScroll(r0);
        return true;
    }

    public void attachToRecyclerView(RecyclerView r3) {
        RecyclerView r0 = this.mRecyclerView;
        if (r0 == r3) goto L12;
        if (r0 == null) goto L7;
        destroyCallbacks();
    L7:
        this.mRecyclerView = r3;
        if (r3 == null) goto L11;
        setupCallbacks();
        this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
        snapToTargetExistingView();
        return;
    L11:
        return;
    }

    public abstract int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager r1, View r2);

    public int[] calculateScrollDistance(int r10, int r11) {
        this.mGravityScroller.fling(0, 0, r10, r11, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.mGravityScroller.getFinalX(), this.mGravityScroller.getFinalY()};
    }

    public RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager r1) {
        return createSnapScroller(r1);
    }

    @Deprecated
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager r2) {
        if ((r2 instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) == true) goto L7;
        return null;
    L7:
        return new AnonymousClass2(this, this.mRecyclerView.getContext());
    }

    public abstract View findSnapView(RecyclerView.LayoutManager r1);

    public abstract int findTargetSnapPosition(RecyclerView.LayoutManager r1, int r2, int r3);

    @Override // androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean onFling(int r5, int r6) {
        RecyclerView.LayoutManager r0 = this.mRecyclerView.getLayoutManager();
        if (r0 != null) goto L6;
        return false;
    L6:
        if (this.mRecyclerView.getAdapter() != null) goto L8;
        return false;
    L8:
        int r2 = this.mRecyclerView.getMinFlingVelocity();
        if (Math.abs(r6) > r2) goto L13;
        if (Math.abs(r5) > r2) goto L13;
    L16:
        return false;
    L13:
        if (snapFromFling(r0, r5, r6) == false) goto L16;
        return true;
    }

    public void snapToTargetExistingView() {
        RecyclerView r0 = this.mRecyclerView;
        if (r0 == null) goto L19;
        RecyclerView.LayoutManager r02 = r0.getLayoutManager();
        if (r02 == null) goto L20;
        View r1 = findSnapView(r02);
        if (r1 == null) goto L21;
        int[] r03 = calculateDistanceToFinalSnap(r02, r1);
        int r12 = r03[0];
        if (r12 == 0) goto L14;
    L17:
        this.mRecyclerView.smoothScrollBy(r12, r03[1]);
        return;
    L14:
        if (r03[1] != 0) goto L17;
        return;
    L21:
        return;
    L20:
        return;
    }
}
