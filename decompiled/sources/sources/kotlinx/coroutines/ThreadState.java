package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0010J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\t\u0010\u0004\u001a\u00020\u0005X\u0082\u0004R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/ThreadState;", "Lkotlinx/coroutines/JobNode;", "<init>", "()V", "_state", "Lkotlinx/atomicfu/AtomicInt;", "targetThread", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "cancelHandle", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", _UrlKt.FRAGMENT_ENCODE_SET, "getOnCancelling", "()Z", "setup", _UrlKt.FRAGMENT_ENCODE_SET, "job", "Lkotlinx/coroutines/Job;", "clearInterrupt", "invoke", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "invalidState", _UrlKt.FRAGMENT_ENCODE_SET, "state", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ThreadState extends JobNode {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _state$volatile$FU = null;
    private volatile /* synthetic */ int _state$volatile;
    private DisposableHandle cancelHandle;
    private final Thread targetThread;

    static {
        _state$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadState.class, "_state$volatile");
    }

    public ThreadState() {
        this.targetThread = Thread.currentThread();
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_state$volatile$FU() {
        return _state$volatile$FU;
    }

    private final Void invalidState(int r3) {
        throw new IllegalStateException(("Illegal state " + r3).toString());
    }

    public final void clearInterrupt() {
        AtomicIntegerFieldUpdater r0 = get_state$volatile$FU();
    L3:
        int r1 = r0.get(this);
        if (r1 != 0) goto L6;
        if (get_state$volatile$FU().compareAndSet(this, r1, 1) == false) goto L3;
        DisposableHandle r4 = this.cancelHandle;
        if (r4 == null) goto L26;
        r4.dispose();
        return;
    L26:
        return;
    L6:
        if (r1 == 2) goto L3;
        if (r1 != 3) goto L11;
        Thread.interrupted();
        return;
    L11:
        invalidState(r1);
        InstantKt$$ExternalSyntheticBUOutline0.m();
    }

    @Override // kotlinx.coroutines.JobNode
    public boolean getOnCancelling() {
        return true;
    }

    @Override // kotlinx.coroutines.JobNode
    public void invoke(Throwable r5) {
        AtomicIntegerFieldUpdater r52 = get_state$volatile$FU();
    L3:
        int r0 = r52.get(this);
        if (r0 != 0) goto L6;
        if (get_state$volatile$FU().compareAndSet(this, r0, 2) == false) goto L3;
        this.targetThread.interrupt();
        get_state$volatile$FU().set(this, 3);
        return;
    L6:
        if (r0 == 1) goto L19;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L21;
        invalidState(r0);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return;
    L21:
        return;
    L20:
        return;
    }

    public final void setup(Job r4) {
        this.cancelHandle = JobKt.invokeOnCompletion$default(r4, false, this, 1, null);
        AtomicIntegerFieldUpdater r42 = get_state$volatile$FU();
    L3:
        int r0 = r42.get(this);
        if (r0 != 0) goto L6;
        if (get_state$volatile$FU().compareAndSet(this, r0, 0) == false) goto L3;
        return;
    L6:
        if (r0 != 2) goto L8;
        return;
    L8:
        if (r0 == 3) goto L18;
        invalidState(r0);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return;
    }
}
