package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b \u0018\u00002\u00020\u00012\u00020\u0002:\u0004:;<=B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001cH\u0016J!\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u00142\n\u0010\u001f\u001a\u00060 j\u0002`!H\u0004¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\u0014H\u0016J\u001f\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&2\n\u0010\u001f\u001a\u00060 j\u0002`!¢\u0006\u0002\u0010'J\u0019\u0010(\u001a\u00020\u00182\n\u0010)\u001a\u00060 j\u0002`!H\u0016¢\u0006\u0002\u0010*J\u0019\u0010+\u001a\u00020\r2\n\u0010)\u001a\u00060 j\u0002`!H\u0002¢\u0006\u0002\u0010,J\u0015\u0010-\u001a\n\u0018\u00010 j\u0004\u0018\u0001`!H\u0002¢\u0006\u0002\u0010.J\b\u0010/\u001a\u00020\u0018H\u0002J\b\u00100\u001a\u00020\u0018H\u0002J\u0016\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u000204J\u0010\u00105\u001a\u00020\r2\u0006\u0010)\u001a\u000204H\u0002J\u0018\u00106\u001a\u0002072\u0006\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u000204H\u0002J\b\u00108\u001a\u00020\u0018H\u0004J\b\u00109\u001a\u00020\u0018H\u0002R\u0011\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004R\u0011\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004R\t\u0010\n\u001a\u00020\u000bX\u0082\u0004R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00148TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006>"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", "Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/Delay;", "<init>", "()V", "_queue", "Lkotlinx/atomicfu/AtomicRef;", _UrlKt.FRAGMENT_ENCODE_SET, "_delayed", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "_isCompleted", "Lkotlinx/atomicfu/AtomicBoolean;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "isCompleted", "()Z", "setCompleted", "(Z)V", "isEmpty", "nextTime", _UrlKt.FRAGMENT_ENCODE_SET, "getNextTime", "()J", "shutdown", _UrlKt.FRAGMENT_ENCODE_SET, "scheduleResumeAfterDelay", "timeMillis", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "scheduleInvokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)Lkotlinx/coroutines/DisposableHandle;", "processNextEvent", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "enqueue", "task", "(Ljava/lang/Runnable;)V", "enqueueImpl", "(Ljava/lang/Runnable;)Z", "dequeue", "()Ljava/lang/Runnable;", "enqueueDelayedTasks", "closeQueue", "schedule", "now", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "shouldUnpark", "scheduleImpl", _UrlKt.FRAGMENT_ENCODE_SET, "resetAll", "rescheduleAllDelayed", "DelayedTask", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTaskQueue", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase\n+ 2 EventLoop.kt\nkotlinx/coroutines/EventLoopKt\n+ 3 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n+ 4 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 5 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,540:1\n24#2:541\n51#3:542\n52#3,7:545\n26#4:543\n16#5:544\n1#6:552\n*S KotlinDebug\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase\n*L\n256#1:541\n329#1:542\n329#1:545,7\n329#1:543\n329#1:544\n*E\n"})
/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _delayed$volatile$FU = null;
    private static final /* synthetic */ AtomicIntegerFieldUpdater _isCompleted$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _queue$volatile$FU = null;
    private volatile /* synthetic */ Object _delayed$volatile;
    private volatile /* synthetic */ int _isCompleted$volatile;
    private volatile /* synthetic */ Object _queue$volatile;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", _UrlKt.FRAGMENT_ENCODE_SET, "cont", "Lkotlinx/coroutines/CancellableContinuation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/coroutines/EventLoopImplBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,540:1\n1#2:541\n*E\n"})
    /* loaded from: classes5.dex */
    public final class DelayedResumeTask extends DelayedTask {
        private final CancellableContinuation<Unit> cont;
        final /* synthetic */ EventLoopImplBase this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public DelayedResumeTask(EventLoopImplBase r1, long r2, CancellableContinuation<? super Unit> r4) {
            this.this$0 = r1;
            super(r2);
            this.cont = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.cont.resumeUndispatched(this.this$0, Unit.INSTANCE);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return super.toString() + this.cont;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "<init>", "(JLjava/lang/Runnable;)V", "Ljava/lang/Runnable;", "run", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class DelayedRunnableTask extends DelayedTask {
        private final Runnable block;

        public DelayedRunnableTask(long r1, Runnable r3) {
            super(r1);
            this.block = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.block.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return super.toString() + this.block;
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u00052\u00060\u0006j\u0002`\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0000H\u0096\u0002J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\tJ\u001e\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020'H\u0016R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\f\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/SynchronizedObject;", "nanoTime", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(J)V", "_heap", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "timeToExecute", _UrlKt.FRAGMENT_ENCODE_SET, "now", "scheduleTask", "delayed", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "dispose", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase$DelayedTask\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n*L\n1#1,540:1\n26#2:541\n26#2:544\n26#2:553\n16#3:542\n16#3:545\n16#3:554\n63#4:543\n64#4,7:546\n*S KotlinDebug\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase$DelayedTask\n*L\n434#1:541\n436#1:544\n476#1:553\n434#1:542\n436#1:545\n476#1:554\n436#1:543\n436#1:546,7\n*E\n"})
    /* loaded from: classes5.dex */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        private volatile Object _heap;
        private int index;

        @JvmField
        public long nanoTime;

        public DelayedTask(long r1) {
            this.nanoTime = r1;
            this.index = -1;
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(DelayedTask r1) {
            return compareTo2(r1);
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(DelayedTask r3) {
            long r0 = this.nanoTime - r3.nanoTime;
            if (r0 <= 0) goto L6;
            return 1;
        L6:
            if (r0 >= 0) goto L9;
            return -1;
        L9:
            return 0;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            monitor-enter(this);
            Object r0 = this._heap;     // Catch: Throwable -> L10
            if (r0 != EventLoop_commonKt.access$getDISPOSED_TASK$p()) goto L8;
            monitor-exit(this);
            return;
        L8:
            if ((r0 instanceof DelayedTaskQueue) == false) goto L12;
            DelayedTaskQueue r02 = (DelayedTaskQueue) r0;     // Catch: Throwable -> L10
        L13:
            if (r02 == null) goto L15;
            r02.remove(this);     // Catch: Throwable -> L10
        L15:
            this._heap = EventLoop_commonKt.access$getDISPOSED_TASK$p();     // Catch: Throwable -> L10
            Unit r03 = Unit.INSTANCE;     // Catch: Throwable -> L10
            monitor-exit(this);
            return;
        L12:
            r02 = null;
        L10:
            th = move-exception;
            throw th;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public ThreadSafeHeap<?> getHeap() {
            Object r1 = this._heap;
            if ((r1 instanceof ThreadSafeHeap) == true) goto L5;
            return null;
        L5:
            return (ThreadSafeHeap) r1;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        public final int scheduleTask(long r8, DelayedTaskQueue r10, EventLoopImplBase r11) {
            monitor-enter(this);
        L15:
            th = move-exception;
            throw th;
        L4:
            if (this._heap != EventLoop_commonKt.access$getDISPOSED_TASK$p()) goto L8;
            monitor-exit(this);
            return 2;
        L8:
            monitor-enter(r10);     // Catch: Throwable -> L15
            DelayedTask r0 = r10.firstImpl();     // Catch: Throwable -> L20
            if (EventLoopImplBase.access$isCompleted(r11) == false) goto L18;
            monitor-exit(r10);     // Catch: Throwable -> L15
            monitor-exit(this);
            return 1;
        L18:
            if (r0 != null) goto L22;
            r10.timeNow = r8;     // Catch: Throwable -> L20
        L29:
            long r82 = this.nanoTime;     // Catch: Throwable -> L20
            long r3 = r10.timeNow;     // Catch: Throwable -> L20
            if ((r82 - r3) >= 0) goto L32;
            this.nanoTime = r3;     // Catch: Throwable -> L20
        L32:
            r10.addImpl(this);     // Catch: Throwable -> L20
            monitor-exit(r10);     // Catch: Throwable -> L15
            monitor-exit(this);
            return 0;
        L22:
            long r32 = r0.nanoTime;     // Catch: Throwable -> L20
            if ((r32 - r8) >= 0) goto L27;
            r8 = r32;
        L27:
            if ((r8 - r10.timeNow) <= 0) goto L29;
            r10.timeNow = r8;     // Catch: Throwable -> L20
        L20:
            th = move-exception;
            throw th;     // Catch: Throwable -> L15
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(ThreadSafeHeap<?> r3) {
            if (this._heap == EventLoop_commonKt.access$getDISPOSED_TASK$p()) goto L6;
            this._heap = r3;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int r1) {
            this.index = r1;
        }

        public final boolean timeToExecute(long r3) {
            if ((r3 - this.nanoTime) < 0) goto L6;
            return true;
        L6:
            return false;
        }

        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + ']';
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "timeNow", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(J)V", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {

        @JvmField
        public long timeNow;

        public DelayedTaskQueue(long r1) {
            this.timeNow = r1;
        }
    }

    static {
        _queue$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue$volatile");
        _delayed$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed$volatile");
        _isCompleted$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(EventLoopImplBase.class, "_isCompleted$volatile");
    }

    public EventLoopImplBase() {
    }

    public static final /* synthetic */ boolean access$isCompleted(EventLoopImplBase r0) {
        return r0.isCompleted();
    }

    private final void closeQueue() {
        AtomicReferenceFieldUpdater r0 = get_queue$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        if (r1 == null) goto L6;
        if ((r1 instanceof LockFreeTaskQueueCore) == true) goto L10;
        if (r1 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) goto L28;
        LockFreeTaskQueueCore r2 = new LockFreeTaskQueueCore(8, true);
        r2.addLast((Runnable) r1);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_queue$volatile$FU(), this, r1, r2) == false) goto L3;
        return;
    L28:
        return;
    L10:
        ((LockFreeTaskQueueCore) r1).close();
        return;
    L6:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_queue$volatile$FU(), this, null, EventLoop_commonKt.access$getCLOSED_EMPTY$p()) == false) goto L3;
    }

    private final Runnable dequeue() {
        AtomicReferenceFieldUpdater r0 = get_queue$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        if (r1 == null) goto L5;
        if ((r1 instanceof LockFreeTaskQueueCore) == true) goto L8;
        if (r1 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) goto L15;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_queue$volatile$FU(), this, r1, null) == false) goto L3;
        return (Runnable) r1;
    L15:
        return null;
    L8:
        LockFreeTaskQueueCore r2 = (LockFreeTaskQueueCore) r1;
        Object r3 = r2.removeFirstOrNull();
        if (r3 != LockFreeTaskQueueCore.REMOVE_FROZEN) goto L11;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_queue$volatile$FU(), this, r1, r2.next());
        goto L3
    L11:
        return (Runnable) r3;
    L5:
        return null;
    }

    private final void enqueueDelayedTasks() {
        DelayedTaskQueue r0 = (DelayedTaskQueue) get_delayed$volatile$FU().get(this);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L32;
        AbstractTimeSourceKt.access$getTimeSource$p();
        long r1 = System.nanoTime();
    L7:
        monitor-enter(r0);
        DelayedTask r3 = r0.firstImpl();     // Catch: Throwable -> L16
        DelayedTask r4 = null;
        if (r3 != null) goto L13;
    L11:
        monitor-exit(r0);
        if (r4 != null) goto L7;
        return;
    L13:
        DelayedTask r32 = r3;     // Catch: Throwable -> L16
        if (r32.timeToExecute(r1) == false) goto L18;
        boolean r33 = enqueueImpl(r32);     // Catch: Throwable -> L16
    L19:
        if (r33 == false) goto L11;
        r4 = r0.removeAtImpl(0);     // Catch: Throwable -> L16
        goto L11
    L18:
        r33 = false;
    L16:
        th = move-exception;
        throw th;
    }

    private final boolean enqueueImpl(Runnable r7) {
        AtomicReferenceFieldUpdater r0 = get_queue$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        if (isCompleted() == true) goto L5;
        if (r1 == null) goto L9;
        if ((r1 instanceof LockFreeTaskQueueCore) == true) goto L13;
        if (r1 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) goto L24;
        LockFreeTaskQueueCore r3 = new LockFreeTaskQueueCore(8, true);
        r3.addLast((Runnable) r1);
        r3.addLast(r7);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_queue$volatile$FU(), this, r1, r3) == false) goto L3;
        return true;
    L24:
        return false;
    L13:
        LockFreeTaskQueueCore r4 = (LockFreeTaskQueueCore) r1;
        int r5 = r4.addLast(r7);
        if (r5 == 0) goto L21;
        if (r5 != 1) goto L17;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_queue$volatile$FU(), this, r1, r4.next());
        goto L3
    L17:
        if (r5 != 2) goto L3;
        return false;
    L21:
        return true;
    L9:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_queue$volatile$FU(), this, null, r7) == false) goto L3;
        return true;
    L5:
        return false;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_delayed$volatile$FU() {
        return _delayed$volatile$FU;
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_isCompleted$volatile$FU() {
        return _isCompleted$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_queue$volatile$FU() {
        return _queue$volatile$FU;
    }

    private final boolean isCompleted() {
        if (get_isCompleted$volatile$FU().get(this) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private final void rescheduleAllDelayed() {
        AbstractTimeSourceKt.access$getTimeSource$p();
        long r0 = System.nanoTime();
    L3:
        DelayedTaskQueue r2 = (DelayedTaskQueue) get_delayed$volatile$FU().get(this);
        if (r2 == null) goto L9;
        DelayedTask r22 = r2.removeFirstOrNull();
        if (r22 == null) goto L12;
        reschedule(r0, r22);
        goto L3
    L12:
        return;
    }

    private final int scheduleImpl(long r4, DelayedTask r6) {
        if (isCompleted() == false) goto L6;
        return 1;
    L6:
        DelayedTaskQueue r0 = (DelayedTaskQueue) get_delayed$volatile$FU().get(this);
        if (r0 != null) goto L10;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_delayed$volatile$FU(), this, null, new DelayedTaskQueue(r4));
        r0 = (DelayedTaskQueue) get_delayed$volatile$FU().get(this);
    L10:
        return r6.scheduleTask(r4, r0, this);
    }

    private final void setCompleted(boolean r2) {
        get_isCompleted$volatile$FU().set(this, r2 ? 1 : 0);
    }

    private final boolean shouldUnpark(DelayedTask r2) {
        DelayedTaskQueue r1 = (DelayedTaskQueue) get_delayed$volatile$FU().get(this);
        if (r1 == null) goto L5;
        DelayedTask r12 = r1.peek();
    L6:
        if (r12 != r2) goto L9;
        return true;
    L9:
        return false;
    L5:
        r12 = null;
        goto L6
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext r1, Runnable r2) {
        enqueue(r2);
    }

    public void enqueue(Runnable r2) {
        enqueueDelayedTasks();
        if (enqueueImpl(r2) == false) goto L6;
        unpark();
        return;
    L6:
        DefaultExecutor.INSTANCE.enqueue(r2);
    }

    @Override // kotlinx.coroutines.EventLoop
    public long getNextTime() {
        if (super.getNextTime() != 0) goto L5;
        return 0;
    L5:
        Object r0 = get_queue$volatile$FU().get(this);
        if (r0 != null) goto L8;
    L16:
        DelayedTaskQueue r6 = (DelayedTaskQueue) get_delayed$volatile$FU().get(this);
        if (r6 == null) goto L23;
        DelayedTask r62 = r6.peek();
        if (r62 == null) goto L23;
        long r02 = r62.nanoTime;
        AbstractTimeSourceKt.access$getTimeSource$p();
        return RangesKt.coerceAtLeast(r02 - System.nanoTime(), 0);
    L23:
        return LongCompanionObject.MAX_VALUE;
    L8:
        if ((r0 instanceof LockFreeTaskQueueCore) == false) goto L13;
        if (((LockFreeTaskQueueCore) r0).isEmpty() == true) goto L16;
        return 0;
    L13:
        if (r0 != EventLoop_commonKt.access$getCLOSED_EMPTY$p()) goto L15;
        return LongCompanionObject.MAX_VALUE;
    L15:
        return 0;
    }

    public /* bridge */ DisposableHandle invokeOnTimeout(long r1, Runnable r3, CoroutineContext r4) {
        return super.invokeOnTimeout(r1, r3, r4);
    }

    public boolean isEmpty() {
        if (isUnconfinedQueueEmpty() == true) goto L5;
        return false;
    L5:
        DelayedTaskQueue r0 = (DelayedTaskQueue) get_delayed$volatile$FU().get(this);
        if (r0 != null) goto L8;
    L10:
        Object r3 = get_queue$volatile$FU().get(this);
        if (r3 != null) goto L14;
        return true;
    L14:
        if ((r3 instanceof LockFreeTaskQueueCore) == false) goto L18;
        return ((LockFreeTaskQueueCore) r3).isEmpty();
    L18:
        if (r3 != EventLoop_commonKt.access$getCLOSED_EMPTY$p()) goto L20;
        return true;
    L20:
        return false;
    L8:
        if (r0.isEmpty() == true) goto L10;
        return false;
    }

    @Override // kotlinx.coroutines.EventLoop
    public long processNextEvent() {
        if (processUnconfinedEvent() == false) goto L5;
        return 0;
    L5:
        enqueueDelayedTasks();
        Runnable r0 = dequeue();
        if (r0 == null) goto L10;
        r0.run();
        return 0;
    L10:
        return getNextTime();
    }

    public final void resetAll() {
        get_queue$volatile$FU().set(this, null);
        get_delayed$volatile$FU().set(this, null);
    }

    public final void schedule(long r3, DelayedTask r5) {
        int r0 = scheduleImpl(r3, r5);
        if (r0 == 0) goto L14;
        if (r0 != 1) goto L7;
        reschedule(r3, r5);
        return;
    L7:
        if (r0 == 2) goto L18;
        Segment$$ExternalSyntheticBUOutline1.m("unexpected result");
        return;
    L18:
        return;
    L14:
        if (shouldUnpark(r5) == false) goto L17;
        unpark();
        return;
    }

    public final DisposableHandle scheduleInvokeOnTimeout(long r4, Runnable r6) {
        long r42 = EventLoop_commonKt.delayToNanos(r4);
        if (r42 >= DurationKt.MAX_MILLIS) goto L7;
        AbstractTimeSourceKt.access$getTimeSource$p();
        long r0 = System.nanoTime();
        DelayedRunnableTask r2 = new DelayedRunnableTask(r42 + r0, r6);
        schedule(r0, r2);
        return r2;
    L7:
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long r4, CancellableContinuation<? super Unit> r6) {
        long r42 = EventLoop_commonKt.delayToNanos(r4);
        if (r42 >= DurationKt.MAX_MILLIS) goto L6;
        AbstractTimeSourceKt.access$getTimeSource$p();
        long r0 = System.nanoTime();
        DelayedResumeTask r2 = new DelayedResumeTask(this, r42 + r0, r6);
        schedule(r0, r2);
        CancellableContinuationKt.disposeOnCancellation(r6, r2);
        return;
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        setCompleted(true);
        closeQueue();
    L4:
        if (processNextEvent() <= 0) goto L4;
        rescheduleAllDelayed();
    }
}
