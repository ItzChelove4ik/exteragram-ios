package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ControlZoomRatioRangeAssertionErrorQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class ControlZoomRatioRangeAssertionErrorQuirk implements Quirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0002¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ControlZoomRatioRangeAssertionErrorQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isJioPhoneNext", "isSamsungA2s", "isVivo2039", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isJioPhoneNext() {
            if (Device.INSTANCE.isJioDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.startsWith(Build.MODEL, "LS1542QW", true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungA2s() {
            if (Device.INSTANCE.isSamsungDevice() == false) goto L9;
            String r2 = Build.MODEL;
            if (StringsKt.startsWith(r2, "SM-A025", true) == false) goto L7;
        L8:
            return true;
        L7:
            if (StringsKt.equals(r2, "SM-S124DL", true) == true) goto L8;
            return false;
        L9:
            return false;
        }

        private final boolean isVivo2039() {
            if (Device.INSTANCE.isVivoDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals(Build.MODEL, "VIVO 2039", true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isEnabled() {
            if (isJioPhoneNext() == false) goto L5;
            return true;
        L5:
            if (isSamsungA2s() == false) goto L7;
            return true;
        L7:
            if (isVivo2039() == true) goto L14;
            return false;
        L14:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public ControlZoomRatioRangeAssertionErrorQuirk() {
    }
}
