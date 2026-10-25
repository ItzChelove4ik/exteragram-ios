package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackendFactory;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.config.CameraPipeModule;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraPipeModule$Companion$$ExternalSyntheticLambda0 implements CameraBackendFactory {
    public final /* synthetic */ CameraBackend f$0;

    public /* synthetic */ CameraPipeModule$Companion$$ExternalSyntheticLambda0(CameraBackend r1) {
        this.f$0 = r1;
    }

    @Override // androidx.camera.camera2.pipe.CameraBackendFactory
    public final CameraBackend create(CameraContext r1) {
        return CameraPipeModule.Companion.m488$r8$lambda$u5fcAhcmf6Jw690I5PCj5GlYg(this.f$0, r1);
    }
}
