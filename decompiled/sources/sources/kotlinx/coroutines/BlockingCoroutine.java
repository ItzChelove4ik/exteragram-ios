package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u000b\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/BlockingCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "blockedThread", "Ljava/lang/Thread;", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "<init>", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Thread;Lkotlinx/coroutines/EventLoop;)V", "isScopedCoroutine", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "afterCompletion", _UrlKt.FRAGMENT_ENCODE_SET, "state", _UrlKt.FRAGMENT_ENCODE_SET, "joinBlocking", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Builders.kt\nkotlinx/coroutines/BlockingCoroutine\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,72:1\n1#2:73\n*E\n"})
/* loaded from: classes.dex */
final class BlockingCoroutine<T> extends AbstractCoroutine<T> {
    private final Thread blockedThread;
    private final EventLoop eventLoop;

    public BlockingCoroutine(CoroutineContext r2, Thread r3, EventLoop r4) {
        super(r2, true, true);
        this.blockedThread = r3;
        this.eventLoop = r4;
    }

    @Override // kotlinx.coroutines.JobSupport
    public void afterCompletion(Object r2) {
        if (Intrinsics.areEqual(Thread.currentThread(), this.blockedThread) == true) goto L6;
        Thread r1 = this.blockedThread;
        AbstractTimeSourceKt.access$getTimeSource$p();
        LockSupport.unpark(r1);
        return;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean isScopedCoroutine() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final T joinBlocking() {
        AbstractTimeSourceKt.access$getTimeSource$p();
        EventLoop r0 = this.eventLoop;     // Catch: Throwable -> L32
        CompletedExceptionally r3 = null;
        if (r0 == null) goto L35;
        EventLoop.incrementUseCount$default(r0, false, 1, null);     // Catch: Throwable -> L32
    L35:
        EventLoop r02 = this.eventLoop;     // Catch: Throwable -> L9
        if (r02 == null) goto L11;
        long r4 = r02.processNextEvent();     // Catch: Throwable -> L9
    L13:
        if (isCompleted() == true) goto L18;
        AbstractTimeSourceKt.access$getTimeSource$p();     // Catch: Throwable -> L9
        LockSupport.parkNanos(this, r4);     // Catch: Throwable -> L9
        if (Thread.interrupted() == false) goto L35;
        cancelCoroutine(new InterruptedException());     // Catch: Throwable -> L9
        goto L35
    L18:
        EventLoop r03 = this.eventLoop;     // Catch: Throwable -> L32
        if (r03 == null) goto L21;
        EventLoop.decrementUseCount$default(r03, false, 1, null);     // Catch: Throwable -> L32
    L21:
        AbstractTimeSourceKt.access$getTimeSource$p();
        T r6 = (T) JobSupportKt.unboxState(getState$kotlinx_coroutines_core());
        if ((r6 instanceof CompletedExceptionally) == false) goto L24;
        r3 = (CompletedExceptionally) r6;
    L24:
        if (r3 != null) goto L27;
        return r6;
    L27:
        throw r3.cause;
    L11:
        r4 = LongCompanionObject.MAX_VALUE;
        goto L13
    L9:
        th = move-exception;
        EventLoop r62 = this.eventLoop;     // Catch: Throwable -> L32
        if (r62 == null) goto L31;
        EventLoop.decrementUseCount$default(r62, false, 1, null);     // Catch: Throwable -> L32
    L31:
        throw th;     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        AbstractTimeSourceKt.access$getTimeSource$p();
        throw th;
    }
}
