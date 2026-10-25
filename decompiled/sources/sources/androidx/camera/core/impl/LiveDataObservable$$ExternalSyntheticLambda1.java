package androidx.camera.core.impl;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes3.dex */
public final /* synthetic */ class LiveDataObservable$$ExternalSyntheticLambda1 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ LiveDataObservable f$0;

    public /* synthetic */ LiveDataObservable$$ExternalSyntheticLambda1(LiveDataObservable r1) {
        this.f$0 = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return LiveDataObservable.m600$r8$lambda$DTQl2TjsJw2q1DsAXZ5y6fSWXY(this.f$0, r1);
    }
}
