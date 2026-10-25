package org.telegram.messenger.utils;

import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.View;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import me.vkryl.core.reference.ReferenceList;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;

/* loaded from: classes.dex */
public final class Choreographer60FpsContent implements Choreographer.FrameCallback {
    private static Choreographer60FpsContent sInstance;
    private long mAccumulatedNs;
    private final Choreographer mChoreographer;
    private int mCounter;
    private final ReferenceList<Drawable> mDrawablesToInvalidate;
    private final ReferenceList<Drawable> mDrawablesToInvalidate30fps;
    private final SparseArray<CallbackGroup> mGroups;
    private long mLastVsyncNs;
    private final Set<FrameCallback> mOneShot;
    private final ReferenceList<View> mViewsToInvalidate;

    public static final class CallbackGroup {
        long accumulatedNs;
        final ReferenceList<FrameCallback> callbacks;
        final long intervalNs;
        final ReferenceList<Runnable> runnableCallbacks;
        ReferenceList<Runnable> runnableCallbacksOnce;
        final int stride;

        public CallbackGroup(long r2, int r4) {
            this.callbacks = new ReferenceList();
            this.runnableCallbacks = new ReferenceList();
            this.intervalNs = r2;
            this.stride = r4;
        }
    }

    public interface FrameCallback {
        void doFrame(long r1);
    }

    private Choreographer60FpsContent() {
        Choreographer r0 = Choreographer.getInstance();
        this.mChoreographer = r0;
        this.mOneShot = new LinkedHashSet();
        this.mGroups = new SparseArray();
        this.mDrawablesToInvalidate = new ReferenceList();
        this.mDrawablesToInvalidate30fps = new ReferenceList();
        this.mViewsToInvalidate = new ReferenceList();
        if (ApplicationLoader.isScreenOn == true) goto L5;
        return;
    L5:
        if (ApplicationLoader.mainInterfacePaused == true) goto L9;
        r0.postFrameCallback(this);
        return;
    }

    private static void checkMainThread() {
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L6;
        return;
    L6:
        if (Looper.myLooper() != Looper.getMainLooper()) goto L8;
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Choreographer60FpsContent must be used on the main thread");
    }

    private void dispatchFrame(long r8) {
        int r0 = 0;
    L4:
        if (r0 >= this.mGroups.size()) goto L28;
        CallbackGroup r1 = this.mGroups.valueAt(r0);
        int r2 = r1.stride;
        if (r2 > 0) goto L8;
        long r22 = r1.accumulatedNs + 16666666;
        r1.accumulatedNs = r22;
        long r4 = r1.intervalNs;
        if (r22 < r4) goto L27;
        r1.accumulatedNs = r22 % r4;
    L13:
        ReferenceList<Runnable> r23 = r1.runnableCallbacksOnce;
        if (r23 == null) goto L19;
        r1.runnableCallbacksOnce = null;
        Iterator<Runnable> r24 = r23.iterator();
    L17:
        if (r24.hasNext() == false) goto L19;
        r24.next().run();
    L19:
        Iterator<FrameCallback> r25 = r1.callbacks.iterator();
    L21:
        if (r25.hasNext() == false) goto L23;
        r25.next().doFrame(r8);
        goto L21
    L23:
        Iterator<Runnable> r12 = r1.runnableCallbacks.iterator();
    L25:
        if (r12.hasNext() == false) goto L27;
        r12.next().run();
    L27:
        r0 = r0 + 1;
        goto L4
    L8:
        if ((this.mCounter % r2) != 0) goto L27;
    L28:
        Iterator<FrameCallback> r02 = this.mOneShot.iterator();
    L30:
        if (r02.hasNext() == false) goto L32;
        r02.next().doFrame(r8);
        goto L30
    L32:
        Iterator<View> r82 = this.mViewsToInvalidate.iterator();
    L34:
        if (r82.hasNext() == false) goto L36;
        r82.next().invalidate();
        goto L34
    L36:
        Iterator<Drawable> r83 = this.mDrawablesToInvalidate.iterator();
    L38:
        if (r83.hasNext() == false) goto L40;
        r83.next().invalidateSelf();
        goto L38
    L40:
        this.mViewsToInvalidate.clear();
        this.mDrawablesToInvalidate.clear();
        this.mOneShot.clear();
        if ((this.mCounter % 2) != 0) goto L47;
        Iterator<Drawable> r84 = this.mDrawablesToInvalidate30fps.iterator();
    L44:
        if (r84.hasNext() == false) goto L46;
        r84.next().invalidateSelf();
        goto L44
    L46:
        this.mDrawablesToInvalidate30fps.clear();
    L47:
        this.mCounter++;
    }

