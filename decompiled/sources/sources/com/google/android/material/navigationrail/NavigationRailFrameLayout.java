package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes5.dex */
public class NavigationRailFrameLayout extends FrameLayout {
    int paddingTop;
    boolean scrollingEnabled;

    public NavigationRailFrameLayout(Context r1) {
        super(r1);
        this.paddingTop = 0;
        this.scrollingEnabled = false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        super.onLayout(r4, r5, r6, r7, r8);
        int r42 = getChildCount();
        int r52 = this.paddingTop;
        int r62 = 0;
    L3:
        if (r62 >= r42) goto L5;
        View r72 = getChildAt(r62);
        FrameLayout.LayoutParams r82 = (FrameLayout.LayoutParams) r72.getLayoutParams();
        int r53 = Math.max(r52, r72.getTop()) + r82.topMargin;
        r72.layout(r72.getLeft(), r53, r72.getRight(), r72.getMeasuredHeight() + r53);
        r52 = r53 + (r72.getMeasuredHeight() + r82.bottomMargin);
        r62 = r62 + 1;
        goto L3
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        super.onMeasure(r6, r7);
        int r0 = getChildCount();
        int r1 = 0;
        View r2 = getChildAt(0);
        int r3 = View.MeasureSpec.getSize(r7);
        if (r0 <= 1) goto L7;
        View r02 = getChildAt(0);
        measureChild(r02, r6, r7);
        FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) r02.getLayoutParams();
        int r03 = r02.getMeasuredHeight() + r12.bottomMargin;
        r1 = r12.topMargin + r03;
        int r04 = (r3 - r1) - this.paddingTop;
        r2 = getChildAt(1);
        if (this.scrollingEnabled == true) goto L7;
        r7 = View.MeasureSpec.makeMeasureSpec(r04, Integer.MIN_VALUE);
    L7:
        FrameLayout.LayoutParams r05 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        measureChild(r2, r6, r7);
        int r62 = (r2.getMeasuredHeight() + r05.bottomMargin) + r05.topMargin;
        int r63 = Math.max(r3, (this.paddingTop + r1) + r62);
        setMeasuredDimension(getMeasuredWidth(), r63);
    }

    public void setPaddingTop(int r1) {
        this.paddingTop = r1;
    }

    public void setScrollingEnabled(boolean r1) {
        this.scrollingEnabled = r1;
    }
}
