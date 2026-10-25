package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.collection.SparseArrayCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
class LoaderManagerImpl extends LoaderManager {
    static boolean DEBUG = false;
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {
        private final Bundle mArgs;
        private final int mId;
        private LifecycleOwner mLifecycleOwner;
        private final Loader<D> mLoader;
        private LoaderObserver<D> mObserver;
        private Loader<D> mPriorLoader;

        public LoaderInfo(int r1, Bundle r2, Loader<D> r3, Loader<D> r4) {
            this.mId = r1;
            this.mArgs = r2;
            this.mLoader = r3;
            this.mPriorLoader = r4;
            r3.registerListener(r1, this);
        }

        public Loader<D> destroy(boolean r3) {
            if (LoaderManagerImpl.DEBUG == false) goto L5;
            Log.v("LoaderManager", "  Destroying: " + this);
        L5:
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver<D> r0 = this.mObserver;
            if (r0 == null) goto L10;
            removeObserver(r0);
            if (r3 == false) goto L10;
            r0.reset();
        L10:
            this.mLoader.unregisterListener(this);
            if (r0 != null) goto L13;
        L14:
            if (r3 == false) goto L18;
        L15:
            this.mLoader.reset();
            return this.mPriorLoader;
        L18:
            return this.mLoader;
        L13:
            if (r0.hasDeliveredData() == false) goto L15;
            goto L14
        }

        public void dump(String r4, FileDescriptor r5, PrintWriter r6, String[] r7) {
            r6.print(r4);
            r6.print("mId=");
            r6.print(this.mId);
            r6.print(" mArgs=");
            r6.println(this.mArgs);
            r6.print(r4);
            r6.print("mLoader=");
            r6.println(this.mLoader);
            this.mLoader.dump(r4 + "  ", r5, r6, r7);
            if (this.mObserver == null) goto L5;
            r6.print(r4);
            r6.print("mCallbacks=");
            r6.println(this.mObserver);
            this.mObserver.dump(r4 + "  ", r6);
        L5:
            r6.print(r4);
            r6.print("mData=");
            r6.println(getLoader().dataToString(getValue()));
            r6.print(r4);
            r6.print("mStarted=");
            r6.println(hasActiveObservers());
        }

        public Loader<D> getLoader() {
            return this.mLoader;
        }

        public void markForRedelivery() {
            LifecycleOwner r0 = this.mLifecycleOwner;
            LoaderObserver<D> r1 = this.mObserver;
            if (r0 == null) goto L7;
            if (r1 == null) goto L8;
            super.removeObserver(r1);
            observe(r0, r1);
            return;
        L8:
            return;
        }

        @Override // androidx.lifecycle.LiveData
        public void onActive() {
            if (LoaderManagerImpl.DEBUG == false) goto L5;
            Log.v("LoaderManager", "  Starting: " + this);
        L5:
            this.mLoader.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        public void onInactive() {
            if (LoaderManagerImpl.DEBUG == false) goto L5;
            Log.v("LoaderManager", "  Stopping: " + this);
        L5:
            this.mLoader.stopLoading();
        }

        @Override // androidx.loader.content.Loader.OnLoadCompleteListener
        public void onLoadComplete(Loader<D> r3, D r4) {
            if (LoaderManagerImpl.DEBUG == false) goto L6;
            Log.v("LoaderManager", "onLoadComplete: " + this);
        L6:
            if (Looper.myLooper() != Looper.getMainLooper()) goto L10;
            setValue(r4);
            return;
        L10:
            if (LoaderManagerImpl.DEBUG == false) goto L12;
            Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
        L12:
            postValue(r4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void removeObserver(Observer<? super D> r1) {
            super.removeObserver(r1);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        public Loader<D> setCallback(LifecycleOwner r3, LoaderManager.LoaderCallbacks<D> r4) {
            LoaderObserver<D> r0 = new LoaderObserver(this.mLoader, r4);
            observe(r3, r0);
            LoaderObserver<D> r42 = this.mObserver;
            if (r42 == null) goto L5;
            removeObserver(r42);
        L5:
            this.mLifecycleOwner = r3;
            this.mObserver = r0;
            return this.mLoader;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(D r1) {
            super.setValue(r1);
            Loader<D> r12 = this.mPriorLoader;
            if (r12 == null) goto L6;
            r12.reset();
            this.mPriorLoader = null;
            return;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder(64);
            r0.append("LoaderInfo{");
            r0.append(Integer.toHexString(System.identityHashCode(this)));
            r0.append(" #");
            r0.append(this.mId);
            r0.append(" : ");
            Class<?> r2 = this.mLoader.getClass();
            r0.append(r2.getSimpleName());
            r0.append("{");
            r0.append(Integer.toHexString(System.identityHashCode(r2)));
            r0.append("}}");
            return r0.toString();
        }
    }

    public static class LoaderObserver<D> implements Observer<D> {
        private final LoaderManager.LoaderCallbacks<D> mCallback;
        private boolean mDeliveredData;
        private final Loader<D> mLoader;

        public LoaderObserver(Loader<D> r2, LoaderManager.LoaderCallbacks<D> r3) {
            this.mDeliveredData = false;
            this.mLoader = r2;
            this.mCallback = r3;
        }

        public void dump(String r1, PrintWriter r2) {
            r2.print(r1);
            r2.print("mDeliveredData=");
            r2.println(this.mDeliveredData);
        }

        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(D r3) {
            if (LoaderManagerImpl.DEBUG == false) goto L5;
            Log.v("LoaderManager", "  onLoadFinished in " + this.mLoader + ": " + this.mLoader.dataToString(r3));
        L5:
            this.mDeliveredData = true;
            this.mCallback.onLoadFinished(this.mLoader, r3);
        }

        public void reset() {
            if (this.mDeliveredData == true) goto L5;
            return;
        L5:
            if (LoaderManagerImpl.DEBUG == false) goto L7;
            Log.v("LoaderManager", "  Resetting: " + this.mLoader);
        L7:
            this.mCallback.onLoaderReset(this.mLoader);
        }

        public String toString() {
            return this.mCallback.toString();
        }
    }

    public static class LoaderViewModel extends ViewModel {
        private static final ViewModelProvider.Factory FACTORY = null;
        private boolean mCreatingLoader;
        private SparseArrayCompat<LoaderInfo> mLoaders;

        /* renamed from: androidx.loader.app.LoaderManagerImpl$LoaderViewModel$1, reason: invalid class name */
        public static class AnonymousClass1 implements ViewModelProvider.Factory {
            public AnonymousClass1() {
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public <T extends ViewModel> T create(Class<T> r1) {
                return new LoaderViewModel();
            }
        }

        static {
            FACTORY = new AnonymousClass1();
        }

        public LoaderViewModel() {
            this.mLoaders = new SparseArrayCompat();
            this.mCreatingLoader = false;
        }

        public static LoaderViewModel getInstance(ViewModelStore r2) {
            return (LoaderViewModel) new ViewModelProvider(r2, FACTORY).get(LoaderViewModel.class);
        }

        public void dump(String r5, FileDescriptor r6, PrintWriter r7, String[] r8) {
            if (this.mLoaders.size() <= 0) goto L8;
            r7.print(r5);
            r7.println("Loaders:");
            String r0 = r5 + "    ";
            int r1 = 0;
        L6:
            if (r1 >= this.mLoaders.size()) goto L10;
            LoaderInfo r2 = this.mLoaders.valueAt(r1);
            r7.print(r5);
            r7.print("  #");
            r7.print(this.mLoaders.keyAt(r1));
            r7.print(": ");
            r7.println(r2.toString());
            r2.dump(r0, r6, r7, r8);
            r1 = r1 + 1;
            goto L6
        L10:
            return;
        }

        public void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        public <D> LoaderInfo<D> getLoader(int r1) {
            return this.mLoaders.get(r1);
        }

        public boolean isCreatingLoader() {
            return this.mCreatingLoader;
        }

        public void markForRedelivery() {
            int r0 = this.mLoaders.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.mLoaders.valueAt(r1).markForRedelivery();
            r1 = r1 + 1;
            goto L3
        }

        @Override // androidx.lifecycle.ViewModel
        public void onCleared() {
            super.onCleared();
            int r0 = this.mLoaders.size();
            int r1 = 0;
        L3:
            SparseArrayCompat<LoaderInfo> r2 = this.mLoaders;
            if (r1 >= r0) goto L6;
            r2.valueAt(r1).destroy(true);
            r1 = r1 + 1;
            goto L3
        L6:
            r2.clear();
        }

        public void putLoader(int r1, LoaderInfo r2) {
            this.mLoaders.put(r1, r2);
        }

        public void startCreatingLoader() {
            this.mCreatingLoader = true;
        }
    }

    static {
    }

    public LoaderManagerImpl(LifecycleOwner r1, ViewModelStore r2) {
        this.mLifecycleOwner = r1;
        this.mLoaderViewModel = LoaderViewModel.getInstance(r2);
    }

    private <D> Loader<D> createAndInstallLoader(int r5, Bundle r6, LoaderManager.LoaderCallbacks<D> r7, Loader<D> r8) {
        this.mLoaderViewModel.startCreatingLoader();     // Catch: Throwable -> L12
        Loader<D> r2 = r7.onCreateLoader(r5, r6);     // Catch: Throwable -> L12
        if (r2 == null) goto L21;
        if (r2.getClass().isMemberClass() == true) goto L8;
    L14:
        LoaderInfo r1 = new LoaderInfo(r5, r6, r2, r8);     // Catch: Throwable -> L12
        if (DEBUG == false) goto L17;
        Log.v("LoaderManager", "  Created new loader " + r1);     // Catch: Throwable -> L12
    L17:
        this.mLoaderViewModel.putLoader(r5, r1);     // Catch: Throwable -> L12
        this.mLoaderViewModel.finishCreatingLoader();
        return r1.setCallback(this.mLifecycleOwner, r7);
    L8:
        if (Modifier.isStatic(r2.getClass().getModifiers()) == true) goto L14;
        throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + r2);     // Catch: Throwable -> L12
    L21:
        throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        this.mLoaderViewModel.finishCreatingLoader();
        throw th;
    }

    @Override // androidx.loader.app.LoaderManager
    @Deprecated
    public void dump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4) {
        this.mLoaderViewModel.dump(r1, r2, r3, r4);
    }

    @Override // androidx.loader.app.LoaderManager
    public <D> Loader<D> initLoader(int r6, Bundle r7, LoaderManager.LoaderCallbacks<D> r8) {
        if (this.mLoaderViewModel.isCreatingLoader() == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Called while creating a loader");
        return null;
    L5:
        if (Looper.getMainLooper() != Looper.myLooper()) goto L17;
        LoaderInfo<D> r0 = this.mLoaderViewModel.getLoader(r6);
        if (DEBUG == false) goto L9;
        Log.v("LoaderManager", "initLoader in " + this + ": args=" + r7);
    L9:
        if (r0 != null) goto L13;
        return createAndInstallLoader(r6, r7, r8, null);
    L13:
        if (DEBUG == false) goto L16;
        Log.v("LoaderManager", "  Re-using existing loader " + r0);
    L16:
        return r0.setCallback(this.mLifecycleOwner, r8);
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("initLoader must be called on the main thread");
        return null;
    }

    @Override // androidx.loader.app.LoaderManager
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(128);
        r0.append("LoaderManager{");
        r0.append(Integer.toHexString(System.identityHashCode(this)));
        r0.append(" in ");
        Class<?> r2 = this.mLifecycleOwner.getClass();
        r0.append(r2.getSimpleName());
        r0.append("{");
        r0.append(Integer.toHexString(System.identityHashCode(r2)));
        r0.append("}}");
        return r0.toString();
    }
}
