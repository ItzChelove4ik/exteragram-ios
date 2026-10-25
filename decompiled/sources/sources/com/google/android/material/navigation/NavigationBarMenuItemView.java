package com.google.android.material.navigation;

import android.graphics.drawable.Drawable;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;

/* loaded from: classes5.dex */
public interface NavigationBarMenuItemView extends MenuView.ItemView {
    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    /* synthetic */ MenuItemImpl getItemData();

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    /* synthetic */ void initialize(MenuItemImpl r1, int r2);

    boolean isExpanded();

    boolean isOnlyVisibleWhenExpanded();

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    /* synthetic */ boolean prefersCondensedTitle();

    /* synthetic */ void setCheckable(boolean r1);

    /* synthetic */ void setChecked(boolean r1);

    /* synthetic */ void setEnabled(boolean r1);

    void setExpanded(boolean r1);

    /* synthetic */ void setIcon(Drawable r1);

    void setOnlyShowWhenExpanded(boolean r1);

    /* synthetic */ void setShortcut(boolean r1, char r2);

    /* synthetic */ void setTitle(CharSequence r1);

    /* synthetic */ boolean showsIcon();
}
