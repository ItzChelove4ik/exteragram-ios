package androidx.camera.core.processing;

import androidx.camera.core.processing.SurfaceEdge;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes3.dex */
public final /* synthetic */ class SurfaceEdge$SettableSurface$$ExternalSyntheticLambda1 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ SurfaceEdge.SettableSurface f$0;

    public /* synthetic */ SurfaceEdge$SettableSurface$$ExternalSyntheticLambda1(SurfaceEdge.SettableSurface r1) {
        this.f$0 = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return SurfaceEdge.SettableSurface.m615$r8$lambda$VdiGildDqQQQLDZiSabHKVcb7o(this.f$0, r1);
    }
}
