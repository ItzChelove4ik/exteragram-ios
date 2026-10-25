package org.webrtc;

import java.util.concurrent.atomic.AtomicInteger;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
class RefCountDelegate implements RefCounted {
    private final AtomicInteger refCount;
    private final Runnable releaseCallback;

    public RefCountDelegate(Runnable r3) {
        this.refCount = new AtomicInteger(1);
        this.releaseCallback = r3;
    }

    @Override // org.webrtc.RefCounted
    public void release() {
        int r0 = this.refCount.decrementAndGet();
        if (r0 < 0) goto L9;
        if (r0 != 0) goto L11;
        Runnable r1 = this.releaseCallback;
        if (r1 == null) goto L12;
        r1.run();
        return;
    L12:
        return;
    L11:
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("release() called on an object with refcount < 1");
    }

    @Override // org.webrtc.RefCounted
    public void retain() {
        if (this.refCount.incrementAndGet() < 2) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("retain() called on an object with refcount < 1");
    }

    public boolean safeRetain() {
        int r0 = this.refCount.get();
    L3:
        if (r0 == 0) goto L9;
        if (this.refCount.weakCompareAndSet(r0, r0 + 1) == true) goto L6;
        r0 = this.refCount.get();
        goto L3
    L6:
        return true;
    L9:
        return false;
    }
}
