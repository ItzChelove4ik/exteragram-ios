package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.arch.core.util.Function;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import com.sun.jna.Native$$ExternalSyntheticBUOutline3;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.properties.NotNullVar$$ExternalSyntheticBUOutline0;
import okhttp3.OkHttpClient$Builder$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner {
    static final int ACTIVITY_CREATED = 4;
    static final int ATTACHED = 0;
    static final int AWAITING_ENTER_EFFECTS = 6;
    static final int AWAITING_EXIT_EFFECTS = 3;
    static final int CREATED = 1;
    static final int INITIALIZING = -1;
    static final int RESUMED = 7;
    static final int STARTED = 5;
    static final Object USE_DEFAULT_TRANSITION = null;
    static final int VIEW_CREATED = 2;
    boolean mAdded;
    AnimationInfo mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mBeingSaved;
    private boolean mCalled;
    FragmentManager mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    ViewModelProvider.Factory mDefaultFactory;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManager mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    FragmentHostCallback<?> mHost;
    boolean mInLayout;
    boolean mIsCreated;
    private Boolean mIsPrimaryNavigationFragment;
    LayoutInflater mLayoutInflater;
    LifecycleRegistry mLifecycleRegistry;
    Lifecycle.State mMaxState;
    boolean mMenuVisible;
    private final AtomicInteger mNextLocalRequestCode;
    private final ArrayList<OnPreAttachedListener> mOnPreAttachedListeners;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    Runnable mPostponedDurationRunnable;
    Handler mPostponedHandler;
    public String mPreviousWho;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    private final OnPreAttachedListener mSavedStateAttachListener;
    SavedStateRegistryController mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    Bundle mSavedViewRegistryState;
    SparseArray<Parcelable> mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetRequestCode;
    String mTargetWho;
    boolean mUserVisibleHint;
    View mView;
    FragmentViewLifecycleOwner mViewLifecycleOwner;
    MutableLiveData<LifecycleOwner> mViewLifecycleOwnerLiveData;
    String mWho;

    /* renamed from: androidx.fragment.app.Fragment$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ Fragment this$0;

        public AnonymousClass1(Fragment r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.startPostponedEnterTransition();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* renamed from: androidx.fragment.app.Fragment$10, reason: invalid class name */
    public class AnonymousClass10<I> extends ActivityResultLauncher<I> {
        final /* synthetic */ Fragment this$0;
        final /* synthetic */ ActivityResultContract val$contract;
        final /* synthetic */ AtomicReference val$ref;

        public AnonymousClass10(Fragment r1, AtomicReference r2, ActivityResultContract r3) {
            this.this$0 = r1;
            this.val$ref = r2;
            this.val$contract = r3;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I r1, ActivityOptionsCompat r2) {
            ActivityResultLauncher r0 = (ActivityResultLauncher) this.val$ref.get();
            if (r0 == null) goto L6;
            r0.launch(r1, r2);
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Operation cannot be started before fragment is in created state");
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            ActivityResultLauncher r1 = (ActivityResultLauncher) this.val$ref.getAndSet(null);
            if (r1 == null) goto L6;
            r1.unregister();
            return;
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$2, reason: invalid class name */
    public class AnonymousClass2 extends OnPreAttachedListener {
        final /* synthetic */ Fragment this$0;

        public AnonymousClass2(Fragment r1) {
            this.this$0 = r1;
            super(null);
        }

        @Override // androidx.fragment.app.Fragment.OnPreAttachedListener
        public void onPreAttached() {
            this.this$0.mSavedStateRegistryController.performAttach();
            SavedStateHandleSupport.enableSavedStateHandles(this.this$0);
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ Fragment this$0;

        public AnonymousClass3(Fragment r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.callStartTransitionListener(false);
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$4, reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ Fragment this$0;
        final /* synthetic */ SpecialEffectsController val$controller;

        public AnonymousClass4(Fragment r1, SpecialEffectsController r2) {
            this.this$0 = r1;
            this.val$controller = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$controller.executePendingOperations();
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$5, reason: invalid class name */
    public class AnonymousClass5 extends FragmentContainer {
        final /* synthetic */ Fragment this$0;

        public AnonymousClass5(Fragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.fragment.app.FragmentContainer
        public View onFindViewById(int r2) {
            View r0 = this.this$0.mView;
            if (r0 != null) goto L5;
            NotNullVar$$ExternalSyntheticBUOutline0.m("Fragment ", this.this$0, " does not have a view");
            return null;
        L5:
            return r0.findViewById(r2);
        }

        @Override // androidx.fragment.app.FragmentContainer
        public boolean onHasView() {
            if (this.this$0.mView == null) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$6, reason: invalid class name */
    public class AnonymousClass6 implements LifecycleEventObserver {
        final /* synthetic */ Fragment this$0;

        public AnonymousClass6(Fragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r1, Lifecycle.Event r2) {
            if (r2 != Lifecycle.Event.ON_STOP) goto L8;
            View r0 = this.this$0.mView;
            if (r0 == null) goto L9;
            Api19Impl.cancelPendingInputEvents(r0);
            return;
        L9:
            return;
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$7, reason: invalid class name */
    public class AnonymousClass7 implements Function<Void, ActivityResultRegistry> {
        final /* synthetic */ Fragment this$0;

        public AnonymousClass7(Fragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: apply, reason: avoid collision after fix types in other method */
        public ActivityResultRegistry apply2(Void r2) {
            Fragment r1 = this.this$0;
            Object r22 = r1.mHost;
            if ((r22 instanceof ActivityResultRegistryOwner) == false) goto L7;
            return ((ActivityResultRegistryOwner) r22).getActivityResultRegistry();
        L7:
            return r1.requireActivity().getActivityResultRegistry();
        }

        @Override // androidx.arch.core.util.Function
        public /* bridge */ /* synthetic */ ActivityResultRegistry apply(Void r1) {
            return apply2(r1);
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$8, reason: invalid class name */
    public class AnonymousClass8 implements Function<Void, ActivityResultRegistry> {
        final /* synthetic */ Fragment this$0;
        final /* synthetic */ ActivityResultRegistry val$registry;

        public AnonymousClass8(Fragment r1, ActivityResultRegistry r2) {
            this.this$0 = r1;
            this.val$registry = r2;
        }

        /* renamed from: apply, reason: avoid collision after fix types in other method */
        public ActivityResultRegistry apply2(Void r1) {
            return this.val$registry;
        }

        @Override // androidx.arch.core.util.Function
        public /* bridge */ /* synthetic */ ActivityResultRegistry apply(Void r1) {
            return apply2(r1);
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$9, reason: invalid class name */
    public class AnonymousClass9 extends OnPreAttachedListener {
        final /* synthetic */ Fragment this$0;
        final /* synthetic */ ActivityResultCallback val$callback;
        final /* synthetic */ ActivityResultContract val$contract;
        final /* synthetic */ AtomicReference val$ref;
        final /* synthetic */ Function val$registryProvider;

        public AnonymousClass9(Fragment r1, Function r2, AtomicReference r3, ActivityResultContract r4, ActivityResultCallback r5) {
            this.this$0 = r1;
            this.val$registryProvider = r2;
            this.val$ref = r3;
            this.val$contract = r4;
            this.val$callback = r5;
            super(null);
        }

        @Override // androidx.fragment.app.Fragment.OnPreAttachedListener
        public void onPreAttached() {
            String r0 = this.this$0.generateActivityResultKey();
            ActivityResultRegistry r1 = (ActivityResultRegistry) this.val$registryProvider.apply(null);
            this.val$ref.set(r1.register(r0, this.this$0, this.val$contract, this.val$callback));
        }
    }

    public static class AnimationInfo {
        Boolean mAllowEnterTransitionOverlap;
        Boolean mAllowReturnTransitionOverlap;
        View mAnimatingAway;
        int mEnterAnim;
        Object mEnterTransition;
        boolean mEnterTransitionPostponed;
        int mExitAnim;
        Object mExitTransition;
        View mFocusedView;
        boolean mIsPop;
        int mNextTransition;
        int mPopEnterAnim;
        int mPopExitAnim;
        float mPostOnViewCreatedAlpha;
        Object mReenterTransition;
        Object mReturnTransition;
        Object mSharedElementEnterTransition;
        Object mSharedElementReturnTransition;
        ArrayList<String> mSharedElementSourceNames;
        ArrayList<String> mSharedElementTargetNames;

        public AnimationInfo() {
            this.mEnterTransition = null;
            Object r1 = Fragment.USE_DEFAULT_TRANSITION;
            this.mReturnTransition = r1;
            this.mExitTransition = null;
            this.mReenterTransition = r1;
            this.mSharedElementEnterTransition = null;
            this.mSharedElementReturnTransition = r1;
            this.mPostOnViewCreatedAlpha = 1.0f;
            this.mFocusedView = null;
        }
    }

    public static class Api19Impl {
        public static void cancelPendingInputEvents(View r0) {
            r0.cancelPendingInputEvents();
        }
    }

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String r1, Exception r2) {
            super(r1, r2);
        }
    }

    public static abstract class OnPreAttachedListener {
        private OnPreAttachedListener() {
        }

        public /* synthetic */ OnPreAttachedListener(AnonymousClass1 r1) {
            this();
        }

        public abstract void onPreAttached();
    }

    @SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        final Bundle mState;

        /* renamed from: androidx.fragment.app.Fragment$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return new SavedState(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return createFromParcel(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1, ClassLoader r2) {
            Bundle r12 = r1.readBundle();
            this.mState = r12;
            if (r2 == null) goto L7;
            if (r12 == null) goto L8;
            r12.setClassLoader(r2);
            return;
        L8:
            return;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeBundle(this.mState);
        }
    }

    static {
        USE_DEFAULT_TRANSITION = new Object();
    }

    public Fragment() {
        this.mState = -1;
        this.mWho = UUID.randomUUID().toString();
        this.mTargetWho = null;
        this.mIsPrimaryNavigationFragment = null;
        this.mChildFragmentManager = new FragmentManagerImpl();
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mPostponedDurationRunnable = new AnonymousClass1(this);
        this.mMaxState = Lifecycle.State.RESUMED;
        this.mViewLifecycleOwnerLiveData = new MutableLiveData();
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mOnPreAttachedListeners = new ArrayList();
        this.mSavedStateAttachListener = new AnonymousClass2(this);
        initLifecycle();
    }

    public Fragment(int r1) {
        this();
        this.mContentLayoutId = r1;
    }

    private AnimationInfo ensureAnimationInfo() {
        if (this.mAnimationInfo != null) goto L6;
        this.mAnimationInfo = new AnimationInfo();
    L6:
        return this.mAnimationInfo;
    }

    private int getMinimumMaxLifecycleState() {
        Lifecycle.State r0 = this.mMaxState;
        if (r0 == Lifecycle.State.INITIALIZED) goto L10;
        if (this.mParentFragment == null) goto L10;
        return Math.min(r0.ordinal(), this.mParentFragment.getMinimumMaxLifecycleState());
    L10:
        return r0.ordinal();
    }

    private Fragment getTargetFragment(boolean r1) {
        if (r1 == false) goto L4;
        FragmentStrictMode.onGetTargetFragmentUsage(this);
    L4:
        Fragment r12 = this.mTarget;
        if (r12 == null) goto L7;
        return r12;
    L7:
        FragmentManager r13 = this.mFragmentManager;
        if (r13 == null) goto L13;
        String r0 = this.mTargetWho;
        if (r0 != null) goto L12;
        return null;
    L12:
        return r13.findActiveFragment(r0);
    L13:
        return null;
    }

    private void initLifecycle() {
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mSavedStateRegistryController = SavedStateRegistryController.create(this);
        this.mDefaultFactory = null;
        if (this.mOnPreAttachedListeners.contains(this.mSavedStateAttachListener) == true) goto L6;
        registerOnPreAttachListener(this.mSavedStateAttachListener);
        return;
    }

    @Deprecated
    public static Fragment instantiate(Context r1, String r2) {
        return instantiate(r1, r2, null);
    }

    @Deprecated
    public static Fragment instantiate(Context r3, String r4, Bundle r5) {
        Fragment r32 = FragmentFactory.loadFragmentClass(r3.getClassLoader(), r4).getConstructor(null).newInstance(null);     // Catch: InvocationTargetException -> L7 NoSuchMethodException -> L9 IllegalAccessException -> L11 java.lang.InstantiationException -> L13
        if (r5 == null) goto L15;
        r5.setClassLoader(r32.getClass().getClassLoader());     // Catch: InvocationTargetException -> L7 NoSuchMethodException -> L9 IllegalAccessException -> L11 java.lang.InstantiationException -> L13
        r32.setArguments(r5);     // Catch: InvocationTargetException -> L7 NoSuchMethodException -> L9 IllegalAccessException -> L11 java.lang.InstantiationException -> L13
        return r32;
    L15:
        return r32;
    L11:
        e = move-exception;
        Fragment$$ExternalSyntheticBUOutline1.m(r4, ": make sure class name exists, is public, and has an empty constructor that is public", e);
        return null;
    L13:
        e = move-exception;
        Fragment$$ExternalSyntheticBUOutline1.m(r4, ": make sure class name exists, is public, and has an empty constructor that is public", e);
        return null;
    L9:
        e = move-exception;
        Fragment$$ExternalSyntheticBUOutline1.m(r4, ": could not find Fragment constructor", e);
        return null;
    L7:
        e = move-exception;
        Fragment$$ExternalSyntheticBUOutline1.m(r4, ": calling Fragment constructor caused an exception", e);
        return null;
    }

    private <I, O> ActivityResultLauncher<I> prepareCallInternal(ActivityResultContract<I, O> r9, Function<Void, ActivityResultRegistry> r10, ActivityResultCallback<O> r11) {
        if (this.mState > 1) goto L6;
        AtomicReference r5 = new AtomicReference();
        registerOnPreAttachListener(new AnonymousClass9(this, r10, r5, r9, r11));
        return new AnonymousClass10(this, r5, r9);
    L6:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).");
        return null;
    }

    private void registerOnPreAttachListener(OnPreAttachedListener r2) {
        if (this.mState < 0) goto L6;
        r2.onPreAttached();
        return;
    L6:
        this.mOnPreAttachedListeners.add(r2);
    }

    private void restoreViewState() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L6;
        Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
    L6:
        if (this.mView == null) goto L8;
        restoreViewState(this.mSavedFragmentState);
    L8:
        this.mSavedFragmentState = null;
    }

    public void callStartTransitionListener(boolean r3) {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 == null) goto L6;
        r0.mEnterTransitionPostponed = false;
    L6:
        if (this.mView == null) goto L19;
        ViewGroup r02 = this.mContainer;
        if (r02 == null) goto L20;
        FragmentManager r1 = this.mFragmentManager;
        if (r1 == null) goto L21;
        SpecialEffectsController r03 = SpecialEffectsController.getOrCreateController(r02, r1);
        r03.markPostponedState();
        if (r3 == false) goto L14;
        this.mHost.getHandler().post(new AnonymousClass4(this, r03));
    L15:
        Handler r32 = this.mPostponedHandler;
        if (r32 == null) goto L22;
        r32.removeCallbacks(this.mPostponedDurationRunnable);
        this.mPostponedHandler = null;
        return;
    L22:
        return;
    L14:
        r03.executePendingOperations();
        goto L15
    L21:
        return;
    L20:
        return;
    }

    public FragmentContainer createFragmentContainer() {
        return new AnonymousClass5(this);
    }

    public void dump(String r3, FileDescriptor r4, PrintWriter r5, String[] r6) {
        r5.print(r3);
        r5.print("mFragmentId=#");
        r5.print(Integer.toHexString(this.mFragmentId));
        r5.print(" mContainerId=#");
        r5.print(Integer.toHexString(this.mContainerId));
        r5.print(" mTag=");
        r5.println(this.mTag);
        r5.print(r3);
        r5.print("mState=");
        r5.print(this.mState);
        r5.print(" mWho=");
        r5.print(this.mWho);
        r5.print(" mBackStackNesting=");
        r5.println(this.mBackStackNesting);
        r5.print(r3);
        r5.print("mAdded=");
        r5.print(this.mAdded);
        r5.print(" mRemoving=");
        r5.print(this.mRemoving);
        r5.print(" mFromLayout=");
        r5.print(this.mFromLayout);
        r5.print(" mInLayout=");
        r5.println(this.mInLayout);
        r5.print(r3);
        r5.print("mHidden=");
        r5.print(this.mHidden);
        r5.print(" mDetached=");
        r5.print(this.mDetached);
        r5.print(" mMenuVisible=");
        r5.print(this.mMenuVisible);
        r5.print(" mHasMenu=");
        r5.println(this.mHasMenu);
        r5.print(r3);
        r5.print("mRetainInstance=");
        r5.print(this.mRetainInstance);
        r5.print(" mUserVisibleHint=");
        r5.println(this.mUserVisibleHint);
        if (this.mFragmentManager == null) goto L6;
        r5.print(r3);
        r5.print("mFragmentManager=");
        r5.println(this.mFragmentManager);
    L6:
        if (this.mHost == null) goto L9;
        r5.print(r3);
        r5.print("mHost=");
        r5.println(this.mHost);
    L9:
        if (this.mParentFragment == null) goto L12;
        r5.print(r3);
        r5.print("mParentFragment=");
        r5.println(this.mParentFragment);
    L12:
        if (this.mArguments == null) goto L15;
        r5.print(r3);
        r5.print("mArguments=");
        r5.println(this.mArguments);
    L15:
        if (this.mSavedFragmentState == null) goto L18;
        r5.print(r3);
        r5.print("mSavedFragmentState=");
        r5.println(this.mSavedFragmentState);
    L18:
        if (this.mSavedViewState == null) goto L21;
        r5.print(r3);
        r5.print("mSavedViewState=");
        r5.println(this.mSavedViewState);
    L21:
        if (this.mSavedViewRegistryState == null) goto L23;
        r5.print(r3);
        r5.print("mSavedViewRegistryState=");
        r5.println(this.mSavedViewRegistryState);
    L23:
        Fragment r0 = getTargetFragment(false);
        if (r0 == null) goto L26;
        r5.print(r3);
        r5.print("mTarget=");
        r5.print(r0);
        r5.print(" mTargetRequestCode=");
        r5.println(this.mTargetRequestCode);
    L26:
        r5.print(r3);
        r5.print("mPopDirection=");
        r5.println(getPopDirection());
        if (getEnterAnim() == 0) goto L30;
        r5.print(r3);
        r5.print("getEnterAnim=");
        r5.println(getEnterAnim());
    L30:
        if (getExitAnim() == 0) goto L33;
        r5.print(r3);
        r5.print("getExitAnim=");
        r5.println(getExitAnim());
    L33:
        if (getPopEnterAnim() == 0) goto L36;
        r5.print(r3);
        r5.print("getPopEnterAnim=");
        r5.println(getPopEnterAnim());
    L36:
        if (getPopExitAnim() == 0) goto L39;
        r5.print(r3);
        r5.print("getPopExitAnim=");
        r5.println(getPopExitAnim());
    L39:
        if (this.mContainer == null) goto L42;
        r5.print(r3);
        r5.print("mContainer=");
        r5.println(this.mContainer);
    L42:
        if (this.mView == null) goto L45;
        r5.print(r3);
        r5.print("mView=");
        r5.println(this.mView);
    L45:
        if (getAnimatingAway() == null) goto L48;
        r5.print(r3);
        r5.print("mAnimatingAway=");
        r5.println(getAnimatingAway());
    L48:
        if (getContext() == null) goto L50;
        LoaderManager.getInstance(this).dump(r3, r4, r5, r6);
    L50:
        r5.print(r3);
        r5.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.dump(r3 + "  ", r4, r5, r6);
    }

    public final boolean equals(Object r1) {
        if (this == r1) goto L5;
        return false;
    L5:
        return true;
    }

    public Fragment findFragmentByWho(String r2) {
        if (r2.equals(this.mWho) == false) goto L6;
        return this;
    L6:
        return this.mChildFragmentManager.findFragmentByWho(r2);
    }

    public String generateActivityResultKey() {
        return "fragment_" + this.mWho + "_rq#" + this.mNextLocalRequestCode.getAndIncrement();
    }

    public final FragmentActivity getActivity() {
        FragmentHostCallback<?> r0 = this.mHost;
        if (r0 != null) goto L7;
        return null;
    L7:
        return (FragmentActivity) r0.getActivity();
    }

    public boolean getAllowEnterTransitionOverlap() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 == null) goto L9;
        Boolean r02 = r0.mAllowEnterTransitionOverlap;
        if (r02 != null) goto L8;
        return true;
    L8:
        return r02.booleanValue();
    L9:
        return true;
    }

    public boolean getAllowReturnTransitionOverlap() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 == null) goto L9;
        Boolean r02 = r0.mAllowReturnTransitionOverlap;
        if (r02 != null) goto L8;
        return true;
    L8:
        return r02.booleanValue();
    L9:
        return true;
    }

    public View getAnimatingAway() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.mAnimatingAway;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final FragmentManager getChildFragmentManager() {
        if (this.mHost != null) goto L5;
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " has not been attached yet.");
        return null;
    L5:
        return this.mChildFragmentManager;
    }

    public Context getContext() {
        FragmentHostCallback<?> r0 = this.mHost;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getContext();
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras getDefaultViewModelCreationExtras() {
        Context r0 = requireContext().getApplicationContext();
    L4:
        if ((r0 instanceof ContextWrapper) == false) goto L9;
        if ((r0 instanceof Application) == true) goto L7;
        r0 = ((ContextWrapper) r0).getBaseContext();
        goto L4
    L7:
        Application r02 = (Application) r0;
    L10:
        if (r02 == null) goto L12;
    L14:
        MutableCreationExtras r1 = new MutableCreationExtras();
        if (r02 == null) goto L17;
        r1.set(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY, r02);
    L17:
        r1.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        r1.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        if (getArguments() == null) goto L20;
        r1.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, getArguments());
    L20:
        return r1;
    L12:
        if (FragmentManager.isLoggingEnabled(3) == false) goto L14;
        Log.d("FragmentManager", "Could not find Application instance from Context " + requireContext().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
        goto L14
    L9:
        r02 = null;
        goto L10
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        Application r1 = null;
        if (this.mFragmentManager != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Can't access ViewModels from detached fragment");
        return null;
    L5:
        if (this.mDefaultFactory != null) goto L19;
        Context r0 = requireContext().getApplicationContext();
    L8:
        if ((r0 instanceof ContextWrapper) == false) goto L13;
        if ((r0 instanceof Application) == true) goto L11;
        r0 = ((ContextWrapper) r0).getBaseContext();
        goto L8
    L11:
        r1 = (Application) r0;
    L13:
        if (r1 == null) goto L15;
    L17:
        this.mDefaultFactory = new SavedStateViewModelFactory(r1, this, getArguments());
        goto L19
    L15:
        if (FragmentManager.isLoggingEnabled(3) == false) goto L17;
        Log.d("FragmentManager", "Could not find Application instance from Context " + requireContext().getApplicationContext() + ", you will need CreationExtras to use AndroidViewModel with the default ViewModelProvider.Factory");
    L19:
        return this.mDefaultFactory;
    }

    public int getEnterAnim() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.mEnterAnim;
    }

    public Object getEnterTransition() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.mEnterTransition;
    }

    public SharedElementCallback getEnterTransitionCallback() {
        AnimationInfo r1 = this.mAnimationInfo;
        if (r1 != null) goto L5;
        return null;
    L5:
        r1.getClass();
        return null;
    }

    public int getExitAnim() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.mExitAnim;
    }

    public Object getExitTransition() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.mExitTransition;
    }

    public SharedElementCallback getExitTransitionCallback() {
        AnimationInfo r1 = this.mAnimationInfo;
        if (r1 != null) goto L5;
        return null;
    L5:
        r1.getClass();
        return null;
    }

    public View getFocusedView() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.mFocusedView;
    }

    @Deprecated
    public final FragmentManager getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        FragmentHostCallback<?> r0 = this.mHost;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.onGetHost();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater r0 = this.mLayoutInflater;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return performGetLayoutInflater(null);
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle r1) {
        FragmentHostCallback<?> r12 = this.mHost;
        if (r12 == null) goto L6;
        LayoutInflater r13 = r12.onGetLayoutInflater();
        LayoutInflaterCompat.setFactory2(r13, this.mChildFragmentManager.getLayoutInflaterFactory());
        return r13;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        return null;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public LoaderManager getLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    public int getNextTransition() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.mNextTransition;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public final FragmentManager getParentFragmentManager() {
        FragmentManager r0 = this.mFragmentManager;
        if (r0 == null) goto L5;
        return r0;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not associated with a fragment manager.");
        return null;
    }

    public boolean getPopDirection() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.mIsPop;
    }

    public int getPopEnterAnim() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.mPopEnterAnim;
    }

    public int getPopExitAnim() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.mPopExitAnim;
    }

    public float getPostOnViewCreatedAlpha() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return 1.0f;
    L7:
        return r0.mPostOnViewCreatedAlpha;
    }

    public Object getReenterTransition() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L6;
        return null;
    L6:
        Object r02 = r0.mReenterTransition;
        if (r02 == USE_DEFAULT_TRANSITION) goto L9;
        return r02;
    L9:
        return getExitTransition();
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        FragmentStrictMode.onGetRetainInstanceUsage(this);
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L6;
        return null;
    L6:
        Object r02 = r0.mReturnTransition;
        if (r02 == USE_DEFAULT_TRANSITION) goto L9;
        return r02;
    L9:
        return getEnterTransition();
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    public Object getSharedElementEnterTransition() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.mSharedElementEnterTransition;
    }

    public Object getSharedElementReturnTransition() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L6;
        return null;
    L6:
        Object r02 = r0.mSharedElementReturnTransition;
        if (r02 == USE_DEFAULT_TRANSITION) goto L9;
        return r02;
    L9:
        return getSharedElementEnterTransition();
    }

    public ArrayList<String> getSharedElementSourceNames() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 == null) goto L9;
        ArrayList<String> r02 = r0.mSharedElementSourceNames;
        if (r02 == null) goto L9;
        return r02;
    L9:
        return new ArrayList();
    }

    public ArrayList<String> getSharedElementTargetNames() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 == null) goto L9;
        ArrayList<String> r02 = r0.mSharedElementTargetNames;
        if (r02 == null) goto L9;
        return r02;
    L9:
        return new ArrayList();
    }

    public final String getString(int r1) {
        return getResources().getString(r1);
    }

    public final String getString(int r1, Object... r2) {
        return getResources().getString(r1, r2);
    }

    public final String getTag() {
        return this.mTag;
    }

    @Deprecated
    public final Fragment getTargetFragment() {
        return getTargetFragment(true);
    }

    @Deprecated
    public final int getTargetRequestCode() {
        FragmentStrictMode.onGetTargetFragmentRequestCodeUsage(this);
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int r1) {
        return getResources().getText(r1);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public LifecycleOwner getViewLifecycleOwner() {
        FragmentViewLifecycleOwner r0 = this.mViewLifecycleOwner;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
        return null;
    }

    public LiveData<LifecycleOwner> getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        if (this.mFragmentManager != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Can't access ViewModels from detached fragment");
        return null;
    L5:
        if (getMinimumMaxLifecycleState() != Lifecycle.State.INITIALIZED.ordinal()) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        return null;
    L7:
        return this.mFragmentManager.getViewModelStore(this);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void initState() {
        initLifecycle();
        this.mPreviousWho = this.mWho;
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new FragmentManagerImpl();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isAdded() {
        if (this.mHost != null) goto L5;
        return false;
    L5:
        if (this.mAdded == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        if (this.mHidden == true) goto L11;
        FragmentManager r0 = this.mFragmentManager;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.isParentHidden(this.mParentFragment) == true) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    public final boolean isInBackStack() {
        if (this.mBackStackNesting <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        if (this.mMenuVisible == false) goto L10;
        FragmentManager r0 = this.mFragmentManager;
        if (r0 != null) goto L7;
        return true;
    L7:
        if (r0.isParentMenuVisible(this.mParentFragment) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isPostponed() {
        AnimationInfo r0 = this.mAnimationInfo;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.mEnterTransitionPostponed;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        if (this.mState < 7) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean isStateSaved() {
        FragmentManager r0 = this.mFragmentManager;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.isStateSaved();
    }

    public final boolean isVisible() {
        if (isAdded() == true) goto L5;
        return false;
    L5:
        if (isHidden() == true) goto L16;
        View r0 = this.mView;
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r0.getWindowToken() != null) goto L11;
        return false;
    L11:
        if (this.mView.getVisibility() != 0) goto L19;
        return true;
    L19:
        return false;
    L16:
        return false;
    }

    public void noteStateNotSaved() {
        this.mChildFragmentManager.noteStateNotSaved();
    }

    @Deprecated
    public void onActivityCreated(Bundle r1) {
        this.mCalled = true;
    }

    @Deprecated
    public void onActivityResult(int r3, int r4, Intent r5) {
        if (FragmentManager.isLoggingEnabled(2) == false) goto L6;
        Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + r3 + " resultCode: " + r4 + " data: " + r5);
        return;
    }

    @Deprecated
    public void onAttach(Activity r1) {
        this.mCalled = true;
    }

    public void onAttach(Context r2) {
        this.mCalled = true;
        FragmentHostCallback<?> r22 = this.mHost;
        if (r22 != null) goto L5;
        Activity r23 = null;
    L6:
        if (r23 == null) goto L9;
        this.mCalled = false;
        onAttach(r23);
        return;
    L9:
        return;
    L5:
        r23 = r22.getActivity();
        goto L6
    }

    @Deprecated
    public void onAttachFragment(Fragment r1) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r1) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem r1) {
        return false;
    }

    public void onCreate(Bundle r2) {
        this.mCalled = true;
        restoreChildFragmentState(r2);
        if (this.mChildFragmentManager.isStateAtLeast(1) == true) goto L6;
        this.mChildFragmentManager.dispatchCreate();
        return;
    }

    public Animation onCreateAnimation(int r1, boolean r2, int r3) {
        return null;
    }

    public Animator onCreateAnimator(int r1, boolean r2, int r3) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu r1, View r2, ContextMenu.ContextMenuInfo r3) {
        requireActivity().onCreateContextMenu(r1, r2, r3);
    }

    @Deprecated
    public void onCreateOptionsMenu(Menu r1, MenuInflater r2) {
    }

    public View onCreateView(LayoutInflater r1, ViewGroup r2, Bundle r3) {
        int r0 = this.mContentLayoutId;
        if (r0 != 0) goto L5;
        return null;
    L5:
        return r1.inflate(r0, r2, false);
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    @Deprecated
    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle r1) {
        return getLayoutInflater(r1);
    }

    public void onHiddenChanged(boolean r1) {
    }

    @Deprecated
    public void onInflate(Activity r1, AttributeSet r2, Bundle r3) {
        this.mCalled = true;
    }

    public void onInflate(Context r2, AttributeSet r3, Bundle r4) {
        this.mCalled = true;
        FragmentHostCallback<?> r22 = this.mHost;
        if (r22 != null) goto L5;
        Activity r23 = null;
    L6:
        if (r23 == null) goto L9;
        this.mCalled = false;
        onInflate(r23, r3, r4);
        return;
    L9:
        return;
    L5:
        r23 = r22.getActivity();
        goto L6
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean r1) {
    }

    @Deprecated
    public boolean onOptionsItemSelected(MenuItem r1) {
        return false;
    }

    @Deprecated
    public void onOptionsMenuClosed(Menu r1) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean r1) {
    }

    @Deprecated
    public void onPrepareOptionsMenu(Menu r1) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean r1) {
    }

    @Deprecated
    public void onRequestPermissionsResult(int r1, String[] r2, int[] r3) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle r1) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View r1, Bundle r2) {
    }

    public void onViewStateRestored(Bundle r1) {
        this.mCalled = true;
    }

    public void performActivityCreated(Bundle r2) {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mState = 3;
        this.mCalled = false;
        onActivityCreated(r2);
        if (this.mCalled == false) goto L6;
        restoreViewState();
        this.mChildFragmentManager.dispatchActivityCreated();
        return;
    L6:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onActivityCreated()");
    }

    public void performAttach() {
        ArrayList<OnPreAttachedListener> r0 = this.mOnPreAttachedListeners;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        OnPreAttachedListener r4 = r0.get(r3);
        r3 = r3 + 1;
        r4.onPreAttached();
        goto L3
    L5:
        this.mOnPreAttachedListeners.clear();
        this.mChildFragmentManager.attachController(this.mHost, createFragmentContainer(), this);
        this.mState = 0;
        this.mCalled = false;
        onAttach(this.mHost.getContext());
        if (this.mCalled == false) goto L9;
        this.mFragmentManager.dispatchOnAttachFragment(this);
        this.mChildFragmentManager.dispatchAttach();
        return;
    L9:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onAttach()");
    }

    public void performConfigurationChanged(Configuration r1) {
        onConfigurationChanged(r1);
    }

    public boolean performContextItemSelected(MenuItem r2) {
        if (this.mHidden == false) goto L5;
        return false;
    L5:
        if (onContextItemSelected(r2) == false) goto L9;
        return true;
    L9:
        return this.mChildFragmentManager.dispatchContextItemSelected(r2);
    }

    public void performCreate(Bundle r4) {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mState = 1;
        this.mCalled = false;
        this.mLifecycleRegistry.addObserver(new AnonymousClass6(this));
        this.mSavedStateRegistryController.performRestore(r4);
        onCreate(r4);
        this.mIsCreated = true;
        if (this.mCalled == false) goto L6;
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        return;
    L6:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onCreate()");
    }

    public boolean performCreateOptionsMenu(Menu r3, MenuInflater r4) {
        boolean r1 = false;
        if (this.mHidden == false) goto L5;
        return false;
    L5:
        if (this.mHasMenu == false) goto L10;
        if (this.mMenuVisible == false) goto L10;
        onCreateOptionsMenu(r3, r4);
        r1 = true;
    L10:
        return this.mChildFragmentManager.dispatchCreateOptionsMenu(r3, r4) | r1;
    }

    public void performCreateView(LayoutInflater r3, ViewGroup r4, Bundle r5) {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new FragmentViewLifecycleOwner(this, getViewModelStore());
        View r32 = onCreateView(r3, r4, r5);
        this.mView = r32;
        FragmentViewLifecycleOwner r42 = this.mViewLifecycleOwner;
        if (r32 == null) goto L7;
        r42.initialize();
        ViewTreeLifecycleOwner.set(this.mView, this.mViewLifecycleOwner);
        ViewTreeViewModelStoreOwner.set(this.mView, this.mViewLifecycleOwner);
        ViewTreeSavedStateRegistryOwner.set(this.mView, this.mViewLifecycleOwner);
        this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
        return;
    L7:
        if (r42.isInitialized() == true) goto L10;
        this.mViewLifecycleOwner = null;
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Called getViewLifecycleOwner() but onCreateView() returned null");
    }

    public void performDestroy() {
        this.mChildFragmentManager.dispatchDestroy();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (this.mCalled == false) goto L5;
        return;
    L5:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onDestroy()");
    }

    public void performDestroyView() {
        this.mChildFragmentManager.dispatchDestroyView();
        if (this.mView != null) goto L5;
    L7:
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled == false) goto L11;
        LoaderManager.getInstance(this).markForRedelivery();
        this.mPerformedCreateView = false;
        return;
    L11:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onDestroyView()");
        return;
    L5:
        if (this.mViewLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED) == false) goto L7;
        this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        goto L7
    }

    public void performDetach() {
        this.mState = -1;
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (this.mCalled == true) goto L5;
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onDetach()");
        return;
    L5:
        if (this.mChildFragmentManager.isDestroyed() == true) goto L10;
        this.mChildFragmentManager.dispatchDestroy();
        this.mChildFragmentManager = new FragmentManagerImpl();
        return;
    }

    public LayoutInflater performGetLayoutInflater(Bundle r1) {
        LayoutInflater r12 = onGetLayoutInflater(r1);
        this.mLayoutInflater = r12;
        return r12;
    }

    public void performLowMemory() {
        onLowMemory();
    }

    public void performMultiWindowModeChanged(boolean r1) {
        onMultiWindowModeChanged(r1);
    }

    public boolean performOptionsItemSelected(MenuItem r2) {
        if (this.mHidden == false) goto L5;
        return false;
    L5:
        if (this.mHasMenu == false) goto L13;
        if (this.mMenuVisible == false) goto L13;
        if (onOptionsItemSelected(r2) == false) goto L13;
        return true;
    L13:
        return this.mChildFragmentManager.dispatchOptionsItemSelected(r2);
    }

    public void performOptionsMenuClosed(Menu r2) {
        if (this.mHidden == false) goto L5;
        return;
    L5:
        if (this.mHasMenu == true) goto L7;
    L9:
        this.mChildFragmentManager.dispatchOptionsMenuClosed(r2);
        return;
    L7:
        if (this.mMenuVisible == false) goto L9;
        onOptionsMenuClosed(r2);
        goto L9
    }

    public void performPause() {
        this.mChildFragmentManager.dispatchPause();
        if (this.mView == null) goto L5;
        this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    L5:
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        this.mState = 6;
        this.mCalled = false;
        onPause();
        if (this.mCalled == false) goto L8;
        return;
    L8:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onPause()");
    }

    public void performPictureInPictureModeChanged(boolean r1) {
        onPictureInPictureModeChanged(r1);
    }

    public boolean performPrepareOptionsMenu(Menu r3) {
        boolean r1 = false;
        if (this.mHidden == false) goto L5;
        return false;
    L5:
        if (this.mHasMenu == false) goto L10;
        if (this.mMenuVisible == false) goto L10;
        onPrepareOptionsMenu(r3);
        r1 = true;
    L10:
        return this.mChildFragmentManager.dispatchPrepareOptionsMenu(r3) | r1;
    }

    public void performPrimaryNavigationFragmentChanged() {
        boolean r0 = this.mFragmentManager.isPrimaryNavigation(this);
        Boolean r1 = this.mIsPrimaryNavigationFragment;
        if (r1 != null) goto L5;
    L8:
        this.mIsPrimaryNavigationFragment = Boolean.valueOf(r0);
        onPrimaryNavigationFragmentChanged(r0);
        this.mChildFragmentManager.dispatchPrimaryNavigationFragmentChanged();
        return;
    L5:
        if (r1.booleanValue() != r0) goto L8;
    }

    public void performResume() {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mChildFragmentManager.execPendingActions(true);
        this.mState = 7;
        this.mCalled = false;
        onResume();
        if (this.mCalled == false) goto L9;
        LifecycleRegistry r0 = this.mLifecycleRegistry;
        Lifecycle.Event r1 = Lifecycle.Event.ON_RESUME;
        r0.handleLifecycleEvent(r1);
        if (this.mView == null) goto L7;
        this.mViewLifecycleOwner.handleLifecycleEvent(r1);
    L7:
        this.mChildFragmentManager.dispatchResume();
        return;
    L9:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onResume()");
    }

    public void performSaveInstanceState(Bundle r2) {
        onSaveInstanceState(r2);
        this.mSavedStateRegistryController.performSave(r2);
        Bundle r1 = this.mChildFragmentManager.saveAllStateInternal();
        if (r1 == null) goto L6;
        r2.putParcelable("android:support:fragments", r1);
        return;
    }

    public void performStart() {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mChildFragmentManager.execPendingActions(true);
        this.mState = 5;
        this.mCalled = false;
        onStart();
        if (this.mCalled == false) goto L9;
        LifecycleRegistry r0 = this.mLifecycleRegistry;
        Lifecycle.Event r1 = Lifecycle.Event.ON_START;
        r0.handleLifecycleEvent(r1);
        if (this.mView == null) goto L7;
        this.mViewLifecycleOwner.handleLifecycleEvent(r1);
    L7:
        this.mChildFragmentManager.dispatchStart();
        return;
    L9:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onStart()");
    }

    public void performStop() {
        this.mChildFragmentManager.dispatchStop();
        if (this.mView == null) goto L5;
        this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    L5:
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        this.mState = 4;
        this.mCalled = false;
        onStop();
        if (this.mCalled == false) goto L8;
        return;
    L8:
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onStop()");
    }

    public void performViewCreated() {
        onViewCreated(this.mView, this.mSavedFragmentState);
        this.mChildFragmentManager.dispatchViewCreated();
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public final void postponeEnterTransition(long r3, TimeUnit r5) {
        ensureAnimationInfo().mEnterTransitionPostponed = true;
        Handler r0 = this.mPostponedHandler;
        if (r0 == null) goto L5;
        r0.removeCallbacks(this.mPostponedDurationRunnable);
    L5:
        FragmentManager r02 = this.mFragmentManager;
        if (r02 == null) goto L8;
        this.mPostponedHandler = r02.getHost().getHandler();
    L9:
        this.mPostponedHandler.removeCallbacks(this.mPostponedDurationRunnable);
        this.mPostponedHandler.postDelayed(this.mPostponedDurationRunnable, r5.toMillis(r3));
        return;
    L8:
        this.mPostponedHandler = new Handler(Looper.getMainLooper());
        goto L9
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> r2, ActivityResultCallback<O> r3) {
        return prepareCallInternal(r2, new AnonymousClass7(this), r3);
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> r2, ActivityResultRegistry r3, ActivityResultCallback<O> r4) {
        return prepareCallInternal(r2, new AnonymousClass8(this, r3), r4);
    }

    public void registerForContextMenu(View r1) {
        r1.setOnCreateContextMenuListener(this);
    }

    @Deprecated
    public final void requestPermissions(String[] r2, int r3) {
        if (this.mHost == null) goto L6;
        getParentFragmentManager().launchRequestPermissions(this, r2, r3);
        return;
    L6:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not attached to Activity");
    }

    public final FragmentActivity requireActivity() {
        FragmentActivity r0 = getActivity();
        if (r0 == null) goto L5;
        return r0;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not attached to an activity.");
        return null;
    }

    public final Bundle requireArguments() {
        Bundle r0 = getArguments();
        if (r0 == null) goto L5;
        return r0;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " does not have any arguments.");
        return null;
    }

    public final Context requireContext() {
        Context r0 = getContext();
        if (r0 == null) goto L5;
        return r0;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not attached to a context.");
        return null;
    }

    @Deprecated
    public final FragmentManager requireFragmentManager() {
        return getParentFragmentManager();
    }

    public final Object requireHost() {
        Object r0 = getHost();
        if (r0 == null) goto L5;
        return r0;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not attached to a host.");
        return null;
    }

    public final Fragment requireParentFragment() {
        Fragment r0 = getParentFragment();
        if (r0 == null) goto L5;
        return r0;
    L5:
        if (getContext() != null) goto L8;
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " is not attached to any Fragment or host");
        return null;
    L8:
        StringBuilder r02 = new StringBuilder("Fragment ");
        r02.append(this);
        OkHttpClient$Builder$$ExternalSyntheticBUOutline0.m(r02, " is not a child Fragment, it is directly attached to ", getContext());
        return null;
    }

    public final View requireView() {
        View r0 = getView();
        if (r0 == null) goto L5;
        return r0;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " did not return a View from onCreateView() or this was called before onCreateView().");
        return null;
    }

    public void restoreChildFragmentState(Bundle r2) {
        if (r2 == null) goto L7;
        Parcelable r22 = r2.getParcelable("android:support:fragments");
        if (r22 == null) goto L8;
        this.mChildFragmentManager.restoreSaveStateInternal(r22);
        this.mChildFragmentManager.dispatchCreate();
        return;
    L8:
        return;
    }

    public final void restoreViewState(Bundle r4) {
        SparseArray<Parcelable> r0 = this.mSavedViewState;
        if (r0 == null) goto L6;
        this.mView.restoreHierarchyState(r0);
        this.mSavedViewState = null;
    L6:
        if (this.mView == null) goto L8;
        this.mViewLifecycleOwner.performRestore(this.mSavedViewRegistryState);
        this.mSavedViewRegistryState = null;
    L8:
        this.mCalled = false;
        onViewStateRestored(r4);
        if (this.mCalled == true) goto L11;
        Fragment$$ExternalSyntheticBUOutline0.m(this, " did not call through to super.onViewStateRestored()");
        return;
    L11:
        if (this.mView == null) goto L16;
        this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        return;
    }

    public void setAllowEnterTransitionOverlap(boolean r1) {
        AnimationInfo r0 = ensureAnimationInfo();
        r0.mAllowEnterTransitionOverlap = Boolean.valueOf(r1);
    }

    public void setAllowReturnTransitionOverlap(boolean r1) {
        AnimationInfo r0 = ensureAnimationInfo();
        r0.mAllowReturnTransitionOverlap = Boolean.valueOf(r1);
    }

    public void setAnimations(int r2, int r3, int r4, int r5) {
        if (this.mAnimationInfo != null) goto L9;
        if (r2 != 0) goto L9;
        if (r3 != 0) goto L9;
        if (r4 != 0) goto L9;
        if (r5 != 0) goto L9;
        return;
    L9:
        ensureAnimationInfo().mEnterAnim = r2;
        ensureAnimationInfo().mExitAnim = r3;
        ensureAnimationInfo().mPopEnterAnim = r4;
        ensureAnimationInfo().mPopExitAnim = r5;
    }

    public void setArguments(Bundle r2) {
        if (this.mFragmentManager != null) goto L5;
    L9:
        this.mArguments = r2;
        return;
    L5:
        if (isStateSaved() == false) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Fragment already added and state has been saved");
    }

    public void setEnterSharedElementCallback(SharedElementCallback r1) {
        ensureAnimationInfo().getClass();
    }

    public void setEnterTransition(Object r1) {
        ensureAnimationInfo().mEnterTransition = r1;
    }

    public void setExitSharedElementCallback(SharedElementCallback r1) {
        ensureAnimationInfo().getClass();
    }

    public void setExitTransition(Object r1) {
        ensureAnimationInfo().mExitTransition = r1;
    }

    public void setFocusedView(View r1) {
        ensureAnimationInfo().mFocusedView = r1;
    }

    @Deprecated
    public void setHasOptionsMenu(boolean r2) {
        if (this.mHasMenu == r2) goto L10;
        this.mHasMenu = r2;
        if (isAdded() == true) goto L7;
        return;
    L7:
        if (isHidden() == true) goto L12;
        this.mHost.onSupportInvalidateOptionsMenu();
        return;
    L12:
        return;
    }

    public void setInitialSavedState(SavedState r2) {
        if (this.mFragmentManager != null) goto L11;
        if (r2 == null) goto L8;
        Bundle r22 = r2.mState;
        if (r22 == null) goto L8;
    L9:
        this.mSavedFragmentState = r22;
        return;
    L8:
        r22 = null;
        goto L9
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("Fragment already added");
    }

    public void setMenuVisibility(boolean r2) {
        if (this.mMenuVisible == r2) goto L12;
        this.mMenuVisible = r2;
        if (this.mHasMenu == true) goto L7;
        return;
    L7:
        if (isAdded() == true) goto L9;
        return;
    L9:
        if (isHidden() == true) goto L15;
        this.mHost.onSupportInvalidateOptionsMenu();
        return;
    L15:
        return;
    }

    public void setNextTransition(int r2) {
        if (this.mAnimationInfo != null) goto L6;
        if (r2 != 0) goto L6;
        return;
    L6:
        ensureAnimationInfo();
        this.mAnimationInfo.mNextTransition = r2;
    }

    public void setPopDirection(boolean r2) {
        if (this.mAnimationInfo != null) goto L5;
        return;
    L5:
        ensureAnimationInfo().mIsPop = r2;
    }

    public void setPostOnViewCreatedAlpha(float r1) {
        ensureAnimationInfo().mPostOnViewCreatedAlpha = r1;
    }

    public void setReenterTransition(Object r1) {
        ensureAnimationInfo().mReenterTransition = r1;
    }

    @Deprecated
    public void setRetainInstance(boolean r2) {
        FragmentStrictMode.onSetRetainInstanceUsage(this);
        this.mRetainInstance = r2;
        FragmentManager r0 = this.mFragmentManager;
        if (r0 == null) goto L9;
        if (r2 == false) goto L7;
        r0.addRetainedFragment(this);
        return;
    L7:
        r0.removeRetainedFragment(this);
        return;
    L9:
        this.mRetainInstanceChangedWhileDetached = true;
    }

    public void setReturnTransition(Object r1) {
        ensureAnimationInfo().mReturnTransition = r1;
    }

    public void setSharedElementEnterTransition(Object r1) {
        ensureAnimationInfo().mSharedElementEnterTransition = r1;
    }

    public void setSharedElementNames(ArrayList<String> r1, ArrayList<String> r2) {
        ensureAnimationInfo();
        AnimationInfo r0 = this.mAnimationInfo;
        r0.mSharedElementSourceNames = r1;
        r0.mSharedElementTargetNames = r2;
    }

    public void setSharedElementReturnTransition(Object r1) {
        ensureAnimationInfo().mSharedElementReturnTransition = r1;
    }

    @Deprecated
    public void setTargetFragment(Fragment r4, int r5) {
        if (r4 == null) goto L4;
        FragmentStrictMode.onSetTargetFragmentUsage(this, r4, r5);
    L4:
        FragmentManager r0 = this.mFragmentManager;
        if (r4 == null) goto L7;
        FragmentManager r2 = r4.mFragmentManager;
    L8:
        if (r0 == null) goto L14;
        if (r2 == null) goto L14;
        if (r0 == r2) goto L14;
        Native$$ExternalSyntheticBUOutline0.m("Fragment ", r4, " must share the same FragmentManager to be set as a target fragment");
        return;
    L14:
        Fragment r02 = r4;
    L15:
        if (r02 == null) goto L21;
        if (r02.equals(this) == true) goto L19;
        r02 = r02.getTargetFragment(false);
        goto L15
    L19:
        Native$$ExternalSyntheticBUOutline3.m("Setting ", r4, " as the target of ", this, " would create a target cycle");
        return;
    L21:
        if (r4 != null) goto L24;
        this.mTargetWho = null;
        this.mTarget = null;
    L29:
        this.mTargetRequestCode = r5;
        return;
    L24:
        if (this.mFragmentManager != null) goto L26;
    L28:
        this.mTargetWho = null;
        this.mTarget = r4;
        goto L29
    L26:
        if (r4.mFragmentManager == null) goto L28;
        this.mTargetWho = r4.mWho;
        this.mTarget = null;
        goto L29
    L7:
        r2 = null;
        goto L8
    }

    @Deprecated
    public void setUserVisibleHint(boolean r4) {
        FragmentStrictMode.onSetUserVisibleHint(this, r4);
        if (this.mUserVisibleHint == true) goto L14;
        if (r4 == false) goto L14;
        if (this.mState >= 5) goto L14;
        if (this.mFragmentManager == null) goto L14;
        if (isAdded() == false) goto L14;
        if (this.mIsCreated == false) goto L14;
        FragmentManager r0 = this.mFragmentManager;
        r0.performPendingDeferredStart(r0.createOrGetFragmentStateManager(this));
    L14:
        this.mUserVisibleHint = r4;
        if (this.mState >= 5) goto L18;
        if (r4 == true) goto L18;
        boolean r02 = true;
    L19:
        this.mDeferStart = r02;
        if (this.mSavedFragmentState == null) goto L23;
        this.mSavedUserVisibleHint = Boolean.valueOf(r4);
        return;
    L23:
        return;
    L18:
        r02 = false;
        goto L19
    }

    public boolean shouldShowRequestPermissionRationale(String r1) {
        FragmentHostCallback<?> r0 = this.mHost;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.onShouldShowRequestPermissionRationale(r1);
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent r2) {
        startActivity(r2, null);
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent r3, Bundle r4) {
        FragmentHostCallback<?> r0 = this.mHost;
        if (r0 == null) goto L6;
        r0.onStartActivityFromFragment(this, r3, -1, r4);
        return;
    L6:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not attached to Activity");
    }

    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent r2, int r3) {
        startActivityForResult(r2, r3, null);
    }

    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent r2, int r3, Bundle r4) {
        if (this.mHost == null) goto L6;
        getParentFragmentManager().launchStartActivityForResult(this, r2, r3, r4);
        return;
    L6:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not attached to Activity");
    }

    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender r10, int r11, Intent r12, int r13, int r14, int r15, Bundle r16) {
        if (this.mHost != null) goto L5;
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", this, " not attached to Activity");
        return;
    L5:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L7;
        StringBuilder r0 = new StringBuilder("Fragment ");
        r0.append(this);
        r0.append(" received the following in startIntentSenderForResult() requestCode: ");
        r0.append(r11);
        r0.append(" IntentSender: ");
        r0.append(r10);
        r0.append(" fillInIntent: ");
        r0.append(r12);
        r0.append(" options: ");
        Bundle r8 = r16;
        r0.append(r8);
        Log.v("FragmentManager", r0.toString());
    L8:
        getParentFragmentManager().launchStartIntentSenderForResult(this, r10, r11, r12, r13, r14, r15, r8);
        return;
    L7:
        r8 = r16;
        goto L8
    }

    public void startPostponedEnterTransition() {
        if (this.mAnimationInfo != null) goto L5;
        return;
    L5:
        if (ensureAnimationInfo().mEnterTransitionPostponed == true) goto L8;
        return;
    L8:
        if (this.mHost != null) goto L12;
        ensureAnimationInfo().mEnterTransitionPostponed = false;
        return;
    L12:
        if (Looper.myLooper() == this.mHost.getHandler().getLooper()) goto L15;
        this.mHost.getHandler().postAtFrontOfQueue(new AnonymousClass3(this));
        return;
    L15:
        callStartTransitionListener(true);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(128);
        r0.append(getClass().getSimpleName());
        r0.append("{");
        r0.append(Integer.toHexString(System.identityHashCode(this)));
        r0.append("} (");
        r0.append(this.mWho);
        if (this.mFragmentId == 0) goto L6;
        r0.append(" id=0x");
        r0.append(Integer.toHexString(this.mFragmentId));
    L6:
        if (this.mTag == null) goto L8;
        r0.append(" tag=");
        r0.append(this.mTag);
    L8:
        r0.append(")");
        return r0.toString();
    }

    public void unregisterForContextMenu(View r1) {
        r1.setOnCreateContextMenuListener(null);
    }
}
