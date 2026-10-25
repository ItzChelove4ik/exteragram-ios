package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.NotCompleted;
import kotlinx.coroutines.internal.Segment;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u0013\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0016J\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0006\u0010\u001e\u001a\u00020\u0018R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\t\u0010\u000e\u001a\u00020\u000fX\u0082\u0004R\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "Lkotlinx/coroutines/NotCompleted;", "id", _UrlKt.FRAGMENT_ENCODE_SET, "prev", "pointers", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JLkotlinx/coroutines/internal/Segment;I)V", "numberOfSlots", "getNumberOfSlots", "()I", "cleanedAndPointers", "Lkotlinx/atomicfu/AtomicInt;", "isRemoved", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "tryIncPointers", "tryIncPointers$kotlinx_coroutines_core", "decPointers", "decPointers$kotlinx_coroutines_core", "onCancellation", _UrlKt.FRAGMENT_ENCODE_SET, "index", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Lkotlin/coroutines/CoroutineContext;", "onSlotCleaned", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConcurrentLinkedList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/Segment\n+ 2 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListKt\n*L\n1#1,265:1\n248#2,4:266\n*S KotlinDebug\n*F\n+ 1 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/Segment\n*L\n221#1:266,4\n*E\n"})
/* loaded from: classes.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> implements NotCompleted {
    private static final /* synthetic */ AtomicIntegerFieldUpdater cleanedAndPointers$volatile$FU = null;
    private volatile /* synthetic */ int cleanedAndPointers$volatile;

    @JvmField
    public final long id;

    static {
        cleanedAndPointers$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers$volatile");
    }

    public Segment(long r1, S r3, int r4) {
        super(r3);
        this.id = r1;
        this.cleanedAndPointers$volatile = r4 << 16;
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater getCleanedAndPointers$volatile$FU() {
        return cleanedAndPointers$volatile$FU;
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        if (getCleanedAndPointers$volatile$FU().addAndGet(this, Opcodes.V_PREVIEW) == getNumberOfSlots()) goto L5;
        return false;
    L5:
        if (isTail() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public abstract int getNumberOfSlots();

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean isRemoved() {
        if (getCleanedAndPointers$volatile$FU().get(this) == getNumberOfSlots()) goto L5;
        return false;
    L5:
        if (isTail() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public abstract void onCancellation(int r1, Throwable r2, CoroutineContext r3);

    public final void onSlotCleaned() {
        if (getCleanedAndPointers$volatile$FU().incrementAndGet(this) != getNumberOfSlots()) goto L6;
        remove();
        return;
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        AtomicIntegerFieldUpdater r0 = getCleanedAndPointers$volatile$FU();
    L3:
        int r1 = r0.get(this);
        if (r1 != getNumberOfSlots()) goto L11;
        if (isTail() == true) goto L11;
        return false;
    L11:
        if (r0.compareAndSet(this, r1, 65536 + r1) == false) goto L3;
        return true;
    }
}
