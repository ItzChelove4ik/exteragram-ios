package androidx.camera.camera2.impl;

import androidx.camera.camera2.config.CameraConfig;
import androidx.camera.camera2.pipe.CameraMetadata;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/impl/CameraPipeCameraProperties;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraConfig", "Landroidx/camera/camera2/config/CameraConfig;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "<init>", "(Landroidx/camera/camera2/config/CameraConfig;Landroidx/camera/camera2/pipe/CameraMetadata;)V", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "metadata", "getMetadata", "()Landroidx/camera/camera2/pipe/CameraMetadata;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraPipeCameraProperties implements CameraProperties {
    private final CameraConfig cameraConfig;
    private final CameraMetadata cameraMetadata;
    private final CameraMetadata metadata;

    public CameraPipeCameraProperties(CameraConfig r1, CameraMetadata r2) {
        this.cameraConfig = r1;
        this.cameraMetadata = r2;
        this.metadata = r2;
    }

    @Override // androidx.camera.camera2.impl.CameraProperties
    /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
    public String mo64getCameraIdDz_R5H8() {
        return this.cameraConfig.m37getCameraIdDz_R5H8();
    }

    @Override // androidx.camera.camera2.impl.CameraProperties
    public CameraMetadata getMetadata() {
        return this.metadata;
    }
}
