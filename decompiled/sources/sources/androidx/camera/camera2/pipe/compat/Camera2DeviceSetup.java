package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2DeviceSetup;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceSetupWrapper;", "cameraDeviceSetup", "Landroid/hardware/camera2/CameraDevice$CameraDeviceSetup;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "cameraErrorListener", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "<init>", "(Landroid/hardware/camera2/CameraDevice$CameraDeviceSetup;Ljava/lang/String;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Ljava/lang/String;", "createCaptureRequest", "Landroid/hardware/camera2/CaptureRequest$Builder;", "templateType", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2DeviceSetupWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceSetupWrapper.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceSetup\n+ 2 Exceptions.kt\nandroidx/camera/camera2/pipe/compat/ExceptionsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,48:1\n53#2,6:49\n59#2,24:57\n83#2,3:83\n71#3,2:55\n50#3,2:81\n*S KotlinDebug\n*F\n+ 1 Camera2DeviceSetupWrapper.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceSetup\n*L\n44#1:49,6\n44#1:57,24\n44#1:83,3\n44#1:55,2\n44#1:81,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2DeviceSetup implements Camera2DeviceSetupWrapper {
    private final CameraDevice.CameraDeviceSetup cameraDeviceSetup;
    private final CameraErrorListener cameraErrorListener;
    private final String cameraId;

    private Camera2DeviceSetup(CameraDevice.CameraDeviceSetup r1, String r2, CameraErrorListener r3) {
        this.cameraDeviceSetup = r1;
        this.cameraId = r2;
        this.cameraErrorListener = r3;
    }

    public /* synthetic */ Camera2DeviceSetup(CameraDevice.CameraDeviceSetup r1, String r2, CameraErrorListener r3, DefaultConstructorMarker r4) {
        this(r1, r2, r3);
    }

    public static final /* synthetic */ CameraDevice.CameraDeviceSetup access$getCameraDeviceSetup$p(Camera2DeviceSetup r0) {
        return r0.cameraDeviceSetup;
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2DeviceSetupWrapper
    public CaptureRequest.Builder createCaptureRequest(int r5) {
        String r0 = this.cameraId;
        CameraErrorListener r1 = this.cameraErrorListener;
        return access$getCameraDeviceSetup$p(this).createCaptureRequest(r5);
    L5:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L13;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());
    L11:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);
        return null;
    L13:
        if ((e instanceof IllegalArgumentException) == true) goto L28;
        if ((e instanceof SecurityException) == true) goto L28;
        if ((e instanceof UnsupportedOperationException) == true) goto L28;
        if ((e instanceof NullPointerException) == true) goto L28;
        if ((e instanceof IllegalStateException) == true) goto L24;
        throw e;
    L24:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L36;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");
        return null;
    L36:
        return null;
    L28:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L30;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());
    L30:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);
        return null;
    }
}
