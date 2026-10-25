package androidx.camera.camera2.config;

import androidx.camera.camera2.impl.UseCaseCamera;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001:\u0001\u0005J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/config/UseCaseCameraComponent;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/impl/UseCaseCamera;", "getUseCaseCamera", "()Landroidx/camera/camera2/impl/UseCaseCamera;", "Builder", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface UseCaseCameraComponent {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/config/UseCaseCameraComponent$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "config", "Landroidx/camera/camera2/config/UseCaseCameraConfig;", "build", "Landroidx/camera/camera2/config/UseCaseCameraComponent;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Builder {
        UseCaseCameraComponent build();

        Builder config(UseCaseCameraConfig r1);
    }

    UseCaseCamera getUseCaseCamera();
}
