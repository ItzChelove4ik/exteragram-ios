package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u00014B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00132\b\b\u0002\u0010%\u001a\u00020&J&\u0010\"\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020&0(J0\u0010)\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010*\u001a\u00020\r2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020#0(J\u0006\u0010+\u001a\u00020,J%\u0010-\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&2\u0006\u0010.\u001a\u00020\rH\u0000¢\u0006\u0002\b/J\u0006\u00100\u001a\u00020#J\u0006\u0010\f\u001a\u00020#J\r\u00101\u001a\u00020\rH\u0000¢\u0006\u0002\b2J\b\u00103\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00130 8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001b¨\u00065"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue;", _UrlKt.FRAGMENT_ENCODE_SET, "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Ljava/lang/String;)V", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "getName$okhttp", "()Ljava/lang/String;", "shutdown", _UrlKt.FRAGMENT_ENCODE_SET, "getShutdown$okhttp", "()Z", "setShutdown$okhttp", "(Z)V", "activeTask", "Lokhttp3/internal/concurrent/Task;", "getActiveTask$okhttp", "()Lokhttp3/internal/concurrent/Task;", "setActiveTask$okhttp", "(Lokhttp3/internal/concurrent/Task;)V", "futureTasks", _UrlKt.FRAGMENT_ENCODE_SET, "getFutureTasks$okhttp", "()Ljava/util/List;", "cancelActiveTask", "getCancelActiveTask$okhttp", "setCancelActiveTask$okhttp", "scheduledTasks", _UrlKt.FRAGMENT_ENCODE_SET, "getScheduledTasks", "schedule", _UrlKt.FRAGMENT_ENCODE_SET, "task", "delayNanos", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Lkotlin/Function0;", "execute", "cancelable", "idleLatch", "Ljava/util/concurrent/CountDownLatch;", "scheduleAndDecide", "recurrence", "scheduleAndDecide$okhttp", "cancelAll", "cancelAllAndDecide", "cancelAllAndDecide$okhttp", "toString", "AwaitIdleTask", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 3 TaskLogger.kt\nokhttp3/internal/concurrent/TaskLoggerKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,241:1\n63#2:242\n63#2:243\n63#2:252\n49#2,4:268\n63#2:272\n49#2,4:273\n63#2:277\n26#3,4:244\n26#3,4:248\n26#3,4:253\n26#3,4:257\n26#3,4:278\n360#4,7:261\n*S KotlinDebug\n*F\n+ 1 TaskQueue.kt\nokhttp3/internal/concurrent/TaskQueue\n*L\n48#1:242\n64#1:243\n122#1:252\n202#1:268,4\n204#1:272\n212#1:273,4\n214#1:277\n67#1:244,4\n70#1:248,4\n173#1:253,4\n179#1:257,4\n231#1:278,4\n188#1:261,7\n*E\n"})
/* loaded from: classes.dex */
public final class TaskQueue {
    private Task activeTask;
    private boolean cancelActiveTask;
    private final List<Task> futureTasks;
    private final String name;
    private boolean shutdown;
    private final TaskRunner taskRunner;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue$AwaitIdleTask;", "Lokhttp3/internal/concurrent/Task;", "<init>", "()V", "latch", "Ljava/util/concurrent/CountDownLatch;", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "runOnce", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class AwaitIdleTask extends Task {
        private final CountDownLatch latch;

        public AwaitIdleTask() {
            super(_UtilJvmKt.okHttpName + " awaitIdle", false);
            this.latch = new CountDownLatch(1);
        }

