package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.ExtendableBuilder;
import androidx.camera.core.UseCase;
import androidx.camera.core.imagecapture.ImageCaptureControl;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.imagecapture.TakePictureManagerImpl;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public interface UseCaseConfig<T extends UseCase> extends TargetConfig<T>, ImageInputConfig {
    public static final Config.Option<CaptureConfig.OptionUnpacker> OPTION_CAPTURE_CONFIG_UNPACKER = null;
    public static final Config.Option<UseCaseConfigFactory.CaptureType> OPTION_CAPTURE_TYPE = null;
    public static final Config.Option<CaptureConfig> OPTION_DEFAULT_CAPTURE_CONFIG = null;
    public static final Config.Option<SessionConfig> OPTION_DEFAULT_SESSION_CONFIG = null;
    public static final Config.Option<Boolean> OPTION_HIGH_RESOLUTION_DISABLED = null;
    public static final Config.Option<Boolean> OPTION_IS_STRICT_FRAME_RATE_REQUIRED = null;
    public static final Config.Option<Boolean> OPTION_IS_VIDEO_QUALITY_SELECTOR_DEFAULT = null;
    public static final Config.Option<Integer> OPTION_PREVIEW_STABILIZATION_MODE = null;
    public static final Config.Option<Map<Size, Integer>> OPTION_RESOLUTION_TO_MAX_FRAME_RATES = null;
    public static final Config.Option<SessionConfig.OptionUnpacker> OPTION_SESSION_CONFIG_UNPACKER = null;
    public static final Config.Option<Integer> OPTION_SESSION_TYPE = null;
    public static final Config.Option<StreamUseCase> OPTION_STREAM_USE_CASE = null;
    public static final Config.Option<Integer> OPTION_SURFACE_OCCUPANCY_PRIORITY = null;
    public static final Config.Option<TakePictureManager.Provider> OPTION_TAKE_PICTURE_MANAGER_PROVIDER = null;
    public static final Config.Option<Range<Integer>> OPTION_TARGET_FRAME_RATE = null;
    public static final Config.Option<Integer> OPTION_VIDEO_STABILIZATION_MODE = null;
    public static final Config.Option<Boolean> OPTION_ZSL_DISABLED = null;

    /* renamed from: androidx.camera.core.impl.UseCaseConfig$1, reason: invalid class name */
    public class AnonymousClass1 implements TakePictureManager.Provider {
        final /* synthetic */ UseCaseConfig this$0;

        public AnonymousClass1(UseCaseConfig r1) {
            this.this$0 = r1;
        }

        @Override // androidx.camera.core.imagecapture.TakePictureManager.Provider
        public TakePictureManager newInstance(ImageCaptureControl r1) {
            return new TakePictureManagerImpl(r1);
        }
    }

    public interface Builder<T extends UseCase, C extends UseCaseConfig<T>, B> extends ExtendableBuilder<T> {
        C getUseCaseConfig();
    }

    static {
        OPTION_DEFAULT_SESSION_CONFIG = Config.Option.create("camerax.core.useCase.defaultSessionConfig", SessionConfig.class);
        OPTION_DEFAULT_CAPTURE_CONFIG = Config.Option.create("camerax.core.useCase.defaultCaptureConfig", CaptureConfig.class);
        OPTION_SESSION_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.sessionConfigUnpacker", SessionConfig.OptionUnpacker.class);
        OPTION_CAPTURE_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.captureConfigUnpacker", CaptureConfig.OptionUnpacker.class);
        Class r1 = Integer.TYPE;
        OPTION_SURFACE_OCCUPANCY_PRIORITY = Config.Option.create("camerax.core.useCase.surfaceOccupancyPriority", r1);
        OPTION_SESSION_TYPE = Config.Option.create("camerax.core.useCase.sessionType", r1);
        OPTION_TARGET_FRAME_RATE = Config.Option.create("camerax.core.useCase.targetFrameRate", Range.class);
        OPTION_IS_STRICT_FRAME_RATE_REQUIRED = Config.Option.create("camerax.core.useCase.isStrictFrameRateRequired", Boolean.class);
        OPTION_RESOLUTION_TO_MAX_FRAME_RATES = Config.Option.create("camerax.core.useCase.resolutionToMaxFrameRate", Map.class);
        Class r3 = Boolean.TYPE;
        OPTION_ZSL_DISABLED = Config.Option.create("camerax.core.useCase.zslDisabled", r3);
        OPTION_HIGH_RESOLUTION_DISABLED = Config.Option.create("camerax.core.useCase.highResolutionDisabled", r3);
        OPTION_CAPTURE_TYPE = Config.Option.create("camerax.core.useCase.captureType", UseCaseConfigFactory.CaptureType.class);
        OPTION_PREVIEW_STABILIZATION_MODE = Config.Option.create("camerax.core.useCase.previewStabilizationMode", r1);
        OPTION_VIDEO_STABILIZATION_MODE = Config.Option.create("camerax.core.useCase.videoStabilizationMode", r1);
        OPTION_IS_VIDEO_QUALITY_SELECTOR_DEFAULT = Config.Option.create("camerax.core.useCase.isVideoQualitySelectorDefault", Boolean.class);
        OPTION_TAKE_PICTURE_MANAGER_PROVIDER = Config.Option.create("camerax.core.useCase.takePictureManagerProvider", TakePictureManager.Provider.class);
        OPTION_STREAM_USE_CASE = Config.Option.create("camerax.core.useCase.streamUseCase", StreamUseCase.class);
    }

    default CaptureConfig.OptionUnpacker getCaptureOptionUnpacker(CaptureConfig.OptionUnpacker r2) {
        return (CaptureConfig.OptionUnpacker) retrieveOption(OPTION_CAPTURE_CONFIG_UNPACKER, r2);
    }

    default UseCaseConfigFactory.CaptureType getCaptureType() {
        return (UseCaseConfigFactory.CaptureType) retrieveOption(OPTION_CAPTURE_TYPE);
    }

    default int getCustomMaxFrameRate(Size r3) {
        Map r2 = (Map) retrieveOption(OPTION_RESOLUTION_TO_MAX_FRAME_RATES, null);
        if (r2 != null) goto L5;
        return Integer.MAX_VALUE;
    L5:
        if (r2.containsKey(r3) == false) goto L10;
        Integer r22 = (Integer) r2.get(r3);
        Objects.requireNonNull(r22);
        return r22.intValue();
    L10:
        return Integer.MAX_VALUE;
    }

    default CaptureConfig getDefaultCaptureConfig(CaptureConfig r2) {
        return (CaptureConfig) retrieveOption(OPTION_DEFAULT_CAPTURE_CONFIG, r2);
    }

    default SessionConfig getDefaultSessionConfig() {
        return (SessionConfig) retrieveOption(OPTION_DEFAULT_SESSION_CONFIG);
    }

    default SessionConfig getDefaultSessionConfig(SessionConfig r2) {
        return (SessionConfig) retrieveOption(OPTION_DEFAULT_SESSION_CONFIG, r2);
    }

    default int getPreviewStabilizationMode() {
        return ((Integer) retrieveOption(OPTION_PREVIEW_STABILIZATION_MODE, 0)).intValue();
    }

    default SessionConfig.OptionUnpacker getSessionOptionUnpacker(SessionConfig.OptionUnpacker r2) {
        return (SessionConfig.OptionUnpacker) retrieveOption(OPTION_SESSION_CONFIG_UNPACKER, r2);
    }

    default int getSessionType(int r2) {
        return ((Integer) retrieveOption(OPTION_SESSION_TYPE, Integer.valueOf(r2))).intValue();
    }

    default StreamUseCase getStreamUseCase() {
        StreamUseCase r2 = (StreamUseCase) retrieveOption(OPTION_STREAM_USE_CASE, StreamUseCase.DEFAULT);
        Objects.requireNonNull(r2);
        return r2;
    }

    default int getSurfaceOccupancyPriority(int r2) {
        return ((Integer) retrieveOption(OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(r2))).intValue();
    }

    default TakePictureManager.Provider getTakePictureManagerProvider() {
        TakePictureManager.Provider r2 = (TakePictureManager.Provider) retrieveOption(OPTION_TAKE_PICTURE_MANAGER_PROVIDER, new AnonymousClass1(this));
        Objects.requireNonNull(r2);
        return r2;
    }

    default Range<Integer> getTargetFrameRate(Range<Integer> r2) {
        return (Range) retrieveOption(OPTION_TARGET_FRAME_RATE, r2);
    }

    default int getVideoStabilizationMode() {
        return ((Integer) retrieveOption(OPTION_VIDEO_STABILIZATION_MODE, 0)).intValue();
    }

    default boolean hasTargetFrameRate() {
        return containsOption(OPTION_TARGET_FRAME_RATE);
    }

    default boolean isHighResolutionDisabled(boolean r2) {
        return ((Boolean) retrieveOption(OPTION_HIGH_RESOLUTION_DISABLED, Boolean.valueOf(r2))).booleanValue();
    }

    default boolean isStrictFrameRateRequired() {
        Boolean r2 = (Boolean) retrieveOption(OPTION_IS_STRICT_FRAME_RATE_REQUIRED, Boolean.FALSE);
        Objects.requireNonNull(r2);
        return r2.booleanValue();
    }

    default boolean isZslDisabled(boolean r2) {
        return ((Boolean) retrieveOption(OPTION_ZSL_DISABLED, Boolean.valueOf(r2))).booleanValue();
    }
}
