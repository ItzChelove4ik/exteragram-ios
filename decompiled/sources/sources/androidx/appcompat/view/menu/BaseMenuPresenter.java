package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class BaseMenuPresenter implements MenuPresenter {
    private MenuPresenter.Callback mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;

    public BaseMenuPresenter(Context r1, int r2, int r3) {
        this.mSystemContext = r1;
        this.mSystemInflater = LayoutInflater.from(r1);
        this.mMenuLayoutRes = r2;
        this.mItemLayoutRes = r3;
    }

    public void addItemView(View r2, int r3) {
        ViewGroup r0 = (ViewGroup) r2.getParent();
        if (r0 == null) goto L5;
        r0.removeView(r2);
    L5:
        ((ViewGroup) this.mMenuView).addView(r2, r3);
    }

    public abstract void bindItemView(MenuItemImpl r1, MenuView.ItemView r2);

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    public MenuView.ItemView createItemView(ViewGroup r3) {
        return (MenuView.ItemView) this.mSystemInflater.inflate(this.mItemLayoutRes, r3, false);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    public boolean filterLeftoverView(ViewGroup r1, int r2) {
        r1.removeViewAt(r2);
        return true;
    }

    public MenuPresenter.Callback getCallback() {
        return this.mCallback;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.mId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View getItemView(MenuItemImpl r2, View r3, ViewGroup r4) {
        if ((r3 instanceof MenuView.ItemView) == false) goto L5;
        MenuView.ItemView r32 = (MenuView.ItemView) r3;
    L6:
        bindItemView(r2, r32);
        return (View) r32;
    L5:
        r32 = createItemView(r4);
        goto L6
    }

    public MenuView getMenuView(ViewGroup r4) {
        if (this.mMenuView != null) goto L6;
        MenuView r42 = (MenuView) this.mSystemInflater.inflate(this.mMenuLayoutRes, r4, false);
        this.mMenuView = r42;
        r42.initialize(this.mMenu);
        updateMenuView(true);
    L6:
        return this.mMenuView;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context r1, MenuBuilder r2) {
        this.mContext = r1;
        this.mInflater = LayoutInflater.from(r1);
        this.mMenu = r2;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder r1, boolean r2) {
        MenuPresenter.Callback r0 = this.mCallback;
        if (r0 == null) goto L6;
        r0.onCloseMenu(r1, r2);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.appcompat.view.menu.MenuBuilder] */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder r2) {
        MenuPresenter.Callback r0 = this.mCallback;
        SubMenuBuilder r22 = r2;
        if (r0 == null) goto L9;
        if (r2 != null) goto L8;
        r22 = this.mMenu;
    L8:
        return r0.onOpenSubMenu(r22);
    L9:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback r1) {
        this.mCallback = r1;
    }

    public void setId(int r1) {
        this.mId = r1;
    }

    public abstract boolean shouldIncludeItem(int r1, MenuItemImpl r2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean r10) {
        ViewGroup r102 = (ViewGroup) this.mMenuView;
        if (r102 == null) goto L28;
        MenuBuilder r0 = this.mMenu;
        int r1 = 0;
        if (r0 == null) goto L24;
        r0.flagActionItems();
        ArrayList<MenuItemImpl> r02 = this.mMenu.getVisibleItems();
        int r2 = r02.size();
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r3 >= r2) goto L22;
        MenuItemImpl r5 = r02.get(r3);
        if (shouldIncludeItem(r4, r5) == false) goto L21;
        View r6 = r102.getChildAt(r4);
        if ((r6 instanceof MenuView.ItemView) == false) goto L14;
        MenuItemImpl r7 = ((MenuView.ItemView) r6).getItemData();
    L15:
        View r8 = getItemView(r5, r6, r102);
        if (r5 == r7) goto L18;
        r8.setPressed(false);
        r8.jumpDrawablesToCurrentState();
    L18:
        if (r8 == r6) goto L20;
        addItemView(r8, r4);
    L20:
        r4 = r4 + 1;
        goto L21
    L14:
        r7 = null;
    L21:
        r3 = r3 + 1;
        goto L8
    L22:
        r1 = r4;
    L24:
        if (r1 >= r102.getChildCount()) goto L38;
        if (filterLeftoverView(r102, r1) == true) goto L24;
        r1 = r1 + 1;
        goto L24
    L38:
        return;
    }
}
