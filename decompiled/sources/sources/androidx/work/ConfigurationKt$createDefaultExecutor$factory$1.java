package androidx.work;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"androidx/work/ConfigurationKt$createDefaultExecutor$factory$1", "Ljava/util/concurrent/ThreadFactory;", "threadCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "newThread", "Ljava/lang/Thread;", "runnable", "Ljava/lang/Runnable;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ConfigurationKt$createDefaultExecutor$factory$1 implements ThreadFactory {
    final /* synthetic */ boolean $isTaskExecutor;
    private final AtomicInteger threadCount;

    public ConfigurationKt$createDefaultExecutor$factory$1(boolean r2) {
        this.$isTaskExecutor = r2;
        this.threadCount = new AtomicInteger(0);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable r4) {
        if (this.$isTaskExecutor == false) goto L5;
        String r0 = "WM.task-";
    L7:
        return new Thread(r4, r0 + this.threadCount.incrementAndGet());
    L5:
        r0 = "androidx.work-";
        goto L7
    }
}
