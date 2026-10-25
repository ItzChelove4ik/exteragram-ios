package androidx.camera.camera2.adapter;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlinx.coroutines.Job;

/* loaded from: classes3.dex */
public final /* synthetic */ class CoroutineAdaptersKt$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Job f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CoroutineAdaptersKt$$ExternalSyntheticLambda0(Job r1, Object r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return CoroutineAdaptersKt.$r8$lambda$291ob6vuDNDeI74nkG615Jp79gg(this.f$0, this.f$1, r2);
    }
}
