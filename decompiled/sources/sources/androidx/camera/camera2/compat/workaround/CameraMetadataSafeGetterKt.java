package androidx.camera.camera2.compat.workaround;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import androidx.camera.camera2.compat.quirk.ControlZoomRatioRangeAssertionErrorQuirk;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.internal.ZoomMath;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.Logger;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0002\u0010\u0005\u001a\u0014\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007*\u00020\u0002H\u0007\u001a\n\u0010\t\u001a\u00020\n*\u00020\u0002¨\u0006\u000b"}, d2 = {"getSafely", "T", "Landroidx/camera/camera2/pipe/CameraMetadata;", PluginsConstants.Settings.KEY, "Landroid/hardware/camera2/CameraCharacteristics$Key;", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;", "getControlZoomRatioRangeSafely", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "getActiveArraySizeSafely", "Landroid/graphics/Rect;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraMetadataSafeGetter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraMetadataSafeGetter.kt\nandroidx/camera/camera2/compat/workaround/CameraMetadataSafeGetterKt\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,132:1\n119#2,4:133\n119#2,4:137\n119#2,4:141\n85#2,4:145\n146#2,4:149\n129#2,4:153\n119#2,4:157\n*S KotlinDebug\n*F\n+ 1 CameraMetadataSafeGetter.kt\nandroidx/camera/camera2/compat/workaround/CameraMetadataSafeGetterKt\n*L\n71#1:133,4\n76#1:137,4\n83#1:141,4\n92#1:145,4\n100#1:149,4\n111#1:153,4\n126#1:157,4\n*E\n"})
/* loaded from: classes3.dex */
public abstract class CameraMetadataSafeGetterKt {
    public static final Rect getActiveArraySizeSafely(CameraMetadata r3) {
        Rect r0 = (Rect) r3.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (r0 != null) goto L9;
        Camera2Logger r02 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L8;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to read SENSOR_INFO_ACTIVE_ARRAY_SIZE for " + CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()) + '!');
    L8:
        return new Rect(0, 0, 4000, 3000);
    L9:
        return r0;
    }

    public static final Range<Float> getControlZoomRatioRangeSafely(CameraMetadata r8) {
        Float r0 = Float.valueOf(1.0f);
        Range r5 = (Range) r8.get(CameraMetadataSafeGetterKt$$ExternalSyntheticApiModelOutline0.m());     // Catch: AssertionError -> L8
        if (r5 != null) goto L12;
        Camera2Logger r2 = Camera2Logger.INSTANCE;     // Catch: AssertionError -> L8
        if (Logger.isWarnEnabled("CXCP") == false) goto L11;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to read CONTROL_ZOOM_RATIO_RANGE for " + CameraId.m232toStringimpl(r8.mo237getCameraDz_R5H8()) + '!');     // Catch: AssertionError -> L8
    L11:
        return new Range(r0, r0);
    L12:
        ZoomMath r82 = ZoomMath.INSTANCE;     // Catch: AssertionError -> L8
        if (r82.nearZero$camera_camera2(((Number) r5.getLower()).floatValue()) == false) goto L15;
    L18:
        Camera2Logger r4 = Camera2Logger.INSTANCE;     // Catch: AssertionError -> L8
        if (Logger.isWarnEnabled("CXCP") == false) goto L21;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Invalid lower zoom range detected: " + r5.getLower());     // Catch: AssertionError -> L8
    L21:
        Float r3 = r0;
    L23:
        if (r82.nearZero$camera_camera2(((Number) r5.getUpper()).floatValue()) == false) goto L25;
    L28:
        Camera2Logger r83 = Camera2Logger.INSTANCE;     // Catch: AssertionError -> L8
        if (Logger.isWarnEnabled("CXCP") == false) goto L32;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Invalid upper zoom range detected: " + r5.getUpper());     // Catch: AssertionError -> L8
    L32:
        return new Range(r3, r0);
    L25:
        if (((Number) r5.getUpper()).floatValue() < 0.0f) goto L28;
        r0 = (Float) r5.getUpper();     // Catch: AssertionError -> L8
        goto L32
    L15:
        if (((Number) r5.getLower()).floatValue() < 0.0f) goto L18;
        r3 = (Float) r5.getLower();     // Catch: AssertionError -> L8
    L8:
        e = move-exception;
        if (DeviceQuirks.INSTANCE.get(ControlZoomRatioRangeAssertionErrorQuirk.class) == null) goto L38;
        Camera2Logger r02 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L42;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Device is known to throw an exception while retrieving the value for CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE. CONTROL_ZOOM_RATIO_RANGE is not supported. [Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", API Level: " + Build.VERSION.SDK_INT + "].");
    L42:
        if (Logger.isWarnEnabled("CXCP") == false) goto L47;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "AssertionError: failed to get CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE", e);
        return null;
    L47:
        return null;
    L38:
        Camera2Logger r03 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L42;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Exception thrown while retrieving the value for CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE on devices not known to throw exceptions during this operation. Please file an issue at https://issuetracker.google.com/issues/new?component=618491&template=1257717 with this error message [Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", API Level: " + Build.VERSION.SDK_INT + "]. CONTROL_ZOOM_RATIO_RANGE is not available.", e);
        goto L42
    }

    public static final <T> T getSafely(CameraMetadata r2, CameraCharacteristics.Key<T> r3) {
        if (Build.VERSION.SDK_INT < 30) goto L9;
        if (Intrinsics.areEqual(r3, CameraMetadataSafeGetterKt$$ExternalSyntheticApiModelOutline0.m()) == false) goto L9;
        return (T) getControlZoomRatioRangeSafely(r2);
    L9:
        if (Intrinsics.areEqual(r3, CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE) == false) goto L13;
        return (T) getActiveArraySizeSafely(r2);
    L13:
        return (T) r2.get(r3);
    }
}
