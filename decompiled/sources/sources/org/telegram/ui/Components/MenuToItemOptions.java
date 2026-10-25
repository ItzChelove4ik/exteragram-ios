package org.telegram.ui.Components;

import android.content.ComponentName;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.FloatingToolbar;

/* loaded from: classes7.dex */
public class MenuToItemOptions implements Menu {
    private final ItemOptions itemOptions;
    private final Utilities.Callback<Integer> onMenuClicked;
    private final Runnable premiumLock;

    public static /* synthetic */ void $r8$lambda$vmhyYUdu2iQgI0nCm9bPdUHxcXo(MenuToItemOptions r0, int r1) {
        r0.lambda$add$0(r1);
    }

    public MenuToItemOptions(ItemOptions r1, Utilities.Callback<Integer> r2, Runnable r3) {
        this.itemOptions = r1;
        this.onMenuClicked = r2;
        this.premiumLock = r3;
    }

    private /* synthetic */ void lambda$add$0(int r1) {
        this.onMenuClicked.run(Integer.valueOf(r1));
    }

    @Override // android.view.Menu
    public MenuItem add(int r1) {
        return null;
    }

    @Override // android.view.Menu
    public MenuItem add(int r1, int r2, int r3, int r4) {
        return add(r1, r2, r3, LocaleController.getString(r4));
    }

    @Override // android.view.Menu
    public MenuItem add(int r2, int r3, int r4, CharSequence r5) {
        if (this.premiumLock != null) goto L5;
    L9:
        this.itemOptions.add(r5, new MenuToItemOptions$$ExternalSyntheticLambda0(this, r3));
        if (this.premiumLock != null) goto L12;
    L14:
        return null;
    L12:
        if (FloatingToolbar.premiumOptions.contains(Integer.valueOf(r3)) == false) goto L14;
        this.itemOptions.putPremiumLock(this.premiumLock);
        goto L14
    L5:
        if (FloatingToolbar.premiumOptions.contains(Integer.valueOf(r3)) == false) goto L9;
        if (MessagesController.getInstance(UserConfig.selectedAccount).premiumFeaturesBlocked() == false) goto L9;
        return null;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence r1) {
        return null;
    }

    @Override // android.view.Menu
    public int addIntentOptions(int r1, int r2, int r3, ComponentName r4, Intent[] r5, Intent r6, int r7, MenuItem[] r8) {
        return 0;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r1) {
        return null;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r1, int r2, int r3, int r4) {
        return null;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r1, int r2, int r3, CharSequence r4) {
        return null;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence r1) {
        return null;
    }

    @Override // android.view.Menu
    public void clear() {
    }

    @Override // android.view.Menu
    public void close() {
    }

    @Override // android.view.Menu
    public MenuItem findItem(int r1) {
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int r1) {
        return null;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return false;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int r1, KeyEvent r2) {
        return false;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int r1, int r2) {
        return false;
    }

    @Override // android.view.Menu
    public boolean performShortcut(int r1, KeyEvent r2, int r3) {
        return false;
    }

    @Override // android.view.Menu
    public void removeGroup(int r1) {
    }

    @Override // android.view.Menu
    public void removeItem(int r1) {
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int r1, boolean r2, boolean r3) {
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int r1, boolean r2) {
    }

    @Override // android.view.Menu
    public void setGroupVisible(int r1, boolean r2) {
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean r1) {
    }

    @Override // android.view.Menu
    public int size() {
        return 0;
    }
}
