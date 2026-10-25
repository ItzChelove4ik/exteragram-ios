package androidx.camera.camera2.pipe;

import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.graph.GraphListener;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H&¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0006\u0018\u00010\u0006H&¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u0003H&¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH&¢\u0006\u0004\b\u0010\u0010\u0011J?\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH&¢\u0006\u0004\b\u001f\u0010 J\u0018\u0010$\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u0016H\u0096@¢\u0006\u0004\b\"\u0010#R\u0014\u0010(\u001a\u00020%8&X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R \u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006-À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/CameraBackend;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "awaitCameraIds", "()Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "awaitConcurrentCameraIds", "()Ljava/util/Set;", "cameraId", "Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata-EfqyGwQ", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "shutdownAsync", "()Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/CameraContext;", "cameraContext", "Landroidx/camera/camera2/pipe/CameraGraphId;", "graphId", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "graphConfig", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", "Landroidx/camera/camera2/pipe/StreamGraph;", "streamGraph", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "surfaceTracker", "Landroidx/camera/camera2/pipe/CameraController;", "createCameraController", "(Landroidx/camera/camera2/pipe/CameraContext;Landroidx/camera/camera2/pipe/CameraGraphId;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/GraphListener;Landroidx/camera/camera2/pipe/StreamGraph;Landroidx/camera/camera2/pipe/SurfaceTracker;)Landroidx/camera/camera2/pipe/CameraController;", "Landroidx/camera/camera2/pipe/ConfigQueryResult;", "isConfigSupported-NpXggIU", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isConfigSupported", "Landroidx/camera/camera2/pipe/CameraBackendId;", "getId-QwmhuAM", "()Ljava/lang/String;", "id", "Lkotlinx/coroutines/flow/Flow;", "getCameraIds", "()Lkotlinx/coroutines/flow/Flow;", "cameraIds", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraBackend {
    List<CameraId> awaitCameraIds();

    /* renamed from: awaitCameraMetadata-EfqyGwQ, reason: not valid java name */
    CameraMetadata mo148awaitCameraMetadataEfqyGwQ(String r1);

    Set<Set<CameraId>> awaitConcurrentCameraIds();

    CameraController createCameraController(CameraContext r1, CameraGraphId r2, CameraGraph.Config r3, GraphListener r4, StreamGraph r5, SurfaceTracker r6);

    Flow<List<CameraId>> getCameraIds();

    /* renamed from: getId-QwmhuAM, reason: not valid java name */
    String mo149getIdQwmhuAM();

    /* renamed from: isConfigSupported-NpXggIU, reason: not valid java name */
    Object mo150isConfigSupportedNpXggIU(CameraGraph.Config r1, Continuation<? super ConfigQueryResult> r2);

    Deferred<Unit> shutdownAsync();
}
