package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes5.dex */
abstract class SheetDelegate {
    public SheetDelegate() {
    }

    public abstract int calculateInnerMargin(ViewGroup.MarginLayoutParams r1);

    public abstract float calculateSlideOffset(int r1);

    public abstract int getCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams r1);

    public abstract int getExpandedOffset();

    public abstract int getHiddenOffset();

    public abstract int getMaxViewPositionHorizontal();

    public abstract int getMinViewPositionHorizontal();

    public abstract <V extends View> int getOuterEdge(V r1);

    public abstract int getParentInnerEdge(CoordinatorLayout r1);

    public abstract int getSheetEdge();

    public abstract boolean isExpandingOutwards(float r1);

    public abstract boolean isReleasedCloseToInnerEdge(View r1);

    public abstract boolean isSwipeSignificant(float r1, float r2);

    public abstract boolean shouldHide(View r1, float r2);

    public abstract void updateCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams r1, int r2);

    public abstract void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams r1, int r2, int r3);
}
