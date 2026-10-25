package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraDevice;
import android.os.Trace;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraInterop;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.SystemTimeSource;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.TimeSource;
import androidx.camera.camera2.pipe.core.TimestampNs;
import androidx.camera.camera2.pipe.core.Timestamps;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001oBo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\"\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b \u0010!J!\u0010\"\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010&\u001a\u00020%H\u0002¢\u0006\u0004\b\"\u0010'J\u0017\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020%H\u0002¢\u0006\u0004\b*\u0010+J%\u0010/\u001a\u00020,*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0004\b-\u0010.J%\u00101\u001a\u00020,*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0004\b0\u0010.J\r\u00102\u001a\u00020\u001f¢\u0006\u0004\b2\u00103J\u0010\u00104\u001a\u00020\u001fH\u0086@¢\u0006\u0004\b4\u00105J\u0017\u0010:\u001a\u00020,2\u0006\u00107\u001a\u000206H\u0000¢\u0006\u0004\b8\u00109J\u0017\u0010;\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b;\u0010<J\u0017\u0010=\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b=\u0010<J\u001f\u0010?\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020#2\u0006\u0010>\u001a\u00020\u0006H\u0016¢\u0006\u0004\b?\u0010@J\u0017\u0010A\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\bA\u0010<J\u0017\u0010C\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020#H\u0000¢\u0006\u0004\bB\u0010<J\u0017\u0010\"\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001bH\u0000¢\u0006\u0004\bD\u0010EJ\u000f\u0010G\u001a\u00020FH\u0016¢\u0006\u0004\bG\u0010HR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010I\u001a\u0004\bJ\u0010HR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010K\u001a\u0004\bL\u0010MR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010NR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010OR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010PR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010QR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010RR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010SR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010TR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010UR\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010VR\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010WR\u0014\u0010X\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010NR\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020,8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0018\u0010^\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020,8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b`\u0010]R\u0014\u0010b\u001a\u00020a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010d\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010OR\u0018\u0010e\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\be\u0010fR\u001a\u0010i\u001a\b\u0012\u0004\u0012\u00020h0g8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010jR\u0017\u0010n\u001a\b\u0012\u0004\u0012\u00020h0k8F¢\u0006\u0006\u001a\u0004\bl\u0010m¨\u0006p"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "Landroid/hardware/camera2/CameraDevice$StateCallback;", "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", "Landroidx/camera/camera2/pipe/CameraMetadata;", "metadata", _UrlKt.FRAGMENT_ENCODE_SET, "attemptNumber", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "attemptTimestampNanos", "Landroidx/camera/camera2/pipe/core/TimeSource;", "timeSource", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "cameraErrorListener", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "camera2DeviceCloser", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "camera2Quirks", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "audioRestrictionController", "interopCameraDeviceStateCallback", "Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "interopCaptureSessionListener", "<init>", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/CameraMetadata;IJLandroidx/camera/camera2/pipe/core/TimeSource;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;Landroidx/camera/camera2/pipe/compat/Camera2Quirks;Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;Landroid/hardware/camera2/CameraDevice$StateCallback;Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "throwable", "Landroidx/camera/camera2/pipe/CameraError;", "cameraError", _UrlKt.FRAGMENT_ENCODE_SET, "closeWith-8PWMtlg", "(Ljava/lang/Throwable;I)V", "closeWith", "Landroid/hardware/camera2/CameraDevice;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState$ClosingInfo;", "closeRequest", "(Landroid/hardware/camera2/CameraDevice;Landroidx/camera/camera2/pipe/compat/AndroidCameraState$ClosingInfo;)V", "closingInfo", "Landroidx/camera/camera2/pipe/compat/CameraStateClosed;", "computeClosedState", "(Landroidx/camera/camera2/pipe/compat/AndroidCameraState$ClosingInfo;)Landroidx/camera/camera2/pipe/compat/CameraStateClosed;", _UrlKt.FRAGMENT_ENCODE_SET, "shouldReopenCameraWhenClosing-_z0IXec", "(Landroidx/camera/camera2/pipe/compat/Camera2Quirks;Ljava/lang/String;Landroidx/camera/camera2/pipe/CameraError;)Z", "shouldReopenCameraWhenClosing", "shouldCreateEmptyCaptureSessionBeforeClosing-_z0IXec", "shouldCreateEmptyCaptureSessionBeforeClosing", "close", "()V", "awaitClosed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "timeoutMillis", "awaitCameraDeviceClosed$camera_camera2_pipe", "(J)Z", "awaitCameraDeviceClosed", "onOpened", "(Landroid/hardware/camera2/CameraDevice;)V", "onDisconnected", "errorCode", "onError", "(Landroid/hardware/camera2/CameraDevice;I)V", "onClosed", "onFinalized$camera_camera2_pipe", "onFinalized", "closeWith$camera_camera2_pipe", "(Ljava/lang/Throwable;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getCameraId-Dz_R5H8", "Landroidx/camera/camera2/pipe/CameraMetadata;", "getMetadata", "()Landroidx/camera/camera2/pipe/CameraMetadata;", "I", "J", "Landroidx/camera/camera2/pipe/core/TimeSource;", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "Landroidx/camera/camera2/pipe/core/Threads;", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "Landroid/hardware/camera2/CameraDevice$StateCallback;", "Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "debugId", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", "opening", "Z", "pendingClose", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState$ClosingInfo;", "shouldDelayFinalizing", "Ljava/util/concurrent/CountDownLatch;", "cameraDeviceClosed", "Ljava/util/concurrent/CountDownLatch;", "requestTimestampNanos", "openTimestampNanos", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/camera/camera2/pipe/compat/CameraState;", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "state", "ClosingInfo", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVirtualCamera.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VirtualCamera.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraState\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 4 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 5 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,585:1\n59#2,2:586\n59#2:594\n60#2:603\n50#2,2:612\n50#2,2:622\n50#2,2:628\n59#2,2:630\n50#2,2:636\n70#3:588\n70#3:589\n74#3,2:597\n74#3,2:599\n74#3,2:601\n70#3:643\n71#4,4:590\n78#4,4:604\n71#4,4:608\n78#4,4:614\n71#4,4:618\n78#4,4:624\n71#4,4:632\n78#4,4:638\n29#5:595\n29#5:596\n29#5:644\n29#5:645\n29#5:646\n29#5:647\n1#6:642\n*S KotlinDebug\n*F\n+ 1 VirtualCamera.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraState\n*L\n275#1:586,2\n309#1:594\n309#1:603\n392#1:612,2\n409#1:622,2\n422#1:628,2\n427#1:630,2\n436#1:636,2\n280#1:588\n305#1:589\n313#1:597,2\n315#1:599,2\n316#1:601,2\n520#1:643\n308#1:590,4\n386#1:604,4\n391#1:608,4\n403#1:614,4\n408#1:618,4\n417#1:624,4\n435#1:632,4\n440#1:638,4\n310#1:595\n311#1:596\n523#1:644\n524#1:645\n530#1:646\n533#1:647\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidCameraState extends CameraDevice.StateCallback {
    private final MutableStateFlow<CameraState> _state;
    private final int attemptNumber;
    private final long attemptTimestampNanos;
    private final AudioRestrictionController audioRestrictionController;
    private final Camera2DeviceCloser camera2DeviceCloser;
    private final Camera2Quirks camera2Quirks;
    private final CountDownLatch cameraDeviceClosed;
    private final CameraErrorListener cameraErrorListener;
    private final String cameraId;
    private final int debugId;
    private final CameraDevice.StateCallback interopCameraDeviceStateCallback;
    private final CameraInterop.CaptureSessionListener interopCaptureSessionListener;
    private final Object lock;
    private final CameraMetadata metadata;
    private TimestampNs openTimestampNanos;
    private boolean opening;
    private ClosingInfo pendingClose;
    private final long requestTimestampNanos;
    private boolean shouldDelayFinalizing;
    private final Threads threads;
    private final TimeSource timeSource;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0082\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidCameraState$ClosingInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/compat/ClosedReason;", "reason", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "closingTimestamp", "Landroidx/camera/camera2/pipe/CameraError;", "errorCode", _UrlKt.FRAGMENT_ENCODE_SET, "exception", "<init>", "(Landroidx/camera/camera2/pipe/compat/ClosedReason;JLandroidx/camera/camera2/pipe/CameraError;Ljava/lang/Throwable;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/ClosedReason;", "getReason", "()Landroidx/camera/camera2/pipe/compat/ClosedReason;", "J", "getClosingTimestamp-vQl9yQU", "()J", "Landroidx/camera/camera2/pipe/CameraError;", "getErrorCode-mVEW8x0", "()Landroidx/camera/camera2/pipe/CameraError;", "Ljava/lang/Throwable;", "getException", "()Ljava/lang/Throwable;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nVirtualCamera.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VirtualCamera.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraState$ClosingInfo\n+ 2 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n*L\n1#1,585:1\n70#2:586\n*S KotlinDebug\n*F\n+ 1 VirtualCamera.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraState$ClosingInfo\n*L\n550#1:586\n*E\n"})
    public static final class ClosingInfo {
        private final long closingTimestamp;
        private final CameraError errorCode;
        private final Throwable exception;
        private final ClosedReason reason;

        private ClosingInfo(ClosedReason r1, long r2, CameraError r4, Throwable r5) {
            this.reason = r1;
            this.closingTimestamp = r2;
            this.errorCode = r4;
            this.exception = r5;
        }

        public /* synthetic */ ClosingInfo(ClosedReason r8, long r9, CameraError r11, Throwable r12, int r13, DefaultConstructorMarker r14) {
            if ((r13 & 2) == 0) goto L5;
            Timestamps r92 = Timestamps.INSTANCE;
            r9 = new SystemTimeSource().mo506nowvQl9yQU();
        L5:
            long r2 = r9;
            if ((r13 & 4) == 0) goto L8;
            CameraError r4 = null;
        L10:
            if ((r13 & 8) == 0) goto L12;
            Throwable r5 = null;
        L13:
            this(r8, r2, r4, r5, null);
            return;
        L12:
            r5 = r12;
            goto L13
        L8:
            r4 = r11;
            goto L10
        }

        public /* synthetic */ ClosingInfo(ClosedReason r1, long r2, CameraError r4, Throwable r5, DefaultConstructorMarker r6) {
            this(r1, r2, r4, r5);
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof ClosingInfo) == true) goto L8;
            return false;
        L8:
            ClosingInfo r82 = (ClosingInfo) r8;
            if (this.reason == r82.reason) goto L12;
            return false;
        L12:
            if (TimestampNs.m511equalsimpl0(this.closingTimestamp, r82.closingTimestamp) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.errorCode, r82.errorCode) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.exception, r82.exception) == true) goto L20;
            return false;
        L20:
            return true;
        }

        /* renamed from: getClosingTimestamp-vQl9yQU, reason: not valid java name */
        public final long m418getClosingTimestampvQl9yQU() {
            return this.closingTimestamp;
        }

        /* renamed from: getErrorCode-mVEW8x0, reason: not valid java name */
        public final CameraError m419getErrorCodemVEW8x0() {
            return this.errorCode;
        }

        public final Throwable getException() {
            return this.exception;
        }

        public final ClosedReason getReason() {
            return this.reason;
        }

        public int hashCode() {
            int r0 = ((this.reason.hashCode() * 31) + TimestampNs.m512hashCodeimpl(this.closingTimestamp)) * 31;
            CameraError r1 = this.errorCode;
            int r2 = 0;
            if (r1 != null) goto L5;
            int r12 = 0;
        L6:
            int r02 = (r0 + r12) * 31;
            Throwable r3 = this.exception;
            if (r3 == null) goto L11;
            r2 = r3.hashCode();
        L11:
            return r02 + r2;
        L5:
            r12 = CameraError.m179hashCodeimpl(r1.m182unboximpl());
            goto L6
        }

        public String toString() {
            return "ClosingInfo(reason=" + this.reason + ", closingTimestamp=" + TimestampNs.m513toStringimpl(this.closingTimestamp) + ", errorCode=" + this.errorCode + ", exception=" + this.exception + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Landroidx/camera/camera2/pipe/compat/CameraState;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.AndroidCameraState$awaitClosed$2", f = "VirtualCamera.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.AndroidCameraState$awaitClosed$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CameraState, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> r2) {
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            AnonymousClass2 r0 = new AnonymousClass2(r2);
            r0.L$0 = r1;
            return r0;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CameraState r1, Continuation<? super Boolean> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CameraState r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r2);
            return Boxing.boxBoolean(((CameraState) this.L$0) instanceof CameraStateClosed);
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    private AndroidCameraState(String r1, CameraMetadata r2, int r3, long r4, TimeSource r6, CameraErrorListener r7, Camera2DeviceCloser r8, Camera2Quirks r9, Threads r10, AudioRestrictionController r11, CameraDevice.StateCallback r12, CameraInterop.CaptureSessionListener r13) {
        this.cameraId = r1;
        this.metadata = r2;
        this.attemptNumber = r3;
        this.attemptTimestampNanos = r4;
        this.timeSource = r6;
        this.cameraErrorListener = r7;
        this.camera2DeviceCloser = r8;
        this.camera2Quirks = r9;
        this.threads = r10;
        this.audioRestrictionController = r11;
        this.interopCameraDeviceStateCallback = r12;
        this.interopCaptureSessionListener = r13;
        this.debugId = VirtualCameraKt.getAndroidCameraDebugIds().incrementAndGet();
        this.lock = new Object();
        this.cameraDeviceClosed = new CountDownLatch(1);
        this._state = StateFlowKt.MutableStateFlow(CameraStateUnopened.INSTANCE);
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L5;
        android.util.Log.i("CXCP", "Opening " + CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()));
    L5:
        if (r3 == 1) goto L8;
        Timestamps r14 = Timestamps.INSTANCE;
        r4 = r6.mo506nowvQl9yQU();
    L8:
        this.requestTimestampNanos = r4;
    }

    public /* synthetic */ AndroidCameraState(String r1, CameraMetadata r2, int r3, long r4, TimeSource r6, CameraErrorListener r7, Camera2DeviceCloser r8, Camera2Quirks r9, Threads r10, AudioRestrictionController r11, CameraDevice.StateCallback r12, CameraInterop.CaptureSessionListener r13, DefaultConstructorMarker r14) {
        this(r1, r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public static final /* synthetic */ int access$getAttemptNumber$p(AndroidCameraState r0) {
        return r0.attemptNumber;
    }

    public static final /* synthetic */ long access$getAttemptTimestampNanos$p(AndroidCameraState r2) {
        return r2.attemptTimestampNanos;
    }

    public static final /* synthetic */ long access$getRequestTimestampNanos$p(AndroidCameraState r2) {
        return r2.requestTimestampNanos;
    }

    private final void closeWith(CameraDevice r11, ClosingInfo r12) {
        CameraState r0 = this._state.getValue();
        if ((r0 instanceof CameraStateOpen) == false) goto L5;
        CameraDeviceWrapper r4 = ((CameraStateOpen) r0).getCameraDevice();
    L6:
        Object r1 = this.lock;
        monitor-enter(r1);
    L13:
        th = move-exception;
        throw th;
    L9:
        if (this.pendingClose != null) goto L15;
        this.pendingClose = r12;     // Catch: Throwable -> L13
        if (this.opening == true) goto L15;
    L16:
        monitor-exit(r1);
        if (r12 != null) goto L19;
        return;
    L19:
        if (r12.m419getErrorCodemVEW8x0() != null) goto L21;
    L23:
        this._state.setValue(new CameraStateClosing(r12.m419getErrorCodemVEW8x0(), null));
        if (r12.getReason() == ClosedReason.CAMERA2_CLOSED) goto L38;
        boolean r8 = m416shouldReopenCameraWhenClosing_z0IXec(this.camera2Quirks, this.cameraId, r12.m419getErrorCodemVEW8x0());
        if (r8 == false) goto L37;
        Object r13 = this.lock;
        monitor-enter(r13);
        this.shouldDelayFinalizing = true;     // Catch: Throwable -> L33
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L33
        monitor-exit(r13);
    L33:
        th = move-exception;
        throw th;
    L37:
        AndroidCameraState r6 = this;
        this.camera2DeviceCloser.closeCamera(r4, r11, r6, this.audioRestrictionController, r8, m415shouldCreateEmptyCaptureSessionBeforeClosing_z0IXec(this.camera2Quirks, this.cameraId, r12.m419getErrorCodemVEW8x0()));
    L39:
        r6._state.setValue(r6.computeClosedState(r12));
        return;
    L38:
        r6 = this;
        goto L39
    L21:
        if (r12.getReason() == ClosedReason.CAMERA2_EXCEPTION) goto L23;
        this.cameraErrorListener.mo447onCameraError3M5Xam4(this.cameraId, r12.m419getErrorCodemVEW8x0().m182unboximpl(), false);
    L15:
        r12 = null;
        goto L16
    L5:
        r4 = null;
        goto L6
    }

    /* renamed from: closeWith-8PWMtlg, reason: not valid java name */
    private final void m414closeWith8PWMtlg(Throwable r9, int r10) {
        closeWith(null, new ClosingInfo(ClosedReason.CAMERA2_EXCEPTION, 0, CameraError.m175boximpl(r10), r9, 2, null));
    }

    private final CameraStateClosed computeClosedState(ClosingInfo r20) {
        Timestamps r1 = Timestamps.INSTANCE;
        long r12 = this.timeSource.mo506nowvQl9yQU();
        TimestampNs r3 = this.openTimestampNanos;
        long r4 = r20.m418getClosingTimestampvQl9yQU();
        DurationNs r6 = null;
        if (r3 == null) goto L5;
        DurationNs r122 = DurationNs.m496boximpl(DurationNs.m498constructorimpl(r3.m514unboximpl() - this.attemptTimestampNanos));
    L6:
        if (r3 == null) goto L8;
        DurationNs r14 = DurationNs.m496boximpl(DurationNs.m498constructorimpl(r3.m514unboximpl() - this.requestTimestampNanos));
    L9:
        if (r3 != null) goto L11;
    L10:
        DurationNs r15 = r6;
        long r13 = DurationNs.m498constructorimpl(r12 - r4);
        String r9 = this.cameraId;
        ClosedReason r10 = r20.getReason();
        int r0 = this.attemptNumber - 1;
        CameraError r17 = r20.m419getErrorCodemVEW8x0();
        return new CameraStateClosed(r9, r10, Integer.valueOf(r0), r122, r20.getException(), r14, r15, DurationNs.m496boximpl(r13), r17, null);
    L11:
        r6 = DurationNs.m496boximpl(DurationNs.m498constructorimpl(r4 - r3.m514unboximpl()));
        goto L10
    L8:
        r14 = null;
        goto L9
    L5:
        r122 = null;
        goto L6
    }

    /* renamed from: shouldCreateEmptyCaptureSessionBeforeClosing-_z0IXec, reason: not valid java name */
    private final boolean m415shouldCreateEmptyCaptureSessionBeforeClosing_z0IXec(Camera2Quirks r1, String r2, CameraError r3) {
        if (r1.m460xfcf3eba9(r2) == false) goto L7;
        if (r3 != null) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    /* renamed from: shouldReopenCameraWhenClosing-_z0IXec, reason: not valid java name */
    private final boolean m416shouldReopenCameraWhenClosing_z0IXec(Camera2Quirks r1, String r2, CameraError r3) {
        if (m415shouldCreateEmptyCaptureSessionBeforeClosing_z0IXec(r1, r2, r3) == true) goto L5;
        return false;
    L5:
        if (r1.m459x552c1673(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean awaitCameraDeviceClosed$camera_camera2_pipe(long r2) {
        return this.cameraDeviceClosed.await(r2, TimeUnit.MILLISECONDS);
    }

    public final Object awaitClosed(Continuation<? super Unit> r3) {
        Object r2 = FlowKt.first(getState(), new AnonymousClass2(null), r3);
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r2;
    L6:
        return Unit.INSTANCE;
    }

    public final void close() {
        CameraState r0 = this._state.getValue();
        CameraDevice r2 = null;
        if ((r0 instanceof CameraStateOpen) == false) goto L5;
        CameraDeviceWrapper r02 = ((CameraStateOpen) r0).getCameraDevice();
    L6:
        if (r02 == null) goto L8;
        r2 = (CameraDevice) r02.unwrapAs(Reflection.getOrCreateKotlinClass(CameraDevice.class));
    L8:
        closeWith(r2, new ClosingInfo(ClosedReason.APP_CLOSED, 0, null, null, 14, null));
        return;
    L5:
        r02 = null;
        goto L6
    }

    public final void closeWith$camera_camera2_pipe(Throwable r3) {
        CameraError.Companion r0 = CameraError.Companion;
        int r1 = r0.m185fromPVuDhNw$camera_camera2_pipe(r3);
        if (CameraError.m178equalsimpl0(r1, r0.m198getERROR_UNDETERMINEDv7Vf74A()) == false) goto L5;
        return;
    L5:
        m414closeWith8PWMtlg(r3, r1);
    }

    /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
    public final String m417getCameraIdDz_R5H8() {
        return this.cameraId;
    }

    public final StateFlow<CameraState> getState() {
        return this._state;
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onClosed(CameraDevice r5) {
        if (Intrinsics.areEqual(r5.getId(), this.cameraId) == false) goto L25;
        Log r0 = Log.INSTANCE;
        if (r0.getDEBUG_LOGGABLE() == false) goto L7;
        android.util.Log.d("CXCP", CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + ": onClosed");
    L7:
        this.cameraDeviceClosed.countDown();
        Object r1 = this.lock;
        monitor-enter(r1);
    L15:
        th = move-exception;
        throw th;
    L10:
        if (this.shouldDelayFinalizing == true) goto L12;
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r1);
        onFinalized$camera_camera2_pipe(r5);
        return;
    L12:
        if (r0.getINFO_LOGGABLE() == false) goto L17;
        android.util.Log.i("CXCP", this + "#onClosed: Delaying finalizing.");     // Catch: Throwable -> L15
    L17:
        monitor-exit(r1);
        return;
    L25:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onDisconnected(CameraDevice r10) {
        if (Intrinsics.areEqual(r10.getId(), this.cameraId) == false) goto L12;
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + "#onDisconnected");
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L7;
        android.util.Log.d("CXCP", CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + ": onDisconnected");
    L7:
        this.cameraDeviceClosed.countDown();
        closeWith(r10, new ClosingInfo(ClosedReason.CAMERA2_DISCONNECTED, 0, CameraError.m175boximpl(CameraError.Companion.m188getERROR_CAMERA_DISCONNECTEDv7Vf74A()), null, 10, null));
        CameraDevice.StateCallback r9 = this.interopCameraDeviceStateCallback;
        if (r9 == null) goto L10;
        r9.onDisconnected(r10);
    L10:
        Trace.endSection();
        return;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onError(CameraDevice r10, int r11) {
        if (Intrinsics.areEqual(r10.getId(), this.cameraId) == false) goto L12;
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + "#onError-" + r11);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L7;
        android.util.Log.d("CXCP", CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + ": onError " + r11);
    L7:
        this.cameraDeviceClosed.countDown();
        closeWith(r10, new ClosingInfo(ClosedReason.CAMERA2_ERROR, 0, CameraError.m175boximpl(CameraError.Companion.m183fromPVuDhNw$camera_camera2_pipe(r11)), null, 10, null));
        CameraDevice.StateCallback r9 = this.interopCameraDeviceStateCallback;
        if (r9 == null) goto L10;
        r9.onError(r10, r11);
    L10:
        Trace.endSection();
        return;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    public final void onFinalized$camera_camera2_pipe(CameraDevice r11) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + "#onFinalized");
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + ": onFinalized");
    L5:
        closeWith(r11, new ClosingInfo(ClosedReason.CAMERA2_CLOSED, 0, null, null, 14, null));
        CameraDevice.StateCallback r10 = this.interopCameraDeviceStateCallback;
        if (r10 == null) goto L8;
        r10.onClosed(r11);
    L8:
        Trace.endSection();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onOpened(CameraDevice r14) {
        if (Intrinsics.areEqual(r14.getId(), this.cameraId) == false) goto L40;
        Timestamps r0 = Timestamps.INSTANCE;
        long r02 = this.timeSource.mo506nowvQl9yQU();
        this.openTimestampNanos = TimestampNs.m508boximpl(r02);
        Debug r2 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + "#onOpened");
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L11;
        long r6 = DurationNs.m498constructorimpl(r02 - access$getRequestTimestampNanos$p(this));
        long r03 = DurationNs.m498constructorimpl(r02 - access$getAttemptTimestampNanos$p(this));
        if (access$getAttemptNumber$p(this) != 1) goto L9;
        String r04 = "Opened " + CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + " in " + String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r6 / 1000000.0d)}, 1));
    L10:
        android.util.Log.i("CXCP", r04);
        goto L11
    L9:
        r04 = "Opened " + CameraId.m232toStringimpl(m417getCameraIdDz_R5H8()) + " in " + String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r6 / 1000000.0d)}, 1)) + " (" + String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r03 / 1000000.0d)}, 1)) + " total) after " + access$getAttemptNumber$p(this) + " attempts.";
    L11:
        Object r1 = this.lock;
        monitor-enter(r1);
        ClosingInfo r05 = this.pendingClose;     // Catch: Throwable -> L17
        if (r05 != null) goto L19;
        this.opening = true;     // Catch: Throwable -> L17
    L19:
        monitor-exit(r1);
        CameraDevice.StateCallback r12 = this.interopCameraDeviceStateCallback;
        if (r12 == null) goto L23;
        r12.onOpened(r14);
    L23:
        if (r05 == null) goto L26;
        Camera2DeviceCloser.closeCamera$default(this.camera2DeviceCloser, null, r14, this, this.audioRestrictionController, m416shouldReopenCameraWhenClosing_z0IXec(this.camera2Quirks, this.cameraId, r05.m419getErrorCodemVEW8x0()), m415shouldCreateEmptyCaptureSessionBeforeClosing_z0IXec(this.camera2Quirks, this.cameraId, r05.m419getErrorCodemVEW8x0()), 1, null);
        return;
    L26:
        AndroidCameraDevice r13 = new AndroidCameraDevice(this.metadata, r14, this.cameraId, this.cameraErrorListener, this.interopCaptureSessionListener, this.threads, null);
        this.audioRestrictionController.addListener(r13);
        this._state.setValue(new CameraStateOpen(r13));
        Object r22 = this.lock;
        monitor-enter(r22);
        this.opening = false;     // Catch: Throwable -> L35
        ClosingInfo r7 = this.pendingClose;     // Catch: Throwable -> L35
        monitor-exit(r22);
        if (r7 == null) goto L33;
        this._state.setValue(new CameraStateClosing(r7.m419getErrorCodemVEW8x0(), null));
        this.camera2DeviceCloser.closeCamera(r13, r14, this, this.audioRestrictionController, m416shouldReopenCameraWhenClosing_z0IXec(this.camera2Quirks, this.cameraId, r7.m419getErrorCodemVEW8x0()), m415shouldCreateEmptyCaptureSessionBeforeClosing_z0IXec(this.camera2Quirks, this.cameraId, r7.m419getErrorCodemVEW8x0()));
        this._state.setValue(computeClosedState(r7));
    L33:
        Trace.endSection();
        return;
    L35:
        th = move-exception;
        throw th;
    L17:
        th = move-exception;
        throw th;
    L40:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    public String toString() {
        return "CameraState-" + this.debugId;
    }
}
