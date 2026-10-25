package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/StartedLazily$command$$inlined$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 SharingStarted.kt\nkotlinx/coroutines/flow/StartedLazily\n*L\n1#1,111:1\n151#2,2:112\n*E\n"})
/* loaded from: classes5.dex */
public final class StartedLazily$command$$inlined$unsafeFlow$1 implements Flow<SharingCommand> {
    final /* synthetic */ StateFlow $subscriptionCount$inlined;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedLazily$command$$inlined$unsafeFlow$1", f = "SharingStarted.kt", i = {0, 0, 0, 0, 0}, l = {113}, m = "collect", n = {"collector", "$completion", "$this$command_u24lambda_u240", "started", "$i$a$-unsafeFlow-StartedLazily$command$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.StartedLazily$command$$inlined$unsafeFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ StartedLazily$command$$inlined$unsafeFlow$1 this$0;

        public AnonymousClass1(StartedLazily$command$$inlined$unsafeFlow$1 r1, Continuation r2) {
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

    public StartedLazily$command$$inlined$unsafeFlow$1(StateFlow r1) {
        this.$subscriptionCount$inlined = r1;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super SharingCommand> r7, Continuation<? super Unit> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L13;
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L13:
        Ref.BooleanRef r6 = (Ref.BooleanRef) r0.L$3;
        FlowCollector r62 = (FlowCollector) r0.L$2;
        Continuation r63 = (Continuation) r0.L$1;
        FlowCollector r64 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r82);
    L17:
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        ResultKt.throwOnFailure(r82);
        Ref.BooleanRef r83 = new Ref.BooleanRef();
        StateFlow r65 = this.$subscriptionCount$inlined;
        StartedLazily$command$1$1 r22 = new StartedLazily$command$1$1(r83, r7);
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r7);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r7);
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r83);
        r0.I$0 = 0;
        r0.label = 1;
        if (r65.collect(r22, r0) != r12) goto L17;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r8);
        goto L8
    }
}
