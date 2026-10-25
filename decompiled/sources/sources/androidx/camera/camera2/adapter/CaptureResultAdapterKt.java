package androidx.camera.camera2.adapter;

import android.hardware.camera2.CaptureResult;
import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.FrameNumber;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureMetaData$AeMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AeState;
import androidx.camera.core.impl.CameraCaptureMetaData$AfMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AfState;
import androidx.camera.core.impl.CameraCaptureMetaData$AwbMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AwbState;
import androidx.camera.core.impl.CameraCaptureMetaData$FlashState;
import androidx.camera.core.impl.utils.ExifData;
import java.nio.BufferUnderflowException;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0002\u001a\f\u0010\t\u001a\u00020\n*\u00020\u0002H\u0002\u001a\f\u0010\u000b\u001a\u00020\f*\u00020\u0002H\u0002\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u0002H\u0002\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¨\u0006\u0015"}, d2 = {"getAfMode", "Landroidx/camera/core/impl/CameraCaptureMetaData$AfMode;", "Landroidx/camera/camera2/pipe/FrameMetadata;", "getAfState", "Landroidx/camera/core/impl/CameraCaptureMetaData$AfState;", "getAeMode", "Landroidx/camera/core/impl/CameraCaptureMetaData$AeMode;", "getAeState", "Landroidx/camera/core/impl/CameraCaptureMetaData$AeState;", "getAwbMode", "Landroidx/camera/core/impl/CameraCaptureMetaData$AwbMode;", "getAwbState", "Landroidx/camera/core/impl/CameraCaptureMetaData$AwbState;", "getFlashState", "Landroidx/camera/core/impl/CameraCaptureMetaData$FlashState;", "getTimestamp", _UrlKt.FRAGMENT_ENCODE_SET, "populateExifData", _UrlKt.FRAGMENT_ENCODE_SET, "exifData", "Landroidx/camera/core/impl/utils/ExifData$Builder;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureResultAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureResultAdapter.kt\nandroidx/camera/camera2/adapter/CaptureResultAdapterKt\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,278:1\n85#2,4:279\n85#2,4:283\n85#2,4:287\n85#2,4:291\n85#2,4:295\n85#2,4:299\n85#2,4:303\n119#2,4:307\n1#3:311\n*S KotlinDebug\n*F\n+ 1 CaptureResultAdapter.kt\nandroidx/camera/camera2/adapter/CaptureResultAdapterKt\n*L\n135#1:279,4\n151#1:283,4\n165#1:287,4\n180#1:291,4\n198#1:295,4\n211#1:299,4\n225#1:303,4\n243#1:307,4\n*E\n"})
/* loaded from: classes3.dex */
public abstract class CaptureResultAdapterKt {
    public static final /* synthetic */ CameraCaptureMetaData$AeMode access$getAeMode(FrameMetadata r0) {
        return getAeMode(r0);
    }

    public static final /* synthetic */ CameraCaptureMetaData$AeState access$getAeState(FrameMetadata r0) {
        return getAeState(r0);
    }

    public static final /* synthetic */ CameraCaptureMetaData$AfMode access$getAfMode(FrameMetadata r0) {
        return getAfMode(r0);
    }

    public static final /* synthetic */ CameraCaptureMetaData$AfState access$getAfState(FrameMetadata r0) {
        return getAfState(r0);
    }

    public static final /* synthetic */ CameraCaptureMetaData$AwbMode access$getAwbMode(FrameMetadata r0) {
        return getAwbMode(r0);
    }

    public static final /* synthetic */ CameraCaptureMetaData$AwbState access$getAwbState(FrameMetadata r0) {
        return getAwbState(r0);
    }

    public static final /* synthetic */ CameraCaptureMetaData$FlashState access$getFlashState(FrameMetadata r0) {
        return getFlashState(r0);
    }

    public static final /* synthetic */ long access$getTimestamp(FrameMetadata r2) {
        return getTimestamp(r2);
    }

    public static final /* synthetic */ void access$populateExifData(FrameMetadata r0, ExifData.Builder r1) {
        populateExifData(r0, r1);
    }

