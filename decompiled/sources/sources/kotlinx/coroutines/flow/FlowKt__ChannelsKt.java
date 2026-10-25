package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a.\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@¢\u0006\u0004\b\u0005\u0010\u0006\u001a6\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0082@¢\u0006\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "channel", _UrlKt.FRAGMENT_ENCODE_SET, "emitAll", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "consume", "emitAllImpl$FlowKt__ChannelsKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitAllImpl", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes.dex */
abstract /* synthetic */ class FlowKt__ChannelsKt {
    public static final /* synthetic */ Object access$emitAllImpl$FlowKt__ChannelsKt(FlowCollector r0, ReceiveChannel r1, boolean r2, Continuation r3) {
        return emitAllImpl$FlowKt__ChannelsKt(r0, r1, r2, r3);
    }

    public static final <T> Object emitAll(FlowCollector<? super T> r1, ReceiveChannel<? extends T> r2, Continuation<? super Unit> r3) {
        Object r12 = emitAllImpl$FlowKt__ChannelsKt(r1, r2, true, r3);
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r12;
    L6:
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0098 -> B:14:0x003a). Please report as a decompilation issue!!! */
    private static final <T> Object emitAllImpl$FlowKt__ChannelsKt(FlowCollector<? super T> r8, ReceiveChannel<? extends T> r9, boolean r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof FlowKt__ChannelsKt$emitAllImpl$1) == false) goto L7;
        FlowKt__ChannelsKt$emitAllImpl$1 r0 = (FlowKt__ChannelsKt$emitAllImpl$1) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L22;
        if (r2 == 1) goto L19;
        if (r2 != 2) goto L17;
        r10 = r0.Z$0;
        ChannelIterator<? extends T> r82 = (ChannelIterator) r0.L$2;
        r9 = (ReceiveChannel) r0.L$1;
        FlowCollector<? super T> r22 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L15
        FlowCollector<? super T> r23 = r22;
    L14:
        ChannelIterator<? extends T> r113 = r82;
        r8 = r23;
    L24:
        r0.L$0 = r8;     // Catch: Throwable -> L15
        r0.L$1 = r9;     // Catch: Throwable -> L15
        r0.L$2 = r113;     // Catch: Throwable -> L15
        r0.L$3 = null;     // Catch: Throwable -> L15
        r0.Z$0 = r10;     // Catch: Throwable -> L15
        r0.label = 1;     // Catch: Throwable -> L15
        Object r24 = r113.hasNext(r0);     // Catch: Throwable -> L15
        if (r24 == r12) goto L32;
        ?? r25 = r8;
        r82 = r113;
        r112 = r24;
    L29:
        if (((Boolean) r112).booleanValue() == false) goto L33;
        T r114 = r82.next();     // Catch: Throwable -> L15
        r0.L$0 = r25;     // Catch: Throwable -> L15
        r0.L$1 = r9;     // Catch: Throwable -> L15
        r0.L$2 = r82;     // Catch: Throwable -> L15
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r114);     // Catch: Throwable -> L15
        r0.Z$0 = r10;     // Catch: Throwable -> L15
        r0.label = 2;     // Catch: Throwable -> L15
        Object r115 = r25.emit(r114, r0);     // Catch: Throwable -> L15
        r23 = r25;
        if (r115 != r12) goto L14;
    L33:
        if (r10 == false) goto L36;
        ChannelsKt.cancelConsumed(r9, null);
    L36:
        return Unit.INSTANCE;
    L32:
        return r12;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        r10 = r0.Z$0;
        r82 = (ChannelIterator) r0.L$2;
        r9 = (ReceiveChannel) r0.L$1;
        FlowCollector r26 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L15
        r25 = r26;
        goto L29
    L22:
        ResultKt.throwOnFailure(r112);
        FlowKt.ensureActive(r8);
        r113 = r9.iterator();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        if (r10 == false) goto L41;
        ChannelsKt.cancelConsumed(r9, th);
    L41:
        throw th;
    L7:
        r0 = new FlowKt__ChannelsKt$emitAllImpl$1(r11);
        goto L8
    }
}
