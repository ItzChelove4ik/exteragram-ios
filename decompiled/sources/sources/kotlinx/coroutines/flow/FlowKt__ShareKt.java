package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a=\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n\u001a-\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\f\u0010\r\u001aM\u0010\u0018\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0000*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a#\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0012¢\u0006\u0004\b\u0019\u0010\u001a\u001a#\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u001b¢\u0006\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"T", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/flow/SharingStarted;", "started", _UrlKt.FRAGMENT_ENCODE_SET, "replay", "Lkotlinx/coroutines/flow/SharedFlow;", "shareIn", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/flow/SharingStarted;I)Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlinx/coroutines/flow/SharingConfig;", "configureSharing$FlowKt__ShareKt", "(Lkotlinx/coroutines/flow/Flow;I)Lkotlinx/coroutines/flow/SharingConfig;", "configureSharing", "Lkotlin/coroutines/CoroutineContext;", "context", "upstream", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "shared", "initialValue", "Lkotlinx/coroutines/Job;", "launchSharing$FlowKt__ShareKt", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlinx/coroutines/flow/SharingStarted;Ljava/lang/Object;)Lkotlinx/coroutines/Job;", "launchSharing", "asSharedFlow", "(Lkotlinx/coroutines/flow/MutableSharedFlow;)Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/StateFlow;", "asStateFlow", "(Lkotlinx/coroutines/flow/MutableStateFlow;)Lkotlinx/coroutines/flow/StateFlow;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
@SourceDebugExtension({"SMAP\nShare.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Share.kt\nkotlinx/coroutines/flow/FlowKt__ShareKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,482:1\n1#2:483\n35#3,5:484\n*S KotlinDebug\n*F\n+ 1 Share.kt\nkotlinx/coroutines/flow/FlowKt__ShareKt\n*L\n382#1:484,5\n*E\n"})
/* loaded from: classes5.dex */
abstract /* synthetic */ class FlowKt__ShareKt {
    public static final <T> SharedFlow<T> asSharedFlow(MutableSharedFlow<T> r2) {
        return new ReadonlySharedFlow(r2, null);
    }

    public static final <T> StateFlow<T> asStateFlow(MutableStateFlow<T> r2) {
        return new ReadonlyStateFlow(r2, null);
    }

    private static final <T> SharingConfig<T> configureSharing$FlowKt__ShareKt(Flow<? extends T> r7, int r8) {
        int r0 = RangesKt.coerceAtLeast(r8, Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core()) - r8;
        if ((r7 instanceof ChannelFlow) == false) goto L21;
        ChannelFlow r1 = (ChannelFlow) r7;
        Flow<T> r2 = r1.dropChannelOperators();
        if (r2 == null) goto L21;
        int r3 = r1.capacity;
        if (r3 == (-3)) goto L13;
        if (r3 == (-2)) goto L13;
        if (r3 == 0) goto L13;
        r0 = r3;
    L19:
        return new SharingConfig(r2, r0, r1.onBufferOverflow, r1.context);
    L13:
        if (r1.onBufferOverflow != BufferOverflow.SUSPEND) goto L16;
        if (r3 != 0) goto L19;
    L15:
        r0 = 0;
        goto L19
    L16:
        if (r8 != 0) goto L15;
        r0 = 1;
    L21:
        return new SharingConfig(r7, r0, BufferOverflow.SUSPEND, EmptyCoroutineContext.INSTANCE);
    }

    private static final <T> Job launchSharing$FlowKt__ShareKt(CoroutineScope r7, CoroutineContext r8, Flow<? extends T> r9, MutableSharedFlow<T> r10, SharingStarted r11, T r12) {
        if (Intrinsics.areEqual(r11, SharingStarted.Companion.getEagerly()) == false) goto L5;
        CoroutineStart r0 = CoroutineStart.DEFAULT;
    L7:
        return BuildersKt.launch(r7, r8, r0, new FlowKt__ShareKt$launchSharing$1(r11, r9, r10, r12, null));
    L5:
        r0 = CoroutineStart.UNDISPATCHED;
        goto L7
    }

    public static final <T> SharedFlow<T> shareIn(Flow<? extends T> r8, CoroutineScope r9, SharingStarted r10, int r11) {
        SharingConfig r82 = configureSharing$FlowKt__ShareKt(r8, r11);
        MutableSharedFlow r5 = SharedFlowKt.MutableSharedFlow(r11, r82.extraBufferCapacity, r82.onBufferOverflow);
        return new ReadonlySharedFlow(r5, launchSharing$FlowKt__ShareKt(r9, r82.context, r82.upstream, r5, r10, SharedFlowKt.NO_VALUE));
    }
}
