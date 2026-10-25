package androidx.camera.core.impl.utils.executor;

import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes3.dex */
public abstract class CameraXExecutors {
    public static Executor directExecutor() {
        return DirectExecutor.getInstance();
    }

    public static Executor ioExecutor() {
        return IoExecutor.getInstance();
    }

    public static ScheduledExecutorService mainThreadExecutor() {
        return MainThreadExecutor.getInstance();
    }

    public static ScheduledExecutorService newHandlerExecutor(Handler r1) {
        return new HandlerScheduledExecutorService(r1);
    }

    public static Executor newSequentialExecutor(Executor r1) {
        return new SequentialExecutor(r1);
    }
}
