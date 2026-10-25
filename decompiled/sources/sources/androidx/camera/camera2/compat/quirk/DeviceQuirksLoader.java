package androidx.camera.camera2.compat.quirk;

import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.QuirkSettings;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/DeviceQuirksLoader;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "loadQuirks", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/Quirk;", "quirkSettings", "Landroidx/camera/core/impl/QuirkSettings;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceQuirksLoader {
    public static final DeviceQuirksLoader INSTANCE = null;

    static {
        INSTANCE = new DeviceQuirksLoader();
    }

    private DeviceQuirksLoader() {
    }

    public final List<Quirk> loadQuirks(QuirkSettings r3) {
        ArrayList r2 = new ArrayList();
        if (r3.shouldEnableQuirk(PixelJpegRSupportedQuirk.class, PixelJpegRSupportedQuirk.Companion.isEnabled()) == false) goto L6;
        r2.add(new PixelJpegRSupportedQuirk());
    L6:
        if (r3.shouldEnableQuirk(CloseCameraDeviceOnCameraGraphCloseQuirk.class, CloseCameraDeviceOnCameraGraphCloseQuirk.Companion.isEnabled()) == false) goto L9;
        r2.add(new CloseCameraDeviceOnCameraGraphCloseQuirk());
    L9:
        if (r3.shouldEnableQuirk(CrashWhenTakingPhotoWithAutoFlashAEModeQuirk.class, CrashWhenTakingPhotoWithAutoFlashAEModeQuirk.Companion.isEnabled()) == false) goto L12;
        r2.add(new CrashWhenTakingPhotoWithAutoFlashAEModeQuirk());
    L12:
        if (r3.shouldEnableQuirk(ControlZoomRatioRangeAssertionErrorQuirk.class, ControlZoomRatioRangeAssertionErrorQuirk.Companion.isEnabled()) == false) goto L15;
        r2.add(new ControlZoomRatioRangeAssertionErrorQuirk());
    L15:
        if (r3.shouldEnableQuirk(DisableAbortCapturesOnStopQuirk.class, DisableAbortCapturesOnStopQuirk.Companion.isEnabled()) == false) goto L18;
        r2.add(new DisableAbortCapturesOnStopQuirk());
    L18:
        if (r3.shouldEnableQuirk(DisableAbortCapturesOnStopWithSessionProcessorQuirk.class, DisableAbortCapturesOnStopWithSessionProcessorQuirk.Companion.isEnabled()) == false) goto L21;
        r2.add(new DisableAbortCapturesOnStopWithSessionProcessorQuirk());
    L21:
        if (r3.shouldEnableQuirk(FlashAvailabilityBufferUnderflowQuirk.class, FlashAvailabilityBufferUnderflowQuirk.Companion.isEnabled()) == false) goto L24;
        r2.add(new FlashAvailabilityBufferUnderflowQuirk());
    L24:
        if (r3.shouldEnableQuirk(ImageCapturePixelHDRPlusQuirk.class, ImageCapturePixelHDRPlusQuirk.Companion.isEnabled()) == false) goto L27;
        r2.add(new ImageCapturePixelHDRPlusQuirk());
    L27:
        if (r3.shouldEnableQuirk(InvalidVideoProfilesQuirk.class, InvalidVideoProfilesQuirk.Companion.isEnabled()) == false) goto L30;
        r2.add(new InvalidVideoProfilesQuirk());
    L30:
        if (r3.shouldEnableQuirk(ExcludedSupportedSizesQuirk.class, ExcludedSupportedSizesQuirk.Companion.isEnabled()) == false) goto L33;
        r2.add(new ExcludedSupportedSizesQuirk());
    L33:
        if (r3.shouldEnableQuirk(ExtraCroppingQuirk.class, ExtraCroppingQuirk.Companion.isEnabled()) == false) goto L36;
        r2.add(new ExtraCroppingQuirk());
    L36:
        if (r3.shouldEnableQuirk(ExtraSupportedOutputSizeQuirk.class, ExtraSupportedOutputSizeQuirk.Companion.isEnabled()) == false) goto L39;
        r2.add(new ExtraSupportedOutputSizeQuirk());
    L39:
        if (r3.shouldEnableQuirk(ExtraSupportedSurfaceCombinationsQuirk.class, ExtraSupportedSurfaceCombinationsQuirk.Companion.isEnabled()) == false) goto L42;
        r2.add(new ExtraSupportedSurfaceCombinationsQuirk());
    L42:
        if (r3.shouldEnableQuirk(Nexus4AndroidLTargetAspectRatioQuirk.class, Nexus4AndroidLTargetAspectRatioQuirk.Companion.isEnabled()) == false) goto L45;
        r2.add(new Nexus4AndroidLTargetAspectRatioQuirk());
    L45:
        if (r3.shouldEnableQuirk(PreviewPixelHDRnetQuirk.class, PreviewPixelHDRnetQuirk.Companion.isEnabled()) == false) goto L48;
        r2.add(new PreviewPixelHDRnetQuirk());
    L48:
        if (r3.shouldEnableQuirk(RepeatingStreamConstraintForVideoRecordingQuirk.class, RepeatingStreamConstraintForVideoRecordingQuirk.Companion.isEnabled()) == false) goto L51;
        r2.add(new RepeatingStreamConstraintForVideoRecordingQuirk());
    L51:
        if (r3.shouldEnableQuirk(StillCaptureFlashStopRepeatingQuirk.class, StillCaptureFlashStopRepeatingQuirk.Companion.isEnabled()) == false) goto L54;
        r2.add(new StillCaptureFlashStopRepeatingQuirk());
    L54:
        if (r3.shouldEnableQuirk(TorchIsClosedAfterImageCapturingQuirk.class, TorchIsClosedAfterImageCapturingQuirk.Companion.isEnabled()) == false) goto L57;
        r2.add(new TorchIsClosedAfterImageCapturingQuirk());
    L57:
        if (r3.shouldEnableQuirk(SurfaceOrderQuirk.class, SurfaceOrderQuirk.Companion.isEnabled()) == false) goto L60;
        r2.add(new SurfaceOrderQuirk());
    L60:
        if (r3.shouldEnableQuirk(CaptureSessionOnClosedNotCalledQuirk.class, CaptureSessionOnClosedNotCalledQuirk.Companion.isEnabled()) == false) goto L63;
        r2.add(new CaptureSessionOnClosedNotCalledQuirk());
    L63:
        if (r3.shouldEnableQuirk(ZslDisablerQuirk.class, ZslDisablerQuirk.Companion.load()) == false) goto L66;
        r2.add(new ZslDisablerQuirk());
    L66:
        if (r3.shouldEnableQuirk(SmallDisplaySizeQuirk.class, SmallDisplaySizeQuirk.Companion.load()) == false) goto L69;
        r2.add(new SmallDisplaySizeQuirk());
    L69:
        if (r3.shouldEnableQuirk(PreviewUnderExposureQuirk.class, PreviewUnderExposureQuirk.load()) == false) goto L71;
        r2.add(PreviewUnderExposureQuirk.INSTANCE);
    L71:
        return r2;
    }
}
