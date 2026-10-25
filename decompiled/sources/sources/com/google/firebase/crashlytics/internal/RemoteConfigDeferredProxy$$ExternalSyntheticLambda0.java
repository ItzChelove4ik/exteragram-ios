package com.google.firebase.crashlytics.internal;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public final /* synthetic */ class RemoteConfigDeferredProxy$$ExternalSyntheticLambda0 implements Deferred.DeferredHandler {
    public final /* synthetic */ CrashlyticsRemoteConfigListener f$0;

    public /* synthetic */ RemoteConfigDeferredProxy$$ExternalSyntheticLambda0(CrashlyticsRemoteConfigListener r1) {
        this.f$0 = r1;
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public final void handle(Provider r1) {
        RemoteConfigDeferredProxy.$r8$lambda$tM9cwbkvs3BgPiTMmQjNX30V2r4(this.f$0, r1);
    }
}
