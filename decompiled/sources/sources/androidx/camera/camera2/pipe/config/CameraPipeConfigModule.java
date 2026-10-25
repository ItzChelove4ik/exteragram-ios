package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraPipe;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/pipe/config/CameraPipeConfigModule;", _UrlKt.FRAGMENT_ENCODE_SET, "config", "Landroidx/camera/camera2/pipe/CameraPipe$Config;", "<init>", "(Landroidx/camera/camera2/pipe/CameraPipe$Config;)V", "provideCameraPipeConfig", "provideCameraPipeFlags", "Landroidx/camera/camera2/pipe/CameraPipe$Flags;", "provideCameraInteropConfig", "Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;", "cameraPipeConfig", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraPipeConfigModule {
    private final CameraPipe.Config config;

    public CameraPipeConfigModule(CameraPipe.Config r1) {
        this.config = r1;
    }

    public final CameraPipe.CameraInteropConfig provideCameraInteropConfig(CameraPipe.Config r1) {
        return r1.getCameraInteropConfig();
    }

    public final CameraPipe.Config provideCameraPipeConfig() {
        return this.config;
    }

    public final CameraPipe.Flags provideCameraPipeFlags() {
        return this.config.getFlags();
    }
}
