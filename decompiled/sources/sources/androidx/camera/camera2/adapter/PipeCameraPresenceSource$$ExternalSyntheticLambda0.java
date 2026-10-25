package androidx.camera.camera2.adapter;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes3.dex */
public final /* synthetic */ class PipeCameraPresenceSource$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ PipeCameraPresenceSource f$0;

    public /* synthetic */ PipeCameraPresenceSource$$ExternalSyntheticLambda0(PipeCameraPresenceSource r1) {
        this.f$0 = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return PipeCameraPresenceSource.$r8$lambda$pNibnT2Ln7rKug19LH2UnicKFAs(this.f$0, r1);
    }
}
