package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Size;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.Quirk;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ%\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\f\u0010\u000bJ3\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J3\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0010J3\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0010J%\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0013\u0010\u000bJ\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0016\u0010\u0015J%\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0017\u0010\u000bJ#\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0018\u0010\u000b¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExcludedSupportedSizesQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "imageFormat", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "getOnePlus6ExcludedSizes", "(Ljava/lang/String;I)Ljava/util/List;", "getOnePlus6TExcludedSizes", "Ljava/lang/Class;", "klass", "getHuaweiP20LiteExcludedSizes", "(Ljava/lang/String;ILjava/lang/Class;)Ljava/util/List;", "getSamsungJ7PrimeApi27AboveExcludedSizes", "getSamsungJ7Api27AboveExcludedSizes", "getRedmiNote9ProExcludedSizes", "getSamsungA05sExcludedSizes", "(I)Ljava/util/List;", "getNokia7PlusExcludedSizes", "getSamsungZFold4ExcludedSizes", "getExcludedSizes", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class ExcludedSupportedSizesQuirk implements Quirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u0014\u0010\u0015\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\fR\u0014\u0010\u0017\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\fR\u0014\u0010\u0019\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u0014\u0010\u001b\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\f¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExcludedSupportedSizesQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "UNKNOWN_IMAGE_FORMAT", _UrlKt.FRAGMENT_ENCODE_SET, "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isOnePlus6", "isOnePlus6$camera_camera2", "()Z", "isOnePlus6T", "isOnePlus6T$camera_camera2", "isHuaweiP20Lite", "isHuaweiP20Lite$camera_camera2", "isSamsungJ7PrimeApi27Above", "isSamsungJ7PrimeApi27Above$camera_camera2", "isSamsungJ7Api27Above", "isSamsungJ7Api27Above$camera_camera2", "isRedmiNote9Pro", "isRedmiNote9Pro$camera_camera2", "isSamsungA05s", "isSamsungA05s$camera_camera2", "isNokia7Plus", "isNokia7Plus$camera_camera2", "isSamsungZFold4", "isSamsungZFold4$camera_camera2", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled() {
            if (isOnePlus6$camera_camera2() == false) goto L5;
            return true;
        L5:
            if (isOnePlus6T$camera_camera2() == false) goto L7;
            return true;
        L7:
            if (isHuaweiP20Lite$camera_camera2() == false) goto L9;
            return true;
        L9:
            if (isSamsungJ7PrimeApi27Above$camera_camera2() == false) goto L11;
            return true;
        L11:
            if (isSamsungJ7Api27Above$camera_camera2() == false) goto L13;
            return true;
        L13:
            if (isRedmiNote9Pro$camera_camera2() == false) goto L15;
            return true;
        L15:
            if (isSamsungA05s$camera_camera2() == false) goto L17;
            return true;
        L17:
            if (isNokia7Plus$camera_camera2() == false) goto L19;
            return true;
        L19:
            if (isSamsungZFold4$camera_camera2() == true) goto L32;
            return false;
        L32:
            return true;
        }

        public final boolean isHuaweiP20Lite$camera_camera2() {
            if (Device.INSTANCE.isHuaweiDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("HWANE", Build.DEVICE, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isNokia7Plus$camera_camera2() {
            if (Device.INSTANCE.isNokiaDevice() == false) goto L9;
            String r2 = Build.DEVICE;
            if (StringsKt.equals("B2N", r2, true) == false) goto L7;
        L8:
            return true;
        L7:
            if (StringsKt.equals("B2N_sprout", r2, true) == true) goto L8;
            return false;
        L9:
            return false;
        }

        public final boolean isOnePlus6$camera_camera2() {
            if (Device.INSTANCE.isOnePlusDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("OnePlus6", Build.DEVICE, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isOnePlus6T$camera_camera2() {
            if (Device.INSTANCE.isOnePlusDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("OnePlus6T", Build.DEVICE, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isRedmiNote9Pro$camera_camera2() {
            if (Device.INSTANCE.isRedmiDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("joyeuse", Build.DEVICE, true) == false) goto L9;
            return true;
        L9:
            return false;
        }

        public final boolean isSamsungA05s$camera_camera2() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
        L9:
            return false;
        L5:
            if (StringsKt.equals("a05s", Build.DEVICE, true) == false) goto L9;
            if (StringsKt.contains$default(Build.MODEL.toUpperCase(Locale.ROOT), "SM-A057", false, 2, null) == false) goto L9;
            return true;
        }

        public final boolean isSamsungJ7Api27Above$camera_camera2() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("J7XELTE", Build.DEVICE, true) == true) goto L7;
            return false;
        L7:
            if (Build.VERSION.SDK_INT < 27) goto L12;
            return true;
        L12:
            return false;
        }

        public final boolean isSamsungJ7PrimeApi27Above$camera_camera2() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            if (StringsKt.equals("ON7XELTE", Build.DEVICE, true) == true) goto L7;
            return false;
        L7:
            if (Build.VERSION.SDK_INT < 27) goto L12;
            return true;
        L12:
            return false;
        }

        public final boolean isSamsungZFold4$camera_camera2() {
            if (Device.INSTANCE.isSamsungDevice() == false) goto L11;
            String r2 = Build.DEVICE;
            if (StringsKt.equals("q4q", r2, true) == false) goto L7;
        L10:
            return true;
        L7:
            if (StringsKt.equals("SCG16", r2, true) == true) goto L10;
            if (StringsKt.equals("SC-55C", r2, true) == true) goto L10;
            return false;
        L11:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public ExcludedSupportedSizesQuirk() {
    }

    private final List<Size> getHuaweiP20LiteExcludedSizes(String r1, int r2, Class<?> r3) {
        if (Intrinsics.areEqual(r1, MVEL.VERSION_SUB) == false) goto L12;
        if (r2 == 34) goto L10;
        if (r2 == 35) goto L10;
        if (r3 == null) goto L12;
    L10:
        return CollectionsKt.listOf(new Size[]{new Size(720, 720), new Size(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN)});
    L12:
        return CollectionsKt.emptyList();
    }

    private final List<Size> getNokia7PlusExcludedSizes(int r8) {
        if (r8 != 35) goto L7;
        return CollectionsKt.listOf(new Size[]{new Size(4032, 3024), new Size(4000, 3000), new Size(3264, 2448), new Size(3200, 2400), new Size(3024, 3024), new Size(2976, 2976), new Size(2448, 2448)});
    L7:
        return CollectionsKt.emptyList();
    }

    private final List<Size> getOnePlus6ExcludedSizes(String r2, int r3) {
        if (Intrinsics.areEqual(r2, MVEL.VERSION_SUB) == false) goto L9;
        if (r3 != 256) goto L9;
        return CollectionsKt.listOf(new Size[]{new Size(4160, 3120), new Size(4000, 3000)});
    L9:
        return CollectionsKt.emptyList();
    }

    private final List<Size> getOnePlus6TExcludedSizes(String r2, int r3) {
        if (Intrinsics.areEqual(r2, MVEL.VERSION_SUB) == false) goto L9;
        if (r3 != 256) goto L9;
        return CollectionsKt.listOf(new Size[]{new Size(4160, 3120), new Size(4000, 3000)});
    L9:
        return CollectionsKt.emptyList();
    }

    private final List<Size> getRedmiNote9ProExcludedSizes(String r1, int r2) {
        if (Intrinsics.areEqual(r1, MVEL.VERSION_SUB) == false) goto L9;
        if (r2 != 256) goto L9;
        return CollectionsKt.listOf(new Size(9280, 6944));
    L9:
        return CollectionsKt.emptyList();
    }

    private final List<Size> getSamsungA05sExcludedSizes(int r8) {
        if (r8 != 35) goto L7;
        return CollectionsKt.listOf(new Size[]{new Size(3840, 2160), new Size(3264, 2448), new Size(3200, 2400), new Size(2688, 1512), new Size(2592, 1944), new Size(2592, 1940), new Size(1920, 1440)});
    L7:
        return CollectionsKt.emptyList();
    }

    private final List<Size> getSamsungJ7Api27AboveExcludedSizes(String r17, int r18, Class<?> r19) {
        if (Intrinsics.areEqual(r17, MVEL.VERSION_SUB) == false) goto L13;
        if (r18 == 34) goto L11;
        if (r19 != null) goto L11;
        if (r18 != 35) goto L20;
        return CollectionsKt.listOf(new Size[]{new Size(2048, 1536), new Size(2048, 1152), new Size(1920, 1080)});
    L20:
        return CollectionsKt.emptyList();
    L11:
        return CollectionsKt.listOf(new Size[]{new Size(4128, 3096), new Size(4128, 2322), new Size(3088, 3088), new Size(3264, 2448), new Size(3264, 1836), new Size(2048, 1536), new Size(2048, 1152), new Size(1920, 1080)});
    L13:
        if (Intrinsics.areEqual(r17, "1") == false) goto L20;
        if (r18 == 34) goto L18;
        if (r18 == 35) goto L18;
        if (r19 == null) goto L20;
    L18:
        return CollectionsKt.listOf(new Size[]{new Size(2576, 1932), new Size(2560, 1440), new Size(1920, 1920), new Size(2048, 1536), new Size(2048, 1152), new Size(1920, 1080)});
    }

    private final List<Size> getSamsungJ7PrimeApi27AboveExcludedSizes(String r22, int r23, Class<?> r24) {
        if (Intrinsics.areEqual(r22, MVEL.VERSION_SUB) == false) goto L14;
        if (r23 == 34) goto L12;
        if (r24 != null) goto L12;
        if (r23 != 35) goto L21;
        return CollectionsKt.listOf(new Size[]{new Size(4128, 2322), new Size(3088, 3088), new Size(3264, 2448), new Size(3264, 1836), new Size(2048, 1536), new Size(2048, 1152), new Size(1920, 1080)});
    L21:
        return CollectionsKt.emptyList();
    L12:
        return CollectionsKt.listOf(new Size[]{new Size(4128, 3096), new Size(4128, 2322), new Size(3088, 3088), new Size(3264, 2448), new Size(3264, 1836), new Size(2048, 1536), new Size(2048, 1152), new Size(1920, 1080)});
    L14:
        if (Intrinsics.areEqual(r22, "1") == false) goto L21;
        if (r23 == 34) goto L19;
        if (r23 == 35) goto L19;
        if (r24 == null) goto L21;
    L19:
        return CollectionsKt.listOf(new Size[]{new Size(3264, 2448), new Size(3264, 1836), new Size(2448, 2448), new Size(1920, 1920), new Size(2048, 1536), new Size(2048, 1152), new Size(1920, 1080)});
    }

    private final List<Size> getSamsungZFold4ExcludedSizes(String r13, int r14) {
        if (Intrinsics.areEqual(r13, "1") == false) goto L9;
        if (r14 != 35) goto L9;
        return CollectionsKt.listOf(new Size[]{new Size(1280, 720), new Size(1920, 1080), new Size(2304, 1296), new Size(640, 360), new Size(177, 144), new Size(2336, 1080), new Size(2400, 1080), new Size(1920, 824), new Size(1088, 1088), new Size(1728, 1728), new Size(2736, 2736), new Size(1824, 712)});
    L9:
        return CollectionsKt.emptyList();
    }

    public final List<Size> getExcludedSizes(String r4, int r5) {
        Companion r0 = Companion;
        if (r0.isOnePlus6$camera_camera2() == false) goto L7;
        return getOnePlus6ExcludedSizes(r4, r5);
    L7:
        if (r0.isOnePlus6T$camera_camera2() == false) goto L11;
        return getOnePlus6TExcludedSizes(r4, r5);
    L11:
        if (r0.isHuaweiP20Lite$camera_camera2() == false) goto L15;
        return getHuaweiP20LiteExcludedSizes(r4, r5, null);
    L15:
        if (r0.isSamsungJ7PrimeApi27Above$camera_camera2() == false) goto L19;
        return getSamsungJ7PrimeApi27AboveExcludedSizes(r4, r5, null);
    L19:
        if (r0.isSamsungJ7Api27Above$camera_camera2() == false) goto L23;
        return getSamsungJ7Api27AboveExcludedSizes(r4, r5, null);
    L23:
        if (r0.isRedmiNote9Pro$camera_camera2() == false) goto L27;
        return getRedmiNote9ProExcludedSizes(r4, r5);
    L27:
        if (r0.isSamsungA05s$camera_camera2() == false) goto L31;
        return getSamsungA05sExcludedSizes(r5);
    L31:
        if (r0.isNokia7Plus$camera_camera2() == false) goto L35;
        return getNokia7PlusExcludedSizes(r5);
    L35:
        if (r0.isSamsungZFold4$camera_camera2() == true) goto L37;
        Logger.w("ExcludedSupportedSizesQuirk", "Cannot retrieve list of supported sizes to exclude on this device.");
        return CollectionsKt.emptyList();
    L37:
        return getSamsungZFold4ExcludedSizes(r4, r5);
    }
}
