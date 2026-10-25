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
import kotlinx.coroutines.flow.internal.SafeCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 3 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n*L\n1#1,111:1\n141#2,13:112\n154#2,6:126\n1307#3:125\n*S KotlinDebug\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n153#1:125\n*E\n"})
/* loaded from: classes.dex */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", f = "Emitters.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {113, 120, 127}, m = "collect", n = {"collector", "$completion", "$this$onCompletion_u24lambda_u240", "$i$a$-unsafeFlow-FlowKt__EmittersKt$onCompletion$1", "collector", "$completion", "$this$onCompletion_u24lambda_u240", "e", "$i$a$-unsafeFlow-FlowKt__EmittersKt$onCompletion$1", "collector", "$completion", "$this$onCompletion_u24lambda_u240", "sc", "$i$a$-unsafeFlow-FlowKt__EmittersKt$onCompletion$1"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 this$0;

        public AnonymousClass1(FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 r1, Continuation r2) {
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

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow r1, Function3 r2) {
        this.$this_onCompletion$inlined = r1;
        this.$action$inlined = r2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r9, Continuation<? super Unit> r10) {
        if ((r10 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L26;
        if (r2 == 1) goto L21;
        if (r2 == 2) goto L20;
        if (r2 != 3) goto L18;
        SafeCollector r8 = (SafeCollector) r0.L$3;
        FlowCollector r92 = (FlowCollector) r0.L$2;
        Continuation r93 = (Continuation) r0.L$1;
        FlowCollector r94 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L16
    L36:
        r8.releaseIntercepted();
        return Unit.INSTANCE;
    L16:
        th = th;
    L40:
        r8.releaseIntercepted();
        throw th;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        Throwable r82 = (Throwable) r0.L$3;
        FlowCollector r95 = (FlowCollector) r0.L$2;
        Continuation r96 = (Continuation) r0.L$1;
        FlowCollector r97 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r102);
        throw r82;
    L21:
        int r98 = r0.I$0;
        FlowCollector<? super T> r22 = (FlowCollector) r0.L$2;
        Continuation r6 = (Continuation) r0.L$1;
        FlowCollector<? super T> r7 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L24
        int r103 = r98;
        r9 = r22;
    L31:
        SafeCollector r23 = new SafeCollector(r9, r0.getContext());
        Function3 r83 = this.$action$inlined;     // Catch: Throwable -> L38
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r7);     // Catch: Throwable -> L38
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r6);     // Catch: Throwable -> L38
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r9);     // Catch: Throwable -> L38
        r0.L$3 = r23;     // Catch: Throwable -> L38
        r0.I$0 = r103;     // Catch: Throwable -> L38
        r0.label = 3;     // Catch: Throwable -> L38
        InlineMarker.mark(6);     // Catch: Throwable -> L38
        Object r84 = r83.invoke(r23, null, r0);     // Catch: Throwable -> L38
        InlineMarker.mark(7);     // Catch: Throwable -> L38
        if (r84 == r12) goto L46;
        r8 = r23;
    L46:
        return r12;
    L38:
        th = th;
        r8 = r23;
    L24:
        th = move-exception;
        th = th;
        FlowCollector<? super T> r104 = r22;
        int r3 = r98;
        r9 = r7;
    L44:
        ThrowingCollector r4 = new ThrowingCollector(th);
        Function3 r85 = this.$action$inlined;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r9);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r6);
        r0.L$2 = SpillingKt.nullOutSpilledVariable(r104);
        r0.L$3 = th;
        r0.I$0 = r3;
        r0.label = 2;
        if (FlowKt__EmittersKt.access$invokeSafely$FlowKt__EmittersKt(r4, r85, th, r0) == r12) goto L46;
        throw th;
    L26:
        ResultKt.throwOnFailure(r102);
        r103 = 0;
        Flow r24 = this.$this_onCompletion$inlined;     // Catch: Throwable -> L42
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r9);     // Catch: Throwable -> L42
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);     // Catch: Throwable -> L42
        r0.L$2 = r9;     // Catch: Throwable -> L42
        r0.I$0 = 0;     // Catch: Throwable -> L42
        r0.label = 1;     // Catch: Throwable -> L42
        if (r24.collect(r9, r0) == r12) goto L46;
        r7 = r9;
        r6 = r0;
    L42:
        th = th;
        r3 = 0;
        r6 = r0;
        r104 = r9;
    L7:
        r0 = new AnonymousClass1(this, r10);
        goto L8
    }
}
