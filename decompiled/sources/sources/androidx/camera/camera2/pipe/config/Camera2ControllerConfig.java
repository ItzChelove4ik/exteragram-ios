package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.SurfaceTracker;
import androidx.camera.camera2.pipe.compat.Camera2CameraController;
import androidx.camera.camera2.pipe.graph.GraphListener;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\fH\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010 R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010!R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\"R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010#R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010$R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010%¨\u0006&"}, d2 = {"Landroidx/camera/camera2/pipe/config/Camera2ControllerConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraBackend;", "cameraBackend", "Landroidx/camera/camera2/pipe/CameraGraphId;", "graphId", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "graphConfig", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", "Landroidx/camera/camera2/pipe/StreamGraph;", "streamGraph", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "surfaceTracker", "Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;", "shutdownListener", "<init>", "(Landroidx/camera/camera2/pipe/CameraBackend;Landroidx/camera/camera2/pipe/CameraGraphId;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/GraphListener;Landroidx/camera/camera2/pipe/StreamGraph;Landroidx/camera/camera2/pipe/SurfaceTracker;Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;)V", "provideCameraGraphConfig", "()Landroidx/camera/camera2/pipe/CameraGraph$Config;", "provideCameraGraphId", "()Landroidx/camera/camera2/pipe/CameraGraphId;", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "provideStreamGraph", "()Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "provideGraphListener", "()Landroidx/camera/camera2/pipe/graph/GraphListener;", "provideSurfaceGraph", "()Landroidx/camera/camera2/pipe/SurfaceTracker;", "provideShutdownListener", "()Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;", "Landroidx/camera/camera2/pipe/CameraBackend;", "Landroidx/camera/camera2/pipe/CameraGraphId;", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "Landroidx/camera/camera2/pipe/StreamGraph;", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2ControllerConfig {
    private final CameraBackend cameraBackend;
    private final CameraGraph.Config graphConfig;
    private final CameraGraphId graphId;
    private final GraphListener graphListener;
    private final Camera2CameraController.ShutdownListener shutdownListener;
    private final StreamGraph streamGraph;
    private final SurfaceTracker surfaceTracker;

    public Camera2ControllerConfig(CameraBackend r1, CameraGraphId r2, CameraGraph.Config r3, GraphListener r4, StreamGraph r5, SurfaceTracker r6, Camera2CameraController.ShutdownListener r7) {
        this.cameraBackend = r1;
        this.graphId = r2;
        this.graphConfig = r3;
        this.graphListener = r4;
        this.streamGraph = r5;
        this.surfaceTracker = r6;
        this.shutdownListener = r7;
    }

    public final CameraGraph.Config provideCameraGraphConfig() {
        return this.graphConfig;
    }

    public final CameraGraphId provideCameraGraphId() {
        return this.graphId;
    }

    public final GraphListener provideGraphListener() {
        return this.graphListener;
    }

    public final Camera2CameraController.ShutdownListener provideShutdownListener() {
        return this.shutdownListener;
    }

    public final StreamGraphImpl provideStreamGraph() {
        return (StreamGraphImpl) this.streamGraph;
    }

    public final SurfaceTracker provideSurfaceGraph() {
        return this.surfaceTracker;
    }
}
