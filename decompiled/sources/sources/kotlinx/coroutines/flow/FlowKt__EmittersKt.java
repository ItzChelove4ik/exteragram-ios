package kotlinx.coroutines.flow;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001aR\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\n\u001ai\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012D\u0010\b\u001a@\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000b¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0017\u0010\u0012\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u0003H\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001ap\u0010\u0016\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032D\u0010\b\u001a@\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000b¢\u0006\u0002\b\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0082@¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"T", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "action", "onStart", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", "onCompletion", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "ensureActive", "(Lkotlinx/coroutines/flow/FlowCollector;)V", "invokeSafely$FlowKt__EmittersKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/jvm/functions/Function3;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeSafely", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
@SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,216:1\n105#2:217\n105#2:218\n105#2:219\n105#2:220\n*S KotlinDebug\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n45#1:217\n70#1:218\n140#1:219\n175#1:220\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class FlowKt__EmittersKt {
    public static final /* synthetic */ Object access$invokeSafely$FlowKt__EmittersKt(FlowCollector r0, Function3 r1, Throwable r2, Continuation r3) {
        return invokeSafely$FlowKt__EmittersKt(r0, r1, r2, r3);
    }

    public static final void ensureActive(FlowCollector<?> r1) {
        if ((r1 instanceof ThrowingCollector) == true) goto L6;
        return;
    L6:
        throw ((ThrowingCollector) r1).e;
    }

    private static final <T> Object invokeSafely$FlowKt__EmittersKt(FlowCollector<? super T> r4, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> r5, Throwable r6, Continuation<? super Unit> r7) {
        if ((r7 instanceof FlowKt__EmittersKt$invokeSafely$1) == false) goto L7;
        FlowKt__EmittersKt$invokeSafely$1 r0 = (FlowKt__EmittersKt$invokeSafely$1) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        r6 = (Throwable) r0.L$2;
        Function3 r42 = (Function3) r0.L$1;
        FlowCollector r43 = (FlowCollector) r0.L$0;
        ResultKt.throwOnFailure(r72);     // Catch: Throwable -> L14
    L22:
        return Unit.INSTANCE;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r72);
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r4);     // Catch: Throwable -> L14
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: Throwable -> L14
        r0.L$2 = r6;     // Catch: Throwable -> L14
        r0.label = 1;     // Catch: Throwable -> L14
        if (r5.invoke(r4, r6, r0) != r12) goto L22;
        return r12;
    L14:
        th = move-exception;
        if (r6 == null) goto L27;
        if (r6 == th) goto L27;
        ExceptionsKt.addSuppressed(th, r6);
    L27:
        throw th;
    L7:
        r0 = new FlowKt__EmittersKt$invokeSafely$1(r7);
        goto L8
    }

    public static final <T> Flow<T> onCompletion(Flow<? extends T> r1, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> r2) {
        return new FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(r1, r2);
    }

    public static final <T> Flow<T> onStart(Flow<? extends T> r1, Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> r2) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(r2, r1);
    }
}
