package kotlinx.coroutines;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.EventLoopImplBase;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\u0005J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u0005J\u001b\u0010\u0010\u001a\u00020\u00062\n\u0010\u000f\u001a\u00060\u0002j\u0002`\u0003H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0018\u0010\u0005J+\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u00122\n\u0010\u001a\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0006H\u0016¢\u0006\u0004\b \u0010\u0005J\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001e\u0010&\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b&\u0010'\u0012\u0004\b(\u0010\u0005R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\rR\u0014\u0010-\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\rR\u0014\u0010/\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\n¨\u00060"}, d2 = {"Lkotlinx/coroutines/DefaultExecutor;", "Lkotlinx/coroutines/EventLoopImplBase;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "shutdownError", "Ljava/lang/Thread;", "createThreadSync", "()Ljava/lang/Thread;", _UrlKt.FRAGMENT_ENCODE_SET, "notifyStartup", "()Z", "acknowledgeShutdownIfNeeded", "task", "enqueue", "(Ljava/lang/Runnable;)V", _UrlKt.FRAGMENT_ENCODE_SET, "now", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "delayedTask", "reschedule", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)V", "shutdown", "timeMillis", "block", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnTimeout", "(JLjava/lang/Runnable;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/DisposableHandle;", "run", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "KEEP_ALIVE_NANOS", "J", "_thread", "Ljava/lang/Thread;", "get_thread$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "debugStatus", "I", "isShutDown", "isShutdownRequested", "getThread", "thread", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDefaultExecutor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultExecutor.kt\nkotlinx/coroutines/DefaultExecutor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,195:1\n1#2:196\n*E\n"})
/* loaded from: classes.dex */
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {
    public static final DefaultExecutor INSTANCE = null;
    private static final long KEEP_ALIVE_NANOS = 0;
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    static {
        DefaultExecutor r0 = new DefaultExecutor();
        INSTANCE = r0;
        EventLoop.incrementUseCount$default(r0, false, 1, null);
        Long r02 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000);     // Catch: SecurityException -> L5
    L6:
        long r03 = r02.longValue();
        KEEP_ALIVE_NANOS = TimeUnit.MILLISECONDS.toNanos(r03);
        return;
    L5:
        r02 = 1000L;
        goto L6
    }

    private DefaultExecutor() {
    }

    private final synchronized void acknowledgeShutdownIfNeeded() {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L4:
        if (isShutdownRequested() == true) goto L8;
        monitor-exit(this);
        return;
    L8:
        debugStatus = 3;     // Catch: Throwable -> L11
        resetAll();     // Catch: Throwable -> L11
        notifyAll();     // Catch: Throwable -> L11
        monitor-exit(this);
    }

    private final synchronized Thread createThreadSync() {
        monitor-enter(this);
        Thread r0 = _thread;     // Catch: Throwable -> L7
        if (r0 != null) goto L9;
        r0 = new Thread(this, "kotlinx.coroutines.DefaultExecutor");     // Catch: Throwable -> L7
        _thread = r0;     // Catch: Throwable -> L7
        r0.setContextClassLoader(INSTANCE.getClass().getClassLoader());     // Catch: Throwable -> L7
        r0.setDaemon(true);     // Catch: Throwable -> L7
        r0.start();     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    private final boolean isShutDown() {
        if (debugStatus != 4) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean isShutdownRequested() {
        int r1 = debugStatus;
        if (r1 != 2) goto L5;
        return true;
    L5:
        if (r1 == 3) goto L11;
        return false;
    L11:
        return true;
    }

    private final synchronized boolean notifyStartup() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (isShutdownRequested() == false) goto L9;
        monitor-exit(this);
        return false;
    L9:
        debugStatus = 1;     // Catch: Throwable -> L12
        notifyAll();     // Catch: Throwable -> L12
        monitor-exit(this);
        return true;
    }

    private final void shutdownError() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // kotlinx.coroutines.EventLoopImplBase
    public void enqueue(Runnable r2) {
        if (isShutDown() == false) goto L5;
        shutdownError();
    L5:
        super.enqueue(r2);
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public Thread getThread() {
        Thread r0 = _thread;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return createThreadSync();
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long r1, Runnable r3, CoroutineContext r4) {
        return scheduleInvokeOnTimeout(r1, r3);
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public void reschedule(long r1, EventLoopImplBase.DelayedTask r3) {
        shutdownError();
    }

    @Override // java.lang.Runnable
    public void run() {
        ThreadLocalEventLoop.INSTANCE.setEventLoop$kotlinx_coroutines_core(this);
        AbstractTimeSourceKt.access$getTimeSource$p();
    L16:
        th = move-exception;
        _thread = null;
        acknowledgeShutdownIfNeeded();
        AbstractTimeSourceKt.access$getTimeSource$p();
        if (isEmpty() == true) goto L39;
        getThread();
    L39:
        throw th;
    L4:
        if (notifyStartup() == true) goto L9;
        _thread = null;
        acknowledgeShutdownIfNeeded();
        AbstractTimeSourceKt.access$getTimeSource$p();
        if (isEmpty() == true) goto L48;
        getThread();
        return;
    L48:
        return;
    L9:
        long r3 = Long.MAX_VALUE;
    L10:
        Thread.interrupted();     // Catch: Throwable -> L16
        long r5 = processNextEvent();     // Catch: Throwable -> L16
        if (r5 != LongCompanionObject.MAX_VALUE) goto L25;
        AbstractTimeSourceKt.access$getTimeSource$p();     // Catch: Throwable -> L16
        long r10 = System.nanoTime();     // Catch: Throwable -> L16
        if (r3 == LongCompanionObject.MAX_VALUE) goto L15;
    L18:
        long r102 = r3 - r10;
        if (r102 <= 0) goto L20;
        r5 = RangesKt.coerceAtMost(r5, r102);     // Catch: Throwable -> L16
    L27:
        if (r5 <= 0) goto L10;
        if (isShutdownRequested() == true) goto L30;
        AbstractTimeSourceKt.access$getTimeSource$p();     // Catch: Throwable -> L16
        LockSupport.parkNanos(this, r5);     // Catch: Throwable -> L16
        goto L10
    L30:
        _thread = null;
        acknowledgeShutdownIfNeeded();
        AbstractTimeSourceKt.access$getTimeSource$p();
        if (isEmpty() == true) goto L50;
        getThread();
        return;
    L50:
        return;
    L20:
        _thread = null;
        acknowledgeShutdownIfNeeded();
        AbstractTimeSourceKt.access$getTimeSource$p();
        if (isEmpty() == true) goto L49;
        getThread();
        return;
    L49:
        return;
    L15:
        r3 = KEEP_ALIVE_NANOS + r10;
        goto L18
    L25:
        r3 = Long.MAX_VALUE;
        goto L27
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.EventLoop
    public void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "DefaultExecutor";
    }
}
