package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.collection.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class TransitionManager {
    private static Transition sDefaultTransition;
    static ArrayList<ViewGroup> sPendingTransitions;
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> sRunningTransitions;

    public static class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        ViewGroup mSceneRoot;
        Transition mTransition;

        /* renamed from: androidx.transition.TransitionManager$MultiListener$1, reason: invalid class name */
        public class AnonymousClass1 extends TransitionListenerAdapter {
            final /* synthetic */ MultiListener this$0;
            final /* synthetic */ ArrayMap val$runningTransitions;

            public AnonymousClass1(MultiListener r1, ArrayMap r2) {
                this.this$0 = r1;
                this.val$runningTransitions = r2;
            }

            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition r3) {
                ((ArrayList) this.val$runningTransitions.get(this.this$0.mSceneRoot)).remove(r3);
                r3.removeListener(this);
            }
        }

        public MultiListener(Transition r1, ViewGroup r2) {
            this.mTransition = r1;
            this.mSceneRoot = r2;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            removeListeners();
            if (TransitionManager.sPendingTransitions.remove(this.mSceneRoot) == true) goto L5;
            return true;
        L5:
            ArrayMap<ViewGroup, ArrayList<Transition>> r0 = TransitionManager.getRunningTransitions();
            ArrayList<Transition> r2 = r0.get(this.mSceneRoot);
            ArrayList r3 = null;
            if (r2 != null) goto L9;
            r2 = new ArrayList();
            r0.put(this.mSceneRoot, r2);
        L11:
            r2.add(this.mTransition);
            this.mTransition.addListener(new AnonymousClass1(this, r0));
            int r4 = 0;
            this.mTransition.captureValues(this.mSceneRoot, false);
            if (r3 == null) goto L16;
            int r02 = r3.size();
        L14:
            if (r4 >= r02) goto L16;
            Object r22 = r3.get(r4);
            r4 = r4 + 1;
            ((Transition) r22).resume(this.mSceneRoot);
        L16:
            this.mTransition.playTransition(this.mSceneRoot);
            return true;
        L9:
            if (r2.size() <= 0) goto L11;
            r3 = new ArrayList(r2);
            goto L11
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r5) {
            removeListeners();
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList<Transition> r52 = TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (r52 != null) goto L5;
        L9:
            this.mTransition.clearValues(true);
            return;
        L5:
            if (r52.size() <= 0) goto L9;
            int r0 = r52.size();
            int r1 = 0;
        L7:
            if (r1 >= r0) goto L9;
            Transition r2 = r52.get(r1);
            r1 = r1 + 1;
            r2.resume(this.mSceneRoot);
            goto L7
        }
    }

    static {
        sDefaultTransition = new AutoTransition();
        sRunningTransitions = new ThreadLocal();
        sPendingTransitions = new ArrayList();
    }

    public static void beginDelayedTransition(ViewGroup r1, Transition r2) {
        if (sPendingTransitions.contains(r1) == false) goto L5;
        return;
    L5:
        if (r1.isLaidOut() == false) goto L12;
        sPendingTransitions.add(r1);
        if (r2 != null) goto L9;
        r2 = sDefaultTransition;
    L9:
        Transition r22 = r2.clone();
        sceneChangeSetup(r1, r22);
        Scene.setCurrentScene(r1, null);
        sceneChangeRunTransition(r1, r22);
        return;
    }

    public static ArrayMap<ViewGroup, ArrayList<Transition>> getRunningTransitions() {
        WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>> r0 = sRunningTransitions.get();
        if (r0 == null) goto L7;
        ArrayMap<ViewGroup, ArrayList<Transition>> r02 = r0.get();
        if (r02 == null) goto L7;
        return r02;
    L7:
        ArrayMap<ViewGroup, ArrayList<Transition>> r03 = new ArrayMap();
        WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>> r1 = new WeakReference(r03);
        sRunningTransitions.set(r1);
        return r03;
    }

    private static void sceneChangeRunTransition(ViewGroup r1, Transition r2) {
        if (r2 == null) goto L6;
        if (r1 == null) goto L7;
        MultiListener r0 = new MultiListener(r2, r1);
        r1.addOnAttachStateChangeListener(r0);
        r1.getViewTreeObserver().addOnPreDrawListener(r0);
        return;
    L7:
        return;
    }

    private static void sceneChangeSetup(ViewGroup r4, Transition r5) {
        ArrayList<Transition> r0 = getRunningTransitions().get(r4);
        if (r0 != null) goto L5;
    L9:
        if (r5 == null) goto L11;
        r5.captureValues(r4, true);
    L11:
        Scene.getCurrentScene(r4);
        return;
    L5:
        if (r0.size() <= 0) goto L9;
        int r1 = r0.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L9;
        Transition r3 = r0.get(r2);
        r2 = r2 + 1;
        r3.pause(r4);
        goto L7
    }
}
