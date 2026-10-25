package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aT\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0086@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\t\u0010\n\u001aT\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\f\u001a\u00020\u000b2'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0086@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\r\u0010\n\u001aI\u0010\u000e\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0086@¢\u0006\u0004\b\u000e\u0010\n\u001a^\u0010\u0012\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u000f\"\b\b\u0001\u0010\u0000*\u00028\u00002\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00102'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a1\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0000¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"T", _UrlKt.FRAGMENT_ENCODE_SET, "timeMillis", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "withTimeout", "(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/time/Duration;", "timeout", "withTimeout-KLykuaI", "withTimeoutOrNull", "U", "Lkotlinx/coroutines/TimeoutCoroutine;", "coroutine", "setupTimeout", "(Lkotlinx/coroutines/TimeoutCoroutine;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "time", "Lkotlinx/coroutines/Delay;", "delay", "Lkotlinx/coroutines/Job;", _UrlKt.FRAGMENT_ENCODE_SET, "coroutineName", "Lkotlinx/coroutines/TimeoutCancellationException;", "TimeoutCancellationException", "(JLkotlinx/coroutines/Delay;Lkotlinx/coroutines/Job;Ljava/lang/String;)Lkotlinx/coroutines/TimeoutCancellationException;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timeout.kt\nkotlinx/coroutines/TimeoutKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,283:1\n1#2:284\n*E\n"})
/* loaded from: classes5.dex */
public abstract class TimeoutKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.TimeoutKt", f = "Timeout.kt", i = {0, 0, 0}, l = {156}, m = "withTimeoutOrNull", n = {"block", "coroutine", "timeMillis"}, s = {"L$0", "L$1", "J$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.TimeoutKt$withTimeoutOrNull$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return TimeoutKt.withTimeoutOrNull(0, null, this);
        }
    }

    public static final TimeoutCancellationException TimeoutCancellationException(long r1, Delay r3, Job r4, String r5) {
        String r12 = "Timed out waiting for " + r1 + " ms";
        if (r5 == null) goto L9;
        StringBuilder r2 = new StringBuilder("Coroutine \"");
        r2.append(r5);
        r2.append("\" ");
        if (r12.length() <= 0) goto L7;
        r12 = Character.toLowerCase(r12.charAt(0)) + r12.substring(1);
    L7:
        r2.append(r12);
        r12 = r2.toString();
    L9:
        return new TimeoutCancellationException(r12, r4);
    }

    private static final <U, T extends U> Object setupTimeout(TimeoutCoroutine<U, ? super T> r4, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r5) {
        JobKt.disposeOnCompletion(r4, DelayKt.getDelay(r4.uCont.getContext()).invokeOnTimeout(r4.time, r4, r4.getContext()));
        return UndispatchedKt.startUndispatchedOrReturnIgnoreTimeout(r4, r4, r5);
    }

    public static final <T> Object withTimeout(long r2, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r4, Continuation<? super T> r5) {
        if (r2 <= 0) goto L9;
        Object r22 = setupTimeout(new TimeoutCoroutine(r2, r5), r4);
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L7;
        DebugProbesKt.probeCoroutineSuspended(r5);
    L7:
        return r22;
    L9:
        throw new TimeoutCancellationException("Timed out immediately");
    }

    /* renamed from: withTimeout-KLykuaI, reason: not valid java name */
    public static final <T> Object m3523withTimeoutKLykuaI(long r0, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r2, Continuation<? super T> r3) {
        return withTimeout(DelayKt.m3522toDelayMillisLRDsOJo(r0), r2, r3);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, kotlinx.coroutines.TimeoutCoroutine] */
    public static final <T> Object withTimeoutOrNull(long r7, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r9, Continuation<? super T> r10) {
        if ((r10 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        Ref.ObjectRef r72 = (Ref.ObjectRef) r0.L$1;
        Function2 r8 = (Function2) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: TimeoutCancellationException -> L14
        return r102;
    L14:
        e = e;
    L32:
        if (e.coroutine != r72.element) goto L34;
        return null;
    L34:
        throw e;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r102);
        if (r7 > 0) goto L21;
        return null;
    L21:
        Ref.ObjectRef r103 = new Ref.ObjectRef();
        r0.L$0 = r9;     // Catch: TimeoutCancellationException -> L26
        r0.L$1 = r103;     // Catch: TimeoutCancellationException -> L26
        r0.J$0 = r7;     // Catch: TimeoutCancellationException -> L26
        r0.label = 1;     // Catch: TimeoutCancellationException -> L26
        ?? r22 = (T) new TimeoutCoroutine(r7, r0);
        r103.element = r22;     // Catch: TimeoutCancellationException -> L26
        Object r73 = setupTimeout(r22, r9);     // Catch: TimeoutCancellationException -> L26
        if (r73 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L28;
        DebugProbesKt.probeCoroutineSuspended(r0);     // Catch: TimeoutCancellationException -> L26
    L28:
        if (r73 != r12) goto L30;
        return r12;
    L30:
        return r73;
    L26:
        e = e;
        r72 = r103;
    L7:
        r0 = new AnonymousClass1(r10);
        goto L8
    }
}
