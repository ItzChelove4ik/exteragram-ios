package androidx.camera.camera2.pipe.compat;

import android.os.Build;
import android.view.Surface;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.ConcurrentCameraGraphs;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.SurfaceTracker;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.TimeSource;
import androidx.camera.camera2.pipe.core.TimestampNs;
import androidx.camera.camera2.pipe.graph.GraphListener;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.internal.CameraStatusMonitor;
import com.android.dx.io.Opcodes;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u0000 \u008a\u00012\u00020\u0001:\u0004\u008b\u0001\u008a\u0001B\u0091\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%J\u000f\u0010'\u001a\u00020&H\u0003¢\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020&H\u0003¢\u0006\u0004\b)\u0010(J\u000f\u0010*\u001a\u00020&H\u0003¢\u0006\u0004\b*\u0010(J\u0017\u0010-\u001a\u00020&2\u0006\u0010,\u001a\u00020+H\u0002¢\u0006\u0004\b-\u0010.J\u0010\u0010/\u001a\u00020&H\u0082@¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020&2\u0006\u00102\u001a\u000201H\u0002¢\u0006\u0004\b3\u00104J#\u00109\u001a\u00020&2\b\u00106\u001a\u0004\u0018\u0001052\b\u00108\u001a\u0004\u0018\u000107H\u0003¢\u0006\u0004\b9\u0010:J\u000f\u0010<\u001a\u00020;H\u0003¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020&H\u0016¢\u0006\u0004\b>\u0010(J\u000f\u0010?\u001a\u00020&H\u0016¢\u0006\u0004\b?\u0010(J\u0010\u0010@\u001a\u00020;H\u0096@¢\u0006\u0004\b@\u00100J#\u0010E\u001a\u00020&2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020C0AH\u0016¢\u0006\u0004\bE\u0010FJ\u000f\u0010H\u001a\u00020GH\u0016¢\u0006\u0004\bH\u0010IR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010JR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010KR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010LR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010MR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010NR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010OR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010PR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010QR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010RR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010SR\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010TR\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010UR\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010VR\u001a\u0010\u001d\u001a\u00020\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010W\u001a\u0004\bX\u0010YR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010ZR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020;8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b_\u0010`R(\u0010b\u001a\u00020a8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\bb\u0010c\u0012\u0004\bh\u0010(\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u0016\u0010i\u001a\u00020+8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bi\u0010jR\u0018\u0010l\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0018\u0010o\u001a\u0004\u0018\u00010n8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bo\u0010pR\u0018\u0010r\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\br\u0010sR\u0016\u0010u\u001a\u0004\u0018\u00010t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bu\u0010vR\u001a\u0010x\u001a\b\u0012\u0004\u0012\u00020&0w8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010yR\u0018\u0010z\u001a\u0004\u0018\u0001078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010{R\u0018\u0010|\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b|\u0010}R$\u0010~\u001a\u0010\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020C\u0018\u00010A8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u001a\u0010\u0080\u0001\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0080\u0001\u0010sR\u001a\u0010\u0081\u0001\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0081\u0001\u0010sR\u001a\u0010\u0082\u0001\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0082\u0001\u0010sR\u0017\u0010\u0085\u0001\u001a\u00030\u0083\u00018VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010IR)\u0010\u0087\u0001\u001a\u00020;2\u0007\u0010\u0086\u0001\u001a\u00020;8V@VX\u0096\u000e¢\u0006\u000f\u001a\u0005\b\u0087\u0001\u0010=\"\u0006\b\u0088\u0001\u0010\u0089\u0001¨\u0006\u008c\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CameraController;", "Landroidx/camera/camera2/pipe/CameraController;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/StrictMode;", "strictMode", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "graphConfig", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "surfaceTracker", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor;", "cameraStatusMonitor", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "captureSessionFactory", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessorFactory;", "captureSequenceProcessorFactory", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;", "camera2DeviceManager", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "cameraSurfaceManager", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "camera2Quirks", "Landroidx/camera/camera2/pipe/core/TimeSource;", "timeSource", "Landroidx/camera/camera2/pipe/CameraGraphId;", "cameraGraphId", "Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;", "shutdownListener", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "streamGraph", "Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencers;", "concurrentSessionSequencers", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/StrictMode;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/GraphListener;Landroidx/camera/camera2/pipe/SurfaceTracker;Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor;Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessorFactory;Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;Landroidx/camera/camera2/pipe/CameraSurfaceManager;Landroidx/camera/camera2/pipe/compat/Camera2Quirks;Landroidx/camera/camera2/pipe/core/TimeSource;Landroidx/camera/camera2/pipe/CameraGraphId;Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencers;)V", _UrlKt.FRAGMENT_ENCODE_SET, "tryRestart", "()V", "startLocked", "stopLocked", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "cameraStatus", "onCameraStatusChanged", "(Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;)V", "bindSessionToCamera", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/compat/CameraStateClosed;", "cameraState", "onStateClosed", "(Landroidx/camera/camera2/pipe/compat/CameraStateClosed;)V", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "session", "Landroidx/camera/camera2/pipe/compat/VirtualCamera;", "camera", "detachSessionAndCamera", "(Landroidx/camera/camera2/pipe/compat/CaptureSessionState;Landroidx/camera/camera2/pipe/compat/VirtualCamera;)V", _UrlKt.FRAGMENT_ENCODE_SET, "isClosed", "()Z", "start", "close", "awaitClosed", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "surfaceMap", "updateSurfaceMap", "(Ljava/util/Map;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/camera/camera2/pipe/core/Threads;", "Landroidx/camera/camera2/pipe/StrictMode;", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessorFactory;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "Landroidx/camera/camera2/pipe/core/TimeSource;", "Landroidx/camera/camera2/pipe/CameraGraphId;", "getCameraGraphId", "()Landroidx/camera/camera2/pipe/CameraGraphId;", "Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", "_isForeground", "Z", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "controllerState", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "getControllerState$camera_camera2_pipe", "()Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "setControllerState$camera_camera2_pipe", "(Landroidx/camera/camera2/pipe/CameraController$ControllerState;)V", "getControllerState$camera_camera2_pipe$annotations", "cameraAvailability", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "Landroidx/camera/camera2/pipe/CameraError;", "lastCameraError", "Landroidx/camera/camera2/pipe/CameraError;", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "lastCameraPrioritiesChangedTs", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "Lkotlinx/coroutines/Job;", "restartJob", "Lkotlinx/coroutines/Job;", "Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;", "concurrentSessionSequencer", "Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;", "Lkotlinx/coroutines/CompletableDeferred;", "closedDeferred", "Lkotlinx/coroutines/CompletableDeferred;", "currentCamera", "Landroidx/camera/camera2/pipe/compat/VirtualCamera;", "currentSession", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "currentSurfaceMap", "Ljava/util/Map;", "currentCameraStateJob", "cameraAvailabilityJob", "cameraPrioritiesJob", "Landroidx/camera/camera2/pipe/CameraId;", "getCameraId-Dz_R5H8", "cameraId", "value", "isForeground", "setForeground", "(Z)V", "Companion", "ShutdownListener", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CameraController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CameraController.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraController\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,512:1\n1#2:513\n50#3,2:514\n59#3,2:516\n71#3,2:518\n82#3,2:520\n50#3,2:522\n71#3,2:524\n71#3,2:526\n50#3,2:528\n50#3,2:530\n50#3,2:532\n50#3,2:534\n50#3,2:536\n50#3,2:538\n71#3,2:540\n50#3,2:542\n50#3,2:544\n50#3,2:546\n*S KotlinDebug\n*F\n+ 1 Camera2CameraController.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraController\n*L\n166#1:514,2\n200#1:516,2\n203#1:518,2\n219#1:520,2\n249#1:522,2\n257#1:524,2\n263#1:526,2\n274#1:528,2\n279#1:530,2\n300#1:532,2\n322#1:534,2\n328#1:536,2\n331#1:538,2\n336#1:540,2\n409#1:542,2\n412#1:544,2\n434#1:546,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CameraController implements CameraController {
    public static final Companion Companion = null;
    private static final long PRIORITIES_CHANGED_THRESHOLD_NS = 0;
    private boolean _isForeground;
    private final Camera2DeviceManager camera2DeviceManager;
    private final Camera2Quirks camera2Quirks;
    private CameraStatusMonitor.CameraStatus cameraAvailability;
    private Job cameraAvailabilityJob;
    private final CameraGraphId cameraGraphId;
    private Job cameraPrioritiesJob;
    private final CameraStatusMonitor cameraStatusMonitor;
    private final CameraSurfaceManager cameraSurfaceManager;
    private final Camera2CaptureSequenceProcessorFactory captureSequenceProcessorFactory;
    private final CaptureSessionFactory captureSessionFactory;
    private final CompletableDeferred<Unit> closedDeferred;
    private final ConcurrentSessionSequencer concurrentSessionSequencer;
    private CameraController.ControllerState controllerState;
    private VirtualCamera currentCamera;
    private Job currentCameraStateJob;
    private CaptureSessionState currentSession;
    private Map<StreamId, ? extends Surface> currentSurfaceMap;
    private final CameraGraph.Config graphConfig;
    private final GraphListener graphListener;
    private CameraError lastCameraError;
    private TimestampNs lastCameraPrioritiesChangedTs;
    private final Object lock;
    private Job restartJob;
    private final CoroutineScope scope;
    private final ShutdownListener shutdownListener;
    private final StreamGraphImpl streamGraph;
    private final StrictMode strictMode;
    private final SurfaceTracker surfaceTracker;
    private final Threads threads;
    private final TimeSource timeSource;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraController$1", f = "Camera2CameraController.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ Camera2CameraController this$0;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00031<T> implements FlowCollector {
            final /* synthetic */ Camera2CameraController this$0;

            public C00031(Camera2CameraController r1) {
                this.this$0 = r1;
            }

            public final Object emit(CameraStatusMonitor.CameraStatus r4, Continuation<? super Unit> r5) {
                if ((r4 instanceof CameraStatusMonitor.CameraStatus.CameraAvailable) == false) goto L10;
                if (CameraId.m230equalsimpl0(((CameraStatusMonitor.CameraStatus.CameraAvailable) r4).m553getCameraIdDz_R5H8(), this.this$0.m429getCameraIdDz_R5H8()) == false) goto L7;
                Camera2CameraController.access$onCameraStatusChanged(this.this$0, r4);
            L17:
                return Unit.INSTANCE;
            L7:
                Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
                return null;
            L10:
                if ((r4 instanceof CameraStatusMonitor.CameraStatus.CameraUnavailable) == false) goto L17;
                if (CameraId.m230equalsimpl0(((CameraStatusMonitor.CameraStatus.CameraUnavailable) r4).m554getCameraIdDz_R5H8(), this.this$0.m429getCameraIdDz_R5H8()) == false) goto L14;
                Camera2CameraController.access$onCameraStatusChanged(this.this$0, r4);
                goto L17
            L14:
                Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
                return null;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
                return emit((CameraStatusMonitor.CameraStatus) r1, r2);
            }
        }

        public AnonymousClass1(Camera2CameraController r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 == 1) goto L8;
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r5);
        L12:
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L9:
            ResultKt.throwOnFailure(r5);
            StateFlow<CameraStatusMonitor.CameraStatus> r52 = Camera2CameraController.access$getCameraStatusMonitor$p(this.this$0).getCameraAvailability();
            C00031 r12 = new C00031(this.this$0);
            this.label = 1;
            if (r52.collect(r12, this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraController$2", f = "Camera2CameraController.kt", i = {}, l = {140}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ Camera2CameraController this$0;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$2$1, reason: invalid class name */
        public static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ Camera2CameraController this$0;

            public AnonymousClass1(Camera2CameraController r1) {
                this.this$0 = r1;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
                return emit((Unit) r1, r2);
            }

            public final Object emit(Unit r1, Continuation<? super Unit> r2) {
                Camera2CameraController.access$onCameraStatusChanged(this.this$0, CameraStatusMonitor.CameraStatus.CameraPrioritiesChanged.INSTANCE);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Camera2CameraController r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 == 1) goto L8;
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r5);
        L12:
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L9:
            ResultKt.throwOnFailure(r5);
            SharedFlow<Unit> r52 = Camera2CameraController.access$getCameraStatusMonitor$p(this.this$0).getCameraPriorities();
            AnonymousClass1 r12 = new AnonymousClass1(this.this$0);
            this.label = 1;
            if (r52.collect(r12, this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J;\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CameraController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "RESTART_TIMEOUT_WHEN_ENABLED_MS", _UrlKt.FRAGMENT_ENCODE_SET, "MS_TO_NS", _UrlKt.FRAGMENT_ENCODE_SET, "PRIORITIES_CHANGED_THRESHOLD_NS", "Landroidx/camera/camera2/pipe/core/DurationNs;", "J", "shouldRestart", _UrlKt.FRAGMENT_ENCODE_SET, "controllerState", "Landroidx/camera/camera2/pipe/CameraController$ControllerState;", "lastCameraError", "Landroidx/camera/camera2/pipe/CameraError;", "cameraAvailability", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "lastCameraPrioritiesChangedTs", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "currentTs", "shouldRestart-X9Wt83s$camera_camera2_pipe", "(Landroidx/camera/camera2/pipe/CameraController$ControllerState;Landroidx/camera/camera2/pipe/CameraError;Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;Landroidx/camera/camera2/pipe/core/TimestampNs;J)Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCamera2CameraController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CameraController.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraController$Companion\n+ 2 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,512:1\n29#2:513\n50#3,2:514\n*S KotlinDebug\n*F\n+ 1 Camera2CameraController.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraController$Companion\n*L\n480#1:513\n492#1:514,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: shouldRestart-X9Wt83s$camera_camera2_pipe, reason: not valid java name */
        public final boolean m430shouldRestartX9Wt83s$camera_camera2_pipe(CameraController.ControllerState r4, CameraError r5, CameraStatusMonitor.CameraStatus r6, TimestampNs r7, long r8) {
            if ((r6 instanceof CameraStatusMonitor.CameraStatus.CameraAvailable) == false) goto L10;
            int r3 = CameraError.Companion.m187getERROR_CAMERA_DISABLEDv7Vf74A();
            if (r5 != null) goto L7;
            boolean r32 = false;
        L8:
            if (r32 == true) goto L10;
            boolean r33 = true;
        L11:
            if (r7 != null) goto L14;
        L12:
            boolean r72 = false;
        L17:
            if (Intrinsics.areEqual(r4, CameraController.ControllerState.DISCONNECTED.INSTANCE) == false) goto L33;
            if (r33 == true) goto L31;
            if (r72 == true) goto L31;
            int r34 = Build.VERSION.SDK_INT;
            if (29 <= r34) goto L24;
        L26:
            boolean r35 = false;
        L27:
            if (r35 == true) goto L29;
        L46:
            return false;
        L29:
            if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L31;
            android.util.Log.d("CXCP", "Quirk for multi-resume activated: Kicking off restart.");
            goto L31
        L24:
            if (r34 >= 33) goto L26;
            r35 = true;
        L31:
            return true;
        L33:
            if (Intrinsics.areEqual(r4, CameraController.ControllerState.ERROR.INSTANCE) == false) goto L46;
            if (r33 == false) goto L46;
            CameraError.Companion r36 = CameraError.Companion;
            int r42 = r36.m195getERROR_GRAPH_CONFIGv7Vf74A();
            if (r5 != null) goto L38;
            boolean r43 = false;
        L39:
            if (r43 == true) goto L46;
            int r37 = r36.m197getERROR_SECURITY_EXCEPTIONv7Vf74A();
            if (r5 != null) goto L43;
            boolean r38 = false;
        L44:
            if (r38 == true) goto L46;
            return true;
        L43:
            r38 = CameraError.m178equalsimpl0(r5.m182unboximpl(), r37);
            goto L44
        L38:
            r43 = CameraError.m178equalsimpl0(r5.m182unboximpl(), r42);
            goto L39
        L14:
            if (DurationNs.m497compareTozYRVrok(DurationNs.m498constructorimpl(r8 - r7.m514unboximpl()), Camera2CameraController.access$getPRIORITIES_CHANGED_THRESHOLD_NS$cp()) > 0) goto L12;
            r72 = true;
            goto L17
        L7:
            r32 = CameraError.m178equalsimpl0(r5.m182unboximpl(), r3);
        L10:
            r33 = false;
            goto L11
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;", _UrlKt.FRAGMENT_ENCODE_SET, "onControllerClosed", _UrlKt.FRAGMENT_ENCODE_SET, "cameraController", "Landroidx/camera/camera2/pipe/CameraController;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface ShutdownListener {
        void onControllerClosed(CameraController r1);
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraController", f = "Camera2CameraController.kt", i = {}, l = {340}, m = "awaitClosed", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$awaitClosed$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00941 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ Camera2CameraController this$0;

        public C00941(Camera2CameraController r1, Continuation<? super C00941> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.awaitClosed(this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$bindSessionToCamera$3, reason: invalid class name */
    public static final class AnonymousClass3<T> implements FlowCollector {
        final /* synthetic */ Ref.ObjectRef<CaptureSessionState> $session;
        final /* synthetic */ Camera2CameraController this$0;

        public AnonymousClass3(Ref.ObjectRef<CaptureSessionState> r1, Camera2CameraController r2) {
            this.$session = r1;
            this.this$0 = r2;
        }

        public final Object emit(CameraState r1, Continuation<? super Unit> r2) {
            if ((r1 instanceof CameraStateOpen) == false) goto L6;
            this.$session.element.setCameraDevice(((CameraStateOpen) r1).getCameraDevice());
        L12:
            return Unit.INSTANCE;
        L6:
            if ((r1 instanceof CameraStateClosing) == false) goto L9;
            this.$session.element.shutdown();
            goto L12
        L9:
            if ((r1 instanceof CameraStateClosed) == false) goto L12;
            this.$session.element.shutdown();
            Camera2CameraController.access$onStateClosed(this.this$0, (CameraStateClosed) r1);
            goto L12
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
            return emit((CameraState) r1, r2);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraController$startLocked$5", f = "Camera2CameraController.kt", i = {}, l = {Opcodes.INVOKE_POLYMORPHIC_RANGE}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$startLocked$5, reason: invalid class name */
    public static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ Camera2CameraController this$0;

        public AnonymousClass5(Camera2CameraController r1, Continuation<? super AnonymousClass5> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass5(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass5) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            Camera2CameraController r42 = this.this$0;
            this.label = 1;
            if (Camera2CameraController.access$bindSessionToCamera(r42, this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraController$tryRestart$2", f = "Camera2CameraController.kt", i = {}, l = {181}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nCamera2CameraController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CameraController.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraController$tryRestart$2\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,512:1\n50#2,2:513\n*S KotlinDebug\n*F\n+ 1 Camera2CameraController.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraController$tryRestart$2\n*L\n188#1:513,2\n*E\n"})
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraController$tryRestart$2, reason: invalid class name and case insensitive filesystem */
    public static final class C00952 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMs;
        int label;
        final /* synthetic */ Camera2CameraController this$0;

        public C00952(long r1, Camera2CameraController r3, Continuation<? super C00952> r4) {
            this.$delayMs = r1;
            this.this$0 = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C00952(this.$delayMs, this.this$0, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C00952) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L8;
            if (r2 != 1) goto L6;
            ResultKt.throwOnFailure(r7);
        L11:
            Object r72 = Camera2CameraController.access$getLock$p(this.this$0);
            Camera2CameraController r6 = this.this$0;
            monitor-enter(r72);
        L22:
            th = move-exception;
            throw th;
        L14:
            if (Camera2CameraController.access$isClosed(r6) == false) goto L16;
        L25:
            monitor-exit(r72);
            return Unit.INSTANCE;
        L16:
            if (Intrinsics.areEqual(r6.getControllerState$camera_camera2_pipe(), CameraController.ControllerState.STOPPING.INSTANCE) == true) goto L25;
            if (Intrinsics.areEqual(r6.getControllerState$camera_camera2_pipe(), CameraController.ControllerState.STOPPED.INSTANCE) == true) goto L25;
            if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L24;
            android.util.Log.d("CXCP", "Restarting " + r6 + "...");     // Catch: Throwable -> L22
        L24:
            Camera2CameraController.access$getSurfaceTracker$p(r6).registerAllSurfaces();     // Catch: Throwable -> L22
            Camera2CameraController.access$stopLocked(r6);     // Catch: Throwable -> L22
            Camera2CameraController.access$startLocked(r6);     // Catch: Throwable -> L22
            goto L25
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            long r4 = this.$delayMs;
            this.label = 1;
            if (DelayKt.delay(r4, this) != r1) goto L11;
            return r1;
        }
    }

    public static Unit $r8$lambda$8pmWcPJwfGYerhscAozQzB2p3_o(Camera2CameraController r3, Throwable r4) {
        Object r42 = r3.lock;
        monitor-enter(r42);
        r3.controllerState = CameraController.ControllerState.CLOSED.INSTANCE;     // Catch: Throwable -> L7
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L9;
        android.util.Log.d("CXCP", r3 + " is closed");     // Catch: Throwable -> L7
    L9:
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r42);
        r3.shutdownListener.onControllerClosed(r3);
        CompletableDeferred<Unit> r43 = r3.closedDeferred;
        Unit r02 = Unit.INSTANCE;
        r43.complete(r02);
        CoroutineScopeKt.cancel$default(r3.scope, null, 1, null);
        return r02;
    L7:
        th = move-exception;
        throw th;
    }

    public static boolean $r8$lambda$PloLbSpMiWE7AtJf4WvxN0sjfEY(Camera2CameraController r0, Unit r1) {
        return r0.isForeground();
    }

    static {
        Companion = new Companion(null);
        PRIORITIES_CHANGED_THRESHOLD_NS = DurationNs.m498constructorimpl(200000000);
    }

    public Camera2CameraController(CoroutineScope r1, Threads r2, StrictMode r3, CameraGraph.Config r4, GraphListener r5, SurfaceTracker r6, CameraStatusMonitor r7, CaptureSessionFactory r8, Camera2CaptureSequenceProcessorFactory r9, Camera2DeviceManager r10, CameraSurfaceManager r11, Camera2Quirks r12, TimeSource r13, CameraGraphId r14, ShutdownListener r15, StreamGraphImpl r16, ConcurrentSessionSequencers r17) {
        this.scope = r1;
        this.threads = r2;
        this.strictMode = r3;
        this.graphConfig = r4;
        this.graphListener = r5;
        this.surfaceTracker = r6;
        this.cameraStatusMonitor = r7;
        this.captureSessionFactory = r8;
        this.captureSequenceProcessorFactory = r9;
        this.camera2DeviceManager = r10;
        this.cameraSurfaceManager = r11;
        this.camera2Quirks = r12;
        this.timeSource = r13;
        this.cameraGraphId = r14;
        this.shutdownListener = r15;
        this.streamGraph = r16;
        this.lock = new Object();
        this._isForeground = true;
        this.controllerState = CameraController.ControllerState.STOPPED.INSTANCE;
        this.cameraAvailability = new CameraStatusMonitor.CameraStatus.CameraUnavailable(m429getCameraIdDz_R5H8(), null);
        ConcurrentCameraGraphs r32 = r4.getConcurrentCameraGraphs$camera_camera2_pipe();
        if (r32 == null) goto L5;
        ConcurrentSessionSequencer r33 = r17.getSequencer(getCameraGraphId(), r32);
    L6:
        this.concurrentSessionSequencer = r33;
        this.closedDeferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.cameraAvailabilityJob = BuildersKt.launch$default(r1, null, null, new AnonymousClass1(this, null), 3, null);
        this.cameraPrioritiesJob = BuildersKt.launch$default(r1, null, null, new AnonymousClass2(this, null), 3, null);
        return;
    L5:
        r33 = null;
        goto L6
    }

    public static final /* synthetic */ Object access$bindSessionToCamera(Camera2CameraController r0, Continuation r1) {
        return r0.bindSessionToCamera(r1);
    }

    public static final /* synthetic */ CameraStatusMonitor.CameraStatus access$getCameraAvailability$p(Camera2CameraController r0) {
        return r0.cameraAvailability;
    }

    public static final /* synthetic */ CameraStatusMonitor access$getCameraStatusMonitor$p(Camera2CameraController r0) {
        return r0.cameraStatusMonitor;
    }

    public static final /* synthetic */ CameraError access$getLastCameraError$p(Camera2CameraController r0) {
        return r0.lastCameraError;
    }

    public static final /* synthetic */ TimestampNs access$getLastCameraPrioritiesChangedTs$p(Camera2CameraController r0) {
        return r0.lastCameraPrioritiesChangedTs;
    }

    public static final /* synthetic */ Object access$getLock$p(Camera2CameraController r0) {
        return r0.lock;
    }

    public static final /* synthetic */ long access$getPRIORITIES_CHANGED_THRESHOLD_NS$cp() {
        return PRIORITIES_CHANGED_THRESHOLD_NS;
    }

    public static final /* synthetic */ SurfaceTracker access$getSurfaceTracker$p(Camera2CameraController r0) {
        return r0.surfaceTracker;
    }

    public static final /* synthetic */ boolean access$isClosed(Camera2CameraController r0) {
        return r0.isClosed();
    }

    public static final /* synthetic */ void access$onCameraStatusChanged(Camera2CameraController r0, CameraStatusMonitor.CameraStatus r1) {
        r0.onCameraStatusChanged(r1);
    }

    public static final /* synthetic */ void access$onStateClosed(Camera2CameraController r0, CameraStateClosed r1) {
        r0.onStateClosed(r1);
    }

    public static final /* synthetic */ void access$startLocked(Camera2CameraController r0) {
        r0.startLocked();
    }

    public static final /* synthetic */ void access$stopLocked(Camera2CameraController r0) {
        r0.stopLocked();
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [T, androidx.camera.camera2.pipe.compat.CaptureSessionState] */
    private final Object bindSessionToCamera(Continuation<? super Unit> r6) {
        Ref.ObjectRef r0 = new Ref.ObjectRef();
        Object r1 = this.lock;
        monitor-enter(r1);
        VirtualCamera r2 = this.currentCamera;     // Catch: Throwable -> L15
        ?? r3 = this.currentSession;     // Catch: Throwable -> L15
        r0.element = r3;     // Catch: Throwable -> L15
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r1);
        if (r2 == null) goto L14;
        if (r3 == 0) goto L14;
        Object r5 = r2.getState().collect(new AnonymousClass3(r0, this), r6);
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L12;
        return r5;
    L12:
        return Unit.INSTANCE;
    L14:
        return Unit.INSTANCE;
    L15:
        th = move-exception;
        throw th;
    }

    private final void detachSessionAndCamera(CaptureSessionState r7, VirtualCamera r8) {
        Job r72 = BuildersKt.launch$default(this.scope, null, null, new Camera2CameraController$detachSessionAndCamera$job$1(r7, r8, null), 3, null);
        if (Intrinsics.areEqual(this.controllerState, CameraController.ControllerState.CLOSING.INSTANCE) == false) goto L6;
        r72.invokeOnCompletion(new Camera2CameraController$$ExternalSyntheticLambda0(this));
        return;
    }

    private final boolean isClosed() {
        if (Intrinsics.areEqual(this.controllerState, CameraController.ControllerState.CLOSING.INSTANCE) == false) goto L5;
        return true;
    L5:
        if (Intrinsics.areEqual(this.controllerState, CameraController.ControllerState.CLOSED.INSTANCE) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private final void onCameraStatusChanged(CameraStatusMonitor.CameraStatus r4) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " (" + CameraId.m232toStringimpl(m429getCameraIdDz_R5H8()) + ") camera status changed: " + r4);
    L5:
        Object r0 = this.lock;
        monitor-enter(r0);
    L14:
        th = move-exception;
        throw th;
    L8:
        if (isClosed() == false) goto L12;
        monitor-exit(r0);
        return;
    L12:
        if ((r4 instanceof CameraStatusMonitor.CameraStatus.CameraAvailable) == false) goto L17;
        this.cameraAvailability = r4;     // Catch: Throwable -> L14
    L22:
        tryRestart();     // Catch: Throwable -> L14
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(r0);
        return;
    L17:
        if ((r4 instanceof CameraStatusMonitor.CameraStatus.CameraUnavailable) == false) goto L20;
        this.cameraAvailability = r4;     // Catch: Throwable -> L14
        goto L22
    L20:
        if ((r4 instanceof CameraStatusMonitor.CameraStatus.CameraPrioritiesChanged) == false) goto L22;
        this.lastCameraPrioritiesChangedTs = TimestampNs.m508boximpl(this.timeSource.mo506nowvQl9yQU());     // Catch: Throwable -> L14
        goto L22
    }

    private final void onStateClosed(CameraStateClosed r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (isClosed() == false) goto L9;
        monitor-exit(r0);
        return;
    L9:
        if (r5.m462getCameraErrorCodemVEW8x0() == null) goto L20;
        this.lastCameraError = r5.m462getCameraErrorCodemVEW8x0();     // Catch: Throwable -> L15
        if (CameraError.m180isDisconnectedimpl(r5.m462getCameraErrorCodemVEW8x0().m182unboximpl()) == false) goto L17;
        this.controllerState = CameraController.ControllerState.DISCONNECTED.INSTANCE;     // Catch: Throwable -> L15
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L21;
        android.util.Log.d("CXCP", this + " is disconnected");     // Catch: Throwable -> L15
    L21:
        this.surfaceTracker.unregisterAllSurfaces();     // Catch: Throwable -> L15
        tryRestart();     // Catch: Throwable -> L15
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r0);
        return;
    L17:
        this.controllerState = CameraController.ControllerState.ERROR.INSTANCE;     // Catch: Throwable -> L15
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L21;
        android.util.Log.d("CXCP", this + " encountered error: " + CameraError.m181toStringimpl(r5.m462getCameraErrorCodemVEW8x0().m182unboximpl()));     // Catch: Throwable -> L15
        goto L21
    L20:
        this.controllerState = CameraController.ControllerState.STOPPED.INSTANCE;     // Catch: Throwable -> L15
        goto L21
    }

    private final void startLocked() {
        if (isClosed() == true) goto L5;
        CameraController.ControllerState r1 = this.controllerState;
        CameraController.ControllerState.STARTED r4 = CameraController.ControllerState.STARTED.INSTANCE;
        if (Intrinsics.areEqual(r1, r4) == true) goto L11;
        this.lastCameraError = null;
        String r6 = this.graphConfig.m200getCameraDz_R5H8();
        ConcurrentCameraGraphs r2 = this.graphConfig.getConcurrentCameraGraphs$camera_camera2_pipe();
        if (r2 == null) goto L18;
        Set<CameraId> r22 = r2.getCameraIds();
        if (r22 == null) goto L18;
    L19:
        VirtualCamera r23 = this.camera2DeviceManager.mo446openzDSwpeU(r6, CollectionsKt.toList(SetsKt.minus(r22, CameraId.m227boximpl(r6))), this.graphListener, false, new Camera2CameraController$$ExternalSyntheticLambda1(this));
        if (r23 != null) goto L26;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L46;
        android.util.Log.e("CXCP", "Failed to start " + this + ": Open request submission failed");
        return;
    L46:
        return;
    L26:
        if (this.currentCamera == null) goto L28;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L28:
        if (this.currentSession != null) goto L40;
        this.currentCamera = r23;
        CaptureSessionState r7 = new CaptureSessionState(this.graphListener, this.captureSessionFactory, this.captureSequenceProcessorFactory, this.cameraSurfaceManager, this.timeSource, this.graphConfig.getFlags(), this.concurrentSessionSequencer, this.streamGraph, this.strictMode, this.threads, this.scope);
        this.currentSession = r7;
        Map<StreamId, ? extends Surface> r24 = this.currentSurfaceMap;
        if (r24 == null) goto L32;
        r7.configureSurfaceMap(r24);
    L32:
        this.controllerState = r4;
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L35;
        android.util.Log.d("CXCP", "Started " + this);
    L35:
        Job r25 = this.currentCameraStateJob;
        if (r25 == null) goto L38;
        Job.DefaultImpls.cancel$default(r25, null, 1, null);
    L38:
        this.currentCameraStateJob = BuildersKt.launch$default(this.scope, null, null, new AnonymousClass5(this, null), 3, null);
        return;
    L40:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L18:
        r22 = SetsKt.setOf(CameraId.m227boximpl(r6));
        goto L19
    L11:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L45;
        android.util.Log.w("CXCP", "Ignoring start(): " + this + " is already started");
        return;
    L45:
        return;
    L5:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L44;
        android.util.Log.i("CXCP", "Ignoring start(): " + this + " is already closed");
        return;
    }

    private final void stopLocked() {
        if (isClosed() == true) goto L5;
        CameraController.ControllerState r0 = this.controllerState;
        CameraController.ControllerState.STOPPING r3 = CameraController.ControllerState.STOPPING.INSTANCE;
        if (Intrinsics.areEqual(r0, r3) == true) goto L19;
        if (Intrinsics.areEqual(this.controllerState, CameraController.ControllerState.STOPPED.INSTANCE) == true) goto L19;
        VirtualCamera r02 = this.currentCamera;
        CaptureSessionState r1 = this.currentSession;
        this.currentCamera = null;
        this.currentSession = null;
        this.controllerState = r3;
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L16;
        android.util.Log.d("CXCP", "Stopping " + this);
    L16:
        detachSessionAndCamera(r1, r02);
        return;
    L19:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L23;
        android.util.Log.w("CXCP", "Ignoring stop(): " + this + " already stopping or stopped");
        return;
    L23:
        return;
    L5:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L22;
        android.util.Log.w("CXCP", "Ignoring stop(): " + this + " is already closed");
        return;
    }

    private final void tryRestart() {
        long r6 = this.timeSource.mo506nowvQl9yQU();
        if (Companion.m430shouldRestartX9Wt83s$camera_camera2_pipe(this.controllerState, this.lastCameraError, this.cameraAvailability, this.lastCameraPrioritiesChangedTs, r6) == true) goto L9;
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L17;
        android.util.Log.d("CXCP", this + ": Not restarting. Controller state = " + getControllerState$camera_camera2_pipe() + ", last camera error = " + access$getLastCameraError$p(this) + ", camera availability = " + access$getCameraAvailability$p(this) + ", last camera priorities changed = " + access$getLastCameraPrioritiesChangedTs$p(this) + ", current timestamp = " + TimestampNs.m513toStringimpl(r6) + '.');
        return;
    L17:
        return;
    L9:
        if (this.graphConfig.getFlags().getEnableRestartDelays() == false) goto L11;
        long r0 = 700;
    L12:
        Job r2 = this.restartJob;
        if (r2 == null) goto L15;
        Job.DefaultImpls.cancel$default(r2, null, 1, null);
    L15:
        this.restartJob = BuildersKt.launch$default(this.scope, null, null, new C00952(r0, this, null), 3, null);
        return;
    L11:
        r0 = 0;
        goto L12
    }

    @Override // androidx.camera.camera2.pipe.CameraController
    public Object awaitClosed(Continuation<? super Boolean> r7) {
        if ((r7 instanceof C00941) == false) goto L7;
        C00941 r0 = (C00941) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r72);
    L43:
        return Boxing.boxBoolean(true);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r72);
        Log r73 = Log.INSTANCE;
        if (r73.getDEBUG_LOGGABLE() == false) goto L17;
        android.util.Log.d("CXCP", this + "#awaitClosed");
    L17:
        Object r22 = this.lock;
        monitor-enter(r22);
    L24:
        th = move-exception;
        throw th;
    L20:
        if (Intrinsics.areEqual(this.controllerState, CameraController.ControllerState.CLOSED.INSTANCE) == false) goto L30;
        if (r73.getDEBUG_LOGGABLE() == false) goto L26;
        android.util.Log.d("CXCP", this + "#awaitClosed: Controller is already closed.");     // Catch: Throwable -> L24
    L26:
        Boolean r6 = Boxing.boxBoolean(true);     // Catch: Throwable -> L24
        monitor-exit(r22);
        return r6;
    L30:
        if (Intrinsics.areEqual(this.controllerState, CameraController.ControllerState.CLOSING.INSTANCE) == false) goto L32;
        Unit r74 = Unit.INSTANCE;     // Catch: Throwable -> L24
        monitor-exit(r22);
        CompletableDeferred<Unit> r62 = this.closedDeferred;
        r0.label = 1;
        if (r62.await(r0) != r12) goto L43;
        return r12;
    L32:
        if (r73.getWARN_LOGGABLE() == false) goto L34;
        android.util.Log.w("CXCP", this + "#awaitClosed: Controller isn't closing!");     // Catch: Throwable -> L24
    L34:
        Boolean r63 = Boxing.boxBoolean(false);     // Catch: Throwable -> L24
        monitor-exit(r22);
        return r63;
    L7:
        r0 = new C00941(this, r7);
        goto L8
    }

    @Override // androidx.camera.camera2.pipe.CameraController
    public void close() {
        Object r2 = this.lock;
        monitor-enter(r2);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (isClosed() == false) goto L8;
        monitor-exit(r2);
        return;
    L8:
        this.controllerState = CameraController.ControllerState.CLOSING.INSTANCE;     // Catch: Throwable -> L11
        Log r3 = Log.INSTANCE;     // Catch: Throwable -> L11
        if (r3.getDEBUG_LOGGABLE() == false) goto L13;
        android.util.Log.d("CXCP", "Closed " + this);     // Catch: Throwable -> L11
    L13:
        VirtualCamera r1 = this.currentCamera;     // Catch: Throwable -> L11
        CaptureSessionState r4 = this.currentSession;     // Catch: Throwable -> L11
        this.currentCamera = null;     // Catch: Throwable -> L11
        this.currentSession = null;     // Catch: Throwable -> L11
        Job r6 = this.restartJob;     // Catch: Throwable -> L11
        if (r6 == null) goto L16;
        Job.DefaultImpls.cancel$default(r6, null, 1, null);     // Catch: Throwable -> L11
    L16:
        Job r62 = this.currentCameraStateJob;     // Catch: Throwable -> L11
        if (r62 == null) goto L19;
        Job.DefaultImpls.cancel$default(r62, null, 1, null);     // Catch: Throwable -> L11
    L19:
        this.currentCameraStateJob = null;     // Catch: Throwable -> L11
        Job r63 = this.cameraAvailabilityJob;     // Catch: Throwable -> L11
        if (r63 == null) goto L22;
        Job.DefaultImpls.cancel$default(r63, null, 1, null);     // Catch: Throwable -> L11
    L22:
        this.cameraAvailabilityJob = null;     // Catch: Throwable -> L11
        Job r64 = this.cameraPrioritiesJob;     // Catch: Throwable -> L11
        if (r64 == null) goto L25;
        Job.DefaultImpls.cancel$default(r64, null, 1, null);     // Catch: Throwable -> L11
    L25:
        this.cameraPrioritiesJob = null;     // Catch: Throwable -> L11
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(this.cameraStatusMonitor);     // Catch: Throwable -> L11
        detachSessionAndCamera(r4, r1);     // Catch: Throwable -> L11
        if (this.graphConfig.getFlags().getCloseCameraDeviceOnClose() == true) goto L30;
        if (this.camera2Quirks.m459x552c1673(m429getCameraIdDz_R5H8()) == true) goto L30;
    L33:
        Unit r8 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r2);
        return;
    L30:
        if (r3.getDEBUG_LOGGABLE() == false) goto L32;
        android.util.Log.d("CXCP", "Quirk: Closing " + CameraId.m232toStringimpl(m429getCameraIdDz_R5H8()) + " during " + this + "#close");     // Catch: Throwable -> L11
    L32:
        this.camera2DeviceManager.mo445closeEfqyGwQ(m429getCameraIdDz_R5H8());     // Catch: Throwable -> L11
        goto L33
    }

    public CameraGraphId getCameraGraphId() {
        return this.cameraGraphId;
    }

    /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
    public String m429getCameraIdDz_R5H8() {
        return this.graphConfig.m200getCameraDz_R5H8();
    }

    public final CameraController.ControllerState getControllerState$camera_camera2_pipe() {
        return this.controllerState;
    }

    public boolean isForeground() {
        Object r0 = this.lock;
        monitor-enter(r0);
        boolean r1 = this._isForeground;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraController
    public void setForeground(boolean r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._isForeground = r2;     // Catch: Throwable -> L7
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraController
    public void start() {
        Object r0 = this.lock;
        monitor-enter(r0);
        startLocked();     // Catch: Throwable -> L7
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public String toString() {
        return "Camera2CameraController(" + getCameraGraphId() + ')';
    }

    @Override // androidx.camera.camera2.pipe.CameraController
    public void updateSurfaceMap(Map<StreamId, ? extends Surface> r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (isClosed() == false) goto L8;
        monitor-exit(r0);
        return;
    L8:
        this.currentSurfaceMap = r3;     // Catch: Throwable -> L13
        CaptureSessionState r2 = this.currentSession;     // Catch: Throwable -> L13
        monitor-exit(r0);
        if (r2 == null) goto L18;
        r2.configureSurfaceMap(r3);
        return;
    }
}
