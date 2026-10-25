package androidx.camera.featurecombinationquery;

import android.content.Context;
import android.hardware.camera2.CameraManager;

/* loaded from: classes3.dex */
class Camera2CameraDeviceSetupCompatProvider implements CameraDeviceSetupCompatProvider {
    private final CameraManager mCameraManager;

    public Camera2CameraDeviceSetupCompatProvider(Context r2) {
        this.mCameraManager = (CameraManager) r2.getSystemService(CameraManager.class);
    }

    @Override // androidx.camera.featurecombinationquery.CameraDeviceSetupCompatProvider
    public CameraDeviceSetupCompat getCameraDeviceSetupCompat(String r2) {
        return new Camera2CameraDeviceSetupCompat(this.mCameraManager, r2);
    }
}
