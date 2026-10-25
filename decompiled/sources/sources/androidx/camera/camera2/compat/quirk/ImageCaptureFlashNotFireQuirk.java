package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraMetadata;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ImageCaptureFlashNotFireQuirk;", "Landroidx/camera/camera2/compat/quirk/UseTorchAsFlashQuirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class ImageCaptureFlashNotFireQuirk implements UseTorchAsFlashQuirk {
    private static final List<String> BUILD_MODELS = null;
    private static final List<String> BUILD_MODELS_FRONT_CAMERA = null;
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ImageCaptureFlashNotFireQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "BUILD_MODELS", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "BUILD_MODELS_FRONT_CAMERA", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled(CameraMetadata r5) {
            List r4 = ImageCaptureFlashNotFireQuirk.access$getBUILD_MODELS_FRONT_CAMERA$cp();
            String r0 = Build.MODEL;
            Locale r1 = Locale.ROOT;
            if (r4.contains(r0.toLowerCase(r1)) == false) goto L10;
            Integer r42 = (Integer) r5.get(CameraCharacteristics.LENS_FACING);
            if (r42 == null) goto L10;
            if (r42.intValue() != 0) goto L10;
            boolean r43 = true;
        L11:
            boolean r52 = ImageCaptureFlashNotFireQuirk.access$getBUILD_MODELS$cp().contains(r0.toLowerCase(r1));
            if (r43 == true) goto L16;
            if (r52 == true) goto L16;
            return false;
        L16:
            return true;
        L10:
            r43 = false;
            goto L11
        }
    }

    static {
        Companion = new Companion(null);
        BUILD_MODELS = CollectionsKt.listOf(new String[]{"itel w6004", "sm-j700m"});
        BUILD_MODELS_FRONT_CAMERA = CollectionsKt.listOf(new String[]{"sm-j700f", "sm-j710f"});
    }

    public ImageCaptureFlashNotFireQuirk() {
    }

    public static final /* synthetic */ List access$getBUILD_MODELS$cp() {
        return BUILD_MODELS;
    }

    public static final /* synthetic */ List access$getBUILD_MODELS_FRONT_CAMERA$cp() {
        return BUILD_MODELS_FRONT_CAMERA;
    }
}
