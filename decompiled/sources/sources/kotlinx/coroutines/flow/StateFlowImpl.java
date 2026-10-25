package kotlinx.coroutines.flow;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00028\u00000\u00042\b\u0012\u0004\u0012\u00028\u00000\u00052\b\u0012\u0004\u0012\u00028\u00000\u0006B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ!\u0010\r\u001a\u00020\f2\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u000eJ\u0017\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001e\u0010\u001d\u001a\u00020\u001c2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096@¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0003H\u0014¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030#2\u0006\u0010\"\u001a\u00020!H\u0014¢\u0006\u0004\b$\u0010%J-\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000+2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020!2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R$\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u0010\tR\u0011\u00104\u001a\b\u0012\u0004\u0012\u00020\u0005038\u0002X\u0082\u0004¨\u00065"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/MutableStateFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "initialState", "<init>", "(Ljava/lang/Object;)V", "expectedState", "newState", _UrlKt.FRAGMENT_ENCODE_SET, "updateState", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "expect", PluginsConstants.UPDATE, "compareAndSet", "value", "tryEmit", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetReplayCache", "()V", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "createSlotArray", "(I)[Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlin/coroutines/CoroutineContext;", "context", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlinx/coroutines/flow/Flow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "sequence", "I", "getValue", "()Ljava/lang/Object;", "setValue", "Lkotlinx/atomicfu/AtomicRef;", "_state", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStateFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowImpl\n+ 2 Symbol.kt\nkotlinx/coroutines/internal/Symbol\n+ 3 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 4 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 6 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n*L\n1#1,433:1\n14#2:434\n14#2:442\n26#3:435\n26#3:439\n16#4:436\n16#4:440\n13805#5,2:437\n1307#6:441\n*S KotlinDebug\n*F\n+ 1 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowImpl\n*L\n320#1:434\n401#1:442\n329#1:435\n357#1:439\n329#1:436\n357#1:440\n353#1:437,2\n390#1:441\n*E\n"})
/* loaded from: classes.dex */
final class StateFlowImpl<T> extends AbstractSharedFlow<StateFlowSlot> implements MutableStateFlow<T>, Flow, FusibleFlow<T> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$volatile$FU = null;
    private volatile /* synthetic */ Object _state$volatile;
    private int sequence;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.StateFlowImpl", f = "StateFlow.kt", i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {389, 401, 406}, m = "collect", n = {"collector", "slot", "collector", "slot", "collectorJob", "oldState", "newState", "collector", "slot", "collectorJob", "oldState", "newState"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.StateFlowImpl$collect$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ StateFlowImpl<T> this$0;

        public AnonymousClass1(StateFlowImpl<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.collect(null, this);
        }
    }

    static {
        _state$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowImpl.class, Object.class, "_state$volatile");
    }

    public StateFlowImpl(Object r1) {
        this._state$volatile = r1;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_state$volatile$FU() {
        return _state$volatile$FU;
    }

    private final boolean updateState(Object r7, Object r8) {
        monitor-enter(this);
        Object r0 = get_state$volatile$FU().get(this);     // Catch: Throwable -> L9
        if (r7 == null) goto L13;
        if (Intrinsics.areEqual(r0, r7) == true) goto L13;
        monitor-exit(this);
        return false;
    L13:
        if (Intrinsics.areEqual(r0, r8) == false) goto L16;
        monitor-exit(this);
        return true;
    L16:
        get_state$volatile$FU().set(this, r8);     // Catch: Throwable -> L9
        int r72 = this.sequence;     // Catch: Throwable -> L9
        if ((r72 & 1) != 0) goto L42;
        int r73 = r72 + 1;     // Catch: Throwable -> L9
        this.sequence = r73;     // Catch: Throwable -> L9
        StateFlowSlot[] r82 = getSlots();     // Catch: Throwable -> L9
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(this);
    L20:
        StateFlowSlot[] r83 = r82;
        if (r83 == null) goto L28;
        int r22 = r83.length;
        int r3 = 0;
    L23:
        if (r3 >= r22) goto L28;
        StateFlowSlot r4 = r83[r3];
        if (r4 == null) goto L27;
        r4.makePending();
    L27:
        r3 = r3 + 1;
    L28:
        monitor-enter(this);
        int r84 = this.sequence;     // Catch: Throwable -> L34
        if (r84 == r73) goto L31;
        StateFlowSlot[] r74 = getSlots();     // Catch: Throwable -> L34
        Unit r23 = Unit.INSTANCE;     // Catch: Throwable -> L34
        monitor-exit(this);
        r82 = r74;
        r73 = r84;
        goto L20
    L31:
        this.sequence = r73 + 1;     // Catch: Throwable -> L34
        monitor-exit(this);
        return true;
    L34:
        th = move-exception;
        throw th;
    L42:
        this.sequence = r72 + 2;     // Catch: Throwable -> L9
        monitor-exit(this);
        return true;
    L9:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00b8 -> B:29:0x007f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00ce -> B:29:0x007f). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r10, Continuation<?> r11) {
        if ((r11 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 != 0) goto L11;
        ResultKt.throwOnFailure(r112);
        StateFlowSlot r6 = allocateSlot();
    L28:
        FlowCollector r7 = r10;
        Job r22 = (Job) r0.getContext().get(Job.Key);     // Catch: Throwable -> L17
        Object r102 = null;
    L29:
        Object r113 = get_state$volatile$FU().get(this);     // Catch: Throwable -> L17
        if (r22 == null) goto L32;
        JobKt.ensureActive(r22);     // Catch: Throwable -> L17
    L32:
        if (r102 == null) goto L36;
        boolean r8 = Intrinsics.areEqual(r102, r113);     // Catch: Throwable -> L17
        Object r114 = r113;
        if (r8 == false) goto L36;
    L45:
        if (r6.takePending() == true) goto L29;
        r0.L$0 = r7;     // Catch: Throwable -> L17
        r0.L$1 = r6;     // Catch: Throwable -> L17
        r0.L$2 = r22;     // Catch: Throwable -> L17
        r0.L$3 = r102;     // Catch: Throwable -> L17
        r0.L$4 = SpillingKt.nullOutSpilledVariable(r114);     // Catch: Throwable -> L17
        r0.label = 3;     // Catch: Throwable -> L17
        if (r6.awaitPending(r0) != r12) goto L29;
    L48:
        return r12;
    L36:
        if (r113 != NullSurrogateKt.NULL) goto L38;
        T r82 = null;
    L39:
        r0.L$0 = r7;     // Catch: Throwable -> L17
        r0.L$1 = r6;     // Catch: Throwable -> L17
        r0.L$2 = r22;     // Catch: Throwable -> L17
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r102);     // Catch: Throwable -> L17
        r0.L$4 = r113;     // Catch: Throwable -> L17
        r0.label = 2;     // Catch: Throwable -> L17
        if (r7.emit(r82, r0) == r12) goto L48;
        r102 = r113;
    L43:
        r114 = r102;
        goto L45
    L38:
        r82 = r113;
        goto L39
    L11:
        if (r2 == 1) goto L24;
        if (r2 == 2) goto L21;
        if (r2 != 3) goto L19;
        r102 = r0.L$3;
        r22 = (Job) r0.L$2;
        r6 = (StateFlowSlot) r0.L$1;
        r7 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L17
        goto L29
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L21:
        r102 = r0.L$4;
        r22 = (Job) r0.L$2;
        r6 = (StateFlowSlot) r0.L$1;
        r7 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L17
        goto L43
    L24:
        r6 = (StateFlowSlot) r0.L$1;
        r10 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        freeSlot(r6);
        throw th;
    L7:
        r0 = new AnonymousClass1(this, r11);
        goto L8
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public boolean compareAndSet(T r1, T r2) {
        if (r1 != null) goto L4;
        r1 = (T) NullSurrogateKt.NULL;
    L4:
        if (r2 != null) goto L7;
        r2 = (T) NullSurrogateKt.NULL;
    L7:
        return updateState(r1, r2);
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public StateFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public /* bridge */ /* synthetic */ AbstractSharedFlowSlot createSlot() {
        return createSlot();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public StateFlowSlot[] createSlotArray(int r1) {
        return new StateFlowSlot[r1];
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public /* bridge */ /* synthetic */ AbstractSharedFlowSlot[] createSlotArray(int r1) {
        return createSlotArray(r1);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow, kotlinx.coroutines.flow.FlowCollector
    public Object emit(T r1, Continuation<? super Unit> r2) {
        setValue(r1);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(CoroutineContext r1, int r2, BufferOverflow r3) {
        return StateFlowKt.fuseStateFlow(this, r1, r2, r3);
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public T getValue() {
        Symbol r0 = NullSurrogateKt.NULL;
        T r2 = (T) get_state$volatile$FU().get(this);
        if (r2 != r0) goto L6;
        return null;
    L6:
        return r2;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(T r2) {
        if (r2 != null) goto L4;
        r2 = (T) NullSurrogateKt.NULL;
    L4:
        updateState(null, r2);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T r1) {
        setValue(r1);
        return true;
    }
}
