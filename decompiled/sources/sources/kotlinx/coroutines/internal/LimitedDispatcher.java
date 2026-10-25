package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u00011B!\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00132\n\u0010\u0015\u001a\u00060\rj\u0002`\u000eH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00132\n\u0010\u0015\u001a\u00060\rj\u0002`\u000eH\u0017¢\u0006\u0004\b\u0019\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ&\u0010 \u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\u001eH\u0096\u0001¢\u0006\u0004\b \u0010!J,\u0010#\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u001c2\n\u0010\u0015\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0096\u0001¢\u0006\u0004\b#\u0010$R\u0014\u0010\u0003\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010%R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010&R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010'R\u001e\u0010)\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010-\u001a\u00060+j\u0002`,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u000b\u00100\u001a\u00020/8\u0002X\u0082\u0004¨\u00062"}, d2 = {"Lkotlinx/coroutines/internal/LimitedDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "dispatcher", _UrlKt.FRAGMENT_ENCODE_SET, "parallelism", _UrlKt.FRAGMENT_ENCODE_SET, "name", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;ILjava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "tryAllocateWorker", "()Z", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "obtainTaskOrDeallocateWorker", "()Ljava/lang/Runnable;", "limitedParallelism", "(ILjava/lang/String;)Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/coroutines/CoroutineContext;", "context", "block", _UrlKt.FRAGMENT_ENCODE_SET, "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "dispatchYield", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "timeMillis", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "scheduleResumeAfterDelay", "(JLkotlinx/coroutines/CancellableContinuation;)V", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnTimeout", "(JLjava/lang/Runnable;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/CoroutineDispatcher;", "I", "Ljava/lang/String;", "Lkotlinx/coroutines/internal/LockFreeTaskQueue;", "queue", "Lkotlinx/coroutines/internal/LockFreeTaskQueue;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/SynchronizedObject;", "workerAllocationLock", "Ljava/lang/Object;", "Lkotlinx/atomicfu/AtomicInt;", "runningWorkers", "Worker", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLimitedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,154:1\n62#1,18:155\n62#1,18:173\n26#2:191\n26#2:193\n16#3:192\n16#3:194\n*S KotlinDebug\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher\n*L\n44#1:155,18\n51#1:173,18\n85#1:191\n98#1:193\n85#1:192\n98#1:194\n*E\n"})
/* loaded from: classes.dex */
public final class LimitedDispatcher extends CoroutineDispatcher implements Delay {
    private static final /* synthetic */ AtomicIntegerFieldUpdater runningWorkers$volatile$FU = null;
    private final /* synthetic */ Delay $$delegate_0;
    private final CoroutineDispatcher dispatcher;
    private final String name;
    private final int parallelism;
    private final LockFreeTaskQueue<Runnable> queue;
    private volatile /* synthetic */ int runningWorkers$volatile;
    private final Object workerAllocationLock;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\u0012\n\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/internal/LimitedDispatcher$Worker;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "currentTask", "<init>", "(Lkotlinx/coroutines/internal/LimitedDispatcher;Ljava/lang/Runnable;)V", "Ljava/lang/Runnable;", "run", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nLimitedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher$Worker\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,154:1\n26#2:155\n16#3:156\n*S KotlinDebug\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher$Worker\n*L\n139#1:155\n139#1:156\n*E\n"})
    public final class Worker implements Runnable {
        private Runnable currentTask;
        final /* synthetic */ LimitedDispatcher this$0;

        public Worker(LimitedDispatcher r1, Runnable r2) {
            this.this$0 = r1;
            this.currentTask = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int r0 = 0;
        L25:
            this.currentTask.run();     // Catch: Throwable -> L5
        L7:
            Runnable r1 = LimitedDispatcher.access$obtainTaskOrDeallocateWorker(this.this$0);     // Catch: Throwable -> L16
            if (r1 == null) goto L37;
            this.currentTask = r1;     // Catch: Throwable -> L16
            r0 = r0 + 1;     // Catch: Throwable -> L16
            if (r0 < 16) goto L25;
            if (DispatchedContinuationKt.safeIsDispatchNeeded(LimitedDispatcher.access$getDispatcher$p(this.this$0), this.this$0) == false) goto L25;
            DispatchedContinuationKt.safeDispatch(LimitedDispatcher.access$getDispatcher$p(this.this$0), this.this$0, this);     // Catch: Throwable -> L16
            return;
        L37:
            return;
        L5:
            th = move-exception;
            CoroutineExceptionHandlerKt.handleCoroutineException(EmptyCoroutineContext.INSTANCE, th);     // Catch: Throwable -> L16
        L16:
            th = move-exception;
            Object r12 = LimitedDispatcher.access$getWorkerAllocationLock$p(this.this$0);
            LimitedDispatcher r3 = this.this$0;
            monitor-enter(r12);
            LimitedDispatcher.access$getRunningWorkers$volatile$FU().decrementAndGet(r3);     // Catch: Throwable -> L22
            throw th;
        L22:
            th = move-exception;
            throw th;
        }
    }

