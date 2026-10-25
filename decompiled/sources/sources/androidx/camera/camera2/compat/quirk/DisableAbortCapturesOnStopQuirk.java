package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/DisableAbortCapturesOnStopQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class DisableAbortCapturesOnStopQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final boolean isPocoX3ProDevice = false;
    private static final boolean isSamsungNote10PlusDevice = false;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/DisableAbortCapturesOnStopQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isSamsungNote10PlusDevice", "isPocoX3ProDevice", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final boolean isEnabled() {
            if (Device.INSTANCE.isTecnoDevice() == false) goto L5;
            return true;
        L5:
            if (DisableAbortCapturesOnStopQuirk.access$isSamsungNote10PlusDevice$cp() == false) goto L7;
            return true;
        L7:
            if (DisableAbortCapturesOnStopQuirk.access$isPocoX3ProDevice$cp() == true) goto L14;
            return false;
        L14:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
        Device r0 = Device.INSTANCE;
        boolean r2 = false;
        if (r0.isSamsungDevice() == true) goto L5;
    L7:
        boolean r1 = false;
    L8:
        isSamsungNote10PlusDevice = r1;
        if (r0.isPocoDevice() == true) goto L11;
    L13:
        isPocoX3ProDevice = r2;
        return;
    L11:
        if (StringsKt.equals("M2102J20SG", Build.MODEL, true) == false) goto L13;
        r2 = true;
        goto L13
    L5:
        if (StringsKt.equals("d2q", Build.DEVICE, true) == false) goto L7;
        r1 = true;
        goto L8
    }

    public DisableAbortCapturesOnStopQuirk() {
    }

    public static final /* synthetic */ boolean access$isPocoX3ProDevice$cp() {
        return isPocoX3ProDevice;
    }

    public static final /* synthetic */ boolean access$isSamsungNote10PlusDevice$cp() {
        return isSamsungNote10PlusDevice;
    }
}
