package androidx.work.impl.utils.taskexecutor;

import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;

/* loaded from: classes.dex */
public interface TaskExecutor {
    default void executeOnTaskThread(Runnable r1) {
        getSerialTaskExecutor().execute(r1);
    }

    Executor getMainThreadExecutor();

    SerialExecutor getSerialTaskExecutor();

    CoroutineDispatcher getTaskCoroutineDispatcher();
}
