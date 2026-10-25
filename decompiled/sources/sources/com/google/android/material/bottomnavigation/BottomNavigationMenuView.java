package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.ArrayList;
import java.util.List;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class BottomNavigationMenuView extends NavigationBarMenuView {
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private boolean itemHorizontalTranslationEnabled;
    private final List<Integer> tempChildWidths;

    public BottomNavigationMenuView(Context r2) {
        super(r2);
        this.tempChildWidths = new ArrayList();
        FrameLayout.LayoutParams r22 = new FrameLayout.LayoutParams(-2, -2);
        r22.gravity = 17;
        setLayoutParams(r22);
        Resources r23 = getResources();
        this.inactiveItemMaxWidth = r23.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = r23.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = r23.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = r23.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public NavigationBarItemView createNavigationBarItemView(Context r1) {
        return new BottomNavigationItemView(r1);
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        int r52 = getChildCount();
        int r82 = r8 - r6;
        int r92 = r9 - r7;
        int r72 = 0;
        int r0 = 0;
    L3:
        if (r72 >= r52) goto L13;
        View r1 = getChildAt(r72);
        if (r1.getVisibility() == 8) goto L12;
        if (getLayoutDirection() != 1) goto L10;
        int r2 = r82 - r0;
        r1.layout(r2 - r1.getMeasuredWidth(), 0, r2, r92);
    L11:
        r0 = r0 + r1.getMeasuredWidth();
        goto L12
    L10:
        r1.layout(r0, 0, r1.getMeasuredWidth() + r0, r92);
    L12:
        r72 = r72 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onMeasure(int r11, int r12) {
        int r112 = View.MeasureSpec.getSize(r11);
        int r0 = getCurrentVisibleContentItemCount();
        int r1 = getChildCount();
        this.tempChildWidths.clear();
        int r122 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r12), Integer.MIN_VALUE);
        int r6 = 1;
        int r7 = 0;
        if (getItemIconGravity() == 0) goto L5;
        if (r0 != 0) goto L52;
        r0 = 1;
    L52:
        float r113 = r112;
        float r3 = Math.min((r0 + 3) / 10.0f, 0.9f) * r113;
        float r02 = r0;
        int r32 = Math.round(r3 / r02);
        int r114 = Math.round(r113 / r02);
        int r03 = 0;
        int r62 = 0;
    L53:
        if (r7 >= r1) goto L61;
        View r8 = getChildAt(r7);
        if (r8.getVisibility() == 8) goto L60;
        r8.measure(View.MeasureSpec.makeMeasureSpec(r114, Integer.MIN_VALUE), r122);
        if (r8.getMeasuredWidth() >= r32) goto L59;
        r8.measure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), r122);
    L59:
        r03 = r03 + r8.getMeasuredWidth();
        r62 = Math.max(r62, r8.getMeasuredHeight());
    L60:
        r7 = r7 + 1;
        goto L53
    L61:
        int r115 = r03;
        int r04 = r62;
    L62:
        setMeasuredDimension(r115, Math.max(r04, getSuggestedMinimumHeight()));
        return;
    L5:
        if (isShifting(getLabelVisibilityMode(), r0) == true) goto L7;
    L31:
        if (r0 == 0) goto L34;
        r6 = r0;
    L34:
        int r2 = Math.min(r112 / r6, this.activeItemMaxWidth);
        int r116 = r112 - (r0 * r2);
        int r05 = 0;
    L35:
        if (r05 >= r1) goto L43;
        if (getChildAt(r05).getVisibility() == 8) goto L41;
        if (r116 <= 0) goto L40;
        int r33 = r2 + 1;
        r116 = r116 - 1;
    L42:
        this.tempChildWidths.add(Integer.valueOf(r33));
        r05 = r05 + 1;
        goto L35
    L40:
        r33 = r2;
        goto L42
    L41:
        r33 = 0;
    L43:
        r115 = 0;
        r04 = 0;
    L44:
        if (r7 >= r1) goto L62;
        View r22 = getChildAt(r7);
        if (r22.getVisibility() == 8) goto L49;
        r22.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths.get(r7).intValue(), TLObject.FLAG_30), r122);
        r22.getLayoutParams().width = r22.getMeasuredWidth();
        r115 = r115 + r22.getMeasuredWidth();
        r04 = Math.max(r04, r22.getMeasuredHeight());
    L49:
        r7 = r7 + 1;
        goto L44
    L7:
        if (isItemHorizontalTranslationEnabled() == false) goto L31;
        View r34 = getChildAt(getSelectedItemPosition());
        int r82 = this.activeItemMinWidth;
        if (r34.getVisibility() == 8) goto L12;
        r34.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), r122);
        r82 = Math.max(r82, r34.getMeasuredWidth());
    L12:
        if (r34.getVisibility() == 8) goto L14;
        int r23 = 1;
    L15:
        int r06 = r0 - r23;
        int r24 = Math.min(r112 - (this.inactiveItemMinWidth * r06), Math.min(r82, this.activeItemMaxWidth));
        int r117 = r112 - r24;
        if (r06 == 0) goto L19;
        r6 = r06;
    L19:
        int r35 = Math.min(r117 / r6, this.inactiveItemMaxWidth);
        int r118 = r117 - (r06 * r35);
        int r07 = 0;
    L20:
        if (r07 >= r1) goto L43;
        if (getChildAt(r07).getVisibility() != 8) goto L24;
        int r63 = 0;
    L30:
        this.tempChildWidths.add(Integer.valueOf(r63));
        r07 = r07 + 1;
        goto L20
    L24:
        if (r07 != getSelectedItemPosition()) goto L26;
        r63 = r24;
    L27:
        if (r118 <= 0) goto L30;
        r63 = r63 + 1;
        r118 = r118 - 1;
        goto L30
    L26:
        r63 = r35;
        goto L27
    L14:
        r23 = 0;
        goto L15
    }

    public void setItemHorizontalTranslationEnabled(boolean r1) {
        this.itemHorizontalTranslationEnabled = r1;
    }
}
