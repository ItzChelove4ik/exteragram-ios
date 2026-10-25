package androidx.camera.lifecycle;

import androidx.camera.core.CameraXConfig;
import kotlin.Metadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LifecycleCameraProviderImpl$configure$1$1$1 implements CameraXConfig.Provider {
    final /* synthetic */ CameraXConfig $cameraXConfig;

    public LifecycleCameraProviderImpl$configure$1$1$1(CameraXConfig r1) {
        this.$cameraXConfig = r1;
    }

    @Override // androidx.camera.core.CameraXConfig.Provider
    public final CameraXConfig getCameraXConfig() {
        return this.$cameraXConfig;
    }
}
