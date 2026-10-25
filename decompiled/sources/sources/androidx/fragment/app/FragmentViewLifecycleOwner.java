package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;

/* loaded from: classes3.dex */
class FragmentViewLifecycleOwner implements HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ViewModelStoreOwner {
    private ViewModelProvider.Factory mDefaultFactory;
    private final Fragment mFragment;
    private LifecycleRegistry mLifecycleRegistry;
    private SavedStateRegistryController mSavedStateRegistryController;
    private final ViewModelStore mViewModelStore;

    public FragmentViewLifecycleOwner(Fragment r2, ViewModelStore r3) {
        this.mLifecycleRegistry = null;
        this.mSavedStateRegistryController = null;
        this.mFragment = r2;
        this.mViewModelStore = r3;
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras getDefaultViewModelCreationExtras() {
        Context r0 = this.mFragment.requireContext().getApplicationContext();
    L4:
        if ((r0 instanceof ContextWrapper) == false) goto L9;
        if ((r0 instanceof Application) == true) goto L7;
        r0 = ((ContextWrapper) r0).getBaseContext();
        goto L4
    L7:
        Application r02 = (Application) r0;
    L10:
        MutableCreationExtras r1 = new MutableCreationExtras();
        if (r02 == null) goto L13;
        r1.set(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY, r02);
    L13:
        r1.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this.mFragment);
        r1.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        if (this.mFragment.getArguments() == null) goto L16;
        r1.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, this.mFragment.getArguments());
    L16:
        return r1;
    L9:
        r02 = null;
        goto L10
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        ViewModelProvider.Factory r0 = this.mFragment.getDefaultViewModelProviderFactory();
        if (r0.equals(this.mFragment.mDefaultFactory) == true) goto L7;
        this.mDefaultFactory = r0;
        return r0;
    L7:
        if (this.mDefaultFactory != null) goto L18;
        Context r02 = this.mFragment.requireContext().getApplicationContext();
    L10:
        if ((r02 instanceof ContextWrapper) == false) goto L15;
        if ((r02 instanceof Application) == true) goto L13;
        r02 = ((ContextWrapper) r02).getBaseContext();
        goto L10
    L13:
        Application r03 = (Application) r02;
    L16:
        Fragment r2 = this.mFragment;
        this.mDefaultFactory = new SavedStateViewModelFactory(r03, r2, r2.getArguments());
        goto L18
    L15:
        r03 = null;
    L18:
        return this.mDefaultFactory;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        initialize();
        return this.mLifecycleRegistry;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        initialize();
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        initialize();
        return this.mViewModelStore;
    }

    public void handleLifecycleEvent(Lifecycle.Event r1) {
        this.mLifecycleRegistry.handleLifecycleEvent(r1);
    }

    public void initialize() {
        if (this.mLifecycleRegistry != null) goto L6;
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        SavedStateRegistryController r0 = SavedStateRegistryController.create(this);
        this.mSavedStateRegistryController = r0;
        r0.performAttach();
        return;
    }

    public boolean isInitialized() {
        if (this.mLifecycleRegistry == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void performRestore(Bundle r1) {
        this.mSavedStateRegistryController.performRestore(r1);
    }

    public void performSave(Bundle r1) {
        this.mSavedStateRegistryController.performSave(r1);
    }

    public void setCurrentState(Lifecycle.State r1) {
        this.mLifecycleRegistry.setCurrentState(r1);
    }
}
