package androidx.arch.core.executor;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class ArchTaskExecutor extends TaskExecutor {
    private static final Executor sIOThreadExecutor = null;
    private static volatile ArchTaskExecutor sInstance;
    private static final Executor sMainThreadExecutor = null;
    private final TaskExecutor mDefaultTaskExecutor;
    private TaskExecutor mDelegate;

    public static /* synthetic */ void $r8$lambda$iDc81zb_vTqqbYgAu5pDFlgKzao(Runnable r1) {
        getInstance().postToMainThread(r1);
    }

    /* renamed from: $r8$lambda$jMV7X2Tn_t5-EUbO6ennHkrVKsQ, reason: not valid java name */
    public static /* synthetic */ void m3$r8$lambda$jMV7X2Tn_t5EUbO6ennHkrVKsQ(Runnable r1) {
        getInstance().executeOnDiskIO(r1);
    }

    static {
        sMainThreadExecutor = new ArchTaskExecutor$$ExternalSyntheticLambda0();
        sIOThreadExecutor = new ArchTaskExecutor$$ExternalSyntheticLambda1();
    }

    private ArchTaskExecutor() {
        DefaultTaskExecutor r0 = new DefaultTaskExecutor();
        this.mDefaultTaskExecutor = r0;
        this.mDelegate = r0;
    }

    public static Executor getIOThreadExecutor() {
        return sIOThreadExecutor;
    }

    public static ArchTaskExecutor getInstance() {
        if (sInstance == null) goto L7;
        return sInstance;
    L7:
        monitor-enter(ArchTaskExecutor.class);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (sInstance != null) goto L13;
        sInstance = new ArchTaskExecutor();     // Catch: Throwable -> L11
    L13:
        monitor-exit(ArchTaskExecutor.class);     // Catch: Throwable -> L11
        return sInstance;
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(Runnable r1) {
        this.mDelegate.executeOnDiskIO(r1);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        return this.mDelegate.isMainThread();
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(Runnable r1) {
        this.mDelegate.postToMainThread(r1);
    }
}
