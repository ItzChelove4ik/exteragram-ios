package com.google.android.material.navigationrail;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarDividerView;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.resources.MaterialResources;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class NavigationRailView extends NavigationBarView {
    static final int COLLAPSED_MAX_ITEM_COUNT = 7;
    private static final TimeInterpolator CUBIC_BEZIER_INTERPOLATOR = null;
    private static final int DEFAULT_HEADER_GRAVITY = 49;
    static final int DEFAULT_MENU_GRAVITY = 49;
    private static final int EXPAND_DURATION = 500;
    private static final int FADE_DURATION = 100;
    static final int NO_ITEM_MINIMUM_HEIGHT = -1;
    private int collapsedIconGravity;
    private int collapsedItemGravity;
    private int collapsedItemMinHeight;
    private int collapsedItemSpacing;
    private NavigationRailFrameLayout contentContainer;
    private final int contentMarginTop;
    private boolean expanded;
    private int expandedIconGravity;
    private int expandedItemGravity;
    private int expandedItemMinHeight;
    private int expandedItemSpacing;
    private final int headerMarginBottom;
    private View headerView;
    private final int maxExpandedWidth;
    private final int minExpandedWidth;
    private Boolean paddingBottomSystemWindowInsets;
    private Boolean paddingStartSystemWindowInsets;
    private Boolean paddingTopSystemWindowInsets;
    private final boolean scrollingEnabled;
    private boolean submenuDividersEnabled;

    /* renamed from: com.google.android.material.navigationrail.NavigationRailView$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewUtils.OnApplyWindowInsetsListener {
        final /* synthetic */ NavigationRailView this$0;

        public AnonymousClass1(NavigationRailView r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r5, WindowInsetsCompat r6, ViewUtils.RelativePadding r7) {
            Insets r0 = r6.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets r1 = r6.getInsets(WindowInsetsCompat.Type.displayCutout());
            NavigationRailView r2 = this.this$0;
            if (NavigationRailView.access$100(r2, NavigationRailView.access$000(r2)) == false) goto L5;
            r7.top += r0.top;
        L5:
            NavigationRailView r22 = this.this$0;
            if (NavigationRailView.access$100(r22, NavigationRailView.access$200(r22)) == false) goto L8;
            r7.bottom += r0.bottom;
        L8:
            NavigationRailView r4 = this.this$0;
            if (NavigationRailView.access$100(r4, NavigationRailView.access$300(r4)) == true) goto L11;
        L14:
            r7.applyToView(r5);
            return r6;
        L11:
            if (ViewUtils.isLayoutRtl(r5) == false) goto L13;
            r7.start += Math.max(r0.right, r1.right);
            goto L14
        L13:
            r7.start += Math.max(r0.left, r1.left);
            goto L14
        }
    }

    static {
        CUBIC_BEZIER_INTERPOLATOR = new PathInterpolator(0.38f, 1.21f, 0.22f, 1.0f);
    }

    public NavigationRailView(Context r2) {
        this(r2, null);
    }

    public NavigationRailView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.navigationRailStyle);
    }

    public NavigationRailView(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, R.style.Widget_MaterialComponents_NavigationRailView);
    }

    public NavigationRailView(Context r9, AttributeSet r10, int r11, int r12) {
        super(r9, r10, r11, r12);
        this.paddingTopSystemWindowInsets = null;
        this.paddingBottomSystemWindowInsets = null;
        this.paddingStartSystemWindowInsets = null;
        this.expanded = false;
        this.collapsedItemMinHeight = -1;
        this.collapsedIconGravity = 0;
        this.collapsedItemGravity = 49;
        Context r2 = getContext();
        this.expandedItemSpacing = getContext().getResources().getDimensionPixelSize(R.dimen.m3_navigation_rail_expanded_item_spacing);
        this.expandedItemGravity = NavigationBarView.ITEM_GRAVITY_START_CENTER;
        this.expandedIconGravity = 1;
        TintTypedArray r102 = ThemeEnforcement.obtainTintedStyledAttributes(r2, r10, R.styleable.NavigationRailView, r11, r12, new int[0]);
        this.contentMarginTop = r102.getDimensionPixelSize(R.styleable.NavigationRailView_contentMarginTop, getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_rail_margin));
        this.headerMarginBottom = r102.getDimensionPixelSize(R.styleable.NavigationRailView_headerMarginBottom, getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_rail_margin));
        this.scrollingEnabled = r102.getBoolean(R.styleable.NavigationRailView_scrollingEnabled, false);
        setSubmenuDividersEnabled(r102.getBoolean(R.styleable.NavigationRailView_submenuDividersEnabled, false));
        addContentContainer();
        int r112 = r102.getResourceId(R.styleable.NavigationRailView_headerLayout, 0);
        if (r112 == 0) goto L5;
        addHeaderView(r112);
    L5:
        setMenuGravity(r102.getInt(R.styleable.NavigationRailView_menuGravity, 49));
        int r113 = r102.getDimensionPixelSize(R.styleable.NavigationRailView_itemMinHeight, -1);
        int r122 = r102.getDimensionPixelSize(R.styleable.NavigationRailView_itemMinHeight, -1);
        if (r102.hasValue(R.styleable.NavigationRailView_collapsedItemMinHeight) == false) goto L9;
        r113 = r102.getDimensionPixelSize(R.styleable.NavigationRailView_collapsedItemMinHeight, -1);
    L9:
        if (r102.hasValue(R.styleable.NavigationRailView_expandedItemMinHeight) == false) goto L11;
        r122 = r102.getDimensionPixelSize(R.styleable.NavigationRailView_expandedItemMinHeight, -1);
    L11:
        setCollapsedItemMinimumHeight(r113);
        setExpandedItemMinimumHeight(r122);
        this.minExpandedWidth = r102.getDimensionPixelSize(R.styleable.NavigationRailView_expandedMinWidth, r2.getResources().getDimensionPixelSize(R.dimen.m3_navigation_rail_min_expanded_width));
        this.maxExpandedWidth = r102.getDimensionPixelSize(R.styleable.NavigationRailView_expandedMaxWidth, r2.getResources().getDimensionPixelSize(R.dimen.m3_navigation_rail_max_expanded_width));
        if (r102.hasValue(R.styleable.NavigationRailView_paddingTopSystemWindowInsets) == false) goto L15;
        this.paddingTopSystemWindowInsets = Boolean.valueOf(r102.getBoolean(R.styleable.NavigationRailView_paddingTopSystemWindowInsets, false));
    L15:
        if (r102.hasValue(R.styleable.NavigationRailView_paddingBottomSystemWindowInsets) == false) goto L18;
        this.paddingBottomSystemWindowInsets = Boolean.valueOf(r102.getBoolean(R.styleable.NavigationRailView_paddingBottomSystemWindowInsets, false));
    L18:
        if (r102.hasValue(R.styleable.NavigationRailView_paddingStartSystemWindowInsets) == false) goto L20;
        this.paddingStartSystemWindowInsets = Boolean.valueOf(r102.getBoolean(R.styleable.NavigationRailView_paddingStartSystemWindowInsets, false));
    L20:
        int r114 = getResources().getDimensionPixelOffset(R.dimen.m3_navigation_rail_item_padding_top_with_large_font);
        int r123 = getResources().getDimensionPixelOffset(R.dimen.m3_navigation_rail_item_padding_bottom_with_large_font);
        float r0 = AnimationUtils.lerp(0.0f, 1.0f, 0.3f, 1.0f, MaterialResources.getFontScale(r2) - 1.0f);
        float r115 = AnimationUtils.lerp(getItemPaddingTop(), r114, r0);
        float r124 = AnimationUtils.lerp(getItemPaddingBottom(), r123, r0);
        setItemPaddingTop(Math.round(r115));
        setItemPaddingBottom(Math.round(r124));
        setCollapsedItemSpacing(r102.getDimensionPixelSize(R.styleable.NavigationRailView_itemSpacing, 0));
        setExpanded(r102.getBoolean(R.styleable.NavigationRailView_expanded, false));
        r102.recycle();
        applyWindowInsets();
    }

    public static /* synthetic */ Boolean access$000(NavigationRailView r0) {
        return r0.paddingTopSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$100(NavigationRailView r0, Boolean r1) {
        return r0.shouldApplyWindowInsetPadding(r1);
    }

    public static /* synthetic */ Boolean access$200(NavigationRailView r0) {
        return r0.paddingBottomSystemWindowInsets;
    }

    public static /* synthetic */ Boolean access$300(NavigationRailView r0) {
        return r0.paddingStartSystemWindowInsets;
    }

    private void addContentContainer() {
        View r0 = (View) getMenuView();
        NavigationRailFrameLayout r1 = new NavigationRailFrameLayout(getContext());
        this.contentContainer = r1;
        r1.setPaddingTop(this.contentMarginTop);
        this.contentContainer.setScrollingEnabled(this.scrollingEnabled);
        this.contentContainer.setClipChildren(false);
        this.contentContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        r0.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        this.contentContainer.addView(r0);
        if (this.scrollingEnabled == true) goto L6;
        addView(this.contentContainer);
        return;
    L6:
        ScrollView r02 = new ScrollView(getContext());
        r02.setVerticalScrollBarEnabled(false);
        r02.addView(this.contentContainer);
        r02.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(r02);
    }

    private void applyWindowInsets() {
        ViewUtils.doOnApplyWindowInsets(this, new AnonymousClass1(this));
    }

    private int getMaxChildWidth() {
        int r0 = getNavigationRailMenuView().getChildCount();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L10;
        View r3 = getNavigationRailMenuView().getChildAt(r1);
        if (r3.getVisibility() == 8) goto L9;
        if ((r3 instanceof NavigationBarDividerView) == true) goto L9;
        r2 = Math.max(r2, r3.getMeasuredWidth());
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return r2;
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView) getMenuView();
    }

    private int makeExpandedWidthMeasureSpec(int r4, int r5) {
        int r0 = Math.min(this.minExpandedWidth, View.MeasureSpec.getSize(r4));
        if (View.MeasureSpec.getMode(r4) == 1073741824) goto L9;
        int r42 = Math.max(r5, r0);
        View r52 = this.headerView;
        if (r52 == null) goto L8;
        r42 = Math.max(r42, r52.getMeasuredWidth());
    L8:
        return View.MeasureSpec.makeMeasureSpec(Math.max(getSuggestedMinimumWidth(), Math.min(r42, this.maxExpandedWidth)), TLObject.FLAG_30);
    L9:
        return r4;
    }

    private int makeMinWidthSpec(int r4) {
        int r0 = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(r4) == 1073741824) goto L7;
        if (r0 <= 0) goto L7;
        int r02 = r0 + (getPaddingLeft() + getPaddingRight());
        return View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r4), r02), TLObject.FLAG_30);
    L7:
        return r4;
    }

    private void setExpanded(boolean r6) {
        if (this.expanded != r6) goto L5;
        return;
    L5:
        startTransitionAnimation();
        this.expanded = r6;
        int r0 = this.collapsedIconGravity;
        int r1 = this.collapsedItemSpacing;
        int r2 = this.collapsedItemMinHeight;
        int r3 = this.collapsedItemGravity;
        if (r6 == false) goto L8;
        r0 = this.expandedIconGravity;
        r1 = this.expandedItemSpacing;
        r2 = this.expandedItemMinHeight;
        r3 = this.expandedItemGravity;
    L8:
        getNavigationRailMenuView().setItemGravity(r3);
        super.setItemIconGravity(r0);
        getNavigationRailMenuView().setItemSpacing(r1);
        getNavigationRailMenuView().setItemMinimumHeight(r2);
        getNavigationRailMenuView().setExpanded(r6);
    }

    private boolean shouldApplyWindowInsetPadding(Boolean r1) {
        if (r1 == null) goto L6;
        return r1.booleanValue();
    L6:
        return getFitsSystemWindows();
    }

    private void startTransitionAnimation() {
        if (isLaidOut() == true) goto L5;
        return;
    L5:
        Transition r0 = new ChangeBounds().setDuration(500).setInterpolator(CUBIC_BEZIER_INTERPOLATOR);
        Transition r1 = new Fade().setDuration(100);
        Transition r4 = new Fade().setDuration(100);
        LabelMoveTransition r5 = new LabelMoveTransition();
        Transition r2 = new Fade().setDuration(100);
        int r3 = getNavigationRailMenuView().getChildCount();
        int r7 = 0;
    L7:
        if (r7 >= r3) goto L16;
        View r9 = getNavigationRailMenuView().getChildAt(r7);
        if ((r9 instanceof NavigationBarItemView) == false) goto L15;
        NavigationBarItemView r10 = (NavigationBarItemView) r9;
        r0.excludeTarget(r10.getLabelGroup(), true);
        r0.excludeTarget(r10.getExpandedLabelGroup(), true);
        if (this.expanded == false) goto L13;
        r4.addTarget(r10.getExpandedLabelGroup());
        r1.addTarget(r10.getLabelGroup());
    L14:
        r5.addTarget(r10.getExpandedLabelGroup());
        goto L15
    L13:
        r4.addTarget(r10.getLabelGroup());
        r1.addTarget(r10.getExpandedLabelGroup());
    L15:
        r2.addTarget(r9);
        r7 = r7 + 1;
        goto L7
    L16:
        TransitionSet r32 = new TransitionSet();
        r32.setOrdering(0);
        r32.addTransition(r0).addTransition(r1).addTransition(r5);
        if (this.expanded == true) goto L19;
        r32.addTransition(r2);
    L19:
        TransitionSet r02 = new TransitionSet();
        r02.setOrdering(0);
        r02.addTransition(r4);
        if (this.expanded == false) goto L22;
        r02.addTransition(r2);
    L22:
        TransitionSet r12 = new TransitionSet();
        r12.setOrdering(1);
        r12.addTransition(r02).addTransition(r32);
        TransitionManager.beginDelayedTransition((ViewGroup) getParent(), r12);
    }

    public void addHeaderView(int r3) {
        addHeaderView(LayoutInflater.from(getContext()).inflate(r3, this, false));
    }

    public void addHeaderView(View r3) {
        removeHeaderView();
        this.headerView = r3;
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(-2, -2);
        r0.gravity = 49;
        r0.bottomMargin = this.headerMarginBottom;
        this.contentContainer.addView(r3, 0, r0);
    }

    public void collapse() {
        if (this.expanded == true) goto L5;
        return;
    L5:
        setExpanded(false);
        announceForAccessibility(getResources().getString(R.string.nav_rail_collapsed_a11y_label));
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public /* bridge */ /* synthetic */ NavigationBarMenuView createNavigationBarMenuView(Context r1) {
        return createNavigationBarMenuView(r1);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public NavigationRailMenuView createNavigationBarMenuView(Context r1) {
        return new NavigationRailMenuView(r1);
    }

    public void expand() {
        if (this.expanded == false) goto L5;
        return;
    L5:
        setExpanded(true);
        announceForAccessibility(getResources().getString(R.string.nav_rail_expanded_a11y_label));
    }

    public int getCollapsedItemMinimumHeight() {
        return this.collapsedItemMinHeight;
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getCollapsedMaxItemCount() {
        return 7;
    }

    public int getExpandedItemMinimumHeight() {
        return this.expandedItemMinHeight;
    }

    public View getHeaderView() {
        return this.headerView;
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getItemGravity() {
        return getNavigationRailMenuView().getItemGravity();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getItemIconGravity() {
        return getNavigationRailMenuView().getItemIconGravity();
    }

    public int getItemMinimumHeight() {
        return getNavigationRailMenuView().getItemMinimumHeight();
    }

    public int getItemSpacing() {
        return getNavigationRailMenuView().getItemSpacing();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return Integer.MAX_VALUE;
    }

    public int getMenuGravity() {
        return getNavigationRailMenuView().getMenuGravity();
    }

    public boolean getSubmenuDividersEnabled() {
        return this.submenuDividersEnabled;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public boolean isSubMenuSupported() {
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r0 = makeMinWidthSpec(r3);
        if (this.expanded == false) goto L10;
        measureChild(getNavigationRailMenuView(), r3, r4);
        View r02 = this.headerView;
        if (r02 == null) goto L7;
        measureChild(r02, r3, r4);
    L7:
        r0 = makeExpandedWidthMeasureSpec(r3, getMaxChildWidth());
        if (getItemActiveIndicatorExpandedWidth() != (-1)) goto L10;
        getNavigationRailMenuView().updateActiveIndicator(View.MeasureSpec.getSize(r0));
    L10:
        super.onMeasure(r0, r4);
        if (this.contentContainer.getMeasuredHeight() >= getMeasuredHeight()) goto L14;
        measureChild(this.contentContainer, r0, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        return;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r1) {
        super.onTouchEvent(r1);
        return true;
    }

    public void removeHeaderView() {
        View r0 = this.headerView;
        if (r0 == null) goto L6;
        this.contentContainer.removeView(r0);
        this.headerView = null;
        return;
    }

    public void setCollapsedItemMinimumHeight(int r2) {
        this.collapsedItemMinHeight = r2;
        if (this.expanded == true) goto L6;
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(r2);
        return;
    }

    public void setCollapsedItemSpacing(int r2) {
        this.collapsedItemSpacing = r2;
        if (this.expanded == true) goto L6;
        getNavigationRailMenuView().setItemSpacing(r2);
        return;
    }

    public void setExpandedItemMinimumHeight(int r2) {
        this.expandedItemMinHeight = r2;
        if (this.expanded == false) goto L6;
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(r2);
        return;
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public void setItemGravity(int r1) {
        this.collapsedItemGravity = r1;
        this.expandedItemGravity = r1;
        super.setItemGravity(r1);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public void setItemIconGravity(int r1) {
        this.collapsedIconGravity = r1;
        this.expandedIconGravity = r1;
        super.setItemIconGravity(r1);
    }

    public void setItemMinimumHeight(int r1) {
        this.collapsedItemMinHeight = r1;
        this.expandedItemMinHeight = r1;
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(r1);
    }

    public void setItemSpacing(int r1) {
        this.collapsedItemSpacing = r1;
        this.expandedItemSpacing = r1;
        getNavigationRailMenuView().setItemSpacing(r1);
    }

    public void setMenuGravity(int r1) {
        getNavigationRailMenuView().setMenuGravity(r1);
    }

    public void setSubmenuDividersEnabled(boolean r2) {
        if (this.submenuDividersEnabled != r2) goto L5;
        return;
    L5:
        this.submenuDividersEnabled = r2;
        getNavigationRailMenuView().setSubmenuDividersEnabled(r2);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public boolean shouldAddMenuView() {
        return true;
    }
}
