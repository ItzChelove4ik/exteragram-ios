package androidx.media3.exoplayer.mediacodec;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract /* synthetic */ class AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(AtomicReference r1, Object r2, Object r3) {
    L3:
        if (r1.compareAndSet(r2, r3) == true) goto L4;
        if (r1.get() == r2) goto L3;
        return false;
    L4:
        return true;
    }
}
