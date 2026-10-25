package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public final class ExecutionSequencer {
    private ThreadConfinedTaskQueue latestTaskQueue;
    private final AtomicReference<ListenableFuture<Void>> ref;

    /* renamed from: com.google.common.util.concurrent.ExecutionSequencer$1, reason: invalid class name */
    abstract class AnonymousClass1 implements AsyncCallable<Object> {
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.util.concurrent.ExecutionSequencer$2, reason: invalid class name */
    public class AnonymousClass2<T> implements AsyncCallable<T> {
        final /* synthetic */ ExecutionSequencer this$0;
        final /* synthetic */ AsyncCallable val$callable;
        final /* synthetic */ TaskNonReentrantExecutor val$taskExecutor;

        public AnonymousClass2(ExecutionSequencer r1, TaskNonReentrantExecutor r2, AsyncCallable r3) {
            this.val$taskExecutor = r2;
            this.val$callable = r3;
            this.this$0 = r1;
        }

        @Override // com.google.common.util.concurrent.AsyncCallable
        public ListenableFuture<T> call() {
            if (TaskNonReentrantExecutor.access$200(this.val$taskExecutor) == true) goto L7;
            return Futures.immediateCancelledFuture();
        L7:
            return this.val$callable.call();
        }

        public String toString() {
            return this.val$callable.toString();
        }
    }

    public enum RunningState extends Enum<RunningState> {
        private static final /* synthetic */ RunningState[] $VALUES = null;
        public static final RunningState NOT_RUN = null;
        public static final RunningState STARTED = null;
        public static final RunningState WILL_NOT_RUN = null;

        private static /* synthetic */ RunningState[] $values() {
            return new RunningState[]{NOT_RUN, WILL_NOT_RUN, STARTED};
        }

        static {
            NOT_RUN = new RunningState("NOT_RUN", 0);
            WILL_NOT_RUN = new RunningState("WILL_NOT_RUN", 1);
            STARTED = new RunningState("STARTED", 2);
            $VALUES = $values();
        }

        RunningState(String r1, int r2) {
        }

        public static RunningState valueOf(String r1) {
            return (RunningState) Enum.valueOf(RunningState.class, r1);
        }

        public static RunningState[] values() {
            return (RunningState[]) $VALUES.clone();
        }
    }

    public static final class TaskNonReentrantExecutor extends AtomicReference<RunningState> implements Executor, Runnable {
        Executor delegate;
        ExecutionSequencer sequencer;
        Thread submitting;
        TrustedListenableFutureTask<?> task;

        private TaskNonReentrantExecutor(Executor r2, ExecutionSequencer r3) {
            super(RunningState.NOT_RUN);
            this.delegate = r2;
            this.sequencer = r3;
        }

        public /* synthetic */ TaskNonReentrantExecutor(Executor r1, ExecutionSequencer r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public static /* synthetic */ boolean access$200(TaskNonReentrantExecutor r0) {
            return r0.trySetStarted();
        }

        public static /* synthetic */ boolean access$300(TaskNonReentrantExecutor r0) {
            return r0.trySetCancelled();
        }

        private boolean trySetCancelled() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.WILL_NOT_RUN);
        }

        private boolean trySetStarted() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.STARTED);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r5) {
            if (get() != RunningState.WILL_NOT_RUN) goto L6;
            this.delegate = null;
            this.sequencer = null;
            return;
        L6:
            this.submitting = Thread.currentThread();
            TrustedListenableFutureTask<?> r52 = (TrustedListenableFutureTask) r5;
            ExecutionSequencer r0 = this.sequencer;     // Catch: Throwable -> L14
            Objects.requireNonNull(r0);     // Catch: Throwable -> L14
            ThreadConfinedTaskQueue r02 = ExecutionSequencer.access$400(r0);     // Catch: Throwable -> L14
            if (r02.thread != this.submitting) goto L16;
            this.sequencer = null;     // Catch: Throwable -> L14
            if (r02.nextTask != null) goto L12;
            boolean r1 = true;
        L13:
            Preconditions.checkState(r1);     // Catch: Throwable -> L14
            r02.nextTask = r52;     // Catch: Throwable -> L14
            Executor r53 = this.delegate;     // Catch: Throwable -> L14
            Objects.requireNonNull(r53);     // Catch: Throwable -> L14
            r02.nextExecutor = r53;     // Catch: Throwable -> L14
            this.delegate = null;     // Catch: Throwable -> L14
        L21:
            this.submitting = null;
            return;
        L12:
            r1 = false;
            goto L13
        L16:
            Executor r03 = this.delegate;     // Catch: Throwable -> L14
            Objects.requireNonNull(r03);     // Catch: Throwable -> L14
            Executor r04 = r03;     // Catch: Throwable -> L14
            this.delegate = null;     // Catch: Throwable -> L14
            this.task = r52;     // Catch: Throwable -> L14
            r04.execute(this);     // Catch: Throwable -> L14 RejectedExecutionException -> L19
        L19:
            e = move-exception;
            trySetCancelled();     // Catch: Throwable -> L14
            r52.setExceptionInternal(e);     // Catch: Throwable -> L14
        L14:
            th = move-exception;
            this.submitting = null;
            throw th;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread r0 = Thread.currentThread();
            if (r0 == this.submitting) goto L6;
            TrustedListenableFutureTask<?> r02 = this.task;
            Objects.requireNonNull(r02);
            this.task = null;
            r02.run();
            return;
        L6:
            ThreadConfinedTaskQueue r1 = new ThreadConfinedTaskQueue(null);
            r1.thread = r0;
            ExecutionSequencer r03 = this.sequencer;
            Objects.requireNonNull(r03);
            ExecutionSequencer.access$402(r03, r1);
            this.sequencer = null;
            TrustedListenableFutureTask<?> r04 = this.task;     // Catch: Throwable -> L15
            Objects.requireNonNull(r04);     // Catch: Throwable -> L15
            this.task = null;     // Catch: Throwable -> L15
            r04.run();     // Catch: Throwable -> L15
        L8:
            TrustedListenableFutureTask<?> r3 = r1.nextTask;     // Catch: Throwable -> L15
            if (r3 == null) goto L20;
            Executor r05 = r1.nextExecutor;     // Catch: Throwable -> L15
            if (r05 == null) goto L20;
            r1.nextTask = null;     // Catch: Throwable -> L15
            r1.nextExecutor = null;     // Catch: Throwable -> L15
            r05.execute(r3);     // Catch: Throwable -> L15 RejectedExecutionException -> L17
        L17:
            e = move-exception;
            r3.setExceptionInternal(e);     // Catch: Throwable -> L15
        L20:
            r1.thread = null;
            return;
        L15:
            th = move-exception;
            r1.thread = null;
            throw th;
        }
    }

    public static final class ThreadConfinedTaskQueue {
        Executor nextExecutor;
        TrustedListenableFutureTask<?> nextTask;
        Thread thread;

        private ThreadConfinedTaskQueue() {
        }

        public /* synthetic */ ThreadConfinedTaskQueue(AnonymousClass1 r1) {
            this();
        }
    }

    public static /* synthetic */ void $r8$lambda$G1fYN83GH2L7tHBoC2wSsN1KFkk(TrustedListenableFutureTask r1, SettableFuture r2, ListenableFuture r3, ListenableFuture r4, TaskNonReentrantExecutor r5) {
        if (r1.isDone() == false) goto L7;
        r2.setFutureInternal(r3);
        return;
    L7:
        if (r4.isCancelled() == true) goto L9;
        return;
    L9:
        if (TaskNonReentrantExecutor.access$300(r5) == false) goto L13;
        r1.cancel(false);
        return;
    }

    private ExecutionSequencer() {
        this.ref = new AtomicReference(Futures.immediateVoidFuture());
        this.latestTaskQueue = new ThreadConfinedTaskQueue(null);
    }

    public static /* synthetic */ ThreadConfinedTaskQueue access$400(ExecutionSequencer r0) {
        return r0.latestTaskQueue;
    }

    public static /* synthetic */ ThreadConfinedTaskQueue access$402(ExecutionSequencer r0, ThreadConfinedTaskQueue r1) {
        r0.latestTaskQueue = r1;
        return r1;
    }

    public static ExecutionSequencer create() {
        return new ExecutionSequencer();
    }

    public <T> ListenableFuture<T> submitAsync(AsyncCallable<T> r7, Executor r8) {
        Preconditions.checkNotNull(r7);
        Preconditions.checkNotNull(r8);
        TaskNonReentrantExecutor r5 = new TaskNonReentrantExecutor(r8, this, null);
        AnonymousClass2 r82 = new AnonymousClass2(this, r5, r7);
        SettableFuture r2 = SettableFuture.create();
        ListenableFuture<Void> r3 = this.ref.getAndSet(r2);
        TrustedListenableFutureTask r1 = TrustedListenableFutureTask.create(r82);
        r3.addListener(r1, r5);
        ListenableFuture<T> r4 = Futures.nonCancellationPropagating(r1);
        ExecutionSequencer$$ExternalSyntheticLambda0 r0 = new ExecutionSequencer$$ExternalSyntheticLambda0(r1, r2, r3, r4, r5);
        r4.addListener(r0, MoreExecutors.directExecutor());
        r1.addListener(r0, MoreExecutors.directExecutor());
        return r4;
    }
}
