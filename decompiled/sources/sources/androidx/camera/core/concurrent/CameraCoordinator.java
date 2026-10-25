package androidx.camera.core.concurrent;

import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.impl.CameraRepository;
import androidx.camera.core.impl.InternalCameraPresenceListener;
import java.util.List;

/* loaded from: classes3.dex */
public interface CameraCoordinator extends InternalCameraPresenceListener {
    void addPendingCameraInfo(CameraInfo r1);

    List<CameraInfo> getActiveConcurrentCameraInfos();

    int getCameraOperatingMode();

    List<List<CameraSelector>> getConcurrentCameraSelectors();

    void init(CameraRepository r1);

    void removePendingCameraInfo(CameraInfo r1);

    void setActiveConcurrentCameraInfos(List<CameraInfo> r1);

    void setCameraOperatingMode(int r1);
}
