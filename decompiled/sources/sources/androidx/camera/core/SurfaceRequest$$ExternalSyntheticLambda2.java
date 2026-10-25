package androidx.camera.core;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public final /* synthetic */ class SurfaceRequest$$ExternalSyntheticLambda2 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ AtomicReference f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ SurfaceRequest$$ExternalSyntheticLambda2(AtomicReference r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return SurfaceRequest.$r8$lambda$uGrSSZ1g66FkCiLouPIMRcy7vLg(this.f$0, this.f$1, r2);
    }
}
