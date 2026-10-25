package androidx.camera.core.impl.capability;

import androidx.camera.core.CameraInfo;
import androidx.camera.core.PreviewCapabilities;
import androidx.camera.core.impl.CameraInfoInternal;

/* loaded from: classes3.dex */
public class PreviewCapabilitiesImpl implements PreviewCapabilities {
    private boolean mIsStabilizationSupported;

    public PreviewCapabilitiesImpl(CameraInfoInternal r1) {
        this.mIsStabilizationSupported = r1.isPreviewStabilizationSupported();
    }

    public static PreviewCapabilities from(CameraInfo r1) {
        return new PreviewCapabilitiesImpl((CameraInfoInternal) r1);
    }

    @Override // androidx.camera.core.PreviewCapabilities
    public boolean isStabilizationSupported() {
        return this.mIsStabilizationSupported;
    }
}
