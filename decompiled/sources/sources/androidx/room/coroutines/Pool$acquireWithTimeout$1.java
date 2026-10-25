package androidx.room.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.coroutines.Pool", f = "ConnectionPoolImpl.kt", i = {0, 0, 0}, l = {231}, m = "acquireWithTimeout-KLykuaI", n = {"onTimeout", "connection", "timeout"}, s = {"L$0", "L$1", "J$0"})
/* loaded from: classes4.dex */
public final class Pool$acquireWithTimeout$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Pool this$0;

    public Pool$acquireWithTimeout$1(Pool r1, Continuation<? super Pool$acquireWithTimeout$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        this.result = r4;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m947acquireWithTimeoutKLykuaI(0, null, this);
    }
}
