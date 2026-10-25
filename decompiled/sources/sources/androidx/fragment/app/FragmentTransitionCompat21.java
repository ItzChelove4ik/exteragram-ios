package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class FragmentTransitionCompat21 extends FragmentTransitionImpl {

    /* renamed from: androidx.fragment.app.FragmentTransitionCompat21$1, reason: invalid class name */
    public class AnonymousClass1 extends Transition.EpicenterCallback {
        final /* synthetic */ FragmentTransitionCompat21 this$0;
        final /* synthetic */ Rect val$epicenter;

        public AnonymousClass1(FragmentTransitionCompat21 r1, Rect r2) {
            this.this$0 = r1;
            this.val$epicenter = r2;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public Rect onGetEpicenter(Transition r1) {
            return this.val$epicenter;
        }
    }

    /* renamed from: androidx.fragment.app.FragmentTransitionCompat21$2, reason: invalid class name */
    public class AnonymousClass2 implements Transition.TransitionListener {
        final /* synthetic */ FragmentTransitionCompat21 this$0;
        final /* synthetic */ ArrayList val$exitingViews;
        final /* synthetic */ View val$fragmentView;

        public AnonymousClass2(FragmentTransitionCompat21 r1, View r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$fragmentView = r2;
            this.val$exitingViews = r3;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r4) {
            Api19Impl.removeListener(r4, this);
            this.val$fragmentView.setVisibility(8);
            int r42 = this.val$exitingViews.size();
            int r1 = 0;
        L3:
            if (r1 >= r42) goto L5;
            ((View) this.val$exitingViews.get(r1)).setVisibility(0);
            r1 = r1 + 1;
            goto L3
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
            Api19Impl.removeListener(r1, this);
            Api19Impl.addListener(r1, this);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentTransitionCompat21$3, reason: invalid class name */
    public class AnonymousClass3 implements Transition.TransitionListener {
        final /* synthetic */ FragmentTransitionCompat21 this$0;
        final /* synthetic */ Object val$enterTransition;
        final /* synthetic */ ArrayList val$enteringViews;
        final /* synthetic */ Object val$exitTransition;
        final /* synthetic */ ArrayList val$exitingViews;
        final /* synthetic */ Object val$sharedElementTransition;
        final /* synthetic */ ArrayList val$sharedElementsIn;

        public AnonymousClass3(FragmentTransitionCompat21 r1, Object r2, ArrayList r3, Object r4, ArrayList r5, Object r6, ArrayList r7) {
            this.this$0 = r1;
            this.val$enterTransition = r2;
            this.val$enteringViews = r3;
            this.val$exitTransition = r4;
            this.val$exitingViews = r5;
            this.val$sharedElementTransition = r6;
            this.val$sharedElementsIn = r7;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            Api19Impl.removeListener(r1, this);
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r4) {
            Object r42 = this.val$enterTransition;
            if (r42 == null) goto L5;
            this.this$0.replaceTargets(r42, this.val$enteringViews, null);
        L5:
            Object r43 = this.val$exitTransition;
            if (r43 == null) goto L8;
            this.this$0.replaceTargets(r43, this.val$exitingViews, null);
        L8:
            Object r44 = this.val$sharedElementTransition;
            if (r44 == null) goto L12;
            this.this$0.replaceTargets(r44, this.val$sharedElementsIn, null);
            return;
        }
    }

    /* renamed from: androidx.fragment.app.FragmentTransitionCompat21$4, reason: invalid class name */
    public class AnonymousClass4 implements Transition.TransitionListener {
        final /* synthetic */ FragmentTransitionCompat21 this$0;
        final /* synthetic */ Runnable val$transitionCompleteRunnable;

        public AnonymousClass4(FragmentTransitionCompat21 r1, Runnable r2) {
            this.this$0 = r1;
            this.val$transitionCompleteRunnable = r2;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            this.val$transitionCompleteRunnable.run();
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
        }
    }

    /* renamed from: androidx.fragment.app.FragmentTransitionCompat21$5, reason: invalid class name */
    public class AnonymousClass5 extends Transition.EpicenterCallback {
        final /* synthetic */ FragmentTransitionCompat21 this$0;
        final /* synthetic */ Rect val$epicenter;

        public AnonymousClass5(FragmentTransitionCompat21 r1, Rect r2) {
            this.this$0 = r1;
            this.val$epicenter = r2;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public Rect onGetEpicenter(Transition r1) {
            Rect r12 = this.val$epicenter;
            if (r12 != null) goto L5;
            return null;
        L5:
            if (r12.isEmpty() == false) goto L8;
            return null;
        L8:
            return this.val$epicenter;
        }
    }

    public static class Api19Impl {
        public static void addListener(Transition r0, Transition.TransitionListener r1) {
            r0.addListener(r1);
        }

        public static void removeListener(Transition r0, Transition.TransitionListener r1) {
            r0.removeListener(r1);
        }
    }

    public FragmentTransitionCompat21() {
    }

    private static boolean hasSimpleTarget(Transition r1) {
        if (FragmentTransitionImpl.isNullOrEmpty(r1.getTargetIds()) == true) goto L5;
        return true;
    L5:
        if (FragmentTransitionImpl.isNullOrEmpty(r1.getTargetNames()) == true) goto L7;
        return true;
    L7:
        if (FragmentTransitionImpl.isNullOrEmpty(r1.getTargetTypes()) == false) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void addTarget(Object r1, View r2) {
        if (r1 == null) goto L5;
        ((Transition) r1).addTarget(r2);
        return;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void addTargets(Object r4, ArrayList<View> r5) {
        Transition r42 = (Transition) r4;
        if (r42 == null) goto L21;
        int r1 = 0;
        if ((r42 instanceof TransitionSet) == false) goto L11;
        TransitionSet r43 = (TransitionSet) r42;
        int r0 = r43.getTransitionCount();
    L8:
        if (r1 >= r0) goto L22;
        addTargets(r43.getTransitionAt(r1), r5);
        r1 = r1 + 1;
        goto L8
    L22:
        return;
    L11:
        if (hasSimpleTarget(r42) == false) goto L13;
        return;
    L13:
        if (FragmentTransitionImpl.isNullOrEmpty(r42.getTargets()) == false) goto L20;
        int r3 = r5.size();
    L15:
        if (r1 >= r3) goto L23;
        r42.addTarget(r5.get(r1));
        r1 = r1 + 1;
        goto L15
    L23:
        return;
    L20:
        return;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void beginDelayedTransition(ViewGroup r1, Object r2) {
        TransitionManager.beginDelayedTransition(r1, (Transition) r2);
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public boolean canHandle(Object r1) {
        return r1 instanceof Transition;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object cloneTransition(Object r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        return ((Transition) r1).clone();
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object mergeTransitionsInSequence(Object r1, Object r2, Object r3) {
        Transition r12 = (Transition) r1;
        Transition r22 = (Transition) r2;
        Transition r32 = (Transition) r3;
        if (r12 == null) goto L6;
        if (r22 == null) goto L6;
        r12 = new TransitionSet().addTransition(r12).addTransition(r22).setOrdering(1);
    L11:
        if (r32 == null) goto L17;
        TransitionSet r0 = new TransitionSet();
        if (r12 == null) goto L15;
        r0.addTransition(r12);
    L15:
        r0.addTransition(r32);
        return r0;
    L17:
        return r12;
    L6:
        if (r12 != null) goto L11;
        if (r22 == null) goto L10;
        r12 = r22;
        goto L11
    L10:
        r12 = null;
        goto L11
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object mergeTransitionsTogether(Object r1, Object r2, Object r3) {
        TransitionSet r0 = new TransitionSet();
        if (r1 == null) goto L5;
        r0.addTransition((Transition) r1);
    L5:
        if (r2 == null) goto L7;
        r0.addTransition((Transition) r2);
    L7:
        if (r3 == null) goto L9;
        r0.addTransition((Transition) r3);
    L9:
        return r0;
    }

    public void replaceTargets(Object r4, ArrayList<View> r5, ArrayList<View> r6) {
        Transition r42 = (Transition) r4;
        int r1 = 0;
        if ((r42 instanceof TransitionSet) == false) goto L8;
        TransitionSet r43 = (TransitionSet) r42;
        int r0 = r43.getTransitionCount();
    L5:
        if (r1 >= r0) goto L31;
        replaceTargets(r43.getTransitionAt(r1), r5, r6);
        r1 = r1 + 1;
        goto L5
    L31:
        return;
    L8:
        if (hasSimpleTarget(r42) == true) goto L23;
        List<View> r3 = r42.getTargets();
        if (r3 != null) goto L12;
        return;
    L12:
        if (r3.size() == r5.size()) goto L14;
        return;
    L14:
        if (r3.containsAll(r5) == false) goto L30;
        if (r6 != null) goto L17;
        int r32 = 0;
    L18:
        if (r1 >= r32) goto L20;
        r42.addTarget(r6.get(r1));
        r1 = r1 + 1;
        goto L18
    L20:
        int r33 = r5.size() - 1;
    L21:
        if (r33 < 0) goto L32;
        r42.removeTarget(r5.get(r33));
        r33 = r33 - 1;
        goto L21
    L32:
        return;
    L17:
        r32 = r6.size();
        goto L18
    L30:
        return;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void scheduleHideFragmentView(Object r2, View r3, ArrayList<View> r4) {
        ((Transition) r2).addListener(new AnonymousClass2(this, r3, r4));
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void scheduleRemoveTargets(Object r9, Object r10, ArrayList<View> r11, Object r12, ArrayList<View> r13, Object r14, ArrayList<View> r15) {
        ((Transition) r9).addListener(new AnonymousClass3(this, r10, r11, r12, r13, r14, r15));
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setEpicenter(Object r2, Rect r3) {
        if (r2 == null) goto L5;
        ((Transition) r2).setEpicenterCallback(new AnonymousClass5(this, r3));
        return;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setEpicenter(Object r2, View r3) {
        if (r3 == null) goto L5;
        Rect r0 = new Rect();
        getBoundsOnScreen(r3, r0);
        ((Transition) r2).setEpicenterCallback(new AnonymousClass1(this, r0));
        return;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setListenerForTransitionEnd(Fragment r1, Object r2, CancellationSignal r3, Runnable r4) {
        ((Transition) r2).addListener(new AnonymousClass4(this, r4));
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void setSharedElementTargets(Object r5, View r6, ArrayList<View> r7) {
        TransitionSet r52 = (TransitionSet) r5;
        List<View> r0 = r52.getTargets();
        r0.clear();
        int r1 = r7.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        FragmentTransitionImpl.bfsAddViewChildren(r0, r7.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        r0.add(r6);
        r7.add(r6);
        addTargets(r52, r7);
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public void swapSharedElementTargets(Object r2, ArrayList<View> r3, ArrayList<View> r4) {
        TransitionSet r22 = (TransitionSet) r2;
        if (r22 == null) goto L6;
        r22.getTargets().clear();
        r22.getTargets().addAll(r4);
        replaceTargets(r22, r3, r4);
        return;
    }

    @Override // androidx.fragment.app.FragmentTransitionImpl
    public Object wrapTransitionInSet(Object r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        TransitionSet r0 = new TransitionSet();
        r0.addTransition((Transition) r1);
        return r0;
    }
}
