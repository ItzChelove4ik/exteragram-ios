package androidx.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import androidx.tracing.Trace;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class ComponentActivity extends androidx.core.app.ComponentActivity implements LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, OnConfigurationChangedProvider, OnTrimMemoryProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, MenuHost, FullyDrawnReporterOwner {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private final ActivityResultRegistry mActivityResultRegistry;
    private int mContentLayoutId;
    final ContextAwareHelper mContextAwareHelper;
    private ViewModelProvider.Factory mDefaultFactory;
    private boolean mDispatchingOnMultiWindowModeChanged;
    private boolean mDispatchingOnPictureInPictureModeChanged;
    final FullyDrawnReporter mFullyDrawnReporter;
    private final LifecycleRegistry mLifecycleRegistry;
    private final MenuHostHelper mMenuHostHelper;
    private final AtomicInteger mNextLocalRequestCode;
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final CopyOnWriteArrayList<Consumer<Configuration>> mOnConfigurationChangedListeners;
    private final CopyOnWriteArrayList<Consumer<MultiWindowModeChangedInfo>> mOnMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<Consumer<Intent>> mOnNewIntentListeners;
    private final CopyOnWriteArrayList<Consumer<PictureInPictureModeChangedInfo>> mOnPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<Consumer<Integer>> mOnTrimMemoryListeners;
    final ReportFullyDrawnExecutor mReportFullyDrawnExecutor;
    final SavedStateRegistryController mSavedStateRegistryController;
    private ViewModelStore mViewModelStore;

    /* renamed from: androidx.activity.ComponentActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActivityResultRegistry {
        final /* synthetic */ ComponentActivity this$0;

        /* renamed from: androidx.activity.ComponentActivity$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes3.dex */
        public class RunnableC00001 implements Runnable {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ int val$requestCode;
            final /* synthetic */ ActivityResultContract.SynchronousResult val$synchronousResult;

            public RunnableC00001(AnonymousClass1 r1, int r2, ActivityResultContract.SynchronousResult r3) {
                this.this$1 = r1;
                this.val$requestCode = r2;
                this.val$synchronousResult = r3;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.dispatchResult(this.val$requestCode, this.val$synchronousResult.getValue());
            }
        }

        /* renamed from: androidx.activity.ComponentActivity$1$2, reason: invalid class name */
        /* loaded from: classes3.dex */
        public class AnonymousClass2 implements Runnable {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ IntentSender.SendIntentException val$e;
            final /* synthetic */ int val$requestCode;

            public AnonymousClass2(AnonymousClass1 r1, int r2, IntentSender.SendIntentException r3) {
                this.this$1 = r1;
                this.val$requestCode = r2;
                this.val$e = r3;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.dispatchResult(this.val$requestCode, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", this.val$e));
            }
        }

        public AnonymousClass1(ComponentActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.activity.result.ActivityResultRegistry
        public <I, O> void onLaunch(int r9, ActivityResultContract<I, O> r10, I r11, ActivityOptionsCompat r12) {
            ComponentActivity r0 = this.this$0;
            ActivityResultContract.SynchronousResult<O> r122 = r10.getSynchronousResult(r0, r11);
            if (r122 == null) goto L6;
            new Handler(Looper.getMainLooper()).post(new RunnableC00001(this, r9, r122));
            return;
        L6:
            Intent r102 = r10.createIntent(r0, r11);
            if (r102.getExtras() == null) goto L12;
            if (r102.getExtras().getClassLoader() != null) goto L12;
            r102.setExtrasClassLoader(r0.getClassLoader());
        L12:
            if (r102.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE") == false) goto L15;
            Bundle r123 = r102.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            r102.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
        L14:
            Bundle r7 = r123;
            if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(r102.getAction()) == false) goto L24;
            String[] r8 = r102.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
            if (r8 != null) goto L21;
            r8 = new String[0];
        L21:
            ActivityCompat.requestPermissions(r0, r8, r9);
            return;
        L24:
            if ("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(r102.getAction()) == false) goto L36;
            IntentSenderRequest r103 = (IntentSenderRequest) r102.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            int r2 = r9;
            ActivityCompat.startIntentSenderForResult(r0, r103.getIntentSender(), r2, r103.getFillInIntent(), r103.getFlagsMask(), r103.getFlagsValues(), 0, r7);     // Catch: IntentSender.SendIntentException -> L30
            return;
        L30:
            e = e;
        L31:
            IntentSender.SendIntentException r92 = e;
            new Handler(Looper.getMainLooper()).post(new AnonymousClass2(this, r2, r92));
            return;
        L32:
            e = e;
            r2 = r9;
            goto L31
        L36:
            ActivityCompat.startActivityForResult(r0, r102, r9, r7);
            return;
        L15:
            r123 = null;
            goto L14
        }
    }

    /* renamed from: androidx.activity.ComponentActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements LifecycleEventObserver {
        final /* synthetic */ ComponentActivity this$0;

        public AnonymousClass2(ComponentActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r1, Lifecycle.Event r2) {
            if (r2 != Lifecycle.Event.ON_STOP) goto L11;
            Window r0 = this.this$0.getWindow();
            if (r0 == null) goto L7;
            View r02 = r0.peekDecorView();
        L8:
            if (r02 == null) goto L12;
            Api19Impl.cancelPendingInputEvents(r02);
            return;
        L12:
            return;
        L7:
            r02 = null;
            goto L8
        }
    }

    /* renamed from: androidx.activity.ComponentActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements LifecycleEventObserver {
        final /* synthetic */ ComponentActivity this$0;

        public AnonymousClass3(ComponentActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r1, Lifecycle.Event r2) {
            if (r2 != Lifecycle.Event.ON_DESTROY) goto L9;
            this.this$0.mContextAwareHelper.clearAvailableContext();
            if (this.this$0.isChangingConfigurations() == true) goto L7;
            this.this$0.getViewModelStore().clear();
        L7:
            this.this$0.mReportFullyDrawnExecutor.activityDestroyed();
            return;
        }
    }

    /* renamed from: androidx.activity.ComponentActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements LifecycleEventObserver {
        final /* synthetic */ ComponentActivity this$0;

        public AnonymousClass4(ComponentActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r1, Lifecycle.Event r2) {
            this.this$0.ensureViewModelStore();
            this.this$0.getLifecycle().removeObserver(this);
        }
    }

    /* renamed from: androidx.activity.ComponentActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ ComponentActivity this$0;

        public AnonymousClass5(ComponentActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.access$001(this.this$0);     // Catch: NullPointerException -> L4 IllegalStateException -> L9
            return;
        L9:
            e = move-exception;
            if (TextUtils.equals(e.getMessage(), "Can not perform this action after onSaveInstanceState") == false) goto L13;
            return;
        L13:
            throw e;
        L4:
            e = move-exception;
            if (TextUtils.equals(e.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference") == true) goto L16;
            throw e;
        }
    }

    /* renamed from: androidx.activity.ComponentActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements LifecycleEventObserver {
        final /* synthetic */ ComponentActivity this$0;

        public AnonymousClass6(ComponentActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r2, Lifecycle.Event r3) {
            if (r3 == Lifecycle.Event.ON_CREATE) goto L5;
            return;
        L5:
            if (Build.VERSION.SDK_INT < 33) goto L9;
            ComponentActivity.access$100(this.this$0).setOnBackInvokedDispatcher(Api33Impl.getOnBackInvokedDispatcher((ComponentActivity) r2));
            return;
        }
    }

    /* loaded from: classes3.dex */
    public static class Api19Impl {
        public static void cancelPendingInputEvents(View r0) {
            r0.cancelPendingInputEvents();
        }
    }

    public static class Api33Impl {
        public static OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity r0) {
            return r0.getOnBackInvokedDispatcher();
        }
    }

    public static final class NonConfigurationInstances {
        Object custom;
        ViewModelStore viewModelStore;

        public NonConfigurationInstances() {
        }
    }

    public interface ReportFullyDrawnExecutor extends Executor {
        void activityDestroyed();

        void viewCreated(View r1);
    }

    public class ReportFullyDrawnExecutorApi16Impl implements ReportFullyDrawnExecutor, ViewTreeObserver.OnDrawListener, Runnable {
        final long mEndWatchTimeMillis;
        boolean mOnDrawScheduled;
        Runnable mRunnable;
        final /* synthetic */ ComponentActivity this$0;

        public static /* synthetic */ void $r8$lambda$zFdbhFANN0XaCUXNOV7NjBkM5D0(ReportFullyDrawnExecutorApi16Impl r1) {
            Runnable r0 = r1.mRunnable;
            if (r0 == null) goto L6;
            r0.run();
            r1.mRunnable = null;
            return;
        }

        public ReportFullyDrawnExecutorApi16Impl(ComponentActivity r5) {
            this.this$0 = r5;
            this.mEndWatchTimeMillis = SystemClock.uptimeMillis() + NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
            this.mOnDrawScheduled = false;
        }

        @Override // androidx.activity.ComponentActivity.ReportFullyDrawnExecutor
        public void activityDestroyed() {
            this.this$0.getWindow().getDecorView().removeCallbacks(this);
            this.this$0.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r2) {
            this.mRunnable = r2;
            View r22 = this.this$0.getWindow().getDecorView();
            if (this.mOnDrawScheduled == true) goto L5;
            r22.postOnAnimation(new ComponentActivity$ReportFullyDrawnExecutorApi16Impl$$ExternalSyntheticLambda0(this));
            return;
        L5:
            if (Looper.myLooper() != Looper.getMainLooper()) goto L8;
            r22.invalidate();
            return;
        L8:
            r22.postInvalidate();
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            Runnable r0 = this.mRunnable;
            if (r0 == null) goto L9;
            r0.run();
            this.mRunnable = null;
            if (this.this$0.mFullyDrawnReporter.isFullyDrawnReported() == false) goto L12;
            this.mOnDrawScheduled = false;
            this.this$0.getWindow().getDecorView().post(this);
            return;
        L12:
            return;
        L9:
            if (SystemClock.uptimeMillis() <= this.mEndWatchTimeMillis) goto L13;
            this.mOnDrawScheduled = false;
            this.this$0.getWindow().getDecorView().post(this);
            return;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override // androidx.activity.ComponentActivity.ReportFullyDrawnExecutor
        public void viewCreated(View r2) {
            if (this.mOnDrawScheduled == true) goto L6;
            this.mOnDrawScheduled = true;
            r2.getViewTreeObserver().addOnDrawListener(this);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$278Cq622rQ9X9WaDkyOm2xm3rFw(ComponentActivity r1, Context r2) {
        Bundle r22 = r1.getSavedStateRegistry().consumeRestoredStateForKey(ACTIVITY_RESULT_TAG);
        if (r22 == null) goto L6;
        r1.mActivityResultRegistry.onRestoreInstanceState(r22);
        return;
    }

    /* renamed from: $r8$lambda$71Q7mCYu4-mmbn-a-hJ05KgHel0, reason: not valid java name */
    public static /* synthetic */ Bundle m0$r8$lambda$71Q7mCYu4mmbnahJ05KgHel0(ComponentActivity r1) {
        r1.getClass();
        Bundle r0 = new Bundle();
        r1.mActivityResultRegistry.onSaveInstanceState(r0);
        return r0;
    }

    public static /* synthetic */ Unit $r8$lambda$aw7Gh21f7q0ZVK9WcrxuxkkoVwc(ComponentActivity r0) {
        r0.reportFullyDrawn();
        return null;
    }

    public ComponentActivity() {
        this.mContextAwareHelper = new ContextAwareHelper();
        this.mMenuHostHelper = new MenuHostHelper(new ComponentActivity$$ExternalSyntheticLambda0(this));
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        SavedStateRegistryController r0 = SavedStateRegistryController.create(this);
        this.mSavedStateRegistryController = r0;
        this.mOnBackPressedDispatcher = null;
        ReportFullyDrawnExecutor r1 = createFullyDrawnExecutor();
        this.mReportFullyDrawnExecutor = r1;
        this.mFullyDrawnReporter = new FullyDrawnReporter(r1, new ComponentActivity$$ExternalSyntheticLambda1(this));
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mActivityResultRegistry = new AnonymousClass1(this);
        this.mOnConfigurationChangedListeners = new CopyOnWriteArrayList();
        this.mOnTrimMemoryListeners = new CopyOnWriteArrayList();
        this.mOnNewIntentListeners = new CopyOnWriteArrayList();
        this.mOnMultiWindowModeChangedListeners = new CopyOnWriteArrayList();
        this.mOnPictureInPictureModeChangedListeners = new CopyOnWriteArrayList();
        this.mDispatchingOnMultiWindowModeChanged = false;
        this.mDispatchingOnPictureInPictureModeChanged = false;
        if (getLifecycle() == null) goto L6;
        getLifecycle().addObserver(new AnonymousClass2(this));
        getLifecycle().addObserver(new AnonymousClass3(this));
        getLifecycle().addObserver(new AnonymousClass4(this));
        r0.performAttach();
        SavedStateHandleSupport.enableSavedStateHandles(this);
        getSavedStateRegistry().registerSavedStateProvider(ACTIVITY_RESULT_TAG, new ComponentActivity$$ExternalSyntheticLambda2(this));
        addOnContextAvailableListener(new ComponentActivity$$ExternalSyntheticLambda3(this));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        throw null;
    }

    public static /* synthetic */ void access$001(ComponentActivity r0) {
        super.onBackPressed();
    }

    public static /* synthetic */ OnBackPressedDispatcher access$100(ComponentActivity r0) {
        return r0.mOnBackPressedDispatcher;
    }

    private ReportFullyDrawnExecutor createFullyDrawnExecutor() {
        return new ReportFullyDrawnExecutorApi16Impl(this);
    }

    @Override // android.app.Activity
    public void addContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View r3, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams r4) {
        initializeViewTreeOwners();
        this.mReportFullyDrawnExecutor.viewCreated(getWindow().getDecorView());
        super.addContentView(r3, r4);
    }

    @Override // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider r1) {
        this.mMenuHostHelper.addMenuProvider(r1);
    }

    public void addMenuProvider(MenuProvider r1, LifecycleOwner r2) {
        this.mMenuHostHelper.addMenuProvider(r1, r2);
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(MenuProvider r1, LifecycleOwner r2, Lifecycle.State r3) {
        this.mMenuHostHelper.addMenuProvider(r1, r2, r3);
    }

    @Override // androidx.core.content.OnConfigurationChangedProvider
    public final void addOnConfigurationChangedListener(Consumer<Configuration> r1) {
        this.mOnConfigurationChangedListeners.add(r1);
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener r1) {
        this.mContextAwareHelper.addOnContextAvailableListener(r1);
    }

    @Override // androidx.core.app.OnMultiWindowModeChangedProvider
    public final void addOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> r1) {
        this.mOnMultiWindowModeChangedListeners.add(r1);
    }

    public final void addOnNewIntentListener(Consumer<Intent> r1) {
        this.mOnNewIntentListeners.add(r1);
    }

    @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
    public final void addOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> r1) {
        this.mOnPictureInPictureModeChangedListeners.add(r1);
    }

    @Override // androidx.core.content.OnTrimMemoryProvider
    public final void addOnTrimMemoryListener(Consumer<Integer> r1) {
        this.mOnTrimMemoryListeners.add(r1);
    }

    public void ensureViewModelStore() {
        if (this.mViewModelStore != null) goto L11;
        NonConfigurationInstances r0 = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (r0 == null) goto L8;
        this.mViewModelStore = r0.viewModelStore;
    L8:
        if (this.mViewModelStore != null) goto L12;
        this.mViewModelStore = new ViewModelStore();
        return;
    L12:
        return;
    }

    @Override // androidx.activity.result.ActivityResultRegistryOwner
    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras getDefaultViewModelCreationExtras() {
        MutableCreationExtras r0 = new MutableCreationExtras();
        if (getApplication() == null) goto L5;
        r0.set(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY, getApplication());
    L5:
        r0.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        r0.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        if (getIntent() != null) goto L8;
    L10:
        return r0;
    L8:
        if (getIntent().getExtras() == null) goto L10;
        r0.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, getIntent().getExtras());
        goto L10
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        if (this.mDefaultFactory != null) goto L10;
        Application r1 = getApplication();
        if (getIntent() == null) goto L7;
        Bundle r2 = getIntent().getExtras();
    L8:
        this.mDefaultFactory = new SavedStateViewModelFactory(r1, this, r2);
        goto L10
    L7:
        r2 = null;
    L10:
        return this.mDefaultFactory;
    }

    public FullyDrawnReporter getFullyDrawnReporter() {
        return this.mFullyDrawnReporter;
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances r0 = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.custom;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        if (this.mOnBackPressedDispatcher != null) goto L6;
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new AnonymousClass5(this));
        getLifecycle().addObserver(new AnonymousClass6(this));
    L6:
        return this.mOnBackPressedDispatcher;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        if (getApplication() == null) goto L6;
        ensureViewModelStore();
        return this.mViewModelStore;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        return null;
    }

    public void initializeViewTreeOwners() {
        ViewTreeLifecycleOwner.set(getWindow().getDecorView(), this);
        ViewTreeViewModelStoreOwner.set(getWindow().getDecorView(), this);
        ViewTreeSavedStateRegistryOwner.set(getWindow().getDecorView(), this);
        ViewTreeOnBackPressedDispatcherOwner.set(getWindow().getDecorView(), this);
        ViewTreeFullyDrawnReporterOwner.set(getWindow().getDecorView(), this);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    @Deprecated
    public void onActivityResult(int r2, int r3, Intent r4) {
        if (this.mActivityResultRegistry.dispatchResult(r2, r3, r4) == true) goto L6;
        super.onActivityResult(r2, r3, r4);
        return;
    }

    @Override // android.app.Activity
    @Deprecated
    public void onBackPressed() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        Iterator<Consumer<Configuration>> r1 = this.mOnConfigurationChangedListeners.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().accept(r2);
        goto L4
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle r2) {
        this.mSavedStateRegistryController.performRestore(r2);
        this.mContextAwareHelper.dispatchOnContextAvailable(this);
        super.onCreate(r2);
        ReportFragment.injectIfNeededIn(this);
        int r22 = this.mContentLayoutId;
        if (r22 == 0) goto L6;
        setContentView(r22);
        return;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int r1, Menu r2) {
        if (r1 != 0) goto L6;
        super.onCreatePanelMenu(r1, r2);
        this.mMenuHostHelper.onCreateMenu(r2, getMenuInflater());
        return true;
    L6:
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int r2, MenuItem r3) {
        if (super.onMenuItemSelected(r2, r3) == false) goto L6;
        return true;
    L6:
        if (r2 == 0) goto L8;
        return false;
    L8:
        return this.mMenuHostHelper.onMenuItemSelected(r3);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean r3) {
        if (this.mDispatchingOnMultiWindowModeChanged == true) goto L9;
        Iterator<Consumer<MultiWindowModeChangedInfo>> r2 = this.mOnMultiWindowModeChangedListeners.iterator();
    L7:
        if (r2.hasNext() == false) goto L11;
        r2.next().accept(new MultiWindowModeChangedInfo(r3));
        goto L7
    L11:
        return;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean r3, Configuration r4) {
        this.mDispatchingOnMultiWindowModeChanged = true;
        super.onMultiWindowModeChanged(r3, r4);     // Catch: Throwable -> L9
        this.mDispatchingOnMultiWindowModeChanged = false;
        Iterator<Consumer<MultiWindowModeChangedInfo>> r2 = this.mOnMultiWindowModeChangedListeners.iterator();
    L6:
        if (r2.hasNext() == false) goto L8;
        r2.next().accept(new MultiWindowModeChangedInfo(r3, r4));
        goto L6
    L8:
        return;
    L9:
        th = move-exception;
        this.mDispatchingOnMultiWindowModeChanged = false;
        throw th;
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness", "MissingNullability"}) Intent r2) {
        super.onNewIntent(r2);
        Iterator<Consumer<Intent>> r1 = this.mOnNewIntentListeners.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().accept(r2);
        goto L4
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int r2, Menu r3) {
        this.mMenuHostHelper.onMenuClosed(r3);
        super.onPanelClosed(r2, r3);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean r3) {
        if (this.mDispatchingOnPictureInPictureModeChanged == true) goto L9;
        Iterator<Consumer<PictureInPictureModeChangedInfo>> r2 = this.mOnPictureInPictureModeChangedListeners.iterator();
    L7:
        if (r2.hasNext() == false) goto L11;
        r2.next().accept(new PictureInPictureModeChangedInfo(r3));
        goto L7
    L11:
        return;
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean r3, Configuration r4) {
        this.mDispatchingOnPictureInPictureModeChanged = true;
        super.onPictureInPictureModeChanged(r3, r4);     // Catch: Throwable -> L9
        this.mDispatchingOnPictureInPictureModeChanged = false;
        Iterator<Consumer<PictureInPictureModeChangedInfo>> r2 = this.mOnPictureInPictureModeChangedListeners.iterator();
    L6:
        if (r2.hasNext() == false) goto L8;
        r2.next().accept(new PictureInPictureModeChangedInfo(r3, r4));
        goto L6
    L8:
        return;
    L9:
        th = move-exception;
        this.mDispatchingOnPictureInPictureModeChanged = false;
        throw th;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int r1, View r2, Menu r3) {
        if (r1 != 0) goto L6;
        super.onPreparePanel(r1, r2, r3);
        this.mMenuHostHelper.onPrepareMenu(r3);
        return true;
    L6:
        return true;
    }

    @Override // android.app.Activity
    @Deprecated
    public void onRequestPermissionsResult(int r4, String[] r5, int[] r6) {
        if (this.mActivityResultRegistry.dispatchResult(r4, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", r5).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", r6)) == true) goto L6;
        super.onRequestPermissionsResult(r4, r5, r6);
        return;
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        Object r0 = onRetainCustomNonConfigurationInstance();
        ViewModelStore r1 = this.mViewModelStore;
        if (r1 != null) goto L7;
        NonConfigurationInstances r2 = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (r2 == null) goto L7;
        r1 = r2.viewModelStore;
    L7:
        if (r1 != null) goto L11;
        if (r0 != null) goto L11;
        return null;
    L11:
        NonConfigurationInstances r22 = new NonConfigurationInstances();
        r22.custom = r0;
        r22.viewModelStore = r1;
        return r22;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle r3) {
        Lifecycle r0 = getLifecycle();
        if ((r0 instanceof LifecycleRegistry) == false) goto L5;
        ((LifecycleRegistry) r0).setCurrentState(Lifecycle.State.CREATED);
    L5:
        super.onSaveInstanceState(r3);
        this.mSavedStateRegistryController.performSave(r3);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int r3) {
        super.onTrimMemory(r3);
        Iterator<Consumer<Integer>> r2 = this.mOnTrimMemoryListeners.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r2.next().accept(Integer.valueOf(r3));
        goto L4
    }

    public Context peekAvailableContext() {
        return this.mContextAwareHelper.peekAvailableContext();
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> r2, ActivityResultCallback<O> r3) {
        return registerForActivityResult(r2, this.mActivityResultRegistry, r3);
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> r3, ActivityResultRegistry r4, ActivityResultCallback<O> r5) {
        return r4.register("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, r3, r5);
    }

    @Override // androidx.core.view.MenuHost
    public void removeMenuProvider(MenuProvider r1) {
        this.mMenuHostHelper.removeMenuProvider(r1);
    }

    @Override // androidx.core.content.OnConfigurationChangedProvider
    public final void removeOnConfigurationChangedListener(Consumer<Configuration> r1) {
        this.mOnConfigurationChangedListeners.remove(r1);
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener r1) {
        this.mContextAwareHelper.removeOnContextAvailableListener(r1);
    }

    @Override // androidx.core.app.OnMultiWindowModeChangedProvider
    public final void removeOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> r1) {
        this.mOnMultiWindowModeChangedListeners.remove(r1);
    }

    public final void removeOnNewIntentListener(Consumer<Intent> r1) {
        this.mOnNewIntentListeners.remove(r1);
    }

    @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
    public final void removeOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> r1) {
        this.mOnPictureInPictureModeChangedListeners.remove(r1);
    }

    @Override // androidx.core.content.OnTrimMemoryProvider
    public final void removeOnTrimMemoryListener(Consumer<Integer> r1) {
        this.mOnTrimMemoryListeners.remove(r1);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
    L8:
        th = move-exception;
        Trace.endSection();
        throw th;
    L3:
        if (Trace.isEnabled() == false) goto L5;
        Trace.beginSection("reportFullyDrawn() for ComponentActivity");     // Catch: Throwable -> L8
    L5:
        super.reportFullyDrawn();     // Catch: Throwable -> L8
        this.mFullyDrawnReporter.fullyDrawnReported();     // Catch: Throwable -> L8
        Trace.endSection();
    }

    @Override // android.app.Activity
    public void setContentView(int r3) {
        initializeViewTreeOwners();
        this.mReportFullyDrawnExecutor.viewCreated(getWindow().getDecorView());
        super.setContentView(r3);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View r3) {
        initializeViewTreeOwners();
        this.mReportFullyDrawnExecutor.viewCreated(getWindow().getDecorView());
        super.setContentView(r3);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View r3, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams r4) {
        initializeViewTreeOwners();
        this.mReportFullyDrawnExecutor.viewCreated(getWindow().getDecorView());
        super.setContentView(r3, r4);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent r1, int r2) {
        super.startActivityForResult(r1, r2);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent r1, int r2, Bundle r3) {
        super.startActivityForResult(r1, r2, r3);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender r1, int r2, Intent r3, int r4, int r5, int r6) {
        super.startIntentSenderForResult(r1, r2, r3, r4, r5, r6);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender r1, int r2, Intent r3, int r4, int r5, int r6, Bundle r7) {
        super.startIntentSenderForResult(r1, r2, r3, r4, r5, r6, r7);
    }
}
