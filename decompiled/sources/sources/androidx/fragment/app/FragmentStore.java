package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class FragmentStore {
    private final HashMap<String, FragmentStateManager> mActive;
    private final ArrayList<Fragment> mAdded;
    private FragmentManagerViewModel mNonConfig;
    private final HashMap<String, FragmentState> mSavedState;

    public FragmentStore() {
        this.mAdded = new ArrayList();
        this.mActive = new HashMap();
        this.mSavedState = new HashMap();
    }

    public void addFragment(Fragment r2) {
        if (this.mAdded.contains(r2) == true) goto L13;
        ArrayList<Fragment> r0 = this.mAdded;
        monitor-enter(r0);
        this.mAdded.add(r2);     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        r2.mAdded = true;
        return;
    L10:
        th = move-exception;
        throw th;
    L13:
        DexMaker$$ExternalSyntheticBUOutline0.m("Fragment already added: ", r2);
    }

    public void burpActive() {
        this.mActive.values().removeAll(Collections.singleton(null));
    }

    public boolean containsActiveFragment(String r1) {
        if (this.mActive.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void dispatchStateChange(int r2) {
        Iterator<FragmentStateManager> r1 = this.mActive.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        FragmentStateManager r0 = r1.next();
        if (r0 == null) goto L4;
        r0.setFragmentManagerState(r2);
        goto L4
    }

    public void dump(String r4, FileDescriptor r5, PrintWriter r6, String[] r7) {
        String r0 = r4 + "    ";
        if (this.mActive.isEmpty() == true) goto L11;
        r6.print(r4);
        r6.println("Active Fragments:");
        Iterator<FragmentStateManager> r1 = this.mActive.values().iterator();
    L6:
        if (r1.hasNext() == false) goto L11;
        FragmentStateManager r2 = r1.next();
        r6.print(r4);
        if (r2 != null) goto L9;
        r6.println("null");
        goto L6
    L9:
        Fragment r22 = r2.getFragment();
        r6.println(r22);
        r22.dump(r0, r5, r6, r7);
    L11:
        int r52 = this.mAdded.size();
        if (r52 <= 0) goto L16;
        r6.print(r4);
        r6.println("Added Fragments:");
        int r72 = 0;
    L14:
        if (r72 >= r52) goto L24;
        Fragment r02 = this.mAdded.get(r72);
        r6.print(r4);
        r6.print("  #");
        r6.print(r72);
        r6.print(": ");
        r6.println(r02.toString());
        r72 = r72 + 1;
        goto L14
    L24:
        return;
    }

    public Fragment findActiveFragment(String r1) {
        FragmentStateManager r0 = this.mActive.get(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getFragment();
    }

    public Fragment findFragmentById(int r4) {
        int r0 = this.mAdded.size() - 1;
    L3:
        if (r0 < 0) goto L10;
        Fragment r1 = this.mAdded.get(r0);
        if (r1 == null) goto L9;
        if (r1.mFragmentId != r4) goto L9;
        return r1;
    L9:
        r0 = r0 - 1;
        goto L3
    L10:
        Iterator<FragmentStateManager> r3 = this.mActive.values().iterator();
    L12:
        if (r3.hasNext() == false) goto L18;
        FragmentStateManager r02 = r3.next();
        if (r02 == null) goto L12;
        Fragment r03 = r02.getFragment();
        if (r03.mFragmentId != r4) goto L12;
        return r03;
    L18:
        return null;
    }

    public Fragment findFragmentByTag(String r4) {
        if (r4 == null) goto L11;
        int r0 = this.mAdded.size() - 1;
    L4:
        if (r0 < 0) goto L11;
        Fragment r1 = this.mAdded.get(r0);
        if (r1 == null) goto L10;
        if (r4.equals(r1.mTag) == false) goto L10;
        return r1;
    L10:
        r0 = r0 - 1;
    L11:
        if (r4 == null) goto L20;
        Iterator<FragmentStateManager> r3 = this.mActive.values().iterator();
    L14:
        if (r3.hasNext() == false) goto L32;
        FragmentStateManager r02 = r3.next();
        if (r02 == null) goto L14;
        Fragment r03 = r02.getFragment();
        if (r4.equals(r03.mTag) == false) goto L14;
        return r03;
    L32:
        return null;
    L20:
        return null;
    }

    public Fragment findFragmentByWho(String r2) {
        Iterator<FragmentStateManager> r1 = this.mActive.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L10;
        FragmentStateManager r0 = r1.next();
        if (r0 == null) goto L4;
        Fragment r02 = r0.getFragment().findFragmentByWho(r2);
        if (r02 == null) goto L4;
        return r02;
    L10:
        return null;
    }

    public int findFragmentIndexInContainer(Fragment r6) {
        ViewGroup r0 = r6.mContainer;
        if (r0 != null) goto L5;
        return -1;
    L5:
        int r62 = this.mAdded.indexOf(r6);
        int r2 = r62 - 1;
    L6:
        if (r2 < 0) goto L14;
        Fragment r3 = this.mAdded.get(r2);
        if (r3.mContainer != r0) goto L13;
        View r32 = r3.mView;
        if (r32 == null) goto L13;
        return r0.indexOfChild(r32) + 1;
    L13:
        r2 = r2 - 1;
    L14:
        r62 = r62 + 1;
        if (r62 >= this.mAdded.size()) goto L23;
        Fragment r22 = this.mAdded.get(r62);
        if (r22.mContainer != r0) goto L14;
        View r23 = r22.mView;
        if (r23 == null) goto L14;
        return r0.indexOfChild(r23);
    L23:
        return -1;
    }

    public List<FragmentStateManager> getActiveFragmentStateManagers() {
        ArrayList r0 = new ArrayList();
        Iterator<FragmentStateManager> r2 = this.mActive.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        FragmentStateManager r1 = r2.next();
        if (r1 == null) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    public List<Fragment> getActiveFragments() {
        ArrayList r0 = new ArrayList();
        Iterator<FragmentStateManager> r2 = this.mActive.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        FragmentStateManager r1 = r2.next();
        if (r1 != null) goto L7;
        r0.add(null);
        goto L4
    L7:
        r0.add(r1.getFragment());
        goto L4
    L9:
        return r0;
    }

    public ArrayList<FragmentState> getAllSavedState() {
        return new ArrayList(this.mSavedState.values());
    }

    public FragmentStateManager getFragmentStateManager(String r1) {
        return this.mActive.get(r1);
    }

    public List<Fragment> getFragments() {
        if (this.mAdded.isEmpty() == true) goto L5;
        ArrayList<Fragment> r0 = this.mAdded;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList(this.mAdded);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r1;
    L11:
        th = move-exception;
        throw th;
    L5:
        return Collections.EMPTY_LIST;
    }

    public FragmentManagerViewModel getNonConfig() {
        return this.mNonConfig;
    }

    public FragmentState getSavedState(String r1) {
        return this.mSavedState.get(r1);
    }

    public void makeActive(FragmentStateManager r4) {
        Fragment r0 = r4.getFragment();
        if (containsActiveFragment(r0.mWho) == true) goto L17;
        this.mActive.put(r0.mWho, r4);
        if (r0.mRetainInstanceChangedWhileDetached == false) goto L13;
        boolean r42 = r0.mRetainInstance;
        FragmentManagerViewModel r3 = this.mNonConfig;
        if (r42 == false) goto L10;
        r3.addRetainedFragment(r0);
    L11:
        r0.mRetainInstanceChangedWhileDetached = false;
        goto L13
    L10:
        r3.removeRetainedFragment(r0);
    L13:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L16;
        Log.v("FragmentManager", "Added fragment to active set " + r0);
        return;
    L16:
        return;
    }

    public void makeInactive(FragmentStateManager r3) {
        Fragment r32 = r3.getFragment();
        if (r32.mRetainInstance == false) goto L6;
        this.mNonConfig.removeRetainedFragment(r32);
    L6:
        if (this.mActive.put(r32.mWho, null) != null) goto L9;
        return;
    L9:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L12;
        Log.v("FragmentManager", "Removed fragment from active set " + r32);
        return;
    }

    public void moveToExpectedState() {
        ArrayList<Fragment> r0 = this.mAdded;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        Fragment r3 = r0.get(r2);
        r2 = r2 + 1;
        FragmentStateManager r32 = this.mActive.get(r3.mWho);
        if (r32 == null) goto L3;
        r32.moveToExpectedState();
        goto L3
    L7:
        Iterator<FragmentStateManager> r02 = this.mActive.values().iterator();
    L9:
        if (r02.hasNext() == false) goto L22;
        FragmentStateManager r12 = r02.next();
        if (r12 == null) goto L9;
        r12.moveToExpectedState();
        Fragment r22 = r12.getFragment();
        if (r22.mRemoving == false) goto L9;
        if (r22.isInBackStack() == true) goto L9;
        if (r22.mBeingSaved == true) goto L19;
    L21:
        makeInactive(r12);
        goto L9
    L19:
        if (this.mSavedState.containsKey(r22.mWho) == true) goto L21;
        r12.saveState();
        goto L21
    }

    public void removeFragment(Fragment r2) {
        ArrayList<Fragment> r0 = this.mAdded;
        monitor-enter(r0);
        this.mAdded.remove(r2);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        r2.mAdded = false;
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public void resetActiveFragments() {
        this.mActive.clear();
    }

    public void restoreAddedFragments(List<String> r5) {
        this.mAdded.clear();
        if (r5 == null) goto L19;
        Iterator<String> r52 = r5.iterator();
    L6:
        if (r52.hasNext() == false) goto L20;
        String r0 = r52.next();
        Fragment r1 = findActiveFragment(r0);
        if (r1 == null) goto L13;
        if (FragmentManager.isLoggingEnabled(2) == false) goto L12;
        Log.v("FragmentManager", "restoreSaveState: added (" + r0 + "): " + r1);
    L12:
        addFragment(r1);
        goto L6
    L13:
        LiveData$$ExternalSyntheticBUOutline0.m("No instantiated fragment for (", r0, ")");
        return;
    L20:
        return;
    }

    public void restoreSaveState(ArrayList<FragmentState> r6) {
        this.mSavedState.clear();
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        FragmentState r2 = r6.get(r1);
        r1 = r1 + 1;
        FragmentState r22 = r2;
        this.mSavedState.put(r22.mWho, r22);
        goto L3
    }

    public ArrayList<String> saveActiveFragments() {
        ArrayList<String> r0 = new ArrayList(this.mActive.size());
        Iterator<FragmentStateManager> r4 = this.mActive.values().iterator();
    L4:
        if (r4.hasNext() == false) goto L10;
        FragmentStateManager r1 = r4.next();
        if (r1 == null) goto L4;
        Fragment r2 = r1.getFragment();
        r1.saveState();
        r0.add(r2.mWho);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L4;
        Log.v("FragmentManager", "Saved state of " + r2 + ": " + r2.mSavedFragmentState);
        goto L4
    L10:
        return r0;
    }

    public ArrayList<String> saveAddedFragments() {
        ArrayList<Fragment> r0 = this.mAdded;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mAdded.isEmpty() == true) goto L7;
        ArrayList<String> r1 = new ArrayList(this.mAdded.size());     // Catch: Throwable -> L9
        ArrayList<Fragment> r8 = this.mAdded;     // Catch: Throwable -> L9
        int r2 = r8.size();     // Catch: Throwable -> L9
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L16;
        Fragment r4 = r8.get(r3);     // Catch: Throwable -> L9
        r3 = r3 + 1;     // Catch: Throwable -> L9
        Fragment r42 = r4;     // Catch: Throwable -> L9
        r1.add(r42.mWho);     // Catch: Throwable -> L9
        if (FragmentManager.isLoggingEnabled(2) == false) goto L12;
        Log.v("FragmentManager", "saveAllState: adding fragment (" + r42.mWho + "): " + r42);     // Catch: Throwable -> L9
        goto L12
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r1;
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    }

    public void setNonConfig(FragmentManagerViewModel r1) {
        this.mNonConfig = r1;
    }

    public FragmentState setSavedState(String r1, FragmentState r2) {
        HashMap<String, FragmentState> r0 = this.mSavedState;
        if (r2 == null) goto L7;
        return r0.put(r1, r2);
    L7:
        return r0.remove(r1);
    }
}
