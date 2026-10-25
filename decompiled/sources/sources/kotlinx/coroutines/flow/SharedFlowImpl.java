package kotlinx.coroutines.flow;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00028\u00000\u00042\b\u0012\u0004\u0012\u00028\u00000\u00052\b\u0012\u0004\u0012\u00028\u00000\u0006:\u0001pB\u001f\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001b\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ9\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u001d2\u0010\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001d2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002¢\u0006\u0004\b!\u0010\"J\u0018\u0010#\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00028\u0000H\u0082@¢\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u00020\u00132\u0006\u0010&\u001a\u00020%H\u0002¢\u0006\u0004\b'\u0010(J/\u0010-\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0002¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u0013H\u0002¢\u0006\u0004\b/\u0010\u0015J\u0019\u00101\u001a\u0004\u0018\u00010\u00052\u0006\u00100\u001a\u00020\u0003H\u0002¢\u0006\u0004\b1\u00102J\u0017\u00103\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u0003H\u0002¢\u0006\u0004\b3\u00104J\u0019\u00106\u001a\u0004\u0018\u00010\u00052\u0006\u00105\u001a\u00020\u0016H\u0002¢\u0006\u0004\b6\u00107J\u0018\u00108\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0003H\u0082@¢\u0006\u0004\b8\u00109J3\u0010<\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010:0\u001d2\u0014\u0010;\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010:0\u001dH\u0002¢\u0006\u0004\b<\u0010=J\u001e\u0010A\u001a\u00020@2\f\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000>H\u0096@¢\u0006\u0004\bA\u0010BJ\u0017\u0010C\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0004\bC\u0010\u0011J\u0018\u0010D\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00028\u0000H\u0096@¢\u0006\u0004\bD\u0010$J\u000f\u0010G\u001a\u00020\u0016H\u0000¢\u0006\u0004\bE\u0010FJ%\u0010K\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010:0\u001d2\u0006\u0010H\u001a\u00020\u0016H\u0000¢\u0006\u0004\bI\u0010JJ\u000f\u0010L\u001a\u00020\u0003H\u0014¢\u0006\u0004\bL\u0010MJ\u001f\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001d2\u0006\u0010N\u001a\u00020\u0007H\u0014¢\u0006\u0004\bO\u0010PJ\u000f\u0010Q\u001a\u00020\u0013H\u0016¢\u0006\u0004\bQ\u0010\u0015J-\u0010V\u001a\b\u0012\u0004\u0012\u00028\u00000U2\u0006\u0010S\u001a\u00020R2\u0006\u0010T\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\bV\u0010WR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010XR\u0014\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010XR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010YR \u0010Z\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010]R\u0016\u0010_\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010XR\u0016\u0010`\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010XR\u0014\u0010b\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\ba\u0010FR\u0014\u0010e\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bc\u0010dR\u0014\u0010g\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bf\u0010dR\u0014\u0010i\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bh\u0010FR\u0014\u0010k\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bj\u0010FR\u001a\u0010o\u001a\u00028\u00008DX\u0084\u0004¢\u0006\f\u0012\u0004\bn\u0010\u0015\u001a\u0004\bl\u0010m¨\u0006q"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl;", "T", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/SharedFlowSlot;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/internal/FusibleFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "replay", "bufferCapacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "<init>", "(IILkotlinx/coroutines/channels/BufferOverflow;)V", "value", _UrlKt.FRAGMENT_ENCODE_SET, "tryEmitLocked", "(Ljava/lang/Object;)Z", "tryEmitNoCollectorsLocked", _UrlKt.FRAGMENT_ENCODE_SET, "dropOldestLocked", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "newHead", "correctCollectorIndexesOnDropOldest", "(J)V", PluginsConstants.Settings.ITEM, "enqueueLocked", "(Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, "curBuffer", "curSize", "newSize", "growBuffer", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "emitSuspend", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "emitter", "cancelEmitter", "(Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;)V", "newReplayIndex", "newMinCollectorIndex", "newBufferEndIndex", "newQueueEndIndex", "updateBufferLocked", "(JJJJ)V", "cleanupTailLocked", "slot", "tryTakeValue", "(Lkotlinx/coroutines/flow/SharedFlowSlot;)Ljava/lang/Object;", "tryPeekLocked", "(Lkotlinx/coroutines/flow/SharedFlowSlot;)J", "index", "getPeekedValueLockedAt", "(J)Ljava/lang/Object;", "awaitValue", "(Lkotlinx/coroutines/flow/SharedFlowSlot;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/Continuation;", "resumesIn", "findSlotsToResumeLocked", "([Lkotlin/coroutines/Continuation;)[Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryEmit", "emit", "updateNewCollectorIndexLocked$kotlinx_coroutines_core", "()J", "updateNewCollectorIndexLocked", "oldIndex", "updateCollectorIndexLocked$kotlinx_coroutines_core", "(J)[Lkotlin/coroutines/Continuation;", "updateCollectorIndexLocked", "createSlot", "()Lkotlinx/coroutines/flow/SharedFlowSlot;", "size", "createSlotArray", "(I)[Lkotlinx/coroutines/flow/SharedFlowSlot;", "resetReplayCache", "Lkotlin/coroutines/CoroutineContext;", "context", "capacity", "Lkotlinx/coroutines/flow/Flow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "I", "Lkotlinx/coroutines/channels/BufferOverflow;", "buffer", "[Ljava/lang/Object;", "replayIndex", "J", "minCollectorIndex", "bufferSize", "queueSize", "getHead", "head", "getReplaySize", "()I", "replaySize", "getTotalSize", "totalSize", "getBufferEndIndex", "bufferEndIndex", "getQueueEndIndex", "queueEndIndex", "getLastReplayedLocked", "()Ljava/lang/Object;", "getLastReplayedLocked$annotations", "lastReplayedLocked", "Emitter", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedFlow.kt\nkotlinx/coroutines/flow/SharedFlowImpl\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/AbstractSharedFlow\n+ 7 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 8 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,741:1\n26#2:742\n26#2:745\n26#2:764\n26#2:767\n26#2:776\n26#2:787\n26#2:797\n16#3:743\n16#3:746\n16#3:765\n16#3:768\n16#3:777\n16#3:788\n16#3:798\n1307#4:744\n1#5:747\n91#6,2:748\n93#6,2:751\n95#6:754\n91#6,2:769\n93#6,2:772\n95#6:775\n91#6,2:790\n93#6,2:793\n95#6:796\n13805#7:750\n13806#7:753\n13805#7:771\n13806#7:774\n13805#7:792\n13806#7:795\n433#8,9:755\n442#8:766\n433#8,9:778\n442#8:789\n*S KotlinDebug\n*F\n+ 1 SharedFlow.kt\nkotlinx/coroutines/flow/SharedFlowImpl\n*L\n366#1:742\n406#1:745\n499#1:764\n520#1:767\n636#1:776\n671#1:787\n699#1:797\n366#1:743\n406#1:746\n499#1:765\n520#1:768\n636#1:777\n671#1:788\n699#1:798\n388#1:744\n468#1:748,2\n468#1:751,2\n468#1:754\n543#1:769,2\n543#1:772,2\n543#1:775\n686#1:790,2\n686#1:793,2\n686#1:796\n468#1:750\n468#1:753\n543#1:771\n543#1:774\n686#1:792\n686#1:795\n497#1:755,9\n497#1:766\n670#1:778,9\n670#1:789\n*E\n"})
/* loaded from: classes5.dex */
public class SharedFlowImpl<T> extends AbstractSharedFlow<SharedFlowSlot> implements MutableSharedFlow<T>, Flow, FusibleFlow<T> {
    private Object[] buffer;
    private final int bufferCapacity;
    private int bufferSize;
    private long minCollectorIndex;
    private final BufferOverflow onBufferOverflow;
    private int queueSize;
    private final int replay;
    private long replayIndex;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B3\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "Lkotlinx/coroutines/DisposableHandle;", "flow", "Lkotlinx/coroutines/flow/SharedFlowImpl;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "cont", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/coroutines/flow/SharedFlowImpl;JLjava/lang/Object;Lkotlin/coroutines/Continuation;)V", "dispose", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Emitter implements DisposableHandle {

        @JvmField
        public final Continuation<Unit> cont;

        @JvmField
        public final SharedFlowImpl<?> flow;

        @JvmField
        public long index;

        @JvmField
        public final Object value;

        /* JADX WARN: Multi-variable type inference failed */
        public Emitter(SharedFlowImpl<?> r1, long r2, Object r4, Continuation<? super Unit> r5) {
            this.flow = r1;
            this.index = r2;
            this.value = r4;
            this.cont = r5;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            SharedFlowImpl.access$cancelEmitter(this.flow, this);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[BufferOverflow.values().length];
            r0[BufferOverflow.SUSPEND.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[BufferOverflow.DROP_LATEST.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[BufferOverflow.DROP_OLDEST.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.SharedFlowImpl", f = "SharedFlow.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {387, 394, 397}, m = "collect$suspendImpl", n = {"$this", "collector", "slot", "$this", "collector", "slot", "collectorJob", "newValue", "$this", "collector", "slot", "collectorJob", "newValue"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.SharedFlowImpl$collect$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SharedFlowImpl<T> this$0;

        public AnonymousClass1(SharedFlowImpl<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return SharedFlowImpl.collect$suspendImpl(this.this$0, null, this);
        }
    }

    public SharedFlowImpl(int r1, int r2, BufferOverflow r3) {
        this.replay = r1;
        this.bufferCapacity = r2;
        this.onBufferOverflow = r3;
    }

    public static final /* synthetic */ void access$cancelEmitter(SharedFlowImpl r0, Emitter r1) {
        r0.cancelEmitter(r1);
    }

    public static final /* synthetic */ void access$enqueueLocked(SharedFlowImpl r0, Object r1) {
        r0.enqueueLocked(r1);
    }

    public static final /* synthetic */ Continuation[] access$findSlotsToResumeLocked(SharedFlowImpl r0, Continuation[] r1) {
        return r0.findSlotsToResumeLocked(r1);
    }

    public static final /* synthetic */ int access$getBufferCapacity$p(SharedFlowImpl r0) {
        return r0.bufferCapacity;
    }

    public static final /* synthetic */ long access$getHead(SharedFlowImpl r2) {
        return r2.getHead();
    }

    public static final /* synthetic */ int access$getQueueSize$p(SharedFlowImpl r0) {
        return r0.queueSize;
    }

    public static final /* synthetic */ int access$getTotalSize(SharedFlowImpl r0) {
        return r0.getTotalSize();
    }

    public static final /* synthetic */ void access$setQueueSize$p(SharedFlowImpl r0, int r1) {
        r0.queueSize = r1;
    }

    public static final /* synthetic */ boolean access$tryEmitLocked(SharedFlowImpl r0, Object r1) {
        return r0.tryEmitLocked(r1);
    }

    public static final /* synthetic */ long access$tryPeekLocked(SharedFlowImpl r0, SharedFlowSlot r1) {
        return r0.tryPeekLocked(r1);
    }

    private final Object awaitValue(SharedFlowSlot r6, Continuation<? super Unit> r7) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r7), 1);
        r0.initCancellability();
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (access$tryPeekLocked(this, r6) >= 0) goto L9;
        r6.cont = r0;     // Catch: Throwable -> L7
    L10:
        Unit r62 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(this);
        Object r5 = r0.getResult();
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L16;
        DebugProbesKt.probeCoroutineSuspended(r7);
    L16:
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L19;
        return r5;
    L19:
        return Unit.INSTANCE;
    L9:
        Result.Companion r63 = Result.Companion;     // Catch: Throwable -> L7
        r0.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));     // Catch: Throwable -> L7
        goto L10
    }

    private final void cancelEmitter(Emitter r5) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (r5.index < getHead()) goto L10;
        Object[] r0 = this.buffer;     // Catch: Throwable -> L8
        if (SharedFlowKt.access$getBufferAt(r0, r5.index) != r5) goto L10;
        SharedFlowKt.access$setBufferAt(r0, r5.index, SharedFlowKt.NO_VALUE);     // Catch: Throwable -> L8
        cleanupTailLocked();     // Catch: Throwable -> L8
    L10:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(this);
    }

    private final void cleanupTailLocked() {
        if (this.bufferCapacity == 0) goto L5;
    L7:
        Object[] r0 = this.buffer;
    L9:
        if (this.queueSize <= 0) goto L16;
        if (SharedFlowKt.access$getBufferAt(r0, (getHead() + getTotalSize()) - 1) != SharedFlowKt.NO_VALUE) goto L17;
        this.queueSize--;
        SharedFlowKt.access$setBufferAt(r0, getHead() + getTotalSize(), null);
        goto L9
    L17:
        return;
    L16:
        return;
    L5:
        if (this.queueSize > 1) goto L7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r5v1, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlow] */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowImpl] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r9v0, types: [kotlinx.coroutines.flow.FlowCollector<? super T>] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.Object, kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r9v9, types: [kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00c9 -> B:16:0x003e). Please report as a decompilation issue!!! */
    public static /* synthetic */ <T> Object collect$suspendImpl(SharedFlowImpl<T> r8, FlowCollector<? super T> r9, Continuation<?> r10) {
        if ((r10 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 != 0) goto L11;
        ResultKt.throwOnFailure(r102);
        SharedFlowSlot r103 = r8.allocateSlot();
        FlowCollector r104 = r9;
        r9 = r103;
    L50:
        Job r22 = (Job) r0.getContext().get(Job.Key);     // Catch: Throwable -> L42
        Object r92 = r9;
    L31:
        ?? r5 = r8;
        Job r82 = r22;
        ?? r23 = r104;
        r9 = r92;
    L32:
        Object r105 = r5.tryTakeValue((SharedFlowSlot) r9);     // Catch: Throwable -> L17
        if (r105 != SharedFlowKt.NO_VALUE) goto L37;
        r0.L$0 = r5;     // Catch: Throwable -> L17
        r0.L$1 = r23;     // Catch: Throwable -> L17
        r0.L$2 = (Object) r9;     // Catch: Throwable -> L17
        r0.L$3 = r82;     // Catch: Throwable -> L17
        r0.L$4 = SpillingKt.nullOutSpilledVariable(r105);     // Catch: Throwable -> L17
        r0.label = 2;     // Catch: Throwable -> L17
        if (r5.awaitValue((SharedFlowSlot) r9, r0) != r12) goto L32;
    L41:
        return r12;
    L37:
        if (r82 == null) goto L39;
        JobKt.ensureActive(r82);     // Catch: Throwable -> L17
    L39:
        r0.L$0 = r5;     // Catch: Throwable -> L17
        r0.L$1 = r23;     // Catch: Throwable -> L17
        r0.L$2 = (Object) r9;     // Catch: Throwable -> L17
        r0.L$3 = r82;     // Catch: Throwable -> L17
        r0.L$4 = SpillingKt.nullOutSpilledVariable(r105);     // Catch: Throwable -> L17
        r0.label = 3;     // Catch: Throwable -> L17
        Object r106 = r23.emit(r105, r0);     // Catch: Throwable -> L17
        FlowCollector r24 = r23;
        SharedFlowImpl<T> r52 = r5;
        Object r93 = r9;
        if (r106 == r12) goto L41;
    L16:
        r104 = r24;
        r22 = r82;
        r8 = r52;
        r92 = r93;
    L42:
        th = move-exception;
        r5 = r8;
        th = th;
    L44:
        r5.freeSlot(r9);
        throw th;
    L11:
        if (r2 == 1) goto L24;
        if (r2 == 2) goto L21;
        if (r2 != 3) goto L19;
        r82 = (Job) r0.L$3;
        Object r94 = (FlowCollector<? super T>) ((SharedFlowSlot) r0.L$2);
        FlowCollector r25 = (FlowCollector) r0.L$1;
        SharedFlowImpl<T> r53 = (SharedFlowImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L17
        r24 = r25;
        r52 = r53;
        r93 = r94;
        goto L16
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L21:
        r82 = (Job) r0.L$3;
        boolean r95 = (FlowCollector<? super T>) ((SharedFlowSlot) r0.L$2);
        FlowCollector r26 = (FlowCollector) r0.L$1;
        SharedFlowImpl r54 = (SharedFlowImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L17
        r23 = r26;
        r5 = r54;
        r9 = r95;
        goto L32
    L24:
        r9 = (SharedFlowSlot) r0.L$2;
        FlowCollector r83 = (FlowCollector) r0.L$1;
        SharedFlowImpl<T> r27 = (SharedFlowImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L27
        r104 = r83;
        r8 = r27;
        r9 = r9;
    L27:
        th = th;
        r5 = r27;
    L17:
        th = th;
    L7:
        r0 = new AnonymousClass1(r8, r10);
        goto L8
    }

    private final void correctCollectorIndexesOnDropOldest(long r9) {
        if (AbstractSharedFlow.access$getNCollectors(this) == 0) goto L16;
        AbstractSharedFlowSlot[] r0 = AbstractSharedFlow.access$getSlots(this);
        if (r0 == null) goto L16;
        int r1 = r0.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L16;
        AbstractSharedFlowSlot r3 = r0[r2];
        if (r3 == null) goto L15;
        SharedFlowSlot r32 = (SharedFlowSlot) r3;
        long r4 = r32.index;
        if (0 > r4) goto L15;
        if (r4 >= r9) goto L15;
        r32.index = r9;
    L15:
        r2 = r2 + 1;
    L16:
        this.minCollectorIndex = r9;
    }

    private final void dropOldestLocked() {
        SharedFlowKt.access$setBufferAt(this.buffer, getHead(), null);
        this.bufferSize--;
        long r0 = getHead() + 1;
        if (this.replayIndex >= r0) goto L6;
        this.replayIndex = r0;
    L6:
        if (this.minCollectorIndex >= r0) goto L9;
        correctCollectorIndexesOnDropOldest(r0);
        return;
    }

    public static /* synthetic */ <T> Object emit$suspendImpl(SharedFlowImpl<T> r1, T r2, Continuation<? super Unit> r3) {
        if (r1.tryEmit(r2) == true) goto L5;
        Object r12 = r1.emitSuspend(r2, r3);
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        return r12;
    L10:
        return Unit.INSTANCE;
    L5:
        return Unit.INSTANCE;
    }

    private final Object emitSuspend(T r9, Continuation<? super Unit> r10) {
        CancellableContinuationImpl r5 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r10), 1);
        r5.initCancellability();
        Continuation<Unit>[] r7 = AbstractSharedFlowKt.EMPTY_RESUMES;
        monitor-enter(this);
    L36:
        th = th;
        SharedFlowImpl<T> r1 = this;
    L17:
        Throwable r92 = th;
    L38:
        monitor-exit(r1);
        throw r92;
    L5:
        if (access$tryEmitLocked(this, r9) == true) goto L42;
        r1 = this;
        Emitter r0 = new Emitter(r1, access$getHead(this) + access$getTotalSize(this), r9, r5);     // Catch: Throwable -> L16
        access$enqueueLocked(r1, r0);     // Catch: Throwable -> L16
        access$setQueueSize$p(r1, access$getQueueSize$p(r1) + 1);     // Catch: Throwable -> L16
        if (access$getBufferCapacity$p(r1) != 0) goto L18;
        r7 = access$findSlotsToResumeLocked(r1, r7);     // Catch: Throwable -> L16
    L18:
        Continuation<Unit>[] r93 = r7;
    L19:
        monitor-exit(r1);
        if (r0 == null) goto L22;
        CancellableContinuationKt.disposeOnCancellation(r5, r0);
    L22:
        int r8 = r93.length;
        int r02 = 0;
    L23:
        if (r02 >= r8) goto L28;
        Continuation<Unit> r12 = r93[r02];
        if (r12 == null) goto L27;
        Result.Companion r2 = Result.Companion;
        r12.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
    L27:
        r02 = r02 + 1;
        goto L23
    L28:
        Object r82 = r5.getResult();
        if (r82 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L32;
        DebugProbesKt.probeCoroutineSuspended(r10);
    L32:
        if (r82 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L35;
        return r82;
    L35:
        return Unit.INSTANCE;
    L16:
        th = th;
        goto L17
    L42:
        Result.Companion r94 = Result.Companion;     // Catch: Throwable -> L8
        r5.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));     // Catch: Throwable -> L8
        r93 = access$findSlotsToResumeLocked(this, r7);     // Catch: Throwable -> L8
        r0 = null;
        r1 = this;
    L8:
        th = move-exception;
        r92 = th;
        r1 = this;
        goto L38
    }

    private final void enqueueLocked(Object r7) {
        int r0 = getTotalSize();
        Object[] r1 = this.buffer;
        if (r1 != null) goto L6;
        r1 = growBuffer(null, 0, 2);
    L8:
        SharedFlowKt.access$setBufferAt(r1, getHead() + r0, r7);
        return;
    L6:
        if (r0 < r1.length) goto L8;
        r1 = growBuffer(r1, r0, r1.length * 2);
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Continuation<Unit>[] findSlotsToResumeLocked(Continuation<Unit>[] r11) {
        int r0 = r11.length;
        if (AbstractSharedFlow.access$getNCollectors(this) == 0) goto L21;
        AbstractSharedFlowSlot[] r1 = AbstractSharedFlow.access$getSlots(this);
        if (r1 == null) goto L21;
        int r2 = r1.length;
        int r3 = 0;
        r11 = r11;
    L7:
        if (r3 >= r2) goto L21;
        AbstractSharedFlowSlot r4 = r1[r3];
        if (r4 == null) goto L19;
        SharedFlowSlot r42 = (SharedFlowSlot) r4;
        Continuation<? super Unit> r5 = r42.cont;
        if (r5 == null) goto L19;
        if (tryPeekLocked(r42) < 0) goto L19;
        int r6 = r11.length;
        r11 = r11;
        if (r0 < r6) goto L18;
        r11 = Arrays.copyOf(r11, Math.max(2, r11.length * 2));
    L18:
        r11[r0] = r5;
        r42.cont = null;
        r0 = r0 + 1;
    L19:
        r3 = r3 + 1;
        r11 = r11;
    L21:
        return r11;
    }

    private final long getBufferEndIndex() {
        return getHead() + this.bufferSize;
    }

    private final long getHead() {
        return Math.min(this.minCollectorIndex, this.replayIndex);
    }

    private final Object getPeekedValueLockedAt(long r1) {
        Object r0 = SharedFlowKt.access$getBufferAt(this.buffer, r1);
        if ((r0 instanceof Emitter) == true) goto L5;
        return r0;
    L5:
        return ((Emitter) r0).value;
    }

    private final long getQueueEndIndex() {
        return (getHead() + this.bufferSize) + this.queueSize;
    }

    private final int getReplaySize() {
        return (int) ((getHead() + this.bufferSize) - this.replayIndex);
    }

    private final int getTotalSize() {
        return this.bufferSize + this.queueSize;
    }

    private final Object[] growBuffer(Object[] r6, int r7, int r8) {
        if (r8 <= 0) goto L10;
        Object[] r82 = new Object[r8];
        this.buffer = r82;
        if (r6 == null) goto L9;
        long r0 = getHead();
        int r5 = 0;
    L7:
        if (r5 >= r7) goto L9;
        long r2 = r5 + r0;
        SharedFlowKt.access$setBufferAt(r82, r2, SharedFlowKt.access$getBufferAt(r6, r2));
        r5 = r5 + 1;
    L9:
        return r82;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Buffer size overflow");
        return null;
    }

    private final boolean tryEmitLocked(T r15) {
        if (getNCollectors() != 0) goto L7;
        return tryEmitNoCollectorsLocked(r15);
    L7:
        if (this.bufferSize >= this.bufferCapacity) goto L9;
    L22:
        enqueueLocked(r15);
        int r152 = this.bufferSize + 1;
        this.bufferSize = r152;
        if (r152 <= this.bufferCapacity) goto L26;
        dropOldestLocked();
    L26:
        if (getReplaySize() <= this.replay) goto L28;
        updateBufferLocked(this.replayIndex + 1, this.minCollectorIndex, getBufferEndIndex(), getQueueEndIndex());
    L28:
        return true;
    L9:
        if (this.minCollectorIndex > this.replayIndex) goto L22;
        BufferOverflow r0 = this.onBufferOverflow;
        int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
        if (r02 != 1) goto L13;
        return false;
    L13:
        if (r02 != 2) goto L15;
        return true;
    L15:
        if (r02 == 3) goto L22;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return false;
    }

    private final boolean tryEmitNoCollectorsLocked(T r7) {
        if (this.replay != 0) goto L5;
        return true;
    L5:
        enqueueLocked(r7);
        int r72 = this.bufferSize + 1;
        this.bufferSize = r72;
        if (r72 <= this.replay) goto L8;
        dropOldestLocked();
    L8:
        this.minCollectorIndex = getHead() + this.bufferSize;
        return true;
    }

    private final long tryPeekLocked(SharedFlowSlot r7) {
        long r0 = r7.index;
        if (r0 >= getBufferEndIndex()) goto L6;
    L14:
        return r0;
    L6:
        if (this.bufferCapacity <= 0) goto L9;
        return -1;
    L9:
        if (r0 <= getHead()) goto L12;
        return -1;
    L12:
        if (this.queueSize != 0) goto L14;
        return -1;
    }

    private final Object tryTakeValue(SharedFlowSlot r9) {
        Continuation<Unit>[] r0 = AbstractSharedFlowKt.EMPTY_RESUMES;
        monitor-enter(this);
        long r1 = tryPeekLocked(r9);     // Catch: Throwable -> L7
        if (r1 >= 0) goto L9;
        Object r92 = SharedFlowKt.NO_VALUE;     // Catch: Throwable -> L7
    L11:
        monitor-exit(this);
        int r8 = r0.length;
        int r12 = 0;
    L13:
        if (r12 >= r8) goto L18;
        Continuation<Unit> r2 = r0[r12];
        if (r2 == null) goto L17;
        Result.Companion r3 = Result.Companion;
        r2.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
    L17:
        r12 = r12 + 1;
        goto L13
    L18:
        return r92;
    L9:
        long r32 = r9.index;     // Catch: Throwable -> L7
        Object r02 = getPeekedValueLockedAt(r1);     // Catch: Throwable -> L7
        r9.index = r1 + 1;     // Catch: Throwable -> L7
        r0 = updateCollectorIndexLocked$kotlinx_coroutines_core(r32);     // Catch: Throwable -> L7
        r92 = r02;
    L7:
        th = move-exception;
        throw th;
    }

    private final void updateBufferLocked(long r7, long r9, long r11, long r13) {
        long r0 = Math.min(r9, r7);
        long r2 = getHead();
    L4:
        if (r2 >= r0) goto L6;
        SharedFlowKt.access$setBufferAt(this.buffer, r2, null);
        r2 = r2 + 1;
        goto L4
    L6:
        this.replayIndex = r7;
        this.minCollectorIndex = r9;
        this.bufferSize = (int) (r11 - r0);
        this.queueSize = (int) (r13 - r11);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r1, Continuation<?> r2) {
        return collect$suspendImpl(this, r1, r2);
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public SharedFlowSlot createSlot() {
        return new SharedFlowSlot();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public /* bridge */ /* synthetic */ AbstractSharedFlowSlot createSlot() {
        return createSlot();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public SharedFlowSlot[] createSlotArray(int r1) {
        return new SharedFlowSlot[r1];
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public /* bridge */ /* synthetic */ AbstractSharedFlowSlot[] createSlotArray(int r1) {
        return createSlotArray(r1);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow, kotlinx.coroutines.flow.FlowCollector
    public Object emit(T r1, Continuation<? super Unit> r2) {
        return emit$suspendImpl(this, r1, r2);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(CoroutineContext r1, int r2, BufferOverflow r3) {
        return SharedFlowKt.fuseSharedFlow(this, r1, r2, r3);
    }

    public final T getLastReplayedLocked() {
        return (T) SharedFlowKt.access$getBufferAt(this.buffer, (this.replayIndex + getReplaySize()) - 1);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        monitor-enter(this);
        SharedFlowImpl<T> r1 = this;
        r1.updateBufferLocked(getBufferEndIndex(), this.minCollectorIndex, getBufferEndIndex(), getQueueEndIndex());     // Catch: Throwable -> L8
        Unit r10 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r1);
        return;
    L8:
        th = th;
    L9:
        Throwable r102 = th;
        monitor-exit(r1);
        throw r102;
    L10:
        th = th;
        r1 = this;
        goto L9
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T r5) {
        Continuation<Unit>[] r0 = AbstractSharedFlowKt.EMPTY_RESUMES;
        monitor-enter(this);
        int r1 = 0;
        if (tryEmitLocked(r5) == false) goto L10;
        r0 = findSlotsToResumeLocked(r0);     // Catch: Throwable -> L8
        boolean r52 = true;
    L11:
        monitor-exit(this);
        int r4 = r0.length;
    L13:
        if (r1 >= r4) goto L18;
        Continuation<Unit> r2 = r0[r1];
        if (r2 == null) goto L17;
        Result.Companion r3 = Result.Companion;
        r2.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
    L17:
        r1 = r1 + 1;
        goto L13
    L18:
        return r52;
    L10:
        r52 = false;
    L8:
        th = move-exception;
        throw th;
    }

    public final Continuation<Unit>[] updateCollectorIndexLocked$kotlinx_coroutines_core(long r22) {
        if (r22 > this.minCollectorIndex) goto L5;
        long r1 = getHead();
        long r3 = this.bufferSize + r1;
        if (this.bufferCapacity == 0) goto L9;
    L11:
        int r8 = 0;
        if (AbstractSharedFlow.access$getNCollectors(this) == 0) goto L26;
        AbstractSharedFlowSlot[] r5 = AbstractSharedFlow.access$getSlots(this);
        if (r5 == null) goto L26;
        int r9 = r5.length;
        int r10 = 0;
    L16:
        if (r10 >= r9) goto L26;
        AbstractSharedFlowSlot r11 = r5[r10];
        if (r11 == null) goto L24;
        long r112 = ((SharedFlowSlot) r11).index;
        if (0 > r112) goto L24;
        if (r112 >= r3) goto L24;
        r3 = r112;
    L24:
        r10 = r10 + 1;
    L26:
        if (r3 <= this.minCollectorIndex) goto L28;
        long r92 = getBufferEndIndex();
        int r52 = getNCollectors();
        int r113 = this.queueSize;
        if (r52 <= 0) goto L32;
        r113 = Math.min(r113, this.bufferCapacity - ((int) (r92 - r3)));
    L32:
        Continuation<Unit>[] r53 = AbstractSharedFlowKt.EMPTY_RESUMES;
        long r12 = this.queueSize + r92;
        if (r113 <= 0) goto L46;
        Continuation<Unit>[] r54 = new Continuation[r113];
        Object[] r14 = this.buffer;
        long r222 = 1;
        long r6 = r92;
    L36:
        if (r92 >= r12) goto L45;
        Object r15 = SharedFlowKt.access$getBufferAt(r14, r92);
        long r16 = r3;
        Symbol r32 = SharedFlowKt.NO_VALUE;
        if (r15 == r32) goto L43;
        Emitter r152 = (Emitter) r15;
        int r4 = r8 + 1;
        Continuation<Unit>[] r18 = r54;
        r18[r8] = r152.cont;
        SharedFlowKt.access$setBufferAt(r14, r92, r32);
        SharedFlowKt.access$setBufferAt(r14, r6, r152.value);
        long r55 = r6 + 1;
        if (r4 >= r113) goto L42;
        r8 = r4;
        r6 = r55;
    L44:
        r92 = r92 + 1;
        r3 = r16;
        r54 = r18;
    L42:
        Continuation<Unit>[] r93 = r18;
    L47:
        long r13 = Math.max(this.replayIndex, Math.max(r1, r55 - this.replay));
        if (this.bufferCapacity != 0) goto L55;
        if (r13 >= r12) goto L55;
        if (Intrinsics.areEqual(SharedFlowKt.access$getBufferAt(this.buffer, r13), SharedFlowKt.NO_VALUE) == false) goto L55;
        r55 = r55 + r222;
        r13 = r13 + r222;
    L55:
        if (getNCollectors() != 0) goto L58;
        long r33 = r55;
    L59:
        updateBufferLocked(r13, r33, r55, r12);
        cleanupTailLocked();
        if (r93.length != 0) goto L63;
        return r93;
    L63:
        return findSlotsToResumeLocked(r93);
    L58:
        r33 = r16;
        goto L59
    L43:
        r18 = r54;
        goto L44
    L45:
        r16 = r3;
        r18 = r54;
        r55 = r6;
        goto L42
    L46:
        r16 = r3;
        r222 = 1;
        r93 = r53;
        r55 = r92;
        goto L47
    L28:
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    L9:
        if (this.queueSize <= 0) goto L11;
        r3 = r3 + 1;
        goto L11
    L5:
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final long updateNewCollectorIndexLocked$kotlinx_coroutines_core() {
        long r0 = this.replayIndex;
        if (r0 >= this.minCollectorIndex) goto L5;
        this.minCollectorIndex = r0;
    L5:
        return r0;
    }
}
