package androidx.camera.core.impl;

import android.content.Context;
import android.util.Size;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public interface CameraDeviceSurfaceManager extends InternalCameraPresenceListener {

    public interface Provider {
        CameraDeviceSurfaceManager newInstance(Context r1, Object r2, Set<String> r3);
    }

    SurfaceStreamSpecQueryResult getSuggestedStreamSpecs(int r1, String r2, List<AttachedSurfaceInfo> r3, Map<UseCaseConfig<?>, List<Size>> r4, VideoStabilization r5, boolean r6, boolean r7, boolean r8);

    SurfaceConfig transformSurfaceConfig(int r1, String r2, int r3, Size r4, StreamUseCase r5);
}
