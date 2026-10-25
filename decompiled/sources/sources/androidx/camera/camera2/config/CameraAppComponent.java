package androidx.camera.camera2.config;

import androidx.camera.camera2.config.CameraComponent;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraPipe;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\bJ\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/config/CameraAppComponent;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraBuilder", "Landroidx/camera/camera2/config/CameraComponent$Builder;", "getCameraPipe", "Landroidx/camera/camera2/pipe/CameraPipe;", "getCameraDevices", "Landroidx/camera/camera2/pipe/CameraDevices;", "Builder", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraAppComponent {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/config/CameraAppComponent$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "config", "Landroidx/camera/camera2/config/CameraAppConfig;", "build", "Landroidx/camera/camera2/config/CameraAppComponent;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Builder {
        CameraAppComponent build();

        Builder config(CameraAppConfig r1);
    }

    CameraComponent.Builder cameraBuilder();

    CameraDevices getCameraDevices();

    CameraPipe getCameraPipe();
}
