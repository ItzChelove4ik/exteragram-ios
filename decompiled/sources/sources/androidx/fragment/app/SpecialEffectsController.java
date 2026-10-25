package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R$id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
abstract class SpecialEffectsController {
    private final ViewGroup mContainer;
    boolean mIsContainerPostponed;
    boolean mOperationDirectionIsPop;
    final ArrayList<Operation> mPendingOperations;
    final ArrayList<Operation> mRunningOperations;

    /* renamed from: androidx.fragment.app.SpecialEffectsController$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ SpecialEffectsController this$0;
        final /* synthetic */ FragmentStateManagerOperation val$operation;

        public AnonymousClass1(SpecialEffectsController r1, FragmentStateManagerOperation r2) {
            this.this$0 = r1;
            this.val$operation = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.this$0.mPendingOperations.contains(this.val$operation) == false) goto L6;
            this.val$operation.getFinalState().applyState(this.val$operation.getFragment().mView);
            return;
        }
    }

    /* renamed from: androidx.fragment.app.SpecialEffectsController$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ SpecialEffectsController this$0;
        final /* synthetic */ FragmentStateManagerOperation val$operation;

        public AnonymousClass2(SpecialEffectsController r1, FragmentStateManagerOperation r2) {
            this.this$0 = r1;
            this.val$operation = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.mPendingOperations.remove(this.val$operation);
            this.this$0.mRunningOperations.remove(this.val$operation);
        }
    }

    /* renamed from: androidx.fragment.app.SpecialEffectsController$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact = null;
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = null;

        static {
            int[] r0 = new int[Operation.LifecycleImpact.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact = r0;
            r0[Operation.LifecycleImpact.ADDING.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L23:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact[Operation.LifecycleImpact.REMOVING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
        L27:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact[Operation.LifecycleImpact.NONE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L16
        L8:
            int[] r3 = new int[Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = r3;
            r3[Operation.State.REMOVED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L17
        L33:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[Operation.State.VISIBLE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L18
        L21:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[Operation.State.GONE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L19
        L25:
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[Operation.State.INVISIBLE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L20
            return;
        }
    }

    public static class FragmentStateManagerOperation extends Operation {
        private final FragmentStateManager mFragmentStateManager;

        public FragmentStateManagerOperation(Operation.State r2, Operation.LifecycleImpact r3, FragmentStateManager r4, CancellationSignal r5) {
            super(r2, r3, r4.getFragment(), r5);
            this.mFragmentStateManager = r4;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void complete() {
            super.complete();
            this.mFragmentStateManager.moveToExpectedState();
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void onStart() {
            if (getLifecycleImpact() != Operation.LifecycleImpact.ADDING) goto L20;
            Fragment r0 = this.mFragmentStateManager.getFragment();
            View r1 = r0.mView.findFocus();
            if (r1 == null) goto L9;
            r0.setFocusedView(r1);
            if (FragmentManager.isLoggingEnabled(2) == false) goto L9;
            Log.v("FragmentManager", "requestFocus: Saved focused view " + r1 + " for Fragment " + r0);
        L9:
            View r12 = getFragment().requireView();
            if (r12.getParent() != null) goto L13;
            this.mFragmentStateManager.addViewToContainer();
            r12.setAlpha(0.0f);
        L13:
            if (r12.getAlpha() == 0.0f) goto L15;
        L17:
            r12.setAlpha(r0.getPostOnViewCreatedAlpha());
            return;
        L15:
            if (r12.getVisibility() != 0) goto L17;
            r12.setVisibility(4);
            goto L17
        L20:
            if (getLifecycleImpact() != Operation.LifecycleImpact.REMOVING) goto L26;
            Fragment r6 = this.mFragmentStateManager.getFragment();
            View r02 = r6.requireView();
            if (FragmentManager.isLoggingEnabled(2) == false) goto L24;
            Log.v("FragmentManager", "Clearing focus " + r02.findFocus() + " on view " + r02 + " for Fragment " + r6);
        L24:
            r02.clearFocus();
            return;
        }
    }

    public static class Operation {
        private final List<Runnable> mCompletionListeners;
        private State mFinalState;
        private final Fragment mFragment;
        private boolean mIsCanceled;
        private boolean mIsComplete;
        private LifecycleImpact mLifecycleImpact;
        private final HashSet<CancellationSignal> mSpecialEffectsSignals;

        /* renamed from: androidx.fragment.app.SpecialEffectsController$Operation$1, reason: invalid class name */
        public class AnonymousClass1 implements CancellationSignal.OnCancelListener {
            final /* synthetic */ Operation this$0;

            public AnonymousClass1(Operation r1) {
                this.this$0 = r1;
            }

            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public void onCancel() {
                this.this$0.cancel();
            }
        }

        public enum LifecycleImpact extends Enum<LifecycleImpact> {
            private static final /* synthetic */ LifecycleImpact[] $VALUES = null;
            public static final LifecycleImpact ADDING = null;
            public static final LifecycleImpact NONE = null;
            public static final LifecycleImpact REMOVING = null;

            static {
                LifecycleImpact r0 = new LifecycleImpact("NONE", 0);
                NONE = r0;
                LifecycleImpact r1 = new LifecycleImpact("ADDING", 1);
                ADDING = r1;
                LifecycleImpact r2 = new LifecycleImpact("REMOVING", 2);
                REMOVING = r2;
                $VALUES = new LifecycleImpact[]{r0, r1, r2};
            }

            LifecycleImpact(String r1, int r2) {
            }

            public static LifecycleImpact valueOf(String r1) {
                return (LifecycleImpact) Enum.valueOf(LifecycleImpact.class, r1);
            }

            public static LifecycleImpact[] values() {
                return (LifecycleImpact[]) $VALUES.clone();
            }
        }

        public enum State extends Enum<State> {
            private static final /* synthetic */ State[] $VALUES = null;
            public static final State GONE = null;
            public static final State INVISIBLE = null;
            public static final State REMOVED = null;
            public static final State VISIBLE = null;

            static {
                State r0 = new State("REMOVED", 0);
                REMOVED = r0;
                State r1 = new State("VISIBLE", 1);
                VISIBLE = r1;
                State r2 = new State("GONE", 2);
                GONE = r2;
                State r3 = new State("INVISIBLE", 3);
                INVISIBLE = r3;
                $VALUES = new State[]{r0, r1, r2, r3};
            }

            State(String r1, int r2) {
            }

            public static State from(int r1) {
                if (r1 == 0) goto L14;
                if (r1 == 4) goto L12;
                if (r1 == 8) goto L8;
                CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown visibility ", r1);
                return null;
            L8:
                return GONE;
            L12:
                return INVISIBLE;
            L14:
                return VISIBLE;
            }

            public static State from(View r2) {
                if (r2.getAlpha() != 0.0f) goto L9;
                if (r2.getVisibility() != 0) goto L9;
                return INVISIBLE;
            L9:
                return from(r2.getVisibility());
            }

            public static State valueOf(String r1) {
                return (State) Enum.valueOf(State.class, r1);
            }

            public static State[] values() {
                return (State[]) $VALUES.clone();
            }

            public void applyState(View r5) {
                int r4 = AnonymousClass3.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[ordinal()];
                if (r4 != 1) goto L5;
                ViewGroup r42 = (ViewGroup) r5.getParent();
                if (r42 != null) goto L29;
                return;
            L29:
                if (FragmentManager.isLoggingEnabled(2) == false) goto L31;
                Log.v("FragmentManager", "SpecialEffectsController: Removing view " + r5 + " from container " + r42);
            L31:
                r42.removeView(r5);
                return;
            L5:
                if (r4 == 2) goto L22;
                if (r4 == 3) goto L17;
                if (r4 == 4) goto L12;
                return;
            L12:
                if (FragmentManager.isLoggingEnabled(2) == false) goto L14;
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + r5 + " to INVISIBLE");
            L14:
                r5.setVisibility(4);
                return;
            L17:
                if (FragmentManager.isLoggingEnabled(2) == false) goto L19;
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + r5 + " to GONE");
            L19:
                r5.setVisibility(8);
                return;
            L22:
                if (FragmentManager.isLoggingEnabled(2) == false) goto L24;
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + r5 + " to VISIBLE");
            L24:
                r5.setVisibility(0);
            }
        }

        public Operation(State r2, LifecycleImpact r3, Fragment r4, CancellationSignal r5) {
            this.mCompletionListeners = new ArrayList();
            this.mSpecialEffectsSignals = new HashSet();
            this.mIsCanceled = false;
            this.mIsComplete = false;
            this.mFinalState = r2;
            this.mLifecycleImpact = r3;
            this.mFragment = r4;
            r5.setOnCancelListener(new AnonymousClass1(this));
        }

        public final void addCompletionListener(Runnable r1) {
            this.mCompletionListeners.add(r1);
        }

        public final void cancel() {
            if (isCanceled() == true) goto L12;
            this.mIsCanceled = true;
            if (this.mSpecialEffectsSignals.isEmpty() == false) goto L9;
            complete();
            return;
        L9:
            ArrayList r0 = new ArrayList(this.mSpecialEffectsSignals);
            int r3 = r0.size();
            int r1 = 0;
        L10:
            if (r1 >= r3) goto L14;
            Object r2 = r0.get(r1);
            r1 = r1 + 1;
            ((CancellationSignal) r2).cancel();
            goto L10
        L14:
            return;
        }

        public void complete() {
            if (this.mIsComplete == false) goto L6;
            return;
        L6:
            if (FragmentManager.isLoggingEnabled(2) == false) goto L8;
            Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
        L8:
            this.mIsComplete = true;
            Iterator<Runnable> r2 = this.mCompletionListeners.iterator();
        L10:
            if (r2.hasNext() == false) goto L14;
            r2.next().run();
            goto L10
        }

        public final void completeSpecialEffect(CancellationSignal r2) {
            if (this.mSpecialEffectsSignals.remove(r2) == true) goto L5;
            return;
        L5:
            if (this.mSpecialEffectsSignals.isEmpty() == false) goto L9;
            complete();
            return;
        }

        public State getFinalState() {
            return this.mFinalState;
        }

        public final Fragment getFragment() {
            return this.mFragment;
        }

        public LifecycleImpact getLifecycleImpact() {
            return this.mLifecycleImpact;
        }

        public final boolean isCanceled() {
            return this.mIsCanceled;
        }

        public final boolean isComplete() {
            return this.mIsComplete;
        }

        public final void markStartedSpecialEffect(CancellationSignal r1) {
            onStart();
            this.mSpecialEffectsSignals.add(r1);
        }

        public final void mergeWith(State r6, LifecycleImpact r7) {
            int r72 = AnonymousClass3.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact[r7.ordinal()];
            if (r72 == 1) goto L22;
            if (r72 == 2) goto L17;
            if (r72 == 3) goto L10;
            return;
        L10:
            if (this.mFinalState != State.REMOVED) goto L12;
            return;
        L12:
            if (FragmentManager.isLoggingEnabled(2) == false) goto L14;
            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + " -> " + r6 + ". ");
        L14:
            this.mFinalState = r6;
            return;
        L17:
            if (FragmentManager.isLoggingEnabled(2) == false) goto L19;
            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + " -> REMOVED. mLifecycleImpact  = " + this.mLifecycleImpact + " to REMOVING.");
        L19:
            this.mFinalState = State.REMOVED;
            this.mLifecycleImpact = LifecycleImpact.REMOVING;
            return;
        L22:
            if (this.mFinalState == State.REMOVED) goto L24;
            return;
        L24:
            if (FragmentManager.isLoggingEnabled(2) == false) goto L26;
            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.mLifecycleImpact + " to ADDING.");
        L26:
            this.mFinalState = State.VISIBLE;
            this.mLifecycleImpact = LifecycleImpact.ADDING;
        }

        public abstract void onStart();

        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.mFinalState + "} {mLifecycleImpact = " + this.mLifecycleImpact + "} {mFragment = " + this.mFragment + "}";
        }
    }

    public SpecialEffectsController(ViewGroup r2) {
        this.mPendingOperations = new ArrayList();
        this.mRunningOperations = new ArrayList();
        this.mOperationDirectionIsPop = false;
        this.mIsContainerPostponed = false;
        this.mContainer = r2;
    }

    private void enqueue(Operation.State r4, Operation.LifecycleImpact r5, FragmentStateManager r6) {
        ArrayList<Operation> r0 = this.mPendingOperations;
        monitor-enter(r0);
        CancellationSignal r1 = new CancellationSignal();     // Catch: Throwable -> L9
        Operation r2 = findPendingOperation(r6.getFragment());     // Catch: Throwable -> L9
        if (r2 == null) goto L11;
        r2.mergeWith(r4, r5);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        FragmentStateManagerOperation r22 = new FragmentStateManagerOperation(r4, r5, r6, r1);     // Catch: Throwable -> L9
        this.mPendingOperations.add(r22);     // Catch: Throwable -> L9
        r22.addCompletionListener(new AnonymousClass1(this, r22));     // Catch: Throwable -> L9
        r22.addCompletionListener(new AnonymousClass2(this, r22));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private Operation findPendingOperation(Fragment r5) {
        ArrayList<Operation> r4 = this.mPendingOperations;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        Operation r2 = r4.get(r1);
        r1 = r1 + 1;
        Operation r22 = r2;
        if (r22.getFragment().equals(r5) == false) goto L3;
        if (r22.isCanceled() == true) goto L3;
        return r22;
    L9:
        return null;
    }

    private Operation findRunningOperation(Fragment r5) {
        ArrayList<Operation> r4 = this.mRunningOperations;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        Operation r2 = r4.get(r1);
        r1 = r1 + 1;
        Operation r22 = r2;
        if (r22.getFragment().equals(r5) == false) goto L3;
        if (r22.isCanceled() == true) goto L3;
        return r22;
    L9:
        return null;
    }

    public static SpecialEffectsController getOrCreateController(ViewGroup r0, FragmentManager r1) {
        return getOrCreateController(r0, r1.getSpecialEffectsControllerFactory());
    }

    public static SpecialEffectsController getOrCreateController(ViewGroup r2, SpecialEffectsControllerFactory r3) {
        Object r0 = r2.getTag(R$id.special_effects_controller_view_tag);
        if ((r0 instanceof SpecialEffectsController) == true) goto L5;
        SpecialEffectsController r32 = r3.createController(r2);
        r2.setTag(R$id.special_effects_controller_view_tag, r32);
        return r32;
    L5:
        return (SpecialEffectsController) r0;
    }

    private void updateFinalState() {
        ArrayList<Operation> r5 = this.mPendingOperations;
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        Operation r2 = r5.get(r1);
        r1 = r1 + 1;
        Operation r22 = r2;
        if (r22.getLifecycleImpact() != Operation.LifecycleImpact.ADDING) goto L3;
        r22.mergeWith(Operation.State.from(r22.getFragment().requireView().getVisibility()), Operation.LifecycleImpact.NONE);
        goto L3
    }

    public void enqueueAdd(Operation.State r3, FragmentStateManager r4) {
        if (FragmentManager.isLoggingEnabled(2) == false) goto L5;
        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + r4.getFragment());
    L5:
        enqueue(r3, Operation.LifecycleImpact.ADDING, r4);
    }

    public void enqueueHide(FragmentStateManager r3) {
        if (FragmentManager.isLoggingEnabled(2) == false) goto L5;
        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + r3.getFragment());
    L5:
        enqueue(Operation.State.GONE, Operation.LifecycleImpact.NONE, r3);
    }

    public void enqueueRemove(FragmentStateManager r3) {
        if (FragmentManager.isLoggingEnabled(2) == false) goto L5;
        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + r3.getFragment());
    L5:
        enqueue(Operation.State.REMOVED, Operation.LifecycleImpact.REMOVING, r3);
    }

    public void enqueueShow(FragmentStateManager r3) {
        if (FragmentManager.isLoggingEnabled(2) == false) goto L5;
        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + r3.getFragment());
    L5:
        enqueue(Operation.State.VISIBLE, Operation.LifecycleImpact.NONE, r3);
    }

    public abstract void executeOperations(List<Operation> r1, boolean r2);

    public void executePendingOperations() {
        if (this.mIsContainerPostponed == false) goto L6;
        return;
    L6:
        if (ViewCompat.isAttachedToWindow(this.mContainer) == true) goto L9;
        forceCompleteAllOperations();
        this.mOperationDirectionIsPop = false;
        return;
    L9:
        ArrayList<Operation> r0 = this.mPendingOperations;
        monitor-enter(r0);
    L19:
        th = move-exception;
        throw th;
    L12:
        if (this.mPendingOperations.isEmpty() == true) goto L33;
        ArrayList r2 = new ArrayList(this.mRunningOperations);     // Catch: Throwable -> L19
        this.mRunningOperations.clear();     // Catch: Throwable -> L19
        int r3 = r2.size();     // Catch: Throwable -> L19
        int r4 = 0;
    L15:
        if (r4 >= r3) goto L24;
        Object r6 = r2.get(r4);     // Catch: Throwable -> L19
        r4 = r4 + 1;     // Catch: Throwable -> L19
        Operation r62 = (Operation) r6;     // Catch: Throwable -> L19
        if (FragmentManager.isLoggingEnabled(2) == false) goto L21;
        Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + r62);     // Catch: Throwable -> L19
    L21:
        r62.cancel();     // Catch: Throwable -> L19
        if (r62.isComplete() == true) goto L15;
        this.mRunningOperations.add(r62);     // Catch: Throwable -> L19
        goto L15
    L24:
        updateFinalState();     // Catch: Throwable -> L19
        ArrayList r22 = new ArrayList(this.mPendingOperations);     // Catch: Throwable -> L19
        this.mPendingOperations.clear();     // Catch: Throwable -> L19
        this.mRunningOperations.addAll(r22);     // Catch: Throwable -> L19
        if (FragmentManager.isLoggingEnabled(2) == false) goto L27;
        Log.v("FragmentManager", "SpecialEffectsController: Executing pending operations");     // Catch: Throwable -> L19
    L27:
        int r32 = r22.size();     // Catch: Throwable -> L19
        int r42 = 0;
    L28:
        if (r42 >= r32) goto L30;
        Object r63 = r22.get(r42);     // Catch: Throwable -> L19
        r42 = r42 + 1;     // Catch: Throwable -> L19
        ((Operation) r63).onStart();     // Catch: Throwable -> L19
        goto L28
    L30:
        executeOperations(r22, this.mOperationDirectionIsPop);     // Catch: Throwable -> L19
        this.mOperationDirectionIsPop = false;     // Catch: Throwable -> L19
        if (FragmentManager.isLoggingEnabled(2) == false) goto L33;
        Log.v("FragmentManager", "SpecialEffectsController: Finished executing pending operations");     // Catch: Throwable -> L19
    L33:
        monitor-exit(r0);     // Catch: Throwable -> L19
    }

    public void forceCompleteAllOperations() {
        if (FragmentManager.isLoggingEnabled(2) == false) goto L5;
        Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
    L5:
        boolean r1 = ViewCompat.isAttachedToWindow(this.mContainer);
        ArrayList<Operation> r2 = this.mPendingOperations;
        monitor-enter(r2);
        updateFinalState();     // Catch: Throwable -> L10
        ArrayList<Operation> r3 = this.mPendingOperations;     // Catch: Throwable -> L10
        int r4 = r3.size();     // Catch: Throwable -> L10
        int r5 = 0;
        int r6 = 0;
    L8:
        if (r6 >= r4) goto L12;
        Operation r7 = r3.get(r6);     // Catch: Throwable -> L10
        r6 = r6 + 1;     // Catch: Throwable -> L10
        r7.onStart();     // Catch: Throwable -> L10
        goto L8
    L12:
        ArrayList r32 = new ArrayList(this.mRunningOperations);     // Catch: Throwable -> L10
        int r42 = r32.size();     // Catch: Throwable -> L10
        int r62 = 0;
    L13:
        if (r62 >= r42) goto L22;
        Object r72 = r32.get(r62);     // Catch: Throwable -> L10
        r62 = r62 + 1;     // Catch: Throwable -> L10
        Operation r73 = (Operation) r72;     // Catch: Throwable -> L10
        if (FragmentManager.isLoggingEnabled(2) == false) goto L21;
        StringBuilder r9 = new StringBuilder();     // Catch: Throwable -> L10
        r9.append("SpecialEffectsController: ");     // Catch: Throwable -> L10
        if (r1 == false) goto L19;
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        r9.append(r10);     // Catch: Throwable -> L10
        r9.append("Cancelling running operation ");     // Catch: Throwable -> L10
        r9.append(r73);     // Catch: Throwable -> L10
        Log.v("FragmentManager", r9.toString());     // Catch: Throwable -> L10
        goto L21
    L19:
        r10 = "Container " + this.mContainer + " is not attached to window. ";     // Catch: Throwable -> L10
    L21:
        r73.cancel();     // Catch: Throwable -> L10
        goto L13
    L22:
        ArrayList r33 = new ArrayList(this.mPendingOperations);     // Catch: Throwable -> L10
        int r43 = r33.size();     // Catch: Throwable -> L10
    L23:
        if (r5 >= r43) goto L32;
        Object r63 = r33.get(r5);     // Catch: Throwable -> L10
        r5 = r5 + 1;     // Catch: Throwable -> L10
        Operation r64 = (Operation) r63;     // Catch: Throwable -> L10
        if (FragmentManager.isLoggingEnabled(2) == false) goto L31;
        StringBuilder r8 = new StringBuilder();     // Catch: Throwable -> L10
        r8.append("SpecialEffectsController: ");     // Catch: Throwable -> L10
        if (r1 == false) goto L29;
        String r92 = _UrlKt.FRAGMENT_ENCODE_SET;
    L30:
        r8.append(r92);     // Catch: Throwable -> L10
        r8.append("Cancelling pending operation ");     // Catch: Throwable -> L10
        r8.append(r64);     // Catch: Throwable -> L10
        Log.v("FragmentManager", r8.toString());     // Catch: Throwable -> L10
        goto L31
    L29:
        r92 = "Container " + this.mContainer + " is not attached to window. ";     // Catch: Throwable -> L10
    L31:
        r64.cancel();     // Catch: Throwable -> L10
        goto L23
    L32:
        monitor-exit(r2);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public void forcePostponedExecutePendingOperations() {
        if (this.mIsContainerPostponed == true) goto L5;
        return;
    L5:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L7;
        Log.v("FragmentManager", "SpecialEffectsController: Forcing postponed operations");
    L7:
        this.mIsContainerPostponed = false;
        executePendingOperations();
    }

    public Operation.LifecycleImpact getAwaitingCompletionLifecycleImpact(FragmentStateManager r2) {
        Operation r0 = findPendingOperation(r2.getFragment());
        if (r0 == null) goto L5;
        Operation.LifecycleImpact r02 = r0.getLifecycleImpact();
    L6:
        Operation r1 = findRunningOperation(r2.getFragment());
        if (r1 == null) goto L13;
        if (r02 == null) goto L12;
        if (r02 != Operation.LifecycleImpact.NONE) goto L13;
    L12:
        return r1.getLifecycleImpact();
    L13:
        return r02;
    L5:
        r02 = null;
        goto L6
    }

    public ViewGroup getContainer() {
        return this.mContainer;
    }

    public void markPostponedState() {
        ArrayList<Operation> r0 = this.mPendingOperations;
        monitor-enter(r0);
        updateFinalState();     // Catch: Throwable -> L10
        this.mIsContainerPostponed = false;     // Catch: Throwable -> L10
        int r1 = this.mPendingOperations.size() - 1;
    L5:
        if (r1 < 0) goto L13;
        Operation r2 = this.mPendingOperations.get(r1);     // Catch: Throwable -> L10
        Operation.State r3 = Operation.State.from(r2.getFragment().mView);     // Catch: Throwable -> L10
        Operation.State r4 = r2.getFinalState();     // Catch: Throwable -> L10
        Operation.State r5 = Operation.State.VISIBLE;     // Catch: Throwable -> L10
        if (r4 != r5) goto L12;
        if (r3 == r5) goto L12;
        this.mIsContainerPostponed = r2.getFragment().isPostponed();     // Catch: Throwable -> L10
    L12:
        r1 = r1 - 1;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public void updateOperationDirection(boolean r1) {
        this.mOperationDirectionIsPop = r1;
    }
}
