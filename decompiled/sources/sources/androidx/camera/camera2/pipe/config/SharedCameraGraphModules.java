package androidx.camera.camera2.pipe.config;

import android.hardware.camera2.CameraCharacteristics;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.core.SystemClockOffsets;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.Listener3A;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.graph.SurfaceGraph;
import androidx.camera.camera2.pipe.internal.FrameCaptureQueue;
import androidx.camera.camera2.pipe.internal.FrameDistributor;
import java.util.List;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b!\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/config/SharedCameraGraphModules;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class SharedCameraGraphModules {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J+\u0010\n\u001a\r\u0012\t\u0012\u00070\f¢\u0006\u0002\b\r0\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J(\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\b\u0010$\u001a\u00020#H\u0007¨\u0006%"}, d2 = {"Landroidx/camera/camera2/pipe/config/SharedCameraGraphModules$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "provideCameraGraphCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "cameraPipeJob", "Lkotlinx/coroutines/Job;", "provideRequestListeners", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "Lkotlin/jvm/JvmSuppressWildcards;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "listener3A", "Landroidx/camera/camera2/pipe/graph/Listener3A;", "frameDistributor", "Landroidx/camera/camera2/pipe/internal/FrameDistributor;", "provideSurfaceGraph", "Landroidx/camera/camera2/pipe/graph/SurfaceGraph;", "streamGraphImpl", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "cameraController", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/pipe/CameraController;", "cameraSurfaceManager", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "provideFrameDistributor", "frameCaptureQueue", "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "systemClockOffsets", "Landroidx/camera/camera2/pipe/core/SystemClockOffsets;", "provideSystemClockOffsets", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CoroutineScope provideCameraGraphCoroutineScope(Threads r2, Job r3) {
            return CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob(r3).plus(r2.getLightweightDispatcher().plus(new CoroutineName("CXCP-Graph"))));
        }

        public final FrameDistributor provideFrameDistributor(StreamGraphImpl r7, FrameCaptureQueue r8, CameraMetadata r9, SystemClockOffsets r10) {
            Integer r6 = (Integer) r9.get(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE);
            if (r6 == null) goto L8;
            boolean r92 = true;
            if (r6.intValue() != 1) goto L8;
        L7:
            boolean r3 = r92;
            return new FrameDistributor(r7, r8, r3, r10.getRealtimeNsToMonotonicNs());
        L8:
            r92 = false;
            goto L7
        }

        public final List<Request.Listener> provideRequestListeners(CameraGraph.Config r2, Listener3A r3, FrameDistributor r4) {
            List<Request.Listener> r1 = CollectionsKt.mutableListOf(new Request.Listener[]{r3});
            r1.add(r3);
            r1.add(r4);
            r1.addAll(r2.getDefaultListeners());
            return r1;
        }

        public final SurfaceGraph provideSurfaceGraph(StreamGraphImpl r2, Provider<CameraController> r3, CameraSurfaceManager r4) {
            return new SurfaceGraph(r2, r3, r4, r2.getImageSourceMap$camera_camera2_pipe());
        }

        public final SystemClockOffsets provideSystemClockOffsets() {
            return SystemClockOffsets.Companion.estimate();
        }
    }

    static {
        Companion = new Companion(null);
    }
}
