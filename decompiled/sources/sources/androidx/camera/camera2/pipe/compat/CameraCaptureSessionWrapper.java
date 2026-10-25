package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import androidx.camera.camera2.pipe.UnsafeWrapper;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001%J\u000f\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\f\u0010\rJ'\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u0012\u0010\u0011J!\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u0013\u0010\rJ\u000f\u0010\u0014\u001a\u00020\u0004H&¢\u0006\u0004\b\u0014\u0010\u0006J\u001d\u0010\u0017\u001a\u00020\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eH&¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010 \u001a\u00020\u001d8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010$\u001a\u0004\u0018\u00010!8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006&À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", _UrlKt.FRAGMENT_ENCODE_SET, "abortCaptures", "()Z", "Landroid/hardware/camera2/CaptureRequest;", PluginsConstants.REQUEST, "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "listener", _UrlKt.FRAGMENT_ENCODE_SET, "capture", "(Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;)Ljava/lang/Integer;", _UrlKt.FRAGMENT_ENCODE_SET, "requests", "captureBurst", "(Ljava/util/List;Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;)Ljava/lang/Integer;", "setRepeatingBurst", "setRepeatingRequest", "stopRepeating", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "outputConfigs", "finalizeOutputConfigurations", "(Ljava/util/List;)Z", "Landroidx/camera/camera2/pipe/CameraInterop$CameraCaptureSessionId;", "getId-159jkk4", "()I", "id", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "getDevice", "()Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "device", "Landroid/view/Surface;", "getInputSurface", "()Landroid/view/Surface;", "inputSurface", "StateCallback", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraCaptureSessionWrapper extends UnsafeWrapper, AutoCloseable {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "Landroidx/camera/camera2/pipe/compat/SessionStateCallback;", "onActive", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "onClosed", "onConfigureFailed", "onConfigured", "onReady", "onCaptureQueueEmpty", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface StateCallback extends SessionStateCallback {
        void onActive(CameraCaptureSessionWrapper r1);

        void onCaptureQueueEmpty(CameraCaptureSessionWrapper r1);

        void onClosed(CameraCaptureSessionWrapper r1);

        void onConfigureFailed(CameraCaptureSessionWrapper r1);

        void onConfigured(CameraCaptureSessionWrapper r1);

        void onReady(CameraCaptureSessionWrapper r1);
    }

    boolean abortCaptures();

    Integer capture(CaptureRequest r1, CameraCaptureSession.CaptureCallback r2);

    Integer captureBurst(List<CaptureRequest> r1, CameraCaptureSession.CaptureCallback r2);

    boolean finalizeOutputConfigurations(List<? extends OutputConfigurationWrapper> r1);

    CameraDeviceWrapper getDevice();

    /* renamed from: getId-159jkk4 */
    int mo410getId159jkk4();

    Surface getInputSurface();

    Integer setRepeatingBurst(List<CaptureRequest> r1, CameraCaptureSession.CaptureCallback r2);

    Integer setRepeatingRequest(CaptureRequest r1, CameraCaptureSession.CaptureCallback r2);

    boolean stopRepeating();
}
