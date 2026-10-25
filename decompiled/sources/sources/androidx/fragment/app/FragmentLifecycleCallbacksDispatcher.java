package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
class FragmentLifecycleCallbacksDispatcher {
    private final FragmentManager mFragmentManager;
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks;

    /* loaded from: classes3.dex */
    public static final class FragmentLifecycleCallbacksHolder {
        final FragmentManager.FragmentLifecycleCallbacks mCallback;
        final boolean mRecursive;

        public FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks r1, boolean r2) {
            this.mCallback = r1;
            this.mRecursive = r2;
        }
    }

    public FragmentLifecycleCallbacksDispatcher(FragmentManager r2) {
        this.mLifecycleCallbacks = new CopyOnWriteArrayList();
        this.mFragmentManager = r2;
    }

    public void dispatchOnFragmentActivityCreated(Fragment r4, Bundle r5, boolean r6) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentActivityCreated(r4, r5, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r6 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentActivityCreated(this.mFragmentManager, r4, r5);
        goto L7
    }

    public void dispatchOnFragmentAttached(Fragment r5, boolean r6) {
        Context r0 = this.mFragmentManager.getHost().getContext();
        Fragment r1 = this.mFragmentManager.getParent();
        if (r1 == null) goto L5;
        r1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentAttached(r5, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r12 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r12.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r2 = r12.next();
        if (r6 == false) goto L12;
        if (r2.mRecursive == false) goto L7;
    L12:
        r2.mCallback.onFragmentAttached(this.mFragmentManager, r5, r0);
        goto L7
    }

    public void dispatchOnFragmentCreated(Fragment r4, Bundle r5, boolean r6) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentCreated(r4, r5, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r6 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentCreated(this.mFragmentManager, r4, r5);
        goto L7
    }

    public void dispatchOnFragmentDestroyed(Fragment r4, boolean r5) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentDestroyed(r4, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r5 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentDestroyed(this.mFragmentManager, r4);
        goto L7
    }

    public void dispatchOnFragmentDetached(Fragment r4, boolean r5) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentDetached(r4, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r5 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentDetached(this.mFragmentManager, r4);
        goto L7
    }

    public void dispatchOnFragmentPaused(Fragment r4, boolean r5) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentPaused(r4, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r5 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentPaused(this.mFragmentManager, r4);
        goto L7
    }

    public void dispatchOnFragmentPreAttached(Fragment r5, boolean r6) {
        Context r0 = this.mFragmentManager.getHost().getContext();
        Fragment r1 = this.mFragmentManager.getParent();
        if (r1 == null) goto L5;
        r1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentPreAttached(r5, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r12 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r12.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r2 = r12.next();
        if (r6 == false) goto L12;
        if (r2.mRecursive == false) goto L7;
    L12:
        r2.mCallback.onFragmentPreAttached(this.mFragmentManager, r5, r0);
        goto L7
    }

    public void dispatchOnFragmentPreCreated(Fragment r4, Bundle r5, boolean r6) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentPreCreated(r4, r5, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r6 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentPreCreated(this.mFragmentManager, r4, r5);
        goto L7
    }

    public void dispatchOnFragmentResumed(Fragment r4, boolean r5) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentResumed(r4, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r5 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentResumed(this.mFragmentManager, r4);
        goto L7
    }

    public void dispatchOnFragmentSaveInstanceState(Fragment r4, Bundle r5, boolean r6) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentSaveInstanceState(r4, r5, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r6 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentSaveInstanceState(this.mFragmentManager, r4, r5);
        goto L7
    }

    public void dispatchOnFragmentStarted(Fragment r4, boolean r5) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentStarted(r4, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r5 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentStarted(this.mFragmentManager, r4);
        goto L7
    }

    public void dispatchOnFragmentStopped(Fragment r4, boolean r5) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentStopped(r4, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r5 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentStopped(this.mFragmentManager, r4);
        goto L7
    }

    public void dispatchOnFragmentViewCreated(Fragment r4, View r5, Bundle r6, boolean r7) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentViewCreated(r4, r5, r6, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r7 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentViewCreated(this.mFragmentManager, r4, r5, r6);
        goto L7
    }

    public void dispatchOnFragmentViewDestroyed(Fragment r4, boolean r5) {
        Fragment r0 = this.mFragmentManager.getParent();
        if (r0 == null) goto L5;
        r0.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentViewDestroyed(r4, true);
    L5:
        Iterator<FragmentLifecycleCallbacksHolder> r02 = this.mLifecycleCallbacks.iterator();
    L7:
        if (r02.hasNext() == false) goto L13;
        FragmentLifecycleCallbacksHolder r1 = r02.next();
        if (r5 == false) goto L12;
        if (r1.mRecursive == false) goto L7;
    L12:
        r1.mCallback.onFragmentViewDestroyed(this.mFragmentManager, r4);
        goto L7
    }

    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks r2, boolean r3) {
        this.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(r2, r3));
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks r5) {
        CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> r0 = this.mLifecycleCallbacks;
        monitor-enter(r0);
        int r1 = this.mLifecycleCallbacks.size();     // Catch: Throwable -> L9
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L12;
        if (this.mLifecycleCallbacks.get(r2).mCallback == r5) goto L8;
        r2 = r2 + 1;     // Catch: Throwable -> L9
        goto L5
    L8:
        this.mLifecycleCallbacks.remove(r2);     // Catch: Throwable -> L9
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
