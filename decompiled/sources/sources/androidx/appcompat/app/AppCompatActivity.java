package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.ViewTreeOnBackPressedDispatcherOwner;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;

/* loaded from: classes3.dex */
public abstract class AppCompatActivity extends FragmentActivity implements AppCompatCallback, TaskStackBuilder.SupportParentable {
    private static final String DELEGATE_TAG = "androidx:appcompat";
    private AppCompatDelegate mDelegate;
    private Resources mResources;

    /* renamed from: androidx.appcompat.app.AppCompatActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements SavedStateRegistry.SavedStateProvider {
        final /* synthetic */ AppCompatActivity this$0;

        public AnonymousClass1(AppCompatActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
        public Bundle saveState() {
            Bundle r0 = new Bundle();
            this.this$0.getDelegate().onSaveInstanceState(r0);
            return r0;
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements OnContextAvailableListener {
        final /* synthetic */ AppCompatActivity this$0;

        public AnonymousClass2(AppCompatActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.activity.contextaware.OnContextAvailableListener
        public void onContextAvailable(Context r2) {
            AppCompatDelegate r22 = this.this$0.getDelegate();
            r22.installViewFactory();
            r22.onCreate(this.this$0.getSavedStateRegistry().consumeRestoredStateForKey(AppCompatActivity.DELEGATE_TAG));
        }
    }

    public AppCompatActivity() {
        initDelegate();
    }

    private void initDelegate() {
        getSavedStateRegistry().registerSavedStateProvider(DELEGATE_TAG, new AnonymousClass1(this));
        addOnContextAvailableListener(new AnonymousClass2(this));
    }

    private void initViewTreeOwners() {
        ViewTreeLifecycleOwner.set(getWindow().getDecorView(), this);
        ViewTreeViewModelStoreOwner.set(getWindow().getDecorView(), this);
        ViewTreeSavedStateRegistryOwner.set(getWindow().getDecorView(), this);
        ViewTreeOnBackPressedDispatcherOwner.set(getWindow().getDecorView(), this);
    }

    private boolean performMenuItemShortcut(KeyEvent r3) {
        if (Build.VERSION.SDK_INT < 26) goto L5;
        return false;
    L5:
        if (r3.isCtrlPressed() == false) goto L7;
        return false;
    L7:
        if (KeyEvent.metaStateHasNoModifiers(r3.getMetaState()) == false) goto L9;
        return false;
    L9:
        if (r3.getRepeatCount() == 0) goto L11;
        return false;
    L11:
        if (KeyEvent.isModifierKey(r3.getKeyCode()) == true) goto L25;
        Window r2 = getWindow();
        if (r2 != null) goto L15;
        return false;
    L15:
        if (r2.getDecorView() != null) goto L17;
        return false;
    L17:
        if (r2.getDecorView().dispatchKeyShortcutEvent(r3) == false) goto L28;
        return true;
    L28:
        return false;
    L25:
        return false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void addContentView(View r1, ViewGroup.LayoutParams r2) {
        initViewTreeOwners();
        getDelegate().addContentView(r1, r2);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context r2) {
        super.attachBaseContext(getDelegate().attachBaseContext2(r2));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        ActionBar r0 = getSupportActionBar();
        if (getWindow().hasFeature(0) == false) goto L9;
        if (r0 != null) goto L6;
    L7:
        super.closeOptionsMenu();
        return;
    L6:
        if (r0.closeOptionsMenu() == false) goto L7;
        return;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent r4) {
        int r0 = r4.getKeyCode();
        ActionBar r1 = getSupportActionBar();
        if (r0 != 82) goto L10;
        if (r1 == null) goto L10;
        if (r1.onMenuKeyEvent(r4) == false) goto L10;
        return true;
    L10:
        return super.dispatchKeyEvent(r4);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int r1) {
        return (T) getDelegate().findViewById(r1);
    }

    public AppCompatDelegate getDelegate() {
        if (this.mDelegate != null) goto L6;
        this.mDelegate = AppCompatDelegate.create(this, this);
    L6:
        return this.mDelegate;
    }

    public ActionBarDrawerToggle$Delegate getDrawerToggleDelegate() {
        return getDelegate().getDrawerToggleDelegate();
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.mResources == null) goto L5;
    L7:
        Resources r0 = this.mResources;
        if (r0 == null) goto L10;
        return r0;
    L10:
        return super.getResources();
    L5:
        if (VectorEnabledTintResources.shouldBeUsed() == false) goto L7;
        this.mResources = new VectorEnabledTintResources(this, super.getResources());
        goto L7
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    @Override // androidx.core.app.TaskStackBuilder.SupportParentable
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        getDelegate().onConfigurationChanged(r2);
        if (this.mResources == null) goto L6;
        Configuration r22 = super.getResources().getConfiguration();
        DisplayMetrics r0 = super.getResources().getDisplayMetrics();
        this.mResources.updateConfiguration(r22, r0);
        return;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        onSupportContentChanged();
    }

    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder r1) {
        r1.addParentStack(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r2, KeyEvent r3) {
        if (performMenuItemShortcut(r3) == false) goto L7;
        return true;
    L7:
        return super.onKeyDown(r2, r3);
    }

    public void onLocalesChanged(LocaleListCompat r1) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int r2, MenuItem r3) {
        if (super.onMenuItemSelected(r2, r3) == false) goto L6;
        return true;
    L6:
        ActionBar r22 = getSupportActionBar();
        if (r3.getItemId() != 16908332) goto L13;
        if (r22 != null) goto L10;
        return false;
    L10:
        if ((r22.getDisplayOptions() & 4) != 0) goto L12;
        return false;
    L12:
        return onSupportNavigateUp();
    L13:
        return false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int r1, Menu r2) {
        return super.onMenuOpened(r1, r2);
    }

    public void onNightModeChanged(int r1) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int r1, Menu r2) {
        super.onPanelClosed(r1, r2);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle r1) {
        super.onPostCreate(r1);
        getDelegate().onPostCreate(r1);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder r1) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        getDelegate().onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeFinished(ActionMode r1) {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeStarted(ActionMode r1) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    public boolean onSupportNavigateUp() {
        Intent r0 = getSupportParentActivityIntent();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (supportShouldUpRecreateTask(r0) == false) goto L10;
        TaskStackBuilder r02 = TaskStackBuilder.create(this);
        onCreateSupportNavigateUpTaskStack(r02);
        onPrepareSupportNavigateUpTaskStack(r02);
        r02.startActivities();
        ActivityCompat.finishAffinity(this);     // Catch: IllegalStateException -> L9
        return true;
    L9:
        finish();
        return true;
    L10:
        supportNavigateUpTo(r0);
        return true;
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence r1, int r2) {
        super.onTitleChanged(r1, r2);
        getDelegate().setTitle(r1);
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback r1) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        ActionBar r0 = getSupportActionBar();
        if (getWindow().hasFeature(0) == false) goto L9;
        if (r0 != null) goto L6;
    L7:
        super.openOptionsMenu();
        return;
    L6:
        if (r0.openOptionsMenu() == false) goto L7;
        return;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int r1) {
        initViewTreeOwners();
        getDelegate().setContentView(r1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View r1) {
        initViewTreeOwners();
        getDelegate().setContentView(r1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View r1, ViewGroup.LayoutParams r2) {
        initViewTreeOwners();
        getDelegate().setContentView(r1, r2);
    }

    public void setSupportActionBar(Toolbar r1) {
        getDelegate().setSupportActionBar(r1);
    }

    @Deprecated
    public void setSupportProgress(int r1) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean r1) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean r1) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean r1) {
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int r1) {
        super.setTheme(r1);
        getDelegate().setTheme(r1);
    }

    public ActionMode startSupportActionMode(ActionMode.Callback r1) {
        return getDelegate().startSupportActionMode(r1);
    }

    public void supportInvalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    public void supportNavigateUpTo(Intent r1) {
        NavUtils.navigateUpTo(this, r1);
    }

    public boolean supportRequestWindowFeature(int r1) {
        return getDelegate().requestWindowFeature(r1);
    }

    public boolean supportShouldUpRecreateTask(Intent r1) {
        return NavUtils.shouldUpRecreateTask(this, r1);
    }
}
