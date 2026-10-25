package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aF\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\u001aN\u0010\u000b\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0000¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"runBlocking", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "runBlockingImpl", "newContext", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/EventLoop;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/BuildersKt")
/* loaded from: classes.dex */
abstract /* synthetic */ class BuildersKt__BuildersKt {
    @LowPriorityInOverloadResolution
    public static final <T> T runBlocking(CoroutineContext r0, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r1) {
        return (T) BuildersKt.runBlockingK(r0, r1);
    }

    public static /* synthetic */ Object runBlocking$default(CoroutineContext r0, Function2 r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r0 = EmptyCoroutineContext.INSTANCE;
    L6:
        return BuildersKt.runBlocking(r0, r1);
    }

    public static final <T> T runBlockingImpl(CoroutineContext r2, EventLoop r3, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r4) {
        BlockingCoroutine r0 = new BlockingCoroutine(r2, Thread.currentThread(), r3);
        r0.start(CoroutineStart.DEFAULT, r0, r4);
        return (T) r0.joinBlocking();
    }
}
