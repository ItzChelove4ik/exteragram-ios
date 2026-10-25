package com.google.firebase.remoteconfig.internal.rollouts;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;

/* loaded from: classes.dex */
public final /* synthetic */ class RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ RolloutsStateSubscriptionsHandler f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ RolloutsStateSubscriber f$2;

    public /* synthetic */ RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda0(RolloutsStateSubscriptionsHandler r1, Task r2, RolloutsStateSubscriber r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r3) {
        RolloutsStateSubscriptionsHandler.$r8$lambda$G2FlweAKkFv8RelwOveXNqMs9Nw(this.f$0, this.f$1, this.f$2, (ConfigContainer) r3);
    }
}
