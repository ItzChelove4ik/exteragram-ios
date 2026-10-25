package androidx.camera.core.impl;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes3.dex */
public final /* synthetic */ class DeferrableSurface$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ DeferrableSurface f$0;

    public /* synthetic */ DeferrableSurface$$ExternalSyntheticLambda0(DeferrableSurface r1) {
        this.f$0 = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return DeferrableSurface.m599$r8$lambda$2bNbZSrwzPE6VUuBbHBM9VrhXU(this.f$0, r1);
    }
}
