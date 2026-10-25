package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\u0018\u0000 -2\u00020\u0001:\u0003+,-B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001eJ\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0002J \u0010\"\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!2\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0010H\u0002J\b\u0010%\u001a\u0004\u0018\u00010!J\b\u0010&\u001a\u00020\u001cH\u0002J\u0006\u0010'\u001a\u00020\u0017J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00170)J\u0006\u0010*\u001a\u00020\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\b\f\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner;", "Lokhttp3/internal/concurrent/Lockable;", "backend", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "logger", "Ljava/util/logging/Logger;", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner$Backend;Ljava/util/logging/Logger;)V", "getBackend", "()Lokhttp3/internal/concurrent/TaskRunner$Backend;", "getLogger$okhttp", "()Ljava/util/logging/Logger;", "logger$1", "nextQueueName", _UrlKt.FRAGMENT_ENCODE_SET, "coordinatorWaiting", _UrlKt.FRAGMENT_ENCODE_SET, "coordinatorWakeUpAt", _UrlKt.FRAGMENT_ENCODE_SET, "executeCallCount", "runCallCount", "busyQueues", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/concurrent/TaskQueue;", "readyQueues", "runnable", "Ljava/lang/Runnable;", "kickCoordinator", _UrlKt.FRAGMENT_ENCODE_SET, "taskQueue", "kickCoordinator$okhttp", "beforeRun", "task", "Lokhttp3/internal/concurrent/Task;", "afterRun", "delayNanos", "completedNormally", "awaitTaskToRun", "startAnotherThread", "newQueue", "activeQueues", _UrlKt.FRAGMENT_ENCODE_SET, "cancelAll", "Backend", "RealBackend", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,364:1\n55#2,4:365\n55#2,4:369\n55#2,4:373\n55#2,4:377\n55#2,4:381\n63#2:385\n63#2:386\n55#2,4:387\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner\n*L\n107#1:365,4\n125#1:369,4\n140#1:373,4\n171#1:377,4\n251#1:381,4\n259#1:385\n268#1:386\n274#1:387,4\n*E\n"})
/* loaded from: classes.dex */
public final class TaskRunner implements Lockable {
    public static final Companion Companion = null;

    @JvmField
    public static final TaskRunner INSTANCE = null;
    private static final Logger logger = null;
    private final Backend backend;
    private final List<TaskQueue> busyQueues;
    private boolean coordinatorWaiting;
    private long coordinatorWakeUpAt;
    private int executeCallCount;
    private final Logger logger$1;
    private int nextQueueName;
    private final List<TaskQueue> readyQueues;
    private int runCallCount;
    private final Runnable runnable;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003H&J\"\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u0004\b\u0000\u0010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000bH&J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Backend;", _UrlKt.FRAGMENT_ENCODE_SET, "nanoTime", _UrlKt.FRAGMENT_ENCODE_SET, "coordinatorNotify", _UrlKt.FRAGMENT_ENCODE_SET, "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorWait", "nanos", "decorate", "Ljava/util/concurrent/BlockingQueue;", "T", "queue", "execute", "runnable", "Ljava/lang/Runnable;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Backend {
        void coordinatorNotify(TaskRunner r1);

        void coordinatorWait(TaskRunner r1, long r2);

        <T> BlockingQueue<T> decorate(BlockingQueue<T> r1);

        void execute(TaskRunner r1, Runnable r2);

