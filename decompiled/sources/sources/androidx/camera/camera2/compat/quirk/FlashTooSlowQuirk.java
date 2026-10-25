package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraMetadata;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FlashTooSlowQuirk;", "Landroidx/camera/camera2/compat/quirk/UseTorchAsFlashQuirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class FlashTooSlowQuirk implements UseTorchAsFlashQuirk {
    private static final List<String> AFFECTED_MODEL_PREFIXES = null;
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\bH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FlashTooSlowQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "AFFECTED_MODEL_PREFIXES", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "isAffectedModel", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isAffectedModel() {
            Iterator r5 = FlashTooSlowQuirk.access$getAFFECTED_MODEL_PREFIXES$cp().iterator();
        L4:
            if (r5.hasNext() == false) goto L9;
            String r0 = (String) r5.next();
            if (StringsKt.startsWith$default(Build.MODEL.toUpperCase(Locale.ROOT), r0, false, 2, null) == false) goto L4;
            return true;
        L9:
            return false;
        }

        public final boolean isEnabled(CameraMetadata r1) {
            if (isAffectedModel() == false) goto L10;
            Integer r0 = (Integer) r1.get(CameraCharacteristics.LENS_FACING);
            if (r0 != null) goto L8;
            return false;
        L8:
            if (r0.intValue() != 1) goto L12;
            return true;
        L12:
            return false;
        L10:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
        AFFECTED_MODEL_PREFIXES = CollectionsKt.listOf(new String[]{"PIXEL 3A", "PIXEL 3A XL", "PIXEL 4", "PIXEL 5", "SM-A320", "MOTO G(20)", "ITEL L6006", "RMX3231"});
    }

    public FlashTooSlowQuirk() {
    }

    public static final /* synthetic */ List access$getAFFECTED_MODEL_PREFIXES$cp() {
        return AFFECTED_MODEL_PREFIXES;
    }
}
