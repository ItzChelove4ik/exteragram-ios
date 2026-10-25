package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aN\u0010\f\u001a\u00020\u000b*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\t¢\u0006\u0004\b\f\u0010\r\u001aZ\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\"\u0004\b\u0000\u0010\u000e*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\t¢\u0006\u0004\b\u0010\u0010\u0011\u001aT\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000e2\u0006\u0010\u0002\u001a\u00020\u00012'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0086@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlinx/coroutines/CoroutineStart;", "start", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "Lkotlinx/coroutines/Job;", "launch", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "T", "Lkotlinx/coroutines/Deferred;", "async", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Deferred;", "withContext", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/BuildersKt")
@SourceDebugExtension({"SMAP\nBuilders.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Builders.common.kt\nkotlinx/coroutines/BuildersKt__Builders_commonKt\n+ 2 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,600:1\n103#2,5:601\n*S KotlinDebug\n*F\n+ 1 Builders.common.kt\nkotlinx/coroutines/BuildersKt__Builders_commonKt\n*L\n496#1:601,5\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class BuildersKt__Builders_commonKt {
    public static final <T> Deferred<T> async(CoroutineScope r1, CoroutineContext r2, CoroutineStart r3, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r4) {
        CoroutineContext r12 = CoroutineContextKt.newCoroutineContext(r1, r2);
        if (r3.isLazy() == false) goto L5;
        DeferredCoroutine r22 = new LazyDeferredCoroutine(r12, r4);
    L6:
        ((AbstractCoroutine) r22).start(r3, r22, r4);
        return (Deferred<T>) r22;
    L5:
        r22 = new DeferredCoroutine(r12, true);
        goto L6
    }

    public static /* synthetic */ Deferred async$default(CoroutineScope r0, CoroutineContext r1, CoroutineStart r2, Function2 r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = EmptyCoroutineContext.INSTANCE;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = CoroutineStart.DEFAULT;
    L9:
        return BuildersKt.async(r0, r1, r2, r3);
    }

    public static final Job launch(CoroutineScope r1, CoroutineContext r2, CoroutineStart r3, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> r4) {
        CoroutineContext r12 = CoroutineContextKt.newCoroutineContext(r1, r2);
        if (r3.isLazy() == false) goto L5;
        AbstractCoroutine r22 = new LazyStandaloneCoroutine(r12, r4);
    L6:
        r22.start(r3, r22, r4);
        return r22;
    L5:
        r22 = new StandaloneCoroutine(r12, true);
        goto L6
    }

    public static /* synthetic */ Job launch$default(CoroutineScope r0, CoroutineContext r1, CoroutineStart r2, Function2 r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = EmptyCoroutineContext.INSTANCE;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = CoroutineStart.DEFAULT;
    L9:
        return BuildersKt.launch(r0, r1, r2, r3);
    }

    public static final <T> Object withContext(CoroutineContext r3, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r4, Continuation<? super T> r5) {
        CoroutineContext r0 = r5.getContext();
        CoroutineContext r32 = CoroutineContextKt.newCoroutineContext(r0, r3);
        JobKt.ensureActive(r32);
        if (r32 != r0) goto L5;
        ScopeCoroutine r02 = new ScopeCoroutine(r32, r5);
        Object r33 = UndispatchedKt.startUndispatchedOrReturn(r02, r02, r4);
    L15:
        if (r33 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L17;
        DebugProbesKt.probeCoroutineSuspended(r5);
    L17:
        return r33;
    L5:
        ContinuationInterceptor.Key r1 = ContinuationInterceptor.Key;
        if (Intrinsics.areEqual(r32.get(r1), r0.get(r1)) == false) goto L13;
        UndispatchedCoroutine r03 = new UndispatchedCoroutine(r32, r5);
        CoroutineContext r34 = r03.getContext();
        Object r12 = ThreadContextKt.updateThreadContext(r34, null);
        Object r42 = UndispatchedKt.startUndispatchedOrReturn(r03, r03, r4);     // Catch: Throwable -> L10
        ThreadContextKt.restoreThreadContext(r34, r12);
        r33 = r42;
    L10:
        th = move-exception;
        ThreadContextKt.restoreThreadContext(r34, r12);
        throw th;
    L13:
        DispatchedCoroutine r04 = new DispatchedCoroutine(r32, r5);
        CancellableKt.startCoroutineCancellable(r4, r04, r04);
        r33 = r04.getResult$kotlinx_coroutines_core();
        goto L15
    }
}
