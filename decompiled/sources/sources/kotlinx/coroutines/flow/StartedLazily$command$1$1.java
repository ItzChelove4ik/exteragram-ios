package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class StartedLazily$command$1$1<T> implements FlowCollector {
    final /* synthetic */ Ref.BooleanRef $started;
    final /* synthetic */ FlowCollector<SharingCommand> $this_unsafeFlow;

    /* JADX WARN: Multi-variable type inference failed */
    public StartedLazily$command$1$1(Ref.BooleanRef r1, FlowCollector<? super SharingCommand> r2) {
        this.$started = r1;
        this.$this_unsafeFlow = r2;
    }

    public final Object emit(int r5, Continuation<? super Unit> r6) {
        if ((r6 instanceof StartedLazily$command$1$1$emit$1) == false) goto L7;
        StartedLazily$command$1$1$emit$1 r0 = (StartedLazily$command$1$1$emit$1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r62);
    L22:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        if (r5 <= 0) goto L24;
        Ref.BooleanRef r63 = this.$started;
        if (r63.element == true) goto L24;
        r63.element = true;
        FlowCollector<SharingCommand> r4 = this.$this_unsafeFlow;
        SharingCommand r64 = SharingCommand.START;
        r0.I$0 = r5;
        r0.label = 1;
        if (r4.emit(r64, r0) != r12) goto L22;
        return r12;
    L24:
        return Unit.INSTANCE;
    L7:
        r0 = new StartedLazily$command$1$1$emit$1(this, r6);
        goto L8
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
        return emit(((Number) r1).intValue(), r2);
    }
}
