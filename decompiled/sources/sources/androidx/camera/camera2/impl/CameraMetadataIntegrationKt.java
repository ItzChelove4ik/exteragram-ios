package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\u001a\u0019\u0010\u0003\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0004\u001a\u001b\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u0011\u0010\n\u001a\u00020\u0007*\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u0019\u0010\f\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\f\u0010\u0004\"\u001b\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\r*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\r*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f\"\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\r*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/CameraMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "preferredMode", "getSupportedAfMode", "(Landroidx/camera/camera2/pipe/CameraMetadata;I)I", "getSupportedAeMode", "aeMode", _UrlKt.FRAGMENT_ENCODE_SET, "isAeModeSupported", "(Landroidx/camera/camera2/pipe/CameraMetadata;I)Z", "isExternalFlashAeModeSupported", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Z", "getSupportedAwbMode", _UrlKt.FRAGMENT_ENCODE_SET, "getAvailableAfModes", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Ljava/util/List;", "availableAfModes", "getAvailableAeModes", "availableAeModes", "getAvailableAwbModes", "availableAwbModes", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CameraMetadataIntegrationKt {
    public static final List<Integer> getAvailableAeModes(CameraMetadata r2) {
        return ArraysKt.asList((int[]) r2.getOrDefault(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES, new int[]{0}));
    }

    public static final List<Integer> getAvailableAfModes(CameraMetadata r2) {
        return ArraysKt.asList((int[]) r2.getOrDefault(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES, new int[]{0}));
    }

    public static final List<Integer> getAvailableAwbModes(CameraMetadata r2) {
        return ArraysKt.asList((int[]) r2.getOrDefault(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES, new int[]{0}));
    }

    public static final int getSupportedAeMode(CameraMetadata r2, int r3) {
        if (getAvailableAeModes(r2).contains(Integer.valueOf(r3)) == false) goto L6;
        return r3;
    L6:
        if (getAvailableAeModes(r2).contains(1) == false) goto L8;
        return 1;
    L8:
        return 0;
    }

    public static final int getSupportedAfMode(CameraMetadata r2, int r3) {
        if (getAvailableAfModes(r2).contains(Integer.valueOf(r3)) == false) goto L6;
        return r3;
    L6:
        if (getAvailableAfModes(r2).contains(4) == false) goto L9;
        return 4;
    L9:
        if (getAvailableAfModes(r2).contains(1) == false) goto L11;
        return 1;
    L11:
        return 0;
    }

    public static final int getSupportedAwbMode(CameraMetadata r2, int r3) {
        if (getAvailableAwbModes(r2).contains(Integer.valueOf(r3)) == false) goto L6;
        return r3;
    L6:
        if (getAvailableAwbModes(r2).contains(1) == false) goto L8;
        return 1;
    L8:
        return 0;
    }

    private static final boolean isAeModeSupported(CameraMetadata r0, int r1) {
        if (getSupportedAeMode(r0, r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public static final boolean isExternalFlashAeModeSupported(CameraMetadata r2) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return false;
    L5:
        if (isAeModeSupported(r2, 5) == false) goto L10;
        return true;
    L10:
        return false;
    }
}
