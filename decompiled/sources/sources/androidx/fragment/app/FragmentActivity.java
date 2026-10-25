package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.core.app.ActivityCompat;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.app.SharedElementCallback;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class FragmentActivity extends ComponentActivity implements ActivityCompat.RequestPermissionsRequestCodeValidator {
    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    final LifecycleRegistry mFragmentLifecycleRegistry;
    final FragmentController mFragments;
    boolean mResumed;
    boolean mStopped;

    public class HostCallbacks extends FragmentHostCallback<FragmentActivity> implements OnConfigurationChangedProvider, OnTrimMemoryProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, ViewModelStoreOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, SavedStateRegistryOwner, FragmentOnAttachListener, MenuHost {
        final /* synthetic */ FragmentActivity this$0;

        public HostCallbacks(FragmentActivity r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // androidx.core.view.MenuHost
        public void addMenuProvider(MenuProvider r1) {
            this.this$0.addMenuProvider(r1);
        }

        @Override // androidx.core.content.OnConfigurationChangedProvider
        public void addOnConfigurationChangedListener(Consumer<Configuration> r1) {
            this.this$0.addOnConfigurationChangedListener(r1);
        }

        @Override // androidx.core.app.OnMultiWindowModeChangedProvider
        public void addOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> r1) {
            this.this$0.addOnMultiWindowModeChangedListener(r1);
        }

        @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
        public void addOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> r1) {
            this.this$0.addOnPictureInPictureModeChangedListener(r1);
        }

        @Override // androidx.core.content.OnTrimMemoryProvider
        public void addOnTrimMemoryListener(Consumer<Integer> r1) {
            this.this$0.addOnTrimMemoryListener(r1);
        }

        @Override // androidx.activity.result.ActivityResultRegistryOwner
        public ActivityResultRegistry getActivityResultRegistry() {
            return this.this$0.getActivityResultRegistry();
        }

        @Override // androidx.lifecycle.LifecycleOwner
        public Lifecycle getLifecycle() {
            return this.this$0.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.OnBackPressedDispatcherOwner
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return this.this$0.getOnBackPressedDispatcher();
        }

        @Override // androidx.savedstate.SavedStateRegistryOwner
        public SavedStateRegistry getSavedStateRegistry() {
            return this.this$0.getSavedStateRegistry();
        }

        @Override // androidx.lifecycle.ViewModelStoreOwner
        public ViewModelStore getViewModelStore() {
            return this.this$0.getViewModelStore();
        }

        public void invalidateMenu() {
            this.this$0.invalidateMenu();
        }

        @Override // androidx.fragment.app.FragmentOnAttachListener
        public void onAttachFragment(FragmentManager r1, Fragment r2) {
            this.this$0.onAttachFragment(r2);
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public void onDump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4) {
            this.this$0.dump(r1, r2, r3, r4);
        }

        @Override // androidx.fragment.app.FragmentContainer
        public View onFindViewById(int r1) {
            return this.this$0.findViewById(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.fragment.app.FragmentHostCallback
        public FragmentActivity onGetHost() {
            return this.this$0;
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public /* bridge */ /* synthetic */ FragmentActivity onGetHost() {
            return onGetHost();
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public LayoutInflater onGetLayoutInflater() {
            return this.this$0.getLayoutInflater().cloneInContext(this.this$0);
        }

        @Override // androidx.fragment.app.FragmentContainer
        public boolean onHasView() {
            Window r0 = this.this$0.getWindow();
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.peekDecorView() == null) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public boolean onShouldShowRequestPermissionRationale(String r1) {
            return ActivityCompat.shouldShowRequestPermissionRationale(this.this$0, r1);
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public void onSupportInvalidateOptionsMenu() {
            invalidateMenu();
        }

        @Override // androidx.core.view.MenuHost
        public void removeMenuProvider(MenuProvider r1) {
            this.this$0.removeMenuProvider(r1);
        }

        @Override // androidx.core.content.OnConfigurationChangedProvider
        public void removeOnConfigurationChangedListener(Consumer<Configuration> r1) {
            this.this$0.removeOnConfigurationChangedListener(r1);
        }

        @Override // androidx.core.app.OnMultiWindowModeChangedProvider
        public void removeOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> r1) {
            this.this$0.removeOnMultiWindowModeChangedListener(r1);
        }

        @Override // androidx.core.app.OnPictureInPictureModeChangedProvider
        public void removeOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> r1) {
            this.this$0.removeOnPictureInPictureModeChangedListener(r1);
        }

        @Override // androidx.core.content.OnTrimMemoryProvider
        public void removeOnTrimMemoryListener(Consumer<Integer> r1) {
            this.this$0.removeOnTrimMemoryListener(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$4ROHaS4O6f2WoPhKvhOMRg_7Bzo(FragmentActivity r0, Context r1) {
        r0.mFragments.attachHost(null);
    }

    public static /* synthetic */ void $r8$lambda$OFEQQzam8OSGV6dVWUiP57Rv4yo(FragmentActivity r0, Configuration r1) {
        r0.mFragments.noteStateNotSaved();
    }

    public static /* synthetic */ Bundle $r8$lambda$PiZLedL0JH1wIOGQM80pCH0fhkU(FragmentActivity r1) {
        r1.markFragmentsCreated();
        r1.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        return new Bundle();
    }

    public static /* synthetic */ void $r8$lambda$tP2n_F9e4k2NvYif5gW5fjCaNlM(FragmentActivity r0, Intent r1) {
        r0.mFragments.noteStateNotSaved();
    }

    public FragmentActivity() {
        this.mFragments = FragmentController.createController(new HostCallbacks(this));
        this.mFragmentLifecycleRegistry = new LifecycleRegistry(this);
        this.mStopped = true;
        init();
    }

    private void init() {
        getSavedStateRegistry().registerSavedStateProvider(LIFECYCLE_TAG, new FragmentActivity$$ExternalSyntheticLambda0(this));
        addOnConfigurationChangedListener(new FragmentActivity$$ExternalSyntheticLambda1(this));
        addOnNewIntentListener(new FragmentActivity$$ExternalSyntheticLambda2(this));
        addOnContextAvailableListener(new FragmentActivity$$ExternalSyntheticLambda3(this));
    }

    private static boolean markState(FragmentManager r5, Lifecycle.State r6) {
        Iterator<Fragment> r52 = r5.getFragments().iterator();
        boolean r0 = false;
    L4:
        if (r52.hasNext() == false) goto L19;
        Fragment r1 = r52.next();
        if (r1 == null) goto L4;
        if (r1.getHost() == null) goto L11;
        r0 = r0 | markState(r1.getChildFragmentManager(), r6);
    L11:
        FragmentViewLifecycleOwner r2 = r1.mViewLifecycleOwner;
        if (r2 == null) goto L17;
        if (r2.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L17;
        r1.mViewLifecycleOwner.setCurrentState(r6);
        r0 = true;
    L17:
        if (r1.mLifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L4;
        r1.mLifecycleRegistry.setCurrentState(r6);
        r0 = true;
        goto L4
    L19:
        return r0;
    }

    public final View dispatchFragmentsOnCreateView(View r1, String r2, Context r3, AttributeSet r4) {
        return this.mFragments.onCreateView(r1, r2, r3, r4);
    }

    @Override // android.app.Activity
    public void dump(String r3, FileDescriptor r4, PrintWriter r5, String[] r6) {
        super.dump(r3, r4, r5, r6);
        if (shouldDumpInternalState(r6) == true) goto L5;
        return;
    L5:
        r5.print(r3);
        r5.print("Local FragmentActivity ");
        r5.print(Integer.toHexString(System.identityHashCode(this)));
        r5.println(" State:");
        String r0 = r3 + "  ";
        r5.print(r0);
        r5.print("mCreated=");
        r5.print(this.mCreated);
        r5.print(" mResumed=");
        r5.print(this.mResumed);
        r5.print(" mStopped=");
        r5.print(this.mStopped);
        if (getApplication() == null) goto L8;
        LoaderManager.getInstance(this).dump(r0, r4, r5, r6);
    L8:
        this.mFragments.getSupportFragmentManager().dump(r3, r4, r5, r6);
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.getSupportFragmentManager();
    }

    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    public void markFragmentsCreated() {
    L3:
        if (markState(getSupportFragmentManager(), Lifecycle.State.CREATED) == true) goto L3;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int r2, int r3, Intent r4) {
        this.mFragments.noteStateNotSaved();
        super.onActivityResult(r2, r3, r4);
    }

    @Deprecated
    public void onAttachFragment(Fragment r1) {
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle r2) {
        super.onCreate(r2);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        this.mFragments.dispatchCreate();
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View r2, String r3, Context r4, AttributeSet r5) {
        View r0 = dispatchFragmentsOnCreateView(r2, r3, r4, r5);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return super.onCreateView(r2, r3, r4, r5);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String r2, Context r3, AttributeSet r4) {
        View r0 = dispatchFragmentsOnCreateView(null, r2, r3, r4);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return super.onCreateView(r2, r3, r4);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.dispatchDestroy();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int r2, MenuItem r3) {
        if (super.onMenuItemSelected(r2, r3) == false) goto L7;
        return true;
    L7:
        if (r2 == 6) goto L9;
        return false;
    L9:
        return this.mFragments.dispatchContextItemSelected(r3);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.dispatchPause();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int r2, String[] r3, int[] r4) {
        this.mFragments.noteStateNotSaved();
        super.onRequestPermissionsResult(r2, r3, r4);
    }

    @Override // android.app.Activity
    public void onResume() {
        this.mFragments.noteStateNotSaved();
        super.onResume();
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        this.mFragments.dispatchResume();
    }

    @Override // android.app.Activity
    public void onStart() {
        this.mFragments.noteStateNotSaved();
        super.onStart();
        this.mStopped = false;
        if (this.mCreated == true) goto L5;
        this.mCreated = true;
        this.mFragments.dispatchActivityCreated();
    L5:
        this.mFragments.execPendingActions();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        this.mFragments.dispatchStart();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mFragments.dispatchStop();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    public void setEnterSharedElementCallback(SharedElementCallback r1) {
        ActivityCompat.setEnterSharedElementCallback(this, r1);
    }

    public void setExitSharedElementCallback(SharedElementCallback r1) {
        ActivityCompat.setExitSharedElementCallback(this, r1);
    }

    public void startActivityFromFragment(Fragment r2, @SuppressLint({"UnknownNullness"}) Intent r3, int r4) {
        startActivityFromFragment(r2, r3, r4, null);
    }

    public void startActivityFromFragment(Fragment r2, @SuppressLint({"UnknownNullness"}) Intent r3, int r4, Bundle r5) {
        if (r4 != (-1)) goto L6;
        ActivityCompat.startActivityForResult(this, r3, -1, r5);
        return;
    L6:
        r2.startActivityForResult(r3, r4, r5);
    }

    @Deprecated
    public void startIntentSenderFromFragment(Fragment r10, @SuppressLint({"UnknownNullness"}) IntentSender r11, int r12, Intent r13, int r14, int r15, int r16, Bundle r17) {
        if (r12 != (-1)) goto L6;
        ActivityCompat.startIntentSenderForResult(this, r11, r12, r13, r14, r15, r16, r17);
        return;
    L6:
        r10.startIntentSenderForResult(r11, r12, r13, r14, r15, r16, r17);
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }

    @Override // androidx.core.app.ActivityCompat.RequestPermissionsRequestCodeValidator
    @Deprecated
    public final void validateRequestPermissionsRequestCode(int r1) {
    }
}
