package androidx.camera.core.internal.utils;

import java.util.ArrayDeque;

/* loaded from: classes3.dex */
public abstract class ArrayRingBuffer<T> {
    private final ArrayDeque<T> mBuffer;
    private final Object mLock;
    final RingBuffer$OnRemoveCallback<T> mOnRemoveCallback;
    private final int mRingBufferCapacity;

    public ArrayRingBuffer(int r2, RingBuffer$OnRemoveCallback<T> r3) {
        this.mLock = new Object();
        this.mRingBufferCapacity = r2;
        this.mBuffer = new ArrayDeque(r2);
        this.mOnRemoveCallback = r3;
    }

    public T dequeue() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        T r1 = this.mBuffer.removeLast();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void enqueue(T r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mBuffer.size() < this.mRingBufferCapacity) goto L9;
        T r1 = dequeue();     // Catch: Throwable -> L7
    L10:
        this.mBuffer.addFirst(r4);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        RingBuffer$OnRemoveCallback<T> r3 = this.mOnRemoveCallback;
        if (r3 == null) goto L21;
        if (r1 == null) goto L22;
        r3.onRemove(r1);
        return;
    L22:
        return;
    L21:
        return;
    L9:
        r1 = null;
        goto L10
    }

    public boolean isEmpty() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        boolean r1 = this.mBuffer.isEmpty();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
