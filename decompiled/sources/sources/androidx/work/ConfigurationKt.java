package androidx.work;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\t\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "isTaskExecutor", "Ljava/util/concurrent/Executor;", "createDefaultExecutor", "(Z)Ljava/util/concurrent/Executor;", "Landroidx/work/Tracer;", "createDefaultTracer", "()Landroidx/work/Tracer;", "Lkotlin/coroutines/CoroutineContext;", "asExecutor", "(Lkotlin/coroutines/CoroutineContext;)Ljava/util/concurrent/Executor;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ConfigurationKt {
    public static final /* synthetic */ Executor access$asExecutor(CoroutineContext r0) {
        return asExecutor(r0);
    }

    public static final /* synthetic */ Executor access$createDefaultExecutor(boolean r0) {
        return createDefaultExecutor(r0);
    }

    public static final /* synthetic */ Tracer access$createDefaultTracer() {
        return createDefaultTracer();
    }

    private static final Executor asExecutor(CoroutineContext r2) {
        if (r2 == null) goto L5;
        ContinuationInterceptor r22 = (ContinuationInterceptor) r2.get(ContinuationInterceptor.Key);
    L7:
        if ((r22 instanceof CoroutineDispatcher) == false) goto L9;
        CoroutineDispatcher r23 = (CoroutineDispatcher) r22;
    L10:
        if (r23 != null) goto L12;
        return null;
    L12:
        return ExecutorsKt.asExecutor(r23);
    L9:
        r23 = null;
        goto L10
    L5:
        r22 = null;
        goto L7
    }

    private static final Executor createDefaultExecutor(boolean r2) {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new ConfigurationKt$createDefaultExecutor$factory$1(r2));
    }

    private static final Tracer createDefaultTracer() {
        return new ConfigurationKt$createDefaultTracer$tracer$1();
    }
}
