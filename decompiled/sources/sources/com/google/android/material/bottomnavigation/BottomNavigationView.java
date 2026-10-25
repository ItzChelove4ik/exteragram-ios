package com.google.android.material.bottomnavigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;

/* loaded from: classes5.dex */
public class BottomNavigationView extends NavigationBarView {
    private static final int MAX_ITEM_COUNT = 6;

    /* renamed from: com.google.android.material.bottomnavigation.BottomNavigationView$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewUtils.OnApplyWindowInsetsListener {
        final /* synthetic */ BottomNavigationView this$0;

        public AnonymousClass1(BottomNavigationView r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r5, WindowInsetsCompat r6, ViewUtils.RelativePadding r7) {
            r7.bottom += r6.getSystemWindowInsetBottom();
            boolean r0 = true;
            if (r5.getLayoutDirection() == 1) goto L6;
            r0 = false;
        L6:
            int r4 = r6.getSystemWindowInsetLeft();
            int r1 = r6.getSystemWindowInsetRight();
            int r2 = r7.start;
            if (r0 == false) goto L9;
            int r3 = r1;
        L10:
            r7.start = r2 + r3;
            int r22 = r7.end;
            if (r0 == true) goto L14;
            r4 = r1;
        L14:
            r7.end = r22 + r4;
            r7.applyToView(r5);
            return r6;
        L9:
            r3 = r4;
            goto L10
        }
    }

    @Deprecated
    public interface OnNavigationItemReselectedListener extends NavigationBarView.OnItemReselectedListener {
    }

    @Deprecated
    public interface OnNavigationItemSelectedListener extends NavigationBarView.OnItemSelectedListener {
    }

    public BottomNavigationView(Context r2) {
        this(r2, null);
    }

    public BottomNavigationView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, R.style.Widget_Design_BottomNavigationView);
    }

    public BottomNavigationView(Context r7, AttributeSet r8, int r9, int r10) {
        super(r7, r8, r9, r10);
        TintTypedArray r82 = ThemeEnforcement.obtainTintedStyledAttributes(getContext(), r8, R.styleable.BottomNavigationView, r9, r10, new int[0]);
        setItemHorizontalTranslationEnabled(r82.getBoolean(R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        if (r82.hasValue(R.styleable.BottomNavigationView_android_minHeight) == false) goto L5;
        setMinimumHeight(r82.getDimensionPixelSize(R.styleable.BottomNavigationView_android_minHeight, 0));
    L5:
        r82.recycle();
        applyWindowInsets();
    }

    private void applyWindowInsets() {
        ViewUtils.doOnApplyWindowInsets(this, new AnonymousClass1(this));
    }

    private int makeMinHeightSpec(int r4) {
        int r0 = getSuggestedMinimumHeight();
        if (View.MeasureSpec.getMode(r4) == 1073741824) goto L7;
        if (r0 <= 0) goto L7;
        int r02 = r0 + (getPaddingTop() + getPaddingBottom());
        return View.MeasureSpec.makeMeasureSpec(Math.max(View.MeasureSpec.getSize(r4), r02), Integer.MIN_VALUE);
    L7:
        return r4;
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public NavigationBarMenuView createNavigationBarMenuView(Context r1) {
        return new BottomNavigationMenuView(r1);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 6;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return ((BottomNavigationMenuView) getMenuView()).isItemHorizontalTranslationEnabled();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, makeMinHeightSpec(r4));
        if (View.MeasureSpec.getMode(r4) == 1073741824) goto L6;
        setMeasuredDimension(getMeasuredWidth(), Math.max(getMeasuredHeight(), (getSuggestedMinimumHeight() + getPaddingTop()) + getPaddingBottom()));
        return;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r1) {
        super.onTouchEvent(r1);
        return true;
    }

    public void setItemHorizontalTranslationEnabled(boolean r3) {
        BottomNavigationMenuView r0 = (BottomNavigationMenuView) getMenuView();
        if (r0.isItemHorizontalTranslationEnabled() == r3) goto L6;
        r0.setItemHorizontalTranslationEnabled(r3);
        getPresenter().updateMenuView(false);
        return;
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener r1) {
        setOnItemReselectedListener(r1);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener r1) {
        setOnItemSelectedListener(r1);
    }
}