    static {
        runningWorkers$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(LimitedDispatcher.class, "runningWorkers$volatile");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LimitedDispatcher(CoroutineDispatcher r2, int r3, String r4) {
        if ((r2 instanceof Delay) == false) goto L5;
        Delay r0 = (Delay) r2;
    L6:
        if (r0 != null) goto L8;
        r0 = DefaultExecutorKt.getDefaultDelay();
    L8:
        this.$$delegate_0 = r0;
        this.dispatcher = r2;
        this.parallelism = r3;
        this.name = r4;
        this.queue = new LockFreeTaskQueue(false);
        this.workerAllocationLock = new Object();
        return;
    L5:
        r0 = null;
        goto L6
    }

    public static final /* synthetic */ CoroutineDispatcher access$getDispatcher$p(LimitedDispatcher r0) {
        return r0.dispatcher;
    }

    public static final /* synthetic */ AtomicIntegerFieldUpdater access$getRunningWorkers$volatile$FU() {
        return getRunningWorkers$volatile$FU();
    }

    public static final /* synthetic */ Object access$getWorkerAllocationLock$p(LimitedDispatcher r0) {
        return r0.workerAllocationLock;
    }

    public static final /* synthetic */ Runnable access$obtainTaskOrDeallocateWorker(LimitedDispatcher r0) {
        return r0.obtainTaskOrDeallocateWorker();
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater getRunningWorkers$volatile$FU() {
        return runningWorkers$volatile$FU;
    }

    private final Runnable obtainTaskOrDeallocateWorker() {
    L2:
        Runnable r0 = this.queue.removeFirstOrNull();
        if (r0 != null) goto L17;
        Object r02 = this.workerAllocationLock;
        monitor-enter(r02);
        getRunningWorkers$volatile$FU().decrementAndGet(this);     // Catch: Throwable -> L14
        if (this.queue.getSize() == 0) goto L8;
        getRunningWorkers$volatile$FU().incrementAndGet(this);     // Catch: Throwable -> L14
        monitor-exit(r02);
        goto L2
    L8:
        monitor-exit(r02);
        return null;
    L14:
        th = move-exception;
        throw th;
    L17:
        return r0;
    }

    private final boolean tryAllocateWorker() {
        Object r0 = this.workerAllocationLock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (getRunningWorkers$volatile$FU().get(this) < this.parallelism) goto L9;
        monitor-exit(r0);
        return false;
    L9:
        getRunningWorkers$volatile$FU().incrementAndGet(this);     // Catch: Throwable -> L13
        monitor-exit(r0);
        return true;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext r1, Runnable r2) {
        this.queue.addLast(r2);
        if (getRunningWorkers$volatile$FU().get(this) < this.parallelism) goto L5;
        return;
    L5:
        if (tryAllocateWorker() == false) goto L17;
        Runnable r12 = obtainTaskOrDeallocateWorker();
        if (r12 == null) goto L18;
        Worker r22 = new Worker(this, r12);     // Catch: Throwable -> L11
        DispatchedContinuationKt.safeDispatch(this.dispatcher, this, r22);     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        getRunningWorkers$volatile$FU().decrementAndGet(this);
        throw th;
    L18:
        return;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext r1, Runnable r2) {
        this.queue.addLast(r2);
        if (getRunningWorkers$volatile$FU().get(this) < this.parallelism) goto L5;
        return;
    L5:
        if (tryAllocateWorker() == false) goto L17;
        Runnable r12 = obtainTaskOrDeallocateWorker();
        if (r12 == null) goto L18;
        Worker r22 = new Worker(this, r12);     // Catch: Throwable -> L11
        this.dispatcher.dispatchYield(this, r22);     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        getRunningWorkers$volatile$FU().decrementAndGet(this);
        throw th;
    L18:
        return;
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long r1, Runnable r3, CoroutineContext r4) {
        return this.$$delegate_0.invokeOnTimeout(r1, r3, r4);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int r2, String r3) {
        LimitedDispatcherKt.checkParallelism(r2);
        if (r2 < this.parallelism) goto L7;
        return LimitedDispatcherKt.namedOrThis(this, r3);
    L7:
        return super.limitedParallelism(r2, r3);
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long r1, CancellableContinuation<? super Unit> r3) {
        this.$$delegate_0.scheduleResumeAfterDelay(r1, r3);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String r0 = this.name;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return this.dispatcher + ".limitedParallelism(" + this.parallelism + ')';
    }
}
