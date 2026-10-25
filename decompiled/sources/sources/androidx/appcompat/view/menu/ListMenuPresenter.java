package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    MenuAdapter mAdapter;
    private MenuPresenter.Callback mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    int mItemIndexOffset;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes;

    public class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex;
        final /* synthetic */ ListMenuPresenter this$0;

        public MenuAdapter(ListMenuPresenter r1) {
            this.this$0 = r1;
            this.mExpandedIndex = -1;
            findExpandedIndex();
        }

        public void findExpandedIndex() {
            MenuItemImpl r0 = this.this$0.mMenu.getExpandedItem();
            if (r0 == null) goto L11;
            ArrayList<MenuItemImpl> r1 = this.this$0.mMenu.getNonActionItems();
            int r2 = r1.size();
            int r3 = 0;
        L5:
            if (r3 >= r2) goto L11;
            if (r1.get(r3) == r0) goto L8;
            r3 = r3 + 1;
            goto L5
        L8:
            this.mExpandedIndex = r3;
            return;
        L11:
            this.mExpandedIndex = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int r0 = this.this$0.mMenu.getNonActionItems().size() - this.this$0.mItemIndexOffset;
            if (this.mExpandedIndex >= 0) goto L6;
            return r0;
        L6:
            return r0 - 1;
        }

        @Override // android.widget.Adapter
        public MenuItemImpl getItem(int r3) {
            ArrayList<MenuItemImpl> r0 = this.this$0.mMenu.getNonActionItems();
            int r32 = r3 + this.this$0.mItemIndexOffset;
            int r2 = this.mExpandedIndex;
            if (r2 < 0) goto L7;
            if (r32 < r2) goto L7;
            r32 = r32 + 1;
        L7:
            return r0.get(r32);
        }

        @Override // android.widget.Adapter
        public /* bridge */ /* synthetic */ Object getItem(int r1) {
            return getItem(r1);
        }

        @Override // android.widget.Adapter
        public long getItemId(int r1) {
            return r1;
        }

        @Override // android.widget.Adapter
        public View getView(int r3, View r4, ViewGroup r5) {
            if (r4 != null) goto L5;
            ListMenuPresenter r42 = this.this$0;
            r4 = r42.mInflater.inflate(r42.mItemLayoutRes, r5, false);
        L5:
            ((MenuView.ItemView) r4).initialize(getItem(r3), 0);
            return r4;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(int r1, int r2) {
        this.mItemLayoutRes = r1;
        this.mThemeRes = r2;
    }

    public ListMenuPresenter(Context r2, int r3) {
        this(r3, 0);
        this.mContext = r2;
        this.mInflater = LayoutInflater.from(r2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.mAdapter != null) goto L6;
        this.mAdapter = new MenuAdapter(this);
    L6:
        return this.mAdapter;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.mId;
    }

    public MenuView getMenuView(ViewGroup r4) {
        if (this.mMenuView != null) goto L9;
        this.mMenuView = (ExpandedMenuView) this.mInflater.inflate(R$layout.abc_expanded_menu_layout, r4, false);
        if (this.mAdapter != null) goto L7;
        this.mAdapter = new MenuAdapter(this);
    L7:
        this.mMenuView.setAdapter(this.mAdapter);
        this.mMenuView.setOnItemClickListener(this);
    L9:
        return this.mMenuView;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context r3, MenuBuilder r4) {
        if (this.mThemeRes == 0) goto L6;
        ContextThemeWrapper r0 = new ContextThemeWrapper(r3, this.mThemeRes);
        this.mContext = r0;
        this.mInflater = LayoutInflater.from(r0);
    L10:
        this.mMenu = r4;
        MenuAdapter r2 = this.mAdapter;
        if (r2 == null) goto L14;
        r2.notifyDataSetChanged();
        return;
    L14:
        return;
    L6:
        if (this.mContext == null) goto L10;
        this.mContext = r3;
        if (this.mInflater != null) goto L10;
        this.mInflater = LayoutInflater.from(r3);
        goto L10
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder r1, boolean r2) {
        MenuPresenter.Callback r0 = this.mCallback;
        if (r0 == null) goto L6;
        r0.onCloseMenu(r1, r2);
        return;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> r1, View r2, int r3, long r4) {
        this.mMenu.performItemAction(this.mAdapter.getItem(r3), this, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable r1) {
        restoreHierarchyState((Bundle) r1);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        if (this.mMenuView != null) goto L6;
        return null;
    L6:
        Bundle r0 = new Bundle();
        saveHierarchyState(r0);
        return r0;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder r3) {
        if (r3.hasVisibleItems() == true) goto L6;
        return false;
    L6:
        new MenuDialogHelper(r3).show(null);
        MenuPresenter.Callback r2 = this.mCallback;
        if (r2 == null) goto L11;
        r2.onOpenSubMenu(r3);
        return true;
    L11:
        return true;
    }

    public void restoreHierarchyState(Bundle r2) {
        SparseArray<Parcelable> r22 = r2.getSparseParcelableArray("android:menu:list");
        if (r22 == null) goto L6;
        this.mMenuView.restoreHierarchyState(r22);
        return;
    }

    public void saveHierarchyState(Bundle r2) {
        SparseArray<Parcelable> r0 = new SparseArray();
        ExpandedMenuView r1 = this.mMenuView;
        if (r1 == null) goto L5;
        r1.saveHierarchyState(r0);
    L5:
        r2.putSparseParcelableArray("android:menu:list", r0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback r1) {
        this.mCallback = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean r1) {
        MenuAdapter r0 = this.mAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
