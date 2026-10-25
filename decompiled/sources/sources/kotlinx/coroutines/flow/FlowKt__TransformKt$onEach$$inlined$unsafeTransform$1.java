package kotlinx.coroutines.flow;

import com.android.dx.io.Opcodes;
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
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,111:1\n46#2,4:112\n*E\n"})
/* loaded from: classes.dex */
public final class FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1<T> implements Flow<T> {
    final /* synthetic */ Function2 $action$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n*L\n1#1,216:1\n75#2,2:217\n*E\n"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {
        final /* synthetic */ Function2 $action$inlined;
        final /* synthetic */ FlowCollector $this_unsafeFlow;

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2", f = "Transform.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {Opcodes.RSUB_INT_LIT8, Opcodes.MUL_INT_LIT8}, m = "emit", n = {"value", "$completion", "value", "$this$onEach_u24lambda_u240", "$i$a$-unsafeTransform-FlowKt__TransformKt$onEach$1", "value", "$completion", "value", "$this$onEach_u24lambda_u240", "$i$a$-unsafeTransform-FlowKt__TransformKt$onEach$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
        /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends ContinuationImpl {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ AnonymousClass2 this$0;

            public AnonymousClass1(AnonymousClass2 r1, Continuation r2) {
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

        public AnonymousClass2(FlowCollector r1, Function2 r2) {
            this.$this_unsafeFlow = r1;
            this.$action$inlined = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T r7, Continuation<? super Unit> r8) {
            if ((r8 instanceof AnonymousClass1) == false) goto L7;
            AnonymousClass1 r0 = (AnonymousClass1) r8;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r82 = r0.result;
            Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            if (r2 == 0) goto L16;
            if (r2 == 1) goto L15;
            if (r2 != 2) goto L13;
            FlowCollector r6 = (FlowCollector) r0.L$3;
            Continuation r62 = (Continuation) r0.L$1;
            ResultKt.throwOnFailure(r82);
        L24:
            return Unit.INSTANCE;
        L13:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L15:
            int r63 = r0.I$0;
            FlowCollector r72 = (FlowCollector) r0.L$3;
            Object r22 = r0.L$2;
            Continuation r4 = (Continuation) r0.L$1;
            Object r5 = r0.L$0;
            ResultKt.throwOnFailure(r82);
            FlowCollector r73 = r72;
        L20:
            r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);
            r0.L$1 = SpillingKt.nullOutSpilledVariable(r4);
            r0.L$2 = SpillingKt.nullOutSpilledVariable(r22);
            r0.L$3 = SpillingKt.nullOutSpilledVariable(r73);
            r0.I$0 = r63;
            r0.label = 2;
            if (r73.emit(r22, r0) != r12) goto L24;
        L22:
            return r12;
        L16:
            ResultKt.throwOnFailure(r82);
            FlowCollector r83 = this.$this_unsafeFlow;
            Function2 r64 = this.$action$inlined;
            r0.L$0 = SpillingKt.nullOutSpilledVariable(r7);
            r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);
            r0.L$2 = r7;
            r0.L$3 = r83;
            r0.I$0 = 0;
            r0.label = 1;
            InlineMarker.mark(6);
            Object r65 = r64.invoke(r7, r0);
            InlineMarker.mark(7);
            if (r65 == r12) goto L22;
            r5 = r7;
            r4 = r0;
            r63 = 0;
            r22 = r5;
            r73 = r83;
        L7:
            r0 = new AnonymousClass1(this, r8);
            goto L8
        }
    }

    public FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(Flow r1, Function2 r2) {
        this.$this_unsafeTransform$inlined = r1;
        this.$action$inlined = r2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector r3, Continuation r4) {
        Object r2 = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(r3, this.$action$inlined), r4);
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r2;
    L6:
        return Unit.INSTANCE;
    }
}
