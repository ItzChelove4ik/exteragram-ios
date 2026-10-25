package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.util.Size;
import androidx.camera.camera2.pipe.CameraMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\u000bJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u001c\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Api35Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "isTorchStrengthSupported", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "getDefaultTorchStrengthLevel", "(Landroidx/camera/camera2/pipe/CameraMetadata;)I", "getMaxTorchStrengthLevel", "format", "Landroid/util/Size;", "surfaceSize", "Landroid/hardware/camera2/params/OutputConfiguration;", "newImageReaderOutputConfiguration", "(ILandroid/util/Size;)Landroid/hardware/camera2/params/OutputConfiguration;", "sessionType", _UrlKt.FRAGMENT_ENCODE_SET, "outputs", "Landroid/hardware/camera2/params/SessionConfiguration;", "newSessionConfiguration", "(ILjava/util/List;)Landroid/hardware/camera2/params/SessionConfiguration;", "Landroid/hardware/camera2/CameraCharacteristics;", "cameraCharacteristics", "Landroid/hardware/camera2/CameraCharacteristics$Key;", "getAvailableSessionCharacteristicsKeys", "(Landroid/hardware/camera2/CameraCharacteristics;)Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api35Compat {
    public static final Api35Compat INSTANCE = null;

    static {
        INSTANCE = new Api35Compat();
    }

    private Api35Compat() {
    }

    @JvmStatic
    public static final List<CameraCharacteristics.Key<?>> getAvailableSessionCharacteristicsKeys(CameraCharacteristics r0) {
        return r0.getAvailableSessionCharacteristicsKeys();
    }

    @JvmStatic
    public static final int getDefaultTorchStrengthLevel(CameraMetadata r1) {
        Integer r12 = (Integer) r1.get(Api35Compat$$ExternalSyntheticApiModelOutline2.m());
        if (r12 != null) goto L5;
        return 1;
    L5:
        return r12.intValue();
    }

    @JvmStatic
    public static final int getMaxTorchStrengthLevel(CameraMetadata r1) {
        Integer r12 = (Integer) r1.get(Api35Compat$$ExternalSyntheticApiModelOutline3.m());
        if (r12 != null) goto L5;
        return 1;
    L5:
        return r12.intValue();
    }

    @JvmStatic
    public static final boolean isTorchStrengthSupported(CameraMetadata r1) {
        Integer r12 = (Integer) r1.get(Api35Compat$$ExternalSyntheticApiModelOutline3.m());
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.intValue() <= 1) goto L9;
        return true;
    L9:
        return false;
    }

    @JvmStatic
    public static final OutputConfiguration newImageReaderOutputConfiguration(int r0, Size r1) {
        return Api35Compat$$ExternalSyntheticApiModelOutline0.m(r0, r1);
    }

    @JvmStatic
    public static final SessionConfiguration newSessionConfiguration(int r0, List<OutputConfiguration> r1) {
        return Api35Compat$$ExternalSyntheticApiModelOutline1.m(r0, r1);
    }
}
