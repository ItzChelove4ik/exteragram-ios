package androidx.camera.core.streamsharing;

import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.streamsharing.StreamSharing;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;

/* loaded from: classes3.dex */
class VirtualCamera implements CameraInternal {
    private final CameraInternal mParentCamera;
    private final UseCase.StateChangeCallback mStateChangeCallback;
    private final VirtualCameraControl mVirtualCameraControl;
    private final VirtualCameraInfo mVirtualCameraInfo;

    public VirtualCamera(CameraInternal r2, UseCase.StateChangeCallback r3, StreamSharing.Control r4) {
        this.mParentCamera = r2;
        this.mStateChangeCallback = r3;
        this.mVirtualCameraControl = new VirtualCameraControl(r2.getCameraControlInternal(), r4);
        this.mVirtualCameraInfo = new VirtualCameraInfo(r2.getCameraInfoInternal());
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void attachUseCases(Collection<UseCase> r1) {
        throw new UnsupportedOperationException("Operation not supported by VirtualCamera.");
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void detachUseCases(Collection<UseCase> r1) {
        throw new UnsupportedOperationException("Operation not supported by VirtualCamera.");
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraControlInternal getCameraControlInternal() {
        return this.mVirtualCameraControl;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraInfoInternal getCameraInfoInternal() {
        return this.mVirtualCameraInfo;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public boolean getHasTransform() {
        return false;
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseActive(UseCase r1) {
        Threads.checkMainThread();
        this.mStateChangeCallback.onUseCaseActive(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseInactive(UseCase r1) {
        Threads.checkMainThread();
        this.mStateChangeCallback.onUseCaseInactive(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseReset(UseCase r1) {
        Threads.checkMainThread();
        this.mStateChangeCallback.onUseCaseReset(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseUpdated(UseCase r1) {
        Threads.checkMainThread();
        this.mStateChangeCallback.onUseCaseUpdated(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public ListenableFuture<Void> release() {
        throw new UnsupportedOperationException("Operation not supported by VirtualCamera.");
    }

    public void setRotationDegrees(int r1) {
        this.mVirtualCameraInfo.setVirtualCameraRotationDegrees(r1);
    }
}
