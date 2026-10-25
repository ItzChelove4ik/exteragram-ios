package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u00032\u00020\u0004B\u001d\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001d\u0010\u0018\u001a\u00020\u00192\u000e\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001bH\u0014¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u0014H\u0016J\u0016\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010 J%\u0010\u001e\u001a\u0004\u0018\u00010\u00192\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\"J'\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010&J\u001a\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0019H\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u00020\u00108\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006*"}, d2 = {"Lkotlinx/coroutines/flow/internal/SafeCollector;", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "collector", "collectContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "collectContextSize", _UrlKt.FRAGMENT_ENCODE_SET, "lastEmissionContext", "completion_", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "invokeSuspend", _UrlKt.FRAGMENT_ENCODE_SET, "result", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uCont", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)Ljava/lang/Object;", "checkContext", "currentContext", "previousContext", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "exceptionTransparencyViolated", "exception", "Lkotlinx/coroutines/flow/internal/DownstreamExceptionContext;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.kt\nkotlinx/coroutines/flow/internal/SafeCollector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,182:1\n1#2:183\n*E\n"})
/* loaded from: classes.dex */
public final class SafeCollector<T> extends ContinuationImpl implements FlowCollector<T>, CoroutineStackFrame {

    @JvmField
    public final CoroutineContext collectContext;

    @JvmField
    public final int collectContextSize;

    @JvmField
    public final FlowCollector<T> collector;
    private Continuation<? super Unit> completion_;
    private CoroutineContext lastEmissionContext;

    public static int $r8$lambda$idxg0bEmSgrK6v2pQsR7xybd4HY(int r0, CoroutineContext.Element r1) {
        return r0 + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SafeCollector(FlowCollector<? super T> r3, CoroutineContext r4) {
        super(NoOpContinuation.INSTANCE, EmptyCoroutineContext.INSTANCE);
        this.collector = r3;
        this.collectContext = r4;
        this.collectContextSize = ((Number) r4.fold(0, new SafeCollector$$ExternalSyntheticLambda0())).intValue();
    }

    private final void checkContext(CoroutineContext r2, CoroutineContext r3, T r4) {
        if ((r3 instanceof DownstreamExceptionContext) == false) goto L5;
        exceptionTransparencyViolated((DownstreamExceptionContext) r3, r4);
    L5:
        SafeCollector_commonKt.checkContext(this, r2);
    }

    private final Object emit(Continuation<? super Unit> r3, T r4) {
        CoroutineContext r0 = r3.getContext();
        JobKt.ensureActive(r0);
        CoroutineContext r1 = this.lastEmissionContext;
        if (r1 == r0) goto L5;
        checkContext(r0, r1, r4);
        this.lastEmissionContext = r0;
    L5:
        this.completion_ = r3;
        Object r32 = SafeCollectorKt.access$getEmitFun$p().invoke(this.collector, r4, this);
        if (Intrinsics.areEqual(r32, IntrinsicsKt.getCOROUTINE_SUSPENDED()) == true) goto L8;
        this.completion_ = null;
    L8:
        return r32;
    }

    private final void exceptionTransparencyViolated(DownstreamExceptionContext r3, Object r4) {
        throw new IllegalStateException(StringsKt.trimIndent("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + r3.e + ", but then emission attempt of value '" + r4 + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T r2, Continuation<? super Unit> r3) {
        Object r1 = emit(r3, r2);     // Catch: Throwable -> L11
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L7;
        DebugProbesKt.probeCoroutineSuspended(r3);
    L7:
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        return r1;
    L10:
        return Unit.INSTANCE;
    L11:
        th = move-exception;
        this.lastEmissionContext = new DownstreamExceptionContext(th, r3.getContext());
        throw th;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<? super Unit> r1 = this.completion_;
        if ((r1 instanceof CoroutineStackFrame) == true) goto L5;
        return null;
    L5:
        return (CoroutineStackFrame) r1;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        CoroutineContext r0 = this.lastEmissionContext;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object r4) {
        Throwable r0 = Result.m2021exceptionOrNullimpl(r4);
        if (r0 == null) goto L5;
        this.lastEmissionContext = new DownstreamExceptionContext(r0, getContext());
    L5:
        Continuation<? super Unit> r3 = this.completion_;
        if (r3 == null) goto L9;
        r3.resumeWith(r4);
    L9:
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        super.releaseIntercepted();
    }
}
