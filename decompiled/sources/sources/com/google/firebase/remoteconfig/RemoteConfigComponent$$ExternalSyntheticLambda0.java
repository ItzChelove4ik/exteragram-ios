package com.google.firebase.remoteconfig;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final /* synthetic */ class RemoteConfigComponent$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ RemoteConfigComponent f$0;

    public /* synthetic */ RemoteConfigComponent$$ExternalSyntheticLambda0(RemoteConfigComponent r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.f$0.getDefault();
    }
}
