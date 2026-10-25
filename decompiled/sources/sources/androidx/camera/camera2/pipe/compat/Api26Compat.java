package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.params.OutputConfiguration;
import android.util.Size;
import android.view.Surface;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\u0010\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Api26Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/hardware/camera2/CameraCaptureSession;", "cameraCaptureSession", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/OutputConfiguration;", "outputConfiguration", _UrlKt.FRAGMENT_ENCODE_SET, "finalizeOutputConfigurations", "(Landroid/hardware/camera2/CameraCaptureSession;Ljava/util/List;)V", "Landroid/util/Size;", "size", "Ljava/lang/Class;", "klass", "newOutputConfiguration", "(Landroid/util/Size;Ljava/lang/Class;)Landroid/hardware/camera2/params/OutputConfiguration;", "outputConfig", "enableSurfaceSharing", "(Landroid/hardware/camera2/params/OutputConfiguration;)V", "Landroid/view/Surface;", "surface", "addSurfaces", "(Landroid/hardware/camera2/params/OutputConfiguration;Landroid/view/Surface;)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api26Compat {
    public static final Api26Compat INSTANCE = null;

    static {
        INSTANCE = new Api26Compat();
    }

    private Api26Compat() {
    }

    @JvmStatic
    public static final void addSurfaces(OutputConfiguration r0, Surface r1) {
        r0.addSurface(r1);
    }

    @JvmStatic
    public static final void enableSurfaceSharing(OutputConfiguration r0) {
        r0.enableSurfaceSharing();
    }

    @JvmStatic
    public static final void finalizeOutputConfigurations(CameraCaptureSession r0, List<OutputConfiguration> r1) {
        r0.finalizeOutputConfigurations(r1);
    }

    @JvmStatic
    public static final OutputConfiguration newOutputConfiguration(Size r0, Class<?> r1) {
        return Api26Compat$$ExternalSyntheticApiModelOutline0.m(r0, r1);
    }
}
