package com.sun.jna;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.IdentityHashMap;

/* loaded from: classes5.dex */
public class WeakMemoryHolder {
    IdentityHashMap<Reference<Object>, Memory> backingMap;
    ReferenceQueue<Object> referenceQueue;

    public WeakMemoryHolder() {
        this.referenceQueue = new ReferenceQueue();
        this.backingMap = new IdentityHashMap();
    }

    public synchronized void clean() {
        monitor-enter(this);
    L13:
        Reference<? extends Object> r0 = this.referenceQueue.poll();     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        this.backingMap.remove(r0);     // Catch: Throwable -> L7
        goto L13
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized void put(Object r3, Memory r4) {
        monitor-enter(this);
        clean();     // Catch: Throwable -> L6
        WeakReference r0 = new WeakReference(r3, this.referenceQueue);     // Catch: Throwable -> L6
        this.backingMap.put(r0, r4);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
