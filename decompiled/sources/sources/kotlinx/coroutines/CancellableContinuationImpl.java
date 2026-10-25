package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;

@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u00032\u00060\u0004j\u0002`\u00052\u00020\u0006B\u001d\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0017\u001a\u00020\u00162\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0019\u0010\u000fJ\u000f\u0010\u001a\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001a\u0010\u000fJ\u0011\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b \u0010!J!\u0010#\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\tH\u0002¢\u0006\u0004\b&\u0010'J\u0088\u0001\u00103\u001a\u0004\u0018\u00010\u001e\"\u0004\b\u0001\u0010(2\u0006\u0010\"\u001a\u00020)2\u0006\u0010*\u001a\u00028\u00012\u0006\u0010\n\u001a\u00020\t2M\u00101\u001aI\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110/¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u0016\u0018\u00010+2\b\u00102\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0004\b3\u00104Jx\u00106\u001a\u0004\u0018\u000105\"\u0004\b\u0001\u0010(2\u0006\u0010*\u001a\u00028\u00012\b\u00102\u001a\u0004\u0018\u00010\u001e2M\u00101\u001aI\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110/¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u0016\u0018\u00010+H\u0002¢\u0006\u0004\b6\u00107J\u0019\u00109\u001a\u0002082\b\u0010*\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u0016H\u0002¢\u0006\u0004\b;\u0010<J\u000f\u0010=\u001a\u00020\u0016H\u0016¢\u0006\u0004\b=\u0010<J\u000f\u0010>\u001a\u00020\rH\u0001¢\u0006\u0004\b>\u0010\u000fJ\u0017\u0010A\u001a\n\u0018\u00010?j\u0004\u0018\u0001`@H\u0016¢\u0006\u0004\bA\u0010BJ\u0011\u0010E\u001a\u0004\u0018\u00010\u001eH\u0010¢\u0006\u0004\bC\u0010DJ!\u0010I\u001a\u00020\u00162\b\u0010F\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0010¢\u0006\u0004\bG\u0010HJ\u0019\u0010J\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0004\bJ\u0010\u0013J\u0017\u0010M\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0010H\u0000¢\u0006\u0004\bK\u0010LJ\u001f\u0010O\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020N2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\bO\u0010PJp\u0010Q\u001a\u00020\u0016\"\u0004\b\u0001\u0010(2K\u00101\u001aG\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110/¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00160+2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010.\u001a\u00028\u0001¢\u0006\u0004\bQ\u0010RJ\u0017\u0010U\u001a\u00020\u00102\u0006\u0010T\u001a\u00020SH\u0016¢\u0006\u0004\bU\u0010VJ\u0011\u0010W\u001a\u0004\u0018\u00010\u001eH\u0001¢\u0006\u0004\bW\u0010DJ\u000f\u0010Y\u001a\u00020\u0016H\u0000¢\u0006\u0004\bX\u0010<J\u001d\u0010\\\u001a\u00020\u00162\f\u0010[\u001a\b\u0012\u0004\u0012\u00028\u00000ZH\u0016¢\u0006\u0004\b\\\u0010!J<\u0010^\u001a\u00020\u00162\u0006\u0010.\u001a\u00028\u00002#\u00101\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0016\u0018\u00010]H\u0016¢\u0006\u0004\b^\u0010_Jp\u0010^\u001a\u00020\u0016\"\b\b\u0001\u0010(*\u00028\u00002\u0006\u0010.\u001a\u00028\u00012M\u00101\u001aI\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110/¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u0016\u0018\u00010+H\u0016¢\u0006\u0004\b^\u0010`J#\u0010b\u001a\u00020\u00162\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010a\u001a\u00020\tH\u0016¢\u0006\u0004\bb\u0010cJ8\u0010b\u001a\u00020\u00162'\u0010\u001f\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00160]j\u0002`dH\u0016¢\u0006\u0004\bb\u0010eJ\u0017\u0010h\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020NH\u0000¢\u0006\u0004\bf\u0010gJv\u0010k\u001a\u00020\u0016\"\u0004\b\u0001\u0010(2\u0006\u0010*\u001a\u00028\u00012\u0006\u0010\n\u001a\u00020\t2O\b\u0002\u00101\u001aI\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110/¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u0016\u0018\u00010+H\u0000¢\u0006\u0004\bi\u0010jJ\u000f\u0010m\u001a\u00020\u0016H\u0000¢\u0006\u0004\bl\u0010<J|\u0010\u001a\u001a\u0004\u0018\u00010\u001e\"\b\b\u0001\u0010(*\u00028\u00002\u0006\u0010.\u001a\u00028\u00012\b\u00102\u001a\u0004\u0018\u00010\u001e2M\u00101\u001aI\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110/¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u0016\u0018\u00010+H\u0016¢\u0006\u0004\b\u001a\u0010nJ\u0019\u0010p\u001a\u0004\u0018\u00010\u001e2\u0006\u0010o\u001a\u00020\u0010H\u0016¢\u0006\u0004\bp\u0010qJ\u0017\u0010s\u001a\u00020\u00162\u0006\u0010r\u001a\u00020\u001eH\u0016¢\u0006\u0004\bs\u0010!J\u001b\u0010u\u001a\u00020\u0016*\u00020t2\u0006\u0010.\u001a\u00028\u0000H\u0016¢\u0006\u0004\bu\u0010vJ\u001f\u0010y\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\"\u001a\u0004\u0018\u00010\u001eH\u0010¢\u0006\u0004\bw\u0010xJ\u001b\u0010|\u001a\u0004\u0018\u00010\u00102\b\u0010\"\u001a\u0004\u0018\u00010\u001eH\u0010¢\u0006\u0004\bz\u0010{J\u000f\u0010~\u001a\u00020}H\u0016¢\u0006\u0004\b~\u0010\u007fJ\u0011\u0010\u0080\u0001\u001a\u00020}H\u0014¢\u0006\u0005\b\u0080\u0001\u0010\u007fR#\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00078\u0000X\u0080\u0004¢\u0006\u000f\n\u0005\b\b\u0010\u0081\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001d\u00100\u001a\u00020/8\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b0\u0010\u0084\u0001\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0018\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u001b8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010\u001dR\u0016\u0010\u008a\u0001\u001a\u00020}8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0089\u0001\u0010\u007fR\u0017\u0010\"\u001a\u0004\u0018\u00010\u001e8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u008b\u0001\u0010DR\u0016\u0010\u008c\u0001\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008c\u0001\u0010\u000fR\u0016\u0010\u008d\u0001\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u0010\u000fR\u0016\u0010\u008e\u0001\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008e\u0001\u0010\u000fR\u001f\u0010\u0091\u0001\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\r\u0010\u0093\u0001\u001a\u00030\u0092\u00018\u0002X\u0082\u0004R\u0015\u0010\u0095\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u0094\u00018\u0002X\u0082\u0004R\u0015\u0010\u0096\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0094\u00018\u0002X\u0082\u0004¨\u0006\u0097\u0001"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/Waiter;", "Lkotlin/coroutines/Continuation;", "delegate", _UrlKt.FRAGMENT_ENCODE_SET, "resumeMode", "<init>", "(Lkotlin/coroutines/Continuation;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "isReusable", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "cause", "cancelLater", "(Ljava/lang/Throwable;)Z", "Lkotlinx/coroutines/internal/Segment;", "segment", _UrlKt.FRAGMENT_ENCODE_SET, "callSegmentOnCancellation", "(Lkotlinx/coroutines/internal/Segment;Ljava/lang/Throwable;)V", "trySuspend", "tryResume", "Lkotlinx/coroutines/DisposableHandle;", "installParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", _UrlKt.FRAGMENT_ENCODE_SET, "handler", "invokeOnCancellationImpl", "(Ljava/lang/Object;)V", "state", "multipleHandlersError", "(Ljava/lang/Object;Ljava/lang/Object;)V", "mode", "dispatchResume", "(I)V", "R", "Lkotlinx/coroutines/NotCompleted;", "proposedUpdate", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/CoroutineContext;", "context", "onCancellation", "idempotent", "resumedState", "(Lkotlinx/coroutines/NotCompleted;Ljava/lang/Object;ILkotlin/jvm/functions/Function3;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeImpl", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/internal/Symbol;", _UrlKt.FRAGMENT_ENCODE_SET, "alreadyResumedError", "(Ljava/lang/Object;)Ljava/lang/Void;", "detachChildIfNonReusable", "()V", "initCancellability", "resetStateReusable", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", "takenState", "cancelCompletedResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelCompletedResult", "cancel", "parentCancelled$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "parentCancelled", "Lkotlinx/coroutines/CancelHandler;", "callCancelHandler", "(Lkotlinx/coroutines/CancelHandler;Ljava/lang/Throwable;)V", "callOnCancellation", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Throwable;Ljava/lang/Object;)V", "Lkotlinx/coroutines/Job;", "parent", "getContinuationCancellationCause", "(Lkotlinx/coroutines/Job;)Ljava/lang/Throwable;", "getResult", "releaseClaimedReusableContinuation$kotlinx_coroutines_core", "releaseClaimedReusableContinuation", "Lkotlin/Result;", "result", "resumeWith", "Lkotlin/Function1;", "resume", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "index", "invokeOnCancellation", "(Lkotlinx/coroutines/internal/Segment;I)V", "Lkotlinx/coroutines/CompletionHandler;", "(Lkotlin/jvm/functions/Function1;)V", "invokeOnCancellationInternal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/CancelHandler;)V", "invokeOnCancellationInternal", "resumeImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;ILkotlin/jvm/functions/Function3;)V", "resumeImpl", "detachChild$kotlinx_coroutines_core", "detachChild", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "exception", "tryResumeWithException", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "token", "completeResume", "Lkotlinx/coroutines/CoroutineDispatcher;", "resumeUndispatched", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getSuccessfulResult", "getExceptionalResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getExceptionalResult", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "nameString", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getParentHandle", "parentHandle", "getStateDebugRepresentation", "stateDebugRepresentation", "getState$kotlinx_coroutines_core", "isActive", "isCompleted", "isCancelled", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "Lkotlinx/atomicfu/AtomicInt;", "_decisionAndIndex", "Lkotlinx/atomicfu/AtomicRef;", "_state", "_parentHandle", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@PublishedApi
@SourceDebugExtension({"SMAP\nCancellableContinuationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellableContinuationImpl.kt\nkotlinx/coroutines/CancellableContinuationImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CancellableContinuationImpl.kt\nkotlinx/coroutines/CancellableContinuationImplKt\n+ 4 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,701:1\n227#1,10:705\n227#1,10:716\n1#2:702\n20#3:703\n20#3:704\n18#3:715\n17#3:726\n18#3,3:727\n17#3:730\n18#3,3:731\n18#3:738\n17#3,4:739\n57#4,2:734\n57#4,2:736\n57#4,2:743\n*S KotlinDebug\n*F\n+ 1 CancellableContinuationImpl.kt\nkotlinx/coroutines/CancellableContinuationImpl\n*L\n239#1:705,10\n244#1:716,10\n69#1:703\n155#1:704\n242#1:715\n271#1:726\n272#1:727,3\n281#1:730\n282#1:731,3\n387#1:738\n390#1:739,4\n323#1:734,2\n333#1:736,2\n614#1:743,2\n*E\n"})
/* loaded from: classes.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame, Waiter {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _decisionAndIndex$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _parentHandle$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$volatile$FU = null;
    private volatile /* synthetic */ int _decisionAndIndex$volatile;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;
    private final CoroutineContext context;
    private final Continuation<T> delegate;

    /* renamed from: $r8$lambda$Dz09W-JjB9rjFw3DzYrFVSkki4w, reason: not valid java name */
    public static /* synthetic */ Unit m3520$r8$lambda$Dz09WJjB9rjFw3DzYrFVSkki4w(Function1 r0, Throwable r1, Object r2, CoroutineContext r3) {
        return resume$lambda$0$0(r0, r1, r2, r3);
    }

    static {
        _decisionAndIndex$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decisionAndIndex$volatile");
        _state$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state$volatile");
        _parentHandle$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_parentHandle$volatile");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(Continuation<? super T> r1, int r2) {
        super(r2);
        this.delegate = r1;
        this.context = r1.getContext();
        this._decisionAndIndex$volatile = 536870911;
        this._state$volatile = Active.INSTANCE;
    }

    private final Void alreadyResumedError(Object r3) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + r3).toString());
    }

    private final void callSegmentOnCancellation(Segment<?> r4, Throwable r5) {
        int r0 = get_decisionAndIndex$volatile$FU().get(this) & 536870911;
        if (r0 != 536870911) goto L11;
        Segment$$ExternalSyntheticBUOutline1.m("The index for Segment.onCancellation(..) is broken");
        return;
    L11:
        r4.onCancellation(r0, r5, getContext());     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th));
    }

    private final boolean cancelLater(Throwable r2) {
        if (isReusable() == true) goto L7;
        return false;
    L7:
        return ((DispatchedContinuation) this.delegate).postponeCancellation$kotlinx_coroutines_core(r2);
    }

    private final void detachChildIfNonReusable() {
        if (isReusable() == true) goto L6;
        detachChild$kotlinx_coroutines_core();
        return;
    }

    private final void dispatchResume(int r2) {
        if (tryResume() == false) goto L5;
        return;
    L5:
        DispatchedTaskKt.dispatch(this, r2);
    }

    private final DisposableHandle getParentHandle() {
        return (DisposableHandle) get_parentHandle$volatile$FU().get(this);
    }

    private final String getStateDebugRepresentation() {
        Object r1 = getState$kotlinx_coroutines_core();
        if ((r1 instanceof NotCompleted) == false) goto L7;
        return "Active";
    L7:
        if ((r1 instanceof CancelledContinuation) == false) goto L10;
        return "Cancelled";
    L10:
        return "Completed";
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_decisionAndIndex$volatile$FU() {
        return _decisionAndIndex$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_parentHandle$volatile$FU() {
        return _parentHandle$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_state$volatile$FU() {
        return _state$volatile$FU;
    }

    private final DisposableHandle installParentHandle() {
        Job r0 = (Job) getContext().get(Job.Key);
        if (r0 != null) goto L5;
        return null;
    L5:
        DisposableHandle r02 = JobKt.invokeOnCompletion$default(r0, false, new ChildContinuation(this), 1, null);
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_parentHandle$volatile$FU(), this, null, r02);
        return r02;
    }

    private final void invokeOnCancellationImpl(Object r12) {
        AtomicReferenceFieldUpdater r0 = get_state$volatile$FU();
    L3:
        Object r2 = r0.get(this);
        if ((r2 instanceof Active) == true) goto L6;
        if ((r2 instanceof CancelHandler) == true) goto L47;
        if ((r2 instanceof Segment) == true) goto L47;
        if ((r2 instanceof CompletedExceptionally) == true) goto L15;
        if ((r2 instanceof CompletedContinuation) == true) goto L28;
        if ((r12 instanceof Segment) == true) goto L43;
        CompletedContinuation r1 = new CompletedContinuation(r2, (CancelHandler) r12, null, null, null, 28, null);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r2, r1) == false) goto L3;
        return;
    L43:
        return;
    L28:
        CompletedContinuation r3 = (CompletedContinuation) r2;
        if (r3.cancelHandler == null) goto L32;
        multipleHandlersError(r12, r2);
    L32:
        if ((r12 instanceof Segment) == true) goto L33;
        CancelHandler r5 = (CancelHandler) r12;
        if (r3.getCancelled() == true) goto L36;
        CompletedContinuation r13 = CompletedContinuation.copy$default(r3, null, r5, null, null, null, 29, null);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r2, r13) == false) goto L3;
        return;
    L36:
        callCancelHandler(r5, r3.cancelCause);
        return;
    L33:
        return;
    L15:
        CompletedExceptionally r02 = (CompletedExceptionally) r2;
        if (r02.makeHandled() == true) goto L19;
        multipleHandlersError(r12, r2);
    L19:
        if ((r2 instanceof CancelledContinuation) == false) goto L46;
        Throwable r03 = r02.cause;
        if ((r12 instanceof CancelHandler) == false) goto L24;
        callCancelHandler((CancelHandler) r12, r03);
        return;
    L24:
        callSegmentOnCancellation((Segment) r12, r03);
        return;
    L46:
        return;
    L47:
        multipleHandlersError(r12, r2);
        goto L3
    L6:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r2, r12) == false) goto L3;
    }

    private final boolean isReusable() {
        if (DispatchedTaskKt.isReusableMode(this.resumeMode) == true) goto L5;
        return false;
    L5:
        if (((DispatchedContinuation) this.delegate).isReusable$kotlinx_coroutines_core() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private final void multipleHandlersError(Object r3, Object r4) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + r3 + ", already has " + r4).toString());
    }

    private static final Unit resume$lambda$0$0(Function1 r0, Throwable r1, Object r2, CoroutineContext r3) {
        r0.invoke(r1);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void resumeImpl$kotlinx_coroutines_core$default(CancellableContinuationImpl r0, Object r1, int r2, Function3 r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: resumeImpl");
        return;
    L4:
        if ((r4 & 4) == 0) goto L6;
        r3 = null;
    L6:
        r0.resumeImpl$kotlinx_coroutines_core(r1, r2, r3);
    }

    private final <R> Object resumedState(NotCompleted r9, R r10, int r11, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r12, Object r13) {
        if ((r10 instanceof CompletedExceptionally) == false) goto L6;
        return r10;
    L6:
        if (DispatchedTaskKt.isCancellableMode(r11) == true) goto L9;
        if (r13 != null) goto L9;
    L14:
        return r10;
    L9:
        if (r12 != null) goto L16;
        if ((r9 instanceof CancelHandler) == true) goto L16;
        if (r13 == null) goto L14;
    L16:
        if ((r9 instanceof CancelHandler) == false) goto L19;
        CancelHandler r92 = (CancelHandler) r9;
    L21:
        return new CompletedContinuation(r10, r92, r12, r13, null, 16, null);
    L19:
        r92 = null;
        goto L21
    }

    private final boolean tryResume() {
        AtomicIntegerFieldUpdater r0 = get_decisionAndIndex$volatile$FU();
    L3:
        int r1 = r0.get(this);
        int r2 = r1 >> 29;
        if (r2 != 0) goto L5;
        if (get_decisionAndIndex$volatile$FU().compareAndSet(this, r1, TLObject.FLAG_30 + (536870911 & r1)) == false) goto L3;
        return true;
    L5:
        if (r2 != 1) goto L8;
        return false;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Already resumed");
        return false;
    }

    private final <R> Symbol tryResumeImpl(R r10, Object r11, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r12) {
        AtomicReferenceFieldUpdater r0 = get_state$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        if ((r1 instanceof NotCompleted) == false) goto L10;
        CancellableContinuationImpl<T> r3 = this;
        R r5 = r10;
        Object r8 = r11;
        Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r7 = r12;
        Object r9 = r3.resumedState((NotCompleted) r1, r5, this.resumeMode, r7, r8);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), r3, r1, r9) == true) goto L7;
        CancellableContinuationImpl<T> this = r3;
        r10 = r5;
        r12 = r7;
        r11 = r8;
        goto L3
    L7:
        r3.detachChildIfNonReusable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    L10:
        Object r82 = r11;
        if ((r1 instanceof CompletedContinuation) == false) goto L17;
        if (r82 == null) goto L17;
        if (((CompletedContinuation) r1).idempotentResume != r82) goto L17;
        return CancellableContinuationImplKt.RESUME_TOKEN;
    L17:
        return null;
    }

    private final boolean trySuspend() {
        AtomicIntegerFieldUpdater r0 = get_decisionAndIndex$volatile$FU();
    L3:
        int r1 = r0.get(this);
        int r2 = r1 >> 29;
        if (r2 != 0) goto L6;
        if (get_decisionAndIndex$volatile$FU().compareAndSet(this, r1, 536870912 + (536870911 & r1)) == false) goto L3;
        return true;
    L6:
        if (r2 != 2) goto L9;
        return false;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("Already suspended");
        return false;
    }

    public final void callCancelHandler(CancelHandler r4, Throwable r5) {
        r4.invoke(r5);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> void callOnCancellation(Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r3, Throwable r4, R r5) {
        r3.invoke(r4, r5, getContext());     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th));
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(Throwable r7) {
        AtomicReferenceFieldUpdater r0 = get_state$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        boolean r3 = false;
        if ((r1 instanceof NotCompleted) == false) goto L5;
        if ((r1 instanceof CancelHandler) == false) goto L9;
    L10:
        r3 = true;
    L12:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r1, new CancelledContinuation(this, r7, r3)) == false) goto L3;
        NotCompleted r02 = (NotCompleted) r1;
        if ((r02 instanceof CancelHandler) == false) goto L17;
        callCancelHandler((CancelHandler) r1, r7);
    L19:
        detachChildIfNonReusable();
        dispatchResume(this.resumeMode);
        return true;
    L17:
        if ((r02 instanceof Segment) == false) goto L19;
        callSegmentOnCancellation((Segment) r1, r7);
        goto L19
    L9:
        if ((r1 instanceof Segment) == false) goto L12;
    L5:
        return false;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object r11, Throwable r12) {
        AtomicReferenceFieldUpdater r112 = get_state$volatile$FU();
    L3:
        Object r1 = r112.get(this);
        if ((r1 instanceof NotCompleted) == true) goto L22;
        if ((r1 instanceof CompletedExceptionally) == true) goto L20;
        if ((r1 instanceof CompletedContinuation) == false) goto L18;
        CompletedContinuation r2 = (CompletedContinuation) r1;
        if (r2.getCancelled() == true) goto L16;
        Throwable r7 = r12;
        CompletedContinuation r122 = CompletedContinuation.copy$default(r2, null, null, null, null, r7, 15, null);
        Throwable r5 = r7;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r1, r122) == false) goto L21;
        r2.invokeHandlers(this, r5);
        return;
    L21:
        r12 = r5;
        goto L3
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("Must be called at most once");
        return;
    L18:
        r5 = r12;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r1, new CompletedContinuation(r1, null, null, null, r5, 14, null)) == false) goto L21;
        return;
    L20:
        return;
    L22:
        Segment$$ExternalSyntheticBUOutline1.m("Not completed");
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(Object r1) {
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle r0 = getParentHandle();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.dispose();
        get_parentHandle$volatile$FU().set(this, NonDisposableHandle.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> r1 = this.delegate;
        if ((r1 instanceof CoroutineStackFrame) == true) goto L5;
        return null;
    L5:
        return (CoroutineStackFrame) r1;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.context;
    }

    public Throwable getContinuationCancellationCause(Job r1) {
        return r1.getCancellationException();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object r1) {
        Throwable r0 = super.getExceptionalResult$kotlinx_coroutines_core(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    @PublishedApi
    public final Object getResult() {
        boolean r0 = isReusable();
        if (trySuspend() == true) goto L5;
        if (r0 == false) goto L13;
        releaseClaimedReusableContinuation$kotlinx_coroutines_core();
    L13:
        Object r02 = getState$kotlinx_coroutines_core();
        if ((r02 instanceof CompletedExceptionally) == true) goto L27;
        if (DispatchedTaskKt.isCancellableMode(this.resumeMode) == false) goto L25;
        Job r1 = (Job) getContext().get(Job.Key);
        if (r1 == null) goto L25;
        if (r1.isActive() == true) goto L25;
        CancellationException r12 = r1.getCancellationException();
        cancelCompletedResult$kotlinx_coroutines_core(r02, r12);
        throw r12;
    L25:
        return getSuccessfulResult$kotlinx_coroutines_core(r02);
    L27:
        throw ((CompletedExceptionally) r02).cause;
    L5:
        if (getParentHandle() != null) goto L7;
        installParentHandle();
    L7:
        if (r0 == false) goto L10;
        releaseClaimedReusableContinuation$kotlinx_coroutines_core();
    L10:
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public final Object getState$kotlinx_coroutines_core() {
        return get_state$volatile$FU().get(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(Object r1) {
        if ((r1 instanceof CompletedContinuation) == true) goto L5;
        return r1;
    L5:
        return (T) ((CompletedContinuation) r1).result;
    }

    public void initCancellability() {
        DisposableHandle r0 = installParentHandle();
        if (r0 != null) goto L6;
        return;
    L6:
        if (isCompleted() == false) goto L9;
        r0.dispose();
        get_parentHandle$volatile$FU().set(this, NonDisposableHandle.INSTANCE);
        return;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(Function1<? super Throwable, Unit> r2) {
        CancellableContinuationKt.invokeOnCancellation(this, new CancelHandler.UserSupplied(r2));
    }

    @Override // kotlinx.coroutines.Waiter
    public void invokeOnCancellation(Segment<?> r5, int r6) {
        AtomicIntegerFieldUpdater r0 = get_decisionAndIndex$volatile$FU();
    L3:
        int r1 = r0.get(this);
        if ((r1 & 536870911) != 536870911) goto L9;
        if (r0.compareAndSet(this, r1, ((r1 >> 29) << 29) + r6) == false) goto L3;
        invokeOnCancellationImpl(r5);
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("invokeOnCancellation should be called at most once");
    }

    public final void invokeOnCancellationInternal$kotlinx_coroutines_core(CancelHandler r1) {
        invokeOnCancellationImpl(r1);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return getState$kotlinx_coroutines_core() instanceof NotCompleted;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return getState$kotlinx_coroutines_core() instanceof CancelledContinuation;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof NotCompleted);
    }

    public String nameString() {
        return "CancellableContinuation";
    }

    public final void parentCancelled$kotlinx_coroutines_core(Throwable r2) {
        if (cancelLater(r2) == false) goto L5;
        return;
    L5:
        cancel(r2);
        detachChildIfNonReusable();
    }

    public final void releaseClaimedReusableContinuation$kotlinx_coroutines_core() {
        Continuation<T> r0 = this.delegate;
        if ((r0 instanceof DispatchedContinuation) == false) goto L5;
        DispatchedContinuation r02 = (DispatchedContinuation) r0;
    L6:
        if (r02 == null) goto L12;
        Throwable r03 = r02.tryReleaseClaimedContinuation$kotlinx_coroutines_core(this);
        if (r03 == null) goto L13;
        detachChild$kotlinx_coroutines_core();
        cancel(r03);
        return;
    L13:
        return;
    L12:
        return;
    L5:
        r02 = null;
        goto L6
    }

    @JvmName(name = "resetStateReusable")
    public final boolean resetStateReusable() {
        Object r0 = get_state$volatile$FU().get(this);
        if ((r0 instanceof CompletedContinuation) == true) goto L5;
    L8:
        get_decisionAndIndex$volatile$FU().set(this, 536870911);
        get_state$volatile$FU().set(this, Active.INSTANCE);
        return true;
    L5:
        if (((CompletedContinuation) r0).idempotentResume == null) goto L8;
        detachChild$kotlinx_coroutines_core();
        return false;
    }

    public void resume(T r3, Function1<? super Throwable, Unit> r4) {
        int r0 = this.resumeMode;
        if (r4 == null) goto L5;
        CancellableContinuationImpl$$ExternalSyntheticLambda0 r1 = new CancellableContinuationImpl$$ExternalSyntheticLambda0(r4);
    L6:
        resumeImpl$kotlinx_coroutines_core(r3, r0, r1);
        return;
    L5:
        r1 = null;
        goto L6
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public <R extends T> void resume(R r2, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r3) {
        resumeImpl$kotlinx_coroutines_core(r2, this.resumeMode, r3);
    }

    public final <R> void resumeImpl$kotlinx_coroutines_core(R r10, int r11, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r12) {
        AtomicReferenceFieldUpdater r0 = get_state$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        if ((r1 instanceof NotCompleted) == false) goto L10;
        CancellableContinuationImpl<T> r3 = this;
        R r5 = r10;
        int r6 = r11;
        Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r7 = r12;
        Object r9 = r3.resumedState((NotCompleted) r1, r5, r6, r7, null);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), r3, r1, r9) == true) goto L7;
        CancellableContinuationImpl<T> this = r3;
        r10 = r5;
        r11 = r6;
        r12 = r7;
        goto L3
    L7:
        r3.detachChildIfNonReusable();
        r3.dispatchResume(r6);
        return;
    L10:
        CancellableContinuationImpl<T> r32 = this;
        R r52 = r10;
        Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r72 = r12;
        if ((r1 instanceof CancelledContinuation) == false) goto L17;
        CancelledContinuation r13 = (CancelledContinuation) r1;
        if (r13.makeResumed() == false) goto L17;
        if (r72 == null) goto L21;
        r32.callOnCancellation(r72, r13.cause, r52);
        return;
    L21:
        return;
    L17:
        r32.alreadyResumedError(r52);
        InstantKt$$ExternalSyntheticBUOutline0.m();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(CoroutineDispatcher r7, T r8) {
        Continuation<T> r0 = this.delegate;
        CoroutineDispatcher r2 = null;
        if ((r0 instanceof DispatchedContinuation) == false) goto L5;
        DispatchedContinuation r02 = (DispatchedContinuation) r0;
    L6:
        if (r02 == null) goto L8;
        r2 = r02.dispatcher;
    L8:
        if (r2 != r7) goto L11;
        int r72 = 4;
    L12:
        resumeImpl$kotlinx_coroutines_core$default(this, r8, r72, null, 4, null);
        return;
    L11:
        r72 = this.resumeMode;
        goto L12
    L5:
        r02 = null;
        goto L6
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object r7) {
        resumeImpl$kotlinx_coroutines_core$default(this, CompletionStateKt.toState(r7, this), this.resumeMode, null, 4, null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        return getState$kotlinx_coroutines_core();
    }

    public String toString() {
        return nameString() + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + getStateDebugRepresentation() + "}@" + DebugStringsKt.getHexAddress(this);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public <R extends T> Object tryResume(R r1, Object r2, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r3) {
        return tryResumeImpl(r1, r2, r3);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResumeWithException(Throwable r5) {
        return tryResumeImpl(new CompletedExceptionally(r5, false, 2, null), null, null);
    }
}
