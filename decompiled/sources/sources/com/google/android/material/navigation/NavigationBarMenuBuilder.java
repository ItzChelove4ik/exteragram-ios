package com.google.android.material.navigation;

import android.view.MenuItem;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class NavigationBarMenuBuilder {
    private int contentItemCount;
    private final List<MenuItem> items;
    private final MenuBuilder menuBuilder;
    private int visibleContentItemCount;
    private int visibleMainItemCount;

    public NavigationBarMenuBuilder(MenuBuilder r2) {
        this.contentItemCount = 0;
        this.visibleContentItemCount = 0;
        this.visibleMainItemCount = 0;
        this.menuBuilder = r2;
        this.items = new ArrayList();
        refreshItems();
    }

    public int getContentItemCount() {
        return this.contentItemCount;
    }

    public MenuItem getItemAt(int r1) {
        return this.items.get(r1);
    }

    public int getVisibleContentItemCount() {
        return this.visibleContentItemCount;
    }

    public int getVisibleMainContentItemCount() {
        return this.visibleMainItemCount;
    }

    public boolean performItemAction(MenuItem r1, MenuPresenter r2, int r3) {
        return this.menuBuilder.performItemAction(r1, r2, r3);
    }

    public void refreshItems() {
        this.items.clear();
        this.contentItemCount = 0;
        this.visibleContentItemCount = 0;
        this.visibleMainItemCount = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.menuBuilder.size()) goto L30;
        MenuItem r2 = this.menuBuilder.getItem(r1);
        boolean r3 = r2.hasSubMenu();
        List<MenuItem> r4 = this.items;
        if (r3 == true) goto L8;
        r4.add(r2);
        this.contentItemCount++;
        if (r2.isVisible() == false) goto L28;
        this.visibleContentItemCount++;
        this.visibleMainItemCount++;
    L28:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r4.isEmpty() == false) goto L10;
    L14:
        this.items.add(r2);
        SubMenu r32 = r2.getSubMenu();
        int r42 = 0;
    L16:
        if (r42 >= r32.size()) goto L24;
        MenuItem r5 = r32.getItem(r42);
        if (r2.isVisible() == true) goto L20;
        r5.setVisible(false);
    L20:
        this.items.add(r5);
        this.contentItemCount++;
        if (r5.isVisible() == false) goto L23;
        this.visibleContentItemCount++;
    L23:
        r42 = r42 + 1;
        goto L16
    L24:
        this.items.add(new DividerMenuItem());
        goto L28
    L10:
        if ((this.items.get(r3.size() - 1) instanceof DividerMenuItem) == true) goto L14;
        if (r2.isVisible() == false) goto L14;
        this.items.add(new DividerMenuItem());
        goto L14
    L30:
        if (this.items.isEmpty() == false) goto L32;
        return;
    L32:
        if ((this.items.get(r0.size() - 1) instanceof DividerMenuItem) == false) goto L43;
        this.items.remove(r7.size() - 1);
        return;
    }

    public int size() {
        return this.items.size();
    }
}
