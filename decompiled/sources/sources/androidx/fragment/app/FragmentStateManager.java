package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.fragment.R$id;
import androidx.fragment.app.SpecialEffectsController;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import com.sun.jna.Native$$ExternalSyntheticBUOutline4;
import java.util.Iterator;

/* loaded from: classes3.dex */
class FragmentStateManager {
    private final FragmentLifecycleCallbacksDispatcher mDispatcher;
    private final Fragment mFragment;
    private int mFragmentManagerState;
    private final FragmentStore mFragmentStore;
    private boolean mMovingToState;

    /* renamed from: androidx.fragment.app.FragmentStateManager$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ FragmentStateManager this$0;
        final /* synthetic */ View val$fragmentView;

        public AnonymousClass1(FragmentStateManager r1, View r2) {
            this.this$0 = r1;
            this.val$fragmentView = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$fragmentView.removeOnAttachStateChangeListener(this);
            ViewCompat.requestApplyInsets(this.val$fragmentView);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
        }
    }

    /* renamed from: androidx.fragment.app.FragmentStateManager$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State = null;

        static {
            int[] r0 = new int[Lifecycle.State.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$State = r0;
            r0[Lifecycle.State.RESUMED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.STARTED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.CREATED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.INITIALIZED.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher r2, FragmentStore r3, Fragment r4) {
        this.mMovingToState = false;
        this.mFragmentManagerState = -1;
        this.mDispatcher = r2;
        this.mFragmentStore = r3;
        this.mFragment = r4;
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher r3, FragmentStore r4, Fragment r5, FragmentState r6) {
        this.mMovingToState = false;
        this.mFragmentManagerState = -1;
        this.mDispatcher = r3;
        this.mFragmentStore = r4;
        this.mFragment = r5;
        r5.mSavedViewState = null;
        r5.mSavedViewRegistryState = null;
        r5.mBackStackNesting = 0;
        r5.mInLayout = false;
        r5.mAdded = false;
        Fragment r32 = r5.mTarget;
        if (r32 == null) goto L5;
        String r33 = r32.mWho;
    L6:
        r5.mTargetWho = r33;
        r5.mTarget = null;
        Bundle r2 = r6.mSavedFragmentState;
        if (r2 == null) goto L10;
        r5.mSavedFragmentState = r2;
        return;
    L10:
        r5.mSavedFragmentState = new Bundle();
        return;
    L5:
        r33 = null;
        goto L6
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher r2, FragmentStore r3, ClassLoader r4, FragmentFactory r5, FragmentState r6) {
        this.mMovingToState = false;
        this.mFragmentManagerState = -1;
        this.mDispatcher = r2;
        this.mFragmentStore = r3;
        Fragment r22 = r6.instantiate(r5, r4);
        this.mFragment = r22;
        if (FragmentManager.isLoggingEnabled(2) == false) goto L6;
        Log.v("FragmentManager", "Instantiated fragment " + r22);
        return;
    }

    private boolean isFragmentViewChild(View r3) {
        if (r3 != this.mFragment.mView) goto L5;
        return true;
    L5:
        ViewParent r32 = r3.getParent();
    L6:
        if (r32 == null) goto L11;
        if (r32 == this.mFragment.mView) goto L9;
        r32 = r32.getParent();
        goto L6
    L9:
        return true;
    L11:
        return false;
    }

    private Bundle saveBasicState() {
        Bundle r0 = new Bundle();
        this.mFragment.performSaveInstanceState(r0);
        this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, r0, false);
        if (r0.isEmpty() == false) goto L6;
        r0 = null;
    L6:
        if (this.mFragment.mView == null) goto L9;
        saveViewState();
    L9:
        if (this.mFragment.mSavedViewState == null) goto L14;
        if (r0 != null) goto L12;
        r0 = new Bundle();
    L12:
        r0.putSparseParcelableArray("android:view_state", this.mFragment.mSavedViewState);
    L14:
        if (this.mFragment.mSavedViewRegistryState == null) goto L19;
        if (r0 != null) goto L17;
        r0 = new Bundle();
    L17:
        r0.putBundle("android:view_registry_state", this.mFragment.mSavedViewRegistryState);
    L19:
        if (this.mFragment.mUserVisibleHint == true) goto L23;
        if (r0 != null) goto L22;
        r0 = new Bundle();
    L22:
        r0.putBoolean("android:user_visible_hint", this.mFragment.mUserVisibleHint);
    L23:
        return r0;
    }

    public void activityCreated() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.mFragment);
    L5:
        Fragment r0 = this.mFragment;
        r0.performActivityCreated(r0.mSavedFragmentState);
        FragmentLifecycleCallbacksDispatcher r02 = this.mDispatcher;
        Fragment r3 = this.mFragment;
        r02.dispatchOnFragmentActivityCreated(r3, r3.mSavedFragmentState, false);
    }

    public void addViewToContainer() {
        int r0 = this.mFragmentStore.findFragmentIndexInContainer(this.mFragment);
        Fragment r2 = this.mFragment;
        r2.mContainer.addView(r2.mView, r0);
    }

    public void attach() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "moveto ATTACHED: " + this.mFragment);
    L5:
        Fragment r0 = this.mFragment;
        Fragment r1 = r0.mTarget;
        FragmentStateManager r2 = null;
        if (r1 == null) goto L12;
        FragmentStateManager r02 = this.mFragmentStore.getFragmentStateManager(r1.mWho);
        Fragment r12 = this.mFragment;
        if (r02 == null) goto L10;
        r12.mTargetWho = r12.mTarget.mWho;
        r12.mTarget = null;
        r2 = r02;
    L19:
        if (r2 == null) goto L21;
        r2.moveToExpectedState();
    L21:
        Fragment r03 = this.mFragment;
        r03.mHost = r03.mFragmentManager.getHost();
        Fragment r04 = this.mFragment;
        r04.mParentFragment = r04.mFragmentManager.getParent();
        this.mDispatcher.dispatchOnFragmentPreAttached(this.mFragment, false);
        this.mFragment.performAttach();
        this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, false);
        return;
    L10:
        StringBuilder r22 = new StringBuilder("Fragment ");
        r22.append(r12);
        Fragment r6 = this.mFragment.mTarget;
        r22.append(" declared target fragment ");
        r22.append(r6);
        r22.append(" that does not belong to this FragmentManager!");
        throw new IllegalStateException(r22.toString());
    L12:
        String r05 = r0.mTargetWho;
        if (r05 == null) goto L19;
        r2 = this.mFragmentStore.getFragmentStateManager(r05);
        if (r2 != null) goto L19;
        StringBuilder r13 = new StringBuilder("Fragment ");
        r13.append(this.mFragment);
        String r62 = this.mFragment.mTargetWho;
        r13.append(" declared target fragment ");
        r13.append(r62);
        r13.append(" that does not belong to this FragmentManager!");
        throw new IllegalStateException(r13.toString());
    }

    public int computeExpectedState() {
        Fragment r0 = this.mFragment;
        if (r0.mFragmentManager == null) goto L5;
        int r1 = this.mFragmentManagerState;
        int r02 = AnonymousClass2.$SwitchMap$androidx$lifecycle$Lifecycle$State[r0.mMaxState.ordinal()];
        if (r02 == 1) goto L15;
        if (r02 == 2) goto L14;
        if (r02 == 3) goto L13;
        if (r02 == 4) goto L12;
        r1 = Math.min(r1, -1);
        goto L15
    L12:
        r1 = Math.min(r1, 0);
        goto L15
    L13:
        r1 = Math.min(r1, 1);
        goto L15
    L14:
        r1 = Math.min(r1, 5);
    L15:
        Fragment r03 = this.mFragment;
        if (r03.mFromLayout == false) goto L28;
        boolean r8 = r03.mInLayout;
        int r9 = this.mFragmentManagerState;
        if (r8 == false) goto L24;
        r1 = Math.max(r9, 2);
        View r04 = this.mFragment.mView;
        if (r04 == null) goto L28;
        if (r04.getParent() != null) goto L28;
        r1 = Math.min(r1, 2);
        goto L28
    L24:
        if (r9 >= 4) goto L26;
        r1 = Math.min(r1, r03.mState);
        goto L28
    L26:
        r1 = Math.min(r1, 1);
    L28:
        if (this.mFragment.mAdded == true) goto L30;
        r1 = Math.min(r1, 1);
    L30:
        Fragment r05 = this.mFragment;
        ViewGroup r82 = r05.mContainer;
        if (r82 == null) goto L33;
        SpecialEffectsController.Operation.LifecycleImpact r06 = SpecialEffectsController.getOrCreateController(r82, r05.getParentFragmentManager()).getAwaitingCompletionLifecycleImpact(this);
    L35:
        if (r06 != SpecialEffectsController.Operation.LifecycleImpact.ADDING) goto L38;
        r1 = Math.min(r1, 6);
    L46:
        Fragment r07 = this.mFragment;
        if (r07.mDeferStart == false) goto L52;
        if (r07.mState >= 5) goto L52;
        r1 = Math.min(r1, 4);
    L52:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L54;
        Log.v("FragmentManager", "computeExpectedState() of " + r1 + " for " + this.mFragment);
    L54:
        return r1;
    L38:
        if (r06 != SpecialEffectsController.Operation.LifecycleImpact.REMOVING) goto L40;
        r1 = Math.max(r1, 3);
        goto L46
    L40:
        Fragment r08 = this.mFragment;
        if (r08.mRemoving == false) goto L46;
        if (r08.isInBackStack() == false) goto L45;
        r1 = Math.min(r1, 1);
        goto L46
    L45:
        r1 = Math.min(r1, -1);
        goto L46
    L33:
        r06 = null;
        goto L35
    L5:
        return r0.mState;
    }

    public void create() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "moveto CREATED: " + this.mFragment);
    L5:
        Fragment r0 = this.mFragment;
        if (r0.mIsCreated == true) goto L9;
        this.mDispatcher.dispatchOnFragmentPreCreated(r0, r0.mSavedFragmentState, false);
        Fragment r02 = this.mFragment;
        r02.performCreate(r02.mSavedFragmentState);
        FragmentLifecycleCallbacksDispatcher r03 = this.mDispatcher;
        Fragment r4 = this.mFragment;
        r03.dispatchOnFragmentCreated(r4, r4.mSavedFragmentState, false);
        return;
    L9:
        r0.restoreChildFragmentState(r0.mSavedFragmentState);
        this.mFragment.mState = 1;
    }

    public void createView() {
        if (this.mFragment.mFromLayout == false) goto L6;
        return;
    L6:
        if (FragmentManager.isLoggingEnabled(3) == false) goto L8;
        Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
    L8:
        Fragment r0 = this.mFragment;
        LayoutInflater r02 = r0.performGetLayoutInflater(r0.mSavedFragmentState);
        Fragment r2 = this.mFragment;
        ViewGroup r3 = r2.mContainer;
        if (r3 != null) goto L31;
        int r32 = r2.mContainerId;
        if (r32 != 0) goto L14;
        r3 = null;
        goto L31
    L14:
        if (r32 == (-1)) goto L28;
        r3 = (ViewGroup) r2.mFragmentManager.getContainer().onFindViewById(this.mFragment.mContainerId);
        if (r3 != null) goto L26;
        Fragment r22 = this.mFragment;
        if (r22.mRestored == true) goto L31;
        String r03 = r22.getResources().getResourceName(this.mFragment.mContainerId);     // Catch: Resources.NotFoundException -> L22
    L23:
        Native$$ExternalSyntheticBUOutline4.m("No view found for id 0x", Integer.toHexString(this.mFragment.mContainerId), " (", r03, ") for fragment ", this.mFragment);
        return;
    L22:
        r03 = "unknown";
        goto L23
    L26:
        if ((r3 instanceof FragmentContainerView) == true) goto L31;
        FragmentStrictMode.onWrongFragmentContainer(this.mFragment, r3);
        goto L31
    L28:
        Native$$ExternalSyntheticBUOutline2.m("Cannot create fragment ", this.mFragment, " for a container view with no id");
        return;
    L31:
        Fragment r23 = this.mFragment;
        r23.mContainer = r3;
        r23.performCreateView(r02, r3, r23.mSavedFragmentState);
        View r04 = this.mFragment.mView;
        if (r04 == null) goto L52;
        r04.setSaveFromParentEnabled(false);
        Fragment r05 = this.mFragment;
        r05.mView.setTag(R$id.fragment_container_view_tag, r05);
        if (r3 == null) goto L36;
        addViewToContainer();
    L36:
        Fragment r06 = this.mFragment;
        if (r06.mHidden == false) goto L39;
        r06.mView.setVisibility(8);
    L39:
        boolean r07 = ViewCompat.isAttachedToWindow(this.mFragment.mView);
        Fragment r33 = this.mFragment;
        if (r07 == false) goto L42;
        ViewCompat.requestApplyInsets(r33.mView);
    L43:
        this.mFragment.performViewCreated();
        FragmentLifecycleCallbacksDispatcher r08 = this.mDispatcher;
        Fragment r34 = this.mFragment;
        r08.dispatchOnFragmentViewCreated(r34, r34.mView, r34.mSavedFragmentState, false);
        int r09 = this.mFragment.mView.getVisibility();
        this.mFragment.setPostOnViewCreatedAlpha(this.mFragment.mView.getAlpha());
        Fragment r35 = this.mFragment;
        if (r35.mContainer == null) goto L52;
        if (r09 != 0) goto L52;
        View r010 = r35.mView.findFocus();
        if (r010 == null) goto L51;
        this.mFragment.setFocusedView(r010);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L51;
        Log.v("FragmentManager", "requestFocus: Saved focused view " + r010 + " for Fragment " + this.mFragment);
    L51:
        this.mFragment.mView.setAlpha(0.0f);
        goto L52
    L42:
        View r011 = r33.mView;
        r011.addOnAttachStateChangeListener(new AnonymousClass1(this, r011));
    L52:
        this.mFragment.mState = 2;
    }

    public void destroy() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "movefrom CREATED: " + this.mFragment);
    L5:
        Fragment r0 = this.mFragment;
        boolean r3 = true;
        if (r0.mRemoving == true) goto L8;
    L10:
        boolean r02 = false;
    L12:
        if (r02 == false) goto L16;
        Fragment r4 = this.mFragment;
        if (r4.mBeingSaved == true) goto L16;
        this.mFragmentStore.setSavedState(r4.mWho, null);
    L16:
        if (r02 == false) goto L18;
    L29:
        FragmentHostCallback<?> r42 = this.mFragment.mHost;
        if ((r42 instanceof ViewModelStoreOwner) == false) goto L33;
        r3 = this.mFragmentStore.getNonConfig().isCleared();
    L35:
        if (r02 == true) goto L37;
    L38:
        if (r3 == false) goto L40;
    L39:
        this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment);
    L40:
        this.mFragment.performDestroy();
        this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
        Iterator<FragmentStateManager> r03 = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
    L42:
        if (r03.hasNext() == false) goto L48;
        FragmentStateManager r2 = r03.next();
        if (r2 == null) goto L42;
        Fragment r22 = r2.getFragment();
        if (this.mFragment.mWho.equals(r22.mTargetWho) == false) goto L42;
        r22.mTarget = this.mFragment;
        r22.mTargetWho = null;
        goto L42
    L48:
        Fragment r04 = this.mFragment;
        String r1 = r04.mTargetWho;
        if (r1 == null) goto L51;
        r04.mTarget = this.mFragmentStore.findActiveFragment(r1);
    L51:
        this.mFragmentStore.makeInactive(this);
        return;
    L37:
        if (this.mFragment.mBeingSaved == false) goto L39;
    L33:
        if ((r42.getContext() instanceof Activity) == false) goto L35;
        r3 = true ^ ((Activity) r42.getContext()).isChangingConfigurations();
        goto L35
    L18:
        if (this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment) == true) goto L29;
        String r05 = this.mFragment.mTargetWho;
        if (r05 == null) goto L27;
        Fragment r06 = this.mFragmentStore.findActiveFragment(r05);
        if (r06 == null) goto L27;
        if (r06.mRetainInstance == false) goto L27;
        this.mFragment.mTarget = r06;
    L27:
        this.mFragment.mState = 0;
        return;
    L8:
        if (r0.isInBackStack() == true) goto L10;
        r02 = true;
        goto L12
    }

    public void destroyFragmentView() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.mFragment);
    L5:
        Fragment r0 = this.mFragment;
        ViewGroup r1 = r0.mContainer;
        if (r1 == null) goto L10;
        View r02 = r0.mView;
        if (r02 == null) goto L10;
        r1.removeView(r02);
    L10:
        this.mFragment.performDestroyView();
        this.mDispatcher.dispatchOnFragmentViewDestroyed(this.mFragment, false);
        Fragment r03 = this.mFragment;
        r03.mContainer = null;
        r03.mView = null;
        r03.mViewLifecycleOwner = null;
        r03.mViewLifecycleOwnerLiveData.setValue(null);
        this.mFragment.mInLayout = false;
    }

    public void detach() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "movefrom ATTACHED: " + this.mFragment);
    L5:
        this.mFragment.performDetach();
        this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
        Fragment r1 = this.mFragment;
        r1.mState = -1;
        r1.mHost = null;
        r1.mParentFragment = null;
        r1.mFragmentManager = null;
        if (r1.mRemoving == false) goto L11;
        if (r1.isInBackStack() == true) goto L11;
    L13:
        if (FragmentManager.isLoggingEnabled(3) == false) goto L15;
        Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);
    L15:
        this.mFragment.initState();
        return;
    L11:
        if (this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment) == true) goto L13;
    }

    public void ensureInflatedView() {
        Fragment r0 = this.mFragment;
        if (r0.mFromLayout == true) goto L5;
        return;
    L5:
        if (r0.mInLayout == true) goto L7;
        return;
    L7:
        if (r0.mPerformedCreateView == false) goto L9;
        return;
    L9:
        if (FragmentManager.isLoggingEnabled(3) == false) goto L11;
        Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
    L11:
        Fragment r02 = this.mFragment;
        r02.performCreateView(r02.performGetLayoutInflater(r02.mSavedFragmentState), null, this.mFragment.mSavedFragmentState);
        View r03 = this.mFragment.mView;
        if (r03 == null) goto L21;
        r03.setSaveFromParentEnabled(false);
        Fragment r04 = this.mFragment;
        r04.mView.setTag(R$id.fragment_container_view_tag, r04);
        Fragment r05 = this.mFragment;
        if (r05.mHidden == false) goto L16;
        r05.mView.setVisibility(8);
    L16:
        this.mFragment.performViewCreated();
        FragmentLifecycleCallbacksDispatcher r06 = this.mDispatcher;
        Fragment r2 = this.mFragment;
        r06.dispatchOnFragmentViewCreated(r2, r2.mView, r2.mSavedFragmentState, false);
        this.mFragment.mState = 2;
        return;
    }

    public Fragment getFragment() {
        return this.mFragment;
    }

    public void moveToExpectedState() {
        if (this.mMovingToState == false) goto L98;
        if (FragmentManager.isLoggingEnabled(2) == false) goto L119;
        Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + getFragment());
        return;
    L119:
        return;
    L98:
        this.mMovingToState = true;     // Catch: Throwable -> L17
        boolean r4 = false;
    L10:
        int r5 = computeExpectedState();     // Catch: Throwable -> L17
        Fragment r6 = this.mFragment;     // Catch: Throwable -> L17
        int r7 = r6.mState;     // Catch: Throwable -> L17
        if (r5 == r7) goto L64;
        if (r5 <= r7) goto L32;
        switch((r7 + 1)) {
            case 0: goto L30;
            case 1: goto L29;
            case 2: goto L28;
            case 3: goto L27;
            case 4: goto L22;
            case 5: goto L20;
            case 6: goto L19;
            case 7: goto L16;
            default: goto L63;
        };     // Catch: Throwable -> L17
    L16:
        resume();     // Catch: Throwable -> L17
        goto L63
    L19:
        r6.mState = 6;     // Catch: Throwable -> L17
        goto L63
    L20:
        start();     // Catch: Throwable -> L17
        goto L63
    L27:
        activityCreated();     // Catch: Throwable -> L17
        goto L63
    L28:
        ensureInflatedView();     // Catch: Throwable -> L17
        createView();     // Catch: Throwable -> L17
        goto L63
    L29:
        create();     // Catch: Throwable -> L17
        goto L63
    L30:
        attach();     // Catch: Throwable -> L17
    L63:
        r4 = true;
        goto L10
    L22:
        if (r6.mView == null) goto L26;
        ViewGroup r42 = r6.mContainer;     // Catch: Throwable -> L17
        if (r42 == null) goto L26;
        SpecialEffectsController.getOrCreateController(r42, r6.getParentFragmentManager()).enqueueAdd(SpecialEffectsController.Operation.State.from(this.mFragment.mView.getVisibility()), this);     // Catch: Throwable -> L17
    L26:
        this.mFragment.mState = 4;     // Catch: Throwable -> L17
        goto L63
    L32:
        switch((r7 - 1)) {
            case -1: goto L62;
            case 0: goto L57;
            case 1: goto L55;
            case 2: goto L54;
            case 3: goto L38;
            case 4: goto L36;
            case 5: goto L35;
            case 6: goto L34;
            default: goto L63;
        };     // Catch: Throwable -> L17
    L34:
        pause();     // Catch: Throwable -> L17
        goto L63
    L35:
        r6.mState = 5;     // Catch: Throwable -> L17
        goto L63
    L36:
        stop();     // Catch: Throwable -> L17
        goto L63
    L54:
        r6.mInLayout = false;     // Catch: Throwable -> L17
        r6.mState = 2;     // Catch: Throwable -> L17
        goto L63
    L55:
        destroyFragmentView();     // Catch: Throwable -> L17
        this.mFragment.mState = 1;     // Catch: Throwable -> L17
        goto L63
    L62:
        detach();     // Catch: Throwable -> L17
        goto L63
    L38:
        if (FragmentManager.isLoggingEnabled(3) == false) goto L40;
        Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.mFragment);     // Catch: Throwable -> L17
    L40:
        Fragment r43 = this.mFragment;     // Catch: Throwable -> L17
        if (r43.mBeingSaved == false) goto L44;
        saveState();     // Catch: Throwable -> L17
    L48:
        Fragment r44 = this.mFragment;     // Catch: Throwable -> L17
        if (r44.mView == null) goto L53;
        ViewGroup r52 = r44.mContainer;     // Catch: Throwable -> L17
        if (r52 == null) goto L53;
        SpecialEffectsController.getOrCreateController(r52, r44.getParentFragmentManager()).enqueueRemove(this);     // Catch: Throwable -> L17
    L53:
        this.mFragment.mState = 3;     // Catch: Throwable -> L17
        goto L63
    L44:
        if (r43.mView == null) goto L48;
        if (r43.mSavedViewState != null) goto L48;
        saveViewState();     // Catch: Throwable -> L17
        goto L48
    L57:
        if (r6.mBeingSaved == true) goto L59;
    L61:
        destroy();     // Catch: Throwable -> L17
        goto L63
    L59:
        if (this.mFragmentStore.getSavedState(r6.mWho) != null) goto L61;
        saveState();     // Catch: Throwable -> L17
        goto L61
    L64:
        if (r4 == false) goto L66;
    L80:
        Fragment r1 = this.mFragment;     // Catch: Throwable -> L17
        if (r1.mHiddenChanged == true) goto L83;
    L94:
        this.mMovingToState = false;
        return;
    L83:
        if (r1.mView == null) goto L90;
        ViewGroup r2 = r1.mContainer;     // Catch: Throwable -> L17
        if (r2 == null) goto L90;
        SpecialEffectsController r12 = SpecialEffectsController.getOrCreateController(r2, r1.getParentFragmentManager());     // Catch: Throwable -> L17
        if (this.mFragment.mHidden == false) goto L89;
        r12.enqueueHide(this);     // Catch: Throwable -> L17
        goto L90
    L89:
        r12.enqueueShow(this);     // Catch: Throwable -> L17
    L90:
        Fragment r13 = this.mFragment;     // Catch: Throwable -> L17
        FragmentManager r22 = r13.mFragmentManager;     // Catch: Throwable -> L17
        if (r22 == null) goto L93;
        r22.invalidateMenuForFragment(r13);     // Catch: Throwable -> L17
    L93:
        Fragment r14 = this.mFragment;     // Catch: Throwable -> L17
        r14.mHiddenChanged = false;     // Catch: Throwable -> L17
        r14.onHiddenChanged(r14.mHidden);     // Catch: Throwable -> L17
        this.mFragment.mChildFragmentManager.dispatchOnHiddenChanged();     // Catch: Throwable -> L17
        goto L94
    L66:
        if (r7 != (-1)) goto L80;
        if (r6.mRemoving == false) goto L80;
        if (r6.isInBackStack() == true) goto L80;
        if (this.mFragment.mBeingSaved == true) goto L80;
        if (FragmentManager.isLoggingEnabled(3) == false) goto L76;
        Log.d("FragmentManager", "Cleaning up state of never attached fragment: " + this.mFragment);     // Catch: Throwable -> L17
    L76:
        this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment);     // Catch: Throwable -> L17
        this.mFragmentStore.makeInactive(this);     // Catch: Throwable -> L17
        if (FragmentManager.isLoggingEnabled(3) == false) goto L79;
        Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);     // Catch: Throwable -> L17
    L79:
        this.mFragment.initState();     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        this.mMovingToState = false;
        throw th;
    }

    public void pause() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "movefrom RESUMED: " + this.mFragment);
    L5:
        this.mFragment.performPause();
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    public void restoreState(ClassLoader r4) {
        Bundle r0 = this.mFragment.mSavedFragmentState;
        if (r0 == null) goto L17;
        r0.setClassLoader(r4);
        Fragment r42 = this.mFragment;
        r42.mSavedViewState = r42.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        Fragment r43 = this.mFragment;
        r43.mSavedViewRegistryState = r43.mSavedFragmentState.getBundle("android:view_registry_state");
        Fragment r44 = this.mFragment;
        r44.mTargetWho = r44.mSavedFragmentState.getString("android:target_state");
        Fragment r45 = this.mFragment;
        if (r45.mTargetWho == null) goto L8;
        r45.mTargetRequestCode = r45.mSavedFragmentState.getInt("android:target_req_state", 0);
    L8:
        Fragment r46 = this.mFragment;
        Boolean r02 = r46.mSavedUserVisibleHint;
        if (r02 == null) goto L11;
        r46.mUserVisibleHint = r02.booleanValue();
        this.mFragment.mSavedUserVisibleHint = null;
    L12:
        Fragment r3 = this.mFragment;
        if (r3.mUserVisibleHint == true) goto L16;
        r3.mDeferStart = true;
        return;
    L16:
        return;
    L11:
        r46.mUserVisibleHint = r46.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        goto L12
    }

    public void resume() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "moveto RESUMED: " + this.mFragment);
    L5:
        View r0 = this.mFragment.getFocusedView();
        if (r0 != null) goto L8;
    L16:
        this.mFragment.setFocusedView(null);
        this.mFragment.performResume();
        this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
        Fragment r5 = this.mFragment;
        r5.mSavedFragmentState = null;
        r5.mSavedViewState = null;
        r5.mSavedViewRegistryState = null;
        return;
    L8:
        if (isFragmentViewChild(r0) == false) goto L16;
        boolean r2 = r0.requestFocus();
        if (FragmentManager.isLoggingEnabled(2) == false) goto L16;
        StringBuilder r3 = new StringBuilder("requestFocus: Restoring focused view ");
        r3.append(r0);
        r3.append(" ");
        if (r2 == false) goto L14;
        String r02 = "succeeded";
    L15:
        r3.append(r02);
        r3.append(" on Fragment ");
        r3.append(this.mFragment);
        r3.append(" resulting in focused view ");
        r3.append(this.mFragment.mView.findFocus());
        Log.v("FragmentManager", r3.toString());
        goto L16
    L14:
        r02 = "failed";
        goto L15
    }

    public void saveState() {
        FragmentState r0 = new FragmentState(this.mFragment);
        Fragment r1 = this.mFragment;
        if (r1.mState > (-1)) goto L5;
    L13:
        r0.mSavedFragmentState = r1.mSavedFragmentState;
    L14:
        this.mFragmentStore.setSavedState(this.mFragment.mWho, r0);
        return;
    L5:
        if (r0.mSavedFragmentState != null) goto L13;
        Bundle r12 = saveBasicState();
        r0.mSavedFragmentState = r12;
        if (this.mFragment.mTargetWho == null) goto L14;
        if (r12 != null) goto L10;
        r0.mSavedFragmentState = new Bundle();
    L10:
        r0.mSavedFragmentState.putString("android:target_state", this.mFragment.mTargetWho);
        int r13 = this.mFragment.mTargetRequestCode;
        if (r13 == 0) goto L14;
        r0.mSavedFragmentState.putInt("android:target_req_state", r13);
        goto L14
    }

    public void saveViewState() {
        if (this.mFragment.mView != null) goto L6;
        return;
    L6:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L8;
        Log.v("FragmentManager", "Saving view state for fragment " + this.mFragment + " with view " + this.mFragment.mView);
    L8:
        SparseArray<Parcelable> r0 = new SparseArray();
        this.mFragment.mView.saveHierarchyState(r0);
        if (r0.size() <= 0) goto L11;
        this.mFragment.mSavedViewState = r0;
    L11:
        Bundle r02 = new Bundle();
        this.mFragment.mViewLifecycleOwner.performSave(r02);
        if (r02.isEmpty() == true) goto L15;
        this.mFragment.mSavedViewRegistryState = r02;
        return;
    }

    public void setFragmentManagerState(int r1) {
        this.mFragmentManagerState = r1;
    }

    public void start() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "moveto STARTED: " + this.mFragment);
    L5:
        this.mFragment.performStart();
        this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
    }

    public void stop() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "movefrom STARTED: " + this.mFragment);
    L5:
        this.mFragment.performStop();
        this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
    }
}
