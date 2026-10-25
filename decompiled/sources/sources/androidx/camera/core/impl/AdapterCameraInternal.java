package androidx.camera.core.impl;

import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.UseCase;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;

/* loaded from: classes3.dex */
public class AdapterCameraInternal implements CameraInternal {
    private final AdapterCameraControl mAdapterCameraControl;
    private final AdapterCameraInfo mAdapterCameraInfo;
    private final CameraInternal mCameraInternal;

    public AdapterCameraInternal(CameraInternal r3, AdapterCameraInfo r4) {
        this.mCameraInternal = r3;
        this.mAdapterCameraInfo = r4;
        CameraConfig r42 = r4.getCameraConfig();
        CameraControlInternal r32 = r3.getCameraControlInternal();
        r42.getSessionProcessor(null);
        this.mAdapterCameraControl = new AdapterCameraControl(r32, null);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void attachUseCases(Collection<UseCase> r1) {
        this.mCameraInternal.attachUseCases(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void detachUseCases(Collection<UseCase> r1) {
        this.mCameraInternal.detachUseCases(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal, androidx.camera.core.Camera
    public CameraControl getCameraControl() {
        return this.mAdapterCameraControl;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraControlInternal getCameraControlInternal() {
        return this.mAdapterCameraControl;
    }

    @Override // androidx.camera.core.impl.CameraInternal, androidx.camera.core.Camera
    public CameraInfo getCameraInfo() {
        return this.mAdapterCameraInfo;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraInfoInternal getCameraInfoInternal() {
        return this.mAdapterCameraInfo;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraConfig getExtendedConfig() {
        return this.mCameraInternal.getExtendedConfig();
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public boolean getHasTransform() {
        return this.mCameraInternal.getHasTransform();
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public boolean isFrontFacing() {
        return this.mCameraInternal.isFrontFacing();
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public boolean isRemoved() {
        return this.mCameraInternal.isRemoved();
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseActive(UseCase r1) {
        this.mCameraInternal.onUseCaseActive(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseInactive(UseCase r1) {
        this.mCameraInternal.onUseCaseInactive(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseReset(UseCase r1) {
        this.mCameraInternal.onUseCaseReset(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseUpdated(UseCase r1) {
        this.mCameraInternal.onUseCaseUpdated(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public ListenableFuture<Void> release() {
        return this.mCameraInternal.release();
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void setActiveResumingMode(boolean r1) {
        this.mCameraInternal.setActiveResumingMode(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void setExtendedConfig(CameraConfig r1) {
        this.mCameraInternal.setExtendedConfig(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void setPrimary(boolean r1) {
        this.mCameraInternal.setPrimary(r1);
    }
}
