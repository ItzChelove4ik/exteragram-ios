package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    private int overlayTop;
    final Rect tempRect1;
    final Rect tempRect2;
    private int verticalLayoutGap;

    public HeaderScrollingViewBehavior() {
        this.tempRect1 = new Rect();
        this.tempRect2 = new Rect();
        this.verticalLayoutGap = 0;
    }

    public HeaderScrollingViewBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.tempRect1 = new Rect();
        this.tempRect2 = new Rect();
        this.verticalLayoutGap = 0;
    }

    private static int resolveGravity(int r0) {
        if (r0 != 0) goto L5;
        return 8388659;
    L5:
        return r0;
    }

    public abstract View findFirstDependency(List<View> r1);

    public final int getOverlapPixelsForOffset(View r3) {
        if (this.overlayTop != 0) goto L5;
        return 0;
    L5:
        float r32 = getOverlapRatioForOffset(r3);
        int r2 = this.overlayTop;
        return MathUtils.clamp((int) (r32 * r2), 0, r2);
    }

    public float getOverlapRatioForOffset(View r1) {
        return 1.0f;
    }

    public final int getOverlayTop() {
        return this.overlayTop;
    }

    public int getScrollRange(View r1) {
        return r1.getMeasuredHeight();
    }

    public final int getVerticalLayoutGap() {
        return this.verticalLayoutGap;
    }

    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public void layoutChild(CoordinatorLayout r9, View r10, int r11) {
        View r0 = findFirstDependency(r9.getDependencies(r10));
        if (r0 == null) goto L13;
        CoordinatorLayout.LayoutParams r1 = (CoordinatorLayout.LayoutParams) r10.getLayoutParams();
        Rect r5 = this.tempRect1;
        r5.set(r9.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) r1).leftMargin, r0.getBottom() + ((ViewGroup.MarginLayoutParams) r1).topMargin, (r9.getWidth() - r9.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) r1).rightMargin, ((r9.getHeight() + r0.getBottom()) - r9.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) r1).bottomMargin);
        WindowInsetsCompat r2 = r9.getLastWindowInsets();
        if (r2 != null) goto L7;
    L11:
        Rect r6 = this.tempRect2;
        Gravity.apply(resolveGravity(r1.gravity), r10.getMeasuredWidth(), r10.getMeasuredHeight(), r5, r6, r11);
        int r92 = getOverlapPixelsForOffset(r0);
        r10.layout(r6.left, r6.top - r92, r6.right, r6.bottom - r92);
        this.verticalLayoutGap = r6.top - r0.getBottom();
        return;
    L7:
        if (r9.getFitsSystemWindows() == false) goto L11;
        if (r10.getFitsSystemWindows() == true) goto L11;
        r5.left += r2.getSystemWindowInsetLeft();
        r5.right -= r2.getSystemWindowInsetRight();
        goto L11
    L13:
        super.layoutChild(r9, r10, r11);
        this.verticalLayoutGap = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout r7, View r8, int r9, int r10, int r11, int r12) {
        int r0 = r8.getLayoutParams().height;
        if (r0 != (-1)) goto L5;
    L6:
        View r2 = findFirstDependency(r7.getDependencies(r8));
        if (r2 == null) goto L27;
        int r112 = View.MeasureSpec.getSize(r11);
        if (r112 > 0) goto L11;
        r112 = r7.getHeight();
    L16:
        int r113 = r112 + getScrollRange(r2);
        int r22 = r2.getMeasuredHeight();
        if (shouldHeaderOverlapScrollingChild() == false) goto L19;
        r8.setTranslationY(-r22);
    L20:
        if (r0 != (-1)) goto L22;
        int r6 = TLObject.FLAG_30;
    L23:
        r7.onMeasureChild(r8, r9, r10, View.MeasureSpec.makeMeasureSpec(r113, r6), r12);
        return true;
    L22:
        r6 = Integer.MIN_VALUE;
        goto L23
    L19:
        r8.setTranslationY(0.0f);
        r113 = r113 - r22;
        goto L20
    L11:
        if (r2.getFitsSystemWindows() == false) goto L16;
        WindowInsetsCompat r3 = r7.getLastWindowInsets();
        if (r3 == null) goto L16;
        r112 = r112 + (r3.getSystemWindowInsetTop() + r3.getSystemWindowInsetBottom());
        goto L16
    L27:
        return false;
    L5:
        if (r0 == (-2)) goto L6;
        return false;
    }

    public final void setOverlayTop(int r1) {
        this.overlayTop = r1;
    }

    public boolean shouldHeaderOverlapScrollingChild() {
        return false;
    }
}
