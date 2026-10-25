package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ThreadLocalEventLoop;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u00028\u00000\u0005B\u001d\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0012\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0016\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0017\u0010\u0015J\u0017\u0010\u001c\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0019H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010!\u001a\u0004\u0018\u00010\u001e2\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0000¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010%\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u001eH\u0000¢\u0006\u0004\b#\u0010$J\u0011\u0010)\u001a\u0004\u0018\u00010&H\u0010¢\u0006\u0004\b'\u0010(J\u001d\u0010,\u001a\u00020\u00132\f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000*H\u0016¢\u0006\u0004\b,\u0010-J\u001f\u00103\u001a\u00020\u00132\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00028\u0000H\u0000¢\u0006\u0004\b1\u00102J\u000f\u00105\u001a\u000204H\u0016¢\u0006\u0004\b5\u00106R\u0014\u0010\u0007\u001a\u00020\u00068\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0007\u00107R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u00108R\u001e\u00109\u001a\u0004\u0018\u00010&8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b9\u0010:\u0012\u0004\b;\u0010\u0015R\u0014\u0010<\u001a\u00020&8\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b<\u0010:R\u001a\u0010>\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b=\u0010\u001bR\u001c\u0010A\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0014\u0010/\u001a\u00020.8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0013\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0G8\u0002X\u0082\u0004¨\u0006I"}, d2 = {"Lkotlinx/coroutines/internal/DispatchedContinuation;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "continuation", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", _UrlKt.FRAGMENT_ENCODE_SET, "isReusable$kotlinx_coroutines_core", "()Z", "isReusable", _UrlKt.FRAGMENT_ENCODE_SET, "awaitReusability$kotlinx_coroutines_core", "()V", "awaitReusability", "release$kotlinx_coroutines_core", BuildConfig.BUILD_TYPE, "Lkotlinx/coroutines/CancellableContinuationImpl;", "claimReusableCancellableContinuation$kotlinx_coroutines_core", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "claimReusableCancellableContinuation", "Lkotlinx/coroutines/CancellableContinuation;", _UrlKt.FRAGMENT_ENCODE_SET, "tryReleaseClaimedContinuation$kotlinx_coroutines_core", "(Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Throwable;", "tryReleaseClaimedContinuation", "cause", "postponeCancellation$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)Z", "postponeCancellation", _UrlKt.FRAGMENT_ENCODE_SET, "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", "Lkotlin/Result;", "result", "resumeWith", "(Ljava/lang/Object;)V", "Lkotlin/coroutines/CoroutineContext;", "context", "value", "dispatchYield$kotlinx_coroutines_core", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "dispatchYield", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/coroutines/Continuation;", "_state", "Ljava/lang/Object;", "get_state$kotlinx_coroutines_core$annotations", "countOrElement", "getReusableCancellableContinuation", "reusableCancellableContinuation", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "delegate", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/atomicfu/AtomicRef;", "_reusableCancellableContinuation", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDispatchedContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuationKt\n+ 4 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 5 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,322:1\n224#1,8:386\n236#1:394\n237#1,2:405\n239#1:409\n1#2:323\n1#2:329\n1#2:370\n302#3,5:324\n307#3,12:330\n319#3:364\n302#3,5:365\n307#3,12:371\n319#3:424\n184#4,3:342\n187#4,14:350\n184#4,3:383\n187#4,14:410\n103#5,5:345\n115#5,10:395\n126#5,2:407\n115#5,13:425\n*S KotlinDebug\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n*L\n214#1:386,8\n215#1:394\n215#1:405,2\n215#1:409\n195#1:329\n213#1:370\n195#1:324,5\n195#1:330,12\n195#1:364\n213#1:365,5\n213#1:371,12\n213#1:424\n195#1:342,3\n195#1:350,14\n213#1:383,3\n213#1:410,14\n196#1:345,5\n215#1:395,10\n215#1:407,2\n236#1:425,13\n*E\n"})
/* loaded from: classes.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _reusableCancellableContinuation$volatile$FU = null;
    private volatile /* synthetic */ Object _reusableCancellableContinuation$volatile;

    @JvmField
    public Object _state;

    @JvmField
    public final Continuation<T> continuation;

    @JvmField
    public final Object countOrElement;

    @JvmField
    public final CoroutineDispatcher dispatcher;

    static {
        _reusableCancellableContinuation$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation$volatile");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(CoroutineDispatcher r2, Continuation<? super T> r3) {
        super(-1);
        this.dispatcher = r2;
        this.continuation = r3;
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
    }

    private final CancellableContinuationImpl<?> getReusableCancellableContinuation() {
        Object r1 = get_reusableCancellableContinuation$volatile$FU().get(this);
        if ((r1 instanceof CancellableContinuationImpl) == true) goto L5;
        return null;
    L5:
        return (CancellableContinuationImpl) r1;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_reusableCancellableContinuation$volatile$FU() {
        return _reusableCancellableContinuation$volatile$FU;
    }

    public final void awaitReusability$kotlinx_coroutines_core() {
        AtomicReferenceFieldUpdater r0 = get_reusableCancellableContinuation$volatile$FU();
    L4:
        if (r0.get(this) == DispatchedContinuationKt.REUSABLE_CLAIMED) goto L4;
    }

    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation$kotlinx_coroutines_core() {
        AtomicReferenceFieldUpdater r0 = get_reusableCancellableContinuation$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        if (r1 == null) goto L5;
        if ((r1 instanceof CancellableContinuationImpl) == true) goto L10;
        if (r1 == DispatchedContinuationKt.REUSABLE_CLAIMED) goto L3;
        if ((r1 instanceof Throwable) == true) goto L3;
        DurationKt$$ExternalSyntheticBUOutline0.m("Inconsistent state ", r1);
        return null;
    L10:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_reusableCancellableContinuation$volatile$FU(), this, r1, DispatchedContinuationKt.REUSABLE_CLAIMED) == false) goto L3;
        return (CancellableContinuationImpl) r1;
    L5:
        get_reusableCancellableContinuation$volatile$FU().set(this, DispatchedContinuationKt.REUSABLE_CLAIMED);
        return null;
    }

    public final void dispatchYield$kotlinx_coroutines_core(CoroutineContext r1, T r2) {
        this._state = r2;
        this.resumeMode = 1;
        this.dispatcher.dispatchYield(r1, this);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> r1 = this.continuation;
        if ((r1 instanceof CoroutineStackFrame) == true) goto L5;
        return null;
    L5:
        return (CoroutineStackFrame) r1;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public final boolean isReusable$kotlinx_coroutines_core() {
        if (get_reusableCancellableContinuation$volatile$FU().get(this) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean postponeCancellation$kotlinx_coroutines_core(Throwable r6) {
        AtomicReferenceFieldUpdater r0 = get_reusableCancellableContinuation$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        Symbol r2 = DispatchedContinuationKt.REUSABLE_CLAIMED;
        if (Intrinsics.areEqual(r1, r2) == true) goto L6;
        if ((r1 instanceof Throwable) == true) goto L10;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_reusableCancellableContinuation$volatile$FU(), this, r1, null) == false) goto L3;
        return false;
    L10:
        return true;
    L6:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_reusableCancellableContinuation$volatile$FU(), this, r2, r6) == false) goto L3;
        return true;
    }

    public final void release$kotlinx_coroutines_core() {
        awaitReusability$kotlinx_coroutines_core();
        CancellableContinuationImpl<?> r0 = getReusableCancellableContinuation();
        if (r0 == null) goto L6;
        r0.detachChild$kotlinx_coroutines_core();
        return;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object r6) {
        Object r0 = CompletionStateKt.toState(r6);
        if (DispatchedContinuationKt.safeIsDispatchNeeded(this.dispatcher, getContext()) == false) goto L6;
        this._state = r0;
        this.resumeMode = 0;
        DispatchedContinuationKt.safeDispatch(this.dispatcher, getContext(), this);
        return;
    L6:
        EventLoop r1 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (r1.isUnconfinedLoopActive() == false) goto L9;
        this._state = r0;
        this.resumeMode = 0;
        r1.dispatchUnconfined(this);
        return;
    L9:
        r1.incrementUseCount(true);
        CoroutineContext r2 = getContext();     // Catch: Throwable -> L16
        Object r3 = ThreadContextKt.updateThreadContext(r2, this.countOrElement);     // Catch: Throwable -> L16
        this.continuation.resumeWith(r6);     // Catch: Throwable -> L18
        Unit r62 = Unit.INSTANCE;     // Catch: Throwable -> L18
        ThreadContextKt.restoreThreadContext(r2, r3);     // Catch: Throwable -> L16
    L14:
        if (r1.processUnconfinedEvent() == true) goto L14;
    L15:
        r1.decrementUseCount(true);
        return;
    L18:
        th = move-exception;
        ThreadContextKt.restoreThreadContext(r2, r3);     // Catch: Throwable -> L16
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        handleFatalException$kotlinx_coroutines_core(th);     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        r1.decrementUseCount(true);
        throw th;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        Object r0 = this._state;
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        return r0;
    }

    public String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStringsKt.toDebugString(this.continuation) + ']';
    }

    public final Throwable tryReleaseClaimedContinuation$kotlinx_coroutines_core(CancellableContinuation<?> r5) {
        AtomicReferenceFieldUpdater r0 = get_reusableCancellableContinuation$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        Symbol r2 = DispatchedContinuationKt.REUSABLE_CLAIMED;
        if (r1 != r2) goto L9;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_reusableCancellableContinuation$volatile$FU(), this, r2, r5) == false) goto L3;
        return null;
    L9:
        if ((r1 instanceof Throwable) == true) goto L11;
        DurationKt$$ExternalSyntheticBUOutline0.m("Inconsistent state ", r1);
        return null;
    L11:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_reusableCancellableContinuation$volatile$FU(), this, r1, null) == true) goto L13;
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    L13:
        return (Throwable) r1;
    }
}
