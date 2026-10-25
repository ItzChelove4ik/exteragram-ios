package kotlinx.coroutines.internal;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007*\f\b\u0000\u0010\t\"\u00020\b2\u00020\b*\u001e\b\u0000\u0010\f\u001a\u0004\b\u0000\u0010\n\"\b\u0012\u0004\u0012\u00028\u00000\u000b2\b\u0012\u0004\u0012\u00028\u00000\u000b¨\u0006\r"}, d2 = {"Ljava/util/concurrent/Executor;", "executor", _UrlKt.FRAGMENT_ENCODE_SET, "removeFutureOnCancel", "(Ljava/util/concurrent/Executor;)Z", "Ljava/lang/reflect/Method;", "REMOVE_FUTURE_ON_CANCEL", "Ljava/lang/reflect/Method;", "Ljava/util/concurrent/locks/ReentrantLock;", "ReentrantLock", "T", "Ljava/util/concurrent/atomic/AtomicReference;", "WorkaroundAtomicReference", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ConcurrentKt {
    private static final Method REMOVE_FUTURE_ON_CANCEL = null;

    static {
        Method r0 = ScheduledThreadPoolExecutor.class.getMethod("setRemoveOnCancelPolicy", new Class[]{Boolean.TYPE});     // Catch: Throwable -> L4
    L5:
        REMOVE_FUTURE_ON_CANCEL = r0;
        return;
    L4:
        r0 = null;
        goto L5
    }

    public static final boolean removeFutureOnCancel(Executor r3) {
    L15:
        return false;
    L4:
        if ((r3 instanceof ScheduledThreadPoolExecutor) == false) goto L6;
        ScheduledThreadPoolExecutor r32 = (ScheduledThreadPoolExecutor) r3;     // Catch: Throwable -> L15
    L7:
        if (r32 != null) goto L9;
        return false;
    L9:
        Method r1 = REMOVE_FUTURE_ON_CANCEL;     // Catch: Throwable -> L15
        if (r1 != null) goto L12;
        return false;
    L12:
        r1.invoke(r32, new Object[]{Boolean.TRUE});     // Catch: Throwable -> L15
        return true;
    L6:
        r32 = null;
        goto L7
    }
}
