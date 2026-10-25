package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
final class FragmentManagerViewModel extends ViewModel {
    private static final ViewModelProvider.Factory FACTORY = null;
    private final HashMap<String, FragmentManagerViewModel> mChildNonConfigs;
    private boolean mHasBeenCleared;
    private boolean mHasSavedSnapshot;
    private boolean mIsStateSaved;
    private final HashMap<String, Fragment> mRetainedFragments;
    private final boolean mStateAutomaticallySaved;
    private final HashMap<String, ViewModelStore> mViewModelStores;

    /* renamed from: androidx.fragment.app.FragmentManagerViewModel$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewModelProvider.Factory {
        public AnonymousClass1() {
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> r1) {
            return new FragmentManagerViewModel(true);
        }
    }

    static {
        FACTORY = new AnonymousClass1();
    }

    public FragmentManagerViewModel(boolean r2) {
        this.mRetainedFragments = new HashMap();
        this.mChildNonConfigs = new HashMap();
        this.mViewModelStores = new HashMap();
        this.mHasBeenCleared = false;
        this.mHasSavedSnapshot = false;
        this.mIsStateSaved = false;
        this.mStateAutomaticallySaved = r2;
    }

    private void clearNonConfigStateInternal(String r2) {
        FragmentManagerViewModel r0 = this.mChildNonConfigs.get(r2);
        if (r0 == null) goto L5;
        r0.onCleared();
        this.mChildNonConfigs.remove(r2);
    L5:
        ViewModelStore r02 = this.mViewModelStores.get(r2);
        if (r02 == null) goto L9;
        r02.clear();
        this.mViewModelStores.remove(r2);
        return;
    }

    public static FragmentManagerViewModel getInstance(ViewModelStore r2) {
        return (FragmentManagerViewModel) new ViewModelProvider(r2, FACTORY).get(FragmentManagerViewModel.class);
    }

    public void addRetainedFragment(Fragment r5) {
        if (this.mIsStateSaved == false) goto L9;
        if (FragmentManager.isLoggingEnabled(2) == false) goto L15;
        Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
        return;
    L15:
        return;
    L9:
        if (this.mRetainedFragments.containsKey(r5.mWho) == true) goto L17;
        this.mRetainedFragments.put(r5.mWho, r5);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L16;
        Log.v("FragmentManager", "Updating retained Fragments: Added " + r5);
        return;
    L16:
        return;
    }

    public void clearNonConfigState(Fragment r3) {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "Clearing non-config state for " + r3);
    L5:
        clearNonConfigStateInternal(r3.mWho);
    }

    public void clearNonConfigState(String r3) {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "Clearing non-config state for saved state of Fragment " + r3);
    L5:
        clearNonConfigStateInternal(r3);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (FragmentManagerViewModel.class != r5.getClass()) goto L17;
        FragmentManagerViewModel r52 = (FragmentManagerViewModel) r5;
        if (this.mRetainedFragments.equals(r52.mRetainedFragments) == false) goto L17;
        if (this.mChildNonConfigs.equals(r52.mChildNonConfigs) == false) goto L17;
        if (this.mViewModelStores.equals(r52.mViewModelStores) == false) goto L17;
        return true;
    }

    public Fragment findRetainedFragmentByWho(String r1) {
        return this.mRetainedFragments.get(r1);
    }

    public FragmentManagerViewModel getChildNonConfig(Fragment r3) {
        FragmentManagerViewModel r0 = this.mChildNonConfigs.get(r3.mWho);
        if (r0 != null) goto L6;
        FragmentManagerViewModel r02 = new FragmentManagerViewModel(this.mStateAutomaticallySaved);
        this.mChildNonConfigs.put(r3.mWho, r02);
        return r02;
    L6:
        return r0;
    }

    public Collection<Fragment> getRetainedFragments() {
        return new ArrayList(this.mRetainedFragments.values());
    }

    public ViewModelStore getViewModelStore(Fragment r3) {
        ViewModelStore r0 = this.mViewModelStores.get(r3.mWho);
        if (r0 != null) goto L6;
        ViewModelStore r02 = new ViewModelStore();
        this.mViewModelStores.put(r3.mWho, r02);
        return r02;
    L6:
        return r0;
    }

    public int hashCode() {
        return (((this.mRetainedFragments.hashCode() * 31) + this.mChildNonConfigs.hashCode()) * 31) + this.mViewModelStores.hashCode();
    }

    public boolean isCleared() {
        return this.mHasBeenCleared;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L5;
        Log.d("FragmentManager", "onCleared called for " + this);
    L5:
        this.mHasBeenCleared = true;
    }

    public void removeRetainedFragment(Fragment r4) {
        if (this.mIsStateSaved == false) goto L9;
        if (FragmentManager.isLoggingEnabled(2) == false) goto L14;
        Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
        return;
    L14:
        return;
    L9:
        if (this.mRetainedFragments.remove(r4.mWho) != null) goto L11;
        return;
    L11:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L16;
        Log.v("FragmentManager", "Updating retained Fragments: Removed " + r4);
        return;
    }

    public void setIsStateSaved(boolean r1) {
        this.mIsStateSaved = r1;
    }

    public boolean shouldDestroy(Fragment r2) {
        if (this.mRetainedFragments.containsKey(r2.mWho) == true) goto L6;
        return true;
    L6:
        if (this.mStateAutomaticallySaved == false) goto L10;
        return this.mHasBeenCleared;
    L10:
        return !this.mHasSavedSnapshot;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("FragmentManagerViewModel{");
        r0.append(Integer.toHexString(System.identityHashCode(this)));
        r0.append("} Fragments (");
        Iterator<Fragment> r1 = this.mRetainedFragments.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        r0.append(r1.next());
        if (r1.hasNext() == false) goto L4;
        r0.append(", ");
        goto L4
    L8:
        r0.append(") Child Non Config (");
        Iterator<String> r12 = this.mChildNonConfigs.keySet().iterator();
    L10:
        if (r12.hasNext() == false) goto L14;
        r0.append(r12.next());
        if (r12.hasNext() == false) goto L10;
        r0.append(", ");
        goto L10
    L14:
        r0.append(") ViewModelStores (");
        Iterator<String> r4 = this.mViewModelStores.keySet().iterator();
    L16:
        if (r4.hasNext() == false) goto L20;
        r0.append(r4.next());
        if (r4.hasNext() == false) goto L16;
        r0.append(", ");
        goto L16
    L20:
        r0.append(')');
        return r0.toString();
    }
}
