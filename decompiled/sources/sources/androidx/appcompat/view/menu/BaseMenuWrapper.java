package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenuItem;

/* loaded from: classes3.dex */
abstract class BaseMenuWrapper {
    final Context mContext;
    private SimpleArrayMap<SupportMenuItem, MenuItem> mMenuItems;
    private SimpleArrayMap<Object, SubMenu> mSubMenus;

    public BaseMenuWrapper(Context r1) {
        this.mContext = r1;
    }

    public final MenuItem getMenuItemWrapper(MenuItem r3) {
        if ((r3 instanceof SupportMenuItem) == false) goto L11;
        SupportMenuItem r32 = (SupportMenuItem) r3;
        if (this.mMenuItems != null) goto L7;
        this.mMenuItems = new SimpleArrayMap();
    L7:
        MenuItem r0 = this.mMenuItems.get(r32);
        if (r0 != null) goto L12;
        MenuItemWrapperICS r02 = new MenuItemWrapperICS(this.mContext, r32);
        this.mMenuItems.put(r32, r02);
        return r02;
    L12:
        return r0;
    L11:
        return r3;
    }

    public final SubMenu getSubMenuWrapper(SubMenu r1) {
        return r1;
    }

    public final void internalClear() {
        SimpleArrayMap<SupportMenuItem, MenuItem> r0 = this.mMenuItems;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        SimpleArrayMap<Object, SubMenu> r1 = this.mSubMenus;
        if (r1 == null) goto L9;
        r1.clear();
        return;
    }

    public final void internalRemoveGroup(int r3) {
        if (this.mMenuItems == null) goto L12;
        int r0 = 0;
    L7:
        if (r0 >= this.mMenuItems.size()) goto L16;
        if (this.mMenuItems.keyAt(r0).getGroupId() != r3) goto L11;
        this.mMenuItems.removeAt(r0);
        r0 = r0 - 1;
    L11:
        r0 = r0 + 1;
        goto L7
    L16:
        return;
    }

    public final void internalRemoveItem(int r3) {
        if (this.mMenuItems == null) goto L13;
        int r0 = 0;
    L7:
        if (r0 >= this.mMenuItems.size()) goto L16;
        if (this.mMenuItems.keyAt(r0).getItemId() == r3) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        this.mMenuItems.removeAt(r0);
        return;
    L16:
        return;
    }
}
