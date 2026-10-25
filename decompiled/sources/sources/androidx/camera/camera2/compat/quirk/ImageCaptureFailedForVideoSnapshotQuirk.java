package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ImageCaptureFailedForVideoSnapshotQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class ImageCaptureFailedForVideoSnapshotQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final Set<String> PROBLEMATIC_UNI_SOC_MODELS = null;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\u0005H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ImageCaptureFailedForVideoSnapshotQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "PROBLEMATIC_UNI_SOC_MODELS", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isProblematicUniSocChipsetDevice", "isHuaweiPSmart", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isHuaweiPSmart() {
            if (Device.INSTANCE.isHuaweiDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("FIG-LX1", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isProblematicUniSocChipsetDevice() {
            return ImageCaptureFailedForVideoSnapshotQuirk.access$getPROBLEMATIC_UNI_SOC_MODELS$cp().contains(Build.MODEL.toLowerCase(Locale.ROOT));
        }

        public final boolean isEnabled() {
            if (isProblematicUniSocChipsetDevice() == false) goto L5;
            return true;
        L5:
            if (Device.INSTANCE.isUniSocChipsetDevice() == false) goto L7;
            return true;
        L7:
            if (isHuaweiPSmart() == true) goto L14;
            return false;
        L14:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
        PROBLEMATIC_UNI_SOC_MODELS = SetsKt.setOf(new String[]{"itel l6006", "itel w6004", "moto g(20)", "moto e13", "moto e20", "rmx3231", "rmx3511", "sm-a032f", "sm-a035m", "sm-f946u1", "tecno mobile bf6"});
    }

    public ImageCaptureFailedForVideoSnapshotQuirk() {
    }

    public static final /* synthetic */ Set access$getPROBLEMATIC_UNI_SOC_MODELS$cp() {
        return PROBLEMATIC_UNI_SOC_MODELS;
    }
}