        public final CountDownLatch getLatch() {
            return this.latch;
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            this.latch.countDown();
            return -1;
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"okhttp3/internal/concurrent/TaskQueue$execute$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.concurrent.TaskQueue$execute$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Task {
        final /* synthetic */ Function0<Unit> $block;

        public AnonymousClass1(String r1, boolean r2, Function0<Unit> r3) {
            this.$block = r3;
            super(r1, r2);
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            this.$block.invoke();
            return -1;
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"okhttp3/internal/concurrent/TaskQueue$schedule$2", "Lokhttp3/internal/concurrent/Task;", "runOnce", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.concurrent.TaskQueue$schedule$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2 extends Task {
        final /* synthetic */ Function0<Long> $block;

        public AnonymousClass2(String r3, Function0<Long> r4) {
            this.$block = r4;
            super(r3, false, 2, null);
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            return this.$block.invoke().longValue();
        }
    }

    public TaskQueue(TaskRunner r1, String r2) {
        this.taskRunner = r1;
        this.name = r2;
        this.futureTasks = new ArrayList();
    }

    public static /* synthetic */ void execute$default(TaskQueue r6, String r7, long r8, boolean r10, Function0 r11, int r12, Object r13) {
        if ((r12 & 2) == 0) goto L5;
        r8 = 0;
    L5:
        long r2 = r8;
        if ((r12 & 4) == 0) goto L8;
        r10 = true;
    L8:
        r6.execute(r7, r2, r10, r11);
    }

    public static /* synthetic */ void schedule$default(TaskQueue r0, String r1, long r2, Function0 r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L5;
        r2 = 0;
    L5:
        r0.schedule(r1, r2, r4);
    }

    public static /* synthetic */ void schedule$default(TaskQueue r0, Task r1, long r2, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L5;
        r2 = 0;
    L5:
        r0.schedule(r1, r2);
    }

    public final void cancelAll() {
        TaskRunner r0 = this.taskRunner;
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        TaskRunner r02 = this.taskRunner;
        monitor-enter(r02);
    L14:
        th = move-exception;
        throw th;
    L12:
        if (cancelAllAndDecide$okhttp() == false) goto L16;
        this.taskRunner.kickCoordinator$okhttp(this);     // Catch: Throwable -> L14
    L16:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(r02);
        return;
    L5:
        if (Thread.holdsLock(r0) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r0);
    }

    public final boolean cancelAllAndDecide$okhttp() {
        Task r0 = this.activeTask;
        if (r0 != null) goto L5;
    L7:
        int r02 = this.futureTasks.size() - 1;
        boolean r2 = false;
    L9:
        if ((-1) >= r02) goto L17;
        if (this.futureTasks.get(r02).getCancelable() == false) goto L16;
        Logger r22 = this.taskRunner.getLogger$okhttp();
        Task r3 = this.futureTasks.get(r02);
        if (r22.isLoggable(Level.FINE) == false) goto L15;
        TaskLoggerKt.access$log(r22, r3, this, "canceled");
    L15:
        this.futureTasks.remove(r02);
        r2 = true;
    L16:
        r02 = r02 - 1;
        goto L9
    L17:
        return r2;
    L5:
        if (r0.getCancelable() == false) goto L7;
        this.cancelActiveTask = true;
        goto L7
    }

    public final void execute(String r2, long r3, boolean r5, Function0<Unit> r6) {
        schedule(new AnonymousClass1(r2, r5, r6), r3);
    }

    public final Task getActiveTask$okhttp() {
        return this.activeTask;
    }

    public final boolean getCancelActiveTask$okhttp() {
        return this.cancelActiveTask;
    }

    public final List<Task> getFutureTasks$okhttp() {
        return this.futureTasks;
    }

    public final String getName$okhttp() {
        return this.name;
    }

    public final List<Task> getScheduledTasks() {
        TaskRunner r0 = this.taskRunner;
        monitor-enter(r0);
        List<Task> r1 = CollectionsKt.toList(this.futureTasks);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public final boolean getShutdown$okhttp() {
        return this.shutdown;
    }

    public final TaskRunner getTaskRunner$okhttp() {
        return this.taskRunner;
    }

    public final CountDownLatch idleLatch() {
        TaskRunner r0 = this.taskRunner;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.activeTask == null) goto L7;
    L13:
        Task r1 = this.activeTask;     // Catch: Throwable -> L11
        if ((r1 instanceof AwaitIdleTask) == false) goto L18;
        CountDownLatch r5 = ((AwaitIdleTask) r1).getLatch();     // Catch: Throwable -> L11
        monitor-exit(r0);
        return r5;
    L18:
        Iterator<Task> r12 = this.futureTasks.iterator();     // Catch: Throwable -> L11
    L20:
        if (r12.hasNext() == false) goto L26;
        Task r3 = r12.next();     // Catch: Throwable -> L11
        if ((r3 instanceof AwaitIdleTask) == false) goto L20;
        CountDownLatch r52 = ((AwaitIdleTask) r3).getLatch();     // Catch: Throwable -> L11
        monitor-exit(r0);
        return r52;
    L26:
        AwaitIdleTask r13 = new AwaitIdleTask();     // Catch: Throwable -> L11
        if (scheduleAndDecide$okhttp(r13, 0, false) == false) goto L29;
        this.taskRunner.kickCoordinator$okhttp(this);     // Catch: Throwable -> L11
    L29:
        CountDownLatch r53 = r13.getLatch();     // Catch: Throwable -> L11
        monitor-exit(r0);
        return r53;
    L7:
        if (this.futureTasks.isEmpty() == false) goto L13;
        CountDownLatch r54 = new CountDownLatch(0);     // Catch: Throwable -> L11
        monitor-exit(r0);
        return r54;
    }

    public final void schedule(String r2, long r3, Function0<Long> r5) {
        schedule(new AnonymousClass2(r2, r5), r3);
    }

    public final void schedule(Task r3, long r4) {
        TaskRunner r0 = this.taskRunner;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.shutdown == false) goto L23;
        boolean r42 = r3.getCancelable();     // Catch: Throwable -> L13
        TaskRunner r5 = this.taskRunner;
        if (r42 == false) goto L17;
        Logger r43 = r5.getLogger$okhttp();     // Catch: Throwable -> L13
        if (r43.isLoggable(Level.FINE) == false) goto L15;
        TaskLoggerKt.access$log(r43, r3, this, "schedule canceled (queue is shutdown)");     // Catch: Throwable -> L13
    L15:
        monitor-exit(r0);
        return;
    L17:
        Logger r44 = r5.getLogger$okhttp();     // Catch: Throwable -> L13
        if (r44.isLoggable(Level.FINE) == false) goto L21;
        TaskLoggerKt.access$log(r44, r3, this, "schedule failed (queue is shutdown)");     // Catch: Throwable -> L13
    L21:
        throw new RejectedExecutionException();     // Catch: Throwable -> L13
    L23:
        if (scheduleAndDecide$okhttp(r3, r4, false) == false) goto L25;
        this.taskRunner.kickCoordinator$okhttp(this);     // Catch: Throwable -> L13
    L25:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r0);
    }

