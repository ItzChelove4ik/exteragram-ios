package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/PreviewStretchWhenVideoCaptureIsBoundQuirk;", "Landroidx/camera/camera2/compat/quirk/CaptureIntentPreviewQuirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class PreviewStretchWhenVideoCaptureIsBoundQuirk implements CaptureIntentPreviewQuirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0002J\b\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\u0005H\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0002¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/PreviewStretchWhenVideoCaptureIsBoundQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isHuaweiP8Lite", "isSamsungJ3", "isSamsungJ5", "isSamsungJ7", "isSamsungJ1AceNeo", "isOppoA37F", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isHuaweiP8Lite() {
            if (Device.INSTANCE.isHuaweiDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("HUAWEI ALE-L04", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isOppoA37F() {
            if (Device.INSTANCE.isOppoDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("A37F", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungJ1AceNeo() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("sm-j111f", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungJ3() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("sm-j320f", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungJ5() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("sm-j510fn", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungJ7() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("sm-j700f", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isEnabled() {
            if (isHuaweiP8Lite() == false) goto L5;
            return true;
        L5:
            if (isSamsungJ3() == false) goto L7;
            return true;
        L7:
            if (isSamsungJ7() == false) goto L9;
            return true;
        L9:
            if (isSamsungJ1AceNeo() == false) goto L11;
            return true;
        L11:
            if (isOppoA37F() == false) goto L13;
            return true;
        L13:
            if (isSamsungJ5() == true) goto L23;
            return false;
        L23:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public PreviewStretchWhenVideoCaptureIsBoundQuirk() {
    }
}
