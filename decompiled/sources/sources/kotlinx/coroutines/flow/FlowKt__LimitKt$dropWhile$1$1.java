package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FlowKt__LimitKt$dropWhile$1$1<T> implements FlowCollector {
    final /* synthetic */ Ref.BooleanRef $matched;
    final /* synthetic */ Function2<T, Continuation<? super Boolean>, Object> $predicate;
    final /* synthetic */ FlowCollector<T> $this_flow;

    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__LimitKt$dropWhile$1$1(Ref.BooleanRef r1, FlowCollector<? super T> r2, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> r3) {
        this.$matched = r1;
        this.$this_flow = r2;
        this.$predicate = r3;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(T r7, Continuation<? super Unit> r8) {
        if ((r8 instanceof FlowKt__LimitKt$dropWhile$1$1$emit$1) == false) goto L7;
        FlowKt__LimitKt$dropWhile$1$1$emit$1 r0 = (FlowKt__LimitKt$dropWhile$1$1$emit$1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 == 1) goto L17;
        if (r2 == 2) goto L16;
        if (r2 != 3) goto L14;
        ResultKt.throwOnFailure(r82);
    L34:
        return Unit.INSTANCE;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        r7 = (T) r0.L$0;
        ResultKt.throwOnFailure(r82);
    L29:
        if (((Boolean) r82).booleanValue() == true) goto L36;
        this.$matched.element = true;
        FlowCollector<T> r6 = this.$this_flow;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r7);
        r0.label = 3;
        if (r6.emit(r7, r0) != r12) goto L34;
    L32:
        return r12;
    L36:
        return Unit.INSTANCE;
    L17:
        ResultKt.throwOnFailure(r82);
    L24:
        return Unit.INSTANCE;
    L18:
        ResultKt.throwOnFailure(r82);
        if (this.$matched.element == false) goto L25;
        FlowCollector<T> r62 = this.$this_flow;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r7);
        r0.label = 1;
        if (r62.emit(r7, r0) != r12) goto L24;
    L25:
        Function2<T, Continuation<? super Boolean>, Object> r83 = this.$predicate;
        r0.L$0 = r7;
        r0.label = 2;
        r82 = r83.invoke(r7, r0);
        if (r82 != r12) goto L29;
    L7:
        r0 = new FlowKt__LimitKt$dropWhile$1$1$emit$1(this, r8);
        goto L8
    }
}
