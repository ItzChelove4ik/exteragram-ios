package androidx.camera.core.impl;

import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.impl.utils.SessionProcessorUtil;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes3.dex */
public class AdapterCameraControl extends ForwardingCameraControl {
    private final CameraControlInternal mCameraControl;

    public AdapterCameraControl(CameraControlInternal r1, SessionProcessor r2) {
        super(r1);
        this.mCameraControl = r1;
    }

    @Override // androidx.camera.core.impl.ForwardingCameraControl, androidx.camera.core.CameraControl
    public ListenableFuture<Void> enableTorch(boolean r3) {
        if (SessionProcessorUtil.isOperationSupported(null, new int[]{6}) == true) goto L7;
        return Futures.immediateFailedFuture(new IllegalStateException("Torch is not supported"));
    L7:
        return this.mCameraControl.enableTorch(r3);
    }

    @Override // androidx.camera.core.impl.ForwardingCameraControl, androidx.camera.core.CameraControl
    public ListenableFuture<Void> setZoomRatio(float r3) {
        if (SessionProcessorUtil.isOperationSupported(null, new int[]{0}) == true) goto L7;
        return Futures.immediateFailedFuture(new IllegalStateException("Zoom is not supported"));
    L7:
        return this.mCameraControl.setZoomRatio(r3);
    }

    @Override // androidx.camera.core.impl.ForwardingCameraControl, androidx.camera.core.CameraControl
    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction r2) {
        FocusMeteringAction r22 = SessionProcessorUtil.getModifiedFocusMeteringAction(null, r2);
        if (r22 != null) goto L7;
        return Futures.immediateFailedFuture(new IllegalStateException("FocusMetering is not supported"));
    L7:
        return this.mCameraControl.startFocusAndMetering(r22);
    }
}
