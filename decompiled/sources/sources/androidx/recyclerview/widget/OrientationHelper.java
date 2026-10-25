package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class OrientationHelper {
    private int mLastTotalSpace;
    protected final RecyclerView.LayoutManager mLayoutManager;
    final Rect mTmpRect;

    /* renamed from: androidx.recyclerview.widget.OrientationHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends OrientationHelper {
        public AnonymousClass1(RecyclerView.LayoutManager r2) {
            super(r2, null);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedEnd(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return this.mLayoutManager.getDecoratedRight(r2) + ((ViewGroup.MarginLayoutParams) r0).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurement(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return (this.mLayoutManager.getDecoratedMeasuredWidth(r2) + ((ViewGroup.MarginLayoutParams) r0).leftMargin) + ((ViewGroup.MarginLayoutParams) r0).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurementInOther(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return (this.mLayoutManager.getDecoratedMeasuredHeight(r2) + ((ViewGroup.MarginLayoutParams) r0).topMargin) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedStart(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return this.mLayoutManager.getDecoratedLeft(r2) - ((ViewGroup.MarginLayoutParams) r0).leftMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEnd() {
            return this.mLayoutManager.getWidth();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndAfterPadding() {
            return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndPadding() {
            return this.mLayoutManager.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getMode() {
            return this.mLayoutManager.getWidthMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getModeInOther() {
            return this.mLayoutManager.getHeightMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getStartAfterPadding() {
            return this.mLayoutManager.getPaddingLeft();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTotalSpace() {
            return (this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft()) - this.mLayoutManager.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedEndWithDecoration(View r4) {
            this.mLayoutManager.getTransformedBoundingBox(r4, true, this.mTmpRect);
            return this.mTmpRect.right;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedStartWithDecoration(View r4) {
            this.mLayoutManager.getTransformedBoundingBox(r4, true, this.mTmpRect);
            return this.mTmpRect.left;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChildren(int r1) {
            this.mLayoutManager.offsetChildrenHorizontal(r1);
        }
    }

    /* renamed from: androidx.recyclerview.widget.OrientationHelper$2, reason: invalid class name */
    public class AnonymousClass2 extends OrientationHelper {
        public AnonymousClass2(RecyclerView.LayoutManager r2) {
            super(r2, null);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedEnd(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return this.mLayoutManager.getDecoratedBottom(r2) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurement(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return (this.mLayoutManager.getDecoratedMeasuredHeight(r2) + ((ViewGroup.MarginLayoutParams) r0).topMargin) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurementInOther(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return (this.mLayoutManager.getDecoratedMeasuredWidth(r2) + ((ViewGroup.MarginLayoutParams) r0).leftMargin) + ((ViewGroup.MarginLayoutParams) r0).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedStart(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return this.mLayoutManager.getDecoratedTop(r2) - ((ViewGroup.MarginLayoutParams) r0).topMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEnd() {
            return this.mLayoutManager.getHeight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndAfterPadding() {
            return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndPadding() {
            return this.mLayoutManager.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getMode() {
            return this.mLayoutManager.getHeightMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getModeInOther() {
            return this.mLayoutManager.getWidthMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getStartAfterPadding() {
            return this.mLayoutManager.getStartAfterPadding();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTotalSpace() {
            return this.mLayoutManager.getTotalSpace();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedEndWithDecoration(View r4) {
            this.mLayoutManager.getTransformedBoundingBox(r4, true, this.mTmpRect);
            return this.mTmpRect.bottom;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedStartWithDecoration(View r4) {
            this.mLayoutManager.getTransformedBoundingBox(r4, true, this.mTmpRect);
            return this.mTmpRect.top;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChildren(int r1) {
            this.mLayoutManager.offsetChildrenVertical(r1);
        }
    }

    private OrientationHelper(RecyclerView.LayoutManager r2) {
        this.mLastTotalSpace = Integer.MIN_VALUE;
        this.mTmpRect = new Rect();
        this.mLayoutManager = r2;
    }

    public /* synthetic */ OrientationHelper(RecyclerView.LayoutManager r1, OrientationHelperIA r2) {
        this(r1);
    }

    public static OrientationHelper createHorizontalHelper(RecyclerView.LayoutManager r1) {
        return new AnonymousClass1(r1);
    }

    public static OrientationHelper createOrientationHelper(RecyclerView.LayoutManager r1, int r2) {
        if (r2 == 0) goto L10;
        if (r2 == 1) goto L6;
        f$$ExternalSyntheticBUOutline1.m("invalid orientation");
        return null;
    L6:
        return createVerticalHelper(r1);
    L10:
        return createHorizontalHelper(r1);
    }

    public static OrientationHelper createVerticalHelper(RecyclerView.LayoutManager r1) {
        return new AnonymousClass2(r1);
    }

    public abstract int getDecoratedEnd(View r1);

    public abstract int getDecoratedMeasurement(View r1);

    public abstract int getDecoratedMeasurementInOther(View r1);

    public abstract int getDecoratedStart(View r1);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public int getTotalSpaceChange() {
        if (Integer.MIN_VALUE != this.mLastTotalSpace) goto L7;
        return 0;
    L7:
        return getTotalSpace() - this.mLastTotalSpace;
    }

    public abstract int getTransformedEndWithDecoration(View r1);

    public abstract int getTransformedStartWithDecoration(View r1);

    public abstract void offsetChildren(int r1);

    public void onLayoutComplete() {
        this.mLastTotalSpace = getTotalSpace();
    }
}
