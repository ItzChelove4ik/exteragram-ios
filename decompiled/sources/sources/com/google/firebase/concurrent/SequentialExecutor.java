package com.google.firebase.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
final class SequentialExecutor implements Executor {
    private static final Logger log = null;
    private final Executor executor;
    private final Deque<Runnable> queue;
    private final QueueWorker worker;
    private long workerRunCount;
    private WorkerRunningState workerRunningState;

    /* renamed from: com.google.firebase.concurrent.SequentialExecutor$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ SequentialExecutor this$0;
        final /* synthetic */ Runnable val$task;

        public AnonymousClass1(SequentialExecutor r1, Runnable r2) {
            this.this$0 = r1;
            this.val$task = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$task.run();
        }

        public String toString() {
            return this.val$task.toString();
        }
    }

    public final class QueueWorker implements Runnable {
        Runnable task;
        final /* synthetic */ SequentialExecutor this$0;

        private QueueWorker(SequentialExecutor r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ QueueWorker(SequentialExecutor r1, AnonymousClass1 r2) {
            this(r1);
        }

        private void workOnQueue() {
            boolean r0 = false;
            boolean r1 = false;
        L42:
            Deque r2 = SequentialExecutor.access$100(this.this$0);     // Catch: Throwable -> L27
            monitor-enter(r2);     // Catch: Throwable -> L27
            if (r0 == false) goto L41;
        L14:
            Runnable r3 = (Runnable) SequentialExecutor.access$100(this.this$0).poll();     // Catch: Throwable -> L11
            this.task = r3;     // Catch: Throwable -> L11
            if (r3 == null) goto L16;
            monitor-exit(r2);     // Catch: Throwable -> L11
            r1 = r1 | Thread.interrupted();
            this.task.run();     // Catch: Throwable -> L29 RuntimeException -> L31
        L25:
            this.task = null;     // Catch: Throwable -> L27
        L29:
            th = move-exception;
            this.task = null;     // Catch: Throwable -> L27
            throw th;     // Catch: Throwable -> L27
        L31:
            e = move-exception;
            SequentialExecutor.access$400().log(Level.SEVERE, "Exception while executing runnable " + this.task, e);     // Catch: Throwable -> L29
            goto L25
        L16:
            SequentialExecutor.access$202(this.this$0, WorkerRunningState.IDLE);     // Catch: Throwable -> L11
            monitor-exit(r2);     // Catch: Throwable -> L11
            if (r1 == true) goto L10;
            return;
        L10:
            Thread.currentThread().interrupt();
            return;
        L41:
            WorkerRunningState r02 = SequentialExecutor.access$200(this.this$0);     // Catch: Throwable -> L11
            WorkerRunningState r32 = WorkerRunningState.RUNNING;     // Catch: Throwable -> L11
            if (r02 == r32) goto L8;
            SequentialExecutor.access$308(this.this$0);     // Catch: Throwable -> L11
            SequentialExecutor.access$202(this.this$0, r32);     // Catch: Throwable -> L11
            r0 = true;
            goto L14
        L8:
            monitor-exit(r2);     // Catch: Throwable -> L11
            if (r1 == true) goto L10;
            return;
        L11:
            th = move-exception;
            throw th;     // Catch: Throwable -> L27
        L27:
            th = move-exception;
            if (r1 == false) goto L40;
            Thread.currentThread().interrupt();
        L40:
            throw th;
        }

        @Override // java.lang.Runnable
        public void run() {
            workOnQueue();     // Catch: Error -> L4
            return;
        L4:
            e = move-exception;
            monitor-enter(SequentialExecutor.access$100(this.this$0));
            SequentialExecutor.access$202(this.this$0, WorkerRunningState.IDLE);     // Catch: Throwable -> L10
            throw e;
        L10:
            th = move-exception;
            throw th;
        }

        public String toString() {
            Runnable r0 = this.task;
            if (r0 == null) goto L7;
            return "SequentialExecutorWorker{running=" + r0 + "}";
        L7:
            return "SequentialExecutorWorker{state=" + SequentialExecutor.access$200(this.this$0) + "}";
        }
    }

    public enum WorkerRunningState extends Enum<WorkerRunningState> {
        private static final /* synthetic */ WorkerRunningState[] $VALUES = null;
        public static final WorkerRunningState IDLE = null;
        public static final WorkerRunningState QUEUED = null;
        public static final WorkerRunningState QUEUING = null;
        public static final WorkerRunningState RUNNING = null;

        private static /* synthetic */ WorkerRunningState[] $values() {
            return new WorkerRunningState[]{IDLE, QUEUING, QUEUED, RUNNING};
        }

        static {
            IDLE = new WorkerRunningState("IDLE", 0);
            QUEUING = new WorkerRunningState("QUEUING", 1);
            QUEUED = new WorkerRunningState("QUEUED", 2);
            RUNNING = new WorkerRunningState("RUNNING", 3);
            $VALUES = $values();
        }

        WorkerRunningState(String r1, int r2) {
        }

        public static WorkerRunningState valueOf(String r1) {
            return (WorkerRunningState) Enum.valueOf(WorkerRunningState.class, r1);
        }

        public static WorkerRunningState[] values() {
            return (WorkerRunningState[]) $VALUES.clone();
        }
    }

    static {
        log = Logger.getLogger(SequentialExecutor.class.getName());
    }

    public SequentialExecutor(Executor r3) {
        this.queue = new ArrayDeque();
        this.workerRunningState = WorkerRunningState.IDLE;
        this.workerRunCount = 0;
        this.worker = new QueueWorker(this, null);
        this.executor = (Executor) Preconditions.checkNotNull(r3);
    }

    public static /* synthetic */ Deque access$100(SequentialExecutor r0) {
        return r0.queue;
    }

    public static /* synthetic */ WorkerRunningState access$200(SequentialExecutor r0) {
        return r0.workerRunningState;
    }

    public static /* synthetic */ WorkerRunningState access$202(SequentialExecutor r0, WorkerRunningState r1) {
        r0.workerRunningState = r1;
        return r1;
    }

    public static /* synthetic */ long access$308(SequentialExecutor r4) {
        long r0 = r4.workerRunCount;
        r4.workerRunCount = 1 + r0;
        return r0;
    }

    public static /* synthetic */ Logger access$400() {
        return log;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r8) {
        Preconditions.checkNotNull(r8);
        Deque<Runnable> r0 = this.queue;
        monitor-enter(r0);
        WorkerRunningState r1 = this.workerRunningState;     // Catch: Throwable -> L52
        if (r1 == WorkerRunningState.RUNNING) goto L54;
        WorkerRunningState r2 = WorkerRunningState.QUEUED;     // Catch: Throwable -> L52
        if (r1 == r2) goto L54;
        long r3 = this.workerRunCount;     // Catch: Throwable -> L52
        AnonymousClass1 r12 = new AnonymousClass1(this, r8);     // Catch: Throwable -> L52
        this.queue.add(r12);     // Catch: Throwable -> L52
        WorkerRunningState r82 = WorkerRunningState.QUEUING;     // Catch: Throwable -> L52
        this.workerRunningState = r82;     // Catch: Throwable -> L52
        monitor-exit(r0);     // Catch: Throwable -> L52
        this.executor.execute(this.worker);     // Catch: Error -> L28 Throwable -> L30
        if (this.workerRunningState == r82) goto L15;
        return;
    L15:
        Deque<Runnable> r02 = this.queue;
        monitor-enter(r02);
    L22:
        th = move-exception;
        throw th;
    L18:
        if (this.workerRunCount == r3) goto L20;
    L24:
        monitor-exit(r02);     // Catch: Throwable -> L22
        return;
    L20:
        if (this.workerRunningState != r82) goto L24;
        this.workerRunningState = r2;     // Catch: Throwable -> L22
    L30:
        e = move-exception;
        Deque<Runnable> r22 = this.queue;
        monitor-enter(r22);
        WorkerRunningState r03 = this.workerRunningState;     // Catch: Throwable -> L38
        if (r03 == WorkerRunningState.IDLE) goto L41;
        if (r03 == WorkerRunningState.QUEUING) goto L41;
    L43:
        boolean r7 = false;
    L45:
        if ((e instanceof RejectedExecutionException) == false) goto L49;
        if (r7 == true) goto L49;
        monitor-exit(r22);     // Catch: Throwable -> L38
        return;
    L49:
        throw e;     // Catch: Throwable -> L38
    L41:
        if (this.queue.removeLastOccurrence(r12) == false) goto L43;
        r7 = true;
    L38:
        th = move-exception;
        throw th;
    L54:
        this.queue.add(r8);     // Catch: Throwable -> L52
        monitor-exit(r0);     // Catch: Throwable -> L52
        return;
    L52:
        th = move-exception;
        throw th;
    }

    public String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.executor + "}";
    }
}
