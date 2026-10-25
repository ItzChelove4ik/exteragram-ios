package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop;

/* loaded from: classes.dex */
public class RemoteConfigDeferredProxy {
    private final Deferred<FirebaseRemoteConfigInterop> remoteConfigInteropDeferred;

    public static /* synthetic */ void $r8$lambda$tM9cwbkvs3BgPiTMmQjNX30V2r4(CrashlyticsRemoteConfigListener r1, Provider r2) {
        ((FirebaseRemoteConfigInterop) r2.get()).registerRolloutsStateSubscriber("firebase", r1);
        Logger.getLogger().d("Registering RemoteConfig Rollouts subscriber");
    }

    public RemoteConfigDeferredProxy(Deferred<FirebaseRemoteConfigInterop> r1) {
        this.remoteConfigInteropDeferred = r1;
    }

    public void setupListener(UserMetadata r2) {
        if (r2 != null) goto L5;
        Logger.getLogger().w("Didn't successfully register with UserMetadata for rollouts listener");
        return;
    L5:
        CrashlyticsRemoteConfigListener r0 = new CrashlyticsRemoteConfigListener(r2);
        this.remoteConfigInteropDeferred.whenAvailable(new RemoteConfigDeferredProxy$$ExternalSyntheticLambda0(r0));
    }
}
