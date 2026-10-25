package androidx.transition;

import androidx.transition.Transition;

/* loaded from: classes4.dex */
public final /* synthetic */ class Transition$TransitionNotification$$ExternalSyntheticLambda0 implements Transition.TransitionNotification {
    public /* synthetic */ Transition$TransitionNotification$$ExternalSyntheticLambda0() {
    }

    @Override // androidx.transition.Transition.TransitionNotification
    public final void notifyListener(Transition.TransitionListener r1, Transition r2, boolean r3) {
        r1.onTransitionStart(r2, r3);
    }
}
