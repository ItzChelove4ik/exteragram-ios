package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Size;
import androidx.camera.core.impl.Quirk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExtraSupportedOutputSizeQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "format", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "getExtraSupportedResolutions", "(I)[Landroid/util/Size;", "getMotoE5PlayExtraSupportedResolutions", "()[Landroid/util/Size;", "motoE5PlayExtraSupportedResolutions", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class ExtraSupportedOutputSizeQuirk implements Quirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005R\u0014\u0010\u0006\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExtraSupportedOutputSizeQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isMotoE5Play", "isMotoE5Play$camera_camera2", "()Z", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled() {
            return isMotoE5Play$camera_camera2();
        }

        public final boolean isMotoE5Play$camera_camera2() {
            if (Device.INSTANCE.isMotorolaDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("moto e5 play", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public ExtraSupportedOutputSizeQuirk() {
    }

    private final Size[] getMotoE5PlayExtraSupportedResolutions() {
        return new Size[]{new Size(1440, 1080), new Size(960, 720)};
    }

    public final Size[] getExtraSupportedResolutions(int r2) {
        if (r2 != 34) goto L9;
        if (Companion.isMotoE5Play$camera_camera2() == false) goto L9;
        return getMotoE5PlayExtraSupportedResolutions();
    L9:
        return new Size[0];
    }
}
