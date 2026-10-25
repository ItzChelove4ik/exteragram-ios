package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.impl.DisplayInfoManager$$ExternalSyntheticBUOutline0;
import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackendFactory;
import androidx.camera.camera2.pipe.CameraBackendId;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.SurfaceTracker;
import androidx.camera.camera2.pipe.graph.GraphProcessorImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b!\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/config/InternalCameraGraphModules;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class InternalCameraGraphModules {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0005H\u0007J@\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007¨\u0006\u0019"}, d2 = {"Landroidx/camera/camera2/pipe/config/InternalCameraGraphModules$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "provideCameraBackend", "Landroidx/camera/camera2/pipe/CameraBackend;", "cameraBackends", "Landroidx/camera/camera2/pipe/CameraBackends;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "cameraContext", "Landroidx/camera/camera2/pipe/CameraContext;", "provideCameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraBackend", "provideCameraController", "Landroidx/camera/camera2/pipe/CameraController;", "graphId", "Landroidx/camera/camera2/pipe/CameraGraphId;", "graphProcessor", "Landroidx/camera/camera2/pipe/graph/GraphProcessorImpl;", "streamGraph", "Landroidx/camera/camera2/pipe/StreamGraph;", "surfaceTracker", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CameraBackend provideCameraBackend(CameraBackends r1, CameraGraph.Config r2, CameraContext r3) {
            CameraBackendFactory r0 = r2.getCustomCameraBackend();
            if (r0 != null) goto L5;
            String r02 = r2.m201getCameraBackendIdAKmI2lo();
            if (r02 == null) goto L14;
            CameraBackend r12 = r1.mo158getSG3A4s8(r02);
            if (r12 == null) goto L12;
            return r12;
        L12:
            throw new IllegalStateException(("Failed to initialize " + CameraBackendId.m156toStringimpl(r02) + " from " + r2).toString());
        L14:
            return r1.getDefault();
        L5:
            return r0.create(r3);
        }

        public final CameraController provideCameraController(CameraGraphId r1, CameraGraph.Config r2, CameraBackend r3, CameraContext r4, GraphProcessorImpl r5, StreamGraph r6, SurfaceTracker r7) {
            return r3.createCameraController(r4, r1, r2, r5, r6, r7);
        }

        public final CameraMetadata provideCameraMetadata(CameraGraph.Config r1, CameraBackend r2) {
            CameraMetadata r0 = r2.mo148awaitCameraMetadataEfqyGwQ(r1.m200getCameraDz_R5H8());
            if (r0 == null) goto L5;
            return r0;
        L5:
            DisplayInfoManager$$ExternalSyntheticBUOutline0.m("Failed to load metadata for ", CameraId.m232toStringimpl(r1.m200getCameraDz_R5H8()));
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }
}
