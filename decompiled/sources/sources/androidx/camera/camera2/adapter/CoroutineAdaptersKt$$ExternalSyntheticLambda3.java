package androidx.camera.camera2.adapter;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlinx.coroutines.Deferred;

/* loaded from: classes3.dex */
public final /* synthetic */ class CoroutineAdaptersKt$$ExternalSyntheticLambda3 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Deferred f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CoroutineAdaptersKt$$ExternalSyntheticLambda3(Deferred r1, Object r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return CoroutineAdaptersKt.$r8$lambda$CWMmOpF3SrnUZxqkZ9tNskVvWfc(this.f$0, this.f$1, r2);
    }
}
