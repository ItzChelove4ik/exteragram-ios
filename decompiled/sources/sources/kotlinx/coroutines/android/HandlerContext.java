package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.NonDisposableHandle;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u0016H\u0016J\u001e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u001bH\u0016J$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0006H\u0016J\u0013\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0096\u0002J\b\u0010#\u001a\u00020$H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006%"}, d2 = {"Lkotlinx/coroutines/android/HandlerContext;", "Lkotlinx/coroutines/android/HandlerDispatcher;", "Lkotlinx/coroutines/Delay;", "handler", "Landroid/os/Handler;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "invokeImmediately", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroid/os/Handler;Ljava/lang/String;Z)V", "(Landroid/os/Handler;Ljava/lang/String;)V", "immediate", "getImmediate", "()Lkotlinx/coroutines/android/HandlerContext;", "isDispatchNeeded", "context", "Lkotlin/coroutines/CoroutineContext;", "dispatch", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "scheduleResumeAfterDelay", "timeMillis", _UrlKt.FRAGMENT_ENCODE_SET, "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "cancelOnRejection", "toString", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-android"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHandlerDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HandlerDispatcher.kt\nkotlinx/coroutines/android/HandlerContext\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,212:1\n1#2:213\n*E\n"})
/* loaded from: classes.dex */
public final class HandlerContext extends HandlerDispatcher implements Delay {
    private final Handler handler;
    private final HandlerContext immediate;
    private final boolean invokeImmediately;
    private final String name;

    public static Unit $r8$lambda$O8cClcYwqo3P8dLrvZL3R9RRk44(HandlerContext r0, Runnable r1, Throwable r2) {
        r0.handler.removeCallbacks(r1);
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$iEf6rkPOckkawr7Fyn6goXhizTg(HandlerContext r0, Runnable r1) {
        r0.handler.removeCallbacks(r1);
    }

    public static void $r8$lambda$uromBBUYtZVkDEDRZY7vKjR1AV4(CancellableContinuation r1, HandlerContext r2) {
        r1.resumeUndispatched(r2, Unit.INSTANCE);
    }

    public HandlerContext(Handler r2, String r3) {
        this(r2, r3, false);
    }

    public /* synthetic */ HandlerContext(Handler r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    private HandlerContext(Handler r2, String r3, boolean r4) {
        super(null);
        this.handler = r2;
        this.name = r3;
        this.invokeImmediately = r4;
        if (r4 == false) goto L5;
        HandlerContext r42 = this;
    L6:
        this.immediate = r42;
        return;
    L5:
        r42 = new HandlerContext(r2, r3, true);
        goto L6
    }

    private final void cancelOnRejection(CoroutineContext r4, Runnable r5) {
        JobKt.cancel(r4, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        Dispatchers.getIO().dispatch(r4, r5);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext r2, Runnable r3) {
        if (this.handler.post(r3) == true) goto L6;
        cancelOnRejection(r2, r3);
        return;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof HandlerContext) == false) goto L10;
        HandlerContext r32 = (HandlerContext) r3;
        if (r32.handler == this.handler) goto L7;
        return false;
    L7:
        if (r32.invokeImmediately != this.invokeImmediately) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    public /* bridge */ /* synthetic */ MainCoroutineDispatcher getImmediate() {
        return getImmediate();
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    public HandlerContext getImmediate() {
        return this.immediate;
    }

    public int hashCode() {
        int r0 = System.identityHashCode(this.handler);
        if (this.invokeImmediately == false) goto L5;
        int r1 = 1231;
    L7:
        return r1 ^ r0;
    L5:
        r1 = 1237;
        goto L7
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long r4, Runnable r6, CoroutineContext r7) {
        if (this.handler.postDelayed(r6, RangesKt.coerceAtMost(r4, DurationKt.MAX_MILLIS)) == true) goto L5;
        cancelOnRejection(r7, r6);
        return NonDisposableHandle.INSTANCE;
    L5:
        return new HandlerContext$$ExternalSyntheticLambda2(this, r6);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(CoroutineContext r1) {
        if (this.invokeImmediately == true) goto L5;
        return true;
    L5:
        if (Intrinsics.areEqual(Looper.myLooper(), this.handler.getLooper()) == false) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long r5, CancellableContinuation<? super Unit> r7) {
        HandlerContext$$ExternalSyntheticLambda0 r0 = new HandlerContext$$ExternalSyntheticLambda0(r7, this);
        if (this.handler.postDelayed(r0, RangesKt.coerceAtMost(r5, DurationKt.MAX_MILLIS)) == false) goto L6;
        r7.invokeOnCancellation(new HandlerContext$$ExternalSyntheticLambda1(this, r0));
        return;
    L6:
        cancelOnRejection(r7.getContext(), r0);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String r0 = toStringInternalImpl();
        if (r0 != null) goto L11;
        r0 = this.name;
        if (r0 != null) goto L8;
        r0 = this.handler.toString();
    L8:
        if (this.invokeImmediately == false) goto L11;
        return r0 + ".immediate";
    L11:
        return r0;
    }
}
