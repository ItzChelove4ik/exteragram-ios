package androidx.camera.core.impl.utils.executor;

import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes3.dex */
final class SequentialExecutor implements Executor {
    private final Executor mExecutor;
    final Deque<Runnable> mQueue;
    private final QueueWorker mWorker;
    long mWorkerRunCount;
    WorkerRunningState mWorkerRunningState;

    /* renamed from: androidx.camera.core.impl.utils.executor.SequentialExecutor$1, reason: invalid class name */
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
    }

    public final class QueueWorker implements Runnable {
        final /* synthetic */ SequentialExecutor this$0;

        public QueueWorker(SequentialExecutor r1) {
            this.this$0 = r1;
        }

        private void workOnQueue() {
            boolean r0 = false;
            boolean r1 = false;
        L36:
            Deque<Runnable> r2 = this.this$0.mQueue;     // Catch: Throwable -> L26
            monitor-enter(r2);     // Catch: Throwable -> L26
            if (r0 == false) goto L37;
        L14:
            Runnable r3 = this.this$0.mQueue.poll();     // Catch: Throwable -> L11
            if (r3 == null) goto L16;
            monitor-exit(r2);     // Catch: Throwable -> L11
            r1 = r1 | Thread.interrupted();
            r3.run();     // Catch: Throwable -> L26 RuntimeException -> L28
        L28:
            e = move-exception;
            Logger.e("SequentialExecutor", "Exception while executing runnable " + r3, e);     // Catch: Throwable -> L26
            goto L36
        L16:
            this.this$0.mWorkerRunningState = WorkerRunningState.IDLE;     // Catch: Throwable -> L11
            monitor-exit(r2);     // Catch: Throwable -> L11
            if (r1 == true) goto L10;
            return;
        L10:
            Thread.currentThread().interrupt();
            return;
        L37:
            SequentialExecutor r02 = this.this$0;     // Catch: Throwable -> L11
            WorkerRunningState r32 = r02.mWorkerRunningState;     // Catch: Throwable -> L11
            WorkerRunningState r4 = WorkerRunningState.RUNNING;     // Catch: Throwable -> L11
            if (r32 == r4) goto L8;
            r02.mWorkerRunCount++;
            r02.mWorkerRunningState = r4;     // Catch: Throwable -> L11
            r0 = true;
            goto L14
        L8:
            monitor-exit(r2);     // Catch: Throwable -> L11
            if (r1 == true) goto L10;
            return;
        L11:
            th = move-exception;
            throw th;     // Catch: Throwable -> L26
        L26:
            th = move-exception;
            if (r1 == false) goto L35;
            Thread.currentThread().interrupt();
        L35:
            throw th;
        }

        @Override // java.lang.Runnable
        public void run() {
            workOnQueue();     // Catch: Error -> L4
            return;
        L4:
            e = move-exception;
            monitor-enter(this.this$0.mQueue);
            SequentialExecutor r3 = this.this$0;     // Catch: Throwable -> L10
            r3.mWorkerRunningState = WorkerRunningState.IDLE;     // Catch: Throwable -> L10
            throw e;
        L10:
            th = move-exception;
            throw th;
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

    public SequentialExecutor(Executor r3) {
        this.mQueue = new ArrayDeque();
        this.mWorker = new QueueWorker(this);
        this.mWorkerRunningState = WorkerRunningState.IDLE;
        this.mWorkerRunCount = 0;
        this.mExecutor = (Executor) Preconditions.checkNotNull(r3);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r8) {
        Preconditions.checkNotNull(r8);
        Deque<Runnable> r0 = this.mQueue;
        monitor-enter(r0);
        WorkerRunningState r1 = this.mWorkerRunningState;     // Catch: Throwable -> L52
        if (r1 == WorkerRunningState.RUNNING) goto L54;
        WorkerRunningState r2 = WorkerRunningState.QUEUED;     // Catch: Throwable -> L52
        if (r1 == r2) goto L54;
        long r3 = this.mWorkerRunCount;     // Catch: Throwable -> L52
        AnonymousClass1 r12 = new AnonymousClass1(this, r8);     // Catch: Throwable -> L52
        this.mQueue.add(r12);     // Catch: Throwable -> L52
        WorkerRunningState r82 = WorkerRunningState.QUEUING;     // Catch: Throwable -> L52
        this.mWorkerRunningState = r82;     // Catch: Throwable -> L52
        monitor-exit(r0);     // Catch: Throwable -> L52
        this.mExecutor.execute(this.mWorker);     // Catch: Error -> L28 Throwable -> L30
        if (this.mWorkerRunningState == r82) goto L15;
        return;
    L15:
        Deque<Runnable> r02 = this.mQueue;
        monitor-enter(r02);
    L22:
        th = move-exception;
        throw th;
    L18:
        if (this.mWorkerRunCount == r3) goto L20;
    L24:
        monitor-exit(r02);     // Catch: Throwable -> L22
        return;
    L20:
        if (this.mWorkerRunningState != r82) goto L24;
        this.mWorkerRunningState = r2;     // Catch: Throwable -> L22
    L30:
        e = move-exception;
        Deque<Runnable> r22 = this.mQueue;
        monitor-enter(r22);
        WorkerRunningState r03 = this.mWorkerRunningState;     // Catch: Throwable -> L38
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
        if (this.mQueue.removeLastOccurrence(r12) == false) goto L43;
        r7 = true;
    L38:
        th = move-exception;
        throw th;
    L54:
        this.mQueue.add(r8);     // Catch: Throwable -> L52
        monitor-exit(r0);     // Catch: Throwable -> L52
        return;
    L52:
        th = move-exception;
        throw th;
    }
}
