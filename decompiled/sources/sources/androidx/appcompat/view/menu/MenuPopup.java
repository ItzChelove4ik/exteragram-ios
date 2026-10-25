package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* loaded from: classes3.dex */
abstract class MenuPopup implements ShowableListMenu, MenuPresenter, AdapterView.OnItemClickListener {
    private Rect mEpicenterBounds;

    public MenuPopup() {
    }

    public static int measureIndividualMenuWidth(ListAdapter r9, ViewGroup r10, Context r11, int r12) {
        int r0 = 0;
        int r1 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int r2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int r3 = r9.getCount();
        int r5 = 0;
        int r6 = 0;
        View r7 = null;
    L3:
        if (r0 >= r3) goto L15;
        int r8 = r9.getItemViewType(r0);
        if (r8 == r6) goto L7;
        r7 = null;
        r6 = r8;
    L7:
        if (r10 != null) goto L9;
        r10 = new FrameLayout(r11);
    L9:
        r7 = r9.getView(r0, r7, r10);
        r7.measure(r1, r2);
        int r82 = r7.getMeasuredWidth();
        if (r82 >= r12) goto L11;
        if (r82 <= r5) goto L14;
        r5 = r82;
    L14:
        r0 = r0 + 1;
        goto L3
    L11:
        return r12;
    L15:
        return r5;
    }

    public static boolean shouldPreserveIconSpacing(MenuBuilder r5) {
        int r0 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        MenuItem r3 = r5.getItem(r2);
        if (r3.isVisible() == false) goto L10;
        if (r3.getIcon() == null) goto L10;
        return true;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        return false;
    }

    public static MenuAdapter toMenuAdapter(ListAdapter r1) {
        if ((r1 instanceof HeaderViewListAdapter) == false) goto L7;
        return (MenuAdapter) ((HeaderViewListAdapter) r1).getWrappedAdapter();
    L7:
        return (MenuAdapter) r1;
    }

    public abstract void addMenu(MenuBuilder r1);

    public boolean closeMenuOnSubMenuOpened() {
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context r1, MenuBuilder r2) {
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> r1, View r2, int r3, long r4) {
        ListAdapter r12 = (ListAdapter) r1.getAdapter();
        MenuBuilder r22 = toMenuAdapter(r12).mAdapterMenu;
        MenuItem r13 = (MenuItem) r12.getItem(r3);
        if (closeMenuOnSubMenuOpened() == false) goto L5;
        int r32 = 0;
    L6:
        r22.performItemAction(r13, this, r32);
        return;
    L5:
        r32 = 4;
        goto L6
    }

    public abstract void setAnchorView(View r1);

    public void setEpicenterBounds(Rect r1) {
        this.mEpicenterBounds = r1;
    }

    public abstract void setForceShowIcon(boolean r1);

    public abstract void setGravity(int r1);

    public abstract void setHorizontalOffset(int r1);

    public abstract void setOnDismissListener(PopupWindow.OnDismissListener r1);

    public abstract void setShowTitle(boolean r1);

    public abstract void setVerticalOffset(int r1);
}
