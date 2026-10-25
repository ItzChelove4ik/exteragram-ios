package com.google.android.material.navigation;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.TextScale;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.HashSet;

/* loaded from: classes5.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    private static final int[] CHECKED_STATE_SET = null;
    private static final int DEFAULT_COLLAPSED_MAX_COUNT = 7;
    private static final int[] DISABLED_STATE_SET = null;
    private static final int NO_PADDING = -1;
    private static final int NO_SELECTED_ITEM = -1;
    private final SparseArray<BadgeDrawable> badgeDrawables;
    private NavigationBarMenuItemView[] buttons;
    private MenuItem checkedItem;
    private int collapsedMaxItemCount;
    private boolean dividersEnabled;
    private boolean expanded;
    private int horizontalItemTextAppearanceActive;
    private int horizontalItemTextAppearanceInactive;
    private int iconLabelHorizontalSpacing;
    private ColorStateList itemActiveIndicatorColor;
    private boolean itemActiveIndicatorEnabled;
    private int itemActiveIndicatorExpandedHeight;
    private int itemActiveIndicatorExpandedMarginHorizontal;
    private final Rect itemActiveIndicatorExpandedPadding;
    private int itemActiveIndicatorExpandedWidth;
    private int itemActiveIndicatorHeight;
    private int itemActiveIndicatorLabelPadding;
    private int itemActiveIndicatorMarginHorizontal;
    private boolean itemActiveIndicatorResizeable;
    private ShapeAppearanceModel itemActiveIndicatorShapeAppearance;
    private int itemActiveIndicatorWidth;
    private Drawable itemBackground;
    private int itemBackgroundRes;
    private int itemGravity;
    private int itemIconGravity;
    private int itemIconSize;
    private ColorStateList itemIconTint;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private Pools$Pool<NavigationBarItemView> itemPool;
    private int itemPoolSize;
    private ColorStateList itemRippleColor;
    private int itemTextAppearanceActive;
    private boolean itemTextAppearanceActiveBoldEnabled;
    private int itemTextAppearanceInactive;
    private final ColorStateList itemTextColorDefault;
    private ColorStateList itemTextColorFromUser;
    private int labelMaxLines;
    private int labelVisibilityMode;
    private boolean measurePaddingFromLabelBaseline;
    private NavigationBarMenuBuilder menu;
    private final View.OnClickListener onClickListener;
    private final SparseArray<View.OnTouchListener> onTouchListeners;
    private NavigationBarPresenter presenter;
    private boolean scaleLabelWithFont;
    private int selectedItemId;
    private int selectedItemPosition;
    private final TransitionSet set;

    /* renamed from: com.google.android.material.navigation.NavigationBarMenuView$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ NavigationBarMenuView this$0;

        public AnonymousClass1(NavigationBarMenuView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r4) {
            MenuItemImpl r42 = ((NavigationBarItemView) r4).getItemData();
            boolean r0 = NavigationBarMenuView.access$100(this.this$0).performItemAction(r42, NavigationBarMenuView.access$000(this.this$0), 0);
            if (r42 != null) goto L5;
            return;
        L5:
            if (r42.isCheckable() == false) goto L12;
            if (r0 == true) goto L8;
        L9:
            this.this$0.setCheckedItem(r42);
            return;
        L8:
            if (r42.isChecked() == true) goto L9;
            return;
        }
    }

    static {
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
        DISABLED_STATE_SET = new int[]{-16842910};
    }

    public NavigationBarMenuView(Context r6) {
        super(r6);
        this.onTouchListeners = new SparseArray();
        this.selectedItemId = -1;
        this.selectedItemPosition = -1;
        this.badgeDrawables = new SparseArray();
        this.itemPaddingTop = -1;
        this.itemPaddingBottom = -1;
        this.itemActiveIndicatorLabelPadding = -1;
        this.iconLabelHorizontalSpacing = -1;
        this.itemGravity = 49;
        this.itemActiveIndicatorResizeable = false;
        this.labelMaxLines = 1;
        this.itemPoolSize = 0;
        this.checkedItem = null;
        this.collapsedMaxItemCount = 7;
        this.dividersEnabled = false;
        this.itemActiveIndicatorExpandedPadding = new Rect();
        this.itemTextColorDefault = createDefaultColorStateList(R.attr.textColorSecondary);
        if (isInEditMode() == false) goto L5;
        this.set = null;
    L6:
        this.onClickListener = new AnonymousClass1(this);
        setImportantForAccessibility(1);
        return;
    L5:
        AutoTransition r1 = new AutoTransition();
        this.set = r1;
        r1.setOrdering(0);
        r1.excludeTarget(TextView.class, true);
        r1.setDuration(MotionUtils.resolveThemeDuration(getContext(), com.google.android.material.R.attr.motionDurationMedium4, getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1)));
        r1.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), com.google.android.material.R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        r1.addTransition(new TextScale());
        goto L6
    }

    public static /* synthetic */ NavigationBarPresenter access$000(NavigationBarMenuView r0) {
        return r0.presenter;
    }

    public static /* synthetic */ NavigationBarMenuBuilder access$100(NavigationBarMenuView r0) {
        return r0.menu;
    }

    private Drawable createItemActiveIndicatorDrawable() {
        if (this.itemActiveIndicatorShapeAppearance != null) goto L5;
        return null;
    L5:
        if (this.itemActiveIndicatorColor == null) goto L10;
        MaterialShapeDrawable r0 = new MaterialShapeDrawable(this.itemActiveIndicatorShapeAppearance);
        r0.setFillColor(this.itemActiveIndicatorColor);
        return r0;
    L10:
        return null;
    }

    private NavigationBarItemView createMenuItem(int r4, MenuItemImpl r5, boolean r6, boolean r7) {
        this.presenter.setUpdateSuspended(true);
        r5.setCheckable(true);
        this.presenter.setUpdateSuspended(false);
        NavigationBarItemView r0 = getNewItem();
        r0.setShifting(r6);
        r0.setLabelMaxLines(this.labelMaxLines);
        r0.setIconTintList(this.itemIconTint);
        r0.setIconSize(this.itemIconSize);
        r0.setTextColor(this.itemTextColorDefault);
        r0.setTextAppearanceInactive(this.itemTextAppearanceInactive);
        r0.setTextAppearanceActive(this.itemTextAppearanceActive);
        r0.setHorizontalTextAppearanceInactive(this.horizontalItemTextAppearanceInactive);
        r0.setHorizontalTextAppearanceActive(this.horizontalItemTextAppearanceActive);
        r0.setTextAppearanceActiveBoldEnabled(this.itemTextAppearanceActiveBoldEnabled);
        r0.setTextColor(this.itemTextColorFromUser);
        int r62 = this.itemPaddingTop;
        if (r62 == (-1)) goto L5;
        r0.setItemPaddingTop(r62);
    L5:
        int r63 = this.itemPaddingBottom;
        if (r63 == (-1)) goto L8;
        r0.setItemPaddingBottom(r63);
    L8:
        r0.setMeasureBottomPaddingFromLabelBaseline(this.measurePaddingFromLabelBaseline);
        r0.setLabelFontScalingEnabled(this.scaleLabelWithFont);
        int r64 = this.itemActiveIndicatorLabelPadding;
        if (r64 == (-1)) goto L11;
        r0.setActiveIndicatorLabelPadding(r64);
    L11:
        int r65 = this.iconLabelHorizontalSpacing;
        if (r65 == (-1)) goto L14;
        r0.setIconLabelHorizontalSpacing(r65);
    L14:
        r0.setActiveIndicatorWidth(this.itemActiveIndicatorWidth);
        r0.setActiveIndicatorHeight(this.itemActiveIndicatorHeight);
        r0.setActiveIndicatorExpandedWidth(this.itemActiveIndicatorExpandedWidth);
        r0.setActiveIndicatorExpandedHeight(this.itemActiveIndicatorExpandedHeight);
        r0.setActiveIndicatorMarginHorizontal(this.itemActiveIndicatorMarginHorizontal);
        r0.setItemGravity(this.itemGravity);
        r0.setActiveIndicatorExpandedPadding(this.itemActiveIndicatorExpandedPadding);
        r0.setActiveIndicatorExpandedMarginHorizontal(this.itemActiveIndicatorExpandedMarginHorizontal);
        r0.setActiveIndicatorDrawable(createItemActiveIndicatorDrawable());
        r0.setActiveIndicatorResizeable(this.itemActiveIndicatorResizeable);
        r0.setActiveIndicatorEnabled(this.itemActiveIndicatorEnabled);
        Drawable r66 = this.itemBackground;
        if (r66 == null) goto L17;
        r0.setItemBackground(r66);
    L18:
        r0.setItemRippleColor(this.itemRippleColor);
        r0.setLabelVisibilityMode(this.labelVisibilityMode);
        r0.setItemIconGravity(this.itemIconGravity);
        r0.setOnlyShowWhenExpanded(r7);
        r0.setExpanded(this.expanded);
        r0.initialize(r5, 0);
        r0.setItemPosition(r4);
        int r52 = r5.getItemId();
        r0.setOnTouchListener(this.onTouchListeners.get(r52));
        r0.setOnClickListener(this.onClickListener);
        int r67 = this.selectedItemId;
        if (r67 == 0) goto L22;
        if (r52 != r67) goto L22;
        this.selectedItemPosition = r4;
    L22:
        setBadgeIfNeeded(r0);
        return r0;
    L17:
        r0.setItemBackground(this.itemBackgroundRes);
        goto L18
    }

    private int getCollapsedVisibleItemCount() {
        return Math.min(this.collapsedMaxItemCount, this.menu.getVisibleMainContentItemCount());
    }

    private NavigationBarItemView getNewItem() {
        Pools$Pool<NavigationBarItemView> r0 = this.itemPool;
        if (r0 == null) goto L5;
        NavigationBarItemView r02 = r0.acquire();
    L6:
        if (r02 == null) goto L8;
        return r02;
    L8:
        return createNavigationBarItemView(getContext());
    L5:
        r02 = null;
        goto L6
    }

    private boolean isMenuStructureSame() {
        if (this.buttons == null) goto L36;
        NavigationBarMenuBuilder r0 = this.menu;
        if (r0 == null) goto L36;
        if (r0.size() != this.buttons.length) goto L36;
        int r02 = 0;
    L10:
        boolean r3 = true;
        if (r02 >= this.buttons.length) goto L35;
        if ((this.menu.getItemAt(r02) instanceof DividerMenuItem) == false) goto L18;
        if ((this.buttons[r02] instanceof NavigationBarDividerView) == true) goto L18;
        return false;
    L18:
        if (this.menu.getItemAt(r02).hasSubMenu() == true) goto L20;
    L22:
        boolean r2 = false;
    L24:
        if (this.menu.getItemAt(r02).hasSubMenu() == false) goto L26;
    L28:
        r3 = false;
    L30:
        if ((this.menu.getItemAt(r02) instanceof DividerMenuItem) == true) goto L34;
        if (r2 == true) goto L33;
        if (r3 == false) goto L34;
    L33:
        return false;
    L34:
        r02 = r02 + 1;
        goto L10
    L26:
        if ((this.buttons[r02] instanceof NavigationBarItemView) == true) goto L28;
    L20:
        if ((this.buttons[r02] instanceof NavigationBarSubheaderView) == true) goto L22;
        r2 = true;
        goto L24
    L35:
        return true;
    L36:
        return false;
    }

    private boolean isValidId(int r1) {
        if (r1 == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    private void releaseItemPool() {
        NavigationBarMenuItemView[] r0 = this.buttons;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.itemPool == null) goto L16;
        int r1 = r0.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L17;
        NavigationBarMenuItemView r3 = r0[r2];
        if ((r3 instanceof NavigationBarItemView) == false) goto L11;
        NavigationBarItemView r32 = (NavigationBarItemView) r3;
        this.itemPool.release(r32);
        r32.clear();
    L11:
        r2 = r2 + 1;
        goto L7
    L17:
        return;
    }

    private void removeUnusedBadges() {
        HashSet r0 = new HashSet();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= this.menu.size()) goto L7;
        r0.add(Integer.valueOf(this.menu.getItemAt(r2).getItemId()));
        r2 = r2 + 1;
    L7:
        if (r1 >= this.badgeDrawables.size()) goto L12;
        int r22 = this.badgeDrawables.keyAt(r1);
        if (r0.contains(Integer.valueOf(r22)) == true) goto L11;
        this.badgeDrawables.delete(r22);
    L11:
        r1 = r1 + 1;
        goto L7
    }

    private void setBadgeIfNeeded(NavigationBarItemView r3) {
        int r0 = r3.getId();
        if (isValidId(r0) == false) goto L10;
        BadgeDrawable r2 = this.badgeDrawables.get(r0);
        if (r2 == null) goto L9;
        r3.setBadge(r2);
        return;
    L9:
        return;
    }

    private void validateMenuItemId(int r2) {
        if (isValidId(r2) == false) goto L6;
        return;
    L6:
        throw new IllegalArgumentException(r2 + " is not a valid view id");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"ClickableViewAccessibility"})
    public void buildMenuView() {
        removeAllViews();
        releaseItemPool();
        this.presenter.setUpdateSuspended(true);
        this.menu.refreshItems();
        this.presenter.setUpdateSuspended(false);
        int r0 = this.menu.getContentItemCount();
        if (r0 != 0) goto L7;
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        this.buttons = null;
        this.itemPool = null;
        return;
    L7:
        if (this.itemPool != null) goto L9;
    L10:
        this.itemPoolSize = r0;
        this.itemPool = new Pools$SynchronizedPool(r0);
    L11:
        removeUnusedBadges();
        int r02 = this.menu.size();
        this.buttons = new NavigationBarMenuItemView[r02];
        boolean r3 = isShifting(this.labelVisibilityMode, getCurrentVisibleContentItemCount());
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
    L12:
        if (r4 >= r02) goto L40;
        MenuItem r7 = this.menu.getItemAt(r4);
        boolean r8 = r7 instanceof DividerMenuItem;
        if (r8 == false) goto L17;
        NavigationBarDividerView r9 = new NavigationBarDividerView(getContext());
        r9.setOnlyShowWhenExpanded(true);
        r9.setDividersEnabled(this.dividersEnabled);
        NavigationBarItemView r92 = r9;
    L33:
        if (r8 == true) goto L39;
        if (r7.isCheckable() == false) goto L39;
        if (this.selectedItemPosition != (-1)) goto L39;
        this.selectedItemPosition = r4;
    L39:
        this.buttons[r4] = r92;
        addView(r92);
        r4 = r4 + 1;
        goto L12
    L17:
        if (r7.hasSubMenu() == false) goto L26;
        if (r5 > 0) goto L24;
        NavigationBarSubheaderView r93 = new NavigationBarSubheaderView(getContext());
        int r52 = this.horizontalItemTextAppearanceActive;
        if (r52 != 0) goto L23;
        r52 = this.itemTextAppearanceActive;
    L23:
        r93.setTextAppearance(r52);
        r93.setTextColor(this.itemTextColorFromUser);
        r93.setOnlyShowWhenExpanded(true);
        r93.initialize((MenuItemImpl) r7, 0);
        r5 = r7.getSubMenu().size();
        r92 = r93;
        goto L33
    L24:
        f$$ExternalSyntheticBUOutline1.m("Only one layer of submenu is supported; a submenu inside a submenu is not supported by the Navigation Bar.");
        return;
    L26:
        if (r5 <= 0) goto L28;
        r5 = r5 - 1;
        r92 = createMenuItem(r4, (MenuItemImpl) r7, r3, true);
        goto L33
    L28:
        MenuItemImpl r94 = (MenuItemImpl) r7;
        if (r6 < this.collapsedMaxItemCount) goto L31;
        boolean r10 = true;
    L32:
        r6 = r6 + 1;
        r92 = createMenuItem(r4, r94, r3, r10);
        goto L33
    L31:
        r10 = false;
        goto L32
    L40:
        int r03 = Math.min(r02 - 1, this.selectedItemPosition);
        this.selectedItemPosition = r03;
        setCheckedItem(this.buttons[r03].getItemData());
        return;
    L9:
        if (this.itemPoolSize == r0) goto L11;
        goto L10
    }

    public ColorStateList createDefaultColorStateList(int r6) {
        TypedValue r0 = new TypedValue();
        if (getContext().getTheme().resolveAttribute(r6, r0, true) == true) goto L5;
        return null;
    L5:
        ColorStateList r62 = AppCompatResources.getColorStateList(getContext(), r0.resourceId);
        if (getContext().getTheme().resolveAttribute(R$attr.colorPrimary, r0, true) == true) goto L8;
        return null;
    L8:
        int r5 = r0.data;
        int r02 = r62.getDefaultColor();
        int[] r2 = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{r2, CHECKED_STATE_SET, ViewGroup.EMPTY_STATE_SET}, new int[]{r62.getColorForState(r2, r02), r5, r02});
    }

    public abstract NavigationBarItemView createNavigationBarItemView(Context r1);

    public NavigationBarItemView findItemView(int r5) {
        validateMenuItemId(r5);
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L12;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L18;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L11;
        NavigationBarItemView r22 = (NavigationBarItemView) r2;
        if (r22.getId() != r5) goto L11;
        return r22;
    L11:
        r1 = r1 + 1;
        goto L5
    L18:
        return null;
    L12:
        return null;
    }

    public int getActiveIndicatorLabelPadding() {
        return this.itemActiveIndicatorLabelPadding;
    }

    public BadgeDrawable getBadge(int r1) {
        return this.badgeDrawables.get(r1);
    }

    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.badgeDrawables;
    }

    public int getCurrentVisibleContentItemCount() {
        if (this.expanded == false) goto L7;
        return this.menu.getVisibleContentItemCount();
    L7:
        return getCollapsedVisibleItemCount();
    }

    public int getHorizontalItemTextAppearanceActive() {
        return this.horizontalItemTextAppearanceActive;
    }

    public int getHorizontalItemTextAppearanceInactive() {
        return this.horizontalItemTextAppearanceInactive;
    }

    public int getIconLabelHorizontalSpacing() {
        return this.iconLabelHorizontalSpacing;
    }

    public ColorStateList getIconTintList() {
        return this.itemIconTint;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.itemActiveIndicatorColor;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.itemActiveIndicatorEnabled;
    }

    public int getItemActiveIndicatorExpandedHeight() {
        return this.itemActiveIndicatorExpandedHeight;
    }

    public int getItemActiveIndicatorExpandedMarginHorizontal() {
        return this.itemActiveIndicatorExpandedMarginHorizontal;
    }

    public int getItemActiveIndicatorExpandedWidth() {
        return this.itemActiveIndicatorExpandedWidth;
    }

    public int getItemActiveIndicatorHeight() {
        return this.itemActiveIndicatorHeight;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.itemActiveIndicatorMarginHorizontal;
    }

    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.itemActiveIndicatorShapeAppearance;
    }

    public int getItemActiveIndicatorWidth() {
        return this.itemActiveIndicatorWidth;
    }

    public Drawable getItemBackground() {
        NavigationBarMenuItemView[] r0 = this.buttons;
        if (r0 == null) goto L14;
        if (r0.length <= 0) goto L14;
        int r1 = r0.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L14;
        NavigationBarMenuItemView r3 = r0[r2];
        if ((r3 instanceof NavigationBarItemView) == true) goto L11;
        r2 = r2 + 1;
        goto L7
    L11:
        return ((NavigationBarItemView) r3).getBackground();
    L14:
        return this.itemBackground;
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.itemBackgroundRes;
    }

    public int getItemGravity() {
        return this.itemGravity;
    }

    public int getItemIconGravity() {
        return this.itemIconGravity;
    }

    public int getItemIconSize() {
        return this.itemIconSize;
    }

    public int getItemPaddingBottom() {
        return this.itemPaddingBottom;
    }

    public int getItemPaddingTop() {
        return this.itemPaddingTop;
    }

    public ColorStateList getItemRippleColor() {
        return this.itemRippleColor;
    }

    public int getItemTextAppearanceActive() {
        return this.itemTextAppearanceActive;
    }

    public int getItemTextAppearanceInactive() {
        return this.itemTextAppearanceInactive;
    }

    public ColorStateList getItemTextColor() {
        return this.itemTextColorFromUser;
    }

    public int getLabelMaxLines() {
        return this.labelMaxLines;
    }

    public int getLabelVisibilityMode() {
        return this.labelVisibilityMode;
    }

    public NavigationBarMenuBuilder getMenu() {
        return this.menu;
    }

    public BadgeDrawable getOrCreateBadge(int r3) {
        validateMenuItemId(r3);
        BadgeDrawable r0 = this.badgeDrawables.get(r3);
        if (r0 != null) goto L5;
        r0 = BadgeDrawable.create(getContext());
        this.badgeDrawables.put(r3, r0);
    L5:
        NavigationBarItemView r2 = findItemView(r3);
        if (r2 == null) goto L8;
        r2.setBadge(r0);
    L8:
        return r0;
    }

    public boolean getScaleLabelTextWithFont() {
        return this.scaleLabelWithFont;
    }

    public int getSelectedItemId() {
        return this.selectedItemId;
    }

    public int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder r2) {
        this.menu = new NavigationBarMenuBuilder(r2);
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public boolean isItemActiveIndicatorResizeable() {
        return this.itemActiveIndicatorResizeable;
    }

    public boolean isShifting(int r3, int r4) {
        if (r3 == (-1)) goto L5;
        if (r3 != 0) goto L10;
        return true;
    L10:
        return false;
    L5:
        if (r4 <= 3) goto L7;
        return true;
    L7:
        return false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        AccessibilityNodeInfoCompat.wrap(r3).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getCurrentVisibleContentItemCount(), false, 1));
    }

    public void removeBadge(int r2) {
        validateMenuItemId(r2);
        NavigationBarItemView r0 = findItemView(r2);
        if (r0 == null) goto L5;
        r0.removeBadge();
    L5:
        this.badgeDrawables.put(r2, null);
    }

    public void restoreBadgeDrawables(SparseArray<BadgeDrawable> r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L9;
        int r2 = r6.keyAt(r1);
        if (this.badgeDrawables.indexOfKey(r2) >= 0) goto L8;
        this.badgeDrawables.append(r2, r6.get(r2));
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        NavigationBarMenuItemView[] r62 = this.buttons;
        if (r62 == null) goto L19;
        int r12 = r62.length;
    L12:
        if (r0 >= r12) goto L27;
        NavigationBarMenuItemView r22 = r62[r0];
        if ((r22 instanceof NavigationBarItemView) == false) goto L18;
        NavigationBarItemView r23 = (NavigationBarItemView) r22;
        BadgeDrawable r3 = this.badgeDrawables.get(r23.getId());
        if (r3 == null) goto L18;
        r23.setBadge(r3);
    L18:
        r0 = r0 + 1;
        goto L12
    L27:
        return;
    }

    public void setActiveIndicatorLabelPadding(int r5) {
        this.itemActiveIndicatorLabelPadding = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorLabelPadding(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setCheckedItem(MenuItem r3) {
        if (this.checkedItem != r3) goto L5;
        return;
    L5:
        if (r3.isCheckable() == false) goto L15;
        MenuItem r0 = this.checkedItem;
        if (r0 != null) goto L10;
    L12:
        r3.setChecked(true);
        this.checkedItem = r3;
        return;
    L10:
        if (r0.isChecked() == false) goto L12;
        this.checkedItem.setChecked(false);
        goto L12
    }

    public void setCollapsedMaxItemCount(int r1) {
        this.collapsedMaxItemCount = r1;
    }

    public void setExpanded(boolean r4) {
        this.expanded = r4;
        NavigationBarMenuItemView[] r3 = this.buttons;
        if (r3 == null) goto L7;
        int r0 = r3.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        r3[r1].setExpanded(r4);
        r1 = r1 + 1;
        goto L5
    L9:
        return;
    }

    public void setHorizontalItemTextAppearanceActive(int r5) {
        this.horizontalItemTextAppearanceActive = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setHorizontalTextAppearanceActive(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setHorizontalItemTextAppearanceInactive(int r5) {
        this.horizontalItemTextAppearanceInactive = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setHorizontalTextAppearanceInactive(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setIconLabelHorizontalSpacing(int r5) {
        this.iconLabelHorizontalSpacing = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setIconLabelHorizontalSpacing(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setIconTintList(ColorStateList r5) {
        this.itemIconTint = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setIconTintList(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorColor(ColorStateList r5) {
        this.itemActiveIndicatorColor = r5;
        NavigationBarMenuItemView[] r52 = this.buttons;
        if (r52 == null) goto L10;
        int r0 = r52.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r52[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorDrawable(createItemActiveIndicatorDrawable());
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorEnabled(boolean r5) {
        this.itemActiveIndicatorEnabled = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorEnabled(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorExpandedHeight(int r5) {
        this.itemActiveIndicatorExpandedHeight = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorExpandedHeight(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorExpandedMarginHorizontal(int r5) {
        this.itemActiveIndicatorExpandedMarginHorizontal = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorExpandedMarginHorizontal(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorExpandedPadding(int r2, int r3, int r4, int r5) {
        Rect r0 = this.itemActiveIndicatorExpandedPadding;
        r0.left = r2;
        r0.top = r3;
        r0.right = r4;
        r0.bottom = r5;
        NavigationBarMenuItemView[] r22 = this.buttons;
        if (r22 == null) goto L10;
        int r32 = r22.length;
        int r42 = 0;
    L5:
        if (r42 >= r32) goto L14;
        NavigationBarMenuItemView r52 = r22[r42];
        if ((r52 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r52).setActiveIndicatorExpandedPadding(this.itemActiveIndicatorExpandedPadding);
    L9:
        r42 = r42 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorExpandedWidth(int r5) {
        this.itemActiveIndicatorExpandedWidth = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorExpandedWidth(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorHeight(int r5) {
        this.itemActiveIndicatorHeight = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorHeight(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorMarginHorizontal(int r5) {
        this.itemActiveIndicatorMarginHorizontal = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorMarginHorizontal(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorResizeable(boolean r5) {
        this.itemActiveIndicatorResizeable = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorResizeable(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel r5) {
        this.itemActiveIndicatorShapeAppearance = r5;
        NavigationBarMenuItemView[] r52 = this.buttons;
        if (r52 == null) goto L10;
        int r0 = r52.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r52[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorDrawable(createItemActiveIndicatorDrawable());
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemActiveIndicatorWidth(int r5) {
        this.itemActiveIndicatorWidth = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setActiveIndicatorWidth(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemBackground(Drawable r5) {
        this.itemBackground = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setItemBackground(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemBackgroundRes(int r5) {
        this.itemBackgroundRes = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setItemBackground(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemGravity(int r5) {
        this.itemGravity = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setItemGravity(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemIconGravity(int r5) {
        this.itemIconGravity = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setItemIconGravity(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemIconSize(int r5) {
        this.itemIconSize = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setIconSize(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setItemOnTouchListener(int r5, View.OnTouchListener r6) {
        SparseArray<View.OnTouchListener> r0 = this.onTouchListeners;
        if (r6 != null) goto L5;
        r0.remove(r5);
    L6:
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L18;
        int r02 = r4.length;
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L24;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L17;
        if (r2.getItemData() == null) goto L17;
        if (r2.getItemData().getItemId() != r5) goto L17;
        ((NavigationBarItemView) r2).setOnTouchListener(r6);
    L17:
        r1 = r1 + 1;
        goto L9
    L24:
        return;
    L18:
        return;
    L5:
        r0.put(r5, r6);
        goto L6
    }

    public void setItemPaddingBottom(int r5) {
        this.itemPaddingBottom = r5;
        NavigationBarMenuItemView[] r52 = this.buttons;
        if (r52 == null) goto L10;
        int r0 = r52.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r52[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setItemPaddingBottom(this.itemPaddingBottom);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemPaddingTop(int r5) {
        this.itemPaddingTop = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setItemPaddingTop(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemRippleColor(ColorStateList r5) {
        this.itemRippleColor = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setItemRippleColor(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemTextAppearanceActive(int r5) {
        this.itemTextAppearanceActive = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setTextAppearanceActive(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean r5) {
        this.itemTextAppearanceActiveBoldEnabled = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setTextAppearanceActiveBoldEnabled(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemTextAppearanceInactive(int r5) {
        this.itemTextAppearanceInactive = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setTextAppearanceInactive(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setItemTextColor(ColorStateList r5) {
        this.itemTextColorFromUser = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setTextColor(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setLabelFontScalingEnabled(boolean r5) {
        this.scaleLabelWithFont = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setLabelFontScalingEnabled(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setLabelMaxLines(int r5) {
        this.labelMaxLines = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setLabelMaxLines(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setLabelVisibilityMode(int r1) {
        this.labelVisibilityMode = r1;
    }

    public void setMeasurePaddingFromLabelBaseline(boolean r5) {
        this.measurePaddingFromLabelBaseline = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).setMeasureBottomPaddingFromLabelBaseline(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void setPresenter(NavigationBarPresenter r1) {
        this.presenter = r1;
    }

    public void setSubmenuDividersEnabled(boolean r5) {
        if (this.dividersEnabled == r5) goto L17;
        this.dividersEnabled = r5;
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L13;
        int r0 = r4.length;
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L18;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarDividerView) == false) goto L12;
        ((NavigationBarDividerView) r2).setDividersEnabled(r5);
    L12:
        r1 = r1 + 1;
        goto L8
    L18:
        return;
    L13:
        return;
    }

    public void tryRestoreSelectedItemId(int r5) {
        int r0 = this.menu.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        MenuItem r2 = this.menu.getItemAt(r1);
        if (r5 == r2.getItemId()) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        this.selectedItemId = r5;
        this.selectedItemPosition = r1;
        setCheckedItem(r2);
        return;
    }

    public void updateActiveIndicator(int r5) {
        NavigationBarMenuItemView[] r4 = this.buttons;
        if (r4 == null) goto L10;
        int r0 = r4.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        NavigationBarMenuItemView r2 = r4[r1];
        if ((r2 instanceof NavigationBarItemView) == false) goto L9;
        ((NavigationBarItemView) r2).updateActiveIndicatorLayoutParams(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public void updateMenuView() {
        if (this.menu != null) goto L5;
        return;
    L5:
        if (this.buttons == null) goto L38;
        this.presenter.setUpdateSuspended(true);
        this.menu.refreshItems();
        this.presenter.setUpdateSuspended(false);
        if (isMenuStructureSame() == true) goto L11;
        buildMenuView();
        return;
    L11:
        int r0 = this.selectedItemId;
        int r3 = this.menu.size();
        int r4 = 0;
    L12:
        if (r4 >= r3) goto L18;
        MenuItem r5 = this.menu.getItemAt(r4);
        if (r5.isChecked() == false) goto L16;
        setCheckedItem(r5);
        this.selectedItemId = r5.getItemId();
        this.selectedItemPosition = r4;
    L16:
        r4 = r4 + 1;
        goto L12
    L18:
        if (r0 == this.selectedItemId) goto L22;
        TransitionSet r02 = this.set;
        if (r02 == null) goto L22;
        TransitionManager.beginDelayedTransition(this, r02);
    L22:
        boolean r03 = isShifting(this.labelVisibilityMode, getCurrentVisibleContentItemCount());
        int r42 = 0;
    L23:
        if (r42 >= r3) goto L39;
        this.presenter.setUpdateSuspended(true);
        this.buttons[r42].setExpanded(this.expanded);
        NavigationBarMenuItemView r52 = this.buttons[r42];
        if ((r52 instanceof NavigationBarItemView) == false) goto L28;
        NavigationBarItemView r53 = (NavigationBarItemView) r52;
        r53.setLabelVisibilityMode(this.labelVisibilityMode);
        r53.setItemIconGravity(this.itemIconGravity);
        r53.setItemGravity(this.itemGravity);
        r53.setShifting(r03);
    L28:
        if ((this.menu.getItemAt(r42) instanceof MenuItemImpl) == false) goto L30;
        this.buttons[r42].initialize((MenuItemImpl) this.menu.getItemAt(r42), 0);
    L30:
        this.presenter.setUpdateSuspended(false);
        r42 = r42 + 1;
        goto L23
    L39:
        return;
    }
}
