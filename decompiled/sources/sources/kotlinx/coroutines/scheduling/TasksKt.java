package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001b\u0010\u0004\u001a\u00020\u00032\n\u0010\u0002\u001a\u00060\u0000j\u0002`\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a+\u0010\u000b\u001a\u00020\n*\u00060\u0006j\u0002`\u00072\u0006\u0010\t\u001a\u00020\b2\n\u0010\u0002\u001a\u00060\u0000j\u0002`\u0001H\u0000¢\u0006\u0004\b\u000b\u0010\f\"\u0014\u0010\r\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e\"\u0014\u0010\u000f\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\"\u0014\u0010\u0012\u001a\u00020\u00118\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\u00118\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013\"\u0014\u0010\u0015\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0010\"\u0016\u0010\u0017\u001a\u00020\u00168\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018*\f\b\u0000\u0010\u0019\"\u00020\u00002\u00020\u0000¨\u0006\u001a"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/scheduling/TaskContext;", "taskContext", _UrlKt.FRAGMENT_ENCODE_SET, "taskContextString", "(Z)Ljava/lang/String;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", _UrlKt.FRAGMENT_ENCODE_SET, "submissionTime", "Lkotlinx/coroutines/scheduling/Task;", "asTask", "(Ljava/lang/Runnable;JZ)Lkotlinx/coroutines/scheduling/Task;", "DEFAULT_SCHEDULER_NAME", "Ljava/lang/String;", "WORK_STEALING_TIME_RESOLUTION_NS", "J", _UrlKt.FRAGMENT_ENCODE_SET, "CORE_POOL_SIZE", "I", "MAX_POOL_SIZE", "IDLE_WORKER_KEEP_ALIVE_NS", "Lkotlinx/coroutines/scheduling/SchedulerTimeSource;", "schedulerTimeSource", "Lkotlinx/coroutines/scheduling/SchedulerTimeSource;", "TaskContext", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class TasksKt {

    @JvmField
    public static final int CORE_POOL_SIZE = 0;

    @JvmField
    public static final String DEFAULT_SCHEDULER_NAME = null;

    @JvmField
    public static final long IDLE_WORKER_KEEP_ALIVE_NS = 0;

    @JvmField
    public static final int MAX_POOL_SIZE = 0;

    @JvmField
    public static final long WORK_STEALING_TIME_RESOLUTION_NS = 0;

    @JvmField
    public static SchedulerTimeSource schedulerTimeSource;

    static {
        DEFAULT_SCHEDULER_NAME = SystemPropsKt.systemProp("kotlinx.coroutines.scheduler.default.name", "DefaultDispatcher");
        WORK_STEALING_TIME_RESOLUTION_NS = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.resolution.ns", 100000, 0, 0, 12, null);
        CORE_POOL_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.core.pool.size", RangesKt.coerceAtLeast(SystemPropsKt.getAVAILABLE_PROCESSORS(), 2), 1, 0, 8, null);
        MAX_POOL_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4, null);
        long r0 = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.keep.alive.sec", 60, 0, 0, 12, null);
        IDLE_WORKER_KEEP_ALIVE_NS = TimeUnit.SECONDS.toNanos(r0);
        schedulerTimeSource = NanoTimeSource.INSTANCE;
    }

    public static final /* synthetic */ String access$taskContextString(boolean r0) {
        return taskContextString(r0);
    }

    public static final Task asTask(Runnable r1, long r2, boolean r4) {
        return new TaskImpl(r1, r2, r4);
    }

    private static final String taskContextString(boolean r0) {
        if (r0 == false) goto L5;
        return "Blocking";
    L5:
        return "Non-blocking";
    }
}
