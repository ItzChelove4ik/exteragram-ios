package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.R$id;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import dagger.internal.DoubleCheck$$ExternalSyntheticBUOutline0;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public abstract class FragmentManager {
    private static boolean DEBUG = false;
    ArrayList<BackStackRecord> mBackStack;
    private ArrayList<Object> mBackStackChangeListeners;
    private final AtomicInteger mBackStackIndex;
    private final Map<String, BackStackState> mBackStackStates;
    private FragmentContainer mContainer;
    private ArrayList<Fragment> mCreatedMenus;
    int mCurState;
    private SpecialEffectsControllerFactory mDefaultSpecialEffectsControllerFactory;
    private boolean mDestroyed;
    private Runnable mExecCommit;
    private boolean mExecutingActions;
    private FragmentFactory mFragmentFactory;
    private final FragmentStore mFragmentStore;
    private boolean mHavePendingDeferredStart;
    private FragmentHostCallback<?> mHost;
    private FragmentFactory mHostFragmentFactory;
    ArrayDeque<LaunchedFragmentInfo> mLaunchedFragments;
    private final FragmentLayoutInflaterFactory mLayoutInflaterFactory;
    private final FragmentLifecycleCallbacksDispatcher mLifecycleCallbacksDispatcher;
    private final MenuProvider mMenuProvider;
    private boolean mNeedMenuInvalidate;
    private FragmentManagerViewModel mNonConfig;
    private final CopyOnWriteArrayList<FragmentOnAttachListener> mOnAttachListeners;
    private final OnBackPressedCallback mOnBackPressedCallback;
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final Consumer<Configuration> mOnConfigurationChangedListener;
    private final Consumer<MultiWindowModeChangedInfo> mOnMultiWindowModeChangedListener;
    private final Consumer<PictureInPictureModeChangedInfo> mOnPictureInPictureModeChangedListener;
    private final Consumer<Integer> mOnTrimMemoryListener;
    private Fragment mParent;
    private final ArrayList<OpGenerator> mPendingActions;
    Fragment mPrimaryNav;
    private ActivityResultLauncher<String[]> mRequestPermissions;
    private final Map<String, Object> mResultListeners;
    private final Map<String, Bundle> mResults;
    private SpecialEffectsControllerFactory mSpecialEffectsControllerFactory;
    private ActivityResultLauncher<Intent> mStartActivityForResult;
    private ActivityResultLauncher<IntentSenderRequest> mStartIntentSenderForResult;
    private boolean mStateSaved;
    private boolean mStopped;
    private FragmentStrictMode.Policy mStrictModePolicy;
    private ArrayList<Fragment> mTmpAddedFragments;
    private ArrayList<Boolean> mTmpIsPop;
    private ArrayList<BackStackRecord> mTmpRecords;

    /* renamed from: androidx.fragment.app.FragmentManager$1, reason: invalid class name */
    public class AnonymousClass1 extends OnBackPressedCallback {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass1(FragmentManager r1, boolean r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            this.this$0.handleOnBackPressed();
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$10, reason: invalid class name */
    public class AnonymousClass10 implements ActivityResultCallback<Map<String, Boolean>> {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass10(FragmentManager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.activity.result.ActivityResultCallback
        @SuppressLint({"SyntheticAccessor"})
        public /* bridge */ /* synthetic */ void onActivityResult(Map<String, Boolean> r1) {
            onActivityResult2(r1);
        }

        @SuppressLint({"SyntheticAccessor"})
        /* renamed from: onActivityResult, reason: avoid collision after fix types in other method */
        public void onActivityResult2(Map<String, Boolean> r6) {
            String[] r0 = (String[]) r6.keySet().toArray(new String[0]);
            ArrayList r2 = new ArrayList(r6.values());
            int[] r62 = new int[r2.size()];
            int r3 = 0;
        L4:
            if (r3 >= r2.size()) goto L10;
            if (((Boolean) r2.get(r3)).booleanValue() == false) goto L8;
            int r4 = 0;
        L9:
            r62[r3] = r4;
            r3 = r3 + 1;
            goto L4
        L8:
            r4 = -1;
            goto L9
        L10:
            LaunchedFragmentInfo r1 = this.this$0.mLaunchedFragments.pollFirst();
            if (r1 != null) goto L14;
            Log.w("FragmentManager", "No permissions were requested for " + this);
            return;
        L14:
            String r32 = r1.mWho;
            int r12 = r1.mRequestCode;
            Fragment r5 = FragmentManager.access$200(this.this$0).findFragmentByWho(r32);
            if (r5 != null) goto L18;
            Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + r32);
            return;
        L18:
            r5.onRequestPermissionsResult(r12, r0, r62);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$2, reason: invalid class name */
    public class AnonymousClass2 implements MenuProvider {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass2(FragmentManager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.MenuProvider
        public void onCreateMenu(Menu r1, MenuInflater r2) {
            this.this$0.dispatchCreateOptionsMenu(r1, r2);
        }

        @Override // androidx.core.view.MenuProvider
        public void onMenuClosed(Menu r1) {
            this.this$0.dispatchOptionsMenuClosed(r1);
        }

        @Override // androidx.core.view.MenuProvider
        public boolean onMenuItemSelected(MenuItem r1) {
            return this.this$0.dispatchOptionsItemSelected(r1);
        }

        @Override // androidx.core.view.MenuProvider
        public void onPrepareMenu(Menu r1) {
            this.this$0.dispatchPrepareOptionsMenu(r1);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$3, reason: invalid class name */
    public class AnonymousClass3 extends FragmentFactory {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass3(FragmentManager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.fragment.app.FragmentFactory
        public Fragment instantiate(ClassLoader r2, String r3) {
            return this.this$0.getHost().instantiate(this.this$0.getHost().getContext(), r3, null);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$4, reason: invalid class name */
    public class AnonymousClass4 implements SpecialEffectsControllerFactory {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass4(FragmentManager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.fragment.app.SpecialEffectsControllerFactory
        public SpecialEffectsController createController(ViewGroup r1) {
            return new DefaultSpecialEffectsController(r1);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$5, reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass5(FragmentManager r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.execPendingActions(true);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$7, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass7 implements FragmentOnAttachListener {
        final /* synthetic */ FragmentManager this$0;
        final /* synthetic */ Fragment val$parent;

        public AnonymousClass7(FragmentManager r1, Fragment r2) {
            this.this$0 = r1;
            this.val$parent = r2;
        }

        @Override // androidx.fragment.app.FragmentOnAttachListener
        public void onAttachFragment(FragmentManager r1, Fragment r2) {
            this.val$parent.onAttachFragment(r2);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$8, reason: invalid class name */
    public class AnonymousClass8 implements ActivityResultCallback<ActivityResult> {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass8(FragmentManager r1) {
            this.this$0 = r1;
        }

        /* renamed from: onActivityResult, reason: avoid collision after fix types in other method */
        public void onActivityResult2(ActivityResult r4) {
            LaunchedFragmentInfo r0 = this.this$0.mLaunchedFragments.pollFirst();
            if (r0 != null) goto L6;
            Log.w("FragmentManager", "No Activities were started for result for " + this);
            return;
        L6:
            String r2 = r0.mWho;
            int r02 = r0.mRequestCode;
            Fragment r3 = FragmentManager.access$200(this.this$0).findFragmentByWho(r2);
            if (r3 != null) goto L10;
            Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + r2);
            return;
        L10:
            r3.onActivityResult(r02, r4.getResultCode(), r4.getData());
        }

        @Override // androidx.activity.result.ActivityResultCallback
        public /* bridge */ /* synthetic */ void onActivityResult(ActivityResult r1) {
            onActivityResult2(r1);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$9, reason: invalid class name */
    public class AnonymousClass9 implements ActivityResultCallback<ActivityResult> {
        final /* synthetic */ FragmentManager this$0;

        public AnonymousClass9(FragmentManager r1) {
            this.this$0 = r1;
        }

        /* renamed from: onActivityResult, reason: avoid collision after fix types in other method */
        public void onActivityResult2(ActivityResult r4) {
            LaunchedFragmentInfo r0 = this.this$0.mLaunchedFragments.pollFirst();
            if (r0 != null) goto L6;
            Log.w("FragmentManager", "No IntentSenders were started for " + this);
            return;
        L6:
            String r2 = r0.mWho;
            int r02 = r0.mRequestCode;
            Fragment r3 = FragmentManager.access$200(this.this$0).findFragmentByWho(r2);
            if (r3 != null) goto L10;
            Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + r2);
            return;
        L10:
            r3.onActivityResult(r02, r4.getResultCode(), r4.getData());
        }

        @Override // androidx.activity.result.ActivityResultCallback
        public /* bridge */ /* synthetic */ void onActivityResult(ActivityResult r1) {
            onActivityResult2(r1);
        }
    }

    public static class FragmentIntentSenderContract extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        public FragmentIntentSenderContract() {
        }

        /* renamed from: createIntent, reason: avoid collision after fix types in other method */
        public Intent createIntent2(Context r3, IntentSenderRequest r4) {
            Intent r2 = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent r32 = r4.getFillInIntent();
            if (r32 == null) goto L9;
            Bundle r1 = r32.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            if (r1 == null) goto L9;
            r2.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", r1);
            r32.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            if (r32.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false) == false) goto L9;
            r4 = new IntentSenderRequest.Builder(r4.getIntentSender()).setFillInIntent(null).setFlags(r4.getFlagsValues(), r4.getFlagsMask()).build();
        L9:
            r2.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", r4);
            if (FragmentManager.isLoggingEnabled(2) == false) goto L12;
            Log.v("FragmentManager", "CreateIntent created the following intent: " + r2);
        L12:
            return r2;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        public /* bridge */ /* synthetic */ Intent createIntent(Context r1, IntentSenderRequest r2) {
            return createIntent2(r1, r2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.activity.result.contract.ActivityResultContract
        public ActivityResult parseResult(int r1, Intent r2) {
            return new ActivityResult(r1, r2);
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        public /* bridge */ /* synthetic */ ActivityResult parseResult(int r1, Intent r2) {
            return parseResult(r1, r2);
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class FragmentLifecycleCallbacks {
        public FragmentLifecycleCallbacks() {
        }

        @Deprecated
        public void onFragmentActivityCreated(FragmentManager r1, Fragment r2, Bundle r3) {
        }

        public void onFragmentAttached(FragmentManager r1, Fragment r2, Context r3) {
        }

        public void onFragmentCreated(FragmentManager r1, Fragment r2, Bundle r3) {
        }

        public void onFragmentDestroyed(FragmentManager r1, Fragment r2) {
        }

        public void onFragmentDetached(FragmentManager r1, Fragment r2) {
        }

        public void onFragmentPaused(FragmentManager r1, Fragment r2) {
        }

        public void onFragmentPreAttached(FragmentManager r1, Fragment r2, Context r3) {
        }

        public void onFragmentPreCreated(FragmentManager r1, Fragment r2, Bundle r3) {
        }

        public void onFragmentResumed(FragmentManager r1, Fragment r2) {
        }

        public void onFragmentSaveInstanceState(FragmentManager r1, Fragment r2, Bundle r3) {
        }

        public void onFragmentStarted(FragmentManager r1, Fragment r2) {
        }

        public void onFragmentStopped(FragmentManager r1, Fragment r2) {
        }

        public void onFragmentViewCreated(FragmentManager r1, Fragment r2, View r3, Bundle r4) {
        }

        public void onFragmentViewDestroyed(FragmentManager r1, Fragment r2) {
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes3.dex */
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = null;
        int mRequestCode;
        String mWho;

        /* renamed from: androidx.fragment.app.FragmentManager$LaunchedFragmentInfo$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<LaunchedFragmentInfo> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo createFromParcel(Parcel r1) {
                return new LaunchedFragmentInfo(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ LaunchedFragmentInfo createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo[] newArray(int r1) {
                return new LaunchedFragmentInfo[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ LaunchedFragmentInfo[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public LaunchedFragmentInfo(Parcel r2) {
            this.mWho = r2.readString();
            this.mRequestCode = r2.readInt();
        }

        public LaunchedFragmentInfo(String r1, int r2) {
            this.mWho = r1;
            this.mRequestCode = r2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeString(this.mWho);
            r1.writeInt(this.mRequestCode);
        }
    }

    /* loaded from: classes3.dex */
    public interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> r1, ArrayList<Boolean> r2);
    }

    /* loaded from: classes3.dex */
    public class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;
        final /* synthetic */ FragmentManager this$0;

        public PopBackStackState(FragmentManager r1, String r2, int r3, int r4) {
            this.this$0 = r1;
            this.mName = r2;
            this.mId = r3;
            this.mFlags = r4;
        }

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean generateOps(ArrayList<BackStackRecord> r7, ArrayList<Boolean> r8) {
            Fragment r0 = this.this$0.mPrimaryNav;
            if (r0 == null) goto L13;
            if (this.mId >= 0) goto L13;
            if (this.mName != null) goto L13;
            if (r0.getChildFragmentManager().popBackStackImmediate() == false) goto L13;
            return false;
        L13:
            return this.this$0.popBackStackState(r7, r8, this.mName, this.mId, this.mFlags);
        }
    }

    public static /* synthetic */ void $r8$lambda$7VI8nQONq1ttVaacOnJY0SOCn_A(FragmentManager r1, Integer r2) {
        if (r1.isParentAdded() == true) goto L5;
        return;
    L5:
        if (r2.intValue() != 80) goto L9;
        r1.dispatchLowMemory(false);
        return;
    }

    public static /* synthetic */ Bundle $r8$lambda$RiacCGNf6NqHbI3ngibX25mGBMA(FragmentManager r0) {
        return r0.saveAllStateInternal();
    }

    /* renamed from: $r8$lambda$lO3Nl4juk4ObZbb6MT-FkJMWEjY, reason: not valid java name */
    public static /* synthetic */ void m725$r8$lambda$lO3Nl4juk4ObZbb6MTFkJMWEjY(FragmentManager r1, PictureInPictureModeChangedInfo r2) {
        if (r1.isParentAdded() == false) goto L6;
        r1.dispatchPictureInPictureModeChanged(r2.isInPictureInPictureMode(), false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$sjgpDn2MJatSnY7YFGNxTaNF_8E(FragmentManager r1, MultiWindowModeChangedInfo r2) {
        if (r1.isParentAdded() == false) goto L6;
        r1.dispatchMultiWindowModeChanged(r2.isInMultiWindowMode(), false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$yvqmBMGn9GAGgLS3kX3Pb1Mp5KA(FragmentManager r1, Configuration r2) {
        if (r1.isParentAdded() == false) goto L6;
        r1.dispatchConfigurationChanged(r2, false);
        return;
    }

    static {
    }

    public FragmentManager() {
        this.mPendingActions = new ArrayList();
        this.mFragmentStore = new FragmentStore();
        this.mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
        this.mOnBackPressedCallback = new AnonymousClass1(this, false);
        this.mBackStackIndex = new AtomicInteger();
        this.mBackStackStates = Collections.synchronizedMap(new HashMap());
        this.mResults = Collections.synchronizedMap(new HashMap());
        this.mResultListeners = Collections.synchronizedMap(new HashMap());
        this.mLifecycleCallbacksDispatcher = new FragmentLifecycleCallbacksDispatcher(this);
        this.mOnAttachListeners = new CopyOnWriteArrayList();
        this.mOnConfigurationChangedListener = new FragmentManager$$ExternalSyntheticLambda0(this);
        this.mOnTrimMemoryListener = new FragmentManager$$ExternalSyntheticLambda1(this);
        this.mOnMultiWindowModeChangedListener = new FragmentManager$$ExternalSyntheticLambda2(this);
        this.mOnPictureInPictureModeChangedListener = new FragmentManager$$ExternalSyntheticLambda3(this);
        this.mMenuProvider = new AnonymousClass2(this);
        this.mCurState = -1;
        this.mFragmentFactory = null;
        this.mHostFragmentFactory = new AnonymousClass3(this);
        this.mSpecialEffectsControllerFactory = null;
        this.mDefaultSpecialEffectsControllerFactory = new AnonymousClass4(this);
        this.mLaunchedFragments = new ArrayDeque();
        this.mExecCommit = new AnonymousClass5(this);
    }

    public static /* synthetic */ FragmentStore access$200(FragmentManager r0) {
        return r0.mFragmentStore;
    }

    private void checkStateLoss() {
        if (isStateSaved() == true) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Can not perform this action after onSaveInstanceState");
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    private void clearBackStackStateViewModels() {
        FragmentHostCallback<?> r0 = this.mHost;
        if ((r0 instanceof ViewModelStoreOwner) == false) goto L6;
        boolean r02 = this.mFragmentStore.getNonConfig().isCleared();
    L9:
        if (r02 == false) goto L17;
        Iterator<BackStackState> r03 = this.mBackStackStates.values().iterator();
    L12:
        if (r03.hasNext() == false) goto L20;
        Iterator<String> r1 = r03.next().mFragments.iterator();
    L15:
        if (r1.hasNext() == false) goto L12;
        this.mFragmentStore.getNonConfig().clearNonConfigState(r1.next());
        goto L15
    L20:
        return;
    L17:
        return;
    L6:
        if ((r0.getContext() instanceof Activity) == false) goto L8;
        r02 = !((Activity) this.mHost.getContext()).isChangingConfigurations();
        goto L9
    L8:
        r02 = true;
        goto L9
    }

    private Set<SpecialEffectsController> collectAllSpecialEffectsController() {
        HashSet r0 = new HashSet();
        Iterator<FragmentStateManager> r1 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        ViewGroup r2 = r1.next().getFragment().mContainer;
        if (r2 == null) goto L4;
        r0.add(SpecialEffectsController.getOrCreateController(r2, getSpecialEffectsControllerFactory()));
        goto L4
    L8:
        return r0;
    }

    private Set<SpecialEffectsController> collectChangedControllers(ArrayList<BackStackRecord> r6, int r7, int r8) {
        HashSet r0 = new HashSet();
    L3:
        if (r7 >= r8) goto L12;
        ArrayList<FragmentTransaction.Op> r1 = r6.get(r7).mOps;
        int r2 = r1.size();
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L11;
        FragmentTransaction.Op r4 = r1.get(r3);
        r3 = r3 + 1;
        Fragment r42 = r4.mFragment;
        if (r42 == null) goto L5;
        ViewGroup r43 = r42.mContainer;
        if (r43 == null) goto L5;
        r0.add(SpecialEffectsController.getOrCreateController(r43, this));
        goto L5
    L11:
        r7 = r7 + 1;
        goto L3
    L12:
        return r0;
    }

    private void dispatchParentPrimaryNavigationFragmentChanged(Fragment r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.equals(findActiveFragment(r2.mWho)) == false) goto L8;
        r2.performPrimaryNavigationFragmentChanged();
        return;
    }

    private void dispatchStateChange(int r4) {
        this.mExecutingActions = true;     // Catch: Throwable -> L8
        this.mFragmentStore.dispatchStateChange(r4);     // Catch: Throwable -> L8
        moveToState(r4, false);     // Catch: Throwable -> L8
        Iterator<SpecialEffectsController> r42 = collectAllSpecialEffectsController().iterator();     // Catch: Throwable -> L8
    L4:
        if (r42.hasNext() == false) goto L10;
        r42.next().forceCompleteAllOperations();     // Catch: Throwable -> L8
        goto L4
    L10:
        this.mExecutingActions = false;
        execPendingActions(true);
        return;
    L8:
        th = move-exception;
        this.mExecutingActions = false;
        throw th;
    }

    private void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart == false) goto L6;
        this.mHavePendingDeferredStart = false;
        startPendingDeferredFragments();
        return;
    }

    private void endAnimatingAwayFragments() {
        Iterator<SpecialEffectsController> r1 = collectAllSpecialEffectsController().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().forceCompleteAllOperations();
        goto L4
    }

    private void ensureExecReady(boolean r3) {
        if (this.mExecutingActions == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("FragmentManager is already executing transactions");
        return;
    L5:
        if (this.mHost != null) goto L13;
        if (this.mDestroyed == false) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m("FragmentManager has been destroyed");
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("FragmentManager has not been attached to a host.");
        return;
    L13:
        if (Looper.myLooper() != this.mHost.getHandler().getLooper()) goto L20;
        if (r3 == true) goto L17;
        checkStateLoss();
    L17:
        if (this.mTmpRecords != null) goto L24;
        this.mTmpRecords = new ArrayList();
        this.mTmpIsPop = new ArrayList();
        return;
    L24:
        return;
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("Must be called from main thread of fragment host");
    }

    private static void executeOps(ArrayList<BackStackRecord> r2, ArrayList<Boolean> r3, int r4, int r5) {
    L2:
        if (r4 >= r5) goto L8;
        BackStackRecord r0 = r2.get(r4);
        if (r3.get(r4).booleanValue() == false) goto L6;
        r0.bumpBackStackNesting(-1);
        r0.executePopOps();
    L7:
        r4 = r4 + 1;
        goto L2
    L6:
        r0.bumpBackStackNesting(1);
        r0.executeOps();
        goto L7
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> r10, ArrayList<Boolean> r11, int r12, int r13) {
        boolean r0 = r10.get(r12).mReorderingAllowed;
        ArrayList<Fragment> r1 = this.mTmpAddedFragments;
        if (r1 != null) goto L5;
        this.mTmpAddedFragments = new ArrayList();
    L6:
        this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
        Fragment r14 = getPrimaryNavigationFragment();
        int r3 = r12;
        boolean r4 = false;
    L8:
        if (r3 >= r13) goto L20;
        BackStackRecord r6 = r10.get(r3);
        boolean r7 = r11.get(r3).booleanValue();
        ArrayList<Fragment> r8 = this.mTmpAddedFragments;
        if (r7 == true) goto L12;
        r14 = r6.expandOps(r8, r14);
    L13:
        if (r4 == false) goto L15;
    L18:
        r4 = true;
    L19:
        r3 = r3 + 1;
        goto L8
    L15:
        if (r6.mAddToBackStack == true) goto L18;
        r4 = false;
        goto L19
    L12:
        r14 = r6.trackAddedFragmentsInPop(r8, r14);
        goto L13
    L20:
        this.mTmpAddedFragments.clear();
        if (r0 == false) goto L23;
    L34:
        executeOps(r10, r11, r12, r13);
        boolean r02 = r11.get(r13 - 1).booleanValue();
        int r15 = r12;
    L35:
        if (r15 >= r13) goto L50;
        BackStackRecord r32 = r10.get(r15);
        if (r02 == false) goto L44;
        int r62 = r32.mOps.size() - 1;
    L39:
        if (r62 < 0) goto L49;
        Fragment r72 = r32.mOps.get(r62).mFragment;
        if (r72 == null) goto L43;
        createOrGetFragmentStateManager(r72).moveToExpectedState();
    L43:
        r62 = r62 - 1;
    L49:
        r15 = r15 + 1;
        goto L35
    L44:
        ArrayList<FragmentTransaction.Op> r33 = r32.mOps;
        int r63 = r33.size();
        int r73 = 0;
    L45:
        if (r73 >= r63) goto L49;
        FragmentTransaction.Op r82 = r33.get(r73);
        r73 = r73 + 1;
        Fragment r83 = r82.mFragment;
        if (r83 == null) goto L45;
        createOrGetFragmentStateManager(r83).moveToExpectedState();
        goto L45
    L50:
        moveToState(this.mCurState, true);
        Iterator<SpecialEffectsController> r16 = collectChangedControllers(r10, r12, r13).iterator();
    L52:
        if (r16.hasNext() == false) goto L54;
        SpecialEffectsController r2 = r16.next();
        r2.updateOperationDirection(r02);
        r2.markPostponedState();
        r2.executePendingOperations();
    L54:
        if (r12 >= r13) goto L61;
        BackStackRecord r03 = r10.get(r12);
        if (r11.get(r12).booleanValue() == false) goto L60;
        if (r03.mIndex < 0) goto L60;
        r03.mIndex = -1;
    L60:
        r03.runOnCommitRunnables();
        r12 = r12 + 1;
        goto L54
    L61:
        if (r4 == false) goto L89;
        reportBackStackChanged();
        return;
    L89:
        return;
    L23:
        if (this.mCurState < 1) goto L34;
        int r04 = r12;
    L25:
        if (r04 >= r13) goto L34;
        ArrayList<FragmentTransaction.Op> r17 = r10.get(r04).mOps;
        int r34 = r17.size();
        int r64 = 0;
    L27:
        if (r64 >= r34) goto L33;
        FragmentTransaction.Op r74 = r17.get(r64);
        r64 = r64 + 1;
        Fragment r75 = r74.mFragment;
        if (r75 == null) goto L27;
        if (r75.mFragmentManager == null) goto L27;
        this.mFragmentStore.makeActive(createOrGetFragmentStateManager(r75));
        goto L27
    L33:
        r04 = r04 + 1;
        goto L25
    L5:
        r1.clear();
        goto L6
    }

    private int findBackStackIndex(String r5, int r6, boolean r7) {
        ArrayList<BackStackRecord> r0 = this.mBackStack;
        if (r0 != null) goto L5;
    L44:
        return -1;
    L5:
        if (r0.isEmpty() == true) goto L44;
        if (r5 != null) goto L14;
        if (r6 >= 0) goto L14;
        if (r7 == false) goto L13;
        return 0;
    L13:
        return this.mBackStack.size() - 1;
    L14:
        int r02 = this.mBackStack.size() - 1;
    L15:
        if (r02 < 0) goto L26;
        BackStackRecord r2 = this.mBackStack.get(r02);
        if (r5 != null) goto L19;
    L21:
        if (r6 < 0) goto L25;
        if (r6 == r2.mIndex) goto L26;
    L25:
        r02 = r02 - 1;
        goto L15
    L19:
        if (r5.equals(r2.getName()) == false) goto L21;
    L26:
        if (r02 >= 0) goto L28;
        return r02;
    L28:
        if (r7 == false) goto L40;
    L29:
        if (r02 <= 0) goto L38;
        BackStackRecord r72 = this.mBackStack.get(r02 - 1);
        if (r5 != null) goto L33;
    L34:
        if (r6 < 0) goto L38;
        if (r6 != r72.mIndex) goto L38;
    L37:
        r02 = r02 - 1;
        goto L29
    L33:
        if (r5.equals(r72.getName()) == true) goto L37;
    L38:
        return r02;
    L40:
        if (r02 != (this.mBackStack.size() - 1)) goto L43;
        return -1;
    L43:
        return r02 + 1;
    }

    public static FragmentManager findFragmentManager(View r5) {
        Fragment r0 = findViewFragment(r5);
        if (r0 != null) goto L5;
        Context r02 = r5.getContext();
    L12:
        if ((r02 instanceof ContextWrapper) == false) goto L17;
        if ((r02 instanceof FragmentActivity) == true) goto L15;
        r02 = ((ContextWrapper) r02).getBaseContext();
        goto L12
    L15:
        FragmentActivity r03 = (FragmentActivity) r02;
    L18:
        if (r03 != null) goto L20;
        LiveData$$ExternalSyntheticBUOutline0.m("View ", r5, " is not within a subclass of FragmentActivity.");
        return null;
    L20:
        return r03.getSupportFragmentManager();
    L17:
        r03 = null;
        goto L18
    L5:
        if (r0.isAdded() == true) goto L7;
        DoubleCheck$$ExternalSyntheticBUOutline0.m("The Fragment ", r0, " that owns View ", r5, " has already been destroyed. Nested fragments should always use the child FragmentManager.");
        return null;
    L7:
        return r0.getChildFragmentManager();
    }

    private static Fragment findViewFragment(View r2) {
    L3:
        if (r2 == null) goto L11;
        Fragment r1 = getViewFragment(r2);
        if (r1 != null) goto L6;
        Object r22 = r2.getParent();
        if ((r22 instanceof View) == true) goto L9;
        r2 = null;
        goto L3
    L9:
        r2 = (View) r22;
        goto L3
    L6:
        return r1;
    L11:
        return null;
    }

    private void forcePostponedTransactions() {
        Iterator<SpecialEffectsController> r1 = collectAllSpecialEffectsController().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().forcePostponedExecutePendingOperations();
        goto L4
    }

    private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> r6, ArrayList<Boolean> r7) {
        ArrayList<OpGenerator> r0 = this.mPendingActions;
        monitor-enter(r0);
        int r2 = 0;
        if (this.mPendingActions.isEmpty() == false) goto L25;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return false;
    L25:
        int r1 = this.mPendingActions.size();     // Catch: Throwable -> L16
        boolean r3 = false;
    L12:
        ArrayList<OpGenerator> r4 = this.mPendingActions;
        if (r2 >= r1) goto L18;
        r3 = r3 | r4.get(r2).generateOps(r6, r7);
        r2 = r2 + 1;
        goto L12
    L18:
        r4.clear();     // Catch: Throwable -> L8
        this.mHost.getHandler().removeCallbacks(this.mExecCommit);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r3;
    L16:
        th = move-exception;
        this.mPendingActions.clear();     // Catch: Throwable -> L8
        this.mHost.getHandler().removeCallbacks(this.mExecCommit);     // Catch: Throwable -> L8
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    private FragmentManagerViewModel getChildNonConfig(Fragment r1) {
        return this.mNonConfig.getChildNonConfig(r1);
    }

    private ViewGroup getFragmentContainer(Fragment r3) {
        ViewGroup r0 = r3.mContainer;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (r3.mContainerId > 0) goto L9;
        return null;
    L9:
        if (this.mContainer.onHasView() == false) goto L14;
        View r2 = this.mContainer.onFindViewById(r3.mContainerId);
        if ((r2 instanceof ViewGroup) == false) goto L14;
        return (ViewGroup) r2;
    L14:
        return null;
    }

    public static Fragment getViewFragment(View r1) {
        Object r12 = r1.getTag(R$id.fragment_container_view_tag);
        if ((r12 instanceof Fragment) == true) goto L5;
        return null;
    L5:
        return (Fragment) r12;
    }

    public static boolean isLoggingEnabled(int r1) {
        if (DEBUG == false) goto L5;
        return true;
    L5:
        if (Log.isLoggable("FragmentManager", r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isMenuAvailable(Fragment r1) {
        if (r1.mHasMenu == false) goto L7;
        if (r1.mMenuVisible == false) goto L7;
        return true;
    L7:
        if (r1.mChildFragmentManager.checkForMenus() == true) goto L12;
        return false;
    L12:
        return true;
    }

    private boolean isParentAdded() {
        Fragment r0 = this.mParent;
        if (r0 != null) goto L6;
        return true;
    L6:
        if (r0.isAdded() == true) goto L8;
        return false;
    L8:
        if (this.mParent.getParentFragmentManager().isParentAdded() == false) goto L12;
        return true;
    L12:
        return false;
    }

    private boolean popBackStackImmediate(String r9, int r10, int r11) {
        execPendingActions(false);
        ensureExecReady(true);
        Fragment r1 = this.mPrimaryNav;
        if (r1 == null) goto L9;
        if (r10 >= 0) goto L9;
        if (r9 != null) goto L9;
        if (r1.getChildFragmentManager().popBackStackImmediate() == false) goto L9;
        return true;
    L9:
        boolean r8 = popBackStackState(this.mTmpRecords, this.mTmpIsPop, r9, r10, r11);
        if (r8 == false) goto L17;
        this.mExecutingActions = true;
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);     // Catch: Throwable -> L14
        cleanupExec();
    L14:
        th = move-exception;
        cleanupExec();
        throw th;
    L17:
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return r8;
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> r5, ArrayList<Boolean> r6) {
        if (r5.isEmpty() == false) goto L6;
        return;
    L6:
        if (r5.size() != r6.size()) goto L26;
        int r0 = r5.size();
        int r1 = 0;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L23;
        if (r5.get(r1).mReorderingAllowed == true) goto L22;
        if (r2 == r1) goto L13;
        executeOpsTogether(r5, r6, r2, r1);
    L13:
        r2 = r1 + 1;
        if (r6.get(r1).booleanValue() == false) goto L21;
    L15:
        if (r2 >= r0) goto L21;
        if (r6.get(r2).booleanValue() == false) goto L21;
        if (r5.get(r2).mReorderingAllowed == true) goto L21;
        r2 = r2 + 1;
    L21:
        executeOpsTogether(r5, r6, r1, r2);
        r1 = r2 - 1;
    L22:
        r1 = r1 + 1;
        goto L8
    L23:
        if (r2 == r0) goto L34;
        executeOpsTogether(r5, r6, r2, r0);
        return;
    L34:
        return;
    L26:
        Segment$$ExternalSyntheticBUOutline1.m("Internal error with the back stack records");
    }

    private void reportBackStackChanged() {
        ArrayList<Object> r0 = this.mBackStackChangeListeners;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.size() <= 0) goto L10;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mBackStackChangeListeners.get(0));
        throw null;
    }

    public static int reverseTransit(int r3) {
        if (r3 == 4097) goto L16;
        if (r3 != 8194) goto L6;
        return 4097;
    L6:
        if (r3 != 8197) goto L8;
        return 4100;
    L8:
        if (r3 == 4099) goto L13;
        if (r3 == 4100) goto L12;
        return 0;
    L12:
        return 8197;
    L13:
        return 4099;
    L16:
        return 8194;
    }

    private void setVisibleRemovingFragment(Fragment r3) {
        ViewGroup r2 = getFragmentContainer(r3);
        if (r2 != null) goto L5;
        return;
    L5:
        if ((((r3.getEnterAnim() + r3.getExitAnim()) + r3.getPopEnterAnim()) + r3.getPopExitAnim()) > 0) goto L7;
        return;
    L7:
        if (r2.getTag(R$id.visible_removing_fragment_view_tag) != null) goto L9;
        r2.setTag(R$id.visible_removing_fragment_view_tag, r3);
    L9:
        ((Fragment) r2.getTag(R$id.visible_removing_fragment_view_tag)).setPopDirection(r3.getPopDirection());
    }

    private void startPendingDeferredFragments() {
        Iterator<FragmentStateManager> r0 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        performPendingDeferredStart(r0.next());
        goto L4
    }

    private void throwException(RuntimeException r8) {
        Log.e("FragmentManager", r8.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter r2 = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback<?> r0 = this.mHost;
        if (r0 == null) goto L15;
        r0.onDump("  ", null, r2, new String[0]);     // Catch: Exception -> L6
        throw r8;
    L6:
        e = move-exception;
        Log.e("FragmentManager", "Failed dumping state", e);
        throw r8;
    L15:
        dump("  ", null, r2, new String[0]);     // Catch: Exception -> L10
        throw r8;
    L10:
        e = move-exception;
        Log.e("FragmentManager", "Failed dumping state", e);
        throw r8;
    }

    private void updateOnBackPressedCallbackEnabled() {
        ArrayList<OpGenerator> r0 = this.mPendingActions;
        monitor-enter(r0);
        boolean r2 = true;
        if (this.mPendingActions.isEmpty() == true) goto L11;
        this.mOnBackPressedCallback.setEnabled(true);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        OnBackPressedCallback r02 = this.mOnBackPressedCallback;
        if (getBackStackEntryCount() > 0) goto L15;
    L17:
        r2 = false;
    L18:
        r02.setEnabled(r2);
        return;
    L15:
        if (isPrimaryNavigation(this.mParent) == false) goto L17;
    L9:
        th = move-exception;
        throw th;
    }

    public void addBackStackState(BackStackRecord r2) {
        if (this.mBackStack != null) goto L5;
        this.mBackStack = new ArrayList();
    L5:
        this.mBackStack.add(r2);
    }

    public FragmentStateManager addFragment(Fragment r4) {
        String r0 = r4.mPreviousWho;
        if (r0 == null) goto L6;
        FragmentStrictMode.onFragmentReuse(r4, r0);
    L6:
        if (isLoggingEnabled(2) == false) goto L8;
        Log.v("FragmentManager", "add: " + r4);
    L8:
        FragmentStateManager r02 = createOrGetFragmentStateManager(r4);
        r4.mFragmentManager = this;
        this.mFragmentStore.makeActive(r02);
        if (r4.mDetached == true) goto L16;
        this.mFragmentStore.addFragment(r4);
        r4.mRemoving = false;
        if (r4.mView != null) goto L14;
        r4.mHiddenChanged = false;
    L14:
        if (isMenuAvailable(r4) == false) goto L16;
        this.mNeedMenuInvalidate = true;
    L16:
        return r02;
    }

    public void addFragmentOnAttachListener(FragmentOnAttachListener r1) {
        this.mOnAttachListeners.add(r1);
    }

    public void addRetainedFragment(Fragment r1) {
        this.mNonConfig.addRetainedFragment(r1);
    }

    public int allocBackStackIndex() {
        return this.mBackStackIndex.getAndIncrement();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"SyntheticAccessor"})
    public void attachController(FragmentHostCallback<?> r4, FragmentContainer r5, Fragment r6) {
        if (this.mHost != null) goto L55;
        this.mHost = r4;
        this.mContainer = r5;
        this.mParent = r6;
        if (r6 == null) goto L8;
        addFragmentOnAttachListener(new AnonymousClass7(this, r6));
    L11:
        if (this.mParent == null) goto L14;
        updateOnBackPressedCallbackEnabled();
    L14:
        if ((r4 instanceof OnBackPressedDispatcherOwner) == false) goto L19;
        OnBackPressedDispatcherOwner r52 = (OnBackPressedDispatcherOwner) r4;
        OnBackPressedDispatcher r0 = r52.getOnBackPressedDispatcher();
        this.mOnBackPressedDispatcher = r0;
        LifecycleOwner r53 = r52;
        if (r6 == null) goto L18;
        r53 = r6;
    L18:
        r0.addCallback(r53, this.mOnBackPressedCallback);
    L19:
        if (r6 == null) goto L22;
        this.mNonConfig = r6.mFragmentManager.getChildNonConfig(r6);
    L25:
        this.mNonConfig.setIsStateSaved(isStateSaved());
        this.mFragmentStore.setNonConfig(this.mNonConfig);
        Object r42 = this.mHost;
        if ((r42 instanceof SavedStateRegistryOwner) == false) goto L31;
        if (r6 != null) goto L31;
        SavedStateRegistry r43 = ((SavedStateRegistryOwner) r42).getSavedStateRegistry();
        r43.registerSavedStateProvider("android:support:fragments", new FragmentManager$$ExternalSyntheticLambda4(this));
        Bundle r44 = r43.consumeRestoredStateForKey("android:support:fragments");
        if (r44 == null) goto L31;
        restoreSaveStateInternal(r44);
    L31:
        Object r45 = this.mHost;
        if ((r45 instanceof ActivityResultRegistryOwner) == false) goto L38;
        ActivityResultRegistry r46 = ((ActivityResultRegistryOwner) r45).getActivityResultRegistry();
        if (r6 == null) goto L36;
        String r54 = r6.mWho + ":";
    L37:
        String r55 = "FragmentManager:".concat(r54);
        this.mStartActivityForResult = r46.register(r55.concat("StartActivityForResult"), new ActivityResultContracts$StartActivityForResult(), new AnonymousClass8(this));
        this.mStartIntentSenderForResult = r46.register(r55.concat("StartIntentSenderForResult"), new FragmentIntentSenderContract(), new AnonymousClass9(this));
        this.mRequestPermissions = r46.register(r55.concat("RequestPermissions"), new ActivityResultContracts$RequestMultiplePermissions(), new AnonymousClass10(this));
        goto L38
    L36:
        r54 = _UrlKt.FRAGMENT_ENCODE_SET;
    L38:
        Object r47 = this.mHost;
        if ((r47 instanceof OnConfigurationChangedProvider) == false) goto L41;
        ((OnConfigurationChangedProvider) r47).addOnConfigurationChangedListener(this.mOnConfigurationChangedListener);
    L41:
        Object r48 = this.mHost;
        if ((r48 instanceof OnTrimMemoryProvider) == false) goto L44;
        ((OnTrimMemoryProvider) r48).addOnTrimMemoryListener(this.mOnTrimMemoryListener);
    L44:
        Object r49 = this.mHost;
        if ((r49 instanceof OnMultiWindowModeChangedProvider) == false) goto L47;
        ((OnMultiWindowModeChangedProvider) r49).addOnMultiWindowModeChangedListener(this.mOnMultiWindowModeChangedListener);
    L47:
        Object r410 = this.mHost;
        if ((r410 instanceof OnPictureInPictureModeChangedProvider) == false) goto L50;
        ((OnPictureInPictureModeChangedProvider) r410).addOnPictureInPictureModeChangedListener(this.mOnPictureInPictureModeChangedListener);
    L50:
        Object r411 = this.mHost;
        if ((r411 instanceof MenuHost) == false) goto L57;
        if (r6 != null) goto L58;
        ((MenuHost) r411).addMenuProvider(this.mMenuProvider);
        return;
    L58:
        return;
    L57:
        return;
    L22:
        if ((r4 instanceof ViewModelStoreOwner) == false) goto L24;
        this.mNonConfig = FragmentManagerViewModel.getInstance(((ViewModelStoreOwner) r4).getViewModelStore());
        goto L25
    L24:
        this.mNonConfig = new FragmentManagerViewModel(false);
        goto L25
    L8:
        if ((r4 instanceof FragmentOnAttachListener) == false) goto L11;
        addFragmentOnAttachListener((FragmentOnAttachListener) r4);
        goto L11
    L55:
        Segment$$ExternalSyntheticBUOutline1.m("Already attached");
    }

    public void attachFragment(Fragment r5) {
        if (isLoggingEnabled(2) == false) goto L6;
        Log.v("FragmentManager", "attach: " + r5);
    L6:
        if (r5.mDetached == false) goto L16;
        r5.mDetached = false;
        if (r5.mAdded == true) goto L17;
        this.mFragmentStore.addFragment(r5);
        if (isLoggingEnabled(2) == false) goto L13;
        Log.v("FragmentManager", "add from attach: " + r5);
    L13:
        if (isMenuAvailable(r5) == false) goto L18;
        this.mNeedMenuInvalidate = true;
        return;
    L18:
        return;
    L17:
        return;
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public boolean checkForMenus() {
        Iterator<Fragment> r0 = this.mFragmentStore.getActiveFragments().iterator();
        boolean r2 = false;
    L4:
        if (r0.hasNext() == false) goto L11;
        Fragment r3 = r0.next();
        if (r3 == null) goto L8;
        r2 = isMenuAvailable(r3);
    L8:
        if (r2 == false) goto L4;
        return true;
    L11:
        return false;
    }

    public FragmentStateManager createOrGetFragmentStateManager(Fragment r4) {
        FragmentStateManager r0 = this.mFragmentStore.getFragmentStateManager(r4.mWho);
        if (r0 == null) goto L5;
        return r0;
    L5:
        FragmentStateManager r02 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, r4);
        r02.restoreState(this.mHost.getContext().getClassLoader());
        r02.setFragmentManagerState(this.mCurState);
        return r02;
    }

    public void detachFragment(Fragment r5) {
        if (isLoggingEnabled(2) == false) goto L6;
        Log.v("FragmentManager", "detach: " + r5);
    L6:
        if (r5.mDetached == true) goto L17;
        r5.mDetached = true;
        if (r5.mAdded == true) goto L10;
        return;
    L10:
        if (isLoggingEnabled(2) == false) goto L12;
        Log.v("FragmentManager", "remove from detach: " + r5);
    L12:
        this.mFragmentStore.removeFragment(r5);
        if (isMenuAvailable(r5) == false) goto L15;
        this.mNeedMenuInvalidate = true;
    L15:
        setVisibleRemovingFragment(r5);
        return;
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(4);
    }

    public void dispatchAttach() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(0);
    }

    public void dispatchConfigurationChanged(Configuration r3, boolean r4) {
        if (r4 == true) goto L4;
    L6:
        Iterator<Fragment> r2 = this.mFragmentStore.getFragments().iterator();
    L8:
        if (r2.hasNext() == false) goto L14;
        Fragment r0 = r2.next();
        if (r0 == null) goto L8;
        r0.performConfigurationChanged(r3);
        if (r4 == false) goto L8;
        r0.mChildFragmentManager.dispatchConfigurationChanged(r3, true);
        goto L8
    L14:
        return;
    L4:
        if ((this.mHost instanceof OnConfigurationChangedProvider) == false) goto L6;
        throwException(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
        goto L6
    }

    public boolean dispatchContextItemSelected(MenuItem r4) {
        if (this.mCurState >= 1) goto L5;
        return false;
    L5:
        Iterator<Fragment> r3 = this.mFragmentStore.getFragments().iterator();
    L7:
        if (r3.hasNext() == false) goto L13;
        Fragment r0 = r3.next();
        if (r0 == null) goto L7;
        if (r0.performContextItemSelected(r4) == false) goto L7;
        return true;
    L13:
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu r8, MenuInflater r9) {
        int r1 = 0;
        if (this.mCurState >= 1) goto L5;
        return false;
    L5:
        Iterator<Fragment> r0 = this.mFragmentStore.getFragments().iterator();
        ArrayList<Fragment> r3 = null;
        boolean r4 = false;
    L7:
        if (r0.hasNext() == false) goto L18;
        Fragment r5 = r0.next();
        if (r5 == null) goto L7;
        if (isParentMenuVisible(r5) == false) goto L7;
        if (r5.performCreateOptionsMenu(r8, r9) == false) goto L7;
        if (r3 != null) goto L16;
        r3 = new ArrayList();
    L16:
        r3.add(r5);
        r4 = true;
        goto L7
    L18:
        if (this.mCreatedMenus != null) goto L20;
    L27:
        this.mCreatedMenus = r3;
        return r4;
    L20:
        if (r1 >= this.mCreatedMenus.size()) goto L27;
        Fragment r82 = this.mCreatedMenus.get(r1);
        if (r3 != null) goto L24;
    L25:
        r82.onDestroyOptionsMenu();
    L26:
        r1 = r1 + 1;
        goto L20
    L24:
        if (r3.contains(r82) == true) goto L26;
        goto L25
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions(true);
        endAnimatingAwayFragments();
        clearBackStackStateViewModels();
        dispatchStateChange(-1);
        Object r0 = this.mHost;
        if ((r0 instanceof OnTrimMemoryProvider) == false) goto L5;
        ((OnTrimMemoryProvider) r0).removeOnTrimMemoryListener(this.mOnTrimMemoryListener);
    L5:
        Object r02 = this.mHost;
        if ((r02 instanceof OnConfigurationChangedProvider) == false) goto L8;
        ((OnConfigurationChangedProvider) r02).removeOnConfigurationChangedListener(this.mOnConfigurationChangedListener);
    L8:
        Object r03 = this.mHost;
        if ((r03 instanceof OnMultiWindowModeChangedProvider) == false) goto L11;
        ((OnMultiWindowModeChangedProvider) r03).removeOnMultiWindowModeChangedListener(this.mOnMultiWindowModeChangedListener);
    L11:
        Object r04 = this.mHost;
        if ((r04 instanceof OnPictureInPictureModeChangedProvider) == false) goto L14;
        ((OnPictureInPictureModeChangedProvider) r04).removeOnPictureInPictureModeChangedListener(this.mOnPictureInPictureModeChangedListener);
    L14:
        Object r05 = this.mHost;
        if ((r05 instanceof MenuHost) == true) goto L17;
    L19:
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher == null) goto L22;
        this.mOnBackPressedCallback.remove();
        this.mOnBackPressedDispatcher = null;
    L22:
        ActivityResultLauncher<Intent> r06 = this.mStartActivityForResult;
        if (r06 == null) goto L26;
        r06.unregister();
        this.mStartIntentSenderForResult.unregister();
        this.mRequestPermissions.unregister();
        return;
    L26:
        return;
    L17:
        if (this.mParent != null) goto L19;
        ((MenuHost) r05).removeMenuProvider(this.mMenuProvider);
        goto L19
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchLowMemory(boolean r3) {
        if (r3 == true) goto L4;
    L6:
        Iterator<Fragment> r2 = this.mFragmentStore.getFragments().iterator();
    L8:
        if (r2.hasNext() == false) goto L14;
        Fragment r0 = r2.next();
        if (r0 == null) goto L8;
        r0.performLowMemory();
        if (r3 == false) goto L8;
        r0.mChildFragmentManager.dispatchLowMemory(true);
        goto L8
    L14:
        return;
    L4:
        if ((this.mHost instanceof OnTrimMemoryProvider) == false) goto L6;
        throwException(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
        goto L6
    }

    public void dispatchMultiWindowModeChanged(boolean r3, boolean r4) {
        if (r4 == true) goto L4;
    L6:
        Iterator<Fragment> r2 = this.mFragmentStore.getFragments().iterator();
    L8:
        if (r2.hasNext() == false) goto L14;
        Fragment r0 = r2.next();
        if (r0 == null) goto L8;
        r0.performMultiWindowModeChanged(r3);
        if (r4 == false) goto L8;
        r0.mChildFragmentManager.dispatchMultiWindowModeChanged(r3, true);
        goto L8
    L14:
        return;
    L4:
        if ((this.mHost instanceof OnMultiWindowModeChangedProvider) == false) goto L6;
        throwException(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
        goto L6
    }

    public void dispatchOnAttachFragment(Fragment r3) {
        Iterator<FragmentOnAttachListener> r0 = this.mOnAttachListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onAttachFragment(this, r3);
        goto L4
    }

    public void dispatchOnHiddenChanged() {
        Iterator<Fragment> r2 = this.mFragmentStore.getActiveFragments().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Fragment r0 = r2.next();
        if (r0 == null) goto L4;
        r0.onHiddenChanged(r0.isHidden());
        r0.mChildFragmentManager.dispatchOnHiddenChanged();
        goto L4
    }

    public boolean dispatchOptionsItemSelected(MenuItem r4) {
        if (this.mCurState >= 1) goto L5;
        return false;
    L5:
        Iterator<Fragment> r3 = this.mFragmentStore.getFragments().iterator();
    L7:
        if (r3.hasNext() == false) goto L13;
        Fragment r0 = r3.next();
        if (r0 == null) goto L7;
        if (r0.performOptionsItemSelected(r4) == false) goto L7;
        return true;
    L13:
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu r3) {
        if (this.mCurState < 1) goto L11;
        Iterator<Fragment> r2 = this.mFragmentStore.getFragments().iterator();
    L7:
        if (r2.hasNext() == false) goto L17;
        Fragment r0 = r2.next();
        if (r0 == null) goto L7;
        r0.performOptionsMenuClosed(r3);
        goto L7
    L17:
        return;
    }

    public void dispatchPause() {
        dispatchStateChange(5);
    }

    public void dispatchPictureInPictureModeChanged(boolean r3, boolean r4) {
        if (r4 == true) goto L4;
    L6:
        Iterator<Fragment> r2 = this.mFragmentStore.getFragments().iterator();
    L8:
        if (r2.hasNext() == false) goto L14;
        Fragment r0 = r2.next();
        if (r0 == null) goto L8;
        r0.performPictureInPictureModeChanged(r3);
        if (r4 == false) goto L8;
        r0.mChildFragmentManager.dispatchPictureInPictureModeChanged(r3, true);
        goto L8
    L14:
        return;
    L4:
        if ((this.mHost instanceof OnPictureInPictureModeChangedProvider) == false) goto L6;
        throwException(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
        goto L6
    }

    public boolean dispatchPrepareOptionsMenu(Menu r6) {
        boolean r1 = false;
        if (this.mCurState >= 1) goto L5;
        return false;
    L5:
        Iterator<Fragment> r0 = this.mFragmentStore.getFragments().iterator();
    L7:
        if (r0.hasNext() == false) goto L15;
        Fragment r3 = r0.next();
        if (r3 == null) goto L7;
        if (isParentMenuVisible(r3) == false) goto L7;
        if (r3.performPrepareOptionsMenu(r6) == false) goto L7;
        r1 = true;
        goto L7
    L15:
        return r1;
    }

    public void dispatchPrimaryNavigationFragmentChanged() {
        updateOnBackPressedCallbackEnabled();
        dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(7);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(5);
    }

    public void dispatchStop() {
        this.mStopped = true;
        this.mNonConfig.setIsStateSaved(true);
        dispatchStateChange(4);
    }

    public void dispatchViewCreated() {
        dispatchStateChange(2);
    }

    public void dump(String r5, FileDescriptor r6, PrintWriter r7, String[] r8) {
        String r0 = r5 + "    ";
        this.mFragmentStore.dump(r5, r6, r7, r8);
        ArrayList<Fragment> r62 = this.mCreatedMenus;
        int r82 = 0;
        if (r62 == null) goto L9;
        int r63 = r62.size();
        if (r63 <= 0) goto L9;
        r7.print(r5);
        r7.println("Fragments Created Menus:");
        int r1 = 0;
    L7:
        if (r1 >= r63) goto L9;
        Fragment r2 = this.mCreatedMenus.get(r1);
        r7.print(r5);
        r7.print("  #");
        r7.print(r1);
        r7.print(": ");
        r7.println(r2.toString());
        r1 = r1 + 1;
    L9:
        ArrayList<BackStackRecord> r64 = this.mBackStack;
        if (r64 == null) goto L16;
        int r65 = r64.size();
        if (r65 <= 0) goto L16;
        r7.print(r5);
        r7.println("Back Stack:");
        int r12 = 0;
    L14:
        if (r12 >= r65) goto L16;
        BackStackRecord r22 = this.mBackStack.get(r12);
        r7.print(r5);
        r7.print("  #");
        r7.print(r12);
        r7.print(": ");
        r7.println(r22.toString());
        r22.dump(r0, r7);
        r12 = r12 + 1;
    L16:
        r7.print(r5);
        r7.println("Back Stack Index: " + this.mBackStackIndex.get());
        ArrayList<OpGenerator> r66 = this.mPendingActions;
        monitor-enter(r66);
        int r02 = this.mPendingActions.size();     // Catch: Throwable -> L23
        if (r02 <= 0) goto L25;
        r7.print(r5);     // Catch: Throwable -> L23
        r7.println("Pending Actions:");     // Catch: Throwable -> L23
    L21:
        if (r82 >= r02) goto L25;
        OpGenerator r13 = this.mPendingActions.get(r82);     // Catch: Throwable -> L23
        r7.print(r5);     // Catch: Throwable -> L23
        r7.print("  #");     // Catch: Throwable -> L23
        r7.print(r82);     // Catch: Throwable -> L23
        r7.print(": ");     // Catch: Throwable -> L23
        r7.println(r13);     // Catch: Throwable -> L23
        r82 = r82 + 1;     // Catch: Throwable -> L23
    L25:
        monitor-exit(r66);     // Catch: Throwable -> L23
        r7.print(r5);
        r7.println("FragmentManager misc state:");
        r7.print(r5);
        r7.print("  mHost=");
        r7.println(this.mHost);
        r7.print(r5);
        r7.print("  mContainer=");
        r7.println(this.mContainer);
        if (this.mParent == null) goto L29;
        r7.print(r5);
        r7.print("  mParent=");
        r7.println(this.mParent);
    L29:
        r7.print(r5);
        r7.print("  mCurState=");
        r7.print(this.mCurState);
        r7.print(" mStateSaved=");
        r7.print(this.mStateSaved);
        r7.print(" mStopped=");
        r7.print(this.mStopped);
        r7.print(" mDestroyed=");
        r7.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate == false) goto L40;
        r7.print(r5);
        r7.print("  mNeedMenuInvalidate=");
        r7.println(this.mNeedMenuInvalidate);
        return;
    L40:
        return;
    L23:
        th = move-exception;
        throw th;
    }

    public void enqueueAction(OpGenerator r3, boolean r4) {
        if (r4 == false) goto L4;
    L12:
        ArrayList<OpGenerator> r0 = this.mPendingActions;
        monitor-enter(r0);
    L19:
        th = move-exception;
        throw th;
    L15:
        if (this.mHost != null) goto L23;
        if (r4 == false) goto L22;
        monitor-exit(r0);     // Catch: Throwable -> L19
        return;
    L22:
        throw new IllegalStateException("Activity has been destroyed");     // Catch: Throwable -> L19
    L23:
        this.mPendingActions.add(r3);     // Catch: Throwable -> L19
        scheduleCommit();     // Catch: Throwable -> L19
        monitor-exit(r0);     // Catch: Throwable -> L19
        return;
    L4:
        if (this.mHost == null) goto L6;
        checkStateLoss();
        goto L12
    L6:
        if (this.mDestroyed == false) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("FragmentManager has been destroyed");
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("FragmentManager has not been attached to a host.");
    }

    public boolean execPendingActions(boolean r3) {
        ensureExecReady(r3);
        boolean r32 = false;
    L4:
        if (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop) == false) goto L11;
        r32 = true;
        this.mExecutingActions = true;
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);     // Catch: Throwable -> L8
        cleanupExec();
    L8:
        th = move-exception;
        cleanupExec();
        throw th;
    L11:
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return r32;
    }

    public void execSingleAction(OpGenerator r2, boolean r3) {
        if (r3 == true) goto L4;
    L8:
        ensureExecReady(r3);
        if (r2.generateOps(this.mTmpRecords, this.mTmpIsPop) == false) goto L16;
        this.mExecutingActions = true;
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);     // Catch: Throwable -> L13
        cleanupExec();
    L13:
        th = move-exception;
        cleanupExec();
        throw th;
    L16:
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return;
    L4:
        if (this.mHost != null) goto L6;
        return;
    L6:
        if (this.mDestroyed == false) goto L8;
    }

    public boolean executePendingTransactions() {
        boolean r0 = execPendingActions(true);
        forcePostponedTransactions();
        return r0;
    }

    public Fragment findActiveFragment(String r1) {
        return this.mFragmentStore.findActiveFragment(r1);
    }

    public Fragment findFragmentById(int r1) {
        return this.mFragmentStore.findFragmentById(r1);
    }

    public Fragment findFragmentByTag(String r1) {
        return this.mFragmentStore.findFragmentByTag(r1);
    }

    public Fragment findFragmentByWho(String r1) {
        return this.mFragmentStore.findFragmentByWho(r1);
    }

    public int getBackStackEntryCount() {
        ArrayList<BackStackRecord> r0 = this.mBackStack;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.size();
    }

    public FragmentContainer getContainer() {
        return this.mContainer;
    }

    public FragmentFactory getFragmentFactory() {
        FragmentFactory r0 = this.mFragmentFactory;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Fragment r02 = this.mParent;
        if (r02 == null) goto L10;
        return r02.mFragmentManager.getFragmentFactory();
    L10:
        return this.mHostFragmentFactory;
    }

    public List<Fragment> getFragments() {
        return this.mFragmentStore.getFragments();
    }

    public FragmentHostCallback<?> getHost() {
        return this.mHost;
    }

    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this.mLayoutInflaterFactory;
    }

    public FragmentLifecycleCallbacksDispatcher getLifecycleCallbacksDispatcher() {
        return this.mLifecycleCallbacksDispatcher;
    }

    public Fragment getParent() {
        return this.mParent;
    }

    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public SpecialEffectsControllerFactory getSpecialEffectsControllerFactory() {
        SpecialEffectsControllerFactory r0 = this.mSpecialEffectsControllerFactory;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Fragment r02 = this.mParent;
        if (r02 == null) goto L10;
        return r02.mFragmentManager.getSpecialEffectsControllerFactory();
    L10:
        return this.mDefaultSpecialEffectsControllerFactory;
    }

    public FragmentStrictMode.Policy getStrictModePolicy() {
        return this.mStrictModePolicy;
    }

    public ViewModelStore getViewModelStore(Fragment r1) {
        return this.mNonConfig.getViewModelStore(r1);
    }

    public void handleOnBackPressed() {
        execPendingActions(true);
        if (this.mOnBackPressedCallback.isEnabled() == false) goto L6;
        popBackStackImmediate();
        return;
    L6:
        this.mOnBackPressedDispatcher.onBackPressed();
    }

    public void hideFragment(Fragment r3) {
        if (isLoggingEnabled(2) == false) goto L6;
        Log.v("FragmentManager", "hide: " + r3);
    L6:
        if (r3.mHidden == true) goto L9;
        r3.mHidden = true;
        r3.mHiddenChanged = true ^ r3.mHiddenChanged;
        setVisibleRemovingFragment(r3);
        return;
    }

    public void invalidateMenuForFragment(Fragment r2) {
        if (r2.mAdded == true) goto L5;
        return;
    L5:
        if (isMenuAvailable(r2) == false) goto L9;
        this.mNeedMenuInvalidate = true;
        return;
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public boolean isParentHidden(Fragment r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        return r1.isHidden();
    }

    public boolean isParentMenuVisible(Fragment r1) {
        if (r1 != null) goto L6;
        return true;
    L6:
        return r1.isMenuVisible();
    }

    public boolean isPrimaryNavigation(Fragment r4) {
        if (r4 != null) goto L5;
        return true;
    L5:
        FragmentManager r1 = r4.mFragmentManager;
        if (r4.equals(r1.getPrimaryNavigationFragment()) == true) goto L8;
        return false;
    L8:
        if (isPrimaryNavigation(r1.mParent) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public boolean isStateAtLeast(int r1) {
        if (this.mCurState < r1) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isStateSaved() {
        if (this.mStateSaved == false) goto L5;
        return true;
    L5:
        if (this.mStopped == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void launchRequestPermissions(Fragment r2, String[] r3, int r4) {
        if (this.mRequestPermissions == null) goto L6;
        LaunchedFragmentInfo r0 = new LaunchedFragmentInfo(r2.mWho, r4);
        this.mLaunchedFragments.addLast(r0);
        this.mRequestPermissions.launch(r3);
        return;
    L6:
        this.mHost.onRequestPermissionsFromFragment(r2, r3, r4);
    }

    public void launchStartActivityForResult(Fragment r2, @SuppressLint({"UnknownNullness"}) Intent r3, int r4, Bundle r5) {
        if (this.mStartActivityForResult == null) goto L10;
        LaunchedFragmentInfo r0 = new LaunchedFragmentInfo(r2.mWho, r4);
        this.mLaunchedFragments.addLast(r0);
        if (r3 == null) goto L8;
        if (r5 == null) goto L8;
        r3.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", r5);
    L8:
        this.mStartActivityForResult.launch(r3);
        return;
    L10:
        this.mHost.onStartActivityFromFragment(r2, r3, r4, r5);
    }

    public void launchStartIntentSenderForResult(Fragment r10, @SuppressLint({"UnknownNullness"}) IntentSender r11, int r12, Intent r13, int r14, int r15, int r16, Bundle r17) {
        if (this.mStartIntentSenderForResult != null) goto L5;
        this.mHost.onStartIntentSenderFromFragment(r10, r11, r12, r13, r14, r15, r16, r17);
        return;
    L5:
        if (r17 == null) goto L12;
        if (r13 != null) goto L9;
        r13 = new Intent();
        r13.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
    L9:
        if (isLoggingEnabled(2) == false) goto L11;
        Log.v("FragmentManager", "ActivityOptions " + r17 + " were added to fillInIntent " + r13 + " for fragment " + r10);
    L11:
        r13.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", r17);
    L12:
        IntentSenderRequest r112 = new IntentSenderRequest.Builder(r11).setFillInIntent(r13).setFlags(r15, r14).build();
        this.mLaunchedFragments.addLast(new LaunchedFragmentInfo(r10.mWho, r12));
        if (isLoggingEnabled(2) == false) goto L15;
        Log.v("FragmentManager", "Fragment " + r10 + "is launching an IntentSender for result ");
    L15:
        this.mStartIntentSenderForResult.launch(r112);
    }

    public void moveToState(int r2, boolean r3) {
        if (this.mHost == null) goto L5;
    L9:
        if (r3 == false) goto L11;
    L13:
        this.mCurState = r2;
        this.mFragmentStore.moveToExpectedState();
        startPendingDeferredFragments();
        if (this.mNeedMenuInvalidate == false) goto L21;
        FragmentHostCallback<?> r22 = this.mHost;
        if (r22 != null) goto L18;
        return;
    L18:
        if (this.mCurState != 7) goto L23;
        r22.onSupportInvalidateOptionsMenu();
        this.mNeedMenuInvalidate = false;
        return;
    L23:
        return;
    L21:
        return;
    L11:
        if (r2 != this.mCurState) goto L13;
        return;
    L5:
        if (r2 == (-1)) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("No activity");
    }

    public void noteStateNotSaved() {
        if (this.mHost == null) goto L11;
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        Iterator<Fragment> r2 = this.mFragmentStore.getFragments().iterator();
    L7:
        if (r2.hasNext() == false) goto L17;
        Fragment r0 = r2.next();
        if (r0 == null) goto L7;
        r0.noteStateNotSaved();
        goto L7
    L17:
        return;
    }

    public void onContainerAvailable(FragmentContainerView r5) {
        Iterator<FragmentStateManager> r4 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
    L4:
        if (r4.hasNext() == false) goto L12;
        FragmentStateManager r0 = r4.next();
        Fragment r1 = r0.getFragment();
        if (r1.mContainerId != r5.getId()) goto L4;
        View r2 = r1.mView;
        if (r2 == null) goto L4;
        if (r2.getParent() != null) goto L4;
        r1.mContainer = r5;
        r0.addViewToContainer();
        goto L4
    }

    public void performPendingDeferredStart(FragmentStateManager r3) {
        Fragment r0 = r3.getFragment();
        if (r0.mDeferStart == true) goto L5;
        return;
    L5:
        if (this.mExecutingActions == false) goto L8;
        this.mHavePendingDeferredStart = true;
        return;
    L8:
        r0.mDeferStart = false;
        r3.moveToExpectedState();
    }

    public void popBackStack(int r3, int r4, boolean r5) {
        if (r3 < 0) goto L5;
        enqueueAction(new PopBackStackState(this, null, r3, r4), r5);
        return;
    L5:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Bad id: ", r3);
    }

    public boolean popBackStackImmediate() {
        return popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackImmediate(int r2, int r3) {
        if (r2 >= 0) goto L4;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Bad id: ", r2);
        return false;
    L4:
        return popBackStackImmediate(null, r2, r3);
    }

    public boolean popBackStackState(ArrayList<BackStackRecord> r3, ArrayList<Boolean> r4, String r5, int r6, int r7) {
        if ((r7 & 1) == 0) goto L5;
        boolean r72 = true;
    L6:
        int r52 = findBackStackIndex(r5, r6, r72);
        if (r52 >= 0) goto L9;
        return false;
    L9:
        int r62 = this.mBackStack.size() - 1;
    L10:
        if (r62 < r52) goto L12;
        r3.add(this.mBackStack.remove(r62));
        r4.add(Boolean.TRUE);
        r62 = r62 - 1;
        goto L10
    L12:
        return true;
    L5:
        r72 = false;
        goto L6
    }

    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks r1, boolean r2) {
        this.mLifecycleCallbacksDispatcher.registerFragmentLifecycleCallbacks(r1, r2);
    }

    public void removeFragment(Fragment r3) {
        if (isLoggingEnabled(2) == false) goto L5;
        Log.v("FragmentManager", "remove: " + r3 + " nesting=" + r3.mBackStackNesting);
    L5:
        boolean r0 = r3.isInBackStack();
        if (r3.mDetached == false) goto L10;
        if (r0 == false) goto L10;
        return;
    L10:
        this.mFragmentStore.removeFragment(r3);
        if (isMenuAvailable(r3) == false) goto L13;
        this.mNeedMenuInvalidate = true;
    L13:
        r3.mRemoving = true;
        setVisibleRemovingFragment(r3);
    }

    public void removeRetainedFragment(Fragment r1) {
        this.mNonConfig.removeRetainedFragment(r1);
    }

    public void restoreSaveStateInternal(Parcelable r19) {
        if (r19 == null) goto L97;
        Bundle r1 = (Bundle) r19;
        Iterator<String> r2 = r1.keySet().iterator();
    L7:
        if (r2.hasNext() == false) goto L13;
        String r3 = r2.next();
        if (r3.startsWith("result_") == false) goto L7;
        Bundle r4 = r1.getBundle(r3);
        if (r4 == null) goto L7;
        r4.setClassLoader(this.mHost.getContext().getClassLoader());
        this.mResults.put(r3.substring(7), r4);
        goto L7
    L13:
        ArrayList<FragmentState> r22 = new ArrayList();
        Iterator<String> r32 = r1.keySet().iterator();
    L15:
        if (r32.hasNext() == false) goto L21;
        String r42 = r32.next();
        if (r42.startsWith("fragment_") == false) goto L15;
        Bundle r43 = r1.getBundle(r42);
        if (r43 == null) goto L15;
        r43.setClassLoader(this.mHost.getContext().getClassLoader());
        r22.add((FragmentState) r43.getParcelable("state"));
        goto L15
    L21:
        this.mFragmentStore.restoreSaveState(r22);
        FragmentManagerState r12 = (FragmentManagerState) r1.getParcelable("state");
        if (r12 != null) goto L24;
        return;
    L24:
        this.mFragmentStore.resetActiveFragments();
        ArrayList<String> r23 = r12.mActive;
        int r33 = r23.size();
        int r44 = 0;
        int r5 = 0;
    L26:
        if (r5 >= r33) goto L40;
        String r10 = r23.get(r5);
        r5 = r5 + 1;
        FragmentState r7 = this.mFragmentStore.setSavedState(r10, null);
        if (r7 == null) goto L26;
        Fragment r102 = this.mNonConfig.findRetainedFragmentByWho(r7.mWho);
        if (r102 != null) goto L32;
        FragmentStateManager r11 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, this.mHost.getContext().getClassLoader(), getFragmentFactory(), r7);
    L36:
        Fragment r72 = r11.getFragment();
        r72.mFragmentManager = this;
        if (isLoggingEnabled(2) == false) goto L39;
        Log.v("FragmentManager", "restoreSaveState: active (" + r72.mWho + "): " + r72);
    L39:
        r11.restoreState(this.mHost.getContext().getClassLoader());
        this.mFragmentStore.makeActive(r11);
        r11.setFragmentManagerState(this.mCurState);
        goto L26
    L32:
        if (isLoggingEnabled(2) == false) goto L34;
        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + r102);
    L34:
        r11 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, r102, r7);
        goto L36
    L40:
        Iterator<Fragment> r24 = this.mNonConfig.getRetainedFragments().iterator();
    L42:
        if (r24.hasNext() == false) goto L49;
        Fragment r34 = r24.next();
        if (this.mFragmentStore.containsActiveFragment(r34.mWho) == true) goto L42;
        if (isLoggingEnabled(2) == false) goto L48;
        Log.v("FragmentManager", "Discarding retained Fragment " + r34 + " that was not found in the set of active Fragments " + r12.mActive);
    L48:
        this.mNonConfig.removeRetainedFragment(r34);
        r34.mFragmentManager = this;
        FragmentStateManager r52 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, r34);
        r52.setFragmentManagerState(1);
        r52.moveToExpectedState();
        r34.mRemoving = true;
        r52.moveToExpectedState();
        goto L42
    L49:
        this.mFragmentStore.restoreAddedFragments(r12.mAdded);
        if (r12.mBackStack == null) goto L58;
        this.mBackStack = new ArrayList(r12.mBackStack.length);
        int r25 = 0;
    L52:
        BackStackRecordState[] r35 = r12.mBackStack;
        if (r25 >= r35.length) goto L59;
        BackStackRecord r36 = r35[r25].instantiate(this);
        if (isLoggingEnabled(2) == false) goto L57;
        Log.v("FragmentManager", "restoreAllState: back stack #" + r25 + " (index " + r36.mIndex + "): " + r36);
        PrintWriter r73 = new PrintWriter(new LogWriter("FragmentManager"));
        r36.dump("  ", r73, false);
        r73.close();
    L57:
        this.mBackStack.add(r36);
        r25 = r25 + 1;
    L59:
        this.mBackStackIndex.set(r12.mBackStackIndex);
        String r26 = r12.mPrimaryNavActiveWho;
        if (r26 == null) goto L62;
        Fragment r27 = findActiveFragment(r26);
        this.mPrimaryNav = r27;
        dispatchParentPrimaryNavigationFragmentChanged(r27);
    L62:
        ArrayList<String> r28 = r12.mBackStackStateKeys;
        if (r28 != null) goto L65;
    L67:
        this.mLaunchedFragments = new ArrayDeque(r12.mLaunchedFragments);
        return;
    L65:
        if (r44 >= r28.size()) goto L67;
        this.mBackStackStates.put(r28.get(r44), r12.mBackStackStates.get(r44));
        r44 = r44 + 1;
        goto L65
    L58:
        this.mBackStack = null;
        goto L59
    }

    public Bundle saveAllStateInternal() {
        Bundle r0 = new Bundle();
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions(true);
        this.mStateSaved = true;
        this.mNonConfig.setIsStateSaved(true);
        ArrayList<String> r1 = this.mFragmentStore.saveActiveFragments();
        ArrayList<FragmentState> r2 = this.mFragmentStore.getAllSavedState();
        if (r2.isEmpty() == true) goto L5;
        ArrayList<String> r3 = this.mFragmentStore.saveAddedFragments();
        ArrayList<BackStackRecord> r6 = this.mBackStack;
        int r7 = 0;
        if (r6 == null) goto L18;
        int r62 = r6.size();
        if (r62 <= 0) goto L18;
        BackStackRecordState[] r8 = new BackStackRecordState[r62];
        int r9 = 0;
    L13:
        if (r9 >= r62) goto L19;
        r8[r9] = new BackStackRecordState(this.mBackStack.get(r9));
        if (isLoggingEnabled(2) == false) goto L17;
        Log.v("FragmentManager", "saveAllState: adding back stack #" + r9 + ": " + this.mBackStack.get(r9));
    L17:
        r9 = r9 + 1;
    L19:
        FragmentManagerState r4 = new FragmentManagerState();
        r4.mActive = r1;
        r4.mAdded = r3;
        r4.mBackStack = r8;
        r4.mBackStackIndex = this.mBackStackIndex.get();
        Fragment r12 = this.mPrimaryNav;
        if (r12 == null) goto L22;
        r4.mPrimaryNavActiveWho = r12.mWho;
    L22:
        r4.mBackStackStateKeys.addAll(this.mBackStackStates.keySet());
        r4.mBackStackStates.addAll(this.mBackStackStates.values());
        r4.mLaunchedFragments = new ArrayList(this.mLaunchedFragments);
        r0.putParcelable("state", r4);
        Iterator<String> r32 = this.mResults.keySet().iterator();
    L24:
        if (r32.hasNext() == false) goto L26;
        String r42 = r32.next();
        r0.putBundle("result_" + r42, this.mResults.get(r42));
        goto L24
    L26:
        int r122 = r2.size();
    L27:
        if (r7 >= r122) goto L29;
        FragmentState r33 = r2.get(r7);
        r7 = r7 + 1;
        FragmentState r34 = r33;
        Bundle r43 = new Bundle();
        r43.putParcelable("state", r34);
        r0.putBundle("fragment_" + r34.mWho, r43);
    L29:
        return r0;
    L18:
        r8 = null;
        goto L19
    L5:
        if (isLoggingEnabled(2) == false) goto L29;
        Log.v("FragmentManager", "saveAllState: no fragments!");
        return r0;
    }

    public void scheduleCommit() {
        ArrayList<OpGenerator> r0 = this.mPendingActions;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mPendingActions.size() != 1) goto L9;
        this.mHost.getHandler().removeCallbacks(this.mExecCommit);     // Catch: Throwable -> L7
        this.mHost.getHandler().post(this.mExecCommit);     // Catch: Throwable -> L7
        updateOnBackPressedCallbackEnabled();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    public void setExitAnimationOrder(Fragment r1, boolean r2) {
        ViewGroup r0 = getFragmentContainer(r1);
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof FragmentContainerView) == false) goto L9;
        ((FragmentContainerView) r0).setDrawDisappearingViewsLast(!r2);
        return;
    }

    public void setMaxLifecycle(Fragment r2, Lifecycle.State r3) {
        if (r2.equals(findActiveFragment(r2.mWho)) == true) goto L5;
    L10:
        Utils$$ExternalSyntheticBUOutline2.m("Fragment ", r2, " is not an active fragment of FragmentManager ", this);
        return;
    L5:
        if (r2.mHost != null) goto L7;
    L8:
        r2.mMaxState = r3;
        return;
    L7:
        if (r2.mFragmentManager != this) goto L10;
        goto L8
    }

    public void setPrimaryNavigationFragment(Fragment r3) {
        if (r3 != null) goto L4;
    L12:
        Fragment r0 = this.mPrimaryNav;
        this.mPrimaryNav = r3;
        dispatchParentPrimaryNavigationFragmentChanged(r0);
        dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
        return;
    L4:
        if (r3.equals(findActiveFragment(r3.mWho)) == true) goto L6;
    L10:
        Utils$$ExternalSyntheticBUOutline2.m("Fragment ", r3, " is not an active fragment of FragmentManager ", this);
        return;
    L6:
        if (r3.mHost == null) goto L12;
        if (r3.mFragmentManager != this) goto L10;
        goto L10
    }

    public void showFragment(Fragment r2) {
        if (isLoggingEnabled(2) == false) goto L6;
        Log.v("FragmentManager", "show: " + r2);
    L6:
        if (r2.mHidden == false) goto L9;
        r2.mHidden = false;
        r2.mHiddenChanged = !r2.mHiddenChanged;
        return;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(128);
        r0.append("FragmentManager{");
        r0.append(Integer.toHexString(System.identityHashCode(this)));
        r0.append(" in ");
        Fragment r1 = this.mParent;
        if (r1 == null) goto L5;
        r0.append(r1.getClass().getSimpleName());
        r0.append("{");
        r0.append(Integer.toHexString(System.identityHashCode(this.mParent)));
        r0.append("}");
    L9:
        r0.append("}}");
        return r0.toString();
    L5:
        FragmentHostCallback<?> r12 = this.mHost;
        if (r12 == null) goto L8;
        r0.append(r12.getClass().getSimpleName());
        r0.append("{");
        r0.append(Integer.toHexString(System.identityHashCode(this.mHost)));
        r0.append("}");
        goto L9
    L8:
        r0.append("null");
        goto L9
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks r1) {
        this.mLifecycleCallbacksDispatcher.unregisterFragmentLifecycleCallbacks(r1);
    }
}
