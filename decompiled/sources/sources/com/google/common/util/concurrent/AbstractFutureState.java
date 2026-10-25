package com.google.common.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.internal.cast.zzwb$zzd$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.internal.play_billing.zzo$$ExternalSyntheticBUOutline0;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
abstract class AbstractFutureState<V> extends InternalFutureFailureAccess implements ListenableFuture<V> {
    private static final AtomicHelper ATOMIC_HELPER = null;
    static final boolean GENERATE_CANCELLATION_CAUSES = false;
    static final Object NULL = null;
    static final LazyLogger log = null;
    volatile AbstractFuture.Listener listenersField;
    volatile Object valueField;
    volatile Waiter waitersField;

    /* renamed from: com.google.common.util.concurrent.AbstractFutureState$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        public /* synthetic */ AtomicHelper(AnonymousClass1 r1) {
            this();
        }

        public abstract boolean casListeners(AbstractFutureState<?> r1, AbstractFuture.Listener r2, AbstractFuture.Listener r3);

        public abstract boolean casValue(AbstractFutureState<?> r1, Object r2, Object r3);

        public abstract boolean casWaiters(AbstractFutureState<?> r1, Waiter r2, Waiter r3);

        public abstract AbstractFuture.Listener gasListeners(AbstractFutureState<?> r1, AbstractFuture.Listener r2);

        public abstract Waiter gasWaiters(AbstractFutureState<?> r1, Waiter r2);

        public abstract void putNext(Waiter r1, Waiter r2);

