package androidx.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class TransitionSet extends Transition {
    private int mChangeFlags;
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    ArrayList<Transition> mTransitions;

    /* renamed from: androidx.transition.TransitionSet$1, reason: invalid class name */
    public class AnonymousClass1 extends TransitionListenerAdapter {
        final /* synthetic */ TransitionSet this$0;
        final /* synthetic */ Transition val$nextTransition;

        public AnonymousClass1(TransitionSet r1, Transition r2) {
            this.this$0 = r1;
            this.val$nextTransition = r2;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r2) {
            this.val$nextTransition.runAnimators();
            r2.removeListener(this);
        }
    }

    /* renamed from: androidx.transition.TransitionSet$2, reason: invalid class name */
    public class AnonymousClass2 extends TransitionListenerAdapter {
        final /* synthetic */ TransitionSet this$0;

        public AnonymousClass2(TransitionSet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r3) {
            this.this$0.mTransitions.remove(r3);
            if (this.this$0.hasAnimators() == true) goto L6;
            this.this$0.notifyListeners(Transition.TransitionNotification.ON_CANCEL, false);
            TransitionSet r2 = this.this$0;
            r2.mEnded = true;
            r2.notifyListeners(Transition.TransitionNotification.ON_END, false);
            return;
        }
    }

    public static class TransitionSetListener extends TransitionListenerAdapter {
        TransitionSet mTransitionSet;

        public TransitionSetListener(TransitionSet r1) {
            this.mTransitionSet = r1;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r3) {
            TransitionSet r0 = this.mTransitionSet;
            int r1 = r0.mCurrentListeners - 1;
            r0.mCurrentListeners = r1;
            if (r1 != 0) goto L5;
            r0.mStarted = false;
            r0.end();
        L5:
            r3.removeListener(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r2) {
            TransitionSet r22 = this.mTransitionSet;
            if (r22.mStarted == true) goto L6;
            r22.start();
            this.mTransitionSet.mStarted = true;
            return;
        }
    }

    public TransitionSet() {
        this.mTransitions = new ArrayList();
        this.mPlayTogether = true;
        this.mStarted = false;
        this.mChangeFlags = 0;
    }

    private void addTransitionInternal(Transition r2) {
        this.mTransitions.add(r2);
        r2.mParent = this;
    }

    private int indexOfTransition(long r5) {
        int r1 = 1;
    L3:
        int r2 = this.mTransitions.size();
        ArrayList<Transition> r3 = this.mTransitions;
        if (r1 >= r2) goto L11;
        if (r3.get(r1).mSeekOffsetInParent > r5) goto L8;
        r1 = r1 + 1;
        goto L3
    L8:
        return r1 - 1;
    L11:
        return r3.size() - 1;
    }

    private void setupStartEndListeners() {
        TransitionSetListener r0 = new TransitionSetListener(this);
        ArrayList<Transition> r1 = this.mTransitions;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        Transition r4 = r1.get(r3);
        r3 = r3 + 1;
        r4.addListener(r0);
        goto L3
    L5:
        this.mCurrentListeners = this.mTransitions.size();
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition addListener(Transition.TransitionListener r1) {
        return addListener(r1);
    }

    @Override // androidx.transition.Transition
    public TransitionSet addListener(Transition.TransitionListener r1) {
        return (TransitionSet) super.addListener(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition addTarget(int r1) {
        return addTarget(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition addTarget(View r1) {
        return addTarget(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition addTarget(Class r1) {
        return addTarget(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition addTarget(String r1) {
        return addTarget(r1);
    }

    @Override // androidx.transition.Transition
    public TransitionSet addTarget(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).addTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.addTarget(r3);
    }

    @Override // androidx.transition.Transition
    public TransitionSet addTarget(View r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).addTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.addTarget(r3);
    }

    @Override // androidx.transition.Transition
    public TransitionSet addTarget(Class<?> r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).addTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.addTarget(r3);
    }

    @Override // androidx.transition.Transition
    public TransitionSet addTarget(String r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).addTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.addTarget(r3);
    }

    public TransitionSet addTransition(Transition r5) {
        addTransitionInternal(r5);
        long r0 = this.mDuration;
        if (r0 < 0) goto L6;
        r5.setDuration(r0);
    L6:
        if ((this.mChangeFlags & 1) == 0) goto L9;
        r5.setInterpolator(getInterpolator());
    L9:
        if ((this.mChangeFlags & 2) == 0) goto L12;
        getPropagation();
        r5.setPropagation(null);
    L12:
        if ((this.mChangeFlags & 4) == 0) goto L15;
        r5.setPathMotion(getPathMotion());
    L15:
        if ((this.mChangeFlags & 8) == 0) goto L17;
        r5.setEpicenterCallback(getEpicenterCallback());
    L17:
        return this;
    }

    @Override // androidx.transition.Transition
    public void cancel() {
        super.cancel();
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).cancel();
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues r5) {
        if (isValidTarget(r5.view) == false) goto L9;
        ArrayList<Transition> r4 = this.mTransitions;
        int r0 = r4.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L15;
        Transition r2 = r4.get(r1);
        r1 = r1 + 1;
        Transition r22 = r2;
        if (r22.isValidTarget(r5.view) == false) goto L5;
        r22.captureEndValues(r5);
        r5.mTargetedTransitions.add(r22);
        goto L5
    L15:
        return;
    }

    @Override // androidx.transition.Transition
    public void capturePropagationValues(TransitionValues r4) {
        super.capturePropagationValues(r4);
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).capturePropagationValues(r4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues r5) {
        if (isValidTarget(r5.view) == false) goto L9;
        ArrayList<Transition> r4 = this.mTransitions;
        int r0 = r4.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L15;
        Transition r2 = r4.get(r1);
        r1 = r1 + 1;
        Transition r22 = r2;
        if (r22.isValidTarget(r5.view) == false) goto L5;
        r22.captureStartValues(r5);
        r5.mTargetedTransitions.add(r22);
        goto L5
    L15:
        return;
    }

    @Override // androidx.transition.Transition
    public Transition clone() {
        TransitionSet r0 = (TransitionSet) super.clone();
        r0.mTransitions = new ArrayList();
        int r1 = this.mTransitions.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.addTransitionInternal(this.mTransitions.get(r2).clone());
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @Override // androidx.transition.Transition
    /* renamed from: clone */
    public /* bridge */ /* synthetic */ Object mo964clone() {
        return clone();
    }

    @Override // androidx.transition.Transition
    public void createAnimators(ViewGroup r12, TransitionValuesMaps r13, TransitionValuesMaps r14, ArrayList<TransitionValues> r15, ArrayList<TransitionValues> r16) {
        long r0 = getStartDelay();
        int r2 = this.mTransitions.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L16;
        Transition r5 = this.mTransitions.get(r3);
        if (r0 <= 0) goto L15;
        if (this.mPlayTogether == true) goto L11;
        if (r3 != 0) goto L15;
    L11:
        long r8 = r5.getStartDelay();
        if (r8 <= 0) goto L14;
        r5.setStartDelay(r8 + r0);
        goto L15
    L14:
        r5.setStartDelay(r0);
    L15:
        r5.createAnimators(r12, r13, r14, r15, r16);
        r3 = r3 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public Transition excludeTarget(int r3, boolean r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).excludeTarget(r3, r4);
        r0 = r0 + 1;
        goto L4
    L7:
        return super.excludeTarget(r3, r4);
    }

    @Override // androidx.transition.Transition
    public Transition excludeTarget(View r3, boolean r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).excludeTarget(r3, r4);
        r0 = r0 + 1;
        goto L4
    L7:
        return super.excludeTarget(r3, r4);
    }

    @Override // androidx.transition.Transition
    public Transition excludeTarget(Class<?> r3, boolean r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).excludeTarget(r3, r4);
        r0 = r0 + 1;
        goto L4
    L7:
        return super.excludeTarget(r3, r4);
    }

    @Override // androidx.transition.Transition
    public Transition excludeTarget(String r3, boolean r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).excludeTarget(r3, r4);
        r0 = r0 + 1;
        goto L4
    L7:
        return super.excludeTarget(r3, r4);
    }

    @Override // androidx.transition.Transition
    public void forceToEnd(ViewGroup r4) {
        super.forceToEnd(r4);
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).forceToEnd(r4);
        r1 = r1 + 1;
        goto L3
    }

    public Transition getTransitionAt(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.mTransitions.size()) goto L7;
        return null;
    L7:
        return this.mTransitions.get(r2);
    }

    public int getTransitionCount() {
        return this.mTransitions.size();
    }

    @Override // androidx.transition.Transition
    public boolean hasAnimators() {
        int r1 = 0;
    L4:
        if (r1 >= this.mTransitions.size()) goto L10;
        if (this.mTransitions.get(r1).hasAnimators() == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    @Override // androidx.transition.Transition
    public boolean isSeekingSupported() {
        int r0 = this.mTransitions.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (this.mTransitions.get(r2).isSeekingSupported() == false) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    @Override // androidx.transition.Transition
    public void pause(View r4) {
        super.pause(r4);
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).pause(r4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public void prepareAnimatorsForSeeking() {
        this.mTotalDuration = 0;
        AnonymousClass2 r0 = new AnonymousClass2(this);
        int r1 = 0;
    L4:
        if (r1 >= this.mTransitions.size()) goto L10;
        Transition r2 = this.mTransitions.get(r1);
        r2.addListener(r0);
        r2.prepareAnimatorsForSeeking();
        long r3 = r2.getTotalDurationMillis();
        boolean r5 = this.mPlayTogether;
        long r6 = this.mTotalDuration;
        if (r5 == false) goto L8;
        this.mTotalDuration = Math.max(r6, r3);
    L9:
        r1 = r1 + 1;
        goto L4
    L8:
        r2.mSeekOffsetInParent = r6;
        this.mTotalDuration = r6 + r3;
        goto L9
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition removeListener(Transition.TransitionListener r1) {
        return removeListener(r1);
    }

    @Override // androidx.transition.Transition
    public TransitionSet removeListener(Transition.TransitionListener r1) {
        return (TransitionSet) super.removeListener(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition removeTarget(int r1) {
        return removeTarget(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition removeTarget(View r1) {
        return removeTarget(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition removeTarget(Class r1) {
        return removeTarget(r1);
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition removeTarget(String r1) {
        return removeTarget(r1);
    }

    @Override // androidx.transition.Transition
    public TransitionSet removeTarget(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).removeTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.removeTarget(r3);
    }

    @Override // androidx.transition.Transition
    public TransitionSet removeTarget(View r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).removeTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.removeTarget(r3);
    }

    @Override // androidx.transition.Transition
    public TransitionSet removeTarget(Class<?> r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).removeTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.removeTarget(r3);
    }

    @Override // androidx.transition.Transition
    public TransitionSet removeTarget(String r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.mTransitions.size()) goto L7;
        this.mTransitions.get(r0).removeTarget(r3);
        r0 = r0 + 1;
        goto L4
    L7:
        return (TransitionSet) super.removeTarget(r3);
    }

    public TransitionSet removeTransition(Transition r2) {
        this.mTransitions.remove(r2);
        r2.mParent = null;
        return this;
    }

    @Override // androidx.transition.Transition
    public void resume(View r4) {
        super.resume(r4);
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).resume(r4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public void runAnimators() {
        if (this.mTransitions.isEmpty() == false) goto L6;
        start();
        end();
        return;
    L6:
        setupStartEndListeners();
        int r1 = 0;
        if (this.mPlayTogether == true) goto L16;
        int r0 = 1;
    L9:
        int r2 = this.mTransitions.size();
        ArrayList<Transition> r3 = this.mTransitions;
        if (r0 >= r2) goto L12;
        r3.get(r0 - 1).addListener(new AnonymousClass1(this, this.mTransitions.get(r0)));
        r0 = r0 + 1;
        goto L9
    L12:
        Transition r5 = r3.get(0);
        if (r5 == null) goto L19;
        r5.runAnimators();
        return;
    L19:
        return;
    L16:
        ArrayList<Transition> r52 = this.mTransitions;
        int r02 = r52.size();
    L17:
        if (r1 >= r02) goto L22;
        Transition r22 = r52.get(r1);
        r1 = r1 + 1;
        r22.runAnimators();
        goto L17
    }

    @Override // androidx.transition.Transition
    public void setCanRemoveViews(boolean r4) {
        super.setCanRemoveViews(r4);
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).setCanRemoveViews(r4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public void setCurrentPlayTimeMillis(long r20, long r22) {
        long r5 = getTotalDurationMillis();
        long r8 = 0;
        if (this.mParent != null) goto L5;
    L13:
        int r10 = 0;
        if (r20 >= r22) goto L16;
        boolean r12 = true;
    L18:
        if (r20 < 0) goto L22;
        if (r22 >= 0) goto L22;
    L25:
        this.mEnded = false;
        notifyListeners(Transition.TransitionNotification.ON_START, r12);
    L27:
        if (this.mPlayTogether == true) goto L29;
        int r102 = indexOfTransition(r22);
        if (r20 >= r22) goto L35;
        long r16 = 0;
    L41:
        if (r102 < 0) goto L47;
        Transition r7 = this.mTransitions.get(r102);
        long r82 = r7.mSeekOffsetInParent;
        long r14 = r20 - r82;
        r7.setCurrentPlayTimeMillis(r14, r22 - r82);
        if (r14 >= 0) goto L47;
        r102 = r102 - 1;
    L47:
        if (this.mParent != null) goto L49;
        return;
    L49:
        if (r20 > r5) goto L51;
    L52:
        if (r20 < 0) goto L54;
        return;
    L54:
        if (r22 < r16) goto L67;
    L55:
        if (r20 <= r5) goto L57;
        this.mEnded = true;
    L57:
        notifyListeners(Transition.TransitionNotification.ON_END, r12);
        return;
    L67:
        return;
    L51:
        if (r22 <= r5) goto L55;
    L35:
        if (r102 >= this.mTransitions.size()) goto L31;
        Transition r72 = this.mTransitions.get(r102);
        long r142 = r72.mSeekOffsetInParent;
        r16 = r8;
        long r83 = r20 - r142;
        if (r83 < r16) goto L47;
        r72.setCurrentPlayTimeMillis(r83, r22 - r142);
        r102 = r102 + 1;
        r8 = r16;
    L31:
        r16 = r8;
    L29:
        if (r10 >= this.mTransitions.size()) goto L31;
        this.mTransitions.get(r10).setCurrentPlayTimeMillis(r20, r22);
        r10 = r10 + 1;
    L22:
        if (r20 > r5) goto L27;
        if (r22 <= r5) goto L27;
    L16:
        r12 = false;
        goto L18
    L5:
        if (r20 >= 0) goto L9;
        if (r22 >= 0) goto L9;
        return;
    L9:
        if (r20 <= r5) goto L13;
        if (r22 <= r5) goto L13;
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition setDuration(long r1) {
        return setDuration(r1);
    }

    @Override // androidx.transition.Transition
    public TransitionSet setDuration(long r5) {
        super.setDuration(r5);
        if (this.mDuration < 0) goto L9;
        ArrayList<Transition> r0 = this.mTransitions;
        if (r0 == null) goto L9;
        int r02 = r0.size();
        int r1 = 0;
    L7:
        if (r1 >= r02) goto L9;
        this.mTransitions.get(r1).setDuration(r5);
        r1 = r1 + 1;
    L9:
        return this;
    }

    @Override // androidx.transition.Transition
    public void setEpicenterCallback(Transition.EpicenterCallback r4) {
        super.setEpicenterCallback(r4);
        this.mChangeFlags |= 8;
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).setEpicenterCallback(r4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition setInterpolator(TimeInterpolator r1) {
        return setInterpolator(r1);
    }

    @Override // androidx.transition.Transition
    public TransitionSet setInterpolator(TimeInterpolator r4) {
        this.mChangeFlags |= 1;
        ArrayList<Transition> r0 = this.mTransitions;
        if (r0 == null) goto L8;
        int r02 = r0.size();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L8;
        this.mTransitions.get(r1).setInterpolator(r4);
        r1 = r1 + 1;
    L8:
        return (TransitionSet) super.setInterpolator(r4);
    }

    public TransitionSet setOrdering(int r3) {
        if (r3 == 0) goto L9;
        if (r3 != 1) goto L8;
        this.mPlayTogether = false;
        return this;
    L8:
        throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + r3);
    L9:
        this.mPlayTogether = true;
        return this;
    }

    @Override // androidx.transition.Transition
    public void setPathMotion(PathMotion r3) {
        super.setPathMotion(r3);
        this.mChangeFlags |= 4;
        if (this.mTransitions == null) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= this.mTransitions.size()) goto L10;
        this.mTransitions.get(r0).setPathMotion(r3);
        r0 = r0 + 1;
        goto L6
    L10:
        return;
    }

    @Override // androidx.transition.Transition
    public void setPropagation(TransitionPropagation r4) {
        super.setPropagation(r4);
        this.mChangeFlags |= 2;
        int r0 = this.mTransitions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mTransitions.get(r1).setPropagation(r4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition setStartDelay(long r1) {
        return setStartDelay(r1);
    }

    @Override // androidx.transition.Transition
    public TransitionSet setStartDelay(long r1) {
        return (TransitionSet) super.setStartDelay(r1);
    }

    @Override // androidx.transition.Transition
    public String toString(String r6) {
        String r0 = super.toString(r6);
        int r1 = 0;
    L4:
        if (r1 >= this.mTransitions.size()) goto L6;
        StringBuilder r2 = new StringBuilder();
        r2.append(r0);
        r2.append("\n");
        r2.append(this.mTransitions.get(r1).toString(r6 + "  "));
        r0 = r2.toString();
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }
}
