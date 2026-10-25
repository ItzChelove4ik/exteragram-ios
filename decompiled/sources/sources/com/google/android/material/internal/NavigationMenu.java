package com.google.android.material.internal;

import android.content.Context;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

/* loaded from: classes5.dex */
public class NavigationMenu extends MenuBuilder {
    public NavigationMenu(Context r1) {
        super(r1);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public SubMenu addSubMenu(int r1, int r2, int r3, CharSequence r4) {
        MenuItemImpl r12 = (MenuItemImpl) addInternal(r1, r2, r3, r4);
        NavigationSubMenu r22 = new NavigationSubMenu(getContext(), this, r12);
        r12.setSubMenu(r22);
        return r22;
    }
}
