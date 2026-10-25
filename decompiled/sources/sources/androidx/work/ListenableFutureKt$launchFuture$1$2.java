package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.work.ListenableFutureKt$launchFuture$1$2", f = "ListenableFuture.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class ListenableFutureKt$launchFuture$1$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<CoroutineScope, Continuation<? super T>, Object> $block;
    final /* synthetic */ CallbackToFutureAdapter.Completer<T> $completer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: Multi-variable type inference failed */
    public ListenableFutureKt$launchFuture$1$2(Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r1, CallbackToFutureAdapter.Completer<T> r2, Continuation<? super ListenableFutureKt$launchFuture$1$2> r3) {
        this.$block = r1;
        this.$completer = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        ListenableFutureKt$launchFuture$1$2 r0 = new ListenableFutureKt$launchFuture$1$2(this.$block, this.$completer, r4);
        r0.L$0 = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((ListenableFutureKt$launchFuture$1$2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L11;
        if (r1 != 1) goto L9;
        ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L7 CancellationException -> L18
    L15:
        this.$completer.set(r4);     // Catch: Throwable -> L7 CancellationException -> L18
    L20:
        return Unit.INSTANCE;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        ResultKt.throwOnFailure(r4);
        CoroutineScope r42 = (CoroutineScope) this.L$0;
        Function2<CoroutineScope, Continuation<? super T>, Object> r12 = this.$block;     // Catch: Throwable -> L7 CancellationException -> L18
        this.label = 1;     // Catch: Throwable -> L7 CancellationException -> L18
        r4 = r12.invoke(r42, this);     // Catch: Throwable -> L7 CancellationException -> L18
        if (r4 != r0) goto L15;
        return r0;
    L18:
        this.$completer.setCancelled();
    L7:
        th = move-exception;
        this.$completer.setException(th);
        goto L20
    }
}
