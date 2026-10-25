package androidx.camera.core.impl.utils.executor;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes3.dex */
abstract class MainThreadExecutor {
    private static volatile ScheduledExecutorService sInstance;

    public static ScheduledExecutorService getInstance() {
        if (sInstance == null) goto L7;
        return sInstance;
    L7:
        monitor-enter(MainThreadExecutor.class);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (sInstance != null) goto L13;
        sInstance = new HandlerScheduledExecutorService(new Handler(Looper.getMainLooper()));     // Catch: Throwable -> L11
    L13:
        monitor-exit(MainThreadExecutor.class);     // Catch: Throwable -> L11
        return sInstance;
    }
}
