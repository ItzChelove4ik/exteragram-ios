package androidx.camera.core.impl;

import android.os.Handler;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class CameraThreadConfig {
    public CameraThreadConfig() {
    }

    public static CameraThreadConfig create(Executor r1, Handler r2) {
        return new AutoValue_CameraThreadConfig(r1, r2);
    }

    public abstract Executor getCameraExecutor();

    public abstract Handler getSchedulerHandler();
}
