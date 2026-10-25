package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/PreviewPixelHDRnetQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class PreviewPixelHDRnetQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final List<String> SUPPORTED_DEVICES = null;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/PreviewPixelHDRnetQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SUPPORTED_DEVICES", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled() {
            if (Device.INSTANCE.isGoogleDevice() == true) goto L5;
            return false;
        L5:
            if (PreviewPixelHDRnetQuirk.access$getSUPPORTED_DEVICES$cp().contains(Build.DEVICE.toLowerCase(Locale.getDefault())) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
        SUPPORTED_DEVICES = CollectionsKt.listOf(new String[]{"sunfish", "bramble", "redfin", "barbet"});
    }

    public PreviewPixelHDRnetQuirk() {
    }

    public static final /* synthetic */ List access$getSUPPORTED_DEVICES$cp() {
        return SUPPORTED_DEVICES;
    }
}
