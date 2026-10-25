package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.UnsafeWrapper;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u00012\u00020\u00022\u00060\u0003j\u0002`\u0004:\u0001\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper;", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "StateCallback", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraExtensionSessionWrapper extends CameraCaptureSessionWrapper, UnsafeWrapper, AutoCloseable {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper$StateCallback;", "Landroidx/camera/camera2/pipe/compat/SessionStateCallback;", "onClosed", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper;", "onConfigureFailed", "onConfigured", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface StateCallback extends SessionStateCallback {
        void onClosed(CameraExtensionSessionWrapper r1);

        void onConfigureFailed(CameraExtensionSessionWrapper r1);

        void onConfigured(CameraExtensionSessionWrapper r1);
    }
}
