package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.core.impl.Quirk;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FinalizeSessionOnCloseQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class FinalizeSessionOnCloseQuirk implements Quirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\r\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FinalizeSessionOnCloseQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "getBehavior", "Landroidx/camera/camera2/pipe/CameraGraph$Flags$FinalizeSessionOnCloseBehavior;", "getBehavior-Bm6Tfm4", "()I", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getBehavior-Bm6Tfm4, reason: not valid java name */
        public final int m30getBehaviorBm6Tfm4() {
            if (StringsKt.startsWith$default(Build.MODEL.toLowerCase(Locale.getDefault()), "cph", false, 2, null) == false) goto L7;
            return CameraGraph.Flags.FinalizeSessionOnCloseBehavior.Companion.m211getIMMEDIATEBm6Tfm4();
        L7:
            return CameraGraph.Flags.FinalizeSessionOnCloseBehavior.Companion.m212getOFFBm6Tfm4();
        }

        public final boolean isEnabled() {
            return true;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public FinalizeSessionOnCloseQuirk() {
    }
}
