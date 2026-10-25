package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import androidx.work.impl.utils.SerialExecutorImpl;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

/* loaded from: classes.dex */
public class WorkManagerTaskExecutor implements TaskExecutor {
    private final SerialExecutorImpl mBackgroundExecutor;
    private final Executor mMainThreadExecutor;
    final Handler mMainThreadHandler;
    private final CoroutineDispatcher mTaskDispatcher;

    /* renamed from: androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor$1, reason: invalid class name */
    public class AnonymousClass1 implements Executor {
        final /* synthetic */ WorkManagerTaskExecutor this$0;

        public AnonymousClass1(WorkManagerTaskExecutor r1) {
            this.this$0 = r1;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r1) {
            this.this$0.mMainThreadHandler.post(r1);
        }
    }

    public WorkManagerTaskExecutor(Executor r3) {
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.mMainThreadExecutor = new AnonymousClass1(this);
        SerialExecutorImpl r0 = new SerialExecutorImpl(r3);
        this.mBackgroundExecutor = r0;
        this.mTaskDispatcher = ExecutorsKt.from(r0);
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public SerialExecutorImpl getSerialTaskExecutor() {
        return this.mBackgroundExecutor;
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public /* bridge */ /* synthetic */ SerialExecutor getSerialTaskExecutor() {
        return getSerialTaskExecutor();
    }

    @Override // androidx.work.impl.utils.taskexecutor.TaskExecutor
    public CoroutineDispatcher getTaskCoroutineDispatcher() {
        return this.mTaskDispatcher;
    }
}
