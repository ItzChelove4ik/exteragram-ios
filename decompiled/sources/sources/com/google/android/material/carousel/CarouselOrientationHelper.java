package com.google.android.material.carousel;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
abstract class CarouselOrientationHelper {
    final int orientation;

    /* renamed from: com.google.android.material.carousel.CarouselOrientationHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends CarouselOrientationHelper {
        final /* synthetic */ CarouselLayoutManager val$carouselLayoutManager;

        public AnonymousClass1(int r1, CarouselLayoutManager r2) {
            this.val$carouselLayoutManager = r2;
            super(r1, null);
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void containMaskWithinBounds(RectF r3, RectF r4, RectF r5) {
            float r2 = r4.top;
            float r0 = r5.top;
            if (r2 < r0) goto L5;
        L7:
            float r22 = r4.bottom;
            float r52 = r5.bottom;
            if (r22 > r52) goto L10;
            return;
        L10:
            if (r4.top >= r52) goto L14;
            float r23 = r22 - r52;
            r3.bottom = Math.max(r3.bottom - r23, r3.top);
            r4.bottom = Math.max(r4.bottom - r23, r4.top);
            return;
        L14:
            return;
        L5:
            if (r4.bottom <= r0) goto L7;
            float r02 = r0 - r2;
            r3.top += r02;
            r5.top += r02;
            goto L7
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getDecoratedCrossAxisMeasurement(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return (this.val$carouselLayoutManager.getDecoratedMeasuredWidth(r2) + ((ViewGroup.MarginLayoutParams) r0).leftMargin) + ((ViewGroup.MarginLayoutParams) r0).rightMargin;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public float getMaskMargins(RecyclerView.LayoutParams r1) {
            return ((ViewGroup.MarginLayoutParams) r1).topMargin + ((ViewGroup.MarginLayoutParams) r1).bottomMargin;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public RectF getMaskRect(float r1, float r2, float r3, float r4) {
            return new RectF(0.0f, r3, r2, r1 - r3);
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentBottom() {
            return this.val$carouselLayoutManager.getHeight();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentEnd() {
            return getParentBottom();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentLeft() {
            return this.val$carouselLayoutManager.getPaddingLeft();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentRight() {
            return this.val$carouselLayoutManager.getWidth() - this.val$carouselLayoutManager.getPaddingRight();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentStart() {
            return getParentTop();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentTop() {
            return 0;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void layoutDecoratedWithMargins(View r7, int r8, int r9) {
            int r2 = getParentLeft();
            int r4 = r2 + getDecoratedCrossAxisMeasurement(r7);
            this.val$carouselLayoutManager.layoutDecoratedWithMargins(r7, r2, r8, r4, r9);
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void moveMaskOnEdgeOutsideBounds(RectF r4, RectF r5, RectF r6) {
            if (r5.bottom > r6.top) goto L6;
            float r3 = ((float) Math.floor(r4.bottom)) - 1.0f;
            r4.bottom = r3;
            r4.top = Math.min(r4.top, r3);
        L6:
            if (r5.top < r6.bottom) goto L9;
            float r32 = ((float) Math.ceil(r4.top)) + 1.0f;
            r4.top = r32;
            r4.bottom = Math.max(r32, r4.bottom);
            return;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void offsetChild(View r1, Rect r2, float r3, float r4) {
            r1.offsetTopAndBottom((int) (r4 - (r2.top + r3)));
        }
    }

    /* renamed from: com.google.android.material.carousel.CarouselOrientationHelper$2, reason: invalid class name */
    public class AnonymousClass2 extends CarouselOrientationHelper {
        final /* synthetic */ CarouselLayoutManager val$carouselLayoutManager;

        public AnonymousClass2(int r1, CarouselLayoutManager r2) {
            this.val$carouselLayoutManager = r2;
            super(r1, null);
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void containMaskWithinBounds(RectF r3, RectF r4, RectF r5) {
            float r2 = r4.left;
            float r0 = r5.left;
            if (r2 < r0) goto L5;
        L7:
            float r22 = r4.right;
            float r52 = r5.right;
            if (r22 > r52) goto L10;
            return;
        L10:
            if (r4.left >= r52) goto L14;
            float r23 = r22 - r52;
            r3.right = Math.max(r3.right - r23, r3.left);
            r4.right = Math.max(r4.right - r23, r4.left);
            return;
        L14:
            return;
        L5:
            if (r4.right <= r0) goto L7;
            float r02 = r0 - r2;
            r3.left += r02;
            r4.left += r02;
            goto L7
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getDecoratedCrossAxisMeasurement(View r2) {
            RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r2.getLayoutParams();
            return (this.val$carouselLayoutManager.getDecoratedMeasuredHeight(r2) + ((ViewGroup.MarginLayoutParams) r0).topMargin) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public float getMaskMargins(RecyclerView.LayoutParams r1) {
            return ((ViewGroup.MarginLayoutParams) r1).rightMargin + ((ViewGroup.MarginLayoutParams) r1).leftMargin;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public RectF getMaskRect(float r1, float r2, float r3, float r4) {
            return new RectF(r4, 0.0f, r2 - r4, r1);
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentBottom() {
            return this.val$carouselLayoutManager.getHeight() - this.val$carouselLayoutManager.getPaddingBottom();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentEnd() {
            if (this.val$carouselLayoutManager.isLayoutRtl() == false) goto L7;
            return getParentLeft();
        L7:
            return getParentRight();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentLeft() {
            return 0;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentRight() {
            return this.val$carouselLayoutManager.getWidth();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentStart() {
            if (this.val$carouselLayoutManager.isLayoutRtl() == false) goto L7;
            return getParentRight();
        L7:
            return getParentLeft();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public int getParentTop() {
            return this.val$carouselLayoutManager.getPaddingTop();
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void layoutDecoratedWithMargins(View r7, int r8, int r9) {
            int r3 = getParentTop();
            int r5 = r3 + getDecoratedCrossAxisMeasurement(r7);
            this.val$carouselLayoutManager.layoutDecoratedWithMargins(r7, r8, r3, r9, r5);
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void moveMaskOnEdgeOutsideBounds(RectF r4, RectF r5, RectF r6) {
            if (r5.right > r6.left) goto L6;
            float r3 = ((float) Math.floor(r4.right)) - 1.0f;
            r4.right = r3;
            r4.left = Math.min(r4.left, r3);
        L6:
            if (r5.left < r6.right) goto L9;
            float r32 = ((float) Math.ceil(r4.left)) + 1.0f;
            r4.left = r32;
            r4.right = Math.max(r32, r4.right);
            return;
        }

        @Override // com.google.android.material.carousel.CarouselOrientationHelper
        public void offsetChild(View r1, Rect r2, float r3, float r4) {
            r1.offsetLeftAndRight((int) (r4 - (r2.left + r3)));
        }
    }

    private CarouselOrientationHelper(int r1) {
        this.orientation = r1;
    }

    public /* synthetic */ CarouselOrientationHelper(int r1, AnonymousClass1 r2) {
        this(r1);
    }

    private static CarouselOrientationHelper createHorizontalHelper(CarouselLayoutManager r2) {
        return new AnonymousClass2(0, r2);
    }

    public static CarouselOrientationHelper createOrientationHelper(CarouselLayoutManager r1, int r2) {
        if (r2 == 0) goto L10;
        if (r2 == 1) goto L6;
        f$$ExternalSyntheticBUOutline1.m("invalid orientation");
        return null;
    L6:
        return createVerticalHelper(r1);
    L10:
        return createHorizontalHelper(r1);
    }

    private static CarouselOrientationHelper createVerticalHelper(CarouselLayoutManager r2) {
        return new AnonymousClass1(1, r2);
    }

    public abstract void containMaskWithinBounds(RectF r1, RectF r2, RectF r3);

    public abstract int getDecoratedCrossAxisMeasurement(View r1);

    public abstract float getMaskMargins(RecyclerView.LayoutParams r1);

    public abstract RectF getMaskRect(float r1, float r2, float r3, float r4);

    public abstract int getParentBottom();

    public abstract int getParentEnd();

    public abstract int getParentLeft();

    public abstract int getParentRight();

    public abstract int getParentStart();

    public abstract int getParentTop();

    public abstract void layoutDecoratedWithMargins(View r1, int r2, int r3);

    public abstract void moveMaskOnEdgeOutsideBounds(RectF r1, RectF r2, RectF r3);

    public abstract void offsetChild(View r1, Rect r2, float r3, float r4);
}
