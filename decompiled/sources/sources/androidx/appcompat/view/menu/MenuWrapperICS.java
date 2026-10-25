package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.core.internal.view.SupportMenu;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class MenuWrapperICS extends BaseMenuWrapper implements Menu {
    private final SupportMenu mWrappedObject;

    public MenuWrapperICS(Context r1, SupportMenu r2) {
        super(r1);
        if (r2 == null) goto L6;
        this.mWrappedObject = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Wrapped Object can not be null.");
        throw null;
    }

    @Override // android.view.Menu
    public MenuItem add(int r2) {
        return getMenuItemWrapper(this.mWrappedObject.add(r2));
    }

    @Override // android.view.Menu
    public MenuItem add(int r2, int r3, int r4, int r5) {
        return getMenuItemWrapper(this.mWrappedObject.add(r2, r3, r4, r5));
    }

    @Override // android.view.Menu
    public MenuItem add(int r2, int r3, int r4, CharSequence r5) {
        return getMenuItemWrapper(this.mWrappedObject.add(r2, r3, r4, r5));
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence r2) {
        return getMenuItemWrapper(this.mWrappedObject.add(r2));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int r12, int r13, int r14, ComponentName r15, Intent[] r16, Intent r17, int r18, MenuItem[] r19) {
        if (r19 == null) goto L6;
        MenuItem[] r1 = new MenuItem[r19.length];
    L5:
        MenuItem[] r10 = r1;
        int r122 = this.mWrappedObject.addIntentOptions(r12, r13, r14, r15, r16, r17, r18, r10);
        if (r10 == null) goto L12;
        int r132 = r10.length;
        int r142 = 0;
    L10:
        if (r142 >= r132) goto L12;
        r19[r142] = getMenuItemWrapper(r10[r142]);
        r142 = r142 + 1;
    L12:
        return r122;
    L6:
        r1 = null;
        goto L5
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r2) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(r2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r2, int r3, int r4, int r5) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(r2, r3, r4, r5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r2, int r3, int r4, CharSequence r5) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(r2, r3, r4, r5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence r2) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(r2));
    }

    @Override // android.view.Menu
    public void clear() {
        internalClear();
        this.mWrappedObject.clear();
    }

    @Override // android.view.Menu
    public void close() {
        this.mWrappedObject.close();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int r2) {
        return getMenuItemWrapper(this.mWrappedObject.findItem(r2));
    }

    @Override // android.view.Menu
    public MenuItem getItem(int r2) {
        return getMenuItemWrapper(this.mWrappedObject.getItem(r2));
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return this.mWrappedObject.hasVisibleItems();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int r1, KeyEvent r2) {
        return this.mWrappedObject.isShortcutKey(r1, r2);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int r1, int r2) {
        return this.mWrappedObject.performIdentifierAction(r1, r2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int r1, KeyEvent r2, int r3) {
        return this.mWrappedObject.performShortcut(r1, r2, r3);
    }

    @Override // android.view.Menu
    public void removeGroup(int r1) {
        internalRemoveGroup(r1);
        this.mWrappedObject.removeGroup(r1);
    }

    @Override // android.view.Menu
    public void removeItem(int r1) {
        internalRemoveItem(r1);
        this.mWrappedObject.removeItem(r1);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int r1, boolean r2, boolean r3) {
        this.mWrappedObject.setGroupCheckable(r1, r2, r3);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int r1, boolean r2) {
        this.mWrappedObject.setGroupEnabled(r1, r2);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int r1, boolean r2) {
        this.mWrappedObject.setGroupVisible(r1, r2);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean r1) {
        this.mWrappedObject.setQwertyMode(r1);
    }

    @Override // android.view.Menu
    public int size() {
        return this.mWrappedObject.size();
    }
}
