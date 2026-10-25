package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

/* loaded from: classes5.dex */
public class NavigationRailMenuView extends NavigationBarMenuView {
    private int itemMinimumHeight;
    private int itemSpacing;
    private final FrameLayout.LayoutParams layoutParams;

    public NavigationRailMenuView(Context r3) {
        super(r3);
        this.itemMinimumHeight = -1;
        this.itemSpacing = 0;
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(-1, -2);
        this.layoutParams = r0;
        r0.gravity = 49;
        setLayoutParams(r0);
        setItemActiveIndicatorResizeable(true);
    }

    private int makeSharedHeightSpec(int r2, int r3, int r4) {
        int r32 = r3 / Math.max(1, r4);
        int r1 = this.itemMinimumHeight;
        if (r1 != (-1)) goto L7;
        r1 = View.MeasureSpec.getSize(r2);
    L7:
        return View.MeasureSpec.makeMeasureSpec(Math.min(r1, r32), 0);
    }

    private int measureChildHeight(View r1, int r2, int r3) {
        r1.measure(r2, r3);
        if (r1.getVisibility() != 8) goto L5;
        return 0;
    L5:
        return r1.getMeasuredHeight();
    }

    private int measureSharedChildHeights(int r8, int r9, int r10, View r11) {
        int r1 = View.MeasureSpec.makeMeasureSpec(r9, 0);
        int r2 = getChildCount();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r2) goto L8;
        View r5 = getChildAt(r3);
        if ((r5 instanceof NavigationBarItemView) == true) goto L7;
        int r52 = measureChildHeight(r5, r8, r1);
        r9 = r9 - r52;
        r4 = r4 + r52;
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        int r92 = Math.max(r9, 0);
        if (r11 != null) goto L11;
        int r93 = makeSharedHeightSpec(r8, r92, r10);
    L12:
        int r102 = 0;
        int r12 = 0;
    L13:
        if (r102 >= r2) goto L23;
        View r32 = getChildAt(r102);
        if (r32.getVisibility() != 0) goto L18;
        r12 = r12 + 1;
    L18:
        if ((r32 instanceof NavigationBarItemView) == false) goto L21;
        if (r32 == r11) goto L21;
        r4 = r4 + measureChildHeight(r32, r8, r93);
    L21:
        r102 = r102 + 1;
        goto L13
    L23:
        return r4 + (Math.max(0, r12 - 1) * this.itemSpacing);
    L11:
        r93 = View.MeasureSpec.makeMeasureSpec(r11.getMeasuredHeight(), 0);
        goto L12
    }

    private int measureShiftingChildHeights(int r3, int r4, int r5) {
        View r0 = getChildAt(getSelectedItemPosition());
        if (r0 == null) goto L5;
        int r1 = measureChildHeight(r0, r3, makeSharedHeightSpec(r3, r4, r5));
        r4 = r4 - r1;
        r5 = r5 - 1;
    L7:
        return r1 + measureSharedChildHeights(r3, r4, r5, r0);
    L5:
        r1 = 0;
        goto L7
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public NavigationBarItemView createNavigationBarItemView(Context r1) {
        return new NavigationRailItemView(r1);
    }

    public int getItemMinimumHeight() {
        return this.itemMinimumHeight;
    }

    public int getItemSpacing() {
        return this.itemSpacing;
    }

    public int getMenuGravity() {
        return this.layoutParams.gravity;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        int r62 = getChildCount();
        int r92 = r9 - r7;
        int r82 = 0;
        int r102 = 0;
        int r0 = 0;
    L4:
        if (r82 >= r62) goto L10;
        View r2 = getChildAt(r82);
        if (r2.getVisibility() == 8) goto L8;
        r0 = r0 + r2.getMeasuredHeight();
        r102 = r102 + 1;
    L8:
        r82 = r82 + 1;
        goto L4
    L10:
        if (r102 > 1) goto L12;
        int r83 = 0;
    L13:
        int r103 = 0;
        int r02 = 0;
    L14:
        if (r103 >= r62) goto L19;
        View r22 = getChildAt(r103);
        if (r22.getVisibility() == 8) goto L18;
        int r3 = r22.getMeasuredHeight();
        r22.layout(0, r02, r92, r3 + r02);
        r02 = r02 + (r3 + r83);
    L18:
        r103 = r103 + 1;
        goto L14
    L19:
        return;
    L12:
        r83 = Math.max(0, Math.min((getMeasuredHeight() - r0) / (r102 - 1), this.itemSpacing));
        goto L13
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        int r0 = View.MeasureSpec.getSize(r5);
        int r1 = getCurrentVisibleContentItemCount();
        if (r1 > 1) goto L5;
    L7:
        int r02 = measureSharedChildHeights(r4, r0, r1, null);
    L8:
        setMeasuredDimension(View.MeasureSpec.getSize(r4), View.resolveSizeAndState(r02, r5, 0));
        return;
    L5:
        if (isShifting(getLabelVisibilityMode(), r1) == false) goto L7;
        r02 = measureShiftingChildHeights(r4, r0, r1);
        goto L8
    }

    public void setItemMinimumHeight(int r2) {
        if (this.itemMinimumHeight == r2) goto L6;
        this.itemMinimumHeight = r2;
        requestLayout();
        return;
    }

    public void setItemSpacing(int r2) {
        if (this.itemSpacing == r2) goto L6;
        this.itemSpacing = r2;
        requestLayout();
        return;
    }

    public void setMenuGravity(int r3) {
        FrameLayout.LayoutParams r0 = this.layoutParams;
        if (r0.gravity == r3) goto L6;
        r0.gravity = r3;
        setLayoutParams(r0);
        return;
    }
}
