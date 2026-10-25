package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.config.CameraGraphComponent;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\ba\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH&¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H&¢\u0006\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/config/CameraPipeComponent;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", "cameraPipeLifetime", "()Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", "Landroidx/camera/camera2/pipe/config/CameraGraphComponent$Builder;", "cameraGraphComponentBuilder", "()Landroidx/camera/camera2/pipe/config/CameraGraphComponent$Builder;", "Landroidx/camera/camera2/pipe/CameraDevices;", "cameras", "()Landroidx/camera/camera2/pipe/CameraDevices;", "Landroidx/camera/camera2/pipe/CameraBackends;", "cameraBackends", "()Landroidx/camera/camera2/pipe/CameraBackends;", "Landroidx/camera/camera2/pipe/CameraContext;", "cameraContext", "()Landroidx/camera/camera2/pipe/CameraContext;", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "cameraSurfaceManager", "()Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraPipeComponent {
    CameraBackends cameraBackends();

    CameraContext cameraContext();

    CameraGraphComponent.Builder cameraGraphComponentBuilder();

    CameraPipeLifetime cameraPipeLifetime();

    CameraSurfaceManager cameraSurfaceManager();

    CameraDevices cameras();
}
