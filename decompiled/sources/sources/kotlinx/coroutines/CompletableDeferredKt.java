package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a+\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\n\u001a!\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\f¨\u0006\r"}, d2 = {"T", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlin/Result;", "result", _UrlKt.FRAGMENT_ENCODE_SET, "completeWith", "(Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/Job;", "parent", "CompletableDeferred", "(Lkotlinx/coroutines/Job;)Lkotlinx/coroutines/CompletableDeferred;", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCompletableDeferred.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CompletableDeferred.kt\nkotlinx/coroutines/CompletableDeferredKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,122:1\n1#2:123\n*E\n"})
/* loaded from: classes.dex */
public abstract class CompletableDeferredKt {
    public static final <T> CompletableDeferred<T> CompletableDeferred(T r2) {
        CompletableDeferredImpl r0 = new CompletableDeferredImpl(null);
        r0.complete(r2);
        return r0;
    }

    public static final <T> CompletableDeferred<T> CompletableDeferred(Job r1) {
        return new CompletableDeferredImpl(r1);
    }

    public static /* synthetic */ CompletableDeferred CompletableDeferred$default(Job r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = null;
    L6:
        return CompletableDeferred(r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> boolean completeWith(CompletableDeferred<T> r1, Object r2) {
        Throwable r0 = Result.m2021exceptionOrNullimpl(r2);
        if (r0 != null) goto L7;
        return r1.complete(r2);
    L7:
        return r1.completeExceptionally(r0);
    }
}
