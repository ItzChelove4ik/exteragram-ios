package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.LimitedDispatcherKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b \u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0010\u001a\u00020\u000f2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\r¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0013J\u001f\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001b\u0010\u0003R\u0016\u0010\u001c\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\"\u0010!\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\nR\u0011\u0010%\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b%\u0010\fR\u0011\u0010&\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b&\u0010\f¨\u0006'"}, d2 = {"Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "unconfined", _UrlKt.FRAGMENT_ENCODE_SET, "delta", "(Z)J", "processNextEvent", "()J", "processUnconfinedEvent", "()Z", "Lkotlinx/coroutines/DispatchedTask;", "task", _UrlKt.FRAGMENT_ENCODE_SET, "dispatchUnconfined", "(Lkotlinx/coroutines/DispatchedTask;)V", "incrementUseCount", "(Z)V", "decrementUseCount", _UrlKt.FRAGMENT_ENCODE_SET, "parallelism", _UrlKt.FRAGMENT_ENCODE_SET, "name", "limitedParallelism", "(ILjava/lang/String;)Lkotlinx/coroutines/CoroutineDispatcher;", "shutdown", "useCount", "J", "shared", "Z", "Lkotlin/collections/ArrayDeque;", "unconfinedQueue", "Lkotlin/collections/ArrayDeque;", "getNextTime", "nextTime", "isUnconfinedLoopActive", "isUnconfinedQueueEmpty", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoop\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,540:1\n1#2:541\n*E\n"})
/* loaded from: classes.dex */
public abstract class EventLoop extends CoroutineDispatcher {
    private boolean shared;
    private ArrayDeque<DispatchedTask<?>> unconfinedQueue;
    private long useCount;

    public EventLoop() {
    }

    public static /* synthetic */ void decrementUseCount$default(EventLoop r0, boolean r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: decrementUseCount");
        return;
    L4:
        if ((r2 & 1) == 0) goto L6;
        r1 = false;
    L6:
        r0.decrementUseCount(r1);
    }

    private final long delta(boolean r1) {
        if (r1 == false) goto L5;
        return 4294967296L;
    L5:
        return 1;
    }

    public static /* synthetic */ void incrementUseCount$default(EventLoop r0, boolean r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: incrementUseCount");
        return;
    L4:
        if ((r2 & 1) == 0) goto L6;
        r1 = false;
    L6:
        r0.incrementUseCount(r1);
    }

    public final void decrementUseCount(boolean r5) {
        long r0 = this.useCount - delta(r5);
        this.useCount = r0;
        if (r0 <= 0) goto L6;
        return;
    L6:
        if (this.shared == false) goto L9;
        shutdown();
        return;
    }

    public final void dispatchUnconfined(DispatchedTask<?> r2) {
        ArrayDeque<DispatchedTask<?>> r0 = this.unconfinedQueue;
        if (r0 != null) goto L5;
        r0 = new ArrayDeque();
        this.unconfinedQueue = r0;
    L5:
        r0.addLast(r2);
    }

    public long getNextTime() {
        ArrayDeque<DispatchedTask<?>> r2 = this.unconfinedQueue;
        if (r2 != null) goto L6;
        return LongCompanionObject.MAX_VALUE;
    L6:
        if (r2.isEmpty() == false) goto L8;
        return LongCompanionObject.MAX_VALUE;
    L8:
        return 0;
    }

    public final void incrementUseCount(boolean r5) {
        this.useCount += delta(r5);
        if (r5 == true) goto L6;
        this.shared = true;
        return;
    }

    public final boolean isUnconfinedLoopActive() {
        if (this.useCount < delta(true)) goto L5;
        return true;
    L5:
        return false;
    }

    public final boolean isUnconfinedQueueEmpty() {
        ArrayDeque<DispatchedTask<?>> r0 = this.unconfinedQueue;
        if (r0 != null) goto L5;
        return true;
    L5:
        return r0.isEmpty();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final CoroutineDispatcher limitedParallelism(int r1, String r2) {
        LimitedDispatcherKt.checkParallelism(r1);
        return LimitedDispatcherKt.namedOrThis(this, r2);
    }

    public abstract long processNextEvent();

    public final boolean processUnconfinedEvent() {
        ArrayDeque<DispatchedTask<?>> r1 = this.unconfinedQueue;
        if (r1 != null) goto L5;
        return false;
    L5:
        DispatchedTask<?> r12 = r1.removeFirstOrNull();
        if (r12 != null) goto L8;
        return false;
    L8:
        r12.run();
        return true;
    }

    public abstract void shutdown();
}
