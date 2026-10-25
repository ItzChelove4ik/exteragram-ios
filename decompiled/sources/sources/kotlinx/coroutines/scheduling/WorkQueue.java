package kotlinx.coroutines.scheduling;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.internal.measurement.zzpo$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002J\"\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u0005j\u0002`\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\f2\u0006\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u0004\u0018\u00010\fJ\u0012\u0010\"\u001a\u0004\u0018\u00010\f2\u0006\u0010 \u001a\u00020\u0017H\u0002J\u001a\u0010#\u001a\u0004\u0018\u00010\f2\u0006\u0010$\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0017H\u0002J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J$\u0010)\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u0005j\u0002`\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001eH\u0002J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020(H\u0002J\n\u0010,\u001a\u0004\u0018\u00010\fH\u0002J\u000e\u0010-\u001a\u00020&*\u0004\u0018\u00010\fH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000eX\u0082\u0004R\t\u0010\u000f\u001a\u00020\u0010X\u0082\u0004R\t\u0010\u0011\u001a\u00020\u0010X\u0082\u0004R\t\u0010\u0012\u001a\u00020\u0010X\u0082\u0004¨\u0006."}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "bufferSize", _UrlKt.FRAGMENT_ENCODE_SET, "getBufferSize", "()I", "size", "getSize$kotlinx_coroutines_core", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Lkotlinx/coroutines/scheduling/Task;", "lastScheduledTask", "Lkotlinx/atomicfu/AtomicRef;", "producerIndex", "Lkotlinx/atomicfu/AtomicInt;", "consumerIndex", "blockingTasksInBuffer", "poll", "add", "task", "fair", _UrlKt.FRAGMENT_ENCODE_SET, "addLast", "trySteal", _UrlKt.FRAGMENT_ENCODE_SET, "stealingMode", "Lkotlinx/coroutines/scheduling/StealingMode;", "stolenTaskRef", "Lkotlin/jvm/internal/Ref$ObjectRef;", "stealWithExclusiveMode", "onlyBlocking", "pollBlocking", "pollWithExclusiveMode", "tryExtractFromTheMiddle", "index", "offloadAllWorkTo", _UrlKt.FRAGMENT_ENCODE_SET, "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "tryStealLastScheduled", "pollTo", "queue", "pollBuffer", "decrementIfBlocking", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueue\n+ 2 Tasks.kt\nkotlinx/coroutines/scheduling/TasksKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueueKt\n*L\n1#1,246:1\n77#2:247\n77#2:248\n77#2:249\n77#2:252\n77#2:253\n1#3:250\n21#4:251\n*S KotlinDebug\n*F\n+ 1 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueue\n*L\n91#1:247\n153#1:248\n176#1:249\n196#1:252\n240#1:253\n196#1:251\n*E\n"})
/* loaded from: classes.dex */
public final class WorkQueue {
    private static final /* synthetic */ AtomicIntegerFieldUpdater blockingTasksInBuffer$volatile$FU = null;
    private static final /* synthetic */ AtomicIntegerFieldUpdater consumerIndex$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater lastScheduledTask$volatile$FU = null;
    private static final /* synthetic */ AtomicIntegerFieldUpdater producerIndex$volatile$FU = null;
    private volatile /* synthetic */ int blockingTasksInBuffer$volatile;
    private final AtomicReferenceArray<Task> buffer;
    private volatile /* synthetic */ int consumerIndex$volatile;
    private volatile /* synthetic */ Object lastScheduledTask$volatile;
    private volatile /* synthetic */ int producerIndex$volatile;

    static {
        lastScheduledTask$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask$volatile");
        producerIndex$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex$volatile");
        consumerIndex$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex$volatile");
        blockingTasksInBuffer$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer$volatile");
    }

    public WorkQueue() {
        this.buffer = new AtomicReferenceArray(128);
    }

    private final Task addLast(Task r3) {
        if (getBufferSize() != 127) goto L6;
        return r3;
    L6:
        if (r3.taskContext == false) goto L8;
        getBlockingTasksInBuffer$volatile$FU().incrementAndGet(this);
    L8:
        int r0 = getProducerIndex$volatile$FU().get(this) & 127;
    L10:
        if (this.buffer.get(r0) == null) goto L12;
        Thread.yield();
        goto L10
    L12:
        this.buffer.lazySet(r0, r3);
        getProducerIndex$volatile$FU().incrementAndGet(this);
        return null;
    }

    private final void decrementIfBlocking(Task r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.taskContext == false) goto L8;
        getBlockingTasksInBuffer$volatile$FU().decrementAndGet(this);
        return;
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater getBlockingTasksInBuffer$volatile$FU() {
        return blockingTasksInBuffer$volatile$FU;
    }

