package androidx.camera.core.impl;

import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.SessionProcessorUtil;
import androidx.camera.core.internal.ImmutableZoomState;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/* loaded from: classes3.dex */
public class AdapterCameraInfo extends ForwardingCameraInfo {
    private final CameraConfig mCameraConfig;
    private final CameraInfoInternal mCameraInfo;
    private LiveData<ZoomState> mExtensionZoomStateLiveData;
    private boolean mIsCaptureProcessProgressSupported;
    private boolean mIsPostviewSupported;

    public AdapterCameraInfo(CameraInfoInternal r2, CameraConfig r3) {
        super(r2);
        this.mIsPostviewSupported = false;
        this.mIsCaptureProcessProgressSupported = false;
        this.mExtensionZoomStateLiveData = null;
        this.mCameraInfo = r2;
        this.mCameraConfig = r3;
        r3.getSessionProcessor(null);
        setPostviewSupported(r3.isPostviewSupported());
        setCaptureProcessProgressSupported(r3.isCaptureProcessProgressSupported());
    }

    public CameraConfig getCameraConfig() {
        return this.mCameraConfig;
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.impl.CameraInfoInternal
    public CameraInfoInternal getImplementation() {
        return this.mCameraInfo;
    }

    public SessionProcessor getSessionProcessor() {
        return null;
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.CameraInfo
    public LiveData<ZoomState> getZoomState() {
        if (SessionProcessorUtil.isOperationSupported(null, new int[]{0}) == true) goto L7;
        return new MutableLiveData(ImmutableZoomState.create(1.0f, 1.0f, 1.0f, 0.0f));
    L7:
        return this.mCameraInfo.getZoomState();
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.CameraInfo
    public boolean hasFlashUnit() {
        if (SessionProcessorUtil.isOperationSupported(null, new int[]{5}) == true) goto L7;
        return false;
    L7:
        return this.mCameraInfo.hasFlashUnit();
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.impl.CameraInfoInternal
    public boolean isPreviewStabilizationSupported() {
        return super.isPreviewStabilizationSupported();
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.impl.CameraInfoInternal
    public boolean isVideoStabilizationSupported() {
        return super.isVideoStabilizationSupported();
    }

    public void setCaptureProcessProgressSupported(boolean r1) {
        this.mIsCaptureProcessProgressSupported = r1;
    }

    public void setPostviewSupported(boolean r1) {
        this.mIsPostviewSupported = r1;
    }
}
