package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.view.menu.MenuBuilder;

/* loaded from: classes3.dex */
public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuItemImpl mItem;
    private MenuBuilder mParentMenu;

    public SubMenuBuilder(Context r1, MenuBuilder r2, MenuItemImpl r3) {
        super(r1);
        this.mParentMenu = r2;
        this.mItem = r3;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean collapseItemActionView(MenuItemImpl r1) {
        return this.mParentMenu.collapseItemActionView(r1);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean dispatchMenuItemSelected(MenuBuilder r2, MenuItem r3) {
        if (super.dispatchMenuItemSelected(r2, r3) == false) goto L5;
        return true;
    L5:
        if (this.mParentMenu.dispatchMenuItemSelected(r2, r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean expandItemActionView(MenuItemImpl r1) {
        return this.mParentMenu.expandItemActionView(r1);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public String getActionViewStatesKey() {
        MenuItemImpl r0 = this.mItem;
        if (r0 == null) goto L5;
        int r02 = r0.getItemId();
    L6:
        if (r02 != 0) goto L10;
        return null;
    L10:
        return super.getActionViewStatesKey() + ":" + r02;
    L5:
        r02 = 0;
        goto L6
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.mItem;
    }

    public Menu getParentMenu() {
        return this.mParentMenu;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public MenuBuilder getRootMenu() {
        return this.mParentMenu.getRootMenu();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean isGroupDividerEnabled() {
        return this.mParentMenu.isGroupDividerEnabled();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean isQwertyMode() {
        return this.mParentMenu.isQwertyMode();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public boolean isShortcutsVisible() {
        return this.mParentMenu.isShortcutsVisible();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public void setCallback(MenuBuilder.Callback r1) {
        this.mParentMenu.setCallback(r1);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public void setGroupDividerEnabled(boolean r1) {
        this.mParentMenu.setGroupDividerEnabled(r1);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int r1) {
        return (SubMenu) super.setHeaderIconInt(r1);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable r1) {
        return (SubMenu) super.setHeaderIconInt(r1);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int r1) {
        return (SubMenu) super.setHeaderTitleInt(r1);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence r1) {
        return (SubMenu) super.setHeaderTitleInt(r1);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View r1) {
        return (SubMenu) super.setHeaderViewInt(r1);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int r2) {
        this.mItem.setIcon(r2);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable r2) {
        this.mItem.setIcon(r2);
        return this;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public void setQwertyMode(boolean r1) {
        this.mParentMenu.setQwertyMode(r1);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public void setShortcutsVisible(boolean r1) {
        this.mParentMenu.setShortcutsVisible(r1);
    }
}
