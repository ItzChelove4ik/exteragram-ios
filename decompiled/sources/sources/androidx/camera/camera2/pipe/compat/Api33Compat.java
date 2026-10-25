package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraExtensionCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.params.OutputConfiguration;
import androidx.camera.camera2.pipe.CameraMetadata;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0010\u0010\nJ+\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00150\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0016\u0010\u0017J+\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00180\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0019\u0010\u0017J\u0015\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Api33Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/hardware/camera2/params/OutputConfiguration;", "outputConfig", _UrlKt.FRAGMENT_ENCODE_SET, "dynamicRangeProfile", _UrlKt.FRAGMENT_ENCODE_SET, "setDynamicRangeProfile", "(Landroid/hardware/camera2/params/OutputConfiguration;J)V", _UrlKt.FRAGMENT_ENCODE_SET, "mirrorMode", "setMirrorMode", "(Landroid/hardware/camera2/params/OutputConfiguration;I)V", "streamUseCase", "setStreamUseCase", "Landroid/hardware/camera2/CameraExtensionCharacteristics;", "extensionCharacteristics", "extension", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "getAvailableCaptureRequestKeys", "(Landroid/hardware/camera2/CameraExtensionCharacteristics;I)Ljava/util/Set;", "Landroid/hardware/camera2/CaptureResult$Key;", "getAvailableCaptureResultKeys", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "supportsPreviewStabilization", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api33Compat {
    public static final Api33Compat INSTANCE = null;

    static {
        INSTANCE = new Api33Compat();
    }

    private Api33Compat() {
    }

    @JvmStatic
    public static final Set<CaptureRequest.Key<Object>> getAvailableCaptureRequestKeys(CameraExtensionCharacteristics r0, int r1) {
        return r0.getAvailableCaptureRequestKeys(r1);
    }

    @JvmStatic
    public static final Set<CaptureResult.Key<Object>> getAvailableCaptureResultKeys(CameraExtensionCharacteristics r0, int r1) {
        return r0.getAvailableCaptureResultKeys(r1);
    }

    @JvmStatic
    public static final void setDynamicRangeProfile(OutputConfiguration r0, long r1) {
        r0.setDynamicRangeProfile(r1);
    }

    @JvmStatic
    public static final void setMirrorMode(OutputConfiguration r0, int r1) {
        r0.setMirrorMode(r1);
    }

    @JvmStatic
    public static final void setStreamUseCase(OutputConfiguration r0, long r1) {
        r0.setStreamUseCase(r1);
    }

    public final boolean supportsPreviewStabilization(CameraMetadata r1) {
        return ArraysKt.contains(CameraMetadata.Companion.getAvailableVideoStabilizationModes(r1), 2);
    }
}
