package androidx.core.view;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* loaded from: classes3.dex */
public final /* synthetic */ class MenuHostHelper$$ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final /* synthetic */ MenuHostHelper f$0;
    public final /* synthetic */ Lifecycle.State f$1;
    public final /* synthetic */ MenuProvider f$2;

    public /* synthetic */ MenuHostHelper$$ExternalSyntheticLambda0(MenuHostHelper r1, Lifecycle.State r2, MenuProvider r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner r3, Lifecycle.Event r4) {
        MenuHostHelper.$r8$lambda$L4bp1I_Mq82BF4CTDbjIos9_dFE(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
