package androidx.camera.core;

import android.util.Range;
import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes3.dex */
public interface CameraInfo {
    CameraIdentifier getCameraIdentifier();

    LiveData<CameraState> getCameraState();

    float getIntrinsicZoomRatio();

    int getLensFacing();

    Set<CameraInfo> getPhysicalCameraInfos();

    int getSensorRotationDegrees();

    int getSensorRotationDegrees(int r1);

    Set<Range<Integer>> getSupportedFrameRateRanges();

    default Set<Range<Integer>> getSupportedFrameRateRanges(SessionConfig r1) {
        return Collections.EMPTY_SET;
    }

    LiveData<ZoomState> getZoomState();

    boolean hasFlashUnit();

    default boolean isSessionConfigSupported(SessionConfig r1) {
        return false;
    }
}
