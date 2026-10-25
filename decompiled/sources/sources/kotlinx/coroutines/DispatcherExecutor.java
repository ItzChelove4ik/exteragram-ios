package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/DispatcherExecutor;", "Ljava/util/concurrent/Executor;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "execute", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
final class DispatcherExecutor implements Executor {

    @JvmField
    public final CoroutineDispatcher dispatcher;

    public DispatcherExecutor(CoroutineDispatcher r1) {
        this.dispatcher = r1;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r3) {
        CoroutineDispatcher r0 = this.dispatcher;
        EmptyCoroutineContext r1 = EmptyCoroutineContext.INSTANCE;
        if (DispatchedContinuationKt.safeIsDispatchNeeded(r0, r1) == false) goto L6;
        DispatchedContinuationKt.safeDispatch(this.dispatcher, r1, r3);
        return;
    L6:
        r3.run();
    }

    public String toString() {
        return this.dispatcher.toString();
    }
}