    private static final CameraCaptureMetaData$AeMode getAeMode(FrameMetadata r5) {
        Integer r0 = (Integer) r5.get(CaptureResult.CONTROL_AE_MODE);
        if (r0 != null) goto L6;
    L9:
        if (r0 != null) goto L12;
    L15:
        if (r0 != null) goto L18;
    L21:
        if (r0 != null) goto L24;
    L27:
        if (r0 != null) goto L30;
    L33:
        if (r0 == null) goto L35;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L40;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unknown AE mode (" + r0.intValue() + ") for " + FrameNumber.m272toStringimpl(r5.mo266getFrameNumberUgla2oM()) + '!');
    L40:
        return CameraCaptureMetaData$AeMode.UNKNOWN;
    L35:
        return CameraCaptureMetaData$AeMode.UNKNOWN;
    L30:
        if (r0.intValue() != 4) goto L33;
        return CameraCaptureMetaData$AeMode.ON_AUTO_FLASH_REDEYE;
    L24:
        if (r0.intValue() != 3) goto L27;
        return CameraCaptureMetaData$AeMode.ON_ALWAYS_FLASH;
    L18:
        if (r0.intValue() != 2) goto L21;
        return CameraCaptureMetaData$AeMode.ON_AUTO_FLASH;
    L12:
        if (r0.intValue() != 1) goto L15;
        return CameraCaptureMetaData$AeMode.ON;
    L6:
        if (r0.intValue() != 0) goto L9;
        return CameraCaptureMetaData$AeMode.OFF;
    }

    private static final CameraCaptureMetaData$AeState getAeState(FrameMetadata r5) {
        Integer r0 = (Integer) r5.get(CaptureResult.CONTROL_AE_STATE);
        if (r0 != null) goto L6;
    L9:
        if (r0 != null) goto L12;
    L13:
        if (r0 != null) goto L16;
    L18:
        if (r0 != null) goto L21;
    L24:
        if (r0 != null) goto L27;
    L30:
        if (r0 != null) goto L33;
    L36:
        if (r0 == null) goto L38;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L43;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unknown AE state (" + r0.intValue() + ") for " + FrameNumber.m272toStringimpl(r5.mo266getFrameNumberUgla2oM()) + '!');
    L43:
        return CameraCaptureMetaData$AeState.UNKNOWN;
    L38:
        return CameraCaptureMetaData$AeState.UNKNOWN;
    L33:
        if (r0.intValue() != 3) goto L36;
        return CameraCaptureMetaData$AeState.LOCKED;
    L27:
        if (r0.intValue() != 2) goto L30;
        return CameraCaptureMetaData$AeState.CONVERGED;
    L21:
        if (r0.intValue() != 4) goto L24;
        return CameraCaptureMetaData$AeState.FLASH_REQUIRED;
    L16:
        if (r0.intValue() != 5) goto L18;
    L45:
        return CameraCaptureMetaData$AeState.SEARCHING;
    L12:
        if (r0.intValue() == 1) goto L45;
    L6:
        if (r0.intValue() != 0) goto L9;
        return CameraCaptureMetaData$AeState.INACTIVE;
    }

    private static final CameraCaptureMetaData$AfMode getAfMode(FrameMetadata r5) {
        Integer r0 = (Integer) r5.get(CaptureResult.CONTROL_AF_MODE);
        if (r0 != null) goto L6;
    L7:
        if (r0 != null) goto L10;
    L12:
        if (r0 != null) goto L15;
    L16:
        if (r0 != null) goto L19;
    L21:
        if (r0 != null) goto L24;
    L25:
        if (r0 != null) goto L28;
    L30:
        if (r0 == null) goto L32;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L37;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unknown AF mode (" + r0.intValue() + ") for " + FrameNumber.m272toStringimpl(r5.mo266getFrameNumberUgla2oM()) + '!');
    L37:
        return CameraCaptureMetaData$AfMode.UNKNOWN;
    L32:
        return CameraCaptureMetaData$AfMode.UNKNOWN;
    L28:
        if (r0.intValue() != 3) goto L30;
    L39:
        return CameraCaptureMetaData$AfMode.ON_CONTINUOUS_AUTO;
    L24:
        if (r0.intValue() == 4) goto L39;
    L19:
        if (r0.intValue() != 2) goto L21;
    L41:
        return CameraCaptureMetaData$AfMode.ON_MANUAL_AUTO;
    L15:
        if (r0.intValue() == 1) goto L41;
    L10:
        if (r0.intValue() != 5) goto L12;
    L43:
        return CameraCaptureMetaData$AfMode.OFF;
    L6:
        if (r0.intValue() == 0) goto L43;
        goto L7
    }

