package kotlinx.coroutines.flow.internal;

import com.android.dx.io.Opcodes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a-\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001aZ\u0010\u000e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00028\u00012\b\b\u0002\u0010\n\u001a\u00020\t2\"\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000bH\u0080@¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/CoroutineContext;", "emitContext", "withUndispatchedContextCollector", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/flow/FlowCollector;", "V", "newContext", "value", _UrlKt.FRAGMENT_ENCODE_SET, "countOrElement", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "block", "withContextUndispatched", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nChannelFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChannelFlow.kt\nkotlinx/coroutines/flow/internal/ChannelFlowKt\n+ 2 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,240:1\n103#2,5:241\n*S KotlinDebug\n*F\n+ 1 ChannelFlow.kt\nkotlinx/coroutines/flow/internal/ChannelFlowKt\n*L\n220#1:241,5\n*E\n"})
/* loaded from: classes5.dex */
public abstract class ChannelFlowKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowKt", f = "ChannelFlow.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {Opcodes.AND_INT_LIT8}, m = "withContextUndispatched", n = {"newContext", "value", "countOrElement", "block", "context$iv", "countOrElement$iv", "oldValue$iv", "$i$f$withCoroutineContext", "$i$a$-withCoroutineContext-ChannelFlowKt$withContextUndispatched$2"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowKt$withContextUndispatched$1, reason: invalid class name */
    public static final class AnonymousClass1<T, V> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return ChannelFlowKt.withContextUndispatched(null, null, null, null, this);
        }
    }

    public static final /* synthetic */ FlowCollector access$withUndispatchedContextCollector(FlowCollector r0, CoroutineContext r1) {
        return withUndispatchedContextCollector(r0, r1);
    }

    public static final <T, V> Object withContextUndispatched(CoroutineContext r5, V r6, Object r7, Function2<? super V, ? super Continuation<? super T>, ? extends Object> r8, Continuation<? super T> r9) {
        if ((r9 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        AnonymousClass1 r52 = (AnonymousClass1) r0.L$7;
        Object r53 = r0.L$6;
        CoroutineContext r62 = (CoroutineContext) r0.L$4;
        Function2 r72 = (Function2) r0.L$3;
        CoroutineContext r73 = (CoroutineContext) r0.L$0;
        ResultKt.throwOnFailure(r92);     // Catch: Throwable -> L14
        Object r93 = r53;
        r5 = r62;
        Object r63 = r92;
    L30:
        ThreadContextKt.restoreThreadContext(r5, r93);
        return r63;
    L14:
        th = th;
        r93 = r53;
        r5 = r62;
    L32:
        ThreadContextKt.restoreThreadContext(r5, r93);
        throw th;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r92);
        r93 = ThreadContextKt.updateThreadContext(r5, r7);
        r0.L$0 = r5;     // Catch: Throwable -> L22
        r0.L$1 = r6;     // Catch: Throwable -> L22
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r7);     // Catch: Throwable -> L22
        r0.L$3 = r8;     // Catch: Throwable -> L22
        r0.L$4 = r5;     // Catch: Throwable -> L22
        r0.L$5 = SpillingKt.nullOutSpilledVariable(r7);     // Catch: Throwable -> L22
        r0.L$6 = r93;     // Catch: Throwable -> L22
        r0.L$7 = r0;     // Catch: Throwable -> L22
        r0.I$0 = 0;     // Catch: Throwable -> L22
        r0.I$1 = 0;     // Catch: Throwable -> L22
        r0.label = 1;     // Catch: Throwable -> L22
        StackFrameContinuation r74 = new StackFrameContinuation(r0, r5);     // Catch: Throwable -> L22
        if ((r8 instanceof BaseContinuationImpl) == true) goto L24;
        r63 = IntrinsicsKt.wrapWithContinuationImpl(r8, r6, r74);     // Catch: Throwable -> L22
    L26:
        if (r63 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L28;
        DebugProbesKt.probeCoroutineSuspended(r0);     // Catch: Throwable -> L22
    L28:
        if (r63 != r12) goto L30;
        return r12;
    L24:
        r63 = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(r8, 2)).invoke(r6, r74);     // Catch: Throwable -> L22
    L22:
        th = th;
    L7:
        r0 = new AnonymousClass1(r9);
        goto L8
    }

    public static /* synthetic */ Object withContextUndispatched$default(CoroutineContext r0, Object r1, Object r2, Function2 r3, Continuation r4, int r5, Object r6) {
        if ((r5 & 4) == 0) goto L6;
        r2 = ThreadContextKt.threadContextElements(r0);
    L6:
        return withContextUndispatched(r0, r1, r2, r3, r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> FlowCollector<T> withUndispatchedContextCollector(FlowCollector<? super T> r1, CoroutineContext r2) {
        if ((r1 instanceof SendingCollector) == false) goto L5;
        return r1;
    L5:
        if ((r1 instanceof NopCollector) == false) goto L8;
        return r1;
    L8:
        return new UndispatchedContextCollector(r1, r2);
    }
}
