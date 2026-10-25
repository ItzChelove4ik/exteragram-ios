package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class ToolbarActionBar extends ActionBar {
    final DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    final AppCompatDelegateImpl.ActionBarMenuCallback mMenuCallback;
    private boolean mMenuCallbackSet;
    private final Toolbar.OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList<Object> mMenuVisibilityListeners;
    boolean mToolbarMenuPrepared;
    final Window.Callback mWindowCallback;

    /* renamed from: androidx.appcompat.app.ToolbarActionBar$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ToolbarActionBar this$0;

        public AnonymousClass1(ToolbarActionBar r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.populateOptionsMenu();
        }
    }

    /* renamed from: androidx.appcompat.app.ToolbarActionBar$2, reason: invalid class name */
    public class AnonymousClass2 implements Toolbar.OnMenuItemClickListener {
        final /* synthetic */ ToolbarActionBar this$0;

        public AnonymousClass2(ToolbarActionBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem r2) {
            return this.this$0.mWindowCallback.onMenuItemSelected(0, r2);
        }
    }

    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        private boolean mClosingActionMenu;
        final /* synthetic */ ToolbarActionBar this$0;

        public ActionMenuPresenterCallback(ToolbarActionBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder r2, boolean r3) {
            if (this.mClosingActionMenu == false) goto L5;
            return;
        L5:
            this.mClosingActionMenu = true;
            this.this$0.mDecorToolbar.dismissPopupMenus();
            this.this$0.mWindowCallback.onPanelClosed(108, r2);
            this.mClosingActionMenu = false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder r2) {
            this.this$0.mWindowCallback.onMenuOpened(108, r2);
            return true;
        }
    }

    public final class MenuBuilderCallback implements MenuBuilder.Callback {
        final /* synthetic */ ToolbarActionBar this$0;

        public MenuBuilderCallback(ToolbarActionBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder r1, MenuItem r2) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder r5) {
            boolean r0 = this.this$0.mDecorToolbar.isOverflowMenuShowing();
            ToolbarActionBar r1 = this.this$0;
            if (r0 == false) goto L7;
            r1.mWindowCallback.onPanelClosed(108, r5);
            return;
        L7:
            if (r1.mWindowCallback.onPreparePanel(0, null, r5) == false) goto L10;
            this.this$0.mWindowCallback.onMenuOpened(108, r5);
            return;
        }
    }

    public class ToolbarMenuCallback implements AppCompatDelegateImpl.ActionBarMenuCallback {
        final /* synthetic */ ToolbarActionBar this$0;

        public ToolbarMenuCallback(ToolbarActionBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.ActionBarMenuCallback
        public View onCreatePanelView(int r1) {
            if (r1 == 0) goto L4;
            return null;
        L4:
            return new View(this.this$0.mDecorToolbar.getContext());
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.ActionBarMenuCallback
        public boolean onPreparePanel(int r2) {
            if (r2 != 0) goto L8;
            ToolbarActionBar r22 = this.this$0;
            if (r22.mToolbarMenuPrepared == true) goto L9;
            r22.mDecorToolbar.setMenuPrepared();
            this.this$0.mToolbarMenuPrepared = true;
            return false;
        L9:
            return false;
        L8:
            return false;
        }
    }

    public ToolbarActionBar(Toolbar r4, CharSequence r5, Window.Callback r6) {
        this.mMenuVisibilityListeners = new ArrayList();
        this.mMenuInvalidator = new AnonymousClass1(this);
        AnonymousClass2 r0 = new AnonymousClass2(this);
        this.mMenuClicker = r0;
        Preconditions.checkNotNull(r4);
        ToolbarWidgetWrapper r1 = new ToolbarWidgetWrapper(r4, false);
        this.mDecorToolbar = r1;
        this.mWindowCallback = (Window.Callback) Preconditions.checkNotNull(r6);
        r1.setWindowCallback(r6);
        r4.setOnMenuItemClickListener(r0);
        r1.setWindowTitle(r5);
        this.mMenuCallback = new ToolbarMenuCallback(this);
    }

    private Menu getMenu() {
        if (this.mMenuCallbackSet == true) goto L6;
        this.mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(this), new MenuBuilderCallback(this));
        this.mMenuCallbackSet = true;
    L6:
        return this.mDecorToolbar.getMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean closeOptionsMenu() {
        return this.mDecorToolbar.hideOverflowMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean collapseActionView() {
        if (this.mDecorToolbar.hasExpandedActionView() == false) goto L6;
        this.mDecorToolbar.collapseActionView();
        return true;
    L6:
        return false;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void dispatchMenuVisibilityChanged(boolean r2) {
        if (r2 == this.mLastMenuVisibility) goto L10;
        this.mLastMenuVisibility = r2;
        if (this.mMenuVisibilityListeners.size() > 0) goto L8;
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mMenuVisibilityListeners.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void onDestroy() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean onKeyShortcut(int r4, KeyEvent r5) {
        Menu r3 = getMenu();
        if (r3 == null) goto L13;
        if (r5 == null) goto L6;
        int r1 = r5.getDeviceId();
    L7:
        boolean r2 = true;
        if (KeyCharacterMap.load(r1).getKeyboardType() != 1) goto L11;
        r2 = false;
    L11:
        r3.setQwertyMode(r2);
        return r3.performShortcut(r4, r5, 0);
    L6:
        r1 = -1;
        goto L7
    L13:
        return false;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean onMenuKeyEvent(KeyEvent r2) {
        if (r2.getAction() != 1) goto L5;
        openOptionsMenu();
    L5:
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean openOptionsMenu() {
        return this.mDecorToolbar.showOverflowMenu();
    }

    public void populateOptionsMenu() {
        Menu r0 = getMenu();
        if ((r0 instanceof MenuBuilder) == false) goto L5;
        MenuBuilder r1 = (MenuBuilder) r0;
    L6:
        if (r1 == null) goto L22;
        r1.stopDispatchingItemsChanged();
    L22:
        r0.clear();     // Catch: Throwable -> L13
        if (this.mWindowCallback.onCreatePanelMenu(0, r0) == true) goto L11;
    L15:
        r0.clear();     // Catch: Throwable -> L13
    L16:
        if (r1 == null) goto L24;
        r1.startDispatchingItemsChanged();
        return;
    L24:
        return;
    L11:
        if (this.mWindowCallback.onPreparePanel(0, null, r0) == true) goto L16;
    L13:
        th = move-exception;
        if (r1 == null) goto L21;
        r1.startDispatchingItemsChanged();
    L21:
        throw th;
    L5:
        r1 = null;
        goto L6
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean r1) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean r2) {
        if (r2 == false) goto L5;
        int r22 = 4;
    L6:
        setDisplayOptions(r22, 4);
        return;
    L5:
        r22 = 0;
        goto L6
    }

    public void setDisplayOptions(int r2, int r3) {
        int r0 = this.mDecorToolbar.getDisplayOptions();
        this.mDecorToolbar.setDisplayOptions((r2 & r3) | ((~r3) & r0));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeAsUpIndicator(int r1) {
        this.mDecorToolbar.setNavigationIcon(r1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setShowHideAnimationEnabled(boolean r1) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSubtitle(CharSequence r1) {
        this.mDecorToolbar.setSubtitle(r1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setTitle(CharSequence r1) {
        this.mDecorToolbar.setTitle(r1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setWindowTitle(CharSequence r1) {
        this.mDecorToolbar.setWindowTitle(r1);
    }
}