    private static final CameraCaptureMetaData$AfState getAfState(FrameMetadata r5) {
        Integer r0 = (Integer) r5.get(CaptureResult.CONTROL_AF_STATE);
        if (r0 != null) goto L6;
    L9:
        if (r0 != null) goto L12;
    L13:
        if (r0 != null) goto L16;
    L18:
        if (r0 != null) goto L21;
    L24:
        if (r0 != null) goto L27;
    L30:
        if (r0 != null) goto L33;
    L36:
        if (r0 != null) goto L39;
    L42:
        if (r0 == null) goto L44;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L49;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unknown AF state (" + r0.intValue() + ") for " + FrameNumber.m272toStringimpl(r5.mo266getFrameNumberUgla2oM()) + '!');
    L49:
        return CameraCaptureMetaData$AfState.UNKNOWN;
    L44:
        return CameraCaptureMetaData$AfState.UNKNOWN;
    L39:
        if (r0.intValue() != 6) goto L42;
        return CameraCaptureMetaData$AfState.PASSIVE_NOT_FOCUSED;
    L33:
        if (r0.intValue() != 2) goto L36;
        return CameraCaptureMetaData$AfState.PASSIVE_FOCUSED;
    L27:
        if (r0.intValue() != 5) goto L30;
        return CameraCaptureMetaData$AfState.LOCKED_NOT_FOCUSED;
    L21:
        if (r0.intValue() != 4) goto L24;
        return CameraCaptureMetaData$AfState.LOCKED_FOCUSED;
    L16:
        if (r0.intValue() != 1) goto L18;
    L51:
        return CameraCaptureMetaData$AfState.SCANNING;
    L12:
        if (r0.intValue() == 3) goto L51;
    L6:
        if (r0.intValue() != 0) goto L9;
        return CameraCaptureMetaData$AfState.INACTIVE;
    }

    private static final CameraCaptureMetaData$AwbMode getAwbMode(FrameMetadata r5) {
        Integer r0 = (Integer) r5.get(CaptureResult.CONTROL_AWB_MODE);
        if (r0 != null) goto L6;
    L9:
        if (r0 != null) goto L12;
    L15:
        if (r0 != null) goto L18;
    L21:
        if (r0 != null) goto L24;
    L27:
        if (r0 != null) goto L30;
    L33:
        if (r0 != null) goto L36;
    L39:
        if (r0 != null) goto L42;
    L45:
        if (r0 != null) goto L48;
    L51:
        if (r0 != null) goto L54;
    L57:
        if (r0 == null) goto L59;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L64;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unknown AWB mode (" + r0.intValue() + ") for " + FrameNumber.m272toStringimpl(r5.mo266getFrameNumberUgla2oM()) + '!');
    L64:
        return CameraCaptureMetaData$AwbMode.UNKNOWN;
    L59:
        return CameraCaptureMetaData$AwbMode.UNKNOWN;
    L54:
        if (r0.intValue() != 8) goto L57;
        return CameraCaptureMetaData$AwbMode.SHADE;
    L48:
        if (r0.intValue() != 7) goto L51;
        return CameraCaptureMetaData$AwbMode.TWILIGHT;
    L42:
        if (r0.intValue() != 6) goto L45;
        return CameraCaptureMetaData$AwbMode.CLOUDY_DAYLIGHT;
    L36:
        if (r0.intValue() != 5) goto L39;
        return CameraCaptureMetaData$AwbMode.DAYLIGHT;
    L30:
        if (r0.intValue() != 4) goto L33;
        return CameraCaptureMetaData$AwbMode.WARM_FLUORESCENT;
    L24:
        if (r0.intValue() != 3) goto L27;
        return CameraCaptureMetaData$AwbMode.FLUORESCENT;
    L18:
        if (r0.intValue() != 2) goto L21;
        return CameraCaptureMetaData$AwbMode.INCANDESCENT;
    L12:
        if (r0.intValue() != 1) goto L15;
        return CameraCaptureMetaData$AwbMode.AUTO;
    L6:
        if (r0.intValue() != 0) goto L9;
        return CameraCaptureMetaData$AwbMode.OFF;
    }

