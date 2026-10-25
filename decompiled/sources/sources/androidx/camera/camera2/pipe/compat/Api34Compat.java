package androidx.camera.camera2.pipe.compat;

import android.graphics.ColorSpace;
import android.hardware.camera2.CameraExtensionCharacteristics;
import android.hardware.camera2.params.ExtensionSessionConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import androidx.camera.camera2.pipe.CameraMetadata;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u000b\u0010\nJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Api34Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/hardware/camera2/CameraExtensionCharacteristics;", "extensionCharacteristics", _UrlKt.FRAGMENT_ENCODE_SET, "extension", _UrlKt.FRAGMENT_ENCODE_SET, "isPostviewAvailable", "(Landroid/hardware/camera2/CameraExtensionCharacteristics;I)Z", "isCaptureProcessProgressAvailable", "Landroid/hardware/camera2/params/ExtensionSessionConfiguration;", "extensionSessionConfiguration", "Landroid/hardware/camera2/params/OutputConfiguration;", "postviewOutputConfiguration", _UrlKt.FRAGMENT_ENCODE_SET, "setPostviewOutputConfiguration", "(Landroid/hardware/camera2/params/ExtensionSessionConfiguration;Landroid/hardware/camera2/params/OutputConfiguration;)V", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "isZoomOverrideSupported", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Z", "Landroid/hardware/camera2/params/SessionConfiguration;", "sessionConfiguration", "Landroid/graphics/ColorSpace$Named;", "colorSpace", "setColorSpace", "(Landroid/hardware/camera2/params/SessionConfiguration;Landroid/graphics/ColorSpace$Named;)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api34Compat {
    public static final Api34Compat INSTANCE = null;

    static {
        INSTANCE = new Api34Compat();
    }

    private Api34Compat() {
    }

    @JvmStatic
    public static final boolean isCaptureProcessProgressAvailable(CameraExtensionCharacteristics r0, int r1) {
        return r0.isCaptureProcessProgressAvailable(r1);
    }

    @JvmStatic
    public static final boolean isPostviewAvailable(CameraExtensionCharacteristics r0, int r1) {
        return r0.isPostviewAvailable(r1);
    }

    @JvmStatic
    public static final boolean isZoomOverrideSupported(CameraMetadata r2) {
        int[] r22 = (int[]) r2.get(Api34Compat$$ExternalSyntheticApiModelOutline0.m());
        if (r22 != null) goto L5;
    L7:
        return false;
    L5:
        if (ArraysKt.contains(r22, 1) != true) goto L7;
        return true;
    }

    @JvmStatic
    public static final void setColorSpace(SessionConfiguration r0, ColorSpace.Named r1) {
        r0.setColorSpace(r1);
    }

    @JvmStatic
    public static final void setPostviewOutputConfiguration(ExtensionSessionConfiguration r0, OutputConfiguration r1) {
        r0.setPostviewOutputConfiguration(r1);
    }
}
