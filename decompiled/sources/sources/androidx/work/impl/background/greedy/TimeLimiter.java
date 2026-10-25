package androidx.work.impl.background.greedy;

import androidx.work.RunnableScheduler;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.WorkLauncher;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/background/greedy/TimeLimiter;", _UrlKt.FRAGMENT_ENCODE_SET, "runnableScheduler", "Landroidx/work/RunnableScheduler;", "launcher", "Landroidx/work/impl/WorkLauncher;", "timeoutMs", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/work/RunnableScheduler;Landroidx/work/impl/WorkLauncher;J)V", "lock", "tracked", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/StartStopToken;", "Ljava/lang/Runnable;", "track", _UrlKt.FRAGMENT_ENCODE_SET, "token", "cancel", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTimeLimiter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeLimiter.kt\nandroidx/work/impl/background/greedy/TimeLimiter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1#2:46\n*E\n"})
/* loaded from: classes.dex */
public final class TimeLimiter {
    private final WorkLauncher launcher;
    private final Object lock;
    private final RunnableScheduler runnableScheduler;
    private final long timeoutMs;
    private final Map<StartStopToken, Runnable> tracked;

    public static void $r8$lambda$dwAYwAFpPKNbMC8c13ZgeRxadTI(TimeLimiter r1, StartStopToken r2) {
        r1.launcher.stopWork(r2, 3);
    }

    @JvmOverloads
    public TimeLimiter(RunnableScheduler r8, WorkLauncher r9) {
        this(r8, r9, 0, 4, null);
    }

    @JvmOverloads
    public TimeLimiter(RunnableScheduler r1, WorkLauncher r2, long r3) {
        this.runnableScheduler = r1;
        this.launcher = r2;
        this.timeoutMs = r3;
        this.lock = new Object();
        this.tracked = new LinkedHashMap();
    }

    public /* synthetic */ TimeLimiter(RunnableScheduler r1, WorkLauncher r2, long r3, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 4) == 0) goto L5;
        r3 = 5400000;
    L5:
        this(r1, r2, r3);
    }

    public final void cancel(StartStopToken r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Runnable r32 = this.tracked.remove(r3);     // Catch: Throwable -> L9
        monitor-exit(r0);
        if (r32 == null) goto L14;
        this.runnableScheduler.cancel(r32);
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final void track(StartStopToken r4) {
        TimeLimiter$$ExternalSyntheticLambda0 r0 = new TimeLimiter$$ExternalSyntheticLambda0(this, r4);
        Object r1 = this.lock;
        monitor-enter(r1);
        Runnable r42 = this.tracked.put(r4, r0);     // Catch: Throwable -> L8
        monitor-exit(r1);
        this.runnableScheduler.scheduleWithDelay(this.timeoutMs, r0);
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
