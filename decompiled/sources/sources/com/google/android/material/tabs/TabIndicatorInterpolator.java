package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.tabs.TabLayout;

/* loaded from: classes5.dex */
class TabIndicatorInterpolator {
    private static final int MIN_INDICATOR_WIDTH = 24;

    public TabIndicatorInterpolator() {
    }

    public static RectF calculateIndicatorWidthForTab(TabLayout r3, View r4) {
        if (r4 != null) goto L6;
        return new RectF();
    L6:
        if (r3.isTabIndicatorFullWidth() == true) goto L12;
        if ((r4 instanceof TabLayout.TabView) == false) goto L12;
        return calculateTabViewContentBounds((TabLayout.TabView) r4, 24);
    L12:
        return new RectF(r4.getLeft(), r4.getTop(), r4.getRight(), r4.getBottom());
    }

    public static RectF calculateTabViewContentBounds(TabLayout.TabView r3, int r4) {
        int r0 = r3.getContentWidth();
        int r1 = r3.getContentHeight();
        int r42 = (int) ViewUtils.dpToPx(r3.getContext(), r4);
        if (r0 >= r42) goto L5;
        r0 = r42;
    L5:
        int r43 = (r3.getLeft() + r3.getRight()) / 2;
        int r2 = (r3.getTop() + r3.getBottom()) / 2;
        int r02 = r0 / 2;
        return new RectF(r43 - r02, r2 - (r1 / 2), r02 + r43, r2 + (r43 / 2));
    }

    public void setIndicatorBoundsForTab(TabLayout r2, View r3, Drawable r4) {
        RectF r1 = calculateIndicatorWidthForTab(r2, r3);
        r4.setBounds((int) r1.left, r4.getBounds().top, (int) r1.right, r4.getBounds().bottom);
    }

    public void updateIndicatorForOffset(TabLayout r1, View r2, View r3, float r4, Drawable r5) {
        RectF r0 = calculateIndicatorWidthForTab(r1, r2);
        RectF r12 = calculateIndicatorWidthForTab(r1, r3);
        r5.setBounds(AnimationUtils.lerp((int) r0.left, (int) r12.left, r4), r5.getBounds().top, AnimationUtils.lerp((int) r0.right, (int) r12.right, r4), r5.getBounds().bottom);
    }
}
