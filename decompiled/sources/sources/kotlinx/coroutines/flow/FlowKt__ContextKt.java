package kotlinx.coroutines.flow;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a7\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a#\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"T", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "buffer", "(Lkotlinx/coroutines/flow/Flow;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "conflate", "(Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes.dex */
abstract /* synthetic */ class FlowKt__ContextKt {
    public static final <T> Flow<T> buffer(Flow<? extends T> r7, int r8, BufferOverflow r9) {
        if (r8 < 0) goto L5;
    L10:
        if (r8 == (-1)) goto L12;
    L16:
        if (r8 != (-1)) goto L18;
        r9 = BufferOverflow.DROP_OLDEST;
        r8 = 0;
    L18:
        int r2 = r8;
        BufferOverflow r3 = r9;
        if ((r7 instanceof FusibleFlow) == false) goto L23;
        return FusibleFlow.fuse$default((FusibleFlow) r7, null, r2, r3, 1, null);
    L23:
        return new ChannelFlowOperatorImpl(r7, null, r2, r3, 2, null);
    L12:
        if (r9 == BufferOverflow.SUSPEND) goto L16;
        f$$ExternalSyntheticBUOutline1.m("CONFLATED capacity cannot be used with non-default onBufferOverflow");
        return null;
    L5:
        if (r8 == (-2)) goto L10;
        if (r8 == (-1)) goto L10;
        Utf8$$ExternalSyntheticBUOutline1.m("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was ", r8);
        return null;
    }

    public static /* synthetic */ Flow buffer$default(Flow r0, int r1, BufferOverflow r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = -2;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = BufferOverflow.SUSPEND;
    L9:
        return FlowKt.buffer(r0, r1, r2);
    }

    public static final <T> Flow<T> conflate(Flow<? extends T> r3) {
        return FlowKt.buffer$default(r3, -1, null, 2, null);
    }
}
