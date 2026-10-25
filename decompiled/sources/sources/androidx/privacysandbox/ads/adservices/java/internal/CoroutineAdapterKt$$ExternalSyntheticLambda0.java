package androidx.privacysandbox.ads.adservices.java.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlinx.coroutines.Deferred;

/* loaded from: classes4.dex */
public final /* synthetic */ class CoroutineAdapterKt$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Deferred f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CoroutineAdapterKt$$ExternalSyntheticLambda0(Deferred r1, Object r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return CoroutineAdapterKt.$r8$lambda$u17zFg4_yVVb03rPOrSamnVgzLA(this.f$0, this.f$1, r2);
    }
}
