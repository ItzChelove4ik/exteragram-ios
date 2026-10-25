package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Errors.kt\nkotlinx/coroutines/flow/FlowKt__ErrorsKt\n*L\n1#1,111:1\n129#2,15:112\n*E\n"})
/* loaded from: classes.dex */
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function4 $predicate$inlined;
    final /* synthetic */ Flow $this_retryWhen$inlined;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1", f = "Errors.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {116, 118}, m = "collect", n = {"collector", "$completion", "$this$retryWhen_u24lambda_u240", "$i$a$-unsafeFlow-FlowKt__ErrorsKt$retryWhen$1", "attempt", "shallRetry", "collector", "$completion", "$this$retryWhen_u24lambda_u240", "cause", "$i$a$-unsafeFlow-FlowKt__ErrorsKt$retryWhen$1", "attempt", "shallRetry"}, s = {"L$0", "L$1", "L$2", "I$0", "J$0", "I$1", "L$0", "L$1", "L$2", "L$3", "I$0", "J$0", "I$1"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 this$0;

        public AnonymousClass1(FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 r1, Continuation r2) {
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

    public FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(Flow r1, Function4 r2) {
        this.$this_retryWhen$inlined = r1;
        this.$predicate$inlined = r2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x009f -> B:30:0x00de). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00ce -> B:27:0x00d1). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r19, Continuation<? super Unit> r20) {
        if ((r20 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r2 = (AnonymousClass1) r20;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.label = r3 - Integer.MIN_VALUE;
    L8:
        Object r1 = r2.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.label;
        Object r5 = null;
        int r6 = 0;
        if (r4 == 0) goto L16;
        if (r4 == 1) goto L15;
        if (r4 != 2) goto L13;
        long r9 = r2.J$0;
        int r42 = r2.I$0;
        Throwable r11 = (Throwable) r2.L$3;
        FlowCollector<? super T> r12 = (FlowCollector) r2.L$2;
        Object r13 = (Continuation) r2.L$1;
        FlowCollector<? super T> r14 = (FlowCollector) r2.L$0;
        ResultKt.throwOnFailure(r1);
    L28:
        if (((Boolean) r1).booleanValue() == false) goto L31;
        long r10 = r9 + 1;
        int r92 = 1;
    L30:
        FlowCollector<? super T> r15 = r12;
        int r122 = r42;
        AnonymousClass1 r43 = r2;
        FlowCollector<? super T> r22 = r14;
        if (r92 == 0) goto L34;
        Object r93 = r13;
        r5 = null;
        r6 = 0;
    L17:
        Flow r132 = this.$this_retryWhen$inlined;
        r43.L$0 = SpillingKt.nullOutSpilledVariable(r22);
        r43.L$1 = SpillingKt.nullOutSpilledVariable(r93);
        r43.L$2 = r15;
        r43.L$3 = r5;
        r43.I$0 = r122;
        r43.J$0 = r10;
        r43.I$1 = r6;
        r43.label = 1;
        Object r133 = FlowKt.catchImpl(r132, r15, r43);
        if (r133 == r32) goto L25;
        r14 = r22;
        r2 = r43;
        r42 = r122;
        r12 = r15;
        r1 = r133;
        r13 = r93;
        r92 = r6;
    L21:
        Throwable r16 = (Throwable) r1;
        if (r16 == null) goto L30;
        Function4 r152 = this.$predicate$inlined;
        Long r52 = Boxing.boxLong(r10);
        r2.L$0 = SpillingKt.nullOutSpilledVariable(r14);
        r2.L$1 = SpillingKt.nullOutSpilledVariable(r13);
        r2.L$2 = r12;
        r2.L$3 = r16;
        r2.I$0 = r42;
        r2.J$0 = r10;
        r2.I$1 = r92;
        r2.label = 2;
        InlineMarker.mark(6);
        Object r53 = r152.invoke(r12, r16, r52, r2);
        InlineMarker.mark(7);
        if (r53 == r32) goto L25;
        r9 = r10;
        r11 = r16;
        r1 = r53;
    L25:
        return r32;
    L34:
        return Unit.INSTANCE;
    L31:
        throw r11;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        int r44 = r2.I$1;
        long r94 = r2.J$0;
        int r112 = r2.I$0;
        r12 = (FlowCollector) r2.L$2;
        r13 = (Continuation) r2.L$1;
        r14 = (FlowCollector) r2.L$0;
        ResultKt.throwOnFailure(r1);
        r92 = r44;
        r42 = r112;
        r10 = r94;
        goto L21
    L16:
        ResultKt.throwOnFailure(r1);
        r15 = r19;
        r43 = r2;
        r122 = 0;
        r10 = 0;
        r22 = r15;
        r93 = r43;
    L7:
        r2 = new AnonymousClass1(this, r20);
        goto L8
    }
}
