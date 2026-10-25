package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.BufferedChannel", f = "BufferedChannel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {736}, m = "receiveCatching-JP2dKIU$suspendImpl", n = {"$this", "this_$iv", "segment$iv", "updCellResult$iv", "segm", "$i$f$receiveImpl", "r$iv", "id$iv", "i$iv", "r", "i", "$i$a$-receiveImpl-BufferedChannel$receiveCatching$5"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "J$0", "J$1", "I$1", "J$2", "I$2", "I$3"}, v = 1)
/* loaded from: classes5.dex */
public final class BufferedChannel$receiveCatching$1<E> extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    public BufferedChannel$receiveCatching$1(BufferedChannel<E> r1, Continuation<? super BufferedChannel$receiveCatching$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        Object r1 = BufferedChannel.m3528receiveCatchingJP2dKIU$suspendImpl(this.this$0, this);
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r1;
    L6:
        return ChannelResult.m3534boximpl(r1);
    }
}
