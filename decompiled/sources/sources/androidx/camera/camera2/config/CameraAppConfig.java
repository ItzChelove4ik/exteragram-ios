package androidx.camera.camera2.config;

import android.content.Context;
import androidx.camera.camera2.impl.CameraInteropStateCallbackRepository;
import androidx.camera.camera2.impl.DisplayInfoManager;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.CameraThreadConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0003H\u0007J\b\u0010\u0011\u001a\u00020\u0005H\u0007J\b\u0010\u0012\u001a\u00020\u0007H\u0007J\b\u0010\u0013\u001a\u00020\tH\u0007J\b\u0010\u0014\u001a\u00020\u000bH\u0007J\b\u0010\u0015\u001a\u00020\rH\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/config/CameraAppConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "cameraThreadConfig", "Landroidx/camera/core/impl/CameraThreadConfig;", "cameraPipe", "Landroidx/camera/camera2/pipe/CameraPipe;", "camera2InteropCallbacks", "Landroidx/camera/camera2/impl/CameraInteropStateCallbackRepository;", "cameraCoordinator", "Landroidx/camera/core/concurrent/CameraCoordinator;", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "<init>", "(Landroid/content/Context;Landroidx/camera/core/impl/CameraThreadConfig;Landroidx/camera/camera2/pipe/CameraPipe;Landroidx/camera/camera2/impl/CameraInteropStateCallbackRepository;Landroidx/camera/core/concurrent/CameraCoordinator;Landroidx/camera/core/CameraXConfig;)V", "provideContext", "provideCameraThreadConfig", "provideCameraPipe", "provideCamera2InteropCallbacks", "provideCameraCoordinator", "provideCameraXConfig", "provideDisplayInfoManager", "Landroidx/camera/camera2/impl/DisplayInfoManager;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraAppConfig {
    private final CameraInteropStateCallbackRepository camera2InteropCallbacks;
    private final CameraCoordinator cameraCoordinator;
    private final CameraPipe cameraPipe;
    private final CameraThreadConfig cameraThreadConfig;
    private final CameraXConfig cameraXConfig;
    private final Context context;

    public CameraAppConfig(Context r1, CameraThreadConfig r2, CameraPipe r3, CameraInteropStateCallbackRepository r4, CameraCoordinator r5, CameraXConfig r6) {
        this.context = r1;
        this.cameraThreadConfig = r2;
        this.cameraPipe = r3;
        this.camera2InteropCallbacks = r4;
        this.cameraCoordinator = r5;
        this.cameraXConfig = r6;
    }

    public final CameraInteropStateCallbackRepository provideCamera2InteropCallbacks() {
        return this.camera2InteropCallbacks;
    }

    public final CameraCoordinator provideCameraCoordinator() {
        return this.cameraCoordinator;
    }

    public final CameraPipe provideCameraPipe() {
        return this.cameraPipe;
    }

    public final CameraThreadConfig provideCameraThreadConfig() {
        return this.cameraThreadConfig;
    }

    public final CameraXConfig provideCameraXConfig() {
        return this.cameraXConfig;
    }

    public final Context provideContext() {
        return this.context;
    }

    public final DisplayInfoManager provideDisplayInfoManager(Context r1) {
        return DisplayInfoManager.Companion.getInstance(r1);
    }
}
