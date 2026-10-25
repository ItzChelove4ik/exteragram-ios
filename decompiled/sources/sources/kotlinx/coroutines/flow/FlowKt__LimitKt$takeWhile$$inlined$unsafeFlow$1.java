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
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n*L\n1#1,111:1\n83#2:112\n124#2,17:113\n*S KotlinDebug\n*F\n+ 1 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n*L\n83#1:113,17\n*E\n"})
/* loaded from: classes.dex */
public final class FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function2 $predicate$inlined;
    final /* synthetic */ Flow $this_takeWhile$inlined;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1", f = "Limit.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {123}, m = "collect", n = {"collector", "$completion", "$this$takeWhile_u24lambda_u240", "$this$collectWhile$iv", "$completion$iv", "collector$iv", "$i$a$-unsafeFlow-FlowKt__LimitKt$takeWhile$1", "$i$f$collectWhile"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1 this$0;

        public AnonymousClass1(FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1 r1, Continuation r2) {
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

    public FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1(Flow r1, Function2 r2) {
        this.$this_takeWhile$inlined = r1;
        this.$predicate$inlined = r2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r5, Continuation<? super Unit> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1 r4 = (FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1) r0.L$5;
        AnonymousClass1 r52 = (AnonymousClass1) r0.L$4;
        Flow r53 = (Flow) r0.L$3;
        FlowCollector r54 = (FlowCollector) r0.L$2;
        Continuation r55 = (Continuation) r0.L$1;
        FlowCollector r56 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r62);     // Catch: AbortFlowException -> L14
    L26:
        return Unit.INSTANCE;
    L14:
        e = e;
    L24:
        FlowExceptions_commonKt.checkOwnership(e, r4);
        JobKt.ensureActive(r0.getContext());
        goto L26
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r62);
        Flow r63 = this.$this_takeWhile$inlined;
        FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1 r22 = new FlowKt__LimitKt$takeWhile$lambda$0$$inlined$collectWhile$1(this.$predicate$inlined, r5);
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: AbortFlowException -> L22
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);     // Catch: AbortFlowException -> L22
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: AbortFlowException -> L22
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r63);     // Catch: AbortFlowException -> L22
        r0.L$4 = SpillingKt.nullOutSpilledVariable(r0);     // Catch: AbortFlowException -> L22
        r0.L$5 = r22;     // Catch: AbortFlowException -> L22
        r0.I$0 = 0;     // Catch: AbortFlowException -> L22
        r0.I$1 = 0;     // Catch: AbortFlowException -> L22
        r0.label = 1;     // Catch: AbortFlowException -> L22
        if (r63.collect(r22, r0) != r12) goto L26;
        return r12;
    L22:
        e = e;
        r4 = r22;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }
}
