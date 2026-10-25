package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/InvalidVideoProfilesQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class InvalidVideoProfilesQuirk implements Quirk {
    private static final List<String> AFFECTED_ONE_PLUS_MODELS = null;
    private static final List<String> AFFECTED_OPPO_MODELS = null;
    private static final List<String> AFFECTED_PIXEL_MODELS = null;
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\nH\u0002J\b\u0010\u0017\u001a\u00020\nH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/compat/quirk/InvalidVideoProfilesQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "AFFECTED_PIXEL_MODELS", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "AFFECTED_ONE_PLUS_MODELS", "AFFECTED_OPPO_MODELS", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isAffectedSamsungDevices", "isAffectedPixelDevices", "isAffectedXiaomiDevices", "isAffectedOnePlusDevices", "isAffectedOppoDevices", "isAffectedPixelModel", "isAffectedOnePlusModel", "isAffectedOppoModel", "isAffectedPixelBuild", "isTp1aBuild", "isTd1aBuild", "isTkq1Build", "isAPI33", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isAPI33() {
            if (Build.VERSION.SDK_INT != 33) goto L6;
            return true;
        L6:
            return false;
        }

        private final boolean isAffectedOnePlusDevices() {
            if (isAffectedOnePlusModel() == true) goto L5;
            return false;
        L5:
            if (isAPI33() == false) goto L10;
            return true;
        L10:
            return false;
        }

        private final boolean isAffectedOnePlusModel() {
            return InvalidVideoProfilesQuirk.access$getAFFECTED_ONE_PLUS_MODELS$cp().contains(Build.MODEL.toLowerCase(Locale.ROOT));
        }

        private final boolean isAffectedOppoDevices() {
            if (isAffectedOppoModel() == true) goto L5;
            return false;
        L5:
            if (isAPI33() == false) goto L10;
            return true;
        L10:
            return false;
        }

        private final boolean isAffectedOppoModel() {
            return InvalidVideoProfilesQuirk.access$getAFFECTED_OPPO_MODELS$cp().contains(Build.MODEL.toLowerCase(Locale.ROOT));
        }

        private final boolean isAffectedPixelBuild() {
            if (isTp1aBuild() == false) goto L5;
            return true;
        L5:
            if (isTd1aBuild() == true) goto L11;
            return false;
        L11:
            return true;
        }

        private final boolean isAffectedPixelDevices() {
            if (isAffectedPixelModel() == true) goto L5;
            return false;
        L5:
            if (isAffectedPixelBuild() == false) goto L10;
            return true;
        L10:
            return false;
        }

        private final boolean isAffectedPixelModel() {
            return InvalidVideoProfilesQuirk.access$getAFFECTED_PIXEL_MODELS$cp().contains(Build.MODEL.toLowerCase(Locale.ROOT));
        }

        private final boolean isAffectedSamsungDevices() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (isTp1aBuild() == false) goto L10;
            return true;
        L10:
            return false;
        }

        private final boolean isAffectedXiaomiDevices() {
            Device r0 = Device.INSTANCE;
            boolean r1 = r0.isRedmiDevice();
            if ((r0.isXiaomiDevice() | r1) == true) goto L5;
            return false;
        L5:
            if (isTkq1Build() == false) goto L7;
            return true;
        L7:
            if (isTp1aBuild() == false) goto L13;
            return true;
        L13:
            return false;
        }

        private final boolean isTd1aBuild() {
            return StringsKt.startsWith(Build.ID, "TD1A", true);
        }

        private final boolean isTkq1Build() {
            return StringsKt.startsWith(Build.ID, "TKQ1", true);
        }

        private final boolean isTp1aBuild() {
            return StringsKt.startsWith(Build.ID, "TP1A", true);
        }

        public final boolean isEnabled() {
            if (isAffectedSamsungDevices() == false) goto L5;
            return true;
        L5:
            if (isAffectedPixelDevices() == false) goto L7;
            return true;
        L7:
            if (isAffectedXiaomiDevices() == false) goto L9;
            return true;
        L9:
            if (isAffectedOppoDevices() == false) goto L11;
            return true;
        L11:
            if (isAffectedOnePlusDevices() == true) goto L20;
            return false;
        L20:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
        AFFECTED_PIXEL_MODELS = CollectionsKt.listOf(new String[]{"pixel 4", "pixel 4a", "pixel 4a (5g)", "pixel 4 xl", "pixel 5", "pixel 5a", "pixel 6", "pixel 6a", "pixel 6 pro", "pixel 7", "pixel 7 pro"});
        AFFECTED_ONE_PLUS_MODELS = CollectionsKt.listOf(new String[]{"cph2417", "cph2451"});
        AFFECTED_OPPO_MODELS = CollectionsKt.listOf(new String[]{"cph2437", "cph2525", "pht110"});
    }

    public InvalidVideoProfilesQuirk() {
    }

    public static final /* synthetic */ List access$getAFFECTED_ONE_PLUS_MODELS$cp() {
        return AFFECTED_ONE_PLUS_MODELS;
    }

    public static final /* synthetic */ List access$getAFFECTED_OPPO_MODELS$cp() {
        return AFFECTED_OPPO_MODELS;
    }

    public static final /* synthetic */ List access$getAFFECTED_PIXEL_MODELS$cp() {
        return AFFECTED_PIXEL_MODELS;
    }
}