        public abstract void putThread(Waiter r1, Thread r2);
    }

    public static final class AtomicReferenceFieldUpdaterAtomicHelper extends AtomicHelper {
        private static final AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, AbstractFuture.Listener> listenersUpdater = null;
        private static final AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, Object> valueUpdater = null;
        private static final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater = null;
        private static final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater = null;
        private static final AtomicReferenceFieldUpdater<? super AbstractFutureState<?>, Waiter> waitersUpdater = null;

        static {
            waiterThreadUpdater = AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread");
            waiterNextUpdater = AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next");
            waitersUpdater = AtomicReferenceFieldUpdater.newUpdater(AbstractFutureState.class, Waiter.class, "waitersField");
            listenersUpdater = AtomicReferenceFieldUpdater.newUpdater(AbstractFutureState.class, AbstractFuture.Listener.class, "listenersField");
            valueUpdater = AtomicReferenceFieldUpdater.newUpdater(AbstractFutureState.class, Object.class, "valueField");
        }

        private AtomicReferenceFieldUpdaterAtomicHelper() {
            super(null);
        }

        public /* synthetic */ AtomicReferenceFieldUpdaterAtomicHelper(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casListeners(AbstractFutureState<?> r1, AbstractFuture.Listener r2, AbstractFuture.Listener r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(listenersUpdater, r1, r2, r3);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casValue(AbstractFutureState<?> r1, Object r2, Object r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(valueUpdater, r1, r2, r3);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casWaiters(AbstractFutureState<?> r1, Waiter r2, Waiter r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(waitersUpdater, r1, r2, r3);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public AbstractFuture.Listener gasListeners(AbstractFutureState<?> r1, AbstractFuture.Listener r2) {
            return listenersUpdater.getAndSet(r1, r2);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public Waiter gasWaiters(AbstractFutureState<?> r1, Waiter r2) {
            return waitersUpdater.getAndSet(r1, r2);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putNext(Waiter r1, Waiter r2) {
            waiterNextUpdater.lazySet(r1, r2);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putThread(Waiter r1, Thread r2) {
            waiterThreadUpdater.lazySet(r1, r2);
        }
    }

    public static final class UnsafeAtomicHelper extends AtomicHelper {
        static final long LISTENERS_OFFSET = 0;
        static final Unsafe UNSAFE = null;
        static final long VALUE_OFFSET = 0;
        static final long WAITERS_OFFSET = 0;
        static final long WAITER_NEXT_OFFSET = 0;
        static final long WAITER_THREAD_OFFSET = 0;

        /* renamed from: $r8$lambda$1xDzn45jsNt5z--M7O-rj5JR6vg, reason: not valid java name */
        public static /* synthetic */ Unsafe m1915$r8$lambda$1xDzn45jsNt5zM7Orj5JR6vg() {
            return lambda$static$0();
        }

        static {
            Unsafe r1 = Unsafe.getUnsafe();     // Catch: SecurityException -> L5
        L21:
            WAITERS_OFFSET = r1.objectFieldOffset(AbstractFutureState.class.getDeclaredField("waitersField"));     // Catch: NoSuchFieldException -> L11
            LISTENERS_OFFSET = r1.objectFieldOffset(AbstractFutureState.class.getDeclaredField("listenersField"));     // Catch: NoSuchFieldException -> L11
            VALUE_OFFSET = r1.objectFieldOffset(AbstractFutureState.class.getDeclaredField("valueField"));     // Catch: NoSuchFieldException -> L11
            WAITER_THREAD_OFFSET = r1.objectFieldOffset(Waiter.class.getDeclaredField("thread"));     // Catch: NoSuchFieldException -> L11
            WAITER_NEXT_OFFSET = r1.objectFieldOffset(Waiter.class.getDeclaredField("next"));     // Catch: NoSuchFieldException -> L11
            UNSAFE = r1;     // Catch: NoSuchFieldException -> L11
            return;
        L11:
            e = move-exception;
            HttpUrl$$ExternalSyntheticBUOutline0.m(e);
            return;
        L5:
            AbstractFutureState$UnsafeAtomicHelper$$ExternalSyntheticLambda0 r12 = new AbstractFutureState$UnsafeAtomicHelper$$ExternalSyntheticLambda0();     // Catch: Exception -> L14
            r1 = (Unsafe) Class.forName("java.security.AccessController").getMethod("doPrivileged", new Class[]{PrivilegedExceptionAction.class}).invoke(null, new Object[]{r12});     // Catch: Exception -> L8
            goto L21
        L8:
            r1 = (Unsafe) r12.run();     // Catch: Exception -> L14
        L14:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Could not initialize intrinsics", e);
        }

        private UnsafeAtomicHelper() {
            super(null);
        }

        public /* synthetic */ UnsafeAtomicHelper(AnonymousClass1 r1) {
            this();
        }

        private static /* synthetic */ Unsafe lambda$static$0() {
            Field[] r1 = Unsafe.class.getDeclaredFields();
            int r2 = r1.length;
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L10;
            Field r4 = r1[r3];
            r4.setAccessible(true);
            Object r42 = r4.get(null);
            if (Unsafe.class.isInstance(r42) == true) goto L7;
            r3 = r3 + 1;
            goto L3
        L7:
            return (Unsafe) Unsafe.class.cast(r42);
        L10:
            throw new NoSuchFieldError("the Unsafe");
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casListeners(AbstractFutureState<?> r7, AbstractFuture.Listener r8, AbstractFuture.Listener r9) {
            return zzwb$zzd$$ExternalSyntheticBackportWithForwarding0.m(UNSAFE, r7, LISTENERS_OFFSET, r8, r9);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casValue(AbstractFutureState<?> r7, Object r8, Object r9) {
            return zzwb$zzd$$ExternalSyntheticBackportWithForwarding0.m(UNSAFE, r7, VALUE_OFFSET, r8, r9);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public boolean casWaiters(AbstractFutureState<?> r7, Waiter r8, Waiter r9) {
            return zzwb$zzd$$ExternalSyntheticBackportWithForwarding0.m(UNSAFE, r7, WAITERS_OFFSET, r8, r9);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public AbstractFuture.Listener gasListeners(AbstractFutureState<?> r3, AbstractFuture.Listener r4) {
        L2:
            AbstractFuture.Listener r0 = r3.listenersField;
            if (r4 == r0) goto L7;
            if (casListeners(r3, r0, r4) == false) goto L2;
        L7:
            return r0;
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public Waiter gasWaiters(AbstractFutureState<?> r3, Waiter r4) {
        L2:
            Waiter r0 = r3.waitersField;
            if (r4 == r0) goto L7;
            if (casWaiters(r3, r0, r4) == false) goto L2;
        L7:
            return r0;
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putNext(Waiter r3, Waiter r4) {
            UNSAFE.putObject(r3, WAITER_NEXT_OFFSET, r4);
        }

        @Override // com.google.common.util.concurrent.AbstractFutureState.AtomicHelper
        public void putThread(Waiter r3, Thread r4) {
            UNSAFE.putObject(r3, WAITER_THREAD_OFFSET, r4);
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
            AbstractFutureState.access$000(this, Thread.currentThread());
        }

        public Waiter(boolean r1) {
        }

        public void setNext(Waiter r1) {
            AbstractFutureState.access$100(this, r1);
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
        NULL = new Object();
        log = new LazyLogger(AbstractFuture.class);
        GENERATE_CANCELLATION_CAUSES = computeGenerateCancellationCauses();
        if (mightBeAndroid() == true) goto L10;
        AtomicHelper r0 = new AtomicReferenceFieldUpdaterAtomicHelper(null);
    L8:
        ATOMIC_HELPER = r0;
        return;
    L10:
        r0 = new UnsafeAtomicHelper(null);     // Catch: Throwable -> L6
    L6:
        r0 = new AtomicReferenceFieldUpdaterAtomicHelper(null);
        goto L8
    }

    public AbstractFutureState() {
    }

    public static /* synthetic */ void access$000(Waiter r0, Thread r1) {
        putThread(r0, r1);
    }

    public static /* synthetic */ void access$100(Waiter r0, Waiter r1) {
        putNext(r0, r1);
    }

    public static boolean casValue(AbstractFutureState<?> r1, Object r2, Object r3) {
        return ATOMIC_HELPER.casValue(r1, r2, r3);
    }

    private boolean casWaiters(Waiter r2, Waiter r3) {
        return ATOMIC_HELPER.casWaiters(this, r2, r3);
    }

    private static boolean computeGenerateCancellationCauses() {
        return Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    L4:
        return false;
    }

    private final Waiter gasWaiters(Waiter r2) {
        return ATOMIC_HELPER.gasWaiters(this, r2);
    }

    private static boolean mightBeAndroid() {
        String r0 = System.getProperty("java.runtime.name", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.contains("Android") == true) goto L11;
        return false;
    L11:
        return true;
    }

    private static void putNext(Waiter r1, Waiter r2) {
        ATOMIC_HELPER.putNext(r1, r2);
    }

    private static void putThread(Waiter r1, Thread r2) {
        ATOMIC_HELPER.putThread(r1, r2);
    }

    private void removeWaiter(Waiter r5) {
        r5.thread = null;
    L3:
        Waiter r52 = this.waitersField;
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
        if (casWaiters(r52, r2) == true) goto L18;
    L28:
        return;
    }

    public final V blockingGet() {
        if (Thread.interrupted() == true) goto L34;
        Object r0 = this.valueField;
        if (r0 == null) goto L7;
        boolean r3 = true;
    L9:
        if ((r3 & AbstractFuture.notInstanceOfDelegatingToFuture(r0)) == true) goto L11;
        Waiter r02 = this.waitersField;
        if (r02 == Waiter.TOMBSTONE) goto L31;
        Waiter r32 = new Waiter();
    L15:
        r32.setNext(r02);
        if (casWaiters(r02, r32) == true) goto L17;
        r02 = this.waitersField;
        if (r02 != Waiter.TOMBSTONE) goto L15;
    L17:
        LockSupport.park(this);
        if (Thread.interrupted() == true) goto L27;
        Object r03 = this.valueField;
        if (r03 == null) goto L22;
        boolean r4 = true;
    L24:
        if ((r4 & AbstractFuture.notInstanceOfDelegatingToFuture(r03)) == false) goto L17;
        return (V) AbstractFuture.getDoneValue(r03);
    L22:
        r4 = false;
        goto L24
    L27:
        removeWaiter(r32);
        throw new InterruptedException();
    L31:
        Object r6 = this.valueField;
        Objects.requireNonNull(r6);
        return (V) AbstractFuture.getDoneValue(r6);
    L11:
        return (V) AbstractFuture.getDoneValue(r0);
    L7:
        r3 = false;
        goto L9
    L34:
        throw new InterruptedException();
    }

    public final V blockingGet(long r20, TimeUnit r22) {
        long r4 = r22.toNanos(r20);
        if (Thread.interrupted() == true) goto L81;
        Object r6 = this.valueField;
        if (r6 == null) goto L7;
        boolean r9 = true;
    L9:
        if ((r9 & AbstractFuture.notInstanceOfDelegatingToFuture(r6)) == false) goto L13;
        return (V) AbstractFuture.getDoneValue(r6);
    L13:
        if (r4 <= 0) goto L15;
        long r11 = System.nanoTime() + r4;
    L17:
        if (r4 < 1000) goto L43;
        Waiter r62 = this.waitersField;
        if (r62 == Waiter.TOMBSTONE) goto L40;
        Waiter r15 = new Waiter();
    L21:
        r15.setNext(r62);
        if (casWaiters(r62, r15) == true) goto L23;
        r62 = this.waitersField;
        if (r62 != Waiter.TOMBSTONE) goto L21;
    L23:
        OverflowAvoidingLockSupport.parkNanos(this, r4);
        if (Thread.interrupted() == true) goto L36;
        Object r42 = this.valueField;
        if (r42 == null) goto L28;
        boolean r5 = true;
    L30:
        if ((r5 & AbstractFuture.notInstanceOfDelegatingToFuture(r42)) == true) goto L32;
        r4 = r11 - System.nanoTime();
        if (r4 >= 1000) goto L23;
        removeWaiter(r15);
        goto L43
    L32:
        return (V) AbstractFuture.getDoneValue(r42);
    L28:
        r5 = false;
        goto L30
    L36:
        removeWaiter(r15);
        throw new InterruptedException();
    L40:
        Object r0 = this.valueField;
        Objects.requireNonNull(r0);
        return (V) AbstractFuture.getDoneValue(r0);
    L43:
        if (r4 <= 0) goto L57;
        Object r43 = this.valueField;
        if (r43 == null) goto L47;
        boolean r52 = true;
    L49:
        if ((r52 & AbstractFuture.notInstanceOfDelegatingToFuture(r43)) == true) goto L51;
        if (Thread.interrupted() == true) goto L56;
        r4 = r11 - System.nanoTime();
        goto L43
    L56:
        throw new InterruptedException();
    L51:
        return (V) AbstractFuture.getDoneValue(r43);
    L47:
        r52 = false;
        goto L49
    L57:
        String r63 = toString();
        String r7 = r22.toString();
        Locale r112 = Locale.ROOT;
        String r72 = r7.toLowerCase(r112);
        String r2 = "Waited " + r20 + " " + r22.toString().toLowerCase(r112);
        if ((r4 + 1000) >= 0) goto L75;
        String r23 = r2.concat(" (plus ");
        long r44 = -r4;
        long r113 = r22.convert(r44, TimeUnit.NANOSECONDS);
        long r45 = r44 - r22.toNanos(r113);
        if (r113 != 0) goto L62;
    L65:
        boolean r16 = true;
    L66:
        if (r113 <= 0) goto L71;
        String r24 = r23 + r113 + " " + r72;
        if (r16 == false) goto L70;
        r24 = r24.concat(",");
    L70:
        r23 = r24.concat(" ");
    L71:
        if (r16 == false) goto L73;
        r23 = r23 + r45 + " nanoseconds ";
    L73:
        r2 = r23.concat("delay)");
        goto L75
    L62:
        if (r45 > 1000) goto L65;
        r16 = false;
    L75:
        if (isDone() == true) goto L79;
        zzo$$ExternalSyntheticBUOutline0.m(r2, r63);
        return null;
    L79:
        throw new TimeoutException(r2.concat(" but future completed as timeout expired"));
    L15:
        r11 = 0;
        goto L17
    L7:
        r9 = false;
        goto L9
    L81:
        throw new InterruptedException();
    }

    public final boolean casListeners(AbstractFuture.Listener r2, AbstractFuture.Listener r3) {
        return ATOMIC_HELPER.casListeners(this, r2, r3);
    }

    public final AbstractFuture.Listener gasListeners(AbstractFuture.Listener r2) {
        return ATOMIC_HELPER.gasListeners(this, r2);
    }

    public final AbstractFuture.Listener listeners() {
        return this.listenersField;
    }

    public final void releaseWaiters() {
        Waiter r1 = gasWaiters(Waiter.TOMBSTONE);
    L3:
        if (r1 == null) goto L5;
        r1.unpark();
        r1 = r1.next;
        goto L3
    }

    public final Object value() {
        return this.valueField;
    }
}
