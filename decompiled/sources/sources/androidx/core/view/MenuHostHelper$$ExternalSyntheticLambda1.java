package androidx.core.view;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* loaded from: classes3.dex */
public final /* synthetic */ class MenuHostHelper$$ExternalSyntheticLambda1 implements LifecycleEventObserver {
    public final /* synthetic */ MenuHostHelper f$0;
    public final /* synthetic */ MenuProvider f$1;

    public /* synthetic */ MenuHostHelper$$ExternalSyntheticLambda1(MenuHostHelper r1, MenuProvider r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner r2, Lifecycle.Event r3) {
        MenuHostHelper.m670$r8$lambda$zr_Wn7n1nYoeZCJzTn8vTFGOUc(this.f$0, this.f$1, r2, r3);
    }
}
