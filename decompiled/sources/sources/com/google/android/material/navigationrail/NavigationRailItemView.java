package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;

/* loaded from: classes5.dex */
final class NavigationRailItemView extends NavigationBarItemView {
    public NavigationRailItemView(Context r1) {
        super(r1);
    }

    @Override // com.google.android.material.navigation.NavigationBarItemView
    public int getItemDefaultMarginResId() {
        return R.dimen.mtrl_navigation_rail_icon_margin;
    }

    @Override // com.google.android.material.navigation.NavigationBarItemView
    public int getItemLayoutResId() {
        return R.layout.mtrl_navigation_rail_item;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        if (View.MeasureSpec.getMode(r2) != 0) goto L6;
        int r12 = View.MeasureSpec.getSize(r2);
        int r13 = Math.max(getMeasuredHeight(), r12);
        setMeasuredDimension(getMeasuredWidthAndState(), r13);
        return;
    }
}
