package com.google.firebase.components;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class EventBus implements Subscriber, Publisher {
    private final Executor defaultExecutor;
    private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> handlerMap;
    private Queue<Event<?>> pendingEvents;

    public static /* synthetic */ void $r8$lambda$sqqhdhVt61Z8rtVGSrd8s31MuYQ(Map.Entry r0, Event r1) {
        ((EventHandler) r0.getKey()).handle(r1);
    }

    public EventBus(Executor r2) {
        this.handlerMap = new HashMap();
        this.pendingEvents = new ArrayDeque();
        this.defaultExecutor = r2;
    }

    private synchronized Set<Map.Entry<EventHandler<Object>, Executor>> getHandlers(Event<?> r1) {
        monitor-enter(this);
        throw null;     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        throw th;
    }

    public void enablePublishingAndFlushPending() {
        monitor-enter(this);
        Queue<Event<?>> r0 = this.pendingEvents;     // Catch: Throwable -> L6
        if (r0 == null) goto L8;
        this.pendingEvents = null;     // Catch: Throwable -> L6
    L9:
        monitor-exit(this);     // Catch: Throwable -> L6
        if (r0 == null) goto L15;
        Iterator<Event<?>> r02 = r0.iterator();
    L13:
        if (r02.hasNext() == false) goto L21;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.next());
        publish(null);
        goto L13
    L21:
        return;
    L15:
        return;
    L8:
        r0 = null;
    L6:
        th = move-exception;
        throw th;
    }

    public void publish(Event<?> r4) {
        Preconditions.checkNotNull(r4);
        monitor-enter(this);
        Queue<Event<?>> r0 = this.pendingEvents;     // Catch: Throwable -> L9
        if (r0 == null) goto L11;
        r0.add(r4);     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        return;
    L11:
        monitor-exit(this);     // Catch: Throwable -> L9
        Iterator<Map.Entry<EventHandler<Object>, Executor>> r3 = getHandlers(r4).iterator();
    L14:
        if (r3.hasNext() == false) goto L16;
        Map.Entry<EventHandler<Object>, Executor> r02 = r3.next();
        r02.getValue().execute(new EventBus$$ExternalSyntheticLambda0(r02, r4));
        goto L14
    L16:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    @Override // com.google.firebase.events.Subscriber
    public <T> void subscribe(Class<T> r2, EventHandler<? super T> r3) {
        subscribe(r2, this.defaultExecutor, r3);
    }

    @Override // com.google.firebase.events.Subscriber
    public synchronized <T> void subscribe(Class<T> r3, Executor r4, EventHandler<? super T> r5) {
        monitor-enter(this);
        Preconditions.checkNotNull(r3);     // Catch: Throwable -> L6
        Preconditions.checkNotNull(r5);     // Catch: Throwable -> L6
        Preconditions.checkNotNull(r4);     // Catch: Throwable -> L6
        if (this.handlerMap.containsKey(r3) == true) goto L8;
        this.handlerMap.put(r3, new ConcurrentHashMap());     // Catch: Throwable -> L6
    L8:
        this.handlerMap.get(r3).put(r5, r4);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
