package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0013\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0004\u001a\u00020\u0006*\u00020\u0005H\u0007¢\u0006\u0004\b\u0002\u0010\u0007\u001a\u0011\u0010\b\u001a\u00020\u0005*\u00020\u0006¢\u0006\u0004\b\b\u0010\t*\f\b\u0007\u0010\n\"\u00020\u00012\u00020\u0001¨\u0006\u000b"}, d2 = {"Ljava/util/concurrent/ExecutorService;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "from", "(Ljava/util/concurrent/ExecutorService;)Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "asCoroutineDispatcher", "Ljava/util/concurrent/Executor;", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Ljava/util/concurrent/Executor;)Lkotlinx/coroutines/CoroutineDispatcher;", "asExecutor", "(Lkotlinx/coroutines/CoroutineDispatcher;)Ljava/util/concurrent/Executor;", "CloseableCoroutineDispatcher", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ExecutorsKt {
    public static final Executor asExecutor(CoroutineDispatcher r1) {
        if ((r1 instanceof ExecutorCoroutineDispatcher) == false) goto L5;
        ExecutorCoroutineDispatcher r0 = (ExecutorCoroutineDispatcher) r1;
    L6:
        if (r0 == null) goto L12;
        Executor r02 = r0.getExecutor();
        if (r02 == null) goto L12;
        return r02;
    L12:
        return new DispatcherExecutor(r1);
    L5:
        r0 = null;
        goto L6
    }

    @JvmName(name = "from")
    public static final CoroutineDispatcher from(Executor r1) {
        if ((r1 instanceof DispatcherExecutor) == false) goto L5;
        DispatcherExecutor r0 = (DispatcherExecutor) r1;
    L6:
        if (r0 == null) goto L12;
        CoroutineDispatcher r02 = r0.dispatcher;
        if (r02 == null) goto L12;
        return r02;
    L12:
        return new ExecutorCoroutineDispatcherImpl(r1);
    L5:
        r0 = null;
        goto L6
    }

    @JvmName(name = "from")
    public static final ExecutorCoroutineDispatcher from(ExecutorService r1) {
        return new ExecutorCoroutineDispatcherImpl(r1);
    }
}
