package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0011\u0010\u0012\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0016\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00000\u0014j\b\u0012\u0004\u0012\u00028\u0000`\u00150\u00138\u0002X\u0082\u0004¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueue;", _UrlKt.FRAGMENT_ENCODE_SET, "E", _UrlKt.FRAGMENT_ENCODE_SET, "singleConsumer", "<init>", "(Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "element", "addLast", "(Ljava/lang/Object;)Z", "removeFirstOrNull", "()Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "size", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "Lkotlinx/coroutines/internal/Core;", "_cur", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public class LockFreeTaskQueue<E> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _cur$volatile$FU = null;
    private volatile /* synthetic */ Object _cur$volatile;

    static {
        _cur$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueue.class, Object.class, "_cur$volatile");
    }

    public LockFreeTaskQueue(boolean r3) {
        this._cur$volatile = new LockFreeTaskQueueCore(8, r3);
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_cur$volatile$FU() {
        return _cur$volatile$FU;
    }

    public final boolean addLast(E r5) {
        AtomicReferenceFieldUpdater r0 = get_cur$volatile$FU();
    L3:
        LockFreeTaskQueueCore r1 = (LockFreeTaskQueueCore) r0.get(this);
        int r2 = r1.addLast(r5);
        if (r2 == 0) goto L12;
        if (r2 != 1) goto L7;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_cur$volatile$FU(), this, r1, r1.next());
        goto L3
    L7:
        if (r2 != 2) goto L3;
        return false;
    L12:
        return true;
    }

    public final void close() {
        AtomicReferenceFieldUpdater r0 = get_cur$volatile$FU();
    L3:
        LockFreeTaskQueueCore r1 = (LockFreeTaskQueueCore) r0.get(this);
        if (r1.close() == true) goto L5;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_cur$volatile$FU(), this, r1, r1.next());
        goto L3
    }

    public final int getSize() {
        return ((LockFreeTaskQueueCore) get_cur$volatile$FU().get(this)).getSize();
    }

    public final E removeFirstOrNull() {
        AtomicReferenceFieldUpdater r0 = get_cur$volatile$FU();
    L3:
        LockFreeTaskQueueCore r1 = (LockFreeTaskQueueCore) r0.get(this);
        E r2 = (E) r1.removeFirstOrNull();
        if (r2 != LockFreeTaskQueueCore.REMOVE_FROZEN) goto L5;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_cur$volatile$FU(), this, r1, r1.next());
        goto L3
    L5:
        return r2;
    }
}
