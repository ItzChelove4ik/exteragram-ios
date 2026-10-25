package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.os.Trace;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B)\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J'\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidCameraConstrainedHighSpeedCaptureSession;", "Landroidx/camera/camera2/pipe/compat/AndroidCameraCaptureSession;", "Landroidx/camera/camera2/pipe/compat/CameraConstrainedHighSpeedCaptureSessionWrapper;", "device", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "session", "Landroid/hardware/camera2/CameraConstrainedHighSpeedCaptureSession;", "cameraErrorListener", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "callbackHandler", "Landroid/os/Handler;", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;Landroid/hardware/camera2/CameraConstrainedHighSpeedCaptureSession;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Landroid/os/Handler;)V", "createHighSpeedRequestList", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest;", PluginsConstants.REQUEST, "unwrapAs", "T", _UrlKt.FRAGMENT_ENCODE_SET, "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraConstrainedHighSpeedCaptureSession\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,401:1\n48#2,2:402\n71#2,4:404\n50#2,3:408\n78#2,4:411\n71#3,2:415\n71#3,2:417\n71#3,2:419\n*S KotlinDebug\n*F\n+ 1 CaptureSessionWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraConstrainedHighSpeedCaptureSession\n*L\n359#1:402,2\n359#1:404,4\n359#1:408,3\n359#1:411,4\n368#1:415,2\n376#1:417,2\n387#1:419,2\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidCameraConstrainedHighSpeedCaptureSession extends AndroidCameraCaptureSession implements CameraConstrainedHighSpeedCaptureSessionWrapper {
    private final CameraConstrainedHighSpeedCaptureSession session;

    public AndroidCameraConstrainedHighSpeedCaptureSession(CameraDeviceWrapper r1, CameraConstrainedHighSpeedCaptureSession r2, CameraErrorListener r3, Handler r4) {
        super(r1, r2, r3, r4);
        this.session = r2;
    }

    public static final /* synthetic */ CameraConstrainedHighSpeedCaptureSession access$getSession$p(AndroidCameraConstrainedHighSpeedCaptureSession r0) {
        return r0.session;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraConstrainedHighSpeedCaptureSessionWrapper
    public List<CaptureRequest> createHighSpeedRequestList(CaptureRequest r4) {
        Debug r2 = Debug.INSTANCE;     // Catch: UnsupportedOperationException -> L10 IllegalArgumentException -> L13 IllegalStateException -> L16
        Trace.beginSection("CXCP#createHighSpeedRequestList");     // Catch: Throwable -> L7
        List<CaptureRequest> r42 = access$getSession$p(this).createHighSpeedRequestList(r4);     // Catch: Throwable -> L7
        Trace.endSection();     // Catch: UnsupportedOperationException -> L10 IllegalArgumentException -> L13 IllegalStateException -> L16
        return r42;
    L7:
        th = move-exception;
        Trace.endSection();     // Catch: UnsupportedOperationException -> L10 IllegalArgumentException -> L13 IllegalStateException -> L16
        throw th;     // Catch: UnsupportedOperationException -> L10 IllegalArgumentException -> L13 IllegalStateException -> L16
    L14:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L26;
        android.util.Log.w("CXCP", "Failed to createHighSpeedRequestList from " + getDevice() + " because the output surface was destroyed before calling createHighSpeedRequestList.");
        return null;
    L26:
        return null;
    L17:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L28;
        android.util.Log.w("CXCP", "Failed to createHighSpeedRequestList. " + getDevice() + " may be closed.");
        return null;
    L28:
        return null;
    L11:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L24;
        android.util.Log.w("CXCP", "Failed to createHighSpeedRequestList from " + getDevice() + " because the output surface was not available.");
        return null;
    L24:
        return null;
    }

    @Override // androidx.camera.camera2.pipe.compat.AndroidCameraCaptureSession, androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CameraConstrainedHighSpeedCaptureSession.class)) == false) goto L7;
        return (T) this.session;
    L7:
        return (T) super.unwrapAs(r2);
    }
}
