package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0086@¢\u0006\u0004\b\u0002\u0010\u0003\u001aD\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086@¢\u0006\u0004\b\u0002\u0010\t\u001a\"\u0010\n\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0086@¢\u0006\u0004\b\n\u0010\u0003¨\u0006\u000b"}, d2 = {"T", "Lkotlinx/coroutines/flow/Flow;", "first", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "predicate", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstOrNull", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
@SourceDebugExtension({"SMAP\nReduce.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Reduce.kt\nkotlinx/coroutines/flow/FlowKt__ReduceKt\n+ 2 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n*L\n1#1,168:1\n124#2,17:169\n124#2,17:186\n124#2,17:203\n124#2,17:220\n124#2,17:237\n*S KotlinDebug\n*F\n+ 1 Reduce.kt\nkotlinx/coroutines/flow/FlowKt__ReduceKt\n*L\n68#1:169,17\n88#1:186,17\n102#1:203,17\n120#1:220,17\n133#1:237,17\n*E\n"})
/* loaded from: classes5.dex */
abstract /* synthetic */ class FlowKt__ReduceKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0, 0, 0}, l = {179}, m = "first", n = {"$this$first", "result", "$this$collectWhile$iv", "collector$iv", "$i$f$collectWhile"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.first(null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0, 0, 0, 0}, l = {179}, m = "first", n = {"$this$first", "predicate", "result", "$this$collectWhile$iv", "collector$iv", "$i$f$collectWhile"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3, reason: invalid class name */
    public static final class AnonymousClass3<T> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(Continuation<? super AnonymousClass3> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.first(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0, 0, 0}, l = {179}, m = "firstOrNull", n = {"$this$firstOrNull", "result", "$this$collectWhile$iv", "collector$iv", "$i$f$collectWhile"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01901<T> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C01901(Continuation<? super C01901> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.firstOrNull(null, this);
        }
    }

    public static final <T> Object first(Flow<? extends T> r6, Continuation<? super T> r7) {
        if ((r7 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        FlowCollector<? super Object> r62 = (FlowKt__ReduceKt$first$$inlined$collectWhile$1) r0.L$3;
        Flow r13 = (Flow) r0.L$2;
        Ref.ObjectRef r14 = (Ref.ObjectRef) r0.L$1;
        Flow r22 = (Flow) r0.L$0;
        ResultKt.throwOnFailure(r72);     // Catch: AbortFlowException -> L14
    L26:
        T r63 = r14.element;
        if (r63 == NullSurrogateKt.NULL) goto L29;
        return r63;
    L29:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("Expected at least one element");
        return null;
    L14:
        AbortFlowException e = e;
    L25:
        FlowExceptions_commonKt.checkOwnership(e, r62);
        JobKt.ensureActive(r0.getContext());
        goto L26
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r72);
        Ref.ObjectRef r73 = new Ref.ObjectRef();
        r73.element = (T) NullSurrogateKt.NULL;
        FlowCollector<? super Object> r23 = new FlowKt__ReduceKt$first$$inlined$collectWhile$1(r73);
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r6);     // Catch: AbortFlowException -> L23
        r0.L$1 = r73;     // Catch: AbortFlowException -> L23
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r6);     // Catch: AbortFlowException -> L23
        r0.L$3 = r23;     // Catch: AbortFlowException -> L23
        r0.I$0 = 0;     // Catch: AbortFlowException -> L23
        r0.label = 1;     // Catch: AbortFlowException -> L23
        if (r6.collect(r23, r0) != r12) goto L22;
        return r12;
    L22:
        r14 = r73;
    L23:
        e = move-exception;
        r14 = r73;
        e = e;
        r62 = r23;
    L7:
        r0 = new AnonymousClass1(r7);
        goto L8
    }

    public static final <T> Object first(Flow<? extends T> r6, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> r7, Continuation<? super T> r8) {
        if ((r8 instanceof AnonymousClass3) == false) goto L7;
        AnonymousClass3 r0 = (AnonymousClass3) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        FlowCollector<? super Object> r62 = (FlowKt__ReduceKt$first$$inlined$collectWhile$2) r0.L$4;
        Flow r72 = (Flow) r0.L$3;
        Ref.ObjectRef r73 = (Ref.ObjectRef) r0.L$2;
        Function2 r13 = (Function2) r0.L$1;
        Flow r14 = (Flow) r0.L$0;
        ResultKt.throwOnFailure(r82);     // Catch: AbortFlowException -> L14
    L26:
        T r63 = r73.element;
        if (r63 == NullSurrogateKt.NULL) goto L29;
        return r63;
    L29:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("Expected at least one element matching the predicate");
        return null;
    L14:
        AbortFlowException e = e;
    L25:
        FlowExceptions_commonKt.checkOwnership(e, r62);
        JobKt.ensureActive(r0.getContext());
        goto L26
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r82);
        Ref.ObjectRef r83 = new Ref.ObjectRef();
        r83.element = (T) NullSurrogateKt.NULL;
        FlowCollector<? super Object> r22 = new FlowKt__ReduceKt$first$$inlined$collectWhile$2(r7, r83);
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r6);     // Catch: AbortFlowException -> L23
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r7);     // Catch: AbortFlowException -> L23
        r0.L$2 = r83;     // Catch: AbortFlowException -> L23
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r6);     // Catch: AbortFlowException -> L23
        r0.L$4 = r22;     // Catch: AbortFlowException -> L23
        r0.I$0 = 0;     // Catch: AbortFlowException -> L23
        r0.label = 1;     // Catch: AbortFlowException -> L23
        if (r6.collect(r22, r0) != r12) goto L22;
        return r12;
    L22:
        r73 = r83;
    L23:
        e = move-exception;
        r73 = r83;
        e = e;
        r62 = r22;
    L7:
        r0 = new AnonymousClass3(r8);
        goto L8
    }

    public static final <T> Object firstOrNull(Flow<? extends T> r5, Continuation<? super T> r6) {
        if ((r6 instanceof C01901) == false) goto L7;
        C01901 r0 = (C01901) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        FlowCollector<? super Object> r52 = (FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1) r0.L$3;
        Flow r13 = (Flow) r0.L$2;
        Ref.ObjectRef r14 = (Ref.ObjectRef) r0.L$1;
        Flow r22 = (Flow) r0.L$0;
        ResultKt.throwOnFailure(r62);     // Catch: AbortFlowException -> L14
    L27:
        return r14.element;
    L14:
        AbortFlowException e = e;
    L25:
        FlowExceptions_commonKt.checkOwnership(e, r52);
        JobKt.ensureActive(r0.getContext());
        goto L27
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r62);
        Ref.ObjectRef r63 = new Ref.ObjectRef();
        FlowCollector<? super Object> r23 = new FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1(r63);
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: AbortFlowException -> L23
        r0.L$1 = r63;     // Catch: AbortFlowException -> L23
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: AbortFlowException -> L23
        r0.L$3 = r23;     // Catch: AbortFlowException -> L23
        r0.I$0 = 0;     // Catch: AbortFlowException -> L23
        r0.label = 1;     // Catch: AbortFlowException -> L23
        if (r5.collect(r23, r0) != r12) goto L22;
        return r12;
    L22:
        r14 = r63;
    L23:
        e = move-exception;
        r14 = r63;
        e = e;
        r52 = r23;
    L7:
        r0 = new C01901(r6);
        goto L8
    }
}
