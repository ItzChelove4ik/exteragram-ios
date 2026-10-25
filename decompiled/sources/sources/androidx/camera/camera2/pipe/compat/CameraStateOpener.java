package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraDevice;
import androidx.camera.camera2.pipe.CameraInterop;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.TimeSource;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 %2\u00020\u0001:\u0001%BC\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J8\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0080@¢\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0014H\u0000¢\u0006\u0002\b$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraStateOpener;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraOpener", "Landroidx/camera/camera2/pipe/compat/CameraOpener;", "camera2MetadataProvider", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;", "cameraErrorListener", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "camera2Quirks", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "timeSource", "Landroidx/camera/camera2/pipe/core/TimeSource;", "cameraInteropConfig", "Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraOpener;Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Landroidx/camera/camera2/pipe/compat/Camera2Quirks;Landroidx/camera/camera2/pipe/core/TimeSource;Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;Landroidx/camera/camera2/pipe/core/Threads;)V", "cameraOpenCancelled", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "tryOpenCamera", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "attempts", _UrlKt.FRAGMENT_ENCODE_SET, "requestTimestamp", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "camera2DeviceCloser", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "audioRestrictionController", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "tryOpenCamera-7pD7j80$camera_camera2_pipe", "(Ljava/lang/String;IJLandroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelOpen", "cancelOpen$camera_camera2_pipe", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraStateOpener {
    private static final Companion Companion = null;
    private final Camera2MetadataProvider camera2MetadataProvider;
    private final Camera2Quirks camera2Quirks;
    private final CameraErrorListener cameraErrorListener;
    private final CameraPipe.CameraInteropConfig cameraInteropConfig;
    private CompletableDeferred<Unit> cameraOpenCancelled;
    private final CameraOpener cameraOpener;
    private final Threads threads;
    private final TimeSource timeSource;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraStateOpener$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CAMERA_OPEN_TIMEOUT_MS", _UrlKt.FRAGMENT_ENCODE_SET, "CAMERA_OPEN_CANCEL_TIMEOUT_MS", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CameraStateOpener(CameraOpener r1, Camera2MetadataProvider r2, CameraErrorListener r3, Camera2Quirks r4, TimeSource r5, CameraPipe.CameraInteropConfig r6, Threads r7) {
        this.cameraOpener = r1;
        this.camera2MetadataProvider = r2;
        this.cameraErrorListener = r3;
        this.camera2Quirks = r4;
        this.timeSource = r5;
        this.cameraInteropConfig = r6;
        this.threads = r7;
        this.cameraOpenCancelled = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    public static final /* synthetic */ CompletableDeferred access$getCameraOpenCancelled$p(CameraStateOpener r0) {
        return r0.cameraOpenCancelled;
    }

    public static final /* synthetic */ CameraOpener access$getCameraOpener$p(CameraStateOpener r0) {
        return r0.cameraOpener;
    }

    public final void cancelOpen$camera_camera2_pipe() {
        this.cameraOpenCancelled.complete(Unit.INSTANCE);
    }

    /* renamed from: tryOpenCamera-7pD7j80$camera_camera2_pipe, reason: not valid java name */
    public final Object m464tryOpenCamera7pD7j80$camera_camera2_pipe(String r25, int r26, long r27, Camera2DeviceCloser r29, AudioRestrictionController r30, Continuation<? super OpenCameraResult> r31) {
        if ((r31 instanceof CameraStateOpener$tryOpenCamera$1) == false) goto L7;
        CameraStateOpener$tryOpenCamera$1 r3 = (CameraStateOpener$tryOpenCamera$1) r31;
        int r4 = r3.label;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L7;
        r3.label = r4 - Integer.MIN_VALUE;
    L8:
        Object r2 = r3.result;
        Object r42 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r5 = r3.label;
        if (r5 == 0) goto L17;
        if (r5 == 1) goto L16;
        if (r5 != 2) goto L14;
        ResultKt.throwOnFailure(r2);
        return r2;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        long r9 = r3.J$0;
        int r1 = r3.I$0;
        AudioRestrictionController r52 = (AudioRestrictionController) r3.L$2;
        Camera2DeviceCloser r7 = (Camera2DeviceCloser) r3.L$1;
        String r11 = (String) r3.L$0;
        ResultKt.throwOnFailure(r2);
        int r12 = r1;
        AudioRestrictionController r20 = r52;
        Camera2DeviceCloser r17 = r7;
        long r13 = r9;
        String r10 = r11;
    L21:
        CameraMetadata r112 = (CameraMetadata) r2;
        TimeSource r15 = this.timeSource;
        CameraErrorListener r14 = this.cameraErrorListener;
        Camera2Quirks r22 = this.camera2Quirks;
        Threads r53 = this.threads;
        CameraPipe.CameraInteropConfig r72 = this.cameraInteropConfig;
        if (r72 == null) goto L24;
        CameraDevice.StateCallback r21 = r72.getCameraDeviceStateCallback();
    L25:
        CameraPipe.CameraInteropConfig r73 = this.cameraInteropConfig;
        if (r73 == null) goto L28;
        CameraInterop.CaptureSessionListener r222 = r73.getCameraCaptureSessionListener();
    L29:
        CameraStateOpener$tryOpenCamera$2 r16 = new CameraStateOpener$tryOpenCamera$2(this, r10, new AndroidCameraState(r10, r112, r12, r13, r15, r14, r17, r22, r53, r20, r21, r222, null), null);
        r3.L$0 = null;
        r3.L$1 = null;
        r3.L$2 = null;
        r3.label = 2;
        Object r0 = SupervisorKt.supervisorScope(r16, r3);
        if (r0 != r42) goto L32;
    L31:
        return r42;
    L32:
        return r0;
    L28:
        r222 = null;
        goto L29
    L24:
        r21 = null;
        goto L25
    L17:
        ResultKt.throwOnFailure(r2);
        Camera2MetadataProvider r23 = this.camera2MetadataProvider;
        r3.L$0 = r25;
        r3.L$1 = r29;
        r3.L$2 = r30;
        r3.I$0 = r26;
        r3.J$0 = r27;
        r3.label = 1;
        r2 = r23.mo457getCameraMetadata0r8Bogc(r25, r3);
        if (r2 == r42) goto L31;
        r17 = r29;
        r20 = r30;
        r13 = r27;
        r12 = r26;
        r10 = r25;
    L7:
        r3 = new CameraStateOpener$tryOpenCamera$1(this, r31);
        goto L8
    }
}
