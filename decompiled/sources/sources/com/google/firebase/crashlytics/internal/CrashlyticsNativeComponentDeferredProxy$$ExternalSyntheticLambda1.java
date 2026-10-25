package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public final /* synthetic */ class CrashlyticsNativeComponentDeferredProxy$$ExternalSyntheticLambda1 implements Deferred.DeferredHandler {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ StaticSessionData f$3;

    public /* synthetic */ CrashlyticsNativeComponentDeferredProxy$$ExternalSyntheticLambda1(String r1, String r2, long r3, StaticSessionData r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public final void handle(Provider r7) {
        CrashlyticsNativeComponentDeferredProxy.m1931$r8$lambda$ABui6NqCWHawGe888TdFdiMYTk(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
