package com.google.android.material.navigation;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class NavigationBarMenu extends MenuBuilder {
    public static final int NO_MAX_ITEM_LIMIT = Integer.MAX_VALUE;
    private final int maxItemCount;
    private final boolean subMenuSupported;
    private final Class<?> viewClass;

    public NavigationBarMenu(Context r1, Class<?> r2, int r3, boolean r4) {
        super(r1);
        this.viewClass = r2;
        this.maxItemCount = r3;
        this.subMenuSupported = r4;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public MenuItem addInternal(int r3, int r4, int r5, CharSequence r6) {
        if ((size() + 1) > this.maxItemCount) goto L6;
        stopDispatchingItemsChanged();
        MenuItem r32 = super.addInternal(r3, r4, r5, r6);
        startDispatchingItemsChanged();
        return r32;
    L6:
        String r33 = this.viewClass.getSimpleName();
        throw new IllegalArgumentException("Maximum number of items supported by " + r33 + " is " + this.maxItemCount + ". Limit can be checked with " + r33 + "#getMaxItemCount()");
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public SubMenu addSubMenu(int r2, int r3, int r4, CharSequence r5) {
        if (this.subMenuSupported == false) goto L6;
        MenuItemImpl r22 = (MenuItemImpl) addInternal(r2, r3, r4, r5);
        NavigationBarSubMenu r32 = new NavigationBarSubMenu(getContext(), this, r22);
        r22.setSubMenu(r32);
        return r32;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m(this.viewClass.getSimpleName().concat(" does not support submenus"));
        return null;
    }

    public int getMaxItemCount() {
        return this.maxItemCount;
    }
}
