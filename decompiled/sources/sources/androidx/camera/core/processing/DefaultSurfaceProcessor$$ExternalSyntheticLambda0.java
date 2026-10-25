package androidx.camera.core.processing;

import androidx.camera.core.DynamicRange;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Map;

/* loaded from: classes3.dex */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ DynamicRange f$1;
    public final /* synthetic */ Map f$2;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda0(DefaultSurfaceProcessor r1, DynamicRange r2, Map r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r3) {
        return DefaultSurfaceProcessor.$r8$lambda$eZcZsxjTSPFb419HaHACXsjhtYA(this.f$0, this.f$1, this.f$2, r3);
    }
}
