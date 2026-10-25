package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes5.dex */
class ViewOffsetHelper {
    private boolean horizontalOffsetEnabled;
    private int layoutLeft;
    private int layoutTop;
    private int offsetLeft;
    private int offsetTop;
    private boolean verticalOffsetEnabled;
    private final View view;

    public ViewOffsetHelper(View r2) {
        this.verticalOffsetEnabled = true;
        this.horizontalOffsetEnabled = true;
        this.view = r2;
    }

    public void applyOffsets() {
        View r0 = this.view;
        ViewCompat.offsetTopAndBottom(r0, this.offsetTop - (r0.getTop() - this.layoutTop));
        View r02 = this.view;
        ViewCompat.offsetLeftAndRight(r02, this.offsetLeft - (r02.getLeft() - this.layoutLeft));
    }

    public int getLayoutLeft() {
        return this.layoutLeft;
    }

    public int getLayoutTop() {
        return this.layoutTop;
    }

    public int getLeftAndRightOffset() {
        return this.offsetLeft;
    }

    public int getTopAndBottomOffset() {
        return this.offsetTop;
    }

    public boolean isHorizontalOffsetEnabled() {
        return this.horizontalOffsetEnabled;
    }

    public boolean isVerticalOffsetEnabled() {
        return this.verticalOffsetEnabled;
    }

    public void onViewLayout() {
        this.layoutTop = this.view.getTop();
        this.layoutLeft = this.view.getLeft();
    }

    public void setHorizontalOffsetEnabled(boolean r1) {
        this.horizontalOffsetEnabled = r1;
    }

    public boolean setLeftAndRightOffset(int r2) {
        if (this.horizontalOffsetEnabled == true) goto L5;
        return false;
    L5:
        if (this.offsetLeft == r2) goto L10;
        this.offsetLeft = r2;
        applyOffsets();
        return true;
    L10:
        return false;
    }

    public boolean setTopAndBottomOffset(int r2) {
        if (this.verticalOffsetEnabled == true) goto L5;
        return false;
    L5:
        if (this.offsetTop == r2) goto L10;
        this.offsetTop = r2;
        applyOffsets();
        return true;
    L10:
        return false;
    }

    public void setVerticalOffsetEnabled(boolean r1) {
        this.verticalOffsetEnabled = r1;
    }
}
