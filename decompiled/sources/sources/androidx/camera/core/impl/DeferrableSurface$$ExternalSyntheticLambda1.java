package androidx.camera.core.impl;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes3.dex */
public final /* synthetic */ class DeferrableSurface$$ExternalSyntheticLambda1 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ DeferrableSurface f$0;

    public /* synthetic */ DeferrableSurface$$ExternalSyntheticLambda1(DeferrableSurface r1) {
        this.f$0 = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return DeferrableSurface.$r8$lambda$n5A2Gczc3HX9R1GqVs9IlIvNPv4(this.f$0, r1);
    }
}
