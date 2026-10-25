package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class NavigationBarSubheaderView extends FrameLayout implements NavigationBarMenuItemView {
    private boolean expanded;
    private MenuItemImpl itemData;
    boolean onlyShowWhenExpanded;
    private final TextView subheaderLabel;
    private ColorStateList textColor;

    public NavigationBarSubheaderView(Context r3) {
        super(r3);
        LayoutInflater.from(r3).inflate(R.layout.m3_navigation_menu_subheader, this, true);
        this.subheaderLabel = (TextView) findViewById(R.id.navigation_menu_subheader_label);
    }

    private void updateVisibility() {
        MenuItemImpl r0 = this.itemData;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isVisible() == true) goto L7;
    L11:
        int r02 = 8;
    L12:
        setVisibility(r02);
        return;
    L7:
        if (this.expanded == false) goto L9;
    L10:
        r02 = 0;
        goto L12
    L9:
        if (this.onlyShowWhenExpanded == true) goto L11;
        goto L10
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView, androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView, androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl r1, int r2) {
        this.itemData = r1;
        r1.setCheckable(false);
        this.subheaderLabel.setText(r1.getTitle());
        updateVisibility();
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public boolean isExpanded() {
        return this.expanded;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public boolean isOnlyVisibleWhenExpanded() {
        return this.onlyShowWhenExpanded;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView, androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setCheckable(boolean r1) {
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setChecked(boolean r1) {
    }

    @Override // android.view.View, com.google.android.material.navigation.NavigationBarMenuItemView
    public void setEnabled(boolean r1) {
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setExpanded(boolean r1) {
        this.expanded = r1;
        updateVisibility();
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setIcon(Drawable r1) {
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setOnlyShowWhenExpanded(boolean r1) {
        this.onlyShowWhenExpanded = r1;
        updateVisibility();
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setShortcut(boolean r1, char r2) {
    }

    public void setTextAppearance(int r2) {
        TextViewCompat.setTextAppearance(this.subheaderLabel, r2);
        ColorStateList r22 = this.textColor;
        if (r22 == null) goto L6;
        this.subheaderLabel.setTextColor(r22);
        return;
    }

    public void setTextColor(ColorStateList r1) {
        this.textColor = r1;
        if (r1 == null) goto L6;
        this.subheaderLabel.setTextColor(r1);
        return;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setTitle(CharSequence r1) {
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public boolean showsIcon() {
        return false;
    }
}
