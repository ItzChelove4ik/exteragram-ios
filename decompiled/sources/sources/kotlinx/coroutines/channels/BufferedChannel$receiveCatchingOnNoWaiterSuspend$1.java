package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.BufferedChannel", f = "BufferedChannel.kt", i = {0, 0, 0, 0}, l = {3093}, m = "receiveCatchingOnNoWaiterSuspend-GKJJFZk", n = {"segment", "index", "r", "$i$f$suspendCancellableCoroutineReusable"}, s = {"L$0", "I$0", "J$0", "I$1"}, v = 1)
/* loaded from: classes5.dex */
public final class BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    public BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(BufferedChannel<E> r1, Continuation<? super BufferedChannel$receiveCatchingOnNoWaiterSuspend$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        this.result = r7;
        this.label |= Integer.MIN_VALUE;
        Object r6 = BufferedChannel.m3526access$receiveCatchingOnNoWaiterSuspendGKJJFZk(this.this$0, null, 0, 0, this);
        if (r6 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r6;
    L6:
        return ChannelResult.m3534boximpl(r6);
    }
}
