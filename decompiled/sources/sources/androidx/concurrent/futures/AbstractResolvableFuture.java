package androidx.concurrent.futures;

import com.google.android.gms.internal.play_billing.zzo$$ExternalSyntheticBUOutline0;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class AbstractResolvableFuture<V> implements ListenableFuture<V> {
    static final AtomicHelper ATOMIC_HELPER = null;
    static final boolean GENERATE_CANCELLATION_CAUSES = false;
    private static final Object NULL = null;
    private static final Logger log = null;
    volatile Listener listeners;
    volatile Object value;
    volatile Waiter waiters;

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        public /* synthetic */ AtomicHelper(AnonymousClass1 r1) {
            this();
        }

        public abstract boolean casListeners(AbstractResolvableFuture<?> r1, Listener r2, Listener r3);

        public abstract boolean casValue(AbstractResolvableFuture<?> r1, Object r2, Object r3);

        public abstract boolean casWaiters(AbstractResolvableFuture<?> r1, Waiter r2, Waiter r3);

        public abstract void putNext(Waiter r1, Waiter r2);

        public abstract void putThread(Waiter r1, Thread r2);
    }

    public static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED = null;
        static final Cancellation CAUSELESS_INTERRUPTED = null;
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if (AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES == false) goto L6;
            CAUSELESS_CANCELLED = null;
            CAUSELESS_INTERRUPTED = null;
            return;
        L6:
            CAUSELESS_CANCELLED = new Cancellation(false, null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, null);
        }

        public Cancellation(boolean r1, Throwable r2) {
            this.wasInterrupted = r1;
            this.cause = r2;
        }
    }

    public static final class Failure {
        static final Failure FALLBACK_INSTANCE = null;
        final Throwable exception;

        /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$Failure$1, reason: invalid class name */
        /* loaded from: classes3.dex */
        public class AnonymousClass1 extends Throwable {
            public AnonymousClass1(String r1) {
                super(r1);
            }

            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                monitor-enter(this);
                monitor-exit(this);
                return this;
            }
        }

        static {
            FALLBACK_INSTANCE = new Failure(new AnonymousClass1("Failure occurred while trying to finish a future."));
        }

        public Failure(Throwable r1) {
            this.exception = (Throwable) AbstractResolvableFuture.checkNotNull(r1);
        }
    }

    public static final class Listener {
        static final Listener TOMBSTONE = null;
        final Executor executor;
        Listener next;
        final Runnable task;

        static {
            TOMBSTONE = new Listener(null, null);
        }

        public Listener(Runnable r1, Executor r2) {
            this.task = r1;
            this.executor = r2;
        }
    }

    public static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Listener> listenersUpdater;
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Waiter> waitersUpdater;

        public SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> r2, AtomicReferenceFieldUpdater<Waiter, Waiter> r3, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Waiter> r4, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Listener> r5, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> r6) {
            super(null);
            this.waiterThreadUpdater = r2;
            this.waiterNextUpdater = r3;
            this.waitersUpdater = r4;
            this.listenersUpdater = r5;
            this.valueUpdater = r6;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public boolean casListeners(AbstractResolvableFuture<?> r1, Listener r2, Listener r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.listenersUpdater, r1, r2, r3);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public boolean casValue(AbstractResolvableFuture<?> r1, Object r2, Object r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.valueUpdater, r1, r2, r3);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public boolean casWaiters(AbstractResolvableFuture<?> r1, Waiter r2, Waiter r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.waitersUpdater, r1, r2, r3);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public void putNext(Waiter r1, Waiter r2) {
            this.waiterNextUpdater.lazySet(r1, r2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public void putThread(Waiter r1, Thread r2) {
            this.waiterThreadUpdater.lazySet(r1, r2);
        }
    }

    /* loaded from: classes3.dex */
    public static final class SynchronizedHelper extends AtomicHelper {
        public SynchronizedHelper() {
            super(null);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public boolean casListeners(AbstractResolvableFuture<?> r1, Listener r2, Listener r3) {
            monitor-enter(r1);
        L8:
            th = move-exception;
            throw th;
        L4:
            if (r1.listeners != r2) goto L11;
            r1.listeners = r3;     // Catch: Throwable -> L8
            monitor-exit(r1);     // Catch: Throwable -> L8
            return true;
        L11:
            monitor-exit(r1);     // Catch: Throwable -> L8
            return false;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public boolean casValue(AbstractResolvableFuture<?> r1, Object r2, Object r3) {
            monitor-enter(r1);
        L8:
            th = move-exception;
            throw th;
        L4:
            if (r1.value != r2) goto L11;
            r1.value = r3;     // Catch: Throwable -> L8
            monitor-exit(r1);     // Catch: Throwable -> L8
            return true;
        L11:
            monitor-exit(r1);     // Catch: Throwable -> L8
            return false;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public boolean casWaiters(AbstractResolvableFuture<?> r1, Waiter r2, Waiter r3) {
            monitor-enter(r1);
        L8:
            th = move-exception;
            throw th;
        L4:
            if (r1.waiters != r2) goto L11;
            r1.waiters = r3;     // Catch: Throwable -> L8
            monitor-exit(r1);     // Catch: Throwable -> L8
            return true;
        L11:
            monitor-exit(r1);     // Catch: Throwable -> L8
            return false;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public void putNext(Waiter r1, Waiter r2) {
            r1.next = r2;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        public void putThread(Waiter r1, Thread r2) {
            r1.thread = r2;
        }
    }

    public static final class Waiter {
        static final Waiter TOMBSTONE = null;
        volatile Waiter next;
        volatile Thread thread;

        static {
            TOMBSTONE = new Waiter(false);
        }

        public Waiter() {
            AbstractResolvableFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }

        public Waiter(boolean r1) {
        }

        public void setNext(Waiter r2) {
            AbstractResolvableFuture.ATOMIC_HELPER.putNext(this, r2);
        }

        public void unpark() {
            Thread r0 = this.thread;
            if (r0 == null) goto L6;
            this.thread = null;
            LockSupport.unpark(r0);
            return;
        }
    }

    static {
        GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        log = Logger.getLogger(AbstractResolvableFuture.class.getName());
        AtomicHelper r3 = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Waiter.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Object.class, "value"));     // Catch: Throwable -> L5
        th = null;
    L7:
        ATOMIC_HELPER = r3;
        if (th == null) goto L10;
        log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
    L10:
        NULL = new Object();
        return;
    L5:
        th = th;
        r3 = new SynchronizedHelper();
        goto L7
    }

    public AbstractResolvableFuture() {
    }

    private void addDoneString(StringBuilder r4) {
        Object r1 = getUninterruptibly(this);     // Catch: RuntimeException -> L5 ExecutionException -> L7 CancellationException -> L10
        r4.append("SUCCESS, result=[");     // Catch: RuntimeException -> L5 ExecutionException -> L7 CancellationException -> L10
        r4.append(userObjectToString(r1));     // Catch: RuntimeException -> L5 ExecutionException -> L7 CancellationException -> L10
        r4.append("]");     // Catch: RuntimeException -> L5 ExecutionException -> L7 CancellationException -> L10
        return;
    L10:
        r4.append("CANCELLED");
        return;
    L5:
        e = move-exception;
        r4.append("UNKNOWN, cause=[");
        r4.append(e.getClass());
        r4.append(" thrown from get()]");
        return;
    L7:
        e = move-exception;
        r4.append("FAILURE, cause=[");
        r4.append(e.getCause());
        r4.append("]");
    }

    private static CancellationException cancellationExceptionWithCause(String r1, Throwable r2) {
        CancellationException r0 = new CancellationException(r1);
        r0.initCause(r2);
        return r0;
    }

    public static <T> T checkNotNull(T r0) {
        r0.getClass();
        return r0;
    }

    private Listener clearListeners(Listener r4) {
    L2:
        Listener r0 = this.listeners;
        if (ATOMIC_HELPER.casListeners(this, r0, Listener.TOMBSTONE) == false) goto L2;
    L4:
        Listener r3 = r4;
        r4 = r0;
        if (r4 == null) goto L7;
        r0 = r4.next;
        r4.next = r3;
        goto L4
    L7:
        return r3;
    }

    public static void complete(AbstractResolvableFuture<?> r2) {
        r2.releaseWaiters();
        r2.afterDone();
        Listener r22 = r2.clearListeners(null);
    L3:
        if (r22 == null) goto L5;
        Listener r0 = r22.next;
        executeListener(r22.task, r22.executor);
        r22 = r0;
        goto L3
    }

    private static void executeListener(Runnable r5, Executor r6) {
        r6.execute(r5);     // Catch: RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        log.log(Level.SEVERE, "RuntimeException while executing runnable " + r5 + " with executor " + r6, e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object r1) {
        if ((r1 instanceof Cancellation) == true) goto L14;
        if ((r1 instanceof Failure) == true) goto L12;
        if (r1 != NULL) goto L10;
        return null;
    L10:
        return r1;
    L12:
        throw new ExecutionException(((Failure) r1).exception);
    L14:
        throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) r1).cause);
    }

    public static <V> V getUninterruptibly(Future<V> r1) {
        boolean r0 = false;
    L12:
        V r12 = r1.get();     // Catch: Throwable -> L7 InterruptedException -> L11
    L4:
        if (r0 == false) goto L6;
        Thread.currentThread().interrupt();
    L6:
        return r12;
    L11:
        r0 = true;
    L7:
        th = move-exception;
        if (r0 == false) goto L10;
        Thread.currentThread().interrupt();
    L10:
        throw th;
    }

    private void releaseWaiters() {
    L2:
        Waiter r0 = this.waiters;
        if (ATOMIC_HELPER.casWaiters(this, r0, Waiter.TOMBSTONE) == false) goto L2;
    L4:
        if (r0 == null) goto L6;
        r0.unpark();
        r0 = r0.next;
        goto L4
    }

    private void removeWaiter(Waiter r5) {
        r5.thread = null;
    L3:
        Waiter r52 = this.waiters;
        if (r52 == Waiter.TOMBSTONE) goto L19;
        Waiter r1 = null;
    L7:
        if (r52 == null) goto L28;
        Waiter r2 = r52.next;
        if (r52.thread == null) goto L11;
        r1 = r52;
    L18:
        r52 = r2;
        goto L7
    L11:
        if (r1 == null) goto L16;
        r1.next = r2;
        if (r1.thread != null) goto L18;
    L16:
        if (ATOMIC_HELPER.casWaiters(this, r52, r2) == true) goto L18;
    L28:
        return;
    }

    private String userObjectToString(Object r1) {
        if (r1 != this) goto L6;
        return "this future";
    L6:
        return String.valueOf(r1);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable r4, Executor r5) {
        checkNotNull(r4);
        checkNotNull(r5);
        Listener r0 = this.listeners;
        if (r0 == Listener.TOMBSTONE) goto L10;
        Listener r1 = new Listener(r4, r5);
    L5:
        r1.next = r0;
        if (ATOMIC_HELPER.casListeners(this, r0, r1) == true) goto L7;
        r0 = this.listeners;
        if (r0 != Listener.TOMBSTONE) goto L5;
    L7:
        return;
    L10:
        executeListener(r4, r5);
    }

    public void afterDone() {
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r7) {
        Object r0 = this.value;
        if (r0 != null) goto L5;
        boolean r3 = true;
    L6:
        if (r3 == true) goto L8;
    L19:
        return false;
    L8:
        if (GENERATE_CANCELLATION_CAUSES == false) goto L10;
        Cancellation r32 = new Cancellation(r7, new CancellationException("Future.cancel() was called."));
    L14:
        if (ATOMIC_HELPER.casValue(this, r0, r32) == false) goto L19;
        if (r7 == false) goto L17;
        interruptTask();
    L17:
        complete(this);
        return true;
    L10:
        if (r7 == false) goto L12;
        r32 = Cancellation.CAUSELESS_INTERRUPTED;
        goto L14
    L12:
        r32 = Cancellation.CAUSELESS_CANCELLED;
        goto L14
    L5:
        r3 = false;
        goto L6
    }

    @Override // java.util.concurrent.Future
    public final V get() {
        if (Thread.interrupted() == true) goto L32;
        Object r0 = this.value;
        if (r0 == null) goto L7;
        boolean r3 = true;
    L8:
        if (r3 == true) goto L10;
        Waiter r02 = this.waiters;
        if (r02 == Waiter.TOMBSTONE) goto L30;
        Waiter r32 = new Waiter();
    L14:
        r32.setNext(r02);
        if (ATOMIC_HELPER.casWaiters(this, r02, r32) == true) goto L16;
        r02 = this.waiters;
        if (r02 != Waiter.TOMBSTONE) goto L14;
    L16:
        LockSupport.park(this);
        if (Thread.interrupted() == true) goto L25;
        Object r03 = this.value;
        if (r03 == null) goto L21;
        boolean r4 = true;
    L22:
        if (r4 == false) goto L16;
        return getDoneValue(r03);
    L21:
        r4 = false;
        goto L22
    L25:
        removeWaiter(r32);
        throw new InterruptedException();
    L30:
        return getDoneValue(this.value);
    L10:
        return getDoneValue(r0);
    L7:
        r3 = false;
        goto L8
    L32:
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public final V get(long r20, TimeUnit r22) {
        long r4 = r22.toNanos(r20);
        if (Thread.interrupted() == true) goto L78;
        Object r6 = this.value;
        if (r6 == null) goto L7;
        boolean r9 = true;
    L8:
        if (r9 == false) goto L12;
        return getDoneValue(r6);
    L12:
        if (r4 <= 0) goto L14;
        long r11 = System.nanoTime() + r4;
    L16:
        if (r4 < 1000) goto L41;
        Waiter r62 = this.waiters;
        if (r62 == Waiter.TOMBSTONE) goto L39;
        Waiter r15 = new Waiter();
    L20:
        r15.setNext(r62);
        if (ATOMIC_HELPER.casWaiters(this, r62, r15) == true) goto L22;
        r62 = this.waiters;
        if (r62 != Waiter.TOMBSTONE) goto L20;
    L22:
        LockSupport.parkNanos(this, r4);
        if (Thread.interrupted() == true) goto L34;
        Object r42 = this.value;
        if (r42 == null) goto L27;
        boolean r5 = true;
    L28:
        if (r5 == true) goto L30;
        r4 = r11 - System.nanoTime();
        if (r4 >= 1000) goto L22;
        removeWaiter(r15);
        goto L41
    L30:
        return getDoneValue(r42);
    L27:
        r5 = false;
        goto L28
    L34:
        removeWaiter(r15);
        throw new InterruptedException();
    L39:
        return getDoneValue(this.value);
    L41:
        if (r4 <= 0) goto L54;
        Object r43 = this.value;
        if (r43 == null) goto L45;
        boolean r52 = true;
    L46:
        if (r52 == true) goto L48;
        if (Thread.interrupted() == true) goto L53;
        r4 = r11 - System.nanoTime();
        goto L41
    L53:
        throw new InterruptedException();
    L48:
        return getDoneValue(r43);
    L45:
        r52 = false;
        goto L46
    L54:
        String r63 = toString();
        String r7 = r22.toString();
        Locale r112 = Locale.ROOT;
        String r72 = r7.toLowerCase(r112);
        String r2 = "Waited " + r20 + " " + r22.toString().toLowerCase(r112);
        if ((r4 + 1000) >= 0) goto L72;
        String r23 = r2.concat(" (plus ");
        long r44 = -r4;
        long r113 = r22.convert(r44, TimeUnit.NANOSECONDS);
        long r45 = r44 - r22.toNanos(r113);
        if (r113 != 0) goto L59;
    L62:
        boolean r16 = true;
    L63:
        if (r113 <= 0) goto L68;
        String r24 = r23 + r113 + " " + r72;
        if (r16 == false) goto L67;
        r24 = r24.concat(",");
    L67:
        r23 = r24.concat(" ");
    L68:
        if (r16 == false) goto L70;
        r23 = r23 + r45 + " nanoseconds ";
    L70:
        r2 = r23.concat("delay)");
        goto L72
    L59:
        if (r45 > 1000) goto L62;
        r16 = false;
    L72:
        if (isDone() == true) goto L76;
        zzo$$ExternalSyntheticBUOutline0.m(r2, r63);
        return null;
    L76:
        throw new TimeoutException(r2.concat(" but future completed as timeout expired"));
    L14:
        r11 = 0;
        goto L16
    L7:
        r9 = false;
        goto L8
    L78:
        throw new InterruptedException();
    }

    public void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        if (this.value == null) goto L5;
        return true;
    L5:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String pendingToString() {
        if ((this instanceof ScheduledFuture) == true) goto L5;
        return null;
    L5:
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public boolean set(V r3) {
        if (r3 != null) goto L5;
        r3 = (V) NULL;
    L5:
        if (ATOMIC_HELPER.casValue(this, null, r3) == false) goto L8;
        complete(this);
        return true;
    L8:
        return false;
    }

    public boolean setException(Throwable r3) {
        Failure r0 = new Failure((Throwable) checkNotNull(r3));
        if (ATOMIC_HELPER.casValue(this, null, r0) == false) goto L6;
        complete(this);
        return true;
    L6:
        return false;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(super.toString());
        r0.append("[status=");
        if (isCancelled() == false) goto L6;
        r0.append("CANCELLED");
    L20:
        r0.append("]");
        return r0.toString();
    L6:
        if (isDone() == false) goto L22;
        addDoneString(r0);
        goto L20
    L22:
        String r1 = pendingToString();     // Catch: RuntimeException -> L10
    L12:
        if (r1 == null) goto L17;
        if (r1.isEmpty() == true) goto L17;
        r0.append("PENDING, info=[");
        r0.append(r1);
        r0.append("]");
    L17:
        if (isDone() == false) goto L19;
        addDoneString(r0);
        goto L20
    L19:
        r0.append("PENDING");
    L10:
        e = move-exception;
        r1 = "Exception thrown from implementation: " + e.getClass();
        goto L12
    }

    public final boolean wasInterrupted() {
        Object r1 = this.value;
        if ((r1 instanceof Cancellation) == true) goto L5;
        return false;
    L5:
        if (((Cancellation) r1).wasInterrupted == false) goto L10;
        return true;
    L10:
        return false;
    }
}
