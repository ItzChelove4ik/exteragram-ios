package androidx.media3.common.util;

import android.os.Looper;
import android.os.Message;
import androidx.media3.common.FlagSet;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes3.dex */
public final class ListenerSet<T> {
    private final Clock clock;
    private final ArrayDeque<Runnable> flushingEvents;
    private final HandlerWrapper handler;
    private final IterationFinishedEvent<T> iterationFinishedEvent;
    private final CopyOnWriteArraySet<ListenerHolder<T>> listeners;
    private final ArrayDeque<Runnable> queuedEvents;
    private boolean released;
    private final Object releasedLock;
    private boolean throwsWhenUsingWrongThread;

    public interface Event<T> {
        void invoke(T r1);
    }

    public interface IterationFinishedEvent<T> {
        void invoke(T r1, FlagSet r2);
    }

    public static final class ListenerHolder<T> {
        private FlagSet.Builder flagsBuilder;
        public final T listener;
        private boolean needsIterationFinishedEvent;
        private boolean released;

        public ListenerHolder(T r1) {
            this.listener = r1;
            this.flagsBuilder = new FlagSet.Builder();
        }

        public boolean equals(Object r3) {
            if (this != r3) goto L5;
            return true;
        L5:
            if (r3 != null) goto L7;
            return false;
        L7:
            if (ListenerHolder.class == r3.getClass()) goto L10;
            return false;
        L10:
            return this.listener.equals(((ListenerHolder) r3).listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }

        public void invoke(int r2, Event<T> r3) {
            if (this.released == false) goto L5;
            return;
        L5:
            if (r2 == (-1)) goto L7;
            this.flagsBuilder.add(r2);
        L7:
            this.needsIterationFinishedEvent = true;
            r3.invoke(this.listener);
        }

        public void iterationFinished(IterationFinishedEvent<T> r3) {
            if (this.released == false) goto L5;
            return;
        L5:
            if (this.needsIterationFinishedEvent == false) goto L9;
            FlagSet r0 = this.flagsBuilder.build();
            this.flagsBuilder = new FlagSet.Builder();
            this.needsIterationFinishedEvent = false;
            r3.invoke(this.listener, r0);
            return;
        }

        public void release(IterationFinishedEvent<T> r2) {
            this.released = true;
            if (this.needsIterationFinishedEvent == false) goto L6;
            this.needsIterationFinishedEvent = false;
            r2.invoke(this.listener, this.flagsBuilder.build());
            return;
        }
    }

    /* renamed from: $r8$lambda$Maws-DUsVhcg5uFQgolq5mcQJiM, reason: not valid java name */
    public static /* synthetic */ void m757$r8$lambda$MawsDUsVhcg5uFQgolq5mcQJiM(CopyOnWriteArraySet r1, int r2, Event r3) {
        Iterator r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        ((ListenerHolder) r12.next()).invoke(r2, r3);
        goto L4
    }

    /* renamed from: $r8$lambda$rFcF5Pkb99AL585p5-2u78YfNkY, reason: not valid java name */
    public static /* synthetic */ boolean m758$r8$lambda$rFcF5Pkb99AL585p52u78YfNkY(ListenerSet r0, Message r1) {
        return r0.handleMessage(r1);
    }

    public ListenerSet(Looper r7, Clock r8, IterationFinishedEvent<T> r9) {
        this(new CopyOnWriteArraySet(), r7, r8, r9, true);
    }

    private ListenerSet(CopyOnWriteArraySet<ListenerHolder<T>> r1, Looper r2, Clock r3, IterationFinishedEvent<T> r4, boolean r5) {
        this.clock = r3;
        this.listeners = r1;
        this.iterationFinishedEvent = r4;
        this.releasedLock = new Object();
        this.flushingEvents = new ArrayDeque();
        this.queuedEvents = new ArrayDeque();
        this.handler = r3.createHandler(r2, new ListenerSet$$ExternalSyntheticLambda0(this));
        this.throwsWhenUsingWrongThread = r5;
    }

    private boolean handleMessage(Message r4) {
        Iterator<ListenerHolder<T>> r42 = this.listeners.iterator();
    L4:
        if (r42.hasNext() == false) goto L7;
        r42.next().iterationFinished(this.iterationFinishedEvent);
        if (this.handler.hasMessages(1) == false) goto L4;
    L7:
        return true;
    }

    private void verifyCurrentThread() {
        if (this.throwsWhenUsingWrongThread == true) goto L6;
        return;
    L6:
        if (Thread.currentThread() != this.handler.getLooper().getThread()) goto L8;
        boolean r1 = true;
    L9:
        Assertions.checkState(r1);
        return;
    L8:
        r1 = false;
        goto L9
    }

    public void add(T r3) {
        Assertions.checkNotNull(r3);
        Object r0 = this.releasedLock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.released == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.listeners.add(new ListenerHolder(r3));     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }

    public ListenerSet<T> copy(Looper r7, Clock r8, IterationFinishedEvent<T> r9) {
        return new ListenerSet(this.listeners, r7, r8, r9, this.throwsWhenUsingWrongThread);
    }

    public ListenerSet<T> copy(Looper r2, IterationFinishedEvent<T> r3) {
        return copy(r2, this.clock, r3);
    }

    public void flushEvents() {
        verifyCurrentThread();
        if (this.queuedEvents.isEmpty() == false) goto L6;
        return;
    L6:
        if (this.handler.hasMessages(1) == true) goto L8;
        HandlerWrapper r0 = this.handler;
        r0.sendMessageAtFrontOfQueue(r0.obtainMessage(1));
    L8:
        boolean r02 = this.flushingEvents.isEmpty();
        this.flushingEvents.addAll(this.queuedEvents);
        this.queuedEvents.clear();
        if (r02 == true) goto L12;
        return;
    L12:
        if (this.flushingEvents.isEmpty() == true) goto L17;
        this.flushingEvents.peekFirst().run();
        this.flushingEvents.removeFirst();
        goto L12
    }

    public void queueEvent(int r3, Event<T> r4) {
        verifyCurrentThread();
        CopyOnWriteArraySet r0 = new CopyOnWriteArraySet(this.listeners);
        this.queuedEvents.add(new ListenerSet$$ExternalSyntheticLambda1(r0, r3, r4));
    }

    public void release() {
        verifyCurrentThread();
        Object r0 = this.releasedLock;
        monitor-enter(r0);
        this.released = true;     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        Iterator<ListenerHolder<T>> r02 = this.listeners.iterator();
    L9:
        if (r02.hasNext() == false) goto L11;
        r02.next().release(this.iterationFinishedEvent);
        goto L9
    L11:
        this.listeners.clear();
        return;
    L13:
        th = move-exception;
        throw th;
    }

    public void remove(T r4) {
        verifyCurrentThread();
        Iterator<ListenerHolder<T>> r0 = this.listeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        ListenerHolder<T> r1 = r0.next();
        if (r1.listener.equals(r4) == false) goto L4;
        r1.release(this.iterationFinishedEvent);
        this.listeners.remove(r1);
        goto L4
    }

    public void sendEvent(int r1, Event<T> r2) {
        queueEvent(r1, r2);
        flushEvents();
    }
}