    public static Choreographer60FpsContent getInstance() {
        checkMainThread();
        if (sInstance != null) goto L6;
        sInstance = new Choreographer60FpsContent();
    L6:
        return sInstance;
    }

    private CallbackGroup getOrCreateGroup(int r5) {
        CallbackGroup r0 = this.mGroups.get(r5);
        if (r0 != null) goto L10;
        long r02 = 1000000000 / r5;
        if ((60 % r5) != 0) goto L7;
        int r2 = 60 / r5;
    L8:
        CallbackGroup r3 = new CallbackGroup(r02, r2);
        this.mGroups.put(r5, r3);
        return r3;
    L7:
        r2 = 0;
        goto L8
    L10:
        return r0;
    }

    public static void onApplicationStateChanged() {
        Choreographer60FpsContent r0 = sInstance;
        if (r0 == null) goto L10;
        r0.mChoreographer.removeFrameCallback(r0);
        Choreographer60FpsContent r02 = sInstance;
        r02.mAccumulatedNs = 0;
        r02.mLastVsyncNs = 0;
        if (ApplicationLoader.isScreenOn == true) goto L7;
        return;
    L7:
        if (ApplicationLoader.mainInterfacePaused == true) goto L12;
        Choreographer60FpsContent r03 = sInstance;
        r03.mChoreographer.postFrameCallback(r03);
        return;
    L12:
        return;
    }

    public void addFrameCallback(Runnable r2, int r3) {
        checkMainThread();
        if (r2 != null) goto L5;
        return;
    L5:
        int r32 = Math.max(1, Math.min(r3, 60));
        removeFrameCallback(r2);
        getOrCreateGroup(r32).runnableCallbacks.add(r2);
    }

    public void addFrameCallback(FrameCallback r2, int r3) {
        checkMainThread();
        int r32 = Math.max(1, Math.min(r3, 60));
        removeFrameCallback(r2);
        getOrCreateGroup(r32).callbacks.add(r2);
    }

    public void addFrameCallbackOnce(Runnable r2, int r3) {
        checkMainThread();
        if (r2 != null) goto L5;
        return;
    L5:
        int r32 = Math.max(1, Math.min(r3, 60));
        removeFrameCallbackOnce(r2);
        CallbackGroup r1 = getOrCreateGroup(r32);
        if (r1.runnableCallbacksOnce != null) goto L8;
        r1.runnableCallbacksOnce = new ReferenceList();
    L8:
        r1.runnableCallbacksOnce.add(r2);
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long r6) {
        long r0 = this.mLastVsyncNs;
        if (r0 != 0) goto L5;
        this.mLastVsyncNs = r6;
    L8:
        this.mChoreographer.postFrameCallback(this);
        return;
    L5:
        long r2 = this.mAccumulatedNs + (r6 - r0);
        this.mAccumulatedNs = r2;
        this.mLastVsyncNs = r6;
        if (r2 < 16666666) goto L8;
        this.mAccumulatedNs = r2 % 16666666;
        dispatchFrame(r6);
        goto L8
    }

    public void postInvalidateDrawable(Drawable r1) {
        checkMainThread();
        this.mDrawablesToInvalidate.add(r1);
    }

    public void postInvalidateDrawable30fps(Drawable r1) {
        checkMainThread();
        this.mDrawablesToInvalidate30fps.add(r1);
    }

    public void postInvalidateView(View r1) {
        checkMainThread();
        this.mViewsToInvalidate.add(r1);
    }

    public void removeFrameCallback(Runnable r3) {
        checkMainThread();
        if (r3 == null) goto L12;
        int r0 = 0;
    L7:
        if (r0 >= this.mGroups.size()) goto L15;
        if (this.mGroups.valueAt(r0).runnableCallbacks.remove(r3) == true) goto L16;
        r0 = r0 + 1;
        goto L7
    L16:
        return;
    L15:
        return;
    }

    public void removeFrameCallback(FrameCallback r3) {
        checkMainThread();
        if (r3 == null) goto L12;
        int r0 = 0;
    L7:
        if (r0 >= this.mGroups.size()) goto L15;
        if (this.mGroups.valueAt(r0).callbacks.remove(r3) == true) goto L16;
        r0 = r0 + 1;
        goto L7
    L16:
        return;
    L15:
        return;
    }

    public void removeFrameCallbackOnce(Runnable r3) {
        checkMainThread();
        if (r3 == null) goto L14;
        int r0 = 0;
    L7:
        if (r0 >= this.mGroups.size()) goto L19;
        ReferenceList<Runnable> r1 = this.mGroups.valueAt(r0).runnableCallbacksOnce;
        if (r1 == null) goto L13;
        if (r1.remove(r3) == false) goto L13;
        return;
    L13:
        r0 = r0 + 1;
        goto L7
    L19:
        return;
    }
}
