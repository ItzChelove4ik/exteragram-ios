package androidx.camera.core.impl;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageReaderProxyProvider;
import androidx.camera.core.impl.Config;
import androidx.camera.core.internal.IoConfig;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final class ImageCaptureConfig implements UseCaseConfig<ImageCapture>, ImageOutputConfig, IoConfig {
    public static final Config.Option<Integer> OPTION_BUFFER_FORMAT = null;
    public static final Config.Option<CaptureBundle> OPTION_CAPTURE_BUNDLE = null;
    public static final Config.Option<Integer> OPTION_FLASH_MODE = null;
    public static final Config.Option<Integer> OPTION_FLASH_TYPE = null;
    public static final Config.Option<Integer> OPTION_IMAGE_CAPTURE_MODE = null;
    public static final Config.Option<ImageReaderProxyProvider> OPTION_IMAGE_READER_PROXY_PROVIDER = null;
    public static final Config.Option<Integer> OPTION_JPEG_COMPRESSION_QUALITY = null;
    public static final Config.Option<Integer> OPTION_MAX_CAPTURE_STAGES = null;
    public static final Config.Option<Integer> OPTION_OUTPUT_FORMAT = null;
    public static final Config.Option<Boolean> OPTION_POSTVIEW_ENABLED = null;
    public static final Config.Option<ResolutionSelector> OPTION_POSTVIEW_RESOLUTION_SELECTOR = null;
    public static final Config.Option<ImageCapture.ScreenFlash> OPTION_SCREEN_FLASH = null;
    public static final Config.Option<Boolean> OPTION_USE_SOFTWARE_JPEG_ENCODER = null;
    private final OptionsBundle mConfig;

    static {
        Class r1 = Integer.TYPE;
        OPTION_IMAGE_CAPTURE_MODE = Config.Option.create("camerax.core.imageCapture.captureMode", r1);
        OPTION_FLASH_MODE = Config.Option.create("camerax.core.imageCapture.flashMode", r1);
        OPTION_CAPTURE_BUNDLE = Config.Option.create("camerax.core.imageCapture.captureBundle", CaptureBundle.class);
        OPTION_BUFFER_FORMAT = Config.Option.create("camerax.core.imageCapture.bufferFormat", Integer.class);
        OPTION_OUTPUT_FORMAT = Config.Option.create("camerax.core.imageCapture.outputFormat", Integer.class);
        OPTION_MAX_CAPTURE_STAGES = Config.Option.create("camerax.core.imageCapture.maxCaptureStages", Integer.class);
        OPTION_IMAGE_READER_PROXY_PROVIDER = Config.Option.create("camerax.core.imageCapture.imageReaderProxyProvider", ImageReaderProxyProvider.class);
        OPTION_USE_SOFTWARE_JPEG_ENCODER = Config.Option.create("camerax.core.imageCapture.useSoftwareJpegEncoder", Boolean.TYPE);
        OPTION_FLASH_TYPE = Config.Option.create("camerax.core.imageCapture.flashType", r1);
        OPTION_JPEG_COMPRESSION_QUALITY = Config.Option.create("camerax.core.imageCapture.jpegCompressionQuality", r1);
        OPTION_SCREEN_FLASH = Config.Option.create("camerax.core.imageCapture.screenFlash", ImageCapture.ScreenFlash.class);
        OPTION_POSTVIEW_RESOLUTION_SELECTOR = Config.Option.create("camerax.core.useCase.postviewResolutionSelector", ResolutionSelector.class);
        OPTION_POSTVIEW_ENABLED = Config.Option.create("camerax.core.useCase.isPostviewEnabled", Boolean.class);
    }

    public ImageCaptureConfig(OptionsBundle r1) {
        this.mConfig = r1;
    }

    public int getCaptureMode() {
        return ((Integer) retrieveOption(OPTION_IMAGE_CAPTURE_MODE)).intValue();
    }

    @Override // androidx.camera.core.impl.ReadableConfig
    public Config getConfig() {
        return this.mConfig;
    }

    public int getFlashMode(int r2) {
        return ((Integer) retrieveOption(OPTION_FLASH_MODE, Integer.valueOf(r2))).intValue();
    }

    public int getFlashType(int r2) {
        return ((Integer) retrieveOption(OPTION_FLASH_TYPE, Integer.valueOf(r2))).intValue();
    }

    public ImageReaderProxyProvider getImageReaderProxyProvider() {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(retrieveOption(OPTION_IMAGE_READER_PROXY_PROVIDER, null));
        return null;
    }

    @Override // androidx.camera.core.impl.ImageInputConfig
    public int getInputFormat() {
        return ((Integer) retrieveOption(ImageInputConfig.OPTION_INPUT_FORMAT)).intValue();
    }

    public Executor getIoExecutor(Executor r2) {
        return (Executor) retrieveOption(IoConfig.OPTION_IO_EXECUTOR, r2);
    }

    public ImageCapture.ScreenFlash getScreenFlash() {
        return (ImageCapture.ScreenFlash) retrieveOption(OPTION_SCREEN_FLASH, null);
    }

    public boolean hasCaptureMode() {
        return containsOption(OPTION_IMAGE_CAPTURE_MODE);
    }
}
