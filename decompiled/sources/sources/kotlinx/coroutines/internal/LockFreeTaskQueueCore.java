package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 -*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002.-B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ3\u0010\f\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\f\u0010\rJ3\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000b2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u000b2\u0006\u0010\u0015\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u000b2\u0006\u0010\u0015\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0018\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010!R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\"R\u0014\u0010#\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010!R\u0011\u0010$\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b$\u0010\u001aR\u0011\u0010'\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b%\u0010&R%\u0010)\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000b0(8\u0002X\u0082\u0004R\u000b\u0010+\u001a\u00020*8\u0002X\u0082\u0004R\u0013\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00018\u0002X\u0082\u0004¨\u0006/"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", _UrlKt.FRAGMENT_ENCODE_SET, "E", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "singleConsumer", "<init>", "(IZ)V", "index", "element", "Lkotlinx/coroutines/internal/Core;", "fillPlaceholder", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "oldHead", "newHead", "removeSlowPath", "(II)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", _UrlKt.FRAGMENT_ENCODE_SET, "markFrozen", "()J", "state", "allocateOrGetNextCopy", "(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "allocateNextCopy", "close", "()Z", "addLast", "(Ljava/lang/Object;)I", "removeFirstOrNull", "()Ljava/lang/Object;", "next", "()Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "I", "Z", PluginsConstants.Settings.MASK, "isEmpty", "getSize", "()I", "size", "Lkotlinx/atomicfu/AtomicRef;", "_next", "Lkotlinx/atomicfu/AtomicLong;", "_state", "array", "Companion", "Placeholder", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLockFreeTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n+ 2 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,304:1\n295#2,3:305\n295#2,3:308\n295#2,3:311\n295#2,3:314\n295#2,3:317\n295#2,3:321\n295#2,3:324\n1#3:320\n*S KotlinDebug\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n*L\n87#1:305,3\n88#1:308,3\n103#1:311,3\n163#1:314,3\n196#1:317,3\n227#1:321,3\n243#1:324,3\n*E\n"})
/* loaded from: classes.dex */
public final class LockFreeTaskQueueCore<E> {
    public static final Companion Companion = null;

    @JvmField
    public static final Symbol REMOVE_FROZEN = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater _state$volatile$FU = null;
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ long _state$volatile;
    private final /* synthetic */ AtomicReferenceArray array;
    private final int capacity;
    private final int mask;
    private final boolean singleConsumer;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\r\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000bJ\u0011\u0010\u000e\u001a\u00020\b*\u00020\u0004¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0011R\u0014\u0010\u0018\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0011R\u0014\u0010\u001a\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001b\u0010\u0011R\u0014\u0010\u001c\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001c\u0010\u0016R\u0014\u0010\u001d\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001d\u0010\u0011R\u0014\u0010\u001f\u001a\u00020\u001e8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b!\u0010\u0011R\u0014\u0010\"\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\"\u0010\u0011R\u0014\u0010#\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b#\u0010\u0011¨\u0006$"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "other", "wo", "(JJ)J", _UrlKt.FRAGMENT_ENCODE_SET, "newHead", "updateHead", "(JI)J", "newTail", "updateTail", "addFailReason", "(J)I", "INITIAL_CAPACITY", "I", "CAPACITY_BITS", "MAX_CAPACITY_MASK", "HEAD_SHIFT", "HEAD_MASK", "J", "TAIL_SHIFT", "TAIL_MASK", "FROZEN_SHIFT", "FROZEN_MASK", "CLOSED_SHIFT", "CLOSED_MASK", "MIN_ADD_SPIN_CAPACITY", "Lkotlinx/coroutines/internal/Symbol;", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "ADD_SUCCESS", "ADD_FROZEN", "ADD_CLOSED", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final int addFailReason(long r3) {
            if ((r3 & 2305843009213693952L) == 0) goto L6;
            return 2;
        L6:
            return 1;
        }

        public final long updateHead(long r3, int r5) {
            return wo(r3, 1073741823) | r5;
        }

        public final long updateTail(long r3, int r5) {
            return wo(r3, 1152921503533105152L) | (r5 << 30);
        }

