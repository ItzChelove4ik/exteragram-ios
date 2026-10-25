package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes5.dex */
public abstract class AbstractFuture<V> extends AbstractFutureState<V> {

    public static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED = null;
        static final Cancellation CAUSELESS_INTERRUPTED = null;
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if (AbstractFutureState.GENERATE_CANCELLATION_CAUSES == false) goto L6;
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

        public CancellationException newCancellationException() {
            CancellationException r0 = new CancellationException("Task was cancelled.");
            r0.initCause(this.cause);
            return r0;
        }
    }

    public static final class DelegatingToFuture<V> implements Runnable {
        final ListenableFuture<? extends V> future;
        final AbstractFuture<V> owner;

        public DelegatingToFuture(AbstractFuture<V> r1, ListenableFuture<? extends V> r2) {
            this.owner = r1;
            this.future = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.owner.value() != this) goto L10;
            Object r0 = AbstractFuture.access$000(this.future);
            if (AbstractFutureState.casValue(this.owner, this, r0) == false) goto L9;
            AbstractFuture.access$100(this.owner, false);
            return;
        L9:
            return;
        }
    }

    public static final class Failure {
        static final Failure FALLBACK_INSTANCE = null;
        final Throwable exception;

        /* renamed from: com.google.common.util.concurrent.AbstractFuture$Failure$1, reason: invalid class name */
        public class AnonymousClass1 extends Throwable {
            public AnonymousClass1(String r1) {
                super(r1);
            }

            @Override // java.lang.Throwable
            public Throwable fillInStackTrace() {
                return this;
            }
        }

        static {
            FALLBACK_INSTANCE = new Failure(new AnonymousClass1("Failure occurred while trying to finish a future."));
        }

        public Failure(Throwable r1) {
            this.exception = (Throwable) Preconditions.checkNotNull(r1);
        }

        public ExecutionException newExecutionException() {
            return new ExecutionException(this.exception);
        }
    }

    public static final class Listener {
        static final Listener TOMBSTONE = null;
        final Executor executor;
        Listener next;
        final Runnable task;

        static {
            TOMBSTONE = new Listener();
        }

        public Listener() {
            this.task = null;
            this.executor = null;
        }

        public Listener(Runnable r1, Executor r2) {
            this.task = r1;
            this.executor = r2;
        }
    }

    public interface Trusted<V> extends ListenableFuture<V> {
    }

    public static abstract class TrustedFuture<V> extends AbstractFuture<V> implements Trusted<V> {
        public TrustedFuture() {
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable r1, Executor r2) {
            super.addListener(r1, r2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public boolean cancel(boolean r1) {
            return super.cancel(r1);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public V get() {
            return (V) super.get();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public V get(long r1, TimeUnit r3) {
            return (V) super.get(r1, r3);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public boolean isCancelled() {
            return super.isCancelled();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public boolean isDone() {
            return super.isDone();
        }
    }

    public AbstractFuture() {
    }

    public static /* synthetic */ Object access$000(ListenableFuture r0) {
        return getFutureValue(r0);
    }

    public static /* synthetic */ void access$100(AbstractFuture r0, boolean r1) {
        complete(r0, r1);
    }

    private void addDoneString(StringBuilder r4) {
        Object r1 = getUninterruptibly(this);     // Catch: Exception -> L5 ExecutionException -> L7 CancellationException -> L10
        r4.append("SUCCESS, result=[");     // Catch: Exception -> L5 ExecutionException -> L7 CancellationException -> L10
        appendResultObject(r4, r1);     // Catch: Exception -> L5 ExecutionException -> L7 CancellationException -> L10
        r4.append("]");     // Catch: Exception -> L5 ExecutionException -> L7 CancellationException -> L10
        return;
    L10:
        r4.append("CANCELLED");
        return;
    L7:
        e = move-exception;
        r4.append("FAILURE, cause=[");
        r4.append(e.getCause());
        r4.append("]");
        return;
    L5:
        e = move-exception;
        r4.append("UNKNOWN, cause=[");
        r4.append(e.getClass());
        r4.append(" thrown from get()]");
    }

    private void addPendingString(StringBuilder r6) {
        int r0 = r6.length();
        r6.append("PENDING");
        Object r1 = value();
        if ((r1 instanceof DelegatingToFuture) == false) goto L15;
        r6.append(", setFuture=[");
        appendUserObject(r6, ((DelegatingToFuture) r1).future);
        r6.append("]");
    L12:
        if (isDone() == false) goto L17;
        r6.delete(r0, r6.length());
        addDoneString(r6);
        return;
    L17:
        return;
    L15:
        String r12 = Strings.emptyToNull(pendingToString());     // Catch: Throwable -> L7
    L9:
        if (r12 == null) goto L12;
        r6.append(", info=[");
        r6.append(r12);
        r6.append("]");
    L7:
        th = move-exception;
        Platform.rethrowIfErrorOtherThanStackOverflow(th);
        r12 = "Exception thrown from implementation: " + th.getClass();
        goto L9
    }

    private void appendResultObject(StringBuilder r1, Object r2) {
        if (r2 != null) goto L5;
        r1.append("null");
        return;
    L5:
        if (r2 != this) goto L8;
        r1.append("this future");
        return;
    L8:
        r1.append(r2.getClass().getName());
        r1.append("@");
        r1.append(Integer.toHexString(System.identityHashCode(r2)));
    }

    private void appendUserObject(StringBuilder r1, Object r2) {
        if (r2 != this) goto L7;
        r1.append("this future");     // Catch: Throwable -> L5
        return;
    L7:
        r1.append(r2);     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        Platform.rethrowIfErrorOtherThanStackOverflow(th);
        r1.append("Exception thrown from implementation: ");
        r1.append(th.getClass());
    }

    private Listener clearListeners(Listener r3) {
        Listener r32 = gasListeners(Listener.TOMBSTONE);
        Listener r2 = r3;
    L3:
        if (r32 == null) goto L5;
        Listener r0 = r32.next;
        r32.next = r2;
        r2 = r32;
        r32 = r0;
        goto L3
    L5:
        return r2;
    }

    private static void complete(AbstractFuture<V> r3, boolean r4) {
        Listener r0 = null;
    L3:
        r3.releaseWaiters();
        if (r4 == false) goto L6;
        r3.interruptTask();
        r4 = false;
    L6:
        r3.afterDone();
        Listener r32 = r3.clearListeners(r0);
    L7:
        if (r32 == null) goto L17;
        r0 = r32.next;
        Runnable r1 = r32.task;
        Objects.requireNonNull(r1);
        Runnable r12 = r1;
        if ((r12 instanceof DelegatingToFuture) == false) goto L15;
        DelegatingToFuture r13 = (DelegatingToFuture) r12;
        r3 = r13.owner;
        if (r3.value() != r13) goto L16;
        if (AbstractFutureState.casValue(r3, r13, getFutureValue(r13.future)) == true) goto L3;
    L16:
        r32 = r0;
        goto L7
    L15:
        Executor r33 = r32.executor;
        Objects.requireNonNull(r33);
        executeListener(r12, r33);
        goto L16
    }

    private static void executeListener(Runnable r5, Executor r6) {
        r6.execute(r5);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        AbstractFutureState.log.get().log(Level.SEVERE, "RuntimeException while executing runnable " + r5 + " with executor " + r6, e);
    }

    public static <V> V getDoneValue(Object r1) {
        if ((r1 instanceof Cancellation) == true) goto L11;
        if ((r1 instanceof Failure) == true) goto L9;
        return (V) getKnownSuccessfulDoneValue(r1);
    L9:
        throw ((Failure) r1).newExecutionException();
    L11:
        throw ((Cancellation) r1).newCancellationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Object getFutureValue(ListenableFuture<?> r6) {
        if ((r6 instanceof Trusted) == false) goto L15;
        Object r62 = ((AbstractFuture) r6).value();
        if ((r62 instanceof Cancellation) == false) goto L12;
        Cancellation r0 = (Cancellation) r62;
        if (r0.wasInterrupted == false) goto L12;
        if (r0.cause == null) goto L11;
        r62 = new Cancellation(false, r0.cause);
        goto L12
    L11:
        r62 = Cancellation.CAUSELESS_CANCELLED;
    L12:
        Objects.requireNonNull(r62);
        return r62;
    L15:
        if ((r6 instanceof InternalFutureFailureAccess) == false) goto L20;
        Throwable r1 = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) r6);
        if (r1 == null) goto L20;
        return new Failure(r1);
    L20:
        boolean r12 = r6.isCancelled();
        if (((!AbstractFutureState.GENERATE_CANCELLATION_CAUSES) & r12) == false) goto L47;
        Cancellation r63 = Cancellation.CAUSELESS_CANCELLED;
        Objects.requireNonNull(r63);
        return r63;
    L47:
        Object r3 = getUninterruptibly(r6);     // Catch: CancellationException -> L28 ExecutionException -> L30 Throwable -> L34
        if (r12 == false) goto L33;
        return new Cancellation(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + r6));
    L33:
        return maskNull(r3);
    L34:
        e = move-exception;
        return new Failure(e);
    L28:
        e = move-exception;
        if (r12 == true) goto L41;
        return new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + r6, e));
    L41:
        return new Cancellation(false, e);
    L30:
        e = move-exception;
        if (r12 == false) goto L46;
        return new Cancellation(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + r6, e));
    L46:
        return new Failure(e.getCause());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <V> V getKnownSuccessfulDoneValue(Object r1) {
        if (r1 == AbstractFutureState.NULL) goto L5;
        return r1;
    L5:
        return (V) NullnessCasts.uncheckedNull();
    }

    private static <V> V getUninterruptibly(Future<V> r1) {
        boolean r0 = false;
    L12:
        V r12 = r1.get();     // Catch: Throwable -> L7 InterruptedException -> L11
    L4:
        if (r0 == false) goto L6;
        Platform.interruptCurrentThread();
    L6:
        return r12;
    L11:
        r0 = true;
    L7:
        th = move-exception;
        if (r0 == false) goto L10;
        Platform.interruptCurrentThread();
    L10:
        throw th;
    }

    private static Object maskNull(Object r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        return AbstractFutureState.NULL;
    }

    public static boolean notInstanceOfDelegatingToFuture(Object r0) {
        return !(r0 instanceof DelegatingToFuture);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable r4, Executor r5) {
        Preconditions.checkNotNull(r4, "Runnable was null.");
        Preconditions.checkNotNull(r5, "Executor was null.");
        if (isDone() == true) goto L12;
        Listener r0 = listeners();
        if (r0 == Listener.TOMBSTONE) goto L12;
        Listener r1 = new Listener(r4, r5);
    L7:
        r1.next = r0;
        if (casListeners(r0, r1) == true) goto L9;
        r0 = listeners();
        if (r0 != Listener.TOMBSTONE) goto L7;
    L9:
        return;
    L12:
        executeListener(r4, r5);
    }

    public void afterDone() {
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean r7) {
        Object r0 = value();
        if (r0 != null) goto L5;
        boolean r3 = true;
    L7:
        if ((r3 | (r0 instanceof DelegatingToFuture)) == true) goto L9;
        return false;
    L9:
        if (AbstractFutureState.GENERATE_CANCELLATION_CAUSES == false) goto L11;
        Cancellation r32 = new Cancellation(r7, new CancellationException("Future.cancel() was called."));
    L15:
        boolean r4 = false;
    L17:
        if (AbstractFutureState.casValue(this, r0, r32) == true) goto L18;
        r0 = this.value();
        if (notInstanceOfDelegatingToFuture(r0) == false) goto L17;
        return r4;
    L18:
        complete(this, r7);
        if ((r0 instanceof DelegatingToFuture) == false) goto L30;
        ListenableFuture<? extends V> r6 = ((DelegatingToFuture) r0).future;
        if ((r6 instanceof Trusted) == false) goto L29;
        AbstractFuture<V> this = (AbstractFuture) r6;
        r0 = this.value();
        if (r0 != null) goto L25;
        boolean r42 = true;
    L27:
        if ((r42 | (r0 instanceof DelegatingToFuture)) == false) goto L30;
        r4 = true;
        goto L17
    L25:
        r42 = false;
        goto L27
    L29:
        r6.cancel(r7);
    L30:
        return true;
    L11:
        if (r7 == false) goto L13;
        r32 = Cancellation.CAUSELESS_INTERRUPTED;
    L14:
        Objects.requireNonNull(r32);
        goto L15
    L13:
        r32 = Cancellation.CAUSELESS_CANCELLED;
        goto L14
    L5:
        r3 = false;
        goto L7
    }

    @Override // java.util.concurrent.Future
    public V get() {
        return (V) Platform.get(this);
    }

    @Override // java.util.concurrent.Future
    public V get(long r1, TimeUnit r3) {
        return (V) Platform.get(this, r1, r3);
    }

    public void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return value() instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        Object r1 = value();
        if (r1 == null) goto L5;
        boolean r0 = true;
    L7:
        return notInstanceOfDelegatingToFuture(r1) & r0;
    L5:
        r0 = false;
        goto L7
    }

    public final void maybePropagateCancellationTo(Future<?> r3) {
        if (r3 == null) goto L4;
        boolean r0 = true;
    L6:
        if ((r0 & isCancelled()) == false) goto L9;
        r3.cancel(wasInterrupted());
        return;
    L9:
        return;
    L4:
        r0 = false;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String pendingToString() {
        if ((this instanceof ScheduledFuture) == true) goto L5;
        return null;
    L5:
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public boolean set(V r2) {
        if (AbstractFutureState.casValue(this, null, maskNull(r2)) == false) goto L6;
        complete(this, false);
        return true;
    L6:
        return false;
    }

    public boolean setException(Throwable r2) {
        if (AbstractFutureState.casValue(this, null, new Failure(r2)) == false) goto L6;
        complete(this, false);
        return true;
    L6:
        return false;
    }

    public final boolean setExceptionInternal(Throwable r1) {
        return setException(r1);
    }

    public boolean setFuture(ListenableFuture<? extends V> r5) {
        Preconditions.checkNotNull(r5);
        Object r0 = value();
        if (r0 != null) goto L23;
        if (r5.isDone() == true) goto L7;
        DelegatingToFuture r02 = new DelegatingToFuture(this, r5);
        if (AbstractFutureState.casValue(this, null, r02) == true) goto L26;
        r0 = value();
        goto L23
    L26:
        r5.addListener(r02, DirectExecutor.INSTANCE);     // Catch: Throwable -> L15
    L20:
        return true;
    L15:
        th = move-exception;
        Failure r1 = new Failure(th);     // Catch: Throwable -> L18
    L19:
        AbstractFutureState.casValue(this, r02, r1);
    L18:
        r1 = Failure.FALLBACK_INSTANCE;
        goto L19
    L7:
        if (AbstractFutureState.casValue(this, null, getFutureValue(r5)) == false) goto L10;
        complete(this, false);
        return true;
    L10:
        return false;
    L23:
        if ((r0 instanceof Cancellation) == false) goto L25;
        r5.cancel(((Cancellation) r0).wasInterrupted);
    L25:
        return false;
    }

    public final boolean setFutureInternal(ListenableFuture<? extends V> r1) {
        return setFuture(r1);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.") == false) goto L5;
        r0.append(getClass().getSimpleName());
    L6:
        r0.append('@');
        r0.append(Integer.toHexString(System.identityHashCode(this)));
        r0.append("[status=");
        if (isCancelled() == false) goto L10;
        r0.append("CANCELLED");
    L13:
        r0.append("]");
        return r0.toString();
    L10:
        if (isDone() == false) goto L12;
        addDoneString(r0);
        goto L13
    L12:
        addPendingString(r0);
        goto L13
    L5:
        r0.append(getClass().getName());
        goto L6
    }

    @Override // com.google.common.util.concurrent.internal.InternalFutureFailureAccess
    public final Throwable tryInternalFastPathGetFailure() {
        if ((this instanceof Trusted) == false) goto L8;
        Object r1 = value();
        if ((r1 instanceof Failure) == true) goto L7;
        return null;
    L7:
        return ((Failure) r1).exception;
    L8:
        return null;
    }

    public final boolean wasInterrupted() {
        Object r1 = value();
        if ((r1 instanceof Cancellation) == true) goto L5;
        return false;
    L5:
        if (((Cancellation) r1).wasInterrupted == false) goto L10;
        return true;
    L10:
        return false;
    }
}
