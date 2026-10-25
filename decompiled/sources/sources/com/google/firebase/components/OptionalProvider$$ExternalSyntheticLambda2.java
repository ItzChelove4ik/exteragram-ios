package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public final /* synthetic */ class OptionalProvider$$ExternalSyntheticLambda2 implements Deferred.DeferredHandler {
    public final /* synthetic */ Deferred.DeferredHandler f$0;
    public final /* synthetic */ Deferred.DeferredHandler f$1;

    public /* synthetic */ OptionalProvider$$ExternalSyntheticLambda2(Deferred.DeferredHandler r1, Deferred.DeferredHandler r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public final void handle(Provider r2) {
        OptionalProvider.m1922$r8$lambda$SG6J0sYOwGLatJJ8BJZBQOrQm8(this.f$0, this.f$1, r2);
    }
}
