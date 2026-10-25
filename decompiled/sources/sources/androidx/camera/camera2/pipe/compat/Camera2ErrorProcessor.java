package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.GraphState;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\tH\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2ErrorProcessor;", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "<init>", "()V", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "virtualCameraStateMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "Landroidx/camera/camera2/pipe/compat/VirtualCameraState;", "onCameraError", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", "cameraError", "Landroidx/camera/camera2/pipe/CameraError;", "willAttemptRetry", _UrlKt.FRAGMENT_ENCODE_SET, "onCameraError-3M5Xam4", "(Ljava/lang/String;IZ)V", "setActiveVirtualCamera", "virtualCameraState", "setActiveVirtualCamera-0r8Bogc$camera_camera2_pipe", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/compat/VirtualCameraState;)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2ErrorProcessor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2ErrorProcessor.kt\nandroidx/camera/camera2/pipe/compat/Camera2ErrorProcessor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,60:1\n1#2:61\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2ErrorProcessor implements CameraErrorListener {
    private final Object lock;
    private final Map<CameraId, VirtualCameraState> virtualCameraStateMap;

    public Camera2ErrorProcessor() {
        this.lock = new Object();
        this.virtualCameraStateMap = new LinkedHashMap();
    }

    @Override // androidx.camera.camera2.pipe.internal.CameraErrorListener
    /* renamed from: onCameraError-3M5Xam4, reason: not valid java name */
    public void mo447onCameraError3M5Xam4(String r2, int r3, boolean r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        VirtualCameraState r1 = this.virtualCameraStateMap.get(CameraId.m227boximpl(r2));     // Catch: Throwable -> L10
        monitor-exit(r0);
        if (r1 != null) goto L8;
        return;
    L8:
        r1.getGraphListener().onGraphError(new GraphState.GraphStateError(r3, r4, null));
        return;
    L10:
        th = move-exception;
        throw th;
    }

    /* renamed from: setActiveVirtualCamera-0r8Bogc$camera_camera2_pipe, reason: not valid java name */
    public final void m448setActiveVirtualCamera0r8Bogc$camera_camera2_pipe(String r2, VirtualCameraState r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.virtualCameraStateMap.put(CameraId.m227boximpl(r2), r3);     // Catch: Throwable -> L7
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