        long nanoTime();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "INSTANCE", "Lokhttp3/internal/concurrent/TaskRunner;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Logger getLogger() {
            return TaskRunner.access$getLogger$cp();
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013H\u0016J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0006\u0010\u0019\u001a\u00020\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "<init>", "(Ljava/util/concurrent/ThreadFactory;)V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "getExecutor", "()Ljava/util/concurrent/ThreadPoolExecutor;", "nanoTime", _UrlKt.FRAGMENT_ENCODE_SET, "coordinatorNotify", _UrlKt.FRAGMENT_ENCODE_SET, "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorWait", "nanos", "decorate", "Ljava/util/concurrent/BlockingQueue;", "T", "queue", "execute", "runnable", "Ljava/lang/Runnable;", "shutdown", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$RealBackend\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,364:1\n36#2:365\n55#2,4:366\n41#2,6:370\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$RealBackend\n*L\n324#1:365\n337#1:366,4\n339#1:370,6\n*E\n"})
    public static final class RealBackend implements Backend {
        private final ThreadPoolExecutor executor;

        public RealBackend(ThreadFactory r9) {
            SynchronousQueue r6 = new SynchronousQueue();
            this.executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, r6, r9);
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public void coordinatorNotify(TaskRunner r1) {
            r1.notify();
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public void coordinatorWait(TaskRunner r7, long r8) {
            if (_UtilJvmKt.assertionsEnabled == false) goto L10;
            if (Thread.holdsLock(r7) == true) goto L10;
            _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r7);
            return;
        L10:
            if (r8 <= 0) goto L16;
            long r4 = r8 / 1000000;
            long r82 = r8 - (1000000 * r4);
            if (r4 > 0) goto L14;
            if (r8 > 0) goto L14;
            return;
        L14:
            r7.wait(r4, (int) r82);
            return;
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public <T> BlockingQueue<T> decorate(BlockingQueue<T> r1) {
            return r1;
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public void execute(TaskRunner r1, Runnable r2) {
            this.executor.execute(r2);
        }

        public final ThreadPoolExecutor getExecutor() {
            return this.executor;
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public long nanoTime() {
            return System.nanoTime();
        }

        public final void shutdown() {
            this.executor.shutdown();
        }
    }

    static {
        Companion = new Companion(null);
        logger = Logger.getLogger(TaskRunner.class.getName());
        INSTANCE = new TaskRunner(new RealBackend(_UtilJvmKt.threadFactory(_UtilJvmKt.okHttpName + " TaskRunner", true)), null, 2, null);
    }

    public TaskRunner(Backend r1, Logger r2) {
        this.backend = r1;
        this.logger$1 = r2;
        this.nextQueueName = 10000;
        this.busyQueues = new ArrayList();
        this.readyQueues = new ArrayList();
        this.runnable = new TaskRunner$runnable$1(this);
    }

    public /* synthetic */ TaskRunner(Backend r1, Logger r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = logger;
    L5:
        this(r1, r2);
    }

    public static final /* synthetic */ void access$afterRun(TaskRunner r0, Task r1, long r2, boolean r4) {
        r0.afterRun(r1, r2, r4);
    }

    public static final /* synthetic */ Logger access$getLogger$cp() {
        return logger;
    }

    public static final /* synthetic */ int access$getRunCallCount$p(TaskRunner r0) {
        return r0.runCallCount;
    }

    public static final /* synthetic */ void access$setRunCallCount$p(TaskRunner r0, int r1) {
        r0.runCallCount = r1;
    }

    private final void afterRun(Task r5, long r6, boolean r8) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        TaskQueue r0 = r5.getQueue$okhttp();
        if (r0.getActiveTask$okhttp() != r5) goto L23;
        boolean r1 = r0.getCancelActiveTask$okhttp();
        r0.setCancelActiveTask$okhttp(false);
        r0.setActiveTask$okhttp(null);
        this.busyQueues.remove(r0);
        if (r6 == (-1)) goto L18;
        if (r1 == true) goto L18;
        if (r0.getShutdown$okhttp() == true) goto L18;
        r0.scheduleAndDecide$okhttp(r5, r6, true);
    L18:
        if (r0.getFutureTasks$okhttp().isEmpty() == true) goto L25;
        this.readyQueues.add(r0);
        if (r8 == true) goto L26;
        startAnotherThread();
        return;
    L26:
        return;
    L25:
        return;
    L23:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L5:
        if (Thread.holdsLock(this) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
    }

    private final void beforeRun(Task r3) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        r3.setNextExecuteNanoTime$okhttp(-1);
        TaskQueue r0 = r3.getQueue$okhttp();
        r0.getFutureTasks$okhttp().remove(r3);
        this.readyQueues.remove(r0);
        r0.setActiveTask$okhttp(r3);
        this.busyQueues.add(r0);
        return;
    L5:
        if (Thread.holdsLock(this) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
    }

    private final void startAnotherThread() {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        int r0 = this.executeCallCount;
        if (r0 <= this.runCallCount) goto L12;
        return;
    L12:
        this.executeCallCount = r0 + 1;
        this.backend.execute(this, this.runnable);
        return;
    L5:
        if (Thread.holdsLock(this) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
    }

    public final List<TaskQueue> activeQueues() {
        monitor-enter(this);
        List<TaskQueue> r0 = CollectionsKt.plus(this.busyQueues, this.readyQueues);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final Task awaitTaskToRun() {
        if (_UtilJvmKt.assertionsEnabled == false) goto L10;
        if (Thread.holdsLock(this) == true) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
        return null;
    L10:
        if (this.readyQueues.isEmpty() == true) goto L11;
        long r2 = this.backend.nanoTime();
        Iterator<TaskQueue> r0 = this.readyQueues.iterator();
        long r4 = LongCompanionObject.MAX_VALUE;
        Task r6 = null;
    L14:
        if (r0.hasNext() == false) goto L21;
        Task r7 = r0.next().getFutureTasks$okhttp().get(0);
        long r10 = Math.max(0, r7.getNextExecuteNanoTime$okhttp() - r2);
        if (r10 > 0) goto L17;
        if (r6 != null) goto L19;
        r6 = r7;
        goto L14
    L19:
        boolean r02 = true;
    L22:
        if (r6 != null) goto L23;
        if (this.coordinatorWaiting == true) goto L34;
        this.coordinatorWaiting = true;
        this.coordinatorWakeUpAt = r2 + r4;
        this.backend.coordinatorWait(this, r4);     // Catch: Throwable -> L40 InterruptedException -> L42
    L39:
        this.coordinatorWaiting = false;
    L40:
        th = move-exception;
        this.coordinatorWaiting = false;
        throw th;
    L42:
        cancelAll();     // Catch: Throwable -> L40
        goto L39
    L34:
        if (r4 >= (this.coordinatorWakeUpAt - r2)) goto L36;
        this.backend.coordinatorNotify(this);
    L36:
        return null;
    L23:
        beforeRun(r6);
        if (r02 == false) goto L26;
    L29:
        startAnotherThread();
    L30:
        return r6;
    L26:
        if (this.coordinatorWaiting == true) goto L30;
        if (this.readyQueues.isEmpty() == true) goto L30;
    L17:
        r4 = Math.min(r10, r4);
        goto L14
    L21:
        r02 = false;
        goto L22
    L11:
        return null;
    }

    public final void cancelAll() {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        int r0 = this.busyQueues.size();
    L10:
        r0 = r0 - 1;
        if ((-1) >= r0) goto L13;
        this.busyQueues.get(r0).cancelAllAndDecide$okhttp();
        goto L10
    L13:
        int r02 = this.readyQueues.size() - 1;
    L14:
        if ((-1) >= r02) goto L19;
        TaskQueue r2 = this.readyQueues.get(r02);
        r2.cancelAllAndDecide$okhttp();
        if (r2.getFutureTasks$okhttp().isEmpty() == false) goto L18;
        this.readyQueues.remove(r02);
    L18:
        r02 = r02 - 1;
        goto L14
    L19:
        return;
    L5:
        if (Thread.holdsLock(this) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
    }

    public final Backend getBackend() {
        return this.backend;
    }

    public final Logger getLogger$okhttp() {
        return this.logger$1;
    }

    public final void kickCoordinator$okhttp(TaskQueue r3) {
        if (_UtilJvmKt.assertionsEnabled == false) goto L10;
        if (Thread.holdsLock(this) == true) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
        return;
    L10:
        if (r3.getActiveTask$okhttp() != null) goto L16;
        boolean r0 = r3.getFutureTasks$okhttp().isEmpty();
        List<TaskQueue> r1 = this.readyQueues;
        if (r0 == true) goto L14;
        _UtilCommonKt.addIfAbsent(r1, r3);
        goto L16
    L14:
        r1.remove(r3);
    L16:
        if (this.coordinatorWaiting == false) goto L19;
        this.backend.coordinatorNotify(this);
        return;
    L19:
        startAnotherThread();
    }

    public final TaskQueue newQueue() {
        monitor-enter(this);
        int r0 = this.nextQueueName;     // Catch: Throwable -> L7
        this.nextQueueName = r0 + 1;     // Catch: Throwable -> L7
        monitor-exit(this);
        return new TaskQueue(this, "Q" + r0);
    L7:
        th = move-exception;
        throw th;
    }
}
