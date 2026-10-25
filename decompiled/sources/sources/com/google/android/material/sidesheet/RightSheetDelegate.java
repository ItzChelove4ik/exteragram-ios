package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes5.dex */
final class RightSheetDelegate extends SheetDelegate {
    final SideSheetBehavior<? extends View> sheetBehavior;

    public RightSheetDelegate(SideSheetBehavior<? extends View> r1) {
        this.sheetBehavior = r1;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int calculateInnerMargin(ViewGroup.MarginLayoutParams r1) {
        return r1.rightMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public float calculateSlideOffset(int r2) {
        float r0 = getHiddenOffset();
        return (r0 - r2) / (r0 - getExpandedOffset());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams r1) {
        return r1.rightMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getExpandedOffset() {
        return Math.max(0, (getHiddenOffset() - this.sheetBehavior.getChildWidth()) - this.sheetBehavior.getInnerMargin());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getHiddenOffset() {
        return this.sheetBehavior.getParentWidth();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getMaxViewPositionHorizontal() {
        return this.sheetBehavior.getParentWidth();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getMinViewPositionHorizontal() {
        return getExpandedOffset();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public <V extends View> int getOuterEdge(V r1) {
        return r1.getLeft() - this.sheetBehavior.getInnerMargin();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getParentInnerEdge(CoordinatorLayout r1) {
        return r1.getRight();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public int getSheetEdge() {
        return 0;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public boolean isExpandingOutwards(float r1) {
        if (r1 >= 0.0f) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public boolean isReleasedCloseToInnerEdge(View r2) {
        if (r2.getLeft() <= ((getHiddenOffset() + getExpandedOffset()) / 2)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public boolean isSwipeSignificant(float r1, float r2) {
        if (SheetUtils.isSwipeMostlyHorizontal(r1, r2) == true) goto L5;
        return false;
    L5:
        if (Math.abs(r1) <= this.sheetBehavior.getSignificantVelocityThreshold()) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public boolean shouldHide(View r2, float r3) {
        if (Math.abs(r2.getRight() + (r3 * this.sheetBehavior.getHideFriction())) <= this.sheetBehavior.getHideThreshold()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public void updateCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams r1, int r2) {
        r1.rightMargin = r2;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams r1, int r2, int r3) {
        int r0 = this.sheetBehavior.getParentWidth();
        if (r2 > r0) goto L6;
        r1.rightMargin = r0 - r2;
        return;
    }
}
