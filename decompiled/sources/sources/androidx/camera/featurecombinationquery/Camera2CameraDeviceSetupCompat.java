package androidx.camera.featurecombinationquery;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.SessionConfiguration;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;

/* loaded from: classes3.dex */
class Camera2CameraDeviceSetupCompat implements CameraDeviceSetupCompat {
    private final CameraDevice.CameraDeviceSetup mCameraDeviceSetup;

    public Camera2CameraDeviceSetupCompat(CameraManager r1, String r2) {
        this.mCameraDeviceSetup = r1.getCameraDeviceSetup(r2);
    }

    public static long getBuildTimeEpochMillis() {
        String r0 = System.getProperty("ro.build.date.utc");
        if (r0 != null) goto L10;
        return 0;
    L10:
        return Long.parseLong(r0) * 1000;
    L12:
        return 0;
    }

    @Override // androidx.camera.featurecombinationquery.CameraDeviceSetupCompat
    public CameraDeviceSetupCompat.SupportQueryResult isSessionConfigurationSupported(SessionConfiguration r4) {
        if (this.mCameraDeviceSetup.isSessionConfigurationSupported(r4) == false) goto L5;
        int r3 = 1;
    L7:
        return new CameraDeviceSetupCompat.SupportQueryResult(r3, 2, getBuildTimeEpochMillis());
    L5:
        r3 = 2;
        goto L7
    }
}