    public final boolean scheduleAndDecide$okhttp(Task r10, long r11, boolean r13) {
        r10.initQueue$okhttp(this);
        long r0 = this.taskRunner.getBackend().nanoTime();
        long r2 = r0 + r11;
        int r4 = this.futureTasks.indexOf(r10);
        if (r4 != (-1)) goto L5;
    L11:
        r10.setNextExecuteNanoTime$okhttp(r2);
        Logger r42 = this.taskRunner.getLogger$okhttp();
        if (r42.isLoggable(Level.FINE) == false) goto L17;
        if (r13 == false) goto L15;
        String r132 = "run again after " + TaskLoggerKt.formatDuration(r2 - r0);
    L16:
        TaskLoggerKt.access$log(r42, r10, this, r132);
        goto L17
    L15:
        r132 = "scheduled after " + TaskLoggerKt.formatDuration(r2 - r0);
    L17:
        Iterator<Task> r133 = this.futureTasks.iterator();
        int r22 = 0;
    L19:
        if (r133.hasNext() == false) goto L24;
        if ((r133.next().getNextExecuteNanoTime$okhttp() - r0) > r11) goto L25;
        r22 = r22 + 1;
    L25:
        if (r22 != (-1)) goto L27;
        r22 = this.futureTasks.size();
    L27:
        this.futureTasks.add(r22, r10);
        if (r22 != 0) goto L31;
        return true;
    L31:
        return false;
    L24:
        r22 = -1;
        goto L25
    L5:
        if (r10.getNextExecuteNanoTime$okhttp() > r2) goto L10;
        Logger r112 = this.taskRunner.getLogger$okhttp();
        if (r112.isLoggable(Level.FINE) == false) goto L9;
        TaskLoggerKt.access$log(r112, r10, this, "already scheduled");
    L9:
        return false;
    L10:
        this.futureTasks.remove(r4);
        goto L11
    }

    public final void setActiveTask$okhttp(Task r1) {
        this.activeTask = r1;
    }

    public final void setCancelActiveTask$okhttp(boolean r1) {
        this.cancelActiveTask = r1;
    }

    public final void setShutdown$okhttp(boolean r1) {
        this.shutdown = r1;
    }

    public final void shutdown() {
        TaskRunner r0 = this.taskRunner;
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        TaskRunner r02 = this.taskRunner;
        monitor-enter(r02);
        this.shutdown = true;     // Catch: Throwable -> L15
        if (cancelAllAndDecide$okhttp() == false) goto L17;
        this.taskRunner.kickCoordinator$okhttp(this);     // Catch: Throwable -> L15
    L17:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r02);
        return;
    L15:
        th = move-exception;
        throw th;
    L5:
        if (Thread.holdsLock(r0) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r0);
    }

    public String toString() {
        return this.name;
    }
}