    private static final CameraCaptureMetaData$AwbState getAwbState(FrameMetadata r5) {
        Integer r0 = (Integer) r5.get(CaptureResult.CONTROL_AWB_STATE);
        if (r0 != null) goto L6;
    L9:
        if (r0 != null) goto L12;
    L15:
        if (r0 != null) goto L18;
    L21:
        if (r0 != null) goto L24;
    L27:
        if (r0 == null) goto L29;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L34;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unknown AWB state (" + r0.intValue() + ") for " + FrameNumber.m272toStringimpl(r5.mo266getFrameNumberUgla2oM()) + '!');
    L34:
        return CameraCaptureMetaData$AwbState.UNKNOWN;
    L29:
        return CameraCaptureMetaData$AwbState.UNKNOWN;
    L24:
        if (r0.intValue() != 3) goto L27;
        return CameraCaptureMetaData$AwbState.LOCKED;
    L18:
        if (r0.intValue() != 2) goto L21;
        return CameraCaptureMetaData$AwbState.CONVERGED;
    L12:
        if (r0.intValue() != 1) goto L15;
        return CameraCaptureMetaData$AwbState.METERING;
    L6:
        if (r0.intValue() != 0) goto L9;
        return CameraCaptureMetaData$AwbState.INACTIVE;
    }

    private static final CameraCaptureMetaData$FlashState getFlashState(FrameMetadata r5) {
        Integer r0 = (Integer) r5.get(CaptureResult.FLASH_STATE);
        if (r0 != null) goto L6;
    L7:
        if (r0 != null) goto L10;
    L12:
        if (r0 != null) goto L15;
    L18:
        if (r0 != null) goto L21;
    L22:
        if (r0 != null) goto L25;
    L27:
        if (r0 == null) goto L29;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L34;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unknown flash state (" + r0.intValue() + ") for " + FrameNumber.m272toStringimpl(r5.mo266getFrameNumberUgla2oM()) + '!');
    L34:
        return CameraCaptureMetaData$FlashState.UNKNOWN;
    L29:
        return CameraCaptureMetaData$FlashState.UNKNOWN;
    L25:
        if (r0.intValue() != 4) goto L27;
    L36:
        return CameraCaptureMetaData$FlashState.FIRED;
    L21:
        if (r0.intValue() == 3) goto L36;
    L15:
        if (r0.intValue() != 2) goto L18;
        return CameraCaptureMetaData$FlashState.READY;
    L10:
        if (r0.intValue() != 1) goto L12;
    L38:
        return CameraCaptureMetaData$FlashState.NONE;
    L6:
        if (r0.intValue() == 0) goto L38;
        goto L7
    }

    private static final long getTimestamp(FrameMetadata r3) {
        return ((Number) r3.getOrDefault(CaptureResult.SENSOR_TIMESTAMP, -1L)).longValue();
    }

    private static final void populateExifData(FrameMetadata r3, ExifData.Builder r4) {
        Integer r0 = (Integer) r3.get(CaptureResult.JPEG_ORIENTATION);     // Catch: BufferUnderflowException -> L6
        if (r0 == null) goto L9;
        r4.setOrientationDegrees(r0.intValue());     // Catch: BufferUnderflowException -> L6
    L9:
        Long r02 = (Long) r3.get(CaptureResult.SENSOR_EXPOSURE_TIME);
        if (r02 == null) goto L12;
        r4.setExposureTimeNanos(r02.longValue());
    L12:
        Float r03 = (Float) r3.get(CaptureResult.LENS_APERTURE);
        if (r03 == null) goto L15;
        r4.setLensFNumber(r03.floatValue());
    L15:
        Integer r04 = (Integer) r3.get(CaptureResult.SENSOR_SENSITIVITY);
        if (r04 == null) goto L20;
        int r05 = r04.intValue();
        r4.setIso(r05);
        if (((Integer) r3.get(CaptureResult.CONTROL_POST_RAW_SENSITIVITY_BOOST)) == null) goto L20;
        r4.setIso(r05 * ((int) (r1.intValue() / 100.0f)));
    L20:
        Float r06 = (Float) r3.get(CaptureResult.LENS_FOCAL_LENGTH);
        if (r06 == null) goto L23;
        r4.setFocalLength(r06.floatValue());
    L23:
        Integer r32 = (Integer) r3.get(CaptureResult.CONTROL_AWB_MODE);
        if (r32 == null) goto L32;
        int r33 = r32.intValue();
        ExifData.WhiteBalanceMode r07 = ExifData.WhiteBalanceMode.AUTO;
        if (r33 != 0) goto L28;
        r07 = ExifData.WhiteBalanceMode.MANUAL;
    L28:
        r4.setWhiteBalanceMode(r07);
        return;
    L32:
        return;
    L6:
        Camera2Logger r08 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L9;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to get JPEG orientation.");
        goto L9
    }
}
