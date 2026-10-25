package okhttp3.internal.concurrent;

import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"okhttp3/internal/concurrent/TaskRunner$runnable$1", "Ljava/lang/Runnable;", "run", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$runnable$1\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 3 TaskLogger.kt\nokhttp3/internal/concurrent/TaskLoggerKt\n*L\n1#1,364:1\n63#2:365\n63#2:385\n63#2:386\n36#3,19:366\n*S KotlinDebug\n*F\n+ 1 TaskRunner.kt\nokhttp3/internal/concurrent/TaskRunner$runnable$1\n*L\n69#1:365\n85#1:385\n92#1:386\n80#1:366,19\n*E\n"})
/* loaded from: classes.dex */
public final class TaskRunner$runnable$1 implements Runnable {
    final /* synthetic */ TaskRunner this$0;

    public TaskRunner$runnable$1(TaskRunner r1) {
        this.this$0 = r1;
    }

    @Override // java.lang.Runnable
    public void run() {
        TaskRunner r0 = this.this$0;
        monitor-enter(r0);
        TaskRunner.access$setRunCallCount$p(r0, TaskRunner.access$getRunCallCount$p(r0) + 1);     // Catch: Throwable -> L53
        Task r1 = r0.awaitTaskToRun();     // Catch: Throwable -> L53
        monitor-exit(r0);
        if (r1 != null) goto L8;
        return;
    L8:
        Thread r02 = Thread.currentThread();
        String r3 = r02.getName();
    L59:
        r02.setName(r1.getName());     // Catch: Throwable -> L14
        Logger r6 = this.this$0.getLogger$okhttp();     // Catch: Throwable -> L14
        TaskQueue r7 = r1.getQueue$okhttp();     // Catch: Throwable -> L14
        boolean r8 = r6.isLoggable(Level.FINE);     // Catch: Throwable -> L14
        if (r8 == false) goto L16;
        long r9 = r7.getTaskRunner$okhttp().getBackend().nanoTime();     // Catch: Throwable -> L14
        TaskLoggerKt.access$log(r6, r1, r7, "starting");     // Catch: Throwable -> L14
    L64:
        long r11 = r1.runOnce();     // Catch: Throwable -> L31
        if (r8 == false) goto L20;
        TaskLoggerKt.access$log(r6, r1, r7, "finished run in " + TaskLoggerKt.formatDuration(r7.getTaskRunner$okhttp().getBackend().nanoTime() - r9));     // Catch: Throwable -> L14
    L20:
        TaskRunner r62 = this.this$0;     // Catch: Throwable -> L14
        monitor-enter(r62);     // Catch: Throwable -> L14
        TaskRunner.access$afterRun(r62, r1, r11, true);     // Catch: Throwable -> L28
        Task r72 = r62.awaitTaskToRun();     // Catch: Throwable -> L28
        monitor-exit(r62);     // Catch: Throwable -> L14
        if (r72 == null) goto L25;
        r1 = r72;
        goto L59
    L25:
        r02.setName(r3);
        return;
    L28:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14
    L31:
        th = move-exception;
        if (r8 == false) goto L34;
        TaskLoggerKt.access$log(r6, r1, r7, "failed a run in " + TaskLoggerKt.formatDuration(r7.getTaskRunner$okhttp().getBackend().nanoTime() - r9));     // Catch: Throwable -> L14
    L34:
        throw th;     // Catch: Throwable -> L14
    L16:
        r9 = -1;
    L14:
        th = move-exception;
        TaskRunner r15 = this.this$0;     // Catch: Throwable -> L45
        monitor-enter(r15);     // Catch: Throwable -> L45
        TaskRunner.access$afterRun(r15, r1, -1, false);     // Catch: Throwable -> L48
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L48
    L41:
        if ((th instanceof InterruptedException) == false) goto L47;
        Thread.currentThread().interrupt();     // Catch: Throwable -> L45
        r02.setName(r3);
        return;
    L47:
        throw th;     // Catch: Throwable -> L45
    L48:
        th = move-exception;
        throw th;     // Catch: Throwable -> L45
    L45:
        th = move-exception;
        r02.setName(r3);
        throw th;
    L53:
        th = move-exception;
        throw th;
    }
}
