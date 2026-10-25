package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DispatchException;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aO\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0000¢\u0006\u0002\u0010\t\u001aV\u0010\n\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0006\u0010\u0007\u001a\u0002H\u00022'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\rH\u0000¢\u0006\u0002\u0010\u000e\u001aV\u0010\u000f\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0006\u0010\u0007\u001a\u0002H\u00022'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\rH\u0000¢\u0006\u0002\u0010\u000e\u001a^\u0010\u0010\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u0002H\u00022'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\rH\u0002¢\u0006\u0002\u0010\u0013\u001a\u0018\u0010\u0014\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002\u001a\u0018\u0010\u0017\u001a\u00020\u0018*\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¨\u0006\u001b"}, d2 = {"startCoroutineUndispatched", _UrlKt.FRAGMENT_ENCODE_SET, "R", "T", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "receiver", "completion", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "startUndispatchedOrReturn", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "block", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/internal/ScopeCoroutine;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "startUndispatchedOrReturnIgnoreTimeout", "startUndispatched", "alwaysRethrow", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlinx/coroutines/internal/ScopeCoroutine;ZLjava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "notOwnTimeout", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "dispatchExceptionAndMakeCompleting", _UrlKt.FRAGMENT_ENCODE_SET, "e", "Lkotlinx/coroutines/DispatchException;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUndispatched.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Undispatched.kt\nkotlinx/coroutines/intrinsics/UndispatchedKt\n+ 2 ProbesSupport.kt\nkotlinx/coroutines/internal/ProbesSupportKt\n+ 3 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n+ 4 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,105:1\n8#2:106\n11#2,2:110\n103#3,3:107\n107#3:112\n57#4,2:113\n57#4,2:115\n57#4,2:117\n*S KotlinDebug\n*F\n+ 1 Undispatched.kt\nkotlinx/coroutines/intrinsics/UndispatchedKt\n*L\n14#1:106\n19#1:110,2\n18#1:107,3\n18#1:112\n88#1:113,2\n89#1:115,2\n103#1:117,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class UndispatchedKt {
    private static final Void dispatchExceptionAndMakeCompleting(ScopeCoroutine<?> r5, DispatchException r6) {
        r5.makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(r6.getCause(), false, 2, null));
        throw r6.getCause();
    }

    private static final boolean notOwnTimeout(ScopeCoroutine<?> r1, Throwable r2) {
        if ((r2 instanceof TimeoutCancellationException) == true) goto L5;
        return true;
    L5:
        if (((TimeoutCancellationException) r2).coroutine != r1) goto L11;
        return false;
    L11:
        return true;
    }

    public static final <R, T> void startCoroutineUndispatched(Function2<? super R, ? super Continuation<? super T>, ? extends Object> r3, R r4, Continuation<? super T> r5) {
        Continuation r52 = DebugProbesKt.probeCoroutineCreated(r5);
        CoroutineContext r0 = r52.getContext();     // Catch: Throwable -> L15
        Object r1 = ThreadContextKt.updateThreadContext(r0, null);     // Catch: Throwable -> L15
        DebugProbesKt.probeCoroutineResumed(r52);     // Catch: Throwable -> L7
        if ((r3 instanceof BaseContinuationImpl) == true) goto L9;
        Object r32 = IntrinsicsKt.wrapWithContinuationImpl(r3, r4, r52);     // Catch: Throwable -> L7
    L10:
        ThreadContextKt.restoreThreadContext(r0, r1);     // Catch: Throwable -> L15
        if (r32 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L26;
        r52.resumeWith(Result.m2018constructorimpl(r32));
        return;
    L26:
        return;
    L9:
        r32 = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(r3, 2)).invoke(r4, r52);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        ThreadContextKt.restoreThreadContext(r0, r1);     // Catch: Throwable -> L15
        throw th;     // Catch: Throwable -> L15
    L15:
        th = th;
        if ((th instanceof DispatchException) == false) goto L22;
        th = ((DispatchException) th).getCause();
    L22:
        Result.Companion r42 = Result.Companion;
        r52.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(th)));
    }

    private static final <T, R> Object startUndispatched(ScopeCoroutine<? super T> r3, boolean r4, R r5, Function2<? super R, ? super Continuation<? super T>, ? extends Object> r6) {
    L8:
        e = move-exception;
        dispatchExceptionAndMakeCompleting(r3, e);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L6:
        th = move-exception;
        Object r52 = new CompletedExceptionally(th, false, 2, null);
    L14:
        if (r52 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L16;
        Object r62 = r3.makeCompletingOnce$kotlinx_coroutines_core(r52);
        if (r62 == JobSupportKt.COMPLETING_WAITING_CHILDREN) goto L20;
        r3.afterCompletionUndispatched();
        if ((r62 instanceof CompletedExceptionally) == false) goto L34;
        if (r4 == true) goto L32;
        if (notOwnTimeout(r3, ((CompletedExceptionally) r62).cause) == true) goto L32;
        if ((r52 instanceof CompletedExceptionally) == true) goto L30;
        return r52;
    L30:
        throw ((CompletedExceptionally) r52).cause;
    L32:
        throw ((CompletedExceptionally) r62).cause;
    L34:
        return JobSupportKt.unboxState(r62);
    L20:
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    L16:
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    L4:
        if ((r6 instanceof BaseContinuationImpl) == true) goto L10;
        r52 = IntrinsicsKt.wrapWithContinuationImpl(r6, r5, r3);     // Catch: Throwable -> L6 DispatchException -> L8
    L37:
    L10:
        r52 = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(r6, 2)).invoke(r5, r3);     // Catch: Throwable -> L6 DispatchException -> L8
        goto L37
    }

    public static final <T, R> Object startUndispatchedOrReturn(ScopeCoroutine<? super T> r1, R r2, Function2<? super R, ? super Continuation<? super T>, ? extends Object> r3) {
        return startUndispatched(r1, true, r2, r3);
    }

    public static final <T, R> Object startUndispatchedOrReturnIgnoreTimeout(ScopeCoroutine<? super T> r1, R r2, Function2<? super R, ? super Continuation<? super T>, ? extends Object> r3) {
        return startUndispatched(r1, false, r2, r3);
    }
}
