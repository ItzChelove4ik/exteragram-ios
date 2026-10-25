package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ZslDisablerQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class ZslDisablerQuirk implements Quirk {
    private static final List<String> AFFECTED_SAMSUNG_MODEL = null;
    private static final List<String> AFFECTED_XIAOMI_MODEL = null;
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\tH\u0002J\u0016\u0010\f\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ZslDisablerQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "AFFECTED_SAMSUNG_MODEL", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "AFFECTED_XIAOMI_MODEL", "load", _UrlKt.FRAGMENT_ENCODE_SET, "isAffectedSamsungDevices", "isAffectedXiaoMiDevices", "isAffectedModel", "modelList", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isAffectedModel(List<String> r5) {
            Iterator<String> r4 = r5.iterator();
        L4:
            if (r4.hasNext() == false) goto L9;
            String r52 = r4.next();
            if (StringsKt.startsWith$default(Build.MODEL.toUpperCase(Locale.ROOT), r52, false, 2, null) == false) goto L4;
            return true;
        L9:
            return false;
        }

        private final boolean isAffectedSamsungDevices() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (isAffectedModel(ZslDisablerQuirk.access$getAFFECTED_SAMSUNG_MODEL$cp()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        private final boolean isAffectedXiaoMiDevices() {
            if (Device.INSTANCE.isXiaomiDevice() == true) goto L5;
            return false;
        L5:
            if (isAffectedModel(ZslDisablerQuirk.access$getAFFECTED_XIAOMI_MODEL$cp()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public final boolean load() {
            if (isAffectedSamsungDevices() == false) goto L5;
            return true;
        L5:
            if (isAffectedXiaoMiDevices() == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    static {
        Companion = new Companion(null);
        AFFECTED_SAMSUNG_MODEL = CollectionsKt.listOf(new String[]{"SM-F936", "SM-S901U", "SM-S908U", "SM-S908U1", "SM-F721U1", "SM-S928U1"});
        AFFECTED_XIAOMI_MODEL = CollectionsKt.listOf("MI 8");
    }

    public ZslDisablerQuirk() {
    }

    public static final /* synthetic */ List access$getAFFECTED_SAMSUNG_MODEL$cp() {
        return AFFECTED_SAMSUNG_MODEL;
    }

    public static final /* synthetic */ List access$getAFFECTED_XIAOMI_MODEL$cp() {
        return AFFECTED_XIAOMI_MODEL;
    }
}
