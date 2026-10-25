package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.google.android.material.R;
import java.util.ArrayList;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class NavigationMenuPresenter implements MenuPresenter {
    public static final int NO_TEXT_APPEARANCE_SET = 0;
    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HEADER = "android:menu:header";
    private static final String STATE_HIERARCHY = "android:menu:list";
    NavigationMenuAdapter adapter;
    private MenuPresenter.Callback callback;
    int dividerInsetEnd;
    int dividerInsetStart;
    boolean hasCustomItemIconSize;
    LinearLayout headerLayout;
    ColorStateList iconTintList;
    private int id;
    boolean isBehindStatusBar;
    Drawable itemBackground;
    RippleDrawable itemForeground;
    int itemHorizontalPadding;
    int itemIconPadding;
    int itemIconSize;
    private int itemMaxLines;
    int itemVerticalPadding;
    LayoutInflater layoutInflater;
    MenuBuilder menu;
    private NavigationMenuView menuView;
    final View.OnClickListener onClickListener;
    private int overScrollMode;
    int paddingSeparator;
    private int paddingTopDefault;
    ColorStateList subheaderColor;
    int subheaderInsetEnd;
    int subheaderInsetStart;
    int subheaderTextAppearance;
    int textAppearance;
    boolean textAppearanceActiveBoldEnabled;
    ColorStateList textColor;

    /* renamed from: com.google.android.material.internal.NavigationMenuPresenter$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ NavigationMenuPresenter this$0;

        public AnonymousClass1(NavigationMenuPresenter r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r5) {
            boolean r1 = true;
            this.this$0.setUpdateSuspended(true);
            MenuItemImpl r52 = ((NavigationMenuItemView) r5).getItemData();
            NavigationMenuPresenter r0 = this.this$0;
            boolean r02 = r0.menu.performItemAction(r52, r0, 0);
            if (r52 != null) goto L5;
        L8:
            r1 = false;
        L9:
            this.this$0.setUpdateSuspended(false);
            if (r1 == false) goto L13;
            this.this$0.updateMenuView(false);
            return;
        L13:
            return;
        L5:
            if (r52.isCheckable() == false) goto L8;
            if (r02 == false) goto L8;
            this.this$0.adapter.setCheckedItem(r52);
            goto L9
        }
    }

    public static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View r1) {
            super(r1);
        }
    }

    public class NavigationMenuAdapter extends RecyclerView.Adapter<ViewHolder> {
        private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
        private static final String STATE_CHECKED_ITEM = "android:menu:checked";
        private static final int VIEW_TYPE_HEADER = 3;
        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SEPARATOR = 2;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private MenuItemImpl checkedItem;
        private final ArrayList<NavigationMenuItem> items;
        final /* synthetic */ NavigationMenuPresenter this$0;
        private boolean updateSuspended;

        /* renamed from: com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends AccessibilityDelegateCompat {
            final /* synthetic */ NavigationMenuAdapter this$1;
            final /* synthetic */ boolean val$isHeader;
            final /* synthetic */ int val$position;

            public AnonymousClass1(NavigationMenuAdapter r1, int r2, boolean r3) {
                this.this$1 = r1;
                this.val$position = r2;
                this.val$isHeader = r3;
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View r9, AccessibilityNodeInfoCompat r10) {
                super.onInitializeAccessibilityNodeInfo(r9, r10);
                r10.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(NavigationMenuAdapter.access$400(this.this$1, this.val$position), 1, 1, 1, this.val$isHeader, r9.isSelected()));
            }
        }

        public NavigationMenuAdapter(NavigationMenuPresenter r1) {
            this.this$0 = r1;
            this.items = new ArrayList();
            prepareMenuItems();
        }

        public static /* synthetic */ void access$000(NavigationMenuAdapter r0) {
            r0.updateAllTextMenuItems();
        }

        public static /* synthetic */ void access$100(NavigationMenuAdapter r0) {
            r0.updateAllSubHeaderMenuItems();
        }

        public static /* synthetic */ void access$200(NavigationMenuAdapter r0) {
            r0.updateAllDividerMenuItems();
        }

        public static /* synthetic */ int access$400(NavigationMenuAdapter r0, int r1) {
            return r0.adjustItemPositionForA11yDelegate(r1);
        }

        private int adjustItemPositionForA11yDelegate(int r5) {
            int r0 = 0;
            int r1 = r5;
        L3:
            if (r0 >= r5) goto L10;
            if (this.this$0.adapter.getItemViewType(r0) != 2) goto L7;
        L8:
            r1 = r1 - 1;
        L9:
            r0 = r0 + 1;
            goto L3
        L7:
            if (this.this$0.adapter.getItemViewType(r0) != 3) goto L9;
        L10:
            return r1;
        }

        private void appendTransparentIconIfMissing(int r3, int r4) {
        L2:
            if (r3 >= r4) goto L4;
            ((NavigationMenuTextItem) this.items.get(r3)).needsEmptyIcon = true;
            r3 = r3 + 1;
            goto L2
        }

        private void prepareMenuItems() {
            if (this.updateSuspended == false) goto L5;
            return;
        L5:
            this.updateSuspended = true;
            this.items.clear();
            this.items.add(new NavigationMenuHeaderItem());
            int r1 = this.this$0.menu.getVisibleItems().size();
            int r3 = -1;
            int r4 = 0;
            boolean r5 = false;
            int r6 = 0;
        L6:
            if (r4 >= r1) goto L51;
            MenuItemImpl r7 = this.this$0.menu.getVisibleItems().get(r4);
            if (r7.isChecked() == false) goto L11;
            setCheckedItem(r7);
        L11:
            if (r7.isCheckable() == false) goto L14;
            r7.setExclusiveCheckable(false);
        L14:
            if (r7.hasSubMenu() == false) goto L37;
            SubMenu r8 = r7.getSubMenu();
            if (r8.hasVisibleItems() == false) goto L50;
            if (r4 == 0) goto L19;
            this.items.add(new NavigationMenuSeparatorItem(this.this$0.paddingSeparator, 0));
        L19:
            this.items.add(new NavigationMenuTextItem(r7));
            int r72 = this.items.size();
            int r9 = r8.size();
            int r10 = 0;
            boolean r11 = false;
        L20:
            if (r10 >= r9) goto L35;
            MenuItemImpl r12 = (MenuItemImpl) r8.getItem(r10);
            if (r12.isVisible() == false) goto L34;
            if (r11 == true) goto L28;
            if (r12.getIcon() == null) goto L28;
            r11 = true;
        L28:
            if (r12.isCheckable() == false) goto L31;
            r12.setExclusiveCheckable(false);
        L31:
            if (r12.isChecked() == false) goto L33;
            setCheckedItem(r12);
        L33:
            this.items.add(new NavigationMenuTextItem(r12));
        L34:
            r10 = r10 + 1;
            goto L20
        L35:
            if (r11 == false) goto L50;
            appendTransparentIconIfMissing(r72, this.items.size());
        L50:
            r4 = r4 + 1;
            goto L6
        L37:
            int r82 = r7.getGroupId();
            if (r82 == r3) goto L45;
            r6 = this.items.size();
            if (r7.getIcon() == null) goto L42;
            r5 = true;
        L43:
            if (r4 == 0) goto L49;
            r6 = r6 + 1;
            ArrayList<NavigationMenuItem> r32 = this.items;
            int r102 = this.this$0.paddingSeparator;
            r32.add(new NavigationMenuSeparatorItem(r102, r102));
        L49:
            NavigationMenuTextItem r33 = new NavigationMenuTextItem(r7);
            r33.needsEmptyIcon = r5;
            this.items.add(r33);
            r3 = r82;
            goto L50
        L42:
            r5 = false;
            goto L43
        L45:
            if (r5 == true) goto L49;
            if (r7.getIcon() == null) goto L49;
            appendTransparentIconIfMissing(r6, this.items.size());
            r5 = true;
            goto L49
        L51:
            this.updateSuspended = false;
        }

        private void setAccessibilityDelegate(View r2, int r3, boolean r4) {
            ViewCompat.setAccessibilityDelegate(r2, new AnonymousClass1(this, r3, r4));
        }

        private void updateAllDividerMenuItems() {
            int r0 = 0;
        L4:
            if (r0 >= this.items.size()) goto L9;
            if ((this.items.get(r0) instanceof NavigationMenuSeparatorItem) == false) goto L8;
            notifyItemChanged(r0);
        L8:
            r0 = r0 + 1;
            goto L4
        }

        private void updateAllSubHeaderMenuItems() {
            int r0 = 0;
        L4:
            if (r0 >= this.items.size()) goto L11;
            if ((this.items.get(r0) instanceof NavigationMenuTextItem) == false) goto L10;
            if (getItemViewType(r0) != 1) goto L10;
            notifyItemChanged(r0);
        L10:
            r0 = r0 + 1;
            goto L4
        }

        private void updateAllTextMenuItems() {
            int r0 = 0;
        L4:
            if (r0 >= this.items.size()) goto L11;
            if ((this.items.get(r0) instanceof NavigationMenuTextItem) == false) goto L10;
            if (getItemViewType(r0) != 0) goto L10;
            notifyItemChanged(r0);
        L10:
            r0 = r0 + 1;
            goto L4
        }

        public Bundle createInstanceState() {
            Bundle r0 = new Bundle();
            MenuItemImpl r1 = this.checkedItem;
            if (r1 == null) goto L5;
            r0.putInt(STATE_CHECKED_ITEM, r1.getItemId());
        L5:
            SparseArray<? extends Parcelable> r12 = new SparseArray();
            int r2 = this.items.size();
            int r3 = 0;
        L6:
            if (r3 >= r2) goto L16;
            NavigationMenuItem r4 = this.items.get(r3);
            if ((r4 instanceof NavigationMenuTextItem) == false) goto L15;
            MenuItemImpl r42 = ((NavigationMenuTextItem) r4).getMenuItem();
            if (r42 == null) goto L12;
            View r5 = r42.getActionView();
        L13:
            if (r5 == null) goto L15;
            ParcelableSparseArray r6 = new ParcelableSparseArray();
            r5.saveHierarchyState(r6);
            r12.put(r42.getItemId(), r6);
            goto L15
        L12:
            r5 = null;
        L15:
            r3 = r3 + 1;
            goto L6
        L16:
            r0.putSparseParcelableArray(STATE_ACTION_VIEWS, r12);
            return r0;
        }

        public MenuItemImpl getCheckedItem() {
            return this.checkedItem;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            NavigationMenuItem r1 = this.items.get(r2);
            if ((r1 instanceof NavigationMenuSeparatorItem) == false) goto L7;
            return 2;
        L7:
            if ((r1 instanceof NavigationMenuHeaderItem) == false) goto L11;
            return 3;
        L11:
            if ((r1 instanceof NavigationMenuTextItem) == true) goto L13;
            GlShader$$ExternalSyntheticBUOutline1.m("Unknown item type.");
            return 0;
        L13:
            if (((NavigationMenuTextItem) r1).getMenuItem().hasSubMenu() == false) goto L16;
            return 1;
        L16:
            return 0;
        }

        public int getRowCount() {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r0 >= this.this$0.adapter.getItemCount()) goto L11;
            int r2 = this.this$0.adapter.getItemViewType(r0);
            if (r2 != 0) goto L8;
        L9:
            r1 = r1 + 1;
        L10:
            r0 = r0 + 1;
            goto L4
        L8:
            if (r2 != 1) goto L10;
        L11:
            return r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
            onBindViewHolder((ViewHolder) r1, r2);
        }

        public void onBindViewHolder(ViewHolder r6, int r7) {
            int r0 = getItemViewType(r7);
            if (r0 != 0) goto L5;
            NavigationMenuItemView r62 = (NavigationMenuItemView) r6.itemView;
            r62.setIconTintList(this.this$0.iconTintList);
            r62.setTextAppearance(this.this$0.textAppearance);
            ColorStateList r02 = this.this$0.textColor;
            if (r02 == null) goto L19;
            r62.setTextColor(r02);
        L19:
            Drawable r03 = this.this$0.itemBackground;
            if (r03 == null) goto L22;
            Drawable r04 = r03.getConstantState().newDrawable();
        L23:
            r62.setBackground(r04);
            RippleDrawable r05 = this.this$0.itemForeground;
            if (r05 == null) goto L26;
            r62.setForeground(r05.getConstantState().newDrawable());
        L26:
            NavigationMenuTextItem r06 = (NavigationMenuTextItem) this.items.get(r7);
            r62.setNeedsEmptyIcon(r06.needsEmptyIcon);
            NavigationMenuPresenter r1 = this.this$0;
            int r2 = r1.itemHorizontalPadding;
            int r12 = r1.itemVerticalPadding;
            r62.setPadding(r2, r12, r2, r12);
            r62.setIconPadding(this.this$0.itemIconPadding);
            NavigationMenuPresenter r13 = this.this$0;
            if (r13.hasCustomItemIconSize == false) goto L29;
            r62.setIconSize(r13.itemIconSize);
        L29:
            r62.setMaxLines(NavigationMenuPresenter.access$300(this.this$0));
            r62.initialize(r06.getMenuItem(), this.this$0.textAppearanceActiveBoldEnabled);
            setAccessibilityDelegate(r62, r7, false);
            return;
        L22:
            r04 = null;
            goto L23
        L5:
            if (r0 != 1) goto L7;
            TextView r63 = (TextView) r6.itemView;
            r63.setText(((NavigationMenuTextItem) this.items.get(r7)).getMenuItem().getTitle());
            TextViewCompat.setTextAppearance(r63, this.this$0.subheaderTextAppearance);
            r63.setPaddingRelative(this.this$0.subheaderInsetStart, r63.getPaddingTop(), this.this$0.subheaderInsetEnd, r63.getPaddingBottom());
            ColorStateList r07 = this.this$0.subheaderColor;
            if (r07 == null) goto L14;
            r63.setTextColor(r07);
        L14:
            setAccessibilityDelegate(r63, r7, true);
            return;
        L7:
            if (r0 == 2) goto L9;
            return;
        L9:
            NavigationMenuSeparatorItem r72 = (NavigationMenuSeparatorItem) this.items.get(r7);
            r6.itemView.setPaddingRelative(this.this$0.dividerInsetStart, r72.getPaddingTop(), this.this$0.dividerInsetEnd, r72.getPaddingBottom());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return onCreateViewHolder(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            NavigationMenuPresenter r1 = this.this$0;
            return new NormalViewHolder(r1.layoutInflater, r2, r1.onClickListener);
        L4:
            if (r3 == 1) goto L16;
            if (r3 == 2) goto L14;
            if (r3 == 3) goto L12;
            return null;
        L12:
            return new HeaderViewHolder(this.this$0.headerLayout);
        L14:
            return new SeparatorViewHolder(this.this$0.layoutInflater, r2);
        L16:
            return new SubheaderViewHolder(this.this$0.layoutInflater, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ void onViewRecycled(RecyclerView.ViewHolder r1) {
            onViewRecycled((ViewHolder) r1);
        }

        public void onViewRecycled(ViewHolder r1) {
            if ((r1 instanceof NormalViewHolder) == false) goto L6;
            ((NavigationMenuItemView) r1.itemView).recycle();
            return;
        }

        public void restoreInstanceState(Bundle r7) {
            int r1 = 0;
            int r0 = r7.getInt(STATE_CHECKED_ITEM, 0);
            if (r0 == 0) goto L15;
            this.updateSuspended = true;
            int r2 = this.items.size();
            int r3 = 0;
        L5:
            if (r3 >= r2) goto L14;
            NavigationMenuItem r4 = this.items.get(r3);
            if ((r4 instanceof NavigationMenuTextItem) == false) goto L13;
            MenuItemImpl r42 = ((NavigationMenuTextItem) r4).getMenuItem();
            if (r42 == null) goto L13;
            if (r42.getItemId() != r0) goto L13;
            setCheckedItem(r42);
        L13:
            r3 = r3 + 1;
        L14:
            this.updateSuspended = false;
            prepareMenuItems();
        L15:
            SparseArray r72 = r7.getSparseParcelableArray(STATE_ACTION_VIEWS);
            if (r72 == null) goto L33;
            int r02 = this.items.size();
        L18:
            if (r1 >= r02) goto L45;
            NavigationMenuItem r22 = this.items.get(r1);
            if ((r22 instanceof NavigationMenuTextItem) == false) goto L32;
            MenuItemImpl r23 = ((NavigationMenuTextItem) r22).getMenuItem();
            if (r23 == null) goto L32;
            View r32 = r23.getActionView();
            if (r32 == null) goto L32;
            ParcelableSparseArray r24 = (ParcelableSparseArray) r72.get(r23.getItemId());
            if (r24 == null) goto L32;
            r32.restoreHierarchyState(r24);
        L32:
            r1 = r1 + 1;
            goto L18
        L45:
            return;
        }

        public void setCheckedItem(MenuItemImpl r3) {
            if (this.checkedItem != r3) goto L5;
            return;
        L5:
            if (r3.isCheckable() == false) goto L13;
            MenuItemImpl r0 = this.checkedItem;
            if (r0 == null) goto L10;
            r0.setChecked(false);
        L10:
            this.checkedItem = r3;
            r3.setChecked(true);
            return;
        }

        public void setUpdateSuspended(boolean r1) {
            this.updateSuspended = r1;
        }

        public void update() {
            int r0 = this.items.size();
            prepareMenuItems();
            notifyDataSetChanged();
            if (r0 != this.items.size()) goto L6;
            notifyItemRangeChanged(0, this.items.size());
            return;
        }
    }

    public static class NavigationMenuHeaderItem implements NavigationMenuItem {
        public NavigationMenuHeaderItem() {
        }
    }

    public interface NavigationMenuItem {
    }

    public static class NavigationMenuSeparatorItem implements NavigationMenuItem {
        private final int paddingBottom;
        private final int paddingTop;

        public NavigationMenuSeparatorItem(int r1, int r2) {
            this.paddingTop = r1;
            this.paddingBottom = r2;
        }

        public int getPaddingBottom() {
            return this.paddingBottom;
        }

        public int getPaddingTop() {
            return this.paddingTop;
        }
    }

    public static class NavigationMenuTextItem implements NavigationMenuItem {
        private final MenuItemImpl menuItem;
        boolean needsEmptyIcon;

        public NavigationMenuTextItem(MenuItemImpl r1) {
            this.menuItem = r1;
        }

        public MenuItemImpl getMenuItem() {
            return this.menuItem;
        }
    }

    public class NavigationMenuViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
        final /* synthetic */ NavigationMenuPresenter this$0;

        public NavigationMenuViewAccessibilityDelegate(NavigationMenuPresenter r1, RecyclerView r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r2, r3);
            r3.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(this.this$0.adapter.getRowCount(), 1, false));
        }
    }

    public static class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(LayoutInflater r3, ViewGroup r4, View.OnClickListener r5) {
            super(r3.inflate(R.layout.design_navigation_item, r4, false));
            this.itemView.setOnClickListener(r5);
        }
    }

    public static class SeparatorViewHolder extends ViewHolder {
        public SeparatorViewHolder(LayoutInflater r3, ViewGroup r4) {
            super(r3.inflate(R.layout.design_navigation_item_separator, r4, false));
        }
    }

    public static class SubheaderViewHolder extends ViewHolder {
        public SubheaderViewHolder(LayoutInflater r3, ViewGroup r4) {
            super(r3.inflate(R.layout.design_navigation_item_subheader, r4, false));
        }
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View r1) {
            super(r1);
        }
    }

    public NavigationMenuPresenter() {
        this.subheaderTextAppearance = 0;
        this.textAppearance = 0;
        this.textAppearanceActiveBoldEnabled = true;
        this.isBehindStatusBar = true;
        this.overScrollMode = -1;
        this.onClickListener = new AnonymousClass1(this);
    }

    public static /* synthetic */ int access$300(NavigationMenuPresenter r0) {
        return r0.itemMaxLines;
    }

    private boolean hasHeader() {
        if (getHeaderCount() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    private void updateAllDividerMenuItems() {
        NavigationMenuAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        NavigationMenuAdapter.access$200(r0);
        return;
    }

    private void updateAllSubHeaderMenuItems() {
        NavigationMenuAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        NavigationMenuAdapter.access$100(r0);
        return;
    }

    private void updateAllTextMenuItems() {
        NavigationMenuAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        NavigationMenuAdapter.access$000(r0);
        return;
    }

    private void updateTopPadding() {
        if (hasHeader() == false) goto L5;
    L7:
        int r0 = 0;
    L8:
        NavigationMenuView r3 = this.menuView;
        r3.setPadding(0, r0, 0, r3.getPaddingBottom());
        return;
    L5:
        if (this.isBehindStatusBar == false) goto L7;
        r0 = this.paddingTopDefault;
        goto L8
    }

    public void addHeaderView(View r2) {
        this.headerLayout.addView(r2);
        NavigationMenuView r1 = this.menuView;
        r1.setPadding(0, 0, 0, r1.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    public void dispatchApplyWindowInsets(WindowInsetsCompat r5) {
        int r0 = r5.getSystemWindowInsetTop();
        if (this.paddingTopDefault == r0) goto L5;
        this.paddingTopDefault = r0;
        updateTopPadding();
    L5:
        NavigationMenuView r02 = this.menuView;
        r02.setPadding(0, r02.getPaddingTop(), 0, r5.getSystemWindowInsetBottom());
        ViewCompat.dispatchApplyWindowInsets(this.headerLayout, r5);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public MenuItemImpl getCheckedItem() {
        return this.adapter.getCheckedItem();
    }

    public int getDividerInsetEnd() {
        return this.dividerInsetEnd;
    }

    public int getDividerInsetStart() {
        return this.dividerInsetStart;
    }

    public int getHeaderCount() {
        return this.headerLayout.getChildCount();
    }

    public View getHeaderView(int r1) {
        return this.headerLayout.getChildAt(r1);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.id;
    }

    public Drawable getItemBackground() {
        return this.itemBackground;
    }

    public int getItemHorizontalPadding() {
        return this.itemHorizontalPadding;
    }

    public int getItemIconPadding() {
        return this.itemIconPadding;
    }

    public int getItemMaxLines() {
        return this.itemMaxLines;
    }

    public ColorStateList getItemTextColor() {
        return this.textColor;
    }

    public ColorStateList getItemTintList() {
        return this.iconTintList;
    }

    public int getItemVerticalPadding() {
        return this.itemVerticalPadding;
    }

    public MenuView getMenuView(ViewGroup r4) {
        if (this.menuView != null) goto L12;
        NavigationMenuView r42 = (NavigationMenuView) this.layoutInflater.inflate(R.layout.design_navigation_menu, r4, false);
        this.menuView = r42;
        r42.setAccessibilityDelegateCompat(new NavigationMenuViewAccessibilityDelegate(this, this.menuView));
        if (this.adapter != null) goto L7;
        NavigationMenuAdapter r43 = new NavigationMenuAdapter(this);
        this.adapter = r43;
        r43.setHasStableIds(true);
    L7:
        int r44 = this.overScrollMode;
        if (r44 == (-1)) goto L10;
        this.menuView.setOverScrollMode(r44);
    L10:
        LinearLayout r45 = (LinearLayout) this.layoutInflater.inflate(R.layout.design_navigation_item_header, this.menuView, false);
        this.headerLayout = r45;
        r45.setImportantForAccessibility(2);
        this.menuView.setAdapter(this.adapter);
    L12:
        return this.menuView;
    }

    public int getSubheaderInsetEnd() {
        return this.subheaderInsetEnd;
    }

    public int getSubheaderInsetStart() {
        return this.subheaderInsetStart;
    }

    public View inflateHeaderView(int r4) {
        View r42 = this.layoutInflater.inflate(r4, this.headerLayout, false);
        addHeaderView(r42);
        return r42;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context r2, MenuBuilder r3) {
        this.layoutInflater = LayoutInflater.from(r2);
        this.menu = r3;
        this.paddingSeparator = r2.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public boolean isBehindStatusBar() {
        return this.isBehindStatusBar;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder r1, boolean r2) {
        MenuPresenter.Callback r0 = this.callback;
        if (r0 == null) goto L6;
        r0.onCloseMenu(r1, r2);
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable r3) {
        if ((r3 instanceof Bundle) == false) goto L14;
        Bundle r32 = (Bundle) r3;
        SparseArray<Parcelable> r0 = r32.getSparseParcelableArray(STATE_HIERARCHY);
        if (r0 == null) goto L7;
        this.menuView.restoreHierarchyState(r0);
    L7:
        Bundle r02 = r32.getBundle(STATE_ADAPTER);
        if (r02 == null) goto L10;
        this.adapter.restoreInstanceState(r02);
    L10:
        SparseArray<Parcelable> r33 = r32.getSparseParcelableArray(STATE_HEADER);
        if (r33 == null) goto L15;
        this.headerLayout.restoreHierarchyState(r33);
        return;
    L15:
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        Bundle r0 = new Bundle();
        if (this.menuView == null) goto L5;
        SparseArray<Parcelable> r1 = new SparseArray();
        this.menuView.saveHierarchyState(r1);
        r0.putSparseParcelableArray(STATE_HIERARCHY, r1);
    L5:
        NavigationMenuAdapter r12 = this.adapter;
        if (r12 == null) goto L9;
        r0.putBundle(STATE_ADAPTER, r12.createInstanceState());
    L9:
        if (this.headerLayout == null) goto L11;
        SparseArray<Parcelable> r13 = new SparseArray();
        this.headerLayout.saveHierarchyState(r13);
        r0.putSparseParcelableArray(STATE_HEADER, r13);
    L11:
        return r0;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder r1) {
        return false;
    }

    public void removeHeaderView(View r3) {
        this.headerLayout.removeView(r3);
        if (hasHeader() == true) goto L6;
        NavigationMenuView r32 = this.menuView;
        r32.setPadding(0, this.paddingTopDefault, 0, r32.getPaddingBottom());
        return;
    }

    public void setBehindStatusBar(boolean r2) {
        if (this.isBehindStatusBar == r2) goto L6;
        this.isBehindStatusBar = r2;
        updateTopPadding();
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback r1) {
        this.callback = r1;
    }

    public void setCheckedItem(MenuItemImpl r1) {
        this.adapter.setCheckedItem(r1);
    }

    public void setDividerInsetEnd(int r1) {
        this.dividerInsetEnd = r1;
        updateAllDividerMenuItems();
    }

    public void setDividerInsetStart(int r1) {
        this.dividerInsetStart = r1;
        updateAllDividerMenuItems();
    }

    public void setId(int r1) {
        this.id = r1;
    }

    public void setItemBackground(Drawable r1) {
        this.itemBackground = r1;
        updateAllTextMenuItems();
    }

    public void setItemForeground(RippleDrawable r1) {
        this.itemForeground = r1;
        updateAllTextMenuItems();
    }

    public void setItemHorizontalPadding(int r1) {
        this.itemHorizontalPadding = r1;
        updateAllTextMenuItems();
    }

    public void setItemIconPadding(int r1) {
        this.itemIconPadding = r1;
        updateAllTextMenuItems();
    }

    public void setItemIconSize(int r2) {
        if (this.itemIconSize == r2) goto L6;
        this.itemIconSize = r2;
        this.hasCustomItemIconSize = true;
        updateAllTextMenuItems();
        return;
    }

    public void setItemIconTintList(ColorStateList r1) {
        this.iconTintList = r1;
        updateAllTextMenuItems();
    }

    public void setItemMaxLines(int r1) {
        this.itemMaxLines = r1;
        updateAllTextMenuItems();
    }

    public void setItemTextAppearance(int r1) {
        this.textAppearance = r1;
        updateAllTextMenuItems();
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean r1) {
        this.textAppearanceActiveBoldEnabled = r1;
        updateAllTextMenuItems();
    }

    public void setItemTextColor(ColorStateList r1) {
        this.textColor = r1;
        updateAllTextMenuItems();
    }

    public void setItemVerticalPadding(int r1) {
        this.itemVerticalPadding = r1;
        updateAllTextMenuItems();
    }

    public void setOverScrollMode(int r1) {
        this.overScrollMode = r1;
        NavigationMenuView r0 = this.menuView;
        if (r0 == null) goto L6;
        r0.setOverScrollMode(r1);
        return;
    }

    public void setSubheaderColor(ColorStateList r1) {
        this.subheaderColor = r1;
        updateAllSubHeaderMenuItems();
    }

    public void setSubheaderInsetEnd(int r1) {
        this.subheaderInsetEnd = r1;
        updateAllSubHeaderMenuItems();
    }

    public void setSubheaderInsetStart(int r1) {
        this.subheaderInsetStart = r1;
        updateAllSubHeaderMenuItems();
    }

    public void setSubheaderTextAppearance(int r1) {
        this.subheaderTextAppearance = r1;
        updateAllSubHeaderMenuItems();
    }

    public void setUpdateSuspended(boolean r1) {
        NavigationMenuAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.setUpdateSuspended(r1);
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean r1) {
        NavigationMenuAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.update();
        return;
    }
}
