package androidx.work.impl;

import androidx.work.WorkerParameters;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/work/impl/WorkLauncherImpl;", "Landroidx/work/impl/WorkLauncher;", "Landroidx/work/impl/Processor;", "processor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "workTaskExecutor", "<init>", "(Landroidx/work/impl/Processor;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "Landroidx/work/impl/StartStopToken;", "workSpecId", "Landroidx/work/WorkerParameters$RuntimeExtras;", "runtimeExtras", _UrlKt.FRAGMENT_ENCODE_SET, "startWork", "(Landroidx/work/impl/StartStopToken;Landroidx/work/WorkerParameters$RuntimeExtras;)V", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "stopWork", "(Landroidx/work/impl/StartStopToken;I)V", "Landroidx/work/impl/Processor;", "getProcessor", "()Landroidx/work/impl/Processor;", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "getWorkTaskExecutor", "()Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WorkLauncherImpl implements WorkLauncher {
    private final Processor processor;
    private final TaskExecutor workTaskExecutor;

    public static void $r8$lambda$IJHwq8ui46LmYGGj0wRpXW6AsK0(WorkLauncherImpl r0, StartStopToken r1, WorkerParameters.RuntimeExtras r2) {
        r0.processor.startWork(r1, r2);
    }

    public WorkLauncherImpl(Processor r1, TaskExecutor r2) {
        this.processor = r1;
        this.workTaskExecutor = r2;
    }

    @Override // androidx.work.impl.WorkLauncher
    public void startWork(StartStopToken r3, WorkerParameters.RuntimeExtras r4) {
        this.workTaskExecutor.executeOnTaskThread(new WorkLauncherImpl$$ExternalSyntheticLambda0(this, r3, r4));
    }

    @Override // androidx.work.impl.WorkLauncher
    public void stopWork(StartStopToken r4, int r5) {
        this.workTaskExecutor.executeOnTaskThread(new StopWorkRunnable(this.processor, r4, false, r5));
    }
}
