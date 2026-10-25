package androidx.transition;

import androidx.transition.Transition;

/* loaded from: classes4.dex */
public final /* synthetic */ class Transition$TransitionNotification$$ExternalSyntheticLambda1 implements Transition.TransitionNotification {
    public /* synthetic */ Transition$TransitionNotification$$ExternalSyntheticLambda1() {
    }

    @Override // androidx.transition.Transition.TransitionNotification
    public final void notifyListener(Transition.TransitionListener r1, Transition r2, boolean r3) {
        r1.onTransitionEnd(r2, r3);
    }
}
