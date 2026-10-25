package androidx.camera.core.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.internal.StreamSpecsCalculator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public interface CameraFactory extends CameraPresenceMonitor {

    public interface Interrogator {
        List<String> getAvailableCameraIds(List<String> r1);
    }

    public interface Provider {
        @SuppressLint({"LambdaLast"})
        CameraFactory newInstance(Context r1, CameraThreadConfig r2, CameraSelector r3, long r4, CameraXConfig r6, StreamSpecsCalculator r7);
    }

    Set<String> getAvailableCameraIds();

    CameraInternal getCamera(String r1);

    CameraCoordinator getCameraCoordinator();

    Object getCameraManager();

    Observable<List<CameraIdentifier>> getCameraPresenceSource();

    void shutdown();
}
