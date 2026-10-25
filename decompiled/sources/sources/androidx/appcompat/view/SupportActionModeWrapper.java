package androidx.appcompat.view;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.view.menu.MenuWrapperICS;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportMenuItem;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class SupportActionModeWrapper extends android.view.ActionMode {
    final Context mContext;
    final ActionMode mWrappedObject;

    public static class CallbackWrapper implements ActionMode.Callback {
        final ArrayList<SupportActionModeWrapper> mActionModes;
        final Context mContext;
        final SimpleArrayMap<Menu, Menu> mMenus;
        final ActionMode.Callback mWrappedCallback;

        public CallbackWrapper(Context r1, ActionMode.Callback r2) {
            this.mContext = r1;
            this.mWrappedCallback = r2;
            this.mActionModes = new ArrayList();
            this.mMenus = new SimpleArrayMap();
        }

        private Menu getMenuWrapper(Menu r4) {
            Menu r0 = this.mMenus.get(r4);
            if (r0 != null) goto L6;
            MenuWrapperICS r02 = new MenuWrapperICS(this.mContext, (SupportMenu) r4);
            this.mMenus.put(r4, r02);
            return r02;
        L6:
            return r0;
        }

        public android.view.ActionMode getActionModeWrapper(ActionMode r5) {
            int r0 = this.mActionModes.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L10;
            SupportActionModeWrapper r2 = this.mActionModes.get(r1);
            if (r2 == null) goto L9;
            if (r2.mWrappedObject != r5) goto L9;
            return r2;
        L9:
            r1 = r1 + 1;
            goto L3
        L10:
            SupportActionModeWrapper r02 = new SupportActionModeWrapper(this.mContext, r5);
            this.mActionModes.add(r02);
            return r02;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r3, MenuItem r4) {
            return this.mWrappedCallback.onActionItemClicked(getActionModeWrapper(r3), new MenuItemWrapperICS(this.mContext, (SupportMenuItem) r4));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r2, Menu r3) {
            return this.mWrappedCallback.onCreateActionMode(getActionModeWrapper(r2), getMenuWrapper(r3));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r2) {
            this.mWrappedCallback.onDestroyActionMode(getActionModeWrapper(r2));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r2, Menu r3) {
            return this.mWrappedCallback.onPrepareActionMode(getActionModeWrapper(r2), getMenuWrapper(r3));
        }
    }

    public SupportActionModeWrapper(Context r1, ActionMode r2) {
        this.mContext = r1;
        this.mWrappedObject = r2;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.mWrappedObject.finish();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.mWrappedObject.getCustomView();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new MenuWrapperICS(this.mContext, (SupportMenu) this.mWrappedObject.getMenu());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.mWrappedObject.getMenuInflater();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.mWrappedObject.getSubtitle();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.mWrappedObject.getTag();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.mWrappedObject.getTitleOptionalHint();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.mWrappedObject.invalidate();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.mWrappedObject.isTitleOptional();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View r1) {
        this.mWrappedObject.setCustomView(r1);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int r1) {
        this.mWrappedObject.setSubtitle(r1);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence r1) {
        this.mWrappedObject.setSubtitle(r1);
    }

    @Override // android.view.ActionMode
    public void setTag(Object r1) {
        this.mWrappedObject.setTag(r1);
    }

    @Override // android.view.ActionMode
    public void setTitle(int r1) {
        this.mWrappedObject.setTitle(r1);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence r1) {
        this.mWrappedObject.setTitle(r1);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean r1) {
        this.mWrappedObject.setTitleOptionalHint(r1);
    }
}
