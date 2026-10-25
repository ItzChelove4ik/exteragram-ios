package kotlinx.coroutines.flow;

import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.internal.Concurrent_commonKt;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016J'\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f0\u000e2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0015R$\u0010\u0005\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0007`\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "Lkotlinx/coroutines/flow/StateFlowImpl;", "<init>", "()V", "_state", "Ljava/util/concurrent/atomic/AtomicReference;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/WorkaroundAtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "allocateLocked", _UrlKt.FRAGMENT_ENCODE_SET, "flow", "freeLocked", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlinx/coroutines/flow/StateFlowImpl;)[Lkotlin/coroutines/Continuation;", "makePending", "takePending", "awaitPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStateFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowSlot\n+ 2 Concurrent.common.kt\nkotlinx/coroutines/internal/Concurrent_commonKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,433:1\n34#2,2:434\n1#3:436\n433#4,10:437\n*S KotlinDebug\n*F\n+ 1 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowSlot\n*L\n280#1:434,2\n303#1:437,10\n*E\n"})
/* loaded from: classes.dex */
final class StateFlowSlot extends AbstractSharedFlowSlot<StateFlowImpl<?>> {
    private final AtomicReference<Object> _state;

    public StateFlowSlot() {
        this._state = new AtomicReference(null);
    }

    public static final /* synthetic */ AtomicReference access$get_state$p(StateFlowSlot r0) {
        return r0._state;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public /* bridge */ /* synthetic */ boolean allocateLocked(StateFlowImpl<?> r1) {
        return allocateLocked2(r1);
    }

    /* renamed from: allocateLocked, reason: avoid collision after fix types in other method */
    public boolean allocateLocked2(StateFlowImpl<?> r1) {
        if (Concurrent_commonKt.getValue(this._state) == null) goto L6;
        return false;
    L6:
        Concurrent_commonKt.setValue(this._state, StateFlowKt.access$getNONE$p());
        return true;
    }

    public final Object awaitPending(Continuation<? super Unit> r4) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r4), 1);
        r0.initCancellability();
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(access$get_state$p(this), StateFlowKt.access$getNONE$p(), r0) == true) goto L5;
        Result.Companion r3 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
    L5:
        Object r32 = r0.getResult();
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        DebugProbesKt.probeCoroutineSuspended(r4);
    L9:
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L12;
        return r32;
    L12:
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public /* bridge */ /* synthetic */ Continuation[] freeLocked(StateFlowImpl<?> r1) {
        return freeLocked2(r1);
    }

    /* renamed from: freeLocked, reason: avoid collision after fix types in other method */
    public Continuation<Unit>[] freeLocked2(StateFlowImpl<?> r1) {
        Concurrent_commonKt.setValue(this._state, null);
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final void makePending() {
        AtomicReference<Object> r0 = this._state;
    L3:
        Object r1 = Concurrent_commonKt.getValue(r0);
        if (r1 == null) goto L13;
        if (r1 == StateFlowKt.access$getPENDING$p()) goto L27;
        Symbol r2 = StateFlowKt.access$getNONE$p();
        AtomicReference<Object> r3 = this._state;
        if (r1 == r2) goto L12;
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r3, r1, StateFlowKt.access$getNONE$p()) == false) goto L3;
        ((CancellableContinuationImpl) r1).resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
        return;
    L12:
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r3, r1, StateFlowKt.access$getPENDING$p()) == false) goto L3;
        return;
    L27:
        return;
    }

    public final boolean takePending() {
        if (this._state.getAndSet(StateFlowKt.access$getNONE$p()) != StateFlowKt.access$getPENDING$p()) goto L6;
        return true;
    L6:
        return false;
    }
}
