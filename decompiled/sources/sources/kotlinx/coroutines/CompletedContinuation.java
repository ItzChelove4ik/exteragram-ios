package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0084\u0001\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012O\b\u0002\u0010\u000f\u001aI\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0016\u001a\u00020\u000e2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0095\u0001\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042O\b\u0002\u0010\u000f\u001aI\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\"\u0010#R\u0014\u0010\u0003\u001a\u00028\u00008\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%R[\u0010\u000f\u001aI\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010&R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010$R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010'R\u0011\u0010*\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lkotlinx/coroutines/CompletedContinuation;", "R", _UrlKt.FRAGMENT_ENCODE_SET, "result", "Lkotlinx/coroutines/CancelHandler;", "cancelHandler", "Lkotlin/Function3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", "value", "Lkotlin/coroutines/CoroutineContext;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "onCancellation", "idempotentResume", "cancelCause", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Throwable;)V", "Lkotlinx/coroutines/CancellableContinuationImpl;", "cont", "invokeHandlers", "(Lkotlinx/coroutines/CancellableContinuationImpl;Ljava/lang/Throwable;)V", "copy", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Throwable;)Lkotlinx/coroutines/CompletedContinuation;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Object;", "Lkotlinx/coroutines/CancelHandler;", "Lkotlin/jvm/functions/Function3;", "Ljava/lang/Throwable;", "getCancelled", "()Z", "cancelled", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCancellableContinuationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellableContinuationImpl.kt\nkotlinx/coroutines/CompletedContinuation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,701:1\n1#2:702\n*E\n"})
/* loaded from: classes.dex */
final class CompletedContinuation<R> {

    @JvmField
    public final Throwable cancelCause;

    @JvmField
    public final CancelHandler cancelHandler;

    @JvmField
    public final Object idempotentResume;

    @JvmField
    public final Function3<Throwable, R, CoroutineContext, Unit> onCancellation;

    @JvmField
    public final R result;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedContinuation(R r1, CancelHandler r2, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r3, Object r4, Throwable r5) {
        this.result = r1;
        this.cancelHandler = r2;
        this.onCancellation = r3;
        this.idempotentResume = r4;
        this.cancelCause = r5;
    }

    public /* synthetic */ CompletedContinuation(Object r2, CancelHandler r3, Function3 r4, Object r5, Throwable r6, int r7, DefaultConstructorMarker r8) {
        if ((r7 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r7 & 4) == 0) goto L9;
        r4 = null;
    L9:
        if ((r7 & 8) == 0) goto L12;
        r5 = null;
    L12:
        if ((r7 & 16) == 0) goto L14;
        r6 = null;
    L14:
        this(r2, r3, r4, r5, r6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CompletedContinuation copy$default(CompletedContinuation r0, Object r1, CancelHandler r2, Function3 r3, Object r4, Throwable r5, int r6, Object r7) {
        R r12 = r1;
        if ((r6 & 1) == 0) goto L6;
        r12 = r0.result;
    L6:
        if ((r6 & 2) == 0) goto L9;
        r2 = r0.cancelHandler;
    L9:
        if ((r6 & 4) == 0) goto L12;
        r3 = r0.onCancellation;
    L12:
        if ((r6 & 8) == 0) goto L15;
        r4 = r0.idempotentResume;
    L15:
        if ((r6 & 16) == 0) goto L17;
        r5 = r0.cancelCause;
    L17:
        Object r62 = r4;
        Throwable r72 = r5;
        Function3 r52 = r3;
        R r32 = r12;
        return r0.copy(r32, r2, r52, r62, r72);
    }

    public final CompletedContinuation<R> copy(R r1, CancelHandler r2, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r3, Object r4, Throwable r5) {
        return new CompletedContinuation(r1, r2, r3, r4, r5);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CompletedContinuation) == true) goto L8;
        return false;
    L8:
        CompletedContinuation r52 = (CompletedContinuation) r5;
        if (Intrinsics.areEqual(this.result, r52.result) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.cancelHandler, r52.cancelHandler) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.onCancellation, r52.onCancellation) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.idempotentResume, r52.idempotentResume) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.cancelCause, r52.cancelCause) == true) goto L23;
        return false;
    L23:
        return true;
    }

    public final boolean getCancelled() {
        if (this.cancelCause == null) goto L6;
        return true;
    L6:
        return false;
    }

    public int hashCode() {
        R r0 = this.result;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        CancelHandler r2 = this.cancelHandler;
        if (r2 != null) goto L9;
        int r22 = 0;
    L10:
        int r04 = (r03 + r22) * 31;
        Function3<Throwable, R, CoroutineContext, Unit> r23 = this.onCancellation;
        if (r23 != null) goto L13;
        int r24 = 0;
    L14:
        int r05 = (r04 + r24) * 31;
        Object r25 = this.idempotentResume;
        if (r25 != null) goto L17;
        int r26 = 0;
    L18:
        int r06 = (r05 + r26) * 31;
        Throwable r3 = this.cancelCause;
        if (r3 == null) goto L23;
        r1 = r3.hashCode();
    L23:
        return r06 + r1;
    L17:
        r26 = r25.hashCode();
        goto L18
    L13:
        r24 = r23.hashCode();
        goto L14
    L9:
        r22 = r2.hashCode();
        goto L10
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public final void invokeHandlers(CancellableContinuationImpl<?> r2, Throwable r3) {
        CancelHandler r0 = this.cancelHandler;
        if (r0 == null) goto L5;
        r2.callCancelHandler(r0, r3);
    L5:
        Function3<Throwable, R, CoroutineContext, Unit> r02 = this.onCancellation;
        if (r02 == null) goto L9;
        r2.callOnCancellation(r02, r3, this.result);
        return;
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=" + this.idempotentResume + ", cancelCause=" + this.cancelCause + ')';
    }
}
