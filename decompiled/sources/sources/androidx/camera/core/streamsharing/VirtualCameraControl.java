package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.ForwardingCameraControl;
import androidx.camera.core.streamsharing.StreamSharing;

/* loaded from: classes3.dex */
public class VirtualCameraControl extends ForwardingCameraControl {
    private final StreamSharing.Control mStreamSharingControl;

    public VirtualCameraControl(CameraControlInternal r1, StreamSharing.Control r2) {
        super(r1);
        this.mStreamSharingControl = r2;
    }
}
