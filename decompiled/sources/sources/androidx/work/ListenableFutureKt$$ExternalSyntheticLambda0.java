package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineStart;

/* loaded from: classes4.dex */
public final /* synthetic */ class ListenableFutureKt$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ CoroutineContext f$0;
    public final /* synthetic */ CoroutineStart f$1;
    public final /* synthetic */ Function2 f$2;

    public /* synthetic */ ListenableFutureKt$$ExternalSyntheticLambda0(CoroutineContext r1, CoroutineStart r2, Function2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r3) {
        return ListenableFutureKt.m968$r8$lambda$GaXsP0J9ZiqTNKOjjWkBCoRah8(this.f$0, this.f$1, this.f$2, r3);
    }
}
