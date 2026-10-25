package kotlinx.coroutines.flow;

import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u001ag\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012B\u0010\f\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0002¢\u0006\u0002\b\u000b¢\u0006\u0004\b\r\u0010\u000e\u001a|\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012W\u0010\u0013\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000f¢\u0006\u0002\b\u000b¢\u0006\u0004\b\u0014\u0010\u0015\u001a0\u0010\u0017\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0080@¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001b\u0010\u001d\u001a\u00020\u0012*\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001d\u0010!\u001a\u00020\u0012*\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"T", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "action", "catch", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function4;", _UrlKt.FRAGMENT_ENCODE_SET, "attempt", _UrlKt.FRAGMENT_ENCODE_SET, "predicate", "retryWhen", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "collector", "catchImpl", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "isCancellationCause$FlowKt__ErrorsKt", "(Ljava/lang/Throwable;Lkotlin/coroutines/CoroutineContext;)Z", "isCancellationCause", "other", "isSameExceptionAs$FlowKt__ErrorsKt", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)Z", "isSameExceptionAs", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
@SourceDebugExtension({"SMAP\nErrors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Errors.kt\nkotlinx/coroutines/flow/FlowKt__ErrorsKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,220:1\n105#2:221\n105#2:223\n1#3:222\n1307#4:224\n159#5:225\n*S KotlinDebug\n*F\n+ 1 Errors.kt\nkotlinx/coroutines/flow/FlowKt__ErrorsKt\n*L\n54#1:221\n128#1:223\n167#1:224\n217#1:225\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class FlowKt__ErrorsKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt", f = "Errors.kt", i = {0, 0, 0}, l = {152}, m = "catchImpl", n = {"$this$catchImpl", "collector", "fromDownstream"}, s = {"L$0", "L$1", "L$2"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.catchImpl(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {
        final /* synthetic */ FlowCollector<T> $collector;
        final /* synthetic */ Ref.ObjectRef<Throwable> $fromDownstream;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(FlowCollector<? super T> r1, Ref.ObjectRef<Throwable> r2) {
            this.$collector = r1;
            this.$fromDownstream = r2;
        }

        /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.Throwable] */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T r5, Continuation<? super Unit> r6) {
            if ((r6 instanceof FlowKt__ErrorsKt$catchImpl$2$emit$1) == false) goto L7;
            FlowKt__ErrorsKt$catchImpl$2$emit$1 r0 = (FlowKt__ErrorsKt$catchImpl$2$emit$1) r6;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r62 = r0.result;
            Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            if (r2 == 0) goto L17;
            if (r2 != 1) goto L15;
            ResultKt.throwOnFailure(r62);     // Catch: Throwable -> L13
        L21:
            AnonymousClass2<T> this = (AnonymousClass2<T>) Unit.INSTANCE;
            return this;
        L15:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L17:
            ResultKt.throwOnFailure(r62);
            FlowCollector<T> r63 = this.$collector;     // Catch: Throwable -> L13
            r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: Throwable -> L13
            r0.label = 1;     // Catch: Throwable -> L13
            if (r63.emit(r5, r0) != r12) goto L21;
            return r12;
        L13:
            th = move-exception;
            r5 = (T) th;
            this.$fromDownstream.element = r5;
            throw r5;
        L7:
            r0 = new FlowKt__ErrorsKt$catchImpl$2$emit$1(this, r6);
            goto L8
        }
    }

    /* renamed from: catch, reason: not valid java name */
    public static final <T> Flow<T> m3552catch(Flow<? extends T> r1, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> r2) {
        return new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(r1, r2);
    }

    public static final <T> Object catchImpl(Flow<? extends T> r6, FlowCollector<? super T> r7, Continuation<? super Throwable> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        Ref.ObjectRef r62 = (Ref.ObjectRef) r0.L$2;
        FlowCollector r72 = (FlowCollector) r0.L$1;
        Flow r73 = (Flow) r0.L$0;
        ResultKt.throwOnFailure(r82);     // Catch: Throwable -> L14
    L22:
        return null;
    L14:
        th = th;
    L25:
        Throwable r63 = (Throwable) r62.element;
        if (isSameExceptionAs$FlowKt__ErrorsKt(th, r63) == false) goto L28;
    L37:
        throw th;
    L28:
        if (isCancellationCause$FlowKt__ErrorsKt(th, r0.getContext()) == true) goto L37;
        if (r63 != null) goto L32;
        return th;
    L32:
        if ((th instanceof CancellationException) == false) goto L35;
        ExceptionsKt.addSuppressed(r63, th);
        throw r63;
    L35:
        ExceptionsKt.addSuppressed(th, r63);
        throw th;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r82);
        Ref.ObjectRef r83 = new Ref.ObjectRef();
        FlowCollector<? super Object> r22 = new AnonymousClass2(r7, r83);     // Catch: Throwable -> L23
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r6);     // Catch: Throwable -> L23
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r7);     // Catch: Throwable -> L23
        r0.L$2 = r83;     // Catch: Throwable -> L23
        r0.label = 1;     // Catch: Throwable -> L23
        if (r6.collect(r22, r0) != r12) goto L22;
        return r12;
    L23:
        th = th;
        r62 = r83;
    L7:
        r0 = new AnonymousClass1(r8);
        goto L8
    }

    private static final boolean isCancellationCause$FlowKt__ErrorsKt(Throwable r1, CoroutineContext r2) {
        Job r22 = (Job) r2.get(Job.Key);
        if (r22 != null) goto L5;
        return false;
    L5:
        if (r22.isCancelled() == true) goto L8;
        return false;
    L8:
        return isSameExceptionAs$FlowKt__ErrorsKt(r1, r22.getCancellationException());
    }

    private static final boolean isSameExceptionAs$FlowKt__ErrorsKt(Throwable r0, Throwable r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (Intrinsics.areEqual(r1, r0) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static final <T> Flow<T> retryWhen(Flow<? extends T> r1, Function4<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> r2) {
        return new FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(r1, r2);
    }
}
