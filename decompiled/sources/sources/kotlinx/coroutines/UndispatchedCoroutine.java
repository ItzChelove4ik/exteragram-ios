package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u0012\u001a\u00020\u000eJ\b\u0010\u0013\u001a\u00020\u0010H\u0016J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\u0016\u001a\u00020\u0010H\u0002R\"\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/UndispatchedCoroutine;", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "threadStateToRecover", "Ljava/lang/ThreadLocal;", "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "threadLocalIsSet", _UrlKt.FRAGMENT_ENCODE_SET, "saveThreadContext", _UrlKt.FRAGMENT_ENCODE_SET, "oldValue", "clearThreadContext", "afterCompletionUndispatched", "afterResume", "state", "clearThreadLocal", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoroutineContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContext.kt\nkotlinx/coroutines/UndispatchedCoroutine\n+ 2 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,331:1\n115#2,13:332\n*S KotlinDebug\n*F\n+ 1 CoroutineContext.kt\nkotlinx/coroutines/UndispatchedCoroutine\n*L\n277#1:332,13\n*E\n"})
/* loaded from: classes.dex */
public final class UndispatchedCoroutine<T> extends ScopeCoroutine<T> {
    private volatile boolean threadLocalIsSet;
    private final ThreadLocal<Pair<CoroutineContext, Object>> threadStateToRecover;

    public UndispatchedCoroutine(CoroutineContext r3, Continuation<? super T> r4) {
        UndispatchedMarker r0 = UndispatchedMarker.INSTANCE;
        if (r3.get(r0) != null) goto L5;
        CoroutineContext r02 = r3.plus(r0);
    L6:
        super(r02, r4);
        this.threadStateToRecover = new ThreadLocal();
        if ((r4.getContext().get(ContinuationInterceptor.Key) instanceof CoroutineDispatcher) == true) goto L10;
        Object r42 = ThreadContextKt.updateThreadContext(r3, null);
        ThreadContextKt.restoreThreadContext(r3, r42);
        saveThreadContext(r3, r42);
        return;
    L10:
        return;
    L5:
        r02 = r3;
        goto L6
    }

    private final void clearThreadLocal() {
        if (this.threadLocalIsSet == false) goto L9;
        Pair<CoroutineContext, Object> r0 = this.threadStateToRecover.get();
        if (r0 == null) goto L7;
        ThreadContextKt.restoreThreadContext(r0.component1(), r0.component2());
    L7:
        this.threadStateToRecover.remove();
        return;
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine
    public void afterCompletionUndispatched() {
        clearThreadLocal();
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    public void afterResume(Object r6) {
        clearThreadLocal();
        Object r62 = CompletionStateKt.recoverResult(r6, this.uCont);
        Continuation<T> r0 = this.uCont;
        CoroutineContext r1 = r0.getContext();
        UndispatchedCoroutine<?> r2 = null;
        Object r3 = ThreadContextKt.updateThreadContext(r1, null);
        if (r3 == ThreadContextKt.NO_THREAD_ELEMENTS) goto L19;
        r2 = CoroutineContextKt.updateUndispatchedCompletion(r0, r1, r3);
    L19:
        this.uCont.resumeWith(r62);     // Catch: Throwable -> L13
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L13
        if (r2 != null) goto L8;
    L11:
        ThreadContextKt.restoreThreadContext(r1, r3);
        return;
    L8:
        if (r2.clearThreadContext() == true) goto L11;
        return;
    L13:
        th = move-exception;
        if (r2 != null) goto L16;
    L17:
        ThreadContextKt.restoreThreadContext(r1, r3);
    L18:
        throw th;
    L16:
        if (r2.clearThreadContext() == false) goto L18;
        goto L17
    }

    public final boolean clearThreadContext() {
        if (this.threadLocalIsSet == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        this.threadStateToRecover.remove();
        return !r0;
    L5:
        if (this.threadStateToRecover.get() != null) goto L7;
        r0 = true;
        goto L8
    }

    public final void saveThreadContext(CoroutineContext r2, Object r3) {
        this.threadLocalIsSet = true;
        this.threadStateToRecover.set(TuplesKt.to(r2, r3));
    }
}
