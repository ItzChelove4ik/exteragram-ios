package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.internal.measurement.zzpo$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0082@¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u0010*\u00020\u0001H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0010¢\u0006\u0004\b\u0017\u0010\u0014J\u0010\u0010\u0018\u001a\u00020\u0007H\u0086@¢\u0006\u0004\b\u0018\u0010\tJ\u001d\u0010\u0018\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0019H\u0005¢\u0006\u0004\b\u0018\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0007¢\u0006\u0004\b\u001b\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR,\u0010 \u001a\u001a\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00070\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0011\u0010#\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\"\u0010\u000bR\u0011\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$8\u0002X\u0082\u0004R\u000b\u0010(\u001a\u00020'8\u0002X\u0082\u0004R\u0011\u0010)\u001a\b\u0012\u0004\u0012\u00020%0$8\u0002X\u0082\u0004R\u000b\u0010*\u001a\u00020'8\u0002X\u0082\u0004R\u000b\u0010,\u001a\u00020+8\u0002X\u0082\u0004¨\u0006-"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreAndMutexImpl;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "permits", "acquiredPermits", "<init>", "(II)V", _UrlKt.FRAGMENT_ENCODE_SET, "acquireSlowPath", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decPermits", "()I", "coerceAvailablePermitsAtMaximum", "()V", "Lkotlinx/coroutines/Waiter;", "waiter", _UrlKt.FRAGMENT_ENCODE_SET, "addAcquireToQueue", "(Lkotlinx/coroutines/Waiter;)Z", "tryResumeNextFromQueue", "()Z", "tryResumeAcquire", "(Ljava/lang/Object;)Z", "tryAcquire", "acquire", "Lkotlinx/coroutines/CancellableContinuation;", "(Lkotlinx/coroutines/CancellableContinuation;)V", BuildConfig.BUILD_TYPE, "I", "Lkotlin/Function3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/CoroutineContext;", "onCancellationRelease", "Lkotlin/jvm/functions/Function3;", "getAvailablePermits", "availablePermits", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "head", "Lkotlinx/atomicfu/AtomicLong;", "deqIdx", "tail", "enqIdx", "Lkotlinx/atomicfu/AtomicInt;", "_availablePermits", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSemaphore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreAndMutexImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 4 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListKt\n+ 5 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreSegment\n*L\n1#1,396:1\n200#1,10:410\n200#1,10:420\n1#2:397\n452#3,12:398\n68#4,3:430\n41#4,9:433\n68#4,3:445\n41#4,9:448\n374#5:442\n374#5:443\n366#5:444\n377#5:457\n366#5:458\n374#5:459\n*S KotlinDebug\n*F\n+ 1 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreAndMutexImpl\n*L\n192#1:410,10\n216#1:420,10\n182#1:398,12\n284#1:430,3\n284#1:433,9\n318#1:445,3\n318#1:448,9\n288#1:442\n294#1:443\n308#1:444\n323#1:457\n329#1:458\n332#1:459\n*E\n"})
/* loaded from: classes.dex */
public class SemaphoreAndMutexImpl {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater deqIdx$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater enqIdx$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater head$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater tail$volatile$FU = null;
    private volatile /* synthetic */ int _availablePermits$volatile;
    private volatile /* synthetic */ long deqIdx$volatile;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private final Function3<Throwable, Unit, CoroutineContext, Unit> onCancellationRelease;
    private final int permits;
    private volatile /* synthetic */ Object tail$volatile;

    /* renamed from: $r8$lambda$k7tNymquqjBMM7st74A-BU0dLJQ, reason: not valid java name */
    public static Unit m3566$r8$lambda$k7tNymquqjBMM7st74ABU0dLJQ(SemaphoreAndMutexImpl r0, Throwable r1, Unit r2, CoroutineContext r3) {
        r0.release();
        return Unit.INSTANCE;
    }

