package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class MenuItemWrapperICS extends BaseMenuWrapper implements MenuItem {
    private Method mSetExclusiveCheckableMethod;
    private final SupportMenuItem mWrappedObject;

    public class ActionProviderWrapper extends ActionProvider implements ActionProvider.VisibilityListener {
        private final android.view.ActionProvider mInner;
        private ActionProvider.VisibilityListener mListener;
        final /* synthetic */ MenuItemWrapperICS this$0;

        public ActionProviderWrapper(MenuItemWrapperICS r1, Context r2, android.view.ActionProvider r3) {
            this.this$0 = r1;
            super(r2);
            this.mInner = r3;
        }

        public static /* synthetic */ android.view.ActionProvider access$000(ActionProviderWrapper r0) {
            return r0.mInner;
        }

        @Override // androidx.core.view.ActionProvider
        public boolean hasSubMenu() {
            return this.mInner.hasSubMenu();
        }

        @Override // androidx.core.view.ActionProvider
        public boolean isVisible() {
            return this.mInner.isVisible();
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean r1) {
            ActionProvider.VisibilityListener r0 = this.mListener;
            if (r0 == null) goto L6;
            r0.onActionProviderVisibilityChanged(r1);
            return;
        }

        @Override // androidx.core.view.ActionProvider
        public View onCreateActionView() {
            return this.mInner.onCreateActionView();
        }

        @Override // androidx.core.view.ActionProvider
        public View onCreateActionView(MenuItem r1) {
            return this.mInner.onCreateActionView(r1);
        }

        @Override // androidx.core.view.ActionProvider
        public boolean onPerformDefaultAction() {
            return this.mInner.onPerformDefaultAction();
        }

        @Override // androidx.core.view.ActionProvider
        public void onPrepareSubMenu(SubMenu r2) {
            this.mInner.onPrepareSubMenu(this.this$0.getSubMenuWrapper(r2));
        }

        @Override // androidx.core.view.ActionProvider
        public boolean overridesItemVisibility() {
            return this.mInner.overridesItemVisibility();
        }

        @Override // androidx.core.view.ActionProvider
        public void refreshVisibility() {
            this.mInner.refreshVisibility();
        }

        @Override // androidx.core.view.ActionProvider
        public void setVisibilityListener(ActionProvider.VisibilityListener r2) {
            this.mListener = r2;
            android.view.ActionProvider r0 = this.mInner;
            if (r2 != null) goto L6;
            ActionProviderWrapper this = null;
        L6:
            r0.setVisibilityListener(this);
        }
    }

    public static class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView {
        final android.view.CollapsibleActionView mWrappedView;

        /* JADX WARN: Multi-variable type inference failed */
        public CollapsibleActionViewWrapper(View r2) {
            super(r2.getContext());
            this.mWrappedView = (android.view.CollapsibleActionView) r2;
            addView(r2);
        }

        public View getWrappedView() {
            return (View) this.mWrappedView;
        }

        @Override // androidx.appcompat.view.CollapsibleActionView
        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }

        @Override // androidx.appcompat.view.CollapsibleActionView
        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }
    }

    public class OnActionExpandListenerWrapper implements MenuItem.OnActionExpandListener {
        private final MenuItem.OnActionExpandListener mObject;
        final /* synthetic */ MenuItemWrapperICS this$0;

        public OnActionExpandListenerWrapper(MenuItemWrapperICS r1, MenuItem.OnActionExpandListener r2) {
            this.this$0 = r1;
            this.mObject = r2;
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem r2) {
            return this.mObject.onMenuItemActionCollapse(this.this$0.getMenuItemWrapper(r2));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem r2) {
            return this.mObject.onMenuItemActionExpand(this.this$0.getMenuItemWrapper(r2));
        }
    }

    public class OnMenuItemClickListenerWrapper implements MenuItem.OnMenuItemClickListener {
        private final MenuItem.OnMenuItemClickListener mObject;
        final /* synthetic */ MenuItemWrapperICS this$0;

        public OnMenuItemClickListenerWrapper(MenuItemWrapperICS r1, MenuItem.OnMenuItemClickListener r2) {
            this.this$0 = r1;
            this.mObject = r2;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem r2) {
            return this.mObject.onMenuItemClick(this.this$0.getMenuItemWrapper(r2));
        }
    }

    public MenuItemWrapperICS(Context r1, SupportMenuItem r2) {
        super(r1);
        if (r2 == null) goto L6;
        this.mWrappedObject = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Wrapped Object can not be null.");
        throw null;
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return this.mWrappedObject.collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return this.mWrappedObject.expandActionView();
    }

    @Override // android.view.MenuItem
    public android.view.ActionProvider getActionProvider() {
        androidx.core.view.ActionProvider r1 = this.mWrappedObject.getSupportActionProvider();
        if ((r1 instanceof ActionProviderWrapper) == true) goto L5;
        return null;
    L5:
        return ActionProviderWrapper.access$000((ActionProviderWrapper) r1);
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View r1 = this.mWrappedObject.getActionView();
        if ((r1 instanceof CollapsibleActionViewWrapper) == true) goto L5;
        return r1;
    L5:
        return ((CollapsibleActionViewWrapper) r1).getWrappedView();
    }

    @Override // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.mWrappedObject.getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mWrappedObject.getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.mWrappedObject.getContentDescription();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.mWrappedObject.getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.mWrappedObject.getIcon();
    }

    @Override // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.mWrappedObject.getIconTintList();
    }

    @Override // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.mWrappedObject.getIconTintMode();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.mWrappedObject.getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.mWrappedObject.getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mWrappedObject.getMenuInfo();
    }

    @Override // android.view.MenuItem
    public int getNumericModifiers() {
        return this.mWrappedObject.getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.mWrappedObject.getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.mWrappedObject.getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return getSubMenuWrapper(this.mWrappedObject.getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.mWrappedObject.getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.mWrappedObject.getTooltipText();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.mWrappedObject.hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.mWrappedObject.isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return this.mWrappedObject.isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return this.mWrappedObject.isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return this.mWrappedObject.isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return this.mWrappedObject.isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(android.view.ActionProvider r3) {
        ActionProviderWrapper r0 = new ActionProviderWrapper(this, this.mContext, r3);
        SupportMenuItem r1 = this.mWrappedObject;
        if (r3 != null) goto L6;
        r0 = null;
    L6:
        r1.setSupportActionProvider(r0);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int r3) {
        this.mWrappedObject.setActionView(r3);
        View r32 = this.mWrappedObject.getActionView();
        if ((r32 instanceof android.view.CollapsibleActionView) == false) goto L5;
        this.mWrappedObject.setActionView(new CollapsibleActionViewWrapper(r32));
    L5:
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View r2) {
        if ((r2 instanceof android.view.CollapsibleActionView) == false) goto L5;
        r2 = new CollapsibleActionViewWrapper(r2);
    L5:
        this.mWrappedObject.setActionView(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char r2) {
        this.mWrappedObject.setAlphabeticShortcut(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char r2, int r3) {
        this.mWrappedObject.setAlphabeticShortcut(r2, r3);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean r2) {
        this.mWrappedObject.setCheckable(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean r2) {
        this.mWrappedObject.setChecked(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence r2) {
        this.mWrappedObject.setContentDescription(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean r2) {
        this.mWrappedObject.setEnabled(r2);
        return this;
    }

    public void setExclusiveCheckable(boolean r4) {
    L7:
        e = move-exception;
        Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
        return;
    L3:
        if (this.mSetExclusiveCheckableMethod != null) goto L5;
        this.mSetExclusiveCheckableMethod = this.mWrappedObject.getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});     // Catch: Exception -> L7
    L5:
        this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, new Object[]{Boolean.valueOf(r4)});     // Catch: Exception -> L7
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int r2) {
        this.mWrappedObject.setIcon(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable r2) {
        this.mWrappedObject.setIcon(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList r2) {
        this.mWrappedObject.setIconTintList(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode r2) {
        this.mWrappedObject.setIconTintMode(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent r2) {
        this.mWrappedObject.setIntent(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char r2) {
        this.mWrappedObject.setNumericShortcut(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char r2, int r3) {
        this.mWrappedObject.setNumericShortcut(r2, r3);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener r3) {
        SupportMenuItem r0 = this.mWrappedObject;
        if (r3 == null) goto L5;
        OnActionExpandListenerWrapper r1 = new OnActionExpandListenerWrapper(this, r3);
    L6:
        r0.setOnActionExpandListener(r1);
        return this;
    L5:
        r1 = null;
        goto L6
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener r3) {
        SupportMenuItem r0 = this.mWrappedObject;
        if (r3 == null) goto L5;
        OnMenuItemClickListenerWrapper r1 = new OnMenuItemClickListenerWrapper(this, r3);
    L6:
        r0.setOnMenuItemClickListener(r1);
        return this;
    L5:
        r1 = null;
        goto L6
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char r2, char r3) {
        this.mWrappedObject.setShortcut(r2, r3);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char r2, char r3, int r4, int r5) {
        this.mWrappedObject.setShortcut(r2, r3, r4, r5);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int r1) {
        this.mWrappedObject.setShowAsAction(r1);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int r2) {
        this.mWrappedObject.setShowAsActionFlags(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int r2) {
        this.mWrappedObject.setTitle(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence r2) {
        this.mWrappedObject.setTitle(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence r2) {
        this.mWrappedObject.setTitleCondensed(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence r2) {
        this.mWrappedObject.setTooltipText(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean r1) {
        return this.mWrappedObject.setVisible(r1);
    }
}
