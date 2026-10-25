package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Errors.kt\nkotlinx/coroutines/flow/FlowKt__ErrorsKt\n*L\n1#1,111:1\n55#2,3:112\n*E\n"})
/* loaded from: classes.dex */
public final class FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_catch$inlined;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1", f = "Errors.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {112, 113}, m = "collect", n = {"collector", "$completion", "$this$catch_u24lambda_u240", "$i$a$-unsafeFlow-FlowKt__ErrorsKt$catch$1", "collector", "$completion", "$this$catch_u24lambda_u240", "exception", "$i$a$-unsafeFlow-FlowKt__ErrorsKt$catch$1"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1 this$0;

        public AnonymousClass1(FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1 r1, Continuation r2) {
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

    public FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(Flow r1, Function3 r2) {
        this.$this_catch$inlined = r1;
        this.$action$inlined = r2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r8, Continuation<? super Unit> r9) {
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
        Throwable r7 = (Throwable) r0.L$3;
        FlowCollector r72 = (FlowCollector) r0.L$2;
        Continuation r73 = (Continuation) r0.L$1;
        FlowCollector r74 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r92);
    L26:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        int r82 = r0.I$0;
        FlowCollector<? super T> r22 = (FlowCollector) r0.L$2;
        Continuation r4 = (Continuation) r0.L$1;
        FlowCollector<? super T> r5 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r92);
        int r23 = r82;
        r8 = r22;
    L20:
        Throwable r93 = (Throwable) r92;
        if (r93 == null) goto L26;
        Function3 r75 = this.$action$inlined;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r4);
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r8);
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r93);
        r0.I$0 = r23;
        r0.label = 2;
        InlineMarker.mark(6);
        Object r76 = r75.invoke(r8, r93, r0);
        InlineMarker.mark(7);
        if (r76 != r12) goto L26;
    L24:
        return r12;
    L16:
        ResultKt.throwOnFailure(r92);
        Flow r94 = this.$this_catch$inlined;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r8);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);
        r0.L$2 = r8;
        r23 = 0;
        r0.I$0 = 0;
        r0.label = 1;
        r92 = FlowKt.catchImpl(r94, r8, r0);
        if (r92 == r12) goto L24;
        r5 = r8;
        r4 = r0;
    L7:
        r0 = new AnonymousClass1(this, r9);
        goto L8
    }
}
