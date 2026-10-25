package com.google.android.material.internal;

import android.content.Context;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.SubMenuBuilder;

/* loaded from: classes5.dex */
public class NavigationSubMenu extends SubMenuBuilder {
    public NavigationSubMenu(Context r1, NavigationMenu r2, MenuItemImpl r3) {
        super(r1, r2, r3);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public void onItemsChanged(boolean r1) {
        super.onItemsChanged(r1);
        ((MenuBuilder) getParentMenu()).onItemsChanged(r1);
    }
}
