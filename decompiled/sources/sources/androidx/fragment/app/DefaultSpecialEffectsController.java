package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import retrofit2.Utils$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ List val$awaitingContainerChanges;
        final /* synthetic */ SpecialEffectsController.Operation val$operation;

        public AnonymousClass1(DefaultSpecialEffectsController r1, List r2, SpecialEffectsController.Operation r3) {
            this.this$0 = r1;
            this.val$awaitingContainerChanges = r2;
            this.val$operation = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.val$awaitingContainerChanges.contains(this.val$operation) == false) goto L6;
            this.val$awaitingContainerChanges.remove(this.val$operation);
            this.this$0.applyContainerChanges(this.val$operation);
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = null;

        static {
            int[] r0 = new int[SpecialEffectsController.Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = r0;
            r0[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ AnimationInfo val$animationInfo;
        final /* synthetic */ ViewGroup val$container;
        final /* synthetic */ boolean val$isHideOperation;
        final /* synthetic */ SpecialEffectsController.Operation val$operation;
        final /* synthetic */ View val$viewToAnimate;

        public AnonymousClass2(DefaultSpecialEffectsController r1, ViewGroup r2, View r3, boolean r4, SpecialEffectsController.Operation r5, AnimationInfo r6) {
            this.this$0 = r1;
            this.val$container = r2;
            this.val$viewToAnimate = r3;
            this.val$isHideOperation = r4;
            this.val$operation = r5;
            this.val$animationInfo = r6;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$container.endViewTransition(this.val$viewToAnimate);
            if (this.val$isHideOperation == false) goto L5;
            this.val$operation.getFinalState().applyState(this.val$viewToAnimate);
        L5:
            this.val$animationInfo.completeSpecialEffect();
            if (FragmentManager.isLoggingEnabled(2) == false) goto L9;
            Log.v("FragmentManager", "Animator from operation " + this.val$operation + " has ended.");
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$3, reason: invalid class name */
    public class AnonymousClass3 implements CancellationSignal.OnCancelListener {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ Animator val$animator;
        final /* synthetic */ SpecialEffectsController.Operation val$operation;

        public AnonymousClass3(DefaultSpecialEffectsController r1, Animator r2, SpecialEffectsController.Operation r3) {
            this.this$0 = r1;
            this.val$animator = r2;
            this.val$operation = r3;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            this.val$animator.end();
            if (FragmentManager.isLoggingEnabled(2) == false) goto L6;
            Log.v("FragmentManager", "Animator from operation " + this.val$operation + " has been canceled.");
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$4, reason: invalid class name */
    public class AnonymousClass4 implements Animation.AnimationListener {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ AnimationInfo val$animationInfo;
        final /* synthetic */ ViewGroup val$container;
        final /* synthetic */ SpecialEffectsController.Operation val$operation;
        final /* synthetic */ View val$viewToAnimate;

        /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$4$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                AnonymousClass4 r0 = this.this$1;
                r0.val$container.endViewTransition(r0.val$viewToAnimate);
                this.this$1.val$animationInfo.completeSpecialEffect();
            }
        }

        public AnonymousClass4(DefaultSpecialEffectsController r1, SpecialEffectsController.Operation r2, ViewGroup r3, View r4, AnimationInfo r5) {
            this.this$0 = r1;
            this.val$operation = r2;
            this.val$container = r3;
            this.val$viewToAnimate = r4;
            this.val$animationInfo = r5;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation r2) {
            this.val$container.post(new AnonymousClass1(this));
            if (FragmentManager.isLoggingEnabled(2) == false) goto L6;
            Log.v("FragmentManager", "Animation from operation " + this.val$operation + " has ended.");
            return;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation r1) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation r2) {
            if (FragmentManager.isLoggingEnabled(2) == false) goto L6;
            Log.v("FragmentManager", "Animation from operation " + this.val$operation + " has reached onAnimationStart.");
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$5, reason: invalid class name */
    public class AnonymousClass5 implements CancellationSignal.OnCancelListener {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ AnimationInfo val$animationInfo;
        final /* synthetic */ ViewGroup val$container;
        final /* synthetic */ SpecialEffectsController.Operation val$operation;
        final /* synthetic */ View val$viewToAnimate;

        public AnonymousClass5(DefaultSpecialEffectsController r1, View r2, ViewGroup r3, AnimationInfo r4, SpecialEffectsController.Operation r5) {
            this.this$0 = r1;
            this.val$viewToAnimate = r2;
            this.val$container = r3;
            this.val$animationInfo = r4;
            this.val$operation = r5;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            this.val$viewToAnimate.clearAnimation();
            this.val$container.endViewTransition(this.val$viewToAnimate);
            this.val$animationInfo.completeSpecialEffect();
            if (FragmentManager.isLoggingEnabled(2) == false) goto L6;
            Log.v("FragmentManager", "Animation from operation " + this.val$operation + " has been cancelled.");
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$6, reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ SpecialEffectsController.Operation val$firstOut;
        final /* synthetic */ boolean val$isPop;
        final /* synthetic */ SpecialEffectsController.Operation val$lastIn;
        final /* synthetic */ ArrayMap val$lastInViews;

        public AnonymousClass6(DefaultSpecialEffectsController r1, SpecialEffectsController.Operation r2, SpecialEffectsController.Operation r3, boolean r4, ArrayMap r5) {
            this.this$0 = r1;
            this.val$lastIn = r2;
            this.val$firstOut = r3;
            this.val$isPop = r4;
            this.val$lastInViews = r5;
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentTransition.callSharedElementStartEnd(this.val$lastIn.getFragment(), this.val$firstOut.getFragment(), this.val$isPop, this.val$lastInViews, false);
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$7, reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ FragmentTransitionImpl val$impl;
        final /* synthetic */ Rect val$lastInEpicenterRect;
        final /* synthetic */ View val$lastInEpicenterView;

        public AnonymousClass7(DefaultSpecialEffectsController r1, FragmentTransitionImpl r2, View r3, Rect r4) {
            this.this$0 = r1;
            this.val$impl = r2;
            this.val$lastInEpicenterView = r3;
            this.val$lastInEpicenterRect = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$impl.getBoundsOnScreen(this.val$lastInEpicenterView, this.val$lastInEpicenterRect);
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$8, reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ ArrayList val$transitioningViews;

        public AnonymousClass8(DefaultSpecialEffectsController r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$transitioningViews = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentTransition.setViewVisibility(this.val$transitioningViews, 4);
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$9, reason: invalid class name */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ DefaultSpecialEffectsController this$0;
        final /* synthetic */ SpecialEffectsController.Operation val$operation;
        final /* synthetic */ TransitionInfo val$transitionInfo;

        public AnonymousClass9(DefaultSpecialEffectsController r1, TransitionInfo r2, SpecialEffectsController.Operation r3) {
            this.this$0 = r1;
            this.val$transitionInfo = r2;
            this.val$operation = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$transitionInfo.completeSpecialEffect();
            if (FragmentManager.isLoggingEnabled(2) == false) goto L6;
            Log.v("FragmentManager", "Transition for operation " + this.val$operation + "has completed");
            return;
        }
    }

    public static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim;

        public AnimationInfo(SpecialEffectsController.Operation r1, CancellationSignal r2, boolean r3) {
            super(r1, r2);
            this.mLoadedAnim = false;
            this.mIsPop = r3;
        }

        public FragmentAnim.AnimationOrAnimator getAnimation(Context r5) {
            if (this.mLoadedAnim == true) goto L5;
            Fragment r0 = getOperation().getFragment();
            if (getOperation().getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) goto L9;
            boolean r1 = true;
        L10:
            FragmentAnim.AnimationOrAnimator r52 = FragmentAnim.loadAnimation(r5, r0, r1, this.mIsPop);
            this.mAnimation = r52;
            this.mLoadedAnim = true;
            return r52;
        L9:
            r1 = false;
            goto L10
        L5:
            return this.mAnimation;
        }
    }

    public static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation r1, CancellationSignal r2) {
            this.mOperation = r1;
            this.mSignal = r2;
        }

        public void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }

        public SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        public CancellationSignal getSignal() {
            return this.mSignal;
        }

        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State r0 = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State r2 = this.mOperation.getFinalState();
            if (r0 == r2) goto L10;
            SpecialEffectsController.Operation.State r1 = SpecialEffectsController.Operation.State.VISIBLE;
            if (r0 == r1) goto L8;
            if (r2 != r1) goto L13;
            return false;
        L13:
            return true;
        L8:
            return false;
        L10:
            return true;
        }
    }

    public static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        public TransitionInfo(SpecialEffectsController.Operation r2, CancellationSignal r3, boolean r4, boolean r5) {
            super(r2, r3);
            if (r2.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) goto L12;
            if (r4 == false) goto L6;
            Object r32 = r2.getFragment().getReenterTransition();
        L7:
            this.mTransition = r32;
            if (r4 == false) goto L10;
            boolean r33 = r2.getFragment().getAllowReturnTransitionOverlap();
        L11:
            this.mOverlapAllowed = r33;
        L16:
            if (r5 == false) goto L22;
            if (r4 == false) goto L20;
            this.mSharedElementTransition = r2.getFragment().getSharedElementReturnTransition();
            return;
        L20:
            this.mSharedElementTransition = r2.getFragment().getSharedElementEnterTransition();
            return;
        L22:
            this.mSharedElementTransition = null;
            return;
        L10:
            r33 = r2.getFragment().getAllowEnterTransitionOverlap();
            goto L11
        L6:
            r32 = r2.getFragment().getEnterTransition();
            goto L7
        L12:
            if (r4 == false) goto L14;
            Object r34 = r2.getFragment().getReturnTransition();
        L15:
            this.mTransition = r34;
            this.mOverlapAllowed = true;
            goto L16
        L14:
            r34 = r2.getFragment().getExitTransition();
            goto L15
        }

        private FragmentTransitionImpl getHandlingImpl(Object r4) {
            if (r4 != null) goto L5;
            return null;
        L5:
            FragmentTransitionImpl r0 = FragmentTransition.PLATFORM_IMPL;
            if (r0 != null) goto L8;
        L10:
            FragmentTransitionImpl r02 = FragmentTransition.SUPPORT_IMPL;
            if (r02 != null) goto L13;
        L15:
            StringBuilder r1 = new StringBuilder("Transition ");
            r1.append(r4);
            Fragment r3 = getOperation().getFragment();
            r1.append(" for fragment ");
            r1.append(r3);
            r1.append(" is not a valid framework Transition or AndroidX Transition");
            throw new IllegalArgumentException(r1.toString());
        L13:
            if (r02.canHandle(r4) == false) goto L15;
            return r02;
        L8:
            if (r0.canHandle(r4) == false) goto L10;
            return r0;
        }

        public FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl r0 = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl r1 = getHandlingImpl(this.mSharedElementTransition);
            if (r0 == null) goto L9;
            if (r1 == null) goto L9;
            if (r0 == r1) goto L9;
            StringBuilder r02 = new StringBuilder("Mixing framework transitions and AndroidX transitions is not allowed. Fragment ");
            r02.append(getOperation().getFragment());
            r02.append(" returned Transition ");
            r02.append(this.mTransition);
            Utils$$ExternalSyntheticBUOutline1.m(r02, " which uses a different Transition  type than its shared element transition ", this.mSharedElementTransition);
            return null;
        L9:
            if (r0 == null) goto L11;
            return r0;
        L11:
            return r1;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        public Object getTransition() {
            return this.mTransition;
        }

        public boolean hasSharedElementTransition() {
            if (this.mSharedElementTransition == null) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }
    }

    public DefaultSpecialEffectsController(ViewGroup r1) {
        super(r1);
    }

    private void startAnimations(List<AnimationInfo> r18, List<SpecialEffectsController.Operation> r19, boolean r20, Map<SpecialEffectsController.Operation, Boolean> r21) {
        ViewGroup r3 = getContainer();
        Context r7 = r3.getContext();
        ArrayList r8 = new ArrayList();
        Iterator<AnimationInfo> r9 = r18.iterator();
        int r10 = 0;
        boolean r6 = false;
    L4:
        if (r9.hasNext() == false) goto L32;
        AnimationInfo r0 = r9.next();
        if (r0.isVisibilityUnchanged() == true) goto L7;
        FragmentAnim.AnimationOrAnimator r1 = r0.getAnimation(r7);
        if (r1 == null) goto L11;
        Animator r14 = r1.animator;
        if (r14 == null) goto L14;
        SpecialEffectsController.Operation r5 = r0.getOperation();
        Fragment r12 = r5.getFragment();
        if (Boolean.TRUE.equals(r21.get(r5)) == true) goto L18;
        if (r5.getFinalState() != SpecialEffectsController.Operation.State.GONE) goto L24;
        boolean r4 = true;
    L26:
        if (r4 == false) goto L28;
        r19.remove(r5);
    L28:
        View r13 = r12.mView;
        r3.startViewTransition(r13);
        ViewGroup r2 = r3;
        r3 = r2;
        r14.addListener(new AnonymousClass2(this, r2, r13, r4, r5, r0));
        r14.setTarget(r13);
        r14.start();
        if (FragmentManager.isLoggingEnabled(2) == false) goto L31;
        Log.v("FragmentManager", "Animator from operation " + r5 + " has started.");
    L31:
        r0.getSignal().setOnCancelListener(new AnonymousClass3(this, r14, r5));
        r6 = true;
        goto L4
    L24:
        r4 = false;
        goto L26
    L18:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L20;
        Log.v("FragmentManager", "Ignoring Animator set on " + r12 + " as this Fragment was involved in a Transition.");
    L20:
        r0.completeSpecialEffect();
        goto L4
    L14:
        r8.add(r0);
        goto L4
    L11:
        r0.completeSpecialEffect();
        goto L4
    L7:
        r0.completeSpecialEffect();
        goto L4
    L32:
        int r92 = r8.size();
    L33:
        if (r10 >= r92) goto L52;
        Object r02 = r8.get(r10);
        r10 = r10 + 1;
        AnimationInfo r42 = (AnimationInfo) r02;
        SpecialEffectsController.Operation r52 = r42.getOperation();
        Fragment r03 = r52.getFragment();
        if (r20 == true) goto L37;
        if (r6 == true) goto L42;
        View r04 = r03.mView;
        Animation r15 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(r42.getAnimation(r7))).animation);
        if (r52.getFinalState() == SpecialEffectsController.Operation.State.REMOVED) goto L48;
        r04.startAnimation(r15);
        r42.completeSpecialEffect();
        SpecialEffectsController.Operation r22 = r52;
        AnimationInfo r53 = r42;
        View r43 = r04;
    L51:
        r53.getSignal().setOnCancelListener(new AnonymousClass5(this, r43, r3, r53, r22));
        goto L33
    L48:
        r3.startViewTransition(r04);
        FragmentAnim.EndViewTransitionAnimation r142 = new FragmentAnim.EndViewTransitionAnimation(r15, r3, r04);
        r22 = r52;
        r53 = r42;
        r43 = r04;
        r142.setAnimationListener(new AnonymousClass4(this, r22, r3, r43, r53));
        r43.startAnimation(r142);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L51;
        Log.v("FragmentManager", "Animation from operation " + r22 + " has started.");
        goto L51
    L42:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L44;
        Log.v("FragmentManager", "Ignoring Animation set on " + r03 + " as Animations cannot run alongside Animators.");
    L44:
        r42.completeSpecialEffect();
        goto L33
    L37:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L39;
        Log.v("FragmentManager", "Ignoring Animation set on " + r03 + " as Animations cannot run alongside Transitions.");
    L39:
        r42.completeSpecialEffect();
        goto L33
    }

    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> r31, List<SpecialEffectsController.Operation> r32, boolean r33, SpecialEffectsController.Operation r34, SpecialEffectsController.Operation r35) {
        boolean r4 = r33;
        HashMap r6 = new HashMap();
        Iterator<TransitionInfo> r0 = r31.iterator();
        FragmentTransitionImpl r8 = null;
    L4:
        if (r0.hasNext() == false) goto L16;
        TransitionInfo r2 = r0.next();
        if (r2.isVisibilityUnchanged() == true) goto L4;
        FragmentTransitionImpl r3 = r2.getHandlingImpl();
        if (r8 == null) goto L10;
        if (r3 == null) goto L4;
        if (r8 == r3) goto L4;
        StringBuilder r1 = new StringBuilder("Mixing framework transitions and AndroidX transitions is not allowed. Fragment ");
        r1.append(r2.getOperation().getFragment());
        Object r22 = r2.getTransition();
        r1.append(" returned Transition ");
        r1.append(r22);
        r1.append(" which uses a different Transition  type than other Fragments.");
        throw new IllegalArgumentException(r1.toString());
    L10:
        r8 = r3;
        goto L4
    L16:
        if (r8 != null) goto L21;
        Iterator<TransitionInfo> r02 = r31.iterator();
    L19:
        if (r02.hasNext() == false) goto L137;
        TransitionInfo r12 = r02.next();
        r6.put(r12.getOperation(), Boolean.FALSE);
        r12.completeSpecialEffect();
    L137:
        return r6;
    L21:
        View r9 = new View(getContainer().getContext());
        Rect r10 = new Rect();
        ArrayList<View> r11 = new ArrayList();
        ArrayList<View> r15 = new ArrayList();
        ArrayMap r122 = new ArrayMap();
        Iterator<TransitionInfo> r16 = r31.iterator();
        Object r03 = null;
        View r14 = null;
        boolean r17 = false;
    L22:
        String r5 = "FragmentManager";
        if (r16.hasNext() == false) goto L67;
        TransitionInfo r23 = r16.next();
        if (r23.hasSharedElementTransition() == false) goto L65;
        if (r34 == null) goto L65;
        if (r35 == null) goto L65;
        Object r04 = r8.wrapTransitionInSet(r8.cloneTransition(r23.getSharedElementTransition()));
        ArrayList<String> r24 = r35.getFragment().getSharedElementSourceNames();
        ArrayList<String> r36 = r34.getFragment().getSharedElementSourceNames();
        ArrayList<String> r13 = r34.getFragment().getSharedElementTargetNames();
        int r7 = 0;
    L30:
        if (r7 >= r13.size()) goto L35;
        int r05 = r24.indexOf(r13.get(r7));
        ArrayList<String> r222 = r13;
        if (r05 == (-1)) goto L34;
        r24.set(r05, r36.get(r7));
    L34:
        r7 = r7 + 1;
        r13 = r222;
        goto L30
    L35:
        ArrayList<String> r72 = r35.getFragment().getSharedElementTargetNames();
        if (r4 == true) goto L38;
        r34.getFragment().getExitTransitionCallback();
        r35.getFragment().getEnterTransitionCallback();
    L39:
        int r06 = r24.size();
        int r37 = 0;
    L40:
        if (r37 >= r06) goto L43;
        r122.put(r24.get(r37), r72.get(r37));
        r37 = r37 + 1;
        r06 = r06;
        goto L40
    L43:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L51;
        Log.v("FragmentManager", ">>> entering view names <<<");
        int r07 = r72.size();
        int r38 = 0;
    L46:
        if (r38 >= r07) goto L48;
        String r19 = r72.get(r38);
        Log.v("FragmentManager", "Name: " + r19);
        r38 = r38 + 1;
        r07 = r07;
        goto L46
    L48:
        Log.v("FragmentManager", ">>> exiting view names <<<");
        int r08 = r24.size();
        int r39 = 0;
    L49:
        if (r39 >= r08) goto L51;
        String r192 = r24.get(r39);
        Log.v("FragmentManager", "Name: " + r192);
        r39 = r39 + 1;
        r08 = r08;
    L51:
        ArrayMap<String, View> r132 = new ArrayMap();
        findNamedViews(r132, r34.getFragment().mView);
        r132.retainAll(r24);
        r122.retainAll(r132.keySet());
        ArrayMap<String, View> r52 = new ArrayMap();
        findNamedViews(r52, r35.getFragment().mView);
        r52.retainAll(r72);
        r52.retainAll(r122.values());
        FragmentTransition.retainValues(r122, r52);
        retainMatchingViews(r132, r122.keySet());
        retainMatchingViews(r52, r122.values());
        if (r122.isEmpty() == false) goto L54;
        r11.clear();
        r15.clear();
        View r73 = r9;
        Rect r53 = r10;
        ArrayList<View> r92 = r11;
        ArrayMap r223 = r122;
        ArrayList<View> r102 = r15;
        r03 = null;
    L66:
        r4 = r33;
        r11 = r92;
        r15 = r102;
        r122 = r223;
        r10 = r53;
        r9 = r73;
        goto L22
    L54:
        r223 = r122;
        FragmentTransition.callSharedElementStartEnd(r35.getFragment(), r34.getFragment(), r4, r132, true);
        View r21 = r14;
        OneShotPreDrawListener.add(getContainer(), new AnonymousClass6(this, r35, r34, r4, r52));
        r11.addAll(r132.values());
        if (r24.isEmpty() == true) goto L57;
        int r42 = 0;
        View r142 = r132.get(r24.get(0));
        r8.setEpicenter(r04, r142);
        r21 = r142;
    L58:
        r15.addAll(r52.values());
        if (r72.isEmpty() == true) goto L63;
        View r09 = r52.get(r72.get(r42));
        if (r09 == null) goto L63;
        OneShotPreDrawListener.add(getContainer(), new AnonymousClass7(this, r8, r09, r10));
        r17 = true;
    L63:
        r8.setSharedElementTargets(r04, r9, r11);
        r53 = r10;
        ArrayList<View> r74 = r11;
        r73 = r9;
        r8.scheduleRemoveTargets(r04, null, null, null, null, r04, r15);
        r102 = r15;
        Boolean r112 = Boolean.TRUE;
        r6.put(r34, r112);
        r6.put(r35, r112);
        r92 = r74;
        r03 = r04;
    L64:
        r14 = r21;
        goto L66
    L57:
        r42 = 0;
        goto L58
    L38:
        r34.getFragment().getEnterTransitionCallback();
        r35.getFragment().getExitTransitionCallback();
    L65:
        r73 = r9;
        r53 = r10;
        r92 = r11;
        r223 = r122;
        r21 = r14;
        r102 = r15;
        goto L64
    L67:
        SpecialEffectsController.Operation r310 = r34;
        SpecialEffectsController.Operation r25 = r35;
        View r75 = r9;
        ArrayList<View> r93 = r11;
        ArrayMap r224 = r122;
        View r212 = r14;
        Rect r113 = r10;
        ArrayList<View> r103 = r15;
        ArrayList r123 = new ArrayList();
        Iterator<TransitionInfo> r162 = r31.iterator();
        Object r133 = null;
        Object r143 = null;
    L69:
        if (r162.hasNext() == false) goto L107;
        TransitionInfo r20 = r162.next();
        if (r20.isVisibilityUnchanged() == true) goto L72;
        Object r43 = r8.cloneTransition(r20.getTransition());
        SpecialEffectsController.Operation r152 = r20.getOperation();
        if (r03 == null) goto L78;
        if (r152 == r310) goto L77;
        if (r152 != r25) goto L78;
    L77:
        boolean r242 = true;
    L79:
        if (r43 != null) goto L83;
        if (r242 == true) goto L82;
        r6.put(r152, Boolean.FALSE);
        r20.completeSpecialEffect();
    L82:
        View r18 = r75;
        ArrayList<View> r332 = r93;
        ArrayList<View> r28 = r103;
        Rect r44 = r113;
        View r76 = r212;
        String r213 = r5;
        ArrayList r54 = r123;
    L106:
        r93 = r332;
        r310 = r34;
        r25 = r35;
        r113 = r44;
        r123 = r54;
        r5 = r213;
        r103 = r28;
        r212 = r76;
        r75 = r18;
        goto L69
    L83:
        Rect r252 = r113;
        ArrayList<View> r114 = new ArrayList();
        ArrayList r333 = r123;
        captureTransitioningViews(r114, r152.getFragment().mView);
        if (r242 == false) goto L89;
        if (r152 != r310) goto L87;
        r114.removeAll(r93);
        goto L89
    L87:
        r114.removeAll(r103);
    L89:
        if (r114.isEmpty() == false) goto L91;
        r8.addTarget(r43, r75);
        r18 = r75;
        r28 = r103;
        Object r26 = r133;
        Object r311 = r143;
        SpecialEffectsController.Operation r124 = r152;
        r76 = r212;
        r213 = r5;
        r54 = r333;
        r332 = r93;
        Object r94 = r43;
        r44 = r252;
    L96:
        if (r124.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) goto L100;
        r54.addAll(r114);
        if (r17 == false) goto L101;
        r8.setEpicenter(r94, r44);
    L101:
        r6.put(r124, Boolean.TRUE);
        if (r20.isOverlapAllowed() == false) goto L105;
        r26 = r8.mergeTransitionsTogether(r26, r94, null);
    L104:
        r133 = r26;
        r143 = r311;
        goto L106
    L105:
        r311 = r8.mergeTransitionsTogether(r311, r94, null);
        goto L104
    L100:
        r8.setEpicenter(r94, r76);
        goto L101
    L91:
        r8.addTargets(r43, r114);
        r28 = r103;
        r18 = r75;
        r76 = r212;
        r311 = r143;
        r26 = r133;
        r213 = r5;
        r54 = r333;
        r332 = r93;
        r94 = r43;
        r44 = r252;
        r8.scheduleRemoveTargets(r94, r43, r114, null, null, null, null);
        if (r152.getFinalState() != SpecialEffectsController.Operation.State.GONE) goto L94;
        r124 = r152;
        r32.remove(r124);
        ArrayList<View> r134 = new ArrayList(r114);
        r134.remove(r124.getFragment().mView);
        r8.scheduleHideFragmentView(r94, r124.getFragment().mView, r134);
        OneShotPreDrawListener.add(getContainer(), new AnonymousClass8(this, r114));
        goto L96
    L94:
        r124 = r152;
    L78:
        r242 = false;
        goto L79
    L72:
        r6.put(r20.getOperation(), Boolean.FALSE);
        r20.completeSpecialEffect();
        goto L69
    L107:
        String r214 = r5;
        ArrayList<View> r334 = r93;
        ArrayList<View> r282 = r103;
        ArrayList r55 = r123;
        Object r27 = r8.mergeTransitionsInSequence(r133, r143, r03);
        if (r27 == null) goto L137;
        Iterator<TransitionInfo> r312 = r31.iterator();
    L112:
        if (r312.hasNext() == false) goto L135;
        TransitionInfo r45 = r312.next();
        if (r45.isVisibilityUnchanged() == true) goto L112;
        Object r77 = r45.getTransition();
        SpecialEffectsController.Operation r95 = r45.getOperation();
        if (r03 == null) goto L121;
        if (r95 == r34) goto L120;
        if (r95 != r35) goto L121;
    L120:
        boolean r135 = true;
    L122:
        if (r77 != null) goto L127;
        if (r135 == true) goto L127;
        String r125 = r214;
    L134:
        r214 = r125;
    L127:
        if (ViewCompat.isLaidOut(getContainer()) == false) goto L129;
        r125 = r214;
        r8.setListenerForTransitionEnd(r45.getOperation().getFragment(), r27, r45.getSignal(), new AnonymousClass9(this, r45, r95));
        goto L134
    L129:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L131;
        r125 = r214;
        Log.v(r125, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + r95);
    L132:
        r45.completeSpecialEffect();
        goto L134
    L131:
        r125 = r214;
    L121:
        r135 = false;
        goto L122
    L135:
        String r126 = r214;
        if (ViewCompat.isLaidOut(getContainer()) == false) goto L137;
        FragmentTransition.setViewVisibility(r55, 4);
        ArrayList<String> r313 = r8.prepareSetNameOverridesReordered(r282);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L147;
        Log.v(r126, ">>>>> Beginning transition <<<<<");
        Log.v(r126, ">>>>> SharedElementFirstOutViews <<<<<");
        int r46 = r334.size();
        int r136 = 0;
    L142:
        if (r136 >= r46) goto L144;
        View r144 = r334.get(r136);
        r136 = r136 + 1;
        View r145 = r144;
        Log.v(r126, "View: " + r145 + " Name: " + ViewCompat.getTransitionName(r145));
        goto L142
    L144:
        ArrayList<View> r115 = r334;
        Log.v(r126, ">>>>> SharedElementLastInViews <<<<<");
        int r47 = r282.size();
        int r137 = 0;
    L145:
        if (r137 >= r47) goto L148;
        View r146 = r282.get(r137);
        r137 = r137 + 1;
        View r147 = r146;
        Log.v(r126, "View: " + r147 + " Name: " + ViewCompat.getTransitionName(r147));
    L148:
        r8.beginDelayedTransition(getContainer(), r27);
        r8.setNameOverridesReordered(getContainer(), r115, r282, r313, r224);
        FragmentTransition.setViewVisibility(r55, 0);
        r8.swapSharedElementTargets(r03, r115, r282);
        return r6;
    L147:
        r115 = r334;
        goto L148
    }

    private void syncAnimations(List<SpecialEffectsController.Operation> r4) {
        Fragment r3 = r4.get(r4.size() - 1).getFragment();
        Iterator<SpecialEffectsController.Operation> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        SpecialEffectsController.Operation r0 = r42.next();
        r0.getFragment().mAnimationInfo.mEnterAnim = r3.mAnimationInfo.mEnterAnim;
        r0.getFragment().mAnimationInfo.mExitAnim = r3.mAnimationInfo.mExitAnim;
        r0.getFragment().mAnimationInfo.mPopEnterAnim = r3.mAnimationInfo.mPopEnterAnim;
        r0.getFragment().mAnimationInfo.mPopExitAnim = r3.mAnimationInfo.mPopExitAnim;
        goto L4
    }

    public void applyContainerChanges(SpecialEffectsController.Operation r1) {
        View r0 = r1.getFragment().mView;
        r1.getFinalState().applyState(r0);
    }

    public void captureTransitioningViews(ArrayList<View> r5, View r6) {
        if ((r6 instanceof ViewGroup) == false) goto L17;
        ViewGroup r0 = (ViewGroup) r6;
        if (ViewGroupCompat.isTransitionGroup(r0) == true) goto L7;
        int r62 = r0.getChildCount();
        int r1 = 0;
    L11:
        if (r1 >= r62) goto L25;
        View r2 = r0.getChildAt(r1);
        if (r2.getVisibility() != 0) goto L15;
        captureTransitioningViews(r5, r2);
    L15:
        r1 = r1 + 1;
        goto L11
    L25:
        return;
    L7:
        if (r5.contains(r6) == true) goto L23;
        r5.add(r0);
        return;
    L23:
        return;
    L17:
        if (r5.contains(r6) == true) goto L24;
        r5.add(r6);
        return;
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    public void executeOperations(List<SpecialEffectsController.Operation> r14, boolean r15) {
        Iterator<SpecialEffectsController.Operation> r0 = r14.iterator();
        SpecialEffectsController.Operation r6 = null;
        SpecialEffectsController.Operation r7 = null;
    L4:
        if (r0.hasNext() == false) goto L21;
        SpecialEffectsController.Operation r1 = r0.next();
        SpecialEffectsController.Operation.State r2 = SpecialEffectsController.Operation.State.from(r1.getFragment().mView);
        int r3 = AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[r1.getFinalState().ordinal()];
        if (r3 == 1) goto L17;
        if (r3 == 2) goto L17;
        if (r3 == 3) goto L17;
        if (r3 != 4) goto L4;
        if (r2 == SpecialEffectsController.Operation.State.VISIBLE) goto L4;
        r7 = r1;
    L17:
        if (r2 != SpecialEffectsController.Operation.State.VISIBLE) goto L4;
        if (r6 != null) goto L4;
        r6 = r1;
        goto L4
    L21:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L23;
        Log.v("FragmentManager", "Executing operations from " + r6 + " to " + r7);
    L23:
        List<AnimationInfo> r02 = new ArrayList();
        List<TransitionInfo> r32 = new ArrayList();
        ArrayList r4 = new ArrayList(r14);
        syncAnimations(r14);
        Iterator<SpecialEffectsController.Operation> r142 = r14.iterator();
    L24:
        int r11 = 0;
        boolean r112 = false;
        r112 = false;
        if (r142.hasNext() == false) goto L33;
        SpecialEffectsController.Operation r22 = r142.next();
        CancellationSignal r5 = new CancellationSignal();
        r22.markStartedSpecialEffect(r5);
        r02.add(new AnimationInfo(r22, r5, r15));
        CancellationSignal r52 = new CancellationSignal();
        r22.markStartedSpecialEffect(r52);
        if (r15 == false) goto L30;
        if (r22 != r6) goto L32;
    L29:
        r112 = true;
    L32:
        r32.add(new TransitionInfo(r22, r52, r15, r112));
        r22.addCompletionListener(new AnonymousClass1(this, r4, r22));
        goto L24
    L30:
        if (r22 != r7) goto L32;
    L33:
        Map<SpecialEffectsController.Operation, Boolean> r13 = startTransitions(r32, r4, r15, r6, r7);
        startAnimations(r02, r4, r13.containsValue(Boolean.TRUE), r13);
        int r132 = r4.size();
    L34:
        if (r11 >= r132) goto L36;
        Object r143 = r4.get(r11);
        r11 = r11 + 1;
        applyContainerChanges((SpecialEffectsController.Operation) r143);
        goto L34
    L36:
        r4.clear();
        if (FragmentManager.isLoggingEnabled(2) == false) goto L61;
        Log.v("FragmentManager", "Completed executing operations from " + r6 + " to " + r7);
        return;
    }

    public void findNamedViews(Map<String, View> r5, View r6) {
        String r0 = ViewCompat.getTransitionName(r6);
        if (r0 == null) goto L6;
        r5.put(r0, r6);
    L6:
        if ((r6 instanceof ViewGroup) == false) goto L13;
        ViewGroup r62 = (ViewGroup) r6;
        int r02 = r62.getChildCount();
        int r1 = 0;
    L8:
        if (r1 >= r02) goto L17;
        View r2 = r62.getChildAt(r1);
        if (r2.getVisibility() != 0) goto L12;
        findNamedViews(r5, r2);
    L12:
        r1 = r1 + 1;
        goto L8
    L17:
        return;
    }

    public void retainMatchingViews(ArrayMap<String, View> r1, Collection<String> r2) {
        Iterator<Map.Entry<String, View>> r0 = r1.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        if (r2.contains(ViewCompat.getTransitionName(r0.next().getValue())) == true) goto L4;
        r0.remove();
        goto L4
    }
}
