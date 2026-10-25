package androidx.camera.camera2.pipe.compat;

import android.view.Surface;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CaptureSequence;
import androidx.camera.camera2.pipe.CaptureSequenceProcessor;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJH\u0010\u000e\u001a\u0012\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00152\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/pipe/compat/StandardCamera2CaptureSequenceProcessorFactory;", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessorFactory;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "quirks", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "strictMode", "Landroidx/camera/camera2/pipe/StrictMode;", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/compat/Camera2Quirks;Landroidx/camera/camera2/pipe/StrictMode;)V", "create", "Landroidx/camera/camera2/pipe/CaptureSequenceProcessor;", "Landroidx/camera/camera2/pipe/CaptureSequence;", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "streamToSurfaceMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "outputToSurfaceMap", "Landroidx/camera/camera2/pipe/OutputId;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StandardCamera2CaptureSequenceProcessorFactory implements Camera2CaptureSequenceProcessorFactory {
    private final CameraGraph.Config graphConfig;
    private final Camera2Quirks quirks;
    private final StreamGraphImpl streamGraph;
    private final StrictMode strictMode;
    private final Threads threads;

    public StandardCamera2CaptureSequenceProcessorFactory(Threads r1, CameraGraph.Config r2, StreamGraphImpl r3, Camera2Quirks r4, StrictMode r5) {
        this.threads = r1;
        this.graphConfig = r2;
        this.streamGraph = r3;
        this.quirks = r4;
        this.strictMode = r5;
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CaptureSequenceProcessorFactory
    public CaptureSequenceProcessor<?, CaptureSequence<Object>> create(CameraCaptureSessionWrapper r11, Map<StreamId, ? extends Surface> r12, Map<OutputId, ? extends Surface> r13) {
        return new Camera2CaptureSequenceProcessor(r11, this.threads, this.graphConfig.m202getDefaultTemplatefGx8uWA(), r12, r13, this.streamGraph, this.strictMode, this.quirks.shouldWaitForRepeatingRequestStartOnDisconnect$camera_camera2_pipe(this.graphConfig), null);
    }
}
