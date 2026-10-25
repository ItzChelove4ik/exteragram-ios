package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Log;
import androidx.camera.core.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/impl/DeviceInfoLogger;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "logDeviceInfo", _UrlKt.FRAGMENT_ENCODE_SET, "cameraProperties", "Landroidx/camera/camera2/impl/CameraProperties;", "logDeviceLevel", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDeviceInfoLogger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeviceInfoLogger.kt\nandroidx/camera/camera2/impl/DeviceInfoLogger\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,56:1\n102#2,4:57\n*S KotlinDebug\n*F\n+ 1 DeviceInfoLogger.kt\nandroidx/camera/camera2/impl/DeviceInfoLogger\n*L\n53#1:57,4\n*E\n"})
/* loaded from: classes3.dex */
public final class DeviceInfoLogger {
    public static final DeviceInfoLogger INSTANCE = null;

    static {
        INSTANCE = new DeviceInfoLogger();
    }

    private DeviceInfoLogger() {
    }

    private final void logDeviceLevel(CameraProperties r2) {
        Integer r1 = (Integer) r2.getMetadata().getOrDefault(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, -1);
        if (r1 != null) goto L6;
    L8:
        if (r1 != null) goto L11;
    L13:
        if (r1 != null) goto L16;
    L18:
        if (r1 != null) goto L21;
    L23:
        if (r1 != null) goto L26;
    L28:
        String r12 = "Unknown value: " + r1;
    L29:
        Camera2Logger r22 = Camera2Logger.INSTANCE;
        if (Logger.isInfoEnabled("CXCP") == false) goto L33;
        Log.i(Camera2Logger.access$getTRUNCATED_TAG$p(), "Device Level: ".concat(r12));
        return;
    L33:
        return;
    L26:
        if (r1.intValue() != 3) goto L28;
        r12 = "INFO_SUPPORTED_HARDWARE_LEVEL_3";
        goto L29
    L21:
        if (r1.intValue() != 1) goto L23;
        r12 = "INFO_SUPPORTED_HARDWARE_LEVEL_FULL";
        goto L29
    L16:
        if (r1.intValue() != 0) goto L18;
        r12 = "INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED";
        goto L29
    L11:
        if (r1.intValue() != 4) goto L13;
        r12 = "INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL";
        goto L29
    L6:
        if (r1.intValue() != 2) goto L8;
        r12 = "INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY";
        goto L29
    }

    public final void logDeviceInfo(CameraProperties r1) {
        logDeviceLevel(r1);
    }
}
