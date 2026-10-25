package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aU\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"runBlocking", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "runBlockingK", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/BuildersKt")
/* loaded from: classes5.dex */
abstract /* synthetic */ class BuildersKt__Builders_concurrentKt {
    @JvmName(name = "runBlockingK")
    public static final <T> T runBlockingK(CoroutineContext r2, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r3) {
        if (((ContinuationInterceptor) r2.get(ContinuationInterceptor.Key)) != null) goto L5;
        EventLoop r0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        CoroutineContext r22 = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, r2.plus(r0));
    L7:
        return (T) BuildersKt.runBlockingImpl(r22, r0, r3);
    L5:
        r0 = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
        r22 = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, r2);
        goto L7
    }

    public static /* synthetic */ Object runBlockingK$default(CoroutineContext r0, Function2 r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r0 = EmptyCoroutineContext.INSTANCE;
    L6:
        return BuildersKt.runBlockingK(r0, r1);
    }
}
