package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DebugStringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J \u0010\t\u001a\u00060\u0000j\u0002`\u00072\n\u0010\b\u001a\u00060\u0000j\u0002`\u0007H\u0082\u0010¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\f2\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u0007H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u000f\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0007H\u0082\u0010¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0013\u001a\u00020\u00122\n\u0010\u0011\u001a\u00060\u0000j\u0002`\u0007¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0017\u001a\u00020\u00122\n\u0010\u0011\u001a\u00060\u0000j\u0002`\u00072\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001c\u001a\u00020\u00122\n\u0010\u0011\u001a\u00060\u0000j\u0002`\u00072\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u0007H\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0007H\u0001¢\u0006\u0004\b \u0010\u0010J\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0015\u0010(\u001a\u00060\u0000j\u0002`\u00078F¢\u0006\u0006\u001a\u0004\b'\u0010\u0010R\u0015\u0010*\u001a\u00060\u0000j\u0002`\u00078F¢\u0006\u0006\u001a\u0004\b)\u0010\u0010R\u0011\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00010+8\u0002X\u0082\u0004R\u0011\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00000+8\u0002X\u0082\u0004R\u0013\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040+8\u0002X\u0082\u0004¨\u0006/"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lkotlinx/coroutines/internal/Removed;", "removed", "()Lkotlinx/coroutines/internal/Removed;", "Lkotlinx/coroutines/internal/Node;", "current", "findPrevNonRemoved", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", _UrlKt.FRAGMENT_ENCODE_SET, "finishAdd", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "correctPrev", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "node", _UrlKt.FRAGMENT_ENCODE_SET, "addOneIfEmpty", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "permissionsBitmask", "addLast", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;I)Z", "forbiddenElementsBit", "close", "(I)V", "addNext", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Z", "remove", "()Z", "removeOrNext", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "isRemoved", "getNext", "()Ljava/lang/Object;", "getNextNode", "nextNode", "getPrevNode", "prevNode", "Lkotlinx/atomicfu/AtomicRef;", "_next", "_prev", "_removedRef", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLockFreeLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeLinkedList.kt\nkotlinx/coroutines/internal/LockFreeLinkedListNode\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,288:1\n1#2:289\n*E\n"})
/* loaded from: classes.dex */
public class LockFreeLinkedListNode {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _prev$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _removedRef$volatile$FU = null;
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ Object _prev$volatile;
    private volatile /* synthetic */ Object _removedRef$volatile;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class AnonymousClass1 extends PropertyReference0Impl {
        public AnonymousClass1(Object r7) {
            super(r7, DebugStringsKt.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1);
        }

        @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
        public Object get() {
            return DebugStringsKt.getClassSimpleName(this.receiver);
        }
    }

    static {
        _next$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next$volatile");
        _prev$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev$volatile");
        _removedRef$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef$volatile");
    }

    public LockFreeLinkedListNode() {
        this._next$volatile = this;
        this._prev$volatile = this;
    }

