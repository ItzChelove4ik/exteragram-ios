package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.R$attr;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements MenuView.ItemView {
    private static final int[] CHECKED_STATE_SET = null;
    private final AccessibilityDelegateCompat accessibilityDelegate;
    private FrameLayout actionArea;
    boolean checkable;
    private Drawable emptyDrawable;
    private boolean hasIconTintList;
    private int iconSize;
    private ColorStateList iconTintList;
    boolean isBold;
    private MenuItemImpl itemData;
    private boolean needsEmptyIcon;
    private final CheckedTextView textView;

    /* renamed from: com.google.android.material.internal.NavigationMenuItemView$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityDelegateCompat {
        final /* synthetic */ NavigationMenuItemView this$0;

        public AnonymousClass1(NavigationMenuItemView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setCheckable(this.this$0.checkable);
            r2.setRoleDescription(this.this$0.getResources().getString(R.string.item_view_role_description));
        }
    }

    static {
        CHECKED_STATE_SET = new int[]{android.R.attr.state_checked};
    }

    public NavigationMenuItemView(Context r2) {
        this(r2, null);
    }

    public NavigationMenuItemView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public NavigationMenuItemView(Context r3, AttributeSet r4, int r5) {
        super(r3, r4, r5);
        this.isBold = true;
        AnonymousClass1 r52 = new AnonymousClass1(this);
        this.accessibilityDelegate = r52;
        setOrientation(0);
        LayoutInflater.from(r3).inflate(R.layout.design_navigation_menu_item, this, true);
        setIconSize(r3.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size));
        CheckedTextView r32 = (CheckedTextView) findViewById(R.id.design_menu_item_text);
        this.textView = r32;
        ViewCompat.setAccessibilityDelegate(r32, r52);
    }

    private void adjustAppearance() {
        boolean r0 = shouldExpandActionArea();
        CheckedTextView r1 = this.textView;
        if (r0 == false) goto L8;
        r1.setVisibility(8);
        FrameLayout r02 = this.actionArea;
        if (r02 == null) goto L12;
        LinearLayoutCompat.LayoutParams r03 = (LinearLayoutCompat.LayoutParams) r02.getLayoutParams();
        ((LinearLayout.LayoutParams) r03).width = -1;
        this.actionArea.setLayoutParams(r03);
        return;
    L12:
        return;
    L8:
        r1.setVisibility(0);
        FrameLayout r04 = this.actionArea;
        if (r04 == null) goto L13;
        LinearLayoutCompat.LayoutParams r05 = (LinearLayoutCompat.LayoutParams) r04.getLayoutParams();
        ((LinearLayout.LayoutParams) r05).width = -2;
        this.actionArea.setLayoutParams(r05);
        return;
    }

    private StateListDrawable createDefaultBackground() {
        TypedValue r0 = new TypedValue();
        if (getContext().getTheme().resolveAttribute(R$attr.colorControlHighlight, r0, true) == false) goto L6;
        StateListDrawable r3 = new StateListDrawable();
        r3.addState(CHECKED_STATE_SET, new ColorDrawable(r0.data));
        ColorDrawable r02 = new ColorDrawable(0);
        r3.addState(ViewGroup.EMPTY_STATE_SET, r02);
        return r3;
    L6:
        return null;
    }

    private void setActionView(View r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (this.actionArea != null) goto L7;
        this.actionArea = (FrameLayout) ((ViewStub) findViewById(R.id.design_menu_item_action_area_stub)).inflate();
    L7:
        if (r2.getParent() == null) goto L9;
        ((ViewGroup) r2.getParent()).removeView(r2);
    L9:
        this.actionArea.removeAllViews();
        this.actionArea.addView(r2);
    }

    private boolean shouldExpandActionArea() {
        if (this.itemData.getTitle() == null) goto L5;
        return false;
    L5:
        if (this.itemData.getIcon() == null) goto L7;
        return false;
    L7:
        if (this.itemData.getActionView() == null) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl r1, int r2) {
        this.itemData = r1;
        if (r1.getItemId() <= 0) goto L6;
        setId(r1.getItemId());
    L6:
        if (r1.isVisible() == false) goto L8;
        int r22 = 0;
    L9:
        setVisibility(r22);
        if (getBackground() != null) goto L12;
        setBackground(createDefaultBackground());
    L12:
        setCheckable(r1.isCheckable());
        setChecked(r1.isChecked());
        setEnabled(r1.isEnabled());
        setTitle(r1.getTitle());
        setIcon(r1.getIcon());
        setActionView(r1.getActionView());
        setContentDescription(r1.getContentDescription());
        TooltipCompat.setTooltipText(this, r1.getTooltipText());
        adjustAppearance();
        return;
    L8:
        r22 = 8;
        goto L9
    }

    public void initialize(MenuItemImpl r1, boolean r2) {
        this.isBold = r2;
        initialize(r1, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int r2) {
        int[] r22 = super.onCreateDrawableState(r2 + 1);
        MenuItemImpl r0 = this.itemData;
        if (r0 != null) goto L5;
    L9:
        return r22;
    L5:
        if (r0.isCheckable() == false) goto L9;
        if (this.itemData.isChecked() == false) goto L9;
        View.mergeDrawableStates(r22, CHECKED_STATE_SET);
        goto L9
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void recycle() {
        FrameLayout r0 = this.actionArea;
        if (r0 == null) goto L5;
        r0.removeAllViews();
    L5:
        this.textView.setCompoundDrawables(null, null, null, null);
    }

    public void setCheckable(boolean r2) {
        refreshDrawableState();
        if (this.checkable == r2) goto L6;
        this.checkable = r2;
        this.accessibilityDelegate.sendAccessibilityEvent(this.textView, 2048);
        return;
    }

    public void setChecked(boolean r3) {
        refreshDrawableState();
        this.textView.setChecked(r3);
        CheckedTextView r0 = this.textView;
        Typeface r1 = r0.getTypeface();
        if (r3 == true) goto L5;
    L7:
        int r2 = 0;
    L8:
        r0.setTypeface(r1, r2);
        return;
    L5:
        if (this.isBold == false) goto L7;
        r2 = 1;
        goto L8
    }

    public void setHorizontalPadding(int r3) {
        setPadding(r3, getPaddingTop(), r3, getPaddingBottom());
    }

    public void setIcon(Drawable r4) {
        if (r4 == null) goto L13;
        if (this.hasIconTintList == false) goto L11;
        Drawable.ConstantState r1 = r4.getConstantState();
        if (r1 == null) goto L10;
        r4 = r1.newDrawable();
    L10:
        r4 = DrawableCompat.wrap(r4).mutate();
        r4.setTintList(this.iconTintList);
    L11:
        int r12 = this.iconSize;
        r4.setBounds(0, 0, r12, r12);
    L20:
        this.textView.setCompoundDrawablesRelative(r4, null, null, null);
        return;
    L13:
        if (this.needsEmptyIcon == false) goto L20;
        if (this.emptyDrawable != null) goto L19;
        Drawable r42 = ResourcesCompat.getDrawable(getResources(), R.drawable.navigation_empty_icon, getContext().getTheme());
        this.emptyDrawable = r42;
        if (r42 == null) goto L19;
        int r13 = this.iconSize;
        r42.setBounds(0, 0, r13, r13);
    L19:
        r4 = this.emptyDrawable;
        goto L20
    }

    public void setIconPadding(int r1) {
        this.textView.setCompoundDrawablePadding(r1);
    }

    public void setIconSize(int r1) {
        this.iconSize = r1;
    }

    public void setIconTintList(ColorStateList r1) {
        this.iconTintList = r1;
        if (r1 == null) goto L5;
        boolean r12 = true;
    L6:
        this.hasIconTintList = r12;
        MenuItemImpl r13 = this.itemData;
        if (r13 == null) goto L10;
        setIcon(r13.getIcon());
        return;
    L10:
        return;
    L5:
        r12 = false;
        goto L6
    }

    public void setMaxLines(int r1) {
        this.textView.setMaxLines(r1);
    }

    public void setNeedsEmptyIcon(boolean r1) {
        this.needsEmptyIcon = r1;
    }

    public void setShortcut(boolean r1, char r2) {
    }

    public void setTextAppearance(int r1) {
        TextViewCompat.setTextAppearance(this.textView, r1);
    }

    public void setTextColor(ColorStateList r1) {
        this.textView.setTextColor(r1);
    }

    public void setTitle(CharSequence r1) {
        this.textView.setText(r1);
    }

    public boolean showsIcon() {
        return true;
    }
}
