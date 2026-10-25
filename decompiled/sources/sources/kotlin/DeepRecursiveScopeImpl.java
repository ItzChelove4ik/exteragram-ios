package kotlin;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.TypeIntrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004BL\bF\u00129\u0010\u0005\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0002\b\b\u0012\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0017\u001a\u00020\u00182\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0019J\u0017\u0010\u001a\u001a\u00028\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0096À\u0004¢\u0006\u0002\u0010\u001bJ3\u0010\u001a\u001a\u0002H\u001c\"\u0004\b\u0002\u0010\u001d\"\u0004\b\u0003\u0010\u001c*\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001c0\u001e2\u0006\u0010\t\u001a\u0002H\u001dH\u0096À\u0004¢\u0006\u0002\u0010\u001fJf\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00042=\u0010!\u001a9\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006j\u0002`\r¢\u0006\u0002\b\b2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0082\u0080\u0004¢\u0006\u0002\u0010\"J\u000f\u0010#\u001a\u00028\u0001H\u0086\u0080\u0004¢\u0006\u0002\u0010$RH\u0010\f\u001a9\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006j\u0002`\r¢\u0006\u0002\b\bX\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u000eR\u0011\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0004X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u0012R\u0015\u0010\u0013\u001a\u00020\u00148VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lkotlin/DeepRecursiveScopeImpl;", "T", "R", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "value", "<init>", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)V", "function", "Lkotlin/DeepRecursiveFunctionBlock;", "Lkotlin/jvm/functions/Function3;", "cont", "result", "Lkotlin/Result;", "Ljava/lang/Object;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Object;)V", "callRecursive", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "S", "U", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "crossFunctionCompletion", "currentFunction", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "runCallLoop", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class DeepRecursiveScopeImpl<T, R> extends DeepRecursiveScope<T, R> implements Continuation<R> {
    private Continuation<Object> cont;
    private Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function;
    private Object result;
    private Object value;

    /* JADX WARN: Multi-variable type inference failed */
    public DeepRecursiveScopeImpl(Function3<? super DeepRecursiveScope<T, R>, ? super T, ? super Continuation<? super R>, ? extends Object> r2, T r3) {
        super(null);
        this.function = r2;
        this.value = r3;
        this.cont = this;
        this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
    }

    public static final /* synthetic */ void access$setCont$p(DeepRecursiveScopeImpl r0, Continuation r1) {
        r0.cont = r1;
    }

    public static final /* synthetic */ void access$setFunction$p(DeepRecursiveScopeImpl r0, Function3 r1) {
        r0.function = r1;
    }

    public static final /* synthetic */ void access$setResult$p(DeepRecursiveScopeImpl r0, Object r1) {
        r0.result = r1;
    }

    private final Continuation<Object> crossFunctionCompletion(Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> r3, Continuation<Object> r4) {
        return new DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1(EmptyCoroutineContext.INSTANCE, this, r3, r4);
    }

    @Override // kotlin.DeepRecursiveScope
    public Object callRecursive(T r1, Continuation<? super R> r2) {
        this.cont = r2;
        this.value = r1;
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        DebugProbesKt.probeCoroutineSuspended(r2);
    L5:
        return r0;
    }

    @Override // kotlin.DeepRecursiveScope
    public <U, S> Object callRecursive(DeepRecursiveFunction<U, S> r2, U r3, Continuation<? super S> r4) {
        Function3<DeepRecursiveScope<U, S>, U, Continuation<? super S>, Object> r22 = r2.getBlock$kotlin_stdlib();
        Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> r0 = this.function;
        if (r22 == r0) goto L5;
        this.function = r22;
        this.cont = crossFunctionCompletion(r0, r4);
    L6:
        this.value = r3;
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        DebugProbesKt.probeCoroutineSuspended(r4);
    L9:
        return r1;
    L5:
        this.cont = r4;
        goto L6
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object r2) {
        this.cont = null;
        this.result = r2;
    }

    public final R runCallLoop() {
    L2:
        R r0 = (R) this.result;
        Continuation<Object> r1 = this.cont;
        if (r1 == null) goto L4;
        if (Result.m2020equalsimpl0(DeepRecursiveKt.access$getUNDEFINED_RESULT$p(), r0) == false) goto L18;
        Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> r02 = this.function;     // Catch: Throwable -> L11
        Object r2 = this.value;     // Catch: Throwable -> L11
        if ((r02 instanceof BaseContinuationImpl) == true) goto L13;
        Object r03 = IntrinsicsKt.wrapWithContinuationImpl(r02, this, r2, r1);     // Catch: Throwable -> L11
    L15:
        if (r03 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L2;
        r1.resumeWith(Result.m2018constructorimpl(r03));
        goto L2
    L13:
        r03 = ((Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(r02, 3)).invoke(this, r2, r1);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        Result.Companion r22 = Result.Companion;
        r1.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(th)));
        goto L2
    L18:
        this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
        r1.resumeWith(r0);
        goto L2
    L4:
        ResultKt.throwOnFailure(r0);
        return r0;
    }
}