    private final LockFreeLinkedListNode correctPrev() {
    L2:
        LockFreeLinkedListNode r0 = (LockFreeLinkedListNode) get_prev$volatile$FU().get(this);
        LockFreeLinkedListNode r2 = r0;
    L3:
        LockFreeLinkedListNode r3 = null;
    L4:
        Object r4 = get_next$volatile$FU().get(r2);
        if (r4 == this) goto L6;
        if (isRemoved() == true) goto L14;
        if ((r4 instanceof Removed) == true) goto L17;
        r3 = r2;
        r2 = (LockFreeLinkedListNode) r4;
        goto L4
    L17:
        if (r3 != null) goto L19;
        r2 = (LockFreeLinkedListNode) get_prev$volatile$FU().get(r2);
        goto L4
    L19:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_next$volatile$FU(), r3, r2, ((Removed) r4).ref) == false) goto L2;
        r2 = r3;
        goto L3
    L14:
        return null;
    L6:
        if (r0 == r2) goto L11;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_prev$volatile$FU(), this, r0, r2) == false) goto L2;
    L11:
        return r2;
    }

    private final LockFreeLinkedListNode findPrevNonRemoved(LockFreeLinkedListNode r1) {
    L3:
        if (r1.isRemoved() == false) goto L4;
        r1 = (LockFreeLinkedListNode) get_prev$volatile$FU().get(r1);
        goto L3
    L4:
        return r1;
    }

    private final void finishAdd(LockFreeLinkedListNode r4) {
        AtomicReferenceFieldUpdater r0 = get_prev$volatile$FU();
    L3:
        LockFreeLinkedListNode r1 = (LockFreeLinkedListNode) r0.get(r4);
        if (getNext() != r4) goto L16;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_prev$volatile$FU(), r4, r1, this) == false) goto L3;
        if (isRemoved() == false) goto L15;
        r4.correctPrev();
        return;
    L15:
        return;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_next$volatile$FU() {
        return _next$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_prev$volatile$FU() {
        return _prev$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_removedRef$volatile$FU() {
        return _removedRef$volatile$FU;
    }

    private final Removed removed() {
        Removed r0 = (Removed) get_removedRef$volatile$FU().get(this);
        if (r0 != null) goto L6;
        Removed r02 = new Removed(this);
        get_removedRef$volatile$FU().set(this, r02);
        return r02;
    L6:
        return r0;
    }

    public final boolean addLast(LockFreeLinkedListNode r4, int r5) {
    L2:
        LockFreeLinkedListNode r0 = getPrevNode();
        if ((r0 instanceof ListClosed) == true) goto L5;
        if (r0.addNext(r4, this) == false) goto L2;
        return true;
    L5:
        if ((((ListClosed) r0).forbiddenElementsBitmask & r5) == 0) goto L7;
        return false;
    L7:
        if (r0.addLast(r4, r5) == false) goto L17;
        return true;
    L17:
        return false;
    }

    @PublishedApi
    public final boolean addNext(LockFreeLinkedListNode r2, LockFreeLinkedListNode r3) {
        get_prev$volatile$FU().set(r2, this);
        get_next$volatile$FU().set(r2, r3);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_next$volatile$FU(), this, r3, r2) == true) goto L6;
        return false;
    L6:
        r2.finishAdd(r3);
        return true;
    }

    public final boolean addOneIfEmpty(LockFreeLinkedListNode r2) {
        get_prev$volatile$FU().set(r2, this);
        get_next$volatile$FU().set(r2, this);
    L4:
        if (getNext() != this) goto L5;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_next$volatile$FU(), this, this, r2) == false) goto L4;
        r2.finishAdd(this);
        return true;
    L5:
        return false;
    }

    public final void close(int r2) {
        addLast(new ListClosed(r2), r2);
    }

    public final Object getNext() {
        return get_next$volatile$FU().get(this);
    }

    public final LockFreeLinkedListNode getNextNode() {
        Object r1 = getNext();
        if ((r1 instanceof Removed) == false) goto L5;
        Removed r0 = (Removed) r1;
    L6:
        if (r0 == null) goto L12;
        LockFreeLinkedListNode r02 = r0.ref;
        if (r02 == null) goto L12;
        return r02;
    L12:
        return (LockFreeLinkedListNode) r1;
    L5:
        r0 = null;
        goto L6
    }

    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode r0 = correctPrev();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return findPrevNonRemoved((LockFreeLinkedListNode) get_prev$volatile$FU().get(this));
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public boolean remove() {
        if (removeOrNext() != null) goto L6;
        return true;
    L6:
        return false;
    }

    @PublishedApi
    public final LockFreeLinkedListNode removeOrNext() {
    L2:
        Object r0 = getNext();
        if ((r0 instanceof Removed) == true) goto L5;
        if (r0 == this) goto L8;
        LockFreeLinkedListNode r1 = (LockFreeLinkedListNode) r0;
        Removed r2 = r1.removed();
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_next$volatile$FU(), this, r0, r2) == false) goto L2;
        r1.correctPrev();
        return null;
    L8:
        return (LockFreeLinkedListNode) r0;
    L5:
        return ((Removed) r0).ref;
    }

    public String toString() {
        return new AnonymousClass1(this) + '@' + DebugStringsKt.getHexAddress(this);
    }
}
