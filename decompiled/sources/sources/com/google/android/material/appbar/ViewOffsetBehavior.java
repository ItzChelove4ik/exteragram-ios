package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes5.dex */
class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private int tempLeftRightOffset;
    private int tempTopBottomOffset;
    private ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }

    public ViewOffsetBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }

    public int getLeftAndRightOffset() {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getLeftAndRightOffset();
    }

    public int getTopAndBottomOffset() {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getTopAndBottomOffset();
    }

    public boolean isHorizontalOffsetEnabled() {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isHorizontalOffsetEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isVerticalOffsetEnabled() {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isVerticalOffsetEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void layoutChild(CoordinatorLayout r1, V r2, int r3) {
        r1.onLayoutChild(r2, r3);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout r1, V r2, int r3) {
        layoutChild(r1, r2, r3);
        if (this.viewOffsetHelper != null) goto L5;
        this.viewOffsetHelper = new ViewOffsetHelper(r2);
    L5:
        this.viewOffsetHelper.onViewLayout();
        this.viewOffsetHelper.applyOffsets();
        int r12 = this.tempTopBottomOffset;
        if (r12 == 0) goto L8;
        this.viewOffsetHelper.setTopAndBottomOffset(r12);
        this.tempTopBottomOffset = 0;
    L8:
        int r13 = this.tempLeftRightOffset;
        if (r13 == 0) goto L13;
        this.viewOffsetHelper.setLeftAndRightOffset(r13);
        this.tempLeftRightOffset = 0;
        return true;
    L13:
        return true;
    }

    public void setHorizontalOffsetEnabled(boolean r1) {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 == null) goto L6;
        r0.setHorizontalOffsetEnabled(r1);
        return;
    }

    public boolean setLeftAndRightOffset(int r2) {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 != null) goto L5;
        this.tempLeftRightOffset = r2;
        return false;
    L5:
        return r0.setLeftAndRightOffset(r2);
    }

    public boolean setTopAndBottomOffset(int r2) {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 != null) goto L5;
        this.tempTopBottomOffset = r2;
        return false;
    L5:
        return r0.setTopAndBottomOffset(r2);
    }

    public void setVerticalOffsetEnabled(boolean r1) {
        ViewOffsetHelper r0 = this.viewOffsetHelper;
        if (r0 == null) goto L6;
        r0.setVerticalOffsetEnabled(r1);
        return;
    }
}
