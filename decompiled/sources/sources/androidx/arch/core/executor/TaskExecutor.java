package androidx.arch.core.executor;

/* loaded from: classes.dex */
public abstract class TaskExecutor {
    public TaskExecutor() {
    }

    public abstract void executeOnDiskIO(Runnable r1);

    public abstract boolean isMainThread();

    public abstract void postToMainThread(Runnable r1);
}
