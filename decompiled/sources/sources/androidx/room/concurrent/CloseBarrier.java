package androidx.room.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\u0013\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0016J\r\u0010\u0017\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0018R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0014\u0010\f\u001a\u00060\rj\u0002`\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012¨\u0006\u0019"}, d2 = {"Landroidx/room/concurrent/CloseBarrier;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/concurrent/SynchronizedObject;", "closeAction", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/jvm/functions/Function0;)V", "blockers", "Ljava/util/concurrent/atomic/AtomicInteger;", "Landroidx/room/concurrent/AtomicInt;", "Ljava/util/concurrent/atomic/AtomicInteger;", "closeInitiated", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Landroidx/room/concurrent/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isClosed", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "block", "block$room_runtime", "unblock", "unblock$room_runtime", "close", "close$room_runtime", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCloseBarrier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CloseBarrier.kt\nandroidx/room/concurrent/CloseBarrier\n+ 2 Synchronized.jvmAndroid.kt\nandroidx/room/concurrent/Synchronized_jvmAndroidKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Atomics.kt\nandroidx/room/concurrent/AtomicsKt\n*L\n1#1,106:1\n22#2:107\n22#2:108\n22#2:110\n1#3:109\n40#4,2:111\n*S KotlinDebug\n*F\n+ 1 CloseBarrier.kt\nandroidx/room/concurrent/CloseBarrier\n*L\n53#1:107\n69#1:108\n83#1:110\n89#1:111,2\n*E\n"})
/* loaded from: classes.dex */
public final class CloseBarrier {
    private final AtomicInteger blockers;
    private final Function0<Unit> closeAction;
    private final AtomicBoolean closeInitiated;

    public CloseBarrier(Function0<Unit> r2) {
        this.closeAction = r2;
        this.blockers = new AtomicInteger(0);
        this.closeInitiated = new AtomicBoolean(false);
    }

    private final boolean isClosed() {
        return this.closeInitiated.get();
    }

    public final boolean block$room_runtime() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (isClosed() == false) goto L8;
        monitor-exit(this);
        return false;
    L8:
        this.blockers.incrementAndGet();     // Catch: Throwable -> L12
        monitor-exit(this);
        return true;
    }

    public final void close$room_runtime() {
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L4:
        if (this.closeInitiated.compareAndSet(false, true) == true) goto L7;
        monitor-exit(this);
        return;
    L7:
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);
        AtomicInteger r02 = this.blockers;
    L11:
        if (r02.get() != 0) goto L11;
        this.closeAction.invoke();
    }

    public final void unblock$room_runtime() {
        monitor-enter(this);
        this.blockers.decrementAndGet();     // Catch: Throwable -> L8
        if (this.blockers.get() < 0) goto L11;
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L11:
        throw new IllegalStateException("Unbalanced call to unblock() detected.");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }
}
