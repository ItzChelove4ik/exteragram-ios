package androidx.camera.core.impl.utils.executor;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
final class IoExecutor implements Executor {
    private static volatile Executor sExecutor;
    private final ExecutorService mIoService;

    /* renamed from: androidx.camera.core.impl.utils.executor.IoExecutor$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        private final AtomicInteger mThreadId;
        final /* synthetic */ IoExecutor this$0;

        public AnonymousClass1(IoExecutor r2) {
            this.this$0 = r2;
            this.mThreadId = new AtomicInteger(0);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r3) {
            Thread r0 = new Thread(r3);
            r0.setName(String.format(Locale.US, "CameraX-camerax_io_%d", new Object[]{Integer.valueOf(this.mThreadId.getAndIncrement())}));
            return r0;
        }
    }

    public IoExecutor() {
        this.mIoService = Executors.newFixedThreadPool(2, new AnonymousClass1(this));
    }

    public static Executor getInstance() {
        if (sExecutor == null) goto L7;
        return sExecutor;
    L7:
        monitor-enter(IoExecutor.class);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (sExecutor != null) goto L13;
        sExecutor = new IoExecutor();     // Catch: Throwable -> L11
    L13:
        monitor-exit(IoExecutor.class);     // Catch: Throwable -> L11
        return sExecutor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r1) {
        this.mIoService.execute(r1);
    }
}
