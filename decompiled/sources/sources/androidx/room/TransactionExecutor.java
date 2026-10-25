package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0016J\u0006\u0010\u000e\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/room/TransactionExecutor;", "Ljava/util/concurrent/Executor;", "executor", "<init>", "(Ljava/util/concurrent/Executor;)V", "tasks", "Ljava/util/ArrayDeque;", "Ljava/lang/Runnable;", "active", "syncLock", _UrlKt.FRAGMENT_ENCODE_SET, "execute", _UrlKt.FRAGMENT_ENCODE_SET, "command", "scheduleNext", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTransactionExecutor.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TransactionExecutor.android.kt\nandroidx/room/TransactionExecutor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,58:1\n1#2:59\n*E\n"})
/* loaded from: classes.dex */
public final class TransactionExecutor implements Executor {
    private Runnable active;
    private final Executor executor;
    private final Object syncLock;
    private final ArrayDeque<Runnable> tasks;

    /* renamed from: $r8$lambda$FZWr2PGmP3sgXLCiri-DCcePXSs, reason: not valid java name */
    public static void m946$r8$lambda$FZWr2PGmP3sgXLCiriDCcePXSs(Runnable r0, TransactionExecutor r1) {
        r0.run();     // Catch: Throwable -> L5
        r1.scheduleNext();
        return;
    L5:
        th = move-exception;
        r1.scheduleNext();
        throw th;
    }

    public TransactionExecutor(Executor r1) {
        this.executor = r1;
        this.tasks = new ArrayDeque();
        this.syncLock = new Object();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r4) {
        Object r0 = this.syncLock;
        monitor-enter(r0);
        this.tasks.offer(new TransactionExecutor$$ExternalSyntheticLambda0(r4, this));     // Catch: Throwable -> L7
        if (this.active != null) goto L9;
        scheduleNext();     // Catch: Throwable -> L7
    L9:
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void scheduleNext() {
        Object r0 = this.syncLock;
        monitor-enter(r0);
        Runnable r1 = this.tasks.poll();     // Catch: Throwable -> L7
        Runnable r2 = r1;     // Catch: Throwable -> L7
        this.active = r2;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        this.executor.execute(r2);     // Catch: Throwable -> L7
    L9:
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
