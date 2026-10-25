package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

/* loaded from: classes5.dex */
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE = null;
    private static final Runnable PARKED = null;

    /* renamed from: com.google.common.util.concurrent.InterruptibleTask$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Blocker extends AbstractOwnableSynchronizer implements Runnable {
        private final InterruptibleTask<?> task;

        private Blocker(InterruptibleTask<?> r1) {
            this.task = r1;
        }

        public /* synthetic */ Blocker(InterruptibleTask r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ void access$200(Blocker r0, Thread r1) {
            r0.setOwner(r1);
        }

        private void setOwner(Thread r1) {
            super.setExclusiveOwnerThread(r1);
        }

        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return this.task.toString();
        }
    }

    public static final class DoNothingRunnable implements Runnable {
        private DoNothingRunnable() {
        }

        public /* synthetic */ DoNothingRunnable(AnonymousClass1 r1) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    static {
        DONE = new DoNothingRunnable(null);
        PARKED = new DoNothingRunnable(null);
    }

    public InterruptibleTask() {
    }

    private void waitForInterrupt(Thread r8) {
        Runnable r0 = get();
        Blocker r2 = null;
        boolean r3 = false;
        int r4 = 0;
    L3:
        boolean r5 = r0 instanceof Blocker;
        if (r5 == false) goto L6;
    L11:
        if (r5 == false) goto L13;
        r2 = (Blocker) r0;
    L13:
        r4 = r4 + 1;
        if (r4 <= 1000) goto L26;
        Runnable r6 = PARKED;
        if (r0 == r6) goto L20;
        if (compareAndSet(r0, r6) == true) goto L20;
    L27:
        r0 = get();
    L20:
        if (Thread.interrupted() == true) goto L24;
        if (r3 == true) goto L24;
        r3 = false;
    L25:
        LockSupport.park(r2);
    L24:
        r3 = true;
        goto L25
    L26:
        Thread.yield();
        goto L27
    L6:
        if (r0 == PARKED) goto L11;
        if (r3 == false) goto L32;
        r8.interrupt();
        return;
    }

    public abstract void afterRanInterruptiblyFailure(Throwable r1);

    public abstract void afterRanInterruptiblySuccess(T r1);

    public final void interruptTask() {
        Runnable r0 = get();
        if ((r0 instanceof Thread) == false) goto L16;
        Blocker r1 = new Blocker(this, null);
        Blocker.access$200(r1, Thread.currentThread());
        if (compareAndSet(r0, r1) == false) goto L19;
        ((Thread) r0).interrupt();     // Catch: Throwable -> L11
        if (getAndSet(DONE) != PARKED) goto L20;
        LockSupport.unpark((Thread) r0);
        return;
    L20:
        return;
    L11:
        th = move-exception;
        if (getAndSet(DONE) != PARKED) goto L15;
        LockSupport.unpark((Thread) r0);
    L15:
        throw th;
    L19:
        return;
    }

    public abstract boolean isDone();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        Thread r0 = Thread.currentThread();
        Object r1 = null;
        if (compareAndSet(null, r0) == false) goto L36;
        boolean r2 = isDone();
        if (r2 == true) goto L24;
        r1 = runInterruptibly();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        Platform.restoreInterruptIfIsInterruptedException(th);     // Catch: Throwable -> L16
        if (compareAndSet(r0, DONE) == true) goto L14;
        waitForInterrupt(r0);
    L14:
        if (r2 == true) goto L33;
        afterRanInterruptiblyFailure(th);
        return;
    L33:
        return;
    L16:
        th = move-exception;
        if (compareAndSet(r0, DONE) == true) goto L20;
        waitForInterrupt(r0);
    L20:
        if (r2 == true) goto L22;
        afterRanInterruptiblySuccess(NullnessCasts.uncheckedCastNullableTToT(null));
    L22:
        throw th;
    L24:
        if (compareAndSet(r0, DONE) == true) goto L26;
        waitForInterrupt(r0);
    L26:
        if (r2 == true) goto L35;
        afterRanInterruptiblySuccess(NullnessCasts.uncheckedCastNullableTToT(r1));
        return;
    L35:
        return;
    }

    public abstract T runInterruptibly();

    public abstract String toPendingString();

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        Runnable r0 = get();
        if (r0 != DONE) goto L6;
        String r02 = "running=[DONE]";
    L13:
        return r02 + ", " + toPendingString();
    L6:
        if ((r0 instanceof Blocker) == false) goto L9;
        r02 = "running=[INTERRUPTED]";
        goto L13
    L9:
        if ((r0 instanceof Thread) == false) goto L11;
        r02 = "running=[RUNNING ON " + ((Thread) r0).getName() + "]";
        goto L13
    L11:
        r02 = "running=[NOT STARTED YET]";
        goto L13
    }
}
