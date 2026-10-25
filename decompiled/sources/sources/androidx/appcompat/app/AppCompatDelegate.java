package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.LocaleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import androidx.core.app.AppLocalesStorageHelper;
import androidx.core.os.LocaleListCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class AppCompatDelegate {
    private static final ArraySet<WeakReference<AppCompatDelegate>> sActivityDelegates = null;
    private static final Object sActivityDelegatesLock = null;
    private static final Object sAppLocalesStorageSyncLock = null;
    private static int sDefaultNightMode;
    private static Boolean sIsAutoStoreLocalesOptedIn;
    private static boolean sIsFrameworkSyncChecked;
    private static LocaleListCompat sRequestedAppLocales;
    static SerialExecutor sSerialExecutorForLocalesStorage;
    private static LocaleListCompat sStoredAppLocales;

    public static class Api24Impl {
        public static LocaleList localeListForLanguageTags(String r0) {
            return LocaleList.forLanguageTags(r0);
        }
    }

    public static class Api33Impl {
        public static LocaleList localeManagerGetApplicationLocales(Object r0) {
            return ((LocaleManager) r0).getApplicationLocales();
        }

        public static void localeManagerSetApplicationLocales(Object r0, LocaleList r1) {
            ((LocaleManager) r0).setApplicationLocales(r1);
        }
    }

    public static class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock;
        final Queue<Runnable> mTasks;

        public static /* synthetic */ void $r8$lambda$TIOuFfuIY7It2pdRDpb_0gu8hwg(SerialExecutor r0, Runnable r1) {
            r0.getClass();
            r1.run();     // Catch: Throwable -> L6
            r0.scheduleNext();
            return;
        L6:
            th = move-exception;
            r0.scheduleNext();
            throw th;
        }

        public SerialExecutor(Executor r2) {
            this.mLock = new Object();
            this.mTasks = new ArrayDeque();
            this.mExecutor = r2;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r4) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mTasks.add(new AppCompatDelegate$SerialExecutor$$ExternalSyntheticLambda0(this, r4));     // Catch: Throwable -> L7
            if (this.mActive != null) goto L9;
            scheduleNext();     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        public void scheduleNext() {
            Object r0 = this.mLock;
            monitor-enter(r0);
            Runnable r1 = this.mTasks.poll();     // Catch: Throwable -> L7
            this.mActive = r1;     // Catch: Throwable -> L7
            if (r1 == null) goto L9;
            this.mExecutor.execute(r1);     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    public static class ThreadPerTaskExecutor implements Executor {
        public ThreadPerTaskExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r1) {
            new Thread(r1).start();
        }
    }

    public static /* synthetic */ void $r8$lambda$OR1arlBlakx1bIyofAYIQapPQac(Context r0) {
        syncLocalesToFramework(r0);
        sIsFrameworkSyncChecked = true;
    }

    static {
        sSerialExecutorForLocalesStorage = new SerialExecutor(new ThreadPerTaskExecutor());
        sDefaultNightMode = -100;
        sRequestedAppLocales = null;
        sStoredAppLocales = null;
        sIsAutoStoreLocalesOptedIn = null;
        sIsFrameworkSyncChecked = false;
        sActivityDelegates = new ArraySet();
        sActivityDelegatesLock = new Object();
        sAppLocalesStorageSyncLock = new Object();
    }

    public AppCompatDelegate() {
    }

    public static void addActiveDelegate(AppCompatDelegate r3) {
        Object r0 = sActivityDelegatesLock;
        monitor-enter(r0);
        removeDelegateFromActives(r3);     // Catch: Throwable -> L7
        sActivityDelegates.add(new WeakReference(r3));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static AppCompatDelegate create(Activity r1, AppCompatCallback r2) {
        return new AppCompatDelegateImpl(r1, r2);
    }

    public static AppCompatDelegate create(Dialog r1, AppCompatCallback r2) {
        return new AppCompatDelegateImpl(r1, r2);
    }

    public static LocaleListCompat getApplicationLocales() {
        if (Build.VERSION.SDK_INT < 33) goto L8;
        Object r0 = getLocaleManagerForApplication();
        if (r0 == null) goto L12;
        return LocaleListCompat.wrap(Api33Impl.localeManagerGetApplicationLocales(r0));
    L12:
        return LocaleListCompat.getEmptyLocaleList();
    L8:
        LocaleListCompat r02 = sRequestedAppLocales;
        if (r02 == null) goto L12;
        return r02;
    }

    public static int getDefaultNightMode() {
        return sDefaultNightMode;
    }

    public static Object getLocaleManagerForApplication() {
        Iterator<WeakReference<AppCompatDelegate>> r0 = sActivityDelegates.iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        AppCompatDelegate r1 = r0.next().get();
        if (r1 == null) goto L4;
        Context r12 = r1.getContextForDelegate();
        if (r12 == null) goto L4;
        return r12.getSystemService("locale");
    L11:
        return null;
    }

    public static LocaleListCompat getRequestedAppLocales() {
        return sRequestedAppLocales;
    }

    public static boolean isAutoStorageOptedIn(Context r1) {
        if (sIsAutoStoreLocalesOptedIn != null) goto L10;
        Bundle r12 = AppLocalesMetadataHolderService.getServiceInfo(r1).metaData;     // Catch: PackageManager.NameNotFoundException -> L8
        if (r12 == null) goto L10;
        sIsAutoStoreLocalesOptedIn = Boolean.valueOf(r12.getBoolean("autoStoreLocales"));     // Catch: PackageManager.NameNotFoundException -> L8
    L8:
        Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
        sIsAutoStoreLocalesOptedIn = Boolean.FALSE;
    L10:
        return sIsAutoStoreLocalesOptedIn.booleanValue();
    }

    public static void removeActivityDelegate(AppCompatDelegate r1) {
        Object r0 = sActivityDelegatesLock;
        monitor-enter(r0);
        removeDelegateFromActives(r1);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static void removeDelegateFromActives(AppCompatDelegate r3) {
        Object r0 = sActivityDelegatesLock;
        monitor-enter(r0);
        Iterator<WeakReference<AppCompatDelegate>> r1 = sActivityDelegates.iterator();     // Catch: Throwable -> L11
    L6:
        if (r1.hasNext() == false) goto L13;
        AppCompatDelegate r2 = r1.next().get();     // Catch: Throwable -> L11
        if (r2 == r3) goto L10;
        if (r2 != null) goto L6;
    L10:
        r1.remove();     // Catch: Throwable -> L11
        goto L6
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public static void syncLocalesToFramework(Context r4) {
        if (Build.VERSION.SDK_INT < 33) goto L13;
        ComponentName r0 = new ComponentName(r4, "androidx.appcompat.app.AppLocalesMetadataHolderService");
        if (r4.getPackageManager().getComponentEnabledSetting(r0) != 1) goto L7;
        return;
    L7:
        if (getApplicationLocales().isEmpty() == false) goto L11;
        String r1 = AppLocalesStorageHelper.readLocales(r4);
        Object r3 = r4.getSystemService("locale");
        if (r3 == null) goto L11;
        Api33Impl.localeManagerSetApplicationLocales(r3, Api24Impl.localeListForLanguageTags(r1));
    L11:
        r4.getPackageManager().setComponentEnabledSetting(r0, 1, 1);
        return;
    }

    public static void syncRequestedAndStoredLocales(Context r3) {
        if (isAutoStorageOptedIn(r3) == true) goto L6;
        return;
    L6:
        if (Build.VERSION.SDK_INT >= 33) goto L8;
        Object r0 = sAppLocalesStorageSyncLock;
        monitor-enter(r0);
        LocaleListCompat r1 = sRequestedAppLocales;     // Catch: Throwable -> L18
        if (r1 != null) goto L26;
        if (sStoredAppLocales != null) goto L21;
        sStoredAppLocales = LocaleListCompat.forLanguageTags(AppLocalesStorageHelper.readLocales(r3));     // Catch: Throwable -> L18
    L21:
        if (sStoredAppLocales.isEmpty() == false) goto L24;
        monitor-exit(r0);     // Catch: Throwable -> L18
        return;
    L24:
        sRequestedAppLocales = sStoredAppLocales;     // Catch: Throwable -> L18
    L28:
        monitor-exit(r0);     // Catch: Throwable -> L18
        return;
    L26:
        if (r1.equals(sStoredAppLocales) == true) goto L28;
        LocaleListCompat r12 = sRequestedAppLocales;     // Catch: Throwable -> L18
        sStoredAppLocales = r12;     // Catch: Throwable -> L18
        AppLocalesStorageHelper.persistLocales(r3, r12.toLanguageTags());     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        throw th;
    L8:
        if (sIsFrameworkSyncChecked == true) goto L33;
        sSerialExecutorForLocalesStorage.execute(new AppCompatDelegate$$ExternalSyntheticLambda0(r3));
        return;
    }

    public abstract void addContentView(View r1, ViewGroup.LayoutParams r2);

    @Deprecated
    public void attachBaseContext(Context r1) {
    }

    public Context attachBaseContext2(Context r1) {
        attachBaseContext(r1);
        return r1;
    }

    public abstract <T extends View> T findViewById(int r1);

    public abstract Context getContextForDelegate();

    public abstract ActionBarDrawerToggle$Delegate getDrawerToggleDelegate();

    public abstract int getLocalNightMode();

    public abstract MenuInflater getMenuInflater();

    public abstract ActionBar getSupportActionBar();

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract void onConfigurationChanged(Configuration r1);

    public abstract void onCreate(Bundle r1);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle r1);

    public abstract void onPostResume();

    public abstract void onSaveInstanceState(Bundle r1);

    public abstract void onStart();

    public abstract void onStop();

    public abstract boolean requestWindowFeature(int r1);

    public abstract void setContentView(int r1);

    public abstract void setContentView(View r1);

    public abstract void setContentView(View r1, ViewGroup.LayoutParams r2);

    public void setOnBackInvokedDispatcher(OnBackInvokedDispatcher r1) {
    }

    public abstract void setSupportActionBar(Toolbar r1);

    public abstract void setTheme(int r1);

    public abstract void setTitle(CharSequence r1);

    public abstract ActionMode startSupportActionMode(ActionMode.Callback r1);
}
