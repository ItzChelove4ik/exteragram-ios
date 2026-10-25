package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes5.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final int ACTIVE_INDICATOR_WIDTH_MATCH_PARENT = -1;
    public static final int ACTIVE_INDICATOR_WIDTH_WRAP_CONTENT = -2;
    public static final int ITEM_GRAVITY_CENTER = 17;
    public static final int ITEM_GRAVITY_START_CENTER = 8388627;
    public static final int ITEM_GRAVITY_TOP_CENTER = 49;
    public static final int ITEM_ICON_GRAVITY_START = 1;
    public static final int ITEM_ICON_GRAVITY_TOP = 0;
    public static final int LABEL_VISIBILITY_AUTO = -1;
    public static final int LABEL_VISIBILITY_LABELED = 1;
    public static final int LABEL_VISIBILITY_SELECTED = 0;
    public static final int LABEL_VISIBILITY_UNLABELED = 2;
    private static final int MENU_PRESENTER_ID = 1;
    private final NavigationBarMenu menu;
    private MenuInflater menuInflater;
    private final NavigationBarMenuView menuView;
    private final NavigationBarPresenter presenter;
    private OnItemReselectedListener reselectedListener;
    private OnItemSelectedListener selectedListener;

    /* renamed from: com.google.android.material.navigation.NavigationBarView$1, reason: invalid class name */
    public class AnonymousClass1 implements MenuBuilder.Callback {
        final /* synthetic */ NavigationBarView this$0;

        public AnonymousClass1(NavigationBarView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder r3, MenuItem r4) {
            if (NavigationBarView.access$000(this.this$0) == null) goto L9;
            if (r4.getItemId() != this.this$0.getSelectedItemId()) goto L9;
            NavigationBarView.access$000(this.this$0).onNavigationItemReselected(r4);
            return true;
        L9:
            if (NavigationBarView.access$100(this.this$0) != null) goto L11;
            return false;
        L11:
            if (NavigationBarView.access$100(this.this$0).onNavigationItemSelected(r4) == true) goto L15;
            return true;
        L15:
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder r1) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemIconGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LabelVisibility {
    }

    public interface OnItemReselectedListener {
        void onNavigationItemReselected(MenuItem r1);
    }

    public interface OnItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem r1);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        Bundle menuPresenterState;

        /* renamed from: com.google.android.material.navigation.NavigationBarView$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return new SavedState(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return createFromParcel(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            if (r2 != null) goto L5;
            r2 = getClass().getClassLoader();
        L5:
            readFromParcel(r1, r2);
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        private void readFromParcel(Parcel r1, ClassLoader r2) {
            this.menuPresenterState = r1.readBundle(r2);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeBundle(this.menuPresenterState);
        }
    }

    public NavigationBarView(Context r10, AttributeSet r11, int r12, int r13) {
        super(MaterialThemeOverlay.wrap(r10, r11, r12, r13), r11, r12);
        NavigationBarPresenter r102 = new NavigationBarPresenter();
        this.presenter = r102;
        Context r0 = getContext();
        TintTypedArray r112 = ThemeEnforcement.obtainTintedStyledAttributes(r0, r11, R.styleable.NavigationBarView, r12, r13, new int[]{R.styleable.NavigationBarView_itemTextAppearanceInactive, R.styleable.NavigationBarView_itemTextAppearanceActive});
        NavigationBarMenu r122 = new NavigationBarMenu(r0, getClass(), getMaxItemCount(), isSubMenuSupported());
        this.menu = r122;
        NavigationBarMenuView r132 = createNavigationBarMenuView(r0);
        this.menuView = r132;
        r132.setMinimumHeight(getSuggestedMinimumHeight());
        r132.setCollapsedMaxItemCount(getCollapsedMaxItemCount());
        r102.setMenuView(r132);
        r102.setId(1);
        r132.setPresenter(r102);
        r122.addMenuPresenter(r102);
        r102.initForMenu(getContext(), r122);
        if (r112.hasValue(R.styleable.NavigationBarView_itemIconTint) == false) goto L5;
        r132.setIconTintList(r112.getColorStateList(R.styleable.NavigationBarView_itemIconTint));
    L6:
        setItemIconSize(r112.getDimensionPixelSize(R.styleable.NavigationBarView_itemIconSize, getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_bar_item_default_icon_size)));
        if (r112.hasValue(R.styleable.NavigationBarView_itemTextAppearanceInactive) == false) goto L10;
        setItemTextAppearanceInactive(r112.getResourceId(R.styleable.NavigationBarView_itemTextAppearanceInactive, 0));
    L10:
        if (r112.hasValue(R.styleable.NavigationBarView_itemTextAppearanceActive) == false) goto L13;
        setItemTextAppearanceActive(r112.getResourceId(R.styleable.NavigationBarView_itemTextAppearanceActive, 0));
    L13:
        if (r112.hasValue(R.styleable.NavigationBarView_horizontalItemTextAppearanceInactive) == false) goto L16;
        setHorizontalItemTextAppearanceInactive(r112.getResourceId(R.styleable.NavigationBarView_horizontalItemTextAppearanceInactive, 0));
    L16:
        if (r112.hasValue(R.styleable.NavigationBarView_horizontalItemTextAppearanceActive) == false) goto L18;
        setHorizontalItemTextAppearanceActive(r112.getResourceId(R.styleable.NavigationBarView_horizontalItemTextAppearanceActive, 0));
    L18:
        setItemTextAppearanceActiveBoldEnabled(r112.getBoolean(R.styleable.NavigationBarView_itemTextAppearanceActiveBoldEnabled, true));
        if (r112.hasValue(R.styleable.NavigationBarView_itemTextColor) == false) goto L21;
        setItemTextColor(r112.getColorStateList(R.styleable.NavigationBarView_itemTextColor));
    L21:
        Drawable r103 = getBackground();
        ColorStateList r6 = DrawableUtils.getColorStateListOrNull(r103);
        if (r103 == null) goto L24;
        if (r6 != null) goto L24;
    L29:
        if (r112.hasValue(R.styleable.NavigationBarView_itemPaddingTop) == false) goto L32;
        setItemPaddingTop(r112.getDimensionPixelSize(R.styleable.NavigationBarView_itemPaddingTop, 0));
    L32:
        if (r112.hasValue(R.styleable.NavigationBarView_itemPaddingBottom) == false) goto L35;
        setItemPaddingBottom(r112.getDimensionPixelSize(R.styleable.NavigationBarView_itemPaddingBottom, 0));
    L35:
        if (r112.hasValue(R.styleable.NavigationBarView_activeIndicatorLabelPadding) == false) goto L38;
        setActiveIndicatorLabelPadding(r112.getDimensionPixelSize(R.styleable.NavigationBarView_activeIndicatorLabelPadding, 0));
    L38:
        if (r112.hasValue(R.styleable.NavigationBarView_iconLabelHorizontalSpacing) == false) goto L41;
        setIconLabelHorizontalSpacing(r112.getDimensionPixelSize(R.styleable.NavigationBarView_iconLabelHorizontalSpacing, 0));
    L41:
        if (r112.hasValue(R.styleable.NavigationBarView_elevation) == false) goto L43;
        setElevation(r112.getDimensionPixelSize(R.styleable.NavigationBarView_elevation, 0));
    L43:
        getBackground().mutate().setTintList(MaterialResources.getColorStateList(r0, r112, R.styleable.NavigationBarView_backgroundTint));
        int r1 = -1;
        setLabelVisibilityMode(r112.getInteger(R.styleable.NavigationBarView_labelVisibilityMode, -1));
        setItemIconGravity(r112.getInteger(R.styleable.NavigationBarView_itemIconGravity, 0));
        setItemGravity(r112.getInteger(R.styleable.NavigationBarView_itemGravity, 49));
        int r104 = r112.getResourceId(R.styleable.NavigationBarView_itemBackground, 0);
        if (r104 == 0) goto L46;
        r132.setItemBackgroundRes(r104);
    L47:
        setMeasureBottomPaddingFromLabelBaseline(r112.getBoolean(R.styleable.NavigationBarView_measureBottomPaddingFromLabelBaseline, true));
        setLabelFontScalingEnabled(r112.getBoolean(R.styleable.NavigationBarView_labelFontScalingEnabled, false));
        setLabelMaxLines(r112.getInteger(R.styleable.NavigationBarView_labelMaxLines, 1));
        int r105 = r112.getResourceId(R.styleable.NavigationBarView_itemActiveIndicatorStyle, 0);
        if (r105 == 0) goto L68;
        setItemActiveIndicatorEnabled(true);
        TypedArray r106 = r0.obtainStyledAttributes(r105, R.styleable.NavigationBarActiveIndicator);
        int r3 = r106.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_android_width, 0);
        setItemActiveIndicatorWidth(r3);
        setItemActiveIndicatorHeight(r106.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_android_height, 0));
        int r4 = r106.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_marginHorizontal, 0);
        setItemActiveIndicatorMarginHorizontal(r4);
        String r62 = r106.getString(R.styleable.NavigationBarActiveIndicator_expandedWidth);
        if (r62 != null) goto L52;
    L56:
        r1 = -2;
    L58:
        setItemActiveIndicatorExpandedWidth(r1);
        setItemActiveIndicatorExpandedHeight(r106.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_expandedHeight, r3));
        setItemActiveIndicatorExpandedMarginHorizontal(r106.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_expandedMarginHorizontal, r4));
        int r14 = getResources().getDimensionPixelSize(R.dimen.m3_navigation_item_leading_trailing_space);
        int r32 = r106.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_expandedActiveIndicatorPaddingStart, r14);
        int r15 = r106.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_expandedActiveIndicatorPaddingEnd, r14);
        if (getLayoutDirection() != 1) goto L61;
        int r42 = r15;
    L62:
        int r63 = r106.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_expandedActiveIndicatorPaddingTop, 0);
        if (getLayoutDirection() == 1) goto L66;
        r32 = r15;
    L66:
        setItemActiveIndicatorExpandedPadding(r42, r63, r32, r106.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_expandedActiveIndicatorPaddingBottom, 0));
        setItemActiveIndicatorColor(MaterialResources.getColorStateList(r0, r106, R.styleable.NavigationBarActiveIndicator_android_color));
        setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel.builder(r0, r106.getResourceId(R.styleable.NavigationBarActiveIndicator_shapeAppearance, 0), 0).build());
        r106.recycle();
        goto L68
    L61:
        r42 = r32;
        goto L62
    L52:
        if (String.valueOf(-1).equals(r62) == true) goto L58;
        if (String.valueOf(-2).equals(r62) == true) goto L56;
        r1 = r106.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_expandedWidth, -2);
    L68:
        if (r112.hasValue(R.styleable.NavigationBarView_menu) == false) goto L70;
        inflateMenu(r112.getResourceId(R.styleable.NavigationBarView_menu, 0));
    L70:
        r112.recycle();
        if (shouldAddMenuView() == true) goto L73;
        addView(r132);
    L73:
        r122.setCallback(new AnonymousClass1(this));
        return;
    L46:
        setItemRippleColor(MaterialResources.getColorStateList(r0, r112, R.styleable.NavigationBarView_itemRippleColor));
    L24:
        MaterialShapeDrawable r16 = new MaterialShapeDrawable(ShapeAppearanceModel.builder(r0, r11, r12, r13).build());
        if (r6 == null) goto L27;
        r16.setFillColor(r6);
    L27:
        r16.initializeElevationOverlay(r0);
        setBackground(r16);
        goto L29
    L5:
        r132.setIconTintList(r132.createDefaultColorStateList(android.R.attr.textColorSecondary));
        goto L6
    }

    public static /* synthetic */ OnItemReselectedListener access$000(NavigationBarView r0) {
        return r0.reselectedListener;
    }

    public static /* synthetic */ OnItemSelectedListener access$100(NavigationBarView r0) {
        return r0.selectedListener;
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater != null) goto L6;
        this.menuInflater = new SupportMenuInflater(getContext());
    L6:
        return this.menuInflater;
    }

    private void setMeasureBottomPaddingFromLabelBaseline(boolean r1) {
        this.menuView.setMeasurePaddingFromLabelBaseline(r1);
    }

    public abstract NavigationBarMenuView createNavigationBarMenuView(Context r1);

    public int getActiveIndicatorLabelPadding() {
        return this.menuView.getActiveIndicatorLabelPadding();
    }

    public BadgeDrawable getBadge(int r1) {
        return this.menuView.getBadge(r1);
    }

    public int getCollapsedMaxItemCount() {
        return getMaxItemCount();
    }

    public int getHorizontalItemTextAppearanceActive() {
        return this.menuView.getHorizontalItemTextAppearanceActive();
    }

    public int getHorizontalItemTextAppearanceInactive() {
        return this.menuView.getHorizontalItemTextAppearanceInactive();
    }

    public int getIconLabelHorizontalSpacing() {
        return this.menuView.getIconLabelHorizontalSpacing();
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.menuView.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorExpandedHeight() {
        return this.menuView.getItemActiveIndicatorExpandedHeight();
    }

    public int getItemActiveIndicatorExpandedMarginHorizontal() {
        return this.menuView.getItemActiveIndicatorExpandedMarginHorizontal();
    }

    public int getItemActiveIndicatorExpandedWidth() {
        return this.menuView.getItemActiveIndicatorExpandedWidth();
    }

    public int getItemActiveIndicatorHeight() {
        return this.menuView.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.menuView.getItemActiveIndicatorMarginHorizontal();
    }

    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.menuView.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.menuView.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public int getItemGravity() {
        return this.menuView.getItemGravity();
    }

    public int getItemIconGravity() {
        return this.menuView.getItemIconGravity();
    }

    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.menuView.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.menuView.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.menuView.getItemRippleColor();
    }

    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public int getLabelMaxLines(int r1) {
        return this.menuView.getLabelMaxLines();
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.menu;
    }

    public MenuView getMenuView() {
        return this.menuView;
    }

    public ViewGroup getMenuViewGroup() {
        return this.menuView;
    }

    public BadgeDrawable getOrCreateBadge(int r1) {
        return this.menuView.getOrCreateBadge(r1);
    }

    public NavigationBarPresenter getPresenter() {
        return this.presenter;
    }

    public boolean getScaleLabelTextWithFont() {
        return this.menuView.getScaleLabelTextWithFont();
    }

    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void inflateMenu(int r4) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(r4, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(true);
    }

    public boolean isItemActiveIndicatorEnabled() {
        return this.menuView.getItemActiveIndicatorEnabled();
    }

    public boolean isSubMenuSupported() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        this.menu.restorePresenterStates(r22.menuPresenterState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        Bundle r0 = new Bundle();
        r1.menuPresenterState = r0;
        this.menu.savePresenterStates(r0);
        return r1;
    }

    public void removeBadge(int r1) {
        this.menuView.removeBadge(r1);
    }

    public void setActiveIndicatorLabelPadding(int r1) {
        this.menuView.setActiveIndicatorLabelPadding(r1);
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        MaterialShapeUtils.setElevation(this, r1);
    }

    public void setHorizontalItemTextAppearanceActive(int r1) {
        this.menuView.setHorizontalItemTextAppearanceActive(r1);
    }

    public void setHorizontalItemTextAppearanceInactive(int r1) {
        this.menuView.setHorizontalItemTextAppearanceInactive(r1);
    }

    public void setIconLabelHorizontalSpacing(int r1) {
        this.menuView.setIconLabelHorizontalSpacing(r1);
    }

    public void setItemActiveIndicatorColor(ColorStateList r1) {
        this.menuView.setItemActiveIndicatorColor(r1);
    }

    public void setItemActiveIndicatorEnabled(boolean r1) {
        this.menuView.setItemActiveIndicatorEnabled(r1);
    }

    public void setItemActiveIndicatorExpandedHeight(int r1) {
        this.menuView.setItemActiveIndicatorExpandedHeight(r1);
    }

    public void setItemActiveIndicatorExpandedMarginHorizontal(int r1) {
        this.menuView.setItemActiveIndicatorExpandedMarginHorizontal(r1);
    }

    public void setItemActiveIndicatorExpandedPadding(int r1, int r2, int r3, int r4) {
        this.menuView.setItemActiveIndicatorExpandedPadding(r1, r2, r3, r4);
    }

    public void setItemActiveIndicatorExpandedWidth(int r1) {
        this.menuView.setItemActiveIndicatorExpandedWidth(r1);
    }

    public void setItemActiveIndicatorHeight(int r1) {
        this.menuView.setItemActiveIndicatorHeight(r1);
    }

    public void setItemActiveIndicatorMarginHorizontal(int r1) {
        this.menuView.setItemActiveIndicatorMarginHorizontal(r1);
    }

    public void setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel r1) {
        this.menuView.setItemActiveIndicatorShapeAppearance(r1);
    }

    public void setItemActiveIndicatorWidth(int r1) {
        this.menuView.setItemActiveIndicatorWidth(r1);
    }

    public void setItemBackground(Drawable r1) {
        this.menuView.setItemBackground(r1);
    }

    public void setItemBackgroundResource(int r1) {
        this.menuView.setItemBackgroundRes(r1);
    }

    public void setItemGravity(int r2) {
        if (this.menuView.getItemGravity() == r2) goto L6;
        this.menuView.setItemGravity(r2);
        this.presenter.updateMenuView(false);
        return;
    }

    public void setItemIconGravity(int r2) {
        if (this.menuView.getItemIconGravity() == r2) goto L6;
        this.menuView.setItemIconGravity(r2);
        this.presenter.updateMenuView(false);
        return;
    }

    public void setItemIconSize(int r1) {
        this.menuView.setItemIconSize(r1);
    }

    public void setItemIconSizeRes(int r2) {
        setItemIconSize(getResources().getDimensionPixelSize(r2));
    }

    public void setItemIconTintList(ColorStateList r1) {
        this.menuView.setIconTintList(r1);
    }

    public void setItemOnTouchListener(int r1, View.OnTouchListener r2) {
        this.menuView.setItemOnTouchListener(r1, r2);
    }

    public void setItemPaddingBottom(int r1) {
        this.menuView.setItemPaddingBottom(r1);
    }

    public void setItemPaddingTop(int r1) {
        this.menuView.setItemPaddingTop(r1);
    }

    public void setItemRippleColor(ColorStateList r1) {
        this.menuView.setItemRippleColor(r1);
    }

    public void setItemTextAppearanceActive(int r1) {
        this.menuView.setItemTextAppearanceActive(r1);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean r1) {
        this.menuView.setItemTextAppearanceActiveBoldEnabled(r1);
    }

    public void setItemTextAppearanceInactive(int r1) {
        this.menuView.setItemTextAppearanceInactive(r1);
    }

    public void setItemTextColor(ColorStateList r1) {
        this.menuView.setItemTextColor(r1);
    }

    public void setLabelFontScalingEnabled(boolean r1) {
        this.menuView.setLabelFontScalingEnabled(r1);
    }

    public void setLabelMaxLines(int r1) {
        this.menuView.setLabelMaxLines(r1);
    }

    public void setLabelVisibilityMode(int r2) {
        if (this.menuView.getLabelVisibilityMode() == r2) goto L6;
        this.menuView.setLabelVisibilityMode(r2);
        this.presenter.updateMenuView(false);
        return;
    }

    public void setOnItemReselectedListener(OnItemReselectedListener r1) {
        this.reselectedListener = r1;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener r1) {
        this.selectedListener = r1;
    }

    public void setSelectedItemId(int r4) {
        MenuItem r42 = this.menu.findItem(r4);
        if (r42 == null) goto L11;
        boolean r0 = this.menu.performItemAction(r42, this.presenter, 0);
        if (r42.isCheckable() == false) goto L12;
        if (r0 == true) goto L8;
    L9:
        this.menuView.setCheckedItem(r42);
        return;
    L8:
        if (r42.isChecked() == true) goto L9;
        return;
    L12:
        return;
    }

    public boolean shouldAddMenuView() {
        return false;
    }
}
