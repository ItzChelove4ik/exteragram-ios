package androidx.camera.featurecombinationquery;

import android.hardware.camera2.params.SessionConfiguration;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
final class AggregatedCameraDeviceSetupCompat implements CameraDeviceSetupCompat {
    private final List<CameraDeviceSetupCompat> mCameraDeviceSetupImpls;

    public AggregatedCameraDeviceSetupCompat(List<CameraDeviceSetupCompat> r1) {
        this.mCameraDeviceSetupImpls = r1;
    }

    @Override // androidx.camera.featurecombinationquery.CameraDeviceSetupCompat
    public CameraDeviceSetupCompat.SupportQueryResult isSessionConfigurationSupported(SessionConfiguration r3) {
        Iterator<CameraDeviceSetupCompat> r2 = this.mCameraDeviceSetupImpls.iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        CameraDeviceSetupCompat.SupportQueryResult r0 = r2.next().isSessionConfigurationSupported(r3);
        if (r0.getSupported() == 0) goto L4;
        return r0;
    L9:
        return new CameraDeviceSetupCompat.SupportQueryResult(0, 0, 0);
    }
}