        public final long wo(long r1, long r3) {
            return r1 & (~r3);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Placeholder {

        @JvmField
        public final int index;

        public Placeholder(int r1) {
            this.index = r1;
        }
    }

    static {
        Companion = new Companion(null);
        _next$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next$volatile");
        _state$volatile$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state$volatile");
        REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    }

    public LockFreeTaskQueueCore(int r2, boolean r3) {
        this.capacity = r2;
        this.singleConsumer = r3;
        int r32 = r2 - 1;
        this.mask = r32;
        this.array = new AtomicReferenceArray(r2);
        if (r32 <= 1073741823) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        throw null;
    L5:
        if ((r2 & r32) != 0) goto L7;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final LockFreeTaskQueueCore<E> allocateNextCopy(long r7) {
        LockFreeTaskQueueCore<E> r0 = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
        int r1 = (int) (1073741823 & r7);
        int r2 = (int) ((1152921503533105152L & r7) >> 30);
    L3:
        int r3 = this.mask;
        if ((r1 & r3) == (r3 & r2)) goto L9;
        Object r32 = getArray().get(this.mask & r1);
        if (r32 != null) goto L8;
        r32 = new Placeholder(r1);
    L8:
        r0.getArray().set(r0.mask & r1, r32);
        r1 = r1 + 1;
        goto L3
    L9:
        get_state$volatile$FU().set(r0, Companion.wo(r7, 1152921504606846976L));
        return r0;
    }

    private final LockFreeTaskQueueCore<E> allocateOrGetNextCopy(long r5) {
        AtomicReferenceFieldUpdater r0 = get_next$volatile$FU();
    L3:
        LockFreeTaskQueueCore<E> r1 = (LockFreeTaskQueueCore) r0.get(this);
        if (r1 != null) goto L5;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_next$volatile$FU(), this, null, allocateNextCopy(r5));
        goto L3
    L5:
        return r1;
    }

    private final LockFreeTaskQueueCore<E> fillPlaceholder(int r3, E r4) {
        Object r0 = getArray().get(this.mask & r3);
        if ((r0 instanceof Placeholder) == true) goto L5;
        return null;
    L5:
        if (((Placeholder) r0).index != r3) goto L10;
        getArray().set(r3 & this.mask, r4);
        return this;
    L10:
        return null;
    }

    private final /* synthetic */ AtomicReferenceArray getArray() {
        return this.array;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_next$volatile$FU() {
        return _next$volatile$FU;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater get_state$volatile$FU() {
        return _state$volatile$FU;
    }

    private final long markFrozen() {
        AtomicLongFieldUpdater r0 = get_state$volatile$FU();
    L3:
        long r2 = r0.get(this);
        if ((r2 & 1152921504606846976L) != 0) goto L5;
        long r4 = 1152921504606846976L | r2;
        LockFreeTaskQueueCore<E> r1 = this;
        if (r0.compareAndSet(r1, r2, r4) == true) goto L8;
        LockFreeTaskQueueCore<E> this = r1;
        goto L3
    L8:
        return r4;
    L5:
        return r2;
    }

    private final LockFreeTaskQueueCore<E> removeSlowPath(int r8, int r9) {
        AtomicLongFieldUpdater r82 = get_state$volatile$FU();
    L3:
        long r2 = r82.get(this);
        int r6 = (int) (1073741823 & r2);
        if ((1152921504606846976L & r2) != 0) goto L6;
        AtomicLongFieldUpdater r0 = get_state$volatile$FU();
        long r4 = Companion.updateHead(r2, r9);
        LockFreeTaskQueueCore<E> r1 = this;
        if (r0.compareAndSet(r1, r2, r4) == true) goto L9;
        LockFreeTaskQueueCore<E> this = r1;
        goto L3
    L9:
        r1.getArray().set(r1.mask & r6, null);
        return null;
    L6:
        return this.next();
    }

    public final int addLast(E r13) {
        AtomicLongFieldUpdater r0 = get_state$volatile$FU();
    L3:
        long r3 = r0.get(this);
        if ((3458764513820540928L & r3) != 0) goto L6;
        int r1 = (int) (1073741823 & r3);
        int r9 = (int) ((1152921503533105152L & r3) >> 30);
        int r10 = this.mask;
        if (((r9 + 2) & r10) == (r1 & r10)) goto L9;
        if (this.singleConsumer == true) goto L19;
        if (this.getArray().get(r9 & r10) == null) goto L19;
        int r2 = this.capacity;
        if (r2 < 1024) goto L18;
        if (((r9 - r1) & 1073741823) <= (r2 >> 1)) goto L3;
    L18:
        return 1;
    L19:
        int r12 = (r9 + 1) & 1073741823;
        LockFreeTaskQueueCore<E> r22 = this;
        if (get_state$volatile$FU().compareAndSet(r22, r3, Companion.updateTail(r3, r12)) == true) goto L21;
        LockFreeTaskQueueCore<E> this = r22;
        goto L3
    L21:
        r22.getArray().set(r9 & r10, r13);
        LockFreeTaskQueueCore<E> r122 = r22;
    L23:
        if ((get_state$volatile$FU().get(r122) & 1152921504606846976L) == 0) goto L26;
        r122 = r122.next().fillPlaceholder(r9, r13);
        if (r122 != null) goto L23;
        return 0;
    L26:
        return 0;
    L9:
        return 1;
    L6:
        return Companion.addFailReason(r3);
    }

    public final boolean close() {
        AtomicLongFieldUpdater r0 = get_state$volatile$FU();
    L3:
        long r2 = r0.get(this);
        if ((r2 & 2305843009213693952L) != 0) goto L5;
        if ((1152921504606846976L & r2) != 0) goto L8;
        LockFreeTaskQueueCore<E> r1 = this;
        if (r0.compareAndSet(r1, r2, 2305843009213693952L | r2) == true) goto L12;
        LockFreeTaskQueueCore<E> this = r1;
        goto L3
    L12:
        return true;
    L8:
        return false;
    L5:
        return true;
    }

    public final int getSize() {
        long r0 = get_state$volatile$FU().get(this);
        int r4 = (int) (1073741823 & r0);
        return 1073741823 & (((int) ((r0 & 1152921503533105152L) >> 30)) - r4);
    }

    public final boolean isEmpty() {
        long r0 = get_state$volatile$FU().get(this);
        if (((int) (1073741823 & r0)) != ((int) ((r0 & 1152921503533105152L) >> 30))) goto L6;
        return true;
    L6:
        return false;
    }

    public final LockFreeTaskQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    public final Object removeFirstOrNull() {
        AtomicLongFieldUpdater r0 = get_state$volatile$FU();
    L3:
        long r3 = r0.get(this);
        if ((1152921504606846976L & r3) != 0) goto L6;
        int r7 = (int) (1073741823 & r3);
        int r2 = this.mask;
        if ((((int) ((1152921503533105152L & r3) >> 30)) & r2) == (r2 & r7)) goto L9;
        Object r9 = this.getArray().get(this.mask & r7);
        if (r9 == null) goto L13;
        if ((r9 instanceof Placeholder) == true) goto L17;
        int r10 = (r7 + 1) & 1073741823;
        AtomicLongFieldUpdater r1 = get_state$volatile$FU();
        long r5 = Companion.updateHead(r3, r10);
        LockFreeTaskQueueCore<E> r22 = this;
        if (r1.compareAndSet(r22, r3, r5) == true) goto L20;
        if (r22.singleConsumer == true) goto L24;
        LockFreeTaskQueueCore<E> this = r22;
        goto L3
    L24:
        LockFreeTaskQueueCore<E> r11 = r22;
    L25:
        r11 = r11.removeSlowPath(r7, r10);
        if (r11 != null) goto L25;
        return r9;
    L20:
        r22.getArray().set(r22.mask & r7, null);
        return r9;
    L17:
        return null;
    L13:
        if (this.singleConsumer == false) goto L3;
        return null;
    L9:
        return null;
    L6:
        return REMOVE_FROZEN;
    }
}