    static {
        head$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, Object.class, "head$volatile");
        deqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, "deqIdx$volatile");
        tail$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, Object.class, "tail$volatile");
        enqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, "enqIdx$volatile");
        _availablePermits$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreAndMutexImpl.class, "_availablePermits$volatile");
    }

    public SemaphoreAndMutexImpl(int r6, int r7) {
        this.permits = r6;
        if (r6 <= 0) goto L10;
        if (r7 < 0) goto L8;
        if (r7 > r6) goto L8;
        SemaphoreSegment r0 = new SemaphoreSegment(0, null, 2);
        this.head$volatile = r0;
        this.tail$volatile = r0;
        this._availablePermits$volatile = r6 - r7;
        this.onCancellationRelease = new SemaphoreAndMutexImpl$$ExternalSyntheticLambda0(this);
        return;
    L8:
        Utf8$$ExternalSyntheticBUOutline1.m("The number of acquired permits should be in 0..", r6);
        throw null;
    L10:
        Utf8$$ExternalSyntheticBUOutline1.m("Semaphore should have at least 1 permit, but had ", r6);
        throw null;
    }

    public static final /* synthetic */ boolean access$addAcquireToQueue(SemaphoreAndMutexImpl r0, Waiter r1) {
        return r0.addAcquireToQueue(r1);
    }

    private final Object acquireSlowPath(Continuation<? super Unit> r3) {
        CancellableContinuationImpl r0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(r3));
    L7:
        th = move-exception;
        r0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        throw th;
    L4:
        if (access$addAcquireToQueue(this, r0) == true) goto L9;
        acquire(r0);     // Catch: Throwable -> L7
    L9:
        Object r2 = r0.getResult();
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L13;
        DebugProbesKt.probeCoroutineSuspended(r3);
    L13:
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L16;
        return r2;
    L16:
        return Unit.INSTANCE;
    }

    private final boolean addAcquireToQueue(Waiter r15) {
        SemaphoreSegment r0 = (SemaphoreSegment) getTail$volatile$FU().get(this);
        long r1 = getEnqIdx$volatile$FU().getAndIncrement(this);
        SemaphoreAndMutexImpl$addAcquireToQueue$createNewSegment$1 r3 = SemaphoreAndMutexImpl$addAcquireToQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater r4 = getTail$volatile$FU();
        long r5 = r1 / SemaphoreKt.access$getSEGMENT_SIZE$p();
    L3:
        Object r7 = ConcurrentLinkedListKt.findSegmentInternal(r0, r5, r3);
        if (SegmentOrClosed.m3559isClosedimpl(r7) == true) goto L20;
        Segment r8 = SegmentOrClosed.m3558getSegmentimpl(r7);
    L6:
        Segment r9 = (Segment) r4.get(this);
        if (r9.id >= r8.id) goto L20;
        if (r8.tryIncPointers$kotlinx_coroutines_core() == false) goto L3;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r4, this, r9, r8) == true) goto L15;
        if (r8.decPointers$kotlinx_coroutines_core() == false) goto L6;
        r8.remove();
        goto L6
    L15:
        if (r9.decPointers$kotlinx_coroutines_core() == false) goto L20;
        r9.remove();
    L20:
        SemaphoreSegment r02 = (SemaphoreSegment) SegmentOrClosed.m3558getSegmentimpl(r7);
        int r12 = (int) (r1 % SemaphoreKt.access$getSEGMENT_SIZE$p());
        if (zzpo$$ExternalSyntheticBackportWithForwarding0.m(r02.getAcquirers(), r12, null, r15) == false) goto L25;
        r15.invokeOnCancellation(r02, r12);
        return true;
    L25:
        if (zzpo$$ExternalSyntheticBackportWithForwarding0.m(r02.getAcquirers(), r12, SemaphoreKt.access$getPERMIT$p(), SemaphoreKt.access$getTAKEN$p()) == true) goto L27;
    L34:
        return false;
    L27:
        if ((r15 instanceof CancellableContinuation) == false) goto L30;
        ((CancellableContinuation) r15).resume(Unit.INSTANCE, this.onCancellationRelease);
    L32:
        return true;
    L30:
        if ((r15 instanceof SelectInstance) == false) goto L33;
        ((SelectInstance) r15).selectInRegistrationPhase(Unit.INSTANCE);
        goto L32
    L33:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("unexpected: ", r15);
        goto L34
    }

    private final void coerceAvailablePermitsAtMaximum() {
    L2:
        int r0 = get_availablePermits$volatile$FU().get(this);
        if (r0 <= this.permits) goto L6;
        if (get_availablePermits$volatile$FU().compareAndSet(this, r0, this.permits) == false) goto L2;
        return;
    }

    private final int decPermits() {
    L2:
        int r0 = get_availablePermits$volatile$FU().getAndDecrement(this);
        if (r0 > this.permits) goto L2;
        return r0;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getDeqIdx$volatile$FU() {
        return deqIdx$volatile$FU;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getEnqIdx$volatile$FU() {
        return enqIdx$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getHead$volatile$FU() {
        return head$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getTail$volatile$FU() {
        return tail$volatile$FU;
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_availablePermits$volatile$FU() {
        return _availablePermits$volatile$FU;
    }

    private final boolean tryResumeAcquire(Object r4) {
        if ((r4 instanceof CancellableContinuation) == false) goto L10;
        CancellableContinuation r42 = (CancellableContinuation) r4;
        Object r3 = r42.tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
        if (r3 == null) goto L8;
        r42.completeResume(r3);
        return true;
    L8:
        return false;
    L10:
        if ((r4 instanceof SelectInstance) == true) goto L12;
        DurationKt$$ExternalSyntheticBUOutline0.m("unexpected: ", r4);
        return false;
    L12:
        return ((SelectInstance) r4).trySelect(this, Unit.INSTANCE);
    }

    private final boolean tryResumeNextFromQueue() {
        SemaphoreSegment r0 = (SemaphoreSegment) getHead$volatile$FU().get(this);
        long r1 = getDeqIdx$volatile$FU().getAndIncrement(this);
        long r3 = r1 / SemaphoreKt.access$getSEGMENT_SIZE$p();
        SemaphoreAndMutexImpl$tryResumeNextFromQueue$createNewSegment$1 r5 = SemaphoreAndMutexImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater r6 = getHead$volatile$FU();
    L3:
        Object r7 = ConcurrentLinkedListKt.findSegmentInternal(r0, r3, r5);
        if (SegmentOrClosed.m3559isClosedimpl(r7) == true) goto L20;
        Segment r8 = SegmentOrClosed.m3558getSegmentimpl(r7);
    L6:
        Segment r9 = (Segment) r6.get(this);
        if (r9.id >= r8.id) goto L20;
        if (r8.tryIncPointers$kotlinx_coroutines_core() == false) goto L3;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r6, this, r9, r8) == true) goto L15;
        if (r8.decPointers$kotlinx_coroutines_core() == false) goto L6;
        r8.remove();
        goto L6
    L15:
        if (r9.decPointers$kotlinx_coroutines_core() == false) goto L20;
        r9.remove();
    L20:
        SemaphoreSegment r02 = (SemaphoreSegment) SegmentOrClosed.m3558getSegmentimpl(r7);
        r02.cleanPrev();
        int r4 = 0;
        if (r02.id <= r3) goto L23;
        return false;
    L23:
        int r12 = (int) (r1 % SemaphoreKt.access$getSEGMENT_SIZE$p());
        Object r2 = r02.getAcquirers().getAndSet(r12, SemaphoreKt.access$getPERMIT$p());
        if (r2 != null) goto L35;
        int r14 = SemaphoreKt.access$getMAX_SPIN_CYCLES$p();
    L27:
        if (r4 >= r14) goto L32;
        if (r02.getAcquirers().get(r12) == SemaphoreKt.access$getTAKEN$p()) goto L30;
        r4 = r4 + 1;
        goto L27
    L30:
        return true;
    L32:
        Symbol r142 = SemaphoreKt.access$getPERMIT$p();
        Symbol r32 = SemaphoreKt.access$getBROKEN$p();
        return !zzpo$$ExternalSyntheticBackportWithForwarding0.m(r02.getAcquirers(), r12, r142, r32);
    L35:
        if (r2 != SemaphoreKt.access$getCANCELLED$p()) goto L38;
        return false;
    L38:
        return tryResumeAcquire(r2);
    }

    public final Object acquire(Continuation<? super Unit> r2) {
        if (decPermits() > 0) goto L5;
        Object r1 = acquireSlowPath(r2);
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        return r1;
    L10:
        return Unit.INSTANCE;
    L5:
        return Unit.INSTANCE;
    }

    public final void acquire(CancellableContinuation<? super Unit> r2) {
    L3:
        if (decPermits() > 0) goto L4;
        if (addAcquireToQueue((Waiter) r2) == false) goto L3;
        return;
    L4:
        r2.resume(Unit.INSTANCE, this.onCancellationRelease);
    }

    public final int getAvailablePermits() {
        return Math.max(get_availablePermits$volatile$FU().get(this), 0);
    }

    public final void release() {
    L2:
        int r0 = get_availablePermits$volatile$FU().getAndIncrement(this);
        if (r0 >= this.permits) goto L9;
        if (r0 >= 0) goto L8;
        if (tryResumeNextFromQueue() == false) goto L2;
        return;
    L8:
        return;
    L9:
        coerceAvailablePermitsAtMaximum();
        throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
    }

    public final boolean tryAcquire() {
    L2:
        int r0 = get_availablePermits$volatile$FU().get(this);
        if (r0 > this.permits) goto L4;
        if (r0 <= 0) goto L6;
        if (get_availablePermits$volatile$FU().compareAndSet(this, r0, r0 - 1) == false) goto L2;
        return true;
    L6:
        return false;
    L4:
        coerceAvailablePermitsAtMaximum();
        goto L2
    }
}
