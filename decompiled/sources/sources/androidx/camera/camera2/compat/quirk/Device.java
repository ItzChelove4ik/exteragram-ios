package androidx.camera.camera2.compat.quirk;

import android.os.Build;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\n\u001a\u00020\u0006*\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\rJ\r\u0010\u000f\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\rJ\r\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\rJ\r\u0010\u0011\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\rJ\r\u0010\u0012\u001a\u00020\u0006¢\u0006\u0004\b\u0012\u0010\rJ\r\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\rJ\r\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\rJ\r\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\rJ\r\u0010\u0016\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\rJ\r\u0010\u0017\u001a\u00020\u0006¢\u0006\u0004\b\u0017\u0010\rJ\r\u0010\u0018\u001a\u00020\u0006¢\u0006\u0004\b\u0018\u0010\rJ\r\u0010\u0019\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010\rJ\r\u0010\u001a\u001a\u00020\u0006¢\u0006\u0004\b\u001a\u0010\rJ\r\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u001b\u0010\rJ\r\u0010\u001c\u001a\u00020\u0006¢\u0006\u0004\b\u001c\u0010\rJ\r\u0010\u001d\u001a\u00020\u0006¢\u0006\u0004\b\u001d\u0010\rJ\r\u0010\u001e\u001a\u00020\u0006¢\u0006\u0004\b\u001e\u0010\rJ\r\u0010\u001f\u001a\u00020\u0006¢\u0006\u0004\b\u001f\u0010\r¨\u0006 "}, d2 = {"Landroidx/camera/camera2/compat/quirk/Device;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "vendor", _UrlKt.FRAGMENT_ENCODE_SET, "isDeviceFrom", "(Ljava/lang/String;)Z", "other", "equalsCaseInsensitive", "(Ljava/lang/String;Ljava/lang/String;)Z", "isBluDevice", "()Z", "isHuaweiDevice", "isItelDevice", "isJioDevice", "isGoogleDevice", "isMotorolaDevice", "isNokiaDevice", "isOnePlusDevice", "isOppoDevice", "isPositivoDevice", "isRealmeDevice", "isRedmiDevice", "isSamsungDevice", "isSonyDevice", "isTecnoDevice", "isXiaomiDevice", "isVivoDevice", "isPocoDevice", "isUniSocChipsetDevice", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Device {
    public static final Device INSTANCE = null;

    static {
        INSTANCE = new Device();
    }

    private Device() {
    }

    private final boolean equalsCaseInsensitive(String r1, String r2) {
        return StringsKt.equals(r1, r2, true);
    }

    private final boolean isDeviceFrom(String r2) {
        if (equalsCaseInsensitive(Build.MANUFACTURER, r2) == false) goto L5;
        return true;
    L5:
        if (equalsCaseInsensitive(Build.BRAND, r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final boolean isBluDevice() {
        return isDeviceFrom("Blu");
    }

    public final boolean isGoogleDevice() {
        return isDeviceFrom("Google");
    }

    public final boolean isHuaweiDevice() {
        return isDeviceFrom("Huawei");
    }

    public final boolean isItelDevice() {
        return isDeviceFrom("Itel");
    }

    public final boolean isJioDevice() {
        return isDeviceFrom("Jio");
    }

    public final boolean isMotorolaDevice() {
        return isDeviceFrom("Motorola");
    }

    public final boolean isNokiaDevice() {
        return isDeviceFrom("Nokia");
    }

    public final boolean isOnePlusDevice() {
        return isDeviceFrom("OnePlus");
    }

    public final boolean isOppoDevice() {
        return isDeviceFrom("Oppo");
    }

    public final boolean isPocoDevice() {
        return isDeviceFrom("Poco");
    }

    public final boolean isPositivoDevice() {
        return isDeviceFrom("Positivo");
    }

    public final boolean isRealmeDevice() {
        return isDeviceFrom("Realme");
    }

    public final boolean isRedmiDevice() {
        return isDeviceFrom("Redmi");
    }

    public final boolean isSamsungDevice() {
        return isDeviceFrom("Samsung");
    }

    public final boolean isSonyDevice() {
        return isDeviceFrom("Sony");
    }

    public final boolean isTecnoDevice() {
        if (isDeviceFrom("Tecno") == false) goto L5;
        return true;
    L5:
        if (isDeviceFrom("Tecno-mobile") == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final boolean isUniSocChipsetDevice() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L6:
        String r0 = Build.HARDWARE;
        Locale r1 = Locale.ROOT;
        if (StringsKt.startsWith$default(r0.toLowerCase(r1), "ums", false, 2, null) == false) goto L9;
    L14:
        return true;
    L9:
        if (isItelDevice() == true) goto L11;
    L13:
        return false;
    L11:
        if (StringsKt.startsWith$default(r0.toLowerCase(r1), "sp", false, 2, null) == false) goto L13;
    L5:
        if (StringsKt.equals("Spreadtrum", Device$$ExternalSyntheticApiModelOutline0.m(), true) == true) goto L14;
        goto L6
    }

    public final boolean isVivoDevice() {
        return isDeviceFrom("Vivo");
    }

    public final boolean isXiaomiDevice() {
        return isDeviceFrom("Xiaomi");
    }
}