    private final int getBufferSize() {
        return getProducerIndex$volatile$FU().get(this) - getConsumerIndex$volatile$FU().get(this);
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater getConsumerIndex$volatile$FU() {
        return consumerIndex$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getLastScheduledTask$volatile$FU() {
        return lastScheduledTask$volatile$FU;
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater getProducerIndex$volatile$FU() {
        return producerIndex$volatile$FU;
    }

    private final Task pollBuffer() {
    L2:
        int r0 = getConsumerIndex$volatile$FU().get(this);
        if ((r0 - getProducerIndex$volatile$FU().get(this)) == 0) goto L4;
        int r1 = r0 & 127;
        if (getConsumerIndex$volatile$FU().compareAndSet(this, r0, r0 + 1) == false) goto L2;
        Task r02 = this.buffer.getAndSet(r1, null);
        if (r02 == null) goto L2;
        decrementIfBlocking(r02);
        return r02;
    L4:
        return null;
    }

    private final boolean pollTo(GlobalQueue r1) {
        Task r0 = pollBuffer();
        if (r0 != null) goto L6;
        return false;
    L6:
        r1.addLast(r0);
        return true;
    }

    private final Task pollWithExclusiveMode(boolean r5) {
    L2:
        Task r0 = (Task) getLastScheduledTask$volatile$FU().get(this);
        if (r0 == null) goto L10;
        if (r0.taskContext != r5) goto L10;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getLastScheduledTask$volatile$FU(), this, r0, null) == false) goto L2;
        return r0;
    L10:
        int r02 = getConsumerIndex$volatile$FU().get(this);
        int r2 = getProducerIndex$volatile$FU().get(this);
    L11:
        if (r02 == r2) goto L19;
        if (r5 == true) goto L14;
    L16:
        r2 = r2 - 1;
        Task r3 = tryExtractFromTheMiddle(r2, r5);
        if (r3 == null) goto L11;
        return r3;
    L14:
        if (getBlockingTasksInBuffer$volatile$FU().get(this) != 0) goto L16;
        return null;
    L19:
        return null;
    }

    private final Task stealWithExclusiveMode(boolean r5) {
        int r0 = getConsumerIndex$volatile$FU().get(this);
        int r1 = getProducerIndex$volatile$FU().get(this);
    L4:
        if (r0 == r1) goto L13;
        if (r5 == true) goto L7;
    L9:
        int r2 = r0 + 1;
        Task r02 = tryExtractFromTheMiddle(r0, r5);
        if (r02 != null) goto L12;
        r0 = r2;
        goto L4
    L12:
        return r02;
    L7:
        if (getBlockingTasksInBuffer$volatile$FU().get(this) != 0) goto L9;
        return null;
    L13:
        return null;
    }

    private final Task tryExtractFromTheMiddle(int r4, boolean r5) {
        int r42 = r4 & 127;
        Task r0 = this.buffer.get(r42);
        if (r0 != null) goto L5;
    L11:
        return null;
    L5:
        if (r0.taskContext != r5) goto L11;
        if (zzpo$$ExternalSyntheticBackportWithForwarding0.m(this.buffer, r42, r0, null) == false) goto L11;
        if (r5 == false) goto L10;
        getBlockingTasksInBuffer$volatile$FU().decrementAndGet(this);
    L10:
        return r0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object, kotlinx.coroutines.scheduling.Task] */
    private final long tryStealLastScheduled(int r7, Ref.ObjectRef<Task> r8) {
    L2:
        ?? r0 = (Task) getLastScheduledTask$volatile$FU().get(this);
        if (r0 == 0) goto L4;
        if (r0.taskContext == false) goto L8;
        int r3 = 1;
    L10:
        if ((r3 & r7) == 0) goto L11;
        long r1 = TasksKt.schedulerTimeSource.nanoTime() - r0.submissionTime;
        long r32 = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
        if (r1 < r32) goto L15;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getLastScheduledTask$volatile$FU(), this, r0, null) == false) goto L2;
        r8.element = r0;
        return -1;
    L15:
        return r32 - r1;
    L11:
        return -2;
    L8:
        r3 = 2;
        goto L10
    L4:
        return -2;
    }

    public final Task add(Task r1, boolean r2) {
        if (r2 == true) goto L4;
        Task r12 = (Task) getLastScheduledTask$volatile$FU().getAndSet(this, r1);
        if (r12 != null) goto L10;
        return null;
    L10:
        return addLast(r12);
    L4:
        return addLast(r1);
    }

    public final int getSize$kotlinx_coroutines_core() {
        Object r0 = getLastScheduledTask$volatile$FU().get(this);
        int r1 = getBufferSize();
        if (r0 != null) goto L5;
        return r1;
    L5:
        return r1 + 1;
    }

    public final void offloadAllWorkTo(GlobalQueue r3) {
        Task r0 = (Task) getLastScheduledTask$volatile$FU().getAndSet(this, null);
        if (r0 == null) goto L6;
        r3.addLast(r0);
    L6:
        if (pollTo(r3) == true) goto L6;
    }

    public final Task poll() {
        Task r0 = (Task) getLastScheduledTask$volatile$FU().getAndSet(this, null);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return pollBuffer();
    }

    public final Task pollBlocking() {
        return pollWithExclusiveMode(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long trySteal(int r2, Ref.ObjectRef<Task> r3) {
        if (r2 != 3) goto L5;
        T r0 = pollBuffer();
    L10:
        if (r0 == 0) goto L14;
        r3.element = r0;
        return -1;
    L14:
        return tryStealLastScheduled(r2, r3);
    L5:
        boolean r02 = true;
        if (r2 == 1) goto L9;
        r02 = false;
    L9:
        r0 = stealWithExclusiveMode(r02);
        goto L10
    }
}
