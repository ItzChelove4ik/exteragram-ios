package com.google.android.material.navigation;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* loaded from: classes5.dex */
class DividerMenuItem implements MenuItem {
    public DividerMenuItem() {
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return 0;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return null;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return null;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return 0;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return 0;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return null;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return false;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char r1, char r2) {
        return null;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int r1) {
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence r1) {
        return null;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean r1) {
        return null;
    }
}
