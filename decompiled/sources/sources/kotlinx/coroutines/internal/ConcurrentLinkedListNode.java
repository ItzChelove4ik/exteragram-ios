package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u0002B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0007¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\fR\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0015\u001a\u0004\u0018\u00018\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0017\u001a\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0019\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u001a\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000eR\u0013\u0010\u0003\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0014R\u0014\u0010\u001c\u001a\u00020\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000eR\u0013\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001d8\u0002X\u0082\u0004R\u0013\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u001d8\u0002X\u0082\u0004¨\u0006 "}, d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", _UrlKt.FRAGMENT_ENCODE_SET, "prev", "<init>", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "value", _UrlKt.FRAGMENT_ENCODE_SET, "trySetNext", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "cleanPrev", "()V", "markAsClosed", "()Z", "remove", "getNextOrClosed", "()Ljava/lang/Object;", "nextOrClosed", "getAliveSegmentLeft", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "aliveSegmentLeft", "getAliveSegmentRight", "aliveSegmentRight", "getNext", "next", "isTail", "getPrev", "isRemoved", "Lkotlinx/atomicfu/AtomicRef;", "_next", "_prev", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConcurrentLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,265:1\n103#1,7:266\n1#2:273\n*S KotlinDebug\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListNode\n*L\n111#1:266,7\n*E\n"})
/* loaded from: classes.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _prev$volatile$FU = null;
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ Object _prev$volatile;

    static {
        _next$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next$volatile");
        _prev$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev$volatile");
    }

    public ConcurrentLinkedListNode(N r1) {
        this._prev$volatile = r1;
    }

    public static final /* synthetic */ Object access$getNextOrClosed(ConcurrentLinkedListNode r0) {
        return r0.getNextOrClosed();
    }

    private final N getAliveSegmentLeft() {
        N r1 = (N) getPrev();
    L3:
        if (r1 == null) goto L7;
        if (r1.isRemoved() == false) goto L7;
        r1 = (N) get_prev$volatile$FU().get(r1);
    L7:
        return r1;
    }

    private final N getAliveSegmentRight() {
        N r1 = (N) getNext();
    L4:
        if (r1.isRemoved() == false) goto L9;
        ConcurrentLinkedListNode r0 = r1.getNext();
        if (r0 == null) goto L9;
        r1 = (N) r0;
    L9:
        return r1;
    }

    private final Object getNextOrClosed() {
        return get_next$volatile$FU().get(this);
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_next$volatile$FU() {
        return _next$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_prev$volatile$FU() {
        return _prev$volatile$FU;
    }

    public final void cleanPrev() {
        get_prev$volatile$FU().set(this, null);
    }

    public final N getNext() {
        Object r1 = access$getNextOrClosed(this);
        if (r1 != ConcurrentLinkedListKt.access$getCLOSED$p()) goto L7;
        return null;
    L7:
        return (N) r1;
    }

    public final N getPrev() {
        return (N) get_prev$volatile$FU().get(this);
    }

    public abstract boolean isRemoved();

    public final boolean isTail() {
        if (getNext() != null) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean markAsClosed() {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_next$volatile$FU(), this, null, ConcurrentLinkedListKt.access$getCLOSED$p());
    }

    public final void remove() {
        if (isTail() == true) goto L21;
    L5:
        ConcurrentLinkedListNode r0 = getAliveSegmentLeft();
        ConcurrentLinkedListNode r1 = getAliveSegmentRight();
        AtomicReferenceFieldUpdater r2 = get_prev$volatile$FU();
    L6:
        Object r3 = r2.get(r1);
        if (((ConcurrentLinkedListNode) r3) != null) goto L9;
        ConcurrentLinkedListNode r4 = null;
    L11:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r2, r1, r3, r4) == false) goto L6;
        if (r0 == null) goto L15;
        get_next$volatile$FU().set(r0, r1);
    L15:
        if (r1.isRemoved() == false) goto L18;
        if (r1.isTail() == false) goto L5;
    L18:
        if (r0 == null) goto L31;
        if (r0.isRemoved() == true) goto L5;
        return;
    L31:
        return;
    L9:
        r4 = r0;
        goto L11
    }

    public final boolean trySetNext(N r3) {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_next$volatile$FU(), this, null, r3);
    }
}
