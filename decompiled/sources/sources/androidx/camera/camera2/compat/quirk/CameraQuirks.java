package androidx.camera.camera2.compat.quirk;

import android.util.Log;
import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.QuirkSettings;
import androidx.camera.core.impl.QuirkSettingsHolder;
import androidx.camera.core.impl.Quirks;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u001b\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CameraQuirks;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "streamConfigurationMapCompat", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;)V", "quirks", "Landroidx/camera/core/impl/Quirks;", "getQuirks", "()Landroidx/camera/core/impl/Quirks;", "quirks$delegate", "Lkotlin/Lazy;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraQuirks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraQuirks.kt\nandroidx/camera/camera2/compat/quirk/CameraQuirks\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,283:1\n136#2,4:284\n1#3:288\n*S KotlinDebug\n*F\n+ 1 CameraQuirks.kt\nandroidx/camera/camera2/compat/quirk/CameraQuirks\n*L\n45#1:284,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraQuirks {
    public static final Companion Companion = null;
    private final CameraMetadata cameraMetadata;
    private final Lazy quirks$delegate;
    private final StreamConfigurationMapCompat streamConfigurationMapCompat;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CameraQuirks$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$3nXzTEXW5hJTW6N-AsrAgNfo7qI, reason: not valid java name */
    public static Quirks m28$r8$lambda$3nXzTEXW5hJTW6NAsrAgNfo7qI(CameraQuirks r5) {
        QuirkSettings r0 = QuirkSettingsHolder.instance().get();
        ArrayList r1 = new ArrayList();
        CameraMetadata r2 = r5.cameraMetadata;
        if (r2 != null) goto L10;
        Camera2Logger r52 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L8;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to enable quirks: camera metadata injection failed");
    L8:
        return new Quirks(r1);
    L10:
        if (r0.shouldEnableQuirk(AeFpsRangeLegacyQuirk.class, AeFpsRangeLegacyQuirk.Companion.isEnabled(r2)) == false) goto L13;
        r1.add(new AeFpsRangeLegacyQuirk(r5.cameraMetadata));
    L13:
        if (r0.shouldEnableQuirk(AfRegionFlipHorizontallyQuirk.class, AfRegionFlipHorizontallyQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L16;
        r1.add(new AfRegionFlipHorizontallyQuirk());
    L16:
        if (r0.shouldEnableQuirk(AspectRatioLegacyApi21Quirk.class, AspectRatioLegacyApi21Quirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L19;
        r1.add(new AspectRatioLegacyApi21Quirk());
    L19:
        if (r0.shouldEnableQuirk(CamcorderProfileResolutionQuirk.class, CamcorderProfileResolutionQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L22;
        r1.add(new CamcorderProfileResolutionQuirk(r5.streamConfigurationMapCompat));
    L22:
        if (r0.shouldEnableQuirk(CameraNoResponseWhenEnablingFlashQuirk.class, CameraNoResponseWhenEnablingFlashQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L25;
        r1.add(new CameraNoResponseWhenEnablingFlashQuirk());
    L25:
        if (r0.shouldEnableQuirk(CaptureSessionStuckQuirk.class, CaptureSessionStuckQuirk.Companion.isEnabled()) == false) goto L28;
        r1.add(new CaptureSessionStuckQuirk());
    L28:
        if (r0.shouldEnableQuirk(CloseCaptureSessionOnVideoQuirk.class, CloseCaptureSessionOnVideoQuirk.Companion.isEnabled()) == false) goto L31;
        r1.add(new CloseCaptureSessionOnVideoQuirk());
    L31:
        if (r0.shouldEnableQuirk(ConfigureSurfaceToSecondarySessionFailQuirk.class, ConfigureSurfaceToSecondarySessionFailQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L34;
        r1.add(new ConfigureSurfaceToSecondarySessionFailQuirk());
    L34:
        if (r0.shouldEnableQuirk(FinalizeSessionOnCloseQuirk.class, FinalizeSessionOnCloseQuirk.Companion.isEnabled()) == false) goto L37;
        r1.add(new FinalizeSessionOnCloseQuirk());
    L37:
        if (r0.shouldEnableQuirk(FlashTooSlowQuirk.class, FlashTooSlowQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L40;
        r1.add(new FlashTooSlowQuirk());
    L40:
        if (r0.shouldEnableQuirk(ImageCaptureFailWithAutoFlashQuirk.class, ImageCaptureFailWithAutoFlashQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L43;
        r1.add(new ImageCaptureFailWithAutoFlashQuirk());
    L43:
        if (r0.shouldEnableQuirk(ImageCaptureFlashNotFireQuirk.class, ImageCaptureFlashNotFireQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L46;
        r1.add(new ImageCaptureFlashNotFireQuirk());
    L46:
        if (r0.shouldEnableQuirk(ImageCaptureWashedOutImageQuirk.class, ImageCaptureWashedOutImageQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L49;
        r1.add(new ImageCaptureWashedOutImageQuirk());
    L49:
        if (r0.shouldEnableQuirk(ImageCaptureWithFlashUnderexposureQuirk.class, ImageCaptureWithFlashUnderexposureQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L52;
        r1.add(new ImageCaptureWithFlashUnderexposureQuirk());
    L52:
        if (r0.shouldEnableQuirk(JpegHalCorruptImageQuirk.class, JpegHalCorruptImageQuirk.Companion.isEnabled()) == false) goto L54;
        r1.add(new JpegHalCorruptImageQuirk());
    L54:
        JpegCaptureDownsizingQuirk r22 = JpegCaptureDownsizingQuirk.INSTANCE;
        if (r0.shouldEnableQuirk(JpegCaptureDownsizingQuirk.class, r22.isEnabled(r5.cameraMetadata)) == false) goto L58;
        r1.add(r22);
    L58:
        if (r0.shouldEnableQuirk(PreviewOrientationIncorrectQuirk.class, PreviewOrientationIncorrectQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L61;
        r1.add(new PreviewOrientationIncorrectQuirk());
    L61:
        if (r0.shouldEnableQuirk(TextureViewIsClosedQuirk.class, TextureViewIsClosedQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L64;
        r1.add(new TextureViewIsClosedQuirk());
    L64:
        if (r0.shouldEnableQuirk(TorchFlashRequiredFor3aUpdateQuirk.class, TorchFlashRequiredFor3aUpdateQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L67;
        r1.add(new TorchFlashRequiredFor3aUpdateQuirk(r5.cameraMetadata));
    L67:
        if (r0.shouldEnableQuirk(YuvImageOnePixelShiftQuirk.class, YuvImageOnePixelShiftQuirk.Companion.isEnabled()) == false) goto L70;
        r1.add(new YuvImageOnePixelShiftQuirk());
    L70:
        if (r0.shouldEnableQuirk(PreviewStretchWhenVideoCaptureIsBoundQuirk.class, PreviewStretchWhenVideoCaptureIsBoundQuirk.Companion.isEnabled()) == false) goto L73;
        r1.add(new PreviewStretchWhenVideoCaptureIsBoundQuirk());
    L73:
        if (r0.shouldEnableQuirk(PreviewDelayWhenVideoCaptureIsBoundQuirk.class, PreviewDelayWhenVideoCaptureIsBoundQuirk.Companion.isEnabled()) == false) goto L76;
        r1.add(new PreviewDelayWhenVideoCaptureIsBoundQuirk());
    L76:
        if (r0.shouldEnableQuirk(QuickSuccessiveImageCaptureFailsRepeatingRequestQuirk.class, QuickSuccessiveImageCaptureFailsRepeatingRequestQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L79;
        r1.add(new QuickSuccessiveImageCaptureFailsRepeatingRequestQuirk());
    L79:
        if (r0.shouldEnableQuirk(ImageCaptureFailedWhenVideoCaptureIsBoundQuirk.class, ImageCaptureFailedWhenVideoCaptureIsBoundQuirk.Companion.isEnabled()) == false) goto L82;
        r1.add(new ImageCaptureFailedWhenVideoCaptureIsBoundQuirk());
    L82:
        if (r0.shouldEnableQuirk(TemporalNoiseQuirk.class, TemporalNoiseQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L85;
        r1.add(new TemporalNoiseQuirk());
    L85:
        if (r0.shouldEnableQuirk(ImageCaptureFailedForVideoSnapshotQuirk.class, ImageCaptureFailedForVideoSnapshotQuirk.Companion.isEnabled()) == false) goto L88;
        r1.add(new ImageCaptureFailedForVideoSnapshotQuirk());
    L88:
        if (r0.shouldEnableQuirk(AbnormalStreamWhenImageAnalysisBindWithTemplateRecordQuirk.class, AbnormalStreamWhenImageAnalysisBindWithTemplateRecordQuirk.Companion.isEnabled()) == false) goto L91;
        r1.add(new AbnormalStreamWhenImageAnalysisBindWithTemplateRecordQuirk());
    L91:
        if (r0.shouldEnableQuirk(UltraWideFlashCaptureUnderexposureQuirk.class, UltraWideFlashCaptureUnderexposureQuirk.Companion.isEnabled(r5.cameraMetadata)) == false) goto L93;
        r1.add(new UltraWideFlashCaptureUnderexposureQuirk());
    L93:
        Quirks r53 = new Quirks(r1);
        Logger.d("CameraQuirks", "camera2 CameraQuirks = " + Quirks.toString(r53));
        return r53;
    }

    static {
        Companion = new Companion(null);
    }

    public CameraQuirks(CameraMetadata r1, StreamConfigurationMapCompat r2) {
        this.cameraMetadata = r1;
        this.streamConfigurationMapCompat = r2;
        this.quirks$delegate = LazyKt.lazy(new CameraQuirks$$ExternalSyntheticLambda0(this));
    }

    public final Quirks getQuirks() {
        return (Quirks) this.quirks$delegate.getValue();
    }
}
