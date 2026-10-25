package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"kotlinx/coroutines/flow/FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1", "Lkotlinx/coroutines/flow/FlowCollector;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLimit.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt$collectWhile$collector$1\n+ 2 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n*L\n1#1,141:1\n84#2,6:142\n*E\n"})
/* loaded from: classes5.dex */
public final class FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1<T> implements FlowCollector<T> {
    final /* synthetic */ Function2 $predicate$inlined;
    final /* synthetic */ FlowCollector $this_flow$inlined;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1", f = "Limit.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {142, 143}, m = "emit", n = {"value", "$completion", "value", "$i$a$-collectWhile-FlowKt__LimitKt$takeWhile$1$1", "value", "$completion", "value", "$i$a$-collectWhile-FlowKt__LimitKt$takeWhile$1$1"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1 this$0;

        public AnonymousClass1(FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1 r1, Continuation r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.emit(null, this);
        }
    }

    public FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1(Function2 r1, FlowCollector r2) {
        this.$predicate$inlined = r1;
        this.$this_flow$inlined = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T r8, Continuation<? super Unit> r9) {
        if ((r9 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        Continuation r7 = (Continuation) r0.L$1;
        ResultKt.throwOnFailure(r92);
    L26:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        int r82 = r0.I$0;
        Object r22 = r0.L$2;
        Continuation r4 = (Continuation) r0.L$1;
        Object r5 = r0.L$0;
        ResultKt.throwOnFailure(r92);
        int r23 = r82;
        r8 = r22;
    L21:
        if (((Boolean) r92).booleanValue() == false) goto L28;
        FlowCollector r72 = this.$this_flow$inlined;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r4);
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r8);
        r0.I$0 = r23;
        r0.label = 2;
        if (r72.emit(r8, r0) != r12) goto L26;
    L24:
        return r12;
    L28:
        throw new AbortFlowException(this);
    L16:
        ResultKt.throwOnFailure(r92);
        Function2 r93 = this.$predicate$inlined;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r8);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);
        r0.L$2 = r8;
        r23 = 0;
        r0.I$0 = 0;
        r0.label = 1;
        InlineMarker.mark(6);
        r92 = r93.invoke(r8, r0);
        InlineMarker.mark(7);
        if (r92 == r12) goto L24;
        r5 = r8;
        r4 = r0;
    L7:
        r0 = new AnonymousClass1(this, r9);
        goto L8
    }
}
