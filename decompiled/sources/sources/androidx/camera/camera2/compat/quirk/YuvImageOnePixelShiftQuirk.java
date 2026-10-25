package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/YuvImageOnePixelShiftQuirk;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class YuvImageOnePixelShiftQuirk implements Quirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0002J\b\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\u0005H\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0002¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/YuvImageOnePixelShiftQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isMotorolaMotoG3", "isSamsungSMG532F", "isSamsungSMJ700F", "isSamsungSMJ415F", "isSamsungSMA920F", "isXiaomiMiA1", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isMotorolaMotoG3() {
            if (Device.INSTANCE.isMotorolaDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("MotoG3", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungSMA920F() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("SM-A920F", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungSMG532F() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("SM-G532F", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungSMJ415F() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("SM-J415F", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isSamsungSMJ700F() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("SM-J700F", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        private final boolean isXiaomiMiA1() {
            if (Device.INSTANCE.isXiaomiDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("Mi A1", Build.MODEL, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isEnabled() {
            if (isMotorolaMotoG3() == false) goto L5;
            return true;
        L5:
            if (isSamsungSMG532F() == false) goto L7;
            return true;
        L7:
            if (isSamsungSMJ700F() == false) goto L9;
            return true;
        L9:
            if (isSamsungSMA920F() == false) goto L11;
            return true;
        L11:
            if (isSamsungSMJ415F() == false) goto L13;
            return true;
        L13:
            if (isXiaomiMiA1() == true) goto L23;
            return false;
        L23:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public YuvImageOnePixelShiftQuirk() {
    }
}
